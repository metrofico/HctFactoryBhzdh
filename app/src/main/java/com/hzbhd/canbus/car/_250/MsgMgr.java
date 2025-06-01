package com.hzbhd.canbus.car._250;

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
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   protected static final String CAN_250_SAVE_RADAR_DISP = "__250_SAVE_RADAR_DISP";
   public static int m0x27SettingData;
   private static int mDifferentId;
   protected static boolean mIs360Full;
   private static boolean mIsBackLast;
   private static boolean mIsBelt;
   private static boolean mIsFLDoorLast;
   private static boolean mIsFRDoorLast;
   private static boolean mIsFrontLast;
   private static boolean mIsLeftEable;
   private static boolean mIsRLDoorLast;
   private static boolean mIsRRDoorLast;
   private static boolean mIsRightEable;
   private static boolean mIsSubBelt;
   protected static int mLast360st;
   private static int mSkyLineSt;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private HashMap mDriveItemHashMap;
   private HashMap mSettingItemHashMap;
   private UiMgr mUiMgr;

   private void SetEnergyinformation0x40() {
      ArrayList var1 = new ArrayList();
      DecimalFormat var2 = new DecimalFormat("0.0");
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_Motor_Power", this.resplveMotoPower(this.mCanBusInfoInt[2]))));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_Other_system_power", this.mCanBusInfoInt[3] + " kw")));
      StringBuilder var4 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_Average_energy_consumption", var4.append(var2.format((double)((float)this.getMsbLsbResult(var3[4], var3[5]) / 10.0F))).append("kwh/100km").toString())));
      var4 = new StringBuilder();
      var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_Remaining_journey", var4.append(this.getMsbLsbResult(var3[6], var3[7])).append("km").toString())));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_battery_power", this.mCanBusInfoInt[8] + "%")));
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_Last_charge_value", this.mCanBusInfoInt[9] + "%")));
      StringBuilder var5 = new StringBuilder();
      int[] var6 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue("_250_Driving_after_the_most_recent_charge_mileage", var5.append(var2.format((double)((float)this.getMsbLsbResult(var6[10], var6[11]) / 10.0F))).append("km").toString())));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void SetEnergysavingdriving() {
      ArrayList var1 = new ArrayList();
      String var2 = "numb_" + this.mCanBusInfoInt[2];
      int[] var3 = this.mCanBusInfoInt;
      var1.add(this.checkDriveEntity(this.helperSetDriveDataValue(var2, this.resolveEnergysaving(var3[2], var3[3], var3[4], var3[5], var3[6], var3[7], var3[8], var3[9], var3[10], var3[11], var3[12]))));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void SettingData8_0x29() {
      ArrayList var4 = new ArrayList();
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 3);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 3);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2);
      var4.add(this.checkEntity(this.helperSetValue("_250_theme_change", var2)));
      var4.add(this.checkEntity(this.helperSetValue("_250_Language", var1)));
      var4.add(this.checkEntity(this.helperSetValue("_250_Combination_meter_style_settings", var3 - 1)));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private void SettingData9_0x29() {
      ArrayList var5 = new ArrayList();
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1);
      boolean var4;
      if (var3 == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 1);
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 1);
      var5.add(this.checkEntity(this.helperSetValue("_250_Lane_Keeping_Assist_System", var3)));
      var5.add(this.checkEntity(this.helperSetValue("_250_Lane_Keeping_Assist_System_Assist_Mode", var2, var4)));
      var5.add(this.checkEntity(this.helperSetValue("_250_Traffic_sign_recognition", var1)));
      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private String Zea0(int var1) {
      return (new DecimalFormat("00")).format((long)var1);
   }

   private String Zea00(float var1) {
      return (new DecimalFormat("0.0")).format((double)var1);
   }

   private void and_ambient_lighting_brightness_meter_0x29() {
      ArrayList var2 = new ArrayList();
      int var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) >= 1) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) - 1;
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3);
      }

      var2.add(new SettingUpdateEntity(3, 0, var1));
      var2.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
      var2.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)));
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) >= 1) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) - 1;
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
      }

      var2.add(new SettingUpdateEntity(3, 3, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity var1) {
      return var1.getPage() != -1 && var1.getIndex() != -1 ? var1 : null;
   }

   private SettingUpdateEntity checkEntity(SettingUpdateEntity var1) {
      return var1.getLeftListIndex() != -1 && var1.getRightListIndex() != -1 ? var1 : null;
   }

   private String getBytesString(byte[] var1) {
      int var3 = var1.length - 3;
      byte[] var4 = new byte[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         var4[var2] = var1[var2 + 3];
      }

      return new String(var4);
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getStringByIndex(boolean var1) {
      return var1 ? "english_on" : "english_off";
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
      List var5 = var1.getList();

      for(int var2 = 0; var2 < var5.size(); ++var2) {
         List var6 = ((SettingPageUiSet.ListBean)var5.get(var2)).getItemList();

         for(int var3 = 0; var3 < var6.size(); ++var3) {
            SettingPageUiSet.ListBean.ItemListBean var7 = (SettingPageUiSet.ListBean.ItemListBean)var6.get(var3);
            String var4 = var7.getTitleSrn();
            this.mSettingItemHashMap.put(var4, new SettingUpdateHelper(new SettingUpdateEntity(var2, var3, "null_value"), var7.getMin()));
         }
      }

   }

   private void keyControl0x20() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     if (var1 != 8) {
                        switch (var1) {
                           case 11:
                              this.realKeyClick(206);
                              break;
                           case 12:
                              this.realKeyClick(207);
                              break;
                           case 13:
                              this.realKeyClick(187);
                        }
                     } else {
                        this.realKeyClick(50);
                     }
                  } else {
                     this.realKeyClick(2);
                  }
               } else {
                  this.realKeyClick(3);
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

   private void keyControl0x21() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     if (var1 != 9) {
                        if (var1 != 10) {
                           if (var1 != 47) {
                              if (var1 != 48) {
                                 switch (var1) {
                                    case 32:
                                       this.realKeyClick(30);
                                       break;
                                    case 33:
                                       this.realKeyClick(129);
                                       break;
                                    case 34:
                                       this.realKeyClick(75);
                                       break;
                                    case 35:
                                       this.realKeyClick(21);
                                       break;
                                    case 36:
                                       this.realKeyClick(20);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 43:
                                             this.realKeyClick(7);
                                             break;
                                          case 44:
                                             this.realKeyClick(8);
                                             break;
                                          case 45:
                                             this.realKeyClick(134);
                                             break;
                                          default:
                                             switch (var1) {
                                                case 50:
                                                   this.realKeyClick(68);
                                                   break;
                                                case 51:
                                                   this.realKeyClick(59);
                                                   break;
                                                case 52:
                                                   this.realKeyClick(50);
                                                   break;
                                                case 53:
                                                   this.realKeyClick(52);
                                                   break;
                                                case 54:
                                                   this.realKeyClick(39);
                                                   break;
                                                case 55:
                                                   this.realKeyClick(128);
                                                   break;
                                                case 56:
                                                   this.realKeyClick(40);
                                                   break;
                                                case 57:
                                                   this.realKeyClick(152);
                                             }
                                       }
                                 }
                              } else {
                                 this.realKeyClick(58);
                              }
                           } else {
                              this.realKeyClick(4);
                           }
                        } else if (this.getCurrentCanDifferentId() != 3 && this.getCurrentCanDifferentId() != 4) {
                           this.realKeyClick(15);
                        } else {
                           this.realKeyClick(14);
                        }
                     } else if (this.getCurrentCanDifferentId() != 3 && this.getCurrentCanDifferentId() != 4) {
                        this.realKeyClick(14);
                     } else {
                        this.realKeyClick(152);
                     }
                  } else {
                     this.realKeyClick(2);
                  }
               } else {
                  this.realKeyClick(3);
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

   private void original_car_set_0x29() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)));
      var1.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)));
      var1.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)));
      var1.add(new SettingUpdateEntity(0, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)));
      var1.add(new SettingUpdateEntity(0, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)));
      var1.add(new SettingUpdateEntity(0, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      var1.add(new SettingUpdateEntity(0, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      var1.add(new SettingUpdateEntity(0, 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)));
      var1.add(new SettingUpdateEntity(0, 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)));
      var1.add(new SettingUpdateEntity(0, 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)));
      var1.add(new SettingUpdateEntity(0, 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)));
      var1.add(new SettingUpdateEntity(0, 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void personalized_instrument_settings_0x29() {
      ArrayList var2 = new ArrayList();
      int var1;
      if (mIsLeftEable) {
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) >= 1) {
            var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) - 1;
         } else {
            var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
         }

         var2.add(new SettingUpdateEntity(2, 0, var1));
      }

      if (mIsRightEable) {
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) >= 1) {
            var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) - 1;
         } else {
            var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
         }

         var2.add(new SettingUpdateEntity(2, 1, var1));
      }

      if (mIsRightEable || mIsLeftEable) {
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void power_tailgate_0x29() {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)));
      var2.add(new SettingUpdateEntity(4, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)));
      var2.add(new SettingUpdateEntity(4, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1)));
      int var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 3) >= 1) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 3) - 1;
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 3);
      }

      var2.add(new SettingUpdateEntity(4, 3, var1));
      var2.add(new SettingUpdateEntity(4, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1)));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void profiles_0x29() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(1, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)));
      var1.add(new SettingUpdateEntity(1, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)));
      var1.add(new SettingUpdateEntity(1, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)));
      var1.add(new SettingUpdateEntity(1, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)));
      var1.add(new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)));
      var1.add(new SettingUpdateEntity(1, 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)));
      var1.add(new SettingUpdateEntity(1, 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void realKeyClick(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick1(var2, var1, var3[2], var3[3]);
   }

   private String resolveEnergysaving(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11) {
      String var12;
      if (var1 == 0) {
         var12 = "20" + this.Zea0(var2) + "." + this.Zea0(var3) + "." + this.Zea0(var4) + "\b\b\b" + this.Zea0(var5) + ":" + this.Zea0(var6) + "\n" + CommUtil.getStrByResId(this.mContext, "_250_Energy_saving_level") + ":\b" + var7 + "\n" + CommUtil.getStrByResId(this.mContext, "_250_mileage") + ":\b" + this.Zea00((float)this.getMsbLsbResult(var8, var9) / 10.0F) + "km\n" + CommUtil.getStrByResId(this.mContext, "_250_consumption") + ":\b" + this.Zea00((float)this.getMsbLsbResult(var10, var11) / 10.0F) + "kwh/100km";
      } else {
         var12 = "20" + this.Zea0(var2) + "." + this.Zea0(var3) + "." + this.Zea0(var4) + "\b\b\b" + this.Zea0(var5) + ":" + this.Zea0(var6) + "\n" + CommUtil.getStrByResId(this.mContext, "_250_Energy_saving_level") + ":\b" + var7 + "\b\b\b" + CommUtil.getStrByResId(this.mContext, "_250_mileage") + ":\b" + this.Zea00((float)this.getMsbLsbResult(var8, var9) / 10.0F) + "km\b\b\b" + CommUtil.getStrByResId(this.mContext, "_250_consumption") + ":\b" + this.Zea00((float)this.getMsbLsbResult(var10, var11) / 10.0F) + "kwh/100km";
      }

      return var12;
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (64 == var1) {
         var2 = "LO";
      } else if (96 == var1) {
         var2 = "HI";
      } else if (65 <= var1 && 95 >= var1) {
         var2 = (float)(var1 - 64) * 0.5F + 16.0F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
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

   private String resplveMotoPower(int var1) {
      return var1 - 80 + "kw";
   }

   private void setAirData0x23() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
      String var2;
      if (this.mCanBusInfoInt[6] == 255) {
         var2 = "PM2.5: --";
      } else {
         var2 = "PM2.5: " + this.mCanBusInfoInt[6];
      }

      GeneralAirData.center_wheel = var2;
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      int var1 = this.mCanBusInfoInt[3];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 == 5) {
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

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void setAirData0x36() {
      this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
   }

   private void setCarInfo_0x7d() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      ArrayList var5;
      if (var1 != 0) {
         int[] var3;
         StringBuilder var4;
         StringBuilder var7;
         int[] var8;
         if (var1 != 3) {
            if (var1 != 4) {
               if (var1 != 6) {
                  if (var1 != 7) {
                     StringBuilder var6;
                     switch (var1) {
                        case 9:
                           var5 = new ArrayList();
                           var5.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[3] - 40 + " ℃"));
                           var5.add(new DriverUpdateEntity(2, 2, this.mCanBusInfoInt[4] - 40 + " ℃"));
                           var5.add(new DriverUpdateEntity(2, 3, this.mCanBusInfoInt[5] - 40 + " ℃"));
                           this.updateGeneralDriveData(var5);
                           this.updateDriveDataActivity((Bundle)null);
                           break;
                        case 10:
                           ArrayList var9 = new ArrayList();
                           var6 = new StringBuilder();
                           var8 = this.mCanBusInfoInt;
                           var9.add(new DriverUpdateEntity(2, 4, var6.append((var8[4] * 256 + var8[3]) / 4).append(" rpm").toString()));
                           this.updateGeneralDriveData(var9);
                           this.updateDriveDataActivity((Bundle)null);
                           break;
                        case 11:
                           ArrayList var10 = new ArrayList();
                           var6 = new StringBuilder();
                           var3 = this.mCanBusInfoInt;
                           var10.add(new DriverUpdateEntity(2, 5, var6.append((var3[4] * 256 + var3[3]) / 1000).append(" V").toString()));
                           this.updateGeneralDriveData(var10);
                           this.updateDriveDataActivity((Bundle)null);
                           break;
                        case 12:
                           var5 = new ArrayList();
                           var4 = new StringBuilder();
                           var3 = this.mCanBusInfoInt;
                           var5.add(new DriverUpdateEntity(3, 0, var4.append(var3[4] * 256 + var3[3]).append("").toString()));
                           var4 = new StringBuilder();
                           var3 = this.mCanBusInfoInt;
                           var5.add(new DriverUpdateEntity(3, 1, var4.append(var3[6] * 256 + var3[5]).append("").toString()));
                           var7 = new StringBuilder();
                           var8 = this.mCanBusInfoInt;
                           var5.add(new DriverUpdateEntity(3, 2, var7.append(var8[8] * 256 + var8[7]).append("").toString()));
                           var7 = new StringBuilder();
                           var8 = this.mCanBusInfoInt;
                           var5.add(new DriverUpdateEntity(3, 3, var7.append(var8[10] * 256 + var8[9]).append("").toString()));
                           var7 = new StringBuilder();
                           var8 = this.mCanBusInfoInt;
                           var5.add(new DriverUpdateEntity(3, 4, var7.append(var8[12] * 256 + var8[11]).append("").toString()));
                           this.updateGeneralDriveData(var5);
                           this.updateDriveDataActivity((Bundle)null);
                           break;
                        case 13:
                           var1 = DataHandleUtils.getIntFromByteWithBit(var2[3], 6, 2);
                           if (var1 != 1) {
                              if (var1 != 2) {
                                 if (var1 != 3) {
                                    GeneralDoorData.skyWindowOpenLevel = 0;
                                 } else {
                                    GeneralDoorData.skyWindowOpenLevel = 1;
                                 }
                              } else {
                                 GeneralDoorData.skyWindowOpenLevel = 0;
                              }
                           } else {
                              GeneralDoorData.skyWindowOpenLevel = 2;
                           }

                           if (mSkyLineSt != GeneralDoorData.skyWindowOpenLevel) {
                              this.updateDoorView(this.mContext);
                           }

                           mSkyLineSt = GeneralDoorData.skyWindowOpenLevel;
                     }
                  } else {
                     var5 = new ArrayList();
                     var7 = new StringBuilder();
                     var8 = this.mCanBusInfoInt;
                     var5.add(new DriverUpdateEntity(0, 4, var7.append((var8[4] * 256 + var8[3]) / 10).append(" L/100KM").toString()));
                     var4 = new StringBuilder();
                     var3 = this.mCanBusInfoInt;
                     var5.add(new DriverUpdateEntity(0, 5, var4.append((var3[6] * 256 + var3[5]) / 10).append("  L/100KM").toString()));
                     this.updateGeneralDriveData(var5);
                     this.updateDriveDataActivity((Bundle)null);
                  }
               } else {
                  GeneralDoorData.isShowSeatBelt = true;
                  GeneralDoorData.isSubShowSeatBelt = true;
                  GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
                  GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
                  if (mIsBelt != GeneralDoorData.isSeatBeltTie || mIsSubBelt != GeneralDoorData.isSubSeatBeltTie) {
                     this.updateDoorView(this.mContext);
                  }

                  mIsBelt = GeneralDoorData.isSeatBeltTie;
                  mIsSubBelt = GeneralDoorData.isSubSeatBeltTie;
               }
            } else {
               var5 = new ArrayList();
               var7 = new StringBuilder();
               var8 = this.mCanBusInfoInt;
               var5.add(new DriverUpdateEntity(0, 0, var7.append(var8[5] * 256 * 256 + var8[4] * 256 + var8[3]).append(" KM").toString()));
               var4 = new StringBuilder();
               var3 = this.mCanBusInfoInt;
               var5.add(new DriverUpdateEntity(0, 1, var4.append((var3[7] * 256 + var3[6]) / 10).append(" KM").toString()));
               var7 = new StringBuilder();
               var8 = this.mCanBusInfoInt;
               var5.add(new DriverUpdateEntity(0, 2, var7.append((var8[10] * 256 * 256 + var8[9] * 256 + var8[8]) / 10).append(" KM").toString()));
               var4 = new StringBuilder();
               var3 = this.mCanBusInfoInt;
               var5.add(new DriverUpdateEntity(0, 3, var4.append((var3[13] * 256 * 256 + var3[12] * 256 + var3[11]) / 10).append(" KM").toString()));
               this.updateGeneralDriveData(var5);
               this.updateDriveDataActivity((Bundle)null);
            }
         } else {
            var5 = new ArrayList();
            var7 = new StringBuilder();
            var8 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 0, var7.append((var8[4] * 256 + var8[3]) / 100).append(" KM/H").toString()));
            var4 = new StringBuilder();
            var3 = this.mCanBusInfoInt;
            var5.add(new DriverUpdateEntity(1, 1, var4.append((var3[6] * 256 + var3[5]) / 100).append(" KM/H").toString()));
            this.updateGeneralDriveData(var5);
            this.updateDriveDataActivity((Bundle)null);
         }
      } else {
         String var11 = this.getBytesString(this.mCanBusInfoByte);
         var5 = new ArrayList();
         var5.add(new DriverUpdateEntity(2, 0, var11));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setDoorData0x28() {
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen) {
         this.updateDoorView(this.mContext);
      }

      mIsFLDoorLast = GeneralDoorData.isLeftFrontDoorOpen;
      mIsFRDoorLast = GeneralDoorData.isRightFrontDoorOpen;
      mIsRLDoorLast = GeneralDoorData.isLeftRearDoorOpen;
      mIsRRDoorLast = GeneralDoorData.isRightRearDoorOpen;
      mIsFrontLast = GeneralDoorData.isFrontOpen;
      mIsBackLast = GeneralDoorData.isBackOpen;
      ArrayList var1 = new ArrayList();
      var1.add(new DriverUpdateEntity(2, 6, this.mCanBusInfoInt[3] + " %"));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setFrontRadar0x26() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationDataType2(4, var1[2], 5, var1[3], 5, var1[4], 4, var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setPanoramic_0x37() {
      if (this.isHaveCam360()) {
         int var1 = this.mCanBusInfoInt[2];
         boolean var3 = false;
         boolean var4 = false;
         boolean var6;
         if (var1 != 0) {
            var6 = true;
         } else {
            var6 = false;
         }

         Context var5 = this.mContext;
         boolean var2;
         if (var6) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.forceReverse(var5, var2);
         if (var6) {
            ArrayList var7 = new ArrayList();
            var1 = mDifferentId;
            if (var1 == 5) {
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 5 && var1 != 6 && var1 != 7 && var1 != 8) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               mIs360Full = var2;
               var7.add(new PanoramicBtnUpdateEntity(0, mIs360Full));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 1 && var1 != 5) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(1, var2));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 4 && var1 != 8) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(2, var2));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 2 && var1 != 6) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               label147: {
                  var7.add(new PanoramicBtnUpdateEntity(3, var2));
                  var1 = this.mCanBusInfoInt[2];
                  if (var1 != 3) {
                     var2 = var4;
                     if (var1 != 7) {
                        break label147;
                     }
                  }

                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(4, var2));
            } else if (var1 == 7) {
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 5 && var1 != 6 && var1 != 7 && var1 != 8) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               mIs360Full = var2;
               var7.add(new PanoramicBtnUpdateEntity(0, mIs360Full));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 1 && var1 != 5) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(1, var2));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 4 && var1 != 8) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(2, var2));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 2 && var1 != 6) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(3, var2));
               var1 = this.mCanBusInfoInt[2];
               if (var1 != 3 && var1 != 7) {
                  var2 = false;
               } else {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(4, var2));
               if (this.mCanBusInfoInt[2] == 17) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(6, var2));
               if (this.mCanBusInfoInt[2] == 18) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(7, var2));
               if (this.mCanBusInfoInt[2] == 19) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(8, var2));
               if (this.mCanBusInfoInt[2] == 20) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(9, var2));
               if (this.mCanBusInfoInt[2] == 21) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(10, var2));
               if (this.mCanBusInfoInt[2] == 22) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(11, var2));
               if (this.mCanBusInfoInt[2] == 23) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               var7.add(new PanoramicBtnUpdateEntity(12, var2));
               var2 = var3;
               if (this.mCanBusInfoInt[2] == 24) {
                  var2 = true;
               }

               var7.add(new PanoramicBtnUpdateEntity(13, var2));
            } else {
               var7 = null;
            }

            GeneralParkData.dataList = var7;
            this.updateParkUi((Bundle)null, this.mContext);
            mLast360st = this.mCanBusInfoInt[2];
         }
      }
   }

   private void setRearRadar0x24() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[2];
      int var1 = var3[3];
      RadarInfoUtil.setRearRadarLocationDataType2(4, var2, 5, var1, 5, var1, 4, var3[4]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setRearRadar0x25() {
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 0;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationDataType2(4, var1[2], 5, var1[3], 5, var1[4], 4, var1[5]);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setSetting0x27() {
      m0x27SettingData = this.mCanBusInfoInt[2];
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "str_250_0_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)));
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "str_250_0_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setTrack() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 5600, 16);
      LogUtil.showLog("trackAngle:" + GeneralParkData.trackAngle);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void vehicle_settings_0x29() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(5, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2)));
      var1.add(new SettingUpdateEntity(5, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)));
      var1.add(new SettingUpdateEntity(5, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1)));
      var1.add(new SettingUpdateEntity(5, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1)));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.initSettingsItem(this.getUiMgr(var1).getSettingUiSet(var1));
      this.initDriveItem(this.getUiMgr(var1).getDriverDataPageUiSet(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 32) {
         if (var3 != 33) {
            if (var3 != 48) {
               if (var3 != 125) {
                  if (var3 != 127) {
                     if (var3 != 54) {
                        if (var3 != 55) {
                           if (var3 != 64) {
                              if (var3 != 65) {
                                 switch (var3) {
                                    case 35:
                                       if (this.isAirMsgRepeat(var2)) {
                                          return;
                                       }

                                       this.setAirData0x23();
                                       break;
                                    case 36:
                                       this.setRearRadar0x24();
                                       break;
                                    case 37:
                                       this.setRearRadar0x25();
                                       break;
                                    case 38:
                                       this.setFrontRadar0x26();
                                       break;
                                    case 39:
                                       this.setSetting0x27();
                                       break;
                                    case 40:
                                       this.setDoorData0x28();
                                       break;
                                    case 41:
                                       this.original_car_set_0x29();
                                       this.profiles_0x29();
                                       this.and_ambient_lighting_brightness_meter_0x29();
                                       this.power_tailgate_0x29();
                                       this.vehicle_settings_0x29();
                                       this.personalized_instrument_settings_0x29();
                                       this.SettingData8_0x29();
                                       this.SettingData9_0x29();
                                 }
                              } else {
                                 this.SetEnergysavingdriving();
                              }
                           } else {
                              this.SetEnergyinformation0x40();
                           }
                        } else {
                           this.setPanoramic_0x37();
                        }
                     } else {
                        this.setAirData0x36();
                     }
                  } else {
                     this.setVersionInfo();
                  }
               } else {
                  this.setCarInfo_0x7d();
               }
            } else {
               this.setTrack();
            }
         } else {
            this.keyControl0x21();
         }
      } else {
         this.keyControl0x20();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte)(var1 - 2000), (byte)var3, (byte)var4, (byte)var8, (byte)var6, (byte)var7});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      mDifferentId = this.getCurrentCanDifferentId();
      int var2 = this.getCurrentCanDifferentId();
      if (var2 != 1) {
         if (var2 != 3) {
            if (var2 != 4) {
               if (var2 != 10) {
                  if (var2 == 11) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -123, 9});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 8});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
      }

      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            while(true) {
               try {
                  sleep(1500L);
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 125, 10});
               } catch (Exception var2) {
                  var2.printStackTrace();
               }
            }
         }
      }).start();
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 125, 0});
   }

   void initRadarDisp(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "__250_SAVE_RADAR_DISP", 0);
      ArrayList var4 = new ArrayList();
      int var3 = mDifferentId;
      if (var3 == 4) {
         var4.add(new SettingUpdateEntity(0, 3, var2));
      } else if (var3 == 5 || var3 == 7) {
         var4.add(new SettingUpdateEntity(5, 4, var2));
      }

      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   boolean isHaveCam360() {
      int var1 = mDifferentId;
      return var1 == 4 || var1 == 5 || var1 == 7;
   }

   void updateDashBoardSetEable_0x29() {
      ArrayList var2 = new ArrayList();
      boolean var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      mIsLeftEable = var1;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      mIsRightEable = var1;
      var2.add((new SettingUpdateEntity(2, 0, (Object)null)).setEnable(mIsLeftEable));
      var2.add((new SettingUpdateEntity(2, 1, (Object)null)).setEnable(mIsRightEable));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, String var2, int var3, int var4, int var5) {
      SharePreUtil.setIntValue(var1, var2, var5);
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
