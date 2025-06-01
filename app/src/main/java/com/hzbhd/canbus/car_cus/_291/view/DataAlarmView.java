package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;

public class DataAlarmView extends RelativeLayout {
   private int[] isOpen = new int[]{2131761264, 2131761276};
   private View mView;
   private TextView tv_alarm_belt;
   private TextView tv_alarm_clean;
   private TextView tv_alarm_oil;
   private TextView tv_alarm_v;

   public DataAlarmView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mView = LayoutInflater.from(var1).inflate(2131558491, this);
      this.initView();
   }

   private int getStr(boolean var1) {
      return var1 ? this.isOpen[1] : this.isOpen[0];
   }

   private void initView() {
      this.tv_alarm_belt = (TextView)this.mView.findViewById(2131363585);
      this.tv_alarm_clean = (TextView)this.mView.findViewById(2131363586);
      this.tv_alarm_oil = (TextView)this.mView.findViewById(2131363587);
      this.tv_alarm_v = (TextView)this.mView.findViewById(2131363588);
   }

   public void refreshUi() {
      this.tv_alarm_oil.setText(this.getStr(GeneralDoorData.isFuelWarning));
      this.tv_alarm_v.setText(this.getStr(GeneralDoorData.isBatteryWarning));
      this.tv_alarm_belt.setText(this.getStr(GeneralDoorData.isSeatBeltTie));
      this.tv_alarm_clean.setText(this.getStr(GeneralDoorData.isWashingFluidWarning));
   }
}
