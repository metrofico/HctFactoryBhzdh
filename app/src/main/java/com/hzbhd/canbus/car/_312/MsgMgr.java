package com.hzbhd.canbus.car._312;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private static final int MEDIA_ID3_INFO_LANGTH = 56;
   private final int DATA_TYPE = 1;
   private final int INVAILE_VALUE = -1;
   private final String TAG = "_999_MsgMgr";
   private final int VEHICLE_TYPE_2019 = 1;
   private int mAmplifierBalanceMid = 10;
   private int mAmplifierBandMin = 3;
   private int mAmplifierSwitch;
   private int mCanId;
   private SparseArray mCanbusDataArray;
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private Context mContext;
   private int mDifferent;
   private int mGlonassStatus;
   private Id3 mId3;
   private boolean mIs2019;
   private boolean mIsBackOpen;
   private boolean mIsFrontOpen;
   private boolean mIsFrontViewOpen;
   private boolean mIsLeftFrontOpen;
   private boolean mIsLeftRearOpen;
   private boolean mIsMute;
   private boolean mIsRightFrontOpen;
   private boolean mIsRightRearOpen;
   private int mPanoramicStatus;
   private HashMap mSettingItemIndeHashMap;
   private UiMgr mUiMgr;

   private String getAirTemperature(Context var1, int var2) {
      if (var2 == 0) {
         return "LO";
      } else if (var2 == 255) {
         return "HI";
      } else {
         return GeneralAirData.fahrenheit_celsius ? var2 + this.getTempUnitF(var1) : (float)var2 * 0.5F + this.getTempUnitC(var1);
      }
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (CommUtil.equal(var1, "AM2", "MW", "AM1", "LW")) {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      } else if (CommUtil.equal(var1, "FM1", "FM2", "FM3", "OIRT")) {
         int var3 = (int)(Double.parseDouble(var2) * 100.0);
         var4[0] = (byte)(var3 >> 8);
         var4[1] = (byte)(var3 & 255);
      }

      return var4;
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         SettingUpdateEntity var2 = new SettingUpdateEntity(-1, -1, (Object)null);
         this.mSettingItemIndeHashMap.put(var1, var2);
         return var2;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initAmplifierParam(Context var1) {
      this.mAmplifierBalanceMid = this.getUiMgr(var1).getBalanceMid();
      this.mAmplifierBandMin = this.getUiMgr(var1).getBandMin();
   }

   private void initSettingsItem(Context var1) {
      Log.i("_999_MsgMgr", "initSettingsItem: ");
      this.mSettingItemIndeHashMap = new HashMap();
      SettingPageUiSet var6 = this.getUiMgr(var1).getSettingUiSet(var1);

      for(int var2 = 0; var2 < var6.getList().size(); ++var2) {
         List var4 = ((SettingPageUiSet.ListBean)var6.getList().get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)var4.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var5, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean isDoorStatusChange() {
      if (!(this.mIsLeftFrontOpen ^ GeneralDoorData.isLeftFrontDoorOpen) && !(this.mIsRightFrontOpen ^ GeneralDoorData.isRightFrontDoorOpen) && !(this.mIsLeftRearOpen ^ GeneralDoorData.isLeftRearDoorOpen) && !(this.mIsRightRearOpen ^ GeneralDoorData.isRightRearDoorOpen) && !(this.mIsBackOpen ^ GeneralDoorData.isBackOpen) && !(this.mIsFrontOpen ^ GeneralDoorData.isFrontOpen)) {
         return false;
      } else {
         this.mIsLeftFrontOpen = GeneralDoorData.isLeftFrontDoorOpen;
         this.mIsRightFrontOpen = GeneralDoorData.isRightFrontDoorOpen;
         this.mIsLeftRearOpen = GeneralDoorData.isLeftRearDoorOpen;
         this.mIsRightRearOpen = GeneralDoorData.isRightRearDoorOpen;
         this.mIsBackOpen = GeneralDoorData.isBackOpen;
         this.mIsFrontOpen = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void realKeyLongClick1(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanbusInfoInt[3]);
   }

   private void sendAmplifierInit(Context var1) {
      if (var1 != null) {
         this.getAmplifierData(var1, this.getCanId(), UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         this.initAmplifierParam(var1);
      }

      TimerUtil var2 = new TimerUtil();
      var2.startTimer(new TimerTask(this, var2) {
         final Iterator iterator;
         final MsgMgr this$0;
         final TimerUtil val$util;

         {
            this.this$0 = var1;
            this.val$util = var2;
            byte[] var6 = new byte[]{22, -124, 1, 1};
            byte[] var8 = new byte[]{22, -124, 2, (byte)GeneralAmplifierData.volume};
            byte[] var7 = new byte[]{22, -124, 3, (byte)(GeneralAmplifierData.frontRear + var1.mAmplifierBalanceMid)};
            byte var4 = (byte)(GeneralAmplifierData.leftRight + var1.mAmplifierBalanceMid);
            byte[] var5 = new byte[]{22, -124, 5, (byte)(GeneralAmplifierData.bandBass + var1.mAmplifierBandMin)};
            byte[] var9 = new byte[]{22, -124, 6, (byte)(GeneralAmplifierData.bandTreble + var1.mAmplifierBandMin)};
            byte var3 = (byte)(GeneralAmplifierData.bandMiddle + var1.mAmplifierBandMin);
            this.iterator = Arrays.stream(new byte[][]{var6, var8, var7, {22, -124, 4, var4}, var5, var9, {22, -124, 7, var3}}).iterator();
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

   private void set0x20WheelKeyData(Context var1) {
      int[] var4 = this.mCanbusInfoInt;
      short var2 = 2;
      int var3 = var4[2];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 4) {
                  if (var3 != 21) {
                     if (var3 != 22) {
                        switch (var3) {
                           case 7:
                              break;
                           case 8:
                              var2 = 187;
                              break;
                           case 9:
                              if (this.mIs2019) {
                                 var2 = 201;
                              } else {
                                 var2 = 14;
                              }
                              break;
                           case 10:
                              var2 = 15;
                              break;
                           default:
                              var2 = 0;
                        }
                     } else {
                        var2 = 49;
                     }
                  } else {
                     var2 = 50;
                  }
               } else {
                  var2 = 45;
               }
            } else {
               var2 = 46;
            }
         } else {
            var2 = 8;
         }
      } else {
         var2 = 7;
      }

      this.realKeyLongClick1(var1, var2);
   }

   private void set0x21AirData(Context var1) {
      int[] var2 = this.mCanbusInfoInt;
      var2[3] &= 239;
      if (this.isDataChange(var2)) {
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[6]);
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]) ^ true;
         if (DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2])) {
            GeneralAirData.auto_1_2 = 2;
         } else if (DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2])) {
            GeneralAirData.auto_1_2 = 1;
         } else {
            GeneralAirData.auto_1_2 = 0;
         }

         GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
         GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
         GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
         GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
         GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
         GeneralAirData.front_left_temperature = this.getAirTemperature(var1, this.mCanbusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.getAirTemperature(var1, this.mCanbusInfoInt[5]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[6]);
         GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
         GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
         GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
         this.updateAirActivity(var1, 1001);
      }

   }

   private void set0x22RadarData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         int[] var2 = this.mCanbusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var2[2], var2[3], var2[4], var2[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x24BaseInfoData(Context var1) {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
      if (this.isDoorStatusChange()) {
         this.updateDoorView(var1);
      }

   }

   private void set0x25ParkAssistData() {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         boolean var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (this.mIsFrontViewOpen ^ var1) {
            this.mIsFrontViewOpen = var1;
            this.switchFCamera(this.mContext, var1);
         }

         ArrayList var2 = new ArrayList();
         var2.add(this.getSettingUpdateEntity("_41_park_assist").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 1, 1)));
         var2.add(this.getSettingUpdateEntity("_312_rab").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 1)));
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x29TrackData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         byte[] var2 = this.mCanbusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[2], var2[3], 0, 4864, 16);
         this.updateParkUi((Bundle)null, var1);
      }

   }

   private void set0x30VersionData(Context var1) {
      this.updateVersionInfo(var1, this.getVersionStr(this.mCanbusInfoByte));
   }

   private void set0x31AmplifierData(Context var1) {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         GeneralAmplifierData.volume = this.mCanbusInfoInt[3];
         GeneralAmplifierData.frontRear = this.mCanbusInfoInt[4] - this.mAmplifierBalanceMid;
         GeneralAmplifierData.leftRight = this.mCanbusInfoInt[5] - this.mAmplifierBalanceMid;
         GeneralAmplifierData.bandBass = this.mCanbusInfoInt[6] - this.mAmplifierBandMin;
         GeneralAmplifierData.bandTreble = this.mCanbusInfoInt[7] - this.mAmplifierBandMin;
         GeneralAmplifierData.bandMiddle = this.mCanbusInfoInt[8] - this.mAmplifierBandMin;
         this.updateAmplifierActivity((Bundle)null);
         this.saveAmplifierData(var1, this.mCanId);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 1);
         if (this.mAmplifierSwitch != var2) {
            this.mAmplifierSwitch = var2;
            ArrayList var3 = new ArrayList();
            var3.add(this.getSettingUpdateEntity("amplifier_switch").setValue(var2));
            this.updateGeneralSettingData(var3);
            this.updateSettingActivity((Bundle)null);
         }
      }

   }

   private void set0x40VehicleSettingData() {
      if (this.isDataChange(this.mCanbusInfoInt)) {
         ArrayList var1 = new ArrayList();
         var1.add(this.getSettingUpdateEntity("_312_interior_light").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 6, 2)));
         var1.add(this.getSettingUpdateEntity("_312_defogger").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 5, 1)));
         var1.add(this.getSettingUpdateEntity("_312_one_touch_lane_changer").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 1)));
         var1.add(this.getSettingUpdateEntity("_312_hazard_warning_flasher").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 3, 1)));
         var1.add(this.getSettingUpdateEntity("_312_security_re_locking").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 3)));
         var1.add(this.getSettingUpdateEntity("_312_auto_door_lock").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 6, 2)));
         var1.add(this.getSettingUpdateEntity("_312_auto_door_unlock").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 4, 2)));
         var1.add(this.getSettingUpdateEntity("_312_wheel_control_type").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setGlonass0x95Data(Context var1) {
      int var3 = this.mGlonassStatus;
      int var2 = this.mCanbusInfoInt[2];
      if (var3 != var2) {
         this.mGlonassStatus = var2;
         if (var2 == 1 && !this.mIsMute) {
            this.realKeyClick(var1, 3);
         }
      }

   }

   private void setPanoramic0x94Data() {
      int var1 = this.mPanoramicStatus;
      int var2 = this.mCanbusInfoInt[2];
      if (var1 != var2) {
         this.mPanoramicStatus = var2;
         Context var4 = this.mContext;
         boolean var3 = true;
         if (var2 != 1) {
            var3 = false;
         }

         this.forceReverse(var4, var3);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.initSettingsItem(var1);
      this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(var1);
      int var2 = this.getCurrentCanDifferentId();
      this.mDifferent = var2;
      var2 = DataHandleUtils.getIntFromByteWithBit(var2, 4, 4);
      boolean var3 = true;
      if (var2 != 1) {
         var3 = false;
      }

      this.mIs2019 = var3;
      this.mCanbusDataArray = new SparseArray();
      this.initAmplifierParam(var1);
      this.mId3 = new Id3(200, "UnicodeLittle", new Id3.Item(3), new Id3.Item(4), new Id3.Item(5));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte var1 = (byte)7;
      byte var2 = (byte)0;
      byte[] var3 = new byte[6];
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var1, var2}, var3));
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      Log.i("_999_MsgMgr", "btMusicId3InfoChange: \ntitle <--> " + var1 + "\nartist <--> " + var2 + "\nalbum <--> " + var3);
      byte var4 = (byte)11;
      byte var5 = (byte)0;
      byte[] var6 = new byte[6];
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var4, var5}, var6));
      this.mId3.sendId3Datas(var1, var3, var2);
   }

   public void btMusiceDestdroy() {
      super.btMusiceDestdroy();
      Log.i("_999_MsgMgr", "btMusiceDestdroy: ");
      this.mId3.sendId3Datas(" ", " ", " ");
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      var1 = new byte[]{22, -56, 16, 1};

      label13: {
         byte[] var4;
         try {
            var4 = DataHandleUtils.byteMerger(var1, " ".getBytes("unicodeLittle"));
         } catch (UnsupportedEncodingException var5) {
            var5.printStackTrace();
            break label13;
         }

         var1 = var4;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(var1, 58));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[]{22, -56, 16, 1};

      try {
         String var5 = new String(var1);
         var1 = DataHandleUtils.byteMerger(var4, var5.getBytes("unicodeLittle"));
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
         var1 = var4;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(var1, 58));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      byte[] var4 = new byte[]{22, -56, 16, 1};

      try {
         String var5 = new String(var1);
         var1 = DataHandleUtils.byteMerger(var4, var5.getBytes("unicodeLittle"));
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
         var1 = var4;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(var1, 58));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3) {
         byte var12;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  if (var1 != 5) {
                     var12 = 0;
                  } else {
                     var12 = 4;
                  }
               } else {
                  var12 = 2;
               }
            } else {
               var12 = 3;
            }
         } else {
            var12 = 1;
         }

         byte var11 = (byte)5;
         byte var10 = (byte)64;
         var2 = new byte[6];
         var2[0] = (byte)var12;
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var11, var10}, var2));
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoInt = var4;
      this.mCanbusInfoByte = var2;
      int var3 = var4[1];
      if (var3 != 36) {
         if (var3 != 37) {
            if (var3 != 41) {
               if (var3 != 64) {
                  if (var3 != 48) {
                     if (var3 != 49) {
                        if (var3 != 148) {
                           if (var3 != 149) {
                              switch (var3) {
                                 case 32:
                                    this.set0x20WheelKeyData(var1);
                                    break;
                                 case 33:
                                    this.set0x21AirData(var1);
                                    break;
                                 case 34:
                                    this.set0x22RadarData(var1);
                              }
                           } else {
                              this.setGlonass0x95Data(var1);
                           }
                        } else {
                           this.setPanoramic0x94Data();
                        }
                     } else {
                        this.set0x31AmplifierData(var1);
                     }
                  } else {
                     this.set0x30VersionData(var1);
                  }
               } else {
                  this.set0x40VehicleSettingData();
               }
            } else {
               this.set0x29TrackData(var1);
            }
         } else {
            this.set0x25ParkAssistData();
         }
      } else {
         this.set0x24BaseInfoData(var1);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.mIsMute = var2;
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 == 1) {
         var4 = var5;
      }

      byte var14 = (byte)2;
      var1 = (byte)19;
      byte[] var15 = new byte[]{(byte)(var4 >> 8 & 255), (byte)(var4 & 255), 0, 0, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var14, var1}, var15));
      this.mId3.sendId3Datas(var11, var12, var13);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.sendAmplifierInit(var1);
   }

   public void musicDestroy() {
      super.musicDestroy();
      this.mId3.sendId3Datas(" ", " ", " ");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var1 = (byte)8;
      var2 = (byte)19;
      byte[] var25 = new byte[]{(byte)var3, var9, 0, 0, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var1, var2}, var25));
      this.mId3.sendId3Datas(var21, var22, var23);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var9;
      if (var2.contains("AM")) {
         var9 = 16;
      } else {
         var9 = 0;
      }

      int[] var8 = this.getFreqByteHiLo(var2, var3);
      byte var6 = (byte)1;
      byte[] var7 = new byte[]{(byte)var9, (byte)var8[1], (byte)var8[0], (byte)var1, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var6, var6}, var7));
   }

   protected void sendDiscEjectMsg(Context var1) {
      super.sendDiscEjectMsg(var1);
      this.mId3.sendId3Datas(" ", " ", " ");
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         byte var2 = (byte)0;
         byte[] var3 = new byte[6];
         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var2, var2}, var3));
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.sendAmplifierInit(this.mContext);
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var2 = (byte)8;
      var1 = (byte)19;
      byte[] var18 = new byte[]{(byte)var3, var9, 0, 0, 0, 0};
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, var2, var1}, var18));
   }

   private static class Id3 {
      private final Item album;
      private final Item artist;
      private final String charset;
      private final int dataType;
      private final Item[] items;
      private final TimerUtil timer;
      private final Item title;

      public Id3(int var1, String var2, Item var3, Item var4, Item var5) {
         this.dataType = var1;
         this.charset = var2;
         this.title = var3;
         this.album = var4;
         this.artist = var5;
         this.items = new Item[]{var3, var4, var5};
         this.timer = new TimerUtil();
      }

      private void sendId3Data(String var1, int var2) {
         byte var4 = (byte)this.dataType;
         byte var3 = (byte)var2;
         byte[] var5 = new byte[0];

         byte[] var7;
         try {
            var7 = var1.getBytes(this.charset);
         } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
            var7 = var5;
         }

         var7 = DataHandleUtils.exceptBOMHead(var7);
         CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, var4, 16, var3}, var7), 58));
      }

      public void sendId3Datas(String var1, String var2, String var3) {
         this.title.setInfo(var1);
         this.album.setInfo(var2);
         this.artist.setInfo(var3);
         if (this.title.isChange() || this.album.isChange() || this.artist.isChange()) {
            this.timer.startTimer(new TimerTask(this) {
               int i;
               final Id3 this$0;

               {
                  this.this$0 = var1;
                  this.i = 0;
               }

               public void run() {
                  if (this.i < this.this$0.items.length) {
                     Id3 var1 = this.this$0;
                     var1.sendId3Data(var1.items[this.i].getInfo(), this.this$0.items[this.i].getCommand());
                     ++this.i;
                  } else {
                     this.this$0.timer.stopTimer();
                  }

               }
            }, 100L, 100L);
         }

      }

      private static class Item {
         private final int command;
         private String info;
         private boolean isChange;

         public Item(int var1) {
            this.command = var1;
         }

         public int getCommand() {
            return this.command;
         }

         public String getInfo() {
            return this.info;
         }

         public boolean isChange() {
            return this.isChange;
         }

         public void setInfo(String var1) {
            if (var1 != null) {
               boolean var2 = var1.equals(this.info) ^ true;
               this.isChange = var2;
               if (var2) {
                  this.info = var1;
               }

            }
         }
      }
   }
}
