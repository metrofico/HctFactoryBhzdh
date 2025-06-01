package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import java.text.DecimalFormat;

public class TireView extends RelativeLayout implements CanInfoObserver {
   private DecimalFormat df_2Decimal;
   private TextView front_left_temp;
   private TextView front_left_tire;
   private TextView front_right_temp;
   private TextView front_right_tire;
   private TextView rear_left_temp;
   private TextView rear_left_tire;
   private TextView rear_right_temp;
   private TextView rear_right_tire;
   private View view;

   public TireView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public TireView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public TireView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.df_2Decimal = new DecimalFormat("###0.00");
      this.view = LayoutInflater.from(var1).inflate(2131558600, this, true);
      this.front_left_tire = (TextView)this.findViewById(2131362271);
      this.front_right_tire = (TextView)this.findViewById(2131362292);
      this.rear_left_tire = (TextView)this.findViewById(2131363052);
      this.rear_right_tire = (TextView)this.findViewById(2131363070);
      this.front_left_temp = (TextView)this.findViewById(2131362270);
      this.front_right_temp = (TextView)this.findViewById(2131362291);
      this.rear_left_temp = (TextView)this.findViewById(2131363051);
      this.rear_right_temp = (TextView)this.findViewById(2131363069);
      this.updateUi();
   }

   public void dataChange() {
      this.updateUi();
   }

   public void updateUi() {
      if (WmCarData.tire_unit.equals("KPA")) {
         this.front_left_tire.setText(WmCarData.tire_front_left + "KPA");
         this.front_right_tire.setText(WmCarData.tire_front_right + "KPA");
         this.rear_left_tire.setText(WmCarData.tire_rear_left + "KPA");
         this.rear_right_tire.setText(WmCarData.tire_rear_right + "KPA");
      } else if (WmCarData.tire_unit.equals("PSI")) {
         this.front_left_tire.setText(this.df_2Decimal.format(WmCarData.tire_front_left / 6.894999980926514) + "PSI");
         this.front_right_tire.setText(this.df_2Decimal.format(WmCarData.tire_front_right / 6.894999980926514) + "PSI");
         this.rear_left_tire.setText(this.df_2Decimal.format(WmCarData.tire_rear_left / 6.894999980926514) + "PSI");
         this.rear_right_tire.setText(this.df_2Decimal.format(WmCarData.tire_rear_right / 6.894999980926514) + "PSI");
      }

      this.front_left_temp.setText(WmCarData.tire_temp_front_left);
      this.front_right_temp.setText(WmCarData.tire_temp_front_right);
      this.rear_left_temp.setText(WmCarData.tire_temp_rear_left);
      this.rear_right_temp.setText(WmCarData.tire_temp_rear_right);
   }
}
