package com.microntek.controlsettings;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class canbus50keyMode extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private byte keycmd = -1;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdDataFD((byte)8, new byte[]{var1, var2}, 2);
   }

   public void onClick(View var1) {
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230725);
      this.findViewById(2131165236).setOnClickListener(this);
      this.findViewById(2131165236).setOnTouchListener(this);
      this.findViewById(2131165266).setOnClickListener(this);
      this.findViewById(2131165266).setOnTouchListener(this);
      this.findViewById(2131165230).setOnClickListener(this);
      this.findViewById(2131165230).setOnTouchListener(this);
      this.findViewById(2131165221).setOnClickListener(this);
      this.findViewById(2131165221).setOnTouchListener(this);
      this.findViewById(2131165246).setOnClickListener(this);
      this.findViewById(2131165246).setOnTouchListener(this);
      this.findViewById(2131165256).setOnClickListener(this);
      this.findViewById(2131165256).setOnTouchListener(this);
      this.findViewById(2131165209).setOnClickListener(this);
      this.findViewById(2131165209).setOnTouchListener(this);
      this.findViewById(2131165214).setOnClickListener(this);
      this.findViewById(2131165214).setOnTouchListener(this);
      this.findViewById(2131165216).setOnClickListener(this);
      this.findViewById(2131165216).setOnTouchListener(this);
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var3 = var2.getAction();
      if (var3 != 3 && var3 != 1) {
         if (var3 == 0) {
            switch (var1.getId()) {
               case 2131165209:
                  this.SetCmdkey((byte)0, (byte)-128);
                  break;
               case 2131165214:
                  this.SetCmdkey((byte)0, (byte)8);
                  break;
               case 2131165216:
                  this.SetCmdkey((byte)0, (byte)2);
                  break;
               case 2131165221:
                  this.SetCmdkey((byte)0, (byte)16);
                  break;
               case 2131165230:
                  this.SetCmdkey((byte)0, (byte)1);
                  break;
               case 2131165236:
                  this.SetCmdkey((byte)0, (byte)64);
                  break;
               case 2131165246:
                  this.SetCmdkey((byte)32, (byte)0);
                  break;
               case 2131165256:
                  this.SetCmdkey((byte)0, (byte)32);
                  break;
               case 2131165266:
                  this.SetCmdkey((byte)0, (byte)4);
                  break;
               default:
                  return false;
            }
         }
      } else {
         this.SetCmdkey((byte)0, (byte)0);
      }

      return false;
   }
}
