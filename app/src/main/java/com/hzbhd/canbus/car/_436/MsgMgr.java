package com.hzbhd.canbus.car._436;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._436.buiding.NotifyBuilding;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrFile;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrPlayPage;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrSetting;
import com.hzbhd.canbus.car_cus._436.data.GeneralDvrState;
import com.hzbhd.canbus.car_cus._436.util.DvrSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_0Decimal = new DecimalFormat("###00");
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int[] dvrInt;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mPanoramicInfo;
   int[] mRadarData;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   byte noneData = 0;
   boolean nowLeftLampState = false;
   boolean nowRightLampState = false;
   private int nowRightLight = 0;

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
         int var3 = var2 + 1;
         int var4 = DataHandleUtils.getIntFromByteWithBit(var1, var3, 7 - var2);
         return DataHandleUtils.getIntFromByteWithBit(var1, 0, var2) & 255 & 255 ^ (var4 & 255) << var3;
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getTemperature(int var1, double var2, double var4, String var6, int var7, int var8) {
      if (var1 == var7) {
         return "LO";
      } else if (var1 == var8) {
         return "HI";
      } else {
         return var6.trim().equals("C") ? (double)var1 * var2 + var4 + this.getTempUnitC(this.mContext) : (double)var1 * var2 + var4 + this.getTempUnitF(this.mContext);
      }
   }

   private String getUTF8Result(String var1) {
      try {
         var1 = URLDecoder.decode(var1, "utf-8");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         var1 = "";
      }

      return var1;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean is404(String var1, String var2) {
      return this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, var1) == -1 || this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, var1, var2) == -1;
   }

   private boolean isAirDataNoChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
      }
   }

   private boolean isBasicInfoChange() {
      if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mCarDoorData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFrontRadarDataChange() {
      if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isPanoramicInfoChange() {
      if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mPanoramicInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isRearRadarDataChange() {
      if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRearRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackInfoChange() {
      if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
         return false;
      } else {
         byte[] var1 = this.mCanBusInfoByte;
         this.mTrackData = Arrays.copyOf(var1, var1.length);
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
      int var2 = 0;
      byte var5 = 0;
      int var4 = 0;
      int var3 = 0;
      byte var7 = 0;
      byte var6 = 0;
      switch (var8) {
         case 1:
            GeneralDvrFile.getInstance().item1.clear();
            var3 = var1.length;

            for(var2 = var7; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item1.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 2:
            GeneralDvrFile.getInstance().item2.clear();
            var4 = var1.length;

            for(var2 = var3; var2 < var4; ++var2) {
               var3 = var1[var2];
               GeneralDvrFile.getInstance().item2.add(var3);
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

            for(var2 = var5; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item4.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 5:
            GeneralDvrFile.getInstance().item5.clear();

            for(var3 = var1.length; var2 < var3; ++var2) {
               var4 = var1[var2];
               GeneralDvrFile.getInstance().item5.add(var4);
            }

            NotifyBuilding.getInstance().updateUi();
            break;
         case 6:
            GeneralDvrFile.getInstance().item6.clear();
            var3 = var1.length;

            for(var2 = var6; var2 < var3; ++var2) {
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
      if (DataHandleUtils.getBoolBit(0, this.mCanBusInfoInt[8])) {
         this.updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + this.getTempUnitC(this.mContext));
      }

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

   private void setData0x300() {
      if (!this.isTrackInfoChange()) {
         Log.d("EPS", "mCanBusInfoByte[11]--------------->" + this.mCanBusInfoByte[11]);
         Log.d("EPS", "mCanBusInfoByte[12]--------------->" + this.mCanBusInfoByte[12]);
         StringBuilder var2 = (new StringBuilder()).append("MSB LSB[11 12]--------------->");
         byte[] var1 = this.mCanBusInfoByte;
         Log.d("EPS", var2.append(this.getMsbLsbResult(var1[11], var1[12])).toString());
         var2 = (new StringBuilder()).append("MSB LSB[12 11]--------------->");
         var1 = this.mCanBusInfoByte;
         Log.d("EPS", var2.append(this.getMsbLsbResult(var1[12], var1[11])).toString());
         DecimalFormat var3 = this.df_0Decimal;
         var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = Integer.parseInt(var3.format((double)(((float)this.getMsbLsbResult(var1[11], var1[12]) / 10.0F - 780.0F) / 30.0F + 1.0F)));
         this.updateParkUi((Bundle)null, this.mContext);
         Log.d("EPS", "实际转角--------------->" + GeneralParkData.trackAngle);
      }
   }

   private void setData0x358() {
      ArrayList var2 = new ArrayList();
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
      if (var1 != 0 && var1 != 1) {
         if (var1 == 2 || var1 == 3 || var1 == 4) {
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_6"), 1));
         }
      } else {
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_6"), 0));
      }

      var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setData0x372() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2) - 1));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setData0x376() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_436_Setting_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_436_Setting_function", "_436_Setting_function_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setFCamera0x350() {
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) != this.nowRightLight) {
         Context var3 = this.mContext;
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
         boolean var2 = true;
         if (var1 != 1) {
            var2 = false;
         }

         this.switchFCamera(var3, var2);
         this.nowRightLight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
      }

   }

   private void setLeftLamp(int[] var1) {
      if (this.nowLeftLampState != DataHandleUtils.getBoolBit0(var1[8])) {
         boolean var2 = DataHandleUtils.getBoolBit0(var1[8]);
         this.nowLeftLampState = var2;
         this.turnLeftLamp(var2);
      }
   }

   private void setRadar0x3A0() {
      if (this.isFrontRadarDataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.setFrontRadarLocationData(7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 3), 0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3));
         RadarInfoUtil.setRearRadarLocationData(7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 3));
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setRightLamp(int[] var1) {
      if (this.nowRightLampState != DataHandleUtils.getBoolBit2(var1[8])) {
         boolean var2 = DataHandleUtils.getBoolBit2(var1[8]);
         this.nowRightLampState = var2;
         this.turnRightLamp(var2);
      }
   }

   private int sixteenToTen(String var1) {
      return Integer.parseInt(("0x" + var1).replaceAll("^0[x|X]", ""), 16);
   }

   private String tenToSixTeen(int var1) {
      return String.format("%02x", var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
      DvrDataKAdapter.INSTANCE.registerOnDataReadCallback(new MsgMgr$$ExternalSyntheticLambda0(this, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = this.getMsbLsbResult(var4[4], var4[5]);
      if (var3 != 768) {
         if (var3 != 848) {
            if (var3 != 856) {
               if (var3 != 882) {
                  if (var3 != 886) {
                     if (var3 != 888) {
                        if (var3 == 928) {
                           this.setRadar0x3A0();
                        }
                     } else {
                        this.setAlr0x378();
                     }
                  } else {
                     this.setData0x376();
                  }
               } else {
                  this.setData0x372();
               }
            } else {
               this.setData0x358();
            }
         } else {
            this.setFCamera0x350();
            this.setLeftLamp(this.mCanBusInfoInt);
            this.setRightLamp(this.mCanBusInfoInt);
         }
      } else {
         this.setData0x300();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      DvrSender.send(new byte[]{35, 0});
      DvrSender.send(new byte[]{35, 8});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   // $FF: synthetic method
   void lambda$afterServiceNormalSetting$0$com_hzbhd_canbus_car__436_MsgMgr(Context var1, byte[] var2) {
      this.DvrModular(var1, var2);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);

      try {
         byte[] var26 = var21.getBytes("UTF-8");
         this.getUiMgr(this.mContext).sendMediaInfo(8, 0, 17, 1, var26);
         var26 = var23.getBytes("UTF-8");
         this.getUiMgr(this.mContext).sendMediaInfo(8, 0, 18, 1, var26);
         var26 = var22.getBytes("UTF-8");
         this.getUiMgr(this.mContext).sendMediaInfo(8, 0, 19, 1, var26);
      } catch (UnsupportedEncodingException var25) {
         var25.printStackTrace();
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);

      try {
         StringBuilder var8 = new StringBuilder();
         byte[] var7 = var8.append(var2).append(" ").append(var3).toString().getBytes("UTF-8");
         this.getUiMgr(this.mContext).sendMediaInfo(1, 0, 1, 1, var7);
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
      }

   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5, String var6) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var7 = new ArrayList();
      var7.add(new SettingUpdateEntity(var2, var3, var5 + var6));
      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);

      try {
         StringBuilder var19 = new StringBuilder();
         byte[] var20 = var19.append(var11).append(":").append(var12).append(":").append(var13).toString().getBytes("UTF-8");
         this.getUiMgr(this.mContext).sendMediaInfo(8, 0, 20, 1, var20);
      } catch (UnsupportedEncodingException var18) {
         var18.printStackTrace();
      }

   }

   public void voiceControlInfo(String var1) {
      super.voiceControlInfo(var1);
      (new Message()).what = 9090;
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1822612586:
            if (var1.equals("front.right.seat.heat.open")) {
               var2 = 0;
            }
            break;
         case -1391366141:
            if (var1.equals("skylight.open")) {
               var2 = 1;
            }
            break;
         case -1391242821:
            if (var1.equals("skylight.stop")) {
               var2 = 2;
            }
            break;
         case -1231133295:
            if (var1.equals("skylight.tiltup")) {
               var2 = 3;
            }
            break;
         case -1226270570:
            if (var1.equals("ac.open")) {
               var2 = 4;
            }
            break;
         case -1192910143:
            if (var1.equals("front.left.seat.heat.close")) {
               var2 = 5;
            }
            break;
         case -910491843:
            if (var1.equals("tailgate.open")) {
               var2 = 6;
            }
            break;
         case -776229461:
            if (var1.equals("sun.visor.close")) {
               var2 = 7;
            }
            break;
         case -677606868:
            if (var1.equals("front.right.seat.heat.close")) {
               var2 = 8;
            }
            break;
         case -453761951:
            if (var1.equals("front.left.seat.heat.open")) {
               var2 = 9;
            }
            break;
         case -193868961:
            if (var1.equals("skylight.close")) {
               var2 = 10;
            }
            break;
         case 629126444:
            if (var1.equals("ac.close")) {
               var2 = 11;
            }
            break;
         case 1358234944:
            if (var1.equals("all.windows.close")) {
               var2 = 12;
            }
            break;
         case 1360794679:
            if (var1.equals("sun.visor.open")) {
               var2 = 13;
            }
            break;
         case 1360917999:
            if (var1.equals("sun.visor.stop")) {
               var2 = 14;
            }
            break;
         case 1828332389:
            if (var1.equals("tailgate.close")) {
               var2 = 15;
            }
            break;
         case 1983837698:
            if (var1.equals("all.windows.open")) {
               var2 = 16;
            }
      }

      switch (var2) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -98, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -98, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -98, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -98, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -98, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -98, 8, -128});
            break;
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 24, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 24, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 24, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 24, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 24, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 24, 0, -74, 8, -128});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 56, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 56, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 56, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 56, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 56, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 56, 0, -74, 8, -128});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 72, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 72, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 72, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 72, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 72, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 72, 0, -74, 8, -128});
            break;
         case 4:
            this.getUiMgr(this.mContext).sendAir(3, 0, 0, 0, 0, 0, 0, 0);
            this.getUiMgr(this.mContext).sendAir(3, 0, 0, 0, 0, 0, 0, 0);
            this.getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -80, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -80, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -80, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -80, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -80, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -80, 8, -128});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 5, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 5, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 5, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 5, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 5, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 5, 9, -128});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 104, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 104, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 104, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 104, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 104, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 104, 0, -74, 8, -128});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -122, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -122, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -122, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -122, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -122, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -122, 8, -128});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -77, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -77, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -77, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -77, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -77, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, -77, 8, -128});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 40, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 40, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 40, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 40, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 40, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 40, 0, -74, 8, -128});
            break;
         case 11:
            this.getUiMgr(this.mContext).sendAir(1, 128, 0, 0, 0, 0, 0, 0);
            this.getUiMgr(this.mContext).sendAir(1, 128, 0, 0, 0, 0, 0, 0);
            this.getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.getUiMgr(this.mContext).sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -1, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -1, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -1, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -1, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -1, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -1, -74, 8, -128});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 88, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 88, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 88, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 88, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 88, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 88, 0, -74, 8, -128});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 120, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 120, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 120, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 120, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 120, 0, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, -113, 127, 120, 0, -74, 8, -128});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 69, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 69, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 69, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 69, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 69, 9, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, 8, 0, 69, 9, -128});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -86, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -86, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -86, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -86, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -86, -74, 8, -128});
            CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 0, -123, 32, -1, 15, 127, -120, -86, -74, 8, -128});
      }

   }
}
