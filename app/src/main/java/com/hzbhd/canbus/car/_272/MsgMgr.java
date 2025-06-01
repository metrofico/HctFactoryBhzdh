package com.hzbhd.canbus.car._272;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final String DEVICE_WORK_MODE_AUX_NAVI = "AUX/Navi";
   static final String DEVICE_WORK_MODE_CD = "CD";
   static final String DEVICE_WORK_MODE_IPOD = "IPOD";
   static final String DEVICE_WORK_MODE_RADIO = "Radio";
   static final String DEVICE_WORK_MODE_REAL_AUX = "Real AUX";
   static final String DEVICE_WORK_MODE_TEL_BT = "TEL/BT";
   static final String DEVICE_WORK_MODE_TV = "TV";
   static final String DEVICE_WORK_MODE_USB = "USB";
   private static final String SHARE_272_AMPLIFIER_BALANCE = "share_11_amplifier_balance";
   private static final String SHARE_272_AMPLIFIER_BASS = "share_11_amplifier_bass";
   static final int SHARE_272_AMPLIFIER_DATA_OFFSET = -1;
   private static final String SHARE_272_AMPLIFIER_FADE = "share_11_amplifier_fade";
   private static final String SHARE_272_AMPLIFIER_MIDDLE = "share_11_amplifier_middle";
   static final int SHARE_272_AMPLIFIER_RANGE = 30;
   private static final String SHARE_272_AMPLIFIER_TREBLE = "share_11_amplifier_treble";
   int currentPtore;
   private boolean isOnlyBit7Change;
   private boolean isShowOriginalDeviceStatusMenu;
   private boolean mAirFirst = true;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusOriginalInfoCopy;
   private Context mContext;
   private String mDeviceStatusNow;

   private void changeOriginalDevicePage(List var1, String[] var2, String[] var3, boolean var4) {
      this.cleanDevice();
      OriginalCarDevicePageUiSet var5 = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      if (var5 != null) {
         var5.setItems(var1);
         var5.setRowTopBtnAction(var2);
         var5.setRowBottomBtnAction(var3);
         var5.setHaveSongList(var4);
         Bundle var6 = new Bundle();
         var6.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var6);
         if (!this.isDeviceStatusSame("AUX/Navi")) {
            this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
         }

      }
   }

   private void cleanAllBlow() {
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
   }

   private void cleanDevice() {
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.am_st = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.scan = false;
      GeneralOriginalCarDeviceData.disc_scan = false;
      GeneralOriginalCarDeviceData.rpt = false;
      GeneralOriginalCarDeviceData.rpt_disc = false;
      GeneralOriginalCarDeviceData.rdm = false;
      GeneralOriginalCarDeviceData.rdm_disc = false;
      GeneralOriginalCarDeviceData.startTime = "     ";
      GeneralOriginalCarDeviceData.endTime = "     ";
      GeneralOriginalCarDeviceData.progress = 0;
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDeviceUpdateEntity(0, " "));
      var1.add(new OriginalCarDeviceUpdateEntity(1, " "));
      var1.add(new OriginalCarDeviceUpdateEntity(2, " "));
      var1.add(new OriginalCarDeviceUpdateEntity(3, " "));
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void cleanSongList() {
      if (GeneralOriginalCarDeviceData.songList != null) {
         GeneralOriginalCarDeviceData.songList.clear();
      }

   }

   private String discInfo(boolean var1, boolean var2) {
      if (var1) {
         String var3;
         if (var2) {
            var3 = "Rom Disk";
         } else {
            var3 = "CD Disc";
         }

         return var3;
      } else {
         return " ";
      }
   }

   private String discStatus(boolean var1) {
      String var2;
      if (var1) {
         var2 = "Have Disk";
      } else {
         var2 = "No Disk";
      }

      return var2;
   }

   private String getChangerStatus(int var1) {
      return CommUtil.getStrByResId(this.mContext, "_272_divice_status_" + var1);
   }

   private String getDeviceWorkModle(int var1) {
      String var2;
      switch (var1) {
         case 0:
            var2 = "Radio";
            break;
         case 1:
            var2 = "CD";
            break;
         case 2:
            var2 = "AUX/Navi";
            break;
         case 3:
            var2 = "USB";
            break;
         case 4:
            var2 = "TEL/BT";
            break;
         case 5:
            var2 = "IPOD";
            break;
         case 6:
            var2 = "TV";
            break;
         case 7:
            var2 = "Real AUX";
            break;
         default:
            var2 = " ";
      }

      return var2;
   }

   private String getRadioBand() {
      int var1 = this.mCanBusInfoInt[2];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        var2 = " ";
                     } else {
                        var2 = "LW";
                     }
                  } else {
                     var2 = "MW";
                  }
               } else {
                  var2 = "FM2";
               }
            } else {
               var2 = "FM1";
            }
         } else {
            var2 = "AM";
         }
      } else {
         var2 = "FM";
      }

      return var2;
   }

   private String getRadioFreq() {
      int var1;
      String var4;
      label33: {
         int[] var3 = this.mCanBusInfoInt;
         var1 = var3[4] * 256 + var3[3];
         int var2 = var3[2];
         if (var2 != 0) {
            if (var2 == 1) {
               break label33;
            }

            if (var2 != 2 && var2 != 3) {
               if (var2 != 4 && var2 != 5) {
                  var4 = " ";
                  return var4;
               }
               break label33;
            }
         }

         var4 = (float)var1 / 10.0F + "MHz";
         return var4;
      }

      var4 = var1 + "KHz";
      return var4;
   }

   private String getRadioStatus(int var1) {
      String var2;
      if (var1 != 255) {
         switch (var1) {
            case 1:
               var2 = "SEEK+";
               break;
            case 2:
               var2 = "SEEK-";
               break;
            case 3:
               var2 = "Auto SEEK";
               break;
            case 4:
               var2 = "Tune+";
               break;
            case 5:
               var2 = "Tune-";
               break;
            case 6:
               var2 = "SCAN";
               break;
            default:
               var2 = " ";
         }
      } else {
         var2 = this.mContext.getResources().getString(2131768909);
      }

      return var2;
   }

   private String getUsbStatus(int var1) {
      String var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     var2 = "";
                  } else {
                     var2 = "Invalid";
                  }
               } else {
                  var2 = "Reading";
               }
            } else {
               var2 = "Stop";
            }
         } else {
            var2 = "Play";
         }
      } else {
         var2 = "Pause";
      }

      return var2;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(var1, "share_11_amplifier_fade", 0);
         GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(var1, "share_11_amplifier_balance", 0);
         GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(var1, "share_11_amplifier_bass", 0);
         GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(var1, "share_11_amplifier_middle", 0);
         GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(var1, "share_11_amplifier_treble", 0);
      }

      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 2, (byte)(GeneralAmplifierData.leftRight + 1 + 15)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 3, (byte)(GeneralAmplifierData.frontRear + 1 + 15)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 4, (byte)(GeneralAmplifierData.bandBass + 1)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 5, (byte)(GeneralAmplifierData.bandMiddle + 1)});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -16, 6, (byte)(GeneralAmplifierData.bandTreble + 1)});
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   private boolean is0x83MsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusOriginalInfoCopy)) {
         return true;
      } else {
         this.mCanBusOriginalInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isCurrentStatus(String var1) {
      return var1.equals(GeneralOriginalCarDeviceData.cdStatus);
   }

   private boolean isDeviceStatusSame(String var1) {
      if (this.isCurrentStatus(var1) && !var1.equals(this.mDeviceStatusNow)) {
         this.mDeviceStatusNow = var1;
         this.cleanDevice();
         return true;
      } else {
         return false;
      }
   }

   private boolean isFirst() {
      if (this.mAirFirst) {
         this.mAirFirst = false;
         return GeneralAirData.power ^ true;
      } else {
         return false;
      }
   }

   private String resolveTemp(int var1) {
      String var2 = " ";
      if (var1 == 1) {
         var2 = "LO";
      } else if (var1 > 64) {
         var2 = "HI";
      } else if (var1 != 0 && var1 >= 36) {
         var2 = (float)var1 / 2.0F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private void saveAmplifierData() {
      SharePreUtil.setIntValue(this.mContext, "share_11_amplifier_fade", GeneralAmplifierData.frontRear);
      SharePreUtil.setIntValue(this.mContext, "share_11_amplifier_balance", GeneralAmplifierData.leftRight);
      SharePreUtil.setIntValue(this.mContext, "share_11_amplifier_bass", GeneralAmplifierData.bandBass);
      SharePreUtil.setIntValue(this.mContext, "share_11_amplifier_treble", GeneralAmplifierData.bandTreble);
      SharePreUtil.setIntValue(this.mContext, "share_11_amplifier_middle", GeneralAmplifierData.bandMiddle);
   }

   private void setAirData0x82() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralAirData.cycle_in_out_close = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.swing = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_temperature = this.resolveTemp(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_temperature = this.resolveTemp(this.mCanBusInfoInt[4]);
            this.cleanAllBlow();
            int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
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
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            } else {
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_head = true;
            }

            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
            GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
            GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setAmplifierData0x87() {
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 1 - 15;
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 1 - 15;
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 1;
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 1;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 1;
      this.updateAmplifierActivity((Bundle)null);
      this.saveAmplifierData();
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, this.mCanBusInfoInt[8]));
      var1.add(new SettingUpdateEntity(0, 1, this.mCanBusInfoInt[9]));
      var1.add(new SettingUpdateEntity(0, 2, this.mCanBusInfoInt[10]));
      var1.add(new SettingUpdateEntity(0, 3, this.mCanBusInfoInt[11]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setBaseTrack0x81() {
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[7], (byte)0, 0, 127, 8);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setCdcData0x86() {
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
      if (var1 == 15) {
         var2.add(new OriginalCarDeviceUpdateEntity(1, "Single CD"));
      } else if (var1 >= 1 && var1 <= 6) {
         var2.add(new OriginalCarDeviceUpdateEntity(1, "Disc" + var1));
      }

      var2.add(new OriginalCarDeviceUpdateEntity(2, Integer.toString(this.mCanBusInfoInt[5])));
      DecimalFormat var4 = new DecimalFormat("00");
      var2.add(new OriginalCarDeviceUpdateEntity(3, var4.format((long)this.mCanBusInfoInt[6]) + ":" + var4.format((long)this.mCanBusInfoInt[7])));
      GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      GeneralOriginalCarDeviceData.disc_scan = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
      GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
      GeneralOriginalCarDeviceData.rpt_disc = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
      GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8]);
      GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]);
      String var12 = this.discStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
      String var9 = this.discStatus(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]));
      String var15 = this.discStatus(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]));
      String var7 = this.discStatus(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
      String var16 = this.discStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      String var10 = this.discStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
      String var11 = this.discInfo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
      String var5 = this.discInfo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]));
      String var8 = this.discInfo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
      String var13 = this.discInfo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
      String var14 = this.discInfo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
      String var6 = this.discInfo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      var3.add(new SongListEntity(var12 + "              " + var11));
      var3.add(new SongListEntity(var9 + "              " + var5));
      var3.add(new SongListEntity(var15 + "              " + var8));
      var3.add(new SongListEntity(var7 + "              " + var13));
      var3.add(new SongListEntity(var16 + "              " + var14));
      var3.add(new SongListEntity(var10 + "              " + var6));
      if (this.isCurrentStatus("CD")) {
         GeneralOriginalCarDeviceData.songList = var3;
         GeneralOriginalCarDeviceData.mList = var2;
         GeneralOriginalCarDeviceData.runningState = this.getChangerStatus(this.mCanBusInfoInt[9]);
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setDrivingData() {
      int var1 = this.mCanBusInfoInt[5];
      String var2;
      if (var1 == 0) {
         var2 = "OFF";
      } else if (var1 == 100) {
         var2 = "ON";
      } else {
         var2 = this.mCanBusInfoInt[5] + "";
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[4] + "Km/h"));
      var3.add(new DriverUpdateEntity(0, 1, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginalCdPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_number_disc", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_track", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_play_time", ""));
      this.changeOriginalDevicePage(var1, new String[]{"am", "fm1", "fm2", "cdc", "navi", "usb_box", "aux", "scan", "disc_scan", "rpt", "rpt_disc", "rdm", "rdm_disc"}, new String[]{"prev_disc", "left", "up", "play", "down", "right", "next_disc"}, true);
   }

   private void setOriginalHeadUnit0x83() {
      if (this.isShowOriginalDeviceStatusMenu) {
         GeneralOriginalCarDeviceData.cdStatus = this.getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
         if (this.isDeviceStatusSame("Radio")) {
            this.cleanSongList();
            this.setOriginalRadioPage();
         }

         if (this.isDeviceStatusSame("CD")) {
            this.cleanSongList();
            this.setOriginalCdPage();
         }

         if (this.isDeviceStatusSame("USB")) {
            this.cleanSongList();
            this.setOriginalUsbPage();
         }

         if (!"Radio".equals(GeneralOriginalCarDeviceData.cdStatus) && !"CD".equals(GeneralOriginalCarDeviceData.cdStatus) && !"USB".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            this.setOriginalOtherPage();
         }

         if (this.mDeviceStatusNow != GeneralOriginalCarDeviceData.cdStatus) {
            this.mDeviceStatusNow = GeneralOriginalCarDeviceData.cdStatus;
            this.cleanDevice();
         }

         ArrayList var2 = new ArrayList();
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
         if (var1 == 127) {
            var2.add(new OriginalCarDeviceUpdateEntity(0, CommUtil.getStrByResId(this.mContext, "mute")));
         } else if (var1 >= 0 && var1 <= 40) {
            var2.add(new OriginalCarDeviceUpdateEntity(0, Integer.toString(var1)));
         }

         if (!this.isCurrentStatus("AUX/Navi")) {
            this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
         }

         GeneralOriginalCarDeviceData.mList = var2;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setOriginalOtherPage() {
      GeneralOriginalCarDeviceData.mList = null;
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
      this.changeOriginalDevicePage(var1, new String[]{"am", "fm1", "fm2", "cdc", "navi", "usb_box", "aux"}, new String[0], false);
   }

   private void setOriginalRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      this.changeOriginalDevicePage(var1, new String[]{"am", "fm1", "fm2", "cdc", "navi", "usb_box", "aux", "am_st", "st"}, new String[]{"band", "radio_scan", "up", "left", "right", "down", "preset_scan", "auto_store"}, true);
   }

   private void setOriginalUsbPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_file", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_song_list", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_play_time", ""));
      this.changeOriginalDevicePage(var1, new String[]{"am", "fm1", "fm2", "cdc", "navi", "usb_box", "aux", "scan", "disc_scan", "rpt", "rpt_disc", "rdm", "rdm_disc", "insert", "aux_insert"}, new String[]{"prev_disc", "left", "up", "play", "down", "right", "next_disc"}, false);
   }

   private void setPresetData0x85() {
      ArrayList var4 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      int var2;
      int var3;
      int[] var5;
      if (var1 != 1) {
         if (var1 == 2 || var1 == 3) {
            for(var1 = 1; var1 < 7; ++var1) {
               var5 = this.mCanBusInfoInt;
               var3 = var1 * 2;
               var2 = var5[var3 + 2];
               var3 = var5[var3 + 1];
               var4.add(new SongListEntity((float)(var2 * 256 + var3) / 10.0F + "MHz"));
            }
         }
      } else {
         for(var1 = 1; var1 < 7; ++var1) {
            var5 = this.mCanBusInfoInt;
            var3 = var1 * 2;
            var2 = var5[var3 + 2];
            var3 = var5[var3 + 1];
            var4.add(new SongListEntity(var2 * 256 + var3 + "KHz"));
         }
      }

      if (this.isCurrentStatus("Radio")) {
         GeneralOriginalCarDeviceData.songList = var4;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setRadioData0x84() {
      int var1 = this.mCanBusInfoInt[5];
      String var2 = "";
      if (var1 != 0) {
         var2 = this.mCanBusInfoInt[5] + "";
      }

      this.currentPtore = this.mCanBusInfoInt[5];
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDeviceUpdateEntity(1, this.getRadioBand()));
      var3.add(new OriginalCarDeviceUpdateEntity(2, "P" + var2));
      var3.add(new OriginalCarDeviceUpdateEntity(3, this.getRadioFreq()));
      GeneralOriginalCarDeviceData.am_st = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      if (this.isCurrentStatus("Radio")) {
         GeneralOriginalCarDeviceData.runningState = this.getRadioStatus(this.mCanBusInfoInt[7]);
         GeneralOriginalCarDeviceData.mList = var3;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setUsbData0x89() {
      DecimalFormat var2 = new DecimalFormat("00");
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDeviceUpdateEntity(1, Integer.toString(this.mCanBusInfoInt[2])));
      var1.add(new OriginalCarDeviceUpdateEntity(2, Integer.toString(this.mCanBusInfoInt[3])));
      var1.add(new OriginalCarDeviceUpdateEntity(3, var2.format((long)this.mCanBusInfoInt[4]) + ":" + var2.format((long)this.mCanBusInfoInt[5])));
      GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.disc_scan = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.rpt_disc = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.insert = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.aux_insert = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
      GeneralOriginalCarDeviceData.runningState = this.getUsbStatus(this.mCanBusInfoInt[7]);
      if (this.isCurrentStatus("USB")) {
         GeneralOriginalCarDeviceData.mList = var1;
         this.updateOriginalCarDeviceActivity((Bundle)null);
      }

   }

   private void setVersionData0x88() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      switch (var3[1]) {
         case 129:
            this.setBaseTrack0x81();
            this.setDrivingData();
            this.updateSpeedInfo(this.mCanBusInfoInt[4]);
            break;
         case 130:
            this.setAirData0x82();
            break;
         case 131:
            this.isShowOriginalDeviceStatusMenu = DataHandleUtils.getBoolBit6(var3[2]);
            this.isOnlyBit7Change = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            if (this.is0x83MsgRepeat(var2) || !this.isOnlyBit7Change) {
               return;
            }

            this.setOriginalHeadUnit0x83();
            break;
         case 132:
            if (this.isShowOriginalDeviceStatusMenu) {
               this.setRadioData0x84();
            }
            break;
         case 133:
            if (this.isShowOriginalDeviceStatusMenu) {
               this.setPresetData0x85();
            }
            break;
         case 134:
            if (this.isShowOriginalDeviceStatusMenu) {
               this.setCdcData0x86();
            }
            break;
         case 135:
            this.setAmplifierData0x87();
            break;
         case 136:
            this.setVersionData0x88();
            break;
         case 137:
            if (this.isShowOriginalDeviceStatusMenu) {
               this.setUsbData0x89();
            }
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.cleanDevice();
      this.initAmplifierData(var1);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.VIDEO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.FM.name().equals(var1) || SourceConstantsDef.SOURCE_ID.MUSIC.name().equals(var1) || SourceConstantsDef.SOURCE_ID.BTAUDIO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(var1) || SourceConstantsDef.SOURCE_ID.NAVIAUDIO.name().equals(var1) || SourceConstantsDef.SOURCE_ID.AUX1.name().equals(var1) || SourceConstantsDef.SOURCE_ID.ATV.name().equals(var1) || SourceConstantsDef.SOURCE_ID.DTV.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -13, 1, 5});
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateOriginalCarDevice(Bundle var1) {
      this.updateOriginalCarDeviceActivity(var1);
   }
}
