package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;

public class UPDProgressActivity extends Activity {
   private static final String CAN_TYPE_ID = "can_type_id";
   private static final String IS_CANBUS_SWC_UPD = "is_canbus_swc_upd";
   private Handler mHandler = new Handler(this) {
      final UPDProgressActivity this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  this.this$0.mUpgradeTitleTv.setText(var1.obj + " " + this.this$0.getResources().getString(2131768898));
               }
            } else {
               System.exit(0);
            }
         } else {
            this.this$0.progressSeek(var1.arg1);
         }

         super.handleMessage(var1);
      }
   };
   private boolean mIsCanSwcUpd;
   private TextView mProgressInfoTv;
   BroadcastReceiver mReceiver = new BroadcastReceiver(this) {
      final UPDProgressActivity this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         if (var2.getAction().equals("com.hzbhd.intent.action.Mcusmodule.upgrade") || var2.getAction().equals("com.hzbhd.intent.action.upgrade")) {
            if (var2.getStringExtra("canbus_swc_upd_name") != null) {
               this.this$0.mHandler.sendMessage(this.this$0.mHandler.obtainMessage(3, 0, 0, var2.getStringExtra("canbus_swc_upd_name")));
            }

            if (var2.getIntExtra("result", 0) == 2) {
               Toast.makeText(this.this$0.getApplicationContext(), "upgrade success", 1).show();
            } else if (var2.getIntExtra("result", 0) == -1) {
               Toast.makeText(this.this$0.getApplicationContext(), "upgrade error!", 1).show();
               this.this$0.mUpgradeTitleTv.setText(2131770252);
               this.this$0.showUpgradeDialog(2131770252);
            } else if (var2.getIntExtra("result", 0) == -2) {
               this.this$0.mUpgradeTitleTv.setText(2131769403);
               this.this$0.showUpgradeDialog(2131769403);
            } else if (var2.getIntExtra("result", 0) == -3) {
               this.this$0.mUpgradeTitleTv.setText(2131769401);
               this.this$0.showUpgradeDialog(2131769401);
            } else if (var2.getIntExtra("result", 0) == -4) {
               this.this$0.mUpgradeTitleTv.setText(2131769390);
               this.this$0.showUpgradeDialog(2131769390);
            } else if (var2.getIntExtra("result", 0) == -5) {
               this.this$0.mUpgradeTitleTv.setText(2131769407);
               this.this$0.showUpgradeDialog(2131769407);
            } else if (var2.getIntExtra("result", 0) == -6) {
               this.this$0.mUpgradeTitleTv.setText(2131769392);
               this.this$0.showUpgradeDialog(2131769392);
            } else if (var2.getIntExtra("result", 0) == -100) {
               this.this$0.mUpgradeTitleTv.setText(2131769607);
               this.this$0.showUpgradeDialog(2131769607);
            } else if (var2.getIntExtra("result", 0) == -102) {
               this.this$0.mHandler.sendMessage(this.this$0.mHandler.obtainMessage(1, 100, 0));
               return;
            }

            int var3 = var2.getIntExtra("progress", 0);
            if (var3 > 0) {
               this.this$0.mHandler.sendMessage(this.this$0.mHandler.obtainMessage(1, var3, 0));
            }
         }

      }
   };
   private SeekBar mSeekBar;
   private TextView mUpgradeTitleTv;

   private void findViews() {
      this.mSeekBar = (SeekBar)this.findViewById(2131363305);
      this.mProgressInfoTv = (TextView)this.findViewById(2131362710);
      this.mUpgradeTitleTv = (TextView)this.findViewById(2131362711);
   }

   private void initViews() {
      int var1 = this.getIntent().getIntExtra("can_type_id", 0);
      this.mIsCanSwcUpd = this.getIntent().getBooleanExtra("is_canbus_swc_upd", false);
      IntentFilter var2 = new IntentFilter();
      if (this.mIsCanSwcUpd) {
         var2.addAction("com.hzbhd.intent.action.upgrade");
      } else {
         var2.addAction("com.hzbhd.intent.action.Mcusmodule.upgrade");
      }

      this.registerReceiver(this.mReceiver, var2);
      this.progressSeek(0);
      String var3 = Util.getFileName(var1);
      Intent var4 = new Intent();
      var4.setAction("com.hzbhd.intent.action.upgrade.service");
      if (this.mIsCanSwcUpd) {
         var4.putExtra("module", "CANBUS_SWC_UPD");
      } else {
         this.mUpgradeTitleTv.setText(var3 + " " + this.getResources().getString(2131768898));
         var4.putExtra("module", "MCU_CANBUS_UPD");
         var4.putExtra("ASSET_FILE_NAME", var3);
      }

      this.sendBroadcast(var4);
   }

   public static void openActivity(Context var0, int var1, boolean var2) {
      Intent var3 = new Intent(var0, UPDProgressActivity.class);
      var3.putExtra("can_type_id", var1);
      var3.putExtra("is_canbus_swc_upd", var2);
      var0.startActivity(var3);
   }

   private void showUpgradeDialog(int var1) {
      this.findViewById(2131362828).setVisibility(4);
      AlertDialog.Builder var2 = new AlertDialog.Builder(this);
      var2.setCancelable(false);
      var2.setTitle(this.getString(2131770251));
      var2.setMessage(var1);
      var2.setCancelable(false);
      var2.setOnDismissListener(new DialogInterface.OnDismissListener(this) {
         final UPDProgressActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onDismiss(DialogInterface var1) {
            this.this$0.finish();
         }
      });
      var2.setPositiveButton(this.getString(2131768068), new DialogInterface.OnClickListener(this) {
         final UPDProgressActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.finish();
         }
      });
      var2.show();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.requestWindowFeature(1);
      this.getWindow().setFlags(1024, 1024);
      this.setContentView(2131558700);
      this.findViews();
      this.initViews();
   }

   protected void onDestroy() {
      super.onDestroy();
      this.unregisterReceiver(this.mReceiver);
   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      return true;
   }

   public void progressSeek(int var1) {
      int var2;
      if (var1 < 0) {
         var2 = 0;
      } else {
         var2 = var1;
         if (var1 > 100) {
            var2 = 100;
         }
      }

      this.mSeekBar.setProgress(var2);
      this.mProgressInfoTv.setText(this.getResources().getString(2131770250) + " " + var2 + "%");
      if (var2 == 100) {
         this.finish();
         if (this.mIsCanSwcUpd) {
            SendKeyManager.getInstance().resetMpu(HotKeyConstant.RESET_MODE.NORMAL, 1);
         }
      }

   }
}
