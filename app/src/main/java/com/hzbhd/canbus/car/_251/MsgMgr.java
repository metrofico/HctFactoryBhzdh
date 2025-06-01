package com.hzbhd.canbus.car._251;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\u0018\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\nH\u0016Jp\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0004H\u0016J\b\u0010)\u001a\u00020\u0015H\u0002J\b\u0010*\u001a\u00020\u0015H\u0002J\u0012\u0010+\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010,\u001a\u00020\u0015H\u0002J\u0010\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u0010/\u001a\u00020\u0015H\u0002J\u0010\u00100\u001a\u0002012\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u00102\u001a\u000201H\u0002J\b\u00103\u001a\u00020\u0015H\u0002J\b\u00104\u001a\u00020\u0015H\u0002J\b\u00105\u001a\u00020\u0015H\u0002J\b\u00106\u001a\u00020\u0015H\u0002J\b\u00107\u001a\u00020\u0015H\u0002J\b\u00108\u001a\u00020\u0015H\u0002J\b\u00109\u001a\u00020\u0015H\u0002J\u0010\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u000201H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\b¨\u0006<"},
   d2 = {"Lcom/hzbhd/canbus/car/_251/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "i", "", "getI", "()I", "setI", "(I)V", "mCanBusInfoByte", "", "mCanBusInfoInt", "", "mContext", "Landroid/content/Context;", "mUiMgr", "Lcom/hzbhd/canbus/car/_251/UiMgr;", "type", "getType", "setType", "afterServiceNormalSetting", "", "context", "anotherRearRadar", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "frontRadar", "information", "initCommand", "keyControl0x21", "realKeyClick", "value", "rearRadar", "resolveLeftAndRightTemp", "", "resolveOutDoorTem", "setAirData0x23", "setAirData0x36", "setDoorData0x28", "setPanoramaData", "setSettingData", "setTrackData", "setVersionInfo", "showDialog", "showContent", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private int i = 2;
   private byte[] mCanBusInfoByte = new byte[0];
   private int[] mCanBusInfoInt = new int[0];
   private Context mContext;
   private UiMgr mUiMgr;
   private int type = 1;

   // $FF: synthetic method
   public static void $r8$lambda$JpkgcVv0DgaFm7_rW5vuyKOpPG0(MsgMgr var0, String var1) {
      showDialog$lambda_6(var0, var1);
   }

   private final void anotherRearRadar() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void frontRadar() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(10, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void information() {
      if (this.mCanBusInfoInt[2] == 1) {
         information$setData("D251_OriginalCar_1", this.mCanBusInfoInt[3] - 40 + " °C");
         information$setData("D251_OriginalCar_2", this.mCanBusInfoInt[4] - 40 + " °C");
      }

      int[] var4;
      if (this.mCanBusInfoInt[2] == 2) {
         StringBuilder var2 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         information$setData("D251_Battery_1", var2.append((float)DataHandleUtils.getMsbLsbResult(var3[3], var3[4]) / 1000.0F).append(" V").toString());
         StringBuilder var6 = new StringBuilder();
         var4 = this.mCanBusInfoInt;
         information$setData("D251_Battery_2", var6.append((float)DataHandleUtils.getMsbLsbResult(var4[5], var4[6]) / 1000.0F).append(" V").toString());
         information$setData("D251_Battery_3", String.valueOf(this.mCanBusInfoInt[7]));
         information$setData("D251_Battery_4", String.valueOf(this.mCanBusInfoInt[8]));
         information$setData("D251_Battery_5", this.mCanBusInfoInt[9] - 50 + " °C");
         information$setData("D251_Battery_6", this.mCanBusInfoInt[10] - 50 + " °C");
         information$setData("D251_Battery_7", String.valueOf(this.mCanBusInfoInt[11]));
         information$setData("D251_Battery_8", String.valueOf(this.mCanBusInfoInt[12]));
         information$setData("D251_Battery_9", String.valueOf(this.mCanBusInfoInt[13]));
      }

      var4 = this.mCanBusInfoInt;
      if (var4[2] == 3) {
         int var1 = var4[3];
         String var7 = "----";
         String var5;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var5 = "----";
               } else {
                  var5 = "慢充中";
               }
            } else {
               var5 = "快充中";
            }
         } else {
            var5 = "放电中";
         }

         information$setData("D251_CarBody_1", var5);
         var1 = this.mCanBusInfoInt[4];
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     var5 = var7;
                  } else {
                     var5 = "充电完成";
                  }
               } else {
                  var5 = "未充电";
               }
            } else {
               var5 = "行驶充电";
            }
         } else {
            var5 = "停车充电";
         }

         information$setData("D251_CarBody_2", var5);
      }

      this.updateDriveDataActivity((Bundle)null);
   }

   private static final void information$setData(String var0, String var1) {
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get(var0);
      if (var2 != null) {
         var2.setValue(var1);
      }

   }

   private final void keyControl0x21() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 11) {
         if (var1 != 12) {
            if (var1 != 50) {
               if (var1 != 151) {
                  if (var1 != 153) {
                     switch (var1) {
                        case 0:
                           this.realKeyLongClick1(this.mContext, 0, var2[3]);
                           break;
                        case 1:
                           this.realKeyLongClick1(this.mContext, 7, var2[3]);
                           break;
                        case 2:
                           this.realKeyLongClick1(this.mContext, 8, var2[3]);
                           break;
                        case 3:
                           this.realKeyLongClick1(this.mContext, 14, var2[3]);
                           break;
                        case 4:
                           this.realKeyLongClick1(this.mContext, 15, var2[3]);
                           break;
                        case 5:
                           this.realKeyLongClick1(this.mContext, 188, var2[3]);
                           break;
                        case 6:
                           this.realKeyLongClick1(this.mContext, 3, var2[3]);
                           break;
                        case 7:
                           this.realKeyLongClick1(this.mContext, 2, var2[3]);
                           break;
                        default:
                           switch (var1) {
                              case 129:
                                 this.realKeyLongClick1(this.mContext, 52, var2[3]);
                                 break;
                              case 130:
                                 this.realKeyLongClick1(this.mContext, 50, var2[3]);
                                 break;
                              case 131:
                                 this.realKeyLongClick1(this.mContext, 59, var2[3]);
                                 break;
                              case 132:
                                 this.realKeyLongClick1(this.mContext, 182, var2[3]);
                                 break;
                              case 133:
                                 this.realKeyLongClick1(this.mContext, 47, var2[3]);
                                 break;
                              case 134:
                                 this.realKeyLongClick1(this.mContext, 76, var2[3]);
                                 break;
                              case 135:
                                 this.realKeyLongClick1(this.mContext, 1, var2[3]);
                                 break;
                              case 136:
                                 this.realKeyLongClick1(this.mContext, 48, var2[3]);
                                 break;
                              case 137:
                                 this.realKeyLongClick1(this.mContext, 77, var2[3]);
                                 break;
                              case 138:
                                 this.realKeyLongClick1(this.mContext, 59, var2[3]);
                                 break;
                              case 139:
                                 this.realKeyLongClick1(this.mContext, 68, var2[3]);
                                 break;
                              case 140:
                                 this.realKeyLongClick1(this.mContext, 58, var2[3]);
                                 break;
                              case 141:
                                 this.realKeyLongClick1(this.mContext, 4, var2[3]);
                                 break;
                              case 142:
                                 var1 = this.i++;
                                 if (var1 % 2 == 0) {
                                    this.forceReverse(this.mContext, true);
                                 } else {
                                    this.forceReverse(this.mContext, false);
                                 }
                                 break;
                              default:
                                 switch (var1) {
                                    case 144:
                                       this.realKeyLongClick1(this.mContext, 4113, var2[3]);
                                       break;
                                    case 145:
                                       this.realKeyLongClick1(this.mContext, 151, var2[3]);
                                       break;
                                    case 146:
                                       this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                       break;
                                    case 147:
                                       this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                       break;
                                    case 148:
                                       this.realKeyLongClick1(this.mContext, 47, var2[3]);
                                       break;
                                    case 149:
                                       this.realKeyLongClick1(this.mContext, 48, var2[3]);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 241:
                                             DataHandleUtils.knob(this.mContext, 8, var2[3]);
                                             break;
                                          case 242:
                                             DataHandleUtils.knob(this.mContext, 7, var2[3]);
                                             break;
                                          case 243:
                                             DataHandleUtils.knob(this.mContext, 47, var2[3]);
                                             break;
                                          case 244:
                                             DataHandleUtils.knob(this.mContext, 48, var2[3]);
                                       }
                                 }
                           }
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 95, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 3, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 128, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 46, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 45, var2[3]);
      }

   }

   private final void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
   }

   private final void rearRadar() {
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarDistanceData(var1[2], 0, 0, var1[3]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final String resolveLeftAndRightTemp(int var1) {
      String var4;
      if (var1 == 0) {
         var4 = "LOW";
      } else if (var1 == 255) {
         var4 = "HIG";
      } else {
         boolean var3 = true;
         boolean var2;
         if (18 <= var1 && var1 < 32) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            var4 = var1 + this.getTempUnitC(this.mContext);
         } else {
            if (124 <= var1 && var1 < 157) {
               var2 = var3;
            } else {
               var2 = false;
            }

            if (var2) {
               var4 = (double)(var1 - 124) * 0.5 + (double)16 + this.getTempUnitC(this.mContext);
            } else {
               var4 = "--";
            }
         }
      }

      return var4;
   }

   private final String resolveOutDoorTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2 = "" + '-' + var1;
      } else {
         var2 = var1 + "";
      }

      return var2 + this.getTempUnitC(this.mContext);
   }

   private final void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var4 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      boolean var2 = true;
      GeneralAirData.in_out_cycle = var4 ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      MsgMgrKt.windDirectionInit();
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
                     GeneralAirData.front_left_blow_window = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_left_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_blow_head = true;
      }

      label54: {
         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         MsgMgr var5 = (MsgMgr)this;
         int var3 = this.mCanBusInfoInt[5];
         String var7;
         if (var3 == 0) {
            var7 = "LOW";
         } else if (var3 == 255) {
            var7 = "HIG";
         } else {
            boolean var6;
            if (17 <= var3 && var3 < 32) {
               var6 = true;
            } else {
               var6 = false;
            }

            if (var6) {
               var7 = var3 + " °C";
            } else {
               if (124 <= var3 && var3 < 157) {
                  var6 = var2;
               } else {
                  var6 = false;
               }

               if (!var6) {
                  break label54;
               }

               var7 = (double)16 + (double)(var3 - 124) * 0.5 + " °C";
            }
         }

         GeneralAirData.front_left_temperature = var7;
      }

      GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
      this.updateAirActivity(this.mContext, 1004);
   }

   private final void setAirData0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private final void setDoorData0x28() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private final void setPanoramaData() {
      int var1 = this.mCanBusInfoInt[2];
      Integer var2;
      if (var1 != 5) {
         if (var1 != 6) {
            if (var1 != 7) {
               if (var1 != 8) {
                  var2 = null;
               } else {
                  var2 = 3;
               }
            } else {
               var2 = 2;
            }
         } else {
            var2 = 1;
         }
      } else {
         var2 = 0;
      }

      ArrayList var4 = new ArrayList(4);

      for(var1 = 0; var1 < 4; ++var1) {
         PanoramicBtnUpdateEntity var3;
         if (var2 != null && var1 == var2) {
            var3 = new PanoramicBtnUpdateEntity(var1, true);
         } else {
            var3 = new PanoramicBtnUpdateEntity(var1, false);
         }

         var4.add(var3);
      }

      GeneralParkData.dataList = (List)var4;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void setSettingData() {
      SettingPageUiSet.ListBean.ItemListBean var1;
      switch (this.mCanBusInfoInt[2]) {
         case 1:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_1");
            if (var1 != null) {
               var1.setValue(var1.getValueSrnArray().get(this.mCanBusInfoInt[3]));
            }
            break;
         case 2:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_2");
            if (var1 != null) {
               var1.setValue(this.mCanBusInfoInt[3]);
            }
            break;
         case 3:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_3");
            if (var1 != null) {
               var1.setValue(this.mCanBusInfoInt[3]);
            }
            break;
         case 4:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_4");
            if (var1 != null) {
               var1.setValue(var1.getValueSrnArray().get(this.mCanBusInfoInt[3]));
            }
            break;
         case 5:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_5");
            if (var1 != null) {
               var1.setValue(this.mCanBusInfoInt[3]);
            }
         case 6:
         default:
            break;
         case 7:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_6");
            if (var1 != null) {
               var1.setValue(var1.getValueSrnArray().get(this.mCanBusInfoInt[3]));
            }
            break;
         case 8:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_7");
            if (var1 != null) {
               var1.setValue(var1.getValueSrnArray().get(this.mCanBusInfoInt[3]));
            }
            break;
         case 9:
            var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S251_Other_8");
            if (var1 != null) {
               var1.setValue(this.mCanBusInfoInt[3]);
            }
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void setTrackData() {
      byte[] var1;
      if (this.getCurrentCanDifferentId() != 16 && this.getCurrentCanDifferentId() != 17 && this.getCurrentCanDifferentId() != 18) {
         var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 5480, 10960, 16);
      } else {
         var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 7818, 13814, 16);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private final void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private final void showDialog(String var1) {
      this.runOnUi(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
   }

   private static final void showDialog$lambda_6(MsgMgr var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "this$0");
      Intrinsics.checkNotNullParameter(var1, "$showContent");
      (new AlertView()).showDialog((Context)var0.getActivity(), var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      GeneralParkData.isShowDistanceAndShowLocationUi = true;
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._251.UiMgr");
      UiMgr var3 = (UiMgr)var2;
      this.mUiMgr = var3;
      UiMgr var4 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)var4, (HashMap)null, 4, (Object)null);
      var3 = this.mUiMgr;
      var4 = var3;
      if (var3 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
         var4 = null;
      }

      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)var4, (HashMap)null, 4, (Object)null);
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
         if (var3 != 48) {
            if (var3 != 127) {
               if (var3 != 54) {
                  if (var3 != 55) {
                     switch (var3) {
                        case 35:
                           this.setAirData0x23();
                           break;
                        case 36:
                           this.rearRadar();
                           break;
                        case 37:
                           this.anotherRearRadar();
                           break;
                        case 38:
                           this.frontRadar();
                           break;
                        case 39:
                           this.information();
                           break;
                        case 40:
                           this.setDoorData0x28();
                           break;
                        case 41:
                           this.setSettingData();
                     }
                  } else {
                     this.setPanoramaData();
                  }
               } else {
                  this.setAirData0x36();
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            this.setTrackData();
         }
      } else {
         this.keyControl0x21();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -78, (byte)(var7 << 2), (byte)(var6 << 2), (byte)(var8 << 3), (byte)(var4 << 3), (byte)(var3 << 4), (byte)(var2 << 2)});
   }

   public final int getI() {
      return this.i;
   }

   public final int getType() {
      return this.type;
   }

   public void initCommand(Context var1) {
      BuildersKt.launch$default((CoroutineScope)GlobalScope.INSTANCE, (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2(this, (Continuation)null) {
         int label;
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            return (Continuation)(new <anonymous constructor>(this.this$0, var2));
         }

         public final Object invoke(CoroutineScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            Continuation var5;
            <undefinedtype> var6;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
               var6 = this;
            } else {
               ResultKt.throwOnFailure(var1);
               var6 = this;
               var5 = (Continuation)this;
               this.label = 1;
               if (DelayKt.delay(1000L, var5) == var4) {
                  return var4;
               }
            }

            do {
               var2 = var6.this$0.getCurrentCanDifferentId();
               short var3;
               if (var2 != 9) {
                  if (var2 != 12) {
                     switch (var2) {
                        case 14:
                        case 15:
                           var3 = 160;
                           break;
                        case 16:
                        case 17:
                        case 18:
                           var3 = 161;
                           break;
                        default:
                           return Unit.INSTANCE;
                     }
                  } else {
                     var3 = 160;
                  }
               } else {
                  var3 = 160;
               }

               var2 = var6.this$0.getCurrentCanDifferentId();
               byte var7;
               if (var2 != 9) {
                  if (var2 != 12) {
                     switch (var2) {
                        case 14:
                        case 17:
                           var7 = 2;
                           break;
                        case 15:
                        case 18:
                           var7 = 3;
                           break;
                        case 16:
                           var7 = 1;
                           break;
                        default:
                           return Unit.INSTANCE;
                     }
                  } else {
                     var7 = 0;
                  }
               } else {
                  var7 = 1;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -18, (byte)var3, (byte)var7});
               var5 = (Continuation)var6;
               var6.label = 1;
            } while(DelayKt.delay(1000L, var5) != var4);

            return var4;
         }
      }), 3, (Object)null);
   }

   public final void setI(int var1) {
      this.i = var1;
   }

   public final void setType(int var1) {
      this.type = var1;
   }
}
