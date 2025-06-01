package com.hzbhd.canbus.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.util.LogUtil;

public class TestActivity extends AbstractBaseActivity implements View.OnClickListener {
   private ServiceConnection mConnection = new ServiceConnection(this) {
      final TestActivity this$0;

      {
         this.this$0 = var1;
      }

      public void onServiceConnected(ComponentName var1, IBinder var2) {
         LogUtil.showLog("fzy", "onServiceConnected");
      }

      public void onServiceDisconnected(ComponentName var1) {
         LogUtil.showLog("fzy", "onServiceDisconnected");
      }
   };

   private void findViews() {
   }

   private void initViews() {
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362059) {
         Intent var2 = new Intent();
         var2.setComponent(HzbhdComponentName.OnPauseOnResumeService);
         this.bindService(var2, this.mConnection, 1);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558697);
      this.findViews();
      this.initViews();
   }

   public void refreshUi(Bundle var1) {
   }
}
