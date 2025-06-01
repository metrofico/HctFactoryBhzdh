package com.hzbhd.canbus.car_cus._436.view.childView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;

public class ItemDialog3 {
   public void showDialog(Context var1, String var2, String var3, String var4, MdOnClickListener var5) {
      View var9 = LayoutInflater.from(var1).inflate(2131558519, (ViewGroup)null, true);
      TextView var6 = (TextView)var9.findViewById(2131362525);
      TextView var7 = (TextView)var9.findViewById(2131362526);
      TextView var8 = (TextView)var9.findViewById(2131362527);
      var7.setText(var3);
      var6.setText(var2);
      var8.setText(var4);
      AlertDialog var10 = (new AlertDialog.Builder(var1)).setView(var9).create();
      var10.setCancelable(true);
      var10.getWindow().setBackgroundDrawableResource(17170445);
      var10.getWindow().setType(2003);
      var10.show();
      var6.setOnClickListener(new View.OnClickListener(this, var5, var10) {
         final ItemDialog3 this$0;
         final AlertDialog val$dialog;
         final MdOnClickListener val$mdOnClickListener;

         {
            this.this$0 = var1;
            this.val$mdOnClickListener = var2;
            this.val$dialog = var3;
         }

         public void onClick(View var1) {
            this.val$mdOnClickListener.clickPosition(0);
            this.val$dialog.dismiss();
         }
      });
      var7.setOnClickListener(new View.OnClickListener(this, var5, var10) {
         final ItemDialog3 this$0;
         final AlertDialog val$dialog;
         final MdOnClickListener val$mdOnClickListener;

         {
            this.this$0 = var1;
            this.val$mdOnClickListener = var2;
            this.val$dialog = var3;
         }

         public void onClick(View var1) {
            this.val$mdOnClickListener.clickPosition(1);
            this.val$dialog.dismiss();
         }
      });
      var8.setOnClickListener(new View.OnClickListener(this, var5, var10) {
         final ItemDialog3 this$0;
         final AlertDialog val$dialog;
         final MdOnClickListener val$mdOnClickListener;

         {
            this.this$0 = var1;
            this.val$mdOnClickListener = var2;
            this.val$dialog = var3;
         }

         public void onClick(View var1) {
            this.val$mdOnClickListener.clickPosition(2);
            this.val$dialog.dismiss();
         }
      });
   }
}
