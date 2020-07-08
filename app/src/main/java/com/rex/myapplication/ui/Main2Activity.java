package com.rex.myapplication.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.rex.myapplication.R;
import com.rex.myapplication.statusbar.StatusBarUtil;
import com.rex.myapplication.trys.ViewInflat;
import java.lang.reflect.Field;

public class Main2Activity extends AppCompatActivity {

//    @ViewInflat(R.id.tv_1)
//    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding

        StatusBarUtil.setRootViewFitsSystemWindows(this,false);

        //设置状态栏透明

        StatusBarUtil.setTranslucentStatus(this);

        if (!StatusBarUtil.setStatusBarDarkTheme(this, true))
        {//如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,       
            // 这样半透明+白=灰, 状态栏的文字能看得清       
            StatusBarUtil.setStatusBarColor(this,0x55000000);
        }




        superInitView(this);

//        tv.setText("sdfsdfds");
    }

    private void superInitView(Activity activity){
        try{
            detailInitView(activity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void detailInitView(Activity activity) throws Exception{

        Class<Activity> activityClass = (Class<Activity>) activity.getClass();
        Field[] fields = activityClass.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(ViewInflat.class)){
                ViewInflat viewInflat = field.getAnnotation(ViewInflat.class);
                int id = viewInflat.value();
                if(id > 0){
                    field.setAccessible(true);
                    field.set(activity,activity.findViewById(id));
                }
            }
        }

    }
}
