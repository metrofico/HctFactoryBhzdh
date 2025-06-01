package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SetAlertView {
   TextView content;
   CountDownTimer countDownTimer;
   Button i_know;

   private static int getScreenHeight(Context var0) {
      return var0.getResources().getDisplayMetrics().heightPixels;
   }

   private static int getScreenWidth(Context var0) {
      return var0.getResources().getDisplayMetrics().widthPixels;
   }

   public void showDialog(Context var1, String var2) {
      View var4 = LayoutInflater.from(var1).inflate(2131558513, (ViewGroup)null, true);
      AlertDialog var3 = (new AlertDialog.Builder(var1)).setView(var4).create();
      if (this.content == null) {
         this.content = (TextView)var4.findViewById(2131361915);
      }

      this.content.setText(var2);
      if (this.i_know == null) {
         this.i_know = (Button)var4.findViewById(2131362380);
      }

      this.i_know.setOnClickListener(new View.OnClickListener(this, var3) {
         final SetAlertView this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var2;
         }

         public void onClick(View var1) {
            this.val$dialog.dismiss();
         }
      });
      var3.setCancelable(false);
      var3.getWindow().setBackgroundDrawableResource(17170445);
      var3.getWindow().setType(2003);
      var3.show();
      if (getScreenWidth(var1) > getScreenHeight(var1)) {
         var3.getWindow().setLayout(getScreenHeight(var1) / 2, getScreenHeight(var1) / 2);
      } else {
         var3.getWindow().setLayout(getScreenWidth(var1) / 2, getScreenWidth(var1) / 2);
      }

      CountDownTimer var5 = this.countDownTimer;
      if (var5 != null) {
         var5.cancel();
      }

      this.countDownTimer = (new CountDownTimer(this, 3000L, 1000L, var3) {
         final SetAlertView this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var6;
         }

         public void onFinish() {
            AlertDialog var1 = this.val$dialog;
            if (var1 != null) {
               var1.dismiss();
            }

         }

         public void onTick(long var1) {
         }
      }).start();
   }
}
