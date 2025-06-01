package com.hzbhd.canbus.car._366;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010\u0013\u001a\u00020\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0015H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"},
   d2 = {"Lcom/hzbhd/canbus/car/_366/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastKnobValue", "", "afterServiceNormalSetting", "", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x21Data", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public Context context;
   public int[] frame;
   private byte lastKnobValue;

   private final void set0x11Data() {
      DriverDataPageUiSet.Page.Item var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D_Speed_1");
      short var1 = 3;
      if (var2 != null) {
         var2.setValue(this.getFrame()[3] + " km/h");
      }

      var2 = (DriverDataPageUiSet.Page.Item)InitUtilsKt.getMDrivingItemIndex().get("D_Speed_2");
      if (var2 != null) {
         var2.setValue(String.valueOf(this.getFrame()[10] * 256 + this.getFrame()[11]));
      }

      this.updateDriveDataActivity((Bundle)null);
      Context var3 = this.getContext();
      switch (this.getFrame()[4]) {
         case 0:
         case 7:
         default:
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
            var1 = 201;
            break;
         case 5:
            var1 = 14;
            break;
         case 6:
            var1 = 15;
            break;
         case 8:
            var1 = 45;
            break;
         case 9:
            var1 = 46;
            break;
         case 10:
            var1 = 2;
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
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.getFrame()[4]);
      this.updateDoorView(this.getContext());
   }

   private final void set0x21Data() {
      Context var3 = this.getContext();
      int[] var4 = this.getFrame();
      short var1 = 2;
      int var2 = var4[2];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 42) {
                  if (var2 != 43) {
                     switch (var2) {
                        case 6:
                           var1 = 50;
                           break;
                        case 9:
                        case 95:
                           var1 = 3;
                           break;
                        case 16:
                           var1 = 95;
                           break;
                        case 32:
                           var1 = 128;
                           break;
                        case 36:
                           var1 = 130;
                           break;
                        case 48:
                           var1 = 68;
                           break;
                        case 57:
                           var1 = 182;
                        case 59:
                           break;
                        case 66:
                           var1 = 4;
                           break;
                        case 75:
                           var1 = 76;
                           break;
                        default:
                           switch (var2) {
                              case 51:
                                 var1 = 4112;
                                 break;
                              case 52:
                                 var1 = 14;
                                 break;
                              case 53:
                                 var1 = 15;
                                 break;
                              default:
                                 var1 = 0;
                           }
                     }
                  } else {
                     var1 = 52;
                  }
               } else {
                  var1 = 49;
               }
            } else {
               var1 = 46;
            }
         } else {
            var1 = 45;
         }
      } else {
         var1 = 1;
      }

      this.realKeyLongClick1(var3, var1, this.getFrame()[3]);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNull(var1);
      this.setContext(var1);
      UiMgrInterface var2 = UiMgrFactory.getCanUiMgr(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.canbus.car._366.UiMgr");
      InitUtilsKt.initDrivingItemsIndexHashMap$default(var1, (AbstractUiMgr)((UiMgr)var2), (HashMap)null, 4, (Object)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var7 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var7, "getByteArrayToIntArray(canbusInfo)");
      this.setFrame(var7);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 == 240) {
                     this.set0xF0Data(var2);
                  }
               } else {
                  byte var6 = var2[3];
                  byte var5 = this.lastKnobValue;
                  var3 = Math.abs(var6 - var5);
                  int var4 = this.getFrame()[2];
                  if (var4 != 1) {
                     if (var4 == 2) {
                        if (var6 > var5) {
                           DataHandleUtils.knob(var1, 46, var3);
                        } else if (var6 < var5) {
                           DataHandleUtils.knob(var1, 45, var3);
                        }
                     }
                  } else if (var6 > var5) {
                     DataHandleUtils.knob(var1, 7, var3);
                  } else if (var6 < var5) {
                     DataHandleUtils.knob(var1, 8, var3);
                  }

                  this.lastKnobValue = var2[3];
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
}
