package com.example.xposedbase.hook.Function;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.xposedbase.hook.BaseHook;
import com.example.xposedbase.hook.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedBridge.log;

public class wechat extends BaseHook {
    String PACKAGE_NAME = "com.tencent.mm";
    public Context ctx1;
    public String SQLiteDatabaseClassName = "com.tencent.wcdb.database.SQLiteDatabase";
    public String SQLiteDatabaseInsertWithOnConflictMethod = "insertWithOnConflict"; //插入


    //微信日志级别
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_NONE = 6;

    //微信日志过滤
    public static final String LOG_VERBOSE = "LOG_VERBOSE";
    public static final String LOG_DEBUG = "LOG_DEBUG";
    public static final String LOG_INFO= "LOG_INFO";
    public static final String LOG_WARNING = "LOG_WARNING";
    public static final String LOG_ERROR = "LOG_ERROR";
    public static final String LOG_FATAL = "LOG_FATAL";
    public static final String LOG_NONE = "LOG_NONE ";


    //根据logWrite2方法里面传过来的参数来过滤字段，默认值
    private String getWechatLogType(int logType) {
        String TAG=null;
        if(logType==LEVEL_VERBOSE){
            TAG=LOG_VERBOSE;
        }else if(logType==LEVEL_DEBUG){
            TAG=LOG_DEBUG;
        }else if(logType==LEVEL_INFO){
            TAG=LOG_INFO;
        }else if(logType==LEVEL_WARNING){
            TAG=LOG_WARNING;
        }else if(logType==LEVEL_ERROR){
            TAG=LOG_ERROR;
        }else if(logType==LEVEL_FATAL){
            TAG=LOG_FATAL;
        }else if(logType==LEVEL_NONE){
            TAG=LOG_NONE;
        }
        return TAG;
    }


    public void onLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
            if (!lpparam.packageName.contains(PACKAGE_NAME)) {  //判断包名
               // XposedBridge.log("没找到");
                return;
            }




            XposedHelpers.findAndHookMethod(ContextWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
                public void afterHookedMethod(MethodHookParam param) throws Throwable {
                    try {

                        XposedHelpers.findAndHookMethod("com.tencent.mm.xlog.app.XLogSetup", lpparam.classLoader, "keep_setupXLog",
                                boolean.class, String.class, String.class, Integer.class, Boolean.class,
                                Boolean.class, //isLogcatOpen
                                String.class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                        param.args[5] = true;
                                        LogUtil.v("OpenLog before","keep_setupXLog参数isLogcatOpen: " +param.args[5]);
                                        log("keep_setupXLog参数isLogcatOpen: " +param.args[5]);
                                    }

                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        param.args[5] = true;
                                        super.afterHookedMethod(param);
                                        LogUtil.v("OpenLog after ","keep_setupXLog参数isLogcatOpen: " +param.args[5]);

                                    }

                                });

                        XposedHelpers.findAndHookMethod("com.tencent.mars.xlog.Xlog", lpparam.classLoader, "getLogLevel",
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        param.setResult(0);
                                    }

                                });

                        XposedHelpers.findAndHookMethod("com.tencent.mm.protocal.protobuf.bew", lpparam.classLoader, "op",int.class,Object[].class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);

                                    }

                                });


                        XposedHelpers.findAndHookMethod("com.tencent.mars.mm.MMLogic", lpparam.classLoader, "setMmtlsCtrlInfo",boolean.class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        param.setResult(0);
                                    }

                                });

                        final Class<?> c_pbye = XposedHelpers.findClass("com.tencent.mm.pointers.PByteArray", lpparam.classLoader);
                        XposedHelpers.findAndHookMethod("com.tencent.mm.protocal.MMProtocalJni", lpparam.classLoader, "pack", byte[].class, c_pbye, byte[].class, int.class, byte[].class, String.class, int.class, int.class, int.class, byte[].class, byte[].class, int.class, int.class, int.class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);

                                    }

                                });



//                        XposedHelpers.findAndHookMethod("com.tencent.mars.xlog.Xlog", lpparam.classLoader, "logWrite2",
//                                int.class,String.class, String.class, String.class,int.class,int.class,long.class,long.class,String.class,
//                                new XC_MethodHook() {
//                                    @Override
//                                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                        int logType  = (int) param.args[0];
//                                        String str2  = (String) param.args[1];
//                                        String str3  = (String) param.args[2];
//                                        String str4 = (String) param.args[3];
//                                        int num4  = (int) param.args[4];
//                                        int num5  = (int) param.args[5];
//                                        long long6  = (long) param.args[6];
//                                        long long7  = (long) param.args[7];
//                                        String str8  = (String) param.args[8];
//                                        //根据值来过过滤日志级别
//                                        String wechatLogType = getWechatLogType(logType);
//
//                                        Log.d(wechatLogType, "LogType==="+logType);
//                                        Log.d(wechatLogType, str2);
//                                        Log.d(wechatLogType, str3);
//                                        Log.d(wechatLogType, str4);
//                                        Log.d(wechatLogType, ""+num4);
//                                        Log.d(wechatLogType, ""+num5);
//                                        Log.d(wechatLogType, ""+long6);
//                                        Log.d(wechatLogType, ""+long7);
//                                        Log.d(wechatLogType, str8);
//                                        super.beforeHookedMethod(param);
//                                    }
//                                });


//                        Class<?> hookclass = ((Context) param.args[0]).getClassLoader().loadClass("com.tencent.mm.plugin.gallery.ui.AlbumPreviewUI");//类方法
//
//                        XposedBridge.hookAllConstructors(hookclass, new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//                            }
//                        });
//
//
//
//                        Class<?> hooksqlite = ((Context) param.args[0]).getClassLoader().loadClass("com.tencent.wcdb.database.SQLiteConnection");//类方法
//
//                        XposedBridge.hookAllConstructors(hooksqlite, new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//                                Log.i("Dump Stack: ", "---------------start----------------");
//                                Throwable ex = new Throwable();
//                                StackTraceElement[] stackElements = ex.getStackTrace();
//                                if (stackElements != null) {
//                                    for (int i = 0; i < stackElements.length; i++) {
//
//                                        Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//                                                +"----"+stackElements[i].getFileName()
//                                                +"----" + stackElements[i].getLineNumber()
//                                                +"----" +stackElements[i].getMethodName());
//                                    }
//                                }
//                                Log.i("Dump Stack: ", "---------------over----------------");
//
//                            }
//                        });


//                        XposedHelpers.findAndHookMethod(hookclass, "bUD",new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//                            }
//                        });






//                        //插入操作
//                        XposedHelpers.findAndHookMethod(SQLiteDatabaseClassName, lpparam.classLoader, SQLiteDatabaseInsertWithOnConflictMethod,
//                                String.class, String.class, ContentValues.class, int.class,
//                                new XC_MethodHook() {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) {
//                                        String tableName = (String) param.args[0];
//                                        ContentValues contentValues = (ContentValues) param.args[2];
//                                        if (tableName == null || tableName.length() == 0 || contentValues == null) {
//                                            return;
//                                        }
//
//                                        //过滤掉非聊天消息
//                                        if (!tableName.equals("message")  ) {
//                                            return;
//                                        }
//
//                                    }
//                                });
//                        XposedHelpers.findAndHookMethod(MenuItem.class, "onMenuItemClick",MenuItem.class,new XC_MethodHook() {
//                            @Override
//                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                super.afterHookedMethod(param);
//
//
//                            }
//                        });


                        Class<?> c_SendImgProxyUI = ((Context) param.args[0]).getClassLoader().loadClass("com.tencent.mm.ui.chatting.SendImgProxyUI");//类方法



                        XposedHelpers.findAndHookMethod(c_SendImgProxyUI, "onCreate", Bundle.class,new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                            }
                        });

                        XposedHelpers.findAndHookMethod(c_SendImgProxyUI, "a", c_SendImgProxyUI, Intent.class,new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                            }
                        });


                        XposedHelpers.findAndHookMethod(c_SendImgProxyUI, "a", c_SendImgProxyUI, Intent.class,new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                            }
                        });

                        Class<?> c_a = ((Context) param.args[0]).getClassLoader().loadClass("com.tencent.mm.au.n");//类方法
                        XposedHelpers.findAndHookMethod(c_a, "a", ArrayList.class, boolean.class,int.class,int.class,String.class,new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                            }
                        });



                    } catch (Exception e) {
                        XposedBridge.log("寻找失败");
                    }
                }
            });


        }


}