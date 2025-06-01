package com.hzbhd.canbus.car._468;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
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
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   int[] mAirData;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   int[] mTrackData;
   private UiMgr mUiMgr;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isBasicInfoChange(int[] var1) {
      if (Arrays.equals(this.mCarDoorData, var1)) {
         return false;
      } else {
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange(int[] var1) {
      if (Arrays.equals(this.mPanoramicInfo, var1)) {
         return false;
      } else {
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mRearRadarData, var1)) {
         return false;
      } else {
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange(int[] var1) {
      if (Arrays.equals(this.mTireInfo, var1)) {
         return false;
      } else {
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange(int[] var1) {
      if (Arrays.equals(this.mTrackData, var1)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x1DRadarInfo(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x1ERadarInfo(int[] var1) {
      if (this.isRearRadarDataChange(var1)) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x20Swc(int[] var1) {
      int var2 = var1[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 19) {
                  if (var2 != 20) {
                     if (var2 != 135) {
                        switch (var2) {
                           case 7:
                              this.buttonKey(2, var1);
                              break;
                           case 8:
                              this.buttonKey(187, var1);
                              break;
                           case 9:
                              this.buttonKey(14, var1);
                              break;
                           case 10:
                              this.buttonKey(15, var1);
                        }
                     } else {
                        this.buttonKey(3, var1);
                     }
                  } else {
                     this.buttonKey(46, var1);
                  }
               } else {
                  this.buttonKey(45, var1);
               }
            } else {
               this.buttonKey(8, var1);
            }
         } else {
            this.buttonKey(7, var1);
         }
      } else {
         this.buttonKey(0, var1);
      }

   }

   private void set0x24Door(int[] var1) {
      if (this.isBasicInfoChange(var1)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(var1[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(var1[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(var1[2]);
         this.updateDoorView(this.mContext);
      }

   }

   private void set0x28Air(int[] var1) {
      if (this.isAirDataChange(var1)) {
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(var1[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(var1[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(var1[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(var1[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(var1[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(var1[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(var1[3], 0, 4);
         int var2 = var1[4];
         if (var2 != 0) {
            if (var2 != 34) {
               if (var2 != 255) {
                  if (var2 != 31) {
                     if (var2 != 32) {
                        GeneralAirData.front_left_temperature = (double)((float)var1[4] / 2.0F) + 17.5 + this.getTempUnitC(this.mContext);
                     } else {
                        GeneralAirData.front_left_temperature = 16 + this.getTempUnitC(this.mContext);
                     }
                  } else {
                     GeneralAirData.front_left_temperature = "HI";
                  }
               } else {
                  GeneralAirData.front_left_temperature = "";
               }
            } else {
               GeneralAirData.front_left_temperature = 17 + this.getTempUnitC(this.mContext);
            }
         } else {
            GeneralAirData.front_left_temperature = "LO";
         }

         var2 = var1[5];
         if (var2 != 0) {
            if (var2 != 34) {
               if (var2 != 255) {
                  if (var2 != 31) {
                     if (var2 != 32) {
                        GeneralAirData.front_right_temperature = (double)((float)var1[5] / 2.0F) + 17.5 + this.getTempUnitC(this.mContext);
                     } else {
                        GeneralAirData.front_right_temperature = 16 + this.getTempUnitC(this.mContext);
                     }
                  } else {
                     GeneralAirData.front_right_temperature = "HI";
                  }
               } else {
                  GeneralAirData.front_right_temperature = "";
               }
            } else {
               GeneralAirData.front_right_temperature = 17 + this.getTempUnitC(this.mContext);
            }
         } else {
            GeneralAirData.front_right_temperature = "LO";
         }

         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void set0x29Esp(byte[] var1) {
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 0, 4608, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x30VersionInfo(byte[] var1) {
      this.updateVersionInfo(this.mContext, this.getVersionStr(var1));
   }

   private void set0x35DriveData(int[] var1) {
      ArrayList var8 = new ArrayList();
      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13"), DataHandleUtils.getMsbLsbResult(var1[2], var1[3]) + "rpm"));
      var8.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14"), DataHandleUtils.getMsbLsbResult(var1[6], var1[7]) + "km/h"));
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[6], var1[7]));
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      int var4 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
      StringBuilder var6 = new StringBuilder();
      int var3 = var1[8];
      var8.add(new DriverUpdateEntity(var2, var4, var6.append((var1[9] & 255) << 8 | (var3 & 255) << 16 | var1[10] & 255).append("km").toString()));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Left_turn");
      boolean var5 = DataHandleUtils.getBoolBit0(var1[11]);
      String var7 = "ON";
      String var9;
      if (var5) {
         var9 = "ON";
      } else {
         var9 = "OFF";
      }

      var8.add(new DriverUpdateEntity(var2, var3, var9));
      this.turnLeftLamp(DataHandleUtils.getBoolBit0(var1[11]));
      var3 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
      var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Right_turn");
      if (DataHandleUtils.getBoolBit1(var1[11])) {
         var9 = var7;
      } else {
         var9 = "OFF";
      }

      var8.add(new DriverUpdateEntity(var3, var2, var9));
      this.turnRightLamp(DataHandleUtils.getBoolBit1(var1[11]));
      this.updateGeneralDriveData(var8);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x36PanelKey(int[] var1) {
      switch (var1[2]) {
         case 1:
            this.realKeyClick(this.mContext, 33);
            break;
         case 2:
            this.realKeyClick(this.mContext, 34);
            break;
         case 3:
            this.realKeyClick(this.mContext, 35);
            break;
         case 4:
            this.realKeyClick(this.mContext, 36);
            break;
         case 5:
            this.realKeyClick(this.mContext, 37);
            break;
         case 6:
            this.realKeyClick(this.mContext, 38);
            break;
         case 7:
            this.realKeyClick(this.mContext, 21);
            break;
         case 8:
            this.realKeyClick(this.mContext, 20);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void buttonKey(int var1, int[] var2) {
      this.realKeyLongClick1(this.mContext, var1, var2[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = var4[1];
      if (var3 != 29) {
         if (var3 != 30) {
            if (var3 != 32) {
               if (var3 != 36) {
                  if (var3 != 48) {
                     if (var3 != 40) {
                        if (var3 != 41) {
                           if (var3 != 53) {
                              if (var3 == 54) {
                                 this.set0x36PanelKey(var4);
                              }
                           } else {
                              this.set0x35DriveData(var4);
                           }
                        } else {
                           this.set0x29Esp(var2);
                        }
                     } else {
                        this.set0x28Air(var4);
                     }
                  } else {
                     this.set0x30VersionInfo(var2);
                  }
               } else {
                  this.set0x24Door(var4);
               }
            } else {
               this.set0x20Swc(var4);
            }
         } else {
            this.set0x1ERadarInfo(var4);
         }
      } else {
         this.set0x1DRadarInfo(var4);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      if (this.getCurrentEachCanId() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 0});
      } else if (this.getCurrentEachCanId() == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
      }

   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, (byte)DataHandleUtils.getMsb(var17), (byte)DataHandleUtils.getLsb(var17)});
   }

   public void onAccOff() {
      super.onAccOff();
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      int var9;
      if (var1 == 0) {
         if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
            if ("AM1".equals(var2) || "AM2".equals(var2)) {
               var1 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
               var5 = DataHandleUtils.getMsb(Integer.parseInt(var3));
               var9 = DataHandleUtils.getLsb(Integer.parseInt(var3));
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte)var1, (byte)var5, (byte)var9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
         } else {
            var5 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
            var1 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
            var9 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte)var5, (byte)var1, (byte)var9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         }
      } else {
         byte var6;
         byte var7;
         byte var8;
         if (var1 == 1) {
            if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
               if ("AM1".equals(var2) || "AM2".equals(var2)) {
                  var1 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
                  var5 = DataHandleUtils.getMsb(Integer.parseInt(var3));
                  var9 = DataHandleUtils.getLsb(Integer.parseInt(var3));
                  var6 = (byte)var1;
                  var7 = (byte)var5;
                  var8 = (byte)var9;
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var6, var7, var8, var7, var8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
               }
            } else {
               var9 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
               var1 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
               var5 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
               var7 = (byte)var9;
               var8 = (byte)var1;
               var6 = (byte)var5;
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var7, var8, var6, var8, var6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }
         } else if (var1 == 2) {
            if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
               if ("AM1".equals(var2) || "AM2".equals(var2)) {
                  var1 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
                  var9 = DataHandleUtils.getMsb(Integer.parseInt(var3));
                  var5 = DataHandleUtils.getLsb(Integer.parseInt(var3));
                  var8 = (byte)var1;
                  var6 = (byte)var9;
                  var7 = (byte)var5;
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var8, var6, var7, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0});
               }
            } else {
               var1 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
               var5 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
               var9 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
               var7 = (byte)var1;
               var8 = (byte)var5;
               var6 = (byte)var9;
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var7, var8, var6, 0, 0, var8, var6, 0, 0, 0, 0, 0, 0, 0, 0});
            }
         } else if (var1 == 3) {
            if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
               if ("AM1".equals(var2) || "AM2".equals(var2)) {
                  var9 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
                  var1 = DataHandleUtils.getMsb(Integer.parseInt(var3));
                  var5 = DataHandleUtils.getLsb(Integer.parseInt(var3));
                  var8 = (byte)var9;
                  var6 = (byte)var1;
                  var7 = (byte)var5;
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var8, var6, var7, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0});
               }
            } else {
               var9 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
               var5 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
               var1 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
               var8 = (byte)var9;
               var7 = (byte)var5;
               var6 = (byte)var1;
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var8, var7, var6, 0, 0, 0, 0, var7, var6, 0, 0, 0, 0, 0, 0});
            }
         } else if (var1 == 4) {
            if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
               if ("AM1".equals(var2) || "AM2".equals(var2)) {
                  var9 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
                  var5 = DataHandleUtils.getMsb(Integer.parseInt(var3));
                  var1 = DataHandleUtils.getLsb(Integer.parseInt(var3));
                  var6 = (byte)var9;
                  var7 = (byte)var5;
                  var8 = (byte)var1;
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var6, var7, var8, 0, 0, 0, 0, 0, 0, var7, var8, 0, 0, 0, 0});
               }
            } else {
               var9 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
               var5 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
               var1 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
               var6 = (byte)var9;
               var7 = (byte)var5;
               var8 = (byte)var1;
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var6, var7, var8, 0, 0, 0, 0, 0, 0, var7, var8, 0, 0, 0, 0});
            }
         } else if (var1 == 5) {
            if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
               if ("AM1".equals(var2) || "AM2".equals(var2)) {
                  var5 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
                  var1 = DataHandleUtils.getMsb(Integer.parseInt(var3));
                  var9 = DataHandleUtils.getLsb(Integer.parseInt(var3));
                  var7 = (byte)var5;
                  var6 = (byte)var1;
                  var8 = (byte)var9;
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var7, var6, var8, 0, 0, 0, 0, 0, 0, 0, 0, var6, var8, 0, 0});
               }
            } else {
               var1 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
               var9 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
               var5 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
               var8 = (byte)var1;
               var7 = (byte)var9;
               var6 = (byte)var5;
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var8, var7, var6, 0, 0, 0, 0, 0, 0, 0, 0, var7, var6, 0, 0});
            }
         } else if (var1 == 6) {
            if (!"FM1".equals(var2) && !"FM2".equals(var2) && !"FM3".equals(var2)) {
               if ("AM1".equals(var2) || "AM2".equals(var2)) {
                  var1 = DataHandleUtils.getMsbLsbResult_4bit(8, var1);
                  var9 = DataHandleUtils.getMsb(Integer.parseInt(var3));
                  var5 = DataHandleUtils.getLsb(Integer.parseInt(var3));
                  var8 = (byte)var1;
                  var7 = (byte)var9;
                  var6 = (byte)var5;
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var8, var7, var6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, var7, var6});
               }
            } else {
               var1 = DataHandleUtils.getMsbLsbResult_4bit(0, var1);
               var5 = DataHandleUtils.getMsb((int)(Float.parseFloat(var3) * 10.0F));
               var9 = DataHandleUtils.getLsb((int)(10.0F * Float.parseFloat(var3)));
               var8 = (byte)var1;
               var7 = (byte)var5;
               var6 = (byte)var9;
               CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, var8, var7, var6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, var7, var6});
            }
         }
      }

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

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, var9, (byte)var3});
   }
}
