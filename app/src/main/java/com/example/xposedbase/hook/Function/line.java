package com.example.xposedbase.hook.Function;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.text.SpannedString;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.util.ObjectsCompat;

import com.example.xposedbase.hook.BaseHook;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

public class line extends BaseHook {
    String PACKAGE_NAME = "jp.naver.line.android";
    public Context ctx1;
    public onClickReceiver oCR = null;
    public Object OBJ_XO=null;
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

            if ("com.xbase.wa.line".equals(action)) {

                CharSequence message= "测试line";
                String uid = "ub52499013839a04fe76a267a4a2da2ad";

                Object oparam = null;
                final Class<?> c_k = XposedHelpers.findClass("g.a.h0.k", cl);
                final Class<?> c_h1 = XposedHelpers.findClass("s0.a.a.a.b.c.b.h1", cl);
                final Class<?> c_l0 = XposedHelpers.findClass("s0.a.a.a.n2.j.l0", cl);
                final Class<?> c_r = XposedHelpers.findClass("s0.a.a.a.b.c.b.r", cl);
                Object obj = XposedHelpers.newInstance(c_r,oparam,uid);

                final Class<?> c_h = XposedHelpers.findClass("g.a.h0.h", cl);
                //右边参数构造完成
                Object ccc = XposedHelpers.callStaticMethod(c_h,"f",obj);

                final Class<?> c_j = XposedHelpers.findClass("  s0.a.a.a.i.j", cl);

                Object obj_e = XposedHelpers.getStaticObjectField(c_j,"x");
                Object obj_a = XposedHelpers.getObjectField(obj_e,"a");

                Object obj_i0 = XposedHelpers.newInstance(c_l0,ctx1,obj_a);
                ////// 上面构造完成this.p.b
                final Class<?> c_enum = XposedHelpers.findClass("s0.a.a.a.i.s0.h", cl);
                Object[] enumConstants = c_enum.getEnumConstants();

                Object thispb = XposedHelpers.newInstance(c_h1,uid,enumConstants[1],obj_i0,oparam);

                final Class<?> c_m = XposedHelpers.findClass("g.a.h0.m", cl);
                Object obj_m = XposedHelpers.newInstance(c_m);

                Object obj_k = XposedHelpers.newInstance(c_k,thispb,obj_m);

                final Class<?> c_e = XposedHelpers.findClass("g.a.h0.e", cl);
                Object obj_ee = XposedHelpers.newInstance(c_e);

                final Class<?> c_c = XposedHelpers.findClass("g.a.h0.c", cl);
                Object obj_h0c = XposedHelpers.newInstance(c_c,obj_k,obj_ee);

                final Class<?> c_gard = XposedHelpers.findClass("g.a.r", cl);

                final Class<?> c_gars = XposedHelpers.findClass("g.a.s", cl);
                Object obj_gars = XposedHelpers.getStaticObjectField(c_gars,"a");

                Object obj_c_gard = XposedHelpers.callStaticMethod(c_gard,"d",obj_gars);
                Object obj_yyy = XposedHelpers.callMethod(obj_c_gard,"y");

                final Class<?> c_f0a = XposedHelpers.findClass("s0.a.a.a.i.s0.f0.a", cl);
                Object obj_thisH = XposedHelpers.newInstance(c_f0a,ctx1,obj_yyy);

                final Class<?> c_p1 = XposedHelpers.findClass("s0.a.a.a.b.c.b.p1", cl);

                Object obj_eiwocao = XposedHelpers.newInstance(c_p1,obj_thisH,oparam,new SpannedString(message),null);

                final Class<?> c_X0_c = XposedHelpers.findClass("s0.a.a.a.b.c.b.x0$c", cl);

                Object obj_0X_c = XposedHelpers.newInstance(c_X0_c,oparam,null);

                //最内层k构造
                Object obj_k33 = XposedHelpers.newInstance(c_k,obj_eiwocao,obj_0X_c);
                Object obj_k333 = XposedHelpers.newInstance(c_k,obj_k33,obj_h0c);

                //最外层
               // Object obj_k3333 = XposedHelpers.newInstance(c_k,obj_k333,ccc);

                //最外层调用a参数
                final Class<?> c_out_a = XposedHelpers.findClass("g.a.h0.a", cl);
                Object obj_out_a = XposedHelpers.getStaticObjectField(c_out_a,"a");

                //调用 但崩溃
               // XposedHelpers.callMethod(obj_k3333,"d",obj_out_a);

                //少一层OK
                XposedHelpers.callMethod(obj_k333,"d",obj_out_a);

            }

        }
    }


    @Override
    public void onLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);

        //    XposedBridge.log("启动: " + lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            XposedBridge.log("line启动");

            XposedHelpers.findAndHookMethod(ContextThemeWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    ctx1 = (Context) param.args[0];

                    Context arg = (Context) param.args[0];
                    final ClassLoader cl = arg.getClassLoader();
                    if (null == oCR) {
                        oCR = new onClickReceiver(cl);
                        // 注册自定义动态广播消息
                        IntentFilter filter_dynamic = new IntentFilter("com.xbase.wa.line");
                        arg.registerReceiver(oCR, filter_dynamic);
                        Toast.makeText(arg, "广播注册成功", Toast.LENGTH_SHORT).show();
                    }

                    XposedBridge.log("Context= " + ctx1);
                }
            });


            final Class<?> c_x0 = XposedHelpers.findClass("s0.a.a.a.b.c.b.x0", lpparam.classLoader);
            final Class<?> c_h = XposedHelpers.findClass("s0.a.a.a.i.s0.h", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_x0, "g", CharSequence.class, Long.class, c_h, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod(c_x0, "i", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            XposedHelpers.findAndHookMethod(c_x0, "i", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                     XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });

            XposedHelpers.findAndHookMethod(c_x0, "m0",String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            final Class<?> c_x1d = XposedHelpers.findClass("s0.a.a.a.x1.d", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_x1d, "c", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });



            final Class<?> c_xgc = XposedHelpers.findClass("s0.a.a.a.k.g.c", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_xgc, "d", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });

            XposedHelpers.findAndHookMethod(c_xgc, "b", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            final Class<?> c_fcb = XposedHelpers.findClass("s0.a.a.a.f.c.b", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_fcb, "H1", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );

                    final Class<?> c_U0C = XposedHelpers.findClass("s0.a.a.a.r0.u0.c", lpparam.classLoader);
                    Object obj = XposedHelpers.getStaticObjectField(c_U0C,"b");
                    XposedBridge.log("aaaaaaaaa");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            final Class<?> c_ge0 = XposedHelpers.findClass("s0.a.a.a.g.e0", lpparam.classLoader);
            final Class<?> c_aam = XposedHelpers.findClass("s0.a.a.a.h.a.a.m", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_aam, "d", SQLiteDatabase.class,c_ge0,String.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            final Class<?> c_x1h = XposedHelpers.findClass("s0.a.a.a.x1.h", lpparam.classLoader);
            XposedBridge.hookAllConstructors(c_x1h, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });



            final Class<?> c_br = XposedHelpers.findClass("s0.a.a.a.b.c.b.r", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_br, "apply", Object.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
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
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            final Class<?> c_g0b = XposedHelpers.findClass("g.a.g0.b", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_x0, "u",String.class, c_g0b,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("g.a.g0.b.u " );
//                    Log.i("Dump Stack: ", "---------------start----------------");
//                    Throwable ex = new Throwable();
//                    StackTraceElement[] stackElements = ex.getStackTrace();
//                    if (stackElements != null) {
//                        for (int i = 0; i < stackElements.length; i++) {
//
//                            Log.i("Dump Stack"+i+": ", stackElements[i].getClassName()
//                                    +"----"+stackElements[i].getFileName()
//                                    +"----" + stackElements[i].getLineNumber()
//                                    +"----" +stackElements[i].getMethodName());
//                        }
//                    }
//                    Log.i("Dump Stack: ", "---------------over----------------");

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            XposedBridge.hookAllConstructors(c_x0, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("x0构造函数运行= ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("x0构造函数运行= ");
                    OBJ_XO= param.thisObject;

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

                    XposedBridge.log("OBJ_XO= "+OBJ_XO);
                }
            });


            final Class<?> c_c = XposedHelpers.findClass("jp.naver.line.android.activity.chathistory.ChatHistoryActivity", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_c, "onCreate",Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= " );
                }
            });


            final Class<?> c_X0_c = XposedHelpers.findClass("s0.a.a.a.b.c.b.x0$c", lpparam.classLoader);
            XposedBridge.hookAllConstructors(c_X0_c, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.args[0]=null;
                    XposedBridge.log("Context= ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                   // param.setResult(null);
                    XposedBridge.log("Context= ");
                }
            });




            final Class<?> c_p1 = XposedHelpers.findClass("s0.a.a.a.b.c.b.p1", lpparam.classLoader);
            XposedBridge.hookAllConstructors(c_p1, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= ");
                }
            });



            final Class<?> c_d = XposedHelpers.findClass("g.a.d0.q.d", lpparam.classLoader);
            final Class<?> c_a = XposedHelpers.findClass("g.a.h0.a", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_d, "B1", c_a, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

//                        Object obj =param.args[0];
//                        Object ooo=null;
//                        String uid = (String)XposedHelpers.getObjectField(obj,"b");
//
//                        if(uid.equals("ub52499013839a04fe76a267a4a2da2ad"))
//                        {
//                            try {
//
//
//                              //  final Class<?> c_enum = XposedHelpers.findClass("s0.a.a.a.i.s0.h", lpparam.classLoader);
//                            //    Enum o = Enum.valueOf(c_enum, "NONE");
//                                //   Object oenum = XposedHelpers.newInstance(c_enum);
//
//
////                                final Class<?> c_l0 = XposedHelpers.findClass("s0.a.a.a.n2.j.l0", lpparam.classLoader);
////                                final Class<?> c_j = XposedHelpers.findClass("  s0.a.a.a.i.j", lpparam.classLoader);
////
////                                Object obj_e = XposedHelpers.getStaticObjectField(c_j,"x");
////                                Object obj_a = XposedHelpers.getObjectField(obj_e,"a");
////
////                                Object obj_i0 = XposedHelpers.newInstance(c_l0,ctx1,obj_a);
////                                ////// 上面构造完成this.p.b
//
//
//                                //Object obj_e = (String)XposedHelpers.getObjectField(obj,"b");
//                             //   XposedHelpers.setObjectField(obj, "e", ooo);
//                                // XposedHelpers.setObjectField(obj,"d",ooo);
//                                XposedBridge.log("Context= " + uid);
//                            }catch(Exception e)
//                                {
//                                    e.printStackTrace();
//                                }
//                        }
                    XposedBridge.log("Context= " );
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    XposedBridge.log("Context= " );
                }
            });


//            final Class<?> c_NearbyListActivity = XposedHelpers.findClass("jp.naver.line.android.activity.nearby.NearbyListActivity", lpparam.classLoader);
//            final Class<?> c_a  = XposedHelpers.findClass("i0.a.a.a.b.n0.u.a", lpparam.classLoader);
//
//            XposedHelpers.findAndHookMethod(c_NearbyListActivity, "onRespondNearbyDialog",c_a,
//                    new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("当前Fb4aBundle 3");
//                     //       XposedBridge.log("当前Fb4aBundle 3:"+ param.args[0].toString());
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
//
//            XposedHelpers.findAndHookMethod(c_NearbyListActivity, "onCreate",Bundle.class,
//                    new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("当前onCreate");
//
//
//                        }
//
//                    });
//
//            XposedHelpers.findAndHookMethod(c_NearbyListActivity, "onActivityResult",int.class,int.class, Intent.class,
//                    new XC_MethodHook() {
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("当前onActivityResult");
//
//
//                        }
//
//                    });


//            final Class<?> c_n0  = XposedHelpers.findClass("i0.a.a.a.b.n0.v.a", lpparam.classLoader);
//
//            XposedBridge.hookAllConstructors(c_n0, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                    Object obj = (Object) param.args[0];
//                    try {
//                        String id = (String)XposedHelpers.getObjectField(param.args[0],"a");
//                        String far = XposedHelpers.getObjectField(param.args[0],"b").toString();
//                        Object e = XposedHelpers.getObjectField(param.args[0],"e");
//                        String nickname = XposedHelpers.getObjectField(e,"f").toString();
//                        String h = XposedHelpers.getObjectField(e,"h").toString();
//                        String j = XposedHelpers.getObjectField(e,"j").toString();
//
//                        XposedBridge.log("当前onActivityResult");
//
//                        final String all = "昵称:" + nickname + "\n" + "签名:" + j + "\n" + "UID:" + id + "\n" + "距离:" +far +  '\n' + "头像:" + h + '\n' ;
//
//                        new Thread() {
//                            public void run() {
//                                Looper.prepare();
//                                Toast toast = Toast.makeText(ctx1, all, Toast.LENGTH_LONG);
//                                showMyToast(toast, 20 * 1000);
//                              //  Toast.makeText(ctx1,all,Toast.LENGTH_SHORT);
//                                Looper.loop();
//                            }
//                        }.start();
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });


        }
    }
}




