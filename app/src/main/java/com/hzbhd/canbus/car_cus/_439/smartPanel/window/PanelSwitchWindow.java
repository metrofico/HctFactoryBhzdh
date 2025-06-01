package com.hzbhd.canbus.car_cus._439.smartPanel.window;

import android.content.Context;
import android.provider.Settings.System;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.car_cus._439.air.view.CheckItemView2;
import com.hzbhd.ui.util.BaseUtil;

public class PanelSwitchWindow {
   public boolean addTag = false;
   private Button cancel;
   private WindowManager.LayoutParams layoutParams;
   private WindowManager mWindowManager;
   private CheckItemView2 off_view;
   private CheckItemView2 on_view;
   private Button reset;
   private ConstraintLayout round_view;
   private View view;

   public PanelSwitchWindow(Context var1) {
      this.initWindow(var1);
   }

   private void initWindow(Context var1) {
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams(2003);
      this.layoutParams = var2;
      var2.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.flags = 1024;
      this.intiView(var1);
   }

   private void intiView(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558537, (ViewGroup)null);
      this.view = var2;
      this.round_view = (ConstraintLayout)var2.findViewById(2131363205);
      this.on_view = (CheckItemView2)this.view.findViewById(2131362925);
      this.off_view = (CheckItemView2)this.view.findViewById(2131362917);
      this.on_view.setTitle(var1.getString(2131766020));
      this.off_view.setTitle(var1.getString(2131766021));
      if (System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_439", 1) == 1) {
         this.on_view.check(true);
         this.off_view.check(false);
      } else {
         this.on_view.check(false);
         this.off_view.check(true);
      }

      this.on_view.setOnClickListener(new View.OnClickListener(this) {
         final PanelSwitchWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.on_view.check(true);
            this.this$0.off_view.check(false);
         }
      });
      this.off_view.setOnClickListener(new View.OnClickListener(this) {
         final PanelSwitchWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.on_view.check(false);
            this.this$0.off_view.check(true);
         }
      });
      this.reset = (Button)this.view.findViewById(2131363092);
      Button var3 = (Button)this.view.findViewById(2131362092);
      this.cancel = var3;
      var3.setOnClickListener(new View.OnClickListener(this) {
         final PanelSwitchWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
         }
      });
      this.reset.setOnClickListener(new View.OnClickListener(this, var1) {
         final PanelSwitchWindow this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClick(View var1) {
            if (this.this$0.on_view.isCheck()) {
               System.putInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_439", 1);
            } else {
               System.putInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_439", 0);
            }

            Context var2 = this.val$context;
            Toast.makeText(var2, var2.getString(2131766022), 0).show();
            java.lang.System.exit(0);
            this.this$0.hide();
         }
      });
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
