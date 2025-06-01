package com.hzbhd.canbus.car._414;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   public static int nowModel;
   private int alertInfo2;
   private int alertInfo3;
   TextView content;
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   AlertDialog dialog;
   int differentId;
   int[] door0x72;
   int eachId;
   Button i_know;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mDoorData;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   SystemButton systemButton;
   View view;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private String getAlertContent() {
      boolean var4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      String var6 = "";
      int var1;
      if (var4) {
         var6 = "" + "   " + 1 + "." + this.mContext.getString(2131765583);
         var1 = 1;
      } else {
         var1 = 0;
      }

      int var2 = var1;
      String var5 = var6;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         var2 = var1 + 1;
         var5 = var6 + "   " + var2 + "." + this.mContext.getString(2131765584);
      }

      var1 = var2;
      var6 = var5;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         var1 = var2 + 1;
         var6 = var5 + "   " + var1 + "." + this.mContext.getString(2131765585);
      }

      var2 = var1;
      var5 = var6;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         var2 = var1 + 1;
         var5 = var6 + "   " + var2 + "." + this.mContext.getString(2131765586);
      }

      var1 = var2;
      var6 = var5;
      if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var1 = var2 + 1;
         var6 = var5 + "   " + var1 + "." + this.mContext.getString(2131765587);
      }

      var2 = var1;
      String var7 = var6;
      if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
         var2 = var1 + 1;
         var7 = var6 + "   " + var2 + "." + this.mContext.getString(2131765588);
      }

      int var3 = var2;
      var5 = var7;
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
         var3 = var2 + 1;
         var5 = var7 + "   " + var3 + "." + this.mContext.getString(2131765589);
      }

      var1 = var3;
      var6 = var5;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         var1 = var3 + 1;
         var6 = var5 + "   " + var1 + "." + this.mContext.getString(2131765590);
      }

      var2 = var1;
      var5 = var6;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         var2 = var1 + 1;
         var5 = var6 + "   " + var2 + "." + this.mContext.getString(2131765591);
      }

      var6 = var5;
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
         var6 = var5 + "   " + (var2 + 1) + "." + this.mContext.getString(2131765592);
      }

      return var6;
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getTemperature(int var1, int var2, int var3) {
      if (var1 == var2) {
         return "LO";
      } else {
         return var1 == var3 ? "HI" : this.df_2Decimal.format((double)var1 * 0.5) + this.getTempUnitC(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isNotDoor0x12DataChange() {
      if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mDoorData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isNotDoor0x72InfoChange() {
      if (Arrays.equals(this.door0x72, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.door0x72 = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
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

   private void set0x12DoorInfo() {
      if (!this.isNotDoor0x12DataChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void set0x13Info() {
      ArrayList var4 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data0");
      StringBuilder var7 = new StringBuilder();
      DecimalFormat var5 = this.df_2Decimal;
      int[] var6 = this.mCanBusInfoInt;
      int var3 = var6[4];
      var4.add(new DriverUpdateEntity(var1, var2, var7.append(var5.format((double)((var6[5] & 255) << 8 | (var3 & 255) << 16 | this.mCanBusInfoByte[6] & 255) * 0.1)).append("KM").toString()));
      var4.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data1"), this.mCanBusInfoInt[7] + "%"));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data2");
      StringBuilder var8 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var1, var2, var8.append(this.getMsbLsbResult(var6[8], var6[9])).append("KM").toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x31AirInfo() {
      this.updateOutDoorTemp(this.mContext, this.df_2Decimal.format((double)this.mCanBusInfoInt[13] * 0.5 - 40.0) + this.getTempUnitC(this.mContext));
      this.mCanBusInfoInt[13] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 3) {
            if (var1 != 12) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[8], 254, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[8], 254, 255);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void set0x32CarInfo() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data4");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[4], var4[5])).append("RPM").toString()));
      var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data5");
      StringBuilder var7 = new StringBuilder();
      int[] var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var7.append(this.getMsbLsbResult(var8[6], var8[7])).append("KM/H").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var6[6], var6[7]));
   }

   private void setAlertInfo0x72() {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != this.alertInfo2 || var3[3] != this.alertInfo3) {
         int var1 = var3[3];
         this.alertInfo3 = var1;
         this.alertInfo2 = var2;
         if (var2 != 0 || var1 != 0) {
            if (this.view == null) {
               this.view = LayoutInflater.from(this.mContext).inflate(2131558513, (ViewGroup)null, true);
            }

            if (this.dialog == null) {
               this.dialog = (new AlertDialog.Builder(this.mContext)).setView(this.view).create();
            }

            if (this.content == null) {
               this.content = (TextView)this.view.findViewById(2131361915);
            }

            this.content.setText(this.getAlertContent());
            if (this.i_know == null) {
               this.i_know = (Button)this.view.findViewById(2131362380);
            }

            this.i_know.setOnClickListener(new View.OnClickListener(this) {
               final MsgMgr this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(View var1) {
                  this.this$0.dialog.dismiss();
               }
            });
            this.dialog.setCancelable(false);
            this.dialog.getWindow().setBackgroundDrawableResource(17170445);
            this.dialog.getWindow().setType(2003);
            this.dialog.show();
         }
      }
   }

   private void setCArSetting0x61() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 1));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      if (!DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
         Toast.makeText(this.getActivity(), this.mContext.getString(2131765622), 0).show();
      }

      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting11"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarInfo0x11() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     switch (var1) {
                        case 8:
                           this.realKeyLongClick1(this.mContext, 465, var2[5]);
                           break;
                        case 9:
                           this.realKeyLongClick1(this.mContext, 466, var2[5]);
                           break;
                        case 10:
                           this.realKeyLongClick1(this.mContext, 2, var2[5]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 187, var2[5]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 3, var2[5]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[5]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[5]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[5]);
      }

      var2 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var2[9], (byte)var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setDoorInfo0x72() {
      if (!this.isNotDoor0x72InfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isShowSeatBelt = true;
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isSubShowSeatBelt = true;
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setPanoromic0xE8() {
      if (nowModel == 0 && this.mCanBusInfoInt[5] == 1) {
         this.forceReverse(this.mContext, true);
      }

      if (nowModel == 5 && this.mCanBusInfoInt[5] == 0) {
         this.forceReverse(this.mContext, false);
      }

      int var1 = nowModel;
      if (var1 == 5) {
         nowModel = 0;
      } else {
         nowModel = var1 + 1;
      }

   }

   private void setRadar0x41() {
      if (this.isRearRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSwc0x21() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 == 1) {
            this.realKeyLongClick1(this.mContext, 1, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 65) {
            if (var3 != 97) {
               if (var3 != 114) {
                  if (var3 != 232) {
                     if (var3 != 240) {
                        if (var3 != 49) {
                           if (var3 != 50) {
                              switch (var3) {
                                 case 17:
                                    this.setCarInfo0x11();
                                    break;
                                 case 18:
                                    this.set0x12DoorInfo();
                                    break;
                                 case 19:
                                    this.set0x13Info();
                              }
                           } else {
                              this.set0x32CarInfo();
                           }
                        } else {
                           this.set0x31AirInfo();
                        }
                     } else {
                        this.setVersion0xF0();
                     }
                  } else {
                     this.setPanoromic0xE8();
                  }
               } else {
                  this.setAlertInfo0x72();
                  this.setDoorInfo0x72();
               }
            } else {
               this.setCArSetting0x61();
            }
         } else {
            this.setRadar0x41();
         }
      } else {
         this.setSwc0x21();
      }

   }

   public void hideButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicInfo0xFD();
                  }
               });
            }

            this.this$0.systemButton.hide();
         }
      });
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).sendCarType();
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void showButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicInfo0xFD();
                  }
               });
            }

            this.this$0.systemButton.show();
         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }
}
