package com.hzbhd.canbus.car._406;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._369.MsgMgrKt;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.TrackInfoUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\b\u0010\u0014\u001a\u00020\fH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0011H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"},
   d2 = {"Lcom/hzbhd/canbus/car/_406/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "lastKnobValue", "", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "canbusInfoChange", "canbusInfo", "", "set0x11Data", "set0x12Data", "set0x21Data", "set0x31Data", "set0xF0Data", "bytes", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr extends AbstractMsgMgr {
   public int[] frame;
   private byte lastKnobValue;

   private final void set0x11Data() {
      Context var3 = InitUtilsKt.getMContext();
      int var2 = this.getFrame()[4];
      short var1 = 3;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               switch (var2) {
                  case 12:
                     var1 = 2;
                     break;
                  case 13:
                     var1 = 465;
                     break;
                  case 14:
                     var1 = 466;
                     break;
                  default:
                     var1 = 0;
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
      int[] var4 = this.getFrame();
      short var1 = 2;
      int var2 = var4[2];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 != 36) {
                  if (var2 != 37) {
                     if (var2 != 60) {
                        if (var2 != 61) {
                           if (var2 != 66) {
                              if (var2 != 67) {
                                 if (var2 != 97) {
                                    if (var2 != 98) {
                                       label103: {
                                          label71:
                                          switch (var2) {
                                             case 5:
                                                var1 = 187;
                                                break label103;
                                             case 17:
                                                var1 = 31;
                                                break label103;
                                             case 22:
                                             case 85:
                                                var1 = 49;
                                                break label103;
                                             case 29:
                                                break;
                                             case 32:
                                                var1 = 128;
                                                break label103;
                                             case 43:
                                                var1 = 52;
                                                break label103;
                                             case 55:
                                                var1 = 58;
                                                break label103;
                                             case 57:
                                                var1 = 182;
                                                break label103;
                                             case 94:
                                                var1 = 75;
                                                break label103;
                                             default:
                                                switch (var2) {
                                                   case 8:
                                                      var1 = 130;
                                                      break label103;
                                                   case 9:
                                                      var1 = 3;
                                                      break label103;
                                                   case 10:
                                                      var1 = 33;
                                                      break label103;
                                                   case 11:
                                                      var1 = 34;
                                                      break label103;
                                                   case 12:
                                                      var1 = 35;
                                                      break label103;
                                                   case 13:
                                                      var1 = 36;
                                                      break label103;
                                                   case 14:
                                                      var1 = 37;
                                                      break label103;
                                                   case 15:
                                                      var1 = 38;
                                                      break label103;
                                                   default:
                                                      switch (var2) {
                                                         case 69:
                                                            var1 = 7;
                                                            break label103;
                                                         case 70:
                                                            var1 = 8;
                                                            break label103;
                                                         case 71:
                                                            var1 = 76;
                                                            break label103;
                                                         case 72:
                                                            var1 = 77;
                                                            break label103;
                                                         default:
                                                            switch (var2) {
                                                               case 75:
                                                                  var1 = 62;
                                                                  break label103;
                                                               case 76:
                                                                  var1 = 188;
                                                                  break label103;
                                                               case 77:
                                                                  break label71;
                                                               case 78:
                                                                  break;
                                                               default:
                                                                  var1 = 0;
                                                                  break label103;
                                                            }
                                                      }
                                                }
                                             case 27:
                                                var1 = 47;
                                                break label103;
                                          }

                                          var1 = 48;
                                       }
                                    } else {
                                       var1 = 148;
                                    }
                                 } else {
                                    var1 = 196;
                                 }
                              } else {
                                 var1 = 30;
                              }
                           } else {
                              var1 = 4;
                           }
                        } else {
                           var1 = 139;
                        }
                     } else {
                        var1 = 205;
                     }
                  } else {
                     var1 = 27;
                  }
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

      this.realKeyLongClick1(var3, var1, this.getFrame()[3]);
   }

   private final void set0x31Data() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.getFrame()[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.getFrame()[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.getFrame()[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.getFrame()[4]);
      com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
      int var1 = this.getFrame()[6];
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

      GeneralAirData.front_wind_level = this.getFrame()[7];
      this.updateOutDoorTemp(InitUtilsKt.getMContext(), this.getFrame()[13] - 40 + " °C");
      this.updateAirActivity(InitUtilsKt.getMContext(), 1004);
   }

   private final void set0xF0Data(byte[] var1) {
      this.updateVersionInfo(InitUtilsKt.getMContext(), this.getVersionStr(var1));
   }

   public void afterServiceNormalSetting(Context var1) {
      Intrinsics.checkNotNull(var1);
      InitUtilsKt.setMContext(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentCanDifferentId(), 19});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      Intrinsics.checkNotNull(var2);
      int[] var7 = this.getByteArrayToIntArray(var2);
      Intrinsics.checkNotNullExpressionValue(var7, "getByteArrayToIntArray(canbusInfo!!)");
      this.setFrame(var7);
      int var3 = this.getFrame()[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 49) {
                     if (var3 == 240) {
                        this.set0xF0Data(var2);
                     }
                  } else {
                     this.set0x31Data();
                  }
               } else {
                  byte var6 = var2[3];
                  byte var8 = this.lastKnobValue;
                  int var4 = Math.abs(var6 - var8);
                  int var5 = this.getFrame()[2];
                  if (var5 != 1) {
                     if (var5 == 2) {
                        if (var6 > var8) {
                           DataHandleUtils.knob(var1, 46, var4);
                        } else if (var6 < var8) {
                           DataHandleUtils.knob(var1, 45, var4);
                        }
                     }
                  } else if (var6 > var8) {
                     DataHandleUtils.knob(var1, 7, var4);
                  } else if (var6 < var8) {
                     DataHandleUtils.knob(var1, 8, var4);
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
