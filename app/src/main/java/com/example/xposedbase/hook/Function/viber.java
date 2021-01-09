package com.example.xposedbase.hook.Function;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannedString;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xposedbase.hook.BaseHook;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class viber extends BaseHook {
    String PACKAGE_NAME = "com.viber.voip";
    public Context ctx1;
    public onClickReceiver oCR = null;
    public Object OBJ_XO=null;
    public Object o_MessageComposerView=null;

    public Object o_runable = null;
    public Object o_1 =null;
    public Object o_2 =null;
    public void showMyToast(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt);
    }

    public enum h {
        TO_BE_SENT_SILENTLY,
        NONE;
    }

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

            if ("com.xbase.viber.test".equals(action)) {
                Object oparam2 = null;
                XposedBridge.log("com.xbase.viber.test 接收");

                final Class<?> c_mn = XposedHelpers.findClass("com.viber.voip.block.n", cl);
                final Class<?> c_Member = XposedHelpers.findClass("com.viber.voip.memberid.Member", cl);
                final Class<?> c_MessageEntity = XposedHelpers.findClass(" com.viber.voip.model.entity.MessageEntity", cl);
                final Class<?> c_ui_r = XposedHelpers.findClass("com.viber.voip.messages.ui.r", cl);
                final Class<?> c_z = XposedHelpers.findClass("com.viber.voip.messages.ui.z", cl);
                final Class<?> c_ConversationItemLoaderEntity = XposedHelpers.findClass("com.viber.voip.messages.conversation.ConversationItemLoaderEntity", cl);


                String message ="测试";
                String memberid = "FH5RlIfS6GQ=";

                //消息结构体
                Object o_messageEntity = XposedHelpers.newInstance(c_MessageEntity);
                XposedHelpers.callMethod(o_messageEntity,"setBody",message);
                XposedHelpers.callMethod(o_messageEntity,"setCount",1);
                XposedHelpers.callMethod(o_messageEntity,"setDate",System.currentTimeMillis());
                XposedHelpers.callMethod(o_messageEntity,"setExtraStatus",3);
                XposedHelpers.callMethod(o_messageEntity,"setRawMessageInfoOnly","{}");
                XposedHelpers.callMethod(o_messageEntity,"setSpans","no_sp");
                XposedHelpers.callMethod(o_messageEntity,"setType",1);
                XposedHelpers.callMethod(o_messageEntity,"setUnread",0);

                //目标
                Object member_obj = XposedHelpers.newInstance(c_Member,memberid);

                //H0目标
                Object o_ConversationItemLoaderEntity = XposedHelpers.newInstance(c_ConversationItemLoaderEntity);
                XposedHelpers.setObjectField(o_ConversationItemLoaderEntity,"participantMemberId",memberid);
                XposedHelpers.setObjectField(o_MessageComposerView,"H0",o_ConversationItemLoaderEntity);

                Object z_obj = XposedHelpers.newInstance(c_ui_r,o_MessageComposerView,o_messageEntity,true,null);
                Object z2_obj = XposedHelpers.newInstance(c_z,z_obj);

                //调用
                XposedHelpers.callStaticMethod(c_mn,"a",oparam2,member_obj,z2_obj);

                XposedBridge.log("构造完成");
            }

        }
    }


    @Override
    public void onLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);

        //    XposedBridge.log("启动: " + lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            XposedBridge.log("viber启动");

            XposedHelpers.findAndHookMethod(ContextThemeWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ctx1 = (Context) param.args[0];

                    Context arg = (Context) param.args[0];
                    final ClassLoader cl = arg.getClassLoader();
                    if (null == oCR) {
                        oCR = new onClickReceiver(cl);
                        // 注册自定义动态广播消息
                        IntentFilter filter_dynamic = new IntentFilter("com.xbase.viber.test");
                        arg.registerReceiver(oCR, filter_dynamic);
                        Toast.makeText(arg, "广播注册成功", Toast.LENGTH_SHORT).show();
                    }

                    XposedBridge.log("Context= " + ctx1);
                }
            });

            final Class<?> c_SQLiteDatabase = XposedHelpers.findClass("org.sqlite.database.sqlite.SQLiteDatabase", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_SQLiteDatabase, "insertWithOnConflict",
                    String.class, String.class, ContentValues.class, int.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);
                            if (param.args[0].equals("messages"))
                            {
                                XposedBridge.log("当前表:" + (String) param.args[0]);
                            }

                        }

                    });

            final Class<?> c_SQLiteConnection = XposedHelpers.findClass("org.sqlite.database.sqlite.SQLiteConnection", lpparam.classLoader);

            final Class<?> c_CancellationSignal = XposedHelpers.findClass("org.sqlite.os.CancellationSignal", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_SQLiteConnection, "executeForLastInsertedRowId",
                    String.class, Object[].class, c_CancellationSignal, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);


                        }

                    });




            final Class<?> c_MessageEntity = XposedHelpers.findClass(" com.viber.voip.model.entity.MessageEntity", lpparam.classLoader);
            final Class<?> c_MessageComposerView = XposedHelpers.findClass("com.viber.voip.messages.ui.MessageComposerView", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_MessageComposerView, "a", String.class, c_MessageEntity, boolean.class,Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                  //  Object ob = XposedHelpers.newInstance(c_MessageComposerView,ctx1);
                 //   Object obj = param.args[1];
                 //   XposedHelpers.setObjectField(obj,"memberId","0");
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });



            XposedBridge.hookAllConstructors(c_MessageEntity, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("c_MessageEntity new = ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("c_MessageEntity = ");

                }
            });



            XposedBridge.hookAllConstructors(c_MessageComposerView, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("c_MessageComposerView new = ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("c_MessageComposerView = "+o_MessageComposerView);
                    o_MessageComposerView= param.thisObject;
                    XposedBridge.log("c_MessageComposerView = "+o_MessageComposerView);
                }
            });


            final Class<?> c_mn = XposedHelpers.findClass("com.viber.voip.block.n", lpparam.classLoader);

            final Class<?> c_Member = XposedHelpers.findClass("com.viber.voip.memberid.Member", lpparam.classLoader);
            final Class<?> c_r = XposedHelpers.findClass("com.viber.voip.block.n$b", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_mn, "a", Context.class, c_Member, c_r, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                      param.args[0]=null;
                      o_runable = param.args[2] ;
                      o_1= param.args[0];
                      o_2 =param.args[1];

                    XposedBridge.log("Context= " );
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



                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });

//            XposedHelpers.findAndHookMethod(c_mn, "a", Context.class, Set[].class, String.class,c_r, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                 //   param.args[0]=null;
//
//
//                    XposedBridge.log("Context= " );
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                    XposedBridge.log("Context= ");
//                }
//            });

//            final Class<?> c_k = XposedHelpers.findClass("com.viber.voip.messages.k", lpparam.classLoader);
//            XposedHelpers.findAndHookMethod(c_k, "a", int.class, String.class, int.class,String.class,int.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//
//                    XposedBridge.log("Context= " );
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                    XposedBridge.log("Context= ");
//                }
//            });



            final Class<?> c_z = XposedHelpers.findClass("com.viber.voip.messages.ui.z", lpparam.classLoader);
            XposedBridge.hookAllConstructors(c_z, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("c_z new = ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("c_z = ");
                }
            });

            final Class<?> c_ui_r = XposedHelpers.findClass("com.viber.voip.messages.ui.r", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_ui_r, "run", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });


            XposedHelpers.findAndHookMethod(c_MessageComposerView, "a",c_MessageEntity,Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });



            final Class<?> c_ConversationFragment = XposedHelpers.findClass("com.viber.voip.messages.conversation.ui.ConversationFragment", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_ConversationFragment, "onCreate",Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("ConversationFragment onCreate" );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });


        }
    }
}




