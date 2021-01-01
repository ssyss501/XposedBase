package com.example.xposedbase.hook.Function;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.xposedbase.hook.BaseHook;

import org.json.JSONArray;
import org.json.JSONObject;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import android.os.Parcel;
import android.view.View;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import android.widget.TextView;

public class facebook extends BaseHook {
    String PACKAGE_NAME = "com.facebook.katana";


    @Override
    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);

    //    XposedBridge.log("启动: " + lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            XposedBridge.log("facebook启动");



            XposedBridge.hookAllConstructors(Parcel.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            final Class<?> c_X2PD = XposedHelpers.findClass("X.2PD", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_X2PD, "onClick",
                     View.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前onClick");
                        }

                    });

            final Class<?> c_6My = XposedHelpers.findClass("X.6My", lpparam.classLoader);
            final Class<?> c_1e1 = XposedHelpers.findClass("X.1e1", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_6My, "Adx",
                    c_1e1,Object.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前onClick");
                        }

                    });



            final Class<?> c_27N = XposedHelpers.findClass("X.27N", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_27N, "A0A",
                    Context.class,String.class, Bundle.class, Map.class,new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前onClick");
                        }

                    });

//            final Class<?> c_6r8 = XposedHelpers.findClass("X.6r8", lpparam.classLoader);
//            XposedHelpers.findAndHookMethod(c_6r8, "A00",
//                    c_6r8,new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("当前6r8");
//                        }
//
//                    });


            final Class<?> c_6Ei = XposedHelpers.findClass("X.6Ei", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_6Ei, "A00",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前Fb4aBundle 1");
                        }

                    });

            XposedHelpers.findAndHookMethod(c_6Ei, "A01",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前Fb4aBundle 2");
                        }

                    });

            XposedHelpers.findAndHookMethod(c_6Ei, "A02",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前Fb4aBundle 3");
                     //       XposedBridge.log("当前Fb4aBundle 3:"+ param.args[0].toString());

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
//
//            final Class<?> c_textview = XposedHelpers.findClass("android.widget.TextView", lpparam.classLoader);
//            XposedHelpers.findAndHookMethod(c_textview, "setText",
//                    CharSequence.class,new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//
//                            XposedBridge.log("当前setText:"+ param.args[0].toString());
//
//                            Log.i("Dump Stack: ", "---------------start----------------");
//                            Throwable ex = new Throwable();
//                            StackTraceElement[] stackElements = ex.getStackTrace();
//                            if (stackElements != null) {
//                                for (int i = 0; i < stackElements.length; i++) {
//
//                                    Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//                                            +"----"+stackElements[i].getFileName()
//                                            +"----" + stackElements[i].getLineNumber()
//                                            +"----" +stackElements[i].getMethodName());
//                                }
//                            }
//                            Log.i("Dump Stack: ", "---------------over----------------");
//
//                        }
//
//                    });




//            XposedHelpers.findAndHookConstructor(JSONArray.class,
//                    String.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("JSONArray:"+ (String) param.args[0] );
//                        }
//
//                    });
//
//
//            XposedHelpers.findAndHookConstructor(JSONObject.class,
//                    String.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("JSONObject:"+ (String) param.args[0] );
//                        }
//
//                    });


//            XposedBridge.hookAllConstructors(JSONObject.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                }
//            });


//            XposedHelpers.findAndHookMethod(JSONObject.class, "insertWithOnConflict",
//                    String.class, String.class, ContentValues.class, int.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("当前表:" + (String) param.args[0]);
//                        }
//
//                    });


//            final Class<?> httpUrlConnection = XposedHelpers.findClass("java.net.HttpURLConnection", lpparam.classLoader);
//            XposedBridge.hookAllConstructors(httpUrlConnection,new XC_MethodHook() {
//
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                    if (param.args.length != 1 || param.args[0].getClass() != URL.class) {
//                        return;
//                    }
//
//                    XposedBridge.log("httphook" + "HttpURLConnection: " + param.args[0] + "");
//
//
//                            Log.i("Dump Stack: ", "---------------start----------------");
//                            Throwable ex = new Throwable();
//                            StackTraceElement[] stackElements = ex.getStackTrace();
//                            if (stackElements != null) {
//                                for (int i = 0; i < stackElements.length; i++) {
//
//                                    Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//                                            +"----"+stackElements[i].getFileName()
//                                            +"----" + stackElements[i].getLineNumber()
//                                            +"----" +stackElements[i].getMethodName());
//                                }
//                            }
//                            Log.i("Dump Stack: ", "---------------over----------------");
//
//
//                }
//            });


        }
    }
}