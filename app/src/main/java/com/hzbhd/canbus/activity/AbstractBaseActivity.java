package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.MgrRefreshUiInterface;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;

public abstract class AbstractBaseActivity extends Activity implements MgrRefreshUiInterface {
   private boolean isActivityFront = false;

   public UiMgrInterface getUiMgrInterface(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      if (var2 != null) {
         if (!MyApplication.IS_SET) {
            var2.updateUiByDifferentCar(var1);
            MyApplication.IS_SET = true;
         }

         return var2;
      } else {
         throw new NullPointerException("1.请检查是否在car目录下有该车型的文件夹；2.尝试在UiMgr构造函数下使用try...catch...排查；3.检查json是否与Bean文件对应");
      }
   }

   public boolean isShouldRefreshUi() {
      return this.isActivityFront;
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      ((MyApplication)this.getApplication()).addActivity(this);
   }

   protected void onDestroy() {
      super.onDestroy();
      ((MyApplication)this.getApplication()).removeActivity(this);
   }

   protected void onPause() {
      this.isActivityFront = false;
      super.onPause();
   }

   protected void onResume() {
      super.onResume();
      MsgMgrInterfaceUtil var1 = MsgMgrFactory.getCanMsgMgrUtil(this);
      if (var1 != null) {
         var1.setMgrRefreshUiInterface(this);
      }

      this.isActivityFront = true;
   }
}
