package com.microntek.controlsettings;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class canbus61keyMode extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private void SetCmdkey(byte var1) {
      this.SendCanBusCmdData2E((byte)-116, new byte[]{var1}, 1);
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131165209:
            this.SetCmdkey((byte)1);
            break;
         case 2131165214:
            this.SetCmdkey((byte)8);
            break;
         case 2131165216:
            this.SetCmdkey((byte)3);
            break;
         case 2131165221:
            this.SetCmdkey((byte)6);
            break;
         case 2131165230:
            this.SetCmdkey((byte)4);
            break;
         case 2131165236:
            this.SetCmdkey((byte)2);
            break;
         case 2131165246:
            this.SetCmdkey((byte)9);
            break;
         case 2131165256:
            this.SetCmdkey((byte)5);
            break;
         case 2131165266:
            this.SetCmdkey((byte)7);
      }

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
      if (var2.getAction() != 3) {
      }

      return false;
   }
}
