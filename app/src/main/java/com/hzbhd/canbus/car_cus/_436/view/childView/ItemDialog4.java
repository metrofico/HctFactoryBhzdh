package com.hzbhd.canbus.car_cus._436.view.childView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;

public class ItemDialog4 {
   public void showDialog(Context var1, String var2, String var3, String var4, String var5, MdOnClickListener var6) {
      View var11 = LayoutInflater.from(var1).inflate(2131558520, (ViewGroup)null, true);
      TextView var10 = (TextView)var11.findViewById(2131362525);
      TextView var8 = (TextView)var11.findViewById(2131362526);
      TextView var9 = (TextView)var11.findViewById(2131362527);
      TextView var7 = (TextView)var11.findViewById(2131362528);
      var8.setText(var3);
      var10.setText(var2);
      var9.setText(var4);
      var7.setText(var5);
      AlertDialog var12 = (new AlertDialog.Builder(var1)).setView(var11).create();
      var12.setCancelable(true);
      var12.getWindow().setBackgroundDrawableResource(17170445);
      var12.getWindow().setType(2003);
      var12.show();
      var10.setOnClickListener(new View.OnClickListener(this, var6, var12) {
         final ItemDialog4 this$0;
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
      var8.setOnClickListener(new View.OnClickListener(this, var6, var12) {
         final ItemDialog4 this$0;
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
      var9.setOnClickListener(new View.OnClickListener(this, var6, var12) {
         final ItemDialog4 this$0;
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
      var7.setOnClickListener(new View.OnClickListener(this, var6, var12) {
         final ItemDialog4 this$0;
         final AlertDialog val$dialog;
         final MdOnClickListener val$mdOnClickListener;

         {
            this.this$0 = var1;
            this.val$mdOnClickListener = var2;
            this.val$dialog = var3;
         }

         public void onClick(View var1) {
            this.val$mdOnClickListener.clickPosition(3);
            this.val$dialog.dismiss();
         }
      });
   }
}
