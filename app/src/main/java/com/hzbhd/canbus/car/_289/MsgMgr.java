package com.hzbhd.canbus.car._289;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static final int RADAR_PARAM_MAX = 24;
   private static final int RADAR_PARAM_RANGE = 10;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int[] mRadarData;

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private int resolveRadarData(int var1) {
      return (int)Math.ceil((double)((float)var1 / 24.0F * 10.0F));
   }

   private void setRearRadarData() {
      if (this.isRadarDataChange()) {
         RadarInfoUtil.setRearRadarLocationData(10, this.resolveRadarData(this.mCanBusInfoInt[1]), this.resolveRadarData(this.mCanBusInfoInt[2]), this.resolveRadarData(this.mCanBusInfoInt[3]), this.resolveRadarData(this.mCanBusInfoInt[4]));
         RadarInfoUtil.setFrontRadarLocationData(10, this.resolveRadarData(this.mCanBusInfoInt[5]), this.resolveRadarData(this.mCanBusInfoInt[6]), this.resolveRadarData(this.mCanBusInfoInt[7]), this.resolveRadarData(this.mCanBusInfoInt[8]));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      StringBuilder var4 = (new StringBuilder()).append("canbusInfoChange: ");
      byte[] var3 = this.mCanBusInfoByte;
      Log.i("ljq", var4.append(DataHandleUtils.bytes2HexString(var3, var3.length)).toString());
      if (this.mCanBusInfoInt[0] == 86) {
         this.setRearRadarData();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
   }
}
