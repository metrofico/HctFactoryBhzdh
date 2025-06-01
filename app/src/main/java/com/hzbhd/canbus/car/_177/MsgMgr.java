package com.hzbhd.canbus.car._177;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   static int ONE;
   static int WIND_LEVEL_HIGH;
   static int WIND_LEVEL_LOW;
   private String SHARE_177_DATA_0X90 = "data0x90";
   private String SHARE_177_DATA_0X91 = "data0x91";
   private String SHARE_177_DATA_0X92 = "data0x92";
   private String SHARE_177_DATA_0X93 = "data0x93";
   private String SHARE_177_DATA_0XA0 = "data0xa0";
   private String SHARE_177_DATA_0XA1 = "data0xa1";
   private String SHARE_177_DATA_0XA2 = "data0xa2";
   private String SHARE_177_DATA_0XA3 = "data0xa3";
   private String SHARE_177_DATA_0XA4 = "data0xa4";
   private String SHARE_177_LANGUAGE = "176_Language";
   private int data0x90 = 0;
   private int data0x91 = 0;
   private int data0x92 = 0;
   private int data0x93 = 0;
   private int data0xA0 = 0;
   private int data0xA1 = 0;
   private int data0xA2 = 0;
   private int data0xA3 = 0;
   private int data0xA4 = 0;
   private int mCallStatus = 1;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mIsBackOpen;
   private boolean mIsBackOpenNow;
   private boolean mIsFrontLeftOpen;
   private boolean mIsFrontLeftOpenNow;
   private boolean mIsFrontOpen;
   private boolean mIsFrontOpenNow;
   private boolean mIsFrontRightOpen;
   private boolean mIsFrontRightOpenNow;
   private boolean mIsRearLeftOpen;
   private boolean mIsRearLeftOpenNow;
   private boolean mIsRearRightOpen;
   private boolean mIsRearRightOpenNow;

   private byte getAllBandTypeData(String var1) {
      byte var2;
      label53: {
         var1.hashCode();
         switch (var1) {
            case "AM":
               var2 = 0;
               break label53;
            case "FM":
               var2 = 1;
               break label53;
            case "AM1":
               var2 = 2;
               break label53;
            case "AM2":
               var2 = 3;
               break label53;
            case "AM3":
               var2 = 4;
               break label53;
            case "FM1":
               var2 = 5;
               break label53;
            case "FM2":
               var2 = 6;
               break label53;
            case "FM3":
               var2 = 7;
               break label53;
         }

         var2 = -1;
      }

      switch (var2) {
         case 0:
            return 16;
         case 1:
            return 0;
         case 2:
            return 17;
         case 3:
            return 18;
         case 4:
            return 19;
         case 5:
            return 1;
         case 6:
            return 2;
         case 7:
            return 3;
         default:
            return -1;
      }
   }

   private String getData(int var1) {
      return var1 == 65535 ? this.mContext.getString(2131769909) : String.format("%.1f", (float)this.getIdFromCanbusInfo(var1) / 10.0F);
   }

   private String getDataDistance(int var1) {
      return var1 == 65535 ? this.mContext.getString(2131769909) : String.format("%.1f", (float)(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1 + 2], 0, 1) * 256 * 256 + this.getIdFromCanbusInfo(var1)) / 10.0F);
   }

   private String getDistanceUnit() {
      int var1 = this.mCanBusInfoInt[9];
      if (var1 == 255) {
         return "KM";
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(var1, 2, 2);
         if (var1 != 0) {
            return var1 != 1 ? "" : "MILES";
         } else {
            return "KM";
         }
      }
   }

   private int getIdFromCanbusInfo(int var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[var1];
      return var3[var1 + 1] & 255 | (var2 & 255) << 8;
   }

   private String getOilUnit() {
      int var1 = this.mCanBusInfoInt[9];
      if (var1 == 255) {
         return "L/100KM";
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(var1, 4, 3);
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        return var1 != 5 ? "" : "MPKWH";
                     } else {
                        return "KWH/100KM";
                     }
                  } else {
                     return "MPGUK";
                  }
               } else {
                  return "MPGUS";
               }
            } else {
               return "KM/L";
            }
         } else {
            return "L/100KM";
         }
      }
   }

   private String getSpeedUnit() {
      int var1 = this.mCanBusInfoInt[9];
      if (var1 == 255) {
         return "KM/H";
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(var1, 2, 2);
         if (var1 != 0) {
            return var1 != 1 ? "" : "MILES/H";
         } else {
            return "KM/H";
         }
      }
   }

   private TireUpdateEntity getTireEntity(int var1, String var2) {
      return new TireUpdateEntity(var1, 0, new String[]{var2});
   }

   private String getTirePressure(int var1) {
      return var1 == 255 ? this.mContext.getString(2131769909) : String.format("%.1f", (float)var1 * 0.03F) + this.mContext.getString(2131769654);
   }

   private String getTisWarmMsg(int var1) {
      switch (var1) {
         case 1:
            var1 = 2131758556;
            break;
         case 2:
            var1 = 2131758557;
            break;
         case 3:
            var1 = 2131758558;
            break;
         case 4:
            var1 = 2131758559;
            break;
         case 5:
            var1 = 2131758560;
            break;
         case 6:
            var1 = 2131758561;
            break;
         default:
            var1 = 0;
      }

      return var1 == 0 ? "" : this.mContext.getString(var1);
   }

   private void initAmplifierData() {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)SharePreUtil.getIntValue(this.mContext, this.SHARE_177_LANGUAGE, 0)});
      this.data0x90 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X90, 0);
      this.data0x91 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X91, 0);
      this.data0x92 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X92, 0);
      this.data0x93 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0X93, 0);
      this.data0xA0 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA0, 0);
      this.data0xA1 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA1, 0);
      this.data0xA2 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA2, 0);
      this.data0xA3 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA3, 0);
      this.data0xA4 = SharePreUtil.getIntValue(this.mContext, this.SHARE_177_DATA_0XA4, 0);
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte)this.this$0.data0x90});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte)(this.this$0.data0x91 + MsgMgr.ONE)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte)(this.this$0.data0x92 + MsgMgr.ONE)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -109, (byte)(this.this$0.data0x93 + MsgMgr.ONE)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte)this.this$0.data0xA0});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte)this.this$0.data0xA1});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte)this.this$0.data0xA2});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte)this.this$0.data0xA3});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte)this.this$0.data0xA4});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean isDoorDataChange() {
      boolean var1 = this.mIsFrontLeftOpen;
      if (var1 == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsFrontOpen == this.mIsFrontOpenNow) {
         return false;
      } else {
         this.mIsFrontLeftOpenNow = var1;
         this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
         this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
         this.mIsRearRightOpenNow = this.mIsRearRightOpen;
         this.mIsBackOpenNow = this.mIsBackOpen;
         this.mIsFrontOpenNow = this.mIsFrontOpen;
         return true;
      }
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var3 = "";
      String var2;
      if (var1 == 0) {
         var2 = var3;
      } else if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = var3;
         if (var1 >= 32) {
            var2 = var3;
            if (var1 <= 64) {
               var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
            }
         }
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 == 255) {
         return "--" + this.mContext.getString(2131770016);
      } else {
         return var1 == 254 ? "" : this.mCanBusInfoInt[3] - 40 + this.mContext.getString(2131770016);
      }
   }

   private void setAccused0x20() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 != 7) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 18) {
                                    if (var1 != 143) {
                                       if (var1 != 21) {
                                          if (var1 == 22) {
                                             this.realKeyLongClick1(this.mContext, 49, var2[3]);
                                          }
                                       } else {
                                          this.realKeyLongClick1(this.mContext, 50, var2[3]);
                                       }
                                    } else {
                                       this.realKeyClick4(14);
                                    }
                                 } else {
                                    this.realKeyLongClick1(this.mContext, 187, var2[3]);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 15, var2[3]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 14, var2[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 2, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 3, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setAirData0x21() {
      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var2 = true;
      GeneralAirData.in_out_cycle = var3 ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.soft = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.fast = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.normal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      int var1 = this.mCanBusInfoInt[4];
      GeneralAirData.front_wind_level = var1;
      if (var1 != 15) {
         var2 = false;
      }

      GeneralAirData.front_auto_wind_speed = var2;
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarData0x81() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.getData(2) + this.getOilUnit()));
      var1.add(new DriverUpdateEntity(0, 1, this.getData(4) + this.getSpeedUnit()));
      var1.add(new DriverUpdateEntity(0, 2, this.getDataDistance(6) + this.getDistanceUnit()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var2 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var2[4], var2[5]));
   }

   private void setCarMessage0x28() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = var1;
      this.mIsFrontLeftOpen = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      this.mIsFrontRightOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = var1;
      this.mIsRearLeftOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = var1;
      this.mIsRearRightOpen = var1;
      var1 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = var1;
      this.mIsBackOpen = var1;
      var1 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = var1;
      this.mIsFrontOpen = var1;
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarSetting0x71() {
      int var34 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      int var29 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
      int var30 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
      int var32 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
      int var26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 3);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
      int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
      int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
      int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
      int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
      int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
      int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      int[] var36 = this.mCanBusInfoInt;
      int var2 = var36[5];
      int var4 = var36[6];
      int var17 = var36[7];
      int var12 = var36[8];
      int var31 = DataHandleUtils.getIntFromByteWithBit(var36[9], 7, 1);
      int var33 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1);
      int var35 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
      int var28 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1);
      int var27 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 3);
      var36 = this.mCanBusInfoInt;
      int var23 = var36[10];
      int var24 = DataHandleUtils.getIntFromByteWithBit(var36[11], 7, 1);
      int var25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3);
      int var22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
      int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1);
      int var21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1);
      ArrayList var37 = new ArrayList();
      var37.add(new SettingUpdateEntity(2, 0, var34));
      var37.add(new SettingUpdateEntity(2, 1, var29));
      var37.add(new SettingUpdateEntity(2, 2, var30));
      var37.add(new SettingUpdateEntity(2, 3, var32));
      var37.add(new SettingUpdateEntity(2, 4, var26));
      var37.add(new SettingUpdateEntity(2, 5, var33));
      var37.add(new SettingUpdateEntity(2, 6, var35));
      var37.add(new SettingUpdateEntity(2, 7, var31));
      var37.add(new SettingUpdateEntity(2, 8, var1));
      var37.add(new SettingUpdateEntity(2, 9, var28));
      if (var27 == 0) {
         var1 = 0;
      } else {
         var1 = var27 - ONE;
      }

      var37.add(new SettingUpdateEntity(2, 10, var1));
      var37.add((new SettingUpdateEntity(2, 11, var23)).setProgress(var23));
      var37.add(new SettingUpdateEntity(2, 12, var24));
      if (var25 == 0) {
         var1 = 0;
      } else {
         var1 = var25 - ONE;
      }

      var37.add(new SettingUpdateEntity(2, 13, var1));
      if (var22 == 0) {
         var1 = 0;
      } else {
         var1 = var22 - ONE;
      }

      var37.add(new SettingUpdateEntity(2, 14, var1));
      var37.add(new SettingUpdateEntity(2, 15, var19));
      var37.add(new SettingUpdateEntity(2, 16, var21));
      if (var20 == 0) {
         var1 = 0;
      } else {
         var1 = var20 - ONE;
      }

      var37.add(new SettingUpdateEntity(2, 17, var1));
      var37.add(new SettingUpdateEntity(2, 18, var8));
      var37.add(new SettingUpdateEntity(2, 19, var10));
      var37.add(new SettingUpdateEntity(0, 0, var3));
      var37.add(new SettingUpdateEntity(0, 1, var6));
      var37.add(new SettingUpdateEntity(0, 2, var14));
      var37.add(new SettingUpdateEntity(0, 3, var18));
      var37.add(new SettingUpdateEntity(0, 4, var11));
      var37.add(new SettingUpdateEntity(0, 5, var15));
      var37.add(new SettingUpdateEntity(0, 6, var16));
      var37.add(new SettingUpdateEntity(0, 7, var5));
      var37.add(new SettingUpdateEntity(0, 8, var7));
      var37.add(new SettingUpdateEntity(0, 9, var9));
      var37.add(new SettingUpdateEntity(0, 10, var13 - ONE));
      var37.add(new SettingUpdateEntity(0, 11, var17 - ONE));
      var37.add((new SettingUpdateEntity(0, 12, var12)).setProgress(var12));
      if (var4 == 0) {
         var1 = 0;
      } else {
         var1 = var4 - ONE;
      }

      var37.add(new SettingUpdateEntity(0, 13, var1));
      if (var2 >= 38) {
         var1 = var2 - 4;
      } else if (var2 >= 23) {
         var1 = var2 - 3;
      } else if (var2 >= 4) {
         var1 = var2 - 2;
      } else {
         var1 = var2;
      }

      if (var2 != 4 && var2 != 5 && var2 != 23 && var2 != 38) {
         var37.add(new SettingUpdateEntity(0, 14, var1));
      }

      this.updateGeneralSettingData(var37);
      this.updateSettingActivity((Bundle)null);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_177_LANGUAGE, var2);
   }

   private void setFrontRadarInfo0x23() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLeftRadarInfo0x24() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setLeftRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRadarInfo0x22() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRightRadarInfo0x25() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRightRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSOS0x91() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 0) {
         this.enterAuxIn2();
      } else if (var1 == 1) {
         this.exitAuxIn2();
      }

   }

   private void setTireData0x61() {
      ArrayList var1 = new ArrayList();
      var1.add(this.getTireEntity(0, this.getTirePressure(this.mCanBusInfoInt[3])));
      var1.add(this.getTireEntity(1, this.getTirePressure(this.mCanBusInfoInt[4])));
      var1.add(this.getTireEntity(2, this.getTirePressure(this.mCanBusInfoInt[5])));
      var1.add(this.getTireEntity(3, this.getTirePressure(this.mCanBusInfoInt[6])));
      GeneralTireData.dataList = var1;
      this.updateTirePressureActivity((Bundle)null);
      String var2 = this.getTisWarmMsg(this.mCanBusInfoInt[2]);
      if (!TextUtils.isEmpty(var2)) {
         GeneralDisplayMsgData.displayMsg = var2;
         this.sendDisplayMsgView(this.mContext);
      }

   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 5400, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, -1, -1, -1, -1, -1, -1, -1});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 1}, var1));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.mCallStatus = 1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 1}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.mCallStatus = 2;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 1}, var1));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      int var4 = this.mCallStatus;
      if (var4 == 1) {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 2, 1}, var1));
      } else if (var4 == 2) {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 1}, var1));
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 40) {
         if (var3 != 48) {
            if (var3 != 97) {
               if (var3 != 113) {
                  if (var3 != 127) {
                     if (var3 != 129) {
                        if (var3 != 145) {
                           switch (var3) {
                              case 32:
                                 this.setAccused0x20();
                                 break;
                              case 33:
                                 if (this.isAirMsgRepeat(var2)) {
                                    return;
                                 }

                                 this.setAirData0x21();
                                 break;
                              case 34:
                                 this.setRadarInfo0x22();
                                 break;
                              case 35:
                                 this.setFrontRadarInfo0x23();
                                 break;
                              case 36:
                                 this.setLeftRadarInfo0x24();
                                 break;
                              case 37:
                                 this.setRightRadarInfo0x25();
                           }
                        } else {
                           this.setSOS0x91();
                        }
                     } else {
                        this.setCarData0x81();
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.setCarSetting0x71();
               }
            } else {
               this.setTireData0x61();
            }
         } else {
            this.setTrackInfo();
         }
      } else {
         if (this.isDoorMsgRepeat(var2)) {
            return;
         }

         this.setCarMessage0x28();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 != 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 16, (byte)(var4 / 256), (byte)(var4 % 256), (byte)(var6 / 256), (byte)(var6 % 256), (byte)(var2 / 3600), (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 2});
      GeneralTireData.isHaveSpareTire = false;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 113, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 114, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, -127, 0});
      this.initAmplifierData();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var3 = var17 + 1;
      var8 = (byte)(var4 / 256);
      var9 = (byte)(var4 % 256);
      var1 = (byte)(var3 / 256);
      var2 = (byte)(var3 % 256);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, var8, var9, var1, var2, var5, var6, var7});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.contains("FM")) {
         var5 = (int)(Float.parseFloat(var3) * 100.0F);
      } else if (var2.contains("AM")) {
         var5 = Integer.parseInt(var3);
      } else {
         var5 = 0;
      }

      byte var7 = this.getAllBandTypeData(var2);
      byte var6 = (byte)(var5 % 256);
      byte var8 = (byte)(var5 / 256);
      byte var9 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, var7, var6, var8, var9});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }
}
