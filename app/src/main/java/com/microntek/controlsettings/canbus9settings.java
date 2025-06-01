package com.microntek.controlsettings;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class canbus9settings extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private Handler handler = new Handler(this) {
      final canbus9settings this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (this.this$0.keycmd != 0) {
            canbus9settings var2 = this.this$0;
            var2.SetCmdkey(var2.keycmd);
            this.this$0.handler.sendEmptyMessageDelayed(1, 200L);
         }

      }
   };
   private byte keycmd = 0;

   private void SetCmdkey(byte var1) {
      byte[] var3 = new byte[]{-3, 5, 6, var1, 0, 0};
      short var2 = (short)(var3[1] + var3[2] + var3[3]);
      var3[4] = (byte)(var2 >> 8 & 255);
      var3[5] = (byte)(var2 & 255);
      this.WritePort(var3);
   }

   private void WritePort(byte[] var1) {
      int var3 = var1.length;
      String var4 = "";

      StringBuilder var5;
      for(int var2 = 0; var2 < var3; ++var2) {
         var5 = new StringBuilder();
         var5.append(var4);
         var5.append(this.getNumberValue(var1[var2] & 255));
         String var8 = var5.toString();
         var4 = var8;
         if (var2 < var3 - 1) {
            StringBuilder var7 = new StringBuilder();
            var7.append(var8);
            var7.append(",");
            var4 = var7.toString();
         }
      }

      BaseApplication var6 = BaseApplication.getInstance();
      var5 = new StringBuilder();
      var5.append("canbus_rsp=");
      var5.append(var4);
      var6.setParameters(var5.toString());
   }

   private String getNumberValue(int var1) {
      int var2 = var1 / 10;
      var1 %= 10;
      StringBuilder var3;
      if (var2 == 0) {
         var3 = new StringBuilder();
         var3.append("");
         var3.append(var1);
         return var3.toString();
      } else {
         var3 = new StringBuilder();
         var3.append("");
         var3.append(var2);
         var3.append(var1);
         return var3.toString();
      }
   }

   public void onClick(View var1) {
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230728);
      this.findViewById(2131165221).setOnClickListener(this);
      this.findViewById(2131165256).setOnClickListener(this);
      this.findViewById(2131165246).setOnClickListener(this);
      this.findViewById(2131165186).setOnClickListener(this);
      this.findViewById(2131165262).setOnClickListener(this);
      this.findViewById(2131165217).setOnClickListener(this);
      this.findViewById(2131165205).setOnClickListener(this);
      this.findViewById(2131165237).setOnClickListener(this);
      this.findViewById(2131165238).setOnClickListener(this);
      this.findViewById(2131165239).setOnClickListener(this);
      this.findViewById(2131165240).setOnClickListener(this);
      this.findViewById(2131165241).setOnClickListener(this);
      this.findViewById(2131165242).setOnClickListener(this);
      this.findViewById(2131165243).setOnClickListener(this);
      this.findViewById(2131165244).setOnClickListener(this);
      this.findViewById(2131165245).setOnClickListener(this);
      this.findViewById(2131165261).setOnClickListener(this);
      this.findViewById(2131165206).setOnClickListener(this);
      this.findViewById(2131165221).setOnTouchListener(this);
      this.findViewById(2131165256).setOnTouchListener(this);
      this.findViewById(2131165246).setOnTouchListener(this);
      this.findViewById(2131165186).setOnTouchListener(this);
      this.findViewById(2131165262).setOnTouchListener(this);
      this.findViewById(2131165217).setOnTouchListener(this);
      this.findViewById(2131165205).setOnTouchListener(this);
      this.findViewById(2131165237).setOnTouchListener(this);
      this.findViewById(2131165238).setOnTouchListener(this);
      this.findViewById(2131165239).setOnTouchListener(this);
      this.findViewById(2131165240).setOnTouchListener(this);
      this.findViewById(2131165241).setOnTouchListener(this);
      this.findViewById(2131165242).setOnTouchListener(this);
      this.findViewById(2131165243).setOnTouchListener(this);
      this.findViewById(2131165244).setOnTouchListener(this);
      this.findViewById(2131165245).setOnTouchListener(this);
      this.findViewById(2131165261).setOnTouchListener(this);
      this.findViewById(2131165206).setOnTouchListener(this);
   }

   public void onDestroy() {
      super.onDestroy();
      this.handler.removeMessages(1);
   }

   protected void onStart() {
      super.onStart();
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var4 = var2.getAction();
      byte var3 = 3;
      if (var4 != 3 && var4 != 1) {
         if (var4 == 0) {
            var4 = var1.getId();
            switch (var4) {
               case 2131165186:
                  break;
               case 2131165217:
                  var3 = 6;
                  break;
               case 2131165221:
                  var3 = 4;
                  break;
               case 2131165256:
                  var3 = 5;
                  break;
               default:
                  switch (var4) {
                     case 2131165205:
                        var3 = 7;
                        break;
                     case 2131165206:
                        var3 = 21;
                        break;
                     default:
                        switch (var4) {
                           case 2131165237:
                              var3 = 8;
                              break;
                           case 2131165238:
                              var3 = 9;
                              break;
                           case 2131165239:
                              var3 = 10;
                              break;
                           case 2131165240:
                              var3 = 11;
                              break;
                           case 2131165241:
                              var3 = 12;
                              break;
                           case 2131165242:
                              var3 = 13;
                              break;
                           case 2131165243:
                              var3 = 14;
                              break;
                           case 2131165244:
                              var3 = 15;
                              break;
                           case 2131165245:
                              var3 = 16;
                              break;
                           case 2131165246:
                              var3 = 1;
                              break;
                           default:
                              switch (var4) {
                                 case 2131165261:
                                    var3 = 22;
                                    break;
                                 case 2131165262:
                                    var3 = 2;
                                    break;
                                 default:
                                    return false;
                              }
                        }
                  }
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
         this.SetCmdkey(this.keycmd);
      }

      return false;
   }
}
