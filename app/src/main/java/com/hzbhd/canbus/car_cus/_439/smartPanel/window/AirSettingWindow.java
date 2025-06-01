package com.hzbhd.canbus.car_cus._439.smartPanel.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.car_cus._439.air.view.CheckItemView2;
import com.hzbhd.canbus.util.SharePreUtil;

public class AirSettingWindow {
   public boolean addTag = false;
   private Button cancel;
   private boolean isShowAirSettings = false;
   private WindowManager.LayoutParams layoutParams;
   private WindowManager mWindowManager;
   private CheckItemView2 mode1;
   private CheckItemView2 mode2;
   private Button ok;
   private ConstraintLayout round_view;
   private View view;

   public AirSettingWindow(Context var1) {
      this.initWindow(var1);
   }

   private void initWindow(Context var1) {
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var3 = new WindowManager.LayoutParams(2003);
      this.layoutParams = var3;
      var3.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.flags = 1024;
      this.intiView(var1);
      boolean var2 = SharePreUtil.getBoolValue(var1, "key.air.settings.show.tag", false);
      this.isShowAirSettings = var2;
      if (var2) {
         this.selectMode(1);
      } else {
         this.selectMode(2);
      }

   }

   private void intiView(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558543, (ViewGroup)null);
      this.view = var2;
      Button var4 = (Button)var2.findViewById(2131362918);
      this.ok = var4;
      var4.setOnClickListener(new View.OnClickListener(this, var1) {
         final AirSettingWindow this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClick(View var1) {
            SharePreUtil.setBoolValue(this.val$context, "key.air.settings.show.tag", this.this$0.isShowAirSettings);
            this.this$0.hide();
            Toast.makeText(this.val$context, "SUCCESS", 0).show();
         }
      });
      CheckItemView2 var5 = (CheckItemView2)this.view.findViewById(2131362860);
      this.mode1 = var5;
      var5.setTitle(var1.getString(2131766015));
      this.mode1.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.selectMode(1);
         }
      });
      var5 = (CheckItemView2)this.view.findViewById(2131362861);
      this.mode2 = var5;
      var5.setTitle(var1.getString(2131766016));
      this.mode2.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.selectMode(2);
         }
      });
      Button var3 = (Button)this.view.findViewById(2131362092);
      this.cancel = var3;
      var3.setOnClickListener(new View.OnClickListener(this) {
         final AirSettingWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
         }
      });
   }

   private void selectMode(int var1) {
      if (var1 != 1) {
         if (var1 == 2) {
            this.isShowAirSettings = false;
            this.mode1.check(false);
            this.mode2.check(true);
         }
      } else {
         this.isShowAirSettings = true;
         this.mode1.check(true);
         this.mode2.check(false);
      }

   }

   public void hide() {
      if (this.addTag) {
         this.mWindowManager.removeView(this.view);
         this.addTag = false;
      }

   }

   public void show() {
      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
      }

   }
}
