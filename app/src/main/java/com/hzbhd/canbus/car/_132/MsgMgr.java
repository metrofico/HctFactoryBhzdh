package com.hzbhd.canbus.car._132;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mData1;

   private void cleanDevice() {
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.rdm_disc = false;
      GeneralOriginalCarDeviceData.rpt_track = false;
      GeneralOriginalCarDeviceData.startTime = "     ";
      GeneralOriginalCarDeviceData.endTime = "     ";
      GeneralOriginalCarDeviceData.progress = 0;
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDeviceUpdateEntity(0, " "));
      var1.add(new OriginalCarDeviceUpdateEntity(1, " "));
      var1.add(new OriginalCarDeviceUpdateEntity(2, " "));
      var1.add(new OriginalCarDeviceUpdateEntity(3, " "));
      GeneralOriginalCarDeviceData.mList = var1;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void cleanSongList() {
      if (GeneralOriginalCarDeviceData.songList != null) {
         GeneralOriginalCarDeviceData.songList.clear();
      }

   }

   private String getCdTrackInfo(byte[] var1) {
      int var3 = var1.length - 2;
      byte[] var4 = new byte[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         var4[var2] = var1[var2 + 2];
      }

      return new String(var4);
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick2(var2, var1, var3[2], var3[3]);
   }

   private void realKeyClick2(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var3, var1, var2[2], var2[3]);
   }

   private void setCdStatus() {
      OriginalCarDevicePageUiSet var2 = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      if (var2 != null) {
         int var1 = this.mCanBusInfoInt[2];
         if (var1 != 1) {
            if (var1 != 4) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralOriginalCarDeviceData.runningState = "PAUSE";
                     var2.setRowBottomBtnAction(new String[]{"left", "play", "right"});
                  }
               } else {
                  GeneralOriginalCarDeviceData.runningState = "PLAY";
                  var2.setRowBottomBtnAction(new String[]{"left", "pause", "right"});
               }
            } else {
               GeneralOriginalCarDeviceData.runningState = "READING";
            }
         } else {
            GeneralOriginalCarDeviceData.runningState = "DISC OUT";
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) == 0) {
            GeneralOriginalCarDeviceData.cdStatus = "CD";
         } else {
            this.cleanDevice();
            this.cleanSongList();
            GeneralOriginalCarDeviceData.cdStatus = "AUX";
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
               GeneralOriginalCarDeviceData.cdStatus = "AUX ON";
            } else {
               GeneralOriginalCarDeviceData.cdStatus = "AUX OFF";
            }
         }

         GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
         GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
         this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
         Bundle var3 = new Bundle();
         var3.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var3);
      }
   }

   private void setCdTime() {
      ArrayList var3 = new ArrayList();

      try {
         StringBuilder var6 = new StringBuilder();
         int[] var5 = this.mCanBusInfoInt;
         OriginalCarDeviceUpdateEntity var4 = new OriginalCarDeviceUpdateEntity(0, var6.append(var5[10] * 256 + var5[11]).append("").toString());
         var3.add(var4);
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      StringBuilder var11 = new StringBuilder();
      int[] var10 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(1, var11.append(var10[2] * 256 + var10[3]).append("").toString()));
      GeneralOriginalCarDeviceData.mList = var3;
      int[] var8 = this.mCanBusInfoInt;
      int var2 = var8[4] * 3600 + var8[5] * 60 + var8[6];
      int var1 = var8[7] * 3600 + var8[8] * 60 + var8[9];
      DecimalFormat var9 = new DecimalFormat("00");
      if (var1 >= 3600) {
         GeneralOriginalCarDeviceData.startTime = var9.format((long)(var1 / 60 / 60)) + ":" + var9.format((long)(var1 % 3600 / 60)) + ":" + var9.format((long)(var1 % 60));
      } else {
         GeneralOriginalCarDeviceData.startTime = var9.format((long)(var1 % 3600 / 60)) + ":" + var9.format((long)(var1 % 60));
      }

      if (var2 >= 3600) {
         GeneralOriginalCarDeviceData.endTime = var9.format((long)(var2 / 60 / 60)) + ":" + var9.format((long)(var2 % 3600 / 60)) + ":" + var9.format((long)(var2 % 60));
      } else {
         GeneralOriginalCarDeviceData.endTime = var9.format((long)(var2 % 3600 / 60)) + ":" + var9.format((long)(var2 % 60));
      }

      if (var2 == 0) {
         GeneralOriginalCarDeviceData.progress = 0;
      } else {
         GeneralOriginalCarDeviceData.progress = var1 * 100 / var2;
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setCdTrackInfo() {
      ArrayList var1 = new ArrayList();
      if (this.mCanBusInfoInt[2] == 1) {
         var1.add(new OriginalCarDeviceUpdateEntity(2, this.getCdTrackInfo(this.mCanBusInfoByte)));
      } else {
         var1.add(new OriginalCarDeviceUpdateEntity(3, this.getCdTrackInfo(this.mCanBusInfoByte)));
      }

      GeneralOriginalCarDeviceData.mList = var1;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setCdTrackList() {
      ArrayList var1 = new ArrayList();
      var1.add(new SongListEntity(this.getCdTrackInfo(this.mCanBusInfoByte)));
      GeneralOriginalCarDeviceData.songList = var1;
      if (this.getCurrentCanDifferentId() == 1) {
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setDoorData0x02() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      ArrayList var5 = new ArrayList();
      int var1 = this.getCurrentCanDifferentId();
      String var3 = "ON";
      String var4 = "Not P";
      if (var1 == 1) {
         if (!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            var4 = "P";
         }

         var5.add(new DriverUpdateEntity(0, 2, var4));
         if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            var3 = "OFF";
         }

         var5.add(new DriverUpdateEntity(0, 3, var3));
      } else {
         if (!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            var4 = "P";
         }

         var5.add(new DriverUpdateEntity(0, 0, var4));
         if (!DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            var3 = "OFF";
         }

         var5.add(new DriverUpdateEntity(0, 1, var3));
      }

      this.updateGeneralDriveData(var5);
      var1 = this.mData1;
      int var2 = this.mCanBusInfoInt[2];
      if (var1 != var2) {
         this.mData1 = var2;
         if (DataHandleUtils.getBoolBit0(var2)) {
            this.updateDoorView(this.mContext);
         }
      } else {
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setDrivingData() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var2.append((float)((var3[3] * 256 + var3[2]) / 100)).append("Km/h").toString()));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var2.append(var3[5] * 256 + var3[4]).append("rpm").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanBusInfoInt;
      this.updateSpeedInfo((var4[3] * 256 + var4[2]) / 100);
   }

   private void setSettingData0x40() {
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
      int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
      byte var3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      byte var7 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
      int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
      ArrayList var12 = new ArrayList();

      for(int var1 = 0; var1 < 10; ++var1) {
         var12.add(new SettingUpdateEntity(0, var1, (new int[]{var4, var8, var11 - 1, var6 - 1, var3, var7, var10 - 1, var5 - 1, var9 - 1, var2})[var1]));
      }

      this.updateGeneralSettingData(var12);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 4608, 16);
      if (this.getCurrentCanDifferentId() == 1) {
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        switch (var1) {
                           case 8:
                              this.realKeyClick(187);
                              break;
                           case 9:
                              this.realKeyClick(14);
                              break;
                           case 10:
                              this.realKeyClick(188);
                              break;
                           default:
                              switch (var1) {
                                 case 32:
                                    this.realKeyClick(2);
                                    break;
                                 case 33:
                                    this.realKeyClick(79);
                                    break;
                                 case 34:
                                    this.realKeyClick(52);
                                    break;
                                 case 35:
                                    this.realKeyClick(50);
                                    break;
                                 case 36:
                                    this.realKeyClick(128);
                                    break;
                                 case 37:
                                    this.realKeyClick(49);
                                    break;
                                 case 38:
                                    this.realKeyClick(47);
                                    break;
                                 case 39:
                                    this.realKeyClick(48);
                                    break;
                                 case 40:
                                    this.realKeyClick(45);
                                    break;
                                 case 41:
                                    this.realKeyClick(46);
                                    break;
                                 case 42:
                                    this.realKeyClick(3);
                                    break;
                                 case 43:
                                    this.realKeyClick2(46);
                                    break;
                                 case 44:
                                    this.realKeyClick2(45);
                                    break;
                                 case 45:
                                    this.realKeyClick2(7);
                                    break;
                                 case 46:
                                    this.realKeyClick2(8);
                              }
                        }
                     } else {
                        this.realKeyClick(3);
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
      if (var3 != 22) {
         if (var3 != 32) {
            if (var3 != 48) {
               if (var3 != 64) {
                  switch (var3) {
                     case 36:
                        this.setDoorData0x02();
                        break;
                     case 37:
                        this.setCdStatus();
                        break;
                     case 38:
                        this.setCdTime();
                        break;
                     case 39:
                        this.setCdTrackInfo();
                        break;
                     case 40:
                        this.setCdTrackList();
                        break;
                     case 41:
                        this.setTrackInfo();
                  }
               } else if (this.getCurrentCanDifferentId() == 1) {
                  this.setSettingData0x40();
               }
            } else {
               this.setVersionInfo();
            }
         } else {
            this.setWheelKey0x20();
         }
      } else if (this.getCurrentCanDifferentId() == 1) {
         this.setDrivingData();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -112, -1, 0});
   }

   void updateOriginalCarDevice(Bundle var1) {
      this.updateOriginalCarDeviceActivity(var1);
   }
}
