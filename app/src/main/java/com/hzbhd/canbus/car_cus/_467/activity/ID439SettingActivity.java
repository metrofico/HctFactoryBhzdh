package com.hzbhd.canbus.car_cus._467.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.car_cus._467.air.window.AirSettingWindow;
import com.hzbhd.canbus.car_cus._467.smartPanel.window.CarSelectWindow;
import com.hzbhd.canbus.car_cus._467.smartPanel.window.PanelSwitchWindow;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.comm.ScreenConfig;
import com.hzbhd.ui.util.BaseUtil;

public class ID439SettingActivity extends AppCompatActivity {
   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
         this.setContentView(2131558683);
      } else {
         this.setContentView(2131558684);
      }

      TextView var3 = (TextView)this.findViewById(2131363474);
      TextView var4 = (TextView)this.findViewById(2131363475);
      TextView var5 = (TextView)this.findViewById(2131363476);
      TextView var2 = (TextView)this.findViewById(2131363477);
      var3.setOnTouchListener(new View.OnTouchListener(this, var3) {
         final ID439SettingActivity this$0;
         final TextView val$switch1;

         {
            this.this$0 = var1;
            this.val$switch1 = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$switch1.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
               return true;
            } else if (var2.getAction() == 1) {
               this.val$switch1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
               BaseUtil.INSTANCE.startActivity(Constant.SwcActivity);
               return true;
            } else {
               return false;
            }
         }
      });
      var4.setOnTouchListener(new View.OnTouchListener(this, var4) {
         final ID439SettingActivity this$0;
         final TextView val$switch2;

         {
            this.this$0 = var1;
            this.val$switch2 = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$switch2.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
               return true;
            } else if (var2.getAction() == 1) {
               this.val$switch2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
               (new PanelSwitchWindow(this.this$0)).show();
               return true;
            } else {
               return false;
            }
         }
      });
      var5.setOnTouchListener(new View.OnTouchListener(this, var5) {
         final ID439SettingActivity this$0;
         final TextView val$switch3;

         {
            this.this$0 = var1;
            this.val$switch3 = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$switch3.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
               return true;
            } else if (var2.getAction() == 1) {
               this.val$switch3.setBackgroundColor(Color.parseColor("#00FFFFFF"));
               CarSelectWindow.getInstance().show();
               return true;
            } else {
               return false;
            }
         }
      });
      var2.setOnTouchListener(new View.OnTouchListener(this, var2) {
         final ID439SettingActivity this$0;
         final TextView val$switch4;

         {
            this.this$0 = var1;
            this.val$switch4 = var2;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$switch4.setBackgroundColor(Color.parseColor("#3EFFFFFF"));
               return true;
            } else if (var2.getAction() == 1) {
               this.val$switch4.setBackgroundColor(Color.parseColor("#00FFFFFF"));
               (new AirSettingWindow(this.this$0)).show();
               return true;
            } else {
               return false;
            }
         }
      });
   }
}
