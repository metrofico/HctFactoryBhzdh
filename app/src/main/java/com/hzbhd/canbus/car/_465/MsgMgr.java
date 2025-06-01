package com.hzbhd.canbus.car._465;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
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

   private void setDrive0x180(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data1"), String.valueOf((double)DataHandleUtils.getMsbLsbResult(var1[7], var1[8]) * 0.125)));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrive0x1F9(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data18"), (float)DataHandleUtils.getMsbLsbResult(var1[8], var1[9]) * 0.01F + "km/h"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrive0x27C(int[] var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data13"), (float)DataHandleUtils.getMsbLsbResult(var1[11], var1[12]) * 0.01F + "km/h"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo((int)((float)DataHandleUtils.getMsbLsbResult(var1[11], var1[12]) * 0.01F));
   }

   private void setDrive0x280(int[] var1) {
      this.updateOutDoorTemp(this.mContext, var1[9] - 40 + "℃");
      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_outdoor_temperature"), var1[9] - 40 + "℃"));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrive0x2DE(int[] var1) {
      ArrayList var5 = new ArrayList();
      int var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data");
      int var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data9");
      String var4;
      if (DataHandleUtils.getBoolBit1(var1[8])) {
         var4 = this.mContext.getString(2131766643);
      } else {
         var4 = this.mContext.getString(2131766642);
      }

      var5.add(new DriverUpdateEntity(var2, var3, var4));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data10");
      if (DataHandleUtils.getBoolBit0(var1[9])) {
         var4 = this.mContext.getString(2131766643);
      } else {
         var4 = this.mContext.getString(2131766642);
      }

      var5.add(new DriverUpdateEntity(var2, var3, var4));
      var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data");
      var3 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data11");
      String var6;
      if (DataHandleUtils.getBoolBit2(var1[9])) {
         var6 = this.mContext.getString(2131766643);
      } else {
         var6 = this.mContext.getString(2131766642);
      }

      var5.add(new DriverUpdateEntity(var2, var3, var6));
      this.updateGeneralDriveData(var5);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrive0x481(int[] var1) {
      // $FF: Couldn't be decompiled
   }

   private void setDrive0x551(int[] var1) {
      // $FF: Couldn't be decompiled
   }

   private void setDrive0x648(int[] var1) {
      // $FF: Couldn't be decompiled
   }

   private void setMute(int[] var1) {
      boolean var2 = DataHandleUtils.getBoolBit7(var1[7]);
      SendKeyManager.getInstance().setAppMute(1, var2);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      int var3 = this.getMsDataType(var4);
      if (var3 != 384) {
         if (var3 != 505) {
            if (var3 != 636) {
               if (var3 != 640) {
                  if (var3 != 734) {
                     if (var3 != 1153) {
                        if (var3 != 1171) {
                           if (var3 != 1361) {
                              if (var3 == 1608) {
                                 this.setDrive0x648(var4);
                              }
                           } else {
                              this.setDrive0x551(var4);
                           }
                        } else {
                           this.setMute(var4);
                        }
                     } else {
                        this.setDrive0x481(var4);
                     }
                  } else {
                     this.setDrive0x2DE(var4);
                  }
               } else {
                  this.setDrive0x280(var4);
               }
            } else {
               this.setDrive0x27C(var4);
            }
         } else {
            this.setDrive0x1F9(var4);
         }
      } else {
         this.setDrive0x180(var4);
      }

   }

   protected int getMsDataType(int[] var1) {
      int var2 = var1[2];
      int var3 = var1[3];
      int var4 = var1[4];
      return var1[5] & 255 | (var2 & 255) << 24 | (var3 & 255) << 16 | (var4 & 255) << 8;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void onAccOff() {
      super.onAccOff();
   }

   public void onAccOn() {
      super.onAccOn();
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
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
