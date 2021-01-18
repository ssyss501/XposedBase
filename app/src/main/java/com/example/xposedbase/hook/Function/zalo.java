package com.example.xposedbase.hook.Function;

import android.app.Activity;
import android.app.AndroidAppHelper;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
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
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

public class zalo extends BaseHook {
    String PACKAGE_NAME = "com.zing.zalo";
    public Context ctx1;
//    public onClickReceiver oCR=null;
    XC_LoadPackage.LoadPackageParam g_lpparam =null;
//    class onClickReceiver extends BroadcastReceiver {
//        ClassLoader cl;
//
//        public onClickReceiver(ClassLoader cl) {
//            this.cl = cl;
//        }
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            XposedBridge.log("onClickReceiver 接收");
//            Class<?> c_ae =XposedHelpers.findClass("com.zing.zalo.g.ae", g_lpparam.classLoader);
//            Object obj = XposedHelpers.newInstance(c_ae);
//            double d=121.5210083;
//            double d2=38.8587082;
//            String str="6660";
//            String str2="460";
//            String str3="46000";
//            String str4="16416";
//            String str5="cc:08:fb:70:21:48";
//            int i=0;
//            int i2=2;
//            String str6="{\"allow_mock_location\":-1,\"use_gps\":1,\"has_gps_status_changed\":1,\"from_mock_provider\":0,\"mock_applications\":[]}";
//            XposedHelpers.callMethod(obj, "a",d,d2,str,str2,str3,str4,str5,i,i2,str6 );
//            XposedBridge.log("onClickReceiver 完成");
//        }
//    }

    public static String intToString(int time) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long timeLong = Long.valueOf(time);
            String stime = simpleDateFormat.format(new Date(timeLong * 1000L));
            return stime;
        } catch (Exception e) {
            return "";
        }
    }

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

    @Override
    public void onLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);


        //  XposedBridge.log("Package name:"+lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            g_lpparam= lpparam;
            XposedBridge.log("zalo启动");

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




//            Class<?>  c_log=  XposedHelpers.findClass("d.a.a$a", lpparam.classLoader);
//
//
//            XposedHelpers.findAndHookMethod(c_log, "getStackTraceString",
//               Throwable.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("isLoggable进来了" );
//
//
//                }
//
//            });


            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {

                @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Log.e("handleLoadPackage", "init");

//                            Context arg = (Context) param.args[0];
//                            final ClassLoader cl = arg.getClassLoader();
//                            if (null == oCR) {
//                                oCR = new onClickReceiver(cl);
//                                // 注册自定义动态广播消息
//                                IntentFilter filter_dynamic = new IntentFilter("com.Xposebase.onClick");
//                                arg.registerReceiver(oCR, filter_dynamic);
//                                Toast.makeText(arg, "广播注册成功", Toast.LENGTH_SHORT).show();
//                        }


                    XposedHelpers.findAndHookMethod("d.a.a$a", lpparam.classLoader,"a",int.class, Throwable.class, String.class, Object[].class,new XC_MethodHook() {

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("Context= ");
                           // param.setResult(true);
                        }
                    });





                        }
                    });

            XposedHelpers.findAndHookMethod(ContextThemeWrapper.class, "attachBaseContext", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    ctx1 = (Context) param.args[0];
                    XposedBridge.log("Context= " + ctx1);
                }
            });


            XposedHelpers.findAndHookMethod("com.zing.zalocore.utils.a", lpparam.classLoader,"init", byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalocore.utils.a", lpparam.classLoader,"c", byte[].class, int.class, byte[].class,int.class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalo.g.aa", lpparam.classLoader,"a", String[].class,String[].class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalo.r.c.a", lpparam.classLoader,"CW", String.class,new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                    Log.i("Dump Stack: ", "---------------start-------- CW--------");
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


            XposedHelpers.findAndHookMethod("com.zing.zalo.r.b", lpparam.classLoader,"cnx",new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
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

            XposedHelpers.findAndHookMethod("com.zing.zalo.r.a", lpparam.classLoader,"bS",Context.class,String.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
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


            XposedHelpers.findAndHookMethod("com.zing.zalo.r.a", lpparam.classLoader,"f",Context.class,String.class,long.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");

                }
            });


            XposedHelpers.findAndHookMethod("com.zing.zalo.utils.cy", lpparam.classLoader,"oG",Context.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");

                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalo.utils.cy", lpparam.classLoader,"oX",Context.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");

                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalocore.a.f", lpparam.classLoader,"acp",String.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");

                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalo.utils.cryptology.AESUtils", lpparam.classLoader,"VT",int.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");

                }
            });


            XposedHelpers.findAndHookMethod("com.zing.zalo.g.aa", lpparam.classLoader,"aJM",new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.g", lpparam.classLoader,"send",new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.g", lpparam.classLoader,"a",String.class,String.class,String[].class,String[].class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });


            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.g", lpparam.classLoader,"N",new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalo.zalosdk.core.helper.e", lpparam.classLoader,"a",String.class, String.class, String[].class, String[].class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });


            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.g", lpparam.classLoader,"af",Map.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });

            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.k", lpparam.classLoader,"a",String.class, String.class, String.class, Hashtable.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });




            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.k", lpparam.classLoader,"a",String.class, String.class, String[].class, String[].class, String.class, String.class, String.class, long.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });


            XposedHelpers.findAndHookMethod("com.zing.zalocore.connection.k", lpparam.classLoader,"ag",Map.class,new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("Context= ");
                }
            });


            Class<?> c_ContactProfile = XposedHelpers.findClass("com.zing.zalo.control.ContactProfile", lpparam.classLoader);
            XposedBridge.log("c_ContactProfile=:" + c_ContactProfile);

            XposedHelpers.findAndHookConstructor(c_ContactProfile, JSONObject.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);

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


                    XposedBridge.log("c_ContactProfile进来了 三参数:");
                    JSONObject JS = (JSONObject) param.args[0];
                    try {
                        String lastUpdate = intToString(JS.getInt("lastUpdateTime"));
                        int uid = JS.getInt("uid");
                        String nickname = (String) JS.getString("dpn");
                        int sex = JS.getInt("ged");
                        String sSex = sex == 0 ? "男" : "女";
                        String birthday = (String) JS.getString("sdob");
                        String avatar = (String) JS.getString("avt");
                        String bg = (String) JS.getString("cover");
                        String sign = (String) JS.getString("stt");
                        String last_action = intToString(JS.getInt("last_action"));
                        //   Context ctx = AndroidAppHelper.currentApplication().getApplicationContext();
                        final String all = "昵称:" + nickname + "\n" + "性别:" + sSex + "\n" + "生日:" + birthday + "\n" + "签名:" + sign + "\n" + "UID:" + uid + "\n" + "最后上线:" + last_action + "\n" + "最后更新:" + lastUpdate + '\n' + "头像:" + avatar + '\n' + "背景:" + bg + '\n';
                        // Looper.prepare();
                 //       Toast toast = Toast.makeText(ctx1, all, Toast.LENGTH_LONG);
                  //      showMyToast(toast, 20 * 1000);
                        // Looper.loop();
                        new Thread() {
                            public void run() {
                                Looper.prepare();
                                Toast toast = Toast.makeText(ctx1, all, Toast.LENGTH_LONG);
                                showMyToast(toast, 20 * 1000);
                                Looper.loop();
                                }
                        }.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    XposedBridge.log("c_ContactProfile进来了 三参数:");
                }
            });


            Class<?> c_p =XposedHelpers.findClass("com.zing.zalo.connection.p", lpparam.classLoader);
            Class<?> c_socket =XposedHelpers.findClass("com.zing.zalocore.connection.socket.c", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_p, "c",c_socket, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("c_p进来了" );
                            byte[] bArr = (byte[]) XposedHelpers.getObjectField(param.args[0],"pqN");

                            String buffer= new String(bArr, StandardCharsets.UTF_8);
                            XposedBridge.log("buffer="+buffer );


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

                        }

                    });


            Class<?> c_bc =XposedHelpers.findClass("com.zing.zalo.g.bc", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_bc, "o",JSONObject.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("c_bc进来了" );
                    byte[] bArr = (byte[]) XposedHelpers.getObjectField(param.args[0],"pqN");

                    String buffer= new String(bArr, StandardCharsets.UTF_8);
                    XposedBridge.log("buffer="+buffer );

                }

            });

        Class<?> c_ay =XposedHelpers.findClass("com.zing.zalo.connection.ay", lpparam.classLoader);

        XposedBridge.hookAllConstructors(c_ay, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                XposedBridge.log("c_bc进来了" );
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





            Class<?> c_ccc =XposedHelpers.findClass("com.zing.zalocore.connection.socket.c", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_ccc, "bu",byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("c_ccc进来了" );
                    byte[] bArr = (byte[]) param.args[0];
                    String buffer= new String(bArr, StandardCharsets.UTF_8);
                    XposedBridge.log("buffer="+buffer );

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




            Class<?> c_ae =XposedHelpers.findClass("com.zing.zalo.g.ae", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_ae, "a",double.class, double.class, String.class, String.class, String.class, String.class, String.class, int.class, int.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("c_ccc进来了" );
//                    byte[] bArr = (byte[]) param.args[0];
//                    String buffer= new String(bArr, StandardCharsets.UTF_8);
//                    XposedBridge.log("buffer="+buffer );
//
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

            });


//
//            Class<?> an =XposedHelpers.findClass("com.zing.zalo.social.controls.an", lpparam.classLoader);
//            XposedBridge.log("an=:" + an);
//
//            XposedHelpers.findAndHookConstructor(an,  JSONObject.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("an进来了 三参数:" );
//
//                }
//            });
//
//            XposedHelpers.findAndHookConstructor(an, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("an进来了 无参数:");
//
//                }
//            });
//
//
//
//            Class<?> ae =XposedHelpers.findClass("com.zing.zalo.social.a.ae", lpparam.classLoader);
//            XposedBridge.log("ae="+ae );
//            Class<?> aj =XposedHelpers.findClass("com.zing.zalo.social.a.aj", lpparam.classLoader);
//            XposedBridge.log("aj="+aj );
//            Class<?> c_TrackingSource= XposedHelpers.findClass("com.zing.zalo.control.TrackingSource", lpparam.classLoader);
//            Class<?>  c_ah=  XposedHelpers.findClass("com.zing.zalo.social.a.ah", lpparam.classLoader);
//
//
//            XposedHelpers.findAndHookMethod(ae, "getItem",
//            int.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("getItem进来了" );
//
//
//                }
//
//            });
//
//            XposedHelpers.findAndHookMethod(aj, "getItem",
//                    int.class, new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("getItem进来了" );
//
//
//                        }
//
//                    });
//
////            Class<?>  c_im=  XposedHelpers.findClass("com.zing.zalo.utils.im", lpparam.classLoader);
////            XposedHelpers.findAndHookMethod(c_im, "efh", new XC_MethodHook() {
////                        @Override
////                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                            super.beforeHookedMethod(param);
////                            XposedBridge.log("efh进来了" );
////
////
////                        }
////
////                    });
//
//            XposedHelpers.findAndHookMethod("com.zing.zalo.utils.im",lpparam.classLoader,"efh", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("efh进来了" );
//
//
//                }
//
//            });


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


//            XposedHelpers.findAndHookConstructor(ae, Integer.class, Context.class, String.class,c_ah, c_TrackingSource, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("an进来了 三参数:" );
//
//                }
//            });


//            XposedHelpers.findAndHookMethod("com.zing.zalo.social.controls.an", lpparam.classLoader,"an",
//                     new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("an进来了:" + (String) param.args[0]);
//
//
//                        }
//
//                    });


        }
    }
}




