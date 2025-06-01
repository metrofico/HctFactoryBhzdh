package com.hzbhd.canbus.car._467;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class AlertWindowView {
   public boolean addTag = false;
   private TextView contentTxt;
   private Button i_know;
   private WindowManager.LayoutParams layoutParams;
   private Context mContext;
   private WindowManager mWindowManager;
   private View view;

   private void initView(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558532, (ViewGroup)null);
      this.view = var2;
      this.contentTxt = (TextView)var2.findViewById(2131361915);
      if (this.i_know == null) {
         this.i_know = (Button)this.view.findViewById(2131362380);
      }

      this.i_know.setOnClickListener(new View.OnClickListener(this) {
         final AlertWindowView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
         }
      });
   }

   public void hide() {
      WindowManager var1;
      boolean var10001;
      try {
         var1 = this.mWindowManager;
      } catch (Exception var5) {
         var10001 = false;
         return;
      }

      if (var1 != null) {
         View var2;
         try {
            var2 = this.view;
         } catch (Exception var4) {
            var10001 = false;
            return;
         }

         if (var2 != null) {
            try {
               if (this.addTag) {
                  var1.removeView(var2);
                  this.addTag = false;
               }
            } catch (Exception var3) {
               var10001 = false;
            }
         }
      }

   }

   public void initWindow(Context var1) {
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams();
      this.layoutParams = var2;
      var2.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.type = 2010;
      this.layoutParams.flags = 16777512;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.width = -1;
      this.layoutParams.height = -1;
      this.initView(var1);
   }

   public void show(Context var1, String var2) {
      label18: {
         WindowManager var5 = this.mWindowManager;
         if (var5 != null) {
            WindowManager.LayoutParams var3 = this.layoutParams;
            if (var3 != null) {
               View var4 = this.view;
               if (var4 != null) {
                  if (!this.addTag) {
                     var5.addView(var4, var3);
                     this.addTag = true;
                  }
                  break label18;
               }
            }
         }

         this.initWindow(var1);
         if (!this.addTag) {
            this.mWindowManager.addView(this.view, this.layoutParams);
            this.addTag = true;
         }
      }

      this.contentTxt.setText(var2);
   }
}
