package com.hzbhd.canbus.car._377;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static int volKnobValue;
   private static int volKnobValueRadio;
   String ACUnit;
   String CurrentOrHistoryACUnit;
   int DoorMemory;
   String InstantConsumptionUnit;
   String RMUnit;
   String TripAUnit;
   int differentId;
   int eachId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private HashMap mDriveItemHashMap;
   private int[] mRadarData;
   private HashMap mSettingItemHashMap;
   private UiMgr mUiMgr;

   private void PanelKnobClick(int var1, int var2) {
      this.realKeyClick3(this.mContext, var1, var2, 1);
   }

   private void ResolveUnit() {
      this.RMUnit = " km";
      this.TripAUnit = " km";
      this.ACUnit = " mpg";
      this.CurrentOrHistoryACUnit = " mpg";
      this.InstantConsumptionUnit = " mpg";
      int var1 = this.mCanBusInfoInt[14];
      if (DataHandleUtils.getBoolBit7(var1)) {
         this.RMUnit = " mile";
      }

      if (DataHandleUtils.getBoolBit6(var1)) {
         this.TripAUnit = " mile";
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1, 4, 2) == 1) {
         this.ACUnit = " km/L";
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1, 4, 2) == 2) {
         this.ACUnit = " l/100km";
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1, 2, 2) == 1) {
         this.CurrentOrHistoryACUnit = " km/L";
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1, 2, 2) == 2) {
         this.CurrentOrHistoryACUnit = " l/100km";
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 1) {
         this.InstantConsumptionUnit = " km/L";
      }

      if (DataHandleUtils.getIntFromByteWithBit(var1, 0, 2) == 2) {
         this.InstantConsumptionUnit = " l/100km";
      }

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
         List var5 = ((DriverDataPageUiSet.Page)var4.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var6 = ((DriverDataPageUiSet.Page.Item)var5.get(var3)).getTitleSrn();
            this.mDriveItemHashMap.put(var6, new DriveDataUpdateHelper(new DriverUpdateEntity(var2, var3, "null_value")));
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

   private boolean isRadarDataChange() {
      Arrays.equals(this.mRadarData, this.mCanBusInfoInt);
      int[] var1 = this.mCanBusInfoInt;
      this.mRadarData = Arrays.copyOf(var1, var1.length);
      return true;
   }

   private void realKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realPanelKeyClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private String resolveData13(int var1) {
      return (new int[]{60, 10, 12, 20, 30, 40, 50, 70, 80, 90, 100})[var1] + "";
   }

   private void setDoorInfo0x12() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != this.DoorMemory) {
         this.DoorMemory = var1;
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(var1);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setFuelConsumptionMileage0x16() {
      ArrayList var1 = new ArrayList();
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_1", this.mCanBusInfoInt[2] + "")));
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_2", var3.append((float)((var2[3] << 8) + var2[4]) / 10.0F).append(this.CurrentOrHistoryACUnit).toString())));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_3", var4.append((float)((var5[5] << 8) + var5[6]) / 10.0F).append(this.CurrentOrHistoryACUnit).toString())));
      var4 = new StringBuilder();
      var5 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_4", var4.append((float)((var5[7] << 8) + var5[8]) / 10.0F).append(this.ACUnit).toString())));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_5", var3.append((float)((var2[9] << 16) + (var2[10] << 8) + var2[11]) / 10.0F).append(this.TripAUnit).toString())));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_6", var3.append((float)((var2[12] << 8) + var2[13]) / 10.0F).append(this.RMUnit).toString())));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_7", this.resolveData13(this.mCanBusInfoInt[15]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setHistoryConsumptionMileage0x17() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_8", var2.append((float)((var3[2] << 16) + (var3[3] << 8) + var3[4]) / 10.0F).append(this.TripAUnit).toString())));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_9", var2.append((float)((var3[5] << 8) + var3[6]) / 10.0F).append(this.ACUnit).toString())));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_10", var5.append((float)((var4[7] << 16) + (var4[8] << 8) + var4[9]) / 10.0F).append(this.TripAUnit).toString())));
      var5 = new StringBuilder();
      var4 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_11", var5.append((float)((var4[10] << 8) + var4[11]) / 10.0F).append(this.ACUnit).toString())));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_12", var2.append((float)((var3[12] << 16) + (var3[13] << 8) + var3[14]) / 10.0F).append(this.TripAUnit).toString())));
      var2 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_377_item_13", var2.append((float)((var3[15] << 8) + var3[16]) / 10.0F).append(this.ACUnit).toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setPanelKey0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 6) {
                     if (var1 != 44) {
                        if (var1 != 47) {
                           if (var1 != 49) {
                              if (var1 != 55) {
                                 if (var1 != 61) {
                                    if (var1 != 97) {
                                       if (var1 != 71) {
                                          if (var1 != 72) {
                                             if (var1 != 75) {
                                                if (var1 != 76) {
                                                   switch (var1) {
                                                      case 8:
                                                         this.realPanelKeyClick(59);
                                                         break;
                                                      case 9:
                                                         this.realPanelKeyClick(3);
                                                         break;
                                                      case 10:
                                                         this.realPanelKeyClick(33);
                                                         break;
                                                      case 11:
                                                         this.realPanelKeyClick(34);
                                                         break;
                                                      case 12:
                                                         this.realPanelKeyClick(35);
                                                         break;
                                                      case 13:
                                                         this.realPanelKeyClick(36);
                                                         break;
                                                      case 14:
                                                         this.realPanelKeyClick(37);
                                                         break;
                                                      case 15:
                                                         this.realPanelKeyClick(38);
                                                         break;
                                                      case 16:
                                                         this.realPanelKeyClick(95);
                                                         break;
                                                      default:
                                                         switch (var1) {
                                                            case 22:
                                                               this.realPanelKeyClick(49);
                                                               break;
                                                            case 23:
                                                               this.realPanelKeyClick(45);
                                                               break;
                                                            case 24:
                                                               this.realPanelKeyClick(46);
                                                         }
                                                   }
                                                } else {
                                                   this.realPanelKeyClick(188);
                                                }
                                             } else {
                                                this.realPanelKeyClick(4112);
                                             }
                                          } else {
                                             this.realPanelKeyClick(77);
                                          }
                                       } else {
                                          this.realPanelKeyClick(76);
                                       }
                                    } else {
                                       this.realPanelKeyClick(35);
                                    }
                                 } else {
                                    this.realPanelKeyClick(139);
                                 }
                              } else {
                                 this.realPanelKeyClick(2);
                              }
                           } else {
                              this.realPanelKeyClick(223);
                           }
                        } else {
                           this.realPanelKeyClick(151);
                        }
                     } else {
                        this.realPanelKeyClick(2);
                     }
                  } else {
                     this.realPanelKeyClick(50);
                  }
               } else {
                  this.realPanelKeyClick(20);
               }
            } else {
               this.realPanelKeyClick(21);
            }
         } else {
            this.realPanelKeyClick(1);
         }
      } else {
         this.realPanelKeyClick(0);
      }

   }

   private void setPanelKey0x22() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 1) {
         if (var1 == 2) {
            var1 = volKnobValueRadio - var2[3];
            if (var1 < 0) {
               this.PanelKnobClick(46, Math.abs(var1));
            } else if (var1 > 0) {
               this.PanelKnobClick(45, Math.abs(var1));
            }

            volKnobValueRadio = this.mCanBusInfoInt[3];
         }
      } else {
         var1 = volKnobValue - var2[3];
         if (var1 < 0) {
            this.PanelKnobClick(7, Math.abs(var1));
         } else if (var1 > 0) {
            this.PanelKnobClick(8, Math.abs(var1));
         }

         volKnobValue = this.mCanBusInfoInt[3];
      }

   }

   private void setRadar0x41() {
      if (this.isRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(4, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

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
                        if (var1 != 16) {
                           switch (var1) {
                              case 8:
                                 this.realKeyClick(45);
                                 break;
                              case 9:
                                 this.realKeyClick(46);
                                 break;
                              case 10:
                                 this.realKeyClick(2);
                           }
                        } else {
                           this.realKeyClick(33);
                        }
                     } else {
                        this.realKeyClick(468);
                     }
                  } else {
                     this.realKeyClick(467);
                  }
               } else {
                  this.realKeyClick(187);
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
      this.differentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mContext = var1;
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 != 18) {
            if (var3 != 22) {
               if (var3 != 23) {
                  if (var3 != 33) {
                     if (var3 != 34) {
                        if (var3 != 65) {
                           if (var3 == 240) {
                              this.setVersionInfo0xF0();
                           }
                        } else {
                           this.setRadar0x41();
                        }
                     } else {
                        this.setPanelKey0x22();
                     }
                  } else {
                     this.setPanelKey0x21();
                  }
               } else {
                  this.setHistoryConsumptionMileage0x17();
               }
            } else {
               this.ResolveUnit();
               this.setFuelConsumptionMileage0x16();
            }
         } else {
            this.setDoorInfo0x12();
         }
      } else {
         this.setWheelKey0x11();
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initDriveItem(this.getUiMgr(this.mContext).getDriverDataPageUiSet(this.mContext));
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
