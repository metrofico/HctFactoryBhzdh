package com.hzbhd.canbus.car._331;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int[] mFrontRadarDataNow;
   private CusPanoramicView mPanoramicView;
   private RadarView mRadarView;
   private int[] mRearRadarDataNow;

   private CusPanoramicView getMyPanoramicView() {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = (CusPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
      }

      return this.mPanoramicView;
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void refreshRadar() {
      this.getMyPanoramicView().refreshUi();
      this.updateDZRadarView();
   }

   private void setFrontRadarData0x1D() {
      if (this.isFrontRadarDataChange()) {
         RadarView.frontRadar[0] = this.mCanBusInfoInt[2];
         RadarView.frontRadar[1] = this.mCanBusInfoInt[3];
         RadarView.frontRadar[2] = this.mCanBusInfoInt[4];
         RadarView.frontRadar[3] = this.mCanBusInfoInt[5];
         this.refreshRadar();
      }

   }

   private void setRearRadarData0x1E() {
      if (this.isRearRadarDataChange()) {
         RadarView.rearRadar[0] = this.mCanBusInfoInt[2];
         RadarView.rearRadar[1] = this.mCanBusInfoInt[3];
         RadarView.rearRadar[2] = this.mCanBusInfoInt[4];
         RadarView.rearRadar[3] = this.mCanBusInfoInt[5];
         this.refreshRadar();
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 34) {
         if (var3 == 35) {
            this.setFrontRadarData0x1D();
         }
      } else {
         this.setRearRadarData0x1E();
      }

   }

   // $FF: synthetic method
   void lambda$updateDZRadarView$0$com_hzbhd_canbus_car__331_MsgMgr() {
      this.mRadarView.refreshUi();
   }

   protected void updateDZRadarView() {
      if (this.mRadarView == null) {
         this.mRadarView = new RadarView(this.mContext);
      }

      this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this));
   }
}
