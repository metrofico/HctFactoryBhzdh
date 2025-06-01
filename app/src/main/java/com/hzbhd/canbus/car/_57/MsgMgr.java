package com.hzbhd.canbus.car._57;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   boolean currentPresetStore;
   private boolean mBackStatus;
   private int mBtOn;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mData;
   private int[] mDriveDatax16Now;
   private int mFrontCameraStatusNow;
   private boolean mFrontStatus;
   private String mFuelUnit;
   private int mId;
   private String mInvalid = " - -";
   private boolean mIsDoorFirst = true;
   private boolean mIsMute;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private String mMileageUnit;
   private int mPanoramicBtnNow;
   private int mPanoramicStatusNow;
   private int[] mRadarDataNow;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private TimerTask mTimeTask;
   private Timer mTimer;
   private byte[] mTrackDataNow;
   private UiMgr mUiMgr;
   private String mUsbSonTimeNow;
   private String mUsbSongAlbumNow;
   private String mUsbSongArtistNow;
   private String mUsbSongTitleNow;
   private byte[] mVersionInfoNow;
   private int mWheelKeyNow;
   private byte[] mediaDataNow;
   private int vol;

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private int getDriveData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private int getDriveData(int var1, int var2, int var3) {
      return var1 * 256 * 256 + var2 * 256 + var3;
   }

   private int getFuelRange(int var1) {
      switch (var1) {
         case 1:
            return 10;
         case 2:
            return 12;
         case 3:
            return 20;
         case 4:
            return 30;
         case 5:
            return 40;
         case 6:
            return 50;
         case 7:
            return 70;
         case 8:
            return 80;
         case 9:
            return 90;
         case 10:
            return 100;
         default:
            return 60;
      }
   }

   private String getFuelUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " MPG";
      } else if (var1 == 1) {
         var2 = " km/L";
      } else if (var1 == 2) {
         var2 = " L/100km";
      } else {
         var2 = "";
      }

      return var2;
   }

   private String getMileageUnit(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = " km";
      } else if (var1 == 1) {
         var2 = " mile";
      } else {
         var2 = "";
      }

      return var2;
   }

   private boolean isDoorDataChange() {
      if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
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

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isDriveData0x16Change() {
      if (Arrays.equals(this.mDriveDatax16Now, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDriveDatax16Now = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontCameraStatusChange() {
      int var1 = this.mCanBusInfoInt[4];
      if (this.mFrontCameraStatusNow == var1) {
         return false;
      } else {
         this.mFrontCameraStatusNow = var1;
         return true;
      }
   }

   private boolean isPanoramicBtnChange() {
      int var2 = this.mPanoramicBtnNow;
      int var1 = this.mCanBusInfoInt[3];
      if (var2 == var1) {
         return false;
      } else {
         this.mPanoramicBtnNow = var1;
         return true;
      }
   }

   private boolean isPanoramicStatusChange() {
      int var1 = this.mCanBusInfoInt[6];
      if (this.mPanoramicStatusNow == var1) {
         return false;
      } else {
         this.mPanoramicStatusNow = var1;
         return true;
      }
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isSupportDriveData0x17() {
      int var1 = this.mId;
      boolean var2;
      if (var1 != 0 && var1 != 17 && var1 != 18) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private boolean isSupportPanoramic() {
      int var1 = this.mId;
      boolean var2;
      if (var1 != 18 && var1 != 12 && var1 != 13 && var1 != 14 && var1 != 15 && var1 != 6) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   private boolean isTrackDataChange() {
      byte[] var2 = new byte[2];
      byte[] var1 = this.mCanBusInfoByte;
      var2[0] = var1[8];
      var2[1] = var1[9];
      if (Arrays.equals(this.mTrackDataNow, var2)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var2, 2);
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isWheelKeyDataChange() {
      int var1 = this.mWheelKeyNow;
      int var2 = this.mCanBusInfoInt[4];
      if (var1 == var2) {
         return var2 == 1 || var2 == 2 || var2 == 8 || var2 == 9;
      } else {
         this.mWheelKeyNow = var2;
         return true;
      }
   }

   private void reportID3Info(String var1, String var2, String var3, boolean var4, String var5) {
      (new Thread(this, var4, var1, var2, var3, var5) {
         final MsgMgr this$0;
         final String val$album;
         final String val$artist;
         final String val$charsetName;
         final boolean val$isClean;
         final String val$title;

         {
            this.this$0 = var1;
            this.val$isClean = var2;
            this.val$title = var3;
            this.val$album = var4;
            this.val$artist = var5;
            this.val$charsetName = var6;
         }

         public void run() {
            super.run();

            try {
               if (this.val$isClean) {
                  sleep(1000L);
               }

               if (!this.val$title.equals(this.this$0.mUsbSongTitleNow) || !this.val$album.equals(this.this$0.mUsbSongAlbumNow) || !this.val$artist.equals(this.this$0.mUsbSongArtistNow)) {
                  this.this$0.mUsbSongTitleNow = this.val$title;
                  this.this$0.mUsbSongAlbumNow = this.val$album;
                  this.this$0.mUsbSongArtistNow = this.val$artist;
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-30, this.val$title, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-29, this.val$album, this.val$charsetName);
                  sleep(100L);
                  this.this$0.reportID3InfoFinal((byte)-28, this.val$artist, this.val$charsetName);
               }
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private void reportID3InfoFinal(byte var1, String var2, String var3) {
      try {
         byte[] var5 = DataHandleUtils.exceptBOMHead(var2.getBytes(var3));
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, var1}, var5), 34));
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void setDoorData0x12() {
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setDriveData0x11() {
      ArrayList var3 = new ArrayList();
      int var1 = this.mCanBusInfoInt[7];
      String var2;
      if (var1 != 0) {
         if (var1 != 100) {
            var2 = this.mCanBusInfoInt[7] + "";
         } else {
            var2 = this.mContext.getResources().getString(2131769223);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131769222);
      }

      var3.add(new DriverUpdateEntity(0, 1, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x16() {
      if (this.isDriveData0x16Change()) {
         int var1 = this.getFuelRange(this.mCanBusInfoInt[15]);
         ArrayList var5 = new ArrayList();
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var3[2];
         String var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(var3[14], 0, 2));
         var5.add(new DriverUpdateEntity(1, 0, (new DecimalFormat("0.0")).format((double)((float)var1 / 21.0F * (float)var2)) + var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[3], var3[4]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
         var6 = (float)var1 / 10.0F + var6;
         String var4 = "--";
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 1, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[5], var3[6]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 2, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[9], var3[10], var3[11]);
         var6 = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 1));
         this.mMileageUnit = var6;
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 3, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[7], var3[8]);
         var6 = this.getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 2));
         this.mFuelUnit = var6;
         var6 = (float)var1 / 10.0F + var6;
         if (var1 == 65535) {
            var6 = "--";
         }

         var5.add(new DriverUpdateEntity(1, 4, var6));
         var3 = this.mCanBusInfoInt;
         var1 = this.getDriveData(var3[12], var3[13]);
         var6 = this.getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1));
         var6 = var1 + var6;
         if (var1 == 65535) {
            var6 = var4;
         }

         var5.add(new DriverUpdateEntity(1, 5, var6));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setDriveData0x17() {
      ArrayList var3 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      float var1 = (float)this.getDriveData(var2[2], var2[3], var2[4]);
      String var4;
      if (var1 == 1.6777215E7F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mMileageUnit;
      }

      var3.add(new DriverUpdateEntity(2, 1, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[5], var2[6]);
      if (var1 == 65535.0F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mFuelUnit;
      }

      var3.add(new DriverUpdateEntity(2, 2, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[7], var2[8], var2[9]);
      if (var1 == 1.6777215E7F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mMileageUnit;
      }

      var3.add(new DriverUpdateEntity(2, 4, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[10], var2[11]);
      if (var1 == 65535.0F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mFuelUnit;
      }

      var3.add(new DriverUpdateEntity(2, 5, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[12], var2[13], var2[14]);
      if (var1 == 1.6777215E7F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mMileageUnit;
      }

      var3.add(new DriverUpdateEntity(2, 7, var4));
      var2 = this.mCanBusInfoInt;
      var1 = (float)this.getDriveData(var2[15], var2[16]);
      if (var1 == 65535.0F) {
         var4 = this.mInvalid;
      } else {
         var4 = var1 / 10.0F + this.mFuelUnit;
      }

      var3.add(new DriverUpdateEntity(2, 8, var4));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setKeyTrack0x11() {
      if (this.isWheelKeyDataChange()) {
         int var1 = this.mCanBusInfoInt[4];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           if (var1 != 8) {
                              if (var1 != 9) {
                                 if (var1 == 11) {
                                    this.wheelKeyLongClick(2);
                                 }
                              } else {
                                 this.wheelKeyLongClick(46);
                              }
                           } else {
                              this.wheelKeyLongClick(45);
                           }
                        } else {
                           this.wheelKeyLongClick(15);
                        }
                     } else {
                        this.wheelKeyLongClick(14);
                     }
                  } else {
                     this.wheelKeyLongClick(187);
                  }
               } else {
                  this.wheelKeyLongClick(8);
               }
            } else {
               this.wheelKeyLongClick(7);
            }
         } else {
            this.wheelKeyLongClick(0);
         }
      }

      if (this.isTrackDataChange()) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(var2[9], var2[8], 0, 5200, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + "km/h"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setPanoramic0xE8() {
      boolean var1 = CommUtil.isMiscReverse();
      boolean var2 = false;
      if (!var1 && this.isFrontCameraStatusChange()) {
         Context var3 = this.mContext;
         if (this.mCanBusInfoInt[4] == 1) {
            var1 = true;
         } else {
            var1 = false;
         }

         this.switchFCamera(var3, var1);
      }

      if (this.isPanoramicBtnChange()) {
         ArrayList var4 = new ArrayList();
         if (this.mCanBusInfoInt[3] == 1) {
            var1 = true;
         } else {
            var1 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var1));
         if (this.mCanBusInfoInt[3] == 2) {
            var1 = true;
         } else {
            var1 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var1));
         var1 = var2;
         if (this.mCanBusInfoInt[3] == 3) {
            var1 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var1));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         UiMgr var3 = this.mUiMgr;
         int var1 = this.mCanBusInfoInt[12];
         boolean var2 = true;
         if (var1 != 1) {
            var2 = false;
         }

         var3.setShowRadar(var2);
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mDisableData = 255;
         int[] var4 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var4[2], var4[3], var4[4], var4[5]);
         var4 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var4[6], 255, 255, var4[9]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void startRequest0x32() {
      if (this.mTimer == null) {
         this.mTimer = new Timer();
      }

      if (this.mTimeTask == null) {
         TimerTask var1 = new TimerTask(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 50});
            }
         };
         this.mTimeTask = var1;
         this.mTimer.schedule(var1, 0L, 1000L);
      }

   }

   private void wheelKeyLongClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void wheelKeyShortClick(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      Log.d("_55_atv", "  .");
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, var1));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      Log.d("_55_aux", "  .");
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      System.arraycopy("AUX".getBytes(), 0, var1, 0, "AUX".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 12}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "A2DP        ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, var1));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 4}, var4), new byte[]{0}));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 1}, var4), new byte[]{0}));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 2}, var4), new byte[]{0}));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, (boolean)var3, var4, var5, var6, var7, var8, var9);
      if (var3 != 0 && var1 == 0) {
         var2 = new byte[30];
         Arrays.fill(var2, (byte)0);
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 0}, var2), new byte[]{0}));
      }

      this.mBtOn = var3;
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[30];
      Arrays.fill(var4, (byte)32);
      var4[var1.length] = 0;
      System.arraycopy(var1, 0, var4, 0, var1.length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 3}, var4), new byte[]{0}));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var1 = ("    " + (new DecimalFormat("00")).format((long)(var4 / 60 % 60)) + (new DecimalFormat("00")).format((long)(var4 % 60)) + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var1));
   }

   public void cameraDestroy() {
      super.cameraDestroy();
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
   }

   public void cameraInfoChange() {
      super.cameraInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 22) {
               if (var3 != 23) {
                  if (var3 != 65) {
                     if (var3 != 232) {
                        if (var3 == 240) {
                           this.setVersionInfo0xF0();
                        }
                     } else {
                        this.setPanoramic0xE8();
                     }
                  } else {
                     this.setRadarData0x41();
                  }
               } else {
                  this.setDriveData0x17();
               }
            } else {
               this.setDriveData0x16();
            }
         } else {
            this.setDoorData0x12();
         }
      } else {
         this.setKeyTrack0x11();
         this.setDriveData0x11();
         this.updateSpeedInfo(this.mCanBusInfoInt[3]);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.vol = var1;
      this.mIsMute = var2;
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte)var5, (byte)var6, (byte)var7});
   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      var1 = var2 << 3 | var3 << 4 | var6 << 7 | this.mBtOn << 6 | this.mIsMute << 5 | var11 << 2 | var12 << 1;
      this.mData = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -26, (byte)var1, 0, 0, 0, (byte)this.vol, 0, 0, 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var1 == 16) {
         var11 = "LOADING ";
      } else if (var1 == 17) {
         var11 = "EJECTING";
      } else {
         var11 = "";
      }

      if (var10 != 48) {
         if (var10 != 65) {
            if (var10 == 241) {
               var11 = "ERROR   ";
            }
         } else {
            var11 = "STOP    ";
         }
      } else {
         var11 = "PAUSE   ";
      }

      byte[] var14 = DataHandleUtils.byteMerger(var11.getBytes(), new byte[]{0, 0, 0, 0});
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 6}, var14));
      DecimalFormat var15 = new DecimalFormat("00");
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(((new DecimalFormat("000")).format((long)var5) + " " + var15.format((long)(var2 / 60)) + var15.format((long)(var2 & 60))).getBytes(), new byte[]{0, 0, 0, 0}));
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = new byte[12];
      Arrays.fill(var1, (byte)32);
      System.arraycopy("TV".getBytes(), 0, var1, 0, "TV".getBytes().length);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, var1));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mId = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, 45, 2, (byte)this.getCurrentEachCanId()});
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

   }

   public void musicDestroy() {
      super.musicDestroy();
      this.reportID3Info("", "", "", true, "ascii");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var12 = (new DecimalFormat("00")).format((long)var6);
      var11 = (new DecimalFormat("00")).format((long)var7);
      byte[] var25 = (String.format((new DecimalFormat("000")).format((long)(var9 * 256 + var3))) + " " + var12 + var11 + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var25));
      if (!var11.equals(this.mUsbSonTimeNow)) {
         this.mUsbSonTimeNow = var11;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -104, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      }

      this.reportID3Info(var21, var22, var23, false, "unicode");
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 == 256) {
         var5 = 0;
      }

      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         if (var3.length() == 4) {
            var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3 + "     ";
         } else {
            var2 = (new DecimalFormat("00")).format((long)var5) + " 0" + var3 + "     ";
         }
      } else if (var3.length() == 5) {
         var2 = (new DecimalFormat("00")).format((long)var5) + "  " + var3.substring(0, var3.length() - 1) + "    ";
      } else {
         var2 = (new DecimalFormat("00")).format((long)var5) + " " + var3.substring(0, var3.length() - 1) + "    ";
      }

      byte[] var7 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, var6}, var7));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.reportID3Info("", "", "", true, "ascii");
      CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var11 = (new DecimalFormat("00")).format((long)var6);
      var8 = (new DecimalFormat("00")).format((long)var7);
      byte[] var18 = (String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var11 + var8 + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var18));
      if (!var8.equals(this.mUsbSonTimeNow)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, var6, var7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         this.mUsbSonTimeNow = var8;
      }

   }
}
