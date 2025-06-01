package com.hzbhd.canbus.car._275;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final boolean $assertionsDisabled = false;
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static boolean isWarnFirst;
   private static int outDoorTemp;
   private final String _275_IS_OUT_DOOR_TEMP = "_275_is_out_door_temp";
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   private String[] arr4 = new String[10];
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusWarnInfoCopy;
   private Context mContext;
   private List mTire0 = new ArrayList();
   private List mTire1 = new ArrayList();
   private List mTire2 = new ArrayList();
   private List mTire3 = new ArrayList();
   private List mTire4 = new ArrayList();
   int nowState0;
   int nowState1;
   int nowState2;
   int nowState3;
   int nowState4;
   private List tyreInfoList = new ArrayList();

   private int getTireStatus(int var1) {
      return var1 != 0 && var1 != 1 ? 1 : 0;
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isOnlyOutDoorDataChange() {
      boolean var1;
      if (SharePreUtil.getFloatValue(this.mContext, "_275_is_out_door_temp", 0.0F) != (float)outDoorTemp) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isWarningMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusWarnInfoCopy)) {
         return true;
      } else {
         this.mCanBusWarnInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isWarnFirst) {
            isWarnFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 == 12) {
                                 this.realKeyClick1(2);
                              }
                           } else {
                              this.realKeyClick1(20);
                           }
                        } else {
                           this.realKeyClick1(21);
                        }
                     } else {
                        this.realKeyClick1(15);
                     }
                  } else {
                     this.realKeyClick1(14);
                  }
               } else {
                  this.realKeyClick1(3);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private static List mergeList(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void operationTireInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      String[] var3;
      StringBuilder var4;
      String[] var5;
      StringBuilder var6;
      int[] var8;
      if (var1 == 0) {
         var1 = var2[3];
         if (var1 == 0) {
            var3 = this.arr0;
            var4 = new StringBuilder();
            var2 = this.mCanBusInfoInt;
            var3[2] = var4.append(var2[4] * 256 + var2[5]).append("Kpa").toString();
         } else if (var1 == 1) {
            var5 = this.arr0;
            var6 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var5[1] = var6.append(var8[4] * 256 + var8[5] - 40).append(this.getTempUnitC(this.mContext)).toString();
         } else if (var1 == 2) {
            this.arr0[0] = this.tireWarningTest(var2[5]);
            this.nowState0 = this.getTireStatus(this.mCanBusInfoInt[5]);
         }
      } else {
         int[] var7;
         if (var1 == 1) {
            var1 = var2[3];
            if (var1 == 0) {
               var3 = this.arr1;
               var4 = new StringBuilder();
               var2 = this.mCanBusInfoInt;
               var3[2] = var4.append(var2[4] * 256 + var2[5]).append("Kpa").toString();
            } else if (var1 == 1) {
               var5 = this.arr1;
               var4 = new StringBuilder();
               var7 = this.mCanBusInfoInt;
               var5[1] = var4.append(var7[4] * 256 + var7[5] - 40).append(this.getTempUnitC(this.mContext)).toString();
            } else if (var1 == 2) {
               this.arr1[0] = this.tireWarningTest(var2[5]);
               this.nowState1 = this.getTireStatus(this.mCanBusInfoInt[5]);
            }
         } else if (var1 == 2) {
            var1 = var2[3];
            if (var1 == 0) {
               var5 = this.arr2;
               var6 = new StringBuilder();
               var8 = this.mCanBusInfoInt;
               var5[2] = var6.append(var8[4] * 256 + var8[5]).append("Kpa").toString();
            } else if (var1 == 1) {
               var3 = this.arr2;
               var4 = new StringBuilder();
               var2 = this.mCanBusInfoInt;
               var3[1] = var4.append(var2[4] * 256 + var2[5] - 40).append(this.getTempUnitC(this.mContext)).toString();
            } else if (var1 == 2) {
               this.arr2[0] = this.tireWarningTest(var2[5]);
               this.nowState2 = this.getTireStatus(this.mCanBusInfoInt[5]);
            }
         } else if (var1 == 3) {
            var1 = var2[3];
            if (var1 == 0) {
               var3 = this.arr3;
               var4 = new StringBuilder();
               var2 = this.mCanBusInfoInt;
               var3[2] = var4.append(var2[4] * 256 + var2[5]).append("Kpa").toString();
            } else if (var1 == 1) {
               var5 = this.arr3;
               var6 = new StringBuilder();
               var8 = this.mCanBusInfoInt;
               var5[1] = var6.append(var8[4] * 256 + var8[5] - 40).append(this.getTempUnitC(this.mContext)).toString();
            } else if (var1 == 2) {
               this.arr3[0] = this.tireWarningTest(var2[5]);
               this.nowState3 = this.getTireStatus(this.mCanBusInfoInt[5]);
            }
         } else if (var1 == 4) {
            var1 = var2[3];
            if (var1 == 0) {
               String[] var10 = this.arr4;
               StringBuilder var9 = new StringBuilder();
               var7 = this.mCanBusInfoInt;
               var10[2] = var9.append(var7[4] * 256 + var7[5]).append("Kpa").toString();
            } else if (var1 == 1) {
               var5 = this.arr4;
               var4 = new StringBuilder();
               var7 = this.mCanBusInfoInt;
               var5[1] = var4.append(var7[4] * 256 + var7[5] - 40).append(this.getTempUnitC(this.mContext)).toString();
            } else if (var1 == 2) {
               this.arr4[0] = this.tireWarningTest(var2[5]);
               this.nowState4 = this.getTireStatus(this.mCanBusInfoInt[5]);
            }
         }
      }

   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[4], var3[5]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      return (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[6];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_left_auto_wind = false;
      GeneralAirData.front_right_auto_wind = false;
      GeneralAirData.auto = false;
      if (var1 != 1) {
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  switch (var1) {
                     case 11:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 12:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 13:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 14:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         GeneralAirData.front_left_auto_wind = true;
         GeneralAirData.front_right_auto_wind = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
      outDoorTemp = this.mCanBusInfoInt[13];
      if (this.isOnlyOutDoorDataChange()) {
         SharePreUtil.setFloatValue(this.mContext, "_275_is_out_door_temp", (float)outDoorTemp);
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setDoorData() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSubShowSeatBelt = true;
      if (!((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getDoorSwapFront() && !((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      } else {
         GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
         GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      }

      this.updateDoorView(this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void tireTest0x37() {
      this.operationTireInfo();
      this.tyreInfoList.add(new TireUpdateEntity(0, this.nowState0, this.arr0));
      this.tyreInfoList.add(new TireUpdateEntity(1, this.nowState1, this.arr1));
      this.tyreInfoList.add(new TireUpdateEntity(2, this.nowState2, this.arr2));
      this.tyreInfoList.add(new TireUpdateEntity(3, this.nowState3, this.arr3));
      this.tyreInfoList.add(new TireUpdateEntity(4, this.nowState4, this.arr4));
      GeneralTireData.dataList = this.tyreInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private String tireWarningTest(int var1) {
      String var2;
      switch (var1) {
         case 1:
            var2 = this.mContext.getResources().getString(2131760221);
            break;
         case 2:
            var2 = this.mContext.getResources().getString(2131760222);
            break;
         case 3:
            var2 = this.mContext.getResources().getString(2131760223);
            break;
         case 4:
            var2 = this.mContext.getResources().getString(2131760224);
            break;
         case 5:
            var2 = this.mContext.getResources().getString(2131760225);
            break;
         case 6:
            var2 = this.mContext.getResources().getString(2131760226);
            break;
         case 7:
            var2 = this.mContext.getResources().getString(2131760227);
            break;
         case 8:
            var2 = this.mContext.getResources().getString(2131760228);
            break;
         default:
            var2 = this.mContext.getResources().getString(2131769909);
      }

      return var2;
   }

   private void warning0x72() {
      ArrayList var3 = new ArrayList();
      String[] var2 = new String[8];

      for(int var1 = 0; var1 < 8; ++var1) {
         var2[var1] = this.mContext.getResources().getString((new int[]{2131760229, 2131760230, 2131760231, 2131760232, 2131760233, 2131760234, 2131760235, 2131760236})[var1]);
         if (DataHandleUtils.getBoolBit(var1, this.mCanBusInfoInt[4])) {
            var3.add(new WarningEntity(var2[var1]));
         }
      }

      GeneralWarningDataData.dataList = var3;
      this.updateWarningActivity((Bundle)null);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);

      Exception var10000;
      label122: {
         int[] var4;
         boolean var10001;
         try {
            this.mCanBusInfoByte = var2;
            var4 = this.getByteArrayToIntArray(var2);
            this.mCanBusInfoInt = var4;
            this.mContext = var1;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
            break label122;
         }

         int var3 = var4[1];
         if (var3 == 17) {
            try {
               this.keyControl0x11();
               return;
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
            }
         } else if (var3 != 18) {
            if (var3 != 49) {
               if (var3 != 55) {
                  if (var3 != 114) {
                     if (var3 != 240) {
                        return;
                     }

                     try {
                        this.setVersionInfo();
                        return;
                     } catch (Exception var5) {
                        var10000 = var5;
                        var10001 = false;
                     }
                  } else {
                     label125: {
                        try {
                           if (this.isWarningMsgReturn(var2)) {
                              return;
                           }
                        } catch (Exception var9) {
                           var10000 = var9;
                           var10001 = false;
                           break label125;
                        }

                        label81: {
                           try {
                              Intent var17 = new Intent(this.mContext, WarningActivity.class);
                              var17.addFlags(268435456);
                              if (this.mCanBusInfoInt[4] != 0) {
                                 this.mContext.startActivity(var17);
                                 break label81;
                              }
                           } catch (Exception var8) {
                              var10000 = var8;
                              var10001 = false;
                              break label125;
                           }

                           try {
                              Log.d("cwh", "0");
                              if (SystemUtil.isForeground(this.mContext, WarningActivity.class.getName())) {
                                 Log.d("cwh", "1");
                                 this.finishActivity();
                              }
                           } catch (Exception var7) {
                              var10000 = var7;
                              var10001 = false;
                              break label125;
                           }
                        }

                        try {
                           this.warning0x72();
                           return;
                        } catch (Exception var6) {
                           var10000 = var6;
                           var10001 = false;
                        }
                     }
                  }
               } else {
                  try {
                     this.tireTest0x37();
                     return;
                  } catch (Exception var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               }
            } else {
               label128: {
                  try {
                     if (this.isAirMsgReturn(var2)) {
                        return;
                     }
                  } catch (Exception var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label128;
                  }

                  try {
                     this.setAirData();
                     return;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                  }
               }
            }
         } else {
            label127: {
               try {
                  if (this.isDoorMsgReturn(var2)) {
                     return;
                  }
               } catch (Exception var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label127;
               }

               try {
                  this.setDoorData();
                  return;
               } catch (Exception var12) {
                  var10000 = var12;
                  var10001 = false;
               }
            }
         }
      }

      Exception var18 = var10000;
      var18.printStackTrace();
      Log.d("cwh", "e = " + var18);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, (byte)this.getCurrentEachCanId()});
   }
}
