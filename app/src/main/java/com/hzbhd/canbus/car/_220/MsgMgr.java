package com.hzbhd.canbus.car._220;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   protected static final String CAN_220_SAVE_360 = "_220_SAVE_360";
   private static boolean isLastLongClick;
   private static int lastPanelKeyVal;
   private static int lastPanelSt;
   private static int lastSwcKey;
   private static int lastSwcSt;
   private static int longClickCount;
   private static int mDifferentId;
   private static boolean mIsBackLast;
   private static boolean mIsBelt;
   private static boolean mIsFLDoorLast;
   private static boolean mIsFRDoorLast;
   private static boolean mIsFrontLast;
   private static boolean mIsHandBreak;
   private static boolean mIsRLDoorLast;
   private static boolean mIsRRDoorLast;
   protected static boolean mLast360st;
   protected static int mNewEnergyData;
   protected static int mNewEnergyEndTime;
   protected static int mNewEnergyStartTime;
   private int eachId;
   private boolean isCallIn = false;
   private boolean isHangUp = false;
   LocationListener locationListener = new LocationListener(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLocationChanged(Location var1) {
         if (var1 != null) {
            this.this$0.sndCurCompassSt(var1);
         }

      }

      public void onProviderDisabled(String var1) {
      }

      public void onProviderEnabled(String var1) {
         this.this$0.mLocationManager.getLastKnownLocation(var1);
      }

      public void onStatusChanged(String var1, int var2, Bundle var3) {
         Log.i("onStatusChanged", "onStatusChanged");
      }
   };
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private LocationManager mLocationManager;
   private MyPanoramicView mPanoramicView;
   private UiMgr mUiMgr;

   // $FF: synthetic method
   public static int $r8$lambda$uBsCIBAbAW1p84hPJ6ULJah0SPE(Integer var0) {
      return var0;
   }

   private String DoorOpenPosition(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131759322);
      } else if (var1 == 100) {
         var2 = this.mContext.getResources().getString(2131759323);
      } else {
         var2 = var1 + "%";
      }

      return var2;
   }

   private String ElectricTailgate(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131759513);
      } else {
         var2 = this.mContext.getResources().getString(2131759512);
      }

      return var2;
   }

   private String ElectricTailgatePosition(boolean var1) {
      String var2;
      if (var1) {
         var2 = this.mContext.getResources().getString(2131759344);
      } else {
         var2 = this.mContext.getResources().getString(2131759314);
      }

      return var2;
   }

   private void Intelligent_parking_assistance0x52() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 57) {
         if (var1 == 58) {
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_parking_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_parking_assistance", "_220_Turn_signals_activate_panorama"), this.getSwapVal(this.mCanBusInfoInt[3])));
         }
      } else {
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_parking_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_parking_assistance", "_220_P_mode_exit"), this.getSwapVal(this.mCanBusInfoInt[3])));
      }

      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private String Skylight(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131759350);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131759352);
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131759354);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131759353);
      } else {
         var2 = this.mContext.getResources().getString(2131759351);
      }

      return var2;
   }

   private String SunShade(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131759358);
      } else if (var1 == 1) {
         var2 = this.mContext.getResources().getString(2131759360);
      } else if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131759361);
      } else {
         var2 = this.mContext.getResources().getString(2131759359);
      }

      return var2;
   }

   private void air_conditioning_settings_0x52() {
      ArrayList var5 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 2) {
         int var2;
         int var3;
         int var4;
         if (var1 != 3) {
            if (var1 != 4) {
               if (var1 != 24) {
                  if (var1 != 37) {
                     if (var1 != 38) {
                        if (var1 != 59) {
                           if (var1 == 60) {
                              var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_Air_conditioning_self_drying"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           }
                        } else {
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_Unlock_the_ventilation"), this.getSwapVal(this.mCanBusInfoInt[3])));
                        }
                     } else {
                        var4 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
                        var3 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_automatic_mode_wind");
                        var2 = this.mCanBusInfoInt[3];
                        var1 = var2;
                        if (var2 >= 1) {
                           var1 = var2 - 1;
                        }

                        var5.add(new SettingUpdateEntity(var4, var3, var1));
                     }
                  } else {
                     var4 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
                     var3 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_air_quality_sensor");
                     var2 = this.mCanBusInfoInt[3];
                     var1 = var2;
                     if (var2 >= 1) {
                        var1 = var2 - 1;
                     }

                     var5.add(new SettingUpdateEntity(var4, var3, var1));
                  }
               } else {
                  var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_negative_ion_mode"), this.getSwapVal(this.mCanBusInfoInt[3])));
               }
            } else {
               var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
               var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_airconditioned_comfort_curve_settings");
               var2 = this.mCanBusInfoInt[3];
               var1 = var2;
               if (var2 >= 1) {
                  var1 = var2 - 1;
               }

               var5.add(new SettingUpdateEntity(var3, var4, var1));
            }
         } else {
            var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
            var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_auto_outer_loop_control");
            var2 = this.mCanBusInfoInt[3];
            var1 = var2;
            if (var2 >= 1) {
               var1 = var2 - 1;
            }

            var5.add(new SettingUpdateEntity(var3, var4, var1));
         }
      } else {
         var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_auto_when_the_compressor_status"), this.getSwapVal(this.mCanBusInfoInt[3])));
      }

      if (var5.size() != 0) {
         this.updateGeneralSettingData(var5);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void body_and_accessories_0x52() {
      ArrayList var5 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      int var2;
      int var3;
      int var4;
      if (var1 != 39) {
         if (var1 != 40) {
            if (var1 != 44) {
               switch (var1) {
                  case 12:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_remote_unlock"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 13:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_speed_lock"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 14:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_auto_unlock"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 15:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_remote_left_front_window_and_skylight"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 16:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_front_wiper_maintenance_functions"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 17:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_rear_wiper_automatic_reverse_function"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  default:
                     switch (var1) {
                        case 27:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_automatic_folding_exterior_rear_view_mirror"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 28:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_unlock_lock_tone"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 29:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_intelligent_active_lock"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 30:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_smart_unlock_initiative"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 31:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_manually_adjustable_exterior_mirror_angle"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 32:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_automatically_adjusting_the_angle_of_the_outer_mirror"), this.mCanBusInfoInt[3] - 1));
                           break;
                        default:
                           switch (var1) {
                              case 51:
                                 var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Key_forget_reminder"), this.getSwapVal(this.mCanBusInfoInt[3])));
                                 break;
                              case 52:
                                 var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories");
                                 var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Low_speed_beep");
                                 var2 = this.mCanBusInfoInt[3];
                                 var1 = var2;
                                 if (var2 >= 1) {
                                    var1 = var2 - 1;
                                 }

                                 var5.add(new SettingUpdateEntity(var3, var4, var1));
                                 break;
                              case 53:
                                 var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Auto_Close_Windows"), this.getSwapVal(this.mCanBusInfoInt[3])));
                                 break;
                              case 54:
                                 var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Wiper_maintenance_mode"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           }
                     }
               }
            } else {
               var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Wireless_charging"), this.getSwapVal(this.mCanBusInfoInt[3])));
            }
         } else {
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Automatic_Wiper_Function"), this.getSwapVal(this.mCanBusInfoInt[3])));
         }
      } else {
         var4 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories");
         var3 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_open_trunk_induction");
         var2 = this.mCanBusInfoInt[3];
         var1 = var2;
         if (var2 >= 1) {
            var1 = var2 - 1;
         }

         var5.add(new SettingUpdateEntity(var4, var3, var1));
      }

      if (var5.size() != 0) {
         this.updateGeneralSettingData(var5);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void driving_assistance_0x52() {
      ArrayList var5 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 34) {
         if (var1 != 35) {
            if (var1 != 56) {
               int var2;
               int var3;
               int var4;
               switch (var1) {
                  case 7:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_speeding_alert"), this.mCanBusInfoInt[3]));
                     break;
                  case 8:
                     var4 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                     var3 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_the_instrument_cluster_alarm_volume");
                     var2 = this.mCanBusInfoInt[3];
                     var1 = var2;
                     if (var2 >= 1) {
                        var1 = var2 - 1;
                     }

                     var5.add(new SettingUpdateEntity(var4, var3, var1));
                     break;
                  case 9:
                     var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_remote_poweron_time"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                     break;
                  case 10:
                     var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_remote_start_time"), this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                     break;
                  case 11:
                     var4 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                     var3 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_steering_modes");
                     var2 = this.mCanBusInfoInt[3];
                     var1 = var2;
                     if (var2 >= 1) {
                        var1 = var2 - 1;
                     }

                     var5.add(new SettingUpdateEntity(var4, var3, var1));
                     break;
                  default:
                     switch (var1) {
                        case 45:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Active_brake_assist"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 46:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Cruise_mode"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 47:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Front_collision_warning"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 48:
                           var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                           var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Front_collision_warning_distance");
                           var2 = this.mCanBusInfoInt[3];
                           var1 = var2;
                           if (var2 >= 1) {
                              var1 = var2 - 1;
                           }

                           var5.add(new SettingUpdateEntity(var3, var4, var1));
                           break;
                        case 49:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Lane_assist_switch"), this.getSwapVal(this.mCanBusInfoInt[3])));
                           break;
                        case 50:
                           var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                           var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Lane_assist");
                           var2 = this.mCanBusInfoInt[3];
                           var1 = var2;
                           if (var2 >= 1) {
                              var1 = var2 - 1;
                           }

                           var5.add(new SettingUpdateEntity(var3, var4, var1));
                     }
               }
            } else {
               var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Memorize_the_current_driving_mode"), this.getSwapVal(this.mCanBusInfoInt[3])));
            }
         } else {
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_and_left_auxiliary_line"), this.getSwapVal(this.mCanBusInfoInt[3])));
         }
      } else {
         var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_and_the_right_auxiliary_line"), this.getSwapVal(this.mCanBusInfoInt[3])));
      }

      if (var5.size() != 0) {
         this.updateGeneralSettingData(var5);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private String getDoorStatus(int var1) {
      String var2 = this.mContext.getResources().getString(2131759350);
      if (var1 == 2) {
         var2 = this.mContext.getResources().getString(2131759352);
      } else if (var1 == 3) {
         var2 = this.mContext.getResources().getString(2131759350);
      } else if (var1 == 14) {
         var2 = this.mContext.getResources().getString(2131759353);
      } else if (var1 == 15) {
         var2 = this.mContext.getResources().getString(2131759351);
      }

      return var2;
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

   private String getFunction6State(boolean var1) {
      return var1 ? this.mContext.getString(2131759379) : this.mContext.getString(2131759378);
   }

   private String getFunction7State(boolean var1) {
      return var1 ? this.mContext.getString(2131759382) : this.mContext.getString(2131759381);
   }

   private String getFunction8State(int var1) {
      switch (var1) {
         case 1:
            return this.mContext.getString(2131759385);
         case 2:
            return this.mContext.getString(2131759396);
         case 3:
            return this.mContext.getString(2131759407);
         case 4:
            return this.mContext.getString(2131759409);
         case 5:
            return this.mContext.getString(2131759410);
         case 6:
            return this.mContext.getString(2131759411);
         case 7:
            return this.mContext.getString(2131759412);
         case 8:
            return this.mContext.getString(2131759413);
         case 9:
            return this.mContext.getString(2131759414);
         case 10:
            return this.mContext.getString(2131759386);
         case 11:
            return this.mContext.getString(2131759387);
         case 12:
            return this.mContext.getString(2131759388);
         case 13:
            return this.mContext.getString(2131759389);
         case 14:
            return this.mContext.getString(2131759390);
         case 15:
            return this.mContext.getString(2131759391);
         case 16:
            return this.mContext.getString(2131759392);
         case 17:
            return this.mContext.getString(2131759393);
         case 18:
            return this.mContext.getString(2131759394);
         case 19:
            return this.mContext.getString(2131759395);
         case 20:
            return this.mContext.getString(2131759397);
         case 21:
            return this.mContext.getString(2131759398);
         case 22:
            return this.mContext.getString(2131759399);
         case 23:
            return this.mContext.getString(2131759400);
         case 24:
            return this.mContext.getString(2131759401);
         case 25:
            return this.mContext.getString(2131759402);
         case 26:
            return this.mContext.getString(2131759403);
         case 27:
            return this.mContext.getString(2131759404);
         case 28:
            return this.mContext.getString(2131759405);
         case 29:
            return this.mContext.getString(2131759406);
         case 30:
            return this.mContext.getString(2131759408);
         default:
            return this.mContext.getString(2131759384);
      }
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private int getSwapVal(int var1) {
      byte var2 = 1;
      byte var3;
      if (var1 >= 1 && var1 != 2 && var1 == 1) {
         var3 = var2;
      } else {
         var3 = 0;
      }

      return var3;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private boolean isHaveCompass() {
      return this.getCurrentCanDifferentId() != 9 && this.getCurrentCanDifferentId() != 7 && this.getCurrentCanDifferentId() != 16;
   }

   private boolean isHaveNewEnergy() {
      return this.getCurrentCanDifferentId() != 11 && this.getCurrentCanDifferentId() != 13 && this.getCurrentCanDifferentId() != 15;
   }

   private boolean isHaveRearAir() {
      if (this.getCurrentCanDifferentId() != 7 && this.getCurrentCanDifferentId() != 16 && this.getCurrentCanDifferentId() != 25 && this.getCurrentCanDifferentId() != 17) {
         int var1 = this.eachId;
         if (var1 != 1 && var1 != 6) {
            return false;
         }
      }

      return true;
   }

   private void keySwc(int var1) {
      switch (var1) {
         case 17:
            this.panelBtnKeyClick(2);
            break;
         case 18:
            this.panelBtnKeyClick(48);
            break;
         case 19:
            this.panelBtnKeyClick(47);
            break;
         case 20:
            this.panelBtnKeyClick(7);
            break;
         case 21:
            this.panelBtnKeyClick(8);
            break;
         case 22:
            this.panelBtnKeyClick(3);
            break;
         case 23:
            this.panelBtnKeyClick(49);
            break;
         case 24:
            this.panelBtnKeyClick(50);
            break;
         default:
            switch (var1) {
               case 48:
                  this.panelBtnKeyClick(14);
               case 49:
                  this.panelBtnKeyClick(15);
                  break;
               case 50:
                  this.panelBtnKeyClick(187);
            }
      }

   }

   private void konbKeySelLeft(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         this.panelBtnKeyClick(47);
      }

   }

   private void konbKeySelRight(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         this.panelBtnKeyClick(48);
      }

   }

   private void konbVolDn(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         this.panelBtnKeyClick(8);
      }

   }

   private void konbVolUp(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         this.panelBtnKeyClick(7);
      }

   }

   private void language_settings_0x52() {
      if (this.mCanBusInfoInt[2] == 1) {
         ArrayList var5 = new ArrayList();
         int var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_language_settings");
         int var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_language_settings", "_220_language");
         int var2 = this.mCanBusInfoInt[3];
         int var1 = var2;
         if (var2 >= 1) {
            var1 = var2 - 1;
         }

         var5.add(new SettingUpdateEntity(var3, var4, var1));
         this.updateGeneralSettingData(var5);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void lighting_control_0x52() {
      ArrayList var5 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 33) {
         if (var1 != 36) {
            if (var1 != 55) {
               int var2;
               int var3;
               int var4;
               switch (var1) {
                  case 18:
                     var4 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control");
                     var3 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_i_went_home_with");
                     var2 = this.mCanBusInfoInt[3];
                     var1 = var2;
                     if (var2 >= 1) {
                        var1 = var2 - 1;
                     }

                     var5.add(new SettingUpdateEntity(var4, var3, var1));
                     break;
                  case 19:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_fog_lights_steering_assist"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 20:
                     var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_daytime_running_lights"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     break;
                  case 21:
                     var3 = this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control");
                     var4 = this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_auto_light_sensitivity");
                     var2 = this.mCanBusInfoInt[3];
                     var1 = var2;
                     if (var2 >= 1) {
                        var1 = var2 - 1;
                     }

                     var5.add(new SettingUpdateEntity(var3, var4, var1));
                     break;
                  default:
                     switch (var1) {
                        case 41:
                           var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Ambient_light_brightness"), this.mCanBusInfoInt[3] + 1)).setProgress(this.mCanBusInfoInt[3]));
                           break;
                        case 42:
                           var5.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Ambient_light_Color"), this.mCanBusInfoInt[3] + 1)).setProgress(this.mCanBusInfoInt[3]));
                           break;
                        case 43:
                           var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Intelligent_high_beam"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     }
               }
            } else {
               var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Delayed_headlight_off"), this.getSwapVal(this.mCanBusInfoInt[3])));
            }
         } else {
            var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_ambient_light_control"), this.getSwapVal(this.mCanBusInfoInt[3])));
         }
      } else {
         var5.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_intelligent_welcome_light_function"), this.getSwapVal(this.mCanBusInfoInt[3])));
      }

      if (var5.size() != 0) {
         this.updateGeneralSettingData(var5);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void panelBtnKeyClick(int var1) {
      this.realKeyClick(this.mContext, var1);
   }

   private void realKeyClick(int var1) {
      Context var3 = this.mContext;
      int[] var2 = this.mCanBusInfoInt;
      this.realKeyClick1(var3, var1, var2[2], var2[3]);
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (1 == var1) {
         var2 = "LO";
      } else if (57 == var1) {
         var2 = "HI";
      } else if (3 <= var1 && 55 >= var1) {
         var2 = (double)((float)((var1 - 3) / 2) * 0.5F) + 18.5 + this.getTempUnitC(this.mContext);
      } else {
         var2 = "--";
      }

      return var2;
   }

   private String resoveTempLev(int var1) {
      return var1 >= 1 && var1 <= 7 ? "LEV: " + var1 : "--";
   }

   private void seat_set_0x52() {
      ArrayList var2 = new ArrayList();
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 5) {
         if (var1 != 6) {
            if (var1 != 22) {
               if (var1 != 23) {
                  if (var1 != 25) {
                     if (var1 == 26) {
                        var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_smart_key_automatic_identification_seat"), this.getSwapVal(this.mCanBusInfoInt[3])));
                     }
                  } else {
                     var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_seat_welcome_light"), this.getSwapVal(this.mCanBusInfoInt[3])));
                  }
               } else {
                  var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_right_seat_heat"), this.strHeatingLev(this.mContext)[this.mCanBusInfoInt[3]])).setValueStr(true));
               }
            } else {
               var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_left_seat_heat"), this.strHeatingLev(this.mContext)[this.mCanBusInfoInt[3]])).setValueStr(true));
            }
         } else {
            var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_automatic_heating_passenger_seat"), this.getSwapVal(this.mCanBusInfoInt[3])));
         }
      } else {
         var2.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_automatic_driving_seat_heating"), this.getSwapVal(this.mCanBusInfoInt[3])));
      }

      if (var2.size() != 0) {
         this.updateGeneralSettingData(var2);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void set0x14BackLightInfo() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 255) {
         switch (var1) {
            case 0:
               this.setBacklightLevel(1);
               break;
            case 1:
               this.setBacklightLevel(1);
               break;
            case 2:
               this.setBacklightLevel(2);
               break;
            case 3:
               this.setBacklightLevel(2);
               break;
            case 4:
               this.setBacklightLevel(3);
               break;
            case 5:
               this.setBacklightLevel(3);
               break;
            case 6:
               this.setBacklightLevel(4);
               break;
            case 7:
               this.setBacklightLevel(4);
         }
      } else {
         this.setBacklightLevel(5);
      }

   }

   private void set0x54newEnergyInfo() {
      if (this.eachId == 2) {
         ArrayList var3 = new ArrayList();
         var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function1"), this.mCanBusInfoInt[3] + ":" + this.mCanBusInfoInt[2]));
         int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
         int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function2");
         StringBuilder var5 = new StringBuilder();
         int[] var4 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(var1, var2, var5.append(var4[5] * 256 + var4[4]).append("V").toString()));
         var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
         var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function3");
         var5 = new StringBuilder();
         var4 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(var1, var2, var5.append((double)(var4[7] * 256 + var4[6]) * 0.1).append("%").toString()));
         var2 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
         var1 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function4");
         StringBuilder var6 = new StringBuilder();
         int[] var7 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(var2, var1, var6.append((double)(var7[9] * 256 + var7[8]) * 0.1).append("A").toString()));
         var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
         var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function5");
         var5 = new StringBuilder();
         var4 = this.mCanBusInfoInt;
         var3.add(new DriverUpdateEntity(var1, var2, var5.append(var4[11] * 256 + var4[10]).append("V").toString()));
         var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function6"), this.getFunction6State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]))));
         var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function7"), this.getFunction7State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]))));
         var3.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function8"), this.getFunction8State(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 5))));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
      }
   }

   private void setAirData0x10() {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
      GeneralAirData.ion = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralAirData.threeZone = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         GeneralAirData.front_left_temperature = this.resoveTempLev(var1);
         GeneralAirData.front_right_temperature = this.resoveTempLev(this.mCanBusInfoInt[2]);
      } else {
         GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(var2[5]);
         GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
      }

      this.updateAirActivity(this.mContext, 1001);
   }

   private void setCarIdCmd() {
      switch (this.getCurrentCanDifferentId()) {
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 1});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 2});
            break;
         case 12:
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 4});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 3});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 5});
         case 16:
         case 21:
         default:
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 6});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 7});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 8});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 9});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 10});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 11});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 12});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 13});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 14});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 15});
      }

   }

   private void setDoorData0x24() {
      GeneralDoorData.isShowHandBrake = true;
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
      if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen || mIsHandBreak != GeneralDoorData.isHandBrakeUp) {
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

   private void setFrontRadar() {
      int var2 = this.mCanBusInfoInt[2];
      int var1 = 0;
      int[] var3;
      if (var2 == 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.mDisableData2 = this.mCanBusInfoInt[3] + 1;
         var3 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(var3[3], var3[4] + 1, var3[5] + 1, var3[6] + 1, var3[7] + 1);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         ArrayList var4;
         for(var4 = new ArrayList(); var1 < 25; ++var1) {
            var4.add(var1);
         }

         for(var1 = 128; var1 <= 255; ++var1) {
            var4.add(var1);
         }

         GeneralParkData.radar_distance_disable = Arrays.stream((Integer[])var4.toArray(new Integer[var4.size()])).mapToInt(new MsgMgr$$ExternalSyntheticLambda0()).toArray();
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var3 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarDistanceData(var3[4], var3[5] + 1, var3[6] + 1, var3[7] + 1);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setIntelligentPerceptionSystemInfo0x55() {
      if (this.eachId == 8) {
         ArrayList var1 = new ArrayList();
         switch (this.mCanBusInfoInt[2]) {
            case 1:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Intelligent_Perception_System"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 2:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Distraction_Reminder"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 3:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Fatigue_Testing"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 4:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Ventilate_While_Smoking"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 5:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Decrease_The_Volume_During_A_Call"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 6:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Sight_Bright_Screen"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 7:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Gestures_To_Cut_The_Song"), this.getSwapVal(this.mCanBusInfoInt[3])));
               break;
            case 8:
               var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Convenient_For_Car"), this.getSwapVal(this.mCanBusInfoInt[3])));
         }

         this.updateGeneralSettingData(var1);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void setNewEnergyInfo_0x53() {
      int[] var5 = this.mCanBusInfoInt;
      int var2 = var5[2];
      mNewEnergyStartTime = var2 << 8 | var5[3];
      int var1 = var5[4];
      mNewEnergyEndTime = var5[5] | var1 << 8;
      var2 = DataHandleUtils.getIntFromByteWithBit(var2, 0, 3) << 2 & 255 | DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
      var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 6);
      int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3) << 2 | DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
      int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 6);
      ArrayList var7 = new ArrayList();
      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 0, var2)).setProgress(var2));
      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 1, var1)).setProgress(var1));
      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 2, var3)).setProgress(var3));
      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 3, var4)).setProgress(var4));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 6, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 7, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 8, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 9, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 10, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 11, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1)));
      int[] var6 = this.mCanBusInfoInt;
      mNewEnergyData = var6[6];
      var1 = DataHandleUtils.getIntFromByteWithBit(var6[7], 0, 3);
      var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 5);
      var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 6);
      var6 = this.mCanBusInfoInt;
      if (var6[7] == 0 && var6[8] == 0) {
         var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_energy_set", "_220_from_the_last_charging_time"), "--" + this.mContext.getResources().getString(2131770118) + "--" + this.mContext.getResources().getString(2131770210) + "--" + this.mContext.getResources().getString(2131770225))).setValueStr(true));
      } else {
         var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_energy_set", "_220_from_the_last_charging_time"), var1 + this.mContext.getResources().getString(2131770118) + var3 + this.mContext.getResources().getString(2131770210) + var2 + this.mContext.getResources().getString(2131770225))).setValueStr(true));
      }

      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 14, this.mContext.getResources().getString(2131759452) + " " + this.mCanBusInfoInt[9])).setValueStr(true));
      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 15, this.mCanBusInfoInt[10] + "")).setValueStr(true));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 16, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 17, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1)));
      var7.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 18, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11])));
      var7.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 19, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 4)));
      if (var7.size() != 0) {
         this.updateGeneralSettingData(var7);
         this.updateSettingActivity((Bundle)null);
      }

   }

   private void setPanelBtnInfo() {
      int var1;
      int[] var2;
      if (lastPanelSt == 1) {
         var1 = longClickCount + 1;
         longClickCount = var1;
         if (var1 >= 5) {
            var2 = this.mCanBusInfoInt;
            if (var2[3] == 0 && var2[2] == 9) {
               isLastLongClick = true;
               this.panelBtnKeyClick(134);
               longClickCount = 0;
            }
         }

         if (this.mCanBusInfoInt[3] == 0 && !isLastLongClick) {
            var1 = lastPanelKeyVal;
            switch (var1) {
               case 1:
                  this.panelBtnKeyClick(134);
                  break;
               case 2:
                  this.panelBtnKeyClick(31);
                  break;
               case 3:
                  this.panelBtnKeyClick(52);
                  break;
               case 4:
                  this.panelBtnKeyClick(2);
                  break;
               case 5:
                  this.panelBtnKeyClick(129);
                  break;
               case 6:
                  this.panelBtnKeyClick(47);
                  break;
               case 7:
                  this.panelBtnKeyClick(48);
                  break;
               case 8:
                  this.panelBtnKeyClick(58);
                  break;
               case 9:
                  this.panelBtnKeyClick(3);
                  break;
               case 10:
                  this.panelBtnKeyClick(128);
                  break;
               case 11:
                  this.panelBtnKeyClick(75);
                  break;
               case 12:
                  this.panelBtnKeyClick(50);
                  break;
               case 13:
                  this.panelBtnKeyClick(14);
                  break;
               case 14:
                  this.panelBtnKeyClick(187);
                  break;
               default:
                  switch (var1) {
                     case 16:
                        this.panelBtnKeyClick(7);
                        break;
                     case 17:
                        this.panelBtnKeyClick(8);
                        break;
                     case 18:
                        this.panelBtnKeyClick(45);
                        break;
                     case 19:
                        this.panelBtnKeyClick(46);
                        break;
                     default:
                        switch (var1) {
                           case 33:
                              this.panelBtnKeyClick(33);
                              break;
                           case 34:
                              this.panelBtnKeyClick(34);
                              break;
                           case 35:
                              this.panelBtnKeyClick(35);
                              break;
                           case 36:
                              this.panelBtnKeyClick(36);
                              break;
                           case 37:
                              this.panelBtnKeyClick(37);
                              break;
                           case 38:
                              this.panelBtnKeyClick(38);
                              break;
                           case 39:
                              this.forceReverse(this.mContext, false);
                              this.panelBtnKeyClick(75);
                              break;
                           case 40:
                              this.panelBtnKeyClick(75);
                              break;
                           case 41:
                              this.panelBtnKeyClick(14);
                              break;
                           case 42:
                              this.panelBtnKeyClick(187);
                              break;
                           case 43:
                              this.realKeyClick(152);
                              break;
                           case 44:
                              this.panelBtnKeyClick(17);
                              break;
                           case 45:
                              this.panelBtnKeyClick(39);
                              break;
                           case 46:
                              this.panelBtnKeyClick(40);
                              break;
                           case 47:
                              Context var3 = this.mContext;
                              this.startSettingActivity(var3, this.getUiMgr(var3).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), 0);
                        }
                  }
            }

            lastPanelKeyVal = 0;
         }
      } else {
         longClickCount = 0;
         isLastLongClick = false;
      }

      var2 = this.mCanBusInfoInt;
      if (var2[3] >= 1) {
         switch (var2[2]) {
            case 16:
               this.konbVolUp(lastPanelSt);
               break;
            case 17:
               this.konbVolDn(lastPanelSt);
               break;
            case 18:
               this.konbKeySelRight(lastPanelSt);
               break;
            case 19:
               this.konbKeySelLeft(lastPanelSt);
         }
      }

      var2 = this.mCanBusInfoInt;
      var1 = var2[3];
      lastPanelSt = var1;
      if (var1 != 0) {
         lastPanelKeyVal = var2[2];
      }

   }

   private void setParkAssistance0x40() {
      if (this.getCurrentCanDifferentId() == 3) {
         MyPanoramicView var1 = (MyPanoramicView)UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
         this.mPanoramicView = var1;
         var1.camSt = this.mCanBusInfoInt[2];
         this.runOnUi(new AbstractMsgMgr.CallBackInterface(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void callback() {
               this.this$0.mPanoramicView.updateUi();
            }
         });
      }
   }

   private void setRearAir() {
      GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
      GeneralAirData.rear_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[3]);
      this.updateAirActivity(this.mContext, 1002);
   }

   private void setRearRadar() {
      int var2 = this.mCanBusInfoInt[2];
      int var1 = 0;
      int[] var3;
      if (var2 == 0) {
         GeneralParkData.isShowDistanceNotShowLocationUi = false;
         RadarInfoUtil.mMinIsClose = true;
         RadarInfoUtil.mDisableData = 255;
         RadarInfoUtil.mDisableData2 = this.mCanBusInfoInt[3] + 1;
         var3 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(var3[3], var3[4] + 1, var3[5] + 1, var3[6] + 1, var3[7] + 1);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      } else {
         ArrayList var4;
         for(var4 = new ArrayList(); var1 < 25; ++var1) {
            var4.add(var1);
         }

         for(var1 = 128; var1 <= 255; ++var1) {
            var4.add(var1);
         }

         GeneralParkData.radar_distance_disable = Arrays.stream((Integer[])var4.toArray(new Integer[var4.size()])).mapToInt(new MsgMgr$$ExternalSyntheticLambda0()).toArray();
         GeneralParkData.isShowDistanceNotShowLocationUi = true;
         var3 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarDistanceData(var3[4], var3[5], var3[6], var3[7]);
         GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSwcKey() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         switch (var1) {
            case 17:
               this.buttonKey(2);
               break;
            case 18:
               this.buttonKey(48);
               break;
            case 19:
               this.buttonKey(47);
               break;
            case 20:
               this.buttonKey(7);
               break;
            case 21:
               this.buttonKey(8);
               break;
            case 22:
               this.buttonKey(3);
               break;
            case 23:
               this.buttonKey(49);
               break;
            case 24:
               this.buttonKey(50);
               break;
            default:
               switch (var1) {
                  case 48:
                     this.buttonKey(14);
                     break;
                  case 49:
                     this.buttonKey(15);
                     break;
                  case 50:
                     this.buttonKey(187);
               }
         }
      } else {
         this.buttonKey(0);
      }

   }

   private void setTrack() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[3], var1[2], 7680, 12544, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setVehicleControlInfo0x58() {
      if (this.eachId == 8) {
         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_frontrightdoor"), this.mCanBusInfoInt[2]));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_rearrightdoor"), this.mCanBusInfoInt[3]));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_frontleftdoor"), this.mCanBusInfoInt[4]));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_rearleftdoor"), this.mCanBusInfoInt[5]));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_Skylight"), this.Skylight(this.mCanBusInfoInt[6])));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_Sunshade"), this.SunShade(this.mCanBusInfoInt[7])));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_ElectricTailgateFunction"), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_ElectricTailgate"), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8])));
         var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_Electric_Tailgate_Open_Position"), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_frontrightdoor"), this.DoorOpenPosition(this.mCanBusInfoInt[2])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_rearrightdoor"), this.DoorOpenPosition(this.mCanBusInfoInt[3])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_frontleftdoor"), this.DoorOpenPosition(this.mCanBusInfoInt[4])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_rearleftdoor"), this.DoorOpenPosition(this.mCanBusInfoInt[5])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_Skylight"), this.Skylight(this.mCanBusInfoInt[6])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_Sunshade"), this.SunShade(this.mCanBusInfoInt[7])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_ElectricTailgateFunction"), this.ElectricTailgate(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_ElectricTailgate"), this.ElectricTailgate(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), 10, this.ElectricTailgatePosition(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]))));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), 8, this.DoorOpenPosition(this.mCanBusInfoInt[9])));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_Memory_Open_Position"), this.DoorOpenPosition(this.mCanBusInfoInt[10])));
         this.updateGeneralSettingData(var1);
         this.updateGeneralDriveData(var2);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSettingActivity((Bundle)null);
      }
   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void sndCurCompassSt(Location var1) {
      if (this.isHaveCompass()) {
         CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)((int)(var1.getBearing() * 32.0F / 360.0F)), 0});
      }
   }

   private String[] strHeatingLev(Context var1) {
      return new String[]{var1.getResources().getString(2131768042), var1.getResources().getString(2131759475), var1.getResources().getString(2131759476), var1.getResources().getString(2131759477)};
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      byte[] var6 = new byte[0];

      byte[] var5;
      try {
         var5 = var1.getBytes("Unicode");
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
         var5 = var6;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 11, -1, 16, 1, (byte)var5.length}, var5));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      int var4;
      if (var1 != null) {
         var4 = var1.length;
      } else {
         var4 = 0;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 0, 0, 1, (byte)var4}, var1));
      this.isHangUp = true;
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.isCallIn = true;
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      int var4;
      if (var1 != null) {
         var4 = var1.length;
      } else {
         var4 = 0;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 0, 0, 1, (byte)var4}, var1));
      this.isHangUp = false;
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      this.isCallIn = false;
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      int var4;
      if (var1 != null) {
         var4 = var1.length;
      } else {
         var4 = 0;
      }

      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 0, 0, 1, (byte)var4}, var1));
      this.isHangUp = false;
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      if (!this.isHangUp) {
         int var6;
         if (var1 != null) {
            var6 = var1.length;
         } else {
            var6 = 0;
         }

         byte var5 = (byte)var6;
         var6 = this.getMsb(var4);
         int var7 = this.getLsb(var4);
         byte var8 = 2;
         if (!this.isCallIn) {
            var8 = 4;
         }

         CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte)var8, (byte)var6, (byte)var7, 1, var5}, var1));
      }

   }

   public void buttonKey(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      this.mContext = var1;
      int var3 = var4[1];
      if (var3 != 20) {
         if (var3 != 36) {
            if (var3 != 64) {
               if (var3 != 80) {
                  if (var3 != 88) {
                     switch (var3) {
                        case 16:
                           if (this.isAirMsgRepeat(var2)) {
                              return;
                           }

                           this.setAirData0x10();
                           break;
                        case 17:
                           if (!this.isAirMsgRepeat(var2) && this.isHaveRearAir()) {
                              this.setRearAir();
                              break;
                           }

                           return;
                        case 18:
                           this.setSwcKey();
                           break;
                        default:
                           switch (var3) {
                              case 48:
                                 this.setVersionInfo();
                                 break;
                              case 49:
                                 this.setTrack();
                                 break;
                              case 50:
                                 this.setRearRadar();
                                 break;
                              case 51:
                                 this.setFrontRadar();
                                 break;
                              default:
                                 switch (var3) {
                                    case 82:
                                       this.language_settings_0x52();
                                       this.air_conditioning_settings_0x52();
                                       this.body_and_accessories_0x52();
                                       this.lighting_control_0x52();
                                       this.seat_set_0x52();
                                       this.driving_assistance_0x52();
                                       this.Intelligent_parking_assistance0x52();
                                       break;
                                    case 83:
                                       this.setNewEnergyInfo_0x53();
                                       break;
                                    case 84:
                                       this.set0x54newEnergyInfo();
                                       break;
                                    case 85:
                                       this.setIntelligentPerceptionSystemInfo0x55();
                                 }
                           }
                     }
                  } else {
                     this.setVehicleControlInfo0x58();
                  }
               } else {
                  this.setPanelBtnInfo();
               }
            } else {
               this.setParkAssistance0x40();
            }
         } else {
            this.setDoorData0x24();
         }
      } else {
         this.set0x14BackLightInfo();
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, (boolean)var11, var12, var13);
      byte var15 = (byte)var6;
      byte var14 = (byte)var5;
      byte var17 = (byte)var10;
      byte var16 = (byte)var11;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -56}, new byte[]{var15, var14, var17, var16}));
      this.setCarIdCmd();
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      if (var7 == 6 || var7 == 7) {
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         byte var14 = (byte)(var6 & 255);
         byte var15 = (byte)(var6 >> 8 & 255);
         byte var17 = (byte)(var4 & 255);
         byte var18 = (byte)(var2 & 255);
         byte var16 = (byte)(var2 >> 8 & 255);
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 16, 0, var15, var14, var17, var15, 0, 0, var16, var18});
      }
   }

   public void forceReverse(Context var1, boolean var2) {
      super.forceReverse(var1, var2);
   }

   void init360Disp(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, "_220_SAVE_360", 0);
      if (this.isHaveCam360()) {
         ArrayList var3 = new ArrayList();
         var3.add(new SettingUpdateEntity(7, 0, var2));
         this.updateGeneralSettingData(var3);
         this.updateSettingActivity((Bundle)null);
      }
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      mDifferentId = this.getCurrentCanDifferentId();
      this.eachId = this.getCurrentEachCanId();
      this.mLocationManager = (LocationManager)var1.getSystemService("location");
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.setCarIdCmd();

      try {
         this.mLocationManager.requestLocationUpdates("gps", 1000L, 1.0F, this.locationListener);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   boolean isHaveCam360() {
      int var1 = this.getCurrentCanDifferentId();
      return var1 == 3 || var1 == 9 || var1 == 14 || var1 == 16 || var1 == 19 || var1 == 5 || var1 == 6 || var1 == 7 || var1 == 11 || var1 == 12;
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      boolean var26;
      if (var1 == 9) {
         var26 = true;
      } else {
         var26 = false;
      }

      var1 = (byte)(var4 & 255);
      var8 = (byte)(var4 >> 8 & 255);
      var2 = (byte)((int)(var19 & 255L));
      var10 = (byte)((int)(255L & var19 >> 8));
      var4 = var5 * 3600 + var6 * 60 + var7;
      var6 = (byte)(var4 & 255);
      var7 = (byte)(var4 >> 8 & 255);
      byte var25;
      if (var26) {
         var25 = 9;
      } else {
         var25 = 8;
      }

      var5 = (byte)var25;
      var16 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, var5, 0, var8, var1, var9, var16, var10, var2, var7, var6});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = var1;
      if (var1 > 6) {
         var5 = 0;
      }

      byte var10 = CommUtil.getRadioCurrentBand(var2, (byte)1, (byte)2, (byte)3, (byte)17, (byte)18);
      int[] var11 = this.getFreqByteHiLo(var2, var3);
      byte var6 = (byte)var10;
      byte var7 = (byte)var11[1];
      byte var8 = (byte)var11[0];
      byte var9 = (byte)var5;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, var6, var7, var8, var9});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      boolean var24;
      if (var1 == 9) {
         var24 = true;
      } else {
         var24 = false;
      }

      byte var19 = (byte)(var4 & 255);
      byte var20 = (byte)(var4 >> 8 & 255);
      var2 = var5 * 3600 + var6 * 60 + var7;
      byte var21 = (byte)(var2 & 255);
      byte var18 = (byte)(var2 >> 8 & 255);
      if (var24) {
         var1 = 9;
      } else {
         var1 = 8;
      }

      byte var22 = (byte)var1;
      byte var23 = (byte)var3;
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, var22, 0, var20, var19, var9, var23, 0, 0, var18, var21});
   }
}
