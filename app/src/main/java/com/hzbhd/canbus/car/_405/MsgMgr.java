package com.hzbhd.canbus.car._405;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._369.MsgMgrKt;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\nH\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u000fH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0019"},
   d2 = {"Lcom/hzbhd/canbus/car/_405/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0x41Data", "set0x45Data", "set0x87Data", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;

   private final void set0x11Data() {
      byte var1;
      Context var3;
      label28: {
         var3 = InitUtilsKt.getMContext();
         int var2 = this.getFrame()[4];
         var1 = 3;
         if (var2 != 0) {
            if (var2 == 1) {
               var1 = 7;
               break label28;
            }

            if (var2 == 2) {
               var1 = 8;
               break label28;
            }

            if (var2 == 3) {
               break label28;
            }

            switch (var2) {
               case 12:
                  var1 = 2;
                  break label28;
               case 13:
                  var1 = 45;
                  break label28;
               case 14:
                  var1 = 46;
                  break label28;
            }
         }

         var1 = 0;
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
      boolean var1 = true;
      GeneralDoorData.isShowSeatBelt = true;
      if (MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[4])) != 1) {
         var1 = false;
      }

      GeneralDoorData.isSeatBeltTie = var1;
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   private final void set0x21Data() {
      Context var3 = InitUtilsKt.getMContext();
      int var2 = this.getFrame()[2];
      short var1 = 1;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 9) {
                  if (var2 != 51) {
                     if (var2 != 69) {
                        if (var2 != 70) {
                           var1 = 0;
                        } else {
                           var1 = 8;
                        }
                     } else {
                        var1 = 7;
                     }
                  } else {
                     var1 = 4112;
                  }
               } else {
                  var1 = 3;
               }
            } else {
               var1 = 20;
            }
         } else {
            var1 = 21;
         }
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[3]);
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      int var1 = this.getFrame()[2];
      boolean var3 = false;
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac = var2;
      var2 = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 4, 1) == 0) {
         var2 = true;
      }

      GeneralAirData.in_out_cycle = var2;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      var1 = this.getFrame()[6];
      if (var1 != 2) {
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  if (var1 == 12) {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
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
         GeneralAirData.front_left_blow_window = true;
      }

      GeneralAirData.front_wind_level = this.getFrame()[7];
      GeneralAirData.front_left_temperature = "Level " + this.getFrame()[8];
      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0x41Data() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(this.getFrame()[2]), set0x41Data$restrictValue(this.getFrame()[3]), set0x41Data$restrictValue(this.getFrame()[4]), set0x41Data$restrictValue(this.getFrame()[5]));
      RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(this.getFrame()[6]), set0x41Data$restrictValue(this.getFrame()[7]), set0x41Data$restrictValue(this.getFrame()[8]), set0x41Data$restrictValue(this.getFrame()[9]));
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

   private final void set0x45Data() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.getFrame()[3], 0, 2);
      Integer var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = null;
            } else {
               var2 = 2;
            }
         } else {
            var2 = 1;
         }
      } else {
         var2 = 0;
      }

      ArrayList var4 = new ArrayList(3);

      for(var1 = 0; var1 < 3; ++var1) {
         PanoramicBtnUpdateEntity var3;
         if (var2 != null && var1 == var2) {
            var3 = new PanoramicBtnUpdateEntity(var1, true);
         } else {
            var3 = new PanoramicBtnUpdateEntity(var1, false);
         }

         var4.add(var3);
      }

      GeneralParkData.dataList = (List)var4;
      this.updateParkUi((Bundle)null, InitUtilsKt.getMContext());
   }

   private final void set0x87Data() {
      SettingPageUiSet.ListBean.ItemListBean var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_1");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 6, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_2");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[5], 3, 3)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_3");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(this.getFrame()[5])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_4");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[5])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_5");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_6");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(this.getFrame()[6])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_7");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 6, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_8");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(this.getFrame()[7])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_9");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 2, 3)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_10");
      if (var1 != null) {
         var1.setValue(var1.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(this.getFrame()[7], 0, 2)));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_11");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(this.getFrame()[8])));
      }

      var1 = (SettingPageUiSet.ListBean.ItemListBean)InitUtilsKt.getMSettingItemIndex().get("S405_x87_12");
      if (var1 != null) {
         var1.setValue(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(this.getFrame()[8])));
      }

      this.updateSettingActivity((Bundle)null);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._405.UiMgr");
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 19});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 49) {
                  if (var3 != 65) {
                     if (var3 != 69) {
                        if (var3 != 135) {
                           if (var3 == 240) {
                              this.set0xF0Data(var2);
                           }
                        } else {
                           this.set0x87Data();
                        }
                     } else {
                        this.set0x45Data();
                     }
                  } else {
                     this.set0x41Data();
                  }
               } else {
                  this.set0x31Data();
               }
            } else {
               this.set0x21Data();
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

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }
}
