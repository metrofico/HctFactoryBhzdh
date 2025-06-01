package com.hzbhd.canbus.car._31;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String _31_IS_BACK_CAMERA = "_31_is_back_camera";
   static final String _31_IS_PANORAMIC = "_31_is_panoramic";
   private int FreqInt;
   private final String TAG = "_31_MsgMgr";
   private final String _31_AIR_REAR = "_31_air_rear";
   private final String _31_AIR_REAR_TEMP = "_31_air_rear_temp";
   private final String _31_ISBACK_OPEN = "_31_isback_open";
   private final String _31_ISLEFT_FRONT_DOOR_OPEN = "_31_isleft_front_door_open";
   private final String _31_ISLEFT_REAR_DOOR_OPEN = "_31_isleft_rear_door_open";
   private final String _31_ISRIGHT_FRONT_DOOR_OPEN = "_31_isright_front_door_open";
   private final String _31_ISRIGHT_REAR_DOOR_OPEN = "_31_isright_rear_door_open";
   private byte bandType;
   private byte freqHi;
   private byte freqLo;
   private int isOnlyRearTempDate;
   private int isOnlyRearUpdate;
   private int[] m0x21Data;
   private int[] mAirFrontData;
   private int[] mAirRearData;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private Context mContext;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   private int mVolume = 20;
   private int tireStatus1;
   private int tireStatus2;
   private int tireStatus3;
   private int tireStatus4;
   private int tireStatus5;

   private int getAirWhat() {
      int[] var2 = new int[5];
      int[] var3 = this.mCanBusInfoInt;
      var2[0] = var3[2];
      var2[1] = var3[3];
      var2[2] = var3[4];
      var2[3] = var3[5];
      var2[4] = var3[6];
      int[] var1 = new int[]{var3[7], 0};
      if (var3.length > 8) {
         var1[1] = var3[8];
      }

      if (!Arrays.equals(this.mAirFrontData, var2)) {
         this.mAirFrontData = Arrays.copyOf(var2, 5);
         return 1001;
      } else if (!Arrays.equals(this.mAirRearData, var1)) {
         this.mAirRearData = Arrays.copyOf(var1, 2);
         return 1002;
      } else {
         return 0;
      }
   }

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private String getSensor(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131762414);
         this.tireStatus5 = 1;
      } else {
         var2 = this.mContext.getResources().getString(2131762413);
         this.tireStatus5 = 0;
      }

      return var2;
   }

   private TireUpdateEntity getTireEntity(int var1, String var2, String var3, String var4, String var5, String var6) {
      byte var7;
      if (this.tireStatus1 != 1 && this.tireStatus2 != 1 && this.tireStatus3 != 1 && this.tireStatus4 != 1 && this.tireStatus5 != 1) {
         var7 = 0;
      } else {
         var7 = 1;
      }

      return new TireUpdateEntity(var1, var7, new String[]{var2, var3, var4, var5, var6});
   }

   private String getTirePressure(int var1, int var2) {
      String var3 = (float)((double)var1 * 2.35) + "kpa";
      if (var2 == 1) {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762416);
         this.tireStatus2 = 1;
      } else if (var2 == 2) {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762417);
         this.tireStatus2 = 1;
      } else {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762411);
         this.tireStatus2 = 0;
      }

      return var3;
   }

   private String getTireTemp(int var1, int var2) {
      String var3 = var1 - 50 + "℃";
      if (var2 == 1) {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762422);
         this.tireStatus3 = 1;
      } else {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762411);
         this.tireStatus3 = 0;
      }

      return var3;
   }

   private String getTisWarmMsg(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131762419);
         this.tireStatus1 = 1;
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131762420);
         this.tireStatus1 = 1;
      } else {
         var2 = this.mContext.getResources().getString(2131762411);
         this.tireStatus1 = 0;
      }

      return var2;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getVoltage(int var1, int var2) {
      String var3 = (float)var1 / 10.0F + "V";
      if (var2 == 1) {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762412);
         this.tireStatus4 = 1;
      } else {
         var3 = var3 + " " + this.mContext.getResources().getString(2131762411);
         this.tireStatus4 = 0;
      }

      return var3;
   }

   private void initAmplifier(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)(10 - GeneralAmplifierData.frontRear), (byte)(GeneralAmplifierData.leftRight + 10), (byte)GeneralAmplifierData.bandTreble, (byte)GeneralAmplifierData.bandMiddle, (byte)GeneralAmplifierData.bandBass});
   }

   private void initAmplifierData(Context var1) {
      if (var1 == null) {
         Log.i("_31_MsgMgr", "ljq initAmplifierData: context is null");
      } else {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         int var5 = GeneralAmplifierData.frontRear;
         int var3 = GeneralAmplifierData.leftRight;
         int var4 = GeneralAmplifierData.bandTreble;
         int var2 = GeneralAmplifierData.bandMiddle;
         int var6 = GeneralAmplifierData.bandBass;
         Log.i("_31_MsgMgr", "initAmplifierData: send");
         (new TimerUtil()).startTimer(new TimerTask(this, 10 - var5, var3 + 10, var4, var2, var6) {
            final MsgMgr this$0;
            final int val$data1;
            final int val$data2;
            final int val$data3;
            final int val$data4;
            final int val$data5;

            {
               this.this$0 = var1;
               this.val$data1 = var2;
               this.val$data2 = var3;
               this.val$data3 = var4;
               this.val$data4 = var5;
               this.val$data5 = var6;
            }

            public void run() {
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)this.val$data1, (byte)this.val$data2, (byte)this.val$data3, (byte)this.val$data4, (byte)this.val$data5});
            }
         }, 1000L);
      }

   }

   private boolean is0x21DataChange() {
      if (Arrays.equals(this.m0x21Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x21Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "_31_isleft_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_31_isright_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_31_isright_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "_31_isleft_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "_31_isback_open", false) != GeneralDoorData.isBackOpen;
      }
   }

   private boolean isOnlyRearUpdate() {
      if (SharePreUtil.getIntValue(this.mContext, "_31_air_rear", 0) != this.isOnlyRearUpdate) {
         return true;
      } else {
         return SharePreUtil.getIntValue(this.mContext, "_31_air_rear_temp", 0) != this.isOnlyRearTempDate;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private static boolean noNeedRepBtStatus(boolean var0, int var1) {
      return !var0 || var1 == 0;
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (30 == var1) {
         var2 = "HI";
      } else if (1 <= var1 && var1 <= 29) {
         var2 = (float)var1 * 0.5F + 17.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      byte var1 = this.mCanBusInfoByte[2];
      String var2;
      if (var1 >= 0 && var1 <= 87) {
         var2 = this.mCanBusInfoByte[2] + this.getTempUnitC(this.mContext);
      } else if (var1 < 0) {
         var2 = this.mCanBusInfoByte[2] + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void set0x20WhellKeyInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 18) {
         switch (var1) {
            case 0:
               this.realKeyLongClick1(this.mContext, 0, var2[3]);
               break;
            case 1:
               this.realKeyLongClick1(this.mContext, 7, var2[3]);
               break;
            case 2:
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
               break;
            case 3:
               this.realKeyLongClick1(this.mContext, 47, var2[3]);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 48, var2[3]);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 14, var2[3]);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 3, var2[3]);
               break;
            case 7:
               this.realKeyLongClick1(this.mContext, 2, var2[3]);
               break;
            case 8:
               this.realKeyLongClick1(this.mContext, 136, var2[3]);
               break;
            case 9:
               this.realKeyLongClick1(this.mContext, 57, var2[3]);
               break;
            case 10:
               this.realKeyLongClick1(this.mContext, 183, var2[3]);
               break;
            case 11:
               this.realKeyLongClick1(this.mContext, 183, var2[3]);
               break;
            case 12:
               this.realKeyLongClick1(this.mContext, 50, var2[3]);
               break;
            case 13:
               this.realKeyLongClick1(this.mContext, 45, var2[3]);
               break;
            case 14:
               this.realKeyLongClick1(this.mContext, 46, var2[3]);
               break;
            case 15:
               this.realKeyLongClick1(this.mContext, 1, var2[3]);
               break;
            case 16:
               this.realKeyLongClick1(this.mContext, 15, var2[3]);
               break;
            default:
               switch (var1) {
                  case 48:
                     this.realKeyLongClick1(this.mContext, 182, var2[3]);
                     break;
                  case 49:
                     this.realKeyLongClick1(this.mContext, 128, var2[3]);
                     break;
                  case 50:
                     this.realKeyLongClick1(this.mContext, 128, var2[3]);
                     break;
                  case 51:
                     this.realKeyLongClick1(this.mContext, 128, var2[3]);
                     break;
                  case 52:
                     this.realKeyLongClick1(this.mContext, 58, var2[3]);
                     break;
                  case 53:
                     this.realKeyLongClick1(this.mContext, 49, var2[3]);
                     break;
                  case 54:
                     this.realKeyLongClick1(this.mContext, 62, var2[3]);
                     break;
                  case 55:
                     this.realKeyLongClick1(this.mContext, 2, var2[3]);
                     break;
                  case 56:
                     this.realKeyLongClick1(this.mContext, 14, var2[3]);
                     break;
                  case 57:
                     this.realKeyLongClick1(this.mContext, 47, var2[3]);
                     break;
                  case 58:
                     this.realKeyLongClick1(this.mContext, 48, var2[3]);
                     break;
                  case 59:
                     this.realKeyLongClick1(this.mContext, 1, var2[3]);
                     break;
                  case 60:
                     this.realKeyClick3_1(this.mContext, 7, 0, var2[3]);
                     break;
                  case 61:
                     this.realKeyClick3_1(this.mContext, 8, 0, var2[3]);
                     break;
                  case 62:
                     this.realKeyClick3(this.mContext, 45, 0, var2[3]);
                     break;
                  case 63:
                     this.realKeyClick3(this.mContext, 46, 0, var2[3]);
                     break;
                  case 64:
                     this.realKeyLongClick1(this.mContext, 1, var2[3]);
                     break;
                  case 65:
                     this.realKeyLongClick1(this.mContext, 45, var2[3]);
                     break;
                  case 66:
                     this.realKeyLongClick1(this.mContext, 48, var2[3]);
                     break;
                  default:
                     switch (var1) {
                        case 129:
                           this.realKeyClick3(this.mContext, 7, var1, var2[3]);
                           break;
                        case 130:
                           this.realKeyClick3(this.mContext, 8, var1, var2[3]);
                           break;
                        case 131:
                           this.realKeyLongClick1(this.mContext, 45, var2[3]);
                           break;
                        case 132:
                           this.realKeyLongClick1(this.mContext, 46, var2[3]);
                           break;
                        case 133:
                           this.realKeyLongClick1(this.mContext, 45, var2[3]);
                           break;
                        case 134:
                           this.realKeyLongClick1(this.mContext, 46, var2[3]);
                           break;
                        case 135:
                           this.realKeyLongClick1(this.mContext, 1, var2[3]);
                           break;
                        case 136:
                           this.realKeyLongClick1(this.mContext, 2, var2[3]);
                           break;
                        case 137:
                           this.realKeyLongClick1(this.mContext, 3, var2[3]);
                           break;
                        case 138:
                           this.realKeyClick3_1(this.mContext, 7, 0, var2[3]);
                           break;
                        case 139:
                           this.realKeyClick3_1(this.mContext, 8, 0, var2[3]);
                     }
               }
         }
      } else {
         this.realKeyLongClick1(this.mContext, 187, var2[3]);
      }

   }

   private void set0x26EspInfo() {
      if (this.isTrackInfoChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setAirData0x21() {
      int[] var1 = this.mCanBusInfoInt;
      var1[2] &= 237;
      var1[3] &= 239;
      if (this.is0x21DataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
         if (this.mCanBusInfoByte.length > 8) {
            GeneralAirData.rear_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[8]);
         }

         this.updateAirActivity(this.mContext, this.getAirWhat());
      }

   }

   private void setAirData0x27() {
   }

   private void setAmplifierData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         ArrayList var2 = new ArrayList();
         String var1;
         if (this.mCanBusInfoInt[2] == 1) {
            var1 = this.mContext.getResources().getString(2131762389);
         } else {
            var1 = this.mContext.getResources().getString(2131762390);
         }

         var2.add(new DriverUpdateEntity(0, 4, var1));
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setCarSetData0x14() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769315);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769174);
      } else {
         var2 = this.mCanBusInfoInt[2] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarSpeed() {
      ArrayList var2 = new ArrayList();
      StringBuilder var1 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(0, 3, var1.append((var3[2] + var3[3] * 256) / 16).append("Km/h").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo((var4[2] + var4[3] * 256) / 16);
   }

   private void setDoorData0x24() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      if (this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "_31_isleft_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_31_isright_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_31_isright_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_31_isleft_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "_31_isback_open", GeneralDoorData.isBackOpen);
         this.updateDoorView(this.mContext);
      }

      ArrayList var2 = new ArrayList();
      String var1;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var1 = this.mContext.getResources().getString(2131769504);
      } else {
         var1 = this.mContext.getResources().getString(2131768042);
      }

      var2.add(new DriverUpdateEntity(0, 1, var1));
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var1 = "Not P";
      } else {
         var1 = "P";
      }

      var2.add(new DriverUpdateEntity(0, 2, var1));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setEra() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131762391);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131762392);
      } else {
         var2 = this.mContext.getResources().getString(2131762393);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 5, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setPanoramicSetting() {
      ArrayList var6 = new ArrayList();
      byte var5 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      byte var2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2);

      for(int var1 = 0; var1 < 4; ++var1) {
         var6.add(new SettingUpdateEntity(0, var1, (new int[]{var5, var2, var4, var3})[var1]));
      }

      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private void setPanoramicStatus() {
      ArrayList var3 = new ArrayList();
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
      Context var4 = this.mContext;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      SharePreUtil.setBoolValue(var4, "_31_is_back_camera", var2);
      var4 = this.mContext;
      if (var1 == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      SharePreUtil.setBoolValue(var4, "_31_is_panoramic", var2);
      var4 = this.mContext;
      if (var1 != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.forceReverse(var4, var2);
      if (!SharePreUtil.getBoolValue(this.mContext, "_31_is_back_camera", false) && !CommUtil.isMiscReverse()) {
         if (SharePreUtil.getBoolValue(this.mContext, "_31_is_panoramic", false)) {
            for(var1 = 5; var1 <= 8; ++var1) {
               if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == var1) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var3.add(new PanoramicBtnUpdateEntity(var1 - 5, var2));
            }
         }
      } else {
         for(var1 = 1; var1 <= 4; ++var1) {
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == var1) {
               var2 = true;
            } else {
               var2 = false;
            }

            var3.add(new PanoramicBtnUpdateEntity(var1 - 1, var2));
         }
      }

      GeneralParkData.dataList = var3;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadar() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTypeDataInfo() {
      byte var1;
      int var2;
      byte var3;
      byte var4;
      int var5;
      byte var6;
      ArrayList var9;
      label23: {
         var9 = new ArrayList();
         int[] var8 = this.mCanBusInfoInt;
         var1 = 4;
         var2 = DataHandleUtils.getIntFromByteWithBit(var8[4], 0, 2);
         var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
         var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         var4 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         var6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         int var7 = this.mCanBusInfoInt[3];
         if (var7 != 1) {
            if (var7 == 2) {
               var1 = 1;
               break label23;
            }

            if (var7 == 3) {
               var1 = 3;
               break label23;
            }

            if (var7 == 4) {
               var1 = 2;
               break label23;
            }

            if (var7 == 5) {
               break label23;
            }
         }

         var1 = 0;
      }

      var9.add(this.getTireEntity(var1, this.getTisWarmMsg(var5), this.getTirePressure(this.mCanBusInfoInt[5], var2), this.getTireTemp(this.mCanBusInfoInt[6], var3), this.getVoltage(this.mCanBusInfoInt[7], var6), this.getSensor(var4)));
      GeneralTireData.dataList = var9;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setVersionInfo() {
      Log.i("_31_MsgMgr", "setVersionInfo: ");
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setVwRadioInfo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW")) {
         if (!var1.equals("AM1") && !var1.equals("LW")) {
            if (var1.equals("FM1")) {
               this.bandType = 1;
            } else if (var1.equals("FM2")) {
               this.bandType = 2;
            } else if (var1.equals("FM3") || var1.equals("OIRT")) {
               this.bandType = 3;
            }
         } else {
            int var3 = Integer.parseInt(var2);
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
            this.bandType = 17;
         }
      } else {
         this.bandType = 18;
      }

      this.getFreqByteHiLo(var1, var2);
   }

   private void sndSimpleVol(byte var1, byte var2, boolean var3) {
      byte var4 = var1;
      if (var1 >= 40) {
         var4 = 40;
      }

      var1 = var2;
      if (var2 >= var4) {
         var1 = var4;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)(var3 << 7 | var1 & 127)});
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      (new TimerUtil()).startTimer(new TimerTask(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            MsgMgr var3 = this.this$0;
            byte var1 = (byte)var3.mVolume;
            boolean var2;
            if (this.this$0.mVolume == 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            var3.sndSimpleVol((byte)35, var1, var2);
         }
      }, 2000L, 1000L);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.ATV, 255, false, true);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.AUX, 255, false, true);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.A2DP, 48, false, true);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (!noNeedRepBtStatus(var3, var1)) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, true);
      }
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 22) {
            if (var3 != 41) {
               if (var3 != 48) {
                  if (var3 != 64) {
                     if (var3 != 80) {
                        if (var3 != 149) {
                           if (var3 != 38) {
                              if (var3 != 39) {
                                 switch (var3) {
                                    case 32:
                                       this.set0x20WhellKeyInfo();
                                       break;
                                    case 33:
                                       this.setAirData0x21();
                                       break;
                                    case 34:
                                       this.setRearRadar();
                                       break;
                                    case 35:
                                       this.setFrontRadar();
                                       break;
                                    case 36:
                                       if (this.isDoorMsgRepeat(var2)) {
                                          return;
                                       }

                                       this.setDoorData0x24();
                                 }
                              } else {
                                 this.setAirData0x27();
                              }
                           } else {
                              this.set0x26EspInfo();
                           }
                        } else {
                           this.setEra();
                        }
                     } else {
                        this.setPanoramicStatus();
                        this.setPanoramicSetting();
                     }
                  } else {
                     this.setTypeDataInfo();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setAmplifierData();
            }
         } else {
            this.setCarSpeed();
         }
      } else {
         this.setCarSetData0x14();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.mVolume = var1;
      this.sndSimpleVol((byte)35, (byte)var1, var2);
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var1 = DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var11), 6, var12);
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 0, 0, 0, (byte)var5, (byte)var6, (byte)var7, (byte)var1});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 33, (byte)var5, (byte)var6, (byte)var3, (byte)(var2 / 3600), (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 16, 1, (byte)var4, (byte)var6, (byte)(var2 / 3600), (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      Log.i("_31_MsgMgr", "initCommand: ");
      CanbusMsgSender.sendMsg(new byte[]{22, -11, 97, 0});
      this.initAmplifier(var1);
      this.getUiMgr(var1).sendAmplifierCommand(true);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte)(var4 & 255), (byte)(var4 >> 8 & 255), (byte)var3, var9, var6, var7});
   }

   public void onAccOff() {
      super.onAccOff();
      Context var1 = this.mContext;
      if (var1 != null) {
         this.getUiMgr(var1).sendAmplifierCommand(false);
      }

   }

   public void onAccOn() {
      super.onAccOn();
      Context var1 = this.mContext;
      if (var1 != null) {
         this.getUiMgr(var1).sendAmplifierCommand(true);
      }

   }

   public void onPowerOff() {
      super.onPowerOff();
      Context var1 = this.mContext;
      if (var1 != null) {
         this.getUiMgr(var1).sendAmplifierCommand(false);
      }

   }

   public void onSleep() {
      super.onSleep();
      Context var1 = this.mContext;
      if (var1 != null) {
         this.getUiMgr(var1).sendAmplifierCommand(false);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.setVwRadioInfo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, this.bandType, this.freqLo, this.freqHi, (byte)var1, 0, 0});
   }

   void saveAmplifierData(Context var1) {
      this.saveAmplifierData(var1, this.mCanId);
   }

   public void setAmplifier(int var1, int var2) {
      GeneralAmplifierData.frontRear = var1;
      GeneralAmplifierData.leftRight = var2;
      this.updateAmplifierActivity((Bundle)null);
   }

   void setLanguage_recNull(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "_31_SAVE_LANGUAGE", 0);
      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(1, 0, var2));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.OFF, 255, false, true);
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifier(this.mContext);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.USB_VIDEO, 255, true, true);
      }

   }
}
