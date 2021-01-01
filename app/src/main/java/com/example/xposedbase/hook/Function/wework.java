package com.example.xposedbase.hook.Function;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import com.example.xposedbase.hook.BaseHook;
import com.example.xposedbase.hook.LogUtil;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.log;

public class wework extends BaseHook {
    String PACKAGE_NAME = "com.tencent.wework";
    public Context ctx1;
    public String SQLiteDatabaseClassName = "com.tencent.wcdb.database.SQLiteDatabase";
    public String SQLiteDatabaseInsertWithOnConflictMethod = "insertWithOnConflict"; //插入


    public void onLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.contains(PACKAGE_NAME)) {

            return;
        }


        XposedHelpers.findAndHookMethod(ContextWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
            public void afterHookedMethod(MethodHookParam param) throws Throwable {
                try {


                    XposedHelpers.findAndHookMethod("com.tencent.wework.msg.controller.CustomAlbumActivity", lpparam.classLoader, "ety",
                            new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);

                                }

                            });

                                            //插入操作
                        XposedHelpers.findAndHookMethod(SQLiteDatabaseClassName, lpparam.classLoader, SQLiteDatabaseInsertWithOnConflictMethod,
                                String.class, String.class, ContentValues.class, int.class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) {
                                        String tableName = (String) param.args[0];
                                        ContentValues contentValues = (ContentValues) param.args[2];
                                        if (tableName == null || tableName.length() == 0 || contentValues == null) {
                                            return;
                                        }

                                        //过滤掉非聊天消息
                                        if (!tableName.equals("message")  ) {
                                            return;
                                        }

                                    }
                                });




                } catch (Exception e) {
                    XposedBridge.log("寻找失败");
                }
            }
        });


    }


}

