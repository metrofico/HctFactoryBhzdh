package com.hzbhd.canbus.car._430;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   DecimalFormat df_2Integer = new DecimalFormat("00");
   int differentId;
   int eachId;
   int[] mAirData;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   int[] mCarDoorData;
   Context mContext;
   int[] mFrontRadarData;
   int[] mPanoramicInfo;
   int[] mRearRadarData;
   int[] mTireInfo;
   byte[] mTrackData;
   private UiMgr mUiMgr;
   int nowLight = 4;
   private int nowValue = -1;

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

   private String getTemperature(int var1, int var2, int var3) {
      if (var1 == var2) {
         return "LO";
      } else if (var1 == var3) {
         return "HI";
      } else {
         return DataHandleUtils.getBoolBit7(var1) ? (double)DataHandleUtils.getIntFromByteWithBit(var1, 0, 7) + 0.5 + this.getTempUnitC(this.mContext) : var1 + this.getTempUnitC(this.mContext);
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
      if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mFrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isNotAirDataChange() {
      if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
         return true;
      } else {
         this.mAirData = this.mCanBusInfoInt;
         return false;
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

   private void setAirInfo0x73() {
      if (!this.isNotAirDataChange()) {
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralAirData.front_left_temperature = this.getTemperature(this.mCanBusInfoInt[4], 1, 255);
         GeneralAirData.front_right_temperature = this.getTemperature(this.mCanBusInfoInt[5], 1, 255);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         this.updateAirActivity(this.mContext, 1004);
      }
   }

   private void setBackLight() {
      int var1 = this.nowLight;
      if (var1 == 3) {
         this.nowLight = 4;
      } else if (var1 == 4) {
         this.nowLight = 5;
      } else if (var1 == 5) {
         this.nowLight = 3;
      }

      this.setBacklightLevel(this.nowLight);
   }

   private void setDoorInfo0x12() {
      if (this.isBasicInfoChange()) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }

   }

   private void setKnob0x22() {
      int var1 = this.nowValue;
      if (var1 == -1) {
         this.nowValue = this.mCanBusInfoInt[3];
      } else {
         int[] var2 = this.mCanBusInfoInt;
         if (var2[2] == 1) {
            if (var1 < var2[3]) {
               this.realKeyClick4(this.mContext, 7);
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         } else if (var1 < var2[3]) {
            this.realKeyClick4(this.mContext, 46);
         } else {
            this.realKeyClick4(this.mContext, 45);
         }

         this.nowValue = this.mCanBusInfoInt[3];
      }
   }

   private void setPanelButton0x21() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 6) {
                     if (var1 != 9) {
                        if (var1 != 16) {
                           if (var1 != 32) {
                              if (var1 != 36) {
                                 if (var1 != 57) {
                                    if (var1 != 59) {
                                       if (var1 != 66) {
                                          if (var1 != 75) {
                                             if (var1 != 95) {
                                                if (var1 != 42) {
                                                   if (var1 != 43) {
                                                      switch (var1) {
                                                         case 47:
                                                            this.buttonKey(151);
                                                            break;
                                                         case 48:
                                                            this.buttonKey(68);
                                                            break;
                                                         case 49:
                                                            if (var2[3] == 0) {
                                                               return;
                                                            }

                                                            this.setBackLight();
                                                            break;
                                                         default:
                                                            switch (var1) {
                                                               case 51:
                                                                  this.buttonKey(62);
                                                                  break;
                                                               case 52:
                                                                  this.buttonKey(14);
                                                                  break;
                                                               case 53:
                                                                  this.buttonKey(15);
                                                            }
                                                      }
                                                   } else {
                                                      this.buttonKey(52);
                                                   }
                                                } else {
                                                   this.buttonKey(49);
                                                }
                                             } else {
                                                this.buttonKey(187);
                                             }
                                          } else {
                                             this.buttonKey(62);
                                          }
                                       } else {
                                          this.buttonKey(4);
                                       }
                                    } else {
                                       this.buttonKey(2);
                                    }
                                 } else {
                                    this.buttonKey(134);
                                 }
                              } else {
                                 this.buttonKey(59);
                              }
                           } else {
                              this.buttonKey(128);
                           }
                        } else {
                           this.buttonKey(49);
                        }
                     } else {
                        this.buttonKey(3);
                     }
                  } else {
                     this.buttonKey(50);
                  }
               } else {
                  this.buttonKey(46);
               }
            } else {
               this.buttonKey(45);
            }
         } else {
            this.buttonKey(1);
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void setSwc0x72() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 10) {
                           if (var1 != 13) {
                              if (var1 == 14) {
                                 this.realKeyClick4(this.mContext, 46);
                              }
                           } else {
                              this.realKeyClick4(this.mContext, 45);
                           }
                        } else {
                           this.realKeyClick4(this.mContext, 2);
                        }
                     } else {
                        this.realKeyClick4(this.mContext, 15);
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 14);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 3);
               }
            } else {
               this.realKeyClick4(this.mContext, 8);
            }
         } else {
            this.realKeyClick4(this.mContext, 7);
         }
      } else {
         this.realKeyClick4(this.mContext, 0);
      }

   }

   private void setTrack0x72() {
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte)DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersion0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
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
      if (var3 != 18) {
         if (var3 != 240) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 114) {
                     if (var3 == 115) {
                        this.setAirInfo0x73();
                     }
                  } else {
                     this.setSwc0x72();
                     this.setTrack0x72();
                     this.updateSpeedInfo(this.mCanBusInfoInt[3]);
                  }
               } else {
                  this.setKnob0x22();
               }
            } else {
               this.setPanelButton0x21();
            }
         } else {
            this.setVersion0xF0();
         }
      } else {
         this.setDoorInfo0x12();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void knobKey(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   public void showDialog() {
      (new SetTimeView()).showDialog(this.getActivity(), new SetTimeView.ThisInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void hourDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 110, 6, 2});
         }

         public void hourUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 110, 6, 1});
         }

         public void minDown() {
            CanbusMsgSender.sendMsg(new byte[]{22, 110, 7, 2});
         }

         public void minUp() {
            CanbusMsgSender.sendMsg(new byte[]{22, 110, 7, 1});
         }

         public void timeFormat(boolean var1) {
            byte var2 = 1;
            if (!var1) {
               var2 = 2;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 110, 8, (byte)var2});
         }
      });
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
}
