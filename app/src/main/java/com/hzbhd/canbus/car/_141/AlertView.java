package com.hzbhd.canbus.car._141;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AlertView {
   TextView content;
   Button i_know;

   private static int getScreenHeight(Context var0) {
      return var0.getResources().getDisplayMetrics().heightPixels;
   }

   private static int getScreenWidth(Context var0) {
      return var0.getResources().getDisplayMetrics().widthPixels;
   }

   public void showDialog(Context var1, String var2) {
      View var3 = LayoutInflater.from(var1).inflate(2131558513, (ViewGroup)null, true);
      AlertDialog var4 = (new AlertDialog.Builder(var1)).setView(var3).create();
      if (this.content == null) {
         this.content = (TextView)var3.findViewById(2131361915);
      }

      this.content.setText(var2);
      if (this.i_know == null) {
         this.i_know = (Button)var3.findViewById(2131362380);
      }

      this.i_know.setOnClickListener(new View.OnClickListener(this, var4) {
         final AlertView this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var2;
         }

         public void onClick(View var1) {
            this.val$dialog.dismiss();
         }
      });
      var4.setCancelable(false);
      var4.getWindow().setBackgroundDrawableResource(17170445);
      var4.show();
      if (getScreenWidth(var1) > getScreenHeight(var1)) {
         var4.getWindow().setLayout(getScreenHeight(var1) / 2, getScreenHeight(var1) / 2);
      } else {
         var4.getWindow().setLayout(getScreenWidth(var1) / 2, getScreenWidth(var1) / 2);
      }

   }
}
