package com.hzbhd.canbus.car._342;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   int A = 0;
   int a = 0;
   int b = 0;
   int c = 0;
   int d = 0;
   int differentId;
   int e = 0;
   private int eachId;
   int f = 0;
   int g = 0;
   int[] m0x60Data;
   int[] mAirData;
   private int mCameraStatus;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   public int nowTemLevel1 = 0;
   public int nowTemLevel2 = 0;
   private UiMgr uiMgr;

   private String getTemperature(int var1, double var2, double var4, String var6) {
      return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private boolean is0x60DataChange() {
      if (Arrays.equals(this.m0x60Data, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x60Data = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean resolveCycle(int var1) {
      return var1 != 1;
   }

   private String resolveVideoStatus(int var1) {
      return var1 == 0 ? this.mContext.getResources().getString(2131764092) : this.mContext.getResources().getString(2131764093);
   }

   private String resolveVideoStatus2(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131764069);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131764076);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131764077);
      } else if (var1 == 4) {
         var2 = this.mContext.getResources().getString(2131764080);
      } else if (var1 == 5) {
         var2 = this.mContext.getResources().getString(2131764083);
      } else if (var1 == 6) {
         var2 = this.mContext.getResources().getString(2131764086);
      } else if (var1 == 7) {
         var2 = this.mContext.getResources().getString(2131764070);
      } else if (var1 == 8) {
         var2 = this.mContext.getResources().getString(2131764071);
      } else if (var1 == 9) {
         var2 = this.mContext.getResources().getString(2131764072);
      } else if (var1 == 10) {
         var2 = this.mContext.getResources().getString(2131764073);
      } else if (var1 == 11) {
         var2 = this.mContext.getResources().getString(2131764074);
      } else if (var1 == 12) {
         var2 = this.mContext.getResources().getString(2131764075);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void sendCarType() {
      if (this.eachId == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getUiMgr(this.mContext).getCarModelData(0)});
      }

      if (this.eachId == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getUiMgr(this.mContext).getCarModelData(1)});
      }

      if (this.eachId == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getUiMgr(this.mContext).getCarModelData(2)});
      }

      if (this.eachId == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getUiMgr(this.mContext).getCarModelData(3)});
      }

      if (this.eachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getUiMgr(this.mContext).getCarModelData(4)});
      }

      if (this.eachId == 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 1, (byte)this.getUiMgr(this.mContext).getCarModelData(5)});
      }

   }

   private void setAVMState0x61() {
      this.a = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
      this.b = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
      this.c = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
      this.d = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
      this.e = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
      this.f = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
      this.g = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_0"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_342_setting_2"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_342_setting_2", "_342_setting_2_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAirInfo0x21() {
      if (!this.isAirDataChange()) {
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = this.resolveCycle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
         int var1 = this.mCanBusInfoInt[3];
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           GeneralAirData.front_right_blow_head = false;
                           GeneralAirData.front_right_blow_foot = false;
                           GeneralAirData.front_left_blow_head = false;
                           GeneralAirData.front_left_blow_foot = false;
                           GeneralAirData.front_defog = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_head = false;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_head = false;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_defog = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_head = false;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_defog = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_defog = false;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_left_blow_foot = false;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_right_blow_foot = false;
               GeneralAirData.front_defog = false;
            }
         } else {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_defog = false;
         }

         if (this.mCanBusInfoInt[4] == 0) {
            GeneralAirData.power = false;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
         if (this.eachId != 7) {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[5] + "Level";
            Log.e("lai3", "用了这个方法");
         } else {
            GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[5], 0.5, 0.0, "C");
            Log.e("lai4", "用了这个方法");
         }

         int[] var2 = this.mCanBusInfoInt;
         this.nowTemLevel1 = var2[5];
         var1 = var2[6];
         this.nowTemLevel2 = var1;
         if (this.eachId != 7) {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[6] + "level";
            Log.e("lai5", "用了这个方法");
         } else {
            GeneralAirData.front_right_temperature = this.getTemperature(var1, 0.5, 0.0, "C");
            Log.e("lai6", "用了这个方法");
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setPanelKeyInfo0x20() {
      int var1 = this.mCanBusInfoInt[2];
      switch (var1) {
         case 18:
            this.buttonKey(1);
            break;
         case 19:
            this.buttonKey(49);
            break;
         case 20:
            this.buttonKey(45);
            break;
         case 21:
            this.buttonKey(46);
            break;
         default:
            switch (var1) {
               case 32:
                  this.buttonKey(7);
                  break;
               case 33:
                  this.buttonKey(8);
                  break;
               case 34:
                  this.buttonKey(50);
                  break;
               case 35:
                  this.buttonKey(151);
                  break;
               case 36:
                  this.buttonKey(182);
                  break;
               case 37:
                  this.buttonKey(59);
                  break;
               case 38:
                  this.buttonKey(52);
                  break;
               case 39:
                  this.buttonKey(128);
                  break;
               case 40:
                  this.buttonKey(3);
                  break;
               case 41:
                  this.buttonKey(58);
                  break;
               case 42:
                  this.buttonKey(30);
                  break;
               case 43:
                  this.buttonKey(129);
                  break;
               case 44:
                  this.buttonKey(4);
            }
      }

   }

   private void setPanoramicData0x60(Context var1) {
      if (this.eachId == 1) {
         ArrayList var3 = new ArrayList();
         var3.add(new DriverUpdateEntity(0, 0, this.resolveVideoStatus(this.mCanBusInfoInt[2])));
         var3.add(new DriverUpdateEntity(0, 1, this.resolveVideoStatus2(this.mCanBusInfoInt[3])));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
         int[] var4 = this.mCanBusInfoInt;
         if (var4[2] == 1 && var4[3] != 0) {
            FutureUtil.instance.detectParkPanoramic(true, this.mCanBusInfoInt[2]);
         } else {
            FutureUtil.instance.detectParkPanoramic(false, this.mCanBusInfoInt[2]);
         }

         var3 = new ArrayList();
         int var2 = this.mCanBusInfoInt[3];
         if (var2 != 3) {
            if (var2 != 4) {
               if (var2 != 5) {
                  if (var2 == 6) {
                     var3.add(new PanoramicBtnUpdateEntity(0, false));
                     var3.add(new PanoramicBtnUpdateEntity(1, false));
                     var3.add(new PanoramicBtnUpdateEntity(2, false));
                     var3.add(new PanoramicBtnUpdateEntity(3, true));
                     var3.add(new PanoramicBtnUpdateEntity(4, false));
                  }
               } else {
                  var3.add(new PanoramicBtnUpdateEntity(0, false));
                  var3.add(new PanoramicBtnUpdateEntity(1, false));
                  var3.add(new PanoramicBtnUpdateEntity(2, true));
                  var3.add(new PanoramicBtnUpdateEntity(3, false));
                  var3.add(new PanoramicBtnUpdateEntity(4, false));
               }
            } else {
               var3.add(new PanoramicBtnUpdateEntity(0, false));
               var3.add(new PanoramicBtnUpdateEntity(1, true));
               var3.add(new PanoramicBtnUpdateEntity(2, false));
               var3.add(new PanoramicBtnUpdateEntity(3, false));
               var3.add(new PanoramicBtnUpdateEntity(4, false));
            }
         } else {
            var3.add(new PanoramicBtnUpdateEntity(0, true));
            var3.add(new PanoramicBtnUpdateEntity(1, false));
            var3.add(new PanoramicBtnUpdateEntity(2, false));
            var3.add(new PanoramicBtnUpdateEntity(3, false));
            var3.add(new PanoramicBtnUpdateEntity(4, false));
         }

         GeneralParkData.dataList = var3;
         this.updateParkUi((Bundle)null, this.mContext);
      }
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKeyInfo0x20() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.buttonKey(0);
            break;
         case 1:
            this.buttonKey(7);
            break;
         case 2:
            this.buttonKey(8);
            break;
         case 3:
            this.buttonKey(45);
            break;
         case 4:
            this.buttonKey(46);
            break;
         case 5:
            this.buttonKey(467);
            break;
         case 6:
            this.buttonKey(468);
            break;
         case 7:
            this.buttonKey(2);
            break;
         case 8:
            this.buttonKey(187);
         case 9:
         default:
            break;
         case 10:
            int var1 = this.eachId;
            if (var1 == 2 || var1 == 7) {
               var1 = this.A;
               if (var1 == 0) {
                  Log.d("lai", this.A + "进来了1");
                  this.forceReverse(this.mContext, true);
                  this.A = 1;
                  Log.d("lai", this.A + "进来了2");
               } else if (var1 == 1) {
                  Log.d("lai", this.A + "进来了3");
                  this.forceReverse(this.mContext, false);
                  this.A = 0;
                  Log.d("lai", this.A + "进来了4");
               }
            }
            break;
         case 11:
            this.buttonKey(49);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 33) {
            if (var3 != 36) {
               if (var3 != 48) {
                  if (var3 != 96) {
                     if (var3 == 97) {
                        if (this.eachId != 1) {
                           return;
                        }

                        this.setAVMState0x61();
                     }
                  } else {
                     if (this.eachId != 1) {
                        return;
                     }

                     this.setPanoramicData0x60(var1);
                  }
               } else {
                  this.setVersionInfo0x30();
               }
            } else {
               SingletonForKt.INSTANCE.setCarBodyData(this.mCanBusInfoInt);
               this.updateDoorView(var1);
            }
         } else {
            if (this.eachId == 1) {
               return;
            }

            this.setAirInfo0x21();
         }
      } else {
         this.setWheelKeyInfo0x20();
         if (this.eachId == 1) {
            return;
         }

         this.setPanelKeyInfo0x20();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.sendCarType();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.sendCarType();
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }
}
