package com.microntek.controlsettings;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class canbus24settingspanel extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private Handler handler = new Handler(this) {
      final canbus24settingspanel this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (this.this$0.keycmd != 0) {
            canbus24settingspanel var2 = this.this$0;
            var2.RequestCmdkey(var2.keycmd, (byte)1);
         }

      }
   };
   private boolean isAirSwap;
   private byte keycmd = 0;
   private Button mCurtain;
   private Button mLef_hot;
   private Button mRedar;
   private Button mRif_hot;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-123, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = var1[0];
      if (var1.length > 2) {
         if (var2 == 7) {
            int var3 = var1[1] >> 4 & 3;
            var2 = var1[1] & 3;
            Button var4;
            StringBuilder var5;
            StringBuilder var8;
            if (this.isAirSwap) {
               if (var2 == 0) {
                  this.mLef_hot.setText(this.getString(2131296836));
               } else {
                  var4 = this.mLef_hot;
                  var5 = new StringBuilder();
                  var5.append(this.getString(2131296836));
                  var5.append("\n");
                  var5.append(var2);
                  var4.setText(var5.toString());
               }

               if (var3 == 0) {
                  this.mRif_hot.setText(this.getString(2131297134));
               } else {
                  var4 = this.mRif_hot;
                  var5 = new StringBuilder();
                  var5.append(this.getString(2131297134));
                  var5.append("\n");
                  var5.append(var3);
                  var4.setText(var5.toString());
               }
            } else {
               if (var3 == 0) {
                  this.mLef_hot.setText(this.getString(2131296836));
               } else {
                  Button var9 = this.mLef_hot;
                  var8 = new StringBuilder();
                  var8.append(this.getString(2131296836));
                  var8.append("\n");
                  var8.append(var3);
                  var9.setText(var8.toString());
               }

               if (var2 == 0) {
                  this.mRif_hot.setText(this.getString(2131297134));
               } else {
                  var4 = this.mRif_hot;
                  var5 = new StringBuilder();
                  var5.append(this.getString(2131297134));
                  var5.append("\n");
                  var5.append(var2);
                  var4.setText(var5.toString());
               }
            }

            if ((var1[2] & 1) != 0) {
               var4 = this.mRedar;
               StringBuilder var6 = new StringBuilder();
               var6.append(this.getString(2131297081));
               var6.append("\n");
               var6.append(this.getString(2131296959));
               var4.setText(var6.toString());
            } else {
               Button var7 = this.mRedar;
               var8 = new StringBuilder();
               var8.append(this.getString(2131297081));
               var8.append("\n");
               var8.append(this.getString(2131296511));
               var7.setText(var8.toString());
            }
         }

      }
   }

   public void onClick(View var1) {
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230723);
      this.isAirSwap = BaseApplication.getInstance().isAirSwap();
      this.mLef_hot = (Button)this.findViewById(2131165220);
      this.mRedar = (Button)this.findViewById(2131165251);
      this.mRif_hot = (Button)this.findViewById(2131165255);
      this.mCurtain = (Button)this.findViewById(2131165208);
      this.mLef_hot.setOnClickListener(this);
      this.mRedar.setOnClickListener(this);
      this.mRif_hot.setOnClickListener(this);
      this.mCurtain.setOnClickListener(this);
      this.mLef_hot.setOnTouchListener(this);
      this.mRedar.setOnTouchListener(this);
      this.mRif_hot.setOnTouchListener(this);
      this.mCurtain.setOnTouchListener(this);
   }

   public void onDestroy() {
      this.handler.removeMessages(1);
      super.onDestroy();
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var5 = var2.getAction();
      byte var3 = 3;
      if (var5 != 3 && var5 != 1) {
         if (var5 == 0) {
            label35: {
               var5 = var1.getId();
               byte var4 = 2;
               switch (var5) {
                  case 2131165208:
                     var3 = 4;
                     break label35;
                  case 2131165220:
                     if (this.isAirSwap) {
                        var3 = var4;
                        break label35;
                     }
                     break;
                  case 2131165251:
                     break label35;
                  case 2131165255:
                     var3 = var4;
                     if (!this.isAirSwap) {
                        break label35;
                     }
                     break;
                  default:
                     return false;
               }

               var3 = 1;
            }

            if (this.keycmd != var3) {
               this.handler.removeMessages(1);
               this.keycmd = var3;
               this.handler.sendEmptyMessage(1);
            }
         }
      } else if (this.keycmd != 0) {
         this.handler.removeMessages(1);
         this.RequestCmdkey(this.keycmd, (byte)0);
         this.keycmd = 0;
      }

      return false;
   }
}
