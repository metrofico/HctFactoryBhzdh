package com.hzbhd.canbus.car._413;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   SystemButton systemDvrButton;

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
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
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
         return var1 == var3 ? "HI" : this.df_1Decimal.format((double)var1 * 0.5) + this.getTempUnitC(this.mContext);
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

   private void set0x31AirInfo() {
      this.updateOutDoorTemp(this.mContext, this.df_2Decimal.format((double)this.mCanBusInfoInt[13] * 0.5 - 40.0));
      this.mCanBusInfoInt[13] = 0;
      if (!this.isNotAirDataChange()) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         int var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            var1 = 2;
         }

         GeneralAirData.in_out_auto_cycle = var1;
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
         GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
         GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_auto_wind = false;
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        switch (var1) {
                           case 11:
                              GeneralAirData.front_left_blow_window = true;
                              break;
                           case 12:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_left_blow_foot = true;
                              break;
                           case 13:
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                              break;
                           case 14:
                              GeneralAirData.front_left_blow_foot = true;
                              GeneralAirData.front_left_blow_window = true;
                              GeneralAirData.front_left_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_auto_wind = true;
         }

         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_auto_wind = false;
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        switch (var1) {
                           case 11:
                              GeneralAirData.front_right_blow_window = true;
                              break;
                           case 12:
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_right_blow_foot = true;
                              break;
                           case 13:
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_right_blow_head = true;
                              break;
                           case 14:
                              GeneralAirData.front_right_blow_foot = true;
                              GeneralAirData.front_right_blow_window = true;
                              GeneralAirData.front_right_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_right_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_right_auto_wind = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[8], 254, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[9], 254, 255);
         GeneralAirData.rear_right_blow_head = false;
         GeneralAirData.rear_right_blow_window = false;
         GeneralAirData.rear_right_blow_foot = false;
         var1 = this.mCanBusInfoInt[10];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralAirData.rear_right_blow_foot = true;
                  GeneralAirData.rear_right_blow_head = true;
               }
            } else {
               GeneralAirData.rear_right_blow_head = true;
            }
         } else {
            GeneralAirData.rear_right_blow_foot = true;
         }

         GeneralAirData.rear_left_blow_head = false;
         GeneralAirData.rear_left_blow_window = false;
         GeneralAirData.rear_left_blow_foot = false;
         var1 = this.mCanBusInfoInt[10];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_left_blow_head = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
            }
         } else {
            GeneralAirData.rear_left_blow_foot = true;
         }

         GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
         GeneralAirData.rear_temperature = this.getTemperature(this.mCanBusInfoInt[12], 254, 255);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void set0x32CarInfo() {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_412_drive");
      int var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_412_drive1");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(this.getMsbLsbResult(var4[4], var4[5])).append("RPM").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_412_drive");
      var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_412_drive2");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(this.getMsbLsbResult(var4[6], var4[7])).append("KM/H").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
      int[] var6 = this.mCanBusInfoInt;
      this.updateSpeedInfo(this.getMsbLsbResult(var6[6], var6[7]));
   }

   private void set0xF0Version() {
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
      if (var3 != 49) {
         if (var3 != 50) {
            if (var3 == 240) {
               this.set0xF0Version();
            }
         } else {
            this.set0x32CarInfo();
         }
      } else {
         this.set0x31AirInfo();
      }

   }

   public void hideDvrButton() {
      SystemButton var1 = this.systemDvrButton;
      if (var1 != null) {
         var1.hide();
      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 38, 1});
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void showDvrButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemDvrButton == null) {
               this.this$0.systemDvrButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicCmd(1);
                  }
               });
            }

            this.this$0.systemDvrButton.show();
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
