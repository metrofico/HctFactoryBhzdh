package com.hzbhd.canbus.car._287;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private byte freqHi;
   private byte freqLo;
   private int[] m0x22Data;
   private int[] m0x23Data;
   private int[] m0x29Data;
   private int[] m0x30Data;
   private int[] m0x40Data;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private boolean mFrontStatus;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int mWheelKeyStatus;
   private int mWheelKeyWhat;

   private byte getAllBandTypeData(String var1) {
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

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.FreqInt = Integer.parseInt(var2);
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private boolean is0x22DataChange() {
      if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x22Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x29DataChange() {
      if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x29Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x30DataChange() {
      if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x30Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x40DataChange() {
      if (Arrays.equals(this.m0x40Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x40Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick3_1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var2, var1, var3[2], var3[3]);
   }

   private void sendPanoramicDispCommand(Context var1) {
      if (SharePreUtil.getBoolValue(var1, "share_287_is_support_panoramic", false)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
      }

   }

   private void setBaseInfo0x28() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      this.mLeftFrontRec = var1;
      GeneralDoorData.isRightFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      this.mRightFrontRec = var1;
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      this.mLeftRearRec = var1;
      GeneralDoorData.isRightRearDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      this.mRightRearRec = var1;
      GeneralDoorData.isLeftRearDoorOpen = var1;
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFrontRadarData0x23() {
      if (this.is0x23DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setMediaSwitch0x40() {
      try {
         switch (this.mCanBusInfoInt[2]) {
            case 0:
               this.enterNoSource();
               this.realKeyClick(52);
               break;
            case 1:
               this.changeBandAm1();
               break;
            case 2:
               this.changeBandFm1();
               break;
            case 3:
               this.changeBandFm2();
               break;
            case 4:
               this.realKeyClick(130);
               break;
            case 5:
               this.realKeyClick(59);
               break;
            case 6:
               this.realKeyClick(61);
               break;
            case 7:
               this.realKeyClick(140);
               break;
            case 8:
               this.realKeyClick(141);
               break;
            case 9:
               this.realKeyClick(129);
               break;
            case 10:
               this.realKeyClick(139);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   private void setRearRadarData0x22() {
      if (this.is0x22DataChange()) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setTrackData0x29() {
      if (this.is0x29DataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 1712, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo0x30() {
      if (this.is0x30DataChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 7) {
                        if (var1 != 135) {
                           if (var1 != 9) {
                              if (var1 != 10) {
                                 if (var1 != 21) {
                                    if (var1 != 22) {
                                       switch (var1) {
                                          case 96:
                                             this.realKeyClick(45);
                                             break;
                                          case 97:
                                             this.realKeyClick(45);
                                             break;
                                          case 98:
                                             this.realKeyClick(48);
                                             break;
                                          case 99:
                                             this.realKeyClick(46);
                                             break;
                                          case 100:
                                             this.realKeyClick(46);
                                             break;
                                          case 101:
                                             this.realKeyClick(46);
                                             break;
                                          case 102:
                                             this.realKeyClick(47);
                                             break;
                                          case 103:
                                             this.realKeyClick(45);
                                             break;
                                          default:
                                             switch (var1) {
                                                case 112:
                                                   this.realKeyClick(49);
                                                   break;
                                                case 113:
                                                   this.realKeyClick(50);
                                                   break;
                                                case 114:
                                                   this.realKeyClick(128);
                                                   break;
                                                case 115:
                                                   this.realKeyClick(52);
                                                   break;
                                                case 116:
                                                   this.realKeyClick3_1(7);
                                                   break;
                                                case 117:
                                                   this.realKeyClick3_1(8);
                                             }
                                       }
                                    } else {
                                       this.realKeyClick(49);
                                    }
                                 } else {
                                    this.realKeyClick(50);
                                 }
                              } else {
                                 this.realKeyClick(202);
                              }
                           } else {
                              this.realKeyClick(14);
                           }
                        } else {
                           this.realKeyClick(134);
                        }
                     } else {
                        this.realKeyClick(2);
                     }
                  } else {
                     this.realKeyClick(45);
                  }
               } else {
                  this.realKeyClick(46);
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

   public void atvInfoChange() {
      super.atvInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3 && var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1, 32});
      } else if (!var3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 32});
      }

   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 48) {
            if (var3 != 64) {
               if (var3 != 34) {
                  if (var3 != 35) {
                     if (var3 != 40) {
                        if (var3 == 41) {
                           this.setTrackData0x29();
                        }
                     } else {
                        this.setBaseInfo0x28();
                     }
                  } else {
                     this.setFrontRadarData0x23();
                  }
               } else {
                  this.setRearRadarData0x22();
               }
            } else {
               this.setMediaSwitch0x40();
            }
         } else {
            this.setVersionInfo0x30();
         }
      } else {
         this.setWheelKey0x20();
      }

   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 2 && var2 != 49) {
         return false;
      } else {
         this.sendPanoramicDispCommand(var1);
         return true;
      }
   }

   public boolean customShortClick(Context var1, int var2) {
      if (!CommUtil.isMiscReverse() || var2 != 2 && var2 != 49) {
         return this.mDifferent == 1 && var2 == 2;
      } else {
         MyLog.temporaryTracking("切换视频");
         this.sendPanoramicDispCommand(var1);
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      if (var10 != 0) {
         var5 = var8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte)var6, (byte)var5, (byte)var10});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var10 == 240) {
         this.sendDiscEjectMsg(this.mContext);
      } else if (var10 == 32) {
         var3 = this.getHour(var2);
         var10 = this.getMinute(var2);
         var2 = this.getSecond(var2);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         byte var17 = (byte)var4;
         var1 = (byte)var6;
         byte var14 = (byte)var3;
         byte var15 = (byte)var10;
         byte var16 = (byte)var2;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, var17, var1, var14, var15, var16});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         var5 = (byte)(var4 & 255);
         var2 = (byte)(var4 >> 8 & 255);
         var1 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, var5, var2, var1, var9, var6, var7});
      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var10 = this.getAllBandTypeData(var2);
      this.getFreqByteHiLo(var2, var3);
      byte var7 = (byte)var10;
      byte var6 = this.freqLo;
      byte var9 = this.freqHi;
      byte var8 = (byte)var1;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, var7, var6, var9, var8, 0, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var1, var2, var3));
      if (var1 == 0 && var2 == 0) {
         boolean var4 = false;
         SettingUpdateEntity var5 = new SettingUpdateEntity(0, 1, (Object)null);
         if (var3 == 1) {
            var4 = true;
         }

         var6.add(var5.setEnable(var4));
      }

      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         var1 = (byte)(var4 & 255);
         var5 = (byte)(var4 >> 8 & 255);
         var2 = (byte)var3;
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, var1, var5, var2, var9, var6, var7});
      }
   }
}
