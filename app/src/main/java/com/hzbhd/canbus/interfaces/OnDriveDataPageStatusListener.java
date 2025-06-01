package com.hzbhd.canbus.interfaces;

public interface OnDriveDataPageStatusListener {
   int ON_CREATE = -1;
   int ON_DESTORY = -2;

   void onStatusChange(int var1);
}
