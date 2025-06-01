package com.hzbhd.canbus.car._385;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr uiMgr;

   private String ResolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "LOW_TEMP";
      }

      if (var1 == 255) {
         var2 = "HIGH_TEMP";
      }

      return var2;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void setAirInfo0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0) {
         if (var1 != 3) {
            if (var1 != 12) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_foot = false;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = false;
         }
      } else {
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_window = false;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCameraStatus0xD1() {
      boolean var1 = SystemUtil.isForeground(this.mContext, Constant.FCameraActivity.getClassName());
      this.switchFCamera(this.mContext, var1 ^ true);
   }

   private void setCarModel0x26() {
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 57) {
                           switch (var1) {
                              case 12:
                                 this.realKeyClick0x11(2);
                                 break;
                              case 13:
                                 this.realKeyClick0x11(45);
                                 break;
                              case 14:
                                 this.realKeyClick0x11(46);
                           }
                        } else {
                           this.setCameraStatus0xD1();
                        }
                     } else {
                        this.realKeyClick0x11(15);
                     }
                  } else {
                     this.realKeyClick0x11(14);
                  }
               } else {
                  this.realKeyClick0x11(3);
               }
            } else {
               this.realKeyClick0x11(8);
            }
         } else {
            this.realKeyClick0x11(7);
         }
      } else {
         this.realKeyClick0x11(0);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 38) {
            if (var3 != 49) {
               if (var3 == 240) {
                  this.setVersionInfo0xF0();
               }
            } else {
               this.setAirInfo0x31();
            }
         } else {
            this.setCarModel0x26();
         }
      } else {
         this.setWheelKey0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).sendCarModel();
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }
}
