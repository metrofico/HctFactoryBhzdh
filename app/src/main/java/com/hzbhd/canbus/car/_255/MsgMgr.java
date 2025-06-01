package com.hzbhd.canbus.car._255;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private byte freqHi;
   private byte freqLo;
   private boolean isid12;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mIsHfpConnected;

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

   private void realKeyClick(int var1) {
      this.realKeyClick1(this.mContext, var1, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
   }

   private void realKeyControl() {
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
                           break;
                        case 14:
                           this.realKeyClick(188);
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

   private void setDoorData0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoByte[3]);
      this.updateDoorView(this.mContext);
   }

   private void setFrontRadarInfo() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadarInfo() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingData0x52() {
      int var1 = this.mCanBusInfoInt[3];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, var1 - 1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 9232, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      if (this.isid12) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7});
      }

   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      if (this.isid12) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 18, 0});
      }

   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      byte var10;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               var10 = 0;
            } else {
               var10 = 4;
            }
         } else {
            var10 = 3;
         }
      } else {
         var10 = 1;
      }

      byte[] var11 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, var10, 18, 2, 18}, var2);
      if (this.isid12) {
         CanbusMsgSender.sendMsg(var11);
      }

      if (this.mIsHfpConnected != var3) {
         this.mIsHfpConnected = var3;
         byte var12;
         if (var3) {
            var12 = -127;
         } else {
            var12 = -128;
         }

         var2 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, var12, 18, 2, 18}, var2);
         if (this.isid12) {
            CanbusMsgSender.sendMsg(var2);
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
                        this.setRearRadarInfo();
                        break;
                     case 39:
                        this.setFrontRadarInfo();
                        break;
                     case 40:
                        if (this.isDoorMsgRepeat(var2)) {
                           return;
                        }

                        this.setDoorData0x28();
                  }
               } else {
                  this.setVersionInfo();
               }
            } else {
               this.setSettingData0x52();
            }
         } else {
            this.setTrackInfo();
         }
      } else {
         this.realKeyControl();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      int var2 = this.getCurrentCanDifferentId();
      boolean var4 = true;
      boolean var3 = var4;
      if (var2 != 1) {
         if (this.getCurrentCanDifferentId() == 2) {
            var3 = var4;
         } else {
            var3 = false;
         }
      }

      this.isid12 = var3;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         byte[] var26 = new byte[0];

         label19: {
            byte[] var27;
            try {
               var27 = var21.getBytes("UTF-8");
            } catch (UnsupportedEncodingException var25) {
               var25.printStackTrace();
               break label19;
            }

            var26 = var27;
         }

         var26 = DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte)var26.length}, var26);
         if (this.isid12) {
            this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var26);
         }

      }
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      int var10 = var2.hashCode();
      byte var11 = -1;
      switch (var10) {
         case 2092:
            if (var2.equals("AM")) {
               var11 = 0;
            }
            break;
         case 2247:
            if (var2.equals("FM")) {
               var11 = 1;
            }
            break;
         case 64901:
            if (var2.equals("AM1")) {
               var11 = 2;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var11 = 3;
            }
            break;
         case 64903:
            if (var2.equals("AM3")) {
               var11 = 4;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var11 = 5;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var11 = 6;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var11 = 7;
            }
      }

      byte var6;
      switch (var11) {
         case 0:
            var6 = 16;
            break;
         case 1:
            var6 = 0;
            break;
         case 2:
            var6 = 17;
            break;
         case 3:
            var6 = 18;
            break;
         case 4:
            var6 = 19;
            break;
         case 5:
         default:
            var6 = 1;
            break;
         case 6:
            var6 = 2;
            break;
         case 7:
            var6 = 3;
      }

      this.getFreqByteHiLo(var2, var3);
      byte var8 = this.freqLo;
      byte var9 = this.freqHi;
      byte var7 = (byte)var1;
      if (this.isid12) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, var6, var8, var9, var7});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (this.isid12) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = "VEDIO " + (var9 * 256 + var3) + "/" + var4 + " " + var11 + ":" + var12 + ":" + var13;
      var1 = (byte)var8.length();
      byte[] var18 = DataHandleUtils.stringGetBytes(var8, "UTF-8");
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, var1}, var18);
      if (this.isid12) {
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      }

   }
}
