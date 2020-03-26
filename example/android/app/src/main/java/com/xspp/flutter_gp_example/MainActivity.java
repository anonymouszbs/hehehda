package com.xspp.flutter_gp_example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.annotation.NonNull;

import java.nio.channels.Channel;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;



public class MainActivity extends FlutterActivity {
  private static final String BATTERY_CHANNEL = "com.allword.flutter/event";
  private static final  String CHANNAL = "flutter_plugin_google";
  String guid = "吹牛逼";
  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    GeneratedPluginRegistrant.registerWith(flutterEngine);
    getGuid();
    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNAL).setMethodCallHandler(new MethodChannel.MethodCallHandler(){

      @Override
      public void onMethodCall(MethodCall call, MethodChannel.Result result) {

        if(call.method.equals("getGuId")){

          result.success(guid);
        }
      }
    });
  }
//  @Override
//  public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    getGuid();
//    //FlutterEventChannel.create(getFlutterView());
//    // GeneratedPluginRegistrant.registerWith(this);
//
//
////    new MethodChannel(getFlutterView(),CHANNAL).setMethodCallHandler( new MethodChannel.MethodCallHandler(){
////
////      @Override
////      public void onMethodCall(MethodCall call, MethodChannel.Result result) {
////
////        if(call.method.equals("getGuId")){
////
////          result.success(guid);
////        }
////      }
////    });
////
////  //  GeneratedPluginRegistrant.registerWith(this);
////
////
////
////
////
////
////
////
////
////
////
////    new EventChannel(getFlutterView(), BATTERY_CHANNEL).setStreamHandler(
////            new EventChannel.StreamHandler() {
////              private BroadcastReceiver chargingStateChangeReceiver;
////              @Override
////              public void onListen(Object args, final EventChannel.EventSink events) {
////                chargingStateChangeReceiver = createChargingStateChangeReceiver(events);
////                registerReceiver(
////                        chargingStateChangeReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
////                // Log.w(TAG, "adding listener");
////              }
////
////              @Override
////              public void onCancel(Object args) {
////                unregisterReceiver(chargingStateChangeReceiver);
////                chargingStateChangeReceiver = null;
////                // Log.w(TAG, "cancelling listener");
////              }
////            }
////    );
////  }
//
//  }
  public void getGuid(){
    new Thread(new Runnable() {
      String advertisingId;
      public void run() {
        try {
          AdvertisingIdClient.AdInfo adInfo = AdvertisingIdClient
                  .getAdvertisingIdInfo(MainActivity.this);
          advertisingId = adInfo.getId();
          guid = advertisingId;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private BroadcastReceiver createChargingStateChangeReceiver(final EventChannel.EventSink events) {
    return new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {

        try {
          Bundle extras = intent.getExtras();
          if (extras != null) {
            String referrer = extras.getString("referrer");
            events.success(referrer);
          }else{}
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
  }
}
