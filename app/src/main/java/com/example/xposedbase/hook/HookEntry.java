package com.example.xposedbase.hook;

import com.example.xposedbase.hook.Function.Kakaotalk;
import com.example.xposedbase.hook.Function.dingding;
import com.example.xposedbase.hook.Function.line;
import com.example.xposedbase.hook.Function.twitter;
import com.example.xposedbase.hook.Function.wechat;
import com.example.xposedbase.hook.Function.wework;
import com.example.xposedbase.hook.Function.whatsapp;
import com.example.xposedbase.hook.Function.zalo;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookLoadPackage /*, IXposedHookZygoteInit */{
    List<BaseHook> m_HookList ;

    {
        m_HookList = new ArrayList<>();
       //  m_HookList.add(new wechat());
       //  m_HookList.add(new wework());
        //  m_HookList.add(new dingding());
    //    m_HookList.add(new Kakaotalk());
      //  m_HookList.add(new zalo());
//        m_HookList.add(new facebook());
        m_HookList.add(new line());
   //     m_HookList.add(new whatsapp());
  //      m_HookList.add(new twitter());
//        m_HookList.add(new discord());
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

       //  XposedBridge.log("handleLoadPackage进来了！");
         for(BaseHook b:m_HookList)
         {
            b.onLoadPackage(lpparam);
         }
    }

//    @Override
//    public void initZygote(StartupParam startupParam) throws Throwable {
//        XposedBridge.log("initZygote进来了！");
//    }
}