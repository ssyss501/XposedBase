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

public class discord extends BaseHook {
    String PACKAGE_NAME = "com.discord";
    public Context ctx1;


    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.contains("com.discord")) {  //判断包名
            // XposedBridge.log("没找到");
            return;
        }
        XposedBridge.log("---》找到了：" + lpparam.packageName);
        XposedHelpers.findAndHookMethod(ContextWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {




            public void afterHookedMethod(MethodHookParam param) throws Throwable {
                try {


                    Class<?> hookclass = ((Context) param.args[0]).getClassLoader().loadClass("com.discord.widgets.channels.memberlist.adapter.ChannelMembersListAdapter$Item$Member");//类方法

                    XposedBridge.log("hookclass"  + hookclass);


                    XposedBridge.hookAllConstructors(hookclass, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);



                            XposedBridge.log("obj"  + "Aaa");

                        }
                    });




                } catch (Exception e) {
                    XposedBridge.log("寻找失败");
                }
            }
        });
    }






}