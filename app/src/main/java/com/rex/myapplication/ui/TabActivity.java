package com.rex.myapplication.ui;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.rex.myapplication.R;

public class TabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.myTabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Tab 1").setIcon(R.mipmap.ic_launcher));

        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));

        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));

        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));

        tabLayout.addTab(tabLayout.newTab().setText("Tab 6"));

        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

    }
}
