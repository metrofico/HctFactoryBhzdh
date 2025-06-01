package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.util.SharePreUtil;

public class CarIdView extends LinearLayout {
   private static String KEY_446_CAR_NUMBER;
   private TextView name;
   private TextView ok;
   private LinearLayout set_lin;
   private View view;

   public CarIdView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarIdView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarIdView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.view = LayoutInflater.from(var1).inflate(2131558592, this, true);
      this.name = (TextView)this.findViewById(2131362886);
      this.ok = (TextView)this.findViewById(2131362918);
      this.set_lin = (LinearLayout)this.view.findViewById(2131363317);
      this.name.setOnClickListener(new View.OnClickListener(this) {
         final CarIdView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.set_lin.setVisibility(0);
         }
      });
      this.ok.setOnClickListener(new View.OnClickListener(this, var1) {
         final CarIdView this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClick(View var1) {
            if (((EditText)this.this$0.view.findViewById(2131362151)).getText().toString().trim().length() == 0) {
               Context var2 = this.val$context;
               Toast.makeText(var2, var2.getString(2131766049), 0).show();
            } else {
               SharePreUtil.setStringValue(this.val$context, CarIdView.KEY_446_CAR_NUMBER, ((EditText)this.this$0.view.findViewById(2131362151)).getText().toString().trim());
               this.this$0.set_lin.setVisibility(8);
               this.this$0.name.setText(((EditText)this.this$0.view.findViewById(2131362151)).getText().toString().trim());
            }

         }
      });
      String var4 = SharePreUtil.getStringValue(var1, KEY_446_CAR_NUMBER, "FFFF");
      if (var4.equals("FFFF")) {
         this.name.setText(var1.getString(2131766049));
      } else {
         this.name.setText(var4);
      }

   }
}
