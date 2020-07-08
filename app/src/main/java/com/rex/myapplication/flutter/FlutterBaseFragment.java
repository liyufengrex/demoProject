/*
package com.rex.myapplication.flutter;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import io.flutter.facade.FlutterFragment;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterView;

import static android.content.Context.BATTERY_SERVICE;


public class FlutterBaseFragment extends FlutterFragment {

    private static final String BATTERY_CHANNEL = "samples.flutter.io/battery";
    private static final String EVENT_CHANNEL = "samples.flutter.io/event";

    public static FlutterBaseFragment newInstance(String route) {

        Bundle args = new Bundle();
        args.putString(ARG_ROUTE, route);
        FlutterBaseFragment fragment = new FlutterBaseFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //提供给flutter调用原生
        new MethodChannel((FlutterView) getView(), BATTERY_CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, final MethodChannel.Result result) {
                        if (call.method.equals("getBatteryLevel")) {
                            int batteryLevel = getBatteryLevel();

                            if (batteryLevel != -1) {
                                result.success(batteryLevel);
                            } else {
                                result.error("UNAVAILABLE", "Battery level not available.", null);
                            }
                        } else {
                            result.notImplemented();
                        }
                    }
                });

        //原生主动推送数据给flutter
        new EventChannel((FlutterView) getView(), EVENT_CHANNEL).setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, final EventChannel.EventSink events) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                events.success("当前时间毫秒" + System.currentTimeMillis() / 1000);
                            }
                        });
                    }
                }, 1000, 1000);
            }

            @Override
            public void onCancel(Object arguments) {
            }
        });

    }

    private int getBatteryLevel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getActivity().getSystemService(BATTERY_SERVICE);
            return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getActivity().getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            return (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }
    }
}

*/
