package com.hzbhd.canbus.car._244;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\"\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0018\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\bH\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u000eH\u0016Jp\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0004H\u0016J\u0010\u0010*\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\fH\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u000eH\u0002J\u0010\u0010-\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0002J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0002JÄ\u0001\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u0002012\u0006\u00106\u001a\u0002012\u0006\u00107\u001a\u0002012\u0006\u00108\u001a\u0002012\u0006\u00109\u001a\u0002012\u0006\u0010:\u001a\u0002012\b\u0010;\u001a\u0004\u0018\u00010<2\b\u0010=\u001a\u0004\u0018\u00010<2\b\u0010>\u001a\u0004\u0018\u00010<2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u0002012\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020@2\b\u0010E\u001a\u0004\u0018\u00010<2\b\u0010F\u001a\u0004\u0018\u00010<2\b\u0010G\u001a\u0004\u0018\u00010<2\u0006\u0010H\u001a\u00020\u000eH\u0016J0\u0010I\u001a\u00020\u00142\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020<2\u0006\u0010M\u001a\u00020<2\u0006\u0010N\u001a\u00020\u0004H\u0016J\u0010\u0010O\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\fH\u0002J\u0010\u0010P\u001a\u00020<2\u0006\u0010Q\u001a\u00020\u0004H\u0002J\b\u0010R\u001a\u00020<H\u0002JN\u0010S\u001a\u00020\u00142\b\b\u0002\u0010T\u001a\u00020\u00042\b\b\u0002\u0010U\u001a\u00020\u00042\b\b\u0002\u0010V\u001a\u00020\u00042\b\b\u0002\u0010W\u001a\u00020\u00042\b\b\u0002\u0010X\u001a\u00020\u00042\b\b\u0002\u0010Y\u001a\u00020\u00042\b\b\u0002\u0010Z\u001a\u00020\u0004H\u0002J\b\u0010[\u001a\u00020\u0014H\u0002J\b\u0010\\\u001a\u00020\u0014H\u0002J\b\u0010]\u001a\u00020\u0014H\u0002J\b\u0010^\u001a\u00020\u0014H\u0002J\b\u0010_\u001a\u00020\u0014H\u0002J\u0010\u0010`\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\fH\u0002J\b\u0010a\u001a\u00020\u0014H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006b"},
   d2 = {"Lcom/hzbhd/canbus/car/_244/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "d7", "", "getD7", "()I", "mCanBusInfoByte", "", "mCanBusInfoInt", "", "mContext", "Landroid/content/Context;", "mute", "", "getMute", "()Z", "setMute", "(Z)V", "auxInInfoChange", "", "btMusicInfoChange", "canbusInfoChange", "context", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "frontRadarInfo", "getIndexBy2Bit", "bit", "getIndexBy3Bit", "getIndexBy4Bit", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "rearRadarInfo", "resolveLeftAndRightTemp", "value", "resolveOutDorrTem", "sendMediaInfo", "d0", "d1", "d2", "d3", "d4", "d5", "d6", "setAirData0x23", "setAirData0x36", "setDoorData0x28", "setSettingData0x40", "setVersionInfo", "steeringWheelAngle", "wheelSteerBtn", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte = new byte[0];
   private int[] mCanBusInfoInt = new int[0];
   private Context mContext;
   private boolean mute;

   private final void frontRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationDataType2(3, var2[2], 4, var2[3], 4, var2[4], 3, var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private final int getD7() {
      byte var1;
      if (this.mute) {
         var1 = 2;
      } else {
         var1 = 1;
      }

      return var1;
   }

   private final int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private final int getIndexBy3Bit(int var1) {
      byte var2 = 2;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private final int getIndexBy4Bit(int var1) {
      byte var2 = 3;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private final void rearRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationDataType2(3, var2[2], 4, var2[3], 4, var2[4], 3, var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private final String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (254 == var1) {
         Context var3 = this.mContext;
         Intrinsics.checkNotNull(var3);
         var2 = var3.getString(2131769395);
         Intrinsics.checkNotNullExpressionValue(var2, "{\n            mContext!!…ing.no_display)\n        }");
      } else if (18 <= var1 && 32 >= var1) {
         var2 = var1 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private final String resolveOutDorrTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         if (var1 == 0) {
            var2 = " 0 ";
         } else {
            var2 = "" + '-' + var1 + ' ';
         }
      } else {
         var2 = "" + var1 + ' ';
      }

      return var2 + this.getTempUnitC(this.mContext);
   }

   private final void sendMediaInfo(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      CanbusMsgSender.sendMsg(new byte[]{22, 117, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)this.getD7()});
   }

   // $FF: synthetic method
   static void sendMediaInfo$default(MsgMgr var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, Object var9) {
      if ((var8 & 1) != 0) {
         var1 = 0;
      }

      if ((var8 & 2) != 0) {
         var2 = 0;
      }

      if ((var8 & 4) != 0) {
         var3 = 0;
      }

      if ((var8 & 8) != 0) {
         var4 = 0;
      }

      if ((var8 & 16) != 0) {
         var5 = 0;
      }

      if ((var8 & 32) != 0) {
         var6 = 0;
      }

      if ((var8 & 64) != 0) {
         var7 = 0;
      }

      var0.sendMediaInfo(var1, var2, var3, var4, var5, var6, var7);
   }

   private final void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
      int var1 = this.mCanBusInfoInt[3];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
         GeneralAirData.front_right_blow_head = true;
      }

      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private final void setAirData0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDorrTem());
   }

   private final void setDoorData0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private final void setSettingData0x40() {
      int var4 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2));
      int var1 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var3 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      List var5 = (List)(new ArrayList());
      var5.add(new SettingUpdateEntity(0, 0, var4));
      var5.add(new SettingUpdateEntity(0, 1, var1));
      var5.add(new SettingUpdateEntity(1, 0, var2));
      var5.add(new SettingUpdateEntity(1, 1, var3));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private final void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private final void steeringWheelAngle(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 7784, 13784, 16);
      this.updateParkUi((Bundle)null, var1);
   }

   private final void wheelSteerBtn() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 8) {
                        if (var1 == 9) {
                           this.realKeyLongClick2(this.mContext, 188);
                        }
                     } else {
                        this.realKeyLongClick2(this.mContext, 187);
                     }
                  } else {
                     this.realKeyLongClick2(this.mContext, 48);
                  }
               } else {
                  this.realKeyLongClick2(this.mContext, 47);
               }
            } else {
               this.realKeyLongClick2(this.mContext, 8);
            }
         } else {
            this.realKeyLongClick2(this.mContext, 7);
         }
      } else {
         this.realKeyLongClick2(this.mContext, 0);
      }

   }

   public void auxInInfoChange() {
      sendMediaInfo$default(this, 5, 0, 0, 0, 0, 0, 0, 126, (Object)null);
   }

   public void btMusicInfoChange() {
      sendMediaInfo$default(this, 6, 0, 0, 0, 0, 0, 0, 126, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "canbusInfo");
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 40) {
            if (var3 != 48) {
               if (var3 != 54) {
                  if (var3 != 64) {
                     if (var3 != 127) {
                        switch (var3) {
                           case 35:
                              if (this.isAirMsgRepeat(var2)) {
                                 return;
                              }

                              this.setAirData0x23();
                              break;
                           case 36:
                              this.rearRadarInfo(var1);
                              break;
                           case 37:
                              this.frontRadarInfo(var1);
                        }
                     } else {
                        this.setVersionInfo();
                     }
                  } else {
                     this.setSettingData0x40();
                  }
               } else {
                  this.setAirData0x36();
               }
            } else {
               this.steeringWheelAngle(var1);
            }
         } else {
            if (this.isDoorMsgRepeat(var2)) {
               return;
            }

            this.setDoorData0x28();
         }
      } else {
         this.wheelSteerBtn();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      this.mute = var2;
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, 118, (byte)var8, (byte)var6, (byte)var7});
   }

   public final boolean getMute() {
      return this.mute;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 == 9) {
         sendMediaInfo$default(this, 2, 0, 0, 0, 0, var6, var7, 30, (Object)null);
      } else if (var1 == 8) {
         sendMediaInfo$default(this, 3, 0, 0, 0, 0, var6, var7, 30, (Object)null);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      int var6;
      int var7;
      byte var8;
      label22: {
         Intrinsics.checkNotNullParameter(var2, "currBand");
         Intrinsics.checkNotNullParameter(var3, "currentFreq");
         Intrinsics.checkNotNullParameter(var4, "psName");
         Log.i("lyn", var2);
         var5 = var2.hashCode();
         if (var5 != 2092) {
            if (var5 != 2247) {
               if (var5 == 69707 && var2.equals("FM2")) {
                  var8 = 2;
                  var6 = (int)(Double.parseDouble(var3) * (double)100);
                  var7 = DataHandleUtils.getMsb(var6);
                  var6 = DataHandleUtils.getLsb(var6);
                  break label22;
               }
            } else if (var2.equals("FM")) {
               var8 = 1;
               var6 = (int)(Double.parseDouble(var3) * (double)100);
               var7 = DataHandleUtils.getMsb(var6);
               var6 = DataHandleUtils.getLsb(var6);
               break label22;
            }
         } else if (var2.equals("AM")) {
            var8 = 3;
            var6 = Integer.parseInt(var3);
            var7 = DataHandleUtils.getMsb(var6);
            var6 = DataHandleUtils.getLsb(var6);
            break label22;
         }

         throw new IllegalStateException("Unexpected value: " + var2);
      }

      sendMediaInfo$default(this, 1, var8, var7, var6, var1, 0, 0, 96, (Object)null);
   }

   public final void setMute(boolean var1) {
      this.mute = var1;
   }
}
