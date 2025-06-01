package com.hzbhd.canbus.car._423;

import android.content.Context;
import android.os.Bundle;
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
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static int TuneValue;
   private static int VolumeValue;
   Boolean PanoramicVideo = false;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   int mDifferentID;
   private HashMap mDriveItemHashMap;
   private HashMap mSettingItemHashMap;
   private UiMgr mUiMgr;
   SystemButton systemButton;

   private void PanelKnobClick(int var1, int var2) {
      this.realKeyClick3(this.mContext, var1, var2, 1);
   }

   private String ResolveTemp(int var1) {
      int var2 = this.mCanBusInfoInt[8];
      String var5 = "HIGH_TEMP";
      String var4 = "LOW_TEMP";
      String var3;
      if (var2 <= 16) {
         if (var2 < 9) {
            var3 = CommUtil.getStrByResId(this.mContext, "_421_Item_12") + this.mCanBusInfoInt[8];
            if (var1 == 1) {
               var3 = var4;
            }
         } else {
            var3 = CommUtil.getStrByResId(this.mContext, "_421_Item_13") + this.mCanBusInfoInt[8];
            if (var1 == 16) {
               var3 = var5;
            }
         }
      } else {
         var3 = (double)var1 * 0.5 + this.getTempUnitC(this.mContext);
         if (var1 == 254) {
            var3 = var4;
         }

         if (var1 == 255) {
            var3 = var5;
         }
      }

      return var3;
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
         List var6 = ((SettingPageUiSet.ListBean)var7.get(var2)).getItemList();

         for(int var3 = 0; var3 < var6.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var5 = (SettingPageUiSet.ListBean.ItemListBean)var6.get(var3);
            String var4 = var5.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var5.getMin()));
         }
      }

   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void realKeyClick0x21(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick0x22(int var1) {
      this.realKeyLongClick2(this.mContext, var1);
   }

   private void setAirInfo0x31() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
         GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
         int var1 = this.mCanBusInfoInt[6];
         if (var1 != 0) {
            if (var1 != 12) {
               if (var1 != 2) {
                  if (var1 != 3) {
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
                     GeneralAirData.front_left_blow_foot = true;
                     GeneralAirData.front_right_blow_foot = true;
                     GeneralAirData.front_left_blow_head = false;
                     GeneralAirData.front_right_blow_head = false;
                     GeneralAirData.front_left_blow_window = false;
                     GeneralAirData.front_right_blow_window = false;
                  }
               } else {
                  GeneralAirData.front_defog = true;
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
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
         }

         GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
         GeneralAirData.front_left_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_right_temperature = this.ResolveTemp(this.mCanBusInfoInt[8]);
         this.updateAirActivity(this.mContext, 1001);
      }
   }

   private void setDirveInfo0x1A() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("engine_speed", var3.append((var2[11] << 8) + var2[12]).append("r/min").toString())));
      StringBuilder var4 = new StringBuilder();
      int[] var5 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("a_current_speed", var4.append((var5[5] << 8) + var5[6]).append("km/h").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDoorInfo0x1A() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
         GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
         GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
         this.updateDoorView(this.mContext);
      }
   }

   private void setPanelKey0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 43) {
                  if (var1 != 65) {
                     if (var1 != 69) {
                        if (var1 != 70) {
                           switch (var1) {
                              case 22:
                                 this.realKeyClick0x21(49);
                                 break;
                              case 23:
                                 this.realKeyClick0x21(45);
                                 break;
                              case 24:
                                 this.realKeyClick0x21(46);
                                 break;
                              case 25:
                                 this.realKeyClick0x21(47);
                                 break;
                              case 26:
                                 this.realKeyClick0x21(48);
                           }
                        } else {
                           this.realKeyClick0x21(8);
                        }
                     } else {
                        this.realKeyClick0x21(7);
                     }
                  } else {
                     this.realKeyClick0x21(33);
                  }
               } else {
                  this.realKeyClick0x21(52);
               }
            } else {
               this.realKeyClick0x21(50);
            }
         } else {
            this.realKeyClick0x21(1);
         }
      } else {
         this.realKeyClick0x21(0);
      }

   }

   private void setPanelKey0x22() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var1 = TuneValue - var2[3];
               if (var1 < 0) {
                  this.PanelKnobClick(7, Math.abs(var1));
               } else if (var1 > 0) {
                  this.PanelKnobClick(8, Math.abs(var1));
               }

               TuneValue = this.mCanBusInfoInt[3];
            }
         } else {
            var1 = VolumeValue - var2[3];
            if (var1 < 0) {
               this.PanelKnobClick(7, Math.abs(var1));
            } else if (var1 > 0) {
               this.PanelKnobClick(8, Math.abs(var1));
            }

            VolumeValue = this.mCanBusInfoInt[3];
         }
      } else {
         this.realKeyClick0x22(0);
      }

   }

   private void setPanoramicVideo0xE8() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         this.switchFCamera(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
      }

      ArrayList var1 = new ArrayList();
      this.PanoramicVideo = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
      var1.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
      GeneralParkData.dataList = var1;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRadarInfo0x41() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 255;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(3, var1[2], var1[3], var1[4], var1[5]);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(3, var1[6], 255, 255, var1[9]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setTrackDate0x1A() {
      int[] var1 = this.mCanBusInfoInt;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte)var1[9], (byte)var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyClick0x11(0);
            break;
         case 1:
            this.realKeyClick0x11(7);
            break;
         case 2:
            this.realKeyClick0x11(8);
            break;
         case 3:
            this.realKeyClick0x11(3);
            break;
         case 4:
            this.realKeyClick0x11(187);
            break;
         case 5:
            this.realKeyClick0x11(14);
            break;
         case 6:
            this.realKeyClick0x11(15);
         case 7:
         case 10:
         case 11:
         default:
            break;
         case 8:
            this.realKeyClick0x11(45);
            break;
         case 9:
            this.realKeyClick0x11(46);
            break;
         case 12:
            this.realKeyClick0x11(2);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.initDriveItem(this.getUiMgr(var1).getDriverDataPageUiSet(var1));
      int var2 = this.getCurrentCanDifferentId();
      this.mDifferentID = var2;
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)var2, 49});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mDifferentID = this.getCurrentCanDifferentId();
      int var3 = this.mCanBusInfoInt[1];
      if (var3 != 17) {
         if (var3 != 26) {
            if (var3 != 49) {
               if (var3 != 65) {
                  if (var3 != 232) {
                     if (var3 != 240) {
                        if (var3 != 33) {
                           if (var3 == 34) {
                              this.setPanelKey0x22();
                           }
                        } else {
                           this.setPanelKey0x21();
                        }
                     } else {
                        this.setVersionInfo0xF0();
                     }
                  } else {
                     this.setPanoramicVideo0xE8();
                  }
               } else {
                  this.setRadarInfo0x41();
               }
            } else {
               this.setAirInfo0x31();
            }
         } else {
            this.setDoorInfo0x1A();
            this.setDirveInfo0x1A();
            this.setTrackDate0x1A();
            this.forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]));
            int[] var4 = this.mCanBusInfoInt;
            this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[5], var4[6]));
         }
      } else {
         this.setWheelKey0x11();
         this.setBacklightLevel(this.mCanBusInfoInt[7] / 20 + 1);
      }

   }

   public void hideButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicInfo0xFD();
                  }
               });
            }

            this.this$0.systemButton.hide();
         }
      });
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void showButton() {
      this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            if (this.this$0.systemButton == null) {
               this.this$0.systemButton = new SystemButton(this.this$0.getActivity(), "P", new SystemButton.PanoramaListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void clickEvent() {
                     this.this$1.this$0.getUiMgr(this.this$1.this$0.mContext).sendPanoramicInfo0xFD();
                     this.this$1.this$0.switchFCamera(this.this$1.this$0.mContext, this.this$1.this$0.PanoramicVideo ^ true);
                  }
               });
            }

            this.this$0.systemButton.show();
         }
      });
   }

   public void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateSettingActivity((Bundle)null);
      this.updateGeneralSettingData(var4);
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
