package com.hzbhd.canbus.car._97;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_97_RADAR_SWITCH = "share_97_radar_switch";
   static final String SHARE_97_TEMPERATURE_UNIT = "share_97_temperature_unit";
   private String[] FL = new String[2];
   private String[] FR = new String[2];
   private String[] RL = new String[2];
   private String[] RR = new String[2];
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private int mDifferent;
   private int[] mDriveDataNow;
   private HashMap mDriveItemHashMap;
   private int mEachId;
   private ID3[] mId3s;
   private boolean mIsAirFirst = true;
   private boolean mIsDoorFirst = true;
   private boolean mLeftFrontRec;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearRec;
   private boolean mLeftRearStatus;
   private int[] mLightDataNow;
   private int mOutDoorTempDataNow;
   private int[] mRadarDataNow;
   private boolean mRightFrontRec;
   private boolean mRightFrontStatus;
   private boolean mRightRearRec;
   private boolean mRightRearStatus;
   private int[] mTrackDataNow;
   private UiMgr mUiMgr;
   private int mUnitDataNow;
   private int[] mVersionInfoNow;

   private byte[] bytesExpectOneByte(byte[] var1, int var2) {
      int var4 = var1.length - 1;
      byte[] var6 = new byte[var4];
      byte[] var5;
      if (var2 == var4) {
         var5 = Arrays.copyOf(var1, var4);
      } else {
         int var3 = 0;

         while(true) {
            var5 = var6;
            if (var3 >= var4) {
               break;
            }

            if (var3 < var2) {
               var6[var3] = var1[var3];
            } else {
               var6[var3] = var1[var3 + 1];
            }

            ++var3;
         }
      }

      return var5;
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 1;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 4;
            }
      }

      switch (var7) {
         case 0:
            return var5;
         case 1:
            return var6;
         case 2:
            return var2;
         case 3:
            return var3;
         case 4:
            return var4;
         default:
            return 0;
      }
   }

   private int getCar(int var1) {
      if (var1 != 5) {
         if (var1 != 9) {
            if (var1 != 32) {
               switch (var1) {
                  case 20:
                     return 3;
                  case 21:
                     return 4;
                  case 22:
                     return 5;
                  default:
                     switch (var1) {
                        case 28:
                           return 6;
                        case 29:
                           return 7;
                        case 30:
                           return 8;
                        default:
                           return 2;
                     }
               }
            } else {
               return 9;
            }
         } else {
            return 1;
         }
      } else {
         return 0;
      }
   }

   private int getHour(int var1) {
      return var1 / 60 / 60;
   }

   private int getMinute(int var1) {
      return var1 / 60 % 60;
   }

   private int getSecond(int var1) {
      return var1 % 60;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private DriverUpdateEntity helperSetDriveDataValue(String var1, String var2) {
      if (!this.mDriveItemHashMap.containsKey(var1)) {
         this.mDriveItemHashMap.put(var1, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
      }

      return ((DriveDataUpdateHelper)this.mDriveItemHashMap.get(var1)).setValue(var2);
   }

   private void initDriveItem(DriverDataPageUiSet var1) {
      this.mDriveItemHashMap = new HashMap();
      List var6 = var1.getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var5 = ((DriverDataPageUiSet.Page)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var4 = ((DriverDataPageUiSet.Page.Item)var5.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var4, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initId3() {
      this.mId3s = new ID3[]{new ID3(146, "Unicode", 34), new ID3(147, "Unicode", 34), new ID3(148, "Unicode", 34)};
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = this.mLeftFrontRec;
         this.mRightFrontStatus = this.mRightFrontRec;
         this.mLeftRearStatus = this.mLeftRearRec;
         this.mRightRearStatus = this.mRightRearRec;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         return true;
      }
   }

   private boolean isDoorFirst() {
      if (this.mIsDoorFirst) {
         this.mIsDoorFirst = false;
         if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen) {
            return true;
         }
      }

      return false;
   }

   private boolean isDriveDataChange() {
      if (Arrays.equals(this.mDriveDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mDriveDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isFirst() {
      if (this.mIsAirFirst) {
         this.mIsAirFirst = false;
         return !GeneralAirData.power;
      } else {
         return false;
      }
   }

   private boolean isLightDataChange() {
      if (Arrays.equals(this.mLightDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mLightDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isOutDoorTempChange() {
      int var1 = this.mOutDoorTempDataNow;
      int var2 = this.mCanBusInfoInt[13];
      if (var1 == var2) {
         return false;
      } else {
         this.mOutDoorTempDataNow = var2;
         return true;
      }
   }

   private boolean isRadarDataChange() {
      if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mRadarDataNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private boolean isTrackDataChange() {
      int[] var1 = new int[2];
      int[] var2 = this.mCanBusInfoInt;
      var1[0] = var2[8];
      var1[1] = var2[9];
      if (Arrays.equals(this.mTrackDataNow, var1)) {
         return false;
      } else {
         this.mTrackDataNow = Arrays.copyOf(var1, 2);
         return true;
      }
   }

   private boolean isUnitDataChange() {
      int var1 = this.mUnitDataNow;
      int var2 = this.mCanBusInfoInt[3];
      if (var1 == var2) {
         return false;
      } else {
         this.mUnitDataNow = var2;
         return true;
      }
   }

   private boolean isVersionInfoChange() {
      if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
         return false;
      } else {
         int[] var1 = this.mCanBusInfoInt;
         this.mVersionInfoNow = Arrays.copyOf(var1, var1.length);
         return true;
      }
   }

   private void panelKeyClick(int var1, int var2) {
      this.realKeyLongClick1(this.mContext, var1, var2);
   }

   private void realKeyClick3_1(int var1) {
      this.realKeyClick3_1(this.mContext, var1, this.mCanBusInfoInt[2], Math.abs(this.mCanBusInfoByte[3]));
   }

   private void realKeyClick3_2(int var1) {
      this.realKeyClick3_2(this.mContext, var1, this.mCanBusInfoInt[2], Math.abs(this.mCanBusInfoByte[3]));
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

   private String resolveAirTemp(int var1) {
      String var2;
      if (SharePreUtil.getBoolValue(this.mContext, "share_97_temperature_unit", false)) {
         var2 = this.getTempUnitF(this.mContext);
      } else {
         var2 = this.getTempUnitC(this.mContext);
      }

      if (SharePreUtil.getBoolValue(this.mContext, "share_air_conditioner_type", true)) {
         if (var1 == 254) {
            return "LO";
         } else {
            return var1 == 255 ? "HI" : (float)var1 * 0.5F + var2;
         }
      } else if (var1 == 255) {
         return "LO";
      } else {
         return var1 == 254 ? "HI" : (float)(90 - var1) * 0.5F + var2;
      }
   }

   private String resolveOutDoorTem() {
      float var2 = (float)DataHandleUtils.rangeNumber(this.mCanBusInfoInt[13], 0, 254) * 0.5F - 40.0F;
      String var3 = this.getTempUnitC(this.mContext);
      float var1 = var2;
      if (SharePreUtil.getBoolValue(this.mContext, "share_97_temperature_unit", false)) {
         var1 = var2 * 9.0F / 5.0F + 32.0F;
         var3 = this.getTempUnitF(this.mContext);
      }

      return (new DecimalFormat("0.0")).format((double)var1) + var3;
   }

   private void set0x26CarSelect() {
      if (this.mCanBusInfoInt[2] == 3) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_97_car"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_97_car", "_97_car"), this.getCar(this.mCanBusInfoInt[3])));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setAirData0x31() {
      byte[] var3 = this.bytesExpectOneByte(this.mCanBusInfoByte, 13);
      this.setOutDoorTem();
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      if (!this.isAirMsgRepeat(var3)) {
         if (!this.isFirst()) {
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            boolean var2;
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 3) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac_max = var2;
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
               var2 = true;
            } else {
               var2 = false;
            }

            GeneralAirData.ac = var2;
            GeneralAirData.max_heat = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_auto_wind = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_auto_wind = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
            String var4;
            if (var1 == 0) {
               var4 = "";
            } else {
               var4 = "Auto " + var1;
            }

            GeneralAirData.center_wheel = var4;
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            if (!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
               var1 = this.mCanBusInfoInt[6];
               if (var1 != 10) {
                  switch (var1) {
                     case 2:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 3:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                     case 4:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                     case 5:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                     case 6:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        break;
                     case 7:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_window = true;
                  GeneralAirData.front_right_blow_window = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
               }
            }

            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveAirTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveAirTemp(this.mCanBusInfoInt[9]);
            this.updateAirActivity(this.mContext, 1001);
         }
      }
   }

   private void setDoorData0x12() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      this.mLeftFrontRec = var1;
      GeneralDoorData.isLeftFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      this.mRightFrontRec = var1;
      GeneralDoorData.isRightFrontDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      this.mLeftRearRec = var1;
      GeneralDoorData.isLeftRearDoorOpen = var1;
      var1 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      this.mRightRearRec = var1;
      GeneralDoorData.isRightRearDoorOpen = var1;
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      if (this.isDoorDataChange() && !this.isDoorFirst()) {
         this.updateDoorView(this.mContext);
      }

   }

   private void setFuelConsumptionMileageInfo() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      String var5 = var2.append(var3[4] * 256 + var3[5]).append("").toString();
      StringBuilder var4 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      String var6 = var4.append((float)((var3[6] << 16) + (var3[7] << 8) + var3[8]) / 10.0F).append("").toString();
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("S97_recharge_mileage", var5)));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("S97_total_mileage", var6)));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setKeyTrack0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 23) {
                           if (var1 != 40) {
                              if (var1 != 48) {
                                 if (var1 != 49) {
                                    switch (var1) {
                                       case 8:
                                          this.wheelKeyClick(48);
                                          break;
                                       case 9:
                                          this.wheelKeyClick(47);
                                          break;
                                       case 10:
                                          this.wheelKeyClick(14);
                                          break;
                                       case 11:
                                          this.wheelKeyClick(15);
                                          break;
                                       case 12:
                                          this.wheelKeyClick(2);
                                          break;
                                       case 13:
                                          this.wheelKeyClick(45);
                                          break;
                                       case 14:
                                          this.wheelKeyClick(46);
                                          break;
                                       case 15:
                                          this.wheelKeyClick(49);
                                    }
                                 } else {
                                    this.wheelKeyClick(52);
                                 }
                              } else {
                                 this.startMainActivity(this.mContext);
                                 this.playBeep();
                              }
                           } else {
                              this.wheelKeyClick(187);
                           }
                        } else {
                           this.wheelKeyClick(139);
                        }
                     } else {
                        this.wheelKeyClick(466);
                     }
                  } else {
                     this.wheelKeyClick(465);
                  }
               } else {
                  this.wheelKeyClick(3);
               }
            } else {
               this.wheelKeyClick(8);
            }
         } else {
            this.wheelKeyClick(7);
         }
      } else {
         this.wheelKeyClick(0);
      }

      if (this.isTrackDataChange()) {
         byte[] var2 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var2[9], var2[8], 0, 540, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setLightData0x61() {
      if (this.isLightDataChange()) {
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(2, 0, this.mCanBusInfoInt[4]));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setOutDoorTem() {
      if (this.isOutDoorTempChange()) {
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      }

   }

   private void setPanelKey0x21() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 17) {
                     if (var1 != 45) {
                        if (var1 != 63) {
                           if (var1 == 64) {
                              this.panelKeyClick(1, var2[3]);
                           }
                        } else {
                           this.panelKeyClick(151, var2[3]);
                        }
                     } else {
                        this.panelKeyClick(76, var2[3]);
                     }
                  } else {
                     this.panelKeyClick(31, var2[3]);
                  }
               } else {
                  this.panelKeyClick(20, var2[3]);
               }
            } else {
               this.panelKeyClick(21, var2[3]);
            }
         } else {
            this.panelKeyClick(134, var2[3]);
         }
      } else {
         this.panelKeyClick(0, var2[3]);
      }

   }

   private void setPanelKnob0x22() {
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

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         boolean var1 = false;
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.mMinIsClose = true;
         int[] var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(9, var2[2] + 1, var2[3] + 1, var2[4] + 1, var2[5] + 1);
         var2 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(9, var2[6] + 1, var2[7] + 1, var2[8] + 1, var2[9] + 1);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
         Context var3 = this.mContext;
         if (this.mCanBusInfoInt[12] == 1) {
            var1 = true;
         }

         SharePreUtil.setBoolValue(var3, "share_97_radar_switch", var1);
      }

   }

   private void setTireInfo0x48() {
      GeneralTireData.isHaveSpareTire = false;
      String[] var1 = this.FL;
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1[0] = var2.append((float)(var3[4] * 256 + var3[5]) / 10.0F).append(" Kpq").toString();
      String[] var6 = this.FR;
      StringBuilder var4 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var6[0] = var4.append((float)(var3[6] * 256 + var3[7]) / 10.0F).append(" Kpa").toString();
      var6 = this.RL;
      var4 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var6[0] = var4.append((float)(var3[8] * 256 + var3[9]) / 10.0F).append(" Kpa").toString();
      var6 = this.RR;
      StringBuilder var8 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var6[0] = var8.append((float)(var5[10] * 256 + var5[11]) / 10.0F).append(" Kpa").toString();
      ArrayList var7 = new ArrayList();
      var7.add(new TireUpdateEntity(0, 0, this.FL));
      var7.add(new TireUpdateEntity(1, 0, this.FR));
      var7.add(new TireUpdateEntity(2, 0, this.RL));
      var7.add(new TireUpdateEntity(3, 0, this.RR));
      GeneralTireData.dataList = var7;
      this.updateTirePressureActivity((Bundle)null);
   }

   private void setUnitData0x68() {
      if (this.isUnitDataChange()) {
         SharePreUtil.setBoolValue(this.mContext, "share_97_temperature_unit", DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
         ArrayList var1 = new ArrayList();
         var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
         var1.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
         var1.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setVehicleInfo0x32() {
      if (this.isDriveDataChange()) {
         ArrayList var1 = new ArrayList();
         StringBuilder var3 = new StringBuilder();
         int[] var2 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 0, var3.append(var2[4] * 256 + var2[5]).append(" rpm").toString()));
         StringBuilder var5 = new StringBuilder();
         int[] var6 = this.mCanBusInfoInt;
         var1.add(new DriverUpdateEntity(0, 1, var5.append(var6[6] * 256 + var6[7]).append(" km/h").toString()));
         this.updateGeneralDriveData(var1);
         this.updateDriveDataActivity((Bundle)null);
         int[] var4 = this.mCanBusInfoInt;
         this.updateSpeedInfo(var4[6] * 256 + var4[7]);
      }

   }

   private void setVersionInfo0xF0() {
      if (this.isVersionInfoChange()) {
         this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   private void wheelKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-111, (byte)-123));
      CanbusMsgSender.sendMsg(new byte[]{22, -111, -123});
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      if (var3) {
         var7 = var1;
      } else {
         var7 = 6;
      }

      byte var10 = (byte)var7;
      if (var1 == 0) {
         var2 = new byte[]{32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
      }

      var2 = DataHandleUtils.phoneNum2UnicodeBig(var2);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -51, var10, 0, 0}, var2), 29));
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var1 = ("    " + (new DecimalFormat("00")).format((long)this.getMinute(var4)) + (new DecimalFormat("00")).format((long)this.getSecond(var4)) + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 10}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 33) {
               if (var3 != 34) {
                  if (var3 != 38) {
                     if (var3 != 52) {
                        if (var3 != 65) {
                           if (var3 != 72) {
                              if (var3 != 97) {
                                 if (var3 != 104) {
                                    if (var3 != 240) {
                                       if (var3 != 49) {
                                          if (var3 == 50) {
                                             this.setVehicleInfo0x32();
                                          }
                                       } else {
                                          this.setAirData0x31();
                                       }
                                    } else {
                                       this.setVersionInfo0xF0();
                                    }
                                 } else {
                                    this.setUnitData0x68();
                                 }
                              } else {
                                 this.setLightData0x61();
                              }
                           } else {
                              this.setTireInfo0x48();
                           }
                        } else {
                           this.setRadarData0x41();
                        }
                     } else {
                        this.setFuelConsumptionMileageInfo();
                     }
                  } else {
                     this.set0x26CarSelect();
                  }
               } else {
                  this.setPanelKnob0x22();
               }
            } else {
               this.setPanelKey0x21();
            }
         } else {
            this.setDoorData0x12();
         }
      } else {
         this.setKeyTrack0x11();
         var3 = this.mCanBusInfoInt[3];
         if (var3 != 0) {
            this.updateSpeedInfo(var3);
         }
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var14 = (byte)var8;
      byte var20 = 1;
      byte var19 = (byte)var6;
      if (!var10) {
         var20 = 0;
      }

      byte var17 = (byte)var20;
      byte var15 = (byte)var2;
      byte var16 = (byte)var3;
      byte var18 = (byte)var4;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -53}, new byte[]{0, var14, var19, 0, 0, var17, var15, var16, var18, 0}));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 19, 3});
      this.initId3();
      this.initDriveItem(this.getUiMgr(var1).getDriverDataPageUiSet(var1));
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
      byte[] var25 = ((new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 999)) + "      " + (new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var4, 0, 999))).getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -111, 13}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
      this.mId3s[0].setId3(var21);
      this.mId3s[1].setId3(var22);
      this.mId3s[2].setId3(var23);
      this.reportID3Info(this.mId3s, false);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var4 = (new DecimalFormat("00")).format((long)var1);
      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)2, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         var2 = var4 + " ";
         var2 = var2 + var3;
      } else {
         var1 = 0;

         for(var2 = var4; var1 < 7 - var3.length(); ++var1) {
            var2 = var2 + " ";
         }

         var2 = var2 + (new DecimalFormat("0.0")).format(Float.valueOf(var3));
      }

      while(var2.length() < 12) {
         var2 = var2 + " ";
      }

      byte[] var7 = var2.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -111, var6}, var7);
      CanbusMsgSender.sendMsg(var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte)-46, (byte)0));
   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte[] var18 = ((new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var9 * 256 + var3, 0, 999)) + "      " + (new DecimalFormat("000")).format((long)DataHandleUtils.rangeNumber(var4, 0, 999))).getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -111, 13}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
      CanbusMsgSender.sendMsg(var18);
   }

   private static class DriveDataUpdateHelper {
      private DriverUpdateEntity entity;

      public DriveDataUpdateHelper(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity getEntity() {
         return this.entity;
      }

      public void setEntity(DriverUpdateEntity var1) {
         this.entity = var1;
      }

      public DriverUpdateEntity setValue(String var1) {
         this.entity.setValue(var1);
         return this.entity;
      }
   }
}
