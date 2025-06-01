package com.hzbhd.canbus.car_cus._467.smartPanel.window;

import android.content.Context;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.car_cus._467.air.view.CheckItemView;
import com.hzbhd.canbus.comm.ScreenConfig;
import com.hzbhd.canbus.util.ContextUtil;
import com.hzbhd.canbus.util.SharePreUtil;

public class CarSelectWindow {
   public boolean addTag;
   private WindowManager.LayoutParams layoutParams;
   private WindowManager mWindowManager;
   private String mode;
   private CheckItemView mode1;
   private CheckItemView mode2;
   private CheckItemView mode3;
   private Button ok;
   private ConstraintLayout round_view;
   private View view;

   private CarSelectWindow(Context var1) {
      this.addTag = false;
      this.mode = "200T";
      this.initWindow(var1);
   }

   // $FF: synthetic method
   CarSelectWindow(Context var1, Object var2) {
      this(var1);
   }

   public static CarSelectWindow getInstance() {
      return CarSelectWindow.Holder.INSTANCE;
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
      String var3 = SharePreUtil.getStringValue(var1, "ID439.CAR.SELECT", "200T");
      if (var3.equals("200T")) {
         this.selectMode(1);
      } else if (var3.equals("160T")) {
         this.selectMode(2);
      } else if (var3.equals("60T")) {
         this.selectMode(3);
      }

   }

   private void intiView(Context var1) {
      if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
         this.view = LayoutInflater.from(var1).inflate(2131558564, (ViewGroup)null);
      } else {
         this.view = LayoutInflater.from(var1).inflate(2131558565, (ViewGroup)null);
      }

      Button var2 = (Button)this.view.findViewById(2131362918);
      this.ok = var2;
      var2.setOnClickListener(new View.OnClickListener(this, var1) {
         final CarSelectWindow this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClick(View var1) {
            SharePreUtil.setStringValue(this.val$context, "ID439.CAR.SELECT", this.this$0.mode);
            this.this$0.hide();
            Toast.makeText(this.val$context, "SUCCESS", 0).show();
            Process.killProcess(Process.myPid());
            System.exit(0);
         }
      });
      CheckItemView var4 = (CheckItemView)this.view.findViewById(2131362860);
      this.mode1 = var4;
      var4.setTitle(var1.getString(2131765994));
      this.mode1.setOnClickListener(new View.OnClickListener(this) {
         final CarSelectWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.selectMode(1);
         }
      });
      var4 = (CheckItemView)this.view.findViewById(2131362861);
      this.mode2 = var4;
      var4.setTitle(var1.getString(2131765995));
      this.mode2.setOnClickListener(new View.OnClickListener(this) {
         final CarSelectWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.selectMode(2);
         }
      });
      var4 = (CheckItemView)this.view.findViewById(2131362862);
      this.mode3 = var4;
      var4.setTitle(var1.getString(2131765996));
      this.mode3.setOnClickListener(new View.OnClickListener(this) {
         final CarSelectWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.selectMode(3);
         }
      });
      ConstraintLayout var3 = (ConstraintLayout)this.view.findViewById(2131363205);
      this.round_view = var3;
      var3.setOnClickListener(new View.OnClickListener(this) {
         final CarSelectWindow this$0;

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
         if (var1 != 2) {
            if (var1 == 3) {
               this.mode = "60T";
               this.mode1.check(false);
               this.mode2.check(false);
               this.mode3.check(true);
            }
         } else {
            this.mode = "160T";
            this.mode1.check(false);
            this.mode2.check(true);
            this.mode3.check(false);
         }
      } else {
         this.mode = "200T";
         this.mode1.check(true);
         this.mode2.check(false);
         this.mode3.check(false);
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

   private static class Holder {
      private static final CarSelectWindow INSTANCE = new CarSelectWindow(ContextUtil.getInstance().getContext());
   }
}
