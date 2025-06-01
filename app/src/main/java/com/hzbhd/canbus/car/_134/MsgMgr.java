package com.hzbhd.canbus.car._134;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static boolean isDoorFirst;
   private List OriginalCarDeviceInfo0x06List = new ArrayList();
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;

   private String decodeBytes2StrNeedSendData(int var1, byte[] var2) throws Exception {
      LogUtil.showLog("code:" + var1);
      String var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var3 = "";
               } else {
                  var3 = new String(var2, "UTF-8");
               }
            } else {
               var3 = DataHandleUtils.Byte2Unicode(var2, false);
            }
         } else {
            var3 = DataHandleUtils.Byte2Unicode(var2, true);
         }
      } else {
         var3 = new String(var2, "ascii");
      }

      return var3;
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy3Bit(int var1) {
      LogUtil.showLog("getIndexBy3Bit:" + var1);
      byte var2 = 2;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getIndexBy4Bit(int var1) {
      byte var2 = 3;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private int getIndexBy5Bit(int var1) {
      byte var2 = 4;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = 1;
            return var2;
         }

         if (var1 == 2) {
            var2 = 2;
            return var2;
         }

         if (var1 == 3) {
            var2 = 3;
            return var2;
         }

         if (var1 == 4) {
            return var2;
         }
      }

      var2 = 0;
      return var2;
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick2(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick3(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var3, var1, var2[2], var2[3]);
   }

   private void setDoorData0x02() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      this.updateDoorView(this.mContext);
   }

   private void setDriveData0x0b() {
      ArrayList var1 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, String.valueOf((float)((double)(var2[2] * 256 + var2[3]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, String.valueOf((float)((double)(var2[4] * 256 + var2[5]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 2, String.valueOf((float)((double)(var2[6] * 256 + var2[7]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 3, String.valueOf((float)((double)(var2[8] * 256 + var2[9]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 4, String.valueOf((float)((double)(var2[10] * 256 + var2[11]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 5, String.valueOf((float)((double)(var2[12] * 256 + var2[13]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 6, String.valueOf((float)((double)(var2[14] * 256 + var2[15]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 7, String.valueOf((float)((double)(var2[16] * 256 + var2[17]) * 0.1 * 10.0) / 10.0F)));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x0c() {
      ArrayList var1 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 0, String.valueOf((float)((double)(var2[2] * 256 + var2[3]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 1, String.valueOf((float)((double)(var2[4] * 256 + var2[5]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 2, String.valueOf((float)((double)(var2[6] * 256 + var2[7]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 3, String.valueOf((float)((double)(var2[8] * 256 + var2[9]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 4, String.valueOf((float)((double)(var2[10] * 256 + var2[11]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 5, String.valueOf((float)((double)(var2[12] * 256 + var2[13]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 6, String.valueOf((float)((double)(var2[14] * 256 + var2[15]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 7, String.valueOf((float)((double)(var2[16] * 256 + var2[17]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 8, String.valueOf((float)((double)(var2[18] * 256 + var2[19]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 9, String.valueOf((float)((double)(var2[20] * 256 + var2[21]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 10, String.valueOf((float)((double)(var2[22] * 256 + var2[23]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 11, String.valueOf((float)((double)(var2[24] * 256 + var2[25]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 12, String.valueOf((float)((double)(var2[26] * 256 + var2[27]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 13, String.valueOf((float)((double)(var2[28] * 256 + var2[29]) * 0.1 * 10.0) / 10.0F)));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 14, String.valueOf((float)((double)(var2[30] * 256 + var2[31]) * 0.1 * 10.0) / 10.0F)));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginalCarDeviceInfo0x04() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new OriginalCarDeviceUpdateEntity(0, var2.append(var3[5] * 256 + var3[6]).append("").toString()));
      GeneralOriginalCarDeviceData.mList = var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1) {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131768702);
      } else {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131768704);
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 0) {
         GeneralOriginalCarDeviceData.discStatus = this.mContext.getResources().getString(2131767999);
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 1) {
         GeneralOriginalCarDeviceData.discStatus = this.mContext.getResources().getString(2131767998);
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setOriginalCarDeviceInfo0x05() {
      ArrayList var4 = new ArrayList();
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(1, var5.append(var6[5] * 256 + var6[6]).append("").toString()));
      var5 = new StringBuilder();
      var6 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(2, var5.append(var6[7] * 256 + var6[8]).append("").toString()));
      GeneralOriginalCarDeviceData.mList = var4;
      boolean var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_off = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_track = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rpt_fold = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_off = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_fold = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      GeneralOriginalCarDeviceData.rdm_disc = var3;
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(2131769769);
                  }
               } else {
                  GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(2131768134);
               }
            } else {
               GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(2131767997);
            }
         } else {
            GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(2131767996);
         }
      } else {
         GeneralOriginalCarDeviceData.runningState = this.mContext.getResources().getString(2131768000);
      }

      DecimalFormat var8 = new DecimalFormat("00");
      int[] var7 = this.mCanBusInfoInt;
      var1 = var7[11] * 256 + var7[12];
      int var2 = var7[9] * 256 + var7[10];
      if (var1 <= var2) {
         if (var1 >= 3600) {
            GeneralOriginalCarDeviceData.startTime = var8.format((long)(var1 / 60 / 60)) + ":" + var8.format((long)(var1 % 3600 / 60)) + ":" + var8.format((long)(var1 % 60));
         } else {
            GeneralOriginalCarDeviceData.startTime = var8.format((long)(var1 % 3600 / 60)) + ":" + var8.format((long)(var1 % 60));
         }

         if (var2 >= 3600) {
            GeneralOriginalCarDeviceData.endTime = var8.format((long)(var2 / 60 / 60)) + ":" + var8.format((long)(var2 % 3600 / 60)) + ":" + var8.format((long)(var2 % 60));
         } else {
            GeneralOriginalCarDeviceData.endTime = var8.format((long)(var2 % 3600 / 60)) + ":" + var8.format((long)(var2 % 60));
         }

         if (var2 == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
         } else {
            GeneralOriginalCarDeviceData.progress = var1 * 100 / var2;
         }
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setOriginalCarDeviceInfo0x06() {
      int[] var3 = this.mCanBusInfoInt;
      byte var1 = 3;
      int var2 = DataHandleUtils.getIntFromByteWithBit(var3[3], 4, 4);

      String var5;
      label28: {
         try {
            if (this.mCanBusInfoInt[2] != 1) {
               var5 = this.decodeBytes2StrNeedSendData(var2, DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 2, (byte)0));
               break label28;
            }
         } catch (Exception var4) {
            var4.printStackTrace();
         }

         var5 = null;
      }

      var2 = this.mCanBusInfoInt[2];
      if (var2 != 1) {
         if (var2 == 0) {
            this.OriginalCarDeviceInfo0x06List.clear();
         } else if (var2 == 2) {
            var1 = 4;
         } else {
            var1 = 5;
         }

         Log.d("cwh", "songInfo = " + var5 + "    index = " + var1);
         this.OriginalCarDeviceInfo0x06List.add(new OriginalCarDeviceUpdateEntity(var1, var5));
         GeneralOriginalCarDeviceData.mList = this.OriginalCarDeviceInfo0x06List;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }
   }

   private void setRadarInfo() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[3];
      int var2 = var3[4];
      RadarInfoUtil.setFrontRadarLocationData(4, var1, var2, var2, var3[5]);
      var3 = this.mCanBusInfoInt;
      var2 = var3[6];
      var1 = var3[7];
      RadarInfoUtil.setRearRadarLocationData(4, var2, var1, var1, var3[8]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSettingData0x09() {
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      int var15 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3));
      int var22 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2));
      int var20 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
      int var23 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      int var26 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      int var6 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2));
      int var13 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2));
      int var21 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      int var1 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
      int var19 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2));
      int var16 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
      int var17 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3));
      int var3 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3));
      byte var12 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
      int var27 = this.mCanBusInfoInt[6];
      int[] var28 = this.mCanBusInfoInt;
      int var18 = var28[6];
      int var2 = var28[7];
      var28 = this.mCanBusInfoInt;
      int var24 = var28[7];
      int var25 = var28[8];
      int var11 = this.mCanBusInfoInt[8];
      ArrayList var29 = new ArrayList();
      var29.add(new SettingUpdateEntity(0, 0, var4));
      var29.add(new SettingUpdateEntity(0, 1, var15));
      var29.add(new SettingUpdateEntity(0, 2, var22));
      var29.add(new SettingUpdateEntity(0, 3, var20));
      var29.add(new SettingUpdateEntity(0, 4, var23));
      var29.add(new SettingUpdateEntity(0, 5, var5));
      var29.add(new SettingUpdateEntity(0, 6, var10));
      var29.add(new SettingUpdateEntity(0, 7, var26));
      var29.add(new SettingUpdateEntity(0, 8, var6));
      var29.add(new SettingUpdateEntity(0, 9, var13));
      var29.add(new SettingUpdateEntity(0, 10, var21));
      var29.add(new SettingUpdateEntity(0, 11, var9));
      var29.add(new SettingUpdateEntity(0, 12, var14));
      var29.add(new SettingUpdateEntity(0, 13, var19));
      var29.add(new SettingUpdateEntity(0, 14, var16));
      var29.add(new SettingUpdateEntity(0, 15, var17));
      var29.add(new SettingUpdateEntity(0, 16, var3));
      var29.add(new SettingUpdateEntity(0, 17, Integer.valueOf(var12)));
      var29.add(new SettingUpdateEntity(1, 0, var7));
      var29.add(new SettingUpdateEntity(1, 1, var8));
      var29.add(new SettingUpdateEntity(1, 2, var1));
      var29.add((new SettingUpdateEntity(1, 3, String.valueOf(var27))).setProgress(var18));
      var29.add((new SettingUpdateEntity(1, 4, String.valueOf(var2))).setProgress(var24));
      var29.add((new SettingUpdateEntity(1, 5, String.valueOf(var25))).setProgress(var11));
      this.updateGeneralSettingData(var29);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSwc() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     switch (var1) {
                        case 8:
                           this.realKeyClick(3);
                           break;
                        case 9:
                           this.realKeyClick(187);
                           break;
                        case 10:
                           this.realKeyClick(14);
                           break;
                        case 11:
                           this.realKeyClick(15);
                           break;
                        default:
                           switch (var1) {
                              case 32:
                                 this.realKeyClick(59);
                                 break;
                              case 33:
                                 this.realKeyClick(52);
                                 break;
                              case 34:
                                 this.realKeyClick(128);
                                 break;
                              case 35:
                                 this.realKeyClick(79);
                                 break;
                              case 36:
                                 this.realKeyClick(45);
                                 break;
                              case 37:
                                 this.realKeyClick(46);
                                 break;
                              case 38:
                                 this.realKeyClick(47);
                                 break;
                              case 39:
                                 this.realKeyClick(48);
                                 break;
                              case 40:
                                 this.realKeyClick(49);
                                 break;
                              case 41:
                                 this.realKeyClick(134);
                                 break;
                              case 42:
                                 this.realKeyClick(50);
                                 break;
                              default:
                                 switch (var1) {
                                    case 240:
                                       this.realKeyClick2(7);
                                       break;
                                    case 241:
                                       this.realKeyClick2(8);
                                       break;
                                    case 242:
                                       this.realKeyClick3(48);
                                       break;
                                    case 243:
                                       this.realKeyClick3(47);
                                 }
                           }
                     }
                  } else {
                     this.realKeyClick(21);
                  }
               } else {
                  this.realKeyClick(20);
               }
            } else {
               this.realKeyClick(8);
            }
         } else {
            this.realKeyClick(7);
         }
      } else {
         this.realKeyClick(0);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public static String stringToAscii(String var0) {
      StringBuffer var2 = new StringBuffer();
      char[] var3 = var0.toCharArray();

      for(int var1 = 0; var1 < var3.length; ++var1) {
         if (var1 != var3.length - 1) {
            var2.append(var3[var1]).append(",");
         } else {
            var2.append(var3[var1]);
         }
      }

      return var2.toString();
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 4) {
               if (var3 != 5) {
                  if (var3 != 6) {
                     if (var3 != 8) {
                        if (var3 != 9) {
                           if (var3 != 11) {
                              if (var3 != 12) {
                                 if (var3 == 127) {
                                    this.setVersionInfo();
                                 }
                              } else {
                                 this.setDriveData0x0c();
                              }
                           } else {
                              this.setDriveData0x0b();
                           }
                        } else {
                           this.setSettingData0x09();
                        }
                     } else {
                        this.setRadarInfo();
                     }
                  } else {
                     this.setOriginalCarDeviceInfo0x06();
                  }
               } else {
                  this.setOriginalCarDeviceInfo0x05();
               }
            } else {
               this.setOriginalCarDeviceInfo0x04();
            }
         } else {
            if (this.isDoorMsgReturn(var2)) {
               return;
            }

            this.setDoorData0x02();
         }
      } else {
         this.setSwc();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 17});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 18});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 19});
   }
}
