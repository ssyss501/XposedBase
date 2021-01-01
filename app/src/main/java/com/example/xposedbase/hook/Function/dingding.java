package com.example.xposedbase.hook.Function;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.xposedbase.hook.BaseHook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import  com.example.xposedbase.hook.KktDatabase.DataBaseResourceCrypto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dingding extends BaseHook {
    String PACKAGE_NAME = "com.alibaba.android.rimet";


    @Override
    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);
        //  XposedBridge.log("Package name:"+lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            XposedBridge.log("dingding启动");

//            XposedHelpers.findAndHookMethod(SQLiteDatabase.class, "insertWithOnConflict",
//                    String.class, String.class, ContentValues.class, int.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("当前表:" + (String) param.args[0]);
//
//
//                        }
//
//                    });


            Class<?> c_wrapper= XposedHelpers.findClass("com.alibaba.sqlcrypto.sqlite.SqliteWrapper",lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_wrapper, "insert",
                    Context.class, ContentResolver.class, Uri.class, ContentValues.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);


                        }

                    });

           Class<?> c_SQLiteOpenHelper= XposedHelpers.findClass("com.alibaba.sqlcrypto.sqlite.SQLiteOpenHelper",lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_SQLiteOpenHelper, "setPassword",
                    String.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("dingding数据库密码:" + (String) param.args[0]);


                        }

                    });


            Class<?> c_SQLiteDatabase= XposedHelpers.findClass("com.alibaba.sqlcrypto.sqlite.SQLiteDatabase",lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_SQLiteDatabase, "insertWithOnConflict",
                    String.class, String.class, ContentValues.class, int.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);


                        }

                    });


            XposedHelpers.findAndHookMethod(c_SQLiteDatabase, "insert",
                    String.class, String.class, ContentValues.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);


                        }

                    });


            Class<?> c_SQLiteSession= XposedHelpers.findClass("com.alibaba.sqlcrypto.sqlite.SQLiteSession",lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_SQLiteSession, "executeForLastInsertedRowId",
                    String.class, Object[].class, int.class,Object.class,new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);


                        }

                    });




        }
    }
}