package com.hzbhd.canbus.car._221;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MsgMgr extends AbstractMsgMgr {
   static final int VEHICLE_17_H2S = 7;
   private final int DATA_TYPE = 1;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray = new SparseArray();
   private Context mContext;
   private int mDifferentId;
   private int mFrontCameraStatusNow;
   private boolean mFrontStatus;
   private int mKeyStatus;
   private int mKeyStatus2;
   private int mKonb;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private int mPanoramaBtnNow;
   private byte[] mRadarData;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private byte[] mTrackData;
   private int mVolume;

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         SettingUpdateEntity var2 = new SettingUpdateEntity(-1, -1, (Object)null);
         this.mSettingItemIndeHashMap.put(var1, var2);
         return var2;
      }
   }

   private boolean isDoorChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private boolean isFrontCameraStatusChange() {
      int var1 = this.mCanBusInfoInt[2];
      if (this.mFrontCameraStatusNow == var1) {
         return false;
      } else {
         this.mFrontCameraStatusNow = var1;
         return true;
      }
   }

   private boolean isPanoramaBtnChange() {
      int var2 = this.mFrontCameraStatusNow;
      int var1 = this.mCanBusInfoInt[3];
      if (var2 == var1) {
         return false;
      } else {
         this.mPanoramaBtnNow = var1;
         return true;
      }
   }

   private boolean isTrackDataChange() {
      byte[] var2 = new byte[2];
      byte[] var1 = this.mCanBusInfoByte;
      var2[0] = var1[8];
      var2[1] = var1[9];
      if (Arrays.equals(this.mTrackData, var2)) {
         return false;
      } else {
         this.mTrackData = Arrays.copyOf(var2, 2);
         return true;
      }
   }

   private void realKeyClick2(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick3(int var1) {
      this.realKeyClick3_1(this.mContext, var1, this.mCanBusInfoInt[2], Math.abs(this.mCanBusInfoByte[3]));
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[5]);
   }

   private String resolveOutdoorTemp(Context var1, int var2) {
      return var2 == 255 ? "--.-" : (double)var2 * 0.5 - 40.0 + this.getTempUnitC(var1);
   }

   private String resolveTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 255) {
         var2 = "HI";
      } else if (var1 > 0 && var1 <= 38) {
         var2 = var1 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void set0x11CarBaseData(Context var1) {
      int var3 = this.mKeyStatus;
      int var2 = this.mCanBusInfoInt[4];
      if (var3 != var2) {
         this.mKeyStatus = var2;
      }

      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 5) {
                  if (var2 != 6) {
                     switch (var2) {
                        case 12:
                           this.realKeyLongClick1(var1, 2);
                           break;
                        case 13:
                           this.realKeyLongClick1(var1, 46);
                           break;
                        case 14:
                           this.realKeyLongClick1(var1, 45);
                     }
                  } else {
                     this.realKeyLongClick1(var1, 15);
                  }
               } else {
                  this.realKeyLongClick1(var1, 14);
               }
            } else {
               this.realKeyLongClick1(var1, 8);
            }
         } else {
            this.realKeyLongClick1(var1, 7);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

      if (this.isTrackDataChange()) {
         byte[] var4 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var4[9], var4[8], 0, 5500, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x12CarBaseData2(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      if (this.isDoorChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x21PanelKey(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 44) {
                     if (var2 != 63) {
                        if (var2 != 67) {
                           switch (var2) {
                              case 71:
                                 this.realKeyClick2(var1, 14);
                                 break;
                              case 72:
                                 this.realKeyClick2(var1, 15);
                                 break;
                              case 73:
                                 this.realKeyClick2(var1, 62);
                                 break;
                              case 74:
                                 this.realKeyClick2(var1, 58);
                                 break;
                              case 75:
                                 this.realKeyClick2(var1, 128);
                           }
                        } else {
                           this.realKeyClick2(var1, 3);
                        }
                     } else {
                        this.realKeyClick2(var1, 52);
                     }
                  } else {
                     this.realKeyClick2(var1, 2);
                  }
               } else {
                  this.realKeyClick2(var1, 20);
               }
            } else {
               this.realKeyClick2(var1, 21);
            }
         } else {
            this.realKeyClick2(var1, 134);
         }
      } else {
         this.realKeyClick2(var1, 0);
      }

   }

   private void set0x22KnobKey(Context var1) {
      if (this.mCanBusInfoInt[2] == 1) {
         int var2 = this.mKonb - this.mCanBusInfoByte[3];
         if (var2 < 0) {
            if (this.mVolume < 40) {
               this.realKeyClick3_1(var1, 7, 0, Math.abs(var2));
            }
         } else {
            if (var2 <= 0) {
               return;
            }

            if (this.mVolume > 0) {
               this.realKeyClick3_1(var1, 8, 0, Math.abs(var2));
            }
         }

         this.mKonb = this.mCanBusInfoByte[3];
      }

   }

   private void set0x31AirData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var2 = this.mCanBusInfoInt[6];
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         if (var2 != 10) {
            switch (var2) {
               case 2:
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 3:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  break;
               case 4:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  break;
               case 5:
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               case 6:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  break;
               case 7:
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.resolveTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.resolveTemp(this.mCanBusInfoInt[9]);
         this.updateOutDoorTemp(var1, this.resolveOutdoorTemp(var1, this.mCanBusInfoInt[13]));
         this.updateAirActivity(var1, 1001);
      }

   }

   private void set0x32CarData(Context var1) {
      int[] var8 = this.mCanBusInfoInt;
      double var4 = (double)var8[8];
      double var2 = (double)(var8[11] - 40);
      double var6 = (double)(var8[14] - 40);
      ArrayList var9 = new ArrayList();
      var9.add(new DriverUpdateEntity(0, 0, (new DecimalFormat("0.0")).format(var4 * 0.1) + " V"));
      var9.add(new DriverUpdateEntity(0, 1, var2 + this.getTempUnitC(var1)));
      var9.add(new DriverUpdateEntity(0, 2, var6 + this.getTempUnitC(var1)));
      this.updateGeneralDriveData(var9);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x41RadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationDataType2(3, var2[2], 7, var2[3], 7, var2[4], 3, var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0xE8PanoramicInfo(Context var1) {
      boolean var2 = CommUtil.isPanoramic(var1);
      boolean var3 = false;
      if (!var2 && this.isFrontCameraStatusChange()) {
         if (this.mCanBusInfoInt[2] == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.switchFCamera(var1, var2);
      }

      if (this.isPanoramaBtnChange()) {
         ArrayList var4 = new ArrayList();
         if (this.mCanBusInfoInt[3] == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var2));
         if (this.mCanBusInfoInt[3] == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var2));
         var2 = var3;
         if (this.mCanBusInfoInt[3] == 3) {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var2));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0xf0Version() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void cameraDestroy() {
      super.cameraDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
   }

   public void cameraInfoChange() {
      super.cameraInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 49) {
                     if (var3 != 50) {
                        if (var3 != 65) {
                           if (var3 != 232) {
                              if (var3 == 240) {
                                 this.set0xf0Version();
                              }
                           } else {
                              this.set0xE8PanoramicInfo(var1);
                           }
                        } else {
                           this.set0x41RadarData(var1);
                        }
                     } else {
                        this.set0x32CarData(var1);
                     }
                  } else {
                     this.set0x31AirData(var1);
                  }
               } else {
                  this.set0x22KnobKey(var1);
               }
            } else {
               this.set0x21PanelKey(var1);
            }
         } else {
            this.set0x12CarBaseData2(var1);
         }
      } else {
         this.set0x11CarBaseData(var1);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      this.mVolume = var1;
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte)var8, (byte)var6, (byte)var7});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 17});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 18});
      this.mDifferentId = this.getCurrentCanDifferentId();
   }
}
