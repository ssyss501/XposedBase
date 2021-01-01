package com.example.xposedbase.hook.Function;

import android.app.Activity;
import android.app.AndroidAppHelper;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.os.Parcel;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.example.xposedbase.hook.BaseHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import com.example.xposedbase.hook.KktDatabase.DataBaseResourceCrypto;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

public class twitter extends BaseHook {
    String PACKAGE_NAME = "com.twitter.android";
    public Context ctx1;


    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
            if (!lpparam.packageName.contains("com.twitter.android")) {  //判断包名
               // XposedBridge.log("没找到");
                return;
            }
            XposedBridge.log("---》找到了：" + lpparam.packageName);
            XposedHelpers.findAndHookMethod(ContextWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {




                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                    try {
//                        Class<?> hookclass = ((Context) param.args[0]).getClassLoader().loadClass("com.twitter.tweetview.TweetView");//类方法
//                        Field[] field = hookclass.getDeclaredFields();
//                        XposedBridge.log("寻找成功");
//                        XposedHelpers.findAndHookMethod(hookclass, "setTruncateText", CharSequence.class,  new XC_MethodHook() { //方法名
//
//                            public void afterHookedMethod(MethodHookParam param) {
//                                XposedBridge.log("---->开始hook！<----：");
//                                XposedBridge.log((String) param.args[0]);
//                                String a = new String((byte[]) param.args[1]);
//                                XposedBridge.log("---->传参" + a + "<----：");
//                                XposedBridge.log("---->加密后数据:" + ((String) param.getResult()) + "<----：");
//                            }
//                        });

                        Class<?> hookclass = ((Context) param.args[0]).getClassLoader().loadClass("com.twitter.tweetview.TweetView");//类方法
                        Class<?> c_hh8 = ((Context) param.args[0]).getClassLoader().loadClass("hh8");//类方法
                        Class<?> c_b = ((Context) param.args[0]).getClassLoader().loadClass("hh8$b");//类方法
                        Class<?> c_i5 = ((Context) param.args[0]).getClassLoader().loadClass("com.twitter.android.TweetDetailActivity");//类方法
                        XposedBridge.log("c_hh8"  + c_hh8);
                        XposedBridge.log("c_i5"  + c_i5);

                        XposedHelpers.findAndHookConstructor(c_hh8,c_b, new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                               if(param.args.length==0)
                               {
                                   return;
                               }
                                Object obj1 = (Object) XposedHelpers.getObjectField(param.args[0],"a");
                                Object obj2 = (Object) XposedHelpers.getObjectField(obj1,"y");
                                String obj3 = (String) XposedHelpers.getObjectField(obj2,"a0");
                                XposedBridge.log("内容为：" + obj3);

                                Object fields3 = (Object) XposedHelpers.getObjectField(param.args[0],"b");
                               Object obj = param.thisObject;
                                Field[] fields = param.thisObject.getClass().getDeclaredFields();
                                for (int i = 0; i < fields.length; i++) {
                                    XposedBridge.log("======【属性】==args[1]=" + fields[i].getName());
                                }

                                XposedBridge.log("obj"  + "Aaa");

                            }
                        });
                      //  XposedBridge.log("c_b"  + c_b);

//                        XposedHelpers.findAndHookMethod(c_i5,"i5",c_hh8, new XC_MethodHook() {
//                            @Override
//                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                super.beforeHookedMethod(param);
//                                XposedBridge.log("efh进来了" );
//
//
//                            }
//
//                        });

                        XposedBridge.hookAllConstructors(c_i5,new XC_MethodHook() {

                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                XposedBridge.log("TweetDetailActivity进来了" );
                            }
                        });

                        XposedBridge.hookAllConstructors(c_hh8,new XC_MethodHook() {

                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                                Object obj = param.args[0];
//                                XposedBridge.log((String) obj);
//                                Field  body  =  param.args[0].getClass().getField("");
                                XposedBridge.log("c_hh8 =" + param.args[0]);

                                Log.i("Dump Stack: ", "---------------start----------------");
                            Throwable ex = new Throwable();
                            StackTraceElement[] stackElements = ex.getStackTrace();
                            if (stackElements != null) {
                                for (int i = 0; i < stackElements.length; i++) {

                                    Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
                                            +"----"+stackElements[i].getFileName()
                                            +"----" + stackElements[i].getLineNumber()
                                            +"----" +stackElements[i].getMethodName());
                                }
                            }
                            Log.i("Dump Stack: ", "---------------over----------------");




                            }
                        });


                    } catch (Exception e) {
                        XposedBridge.log("寻找失败");
                    }
                }
            });
        }




//
//    @Override
//    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        super.onLoadPackage(lpparam);
//
//
//        if (PACKAGE_NAME.equals(lpparam.packageName)) {
//            XposedBridge.log("twitter进来了");
//
//            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    ctx1 = (Context) param.args[0];
//                    XposedBridge.log("Context= " + ctx1);
//
//                    Class<?> c_TweetView = ((Context) param.args[0]).getClassLoader().loadClass("com.twitter.tweetview.TweetView");//类方法
//                  //  Class<?> c_TweetView = XposedHelpers.findClass("com.twitter.tweetview.TweetView", lpparam.classLoader);
//                    XposedBridge.log("c_TweetView="+c_TweetView);
//                    XposedHelpers.findAndHookMethod(c_TweetView, "setTruncateText",CharSequence.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//
//                            XposedBridge.log("tw 666");
//
//                            System.out.println(123);
//
//                        }
//                    });
//
//
//                }
//            });
//




}