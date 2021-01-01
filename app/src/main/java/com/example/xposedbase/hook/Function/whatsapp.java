package com.example.xposedbase.hook.Function;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import com.example.xposedbase.hook.BaseHook;

import org.json.JSONObject;

import java.io.File;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class whatsapp extends BaseHook {
    String PACKAGE_NAME = "com.whatsapp";
    public Context ctx1;
    public Class ccc = null;
    public whatsapp.onClickReceiver oCR = null;

    //接收界面发来的广播
    class onClickReceiver extends BroadcastReceiver {
        ClassLoader cl;

        public onClickReceiver(ClassLoader cl) {
            this.cl = cl;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            XposedBridge.log("onClickReceiver 接收");
            String action = intent.getAction();

            if ("com.Xposebase.onClick".equals(action)) {
                String messagae = intent.getStringExtra("messagae");
                String phone = intent.getStringExtra("phone");
                final Class<?> c_UserJid = XposedHelpers.findClass("com.whatsapp.jid.UserJid", cl);
                try {
                    Object newInstance = XposedHelpers.newInstance(c_UserJid, phone);
                    final Class<?> c_0M6 = XposedHelpers.findClass("X.0M6", cl);
                    final Class<?> c_1Uq = XposedHelpers.findClass("X.1Uq", cl);
                    final Class<?> c_042 = XposedHelpers.findClass("X.042", cl);
                    final Class<?> c_0Qb = XposedHelpers.findClass("X.0Qb", cl);

                    Class<?> clazz = XposedHelpers.findClass("com.whatsapp.Conversation", cl);
                    Object obj = XposedHelpers.getStaticObjectField(c_0M6, "A19");

                    List<String> li = new ArrayList<>();
                    XposedHelpers.callMethod(obj, "A0T", Collections.singletonList(newInstance), messagae, null, null, li, false, false, null, null, null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String path = intent.getStringExtra("path");
                String phone = intent.getStringExtra("phone");

                final Class<?> c_UserJid = XposedHelpers.findClass("com.whatsapp.jid.UserJid", cl);
                try {
                    Object newInstance = XposedHelpers.newInstance(c_UserJid, phone);
                    Uri imageUri = Uri.fromFile(new File(path));
                    List<Uri> p3 = new ArrayList<>();
                    p3.add(imageUri);

                    final Collection A1E = new ArrayList();

                    final HashSet A1G = new HashSet();
                    final Map A1H = new HashMap();
                    final Map a00 = new HashMap();
                    final Class<?> c_0cy = XposedHelpers.findClass("X.0cy", cl);
                    final Class<?> c_2i5 = XposedHelpers.findClass("X.2i5", cl);
                    final Class<?> c_2Ti = XposedHelpers.findClass("X.2Ti", cl);
                    Object new_0cy = XposedHelpers.newInstance(c_0cy);
                    Object new_acti = null;

                    Byte b; //资源类型
                    // 发送资源类型 1=图片 2=?  3=视频
                    if(path.endsWith(".mp4")) {
                         b = Byte.valueOf((byte) 3);
                    }
                    else if(path.endsWith(".jpg")||path.endsWith(".jpeg"))
                    {
                         b = Byte.valueOf((byte) 1);
                    }
                    else
                    {
                        b = Byte.valueOf((byte) 2);
                    }
                    Object new_2Ti = XposedHelpers.newInstance(c_2Ti, imageUri);
                    XposedHelpers.setObjectField(new_2Ti, "A06", new File(path));
                    XposedHelpers.setObjectField(new_2Ti, "A07", b);

                    a00.put(imageUri, new_2Ti);


                    XposedHelpers.setObjectField(new_0cy, "A00", a00);
                    Object p1 = new Object();
                    //A1E.add(new File("/data/user/0/com.whatsapp/files/.Shared/69a006e5db644497aebc776844e7de35"));
                    Object new_2i5 = XposedHelpers.newInstance(c_2i5, new_acti, Collections.singletonList(newInstance), p3, new HashSet(A1E), new_0cy, null, 0L, false, 12, true, new HashMap(A1H), A1G, false);

                    final Class<?> c_01h = XposedHelpers.findClass("X.01h", cl);

                    Object c_asg = XposedHelpers.callStaticMethod(c_01h, "A00");
                    XposedHelpers.callMethod(c_asg, "ASg", new_2i5, new Void[0]);

                    XposedBridge.log("onClickReceiver 接收");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Override
    public void onLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);

        //    XposedBridge.log("启动: " + lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            XposedBridge.log("whatsapp启动");

            // 在attachBaseContext里注册BroadcastReceiver 已接收来自另一个app的指令
            XposedHelpers.findAndHookMethod(ContextThemeWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    ctx1 = (Context) param.args[0];
                    XposedBridge.log("Context= " + ctx1);

                    Context arg = (Context) param.args[0];
                    final ClassLoader cl = arg.getClassLoader();
                    if (null == oCR) {
                        oCR = new onClickReceiver(cl);
                        // 注册自定义动态广播消息
                        IntentFilter filter_dynamic = new IntentFilter("com.Xposebase.onClick");
                        arg.registerReceiver(oCR, filter_dynamic);
                        IntentFilter filter_dynamic2 = new IntentFilter("com.Xposebase.onClick2");
                        arg.registerReceiver(oCR, filter_dynamic2);

                        //Toast.makeText(arg, "广播注册成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });


            final Class<?> c_0M6 = XposedHelpers.findClass("X.0M6", lpparam.classLoader);
            final Class<?> c_1Uq = XposedHelpers.findClass("X.1Uq", lpparam.classLoader);
            final Class<?> c_042 = XposedHelpers.findClass("X.042", lpparam.classLoader);
            final Class<?> c_0Qb = XposedHelpers.findClass("X.0Qb", lpparam.classLoader);
            //文本发送接口
            XposedHelpers.findAndHookMethod(c_0M6, "A0T", List.class, String.class, c_1Uq, c_042, List.class, boolean.class, boolean.class, String.class, String.class, c_0Qb,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("当前A0T");
                        }

                    });


            final Class<?> c_2i5 = XposedHelpers.findClass("X.2i5", lpparam.classLoader);
            XposedBridge.hookAllConstructors(c_2i5, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);

                    XposedBridge.log("当前c_2i5");
                }
            });

            final Class<?> c_Main = XposedHelpers.findClass("com.whatsapp.Main", lpparam.classLoader);
            //主界面onCreate
            XposedHelpers.findAndHookMethod(c_Main, "onCreate",  Bundle.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("当前c_Main");
                            ((Activity)param.thisObject).moveTaskToBack(true);
                        }

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("当前c_Main");
                            ((Activity)param.thisObject).moveTaskToBack(true);
                        }

                    });

            final Class<?> c_HomeActivity = XposedHelpers.findClass("com.whatsapp.HomeActivity", lpparam.classLoader);
            //聊天onCreate
            XposedHelpers.findAndHookMethod(c_HomeActivity, "onCreate",  Bundle.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("当前c_HomeActivity");
                            ((Activity)param.thisObject).moveTaskToBack(true);
                        }

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("当前c_HomeActivity");
                            ((Activity)param.thisObject).moveTaskToBack(true);
                        }

                    });



        }
    }
}