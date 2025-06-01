package com.hzbhd.canbus.car._254;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
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
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private final int DATA_TYPE = 1;
   String albumName = "";
   String artistName = "";
   DECODE_FOMART decodeFomart;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray;
   private Context mContext;
   String songName = "";

   public MsgMgr() {
      this.decodeFomart = MsgMgr.DECODE_FOMART.ASCII;
      this.mCanbusDataArray = new SparseArray();
   }

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
         LogUtil.showLog("result:" + var3);
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

   private void realKeyClick3_1(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var1, var2, var3[2], var3[3]);
   }

   private void realKeyClick3_2(Context var1, int var2) {
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var1, var2, var3[2], var3[3]);
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[3]);
   }

   private void set0x01WheelKey(Context var1) {
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     switch (var2) {
                        case 8:
                           this.realKeyLongClick1(var1, 3);
                           break;
                        case 9:
                           this.realKeyLongClick1(var1, 187);
                           break;
                        case 10:
                           this.realKeyLongClick1(var1, 14);
                           break;
                        case 11:
                           this.realKeyLongClick1(var1, 15);
                           break;
                        default:
                           switch (var2) {
                              case 32:
                                 this.realKeyLongClick1(var1, 59);
                                 break;
                              case 33:
                                 this.realKeyLongClick1(var1, 52);
                                 break;
                              case 34:
                                 this.realKeyLongClick1(var1, 128);
                                 break;
                              case 35:
                                 this.realKeyLongClick1(var1, 79);
                                 break;
                              case 36:
                                 this.realKeyLongClick1(var1, 45);
                                 break;
                              case 37:
                                 this.realKeyLongClick1(var1, 46);
                                 break;
                              case 38:
                                 this.realKeyLongClick1(var1, 47);
                                 break;
                              case 39:
                                 this.realKeyLongClick1(var1, 48);
                                 break;
                              case 40:
                                 this.realKeyLongClick1(var1, 49);
                                 break;
                              case 41:
                                 this.realKeyLongClick1(var1, 134);
                                 break;
                              case 42:
                                 this.realKeyLongClick1(var1, 50);
                                 break;
                              default:
                                 switch (var2) {
                                    case 240:
                                       this.realKeyClick3_1(var1, 7);
                                       break;
                                    case 241:
                                       this.realKeyClick3_1(var1, 8);
                                       break;
                                    case 242:
                                       this.realKeyClick3_2(var1, 48);
                                       break;
                                    case 243:
                                       this.realKeyClick3_2(var1, 47);
                                 }
                           }
                     }
                  } else {
                     this.realKeyLongClick1(var1, 45);
                  }
               } else {
                  this.realKeyLongClick1(var1, 46);
               }
            } else {
               this.realKeyLongClick1(var1, 8);
            }
         } else {
            this.realKeyLongClick1(var1, 7);
         }
      } else {
         this.realKeyLongClick1(var1, 0);
      }

   }

   private void set0x07TrackData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 4608, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x08RadarData(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var4 = this.mCanBusInfoInt;
         int var3 = var4[3];
         int var2 = var4[4];
         RadarInfoUtil.setFrontRadarLocationData(4, var3, var2, var2, var4[5]);
         var4 = this.mCanBusInfoInt;
         var2 = var4[6];
         var3 = var4[7];
         RadarInfoUtil.setRearRadarLocationData(4, var2, var3, var3, var4[8]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void setCanInfo(Context var1) {
      int var2 = this.mCanBusInfoInt[1];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 16) {
               if (var2 != 17) {
                  if (var2 != 103) {
                     if (var2 != 104) {
                        if (var2 != 106) {
                           if (var2 != 127) {
                              switch (var2) {
                                 case 4:
                                    this.setOriginalCarDeviceInfo0x04();
                                    break;
                                 case 5:
                                    this.setOriginalCarDeviceInfo0x05();
                                    break;
                                 case 6:
                                    this.setOriginalCarDeviceInfo0x06(var1);
                                    break;
                                 case 7:
                                    this.set0x07TrackData(var1);
                                    break;
                                 case 8:
                                    this.set0x08RadarData(var1);
                                    break;
                                 case 9:
                                    this.setSettingData0x09();
                                    break;
                                 case 10:
                                    this.setSettingData0x0a();
                                    break;
                                 case 11:
                                    this.setDriveData0x0b();
                                    break;
                                 case 12:
                                    this.setDriveData0x0c();
                                    break;
                                 case 13:
                                    this.setSettingData0x0d();
                              }
                           } else {
                              this.setVersionInfo();
                           }
                        } else {
                           this.setSettingData0x6a();
                        }
                     } else {
                        this.setSettingData0x68();
                     }
                  } else {
                     this.setSettingData0x67();
                  }
               } else {
                  this.setDriveData0x11();
               }
            } else {
               this.setDriveData0x10();
            }
         } else {
            this.setDoorData0x02();
         }
      } else {
         this.set0x01WheelKey(var1);
      }

   }

   private void setDoorData0x02() {
      GeneralDoorData.isShowEsp = true;
      GeneralDoorData.isShowIstop = true;
      GeneralDoorData.isShowLittleLight = true;
      GeneralDoorData.isShowWaterTemp = true;
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralDoorData.isEspOn = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralDoorData.isLittleLightOn = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralDoorData.isIstopOn = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralDoorData.isWaterTempWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
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

   private void setDriveData0x10() {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(2, 0, var3.append(var1[3] * 256 * 256 + var1[4] * 256 + var1[5]).append("KM").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveData0x11() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[2] - 40 + "℃"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginalCarDeviceInfo0x04() {
      ArrayList var3 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(0, var2.append(var1[5] * 256 + var1[6]).append("").toString()));
      GeneralOriginalCarDeviceData.mList = var3;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1) {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131768702);
      } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1) == 1) {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131768703);
      } else {
         GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131768705);
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

      DecimalFormat var7 = new DecimalFormat("00");
      int[] var8 = this.mCanBusInfoInt;
      int var2 = var8[11] * 256 + var8[12];
      var1 = var8[9] * 256 + var8[10];
      if (var2 <= var1) {
         if (var2 >= 3600) {
            GeneralOriginalCarDeviceData.startTime = var7.format((long)(var2 / 60 / 60)) + ":" + var7.format((long)(var2 % 3600 / 60)) + ":" + var7.format((long)(var2 % 60));
         } else {
            GeneralOriginalCarDeviceData.startTime = var7.format((long)(var2 % 3600 / 60)) + ":" + var7.format((long)(var2 % 60));
         }

         if (var1 >= 3600) {
            GeneralOriginalCarDeviceData.endTime = var7.format((long)(var1 / 60 / 60)) + ":" + var7.format((long)(var1 % 3600 / 60)) + ":" + var7.format((long)(var1 % 60));
         } else {
            GeneralOriginalCarDeviceData.endTime = var7.format((long)(var1 % 3600 / 60)) + ":" + var7.format((long)(var1 % 60));
         }

         if (var1 == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
         } else {
            GeneralOriginalCarDeviceData.progress = var2 * 100 / var1;
         }
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setOriginalCarDeviceInfo0x06(Context var1) {
      int[] var9 = this.mCanBusInfoInt;
      byte var3 = 3;
      int var4 = DataHandleUtils.getIntFromByteWithBit(var9[3], 4, 4);

      String var10;
      try {
         byte var2 = (byte)this.mCanBusInfoInt[2];
         ID3_TYPE var11 = MsgMgr.ID3_TYPE.ID3_SONG;
         ID3_TYPE var5 = MsgMgr.ID3_TYPE.ID3_ALBUM;
         ID3_TYPE var6 = MsgMgr.ID3_TYPE.ID3_ARTIST;
         byte[] var7 = DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 2, (byte)0);
         var10 = this.setId3InfoNeedSendCode(var2, new byte[]{0, 3, 2}, new ID3_TYPE[]{var11, var5, var6}, var4, var7);
      } catch (Exception var8) {
         var8.printStackTrace();
         var10 = null;
      }

      ArrayList var12 = new ArrayList();
      var4 = this.mCanBusInfoInt[2];
      if (var4 != 0) {
         if (var4 != 2) {
            if (var4 != 3) {
               var3 = 0;
            } else {
               var3 = 5;
            }
         } else {
            var3 = 4;
         }
      }

      var12.add(new OriginalCarDeviceUpdateEntity(var3, var10));
      GeneralOriginalCarDeviceData.mList = var12;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setSettingData0x09() {
      int var29 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      int var2 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3));
      int var9 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2));
      int var3 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
      int var27 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var24 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      int var20 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      int var16 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2));
      int var4 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2));
      int var25 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
      int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      int var26 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
      int var30 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
      int var12 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2));
      int var28 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
      int var21 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3));
      int var22 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3));
      int var11 = this.mCanBusInfoInt[6];
      int[] var32 = this.mCanBusInfoInt;
      int var1 = var32[6];
      int var17 = var32[7];
      var32 = this.mCanBusInfoInt;
      int var7 = var32[7];
      int var8 = var32[8];
      var32 = this.mCanBusInfoInt;
      int var18 = var32[8];
      int var19 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(var32[9]));
      int var23 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]));
      int var31 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2));
      int var13 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]));
      int var15 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 2));
      ArrayList var33 = new ArrayList();
      var33.add(new SettingUpdateEntity(0, 0, var29));
      var33.add(new SettingUpdateEntity(0, 1, var2));
      var33.add(new SettingUpdateEntity(0, 2, var9));
      var33.add(new SettingUpdateEntity(0, 3, var3));
      var33.add(new SettingUpdateEntity(0, 4, var27));
      var33.add(new SettingUpdateEntity(0, 5, var14));
      var33.add(new SettingUpdateEntity(0, 6, var24));
      var33.add(new SettingUpdateEntity(0, 7, var20));
      var33.add(new SettingUpdateEntity(0, 8, var16));
      var33.add(new SettingUpdateEntity(0, 9, var4));
      var33.add(new SettingUpdateEntity(0, 10, var25));
      var33.add(new SettingUpdateEntity(0, 11, var30));
      var33.add(new SettingUpdateEntity(0, 12, var5));
      var33.add(new SettingUpdateEntity(0, 13, var12));
      var33.add(new SettingUpdateEntity(0, 14, var28));
      var33.add(new SettingUpdateEntity(0, 15, var21));
      var33.add(new SettingUpdateEntity(0, 16, var22));
      var33.add(new SettingUpdateEntity(1, 0, var19));
      var33.add(new SettingUpdateEntity(1, 1, var23));
      var33.add(new SettingUpdateEntity(1, 2, var31));
      var33.add(new SettingUpdateEntity(1, 3, var13));
      var33.add(new SettingUpdateEntity(1, 4, var15));
      var33.add(new SettingUpdateEntity(2, 0, var10));
      var33.add(new SettingUpdateEntity(2, 1, var6));
      var33.add(new SettingUpdateEntity(2, 2, var26));
      var33.add((new SettingUpdateEntity(2, 3, String.valueOf(var11))).setProgress(var1));
      var33.add((new SettingUpdateEntity(2, 4, String.valueOf(var17))).setProgress(var7));
      var33.add((new SettingUpdateEntity(2, 5, String.valueOf(var8))).setProgress(var18));
      this.updateGeneralSettingData(var33);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x0a() {
      ArrayList var3 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 13) {
            var2 = null;
         } else {
            var2 = this.mContext.getResources().getString(2131769223);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131769222);
      }

      var3.add(new DriverUpdateEntity(2, 5, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSettingData0x0d() {
      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
      int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
      int var6 = this.getIndexBy5Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 3));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
      int var13 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2));
      int var7 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      int var11 = this.getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2));
      int var3 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2));
      int var10 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var1 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2));
      int var14 = this.getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 2));
      int var15 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]));
      ArrayList var16 = new ArrayList();
      var16.add(new SettingUpdateEntity(3, 1, var4));
      var16.add(new SettingUpdateEntity(3, 2, var6));
      var16.add(new SettingUpdateEntity(3, 3, var2));
      var16.add(new SettingUpdateEntity(4, 0, var9));
      var16.add(new SettingUpdateEntity(4, 1, var5));
      var16.add(new SettingUpdateEntity(4, 2, var13));
      var16.add(new SettingUpdateEntity(4, 3, var7));
      var16.add(new SettingUpdateEntity(4, 4, var11));
      var16.add(new SettingUpdateEntity(4, 5, var3));
      var16.add(new SettingUpdateEntity(4, 6, var12));
      var16.add(new SettingUpdateEntity(5, 0, var10));
      var16.add(new SettingUpdateEntity(5, 1, var1));
      var16.add(new SettingUpdateEntity(5, 2, var14));
      var16.add(new SettingUpdateEntity(5, 3, var15));
      var16.add(new SettingUpdateEntity(5, 4, var8));
      this.updateGeneralSettingData(var16);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x67() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var2 = null;
               } else {
                  var2 = this.mContext.getResources().getString(2131769291);
               }
            } else {
               var2 = this.mContext.getResources().getString(2131769290);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131769289);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131769288);
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(2, 2, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSettingData0x68() {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(2, 3, var3.append((var1[2] + var1[3] * 256) * 10 / 10).append("rpm").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setSettingData0x6a() {
      ArrayList var2 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var1 = this.mCanBusInfoInt;
      var2.add(new DriverUpdateEntity(2, 4, var3.append((var1[2] + var1[3] * 256) * 10 / 10).append("km/h").toString()));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
      var1 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var1[3], var1[2]));
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
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;

      try {
         this.setCanInfo(var1);
      } catch (Exception var3) {
         Log.e("CanBusError", var3.toString());
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 127, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 17});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 18});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 19});
   }

   public String setId3InfoNeedSendCode(byte var1, byte[] var2, ID3_TYPE[] var3, int var4, byte[] var5) throws Exception {
      String var7 = null;

      for(int var6 = 0; var6 < var2.length; ++var6) {
         if (var1 == var2[var6]) {
            if (var3[var6] == MsgMgr.ID3_TYPE.ID3_SONG) {
               var7 = this.decodeBytes2StrNeedSendData(var4, var5);
               this.songName = var7;
            } else if (var3[var6] == MsgMgr.ID3_TYPE.ID3_ARTIST) {
               var7 = this.decodeBytes2StrNeedSendData(var4, var5);
               this.artistName = var7;
            } else {
               var7 = this.decodeBytes2StrNeedSendData(var4, var5);
               this.albumName = var7;
            }
         }
      }

      return var7;
   }

   public static enum DECODE_FOMART {
      private static final DECODE_FOMART[] $VALUES;
      ASCII,
      UN_BIG,
      UN_SMALL,
      UTF_8;

      static {
         DECODE_FOMART var2 = new DECODE_FOMART("ASCII", 0);
         ASCII = var2;
         DECODE_FOMART var0 = new DECODE_FOMART("UN_SMALL", 1);
         UN_SMALL = var0;
         DECODE_FOMART var1 = new DECODE_FOMART("UN_BIG", 2);
         UN_BIG = var1;
         DECODE_FOMART var3 = new DECODE_FOMART("UTF_8", 3);
         UTF_8 = var3;
         $VALUES = new DECODE_FOMART[]{var2, var0, var1, var3};
      }
   }

   public static enum ID3_TYPE {
      private static final ID3_TYPE[] $VALUES;
      ID3_ALBUM,
      ID3_ARTIST,
      ID3_SONG;

      static {
         ID3_TYPE var0 = new ID3_TYPE("ID3_SONG", 0);
         ID3_SONG = var0;
         ID3_TYPE var1 = new ID3_TYPE("ID3_ARTIST", 1);
         ID3_ARTIST = var1;
         ID3_TYPE var2 = new ID3_TYPE("ID3_ALBUM", 2);
         ID3_ALBUM = var2;
         $VALUES = new ID3_TYPE[]{var0, var1, var2};
      }
   }
}
