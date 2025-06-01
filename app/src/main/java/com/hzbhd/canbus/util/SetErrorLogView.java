package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SetErrorLogView {
   AlertDialog dialog;
   Button ignore;
   TextView log_view;
   View view;

   public void showDialog(Context var1, String var2, ThisInterface var3) {
      if (this.view == null) {
         this.view = LayoutInflater.from(var1).inflate(2131558620, (ViewGroup)null, true);
      }

      if (this.dialog == null) {
         this.dialog = (new AlertDialog.Builder(var1)).setView(this.view).create();
      }

      if (this.log_view == null) {
         this.log_view = (TextView)this.view.findViewById(2131362819);
      }

      this.log_view.setText(var2);
      if (this.ignore == null) {
         this.ignore = (Button)this.view.findViewById(2131362464);
      }

      this.ignore.setOnClickListener(new View.OnClickListener(this, var3) {
         final SetErrorLogView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            if (this.val$thisInterface.ignoreCallBack()) {
               this.this$0.dialog.dismiss();
            }

         }
      });
      this.dialog.setCancelable(false);
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.getWindow().setType(2003);
      this.dialog.show();
   }

   public interface ThisInterface {
      boolean ignoreCallBack();
   }
}
