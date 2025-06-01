package com.hzbhd.canbus.car_cus._439.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ID439MainActivity extends Activity {
   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558665);
      ImageView var2 = (ImageView)this.findViewById(2131362205);
      var2.setOnTouchListener(new View.OnTouchListener(this, var2) {
         final ID439MainActivity this$0;
         final ImageView val$drive;

         {
            this.this$0 = var1;
            this.val$drive = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$drive.setBackgroundResource(2131231510);
               return true;
            } else if (var2.getAction() == 1) {
               this.val$drive.setBackgroundResource(2131231509);
               this.this$0.startActivity(new Intent(this.this$0, ID439DriveDataActivity.class));
               return true;
            } else {
               return false;
            }
         }
      });
      var2 = (ImageView)this.findViewById(2131363319);
      var2.setOnTouchListener(new View.OnTouchListener(this, var2) {
         final ID439MainActivity this$0;
         final ImageView val$setting;

         {
            this.this$0 = var1;
            this.val$setting = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$setting.setBackgroundResource(2131231508);
               return true;
            } else if (var2.getAction() == 1) {
               this.val$setting.setBackgroundResource(2131231507);
               this.this$0.startActivity(new Intent(this.this$0, ID439SettingActivity.class));
               return true;
            } else {
               return false;
            }
         }
      });
   }
}
