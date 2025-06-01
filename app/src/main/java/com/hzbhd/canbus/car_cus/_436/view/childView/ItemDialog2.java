package com.hzbhd.canbus.car_cus._436.view.childView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;

public class ItemDialog2 {
   public void showDialog(Context var1, String var2, String var3, MdOnClickListener var4) {
      View var7 = LayoutInflater.from(var1).inflate(2131558518, (ViewGroup)null, true);
      TextView var6 = (TextView)var7.findViewById(2131362525);
      TextView var5 = (TextView)var7.findViewById(2131362526);
      var5.setText(var3);
      var6.setText(var2);
      AlertDialog var8 = (new AlertDialog.Builder(var1)).setView(var7).create();
      var8.setCancelable(true);
      var8.getWindow().setBackgroundDrawableResource(17170445);
      var8.getWindow().setType(2003);
      var8.show();
      var6.setOnClickListener(new View.OnClickListener(this, var4, var8) {
         final ItemDialog2 this$0;
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
      var5.setOnClickListener(new View.OnClickListener(this, var4, var8) {
         final ItemDialog2 this$0;
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
   }
}
