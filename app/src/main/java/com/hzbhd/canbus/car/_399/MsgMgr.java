package com.hzbhd.canbus.car._399;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int mDifferentID;
   private HashMap mDriveItemHashMap;
   private HashMap mSettingItemHashMap;
   private UiMgr mUiMgr;

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
      List var6 = var1.getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var5 = ((DriverDataPageUiSet.Page)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var4 = ((DriverDataPageUiSet.Page.Item)var5.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var4, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
         }
      }

   }

   private void initSettingsItem(SettingPageUiSet var1) {
      this.mSettingItemHashMap = new HashMap();
      List var7 = var1.getList();

      for(int var2 = 0; var2 < var7.size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var7.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var5.get(var3);
            String var4 = var6.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var6.getMin()));
         }
      }

   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void setAirInfo0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      int var1 = this.mCanBusInfoInt[6];
      if (var1 != 0) {
         if (var1 != 3) {
            if (var1 != 12) {
               if (var1 != 5) {
                  if (var1 == 6) {
                     GeneralAirData.front_left_blow_foot = false;
                     GeneralAirData.front_right_blow_foot = false;
                     GeneralAirData.front_left_blow_head = true;
                     GeneralAirData.front_right_blow_head = true;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_left_blow_foot = true;
                  GeneralAirData.front_right_blow_foot = true;
                  GeneralAirData.front_left_blow_head = true;
                  GeneralAirData.front_right_blow_head = true;
                  GeneralAirData.front_left_blow_window = false;
                  GeneralAirData.front_right_blow_window = false;
               }
            } else {
               var1 = this.mDifferentID;
               if (var1 == 2 || var1 == 3) {
                  return;
               }

               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_left_blow_head = false;
               GeneralAirData.front_right_blow_head = false;
               GeneralAirData.front_left_blow_window = true;
               GeneralAirData.front_right_blow_window = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
         }
      } else {
         GeneralAirData.front_left_blow_foot = false;
         GeneralAirData.front_right_blow_foot = false;
         GeneralAirData.front_left_blow_head = false;
         GeneralAirData.front_right_blow_head = false;
         GeneralAirData.front_left_blow_window = false;
         GeneralAirData.front_right_blow_window = false;
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[9]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarInfo0x32() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("engine_speed", var3.append((var2[4] << 8) + var2[5]).append("RPM").toString())));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("S18_CarBody_2", var3.append((var2[6] << 8) + var2[7]).append("KM/h").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorInfo0x12() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setFuelMileage() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_399_item_1", var2.append((float)((var3[6] << 16) + (var3[7] << 8) + var3[8]) / 10.0F).append("KM").toString())));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_399_item_2", var2.append((float)((var3[9] << 8) + var3[10]) / 10.0F).append("KM/L").toString())));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_399_item_3", var2.append((float)((var3[11] << 16) + (var3[12] << 8) + var3[13]) / 10.0F).append("KM").toString())));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_399_item_4", var5.append((float)((var4[14] << 8) + var4[15]) / 10.0F).append("KM/L").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
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
               if (var1 != 4) {
                  if (var1 != 5) {
                     if (var1 != 6) {
                        if (var1 != 8) {
                           if (var1 != 9) {
                              if (var1 == 12) {
                                 this.realKeyClick0x11(2);
                              }
                           } else {
                              this.realKeyClick0x11(46);
                           }
                        } else {
                           this.realKeyClick0x11(45);
                        }
                     } else {
                        this.realKeyClick0x11(15);
                     }
                  } else {
                     this.realKeyClick0x11(14);
                  }
               } else {
                  this.realKeyClick0x11(187);
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

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.initDriveItem(this.getUiMgr(var1).getDriverDataPageUiSet(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mDifferentID = this.getCurrentCanDifferentId();
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 49) {
               if (var3 != 50) {
                  if (var3 != 52) {
                     if (var3 == 240) {
                        this.setVersionInfo0xF0();
                     }
                  } else {
                     this.setFuelMileage();
                  }
               } else {
                  this.setCarInfo0x32();
               }
            } else {
               this.setAirInfo0x31();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
         this.setTrackDate0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferentID = this.getCurrentCanDifferentId();
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 3, 26});
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
