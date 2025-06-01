package com.hzbhd.canbus.car._387;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   public boolean Down;
   public boolean Left;
   public boolean Right;
   public boolean Up;
   int differentId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private HashMap mDriveItemHashMap;
   private int[] mRadarData;
   private HashMap mSettingItemHashMap;
   private UiMgr mUiMgr;

   private String ResolveOutDoorTemp(int var1) {
      String var2 = (double)var1 * 0.5 - 40.0 + this.getTempUnitC(this.mContext);
      if (var1 == 255) {
         var2 = "---";
      }

      return var2;
   }

   private String ResolveTemp(int var1) {
      String var2 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
      if (var1 == 254) {
         var2 = "LOW_TEMP";
      }

      if (var1 == 255) {
         var2 = "HIGH_TEMP";
      }

      return var2;
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
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

   private SettingUpdateEntity helperSetValue(String var1, Object var2) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2);
   }

   private SettingUpdateEntity helperSetValue(String var1, Object var2, boolean var3) {
      if (!this.mSettingItemHashMap.containsKey(var1)) {
         this.mSettingItemHashMap.put(var1, new SettingUpdateHelper((new SettingUpdateEntity(-1, -1, "null_value")).setEnable(false)));
      }

      return ((SettingUpdateHelper)this.mSettingItemHashMap.get(var1)).setValue(var2).setEnable(var3);
   }

   private void initDriveItem(DriverDataPageUiSet var1) {
      this.mDriveItemHashMap = new HashMap();
      List var4 = var1.getList();

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         List var6 = ((DriverDataPageUiSet.Page)var4.get(var2)).getItemList();

         for(int var3 = 0; var3 < var6.size(); ++var3) {
            String var5 = ((DriverDataPageUiSet.Page.Item)var6.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var5, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var4 = var1.getList();

      for(int var2 = 0; var2 < var4.size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var4.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var5.get(var3);
            String var6 = var7.getTitleSrn();
            this.mSettingItemHashMap.put(var6, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var7.getMin()));
         }
      }

   }

   private boolean isRadarDataChange() {
      Arrays.equals(this.mRadarData, this.mCanBusInfoInt);
      int[] var1 = this.mCanBusInfoInt;
      this.mRadarData = Arrays.copyOf(var1, var1.length);
      return true;
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick0x21(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void setAirInfo0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_right_blow_window = false;
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 3) {
         if (var1 != 12) {
            if (var1 != 5) {
               if (var1 == 6) {
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
         }
      } else {
         GeneralAirData.front_left_blow_foot = true;
         GeneralAirData.front_right_blow_foot = true;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      this.updateOutDoorTemp(this.mContext, this.ResolveOutDoorTemp(this.mCanBusInfoInt[13]));
      this.updateAirActivity(this.mContext, 1004);
   }

   private void setDoorInfo0x12() {
      GeneralDoorData.isSubShowSeatBelt = true;
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setDriveInfo0x13() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("total_mileage", var2.append((float)((var3[9] << 16) + (var3[10] << 8) + var3[11]) / 10.0F).append("KM").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDriveInfo0x32() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("engine_speed", var3.append((var2[4] << 8) + var2[5]).append("").toString())));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("a_current_speed", var4.append((float)((var5[6] << 8) + var5[7]) / 10.0F).append("Km/h").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setOriginalCarVideoStatus0xE8() {
      ArrayList var1 = new ArrayList();
      this.forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
      switch (this.mCanBusInfoInt[3]) {
         case 5:
            var1.add(new PanoramicBtnUpdateEntity(0, true));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            break;
         case 6:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, true));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            break;
         case 7:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, true));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            break;
         case 8:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, true));
            var1.add(new PanoramicBtnUpdateEntity(4, false));
            break;
         case 9:
            var1.add(new PanoramicBtnUpdateEntity(0, false));
            var1.add(new PanoramicBtnUpdateEntity(1, false));
            var1.add(new PanoramicBtnUpdateEntity(2, false));
            var1.add(new PanoramicBtnUpdateEntity(3, false));
            var1.add(new PanoramicBtnUpdateEntity(4, true));
      }

      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setPanelKey0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 37) {
            if (var1 != 43) {
               if (var1 != 45) {
                  if (var1 != 55) {
                     if (var1 != 69) {
                        if (var1 == 70) {
                           this.realKeyClick0x21(8);
                           return;
                        }
                     } else {
                        this.realKeyClick0x21(7);
                     }

                     return;
                  } else {
                     this.realKeyClick0x21(58);
                     return;
                  }
               } else {
                  this.realKeyClick0x21(59);
                  return;
               }
            } else {
               this.realKeyClick0x21(52);
               return;
            }
         }
      } else {
         this.realKeyClick0x21(0);
      }

      this.realKeyClick0x21(128);
   }

   private void setRadar0x41() {
      GeneralParkData.strOnlyOneDistance = CommUtil.getStrByResId(this.mContext, "_387_item_12") + ":" + this.mCanBusInfoInt[11] + "CM";
      GeneralParkData.isShowLeftTopOneDistanceUi = true;
      RadarInfoUtil.mMinIsClose = true;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSetting0x78() {
      ArrayList var12 = new ArrayList();
      boolean var11 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      boolean var1 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      boolean var2 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      boolean var3 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      boolean var9 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      boolean var4 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      boolean var8 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      boolean var5 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      boolean var10 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
      boolean var7 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
      boolean var6 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
      var12.add(this.checkEntity(this.helperSetValue("_387_item_01", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2), var2)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_02", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1), var3)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_03", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1), var9)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_04", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2), var4)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_05", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 1), var8)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_06", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 1), var5)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_07", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1), var10)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_08", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 1), var7)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_09", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2), var6)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_10", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 1), var11)));
      var12.add(this.checkEntity(this.helperSetValue("_387_item_11", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1), var1)));
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var12);
   }

   private void setTrackDate0x11() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 != 11) {
                                 if (var1 == 24) {
                                    this.realKeyClick0x11(33);
                                 }
                              } else {
                                 this.realKeyClick0x11(2);
                              }
                           } else {
                              this.realKeyClick0x11(46);
                           }
                        } else {
                           this.realKeyClick0x11(45);
                        }
                     } else {
                        this.realKeyClick0x11(14);
                     }
                  } else {
                     this.realKeyClick0x11(187);
                  }
               } else {
                  this.realKeyClick0x11(3);
               }
            } else {
               this.realKeyClick0x11(8);
            }
         } else {
            this.realKeyClick0x11(7);
         }
      } else {
         this.realKeyClick0x11(0);
      }

   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 33) {
         if (var3 != 65) {
            if (var3 != 120) {
               if (var3 != 232) {
                  if (var3 != 240) {
                     if (var3 != 49) {
                        if (var3 != 50) {
                           switch (var3) {
                              case 17:
                                 this.setWheelKey0x11();
                                 this.setTrackDate0x11();
                                 break;
                              case 18:
                                 this.setDoorInfo0x12();
                                 break;
                              case 19:
                                 this.setDriveInfo0x13();
                           }
                        } else {
                           this.setDriveInfo0x32();
                        }
                     } else {
                        this.setAirInfo0x31();
                     }
                  } else {
                     this.setVersionInfo0xF0();
                  }
               } else {
                  this.setOriginalCarVideoStatus0xE8();
               }
            } else {
               this.setSetting0x78();
            }
         } else {
            this.setRadar0x41();
         }
      } else {
         this.setPanelKey0x21();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      Log.d("lai", "dateTimeRepCanbus: " + var10);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, (byte)var10}, 12));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initDriveItem(this.getUiMgr(var1).getDriverDataPageUiSet(var1));
      this.initSettingsItem(this.getUiMgr(var1).getSettingUiSet(var1));
      this.differentId = this.getCurrentCanDifferentId();
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

   private static class SettingUpdateHelper {
      private SettingUpdateEntity entity;
      private int progressMin;

      public SettingUpdateHelper(SettingUpdateEntity var1) {
         this(var1, 0);
      }

      public SettingUpdateHelper(SettingUpdateEntity var1, int var2) {
         this.entity = var1;
         this.progressMin = var2;
      }

      public SettingUpdateEntity getEntity() {
         return this.entity;
      }

      public SettingUpdateEntity setEnable(boolean var1) {
         this.entity.setEnable(var1);
         return this.entity;
      }

      public SettingUpdateEntity setValue(Object var1) {
         if (var1 instanceof Integer) {
            SettingUpdateEntity var2 = this.entity;
            Integer var3 = (Integer)var1;
            var2.setValue(var3 + this.progressMin);
            this.entity.setProgress(var3);
         } else {
            this.entity.setValue(var1);
         }

         return this.entity;
      }
   }
}
