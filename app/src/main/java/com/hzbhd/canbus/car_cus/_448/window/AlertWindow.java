package com.hzbhd.canbus.car_cus._448.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.view.EverScrollTextView;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;

public class AlertWindow {
   private boolean addTag = false;
   private EverScrollTextView everScrollTextView;
   private Button i_know;
   private WindowManager.LayoutParams layoutParams;
   private Context mContext;
   private WindowManager mWindowManager;
   private LinearLayout out_view_lin;
   private View view;

   public AlertWindow(Context var1, String var2, String var3, ActionCallback var4) {
      this.initWindow(var1, var2, var3, var4);
   }

   private void hide() {
      BaseUtil.INSTANCE.runUi(new AlertWindow$$ExternalSyntheticLambda1(this));
   }

   private void initWindow(Context var1, String var2, String var3, ActionCallback var4) {
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var5 = new WindowManager.LayoutParams(2024);
      this.layoutParams = var5;
      var5.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.flags = 1024;
      View var6 = LayoutInflater.from(var1).inflate(2131558602, (ViewGroup)null);
      this.view = var6;
      EverScrollTextView var7 = (EverScrollTextView)var6.findViewById(2131361915);
      this.everScrollTextView = var7;
      var7.setText(var2);
      Button var8 = (Button)this.view.findViewById(2131362380);
      this.i_know = var8;
      var8.setText(var3);
      this.i_know.setOnClickListener(new View.OnClickListener(this, var4) {
         final AlertWindow this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void onClick(View var1) {
            this.val$actionCallback.toDo((Object)null);
            this.this$0.hide();
         }
      });
      LinearLayout var9 = (LinearLayout)this.view.findViewById(2131362932);
      this.out_view_lin = var9;
      var9.setOnClickListener(new View.OnClickListener(this) {
         final AlertWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
         }
      });
   }

   // $FF: synthetic method
   Unit lambda$hide$0$com_hzbhd_canbus_car_cus__448_window_AlertWindow() {
      if (this.addTag) {
         this.mWindowManager.removeView(this.view);
         this.addTag = false;
      }

      return null;
   }

   // $FF: synthetic method
   Unit lambda$show$1$com_hzbhd_canbus_car_cus__448_window_AlertWindow() {
      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
      }

      return null;
   }

   public void show() {
      BaseUtil.INSTANCE.runUi(new AlertWindow$$ExternalSyntheticLambda0(this));
   }
}
