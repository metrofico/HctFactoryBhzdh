package com.hzbhd.canbus.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.service.TestCanBusService;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SetErrorLogView;
import com.hzbhd.canbus.util.SetErrorProgressView;
import com.hzbhd.canbus.util.SharePreUtil;

public class TestCanBusActivity extends AppCompatActivity implements View.OnClickListener {
   public static final String BROADCAST_ACTION_DISC = "com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast";
   public static final String BROADCAST_ACTION_SUCCESS = "com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast_SUCCESS";
   private static final String CAN_BUS_SWITCH_KEY = "TEST_SERVICE_SWITCH_KEY";
   private static Button start_button;
   private ServiceConnection conn = new ServiceConnection(this) {
      final TestCanBusActivity this$0;

      {
         this.this$0 = var1;
      }

      public void onServiceConnected(ComponentName var1, IBinder var2) {
         this.this$0.isBind = true;
         TestCanBusService.MyBinder var3 = (TestCanBusService.MyBinder)var2;
         this.this$0.service = var3.getService();
         this.this$0.service.getTag();
      }

      public void onServiceDisconnected(ComponentName var1) {
         this.this$0.isBind = false;
      }
   };
   Intent intent;
   boolean isBind;
   private LocatiopnBroadcast locatiopnBroadcast;
   TestCanBusService service;
   SetErrorLogView setErrorLogView;
   SetErrorProgressView setErrorProgressView;

   private void bindService() {
      MyLog.temporaryTracking("绑定服务");
      if (this.intent == null) {
         this.intent = new Intent(this, TestCanBusService.class);
      }

      this.bindService(this.intent, this.conn, 1);
   }

   private void startService() {
      MyLog.temporaryTracking("启动服务");
      if (this.intent == null) {
         this.intent = new Intent(this, TestCanBusService.class);
      }

      this.startService(this.intent);
   }

   private void stopService() {
      MyLog.temporaryTracking("停止服务");
      Intent var1 = this.intent;
      if (var1 != null) {
         this.stopService(var1);
      }

   }

   private void unBindService() {
      MyLog.temporaryTracking("解除绑定服务");

      try {
         this.unbindService(this.conn);
      } catch (Exception var2) {
         MyLog.temporaryTracking("ServiceConnection没有绑定任何Service");
      }

   }

   public void onClick(View var1) {
      if (var1.getId() == 2131363423) {
         this.startService();
         SharePreUtil.setBoolValue(this, "TEST_SERVICE_SWITCH_KEY", true);
         this.showProgressView(true, false);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558698);
      Button var2 = (Button)this.findViewById(2131363423);
      start_button = var2;
      var2.setOnClickListener(new TestCanBusActivity$$ExternalSyntheticLambda0(this));
      if (SharePreUtil.getBoolValue(this, "TEST_SERVICE_SWITCH_KEY", false)) {
         this.bindService();
      }

   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      if (var2.getAction() == 0 && var1 == 4 && SharePreUtil.getBoolValue(this, "TEST_SERVICE_SWITCH_KEY", false)) {
         Toast.makeText(this, "检测状态下不允许返回", 0).show();
         return false;
      } else {
         return super.onKeyDown(var1, var2);
      }
   }

   protected void onResume() {
      super.onResume();
      this.locatiopnBroadcast = new LocatiopnBroadcast(this);
      IntentFilter var1 = new IntentFilter();
      var1.addAction("com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast");
      var1.addAction("com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast_SUCCESS");
      this.registerReceiver(this.locatiopnBroadcast, var1);
   }

   public void showErrorLogView(String var1) {
      if (this.setErrorLogView == null) {
         this.setErrorLogView = new SetErrorLogView();
      }

      this.setErrorLogView.showDialog(this, var1, new SetErrorLogView.ThisInterface(this) {
         final TestCanBusActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean ignoreCallBack() {
            if (this.this$0.service == null) {
               Toast.makeText(this.this$0, "Operation too fast！", 0).show();
               this.this$0.bindService();
               return false;
            } else {
               this.this$0.service.continueTest();
               this.this$0.showProgressView(true, false);
               return true;
            }
         }
      });
   }

   public void showProgressView(boolean var1, boolean var2) {
      if (var1) {
         start_button.setVisibility(8);
      } else {
         start_button.setVisibility(0);
      }

      if (this.setErrorProgressView == null) {
         this.setErrorProgressView = new SetErrorProgressView();
      }

      this.setErrorProgressView.showDialog(this, var1, var2, new SetErrorProgressView.ThisInterface(this) {
         final TestCanBusActivity this$0;

         {
            this.this$0 = var1;
         }

         public boolean onCancel() {
            if (this.this$0.service == null) {
               Toast.makeText(this.this$0, "Operation too fast！", 0).show();
               this.this$0.bindService();
               return false;
            } else {
               this.this$0.showProgressView(false, false);
               SharePreUtil.setBoolValue(this.this$0, "TEST_SERVICE_SWITCH_KEY", false);
               this.this$0.stopService();

               try {
                  if (this.this$0.locatiopnBroadcast != null) {
                     TestCanBusActivity var1 = this.this$0;
                     var1.unregisterReceiver(var1.locatiopnBroadcast);
                  }

                  this.this$0.finish();
               } catch (Exception var2) {
                  MyLog.temporaryTracking(var2.toString());
               }

               this.this$0.service.stopTest();
               return true;
            }
         }

         public boolean onContinue() {
            if (this.this$0.service == null) {
               Toast.makeText(this.this$0, "Operation too fast！", 0).show();
               this.this$0.bindService();
               return false;
            } else {
               this.this$0.service.continueTest();
               return true;
            }
         }

         public boolean onPause() {
            if (this.this$0.service == null) {
               Toast.makeText(this.this$0, "Operation too fast！", 0).show();
               this.this$0.bindService();
               return false;
            } else {
               this.this$0.service.pauseTest();
               return true;
            }
         }

         public boolean onSuccessButtonClick() {
            this.this$0.showProgressView(false, false);
            SharePreUtil.setBoolValue(this.this$0, "TEST_SERVICE_SWITCH_KEY", false);
            this.this$0.stopService();

            try {
               if (this.this$0.locatiopnBroadcast != null) {
                  TestCanBusActivity var1 = this.this$0;
                  var1.unregisterReceiver(var1.locatiopnBroadcast);
               }

               if (this.this$0.service != null) {
                  this.this$0.service.stopTest();
               }

               this.this$0.finish();
            } catch (Exception var2) {
               MyLog.temporaryTracking(var2.toString());
            }

            return true;
         }
      });
   }

   public class LocatiopnBroadcast extends BroadcastReceiver {
      final TestCanBusActivity this$0;

      public LocatiopnBroadcast(TestCanBusActivity var1) {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         MyLog.temporaryTracking("有收到广播");
         if (var2.getAction().equals("com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast")) {
            MyLog.temporaryTracking("收到了ERROR广播");
            if (SharePreUtil.getBoolValue(this.this$0, "TEST_SERVICE_SWITCH_KEY", false)) {
               this.this$0.runOnUiThread(new Runnable(this, var2) {
                  final LocatiopnBroadcast this$1;
                  final Intent val$intent;

                  {
                     this.this$1 = var1;
                     this.val$intent = var2;
                  }

                  public void run() {
                     MyLog.temporaryTracking("错误信息：" + this.val$intent.getStringExtra("ERROR_DATA"));
                     this.this$1.this$0.showProgressView(false, false);
                     this.this$1.this$0.showErrorLogView(this.val$intent.getStringExtra("ERROR_DATA"));
                  }
               });
            }
         }

         if (var2.getAction().equals("com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast_SUCCESS")) {
            MyLog.temporaryTracking("收到了SUCCESS广播");
            if (SharePreUtil.getBoolValue(this.this$0, "TEST_SERVICE_SWITCH_KEY", false)) {
               this.this$0.runOnUiThread(new Runnable(this) {
                  final LocatiopnBroadcast this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void run() {
                     this.this$1.this$0.showProgressView(true, true);
                  }
               });
            }
         }

      }
   }
}
