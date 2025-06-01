package com.hzbhd.canbus.car._442;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int[] dvrInt;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   private Context mContext;
   int[] mFrontRadarData;
   private UiMgr mUiMgr;
   int nowDoorData1 = 0;
   int nowDoorData2 = 0;

   private void DvrModular(Context var1, byte[] var2) {
      int[] var4 = this.getByteArrayToIntArray(this.SplicingByte(new byte[]{0}, var2));
      if (var4[1] == 170 && var4[2] == 77 && var4[3] == 68) {
         int var3 = var4[5];
         if (var3 != 95) {
            if (var3 != 112) {
               if (var3 != 255) {
                  switch (var3) {
                     case 32:
                        this.set0x20(var4);
                        break;
                     case 33:
                        this.set0x21(var4);
                        break;
                     case 34:
                        this.set0x22();
                        break;
                     default:
                        switch (var3) {
                           case 80:
                              this.set0x50(var4[6]);
                              break;
                           case 81:
                              this.set0x51(var4[6]);
                              break;
                           case 82:
                              this.set0x52(var4[6]);
                              break;
                           case 83:
                              this.set0x53(var4[6]);
                              break;
                           case 84:
                              this.set0x54(var4[6]);
                              break;
                           case 85:
                              this.set0x55(var4[6]);
                              break;
                           case 86:
                              this.set0x56(var4[6]);
                              break;
                           case 87:
                              this.set0x57(var4[6]);
                              break;
                           case 88:
                              this.set0x58(var4[6], var4[7], var4[8], var4[9]);
                              break;
                           case 89:
                              this.set0x59(var4[6], var4[7], var4[8], var4[9]);
                              break;
                           case 90:
                              this.set0x5A(var4);
                              break;
                           case 91:
                              this.set0x5B();
                              break;
                           case 92:
                              this.set0x5C(var4);
                        }
                  }
               } else {
                  this.set0xFF();
               }
            } else {
               this.set0x70(var4);
            }
         } else {
            this.set0x5F(var4);
         }
      }

   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int blockBit(int var1, int var2) {
      if (var2 == 0) {
         return (DataHandleUtils.getIntFromByteWithBit(var1, 1, 7) & 255) << 1;
      } else if (var2 == 7) {
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, 7);
      } else {
         int var4 = var2 + 1;
         int var3 = DataHandleUtils.getIntFromByteWithBit(var1, var4, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var3 & 255) << var4;
      }
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private int getResult(int var1) {
      if (var1 != 0 && var1 != 1) {
         if (var1 >= 2 && var1 <= 5) {
            return 2;
         } else {
            return var1 >= 6 && var1 <= 7 ? 3 : 0;
         }
      } else {
         return 1;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isFrontRadarDataChange(int[] var1) {
      if (Arrays.equals(this.mFrontRadarData, var1)) {
         return false;
      } else {
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void set0x20(int[] var1) {
      GeneralDvrState.lock = DataHandleUtils.getBoolBit7(var1[6]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[6], 5, 2);
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 == 3) {
                  GeneralDvrState.tag = this.mContext.getString(2131765897);
               }
            } else {
               GeneralDvrState.tag = this.mContext.getString(2131765896);
            }
         } else {
            GeneralDvrState.tag = this.mContext.getString(2131765895);
         }
      } else {
         GeneralDvrState.tag = this.mContext.getString(2131765894);
      }

      GeneralDvrPlayPage.pageState = DataHandleUtils.getIntFromByteWithBit(var1[6], 3, 2);
      GeneralDvrState.sd = DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 2);
      var2 = var1[7];
      if (var2 == 0) {
         GeneralDvrPlayPage.time = this.mContext.getString(2131765909) + this.df_2Integer.format((long)var1[8]) + ":" + this.df_2Integer.format((long)var1[9]) + ":" + this.df_2Integer.format((long)var1[10]);
      } else if (var2 == 1) {
         GeneralDvrPlayPage.time = this.mContext.getString(2131765908) + this.df_2Integer.format((long)var1[8]) + ":" + this.df_2Integer.format((long)var1[9]) + ":" + this.df_2Integer.format((long)var1[10]);
      } else if (var2 == 255) {
         GeneralDvrPlayPage.time = " ";
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x21(int[] var1) {
      int var2 = var1[6];
      if (var2 != 209) {
         if (var2 != 210) {
            if (var2 != 212) {
               if (var2 != 216) {
                  if (var2 != 228) {
                     if (var2 != 232) {
                        if (var2 != 225) {
                           if (var2 != 226) {
                              if (var2 != 240) {
                                 if (var2 == 241) {
                                    GeneralDvrPlayPage.controlState = this.mContext.getString(2131765911);
                                 }
                              } else {
                                 GeneralDvrPlayPage.controlState = this.mContext.getString(2131765910);
                              }
                           } else {
                              GeneralDvrPlayPage.controlState = this.mContext.getString(2131765913);
                           }
                        } else {
                           GeneralDvrPlayPage.controlState = this.mContext.getString(2131765912);
                        }
                     } else {
                        GeneralDvrPlayPage.controlState = this.mContext.getString(2131765915);
                     }
                  } else {
                     GeneralDvrPlayPage.controlState = this.mContext.getString(2131765914);
                  }
               } else {
                  GeneralDvrPlayPage.controlState = this.mContext.getString(2131765919);
               }
            } else {
               GeneralDvrPlayPage.controlState = this.mContext.getString(2131765918);
            }
         } else {
            GeneralDvrPlayPage.controlState = this.mContext.getString(2131765917);
         }
      } else {
         GeneralDvrPlayPage.controlState = this.mContext.getString(2131765916);
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x22() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.dvrInt[6] == 1) {
               Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765880), 0).show();
            }

         }
      });
   }

   private void set0x50(int var1) {
      switch (var1) {
         case 128:
            GeneralDvrSetting.resolvingPower = 0;
            break;
         case 129:
            GeneralDvrSetting.resolvingPower = 1;
            break;
         case 130:
            GeneralDvrSetting.resolvingPower = 2;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x51(int var1) {
      switch (var1) {
         case 128:
            GeneralDvrSetting.timeTag = 0;
            break;
         case 129:
            GeneralDvrSetting.timeTag = 1;
            break;
         case 130:
            GeneralDvrSetting.timeTag = 2;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x52(int var1) {
      if (var1 != 128) {
         if (var1 != 129) {
            if (var1 != 131) {
               if (var1 == 133) {
                  GeneralDvrSetting.VideoRecordingSyncTime = 3;
               }
            } else {
               GeneralDvrSetting.VideoRecordingSyncTime = 2;
            }
         } else {
            GeneralDvrSetting.VideoRecordingSyncTime = 1;
         }
      } else {
         GeneralDvrSetting.VideoRecordingSyncTime = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x53(int var1) {
      if (var1 != 128) {
         if (var1 == 129) {
            GeneralDvrSetting.VideoRecordingVoice = 1;
         }
      } else {
         GeneralDvrSetting.VideoRecordingVoice = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x54(int var1) {
      if (var1 != 128) {
         if (var1 == 129) {
            GeneralDvrSetting.dvrLanguage = 1;
         }
      } else {
         GeneralDvrSetting.dvrLanguage = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x55(int var1) {
      if (var1 != 128) {
         if (var1 != 129) {
            if (var1 != 131) {
               if (var1 == 133) {
                  GeneralDvrSetting.gravitySensor = 3;
               }
            } else {
               GeneralDvrSetting.gravitySensor = 2;
            }
         } else {
            GeneralDvrSetting.gravitySensor = 1;
         }
      } else {
         GeneralDvrSetting.gravitySensor = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x56(int var1) {
      if (var1 != 128) {
         if (var1 == 129) {
            GeneralDvrSetting.opticalFrequency = 1;
         }
      } else {
         GeneralDvrSetting.opticalFrequency = 0;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x57(int var1) {
      switch (var1) {
         case 128:
            GeneralDvrSetting.timeFormat = 0;
            break;
         case 129:
            GeneralDvrSetting.timeFormat = 1;
            break;
         case 130:
            GeneralDvrSetting.timeFormat = 2;
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x58(int var1, int var2, int var3, int var4) {
      if (var1 == 2) {
         GeneralDvrState.date = var2 + 2000 + "-" + this.df_2Decimal.format((long)var3) + "-" + this.df_2Decimal.format((long)var4);
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x59(int var1, int var2, int var3, int var4) {
      if (var1 == 2) {
         GeneralDvrState.time = this.df_2Integer.format((long)var2) + ":" + this.df_2Integer.format((long)var3) + ":" + this.df_2Integer.format((long)var4);
      }

      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x5A(int[] var1) {
      Log.d("fxHou0x5A", "收到数据");

      try {
         StringBuilder var2 = new StringBuilder();
         GeneralDvrState.version = var2.append(var1[6] + 2000).append("-").append(this.df_2Integer.format((long)var1[7])).append("-").append(this.df_2Integer.format((long)var1[8])).append(" V").append(var1[9]).toString();
      } catch (Exception var3) {
         Log.d("fxHou0x5A", var3.toString());
      }

      Log.d("fxHou0x5A", GeneralDvrState.version);
      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x5B() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            switch (this.this$0.dvrInt[6]) {
               case 128:
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765890), 0).show();
                  GeneralDvrState.formatSdFail = true;
                  break;
               case 129:
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765891), 0).show();
                  GeneralDvrState.formatSdFail = false;
                  break;
               case 130:
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765892), 0).show();
                  GeneralDvrState.formatSdFail = false;
            }

         }
      });
      NotifyBuilding.getInstance().updateUi();
   }

   private void set0x5C(int[] var1) {
      if (var1[6] == 128) {
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               Toast.makeText(this.this$0.mContext, "Reset Fail!", 0).show();
            }
         });
      }

   }

   private void set0x5F(int[] var1) {
      this.set0x50(var1[6]);
      this.set0x51(var1[7]);
      this.set0x52(var1[8]);
      this.set0x53(var1[9]);
      this.set0x54(var1[10]);
      this.set0x55(var1[11]);
      this.set0x56(var1[12]);
      this.set0x57(var1[13]);
      this.set0x58(2, var1[14], var1[15], var1[16]);
      this.set0x59(2, var1[17], var1[18], var1[19]);
   }

   private void set0x70(int[] var1) {
      int var8 = var1[6];
      int var3 = 0;
      byte var7 = 0;
      int var4 = 0;
      int var2 = 0;
      byte var6 = 0;
      byte var5 = 0;
      switch (var8) {
         case 1:
            GeneralDvrFile.getInstance().item1.clear();
            var3 = var1.length;

            for(var2 = var6; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item1.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 2:
            GeneralDvrFile.getInstance().item2.clear();

            for(var3 = var1.length; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item2.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 3:
            GeneralDvrFile.getInstance().item3.clear();
            var3 = var1.length;

            for(var2 = var4; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item3.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 4:
            GeneralDvrFile.getInstance().item4.clear();
            var3 = var1.length;

            for(var2 = var7; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item4.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 5:
            GeneralDvrFile.getInstance().item5.clear();
            var4 = var1.length;

            for(var2 = var3; var2 < var4; ++var2) {
               var3 = var1[var2];
               GeneralDvrFile.getInstance().item5.add(var3);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 6:
            GeneralDvrFile.getInstance().item6.clear();
            var3 = var1.length;

            for(var2 = var5; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item6.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
      }

   }

   private void set0xFF() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            Toast.makeText(this.this$0.mContext, this.this$0.mContext.getString(2131765903), 0).show();
         }
      });
   }

   private void setAlr0x378() {
      int[] var1 = this.mCanBusInfoInt;
      var1[7] = 0;
      var1[8] = 0;
      if (!this.isAirDataNoChange()) {
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 7) == 34) {
            GeneralAirData.center_wheel = "LO";
         } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 7) == 64) {
            GeneralAirData.center_wheel = "HI";
         } else {
            GeneralAirData.center_wheel = (float)this.mCanBusInfoInt[10] / 2.0F + this.getTempUnitC(this.mContext);
         }

         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_window = false;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 0) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_foot = true;
         } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 2) {
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_foot = true;
         } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3) == 3) {
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_window = true;
         }

         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12]);
         GeneralAirData.power = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]);
         GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setDoor0x350() {
      int[] var3 = this.mCanBusInfoInt;
      var3[9] = this.blockBit(var3[9], 1);
      int var1 = this.nowDoorData1;
      var3 = this.mCanBusInfoInt;
      int var2 = var3[8];
      if (var1 != var2 || this.nowDoorData2 != var3[9]) {
         this.nowDoorData1 = var2;
         this.nowDoorData2 = var3[9];
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit4(var2);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setRadarInfo(int[] var1) {
      if (this.isFrontRadarDataChange(var1)) {
         MdRadarData.left_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[5], 0, 4));
         MdRadarData.left_mid_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[5], 4, 4));
         MdRadarData.right_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[6], 0, 4));
         MdRadarData.right_mid_rear = this.getResult(DataHandleUtils.getIntFromByteWithBit(var1[6], 4, 4));
         this.getUiMgr(this.mContext).refreshRadar();
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      if (var2[1] == -86 && var2[2] == -86 && var2[3] == -86 && var2[4] == -86) {
         this.setRadarInfo(var4);
      } else {
         int var3 = this.getMsbLsbResult(var4[4], var4[5]);
         if (var3 != 848) {
            if (var3 == 888) {
               this.setAlr0x378();
            }
         } else {
            this.setDoor0x350();
         }

      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
   }

   // $FF: synthetic method
   void lambda$initCommand$0$com_hzbhd_canbus_car__442_MsgMgr(Context var1, byte[] var2) {
      this.DvrModular(var1, var2);
   }
}
