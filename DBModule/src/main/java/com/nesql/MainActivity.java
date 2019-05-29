package com.nesql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.hw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(1,"依依","qqqqqq11");
                BaseDao baseDao = BaseDaoFactory.getInstance().getBaseDao(User.class);
                baseDao.insert(user);
            }
        });
    }
}
