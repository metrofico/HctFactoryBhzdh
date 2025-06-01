package com.hzbhd.setting.proxy;

public interface IModuleListener {
   Object getData(int var1, int var2);

   void onReceived(int var1, int var2, Object var3);
}
