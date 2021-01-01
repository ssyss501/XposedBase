package com.example.xposedbase.hook;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class BaseHook implements IHook {


//    @Override
//    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
//
//    }

    @Override
    public void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

    }
}
