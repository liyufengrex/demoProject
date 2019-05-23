package com.rex.myapplication.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import com.rex.myapplication.R;
import com.rex.myapplication.trys.ViewInflat;
import java.lang.reflect.Field;

public class Main2Activity extends AppCompatActivity {

    @ViewInflat(R.id.tv_1)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        superInitView(this);

        tv.setText("sdfsdfds");
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
