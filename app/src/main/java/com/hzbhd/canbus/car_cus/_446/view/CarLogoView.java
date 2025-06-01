package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;

public class CarLogoView extends RelativeLayout {
   private Context context;
   private ImageView logoIcon;
   private TextView name;
   private View view;

   public CarLogoView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarLogoView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarLogoView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.context = var1;
      View var4 = LayoutInflater.from(var1).inflate(2131558590, this, true);
      this.view = var4;
      this.logoIcon = (ImageView)var4.findViewById(2131362435);
      TextView var5 = (TextView)this.view.findViewById(2131362886);
      this.name = var5;
      var5.setText(var1.getString(2131766051));
      this.updateLogo();
   }

   public void getAction(ActionDo var1) {
      this.logoIcon.setOnClickListener(new View.OnClickListener(this, var1) {
         final CarLogoView this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            if (WmCarData.logo) {
               this.val$actionDo.toDo("TURN_OFF");
            } else {
               this.val$actionDo.toDo("TURN_ON");
            }

         }
      });
   }

   public void updateLogo() {
      if (WmCarData.logo) {
         this.logoIcon.setBackgroundResource(2131231540);
      } else {
         this.logoIcon.setBackgroundResource(2131231317);
      }

   }
}
