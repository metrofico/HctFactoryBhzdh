package com.hzbhd.canbus.car_cus._304.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.cantype.CanTypeUtil;

public class VehicleAssistanceView extends RelativeLayout {
   private ImageButton mIbSwitch;
   private View mView;

   public VehicleAssistanceView(Activity var1) {
      super(var1);
      this.mView = LayoutInflater.from(var1).inflate(2131558507, this);
      this.findViews();
      this.initViews(var1);
   }

   private void findViews() {
      this.mIbSwitch = (ImageButton)this.mView.findViewById(2131362424);
   }

   private void initViews(Activity var1) {
      this.mIbSwitch.setSelected(MyGeneralData.mPedestrianRemind);
      this.mIbSwitch.setOnClickListener(new VehicleAssistanceView$$ExternalSyntheticLambda0(this));
      this.findViewById(2131363751).setOnLongClickListener(new VehicleAssistanceView$$ExternalSyntheticLambda1(this, var1));
   }

   // $FF: synthetic method
   void lambda$initViews$0$com_hzbhd_canbus_car_cus__304_view_VehicleAssistanceView(View var1) {
      if (this.mIbSwitch.isSelected()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 46, 1});
         this.mIbSwitch.setSelected(false);
         MyGeneralData.mPedestrianRemind = false;
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 46, 0});
         this.mIbSwitch.setSelected(true);
         MyGeneralData.mPedestrianRemind = true;
      }

   }

   // $FF: synthetic method
   boolean lambda$initViews$1$com_hzbhd_canbus_car_cus__304_view_VehicleAssistanceView(Activity var1, View var2) {
      SelectCanTypeUtil.showDialogToUpdate(var1, (CanTypeAllEntity)CanTypeUtil.INSTANCE.getCanType(SelectCanTypeUtil.getCurrentCanTypeId(var1)).getList().get(0), this.getResources().getString(2131770076));
      return false;
   }
}
