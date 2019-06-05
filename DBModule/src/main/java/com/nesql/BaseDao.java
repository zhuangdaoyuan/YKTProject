package com.nesql;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BaseDao<T> implements IBaseDao<T>{

    // 持有数据库引用
    private SQLiteDatabase sqLiteDatabase;

    // 表名
    private String tableName;

    private Class<T> beanClazz;

    // 缓存map key（数据库对应的字段名），value（成员变量）
    private HashMap<String,Field> cacheMap;

    // 是否已经初始化
    private boolean isInit = false;

    protected boolean init(SQLiteDatabase sqLiteDatabase,Class<T> beanClazz){
        this.sqLiteDatabase = sqLiteDatabase;
        this.beanClazz = beanClazz;
        if(!isInit){
            // 先要得到表名
            tableName = this.beanClazz.getAnnotation(DbTable.class).value();
            if(sqLiteDatabase == null || !sqLiteDatabase.isOpen() || tableName == null){
                return false;
            }
            cacheMap = new HashMap<>();
            initCacheMap();
            // 创建数据表
            sqLiteDatabase.execSQL(getCreateTableSql());
            isInit = true;
        }
        return false;
    }

    private void initCacheMap() {
        // 获取类对象的所有成员变量
        Field[] declaredFields = beanClazz.getDeclaredFields();
        for(Field declaredField : declaredFields){
            String filedName = declaredField.getAnnotation(DbField.class).value();
            cacheMap.put(filedName,declaredField);
        }
    }

    private String getCreateTableSql() {
        // create table if not exists tableName(id integer,name varchar(20))
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists ");
        stringBuffer.append(tableName+"(");
        //获取beanClazz对象的所有成员变量
        Field[] declaredFields = beanClazz.getDeclaredFields();
        for(Field declaredField : declaredFields){
            Class type = declaredField.getType();
            String filedName = declaredField.getAnnotation(DbField.class).value();
            if(type == String.class){
                stringBuffer.append(filedName+" TEXT ,");
            }else if(type == Integer.class){
                stringBuffer.append(filedName+" INTEGER ,");
            }else if(type == Long.class){
                stringBuffer.append(filedName+" LONG ,");
            }else{
                // 不支持类型
                continue;
            }
        }
        // 去掉最后的逗号
        if(stringBuffer.charAt(stringBuffer.length()-1) == ','){
            stringBuffer.deleteCharAt(stringBuffer.length()-1);
        }
        stringBuffer.append(")");
        Log.e("sql语句",stringBuffer.toString());
        return stringBuffer.toString();
    }

    @Override
    public long insert(T bean) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id","1");
        Map<String,String> map = getValue(bean);
        // 将map转换成contentvalues
        ContentValues contentValues = getContentValues(map);
        long result = sqLiteDatabase.insert(tableName,null,contentValues);
        // 获取对象中的值
        Log.e("====>","成功插入"+result+"条数据");
        return result;
    }

    @Override
    public ResultSet select(int id) {
        // TODO: 2019/6/3  
        return null;
    }

    @Override
    public ResultSet selectAll() {
        // TODO: 2019/6/3  
        return null;
    }

    private ContentValues getContentValues(Map<String, String> map) {
        ContentValues contentValues = new ContentValues();
        // 拿到map的keyset
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            if(value != null){
                contentValues.put(key,value);
            }
        }
        return contentValues;
    }

    // 获取对象的属性值，并且按照contentValue的方式存储起来
    private Map<String,String> getValue(T bean) {
        Map<String,String> map = new HashMap<>();
        // 从缓存map中获取成员变量
        Iterator<Field> iterator = cacheMap.values().iterator();
        while (iterator.hasNext()){
            // 拿到成员变量对象
            Field field = iterator.next();
            // 打开权限
            field.setAccessible(true);
            // 从成员变量中获取field值
            try {
                Object o = field.get(bean);
                if(o == null){
                    continue;
                }
                String value = o.toString();
                String key = field.getAnnotation(DbField.class).value();
                if(!TextUtils.isEmpty(value) && !TextUtils.isEmpty(key)){
                    map.put(key,value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


}
