package com.hzbhd.canbus.car._334;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MsgMgr extends AbstractMsgMgr {
   private String TAG = "lyn";
   final String am = "AM";
   private final String cd = "CD";
   TextView content;
   public int currentCanDifferentId;
   private OriginalDeviceData data;
   private final DecimalFormat df = new DecimalFormat("0.0");
   AlertDialog dialog;
   private final String dvd = "DVD";
   int flag = 0;
   final String fm = "FM";
   Button i_know;
   private int[] mAmplifierDataNow;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   Context mContext;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet = null;
   private int mPlayingIndex = -1;
   private List mSongList;
   private int mVolume;
   String nowRunningState = "";
   public String pageFlag = "null";
   private final String radio = "Radio";
   private final String[] tire0 = new String[1];
   private final String[] tire1 = new String[1];
   private final String[] tire2 = new String[1];
   private final String[] tire3 = new String[1];
   private final List tireInfoList = new ArrayList();
   UiMgr uiMgr;
   View view;

   private void amplifierSettingStatus(Context var1) {
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[2];
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[3];
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 8;
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[5] - 8;
      GeneralAmplifierData.volume = this.mCanBusInfoInt[7];
      this.updateAmplifierActivity((Bundle)null);
   }

   private void amplifierSoundsStatus(Context var1) {
      ArrayList var2 = new ArrayList();
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state0"), var1.getString(2131763347)));
      } else {
         var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state0"), var1.getString(2131763346)));
      }

      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state1"), var1.getString(2131763350)));
      } else {
         var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state1"), var1.getString(2131763349)));
      }

      var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6) + "LEVER"));
      var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state3"), this.getMediaState(this.mCanBusInfoInt[3])));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state4"), var1.getString(2131763361)));
      } else {
         var2.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_amp_state"), this.getUiMgr(var1).getDrivingItemIndexes(var1, "_334_amp_state4"), var1.getString(2131763360)));
      }

      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void cdCharInfo(Context var1) {
      this.setCdPage(var1);
      ArrayList var6 = new ArrayList();
      Log.i("lyn", "ListArray " + ((Object[])Objects.requireNonNull(this.getUiMgr(var1).cdPageList.toArray())).length);
      int var3 = ((Object[])Objects.requireNonNull(this.getUiMgr(var1).cdPageList.toArray())).length;
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 == 3) {
               if (var3 == 2) {
                  this.getUiMgr(var1).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_music", "_334_song_title", (String)null));
                  Log.i("lyn", "add success");
               } else {
                  this.getUiMgr(var1).cdPageList.remove(2);
                  this.getUiMgr(var1).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_music", "_334_song_title", (String)null));
               }
            }
         } else if (var3 == 2) {
            this.getUiMgr(var1).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_dvd", "_334_disc_name", (String)null));
            Log.i("lyn", "add success");
         } else {
            this.getUiMgr(var1).cdPageList.remove(2);
            this.getUiMgr(var1).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_dvd", "_334_disc_name", (String)null));
         }
      } else if (var3 == 2) {
         this.getUiMgr(var1).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_artist", "_334_singer", (String)null));
         Log.i("lyn", "add success");
      } else {
         this.getUiMgr(var1).cdPageList.remove(2);
         this.getUiMgr(var1).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_artist", "_334_singer", (String)null));
      }

      boolean var5 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      var2 = 0;
      if (var5) {
         int var4 = this.mCanBusInfoByte.length;
         var3 = 4;
         var4 -= 4;

         byte[] var7;
         for(var7 = new byte[var4]; var2 < var4; ++var3) {
            var7[var2] = this.mCanBusInfoByte[var3];
            ++var2;
         }

         try {
            String var9 = new String(var7, "GBK");
            OriginalCarDeviceUpdateEntity var10 = new OriginalCarDeviceUpdateEntity(2, var9);
            var6.add(var10);
            Log.i("lyn", var9);
         } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
         }

         GeneralOriginalCarDeviceData.mList = var6;
      } else {
         ((OriginalDeviceData)Objects.requireNonNull((OriginalDeviceData)this.getUiMgr(var1).pageMap.get(0))).getItemList().remove(2);
      }

      this.updateOriginalDevicePage();
   }

   private void cdListInfo(Context var1) {
   }

   private void cd_dvd_MediaInfo(Context var1) {
      this.setCdPage(var1);
      ArrayList var3 = new ArrayList();
      int var2 = this.mCanBusInfoInt[2];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 5) {
                        GeneralOriginalCarDeviceData.runningState = var1.getString(2131763391);
                     }
                  } else {
                     GeneralOriginalCarDeviceData.runningState = var1.getString(2131763390);
                  }
               } else {
                  GeneralOriginalCarDeviceData.runningState = var1.getString(2131763392);
               }
            } else {
               GeneralOriginalCarDeviceData.runningState = var1.getString(2131763545);
            }
         } else {
            GeneralOriginalCarDeviceData.runningState = var1.getString(2131763542);
         }
      } else {
         GeneralOriginalCarDeviceData.runningState = var1.getString(2131763589);
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 1) {
         GeneralOriginalCarDeviceData.rpt = true;
      } else {
         GeneralOriginalCarDeviceData.rpt = false;
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1) == 1) {
         GeneralOriginalCarDeviceData.rdm = true;
      } else {
         GeneralOriginalCarDeviceData.rdm = false;
      }

      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1) == 1) {
         GeneralOriginalCarDeviceData.scan = true;
      } else {
         GeneralOriginalCarDeviceData.scan = false;
      }

      int[] var4 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(0, String.valueOf(this.getMsbLsbResult(var4[4], var4[5]))));
      var4 = this.mCanBusInfoInt;
      var3.add(new OriginalCarDeviceUpdateEntity(1, String.valueOf(this.getMsbLsbResult(var4[6], var4[7]))));
      String var5 = this.mCanBusInfoInt[11] + ":" + this.mCanBusInfoInt[12] + ":" + this.mCanBusInfoInt[13];
      String var6 = this.mCanBusInfoInt[8] + ":" + this.mCanBusInfoInt[9] + ":" + this.mCanBusInfoInt[10];
      GeneralOriginalCarDeviceData.startTime = var5;
      GeneralOriginalCarDeviceData.endTime = var6;
      var4 = this.mCanBusInfoInt;
      GeneralOriginalCarDeviceData.progress = this.progress(var4[11], var4[12], var4[13], var4[8], var4[9], var4[10]);
      GeneralOriginalCarDeviceData.mList = var3;
      this.setCdPage(var1);
      this.enterAuxIn2();
   }

   private void cd_dvd_Status(Context var1) {
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1) {
         this.realKeyClick4(var1, 50);
      } else {
         this.setCdPage(var1);
         this.enterAuxIn2();
      }

   }

   private void cornerInfo(Context var1) {
      byte[] var2 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[3], var2[2], 0, 540, 16);
      this.updateParkUi((Bundle)null, var1);
   }

   private void frontRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void fuelAverageInfo(Context var1) {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      StringBuilder var4 = new StringBuilder();
      DecimalFormat var6 = this.df;
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 0, var4.append(var6.format((long)(this.getMsbLsbResult(var5[2], var5[3]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      var4 = new StringBuilder();
      var6 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 1, var4.append(var6.format((long)(this.getMsbLsbResult(var5[4], var5[5]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      StringBuilder var11 = new StringBuilder();
      DecimalFormat var9 = this.df;
      int[] var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 2, var11.append(var9.format((long)(this.getMsbLsbResult(var8[6], var8[7]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      var4 = new StringBuilder();
      var6 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 3, var4.append(var6.format((long)(this.getMsbLsbResult(var5[8], var5[9]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      StringBuilder var10 = new StringBuilder();
      var6 = this.df;
      var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 4, var10.append(var6.format((long)(this.getMsbLsbResult(var8[10], var8[11]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      var4 = new StringBuilder();
      var6 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 5, var4.append(var6.format((long)(this.getMsbLsbResult(var5[12], var5[13]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_1");
      StringBuilder var7 = new StringBuilder();
      var9 = this.df;
      var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 6, var7.append(var9.format((long)(this.getMsbLsbResult(var8[16], var8[17]) / 10))).append(" L/100km").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void fuelPerMinuteInfo(Context var1) {
      ArrayList var3 = new ArrayList();
      int var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      StringBuilder var4 = new StringBuilder();
      DecimalFormat var6 = this.df;
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 0, var4.append(var6.format((long)(this.getMsbLsbResult(var5[2], var5[3]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      StringBuilder var10 = new StringBuilder();
      var6 = this.df;
      int[] var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 1, var10.append(var6.format((long)(this.getMsbLsbResult(var8[4], var8[5]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var10 = new StringBuilder();
      DecimalFormat var9 = this.df;
      int[] var11 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 2, var10.append(var9.format((long)(this.getMsbLsbResult(var11[6], var11[7]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      StringBuilder var12 = new StringBuilder();
      var9 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 3, var12.append(var9.format((long)(this.getMsbLsbResult(var5[8], var5[9]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var10 = new StringBuilder();
      var9 = this.df;
      var11 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 4, var10.append(var9.format((long)(this.getMsbLsbResult(var11[10], var11[11]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var4 = new StringBuilder();
      var6 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 5, var4.append(var6.format((long)(this.getMsbLsbResult(var5[12], var5[13]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var12 = new StringBuilder();
      var9 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 6, var12.append(var9.format((long)(this.getMsbLsbResult(var5[14], var5[15]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var12 = new StringBuilder();
      DecimalFormat var13 = this.df;
      var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 7, var12.append(var13.format((long)(this.getMsbLsbResult(var8[16], var8[17]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var12 = new StringBuilder();
      var13 = this.df;
      var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 8, var12.append(var13.format((long)(this.getMsbLsbResult(var8[18], var8[19]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var10 = new StringBuilder();
      var9 = this.df;
      var11 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 9, var10.append(var9.format((long)(this.getMsbLsbResult(var11[20], var11[21]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var10 = new StringBuilder();
      var9 = this.df;
      var11 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 10, var10.append(var9.format((long)(this.getMsbLsbResult(var11[22], var11[23]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var4 = new StringBuilder();
      var6 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 11, var4.append(var6.format((long)(this.getMsbLsbResult(var5[24], var5[25]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var4 = new StringBuilder();
      var6 = this.df;
      var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 12, var4.append(var6.format((long)(this.getMsbLsbResult(var5[26], var5[27]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var10 = new StringBuilder();
      var6 = this.df;
      var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 13, var10.append(var6.format((long)(this.getMsbLsbResult(var8[28], var8[29]) / 10))).append(" L/100km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_fuel_info_9");
      var10 = new StringBuilder();
      DecimalFormat var7 = this.df;
      var8 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, 14, var10.append(var7.format((long)(this.getMsbLsbResult(var8[30], var8[31]) / 10))).append(" L/100km").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void generalInfo(Context var1) {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralDoorData.isShowFuelWarning = true;
      GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
         this.showDialog(this.getActivity(), this.getActivity().getString(2131763396), true);
      } else {
         this.showDialog(this.getActivity(), this.getActivity().getString(2131763396), false);
      }

      this.updateDoorView(var1);
   }

   private String getMediaState(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     return var1 != 5 ? this.mContext.getString(2131763353) : this.mContext.getString(2131763358);
                  } else {
                     return this.mContext.getString(2131763357);
                  }
               } else {
                  return this.mContext.getString(2131763356);
               }
            } else {
               return this.mContext.getString(2131763355);
            }
         } else {
            return this.mContext.getString(2131763354);
         }
      } else {
         return this.mContext.getString(2131763353);
      }
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context var1) {
      if (this.mOriginalCarDevicePageUiSet == null) {
         this.mOriginalCarDevicePageUiSet = this.getUiMgr(var1).getOriginalCarDevicePageUiSet(var1);
      }

      return this.mOriginalCarDevicePageUiSet;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.uiMgr == null) {
         this.uiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.uiMgr;
   }

   private void hudStatus(Context var1) {
      if (this.flag == 0) {
         this.getUiMgr(var1).initSettingPageIndex();
         ++this.flag;
      }

      ArrayList var8 = new ArrayList();
      int[] var9 = this.mCanBusInfoInt;
      int var7 = var9[2];
      int var6 = var9[3];
      int var4 = var9[4];
      int var3 = DataHandleUtils.getIntFromByteWithBit(var9[5], 0, 7);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 4);
      int var5 = (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud"));
      var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_height")), var7 - 15)).setProgress(var7));
      byte var10 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      var8.add(new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_brightness_control")), Integer.valueOf(var10)));
      if (var10 == 1) {
         var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_brightness")), var6 - 20)).setEnable(true).setProgress(var6));
         var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_calibration")), var4 - 2)).setEnable(false).setProgress(var4));
      } else {
         var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_brightness")), var6 - 20)).setEnable(false).setProgress(var6));
         var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_calibration")), var4 - 2)).setEnable(true).setProgress(var4));
      }

      var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_rotate")), var3 - 3)).setProgress(var3));
      var8.add(new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_active_display")), Integer.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]))));
      var8.add((new SettingUpdateEntity(var5, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_hud_tilt")), var2 - 5)).setProgress(var2));
      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void i_stopInfo(Context var1) {
      ArrayList var4 = new ArrayList();
      int var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      String var3;
      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var3 = "_334_i_stop_running";
      } else {
         var3 = "_334_i_stop_not_running";
      }

      var4.add(new DriverUpdateEntity(var2, 0, CommUtil.getStrByResId(var1, var3)));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         var3 = "_334_i_stop_unprepared";
      } else {
         var3 = "_334_i_stop_prepared";
      }

      var4.add(new DriverUpdateEntity(var2, 1, CommUtil.getStrByResId(var1, var3)));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      if (!DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
         var3 = CommUtil.getStrByResId(var1, "_334_i_stop_not_read");
      } else {
         var3 = CommUtil.getStrByResId(var1, "_334_i_stop_read");
      }

      var4.add(new DriverUpdateEntity(var2, 2, var3));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
         var3 = CommUtil.getStrByResId(var1, "_334_i_stop_not_read");
      } else {
         var3 = CommUtil.getStrByResId(var1, "_334_i_stop_read");
      }

      var4.add(new DriverUpdateEntity(var2, 3, var3));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
         var3 = CommUtil.getStrByResId(var1, "_334_i_stop_not_read");
      } else {
         var3 = CommUtil.getStrByResId(var1, "_334_i_stop_read");
      }

      var4.add(new DriverUpdateEntity(var2, 4, var3));
      var2 = this.mCanBusInfoInt[3];
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 == 4) {
                     var4.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information"), 5, CommUtil.getStrByResId(var1, "_334_i_stop_cant_activate")));
                  }
               } else {
                  var4.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information"), 5, CommUtil.getStrByResId(var1, "_334_i_stop_shift_to_neutral")));
               }
            } else {
               var4.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information"), 5, CommUtil.getStrByResId(var1, "_334_i_stop_more_pressure")));
            }
         } else {
            var4.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information"), 5, CommUtil.getStrByResId(var1, "_334_i_stop_available")));
         }
      } else {
         var4.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information"), 5, CommUtil.getStrByResId(var1, "_334_i_stop_nothing")));
      }

      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      int[] var7 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, 6, this.timeFormat(this.getMsbLsbResult(var7[4], var7[5]), false)));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      var7 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, 7, this.timeFormat(this.getMsbLsbResult(var7[6], var7[7]), false)));
      var4.add(new DriverUpdateEntity(this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information"), 8, this.mCanBusInfoInt[8] + " %"));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      StringBuilder var8 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, 9, var8.append((double)this.getMsbLsbResult(var5[9], var5[10]) * 0.1).append(" km").toString()));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      var7 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, 10, this.timeFormat(this.getMsbLsbResult(var7[11], var7[12]), true)));
      var2 = this.getUiMgr(var1).getDrivingPageIndexes(var1, "_334_i_stop_information");
      StringBuilder var6 = new StringBuilder();
      var7 = this.mCanBusInfoInt;
      var4.add(new DriverUpdateEntity(var2, 11, var6.append((double)this.getMsbLsbResult(var7[13], var7[14]) * 0.1).append(" L/100km").toString()));
      this.updateGeneralDriveData(var4);
      this.updateDriveDataActivity((Bundle)null);
   }

   private boolean isAmplifierDataChange(int[] var1) {
      if (Arrays.equals(var1, this.mAmplifierDataNow)) {
         return false;
      } else {
         this.mAmplifierDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void maintenanceInfo(Context var1) {
      if (this.flag == 0) {
         this.getUiMgr(var1).initSettingPageIndex();
         ++this.flag;
      }

      ArrayList var8 = new ArrayList();
      int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      int[] var9 = this.mCanBusInfoInt;
      int var6 = this.getMsbLsbResult(var9[3], var9[4]);
      var9 = this.mCanBusInfoInt;
      int var4 = this.getMsbLsbResult(var9[6], var9[7]);
      var9 = this.mCanBusInfoInt;
      int var3 = this.getMsbLsbResult(var9[9], var9[10]);
      int var2 = (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_maintenance_info"));
      byte var7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      var8.add(new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_scheduled_maintenance_switch")), Integer.valueOf(var7)));
      Log.i(this.TAG, var2 + " " + Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_scheduled_maintenance_switch")));
      if (var7 == 1) {
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_scheduled_maintenance_time")))).setEnable(true).setValue(var5).setProgress(var5 - 1));
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_scheduled_maintenance_distance")))).setEnable(true).setValue(var6).setProgress(var6 - 1000));
      } else {
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_scheduled_maintenance_time")))).setEnable(false));
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_scheduled_maintenance_distance")))).setEnable(false));
      }

      byte var11 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
      var8.add(new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_tire_rotation_switch")), Integer.valueOf(var11)));
      if (var11 == 1) {
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_tire_rotation_distance")))).setEnable(true).setValue(var4).setProgress(var4 - 1000));
      } else {
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_tire_rotation_distance")))).setEnable(false));
      }

      byte var10 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
      var8.add(new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_oil_change_switch")), Integer.valueOf(var10)));
      if (var10 == 1) {
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_oil_change_distance")))).setEnable(true).setValue(var3).setProgress(var3 - 1000));
      } else {
         var8.add((new SettingUpdateEntity(var2, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_oil_change_distance")))).setEnable(false));
      }

      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void mileageNextServiceDate(Context var1) {
      if (this.flag == 0) {
         this.getUiMgr(var1).initSettingPageIndex();
         ++this.flag;
      }

      ArrayList var8 = new ArrayList();
      int[] var9 = this.mCanBusInfoInt;
      int var6 = var9[3];
      int var5 = var9[4];
      int var4 = var9[5];
      int var7 = this.getMsbLsbResult(var9[6], var9[7]);
      byte var2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      int var3 = (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service"));
      var8.add(new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_manual_auto")), Integer.valueOf(var2)));
      if (var2 == 1) {
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_year")))).setEnable(true).setValue(var6 + 2018).setProgress(var6));
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_month")))).setEnable(true).setValue(var5).setProgress(var5 - 1));
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_day")))).setEnable(true).setValue(var4).setProgress(var4 - 1));
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_mileage")))).setEnable(true).setValue(var7).setProgress(var7 - 400));
      } else {
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_year")))).setEnable(false));
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_month")))).setEnable(false));
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_day")))).setEnable(false));
         var8.add((new SettingUpdateEntity(var3, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_next_service_mileage")))).setEnable(false));
      }

      this.updateGeneralSettingData(var8);
      this.updateSettingActivity((Bundle)null);
   }

   private void panelButtonInfo(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      switch (var3[2]) {
         case 0:
            this.realKeyLongClick1(var1, 0, var3[3]);
            break;
         case 1:
            this.realKeyLongClick1(var1, 128, var3[3]);
            break;
         case 2:
            if (this.mVolume > 0) {
               this.volumeKnob(var1, 2, var3[3]);
            }
            break;
         case 3:
            if (this.mVolume < 40) {
               this.volumeKnob(var1, 3, var3[3]);
            }
            break;
         case 4:
            int var2 = var3[3];
            if (var2 == 1) {
               this.realKeyLongClick1(var1, 3, var2);
            }

            var2 = this.mCanBusInfoInt[3];
            if (var2 == 2) {
               this.realKeyLongClick1(var1, 134, var2);
            }
            break;
         case 5:
            this.realKeyLongClick1(var1, 52, var3[3]);
            break;
         case 6:
            this.realKeyLongClick1(var1, 50, var3[3]);
            break;
         case 7:
            this.realKeyLongClick1(var1, 79, var3[3]);
            break;
         case 8:
            this.realKeyLongClick1(var1, 59, var3[3]);
            break;
         case 9:
            this.realKeyClick3(var1, 33, 0, var3[3]);
            break;
         case 10:
            this.realKeyClick3(var1, 34, 0, var3[3]);
            break;
         case 11:
            this.realKeyClick3(var1, 35, 0, var3[3]);
            break;
         case 12:
            this.realKeyClick3(var1, 36, 0, var3[3]);
            break;
         case 13:
            this.realKeyClick3(var1, 37, 0, var3[3]);
            break;
         case 14:
            this.realKeyClick3(var1, 38, 0, var3[3]);
            break;
         case 15:
            this.realKeyClick3(var1, 39, 0, var3[3]);
            break;
         case 16:
            this.realKeyLongClick1(var1, 21, var3[3]);
            break;
         case 17:
            this.realKeyLongClick1(var1, 20, var3[3]);
      }

   }

   private int progress(int var1, int var2, int var3, int var4, int var5, int var6) {
      var4 = var4 * 3600 + var5 * 60 + var6;
      return var4 != 0 ? (var1 * 3600 + var2 * 60 + var3) * 100 / var4 : 0;
   }

   private void radioListInfo(Context var1) {
      int[] var2;
      StringBuilder var3;
      if (this.mCanBusInfoInt[2] == 0) {
         var3 = (new StringBuilder()).append(this.nowRunningState).append("Update List：").append(this.mCanBusInfoInt[3]).append(" frequency：");
         var2 = this.mCanBusInfoInt;
         GeneralOriginalCarDeviceData.runningState = var3.append(this.getMsbLsbResult(var2[4], var2[5])).append("MHz").toString();
      } else {
         var3 = (new StringBuilder()).append(this.nowRunningState).append("Update List：").append(this.mCanBusInfoInt[3]).append(" frequency：");
         var2 = this.mCanBusInfoInt;
         GeneralOriginalCarDeviceData.runningState = var3.append(this.getMsbLsbResult(var2[4], var2[5])).append("KHz").toString();
      }

      Bundle var4 = new Bundle();
      var4.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var4);
   }

   private void radioStatus(Context var1) {
      this.setRadioPage(var1);
      ArrayList var3 = new ArrayList();
      int var2 = this.mCanBusInfoInt[2];
      String var5 = "AM";
      if (var2 == 0) {
         var3.add(new OriginalCarDeviceUpdateEntity(0, "FM"));
         var5 = "FM";
      } else {
         var3.add(new OriginalCarDeviceUpdateEntity(0, "AM"));
      }

      switch (this.mCanBusInfoInt[3]) {
         case 0:
            this.nowRunningState = this.mContext.getString(2131763547);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763547);
            break;
         case 1:
            this.nowRunningState = this.mContext.getString(2131763548);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763548);
            break;
         case 2:
            this.nowRunningState = this.mContext.getString(2131763549);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763549);
            break;
         case 3:
            this.nowRunningState = this.mContext.getString(2131763550);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763550);
            break;
         case 4:
            this.nowRunningState = this.mContext.getString(2131763551);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763551);
            break;
         case 5:
            this.nowRunningState = this.mContext.getString(2131763552);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763552);
            break;
         case 6:
            this.nowRunningState = this.mContext.getString(2131763553);
            GeneralOriginalCarDeviceData.runningState = this.mContext.getString(2131763553);
      }

      if (var5.equals("FM")) {
         StringBuilder var4 = new StringBuilder();
         int[] var6 = this.mCanBusInfoInt;
         var3.add(new OriginalCarDeviceUpdateEntity(1, var4.append((double)(this.getMsbLsbResult(var6[4], var6[5]) - 1) * 0.05 + 87.5).append(" MHz").toString()));
      } else {
         StringBuilder var7 = new StringBuilder();
         int[] var9 = this.mCanBusInfoInt;
         var3.add(new OriginalCarDeviceUpdateEntity(1, var7.append((this.getMsbLsbResult(var9[4], var9[5]) - 1) * 9 + 522).append(" KHz").toString()));
      }

      GeneralOriginalCarDeviceData.mList = var3;
      Bundle var8 = new Bundle();
      var8.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var8);
   }

   private void realKeyInfo(Context var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      if (var2 != 9) {
         if (var2 != 10) {
            if (var2 != 16) {
               switch (var2) {
                  case 0:
                     this.realKeyLongClick1(var1, 0, var3[3]);
                     break;
                  case 1:
                     this.realKeyLongClick1(var1, 7, var3[3]);
                     break;
                  case 2:
                     this.realKeyLongClick1(var1, 8, var3[3]);
                     break;
                  case 3:
                     this.realKeyLongClick1(var1, 21, var3[3]);
                     break;
                  case 4:
                     this.realKeyLongClick1(var1, 20, var3[3]);
                     break;
                  case 5:
                     this.realKeyLongClick1(var1, 3, var3[3]);
                     break;
                  case 6:
                     if (((List)Objects.requireNonNull((List)this.getUiMgr(var1).cmd_carId_Map.get(2215942))).contains(this.currentCanDifferentId)) {
                        this.realKeyLongClick1(var1, 2, this.mCanBusInfoInt[3]);
                     } else {
                        this.realKeyLongClick1(var1, 39, this.mCanBusInfoInt[3]);
                     }
               }
            } else if (((List)Objects.requireNonNull((List)this.getUiMgr(var1).cmd_carId_Map.get(2215952))).contains(this.currentCanDifferentId)) {
               this.realKeyLongClick1(var1, 187, this.mCanBusInfoInt[3]);
            }
         } else {
            this.realKeyLongClick1(var1, 15, var3[3]);
         }
      } else {
         this.realKeyLongClick1(var1, 14, var3[3]);
      }

   }

   private void rearRadarInfo(Context var1) {
      RadarInfoUtil.mMinIsClose = true;
      int[] var2 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(3, var2[2], var2[3], var2[4], var2[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, var1);
   }

   private void securitySettingStatus(Context var1) {
      if (this.flag == 0) {
         this.getUiMgr(var1).initSettingPageIndex();
         ++this.flag;
      }

      int var4 = (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_security_setting_status_info"));
      ArrayList var5 = new ArrayList();
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_smart_city_brake")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_rear_vehicle_monitoring_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_smart_city_brake_distance")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_smart_city_brake_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_distance_recognition_display")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_distance_recognition_distance")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_sbs_display")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_sbs_distance")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_sbs_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_blind_spot_monitoring")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_warning_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system_time")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system_warning")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system_voice")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system_rumble")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_speed_limit_display")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_speed_limit_warning")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_warning_threshold")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system_intervene")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)));
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1) == 1) {
         var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lane_departure_warning_system_voice")), 2));
      }

      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_cruise_operation_tone")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_parking_sensor_display_guide")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_rear_parking_alarm_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 3) - 1));
      int var3 = (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_pedal_misuse_alarm"));
      byte var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1) == 1) {
         var2 = 0;
      } else {
         var2 = 1;
      }

      var5.add(new SettingUpdateEntity(var4, var3, Integer.valueOf(var2)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_mazda_radar")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_rear_intersection_traffic_alert")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_bind_spot_monitoring")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_alarm_timing")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_alarm_type")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_auto_display_view")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_dynamic_route")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_front_camera_view")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 1)));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_security_alert_lane_departure_warning")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 6, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_security_alert_intersection_traffic_ahead")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_front_parking_sensor_alarm_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 5, 3) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_accident_avoidance_alarm_timing")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 2) - 1));
      var5.add(new SettingUpdateEntity(var4, (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_active_safety_assistance")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 2) - 1));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private void settingItemStatus(Context var1) {
      if (this.flag == 0) {
         this.getUiMgr(var1).initSettingPageIndex();
         ++this.flag;
      }

      ArrayList var3 = new ArrayList();
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_door_lock")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_auto_lock")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_door_lock")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_auto_re_lock")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_door_lock")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_unlock_mode")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_door_lock")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_door_lock")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lock_leave_car")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      if (this.currentCanDifferentId == 12) {
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_door_lock")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_tail_door")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) - 1));
      }

      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_turn")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_turn_signal")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_turn")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_turn_signal_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lighting")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_1_lights_off_automatically")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lighting")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_2_lights_off_automatically")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lighting")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lights_reminder")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lighting")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_adaptive_headlamp")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lighting")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_headlamp_off_timer")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_lighting")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_headlamp_on_auto")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_1")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_rain_sensor")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_1")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_language_state")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_1")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_blind_area_prompt_volume")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2)));
      int var2 = this.currentCanDifferentId;
      if (var2 == 10 || var2 == 12) {
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_1")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_rear_window_demister")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)));
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_1")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_automatic_lock_time")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3)));
      }

      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_2")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_synchronized_1")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
      var2 = this.currentCanDifferentId;
      if (var2 == 10 || var2 == 12) {
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_2")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_rear_vision_mirror")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)));
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_2")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_notification_warning")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2)));
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_2")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_Instrument_custom_display")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2)));
      }

      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_3")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_leaving_home_light")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_3")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_coming_home_light")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 3)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_3")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_distance_unit")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_3")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_temperature_unit")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1)));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_3")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_high_beam_control")), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[9]) ^ 1));
      var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_other_3")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_day_light")), 1 ^ DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])));
      if (this.currentCanDifferentId == 11) {
         var3.add(new SettingUpdateEntity((Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_environmental_lighting_title")), (Integer)Objects.requireNonNull((Integer)this.getUiMgr(var1).settingPageIndex.get("_334_environmental_lighting_item")), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 2)));
      }

      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void songListSetSelected() {
      int[] var1 = this.mCanBusInfoInt;
      this.mPlayingIndex = var1[3] * 256 + var1[4];
      if (GeneralOriginalCarDeviceData.songList != null) {
         Iterator var2 = GeneralOriginalCarDeviceData.songList.iterator();

         while(var2.hasNext()) {
            ((SongListEntity)var2.next()).setSelected(false);
         }

         if (GeneralOriginalCarDeviceData.songList.size() > this.mPlayingIndex) {
            ((SongListEntity)GeneralOriginalCarDeviceData.songList.get(this.mPlayingIndex)).setSelected(true);
         }
      }

   }

   private String timeFormat(int var1, boolean var2) {
      int var3;
      if (var2) {
         var3 = var1 / 60;
         return var3 + "h" + (var1 - var3 * 60) + "m";
      } else {
         var3 = var1 / 60;
         return var3 + "m" + (var1 - var3 * 60) + "s";
      }
   }

   private void tirePressureInfo() {
      this.tire0[0] = this.mCanBusInfoInt[2] * 2 + "KPa";
      this.tire1[0] = this.mCanBusInfoInt[3] * 2 + "KPa";
      this.tire2[0] = this.mCanBusInfoInt[4] * 2 + "KPa";
      this.tire3[0] = this.mCanBusInfoInt[5] * 2 + "KPa";
      this.tireInfoList.add(new TireUpdateEntity(0, 0, this.tire0));
      this.tireInfoList.add(new TireUpdateEntity(1, 0, this.tire1));
      this.tireInfoList.add(new TireUpdateEntity(2, 0, this.tire2));
      this.tireInfoList.add(new TireUpdateEntity(3, 0, this.tire3));
      GeneralTireData.dataList = this.tireInfoList;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void tirePressureUpdateTime() {
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(0, 0, String.valueOf(this.mCanBusInfoInt[2] + 2018)));
      var1.add(new DriverUpdateEntity(0, 1, String.valueOf(this.mCanBusInfoInt[3])));
      var1.add(new DriverUpdateEntity(0, 2, String.valueOf(this.mCanBusInfoInt[4])));
      var1.add(new DriverUpdateEntity(0, 3, String.valueOf(this.mCanBusInfoInt[5])));
      var1.add(new DriverUpdateEntity(0, 4, String.valueOf(this.mCanBusInfoInt[6])));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void touchCoordinateInfo() {
   }

   private void updateOriginalDevicePage() {
      Bundle var1 = new Bundle();
      var1.putBoolean("bundle_key_orinal_init_view", true);
      this.updateOriginalCarDeviceActivity(var1);
   }

   private void versionInfo(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void volumeKnob(Context var1, int var2, int var3) {
      byte var5 = 0;
      if (var2 == 2) {
         for(int var4 = 0; var4 < var3; ++var4) {
            this.realKeyClick4(var1, 8);
         }
      }

      if (var2 == 3) {
         for(var2 = var5; var2 < var3; ++var2) {
            this.realKeyClick4(var1, 7);
         }
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      if (((List)Objects.requireNonNull((List)this.getUiMgr(var1).cmd_carId_Map.get(this.mCanBusInfoInt[1]))).contains(this.currentCanDifferentId)) {
         int var3 = this.mCanBusInfoInt[1];
         if (var3 != 48) {
            if (var3 != 49) {
               if (var3 != 64) {
                  if (var3 != 65) {
                     if (var3 != 127) {
                        switch (var3) {
                           case 33:
                              this.realKeyInfo(var1);
                              break;
                           case 34:
                              this.panelButtonInfo(var1);
                              break;
                           case 35:
                              this.rearRadarInfo(var1);
                              break;
                           case 36:
                              this.frontRadarInfo(var1);
                              break;
                           case 37:
                              this.touchCoordinateInfo();
                              break;
                           case 38:
                              this.tirePressureInfo();
                              break;
                           case 39:
                              this.tirePressureUpdateTime();
                              break;
                           case 40:
                              this.generalInfo(var1);
                              break;
                           case 41:
                              this.cornerInfo(var1);
                              break;
                           default:
                              switch (var3) {
                                 case 80:
                                    this.fuelAverageInfo(var1);
                                    break;
                                 case 81:
                                    this.fuelPerMinuteInfo(var1);
                                    break;
                                 case 82:
                                    this.i_stopInfo(var1);
                                    break;
                                 default:
                                    switch (var3) {
                                       case 96:
                                          this.cd_dvd_Status(var1);
                                          break;
                                       case 97:
                                          this.cd_dvd_MediaInfo(var1);
                                          break;
                                       case 98:
                                          this.cdCharInfo(var1);
                                          break;
                                       case 99:
                                          this.cdListInfo(var1);
                                          break;
                                       default:
                                          switch (var3) {
                                             case 112:
                                                this.amplifierSettingStatus(var1);
                                                break;
                                             case 113:
                                                this.amplifierSoundsStatus(var1);
                                                break;
                                             case 114:
                                                this.radioStatus(var1);
                                                break;
                                             case 115:
                                                this.radioListInfo(var1);
                                                break;
                                             case 116:
                                                this.securitySettingStatus(var1);
                                          }
                                    }
                              }
                        }
                     } else {
                        this.versionInfo(var1);
                     }
                  } else {
                     this.settingItemStatus(var1);
                  }
               } else {
                  this.hudStatus(var1);
               }
            } else {
               this.maintenanceInfo(var1);
            }
         } else {
            this.mileageNextServiceDate(var1);
         }
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.mVolume = var1;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.currentCanDifferentId = this.getCurrentCanDifferentId();
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      GeneralTireData.isHaveSpareTire = false;
      SelectCanTypeUtil.enableApp(var1, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
   }

   public void setCdPage(Context var1) {
      if (!this.pageFlag.equals("CD")) {
         if (this.mSongList == null) {
            this.mSongList = new ArrayList();
         }

         this.pageFlag = "CD";
         this.data = (OriginalDeviceData)this.getUiMgr(var1).pageMap.get(0);
         GeneralOriginalCarDeviceData.cdStatus = "CD";
         OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(var1);
         var2.setItems(this.data.getItemList());
         var2.setRowTopBtnAction(this.data.getTopBtn());
         var2.setRowBottomBtnAction(this.data.getBottomBtn());
         var2.setHavePlayTimeSeekBar(true);
         var2.setHaveSongList(false);
      }

      this.updateOriginalDevicePage();
   }

   public void setRadioPage(Context var1) {
      if (!this.pageFlag.equals("Radio")) {
         this.pageFlag = "Radio";
         GeneralOriginalCarDeviceData.cdStatus = "Radio";
         this.data = (OriginalDeviceData)this.getUiMgr(var1).pageMap.get(2);
         OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(var1);
         var2.setItems(this.data.getItemList());
         var2.setRowBottomBtnAction(this.data.getBottomBtn());
         var2.setRowTopBtnAction(this.data.getTopBtn());
         var2.setHavePlayTimeSeekBar(false);
         var2.setHaveSongList(false);
         Bundle var3 = new Bundle();
         var3.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var3);
         this.enterAuxIn2();
      }

   }

   public void showDialog(Context var1, String var2, boolean var3) {
      if (this.view == null) {
         this.view = LayoutInflater.from(var1).inflate(2131558513, (ViewGroup)null, true);
      }

      if (this.dialog == null) {
         this.dialog = (new AlertDialog.Builder(var1)).setView(this.view).create();
      }

      if (this.content == null) {
         this.content = (TextView)this.view.findViewById(2131361915);
      }

      this.content.setText(var2);
      if (this.i_know == null) {
         this.i_know = (Button)this.view.findViewById(2131362380);
      }

      this.i_know.setOnClickListener(new View.OnClickListener(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.dialog.dismiss();
         }
      });
      this.dialog.setCancelable(false);
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.getWindow().setType(2003);
      if (var3) {
         this.dialog.show();
      } else {
         this.dialog.dismiss();
      }

   }
}
