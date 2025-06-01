package com.microntek.controlsettings;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class canbus63seat extends BaseActivity implements View.OnTouchListener {
   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, var2}, 2);
   }

   private void setTextView(int var1, String var2) {
      ((TextView)this.findViewById(var1)).setText(var2);
   }

   protected void ProcessData(byte[] var1) {
      if (var1.length >= 4) {
         StringBuilder var2;
         if (var1[2] == 22) {
            var2 = new StringBuilder();
            var2.append("");
            var2.append(var1[3] & 3);
            this.setTextView(2131165265, var2.toString());
         } else if (var1[2] == 23) {
            var2 = new StringBuilder();
            var2.append("");
            var2.append(var1[3] & 3);
            this.setTextView(2131165264, var2.toString());
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230726);
      this.findViewById(2131165215).setOnTouchListener(this);
      this.findViewById(2131165185).setOnTouchListener(this);
      this.setTextView(2131165265, "0");
      this.setTextView(2131165264, "0");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{82, 22}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{82, 23}, 2);
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var3 = var2.getAction();
      int var4 = var1.getId();
      if (var3 != 3 && var3 != 1) {
         if (var3 == 0) {
            if (var4 != 2131165185) {
               if (var4 == 2131165215) {
                  this.SetCmdkey((byte)22, (byte)2);
               }
            } else {
               this.SetCmdkey((byte)23, (byte)2);
            }
         }
      } else if (var4 != 2131165185) {
         if (var4 == 2131165215) {
            this.SetCmdkey((byte)22, (byte)1);
         }
      } else {
         this.SetCmdkey((byte)23, (byte)1);
      }

      return false;
   }

   public void syncbutton(View var1) {
      var1.getId();
   }
}
