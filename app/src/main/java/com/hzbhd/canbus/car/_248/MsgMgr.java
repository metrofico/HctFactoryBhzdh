package com.hzbhd.canbus.car._248;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   private static final int RADAR_DATA_MAX = 255;
   private static final int RADAR_DATA_SEGMENT = 10;
   public static int chargeModel;
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   public static int recycleModel;
   private static int volKnobValue;
   private static int volKnobValueRadio;
   private int mCameraStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanbusAirInfoCopy;
   private byte[] mCanbusDoorInfoCopy;
   private Context mContext;
   private int mDifferent;
   private HashMap mDriveItemHashMap;
   private int mEachId;
   private int[] mRadarData;
   private HashMap mSettingItemHashMap;
   private int[] mTrackData;
   private UiMgr mUiMgr;
   String str;

   private void PanelKnobClick(int var1, int var2) {
      this.realKeyClick3(this.mContext, var1, var2, 1);
   }

   private String ResolvePano(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = CommUtil.getStrByResId(this.mContext, "_342_setting_1_5_1");
      } else {
         var2 = CommUtil.getStrByResId(this.mContext, "_342_setting_1_5_0");
      }

      return var2;
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIndexBy6Bit(int var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 <= 60; ++var2) {
         if (var1 == var2) {
            var3 = var1;
         }
      }

      return var3;
   }

   private int getIndexByData(int var1) {
      return var1 == 1 ? 0 : 1;
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
         List var4 = ((SettingPageUiSet.ListBean)var7.get(var2)).getItemList();

         for(int var3 = 0; var3 < var4.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var4.get(var3);
            String var5 = var6.getTitleSrn();
            this.mSettingItemHashMap.put(var5, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var6.getMin()));
         }
      }

   }

   private boolean isRadarDataChange() {
      Arrays.equals(this.mRadarData, this.mCanBusInfoInt);
      int[] var1 = this.mCanBusInfoInt;
      this.mRadarData = Arrays.copyOf(var1, var1.length);
      return true;
   }

   private boolean isTrackDataChange() {
      int[] var2 = this.mTrackData;
      int[] var1 = this.mCanBusInfoInt;
      Arrays.equals(var2, new int[]{var1[8], var1[9]});
      var1 = this.mCanBusInfoInt;
      this.mTrackData = Arrays.copyOf(var1, var1.length);
      return true;
   }

   private int resolveCycle(int var1) {
      if (var1 == 0) {
         return 1;
      } else {
         int var2 = var1;
         if (var1 == 1) {
            var2 = 0;
         }

         return var2;
      }
   }

   private String resolveDriveData(int var1, int var2, String var3) {
      String var4 = "---" + var3;
      var1 = var1 * 256 + var2;
      if (var1 != 65535) {
         var4 = var1 + var3;
      }

      return var4;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (36 <= var1 && 63 >= var1) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "--";
      }

      return var2;
   }

   private int resolveRadarData(int var1) {
      return var1 == 255 ? 255 : var1 / 25 + 1;
   }

   private void set0x11WheelKey() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[4];
      if (var1 != 103) {
         switch (var1) {
            case 0:
               this.realKeyLongClick1(this.mContext, 0, var2[5]);
               break;
            case 1:
               this.realKeyLongClick1(this.mContext, 7, var2[5]);
               break;
            case 2:
               this.realKeyLongClick1(this.mContext, 8, var2[5]);
               break;
            case 3:
               this.realKeyLongClick1(this.mContext, 3, var2[5]);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 187, var2[5]);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 14, var2[5]);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 15, var2[5]);
               break;
            default:
               switch (var1) {
                  case 8:
                     this.realKeyLongClick1(this.mContext, 45, var2[5]);
                     break;
                  case 9:
                     this.realKeyLongClick1(this.mContext, 46, var2[5]);
                     break;
                  case 10:
                     this.realKeyLongClick1(this.mContext, 2, var2[5]);
               }
         }
      } else {
         this.realKeyLongClick1(this.mContext, 33, var2[5]);
      }

   }

   private void set0x21WheelKey() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 17) {
                        if (var1 != 37) {
                           if (var1 != 40) {
                              if (var1 != 45) {
                                 if (var1 != 54) {
                                    if (var1 != 63) {
                                       if (var1 != 21) {
                                          if (var1 != 22) {
                                             if (var1 != 56) {
                                                if (var1 != 57) {
                                                   switch (var1) {
                                                      case 9:
                                                         this.realKeyLongClick1(this.mContext, 3, var2[3]);
                                                         break;
                                                      case 10:
                                                         this.realKeyLongClick1(this.mContext, 33, var2[3]);
                                                         break;
                                                      case 11:
                                                         this.realKeyLongClick1(this.mContext, 34, var2[3]);
                                                         break;
                                                      case 12:
                                                         this.realKeyLongClick1(this.mContext, 35, var2[3]);
                                                         break;
                                                      case 13:
                                                         this.realKeyLongClick1(this.mContext, 36, var2[3]);
                                                         break;
                                                      case 14:
                                                         this.realKeyLongClick1(this.mContext, 37, var2[3]);
                                                         break;
                                                      case 15:
                                                         this.realKeyLongClick1(this.mContext, 38, var2[3]);
                                                   }
                                                } else {
                                                   this.realKeyLongClick1(this.mContext, 30, var2[3]);
                                                }
                                             } else {
                                                this.realKeyLongClick1(this.mContext, 2, var2[3]);
                                             }
                                          } else {
                                             this.realKeyLongClick1(this.mContext, 4, var2[3]);
                                          }
                                       } else {
                                          this.realKeyLongClick1(this.mContext, 75, var2[3]);
                                       }
                                    } else {
                                       this.realKeyLongClick1(this.mContext, 201, var2[3]);
                                    }
                                 } else {
                                    this.realKeyLongClick1(this.mContext, 58, var2[3]);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 151, var2[3]);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 14, var2[3]);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 128, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 31, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 62, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 20, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 21, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 1, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   private void set0x22WheelKey() {
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

   private void setAirData0x31() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac = var2;
      GeneralAirData.ion = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.in_out_auto_cycle = this.resolveCycle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2));
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
      GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
      int var1 = this.mCanBusInfoInt[6];
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      if (var1 != 1) {
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
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
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
         GeneralAirData.front_auto_wind_model = true;
      }

      var1 = this.getCurrentCanDifferentId();
      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      if (var1 != 3 && var1 != 4 && var1 != 0) {
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      } else {
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void setDoorData0x12() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isShowSeatBelt = true;
      GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
      this.updateDoorView(this.mContext);
   }

   private void setEnergyData() {
      ArrayList var1 = new ArrayList();
      if (this.mCanBusInfoInt[8] == 0) {
         this.str = CommUtil.getStrByResId(this.mContext, "energy_no_display");
      } else {
         this.str = CommUtil.getStrByResId(this.mContext, "energy_drive");
      }

      var1.add(this.checkEntity(this.helperSetValue("energy_flow", this.str)).setValueStr(true));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setPanoramic0xe8() {
      LogUtil.showLog("setPanoramic0xe8");
      int var2 = this.mCameraStatus;
      int var1 = this.mCanBusInfoInt[2];
      boolean var5 = false;
      boolean var4 = false;
      boolean var3;
      if (var2 != var1) {
         this.mCameraStatus = var1;
         Context var6 = this.mContext;
         if (var1 == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.forceReverse(var6, var3);
      }

      ArrayList var7 = new ArrayList();
      var1 = this.mDifferent;
      if (var1 == 5) {
         if (this.mCanBusInfoInt[3] == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(1, var3));
         if (this.mCanBusInfoInt[3] == 2) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(2, var3));
         if (this.mCanBusInfoInt[3] == 3) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(3, var3));
         if (this.mCanBusInfoInt[3] == 4) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(4, var3));
         if (this.mCanBusInfoInt[3] == 5) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(5, var3));
         if (this.mCanBusInfoInt[3] == 7) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(6, var3));
         var3 = var4;
         if (this.mCanBusInfoInt[3] == 8) {
            var3 = true;
         }

         var7.add(new PanoramicBtnUpdateEntity(7, var3));
      } else if (var1 != 1 && var1 != 4) {
         var7 = null;
      } else {
         if (this.mCanBusInfoInt[3] == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(1, var3));
         if (this.mCanBusInfoInt[3] == 3) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(2, var3));
         if (this.mCanBusInfoInt[3] == 7) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.add(new PanoramicBtnUpdateEntity(3, var3));
         var3 = var5;
         if (this.mCanBusInfoInt[3] == 8) {
            var3 = true;
         }

         var7.add(new PanoramicBtnUpdateEntity(4, var3));
      }

      GeneralParkData.dataList = var7;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRadarData0x41() {
      if (this.isRadarDataChange()) {
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSettingData0x67() {
      if (this.getCurrentCanDifferentId() == 0) {
         int[] var14 = this.mCanBusInfoInt;
         int var3 = var14[2];
         chargeModel = var3;
         int var11 = var14[3];
         int var1 = var14[4];
         int var10 = var14[5];
         int var7 = var14[6];
         int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(var14[7]));
         recycleModel = var5;
         int var13 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]));
         int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]));
         int var6 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]));
         int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]));
         int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]));
         int var4 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]));
         int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]));
         ArrayList var15 = new ArrayList();
         var15.add(new SettingUpdateEntity(5, 0, var3));
         var15.add((new SettingUpdateEntity(5, 1, var11)).setProgress(var11));
         var15.add((new SettingUpdateEntity(5, 2, var1)).setProgress(var1));
         var15.add((new SettingUpdateEntity(5, 3, var10)).setProgress(var10));
         var15.add((new SettingUpdateEntity(5, 4, var7)).setProgress(var7));
         var15.add(new SettingUpdateEntity(5, 5, var5));
         var15.add(new SettingUpdateEntity(5, 6, var13));
         var15.add(new SettingUpdateEntity(5, 7, var9));
         var15.add(new SettingUpdateEntity(5, 8, var6));
         var15.add(new SettingUpdateEntity(5, 9, var12));
         var15.add(new SettingUpdateEntity(5, 10, var8));
         var15.add(new SettingUpdateEntity(5, 11, var4));
         var15.add(new SettingUpdateEntity(5, 12, var2));
         this.updateGeneralSettingData(var15);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setSettingData0x6D() {
      int var1 = this.mCanBusInfoInt[2];
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(6, 2, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData0x96() {
      int var1 = this.getIndexByData(this.mCanBusInfoInt[2]);
      int var17 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
      int var24 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
      int var8 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
      int var21 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
      int var18 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
      int var11 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
      int var28 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
      int var26 = this.mCanBusInfoInt[5];
      int[] var31 = this.mCanBusInfoInt;
      int var3 = var31[5];
      int var13 = var31[6];
      int var7 = var31[7];
      var31 = this.mCanBusInfoInt;
      int var15 = var31[7];
      int var27 = var31[8];
      var31 = this.mCanBusInfoInt;
      int var20 = var31[8];
      int var6 = var31[9];
      int var25 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(var31[10]));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]));
      int var23 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]));
      int var16 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10]));
      int var5 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10]));
      int var22 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10]));
      int var12 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10]));
      int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 2);
      int var29 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]));
      int var9 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]));
      int var30 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
      int var14 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[11]));
      int var19 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]));
      ArrayList var33 = new ArrayList();
      byte var32;
      if (var1 == 1) {
         var32 = 0;
      } else {
         var32 = 1;
      }

      if (this.getCurrentCanDifferentId() == 0) {
         var33.add(new SettingUpdateEntity(6, 0, Integer.valueOf(var32)));
      } else {
         var33.add(new SettingUpdateEntity(5, 0, Integer.valueOf(var32)));
      }

      var33.add(new SettingUpdateEntity(0, 0, var17));
      var33.add(new SettingUpdateEntity(0, 1, var24));
      var33.add(new SettingUpdateEntity(0, 2, var4));
      var33.add(new SettingUpdateEntity(0, 3, var8));
      var33.add(new SettingUpdateEntity(1, 0, var21));
      var33.add(new SettingUpdateEntity(1, 1, var18));
      var33.add(new SettingUpdateEntity(1, 2, var11));
      var33.add(new SettingUpdateEntity(1, 3, var28));
      var33.add((new SettingUpdateEntity(2, 0, String.valueOf(var26 * 10))).setProgress(var3));
      var33.add(new SettingUpdateEntity(2, 1, var13));
      var33.add((new SettingUpdateEntity(2, 2, String.valueOf(var7))).setProgress(var15));
      var33.add((new SettingUpdateEntity(2, 3, String.valueOf(var27))).setProgress(var20));
      var33.add(new SettingUpdateEntity(2, 4, var6));
      var33.add(new SettingUpdateEntity(3, 0, var25));
      var33.add(new SettingUpdateEntity(3, 1, var2));
      var33.add(new SettingUpdateEntity(3, 2, var23));
      var33.add(new SettingUpdateEntity(3, 3, var16));
      var33.add(new SettingUpdateEntity(3, 4, var5));
      var33.add(new SettingUpdateEntity(3, 5, var22));
      var33.add(new SettingUpdateEntity(3, 6, var12));
      var33.add(new SettingUpdateEntity(4, 0, var10));
      var33.add(new SettingUpdateEntity(4, 1, var29));
      var33.add(new SettingUpdateEntity(4, 2, var9));
      var33.add(new SettingUpdateEntity(4, 3, var30));
      var33.add(new SettingUpdateEntity(4, 4, var14));
      if (this.getCurrentCanDifferentId() == 0) {
         var33.add(new SettingUpdateEntity(6, 1, var19));
      } else {
         var33.add(new SettingUpdateEntity(5, 1, var19));
      }

      this.updateGeneralSettingData(var33);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrackData0x11() {
      if (this.isTrackDataChange()) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 5500, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleInfo0x32() {
      ArrayList var1 = new ArrayList();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, this.resolveDriveData(var2[4], var2[5], " r/min")));
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, this.resolveDriveData(var2[6], var2[7], " km/h")));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var3 = this.mCanBusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var3[6], var3[7]));
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void switchLinkAndSos() {
      if (this.mCanBusInfoInt[2] == 1) {
         GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131769126);
         this.sendDisplayMsgView(this.mContext);
         this.enterAuxIn2();
      }

      if (this.mCanBusInfoInt[2] == 2) {
         GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(2131769953);
         this.sendDisplayMsgView(this.mContext);
         this.enterAuxIn2();
      }

      if (this.mCanBusInfoInt[2] == 0) {
         this.exitAuxIn2();
      }

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
                  if (var3 != 37) {
                     if (var3 != 62) {
                        if (var3 != 65) {
                           if (var3 != 103) {
                              if (var3 != 150) {
                                 if (var3 != 232) {
                                    if (var3 != 240) {
                                       if (var3 != 49) {
                                          if (var3 == 50) {
                                             this.setVehicleInfo0x32();
                                          }
                                       } else {
                                          if (this.isAirMsgRepeatTrumpchi(var2)) {
                                             return;
                                          }

                                          this.setAirData0x31();
                                       }
                                    } else {
                                       this.setVersionInfo();
                                    }
                                 } else {
                                    this.setPanoramic0xe8();
                                 }
                              } else {
                                 this.setSettingData0x96();
                              }
                           } else {
                              this.setSettingData0x67();
                           }
                        } else {
                           this.setRadarData0x41();
                        }
                     } else {
                        this.setEnergyData();
                     }
                  } else {
                     this.switchLinkAndSos();
                  }
               } else {
                  this.set0x22WheelKey();
               }
            } else {
               this.set0x21WheelKey();
            }
         } else {
            if (this.isDoorMsgRepeatTrumpchi(var2)) {
               return;
            }

            this.setDoorData0x12();
         }
      } else {
         this.set0x11WheelKey();
         this.setTrackData0x11();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var8, (byte)var6, 0, 0, (byte)var10, (byte)var2, (byte)var3, (byte)var4, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mDifferent = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      this.initSettingsItem(this.getUiMgr(var1).getSettingUiSet(var1));
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.mEachId, 14});
   }

   protected boolean isAirMsgRepeatTrumpchi(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusAirInfoCopy)) {
         return true;
      } else {
         this.mCanbusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   protected boolean isDoorMsgRepeatTrumpchi(byte[] var1) {
      if (Arrays.equals(var1, this.mCanbusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanbusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
      SharePreUtil.setIntValue(var1, "LeftPos", var3);
      SharePreUtil.setIntValue(var1, "RightPos", var4);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var3, var4, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
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
