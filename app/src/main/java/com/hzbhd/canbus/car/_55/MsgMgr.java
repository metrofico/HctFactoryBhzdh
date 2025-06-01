package com.hzbhd.canbus.car._55;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b-\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\"\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010$\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010%\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010&\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u001c\u0010'\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010(\u001a\u0004\u0018\u00010 H\u0016J\b\u0010)\u001a\u00020\u001aH\u0002J\b\u0010*\u001a\u00020\u001aH\u0002Jp\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020-2\u0006\u00101\u001a\u00020-2\u0006\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020-2\u0006\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020\"2\u0006\u00109\u001a\u00020-H\u0016J\b\u0010:\u001a\u00020\u001aH\u0002J\b\u0010;\u001a\u00020\u001aH\u0002J\b\u0010<\u001a\u00020\u001aH\u0002J\b\u0010=\u001a\u00020\u001aH\u0002J\u000e\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\"J\u0018\u0010@\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\u0006\u0010A\u001a\u00020\"JÄ\u0001\u0010B\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020D2\u0006\u0010I\u001a\u00020D2\u0006\u0010J\u001a\u00020D2\u0006\u0010K\u001a\u00020D2\u0006\u0010L\u001a\u00020D2\u0006\u0010M\u001a\u00020D2\b\u0010N\u001a\u0004\u0018\u00010O2\b\u0010P\u001a\u0004\u0018\u00010O2\b\u0010Q\u001a\u0004\u0018\u00010O2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020D2\u0006\u0010U\u001a\u00020-2\u0006\u0010V\u001a\u00020\"2\u0006\u0010W\u001a\u00020S2\b\u0010X\u001a\u0004\u0018\u00010O2\b\u0010Y\u001a\u0004\u0018\u00010O2\b\u0010Z\u001a\u0004\u0018\u00010O2\u0006\u0010[\u001a\u00020\"H\u0016J\b\u0010\\\u001a\u00020\u001aH\u0002J\b\u0010]\u001a\u00020\u001aH\u0002J\u000e\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\"J\b\u0010`\u001a\u00020\u001aH\u0002J\b\u0010a\u001a\u00020\u001aH\u0002J\b\u0010b\u001a\u00020\u001aH\u0002J\b\u0010c\u001a\u00020\u001aH\u0002J\b\u0010d\u001a\u00020\u001aH\u0002J\b\u0010e\u001a\u00020\u001aH\u0002J6\u0010f\u001a\u00020\u001a2\u0006\u0010g\u001a\u00020-2\b\u0010h\u001a\u0004\u0018\u00010O2\b\u0010i\u001a\u0004\u0018\u00010O2\b\u0010j\u001a\u0004\u0018\u00010O2\u0006\u0010k\u001a\u00020-H\u0016J\b\u0010l\u001a\u00020\u001aH\u0002J\b\u0010m\u001a\u00020\u001aH\u0002J\b\u0010n\u001a\u00020\u001aH\u0002J \u0010o\u001a\u00020\u001a2\u0006\u0010p\u001a\u00020-2\u0006\u0010q\u001a\u00020 2\u0006\u0010r\u001a\u00020-H\u0002J\u001a\u0010s\u001a\u00020\u001a2\u0006\u0010p\u001a\u00020-2\b\b\u0002\u0010t\u001a\u00020 H\u0002J\b\u0010u\u001a\u00020\u001aH\u0002J\b\u0010v\u001a\u00020\u001aH\u0002J\u0010\u0010w\u001a\u00020\u001a2\u0006\u0010x\u001a\u00020 H\u0002J\b\u0010y\u001a\u00020\u001aH\u0002J\u0010\u0010z\u001a\u00020\u001a2\u0006\u0010{\u001a\u00020\"H\u0016J\u0006\u0010|\u001a\u00020\u001aJ\u0098\u0001\u0010}\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020D2\u0006\u0010I\u001a\u00020D2\u0006\u0010J\u001a\u00020D2\b\u0010K\u001a\u0004\u0018\u00010O2\u0006\u0010L\u001a\u00020D2\u0006\u0010M\u001a\u00020D2\b\u0010N\u001a\u0004\u0018\u00010O2\b\u0010P\u001a\u0004\u0018\u00010O2\b\u0010Q\u001a\u0004\u0018\u00010O2\u0006\u0010R\u001a\u00020-2\u0006\u0010~\u001a\u00020D2\u0006\u0010V\u001a\u00020\"2\u0006\u0010\u007f\u001a\u00020-H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0080\u0001"},
   d2 = {"Lcom/hzbhd/canbus/car/_55/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getMOriginalCarDevicePageUiSet", "()Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "mUiMgr", "Lcom/hzbhd/canbus/car/_55/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_55/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_55/UiMgr;)V", "afterServiceNormalSetting", "", "context", "airConditioning", "basicCarInfo", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "carDetailsInfo", "carInfo", "dateTimeRepCanbus", "bYearTotal", "", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "doorLock", "driverAssistanceSystemSetting", "drivingPosturePersonalization", "light", "mEnterOrExitAux", "bool", "mForceReverse", "isReverse", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "oilConsumption", "oilConsumptionHistory", "openRightCamera", "isOpen", "originalCarScreen", "originalVehiclePowerAmplifierStatus", "panelButton", "powerTailgateSettingStatus", "radar", "radioInfo", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "radioPreStoredStationInfo", "remoteControl", "requestSourceCut", "sendBtPhoneNumber", "d0", "d1t30", "d31", "sendVoiceSource", "d1t12", "set0x1FData", "set0xA4Data", "set0xF0Data", "bytes", "setDisplaySettingStatus", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSetting", "videoInfoChange", "playMode", "duration", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;
   public Context mContext;
   private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   public UiMgr mUiMgr;

   public MsgMgr() {
      OriginalCarDevicePageUiSet var1 = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getOriginalCarDevicePageUiSet(BaseUtil.INSTANCE.getContext());
      Intrinsics.checkNotNullExpressionValue(var1, "getCanUiMgr(context).get…rDevicePageUiSet(context)");
      this.mOriginalCarDevicePageUiSet = var1;
   }

   private final void airConditioning() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.getFrame()[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2);
      MsgMgrKt.windDirectionInit();
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 0, 4);
      if (var1 != 3) {
         if (var1 != 4) {
            if (var1 != 5) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     if (var1 == 10) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                     }
                  } else {
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
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 4, 4);
      if (var1 != 3) {
         if (var1 != 4) {
            if (var1 != 5) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     if (var1 == 10) {
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
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
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      var1 = this.getFrame()[8];
      String var3 = "HIGH";
      String var2;
      if (var1 != 254) {
         if (var1 != 255) {
            var2 = (double)var1 * 0.5 + " °C";
         } else {
            var2 = "HIGH";
         }
      } else {
         var2 = "LOW";
      }

      GeneralAirData.front_left_temperature = var2;
      var1 = this.getFrame()[9];
      if (var1 != 254) {
         var2 = var3;
         if (var1 != 255) {
            var2 = (double)var1 * 0.5 + " °C";
         }
      } else {
         var2 = "LOW";
      }

      GeneralAirData.front_right_temperature = var2;
      GeneralAirData.rear_wind_level = this.getFrame()[10];
      this.updateAirActivity(this.getMContext(), 1004);
   }

   private final void basicCarInfo() {
      int[] var5 = this.getFrame();
      byte var2 = 5;
      int var4 = var5[5];
      int var1 = this.getFrame()[4];
      boolean var3 = true;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        switch (var1) {
                           case 8:
                              this.realKeyLongClick1(this.getMContext(), 45, var4);
                              break;
                           case 9:
                              this.realKeyLongClick1(this.getMContext(), 46, var4);
                              break;
                           case 10:
                              this.realKeyLongClick1(this.getMContext(), 151, var4);
                              break;
                           case 11:
                              this.realKeyLongClick1(this.getMContext(), 2, var4);
                              break;
                           case 12:
                              this.realKeyLongClick1(this.getMContext(), 3, var4);
                              break;
                           case 13:
                              this.realKeyLongClick1(this.getMContext(), 45, var4);
                              break;
                           case 14:
                              this.realKeyLongClick1(this.getMContext(), 46, var4);
                              break;
                           case 15:
                              this.realKeyLongClick1(this.getMContext(), 49, var4);
                              break;
                           default:
                              switch (var1) {
                                 case 34:
                                    this.realKeyLongClick1(this.getMContext(), 3, var4);
                                    break;
                                 case 35:
                                    this.realKeyLongClick1(this.getMContext(), 76, var4);
                                    break;
                                 case 36:
                                    this.realKeyLongClick1(this.getMContext(), 77, var4);
                                    break;
                                 case 37:
                                    this.realKeyLongClick1(this.getMContext(), 139, var4);
                                    break;
                                 case 38:
                                    this.realKeyLongClick1(this.getMContext(), 3, var4);
                                    break;
                                 case 39:
                                    this.realKeyLongClick1(this.getMContext(), 3, var4);
                              }
                        }
                     } else {
                        this.realKeyLongClick1(this.getMContext(), 189, var4);
                     }
                  } else {
                     this.realKeyLongClick1(this.getMContext(), 14, var4);
                  }
               } else {
                  this.realKeyLongClick1(this.getMContext(), 187, var4);
               }
            } else {
               this.realKeyLongClick1(this.getMContext(), 8, var4);
            }
         } else {
            this.realKeyLongClick1(this.getMContext(), 7, var4);
         }
      } else {
         this.realKeyLongClick1(this.getMContext(), 0, var4);
      }

      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 5200, 16);
      this.updateParkUi((Bundle)null, this.getMContext());
      DriverDataPageUiSet.Page.Item var8 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S362_vehicleSpeedInfo_1");
      if (var8 != null) {
         var8.setValue(this.getFrame()[3] + " km/h");
      }

      this.updateDriveDataActivity((Bundle)null);
      MsgMgr var9 = (MsgMgr)this;
      var4 = this.getFrame()[7];
      boolean var6;
      if (var4 >= 0 && var4 < 21) {
         var6 = true;
      } else {
         var6 = false;
      }

      byte var7;
      if (var6) {
         var7 = 1;
      } else {
         if (21 <= var4 && var4 < 41) {
            var6 = true;
         } else {
            var6 = false;
         }

         if (var6) {
            var7 = 2;
         } else {
            if (41 <= var4 && var4 < 61) {
               var6 = true;
            } else {
               var6 = false;
            }

            if (var6) {
               var7 = 3;
            } else {
               if (61 <= var4 && var4 < 81) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               if (var6) {
                  var7 = 4;
               } else {
                  if (81 <= var4 && var4 < 101) {
                     var6 = var3;
                  } else {
                     var6 = false;
                  }

                  if (!var6) {
                     return;
                  }

                  var7 = var2;
               }
            }
         }
      }

      this.setBacklightLevel(var7);
   }

   private final void carDetailsInfo() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      this.updateDoorView(this.getMContext());
   }

   private final void carInfo() {
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_Speed_1");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[5]) + " rpm");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S55_Speed_2");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[7])));
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private final void doorLock() {
      SingletonForKt.INSTANCE.setLockData(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void driverAssistanceSystemSetting() {
      SingletonForKt.INSTANCE.setDrivingAssistance(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void drivingPosturePersonalization() {
      SingletonForKt.INSTANCE.setOtherData(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void light() {
      SingletonForKt.INSTANCE.setLightingData(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void oilConsumption() {
      SingletonForKt.INSTANCE.setOilConsumptionData(this.getFrame());
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void oilConsumptionHistory() {
      SingletonForKt.INSTANCE.setHistoryOilConsumptionData(this.getFrame());
      this.updateDriveDataActivity((Bundle)null);
   }

   private final void originalCarScreen() {
      SingletonForKt.INSTANCE.setOriginalCarData(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void originalVehiclePowerAmplifierStatus() {
      GeneralAmplifierData.volume = this.getFrame()[2];
      GeneralAmplifierData.frontRear = com.hzbhd.canbus.car._350.MsgMgrKt.reverse(this.getFrame()[4], 18) - 9;
      GeneralAmplifierData.leftRight = this.getFrame()[3] - 9;
      GeneralAmplifierData.bandBass = this.getFrame()[5];
      GeneralAmplifierData.bandTreble = this.getFrame()[7];
      GeneralAmplifierData.bandMiddle = this.getFrame()[6];
      this.updateAmplifierActivity((Bundle)null);
      SingletonForKt.INSTANCE.setAmplifierData(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void panelButton() {
      int var2 = this.getFrame()[3];
      int var1 = this.getFrame()[2];
      if (var1 != 0) {
         if (var1 != 69) {
            if (var1 != 70) {
               if (var1 != 87) {
                  if (var1 != 88) {
                     switch (var1) {
                        case 91:
                           this.realKeyLongClick1(this.getMContext(), 48, var2);
                           break;
                        case 92:
                           this.realKeyLongClick1(this.getMContext(), 47, var2);
                           break;
                        case 93:
                           this.realKeyLongClick1(this.getMContext(), 45, var2);
                           break;
                        case 94:
                           this.realKeyLongClick1(this.getMContext(), 46, var2);
                     }
                  } else {
                     this.realKeyLongClick1(this.getMContext(), 46, var2);
                  }
               } else {
                  this.realKeyLongClick1(this.getMContext(), 45, var2);
               }
            } else {
               this.realKeyLongClick1(this.getMContext(), 8, var2);
            }
         } else {
            this.realKeyLongClick1(this.getMContext(), 7, var2);
         }
      } else {
         this.realKeyLongClick1(this.getMContext(), 0, var2);
      }

   }

   private final void powerTailgateSettingStatus() {
      SingletonForKt.INSTANCE.setElectricDoor(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void radar() {
      byte var1 = 1;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setFrontRadarLocationData(4, radar$assignRadarProgress(this.getFrame()[6]), radar$assignRadarProgress(this.getFrame()[7]), radar$assignRadarProgress(this.getFrame()[8]), radar$assignRadarProgress(this.getFrame()[9]));
      RadarInfoUtil.setRearRadarLocationData(4, radar$assignRadarProgress(this.getFrame()[2]), radar$assignRadarProgress(this.getFrame()[3]), radar$assignRadarProgress(this.getFrame()[4]), radar$assignRadarProgress(this.getFrame()[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, BaseUtil.INSTANCE.getContext());
      SettingPageUiSet.ListBean.ItemListBean var2 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S314_radar_4");
      if (var2 != null) {
         if (this.getFrame()[12] != 1) {
            var1 = 0;
         }

         var2.setValue(Integer.valueOf(var1));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private static final int radar$assignRadarProgress(int var0) {
      byte var1 = 4;
      if (var0 != 1) {
         if (var0 != 2) {
            if (var0 != 3) {
               if (var0 != 4) {
                  var1 = 0;
               } else {
                  var1 = 1;
               }
            } else {
               var1 = 2;
            }
         } else {
            var1 = 3;
         }
      }

      return var1;
   }

   private final void radioInfo() {
      int var1 = this.getFrame()[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            return;
         }

         var2 = "AM";
      } else {
         var2 = "FM";
      }

      GeneralOriginalCarDeviceData.cdStatus = var2;
      var1 = this.getFrame()[6];
      String var3 = "";
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = this.getMContext().getString(2131768340);
            }
         } else {
            var2 = this.getMContext().getString(2131768339);
         }
      } else {
         var2 = this.getMContext().getString(2131768338);
      }

      GeneralOriginalCarDeviceData.runningState = var2;
      OriginalCarDevicePageUiSet var7 = this.mOriginalCarDevicePageUiSet;
      var7.setRowTopBtnAction(new String[]{"fm", "am", "scan", "refresh"});
      var7.setRowBottomBtnAction(new String[0]);
      List var5 = var7.getItems();
      var5.clear();
      StringBuilder var4 = (new StringBuilder()).append(DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[3]));
      String var6 = GeneralOriginalCarDeviceData.cdStatus;
      if (Intrinsics.areEqual((Object)var6, (Object)"FM")) {
         var2 = " MHZ";
      } else {
         var2 = var3;
         if (Intrinsics.areEqual((Object)var6, (Object)"AM")) {
            var2 = " KHZ";
         }
      }

      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", var4.append(var2).toString()));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_preset", String.valueOf(this.getFrame()[5])));
      Bundle var8 = new Bundle();
      var8.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var8);
   }

   private final void radioPreStoredStationInfo() {
      OriginalCarDevicePageUiSet var2 = this.mOriginalCarDevicePageUiSet;
      var2.setHaveSongList(true);
      var2.setSongListShowIndex(false);
      int var1 = this.getFrame()[2];
      List var3;
      if (var1 != 1) {
         if (var1 != 2) {
            return;
         }

         var3 = CollectionsKt.listOf(new SongListEntity[]{new SongListEntity("Preset 1: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[3]) + " MHz"), new SongListEntity("Preset 2: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[5]) + " MHz"), new SongListEntity("Preset 3: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[7]) + " MHz"), new SongListEntity("Preset 4: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[9]) + " MHz"), new SongListEntity("Preset 5: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[12], this.getFrame()[11]) + " MHz"), new SongListEntity("Preset 6: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[14], this.getFrame()[13]) + " MHz"), new SongListEntity("Preset 7: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[16], this.getFrame()[15]) + " MHz"), new SongListEntity("Preset 8: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[18], this.getFrame()[17]) + " MHz"), new SongListEntity("Preset 9: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[20], this.getFrame()[19]) + " MHz"), new SongListEntity("Preset 10: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[22], this.getFrame()[21]) + " MHz"), new SongListEntity("Preset 11: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[24], this.getFrame()[23]) + " MHz"), new SongListEntity("Preset 12: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[26], this.getFrame()[25]) + " MHz")});
      } else {
         var3 = CollectionsKt.listOf(new SongListEntity[]{new SongListEntity("Preset 1: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[4], this.getFrame()[3]) + " KHz"), new SongListEntity("Preset 2: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[6], this.getFrame()[5]) + " KHz"), new SongListEntity("Preset 3: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[7]) + " KHz"), new SongListEntity("Preset 4: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[10], this.getFrame()[9]) + " KHz"), new SongListEntity("Preset 5: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[12], this.getFrame()[11]) + " KHz"), new SongListEntity("Preset 6: " + DataHandleUtils.getMsbLsbResult(this.getFrame()[14], this.getFrame()[13]) + " KHz")});
      }

      GeneralOriginalCarDeviceData.songList = var3;
      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private final void remoteControl() {
      SingletonForKt.INSTANCE.setRemoteControlData(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   private final void requestSourceCut() {
      switch (this.getFrame()[2]) {
         case 32:
            this.realKeyClick4(BaseUtil.INSTANCE.getContext(), 77);
            break;
         case 33:
            this.realKeyClick4(BaseUtil.INSTANCE.getContext(), 76);
            break;
         case 34:
            this.realKeyClick4(BaseUtil.INSTANCE.getContext(), 139);
            break;
         case 35:
            this.realKeyClick4(BaseUtil.INSTANCE.getContext(), 140);
            break;
         case 36:
            this.realKeyClick4(BaseUtil.INSTANCE.getContext(), 141);
            break;
         case 37:
            this.realKeyClick4(BaseUtil.INSTANCE.getContext(), 53);
            break;
         default:
            return;
      }

   }

   private final void sendBtPhoneNumber(int var1, byte[] var2, int var3) {
      CanbusMsgSender.sendMsg(ArraysKt.plus(ArraysKt.plus(new byte[]{22, -25, (byte)var1}, var2), new byte[]{(byte)var3}));
   }

   private final void sendVoiceSource(int var1, byte[] var2) {
      var2 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict(ArraysKt.plus(new byte[]{(byte)var1}, var2), 13, 32);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -31}, var2));
   }

   // $FF: synthetic method
   static void sendVoiceSource$default(MsgMgr var0, int var1, byte[] var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = new byte[0];
      }

      var0.sendVoiceSource(var1, var2);
   }

   private final void set0x1FData() {
      GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 7) / 11 % 9;
      GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.getFrame()[3]);
      GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.getFrame()[3]);
      GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.getFrame()[3]);
      GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.getFrame()[3]);
      this.updateHybirdActivity((Bundle)null);
   }

   private final void set0xA4Data() {
      int var1 = this.getFrame()[12];
      String var3 = "";
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 6) {
                  if (var1 != 9) {
                     if (var1 != 12) {
                        if (var1 != 13) {
                           var2 = "";
                        } else {
                           var2 = "LOADING";
                        }
                     } else {
                        var2 = "EJECT";
                     }
                  } else {
                     var2 = "FR";
                  }
               } else {
                  var2 = "STOP";
               }
            } else {
               var2 = "FF";
            }
         } else {
            var2 = "PLAY";
         }
      } else {
         var2 = "PAUSE";
      }

      GeneralOriginalCarDeviceData.cdStatus = var2;
      var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 4);
      if (var1 != 13) {
         if (var1 != 14) {
            var2 = var3;
         } else {
            var2 = "Ipod";
         }
      } else {
         var2 = "USB";
      }

      GeneralOriginalCarDeviceData.runningState = var2;
      OriginalCarDevicePageUiSet var4 = this.mOriginalCarDevicePageUiSet;
      var4.setRowTopBtnAction(new String[0]);
      var4.setRowBottomBtnAction(new String[]{"up", "power", "down"});
      List var5 = var4.getItems();
      var5.clear();
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Current_track", String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[5], this.getFrame()[6]))));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Total_repertoire", String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[8], this.getFrame()[7]))));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_play_time", this.getFrame()[9] + " : " + this.getFrame()[10]));
      var5.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Playback_progress", this.getFrame()[11] + " %"));
      Bundle var6 = new Bundle();
      var6.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var6);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   private final void setDisplaySettingStatus() {
      SingletonForKt.INSTANCE.setOther2Data(this.getFrame());
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      if (var1 != null) {
         this.setMContext(var1);
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.UiMgr");
         this.setMUiMgr((UiMgr)var2);
         SingletonForKt.INSTANCE.init(var1, this.getMUiMgr());
      }

      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNull(var1);
      this.sendBtPhoneNumber(4, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var1, 30, 0, 4, (Object)null), 0);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNull(var1);
      this.sendBtPhoneNumber(1, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var1, 30, 0, 4, (Object)null), 0);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNull(var1);
      this.sendBtPhoneNumber(2, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var1, 30, 0, 4, (Object)null), 0);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      Intrinsics.checkNotNull(var1);
      this.sendBtPhoneNumber(3, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var1, 30, 0, 4, (Object)null), 0);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 22) {
               if (var3 != 23) {
                  if (var3 != 31) {
                     if (var3 != 33) {
                        if (var3 != 65) {
                           if (var3 != 117) {
                              if (var3 != 164) {
                                 if (var3 != 166) {
                                    if (var3 != 224) {
                                       if (var3 != 232) {
                                          if (var3 != 240) {
                                             if (var3 != 49) {
                                                if (var3 != 50) {
                                                   if (var3 != 132) {
                                                      if (var3 != 133) {
                                                         switch (var3) {
                                                            case 100:
                                                               this.drivingPosturePersonalization();
                                                               break;
                                                            case 101:
                                                               this.doorLock();
                                                               break;
                                                            case 102:
                                                               this.remoteControl();
                                                               break;
                                                            case 103:
                                                               this.light();
                                                               break;
                                                            case 104:
                                                               this.driverAssistanceSystemSetting();
                                                               break;
                                                            case 105:
                                                               this.setDisplaySettingStatus();
                                                         }
                                                      } else {
                                                         this.radioPreStoredStationInfo();
                                                      }
                                                   } else {
                                                      this.radioInfo();
                                                   }
                                                } else {
                                                   this.carInfo();
                                                }
                                             } else {
                                                this.airConditioning();
                                             }
                                          } else {
                                             Intrinsics.checkNotNull(var2);
                                             this.set0xF0Data(var2);
                                          }
                                       } else {
                                          this.originalCarScreen();
                                       }
                                    } else {
                                       this.requestSourceCut();
                                    }
                                 } else {
                                    this.originalVehiclePowerAmplifierStatus();
                                 }
                              } else {
                                 this.set0xA4Data();
                              }
                           } else {
                              this.powerTailgateSettingStatus();
                           }
                        } else {
                           this.radar();
                        }
                     } else {
                        this.panelButton();
                     }
                  } else {
                     this.set0x1FData();
                  }
               } else {
                  this.oilConsumptionHistory();
               }
            } else {
               this.oilConsumption();
            }
         } else {
            this.carDetailsInfo();
         }
      } else {
         this.basicCarInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      if (var10 != 0) {
         var1 = var8;
      } else {
         var1 = var5;
      }

      byte var14 = (byte)var1;
      byte var15 = (byte)var6;
      byte var16 = (byte)var7;
      CanbusMsgSender.sendMsg(new byte[]{22, -75, var14, var15, var16});
      if (var10 != 0) {
         var1 = var8;
      } else {
         var1 = var5;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var1, var15, var16, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, 0});
   }

   public final int[] getFrame() {
      int[] var1 = this.frame;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("frame");
         return null;
      }
   }

   public final Context getMContext() {
      Context var1 = this.mContext;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mContext");
         return null;
      }
   }

   public final OriginalCarDevicePageUiSet getMOriginalCarDevicePageUiSet() {
      return this.mOriginalCarDevicePageUiSet;
   }

   public final UiMgr getMUiMgr() {
      UiMgr var1 = this.mUiMgr;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         return null;
      }
   }

   public final void mEnterOrExitAux(boolean var1) {
      if (var1) {
         this.enterAuxIn2();
      } else {
         this.exitAuxIn2();
      }

   }

   public final void mForceReverse(Context var1, boolean var2) {
      super.forceReverse(var1, var2);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         var1 = 13;
      } else {
         var1 = 14;
      }

      sendVoiceSource$default(this, var1, (byte[])null, 2, (Object)null);
      Intrinsics.checkNotNull(var22);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(227, var22, Charsets.UTF_16BE, 32, 10);
      Intrinsics.checkNotNull(var23);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(226, var23, Charsets.UTF_16BE, 32, 10);
      Intrinsics.checkNotNull(var21);
      com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(226, var21, Charsets.UTF_16BE, 32, 10);
      byte[] var25 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(new byte[]{0, 0, 0, 0, var6, var7}, 16, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -104}, var25));
   }

   public final void openRightCamera(boolean var1) {
      this.switchFCamera(this.getMContext(), var1);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      if (var2 != null) {
         byte var6;
         switch (var2.hashCode()) {
            case 64901:
               if (!var2.equals("AM1")) {
                  return;
               }

               var6 = 4;
               break;
            case 64902:
               if (!var2.equals("AM2")) {
                  return;
               }

               var6 = 5;
               break;
            case 69706:
               if (!var2.equals("FM1")) {
                  return;
               }

               var6 = 1;
               break;
            case 69707:
               if (!var2.equals("FM2")) {
                  return;
               }

               var6 = 2;
               break;
            case 69708:
               if (var2.equals("FM3")) {
                  var6 = 3;
                  break;
               }

               return;
            default:
               return;
         }

         Intrinsics.checkNotNull(var3);
         this.sendVoiceSource(var6, com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(var1, var2, 8, var3));
      }

   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setMContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mContext = var1;
   }

   public final void setMUiMgr(UiMgr var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mUiMgr = var1;
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      sendVoiceSource$default(this, 0, (byte[])null, 2, (Object)null);
   }

   public final void updateSetting() {
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      if (var1 != 9) {
         var1 = 13;
      } else {
         var1 = 14;
      }

      sendVoiceSource$default(this, var1, (byte[])null, 2, (Object)null);
   }
}
