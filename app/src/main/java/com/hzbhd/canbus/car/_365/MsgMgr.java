package com.hzbhd.canbus.car._365;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u001c\u0010\u0015\u001a\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nH\u0002JÄ\u0001\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u001d2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010)2\b\u0010+\u001a\u0004\u0018\u00010)2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020\u00192\u0006\u00101\u001a\u00020-2\b\u00102\u001a\u0004\u0018\u00010)2\b\u00103\u001a\u0004\u0018\u00010)2\b\u00104\u001a\u0004\u0018\u00010)2\u0006\u00105\u001a\u00020\u0019H\u0016J6\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020 2\b\u00108\u001a\u0004\u0018\u00010)2\b\u00109\u001a\u0004\u0018\u00010)2\b\u0010:\u001a\u0004\u0018\u00010)2\u0006\u0010;\u001a\u00020 H\u0016J\u0010\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020 H\u0002J\b\u0010>\u001a\u00020\u0013H\u0002J\b\u0010?\u001a\u00020\u0013H\u0002J\b\u0010@\u001a\u00020\u0013H\u0002J\b\u0010A\u001a\u00020\u0013H\u0002J\b\u0010B\u001a\u00020\u0013H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006C"},
   d2 = {"Lcom/hzbhd/canbus/car/_365/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "mAirData", "getMAirData", "setMAirData", "afterServiceNormalSetting", "", "auxInInfoChange", "canbusInfoChange", "canbusInfo", "", "isAirDataChange", "", "mCanBusInfoInt", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendXA1Data", "d1", "set0x11Data", "set0x12Data", "set0x24Data", "set0x31Data", "set0x41Data", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;
   private int[] mAirData = new int[0];

   private final boolean isAirDataChange(int[] var1) {
      if (Arrays.equals(this.mAirData, var1)) {
         return false;
      } else {
         this.mAirData = var1;
         return true;
      }
   }

   private final void sendXA1Data(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -95, -128, (byte)var1, 0});
   }

   private final void set0x11Data() {
      byte var1;
      boolean var2;
      byte var3;
      int var4;
      label97: {
         int[] var5 = this.getFrame();
         var3 = 3;
         this.updateSpeedInfo(var5[3]);
         MsgMgr var7 = (MsgMgr)this;
         Context var8 = this.getContext();
         var4 = this.getFrame()[4];
         var1 = 8;
         var2 = true;
         if (var4 != 0) {
            if (var4 != 1) {
               if (var4 != 2) {
                  if (var4 != 5) {
                     if (var4 != 6) {
                        if (var4 != 8) {
                           if (var4 != 9) {
                              if (var4 != 24) {
                                 break label97;
                              }

                              var1 = 3;
                           } else {
                              var1 = 46;
                           }
                        } else {
                           var1 = 45;
                        }
                     } else {
                        var1 = 15;
                     }
                  } else {
                     var1 = 14;
                  }
               }
            } else {
               var1 = 7;
            }
         } else {
            var1 = 0;
         }

         this.realKeyLongClick1(var8, var1, this.getFrame()[5]);
      }

      var4 = this.getFrame()[6];
      boolean var6;
      if (var4 >= 0 && var4 < 21) {
         var6 = true;
      } else {
         var6 = false;
      }

      if (var6) {
         var1 = 1;
      } else {
         if (21 <= var4 && var4 < 41) {
            var6 = true;
         } else {
            var6 = false;
         }

         if (var6) {
            var1 = 2;
         } else {
            if (41 <= var4 && var4 < 61) {
               var6 = true;
            } else {
               var6 = false;
            }

            if (var6) {
               var1 = var3;
            } else {
               if (61 <= var4 && var4 < 81) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               if (var6) {
                  var1 = 4;
               } else {
                  if (81 <= var4 && var4 < 101) {
                     var6 = var2;
                  } else {
                     var6 = false;
                  }

                  if (!var6) {
                     return;
                  }

                  var1 = 5;
               }
            }
         }
      }

      this.setBacklightLevel(var1);
   }

   private final void set0x12Data() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.getFrame()[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.getFrame()[4]);
      this.updateDoorView(this.getContext());
   }

   private final void set0x24Data() {
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.getFrame()[2]);
      GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.getFrame()[2]);
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 2) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac = var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.getFrame()[2], 0, 2) == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac_auto = var2;
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 7, 1);
      if (var1 != 0) {
         if (var1 != 1) {
            return;
         }

         GeneralAirData.right_set_seat_cold = true;
      } else {
         GeneralAirData.right_set_seat_heat = true;
      }

      var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 6, 1);
      if (var1 != 0) {
         if (var1 != 1) {
            return;
         }

         GeneralAirData.left_set_seat_cold = true;
      } else {
         GeneralAirData.left_set_seat_heat = true;
      }

      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      if (DataHandleUtils.getBoolBit3(this.getFrame()[3])) {
         var1 = 2;
      } else {
         var1 = GeneralAirData.in_out_auto_cycle;
      }

      GeneralAirData.in_out_auto_cycle = var1;
      GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2);
      GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2);
      GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2);
      MsgMgrKt.windDirectionInit();
      var1 = this.getFrame()[6];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 5) {
                  if (var1 != 6) {
                     switch (var1) {
                        case 11:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           break;
                        case 12:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_foot = true;
                           break;
                        case 13:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                           break;
                        case 14:
                           GeneralAirData.front_left_blow_window = true;
                           GeneralAirData.front_right_blow_window = true;
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                           GeneralAirData.front_left_blow_foot = true;
                           GeneralAirData.front_right_blow_foot = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_defog = true;
         }
      } else {
         GeneralAirData.front_left_auto_wind = true;
         GeneralAirData.front_right_auto_wind = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      var1 = this.getFrame()[8];
      String var4 = "High";
      String var3;
      if (var1 != 254) {
         if (var1 != 255) {
            var3 = (double)var1 * 0.5 + " °C";
         } else {
            var3 = "High";
         }
      } else {
         var3 = "Low";
      }

      GeneralAirData.front_left_temperature = var3;
      var1 = this.getFrame()[9];
      if (var1 != 254) {
         var3 = var4;
         if (var1 != 255) {
            var3 = (double)var1 * 0.5 + " °C";
         }
      } else {
         var3 = "Low";
      }

      GeneralAirData.front_right_temperature = var3;
      if (this.isAirDataChange(this.getFrame())) {
         this.updateAirActivity(this.getContext(), 1004);
      }

   }

   private final void set0x41Data() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      RadarInfoUtil.setRearRadarDistanceData(this.getFrame()[2], this.getFrame()[3], this.getFrame()[4], this.getFrame()[5]);
      RadarInfoUtil.setFrontRadarDistanceData(this.getFrame()[6], this.getFrame()[7], this.getFrame()[8], this.getFrame()[9]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   public void afterServiceNormalSetting(Context var1) {
      if (var1 != null) {
         this.setContext(var1);
         CanbusMsgSender.sendMsg(new byte[]{22, 38, 7, (byte)this.getCurrentCanDifferentId()});
      }
   }

   public void auxInInfoChange() {
      this.sendXA1Data(3);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var1);
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 36) {
               if (var3 != 49) {
                  if (var3 != 65) {
                     if (var3 == 175) {
                        var3 = this.getFrame()[3];
                        short var5;
                        if (var3 != 2) {
                           if (var3 != 3) {
                              if (var3 != 6) {
                                 if (var3 != 7) {
                                    switch (var3) {
                                       case 10:
                                          var5 = 76;
                                          break;
                                       case 11:
                                          var5 = 77;
                                          break;
                                       case 12:
                                          var5 = 68;
                                          break;
                                       default:
                                          var5 = 0;
                                    }
                                 } else {
                                    var5 = 188;
                                 }
                              } else {
                                 var5 = 139;
                              }
                           } else {
                              var5 = 141;
                           }
                        } else {
                           var5 = 130;
                        }

                        this.realKeyClick4(var1, var5);
                     }
                  } else {
                     this.set0x41Data();
                  }
               } else {
                  this.set0x31Data();
               }
            } else {
               this.set0x24Data();
            }
         } else {
            this.set0x12Data();
         }
      } else {
         this.set0x11Data();
      }

   }

   public final Context getContext() {
      Context var1 = this.context;
      if (var1 != null) {
         return var1;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("context");
         return null;
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

   public final int[] getMAirData() {
      return this.mAirData;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      if (var1 != 9) {
         this.sendXA1Data(6);
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      Intrinsics.checkNotNull(var2);
      CharSequence var7 = (CharSequence)var2;
      byte var6;
      if (StringsKt.contains$default(var7, (CharSequence)"FM", false, 2, (Object)null)) {
         var6 = 10;
      } else {
         if (!StringsKt.contains$default(var7, (CharSequence)"AM", false, 2, (Object)null)) {
            return;
         }

         var6 = 11;
      }

      this.sendXA1Data(var6);
   }

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setMAirData(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.mAirData = var1;
   }
}
