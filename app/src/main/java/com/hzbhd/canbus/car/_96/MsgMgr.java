package com.hzbhd.canbus.car._96;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.view.ParkAssistFloatView;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String ORIGINAL_RADIO_CD = "original_radio_cd";
   static final String Temp_Unit = "Temp_Unit";
   static boolean angleWide;
   private static int[] fordHiworldWarinIds = new int[]{2131768286, 2131768287, -1, -1, -1, -1, -1, -1, 2131768288, 2131768289, 2131768290, -1, -1, -1, -1, -1, 2131768291, 2131768292, 2131768293, 2131768294, 2131768295, 2131768296, 2131768297, 2131768298, 2131768299, 2131768300, 2131768301, 2131768302, 2131768303, -1, -1, -1, 2131768304, 2131768305, 2131768306, 2131768307, 2131768308, 2131768309, 2131768310, -1, 2131768311, 2131768312, 2131768313, 2131768314, 2131768315, 2131768316, 2131768317, 2131768318, 2131768319, 2131768320, 2131768321, 2131768322, 2131768323, -1, -1, -1, 2131768324, 2131768325, 2131768326, 2131768327, 2131768328, 2131768329, 2131768330, 2131768331, -1, -1, 2131768275, 2131768276, 2131768277, 2131768278, 2131768279, -1, 2131768280, -1, -1, 2131768281, 2131768282, 2131768283, 2131768284, 2131768285};
   private static boolean isDoorFirst;
   private static boolean isParkingFirst;
   private static boolean isWarnFirst;
   private static int outDoorTemp;
   static boolean overlook;
   private static int rearTemp;
   private static ArrayList warningList = new ArrayList();
   private final int DATA_TYPE = 1;
   int FactoryCode;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
   private final String IS_REAR_AIR_POWER = "is_rear_air_power";
   private final String IS_REAR_AIR_TEMP_CHANGE_FORD = "is_rear_air_temp_change_ford";
   private final String IS_REAR_AIR_WIND_LV_CHANGE = "is_rear_air_wind_lv_change";
   private final String IS_REAR_LOCK = "is_rear_lock";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   String data1 = "";
   String data2 = "";
   private int ford_park_assist_info;
   private boolean isCdFirst = true;
   private boolean isRadioFirst = true;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusSwcInfoCopy;
   private byte[] mCanBusWarnInfoCopy;
   private int mCanId;
   private SparseArray mCanbusDataArray = new SparseArray();
   private byte[] mCanbusSync0xD3InfoCopy;
   private Context mContext;
   private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
   private byte[] mDoorInfoCopy;
   private ID3[] mId3s;
   private boolean mIsAirFirst = true;
   private List mList1 = new ArrayList();
   private List mList2 = new ArrayList();
   private byte[] mOriginalCdCopy;
   private byte[] mOriginalCdInfoCopy;
   private byte[] mOriginalRadioCopy;
   private byte[] mOriginalRadioInfoCopy;
   private ParkAssistFloatView mParkAssistFloatView;
   private byte[] mParkingInfoCopy;
   private int mSyncBtStatus;
   private SparseIntArray mSyncIconResIdArray;
   private UiMgr mUiMgr;
   private int value;

   public MsgMgr() {
      GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(0));
      GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(1));
      GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(2));
      GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(3));
      GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(4));
      GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(0));
      GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(1));
      GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(2));
      GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(3));
      SparseIntArray var1 = new SparseIntArray();
      this.mSyncIconResIdArray = var1;
      var1.put(0, 2131233649);
      this.mSyncIconResIdArray.append(1, 2131233649);
      this.mSyncIconResIdArray.append(2, 2131233649);
      this.mSyncIconResIdArray.append(3, 2131233480);
      this.mSyncIconResIdArray.append(4, 2131233550);
      this.mSyncIconResIdArray.append(5, 2131233461);
      this.mSyncIconResIdArray.append(6, 2131233494);
      this.mSyncIconResIdArray.append(7, 2131233479);
      this.mSyncIconResIdArray.append(8, 2131233583);
      this.mSyncIconResIdArray.append(9, 2131233582);
      this.mSyncIconResIdArray.append(16, 2131233412);
      this.mSyncIconResIdArray.append(17, 2131233413);
      this.mSyncIconResIdArray.append(18, 2131233414);
      this.mSyncIconResIdArray.append(19, 2131233403);
      this.mSyncIconResIdArray.append(20, 2131233404);
      this.mSyncIconResIdArray.append(21, 2131233405);
      this.mSyncIconResIdArray.append(32, 2131233406);
      this.mSyncIconResIdArray.append(33, 2131233407);
      this.mSyncIconResIdArray.append(34, 2131233408);
      this.mSyncIconResIdArray.append(35, 2131233409);
      this.mSyncIconResIdArray.append(36, 2131233410);
      this.mSyncIconResIdArray.append(37, 2131233411);
      GeneralSyncData.mSyncTopIconResIdArray = new int[6];
      GeneralSyncData.mSyncTopIconCount = 6;
      GeneralSyncData.mInfoLineShowAmount = 5;
      GeneralSyncData.mIsShowSoftKey = true;
   }

   private long bytesToLong(byte[] var1) {
      long var3 = 0L;

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3 = (long)((double)var3 + (double)(var1[var2] & 255) * Math.pow(256.0, (double)var2));
      }

      return var3;
   }

   private void changeOriginalDevicePage(List var1, String[] var2, boolean var3, boolean var4) {
      OriginalCarDevicePageUiSet var5 = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      if (var5 != null) {
         var5.setItems(var1);
         var5.setRowBottomBtnAction(var2);
         var5.setHavePlayTimeSeekBar(var3);
         var5.setHaveSongList(var4);
         Bundle var6 = new Bundle();
         var6.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var6);
         this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      }
   }

   private void cleanDevice() {
      GeneralOriginalCarDeviceData.runningState = "";
      GeneralOriginalCarDeviceData.am_st = false;
      GeneralOriginalCarDeviceData.st = false;
      GeneralOriginalCarDeviceData.scan = false;
      GeneralOriginalCarDeviceData.disc_scan = false;
      GeneralOriginalCarDeviceData.rpt = false;
      GeneralOriginalCarDeviceData.rpt_disc = false;
      GeneralOriginalCarDeviceData.rdm_off = false;
      GeneralOriginalCarDeviceData.rdm_disc = false;
      GeneralOriginalCarDeviceData.startTime = "     ";
      GeneralOriginalCarDeviceData.endTime = "     ";
      GeneralOriginalCarDeviceData.progress = 0;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void cleanSongList() {
      if (GeneralOriginalCarDeviceData.songList != null) {
         GeneralOriginalCarDeviceData.songList.clear();
      }

   }

   private void dccOffOnCtrl(byte[] var1) {
      if (this.mCanBusInfoInt[2] == 18 && !this.isSwcMsgReturn(var1)) {
         Intent var2 = new Intent(this.mContext, MainActivity.class);
         var2.addFlags(268435456);
         this.mContext.startActivity(var2);
      }

   }

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private static ArrayList getArrayEachBitsBool(int[] var0, int var1) {
      ArrayList var5 = new ArrayList();
      var5.clear();
      int var3 = var0.length;
      int var2 = var1 + 2;
      var3 -= var2;
      int[] var4 = new int[var3];

      for(var1 = 0; var1 < var0.length - var2; ++var1) {
         var4[var1] = var0[var1 + var2];
      }

      for(var1 = 0; var1 < var3; ++var1) {
         for(var2 = 0; var2 < 8; ++var2) {
            var5.add(getBooleanFromByteWithBit(var4[var1], var2, 1));
         }
      }

      return var5;
   }

   private static Boolean getBooleanFromByteWithBit(int var0, int var1, int var2) {
      return ((var0 & 255) >> var1 & (1 << var2) - 1) == 1 ? true : false;
   }

   private String getCdStatus(int var1) {
      Context var2 = this.mContext;
      return var2.getString(CommUtil.getStrIdByResId(var2, "_123_divice_status_" + var1));
   }

   private String getCdTrackInfo() {
      ArrayList var4 = new ArrayList();
      int var2 = 4;

      byte[] var5;
      while(true) {
         var5 = this.mCanBusInfoByte;
         if (var2 >= var5.length) {
            break;
         }

         byte var1 = var5[var2 - 1];
         if (var1 == 0 && var5[var2] == 0) {
            break;
         }

         var4.add(var1);
         var4.add(this.mCanBusInfoByte[var2]);
         var2 += 2;
      }

      int var3 = var4.size();
      var5 = new byte[var3];

      for(var2 = 0; var2 < var3; ++var2) {
         var5[var2] = (Byte)var4.get(var2);
      }

      String var7;
      try {
         var7 = new String(var5, "unicode");
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
         var7 = "";
      }

      return var7;
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private String getPreStorage(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131768341);
      } else {
         var2 = " P" + var1;
      }

      return var2;
   }

   private String getRadioStatus(int var1) {
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               var2 = "";
            } else {
               var2 = this.mContext.getResources().getString(2131768340);
            }
         } else {
            var2 = this.mContext.getResources().getString(2131768339);
         }
      } else {
         var2 = this.mContext.getResources().getString(2131768338);
      }

      return var2;
   }

   private String getSyncInfo(byte[] var1) {
      int var4 = var1.length;
      int var2 = 5;

      int var3;
      while(true) {
         var3 = var4;
         if (var2 >= var1.length) {
            break;
         }

         var3 = var2 - 1;
         if (var1[var3] == 0 && var1[var2] == 0) {
            break;
         }

         var2 += 2;
      }

      String var7;
      try {
         byte[] var5 = Arrays.copyOfRange(var1, 4, var3);
         var7 = new String(var5, "unicode");
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
         var7 = "";
      }

      return var7;
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierData(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, this.getUiMgr(var1).getAmplifierPageUiSet(var1));
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte[] var4 = new byte[]{22, -83, 4, (byte)GeneralAmplifierData.bandBass};
            byte[] var5 = new byte[]{22, -83, 6, (byte)GeneralAmplifierData.bandTreble};
            byte var3 = (byte)GeneralAmplifierData.bandMiddle;
            byte[] var8 = new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume};
            byte[] var6 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 7)};
            byte[] var7 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 7)};
            this.iterator = Arrays.stream(new byte[][]{var4, var5, {22, -83, 5, var3}, var8, var6, var7}).iterator();
         }

         public void run() {
            if (this.iterator.hasNext()) {
               CanbusMsgSender.sendMsg((byte[])this.iterator.next());
            } else {
               this.val$util.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void initId3() {
      this.mId3s = new ID3[]{new ID3(this, 146, "Unicode", 34), new ID3(this, 147, "Unicode", 34), new ID3(this, 150, "Unicode", 34)};
   }

   private boolean isCdInfoRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mOriginalCdInfoCopy)) {
         return true;
      } else {
         this.mOriginalCdInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isCdMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mOriginalCdCopy)) {
         return true;
      } else {
         this.mOriginalCdCopy = Arrays.copyOf(var1, var1.length);
         if (this.isCdFirst) {
            this.isCdFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mDoorInfoCopy)) {
         return true;
      } else {
         this.mDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return true;
      } else {
         return false;
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen;
      }
   }

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   private boolean isOnlyRearAirDataChange() {
      if (SharePreUtil.getIntValue(this.mContext, "is_rear_air_temp_change_ford", 0) != rearTemp) {
         return true;
      } else if (SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_lv_change", 0) != GeneralAirData.rear_wind_level) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_rear_air_power", false) != GeneralAirData.rear_power) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_rear_lock", false) != GeneralAirData.rear_lock;
      }
   }

   private boolean isPanelBtnKeygRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isParkingMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mParkingInfoCopy)) {
         return true;
      } else {
         this.mParkingInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isParkingFirst) {
            isParkingFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isRadioInfoRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mOriginalRadioInfoCopy)) {
         return true;
      } else {
         this.mOriginalRadioInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isRadioMsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.mOriginalRadioCopy)) {
         return true;
      } else {
         this.mOriginalRadioCopy = Arrays.copyOf(var1, var1.length);
         if (this.isRadioFirst) {
            this.isRadioFirst = false;
            return true;
         } else {
            return false;
         }
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

   private boolean isSync0xD3MsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusSync0xD3InfoCopy)) {
         return true;
      } else {
         this.mCanbusSync0xD3InfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isWarningMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusWarnInfoCopy)) {
         return true;
      } else {
         this.mCanBusWarnInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isWarnFirst) {
            isWarnFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 8) {
         if (var1 != 9) {
            if (var1 != 23) {
               if (var1 != 45) {
                  if (var1 != 55) {
                     if (var1 != 98) {
                        if (var1 != 101) {
                           switch (var1) {
                              case 0:
                                 this.realKeyClick(0);
                                 break;
                              case 1:
                                 this.realKeyClick(7);
                                 break;
                              case 2:
                                 this.realKeyClick(8);
                                 break;
                              case 3:
                                 this.realKeyClick(3);
                                 break;
                              case 4:
                                 this.realKeyClick(187);
                                 break;
                              case 5:
                                 this.realKeyClick(207);
                                 break;
                              case 6:
                                 this.realKeyClick(206);
                                 break;
                              default:
                                 switch (var1) {
                                    case 11:
                                       this.realKeyClick(2);
                                       break;
                                    case 12:
                                       this.realKeyClick(57);
                                       break;
                                    case 13:
                                       this.realKeyClick(45);
                                       break;
                                    case 14:
                                       this.realKeyClick(46);
                                       break;
                                    case 15:
                                       this.realKeyClick(49);
                                 }
                           }
                        } else {
                           this.realKeyClick(31);
                        }
                     } else {
                        this.realKeyClick(95);
                     }
                  } else {
                     this.realKeyClick(58);
                  }
               } else {
                  this.realKeyClick(59);
               }
            } else {
               this.realKeyClick(128);
            }
         } else {
            this.realKeyClick(47);
         }
      } else {
         this.realKeyClick(48);
      }

   }

   private void languageSettingInfo() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = 2;
      int var2 = var3[2];
      if (var2 != 27) {
         if (var2 != 0) {
            var1 = var2 - 1;
         } else {
            var1 = 0;
         }
      }

      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(0, 0, var1));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private static List mergeLists(List... var0) {
      int var1 = 0;
      Class var3 = var0[0].getClass();

      List var5;
      try {
         var5 = (List)var3.newInstance();
      } catch (Exception var4) {
         var4.printStackTrace();
         var5 = null;
      }

      for(int var2 = var0.length; var1 < var2; ++var1) {
         var5.addAll(var0[var1]);
      }

      return var5;
   }

   private void playPresetChann(int var1) throws RemoteException {
      FutureUtil.instance.playPresetFreq(var1);
      Intent var2 = new Intent();
      var2.setAction("com.hzbhd.intent.action.fm");
      this.mContext.startActivity(var2);
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick1(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void realKeyClick2(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick3_1(int var1) {
      this.realKeyClick3_1(this.mContext, var1, this.mCanBusInfoInt[2], Math.abs(this.mCanBusInfoByte[3]));
   }

   private void realKeyClick3_2(int var1) {
      this.realKeyClick3_2(this.mContext, var1, this.mCanBusInfoInt[2], Math.abs(this.mCanBusInfoByte[3]));
   }

   private void refreshFordParkAssistFlowView(int[] var1) {
      int var2 = var1[3];
      if (var2 != 0 && this.ford_park_assist_info != var2) {
         this.ford_park_assist_info = var2;
         if (this.mParkAssistFloatView == null) {
            this.mParkAssistFloatView = new ParkAssistFloatView(this.mContext);
         }

         this.runOnUi(new CallBackInterface(this, var2) {
            final MsgMgr this$0;
            final int val$cmd;

            {
               this.this$0 = var1;
               this.val$cmd = var2;
            }

            public void callback() {
               Bundle var1 = new Bundle();
               var1.putByte("PARK_ASSIST_REFRESH_UI_BUNDLE_KEY", (byte)this.val$cmd);
               this.this$0.mParkAssistFloatView.refreshUi(var1);
            }
         });
      }

   }

   private void reportID3Info(ID3[] var1, boolean var2) {
      (new Thread(this, var1, var2) {
         final MsgMgr this$0;
         final ID3[] val$id3s;
         final boolean val$isClean;

         {
            this.this$0 = var1;
            this.val$id3s = var2;
            this.val$isClean = var3;
         }

         public void run() {
            super.run();

            Exception var10000;
            label54: {
               boolean var10001;
               ID3[] var3;
               try {
                  var3 = this.val$id3s;
                  if (var3.length <= 0) {
                     return;
                  }
               } catch (Exception var8) {
                  var10000 = var8;
                  var10001 = false;
                  break label54;
               }

               int var1 = 0;

               try {
                  if (!var3[0].isId3Change()) {
                     return;
                  }

                  if (this.val$isClean) {
                     sleep(900L);
                  }
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label54;
               }

               int var2;
               try {
                  var3 = this.val$id3s;
                  var2 = var3.length;
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label54;
               }

               while(true) {
                  if (var1 >= var2) {
                     return;
                  }

                  ID3 var4 = var3[var1];

                  try {
                     sleep(100L);
                     this.this$0.reportID3InfoFinal((byte)var4.getHead(), var4.getId3(), var4.getCharsetName(), var4.getLength());
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                     break;
                  }

                  ++var1;
               }
            }

            Exception var9 = var10000;
            var9.printStackTrace();
         }
      }).start();
   }

   private void reportID3InfoFinal(byte var1, String var2, String var3, int var4) {
      try {
         byte[] var6 = DataHandleUtils.exceptBOMHead(var2.getBytes(var3));
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, var1}, var6), var4));
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (GeneralAirData.fahrenheit_celsius) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = (int)((float)var1 * 0.5F) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (GeneralAirData.fahrenheit_celsius) {
         var2 = (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
      } else {
         var2 = (float)(((double)var1 * 0.5 - 40.0) * 1.8 + 32.0) + this.getTempUnitF(this.mContext);
      }

      return var2;
   }

   private String resolveRearTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "Close";
      } else if (1 == var1) {
         var2 = "Lo";
      } else if (9 == var1) {
         var2 = "Hi";
      } else if (5 == var1) {
         var2 = "Mid";
      } else {
         var2 = "" + var1;
      }

      return var2;
   }

   private void sendId3() {
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -110}, 34));
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -109}, 34));
      CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -106}, 34));
   }

   private void sendSourceMsg2(String var1, int var2) {
      byte[] var3 = var1.getBytes();
      var3 = DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte)var2}, var3), 34);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), var3);
   }

   private void set0xD0SyncDisplayInfo() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralSyncData.mSyncTopIconCount = 6;
         GeneralSyncData.mSyncScreemNumber = this.mCanBusInfoInt[2];
         int var2 = this.mCanBusInfoInt[3];
         int var1 = var2 >> 4;
         var2 &= 15;
         String var11;
         if (var1 >= 1 && var1 <= 5) {
            var11 = this.getSyncInfo(this.mCanBusInfoByte);
            Iterator var10 = GeneralSyncData.mInfoUpdateEntityList.iterator();

            while(var10.hasNext()) {
               SyncListUpdateEntity var8 = (SyncListUpdateEntity)var10.next();
               if (var8.getIndex() == var1 - 1) {
                  if (var2 == 0) {
                     var8.setInfo("");
                  }

                  var8.setInfo(var8.getInfo() + var11);
                  this.updateSyncActivity((Bundle)null);
                  break;
               }
            }
         } else if (var1 >= 10 && var1 <= 13) {
            var11 = this.getSyncInfo(this.mCanBusInfoByte);
            Iterator var7 = GeneralSyncData.mSoftKeyUpdateEntityList.iterator();

            while(var7.hasNext()) {
               SyncSoftKeyUpdateEntity var9 = (SyncSoftKeyUpdateEntity)var7.next();
               if (var9.getIndex() == var1 - 10) {
                  if (var2 == 0) {
                     var9.setName("");
                  }

                  var9.setName(var9.getName() + var11);
                  this.updateSyncActivity((Bundle)null);
                  break;
               }
            }
         } else if (var1 == 15) {
            GeneralSyncData.mSelectedLineIndex = -1;
            Iterator var6 = GeneralSyncData.mInfoUpdateEntityList.iterator();

            while(var6.hasNext()) {
               SyncListUpdateEntity var5 = (SyncListUpdateEntity)var6.next();
               var1 = var5.getIndex();
               int[] var4 = this.mCanBusInfoInt;
               int var3 = var1 * 2;
               var2 = var4[var3 + 10];
               var3 = var4[var3 + 11];
               var5.setLeftIconResId(this.mSyncIconResIdArray.get(var2));
               var5.setRightIconResId(this.mSyncIconResIdArray.get(var3));
               if (var2 == 2 && var3 == 2) {
                  GeneralSyncData.mSelectedLineIndex = var1;
               }
            }

            for(var1 = 0; var1 < GeneralSyncData.mSyncTopIconCount; ++var1) {
               GeneralSyncData.mSyncTopIconResIdArray[var1] = this.mSyncIconResIdArray.get(this.mCanBusInfoInt[var1 + 4]);
            }

            this.updateSyncActivity((Bundle)null);
         }
      }

   }

   private void set0xD2SyncPlayInfo() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var1 = this.mCanBusInfoInt;
         var1 = this.getTime(var1[4] + var1[5] * 256);
         GeneralSyncData.mSyncTime = this.mDecimalFormat00.format((long)var1[0]) + ":" + this.mDecimalFormat00.format((long)var1[1]) + ":" + this.mDecimalFormat00.format((long)var1[2]);
         GeneralSyncData.mSyncScreemNumber = this.mCanBusInfoInt[2];
         GeneralSyncData.mIsSyncTimeChange = true;
         this.updateSyncActivity((Bundle)null);
      }

   }

   private void set0xD3SyncStatus(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         if (this.mCanBusInfoInt[2] == 0) {
            this.exitAuxIn2();
         } else {
            this.enterAuxIn2(var1, Constant.SyncActivity);
         }

         int var2 = this.mSyncBtStatus;
         int var3 = this.mCanBusInfoInt[4];
         if (var2 != var3) {
            this.mSyncBtStatus = var3;
            this.getUiMgr(var1).updateBtStatus(var1, this.mSyncBtStatus);
         }
      }

   }

   private void setAmplifierData() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
         GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 7;
         GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 7;
         GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
         GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
         GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
         this.saveAmplifierData(this.mContext, this.mCanId);
         this.updateAmplifierActivity(new Bundle());
      }

      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(6, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2)));
      var1.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setCarDoorInfo() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setCarNumber() {
      ArrayList var1 = new ArrayList();
      byte[] var2 = this.mCanBusInfoByte;
      var1.add(new DriverUpdateEntity(0, 3, new String(Arrays.copyOfRange(var2, 2, var2.length))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarRangeInfo() {
      ArrayList var3 = new ArrayList();
      StringBuilder var1 = new StringBuilder();
      byte[] var2 = this.mCanBusInfoByte;
      var3.add(new DriverUpdateEntity(0, 2, var1.append((float)((double)this.bytesToLong(new byte[]{var2[8], var2[7], var2[6]}) * 0.1)).append("Km").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatus() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      byte[] var2 = this.mCanBusInfoByte;
      var1.add(new DriverUpdateEntity(0, 4, var3.append(this.bytesToLong(new byte[]{var2[5], var2[4]})).append("Rpm").toString()));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoByte;
      var1.add(new DriverUpdateEntity(0, 5, var3.append(this.bytesToLong(new byte[]{var2[7], var2[6]})).append("Km/h").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarStatusInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 255) {
                     this.data1 = this.mContext.getResources().getString(2131767387);
                  }
               } else {
                  this.data1 = this.mContext.getResources().getString(2131767384);
               }
            } else {
               this.data1 = this.mContext.getResources().getString(2131767388);
            }
         } else {
            this.data1 = this.mContext.getResources().getString(2131767383);
         }
      } else {
         this.data1 = this.mContext.getResources().getString(2131767385);
      }

      var1 = this.mCanBusInfoInt[3];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     this.data2 = "D";
                  }
               } else {
                  this.data2 = "R";
               }
            } else {
               this.data2 = "N";
            }
         } else {
            this.data2 = "P";
         }
      } else {
         this.data2 = this.mContext.getResources().getString(2131768909);
      }

      ArrayList var2 = new ArrayList();
      var2.add(new DriverUpdateEntity(0, 0, this.data1));
      var2.add(new DriverUpdateEntity(0, 1, this.data2));
      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarType() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 1 && var1 != 2) {
         var2.add(new DriverUpdateEntity(0, 6, this.mContext.getResources().getString(2131768271)));
      }

      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setCarVolumeCtrl() {
      RemoteException var10000;
      label104: {
         int[] var2;
         boolean var10001;
         try {
            var2 = this.mCanBusInfoInt;
         } catch (RemoteException var16) {
            var10000 = var16;
            var10001 = false;
            break label104;
         }

         StringBuilder var3;
         FutureUtil var17;
         switch (var2[2]) {
            case 1:
               int var1 = var2[3];
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           return;
                        }

                        try {
                           this.changeBandAm2();
                        } catch (RemoteException var12) {
                           var10000 = var12;
                           var10001 = false;
                           break;
                        }
                     } else {
                        try {
                           this.changeBandAm1();
                        } catch (RemoteException var13) {
                           var10000 = var13;
                           var10001 = false;
                           break;
                        }
                     }
                  } else {
                     try {
                        this.changeBandFm2();
                     } catch (RemoteException var14) {
                        var10000 = var14;
                        var10001 = false;
                        break;
                     }
                  }
               } else {
                  try {
                     this.changeBandFm1();
                  } catch (RemoteException var15) {
                     var10000 = var15;
                     var10001 = false;
                     break;
                  }
               }

               return;
            case 2:
               try {
                  this.changeBandAm1();
                  SystemClock.sleep(500L);
                  var17 = FutureUtil.instance;
                  var3 = new StringBuilder();
                  var17.setCurrentFreq(var3.append(this.mCanBusInfoByte[3] & 255).append("").append(this.mCanBusInfoByte[4] & 255).append("").toString());
                  return;
               } catch (RemoteException var11) {
                  var10000 = var11;
                  var10001 = false;
                  break;
               }
            case 3:
               try {
                  this.changeBandFm1();
                  SystemClock.sleep(500L);
                  var17 = FutureUtil.instance;
                  var3 = new StringBuilder();
                  var17.setCurrentFreq(var3.append(this.mCanBusInfoByte[3] & 255).append(".").append(this.mCanBusInfoByte[4] & 255).toString());
                  return;
               } catch (RemoteException var10) {
                  var10000 = var10;
                  var10001 = false;
                  break;
               }
            case 4:
               try {
                  this.playPresetChann(this.mCanBusInfoByte[3] - 1 & 255);
                  return;
               } catch (RemoteException var9) {
                  var10000 = var9;
                  var10001 = false;
                  break;
               }
            case 5:
               if (var2[3] == 1) {
                  try {
                     this.realKeyClick4(this.mContext, 45);
                  } catch (RemoteException var7) {
                     var10000 = var7;
                     var10001 = false;
                     break;
                  }
               } else {
                  try {
                     this.realKeyClick4(this.mContext, 46);
                  } catch (RemoteException var8) {
                     var10000 = var8;
                     var10001 = false;
                     break;
                  }
               }

               return;
            case 6:
               try {
                  FutureUtil.instance.gotoTrack(this.mCanBusInfoInt[3]);
                  return;
               } catch (RemoteException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break;
               }
            case 7:
               if (var2[3] == 1) {
                  try {
                     FutureUtil.instance.playMpeg();
                  } catch (RemoteException var4) {
                     var10000 = var4;
                     var10001 = false;
                     break;
                  }
               } else {
                  try {
                     FutureUtil.instance.pauseMpeg();
                  } catch (RemoteException var5) {
                     var10000 = var5;
                     var10001 = false;
                     break;
                  }
               }

               return;
            default:
               return;
         }
      }

      RemoteException var18 = var10000;
      var18.printStackTrace();
   }

   private void setCdDataInfo() {
      GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131767994);
      ArrayList var4 = new ArrayList();
      StringBuilder var5 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(0, var5.append(var3[7] * 256 + var3[8]).append("").toString()));
      var5 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var4.add(new OriginalCarDeviceUpdateEntity(1, var5.append(var3[9] * 256 + var3[10]).append("").toString()));
      String var6;
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
         var6 = this.mContext.getResources().getString(2131769504);
      } else {
         var6 = this.mContext.getResources().getString(2131768042);
      }

      var4.add(new OriginalCarDeviceUpdateEntity(4, var6));
      if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
         var6 = this.mContext.getResources().getString(2131769504);
      } else {
         var6 = this.mContext.getResources().getString(2131768042);
      }

      var4.add(new OriginalCarDeviceUpdateEntity(5, var6));
      this.mList1.clear();
      this.mList1.addAll(var4);
      GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
      GeneralOriginalCarDeviceData.runningState = this.getCdStatus(this.mCanBusInfoInt[5]);
      GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      var3 = this.mCanBusInfoInt;
      GeneralOriginalCarDeviceData.startTime = this.startEndTimeMethod(var3[13] * 256 + var3[14]);
      var3 = this.mCanBusInfoInt;
      GeneralOriginalCarDeviceData.endTime = this.startEndTimeMethod(var3[11] * 256 + var3[12]);
      var3 = this.mCanBusInfoInt;
      int var2 = var3[11];
      int var1 = var3[12];
      if (var2 * 256 + var1 == 0) {
         GeneralOriginalCarDeviceData.progress = 0;
      } else {
         GeneralOriginalCarDeviceData.progress = (var3[13] * 256 + var3[14]) * 100 / (var2 * 256 + var1);
      }

      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setCdInfo() {
      ArrayList var1 = new ArrayList();
      if (this.mCanBusInfoInt[2] == 1) {
         var1.add(new OriginalCarDeviceUpdateEntity(2, this.getCdTrackInfo()));
      }

      if (this.mCanBusInfoInt[2] == 2) {
         var1.add(new OriginalCarDeviceUpdateEntity(3, this.getCdTrackInfo()));
      }

      this.mList2.clear();
      this.mList2.addAll(var1);
      GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setDriveData() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 2, var2.append(var3[8] * 256 * 256 + var3[9] * 256 + var3[10]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setEnvironmentLight() {
      ArrayList var1 = new ArrayList();
      this.FactoryCode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
      var1.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8)));
      var1.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8)));
      var1.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x62_data3_765"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x62_data3_765", "_94_atmosphere_lamp"), this.mCanBusInfoInt[11])).setProgress(this.mCanBusInfoInt[11]));
      this.runOnUi(new CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            TypeInView.toJudge(this.this$0.FactoryCode);
         }
      });
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setFrontAirData() {
      GeneralAirData.fahrenheit_celsius = SharePreUtil.getBoolValue(this.mContext, "Temp_Unit", false);
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         if (!this.isFirst()) {
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.max_heat = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralAirData.center_wheel = "Lv." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
            int var1 = this.mCanBusInfoInt[6];
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 5) {
                        if (var1 != 6) {
                           switch (var1) {
                              case 11:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 break;
                              case 12:
                                 GeneralAirData.front_left_blow_foot = true;
                                 GeneralAirData.front_right_blow_foot = true;
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 break;
                              case 13:
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_right_blow_head = true;
                                 break;
                              case 14:
                                 GeneralAirData.front_left_blow_foot = true;
                                 GeneralAirData.front_right_blow_foot = true;
                                 GeneralAirData.front_left_blow_window = true;
                                 GeneralAirData.front_right_blow_window = true;
                                 GeneralAirData.front_left_blow_head = true;
                                 GeneralAirData.front_right_blow_head = true;
                           }
                        } else {
                           GeneralAirData.front_left_blow_head = true;
                           GeneralAirData.front_right_blow_head = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                  }
               } else {
                  GeneralAirData.front_defog = true;
               }
            } else {
               GeneralAirData.front_left_auto_wind = true;
               GeneralAirData.front_right_auto_wind = true;
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]) ^ true;
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
            int[] var4 = this.mCanBusInfoInt;
            var1 = var4[12];
            rearTemp = var1;
            outDoorTemp = var4[13];
            GeneralAirData.rear_temperature = this.resolveRearTemp(var1);
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
            boolean var3 = this.isOnlyRearAirDataChange();
            boolean var2 = this.isOnlyOutDoorDataChange();
            if (var3 && !this.isOnlyOutDoorDataChange()) {
               SharePreUtil.setIntValue(this.mContext, "is_rear_air_temp_change_ford", rearTemp);
               SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_lv_change", GeneralAirData.rear_wind_level);
               SharePreUtil.setBoolValue(this.mContext, "is_rear_air_power", GeneralAirData.rear_power);
               SharePreUtil.setBoolValue(this.mContext, "is_rear_lock", GeneralAirData.rear_lock);
               this.updateAirActivity(this.mContext, 1002);
            }

            if (!var3 && !var2) {
               this.updateAirActivity(this.mContext, 1001);
            }

            if (var2) {
               SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", (float)outDoorTemp);
               this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
            }

         }
      }
   }

   private void setFrontRearRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[6], var1[7], var1[8], var1[9]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setProbeRadarLocationData(7, var1[10], var1[11], 0, 0);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLeftRightRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRightRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setLeftRadarLocationData(7, var1[6], var1[7], var1[8], var1[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLightSetting() {
      ArrayList var2 = new ArrayList();

      for(int var1 = 0; var1 < 5; ++var1) {
         var2.add(new SettingUpdateEntity(2, var1, this.getIndexBy2Bit(DataHandleUtils.getBoolBit(var1, this.mCanBusInfoInt[3]))));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setOriginalCameraStatusInfo() {
      angleWide = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      overlook = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      ArrayList var1 = new ArrayList();
      var1.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
      var1.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setOriginalCdPage() {
      ArrayList var3 = new ArrayList();
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "total_file", ""));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_song_list", ""));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_music", "song_name", ""));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_artist", "singer", ""));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "ford_original_rdm", ""));
      var3.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "ford_original_rep", ""));
      String[] var2 = new String[]{"up", "random", "play", "cycle", "down"};
      int var1 = this.mCanBusInfoInt[5];
      if (var1 == 1) {
         var2 = new String[]{"up", "random", "pause", "cycle", "down"};
      } else if (var1 == 5) {
         var2 = new String[]{"up", "random", "play", "cycle", "down"};
      }

      this.changeOriginalDevicePage(var3, var2, true, false);
   }

   private void setOriginalPanel() {
      if (this.mCanBusInfoInt[2] == 1) {
         if (this.mCanBusInfoByte[3] > 0) {
            this.realKeyClick3_1(7);
         }

         if (this.mCanBusInfoByte[3] < 0) {
            this.realKeyClick3_1(8);
         }
      }

      if (this.mCanBusInfoInt[2] == 2) {
         if (this.mCanBusInfoByte[3] > 0) {
            this.realKeyClick3_2(48);
         }

         if (this.mCanBusInfoByte[3] < 0) {
            this.realKeyClick3_2(47);
         }
      }

   }

   private void setOriginalRadioPage() {
      ArrayList var1 = new ArrayList();
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
      var1.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
      this.changeOriginalDevicePage(var1, new String[]{"band", "up", "left", "right", "down", "radio_scan"}, false, true);
   }

   private void setPanelBtnInfo() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.realKeyClick2(0);
            break;
         case 1:
            this.realKeyClick2(134);
            break;
         case 2:
            this.realKeyClick2(45);
            break;
         case 3:
            this.realKeyClick2(46);
         case 4:
         case 7:
         case 8:
         case 18:
         case 20:
         case 21:
         case 22:
         case 27:
         case 28:
         case 29:
         case 30:
         case 34:
         case 35:
         case 37:
         case 38:
         case 39:
         case 41:
         case 43:
         case 47:
         default:
            break;
         case 5:
            this.realKeyClick2(4);
            break;
         case 6:
            this.realKeyClick2(50);
            break;
         case 9:
            this.realKeyClick2(3);
            break;
         case 10:
            this.realKeyClick2(33);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 21});
            break;
         case 11:
            this.realKeyClick2(34);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 22});
            break;
         case 12:
            this.realKeyClick2(35);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 23});
            break;
         case 13:
            this.realKeyClick2(36);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 24});
            break;
         case 14:
            this.realKeyClick2(37);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 25});
            break;
         case 15:
            this.realKeyClick2(38);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 26});
            break;
         case 16:
            this.realKeyClick2(95);
            break;
         case 17:
            this.realKeyClick2(31);
            break;
         case 19:
            this.realKeyClick2(196);
            break;
         case 23:
            this.realKeyClick2(45);
            break;
         case 24:
            this.realKeyClick2(46);
            break;
         case 25:
            this.realKeyClick2(47);
            break;
         case 26:
            this.realKeyClick2(48);
            break;
         case 31:
            this.realKeyClick2(141);
            break;
         case 32:
            this.realKeyClick2(128);
            break;
         case 33:
            this.realKeyClick2(223);
            break;
         case 36:
            this.realKeyClick2(59);
            break;
         case 40:
            this.realKeyClick2(68);
            break;
         case 42:
            this.realKeyClick2(49);
            break;
         case 44:
            this.realKeyClick2(2);
            break;
         case 45:
            this.realKeyClick2(129);
            break;
         case 46:
            this.realKeyClick2(94);
            break;
         case 48:
            this.realKeyClick2(39);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 27});
            break;
         case 49:
            this.realKeyClick2(40);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 28});
            break;
         case 50:
            this.realKeyClick2(41);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 29});
            break;
         case 51:
            this.realKeyClick2(32);
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 20});
            break;
         case 52:
            this.realKeyClick2(63);
            break;
         case 53:
            this.realKeyClick2(64);
            break;
         case 54:
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 1});
            break;
         case 55:
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 2});
            break;
         case 56:
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 3});
            break;
         case 57:
            CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 4});
            break;
         case 58:
            this.realKeyClick2(130);
            break;
         case 59:
            this.realKeyClick2(59);
            break;
         case 60:
            this.realKeyClick2(48);
            break;
         case 61:
            this.realKeyClick2(47);
            break;
         case 62:
            this.realKeyClick2(75);
            break;
         case 63:
            this.realKeyClick2(151);
            break;
         case 64:
            this.realKeyClick2(152);
      }

   }

   private void setRadioInfo() {
      GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(2131769755);
      GeneralOriginalCarDeviceData.runningState = this.getRadioStatus(this.mCanBusInfoInt[6]);
      ArrayList var2 = new ArrayList();
      String var1;
      if (this.mCanBusInfoInt[2] == 1) {
         var1 = "AM";
      } else {
         var1 = "FM";
      }

      var2.add(new OriginalCarDeviceUpdateEntity(0, var1));
      if (this.mCanBusInfoInt[2] == 1) {
         StringBuilder var4 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         var2.add(new OriginalCarDeviceUpdateEntity(1, var4.append(DataHandleUtils.getMsbLsbResult(var3[4], var3[3])).append("KHz").toString()));
      } else {
         StringBuilder var6 = new StringBuilder();
         int[] var5 = this.mCanBusInfoInt;
         var2.add(new OriginalCarDeviceUpdateEntity(1, var6.append((float)DataHandleUtils.getMsbLsbResult(var5[4], var5[3]) / 10.0F).append("MHz").toString()));
      }

      var2.add(new OriginalCarDeviceUpdateEntity(2, this.getPreStorage(this.mCanBusInfoInt[5])));
      GeneralOriginalCarDeviceData.mList = var2;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setRadioPresetInfo() {
      ArrayList var4 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      int var2;
      int var3;
      int[] var5;
      if (var1 != 1) {
         if (var1 == 2) {
            for(var1 = 1; var1 <= 12; ++var1) {
               var5 = this.mCanBusInfoInt;
               var3 = var1 * 2;
               var2 = var5[var3 + 2];
               var3 = var5[var3 + 1];
               var4.add(new SongListEntity((float)(var2 * 256 + var3) / 10.0F + "MHz"));
            }
         }
      } else {
         for(var1 = 1; var1 <= 18; ++var1) {
            var5 = this.mCanBusInfoInt;
            var3 = var1 * 2;
            var2 = var5[var3 + 2];
            var3 = var5[var3 + 1];
            var4.add(new SongListEntity(var2 * 256 + var3 + "KHz"));
         }
      }

      GeneralOriginalCarDeviceData.songList = var4;
      this.updateOriginalCarDeviceActivity((Bundle)null);
   }

   private void setReversingVideo() {
      int var1 = this.mCanBusInfoInt[5];
      Context var3 = this.mContext;
      boolean var2;
      if (var1 == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.forceReverse(var3, var2);
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(4, 0, this.mCanBusInfoInt[4]));
      var4.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      var4.add(new SettingUpdateEntity(4, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSelectCarType() {
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 3});
   }

   private void setSportSettingInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(3, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
      var1.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTipsInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 0, this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]))));
      SharePreUtil.setBoolValue(this.mContext, "Temp_Unit", DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
      var1.add(new SettingUpdateEntity(1, 1, this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackInfo() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWarningInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = 0;
      warningList = (ArrayList)getArrayEachBitsBool(var2, 0).clone();
      ArrayList var3 = new ArrayList();
      var3.clear();

      for(; var1 < warningList.size(); ++var1) {
         if ((Boolean)warningList.get(var1) && fordHiworldWarinIds[var1] != -1) {
            var3.add(new WarningEntity(this.mContext.getResources().getString(fordHiworldWarinIds[var1])));
         }
      }

      GeneralWarningDataData.dataList = var3;
      this.updateWarningActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      if (this.getCurrentEachCanId() == 17) {
         SelectCanTypeUtil.enableApp(var1, Constant.SyncActivity);
      } else {
         SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      }

      Log.d("lai", "afterServiceNormalSetting: " + this.getCurrentEachCanId());
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, (byte)DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.sendId3();
   }

   public void auxInInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 12, (byte)DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.sendId3();
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -51, 5});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -51, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -51, 2});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -51, 4});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      int var6 = DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
      String var7 = (new DecimalFormat("00")).format((long)(var4 / 60));
      String var8 = (new DecimalFormat("00")).format((long)(var4 % 60));
      var8 = "    " + var7 + var8 + "    ";
      byte var5 = (byte)var6;
      var1 = var8.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, var5}, var1));
      this.sendId3();
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 34) {
            if (var3 != 38) {
               if (var3 != 56) {
                  if (var3 != 97) {
                     if (var3 != 116) {
                        if (var3 != 136) {
                           if (var3 != 148) {
                              if (var3 != 174) {
                                 if (var3 != 208) {
                                    if (var3 != 232) {
                                       if (var3 != 240) {
                                          if (var3 != 49) {
                                             if (var3 != 50) {
                                                if (var3 != 65) {
                                                   if (var3 != 66) {
                                                      if (var3 != 103) {
                                                         if (var3 != 104) {
                                                            if (var3 != 132) {
                                                               if (var3 != 133) {
                                                                  if (var3 != 165) {
                                                                     if (var3 != 166) {
                                                                        if (var3 != 210) {
                                                                           if (var3 != 211) {
                                                                              switch (var3) {
                                                                                 case 17:
                                                                                    this.keyControl0x11();
                                                                                    this.setTrackInfo();
                                                                                    this.updateSpeedInfo(this.mCanBusInfoInt[3]);
                                                                                    break;
                                                                                 case 18:
                                                                                    if (this.isDoorMsgReturn(var2)) {
                                                                                       return;
                                                                                    }

                                                                                    this.setCarDoorInfo();
                                                                                    this.setCarStatusInfo();
                                                                                    break;
                                                                                 case 19:
                                                                                    this.setDriveData();
                                                                              }
                                                                           } else {
                                                                              this.set0xD3SyncStatus(var1);
                                                                           }
                                                                        } else {
                                                                           this.set0xD2SyncPlayInfo();
                                                                        }
                                                                     } else {
                                                                        this.setAmplifierData();
                                                                     }
                                                                  } else {
                                                                     if (this.isCdInfoRepeat(var2)) {
                                                                        return;
                                                                     }

                                                                     this.setCdInfo();
                                                                  }
                                                               } else {
                                                                  this.setSportSettingInfo();
                                                               }
                                                            } else {
                                                               if (this.isRadioMsgRepeat(var2)) {
                                                                  return;
                                                               }

                                                               SharePreUtil.setIntValue(this.mContext, "original_radio_cd", 1);
                                                               this.setRadioInfo();
                                                               this.setOriginalRadioPage();
                                                            }
                                                         } else {
                                                            this.setTipsInfo();
                                                         }
                                                      } else {
                                                         this.setLightSetting();
                                                      }
                                                   } else {
                                                      this.setLeftRightRadar();
                                                   }
                                                } else {
                                                   this.setFrontRearRadar();
                                                }
                                             } else {
                                                this.setCarStatus();
                                             }
                                          } else {
                                             this.setFrontAirData();
                                          }
                                       } else {
                                          this.setVersionInfo();
                                       }
                                    } else {
                                       this.setReversingVideo();
                                       this.setOriginalCameraStatusInfo();
                                    }
                                 } else {
                                    this.set0xD0SyncDisplayInfo();
                                 }
                              } else {
                                 if (this.isCdMsgRepeat(var2)) {
                                    return;
                                 }

                                 SharePreUtil.setIntValue(this.mContext, "original_radio_cd", 0);
                                 this.setOriginalCdPage();
                                 this.setCdDataInfo();
                              }
                           } else {
                              this.languageSettingInfo();
                           }
                        } else {
                           if (this.isRadioInfoRepeat(var2)) {
                              return;
                           }

                           this.setRadioPresetInfo();
                           this.setOriginalRadioPage();
                        }
                     } else {
                        if (this.isWarningMsgReturn(var2)) {
                           return;
                        }

                        Intent var5 = new Intent(this.mContext, WarningActivity.class);
                        var5.addFlags(268435456);
                        this.mContext.startActivity(var5);
                        this.setWarningInfo();
                     }
                  } else {
                     if (this.isParkingMsgReturn(var2)) {
                        return;
                     }

                     this.setEnvironmentLight();
                     this.refreshFordParkAssistFlowView(this.mCanBusInfoInt);
                  }
               } else {
                  this.setCarNumber();
               }
            } else {
               this.setCarType();
            }
         } else {
            this.setOriginalPanel();
         }
      } else {
         this.setPanelBtnInfo();
         this.dccOffOnCtrl(var2);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var18 = 0;
      byte var17;
      if (var12) {
         var17 = 0;
      } else {
         var17 = 1;
      }

      byte var14 = (byte)var17;
      byte var16 = (byte)var5;
      byte var15 = (byte)var6;
      var17 = var18;
      if (var10) {
         var17 = 1;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -53, var14, var16, var15, 0, 0, (byte)var17, (byte)var2, (byte)var3, (byte)var4, (byte)var9});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      boolean var14;
      if (var7 == 1) {
         var14 = true;
      } else {
         var14 = false;
      }

      byte var15;
      if (var14) {
         var15 = 7;
      } else {
         var15 = 6;
      }

      var3 = Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, (byte)var15, (byte)var3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.sendId3();
   }

   public void dtvInfoChange() {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, (byte)Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      this.sendId3();
   }

   public Activity getCurrentActivity() {
      return this.getActivity();
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -108});
      this.setSelectCarType();
      this.initId3();
      this.updateOrignalSetting();
      this.initAmplifierData(var1);
      Log.d("lai", "initCommand: " + this.getCurrentEachCanId());
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mId3s[0].setId3("");
      this.mId3s[1].setId3("");
      this.mId3s[2].setId3("");
      this.reportID3Info(this.mId3s, true);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      int var25 = Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
      var3 = 998;
      if (var17 <= 998) {
         var3 = var17;
      }

      var17 = var4;
      var4 = var4;
      if (var17 > 999) {
         var4 = 999;
      }

      var12 = (new DecimalFormat("000")).format((long)var3);
      var11 = (new DecimalFormat("000")).format((long)var4);
      var11 = var12 + "      " + var11;
      var2 = (byte)var25;
      byte[] var26 = var11.getBytes();
      var26 = Util.byteMerger(new byte[]{22, -111, var1, var2}, var26);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var26);
      this.mId3s[0].setId3(var21);
      this.mId3s[1].setId3(var22);
      this.mId3s[2].setId3(var23);
      this.reportID3Info(this.mId3s, false);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      int var8 = DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
      boolean var9 = this.isBandAm(var2);
      var4 = " ";
      String var10;
      byte var12;
      if (var9) {
         if (var3.length() == 4) {
            var2 = "     ";
         } else {
            var2 = "      ";
         }

         var12 = 0;
         var10 = var2;
      } else {
         var2 = var4;
         if (var3.length() == 5) {
            var2 = "  ";
         }

         var10 = "    ";
         var12 = 1;
         var4 = var2;
      }

      var2 = var3.substring(0, var3.length() - var12);
      var2 = (new DecimalFormat("00")).format((long)var1) + var4 + var2 + var10;
      byte var7 = (byte)var8;
      byte[] var11 = var2.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, var6, var7}, var11));
      this.sendId3();
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, (byte)Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -110}, 34));
         CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -109}, 34));
         CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -106}, 34));
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

   void updateOrignalSetting() {
      this.value = SharePreUtil.getIntValue(this.mContext, "_96_park_assess_item", 0);
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(4, 1, this.value));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   void updateSync(Bundle var1) {
      this.updateSyncActivity(var1);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         var1 = 14;
      } else {
         var1 = 13;
      }

      var14 = Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
      var8 = (new DecimalFormat("000")).format((long)var3);
      var11 = (new DecimalFormat("000")).format((long)var4);
      var8 = var8 + "      " + var11;
      var2 = (byte)var14;
      byte[] var18 = var8.getBytes();
      var18 = Util.byteMerger(new byte[]{22, -111, var1, var2}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      this.sendId3();
   }

   class ID3 {
      private String charsetName;
      private int head;
      private String id3;
      private int length;
      private String record;
      final MsgMgr this$0;

      public ID3(MsgMgr var1, int var2, String var3, int var4) {
         this.this$0 = var1;
         this.head = var2;
         this.id3 = new String();
         this.record = new String();
         this.charsetName = var3;
         this.length = var4;
      }

      public String getCharsetName() {
         return this.charsetName;
      }

      public int getHead() {
         return this.head;
      }

      public String getId3() {
         return this.id3;
      }

      public int getLength() {
         return this.length;
      }

      public boolean isId3Change() {
         if (this.record.equals(this.id3)) {
            return false;
         } else {
            this.record = this.id3;
            return true;
         }
      }

      public void setCharsetName(String var1) {
         this.charsetName = var1;
      }

      public void setId3(String var1) {
         this.id3 = var1;
      }

      public void setLength(int var1) {
         this.length = var1;
      }
   }

   private class TimerHelper {
      private Timer mTimer;
      private TimerTask mTimerTask;
      final MsgMgr this$0;

      private TimerHelper(MsgMgr var1) {
         this.this$0 = var1;
      }

      public void startTimer(TimerTask var1, long var2, long var4) {
         Log.i("TimerUtil", "startTimer: " + this);
         if (var1 != null) {
            this.mTimerTask = var1;
            if (this.mTimer == null) {
               this.mTimer = new Timer();
            }

            this.mTimer.schedule(this.mTimerTask, var2, var4);
         }
      }

      public void stopTimer() {
         Log.i("TimerUtil", "stopTimer: " + this);
         TimerTask var1 = this.mTimerTask;
         if (var1 != null) {
            var1.cancel();
            this.mTimerTask = null;
         }

         Timer var2 = this.mTimer;
         if (var2 != null) {
            var2.cancel();
            this.mTimer = null;
         }

      }
   }
}
