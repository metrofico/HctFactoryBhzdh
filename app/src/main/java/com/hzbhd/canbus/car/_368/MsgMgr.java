package com.hzbhd.canbus.car._368;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016Jp\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0004H\u0016J\b\u0010)\u001a\u00020\u0014H\u0002J\b\u0010*\u001a\u00020\u0014H\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\b¨\u0006+"},
   d2 = {"Lcom/hzbhd/canbus/car/_368/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "color", "", "getColor", "()Ljava/lang/Integer;", "setColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastD2", "getLastD2", "setLastD2", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "set0x72Data", "set0x73Data", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   private Integer color;
   public int[] frame;
   private Integer lastD2;

   private final void set0x72Data() {
      int[] var5 = this.getFrame();
      byte var3 = 3;
      this.updateSpeedInfo(var5[3]);
      DriverDataPageUiSet.Page.Item var8 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("S362_vehicleSpeedInfo_1");
      if (var8 != null) {
         var8.setValue(this.getFrame()[3] + " km/h");
      }

      this.updateDriveDataActivity((Bundle)null);
      Integer var9 = this.lastD2;
      int var1 = this.getFrame()[4];
      boolean var2 = true;
      int var4;
      byte var6;
      if (var9 == null || var9 != var1) {
         Context var10 = InitUtilsKt.getMContext();
         var4 = this.getFrame()[4];
         var6 = 15;
         if (var4 != 1) {
            if (var4 != 2) {
               label110: {
                  if (var4 != 3) {
                     if (var4 == 5) {
                        var6 = 14;
                        break label110;
                     }

                     if (var4 == 6) {
                        break label110;
                     }

                     if (var4 != 15) {
                        if (var4 != 16) {
                           if (var4 != 23) {
                              switch (var4) {
                                 case 8:
                                    var6 = 21;
                                    break label110;
                                 case 9:
                                    var6 = 20;
                                    break label110;
                                 case 10:
                                    break;
                                 default:
                                    var6 = 0;
                                    break label110;
                              }
                           }

                           var6 = 2;
                           break label110;
                        }

                        var6 = 50;
                        break label110;
                     }
                  }

                  var6 = 3;
               }
            } else {
               var6 = 8;
            }
         } else {
            var6 = 7;
         }

         this.realKeyClick4(var10, var6);
         this.lastD2 = this.getFrame()[4];
      }

      MsgMgr var11 = (MsgMgr)this;
      var4 = this.getFrame()[5];
      boolean var7;
      if (var4 >= 0 && var4 < 21) {
         var7 = true;
      } else {
         var7 = false;
      }

      if (var7) {
         var6 = 1;
      } else {
         if (21 <= var4 && var4 < 41) {
            var7 = true;
         } else {
            var7 = false;
         }

         if (var7) {
            var6 = 2;
         } else {
            if (41 <= var4 && var4 < 61) {
               var7 = true;
            } else {
               var7 = false;
            }

            if (var7) {
               var6 = var3;
            } else {
               if (61 <= var4 && var4 < 81) {
                  var7 = true;
               } else {
                  var7 = false;
               }

               if (var7) {
                  var6 = 4;
               } else {
                  if (81 <= var4 && var4 < 101) {
                     var7 = var2;
                  } else {
                     var7 = false;
                  }

                  if (!var7) {
                     return;
                  }

                  var6 = 5;
               }
            }
         }
      }

      this.setBacklightLevel(var6);
   }

   private final void set0x73Data() {
      this.updateOutDoorTemp(InitUtilsKt.getMContext(), (double)this.getFrame()[8] * 0.5 - (double)40 + " °C");
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.getFrame()[9]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.getFrame()[9]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.getFrame()[9]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.getFrame()[9]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.getFrame()[9]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[9]);
      this.updateDoorView(InitUtilsKt.getMContext());
   }

   public void afterServiceNormalSetting(Context var1) {
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._368.UiMgr");
      AbstractUiMgr var3 = (AbstractUiMgr)((UiMgr)var2);
      InitUtilsKt.initSettingItemsIndexHashMap$default(var1, var3, (HashMap)null, 4, (Object)null);
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, var3, (HashMap)null, 4, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var4, "getByteArrayToIntArray(canbusInfo)");
      this.setFrame(var4);
      int var3 = this.getFrame()[1];
      if (var3 != 114) {
         if (var3 == 115) {
            this.set0x73Data();
         }
      } else {
         this.set0x72Data();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      byte var16 = (byte)var2;
      byte var19 = (byte)var3;
      byte var18 = (byte)var4;
      byte var15 = (byte)var5;
      byte var17 = (byte)var6;
      byte var14 = (byte)var7;
      Integer var20 = this.color;
      if (var20 != null) {
         CanbusMsgSender.sendMsg(new byte[]{22, -53, var16, var19, var18, var15, var17, var14, 0, 0, 0, (byte)var20});
      }

   }

   public final Integer getColor() {
      return this.color;
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

   public final Integer getLastD2() {
      return this.lastD2;
   }

   public final void setColor(Integer var1) {
      this.color = var1;
   }

   public final void setFrame(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.frame = var1;
   }

   public final void setLastD2(Integer var1) {
      this.lastD2 = var1;
   }
}
