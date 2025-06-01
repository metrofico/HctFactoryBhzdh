package com.microntek.controlsettings;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class canbus34settings extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdDataFD((byte)6, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   public void onClick(View var1) {
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230724);
      this.findViewById(2131165211).setOnClickListener(this);
      this.findViewById(2131165212).setOnClickListener(this);
      this.findViewById(2131165213).setOnClickListener(this);
      this.findViewById(2131165257).setOnClickListener(this);
      this.findViewById(2131165210).setOnClickListener(this);
      this.findViewById(2131165231).setOnClickListener(this);
      this.findViewById(2131165219).setOnClickListener(this);
      this.findViewById(2131165206).setOnClickListener(this);
      this.findViewById(2131165211).setOnTouchListener(this);
      this.findViewById(2131165212).setOnTouchListener(this);
      this.findViewById(2131165213).setOnTouchListener(this);
      this.findViewById(2131165257).setOnTouchListener(this);
      this.findViewById(2131165210).setOnTouchListener(this);
      this.findViewById(2131165231).setOnTouchListener(this);
      this.findViewById(2131165219).setOnTouchListener(this);
      this.findViewById(2131165206).setOnTouchListener(this);
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var3 = var2.getAction();
      if (var3 != 3 && var3 != 1) {
         if (var3 == 0) {
            switch (var1.getId()) {
               case 2131165206:
                  this.SetCmdkey(0, 32);
                  break;
               case 2131165210:
                  this.SetCmdkey(1, 0);
                  break;
               case 2131165211:
                  this.SetCmdkey(16, 0);
                  break;
               case 2131165212:
                  this.SetCmdkey(8, 0);
                  break;
               case 2131165213:
                  this.SetCmdkey(4, 0);
                  break;
               case 2131165219:
                  this.SetCmdkey(0, 64);
                  break;
               case 2131165231:
                  this.SetCmdkey(0, 128);
                  break;
               case 2131165257:
                  this.SetCmdkey(2, 0);
                  break;
               default:
                  return false;
            }
         }
      } else {
         this.SetCmdkey(0, 0);
      }

      return false;
   }
}
