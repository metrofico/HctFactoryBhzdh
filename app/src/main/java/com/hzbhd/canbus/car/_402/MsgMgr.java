package com.hzbhd.canbus.car._402;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016JT\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001c\u0010\u001c\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0016JÄ\u0001\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020 2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010+2\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u00010+2\b\u00105\u001a\u0004\u0018\u00010+2\b\u00106\u001a\u0004\u0018\u00010+2\u0006\u00107\u001a\u00020\u0014H\u0016J6\u00108\u001a\u00020\n2\u0006\u00109\u001a\u00020\u00102\b\u0010:\u001a\u0004\u0018\u00010+2\b\u0010;\u001a\u0004\u0018\u00010+2\b\u0010<\u001a\u0004\u0018\u00010+2\u0006\u0010=\u001a\u00020\u0010H\u0016J\u001e\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u00102\f\b\u0002\u0010@\u001a\u00020\u0012\"\u00020 H\u0002J\b\u0010A\u001a\u00020\nH\u0002J\b\u0010B\u001a\u00020\nH\u0002J\b\u0010C\u001a\u00020\nH\u0002J\b\u0010D\u001a\u00020\nH\u0002J\b\u0010E\u001a\u00020\nH\u0002J\b\u0010F\u001a\u00020\nH\u0002J\b\u0010G\u001a\u00020\nH\u0002J\u0010\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006J"},
   d2 = {"Lcom/hzbhd/canbus/car/_402/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "auxInInfoChange", "btPhoneStatusInfoChange", "callStatus", "", "phoneNumber", "", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaData", "d0", "d1t12", "set0x11Data", "set0x12Data", "set0x1AData", "set0x21Data", "set0x26Data", "set0x31Data", "set0x41Data", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;

   private final void sendMediaData(int var1, byte... var2) {
      var2 = MsgMgrKt.restrict$default(var2, 13, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, var2));
   }

   // $FF: synthetic method
   static void sendMediaData$default(MsgMgr var0, int var1, byte[] var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = new byte[0];
      }

      var0.sendMediaData(var1, var2);
   }

   private final void set0x11Data() {
      Context var3 = InitUtilsKt.getMContext();
      int var2 = this.getFrame()[4];
      byte var1 = 3;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 5) {
                  if (var2 != 6) {
                     if (var2 != 8) {
                        if (var2 != 9) {
                           if (var2 != 11) {
                              var1 = 0;
                           } else {
                              var1 = 2;
                           }
                        } else {
                           var1 = 20;
                        }
                     } else {
                        var1 = 21;
                     }
                  } else {
                     var1 = 15;
                  }
               } else {
                  var1 = 14;
               }
            }
         } else {
            var1 = 8;
         }
      } else {
         var1 = 7;
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[5]);
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0x12Data() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   private final void set0x1AData() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      DriverDataPageUiSet.Page.Item var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getMsbLsbResult(this.getFrame()[5], this.getFrame()[6]) + " km/h");
      }

      var1 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
      if (var1 != null) {
         var1.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(this.getFrame()[11], this.getFrame()[12])));
      }

      this.updateDriveDataActivity((Bundle)null);
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(this.getFrame()[5], this.getFrame()[6]));
   }

   private final void set0x21Data() {
      Context var4 = InitUtilsKt.getMContext();
      int[] var5 = this.getFrame();
      byte var2 = 2;
      int var3 = var5[2];
      short var1;
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 6) {
                  label68: {
                     label58: {
                        if (var3 != 9) {
                           if (var3 == 18) {
                              break label58;
                           }

                           if (var3 == 32) {
                              var1 = 128;
                              break label68;
                           }

                           var1 = var2;
                           if (var3 == 36) {
                              break label68;
                           }

                           if (var3 == 43 || var3 == 47) {
                              break label58;
                           }

                           var1 = var2;
                           if (var3 == 51) {
                              break label68;
                           }

                           if (var3 != 62) {
                              if (var3 != 76) {
                                 if (var3 != 69) {
                                    if (var3 != 70) {
                                       switch (var3) {
                                          case 22:
                                             var1 = 49;
                                             break label68;
                                          case 23:
                                          case 27:
                                             var1 = 45;
                                             break label68;
                                          case 24:
                                          case 28:
                                             var1 = 46;
                                             break label68;
                                          case 25:
                                          case 29:
                                             var1 = 47;
                                             break label68;
                                          case 26:
                                          case 30:
                                             var1 = 48;
                                             break label68;
                                          default:
                                             var1 = 0;
                                       }
                                    } else {
                                       var1 = 8;
                                    }
                                 } else {
                                    var1 = 7;
                                 }
                              } else {
                                 var1 = 188;
                              }
                              break label68;
                           }
                        }

                        var1 = 3;
                        break label68;
                     }

                     var1 = 52;
                  }
               } else {
                  var1 = 50;
               }
            } else {
               var1 = 20;
            }
         } else {
            var1 = 21;
         }
      } else {
         var1 = 1;
      }

      this.realKeyLongClick1(var4, var1, this.getFrame()[3]);
   }

   private final void set0x26Data() {
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var1 = this.getFrame()[6];
      if (var1 != 1) {
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  if (var1 != 11) {
                     if (var1 == 12) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_auto_wind = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      var1 = this.getFrame()[8];
      String var2;
      if (var1 != 254) {
         if (var1 != 255) {
            var2 = (double)var1 * 0.5 + " °C";
         } else {
            var2 = "High";
         }
      } else {
         var2 = "Low";
      }

      GeneralAirData.front_left_temperature = var2;
      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0x41Data() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(6, set0x41Data$restrictValue(this.getFrame()[2]), set0x41Data$restrictValue(this.getFrame()[3]), set0x41Data$restrictValue(this.getFrame()[4]), set0x41Data$restrictValue(this.getFrame()[5]));
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private static final int set0x41Data$restrictValue(int var0) {
      int var1 = var0;
      if (var0 == 255) {
         var1 = 0;
      }

      return var1;
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._402.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 12});
   }

   public void auxInInfoChange() {
      sendMediaData$default(this, 12, (byte[])null, 2, (Object)null);
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      sendMediaData$default(this, 10, (byte[])null, 2, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var7 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var7, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var7);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 26) {
               if (var3 != 38) {
                  if (var3 != 49) {
                     if (var3 != 65) {
                        if (var3 != 240) {
                           if (var3 != 33) {
                              if (var3 == 34) {
                                 byte var6 = var2[3];
                                 byte var5 = com.hzbhd.canbus.car._369.MsgMgrKt.getLastKnobValue();
                                 int var4 = Math.abs(var6 - var5);
                                 var3 = this.getFrame()[2];
                                 if (var3 != 1) {
                                    if (var3 == 2) {
                                       if (var6 > var5) {
                                          DataHandleUtils.knob(var1, 46, var4);
                                       } else if (var6 < var5) {
                                          DataHandleUtils.knob(var1, 45, var4);
                                       }
                                    }
                                 } else if (var6 > var5) {
                                    DataHandleUtils.knob(var1, 7, var4);
                                 } else if (var6 < var5) {
                                    DataHandleUtils.knob(var1, 8, var4);
                                 }

                                 com.hzbhd.canbus.car._369.MsgMgrKt.setLastKnobValue(var2[3]);
                              }
                           } else {
                              this.set0x21Data();
                           }
                        } else {
                           this.set0xF0Data(var2);
                        }
                     } else {
                        this.set0x41Data();
                     }
                  } else {
                     this.set0x31Data();
                  }
               } else {
                  this.set0x26Data();
               }
            } else {
               this.set0x1AData();
            }
         } else {
            this.set0x12Data();
         }
      } else {
         this.set0x11Data();
      }

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

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         sendMediaData$default(this, 13, (byte[])null, 2, (Object)null);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      byte var6 = 3;
      if (var2 != null) {
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
               if (!var2.equals("FM3")) {
                  return;
               }
               break;
            default:
               return;
         }

         Intrinsics.checkNotNull(var3);
         byte[] var7 = com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(var1, var2, 8, var3);
         CanbusMsgSender.sendMsg(MsgMgrKt.restrict(ArraysKt.plus(new byte[]{22, -111, var6}, var7), 13, 32));
      }

   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
