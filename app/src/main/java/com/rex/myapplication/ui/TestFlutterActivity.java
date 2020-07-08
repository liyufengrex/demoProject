/*
package com.rex.myapplication.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.rex.myapplication.R;
//import com.rex.myapplication.flutter.FlutterBaseFragment;
//import io.flutter.facade.Flutter;
//import io.flutter.facade.FlutterFragment;

public class TestFlutterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flutter);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButton(v);
            }
        });
    }

    private void onClickButton(View target){
//        createFlutterView();
        createFlutterFragment();
    }

    private void createFlutterView(){
        View flutterView = Flutter.createView(
                TestFlutterActivity.this,
                getLifecycle(),
                "route1"
        );
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(600, 800);
        layout.leftMargin = 100;
        layout.topMargin = 200;
        addContentView(flutterView, layout);
    }

    private void createFlutterFragment(){
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        FlutterFragment fragment = FlutterBaseFragment.newInstance("route4");
        tx.replace(R.id.someContainer, fragment);
        tx.commit();
    }


}
*/
