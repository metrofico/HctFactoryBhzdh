package com.hzbhd.canbus.car._194;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private static int mDifferentId;
   static int mFindCarLight;
   static int mFindCarTime;
   static int mHomeWithMeLight;
   static int mHomeWithMeTime;
   private static boolean mIsBackLast;
   private static boolean mIsBelt;
   private static boolean mIsFLDoorLast;
   private static boolean mIsFRDoorLast;
   private static boolean mIsFrontLast;
   private static boolean mIsHandBreak;
   private static boolean mIsRLDoorLast;
   private static boolean mIsRRDoorLast;
   private String[] arr0 = new String[10];
   private String[] arr1 = new String[10];
   private String[] arr2 = new String[10];
   private String[] arr3 = new String[10];
   private int eachID;
   int lastData0 = 0;
   int[] m0x23FrontRadarData;
   int[] m0x54Info;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private byte[] mMediaInfo = new byte[2];
   int[] mTireInfo;
   private UiMgr mUiMgr;
   int nowPm25 = 0;

   public static byte[] byteMerger(byte[] var0, byte[] var1, byte var2, byte var3, byte[] var4) {
      return Util.byteMerger(Util.byteMerger(Util.byteMerger(Util.byteMerger(var0, var1), new byte[]{var2}), new byte[]{var3}), var4);
   }

   private void detectParkPanoramic(Boolean var1) {
      this.forceReverse(this.mContext, var1);
   }

   private int[] getFreqByteHiLo(String var1, String var2) {
      int[] var4 = new int[2];
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            var4[0] = (byte)(var3 >> 8);
            var4[1] = (byte)(var3 & 255);
         }
      } else {
         var4[0] = (byte)(Integer.parseInt(var2) >> 8);
         var4[1] = (byte)(Integer.parseInt(var2) & 255);
      }

      return var4;
   }

   private String getHybridSystemInfo(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131758829);
         case 2:
            return this.mContext.getString(2131758830);
         case 3:
            return this.mContext.getString(2131758831);
         case 4:
            return this.mContext.getString(2131758832);
         case 5:
            return this.mContext.getString(2131758833);
         case 6:
            return this.mContext.getString(2131758834);
         case 7:
            return this.mContext.getString(2131758835);
         default:
            return this.mContext.getString(2131758828);
      }
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private UiMgr getUigMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getWarningMsg(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "";
      } else {
         var2 = CommUtil.getStrByResId(this.mContext, "_194_warning_msg_" + var1);
      }

      return var2;
   }

   private boolean is0x23DataChange() {
      if (Arrays.equals(this.m0x23FrontRadarData, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x23FrontRadarData = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean is0x54InfoChange() {
      if (Arrays.equals(this.m0x54Info, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.m0x54Info = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isTireInfoChange() {
      if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
         return true;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mTireInfo = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private void keyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void otherKnobTurn(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_2(var3, var1, var2[2], var2[3]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (15 == var1) {
         var2 = "HI";
      } else if (16 == var1) {
         var2 = 16 + this.getTempUnitC(this.mContext);
      } else if (1 <= var1 && 14 >= var1) {
         var2 = var1 + 16 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "--";
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2 = "-" + var1;
      } else {
         var2 = var1 + "";
      }

      return var2 + this.getTempUnitC(this.mContext);
   }

   private void sendId3Cmds(String var1, int var2, int var3, String var4) {
      try {
         byte[] var6 = Util.exceptBOMHead(var1.getBytes("Unicode"));
         byte[] var7 = Util.exceptBOMHead(var4.getBytes("Unicode"));
         CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -64, 8, 1, 1}, var6, (byte)0, (byte)0, var7));
      } catch (UnsupportedEncodingException var5) {
         var5.printStackTrace();
      }

   }

   private void sendMusicProgress(long var1, byte var3, byte var4) {
      int var5 = (int)(var1 / 1000L);
      CanbusMsgSender.sendMsg(new byte[]{22, -63, 8, (byte)(var5 % 3600 / 60), (byte)(var5 % 60), var3, var4});
   }

   private void set0x11RearAirInfo() {
      int var1 = mDifferentId;
      if (var1 == 13 || var1 == 17) {
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
         var1 = this.mCanBusInfoInt[3];
         if (var1 == 255) {
            GeneralAirData.rear_temperature = "HI";
         } else if (var1 == 0) {
            GeneralAirData.rear_temperature = "LO";
         } else {
            GeneralAirData.rear_temperature = this.mCanBusInfoInt[3] + 15 + this.getTempUnitC(this.mContext);
         }

         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 15) {
                     GeneralAirData.rear_left_blow_head = false;
                     GeneralAirData.rear_right_blow_head = false;
                     GeneralAirData.rear_left_blow_foot = false;
                     GeneralAirData.rear_right_blow_foot = false;
                     GeneralAirData.rear_left_blow_window = false;
                     GeneralAirData.rear_right_blow_window = false;
                     GeneralAirData.rear_left_auto_wind = true;
                     GeneralAirData.rear_right_auto_wind = true;
                  }
               } else {
                  GeneralAirData.rear_left_blow_head = false;
                  GeneralAirData.rear_right_blow_head = false;
                  GeneralAirData.rear_left_blow_foot = true;
                  GeneralAirData.rear_right_blow_foot = true;
                  GeneralAirData.rear_left_blow_window = false;
                  GeneralAirData.rear_right_blow_window = false;
                  GeneralAirData.rear_left_auto_wind = false;
                  GeneralAirData.rear_right_auto_wind = false;
               }
            } else {
               GeneralAirData.rear_left_blow_head = true;
               GeneralAirData.rear_right_blow_head = true;
               GeneralAirData.rear_left_blow_foot = true;
               GeneralAirData.rear_right_blow_foot = true;
               GeneralAirData.rear_left_blow_window = false;
               GeneralAirData.rear_right_blow_window = false;
               GeneralAirData.rear_left_auto_wind = false;
               GeneralAirData.rear_right_auto_wind = false;
            }
         } else {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_left_auto_wind = false;
            GeneralAirData.rear_right_auto_wind = false;
         }

         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
         GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
         this.updateAirActivity(this.mContext, 1002);
      }
   }

   private void set0x20SwcKey() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 50) {
                        switch (var1) {
                           case 6:
                              this.keyClick(14);
                              break;
                           case 7:
                              this.keyClick(2);
                              break;
                           case 8:
                              this.keyClick(187);
                              break;
                           case 9:
                              this.keyClick(14);
                              break;
                           default:
                              switch (var1) {
                                 case 16:
                                    this.keyClick(187);
                                    break;
                                 case 17:
                                    this.keyClick(52);
                                    break;
                                 case 18:
                                    this.keyClick(50);
                                    break;
                                 case 19:
                                    this.keyClick(52);
                                    break;
                                 case 20:
                                    this.updateAirActivity(this.mContext, 1001);
                                    break;
                                 case 21:
                                    this.keyClick(185);
                                    break;
                                 case 22:
                                    this.keyClick(3);
                                    break;
                                 case 23:
                                    if (SharePreUtil.getBoolValue(this.mContext, "share_is_panoramic", false)) {
                                       this.detectParkPanoramic(false);
                                    } else {
                                       this.detectParkPanoramic(true);
                                    }
                                    break;
                                 default:
                                    switch (var1) {
                                       case 31:
                                          this.keyClick(58);
                                          break;
                                       case 32:
                                          this.otherKnobTurn(47);
                                          break;
                                       case 33:
                                          this.otherKnobTurn(48);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 128:
                                                this.keyClick(134);
                                                break;
                                             case 129:
                                                this.volKnobTurn(7);
                                                break;
                                             case 130:
                                                this.volKnobTurn(8);
                                          }
                                    }
                              }
                        }
                     } else {
                        this.keyClick(128);
                     }
                  } else {
                     this.keyClick(48);
                  }
               } else {
                  this.keyClick(47);
               }
            } else {
               this.keyClick(8);
            }
         } else {
            this.keyClick(7);
         }
      } else {
         this.keyClick(0);
      }

   }

   private void set0x23FrontRedarInfo() {
      if (this.is0x23DataChange()) {
         RadarInfoUtil.mMinIsClose = true;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[6] + this.mContext.getResources().getString(2131770204);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void set0x29Track() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2] * 256 + var2[3];
      if (var1 > 24870 && var1 <= 32768) {
         GeneralParkData.trackAngle = ('耀' - var1) * 40 / 7898;
      } else {
         if (var1 <= 32768 || var1 >= 40531) {
            if (var1 == 0) {
               GeneralParkData.trackAngle = 0;
               this.updateParkUi((Bundle)null, this.mContext);
            }

            return;
         }

         GeneralParkData.trackAngle = -(var1 - '耀') * 40 / 7763;
      }

      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void set0x39CarInfo() {
   }

   private void set0x40CarInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_driving_luosuo"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_unlock"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_unlock_mode"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_smart_unlock_the_car_near"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with__reversing_lights"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with__beam_lights"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with__rear_fog"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with_a_duration"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indication__reversing_lights"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indication__near_light"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indication__rear_fog"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indicate_the_duration"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x41CarInfo() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_i_went_home_with"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_searching_cars_indication"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_steering_wheel_feels"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_unlock_mode"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_smart_unlock_the_car_near"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_a_foldable_outside_mirror_automatically"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_airconditioning_settings"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_airconditioning_settings", "_194_rear_window_demisting_demisting_linkage"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_airconditioning_settings"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_airconditioning_settings", "_194_automatic_mode_wind"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_airconditioning_settings"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_airconditioning_settings", "_194_partition_temperature_settings"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset"), this.mContext.getString(2131758852))).setValueStr(true));
      } else {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset"), this.mContext.getString(2131758853))).setValueStr(true));
      }

      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_vehicle_stability_control"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_ecodriving"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_add_action1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_reset"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_reset", "_194_reset"), this.mContext.getString(2131758852))).setValueStr(true));
      } else {
         var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_reset"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_reset", "_194_reset"), this.mContext.getString(2131758853))).setValueStr(true));
      }

      if (this.mCanBusInfoInt.length == 6) {
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      } else {
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_instrument_brightness"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_instrument_brightness", "_194_meter_brightness_level"), this.mCanBusInfoInt[7] - 1));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_pedestrian_warning_beep"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_speed_auxiliary"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_assist_mode"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_lane_departure_warning"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_alarm_sensitivity"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_alarm_sound"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_auxiliary_systems"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_assist__assist_mode"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_aid__alert_sensitivity"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 7, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 5, 2)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 3, 2)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function11"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 1)));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_window"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_window", "_194_skylights_state"), this.mCanBusInfoInt[11]));
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), this.mCanBusInfoInt[13] - 1));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void set0x42CarInfo() {
   }

   private void set0x43Pm25() {
      this.nowPm25 = this.mCanBusInfoInt[2];
   }

   private void set0x52TireInfo() {
      if (!this.isTireInfoChange()) {
         GeneralTireData.isHaveSpareTire = false;
         ArrayList var2 = new ArrayList();
         int var1 = this.mCanBusInfoInt[12];
         if (var1 == 0) {
            this.arr0[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[4] * 10 + "Kpa";
            this.arr1[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[5] * 10 + "Kpa";
            this.arr3[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[6] * 10 + "Kpa";
            this.arr2[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[7] * 10 + "Kpa";
         } else if (var1 == 1) {
            this.arr0[0] = this.mContext.getString(2131759023) + (double)this.mCanBusInfoInt[4] * 0.1 + "Bar";
            this.arr1[0] = this.mContext.getString(2131759023) + (double)this.mCanBusInfoInt[5] * 0.1 + "Bar";
            this.arr3[0] = this.mContext.getString(2131759023) + (double)this.mCanBusInfoInt[6] * 0.1 + "Bar";
            this.arr2[0] = this.mContext.getString(2131759023) + (double)this.mCanBusInfoInt[7] * 0.1 + "Bar";
         } else if (var1 == 2) {
            this.arr0[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[4] + "Psi";
            this.arr1[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[5] + "Psi";
            this.arr3[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[6] + "Psi";
            this.arr2[0] = this.mContext.getString(2131759023) + this.mCanBusInfoInt[7] + "Psi";
         }

         this.arr0[1] = this.mContext.getString(2131759024) + (this.mCanBusInfoInt[8] - 60) + this.getTempUnitC(this.mContext);
         this.arr1[1] = this.mContext.getString(2131759024) + (this.mCanBusInfoInt[9] - 60) + this.getTempUnitC(this.mContext);
         this.arr3[1] = this.mContext.getString(2131759024) + (this.mCanBusInfoInt[10] - 60) + this.getTempUnitC(this.mContext);
         this.arr2[1] = this.mContext.getString(2131759024) + (this.mCanBusInfoInt[11] - 60) + this.getTempUnitC(this.mContext);
         if (this.mCanBusInfoInt[4] == 255) {
            this.arr0[0] = this.mContext.getString(2131759023) + "Null Info";
         }

         if (this.mCanBusInfoInt[5] == 255) {
            this.arr1[0] = this.mContext.getString(2131759023) + "Null Info";
         }

         if (this.mCanBusInfoInt[6] == 255) {
            this.arr3[0] = this.mContext.getString(2131759023) + "Null Info";
         }

         if (this.mCanBusInfoInt[7] == 255) {
            this.arr2[0] = this.mContext.getString(2131759023) + "Null Info";
         }

         if (this.mCanBusInfoInt[8] == 255) {
            this.arr0[1] = this.mContext.getString(2131759024) + "Null Info";
         }

         if (this.mCanBusInfoInt[9] == 255) {
            this.arr1[1] = this.mContext.getString(2131759024) + "Null Info";
         }

         if (this.mCanBusInfoInt[10] == 255) {
            this.arr3[1] = this.mContext.getString(2131759024) + "Null Info";
         }

         if (this.mCanBusInfoInt[11] == 255) {
            this.arr2[1] = this.mContext.getString(2131759024) + "Null Info";
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 1) {
            var2.add(new TireUpdateEntity(0, 1, this.arr0));
         } else {
            var2.add(new TireUpdateEntity(0, 0, this.arr0));
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 1) {
            var2.add(new TireUpdateEntity(1, 1, this.arr1));
         } else {
            var2.add(new TireUpdateEntity(1, 0, this.arr1));
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) == 1) {
            var2.add(new TireUpdateEntity(2, 1, this.arr3));
         } else {
            var2.add(new TireUpdateEntity(2, 0, this.arr3));
         }

         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 1) {
            var2.add(new TireUpdateEntity(3, 1, this.arr2));
         } else {
            var2.add(new TireUpdateEntity(3, 0, this.arr2));
         }

         GeneralTireData.dataList = var2;
         this.updateTirePressureActivity((Bundle)null);
      }
   }

   private void set0x53DriveInfo() {
      ArrayList var1 = new ArrayList();
      if (this.mCanBusInfoInt[2] == 4) {
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model1"), this.lastData0));
      } else {
         var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model1"), this.mCanBusInfoInt[2]));
         this.lastData0 = this.mCanBusInfoInt[2];
      }

      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model5"), this.mCanBusInfoInt[4] - 40 + this.getTempUnitC(this.mContext))).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model6"), this.mCanBusInfoInt[5] / 10 + "Bar")).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model7"), (double)this.mCanBusInfoInt[6] * 0.04 + "Kpa")).setValueStr(true));
      var1.add((new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model8"), this.mCanBusInfoInt[7] - 40 + this.getTempUnitC(this.mContext))).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x54EnergyLevel() {
      if (!this.is0x54InfoChange()) {
         this.runOnUi(new CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               MsgMgr var1 = this.this$0;
               var1.showEnergyDialog(var1.getActivity(), this.this$0.mCanBusInfoInt[2] + 1);
            }
         });
      }
   }

   private void set0x60EnergyInfo() {
      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo"), this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo1"), this.getHybridSystemInfo(this.mCanBusInfoInt[2])));
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo2");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[3], var4[4])).append("km").toString()));
      var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
      var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo3");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append(this.getMsbLsbResult(var4[5], var4[6])).append("km").toString()));
      var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
      var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo4");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var5.append(this.getMsbLsbResult(var4[7], var4[8])).append("km").toString()));
      var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
      var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo5");
      StringBuilder var6 = new StringBuilder();
      int[] var7 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var2, var1, var6.append((double)this.getMsbLsbResult(var7[9], var7[10]) * 0.1).append("kWh/100km").toString()));
      var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
      var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo6");
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var5.append((double)this.getMsbLsbResult(var4[11], var4[12]) * 0.1).append("L/100km").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x61DianJiInfo() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo7");
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var4.append(this.getMsbLsbResult(var5[2], var5[3])).append("rpm").toString()));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x62ElectricInfo() {
      ArrayList var3 = new ArrayList();
      int var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_electric_info");
      int var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_electric_info1");
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var3.add(new DriverUpdateEntity(var1, var2, var4.append(this.getMsbLsbResult(var5[2], var5[3])).append("V").toString()));
      int[] var6 = this.mCanBusInfoInt;
      if (this.getMsbLsbResult(var6[4], var6[5]) > 60000) {
         var1 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_electric_info");
         var2 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_electric_info2");
         StringBuilder var7 = new StringBuilder();
         var6 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(var1, var2, var7.append(this.getMsbLsbResult(var6[4], var6[5]) - 65536).append("A").toString()));
      } else {
         var2 = this.getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_electric_info");
         var1 = this.getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_electric_info2");
         var4 = new StringBuilder();
         var5 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(var2, var1, var4.append(this.getMsbLsbResult(var5[4], var5[5])).append("A").toString()));
      }

      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setAirData0x23() {
      if (!this.isAirMsgRepeat(this.mCanBusInfoByte)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2);
         GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         boolean var2 = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_auto_wind_model = false;
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 15) {
                           GeneralAirData.front_auto_wind_model = true;
                        }
                     } else {
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                     }
                  } else {
                     GeneralAirData.front_left_blow_window = true;
                     GeneralAirData.front_right_blow_window = true;
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
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
            }
         } else {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
         }

         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         if (GeneralAirData.front_wind_level == 15) {
            var2 = true;
         }

         GeneralAirData.front_auto_wind_speed = var2;
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
         GeneralAirData.econ = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 2);

         try {
            GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
         } catch (Exception var4) {
            Log.e("[CAR_194]", "<AIR> erro=" + var4);
         }

         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setAirData0x27() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarIdCmd() {
      switch (this.getCurrentCanDifferentId()) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 1});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 3});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 6});
         case 5:
         default:
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 1});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 0});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 3});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 2});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 4});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 4});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 7});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 5});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 6});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 8});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 9});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 6});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 7});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 0});
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 3});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 2});
      }

      if (this.eachID == 20) {
         CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 10});
      }

   }

   private void setDoorData0x24() {
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen || mIsHandBreak != GeneralDoorData.isHandBrakeUp || mIsBelt != GeneralDoorData.isSeatBeltTie) {
         this.updateDoorView(this.mContext);
      }

      mIsFLDoorLast = GeneralDoorData.isLeftFrontDoorOpen;
      mIsFRDoorLast = GeneralDoorData.isRightFrontDoorOpen;
      mIsRLDoorLast = GeneralDoorData.isLeftRearDoorOpen;
      mIsRRDoorLast = GeneralDoorData.isRightRearDoorOpen;
      mIsFrontLast = GeneralDoorData.isFrontOpen;
      mIsBackLast = GeneralDoorData.isBackOpen;
      mIsHandBreak = GeneralDoorData.isHandBrakeUp;
      mIsBelt = GeneralDoorData.isSeatBeltTie;
   }

   private void setPanoramic_0x50() {
      if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
         this.detectParkPanoramic(true);
      } else {
         this.detectParkPanoramic(false);
      }

      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "panorama_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "panorama_setting", "_194_360panoramic_10"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "panorama_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "panorama_setting", "_194_360panoramic_11"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)));
      var1.add(new SettingUpdateEntity(this.getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "panorama_setting"), this.getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "panorama_setting", "_194_360panoramic_12"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
      var1 = new ArrayList();
      switch (this.mCanBusInfoInt[3]) {
         case 0:
            var1.add(new PanoramicBtnUpdateEntity(0, true));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 1:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, true));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 2:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, true));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 3:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, true));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 4:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, true));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 5:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, true));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 6:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, true));
            var1.add(new PanoramicBtnUpdateEntity(7, false));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
            break;
         case 7:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            var1.add(new PanoramicBtnUpdateEntity(5, false));
            var1.add(new PanoramicBtnUpdateEntity(6, false));
            var1.add(new PanoramicBtnUpdateEntity(7, true));
            var1.add(new PanoramicBtnUpdateEntity(8, false));
      }

      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadar0x22() {
      GeneralParkData.isShowLeftTopOneDistanceUi = true;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[6] + this.mContext.getResources().getString(2131770204);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWarnInfo0x44() {
      ArrayList var4 = new ArrayList();

      for(int var1 = 0; var1 < 14; ++var1) {
         int[] var3 = this.mCanBusInfoInt;
         int var2 = var1 + 2;
         if (var3[var2] != 0) {
            var4.add(new WarningEntity(this.getWarningMsg(this.mCanBusInfoInt[var2])));
         }
      }

      GeneralWarningDataData.dataList = var4;
      this.updateWarningActivity((Bundle)null);
   }

   private void showEnergyDialog(Context var1, int var2) {
      View var3 = LayoutInflater.from(var1).inflate(2131558404, (ViewGroup)null, true);
      AlertDialog var4 = (new AlertDialog.Builder(var1)).setView(var3).create();
      ((TextView)var3.findViewById(2131362751)).setText(var2 + "LEVEL");
      var4.getWindow().setType(2003);
      var4.getWindow().setBackgroundDrawableResource(17170445);
      var4.show();
      (new CountDownTimer(this, 5000L, 1000L, var4) {
         final MsgMgr this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var6;
         }

         public void onFinish() {
            this.val$dialog.dismiss();
         }

         public void onTick(long var1) {
         }
      }).start();
   }

   private void showPm25Dialog(Context var1, int var2) {
      View var3 = LayoutInflater.from(var1).inflate(2131558405, (ViewGroup)null, true);
      AlertDialog var6 = (new AlertDialog.Builder(var1)).setView(var3).create();
      Button var8 = (Button)var3.findViewById(2131361931);
      Button var5 = (Button)var3.findViewById(2131361932);
      Button var7 = (Button)var3.findViewById(2131361933);
      Button var4 = (Button)var3.findViewById(2131361934);
      Button var9 = (Button)var3.findViewById(2131361935);
      if (var2 < 37) {
         var8.setVisibility(0);
         var5.setVisibility(4);
         var7.setVisibility(4);
         var4.setVisibility(4);
         var9.setVisibility(4);
      } else if (var2 > 37 && var2 <= 75) {
         var8.setVisibility(0);
         var5.setVisibility(0);
         var7.setVisibility(4);
         var4.setVisibility(4);
         var9.setVisibility(4);
      } else if (var2 > 75 && var2 <= 125) {
         var8.setVisibility(0);
         var5.setVisibility(0);
         var7.setVisibility(0);
         var4.setVisibility(4);
         var9.setVisibility(4);
      } else if (var2 > 125 && var2 <= 250) {
         var8.setVisibility(0);
         var5.setVisibility(0);
         var7.setVisibility(0);
         var4.setVisibility(0);
         var9.setVisibility(4);
      } else if (var2 > 250 && var2 < 255) {
         var8.setVisibility(0);
         var5.setVisibility(0);
         var7.setVisibility(0);
         var4.setVisibility(0);
         var9.setVisibility(0);
      }

      var6.getWindow().setBackgroundDrawableResource(17170445);
      var6.show();
   }

   private void volKnobTurn(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick3_1(var3, var1, var2[2], var2[3]);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, -1});
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -80, 4});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -80, 1});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -80, 2});
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -80, 3});
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      String var6 = (new DecimalFormat("00")).format((long)(var4 / 60 % 60));
      String var5 = (new DecimalFormat("00")).format((long)(var4 % 60));
      CanbusMsgSender.sendMsg(new byte[]{22, -79, Byte.parseByte(var6), Byte.parseByte(var5)});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 39) {
            if (var3 != 41) {
               if (var3 != 57) {
                  if (var3 != 80) {
                     if (var3 != 127) {
                        switch (var3) {
                           case 32:
                              this.set0x20SwcKey();
                              break;
                           case 33:
                              this.setAirData0x23();
                              break;
                           case 34:
                              this.setRearRadar0x22();
                              break;
                           case 35:
                              this.set0x23FrontRedarInfo();
                              break;
                           case 36:
                              this.setDoorData0x24();
                              break;
                           default:
                              switch (var3) {
                                 case 64:
                                    this.set0x40CarInfo();
                                    break;
                                 case 65:
                                    this.set0x41CarInfo();
                                    break;
                                 case 66:
                                    this.set0x42CarInfo();
                                    break;
                                 case 67:
                                    this.set0x43Pm25();
                                    break;
                                 case 68:
                                    this.setWarnInfo0x44();
                                    break;
                                 default:
                                    switch (var3) {
                                       case 82:
                                          this.set0x52TireInfo();
                                          break;
                                       case 83:
                                          this.set0x53DriveInfo();
                                          break;
                                       case 84:
                                          this.set0x54EnergyLevel();
                                          break;
                                       default:
                                          switch (var3) {
                                             case 96:
                                                this.set0x60EnergyInfo();
                                                break;
                                             case 97:
                                                this.set0x61DianJiInfo();
                                                break;
                                             case 98:
                                                this.set0x62ElectricInfo();
                                          }
                                    }
                              }
                        }
                     } else {
                        this.setVersionInfo();
                     }
                  } else {
                     this.setPanoramic_0x50();
                  }
               } else {
                  this.set0x39CarInfo();
               }
            } else {
               this.set0x29Track();
            }
         } else {
            this.setAirData0x27();
         }
      } else {
         this.set0x11RearAirInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      short var19;
      if (var10) {
         var19 = 0;
      } else {
         var19 = 128;
      }

      if (var10) {
         var5 = var8;
      }

      byte var18 = (byte)(var5 | var19);
      byte var15 = (byte)var2;
      byte var14 = (byte)var3;
      byte var16 = (byte)var4;
      byte var17 = (byte)var6;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -90}, new byte[]{var15, var14, var16, var18, var17}));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      mDifferentId = this.getCurrentCanDifferentId();
      this.eachID = this.getCurrentEachCanId();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.setCarIdCmd();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, -1, 1});
      this.sendId3Cmds(var21, 0, 0, var23);
      this.sendMusicProgress(var19, var6, var7);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2.hashCode();
      int var6 = var2.hashCode();
      byte var9 = -1;
      switch (var6) {
         case 64901:
            if (var2.equals("AM1")) {
               var9 = 0;
            }
            break;
         case 64902:
            if (var2.equals("AM2")) {
               var9 = 1;
            }
            break;
         case 69706:
            if (var2.equals("FM1")) {
               var9 = 2;
            }
            break;
         case 69707:
            if (var2.equals("FM2")) {
               var9 = 3;
            }
            break;
         case 69708:
            if (var2.equals("FM3")) {
               var9 = 4;
            }
      }

      switch (var9) {
         case 0:
            var9 = 16;
            break;
         case 1:
            var9 = 18;
            break;
         case 2:
            var9 = 1;
            break;
         case 3:
            var9 = 2;
            break;
         case 4:
            var9 = 3;
            break;
         default:
            var9 = 0;
      }

      int[] var7 = this.getFreqByteHiLo(var2, var3);
      byte[] var8 = this.mMediaInfo;
      var8[0] = (byte)var7[0];
      var8[1] = (byte)var7[1];
      var8[2] = (byte)var1;
      CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -64, 1, 1, (byte)var9}, var8));
   }

   public void showPmInfo() {
      this.showPm25Dialog(this.getActivity(), this.nowPm25);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 0});
   }
}
