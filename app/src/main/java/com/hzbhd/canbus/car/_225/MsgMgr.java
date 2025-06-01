package com.hzbhd.canbus.car._225;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   public static String INTENT_KEY_POSITION;
   public static String SHARE_AIR_SET;
   public static String UPDATE_SETTING_ACTION;
   private BroadcastReceiver mBroadcastReceiver;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mIsFirstTime;
   private Resources resource;

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var5 = new byte[var4];

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var3 < var2) {
            var5[var3] = var1[var3];
         } else {
            var5[var3] = var1[var3 + 1];
         }
      }

      return var5;
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private String getAcDiagnosticInfoItem12(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            var2 = "";
         } else {
            var2 = this.resource.getString(2131767714);
         }
      } else {
         var2 = this.resource.getString(2131767713);
      }

      return var2;
   }

   private String getAcDiagnosticInfoItem345(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               var2 = "";
            } else {
               var2 = this.resource.getString(2131767717);
            }
         } else {
            var2 = this.resource.getString(2131767716);
         }
      } else {
         var2 = this.resource.getString(2131767715);
      }

      return var2;
   }

   private String getAcDiagnosticInfoItem6(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = this.resource.getString(2131767718);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveDeiagnosticInfo(int var1, int var2, int var3, int var4, String var5) {
      if (var1 == var4) {
         var5 = "Er";
      } else if (var1 >= var2 && var1 <= var3) {
         var5 = var1 + var5;
      } else {
         var5 = "";
      }

      return var5;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (1 == var1) {
         var2 = "LO";
      } else if (15 == var1) {
         var2 = "HI";
      } else if (var1 > 1 && var1 < 15) {
         var2 = var1 + 17 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var1 = 64 - var1;
         if (var1 > 0 && var1 < 41) {
            var2 = "-" + var1 + this.getTempUnitC(this.mContext);
         } else if (var1 == 41) {
            var2 = "Er";
         } else {
            var2 = this.mContext.getString(2131769395);
         }
      } else {
         var1 = this.mCanBusInfoInt[2];
         if (var1 >= 0 && var1 <= 85) {
            var2 = "" + var1 + this.getTempUnitC(this.mContext);
         } else {
            var2 = this.mContext.getString(2131769395);
         }
      }

      return var2;
   }

   private void setAirDiagnosisInfo0x02() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
      var1.add(new DriverUpdateEntity(0, 1, this.getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
      var1.add(new DriverUpdateEntity(0, 2, this.getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
      var1.add(new DriverUpdateEntity(0, 3, this.getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
      var1.add(new DriverUpdateEntity(0, 4, this.getAcDiagnosticInfoItem345(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
      var1.add(new DriverUpdateEntity(0, 5, this.getAcDiagnosticInfoItem345(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
      var1.add(new DriverUpdateEntity(0, 6, this.getAcDiagnosticInfoItem345(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2))));
      var1.add(new DriverUpdateEntity(0, 7, this.getAcDiagnosticInfoItem6(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
      var1.add(new DriverUpdateEntity(1, 0, this.resolveDeiagnosticInfo(this.mCanBusInfoInt[4], 0, 100, 101, "%")));
      var1.add(new DriverUpdateEntity(1, 1, this.resolveDeiagnosticInfo(this.mCanBusInfoInt[5], 0, 100, 101, "%")));
      var1.add(new DriverUpdateEntity(1, 2, this.resolveDeiagnosticInfo(this.mCanBusInfoInt[6], 0, 109, 110, " Kcal/m².h")));
      var1.add(new DriverUpdateEntity(1, 3, this.resolveDeiagnosticInfo(this.mCanBusInfoInt[7], 0, 125, 255, "℃")));
      var1.add(new DriverUpdateEntity(1, 4, this.resolveDeiagnosticInfo(this.mCanBusInfoInt[8], 0, 125, 255, "℃")));
      var1.add(new DriverUpdateEntity(1, 5, this.resolveDeiagnosticInfo(this.mCanBusInfoInt[9], 0, 130, 255, "℃")));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setAirInfo0x01() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               GeneralAirData.ac_auto = true;
            }
         } else {
            GeneralAirData.ac = true;
            GeneralAirData.ac_auto = false;
         }
      } else {
         GeneralAirData.ac = false;
         GeneralAirData.ac_auto = false;
      }

      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]) ^ true;
      GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
      GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
      GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      if (!GeneralAirData.front_auto_wind_speed) {
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
      }

      boolean var2;
      if (GeneralAirData.front_wind_level > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.power = var2;
      this.cleanAllBlow();
      if (!GeneralAirData.front_auto_wind_model) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 3);
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }
      }

      GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4));
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setOutDoorTem() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setVehicleDoorInfo0x24() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setVehicleRunInfo0x65() {
      ArrayList var3 = new ArrayList();
      int[] var4 = this.mCanBusInfoInt;
      int var1 = var4[2];
      int var2 = var4[3];
      var3.add(new DriverUpdateEntity(2, 0, var1 * 256 + var2 + " r/min"));
      var4 = this.mCanBusInfoInt;
      var2 = var4[4];
      var1 = var4[5];
      var3.add(new DriverUpdateEntity(2, 1, var2 * 256 + var1 + " km/h"));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;
      this.resource = var1.getResources();
      int[] var4 = this.mCanBusInfoInt;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 11) {
               if (var3 != 36) {
                  if (var3 != 48) {
                     if (var3 == 101) {
                        this.setVehicleRunInfo0x65();
                     }
                  } else {
                     this.setVersionInfo0x30();
                  }
               } else {
                  if (this.isDoorMsgRepeat(this.mCanBusInfoByte)) {
                     return;
                  }

                  this.setVehicleDoorInfo0x24();
               }
            } else {
               this.updateSpeedInfo(var4[2]);
            }
         } else {
            this.setAirDiagnosisInfo0x02();
         }
      } else {
         byte[] var5 = this.bytesExpectOneByte(this.mCanBusInfoByte, 2);
         this.setOutDoorTem();
         if (this.isAirMsgRepeat(var5)) {
            return;
         }

         if (this.mIsFirstTime) {
            this.mIsFirstTime = false;
            return;
         }

         this.setAirInfo0x01();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      BroadcastReceiver var2 = new BroadcastReceiver(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onReceive(Context var1, Intent var2) {
            int var3 = var2.getIntExtra(MsgMgr.INTENT_KEY_POSITION, 0);
            this.this$0.setAirSet(var3);
         }
      };
      this.mBroadcastReceiver = var2;
      var1.registerReceiver(var2, new IntentFilter(UPDATE_SETTING_ACTION));
      this.setAirSet(SharePreUtil.getIntValue(var1, SHARE_AIR_SET, 0));
   }

   public void setAirSet(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }
}
