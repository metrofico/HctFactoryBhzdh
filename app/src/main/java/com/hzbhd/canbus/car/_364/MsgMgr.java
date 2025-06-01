package com.hzbhd.canbus.car._364;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010\u0011\u001a\u00020\u00102\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0013H\u0002J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006 "},
   d2 = {"Lcom/hzbhd/canbus/car/_364/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x31Data", "set0x35Data", "set0x41Data", "set0x90Data", "set0xB1Data", "set0xF0Data", "bytes", "updateSettingActivity", "bundle", "Landroid/os/Bundle;", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;

   private final void set0x11Data() {
      int[] var2 = this.getFrame();
      short var1 = 3;
      this.updateSpeedInfo(var2[3]);
      Context var3 = this.getContext();
      switch (this.getFrame()[4]) {
         case 0:
            var1 = 0;
            break;
         case 1:
            var1 = 7;
            break;
         case 2:
            var1 = 8;
         case 3:
            break;
         case 4:
         case 7:
         default:
            return;
         case 5:
            var1 = 14;
            break;
         case 6:
            var1 = 15;
            break;
         case 8:
         case 12:
            var1 = 46;
            break;
         case 9:
         case 11:
            var1 = 45;
            break;
         case 10:
            var1 = 2;
            break;
         case 13:
            var1 = 134;
            break;
         case 14:
            var1 = 60;
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[5]);
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)this.getFrame()[9], (byte)this.getFrame()[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void set0x12Data() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[4]);
      this.updateDoorView(this.getContext());
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      int var1 = this.getFrame()[2];
      boolean var2 = false;
      boolean var4;
      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      GeneralAirData.ac = var4;
      GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.getFrame()[3]);
      GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit4(this.getFrame()[3]);
      if (DataHandleUtils.getBoolBit3(this.getFrame()[3])) {
         var1 = 2;
      } else {
         var1 = GeneralAirData.in_out_auto_cycle;
      }

      GeneralAirData.in_out_auto_cycle = var1;
      GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.getFrame()[4]);
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

      int var3 = this.getFrame()[7];
      boolean var7;
      if (var3 >= 0 && var3 < 19) {
         var7 = true;
      } else {
         var7 = false;
      }

      if (var7) {
         GeneralAirData.front_wind_level = var3;
      } else if (var3 == 19) {
         GeneralAirData.front_auto_wind_speed = true;
      }

      var1 = this.getFrame()[8];
      String var6 = "High";
      String var5;
      if (var1 != 254) {
         if (var1 != 255) {
            var5 = (double)var1 * 0.5 + " °C";
         } else {
            var5 = "High";
         }
      } else {
         var5 = "Low";
      }

      GeneralAirData.front_left_temperature = var5;
      var1 = this.getFrame()[9];
      if (var1 != 254) {
         if (var1 != 255) {
            var5 = (double)var1 * 0.5 + " °C";
         } else {
            var5 = "High";
         }
      } else {
         var5 = "Low";
      }

      GeneralAirData.front_right_temperature = var5;
      var1 = this.getFrame()[10];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  GeneralAirData.rear_left_blow_head = true;
                  GeneralAirData.rear_right_blow_head = true;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_right_blow_head = true;
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_foot = true;
            }
         } else {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
         }
      } else {
         GeneralAirData.rear_left_auto_wind = true;
         GeneralAirData.rear_right_auto_wind = true;
      }

      var3 = this.getFrame()[11];
      if (var3 >= 0) {
         var7 = var2;
         if (var3 < 5) {
            var7 = true;
         }
      } else {
         var7 = var2;
      }

      if (var7) {
         GeneralAirData.rear_wind_level = var3;
      } else if (var3 == 5) {
         GeneralAirData.rear_auto_wind_speed = true;
      }

      var1 = this.getFrame()[12];
      if (var1 != 254) {
         var5 = var6;
         if (var1 != 255) {
            var5 = (double)var1 * 0.5 + " °C";
         }
      } else {
         var5 = "Low";
      }

      GeneralAirData.rear_left_temperature = var5;
      this.updateOutDoorTemp(this.getContext(), (double)this.getFrame()[13] * 0.5 - (double)40 + " °C");
      this.updateAirActivity(this.getContext(), 1004);
      this.updateAirActivity(this.getContext(), 1003);
   }

   private final void set0x35Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 6, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 4, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_3");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 2, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_4");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_5");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 7, 1));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_6");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 6, 1));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_7");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 5, 1));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_8");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 4, 1));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_9");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 2, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_10");
      if (var1 != null) {
         var1.setValue(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 1, 1));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_11");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 0, 1)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_12");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 7, 1)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S364_AIR_13");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[6], 5, 2)));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0x41Data() {
      GeneralParkData.isShowDistanceNotShowLocationUi = true;
      RadarInfoUtil.setRearRadarDistanceData(this.getFrame()[2], this.getFrame()[3], this.getFrame()[4], this.getFrame()[5]);
      RadarInfoUtil.setFrontRadarDistanceData(this.getFrame()[6], this.getFrame()[7], this.getFrame()[8], this.getFrame()[9]);
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.getContext());
   }

   private final void set0x90Data() {
   }

   private final void set0xB1Data() {
      if (this.getFrame()[2] == 0) {
         this.startMainActivity(InitUtilsKt.getMContext());
      } else {
         GeneralOnStartData.mOnStarStatus = this.getFrame()[2] + 1;
         int var1 = this.getFrame()[3];
         String var2;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     var2 = "";
                  } else {
                     var2 = "ROADSIDE ASSISTANCE";
                  }
               } else {
                  var2 = "EMERGENCY";
               }
            } else {
               var2 = "COLLISION";
            }
         } else {
            var2 = "NORMAL";
         }

         GeneralOnStartData.mOnStarCallType = var2;
         GeneralOnStartData.mOnStarCallMuteSt = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[4], 0, 1);
         this.updateOnStarActivity(1005);
      }
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      if (var1 != null) {
         this.setContext(var1);
         UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._364.UiMgr");
         UiMgr var3 = (UiMgr)var2;
         SelectCanTypeUtil.enableApp(var1, Constant.OnStarActivity);
         InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)var3, (HashMap)null, 4, (Object)null);
      }
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
            if (var3 != 49) {
               if (var3 != 53) {
                  if (var3 != 65) {
                     if (var3 != 144) {
                        if (var3 != 177) {
                           if (var3 == 240) {
                              this.set0xF0Data(var2);
                           }
                        } else {
                           this.set0xB1Data();
                        }
                     } else {
                        this.set0x90Data();
                     }
                  } else {
                     this.set0x41Data();
                  }
               } else {
                  this.set0x35Data();
               }
            } else {
               this.set0x31Data();
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

   public final void setContext(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.context = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public void updateSettingActivity(Bundle var1) {
      super.updateSettingActivity(var1);
   }
}
