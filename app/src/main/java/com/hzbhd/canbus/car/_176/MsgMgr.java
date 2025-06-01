package com.hzbhd.canbus.car._176;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static final String DEVICE_STATUS_AUX = "AUX";
   private static final String DEVICE_STATUS_CD = "CD";
   private static final String DEVICE_STATUS_POWER_OFF = "Power Off";
   private static final String DEVICE_STATUS_POWER_ON = "Power On";
   private static final String DEVICE_STATUS_RADIO = "RADIO";
   private static final String DEVICE_STATUS_TV = "TV";
   static int ONE;
   static int WIND_LEVEL_HIGH;
   static int WIND_LEVEL_LOW;
   private String SHARE_176_DATA_0X90 = "data0x90";
   private String SHARE_176_DATA_0X91 = "data0x91";
   private String SHARE_176_DATA_0X92 = "data0x92";
   private String SHARE_176_DATA_0X93 = "data0x93";
   private String SHARE_176_DATA_0XA0 = "data0xa0";
   private String SHARE_176_DATA_0XA1 = "data0xa1";
   private String SHARE_176_DATA_0XA2 = "data0xa2";
   private String SHARE_176_DATA_0XA3 = "data0xa3";
   private String SHARE_176_DATA_0XA4 = "data0xa4";
   private String SHARE_176_LANGUAGE = "176_Language";
   private int data0x90 = 0;
   private int data0x91 = 0;
   private int data0x92 = 0;
   private int data0x93 = 0;
   private int data0xA0 = 0;
   private int data0xA1 = 0;
   private int data0xA2 = 0;
   private int data0xA3 = 0;
   private int data0xA4 = 0;
   private boolean isInComeBtPhone;
   private int[] m0x10DataNow;
   private int mCallStatus = 1;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwcInfoCopy;
   private Context mContext;
   private String mDeviceStatusNow = "";
   private boolean mIsBackOpen;
   private boolean mIsBackOpenNow;
   private boolean mIsFrontLeftOpen;
   private boolean mIsFrontLeftOpenNow;
   private boolean mIsFrontOpen;
   private boolean mIsFrontOpenNow;
   private boolean mIsFrontRightOpen;
   private boolean mIsFrontRightOpenNow;
   private boolean mIsRearLeftOpen;
   private boolean mIsRearLeftOpenNow;
   private boolean mIsRearRightOpen;
   private boolean mIsRearRightOpenNow;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   int[] mPanoramicInfo;
   private UiMgr mUiMgr;

   private void addLanguage(List var1, int var2) {
      int var3;
      if (var2 >= 38) {
         var3 = var2 - 4;
      } else if (var2 >= 23) {
         var3 = var2 - 3;
      } else if (var2 >= 4) {
         var3 = var2 - 2;
      } else {
         var3 = var2;
      }

      if (var2 != 4 && var2 != 5 && var2 != 23 && var2 != 38) {
         var1.add(new SettingUpdateEntity(0, 14, var3));
      }

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

   private void changeOriginalDevicePage(List var1, String[] var2, boolean var3) {
      OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet();
      var4.setItems(var1);
      var4.setRowTopBtnAction(var2);
      var4.setHaveSongList(var3);
      this.updateOriginalDeviceWithInit();
      this.cleanDevice();
   }

   private void cleanDevice() {
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.folder = false;
      GeneralOriginalCarDeviceData.wma = false;
      GeneralOriginalCarDeviceData.mp3 = false;
      GeneralOriginalCarDeviceData.scane = false;
      GeneralOriginalCarDeviceData.rds = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.auto_p = false;
      GeneralOriginalCarDeviceData.mList = null;
      Iterator var1 = this.getOriginalCarDevicePageUiSet().getItems().iterator();

      while(var1.hasNext()) {
         ((OriginalCarDevicePageUiSet.Item)var1.next()).setValue("");
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private byte getAllBandTypeData(String var1) {
      byte var2;
      label53: {
         var1.hashCode();
         switch (var1) {
            case "AM":
               var2 = 0;
               break label53;
            case "FM":
               var2 = 1;
               break label53;
            case "AM1":
               var2 = 2;
               break label53;
            case "AM2":
               var2 = 3;
               break label53;
            case "AM3":
               var2 = 4;
               break label53;
            case "FM1":
               var2 = 5;
               break label53;
            case "FM2":
               var2 = 6;
               break label53;
            case "FM3":
               var2 = 7;
               break label53;
         }

         var2 = -1;
      }

      switch (var2) {
         case 0:
            return 16;
         case 1:
            return 0;
         case 2:
            return 17;
         case 3:
            return 18;
         case 4:
            return 19;
         case 5:
            return 1;
         case 6:
            return 2;
         case 7:
            return 3;
         default:
            return -1;
      }
   }

   private Object getColor(int var1) {
      if (var1 != 0) {
         if (var1 != 6) {
            return var1 != 7 ? 3 : 2;
         } else {
            return 1;
         }
      } else {
         return 0;
      }
   }

   private String getData(int var1) {
      return var1 == 65535 ? this.mContext.getString(2131769909) : String.format("%.1f", (float)this.getIdFromCanbusInfo(var1) / 10.0F);
   }

   private String getDataDistance(int var1) {
      return var1 == 65535 ? this.mContext.getString(2131769909) : String.format("%.1f", (float)(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[var1 + 2], 0, 1) * 256 * 256 + this.getIdFromCanbusInfo(var1)) / 10.0F);
   }

   private String getDeviceWorkModle(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  var2 = "";
               } else {
                  var2 = "TV";
               }
            } else {
               var2 = "AUX";
            }
         } else {
            var2 = "CD";
         }
      } else {
         var2 = "RADIO";
      }

      return var2;
   }

   private String getDistanceUnit() {
      int var1 = this.mCanBusInfoInt[9];
      if (var1 == 255) {
         return "KM";
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(var1, 2, 2);
         if (var1 != 0) {
            return var1 != 1 ? "" : "MILES";
         } else {
            return "KM";
         }
      }
   }

   private int getIdFromCanbusInfo(int var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[var1];
      return var3[var1 + 1] & 255 | (var2 & 255) << 8;
   }

   private String getOilUnit() {
      int var1 = this.mCanBusInfoInt[9];
      if (var1 == 255) {
         return "L/100KM";
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(var1, 4, 3);
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        return var1 != 5 ? "" : "MPKWH";
                     } else {
                        return "KWH/100KM";
                     }
                  } else {
                     return "MPGUK";
                  }
               } else {
                  return "MPGUS";
               }
            } else {
               return "KM/L";
            }
         } else {
            return "L/100KM";
         }
      }
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet() {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr().getOriginalCarDevicePageUiSet(this.mContext);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private String getPowerStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "Power On";
      } else {
         var2 = "Power Off";
      }

      return var2;
   }

   private String getSpeedUnit() {
      int var1 = this.mCanBusInfoInt[9];
      if (var1 == 255) {
         return "KM/H";
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(var1, 2, 2);
         if (var1 != 0) {
            return var1 != 1 ? "" : "MILES/H";
         } else {
            return "KM/H";
         }
      }
   }

   private TireUpdateEntity getTireEntity(int var1, String var2) {
      return new TireUpdateEntity(var1, 0, new String[]{var2});
   }

   private String getTirePressure(int var1) {
      return var1 == 255 ? this.mContext.getString(2131769909) : String.format("%.1f", (float)var1 * 0.03F) + this.mContext.getString(2131769654);
   }

   private String getTisWarmMsg(int var1) {
      switch (var1) {
         case 1:
            var1 = 2131758556;
            break;
         case 2:
            var1 = 2131758557;
            break;
         case 3:
            var1 = 2131758558;
            break;
         case 4:
            var1 = 2131758559;
            break;
         case 5:
            var1 = 2131758560;
            break;
         case 6:
            var1 = 2131758561;
            break;
         default:
            var1 = 0;
      }

      return var1 == 0 ? "" : this.mContext.getString(var1);
   }

   private UiMgr getUiMgr() {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(this.mContext);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData() {
      int var1 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_LANGUAGE, 0);
      if (this.getCurrentCanDifferentId() != 11 && this.getCurrentCanDifferentId() != 12) {
         if (var1 > 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var1});
      }

      this.data0x90 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X90, 0);
      this.data0x91 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X91, 0);
      this.data0x92 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X92, 0);
      this.data0x93 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X93, 0);
      this.data0xA0 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA0, 0);
      this.data0xA1 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA1, 0);
      this.data0xA2 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA2, 0);
      this.data0xA3 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA3, 0);
      this.data0xA4 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA4, 0);
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte)this.this$0.data0x90});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte)(this.this$0.data0x91 + MsgMgr.ONE)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte)(this.this$0.data0x92 + MsgMgr.ONE)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -109, (byte)(this.this$0.data0x93 + MsgMgr.ONE)});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte)this.this$0.data0xA0});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte)this.this$0.data0xA1});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte)this.this$0.data0xA2});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte)this.this$0.data0xA3});
               sleep(50L);
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte)this.this$0.data0xA4});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean is0x10DataChange() {
      if (Arrays.equals(this.m0x10DataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x10DataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isDeviceStatusSame(String var1) {
      if (var1.equals(GeneralOriginalCarDeviceData.discStatus) && !var1.equals(this.mDeviceStatusNow)) {
         this.mDeviceStatusNow = var1;
         return true;
      } else {
         return false;
      }
   }

   private boolean isDoorDataChange() {
      boolean var1 = this.mIsFrontLeftOpen;
      if (var1 == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsFrontOpen == this.mIsFrontOpenNow) {
         return false;
      } else {
         this.mIsFrontLeftOpenNow = var1;
         this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
         this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
         this.mIsRearRightOpenNow = this.mIsRearRightOpen;
         this.mIsBackOpenNow = this.mIsBackOpen;
         this.mIsFrontOpenNow = this.mIsFrontOpen;
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

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void rearKey3(int var1) {
      this.realKeyClick3(this.mContext, var1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 6));
   }

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var3 = "";
      String var2;
      if (var1 == 0) {
         var2 = var3;
      } else if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = var3;
         if (var1 >= 32) {
            var2 = var3;
            if (var1 <= 64) {
               var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
            }
         }
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[3];
      if (var1 == 255) {
         return "--" + this.mContext.getString(2131770016);
      } else {
         return var1 == 254 ? "" : this.mCanBusInfoInt[3] - 40 + this.mContext.getString(2131770016);
      }
   }

   private void set0x3DWheelKeyInfo() {
      // $FF: Couldn't be decompiled
   }

   private void set0x3EWheelInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 == 0) {
         if (DataHandleUtils.getIntFromByteWithBit(var2[3], 0, 2) == 1) {
            this.rearKey3(8);
         } else {
            this.rearKey3(7);
         }
      } else if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 16) {
               if (var1 != 20) {
                  if (var1 != 32) {
                     if (var1 != 36) {
                        if (var1 != 48) {
                           if (var1 != 64) {
                              if (var1 == 68) {
                                 this.realKeyClick4(144);
                              }
                           } else {
                              this.realKeyClick4(44);
                           }
                        } else {
                           this.realKeyClick4(42);
                        }
                     } else {
                        this.realKeyClick4(38);
                     }
                  } else {
                     this.realKeyClick4(37);
                  }
               } else {
                  this.realKeyClick4(36);
               }
            } else {
               this.realKeyClick4(35);
            }
         } else {
            this.realKeyClick4(34);
         }
      } else {
         this.realKeyClick4(33);
      }

   }

   private void set0x40ModeInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 == 0) {
         GeneralDisplayMsgData.displayMsg = this.mContext.getString(2131758393);
         this.sendDisplayMsgView(this.mContext);
      } else if (var1 == 1) {
         GeneralDisplayMsgData.displayMsg = this.mContext.getString(2131758394);
         this.sendDisplayMsgView(this.mContext);
      } else if (var1 == 2) {
         GeneralDisplayMsgData.displayMsg = this.mContext.getString(2131758395);
         this.sendDisplayMsgView(this.mContext);
      }

   }

   private void setAccused0x20() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 143) {
                        switch (var1) {
                           case 6:
                              this.realKeyLongClick1(this.mContext, 464, var2[3]);
                              break;
                           case 7:
                              this.realKeyLongClick1(this.mContext, 2, var2[3]);
                              break;
                           case 8:
                              this.realKeyLongClick1(this.mContext, 187, var2[3]);
                              break;
                           case 9:
                              this.realKeyLongClick1(this.mContext, 14, var2[3]);
                              break;
                           case 10:
                              this.realKeyLongClick1(this.mContext, 15, var2[3]);
                              break;
                           case 11:
                              this.realKeyLongClick1(this.mContext, 187, var2[3]);
                              break;
                           default:
                              switch (var1) {
                                 case 18:
                                    this.realKeyLongClick1(this.mContext, 187, var2[3]);
                                    break;
                                 case 19:
                                    this.realKeyLongClick1(this.mContext, 45, var2[3]);
                                    break;
                                 case 20:
                                    this.realKeyLongClick1(this.mContext, 46, var2[3]);
                                    break;
                                 case 21:
                                    this.realKeyLongClick1(this.mContext, 50, var2[3]);
                                    break;
                                 case 22:
                                    this.realKeyLongClick1(this.mContext, 49, var2[3]);
                              }
                        }
                     } else {
                        this.realKeyClick4(14);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void setAirData0x21() {
      GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      boolean var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      boolean var2 = true;
      GeneralAirData.in_out_cycle = var3 ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.soft = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.fast = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.normal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      int var1 = this.mCanBusInfoInt[4];
      GeneralAirData.front_wind_level = var1;
      if (var1 != 15) {
         var2 = false;
      }

      GeneralAirData.front_auto_wind_speed = var2;
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarData0x81() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, this.getData(2) + this.getOilUnit()));
      var1.add(new DriverUpdateEntity(0, 1, this.getData(4) + this.getSpeedUnit()));
      var1.add(new DriverUpdateEntity(0, 2, this.getDataDistance(6) + this.getDistanceUnit()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var2 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var2[4], var2[5]));
   }

   private void setCarMessage0x28() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightFrontDoorOpen = var1;
      this.mIsFrontLeftOpen = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      this.mIsFrontRightOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = var1;
      this.mIsRearLeftOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = var1;
      this.mIsRearRightOpen = var1;
      var1 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = var1;
      this.mIsBackOpen = var1;
      var1 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = var1;
      this.mIsFrontOpen = var1;
      if (this.isDoorDataChange()) {
         this.updateDoorView(this.mContext);
      }

      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarSetting0x71() {
      int var2;
      ArrayList var38;
      label52: {
         int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
         int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         int var22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
         int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
         int var30 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
         int var31 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 3);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
         int var24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
         int var34 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
         int var29 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         int var23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
         int var35 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
         int var25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
         int var36 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
         int var32 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
         int[] var37 = this.mCanBusInfoInt;
         var2 = var37[5];
         int var28 = var37[6];
         int var33 = var37[7];
         int var27 = var37[8];
         int var13 = DataHandleUtils.getIntFromByteWithBit(var37[9], 7, 1);
         int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1);
         int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
         int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
         int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1);
         int var21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 3);
         var37 = this.mCanBusInfoInt;
         int var11 = var37[10];
         int var9 = DataHandleUtils.getIntFromByteWithBit(var37[11], 7, 1);
         int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 3);
         int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1);
         int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1);
         var38 = new ArrayList();
         int var26 = this.getCurrentCanDifferentId();
         if (var26 != 0 && var26 != 2) {
            switch (var26) {
               case 4:
               case 5:
               case 9:
               case 10:
               case 12:
                  break;
               case 6:
               case 11:
                  var38.add(new SettingUpdateEntity(0, 0, var31));
                  var38.add(new SettingUpdateEntity(0, 1, var30));
                  var38.add(new SettingUpdateEntity(0, 2, var24));
                  var38.add(new SettingUpdateEntity(0, 3, var34));
                  var38.add(new SettingUpdateEntity(0, 4, var29));
                  var38.add(new SettingUpdateEntity(0, 5, var23));
                  var38.add(new SettingUpdateEntity(0, 6, var1));
                  var38.add(new SettingUpdateEntity(0, 7, var35));
                  var38.add(new SettingUpdateEntity(0, 8, var25));
                  var38.add(new SettingUpdateEntity(0, 9, var36));
                  var38.add(new SettingUpdateEntity(0, 10, var32 - ONE));
                  var38.add(new SettingUpdateEntity(0, 11, var33 - ONE));
                  var38.add((new SettingUpdateEntity(0, 12, var27)).setProgress(var27));
                  if (var28 == 0) {
                     var1 = 0;
                  } else {
                     var1 = var28 - ONE;
                  }

                  var38.add(new SettingUpdateEntity(0, 13, var1));
                  this.addLanguage(var38, var2);
                  var38.add(new SettingUpdateEntity(2, 0, var17));
                  var38.add(new SettingUpdateEntity(2, 1, var15));
                  var38.add(new SettingUpdateEntity(2, 2, var22));
                  var38.add(new SettingUpdateEntity(2, 3, var18));
                  var38.add(new SettingUpdateEntity(2, 4, var20));
                  var38.add(new SettingUpdateEntity(2, 5, var14));
                  var38.add(new SettingUpdateEntity(2, 6, var16));
                  var38.add(new SettingUpdateEntity(2, 7, var13));
                  var38.add(new SettingUpdateEntity(2, 8, var12));
                  var38.add(new SettingUpdateEntity(2, 9, var19));
                  if (var21 == 0) {
                     var1 = 0;
                  } else {
                     var1 = var21 - ONE;
                  }

                  var38.add(new SettingUpdateEntity(2, 10, var1));
                  var38.add((new SettingUpdateEntity(2, 11, var11)).setProgress(var11));
                  var38.add(new SettingUpdateEntity(2, 12, var9));
                  if (var10 == 0) {
                     var1 = 0;
                  } else {
                     var1 = var10 - ONE;
                  }

                  var38.add(new SettingUpdateEntity(2, 13, var1));
                  if (var8 == 0) {
                     var1 = 0;
                  } else {
                     var1 = var8 - ONE;
                  }

                  var38.add(new SettingUpdateEntity(2, 14, var1));
                  var38.add(new SettingUpdateEntity(2, 15, var5));
                  var38.add(new SettingUpdateEntity(2, 16, var6));
                  if (var7 == 0) {
                     var1 = 0;
                  } else {
                     var1 = var7 - ONE;
                  }

                  var38.add(new SettingUpdateEntity(2, 17, var1));
                  var38.add(new SettingUpdateEntity(2, 18, var4));
                  var38.add(new SettingUpdateEntity(2, 19, var3));
                  break label52;
               case 7:
               case 8:
                  var38.add(new SettingUpdateEntity(0, 0, var31));
               default:
                  break label52;
            }
         }

         var38.add(new SettingUpdateEntity(0, 0, var31));
         var38.add(new SettingUpdateEntity(0, 1, var30));
         var38.add(new SettingUpdateEntity(0, 2, var24));
         var38.add(new SettingUpdateEntity(0, 3, var34));
         var38.add(new SettingUpdateEntity(0, 4, var29));
         var38.add(new SettingUpdateEntity(0, 5, var23));
         var38.add(new SettingUpdateEntity(0, 6, var1));
         var38.add(new SettingUpdateEntity(0, 7, var35));
         var38.add(new SettingUpdateEntity(0, 8, var25));
         var38.add(new SettingUpdateEntity(0, 9, var36));
         var38.add(new SettingUpdateEntity(0, 10, var32 - ONE));
         var38.add(new SettingUpdateEntity(0, 11, var33 - ONE));
         var38.add((new SettingUpdateEntity(0, 12, var27)).setProgress(var27));
         if (var28 == 0) {
            var1 = 0;
         } else {
            var1 = var28 - ONE;
         }

         var38.add(new SettingUpdateEntity(0, 13, var1));
         this.addLanguage(var38, var2);
      }

      var38.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function2"), this.getColor(this.mCanBusInfoInt[6])));
      var38.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function3"), this.getColor(this.mCanBusInfoInt[9])));
      var38.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_language_title"), this.mCanBusInfoInt[5]));
      this.updateGeneralSettingData(var38);
      this.updateSettingActivity((Bundle)null);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_LANGUAGE, var2);
   }

   private void setCarSetting0x72() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      switch (var1) {
         case 144:
            this.data0x90 = var2[3];
            break;
         case 145:
            this.data0x91 = var2[3];
            break;
         case 146:
            this.data0x92 = var2[3];
            break;
         case 147:
            this.data0x93 = var2[3];
            break;
         default:
            switch (var1) {
               case 160:
                  this.data0xA0 = var2[3];
                  break;
               case 161:
                  this.data0xA1 = var2[3];
                  break;
               case 162:
                  this.data0xA2 = var2[3];
                  break;
               case 163:
                  this.data0xA3 = var2[3];
                  break;
               case 164:
                  this.data0xA4 = var2[3];
            }
      }

      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity(3, 0, this.data0x90));
      var1 = this.data0x91;
      if (var1 == 0) {
         var1 = 0;
      } else {
         var1 -= ONE;
      }

      var3.add(new SettingUpdateEntity(3, 1, var1));
      var1 = this.data0x92;
      if (var1 == 0) {
         var1 = 0;
      } else {
         var1 -= ONE;
      }

      var3.add(new SettingUpdateEntity(3, 2, var1));
      var1 = this.data0x93;
      if (var1 == 0) {
         var1 = 0;
      } else {
         var1 -= ONE;
      }

      var3.add(new SettingUpdateEntity(3, 3, var1));
      var3.add(new SettingUpdateEntity(4, 0, this.data0xA0));
      var3.add(new SettingUpdateEntity(4, 1, this.data0xA1));
      var3.add((new SettingUpdateEntity(4, 2, this.data0xA2)).setProgress(this.data0xA2));
      var3.add((new SettingUpdateEntity(4, 3, this.data0xA3)).setProgress(this.data0xA3));
      var3.add((new SettingUpdateEntity(4, 4, this.data0xA4)).setProgress(this.data0xA4));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X90, this.data0x90);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X91, this.data0x91);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X92, this.data0x92);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X93, this.data0x93);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA0, this.data0xA0);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA1, this.data0xA1);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA2, this.data0xA2);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA3, this.data0xA3);
      SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA4, this.data0xA4);
   }

   private void setDriveInfo0x82() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
      int var2 = this.getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function50");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append((float)DataHandleUtils.getMsbLsbResult(var4[2], var4[3]) / 10.0F).append("kWh/100Km").toString()));
      var2 = this.getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
      var1 = this.getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function51");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append((float)DataHandleUtils.getMsbLsbResult(var4[4], var4[5]) / 10.0F).append("kWh").toString()));
      var1 = this.getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
      var2 = this.getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function52");
      StringBuilder var6 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var6.append((float)DataHandleUtils.getMsbLsbResult(var7[6], var7[7]) / 10.0F).append("kWh").toString()));
      var2 = this.getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
      var1 = this.getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function53");
      var6 = new StringBuilder();
      var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var6.append((float)DataHandleUtils.getMsbLsbResult(var7[8], var7[9]) / 10.0F).append("kWh").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadarInfo0x23() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLeftRadarInfo0x24() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setLeftRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setOriginalCarWorkModle0x10() {
      if (this.is0x10DataChange()) {
         GeneralOriginalCarDeviceData.runningState = this.getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
         GeneralOriginalCarDeviceData.cdStatus = this.getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
         GeneralOriginalCarDeviceData.discStatus = this.getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3));
         this.updateOriginalCarDeviceActivity((Bundle)null);
         if ("Power Off".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            this.cleanDevice();
            return;
         }

         if (this.isDeviceStatusSame("AUX")) {
            this.exitAuxIn2();
            this.setOriginalOtherPage();
         }
      }

   }

   private void setOriginalCdPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_artist", "_186_play_modle", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_play_time", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"folder", "wma", "mp3", "scane"}, true);
   }

   private void setOriginalOtherPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, (String[])null, false);
   }

   private void setOriginalRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"rds", "scane", "st", "auto_p"}, false);
   }

   private void setPanel360Info() {
      if (this.isPanoramicInfoChange()) {
         ArrayList var4 = new ArrayList();
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         boolean var2;
         if (var1 == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(0, var2));
         if (this.mCanBusInfoInt[2] == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(1, var2));
         if (this.mCanBusInfoInt[2] == 3) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.add(new PanoramicBtnUpdateEntity(2, var2));
         var2 = var3;
         if (this.mCanBusInfoInt[2] == 4) {
            var2 = true;
         }

         var4.add(new PanoramicBtnUpdateEntity(3, var2));
         GeneralParkData.dataList = var4;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setPanelButton0xA0() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 5) {
         if (var1 != 6) {
            if (var1 != 64) {
               if (var1 != 128) {
                  if (var1 != 130) {
                     if (var1 == 131) {
                        this.realKeyClick4(152);
                     }
                  } else {
                     Intent var2 = new Intent(this.mContext, SettingActivity.class);
                     var2.addFlags(268435456);
                     this.mContext.startActivity(var2);
                  }
               } else {
                  this.realKeyClick4(52);
               }
            } else {
               this.realKeyClick4(134);
            }
         } else {
            this.realKeyClick4(7);
         }
      } else {
         this.realKeyClick4(8);
      }

   }

   private void setRadarInfo0x22() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRightRadarInfo0x25() {
      RadarInfoUtil.mMinIsClose = false;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRightRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSOS0x91() {
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 1}, new byte[]{31, 33, 36, 39, 32, 37, 35, 33, 35, 30, 34}));
   }

   private void setTireData0x61() {
      ArrayList var1 = new ArrayList();
      var1.add(this.getTireEntity(0, this.getTirePressure(this.mCanBusInfoInt[3])));
      var1.add(this.getTireEntity(1, this.getTirePressure(this.mCanBusInfoInt[4])));
      var1.add(this.getTireEntity(2, this.getTirePressure(this.mCanBusInfoInt[5])));
      var1.add(this.getTireEntity(3, this.getTirePressure(this.mCanBusInfoInt[6])));
      GeneralTireData.dataList = var1;
      this.updateTirePressureActivity((Bundle)null);
      String var2 = this.getTisWarmMsg(this.mCanBusInfoInt[2]);
      if (!TextUtils.isEmpty(var2)) {
         GeneralDisplayMsgData.displayMsg = var2;
         this.sendDisplayMsgView(this.mContext);
      }

   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(var1[3], var1[2], 0, 5400, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setVolumeInfo0x0A() {
      boolean var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      int[] var4 = this.mCanBusInfoInt;
      var4[2] = this.blockBit(var4[2], 7);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
      int var1 = var2 >> 4;
      var2 &= 15;
      String var6;
      if (var3) {
         var6 = Integer.toString(var1) + Integer.toString(var2);
      } else {
         var6 = "";
      }

      if (var1 <= 9 && var2 <= 9 || !var3) {
         ArrayList var5 = new ArrayList();
         var5.add(new OriginalCarDeviceUpdateEntity(this.getOriginalCarDevicePageUiSet().getItems().size() - 1, var6));
         GeneralOriginalCarDeviceData.mList = var5;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void settingInfo0x73() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_0"), Integer.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_1"), Integer.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_2"), Integer.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_3"), Integer.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_4"), Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_5"), Integer.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), this.getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_6"), Integer.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function33"), Integer.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function36"), Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function39"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function43"), Integer.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function44"), Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]))));
      var1.add(new SettingUpdateEntity(this.getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), this.getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function45"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2) - 1));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void showOriginalDevice() {
      Intent var1 = new Intent();
      var1.setComponent(Constant.OriginalDeviceActivity);
      var1.setFlags(268435456);
      this.mContext.startActivity(var1);
   }

   private void updateOriginalDeviceWithInit() {
      Bundle var1 = new Bundle();
      var1.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var1);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      if (this.getCurrentCanDifferentId() == 3) {
         SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      } else {
         SelectCanTypeUtil.disableApp(var1, Constant.OriginalDeviceActivity);
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, -1, -1, -1, -1, -1, -1, -1});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 1}, var1));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.mCallStatus = 1;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 1}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.mCallStatus = 2;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 1}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.mCallStatus = var1;
      byte var10;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 4) {
               var10 = 0;
            } else if (this.isInComeBtPhone) {
               var10 = 2;
            } else {
               var10 = 4;
            }
         } else {
            this.isInComeBtPhone = false;
            var10 = 3;
         }
      } else {
         this.isInComeBtPhone = true;
         var10 = 1;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, var10, 1}, var2));
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      int var4 = this.mCallStatus;
      if (var4 == 1) {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 2, 1}, var1));
      } else if (var4 == 2) {
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 1}, var1));
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 10) {
         if (var3 != 16) {
            if (var3 != 40) {
               if (var3 != 48) {
                  if (var3 != 64) {
                     if (var3 != 97) {
                        if (var3 != 127) {
                           if (var3 != 145) {
                              if (var3 != 160) {
                                 if (var3 != 61) {
                                    if (var3 != 62) {
                                       if (var3 != 129) {
                                          if (var3 != 130) {
                                             switch (var3) {
                                                case 32:
                                                   this.setAccused0x20();
                                                   break;
                                                case 33:
                                                   if (this.isAirMsgRepeat(var2)) {
                                                      return;
                                                   }

                                                   this.setAirData0x21();
                                                   break;
                                                case 34:
                                                   this.setRadarInfo0x22();
                                                   break;
                                                case 35:
                                                   this.setFrontRadarInfo0x23();
                                                   break;
                                                case 36:
                                                   this.setLeftRadarInfo0x24();
                                                   break;
                                                case 37:
                                                   this.setRightRadarInfo0x25();
                                                   break;
                                                default:
                                                   switch (var3) {
                                                      case 113:
                                                         this.setCarSetting0x71();
                                                         break;
                                                      case 114:
                                                         this.setCarSetting0x72();
                                                         break;
                                                      case 115:
                                                         this.settingInfo0x73();
                                                         break;
                                                      case 116:
                                                         this.setPanel360Info();
                                                   }
                                             }
                                          } else {
                                             this.setDriveInfo0x82();
                                          }
                                       } else {
                                          this.setCarData0x81();
                                       }
                                    } else {
                                       this.set0x3EWheelInfo();
                                    }
                                 } else {
                                    this.set0x3DWheelKeyInfo();
                                 }
                              } else {
                                 this.setPanelButton0xA0();
                              }
                           } else {
                              this.setSOS0x91();
                           }
                        } else {
                           this.setVersionInfo();
                        }
                     } else {
                        this.setTireData0x61();
                     }
                  } else {
                     this.set0x40ModeInfo();
                  }
               } else {
                  this.setTrackInfo();
               }
            } else {
               if (this.isDoorMsgRepeat(var2)) {
                  return;
               }

               this.setCarMessage0x28();
            }
         } else {
            this.setOriginalCarWorkModle0x10();
         }
      } else {
         this.setVolumeInfo0x0A();
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 33, (byte)var5, (byte)var6, (byte)var3, (byte)(var2 / 3600), (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -64, 16, (byte)(var4 / 256), (byte)(var4 % 256), (byte)(var6 / 256), (byte)(var6 % 256), (byte)(var2 / 3600), (byte)(var2 / 60 % 60), (byte)(var2 % 60)});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      int var2 = this.getCurrentEachCanId();
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 0});
      }

      GeneralTireData.isHaveSpareTire = false;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 113, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 114, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, -127, 0});
      this.initAmplifierData();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var3 = var17 + 1;
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, (byte)(var4 / 256), (byte)(var4 % 256), (byte)(var3 / 256), (byte)(var3 % 256), var5, var6, var7});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.contains("FM")) {
         var5 = (int)(Float.parseFloat(var3) * 100.0F);
      } else if (var2.contains("AM")) {
         var5 = Integer.parseInt(var3);
      } else {
         var5 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, this.getAllBandTypeData(var2), (byte)(var5 % 256), (byte)(var5 / 256), (byte)var1});
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if ((SourceConstantsDef.SOURCE_ID.ATV.name().equals(var1) || SourceConstantsDef.SOURCE_ID.AUX1.name().equals(var1) || SourceConstantsDef.SOURCE_ID.BTAUDIO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var1) || SourceConstantsDef.SOURCE_ID.MPEG.name().equals(var1) || SourceConstantsDef.SOURCE_ID.DTV.name().equals(var1) || SourceConstantsDef.SOURCE_ID.MUSIC.name().equals(var1) || SourceConstantsDef.SOURCE_ID.FM.name().equals(var1) || SourceConstantsDef.SOURCE_ID.VIDEO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.NAVIAUDIO.name().equals(var1)) && this.getCurrentCanDifferentId() == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void toast(String var1) {
      Toast.makeText(this.getActivity(), var1, 0).show();
   }
}
