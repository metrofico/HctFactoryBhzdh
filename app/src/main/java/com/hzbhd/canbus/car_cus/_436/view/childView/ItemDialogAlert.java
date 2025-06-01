package com.hzbhd.canbus.car_cus._436.view.childView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;

public class ItemDialogAlert {
   public void showDialog(Context var1, String var2, MdOnClickListener var3) {
      View var6 = LayoutInflater.from(var1).inflate(2131558521, (ViewGroup)null, true);
      TextView var4 = (TextView)var6.findViewById(2131361913);
      Button var5 = (Button)var6.findViewById(2131362918);
      AlertDialog var7 = (new AlertDialog.Builder(var1)).setView(var6).create();
      var7.setCancelable(true);
      var7.getWindow().setBackgroundDrawableResource(17170445);
      var7.getWindow().setType(2003);
      var7.show();
      var4.setText(var2);
      var5.setOnClickListener(new View.OnClickListener(this, var3, var7) {
         final ItemDialogAlert this$0;
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
   }
}
