package com.microntek.controlsettings;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class canbus138keyMode extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private Handler handler = new Handler(this) {
      final canbus138keyMode this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (this.this$0.keycmd != 0) {
            canbus138keyMode var2 = this.this$0;
            var2.SetCmdkey(var2.keycmd);
            this.this$0.handler.sendEmptyMessageDelayed(1, 200L);
         }

      }
   };
   private byte keycmd = 0;

   private void SetCmdkey(byte var1) {
      byte[] var2 = new byte[13];
      var2[0] = var1;
      this.SendCanBusCmdData5AA5((byte)-111, var2, 13);
   }

   public static boolean isKeyMode() {
      int var0 = BaseApplication.getInstance().getCarType();
      return var0 == 4 || var0 == 5 || var0 == 10 || var0 == 3 || var0 == 7 || var0 == 13;
   }

   public void onClick(View var1) {
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230721);
      this.findViewById(2131165206).setOnClickListener(this);
      this.findViewById(2131165261).setOnClickListener(this);
      this.findViewById(2131165219).setOnClickListener(this);
      this.findViewById(2131165231).setOnClickListener(this);
      this.findViewById(2131165268).setOnClickListener(this);
      this.findViewById(2131165206).setOnTouchListener(this);
      this.findViewById(2131165261).setOnTouchListener(this);
      this.findViewById(2131165219).setOnTouchListener(this);
      this.findViewById(2131165231).setOnTouchListener(this);
      this.findViewById(2131165268).setOnTouchListener(this);
      int var2 = BaseApplication.getInstance().getCarType();
      if (var2 == 4 || var2 == 5 || var2 == 10) {
         this.findViewById(2131165206).setVisibility(0);
         this.findViewById(2131165261).setVisibility(0);
      }

      if (var2 == 3) {
         this.findViewById(2131165219).setVisibility(0);
         this.findViewById(2131165261).setVisibility(0);
         this.findViewById(2131165231).setVisibility(0);
      }

      if (var2 == 7 || var2 == 13) {
         this.findViewById(2131165219).setVisibility(0);
         this.findViewById(2131165231).setVisibility(0);
         this.findViewById(2131165268).setVisibility(0);
      }

   }

   public void onDestroy() {
      super.onDestroy();
      this.handler.removeCallbacksAndMessages((Object)null);
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var4 = var2.getAction();
      if (var4 != 3 && var4 != 1) {
         if (var4 == 0) {
            this.putSettingsInt("canbus_138_set_time", 1);
            byte var3;
            switch (var1.getId()) {
               case 2131165206:
                  var3 = -126;
                  break;
               case 2131165219:
                  var3 = -125;
                  break;
               case 2131165231:
                  var3 = -124;
                  break;
               case 2131165261:
                  var3 = -127;
                  break;
               case 2131165268:
                  var3 = -123;
                  break;
               default:
                  return false;
            }

            if (this.keycmd != var3) {
               this.handler.removeMessages(1);
               this.keycmd = var3;
               this.handler.sendEmptyMessage(var3);
            }
         }
      } else if (this.keycmd != 0) {
         this.handler.removeMessages(1);
         this.keycmd = 0;
         this.putSettingsInt("canbus_138_set_time", 0);
      }

      return false;
   }
}
