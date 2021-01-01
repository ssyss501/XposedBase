package com.example.xposedbase.hook.Function;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

public class Kakaotalk extends BaseHook {
    String PACKAGE_NAME = "com.kakao.talk";


        @Override
    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        super.onLoadPackage(lpparam);
        //  XposedBridge.log("Package name:"+lpparam.packageName);
        if (PACKAGE_NAME.equals(lpparam.packageName)) {
            XposedBridge.log("KaoTalk启动");

            XposedHelpers.findAndHookMethod(SQLiteDatabase.class, "insertWithOnConflict",
                    String.class, String.class, ContentValues.class, int.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("当前表:" + (String) param.args[0]);



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


//                            if("chat_logs" == (String) param.args[0])
//                            {
//                                ContentValues contentValues = (ContentValues) param.args[2];
//                                String message = (String) contentValues.get("message");
//                                long id = (long) contentValues.get("user_id");
//                                DataBaseResourceCrypto m_d= DataBaseResourceCrypto.GetInstance(id,29);
//                                String plain_txt = m_d.decrypt(message);
//                                XposedBridge.log("内容为:"+ plain_txt);
//
////                                for (Map.Entry<String, Object> item : contentValues.valueSet())
////                                {
////                                    if(item.getValue()!=null)
////                                    {
////                                        XposedBridge.log(item.getKey()+" = "+item.getValue());
////                                    }
////                                }
//
//                            }

                            if("friends" == (String) param.args[0])
                            {
                                ContentValues contentValues = (ContentValues) param.args[2];

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

                        }

                    });



            Class<?> c_decrypt = XposedHelpers.findClass("com.kakao.talk.util.DataBaseResourceCrypto", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_decrypt, "a",String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("wa 666");
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


                            System.out.println(123);

                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("wa 777");
                            if(param.getResult()!=null) XposedBridge.log((String)param.getResult());


                        }
                    });



            Class<?> frienddao = XposedHelpers.findClass("com.kakao.talk.db.model.Friend", lpparam.classLoader);
            XposedBridge.hookAllConstructors(frienddao, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });

            Class<?> c_FriendDecryptor = XposedHelpers.findClass("com.kakao.talk.util.FriendDecryptor", lpparam.classLoader);
            XposedBridge.hookAllConstructors(c_FriendDecryptor, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });



            Class<?> c_Projector = XposedHelpers.findClass("com.kakao.talk.dream.Projector", lpparam.classLoader);
            XposedHelpers.findAndHookMethod(c_Projector, "incept",int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("c_ChatRoom 777");


                        }
                    });



            Class<?> c_BaseChatRoomItem = XposedHelpers.findClass("com.kakao.talk.activity.main.chatroom.BaseChatRoomItem", lpparam.classLoader);
            XposedBridge.log("c_BaseChatRoomItem = "+c_BaseChatRoomItem);
            XposedBridge.hookAllConstructors(c_BaseChatRoomItem, new XC_MethodHook() {

                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("before c_BaseChatRoomItem进来了");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("c_BaseChatRoomItem进来了");
                }
            });



            Class<?> c_ChatRoom = XposedHelpers.findClass("com.kakao.talk.chatroom.ChatRoom", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_ChatRoom, "w",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("c_ChatRoom 666");


                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("c_ChatRoom 777");


                        }
                    });




            XposedHelpers.findAndHookMethod(c_ChatRoom, "v",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("v 666");


                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("v 777");


                        }
                    });

            Class<?> c_OpenLink = XposedHelpers.findClass("com.kakao.talk.openlink.db.model.OpenLink", lpparam.classLoader);
            XposedBridge.log("c_OpenLink= "+c_OpenLink);
            XposedBridge.hookAllConstructors(c_OpenLink, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("c_OpenLink 进来了 ");
                }
            });



//
//            XposedHelpers.findAndHookMethod(c_decrypt, "d",c_ChatLog,
//                    new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            param.method.getClass();
//
//                            XposedBridge.log("wa 666");
//
//                            System.out.println(123);
//
//                        }
//                    });
//


          //  Class<?> c_ChatLog = XposedHelpers.findClass("com.kakao.talk.db.model.chatlog.ChatLog$VField", lpparam.classLoader);
            Class<?> c_ChatLog = XposedHelpers.findClass("com.kakao.talk.db.model.chatlog.PhotoChatLog", lpparam.classLoader);

            XposedBridge.hookAllConstructors(c_ChatLog, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("c_ChatLog 进来了 ");
                }
            });


            Class<?> c_ChatMediaUri = XposedHelpers.findClass("com.kakao.talk.chat.media.ChatMediaUri", lpparam.classLoader);

            XposedBridge.hookAllConstructors(c_ChatMediaUri, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("c_ChatMediaUri 进来了 ");
                }
            });

            Class<?> c_ResourceRepository = XposedHelpers.findClass("com.kakao.talk.util.ResourceRepository", lpparam.classLoader);

            XposedHelpers.findAndHookMethod(c_ResourceRepository, "e",String.class,String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            System.out.println(123);

                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);

                            XposedBridge.log("wa 777");
                            if(param.getResult()!=null) XposedBridge.log((String)param.getResult());


                        }
                    });


        }
    }
}