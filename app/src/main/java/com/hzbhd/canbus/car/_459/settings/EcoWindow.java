package com.hzbhd.canbus.car._459.settings;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class EcoWindow {
   private ActionCallback actionCallback;
   private boolean addTag;
   private TextView alert;
   private Button cancel;
   private Context context;
   private CountDownTimer countDownTimer;
   private WindowManager.LayoutParams layoutParams;
   private WindowManager mWindowManager;
   private Button ok;
   private TextView time;
   private TextView title;
   private View view;

   private EcoWindow() {
      this.addTag = false;
   }

   // $FF: synthetic method
   EcoWindow(Object var1) {
      this();
   }

   public static EcoWindow getInstance() {
      return EcoWindow.Holder.INSTANCE;
   }

   public void hide() {
      if (this.addTag) {
         CountDownTimer var1 = this.countDownTimer;
         if (var1 != null) {
            var1.cancel();
         }

         this.mWindowManager.removeView(this.view);
         this.addTag = false;
      }

   }

   public void initWindow(Context var1, String var2) {
      this.context = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var3 = new WindowManager.LayoutParams(2024);
      this.layoutParams = var3;
      var3.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.flags = 16777512;
      if (var2.equals("c9")) {
         this.view = LayoutInflater.from(var1).inflate(2131558560, (ViewGroup)null);
      } else {
         this.view = LayoutInflater.from(var1).inflate(2131558561, (ViewGroup)null);
      }

      Button var4 = (Button)this.view.findViewById(2131362092);
      this.cancel = var4;
      var4.setOnClickListener(new View.OnClickListener(this) {
         final EcoWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
            if (this.this$0.actionCallback != null) {
               this.this$0.actionCallback.actionDo("cancel");
            }

         }
      });
      var4 = (Button)this.view.findViewById(2131362918);
      this.ok = var4;
      var4.setOnClickListener(new View.OnClickListener(this) {
         final EcoWindow this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
            if (this.this$0.actionCallback != null) {
               this.this$0.actionCallback.actionDo("ok");
            }

         }
      });
      this.time = (TextView)this.view.findViewById(2131363526);
      this.title = (TextView)this.view.findViewById(2131363545);
      this.alert = (TextView)this.view.findViewById(2131361912);
   }

   public void show(Context var1, String var2, ActionCallback var3) {
      this.actionCallback = var3;
      this.initWindow(var1, var2);
      this.title.setText(var1.getString(2131766451));
      this.alert.setText(var1.getString(2131766452));
      this.cancel.setText(var1.getString(2131766455));
      this.ok.setText(var1.getString(2131766454));
      if (!this.addTag) {
         this.addTag = true;
         var3.actionDo("countdown");
         this.mWindowManager.addView(this.view, this.layoutParams);
         CountDownTimer var4 = new CountDownTimer(this, 10000L, 1000L, var1, var3) {
            final EcoWindow this$0;
            final ActionCallback val$actionCallback;
            final Context val$context;

            {
               this.this$0 = var1;
               this.val$context = var6;
               this.val$actionCallback = var7;
            }

            public void onFinish() {
               this.val$actionCallback.actionDo("cancel");
               this.this$0.hide();
            }

            public void onTick(long var1) {
               String var3 = this.val$context.getString(2131766453);
               if (var3.equals("ZH")) {
                  this.this$0.time.setText(var1 / 1000L + 1L + "秒后自动取消");
               } else if (var3.equals("EN")) {
                  this.this$0.time.setText("Automatically cancel after " + (var1 / 1000L + 1L) + " seconds");
               } else if (var3.equals("RU")) {
                  this.this$0.time.setText("Автоматическая отмена через " + (var1 / 1000L + 1L) + " секунд.");
               }

            }
         };
         this.countDownTimer = var4;
         var4.start();
      }

   }

   public interface ActionCallback {
      void actionDo(String var1);
   }

   private static class Holder {
      private static final EcoWindow INSTANCE = new EcoWindow();
   }
}
