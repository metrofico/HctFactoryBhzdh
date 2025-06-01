package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.hzbhd.canbus.adapter.util.CrashReportUtils;

public class OpenCanBusBuglyView {
   Button open;

   private static int getScreenHeight(Context var0) {
      return var0.getResources().getDisplayMetrics().heightPixels;
   }

   private static int getScreenWidth(Context var0) {
      return var0.getResources().getDisplayMetrics().widthPixels;
   }

   public void showDialog(Context var1) {
      View var3 = LayoutInflater.from(var1).inflate(2131558622, (ViewGroup)null, true);
      AlertDialog var2 = (new AlertDialog.Builder(var1)).setView(var3).create();
      if (this.open == null) {
         this.open = (Button)var3.findViewById(2131362926);
      }

      this.open.setOnClickListener(new View.OnClickListener(this, var3, var1, var2) {
         final OpenCanBusBuglyView this$0;
         final Context val$context;
         final AlertDialog val$dialog;
         final View val$view;

         {
            this.this$0 = var1;
            this.val$view = var2;
            this.val$context = var3;
            this.val$dialog = var4;
         }

         public void onClick(View var1) {
            if (((EditText)this.val$view.findViewById(2131362968)).getText().toString().trim().equals("on")) {
               CrashReportUtils.openCanBusBugly(this.val$context, true);
               Toast.makeText(this.val$context, 2131767546, 1).show();
               this.val$dialog.dismiss();
            } else if (((EditText)this.val$view.findViewById(2131362968)).getText().toString().trim().equals("off")) {
               CrashReportUtils.openCanBusBugly(this.val$context, false);
               Toast.makeText(this.val$context, 2131767543, 1).show();
               this.val$dialog.dismiss();
            } else {
               Toast.makeText(this.val$context, 2131767544, 0).show();
               ((EditText)this.val$view.findViewById(2131362968)).setText("");
            }

         }
      });
      var2.getWindow().setBackgroundDrawableResource(17170445);
      var2.getWindow().setType(2003);
      var2.show();
      if (getScreenWidth(var1) > getScreenHeight(var1)) {
         var2.getWindow().setLayout(getScreenHeight(var1) / 2, getScreenHeight(var1) / 2);
      } else {
         var2.getWindow().setLayout(getScreenWidth(var1) / 2, getScreenWidth(var1) / 2);
      }

   }
}
