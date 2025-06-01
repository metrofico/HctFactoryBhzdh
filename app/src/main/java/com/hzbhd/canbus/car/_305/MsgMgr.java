package com.hzbhd.canbus.car._305;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private boolean isBackOpen;
   private boolean isFrontOpen;
   private boolean isLeftFrontDoorOpen;
   private boolean isLeftRearDoorOpen;
   private boolean isRightFrontDoorOpen;
   private boolean isRightRearDoorOpen;
   private int mCallStatus = 1;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mRearFrontRadar;
   private boolean mShowFrontRadar;
   private String mSongArtist = "";
   private String mSongTitle = "";

   private byte getBandUnit(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
            return 17;
         case 1:
            return 18;
         case 2:
            return 1;
         case 3:
            return 2;
         case 4:
            return 3;
         default:
            return 0;
      }
   }

   private int getFrepInt(String var1, String var2) {
      double var3;
      if (var1.contains("FM")) {
         var3 = Double.parseDouble(var2) * 100.0;
      } else {
         var3 = Double.parseDouble(var2);
      }

      return (int)var3;
   }

   private boolean isOnlyDoorMsgShow() {
      if (this.isRightFrontDoorOpen != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (this.isLeftFrontDoorOpen != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (this.isRightRearDoorOpen != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (this.isLeftRearDoorOpen != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (this.isBackOpen != GeneralDoorData.isBackOpen) {
         return true;
      } else {
         return this.isFrontOpen != GeneralDoorData.isFrontOpen;
      }
   }

   private boolean isOther() {
      boolean var1;
      if (this.getCurrentCanDifferentId() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isRadarShow(int var1) {
      boolean var2;
      if (var1 > 0 && var1 <= 4) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private void realKeyClick(int var1) {
      this.realKeyClick1(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private void setCarInfo0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isOnlyDoorMsgShow()) {
         this.isRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
         this.isLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
         this.isRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
         this.isLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
         this.isBackOpen = GeneralDoorData.isBackOpen;
         this.isFrontOpen = GeneralDoorData.isFrontOpen;
         this.updateDoorView(this.mContext);
      }

      ArrayList var3 = new ArrayList();
      int var1;
      Context var2;
      if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
         var2 = this.mContext;
         var1 = 2131769741;
      } else {
         var2 = this.mContext;
         var1 = 2131769742;
      }

      var3.add(new DriverUpdateEntity(0, 0, var2.getString(var1)));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarSettingInfo0x52() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[3] - 1));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setFrontRadarData0x27(Context var1) {
      boolean var3 = true;
      RadarInfoUtil.mMinIsClose = true;
      int[] var4 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var4[2], var4[3], var4[4], var4[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
      boolean var2 = var3;
      if (!this.isRadarShow(this.mCanBusInfoInt[2])) {
         var2 = var3;
         if (!this.isRadarShow(this.mCanBusInfoInt[3])) {
            var2 = var3;
            if (!this.isRadarShow(this.mCanBusInfoInt[4])) {
               if (this.isRadarShow(this.mCanBusInfoInt[5])) {
                  var2 = var3;
               } else {
                  var2 = false;
               }
            }
         }
      }

      this.mShowFrontRadar = var2;
      this.showRadar(var1, var2, this.mRearFrontRadar);
   }

   private void setKeyEvent0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     switch (var1) {
                        case 9:
                           this.realKeyClick(14);
                           break;
                        case 10:
                           this.realKeyClick(15);
                           break;
                        case 11:
                           this.realKeyClick(45);
                           break;
                        case 12:
                           this.realKeyClick(46);
                           break;
                        case 13:
                           this.realKeyClick(187);
                     }
                  } else {
                     this.realKeyClick(2);
                  }
               } else {
                  this.realKeyClick(3);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setRearRadarData0x26(Context var1) {
      boolean var3 = true;
      RadarInfoUtil.mMinIsClose = true;
      int[] var4 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var4[2], var4[3], var4[4], var4[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
      boolean var2 = var3;
      if (!this.isRadarShow(this.mCanBusInfoInt[2])) {
         var2 = var3;
         if (!this.isRadarShow(this.mCanBusInfoInt[3])) {
            var2 = var3;
            if (!this.isRadarShow(this.mCanBusInfoInt[4])) {
               if (this.isRadarShow(this.mCanBusInfoInt[5])) {
                  var2 = var3;
               } else {
                  var2 = false;
               }
            }
         }
      }

      this.mRearFrontRadar = var2;
      this.showRadar(var1, this.mShowFrontRadar, var2);
   }

   private void setTrack0x30() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 9232, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0x7f() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void showRadar(Context var1, boolean var2, boolean var3) {
      if (!var2 && !var3) {
         var2 = false;
      } else {
         var2 = true;
      }

      this.forceReverse(var1, var2);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      if (this.isOther()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 0, 0, 0});
      }

   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.mCallStatus = 5;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 18, 2, (byte)var1.length}, var1));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      if (this.isOther()) {
         this.mCallStatus = 1;
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 18, 2, (byte)var1.length}, var1));
      }

   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      if (this.isOther()) {
         this.mCallStatus = 2;
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 18, 2, (byte)var1.length}, var1));
      }

   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (this.isOther()) {
         var1 = this.mCallStatus;
         if (var1 == 1) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 2, 18, 2, (byte)var2.length}, var2));
         } else if (var1 == 2) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 18, 2, (byte)var2.length}, var2));
         } else if (var1 == 5) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 18, 2, (byte)var2.length}, var2));
         }
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 48) {
            if (var3 != 82) {
               if (var3 != 127) {
                  switch (var3) {
                     case 38:
                        this.setRearRadarData0x26(var1);
                        break;
                     case 39:
                        this.setFrontRadarData0x27(var1);
                        break;
                     case 40:
                        this.setCarInfo0x28();
                  }
               } else {
                  this.setVersionInfo0x7f();
               }
            } else if (this.getCurrentCanDifferentId() == 1) {
               this.setCarSettingInfo0x52();
            }
         } else {
            this.setTrack0x30();
         }
      } else {
         this.setKeyEvent0x21();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (this.isOther() && (!this.mSongTitle.equals(var21) || !this.mSongArtist.equals(var23))) {
         byte[] var25 = var21.getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte)var25.length}, var25));
         var25 = var23.getBytes();
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 2, (byte)var25.length}, var25));
         this.mSongTitle = var21;
         this.mSongArtist = var23;
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (this.isOther()) {
         var5 = this.getFrepInt(var2, var3);
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, this.getBandUnit(var2), (byte)(var5 % 256), (byte)(var5 / 256), (byte)var1});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
      this.mSongTitle = "";
      this.mSongArtist = "";
   }
}
