package com.nesql;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)// 声明注解的作用域  。Type意味着放在类上
@Retention(RetentionPolicy.RUNTIME) // 声明生命周期
public @interface DbTable {

    String value();

}
