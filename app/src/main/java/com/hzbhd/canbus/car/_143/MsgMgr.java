package com.hzbhd.canbus.car._143;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   static final String SHARE_143_ENABLE_INFO = "share_143_enable_info";
   static final int VEHICLE_TYPE_13_C5 = 5;
   static final int VEHICLE_TYPE_14_408 = 16;
   static final int VEHICLE_TYPE_15_508 = 21;
   static final int VEHICLE_TYPE_17_4008 = 20;
   static final int VEHICLE_TYPE_19_301 = 23;
   static final int VEHICLE_TYPE_19_RIFTER_HIGH = 24;
   static final int VEHICLE_TYPE_19_RIFTER_LOW = 25;
   static final int VEHICLE_TYPE_2008 = 14;
   static final int VEHICLE_TYPE_3008_CUT = 11;
   static final int VEHICLE_TYPE_3008_SCREEN = 17;
   static final int VEHICLE_TYPE_301_Elysee = 18;
   static final int VEHICLE_TYPE_307 = 6;
   static final int VEHICLE_TYPE_308 = 7;
   static final int VEHICLE_TYPE_408 = 8;
   static final int VEHICLE_TYPE_508_HIGH = 10;
   static final int VEHICLE_TYPE_508_LOW = 9;
   static final int VEHICLE_TYPE_AIRCROSS = 26;
   static final int VEHICLE_TYPE_C3_XR = 19;
   static final int VEHICLE_TYPE_C4 = 2;
   static final int VEHICLE_TYPE_C4L = 3;
   static final int VEHICLE_TYPE_C5 = 4;
   static final int VEHICLE_TYPE_DS4_HIGH = 15;
   static final int VEHICLE_TYPE_DS4_LOW = 65;
   static final int VEHICLE_TYPE_DS5LS_DS6_HIGH = 13;
   static final int VEHICLE_TYPE_DS5LS_DS6_LOW = 53;
   static final int VEHICLE_TYPE_DS5_HING = 12;
   static final int VEHICLE_TYPE_DS5_LOW = 52;
   static final int VEHICLE_TYPE_QUATRA = 1;
   private static boolean isAirFirst;
   private static boolean isDoorFirst;
   private static boolean isWarnFirst;
   static int language;
   private static int outDoorTemp;
   private static int swc_data3;
   private static int up_dn_btn_data;
   private static int voice_btn_data;
   private final String IS_BACK_OPEN = "is_back_open";
   private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
   private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
   private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
   private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
   private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
   private final String IS_SEAT_CO_PILOT_BELT_TIE = "is_seat_co_pilot_belt_tie";
   private final String IS_SEAT_MASTER_DRIVER_BELT_TIE = "is_seat_master_driver_belt_tie";
   private final String IS_SEAT_R_L_BELT_TIE = "is_seat_r_l_belt_tie";
   private final String IS_SEAT_R_M_BELT_TIE = "is_seat_r_m_belt_tie";
   private final String IS_SEAT_R_R_BELT_TIE = "is_seat_r_r_belt_tie";
   private final int MSG_CLOSE_WARNING_ACTIVITY = 1000;
   private final String SWC_BTN_DATA_3 = "swc_btn_data_3";
   private final String SWC_DATA_3 = "swc_data_3";
   int backLight = 0;
   private byte[] bytes;
   TextView content;
   String contentMsg;
   private int currentItem = 0;
   AlertDialog dialog;
   private int first0x97 = 1;
   Button i_know;
   private boolean isDGear;
   private boolean isRadioFirst = true;
   private byte[] m0x72Enable1 = new byte[4];
   private byte[] m0x81Command = new byte[12];
   private byte[] m0x82Command = new byte[12];
   private byte[] m0x83InfoCopy;
   private byte[] m0x85Command = new byte[4];
   private byte[] m0xC1Command = new byte[4];
   private byte[] mCanBusAirInfoCopy;
   private byte[] mCanBusBtnPanelInfoCopy;
   private byte[] mCanBusDoorInfoCopy;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private byte[] mCanBusRadarInfoCopy;
   private byte[] mCanBusSwcDataCopy;
   private byte[] mCanBusSwcInfoCopy;
   private byte[] mCanBusWarnInfoCopy;
   private Context mContext;
   private int mDifferentId;
   private int mEachId;
   private String mFuelUnit = "L/100KM";
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private String mId3SongTitle = "";
   private byte[] mOriginalRadioCopy;
   private byte[] mOriginalRadioInfoCopy;
   private SparseArray mPanelKeyArray;
   private HashMap mSettingItemIndeHashMap = new HashMap();
   private String mTemperatureUnit = "℃";
   private Timer mTimer;
   private UiMgr mUiMgr;
   private int nextItem;
   int nowLight = 3;
   int nowLightMode = 0;
   int nowPcState = 1;
   boolean pinBlackTag = false;
   BroadcastReceiver receiver = new BroadcastReceiver(this) {
      final MsgMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         int var3 = var2.getIntExtra("selectPos1", 0);
         if ("REVERSING_SOUND".equals(var2.getAction())) {
            this.this$0.settingLeft0Right12Data(var3);
         }

      }
   };
   private int[] setting_0x71_canBusInfo_Enable = new int[]{255, 255, 255, 255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   private byte[] setting_0x72_canBusInfo_Enable = new byte[]{22, 114, -1, -1, 0, 0, 0, 0, 0, 0};
   private int[] setting_0x76_canBusInfo_Enable = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   private int[] setting_0x79_canBusInfo_Enable = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   View view;

   private void car_4008_Year_2017() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 40) {
               if (var1 != 41) {
                  if (var1 != 51) {
                     switch (var1) {
                        case 43:
                           this.panelButtonClick(14);
                           break;
                        case 44:
                           this.panelButtonClick(52);
                           break;
                        case 45:
                           this.panelButtonClick(59);
                           break;
                        case 46:
                           this.panelButtonClick(58);
                     }
                  } else {
                     this.panelButtonClick(128);
                  }
               } else {
                  this.setLightMode();
               }
            } else {
               this.startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_407_Year_2006() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 7) {
            if (var1 != 40) {
               if (var1 != 43) {
                  if (var1 != 46) {
                     if (var1 != 49) {
                        if (var1 != 64) {
                           if (var1 != 36) {
                              if (var1 != 37) {
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(45);
                                       break;
                                    case 24:
                                       this.panelButtonClick(46);
                                       break;
                                    case 25:
                                       this.panelButtonClick(47);
                                       break;
                                    case 26:
                                       this.panelButtonClick(48);
                                 }
                              } else {
                                 this.panelButtonClick(1);
                              }
                           } else {
                              this.panelButtonClick(49);
                           }
                        } else {
                           this.toCarPc();
                        }
                     } else {
                        this.panelButtonClick(2);
                     }
                  } else {
                     this.panelButtonClick(58);
                  }
               } else {
                  this.panelButtonClick(14);
               }
            } else {
               this.startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
            }
         } else {
            this.panelButtonClick(134);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_408_Year_2014() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 17) {
               this.panelButtonClick(31);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_508_High() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 17) {
               if (var1 != 36) {
                  if (var1 != 37) {
                     switch (var1) {
                        case 7:
                           this.panelButtonClick(134);
                           break;
                        case 8:
                           this.panelButtonClick(2);
                           break;
                        case 9:
                           this.panelButtonClick(59);
                           break;
                        case 10:
                           this.panelButtonClick(33);
                           break;
                        case 11:
                           this.panelButtonClick(34);
                           break;
                        case 12:
                           this.panelButtonClick(35);
                           break;
                        case 13:
                           this.panelButtonClick(36);
                           break;
                        case 14:
                           this.panelButtonClick(37);
                           break;
                        case 15:
                           this.panelButtonClick(38);
                           break;
                        default:
                           switch (var1) {
                              case 22:
                                 this.panelButtonClick(62);
                                 break;
                              case 23:
                                 this.panelButtonClick(21);
                                 break;
                              case 24:
                                 this.panelButtonClick(20);
                                 break;
                              case 25:
                                 this.panelButtonClick(45);
                                 break;
                              case 26:
                                 this.panelButtonClick(46);
                                 break;
                              default:
                                 switch (var1) {
                                    case 39:
                                       this.toCarPc();
                                       break;
                                    case 40:
                                       this.panelButtonClick(94);
                                       break;
                                    case 41:
                                       this.startMainActivity(this.mContext);
                                       break;
                                    case 42:
                                       this.panelButtonClick(62);
                                       break;
                                    case 43:
                                       this.panelButtonClick(14);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 45:
                                             this.panelButtonClick(15);
                                             break;
                                          case 46:
                                             this.panelButtonClick(58);
                                             break;
                                          case 47:
                                             this.startWarningActivity(this.mContext);
                                             break;
                                          default:
                                             switch (var1) {
                                                case 49:
                                                   this.panelButtonClick(2);
                                                   break;
                                                case 50:
                                                   this.panelButtonClick(1);
                                                   break;
                                                case 51:
                                                   this.panelButtonClick(128);
                                             }
                                       }
                                 }
                           }
                     }
                  } else {
                     this.panelButtonClick(134);
                  }
               } else {
                  this.panelButtonClick(49);
               }
            } else {
               this.panelButtonClick(31);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_508_Low() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 17) {
               if (var1 != 36) {
                  if (var1 != 37) {
                     switch (var1) {
                        case 7:
                           this.panelButtonClick(62);
                           break;
                        case 8:
                           this.panelButtonClick(2);
                           break;
                        case 9:
                           this.panelButtonClick(59);
                           break;
                        case 10:
                           this.panelButtonClick(33);
                           break;
                        case 11:
                           this.panelButtonClick(34);
                           break;
                        case 12:
                           this.panelButtonClick(35);
                           break;
                        case 13:
                           this.panelButtonClick(36);
                           break;
                        case 14:
                           this.panelButtonClick(37);
                           break;
                        case 15:
                           this.panelButtonClick(38);
                           break;
                        default:
                           switch (var1) {
                              case 22:
                                 this.panelButtonClick(151);
                                 break;
                              case 23:
                                 this.panelButtonClick(21);
                                 break;
                              case 24:
                                 this.panelButtonClick(20);
                                 break;
                              case 25:
                                 this.panelButtonClick(45);
                                 break;
                              case 26:
                                 this.panelButtonClick(46);
                                 break;
                              default:
                                 switch (var1) {
                                    case 39:
                                       this.toCarPc();
                                       break;
                                    case 40:
                                       this.panelButtonClick(94);
                                       break;
                                    case 41:
                                       this.startMainActivity(this.mContext);
                                 }
                           }
                     }
                  } else {
                     this.panelButtonClick(134);
                  }
               } else {
                  this.panelButtonClick(49);
               }
            } else {
               this.panelButtonClick(31);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_508_Year_2015_have_amplifier_High() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 40) {
                     switch (var1) {
                        case 9:
                           this.panelButtonClick(3);
                           break;
                        case 10:
                           this.panelButtonClick(33);
                           break;
                        case 11:
                           this.panelButtonClick(34);
                           break;
                        case 12:
                           this.panelButtonClick(35);
                           break;
                        case 13:
                           this.panelButtonClick(36);
                           break;
                        case 14:
                           this.panelButtonClick(37);
                           break;
                        case 15:
                           this.panelButtonClick(38);
                           break;
                        default:
                           switch (var1) {
                              case 23:
                                 this.panelButtonClick(45);
                                 break;
                              case 24:
                                 this.panelButtonClick(46);
                                 break;
                              case 25:
                                 this.setBackLightUP(false);
                                 break;
                              case 26:
                                 this.setBackLightUP(true);
                                 break;
                              default:
                                 switch (var1) {
                                    case 44:
                                       this.panelButtonClick(2);
                                       break;
                                    case 45:
                                       this.panelButtonClick(187);
                                       break;
                                    case 46:
                                       this.panelButtonClick(62);
                                       break;
                                    case 47:
                                       this.panelButtonClick(20);
                                 }
                           }
                     }
                  } else {
                     this.panelButtonClick(134);
                  }
               } else {
                  this.panelButtonClick(21);
               }
            } else {
               this.startWarningActivity(this.mContext);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_C4L() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 39) {
                        switch (var1) {
                           case 9:
                              this.panelButtonClick(3);
                              break;
                           case 10:
                              this.panelButtonClick(33);
                              break;
                           case 11:
                              this.panelButtonClick(34);
                              break;
                           case 12:
                              this.panelButtonClick(35);
                              break;
                           case 13:
                              this.panelButtonClick(36);
                              break;
                           case 14:
                              this.panelButtonClick(37);
                              break;
                           case 15:
                              this.panelButtonClick(38);
                              break;
                           default:
                              switch (var1) {
                                 case 23:
                                    this.panelButtonClick(21);
                                    break;
                                 case 24:
                                    this.panelButtonClick(20);
                                    break;
                                 case 25:
                                    this.panelButtonClick(45);
                                    break;
                                 case 26:
                                    this.panelButtonClick(46);
                                    break;
                                 default:
                                    switch (var1) {
                                       case 42:
                                          this.panelButtonClick(49);
                                          break;
                                       case 43:
                                          this.panelButtonClick(68);
                                          break;
                                       case 44:
                                          this.panelButtonClick(2);
                                          break;
                                       case 45:
                                          this.panelButtonClick(59);
                                          break;
                                       case 46:
                                          this.startMainActivity(this.mContext);
                                          break;
                                       case 47:
                                          this.panelButtonClick(62);
                                    }
                              }
                        }
                     } else {
                        this.toCarPc();
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(151);
               }
            } else {
               this.panelButtonClick(134);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_C5() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 40) {
               if (var1 != 51) {
                  switch (var1) {
                     case 43:
                        this.panelButtonClick(14);
                        break;
                     case 44:
                        this.panelButtonClick(52);
                        break;
                     case 45:
                        this.panelButtonClick(59);
                        break;
                     case 46:
                        this.panelButtonClick(58);
                        break;
                     case 47:
                        this.panelButtonClick(151);
                  }
               } else {
                  this.panelButtonClick(128);
               }
            } else {
               this.startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_DS4_High() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 42) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 9:
                                 this.panelButtonClick(58);
                                 break;
                              case 10:
                                 this.panelButtonClick(33);
                                 break;
                              case 11:
                                 this.panelButtonClick(34);
                                 break;
                              case 12:
                                 this.panelButtonClick(35);
                                 break;
                              case 13:
                                 this.panelButtonClick(36);
                                 break;
                              case 14:
                                 this.panelButtonClick(37);
                                 break;
                              case 15:
                                 this.panelButtonClick(38);
                                 break;
                              default:
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(45);
                                       break;
                                    case 24:
                                       this.panelButtonClick(46);
                                       break;
                                    case 25:
                                       this.panelButtonClick(20);
                                       break;
                                    case 26:
                                       this.panelButtonClick(21);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 44:
                                             this.startDrivingDataActivity(this.mContext, 0);
                                             break;
                                          case 45:
                                             this.panelButtonClick(59);
                                             break;
                                          case 46:
                                             this.panelButtonClick(128);
                                             break;
                                          case 47:
                                             this.panelButtonClick(62);
                                       }
                                 }
                           }
                        } else {
                           this.toCarPc();
                        }
                     } else {
                        this.panelButtonClick(49);
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(59);
               }
            } else {
               this.panelButtonClick(134);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_DS4_Low() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 42) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 9:
                                 this.panelButtonClick(94);
                                 break;
                              case 10:
                                 this.panelButtonClick(33);
                                 break;
                              case 11:
                                 this.panelButtonClick(34);
                                 break;
                              case 12:
                                 this.panelButtonClick(35);
                                 break;
                              case 13:
                                 this.panelButtonClick(36);
                                 break;
                              case 14:
                                 this.panelButtonClick(37);
                                 break;
                              case 15:
                                 this.panelButtonClick(38);
                                 break;
                              default:
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(45);
                                       break;
                                    case 24:
                                       this.panelButtonClick(46);
                                       break;
                                    case 25:
                                       this.panelButtonClick(20);
                                       break;
                                    case 26:
                                       this.panelButtonClick(21);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 44:
                                             this.panelButtonClick(2);
                                             break;
                                          case 45:
                                             this.panelButtonClick(59);
                                             break;
                                          case 46:
                                             this.startMainActivity(this.mContext);
                                             break;
                                          case 47:
                                             this.panelButtonClick(62);
                                       }
                                 }
                           }
                        } else {
                           this.toCarPc();
                        }
                     } else {
                        this.panelButtonClick(49);
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(151);
               }
            } else {
               this.panelButtonClick(134);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_DS5L_And_DS6_High() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 42) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 9:
                                 this.panelButtonClick(3);
                                 break;
                              case 10:
                                 this.panelButtonClick(33);
                                 break;
                              case 11:
                                 this.panelButtonClick(34);
                                 break;
                              case 12:
                                 this.panelButtonClick(35);
                                 break;
                              case 13:
                                 this.panelButtonClick(36);
                                 break;
                              case 14:
                                 this.panelButtonClick(37);
                                 break;
                              case 15:
                                 this.panelButtonClick(38);
                                 break;
                              default:
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(20);
                                       break;
                                    case 24:
                                       this.panelButtonClick(21);
                                       break;
                                    case 25:
                                       this.panelButtonClick(45);
                                       break;
                                    case 26:
                                       this.panelButtonClick(46);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 44:
                                             this.panelButtonClick(3);
                                             break;
                                          case 45:
                                             this.startWarningActivity(this.mContext);
                                             break;
                                          case 46:
                                             this.panelButtonClick(62);
                                             break;
                                          case 47:
                                             this.setBackLightUP(false);
                                       }
                                 }
                           }
                        } else {
                           this.panelButtonClick(187);
                        }
                     } else {
                        this.panelButtonClick(49);
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(134);
               }
            } else {
               this.setBackLightUP(true);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_DS5L_And_DS6_Low() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 42) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 9:
                                 this.panelButtonClick(3);
                                 break;
                              case 10:
                                 this.panelButtonClick(33);
                                 break;
                              case 11:
                                 this.panelButtonClick(34);
                                 break;
                              case 12:
                                 this.panelButtonClick(35);
                                 break;
                              case 13:
                                 this.panelButtonClick(36);
                                 break;
                              case 14:
                                 this.panelButtonClick(37);
                                 break;
                              case 15:
                                 this.panelButtonClick(38);
                                 break;
                              default:
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(45);
                                       break;
                                    case 24:
                                       this.panelButtonClick(46);
                                       break;
                                    case 25:
                                       this.panelButtonClick(20);
                                       break;
                                    case 26:
                                       this.panelButtonClick(21);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 44:
                                             this.panelButtonClick(2);
                                             break;
                                          case 45:
                                             this.panelButtonClick(59);
                                             break;
                                          case 46:
                                             this.startMainActivity(this.mContext);
                                             break;
                                          case 47:
                                             this.panelButtonClick(62);
                                       }
                                 }
                           }
                        } else {
                           this.toCarPc();
                        }
                     } else {
                        this.panelButtonClick(49);
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(151);
               }
            } else {
               this.panelButtonClick(50);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_DS5_High() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 42) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 9:
                                 this.panelButtonClick(3);
                                 break;
                              case 10:
                                 this.panelButtonClick(33);
                                 break;
                              case 11:
                                 this.panelButtonClick(34);
                                 break;
                              case 12:
                                 this.panelButtonClick(35);
                                 break;
                              case 13:
                                 this.panelButtonClick(36);
                                 break;
                              case 14:
                                 this.panelButtonClick(37);
                                 break;
                              case 15:
                                 this.panelButtonClick(38);
                                 break;
                              default:
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(45);
                                       break;
                                    case 24:
                                       this.panelButtonClick(46);
                                       break;
                                    case 25:
                                       this.setBackLightUP(false);
                                       break;
                                    case 26:
                                       this.setBackLightUP(true);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 44:
                                             this.panelButtonClick(2);
                                             break;
                                          case 45:
                                             this.panelButtonClick(187);
                                             break;
                                          case 46:
                                             this.panelButtonClick(62);
                                             break;
                                          case 47:
                                             this.panelButtonClick(20);
                                       }
                                 }
                           }
                        } else {
                           this.panelButtonClick(187);
                        }
                     } else {
                        this.panelButtonClick(134);
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(21);
               }
            } else {
               this.startWarningActivity(this.mContext);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void car_DS5_Low() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 6) {
               if (var1 != 7) {
                  if (var1 != 17) {
                     if (var1 != 42) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 9:
                                 this.panelButtonClick(3);
                                 break;
                              case 10:
                                 this.panelButtonClick(33);
                                 break;
                              case 11:
                                 this.panelButtonClick(34);
                                 break;
                              case 12:
                                 this.panelButtonClick(35);
                                 break;
                              case 13:
                                 this.panelButtonClick(36);
                                 break;
                              case 14:
                                 this.panelButtonClick(37);
                                 break;
                              case 15:
                                 this.panelButtonClick(38);
                                 break;
                              default:
                                 switch (var1) {
                                    case 23:
                                       this.panelButtonClick(45);
                                       break;
                                    case 24:
                                       this.panelButtonClick(46);
                                       break;
                                    case 25:
                                       this.panelButtonClick(21);
                                       break;
                                    case 26:
                                       this.panelButtonClick(20);
                                       break;
                                    default:
                                       switch (var1) {
                                          case 44:
                                             this.panelButtonClick(2);
                                             break;
                                          case 45:
                                             this.panelButtonClick(59);
                                             break;
                                          case 46:
                                             this.startMainActivity(this.mContext);
                                             break;
                                          case 47:
                                             this.panelButtonClick(151);
                                       }
                                 }
                           }
                        } else {
                           this.toCarPc();
                        }
                     } else {
                        this.panelButtonClick(49);
                     }
                  } else {
                     this.panelButtonClick(31);
                  }
               } else {
                  this.panelButtonClick(62);
               }
            } else {
               this.panelButtonClick(50);
            }
         } else {
            this.panelButtonClick(1);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void changeOriginalDevicePage(List var1, String[] var2, boolean var3, boolean var4) {
      OriginalCarDevicePageUiSet var5 = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
      if (var5 != null) {
         var5.setItems(var1);
         var5.setRowBottomBtnAction(var2);
         var5.setHavePlayTimeSeekBar(var3);
         var5.setHaveSongList(var4);
         Bundle var6 = new Bundle();
         var6.putBoolean("bundle_key_orinal_init_view", true);
         this.updateOriginalCarDeviceActivity(var6);
         this.enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
      }
   }

   private boolean equal(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1.equals(var2[var3])) {
            return true;
         }
      }

      return false;
   }

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private String getCdPlayModel(int var1) {
      String var2;
      if (DataHandleUtils.getBoolBit7(var1)) {
         var2 = "" + "Scan:ON    \n";
      } else {
         var2 = "" + "Scan:OFF    \n";
      }

      if (DataHandleUtils.getBoolBit6(var1)) {
         var2 = var2 + "Disc scan:ON    \n";
      } else {
         var2 = var2 + "Disc scan:OFF    \n";
      }

      if (DataHandleUtils.getBoolBit5(var1)) {
         var2 = var2 + "Repeat:ON    \n";
      } else {
         var2 = var2 + "Repeat:OFF    \n";
      }

      if (DataHandleUtils.getBoolBit4(var1)) {
         var2 = var2 + "Disc repeat:ON    \n";
      } else {
         var2 = var2 + "Disc repeat:OFF    \n";
      }

      if (DataHandleUtils.getBoolBit3(var1)) {
         var2 = var2 + "Random:ON    \n";
      } else {
         var2 = var2 + "Random:OFF    \n";
      }

      if (DataHandleUtils.getBoolBit2(var1)) {
         var2 = var2 + "Disc random:ON    \n";
      } else {
         var2 = var2 + "Disc random:OFF    \n";
      }

      return var2;
   }

   private String getCdState(int var1) {
      switch (var1) {
         case 0:
            return "Reading TOC";
         case 1:
            return "Pause";
         case 2:
            return "Play";
         case 3:
            return "Fast";
         case 4:
            return "User search";
         case 5:
            return "Internal search";
         case 6:
            return "Stop";
         case 7:
            return "Rom read";
         case 8:
            return "Rom search";
         case 9:
            return "Retrieving";
         case 10:
            return "Disc changing（user）";
         case 11:
            return "Disc changing(internal)";
         case 12:
            return "Eject";
         case 13:
            return "Load";
         case 14:
            return "Disc Error";
         default:
            return "Invalid";
      }
   }

   private String getCdType(boolean var1) {
      return var1 ? "     ♬ Type: Rom Disc" : "     ♬ Type: CD Disc";
   }

   private String getHaveNoState(boolean var1) {
      return var1 ? " ●Have Disc  " : " ○No Disc      ";
   }

   private int getIndexBy2Bit(boolean var1) {
      return var1;
   }

   private int getIntByBoolean(boolean var1) {
      return var1 ? 1 : 0;
   }

   private String getLogo() {
      boolean var1 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
      String var2 = "ST";
      if (!var1) {
         if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            var2 = "RDS";
         } else if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            var2 = "AF";
         } else if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
            var2 = "TP";
         } else if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
            var2 = "TA";
         } else if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
            var2 = "EON";
         } else if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
            var2 = "PTY";
         } else if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
            var2 = "";
         }
      }

      return var2;
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private String getModel(int var1) {
      switch (var1) {
         case 1:
            return "Radio";
         case 2:
            return "CD";
         case 3:
            return "AUX";
         case 4:
            return "NAVI";
         case 5:
            return "CDC";
         case 6:
            return "USB";
         case 7:
            return "TEL";
         case 8:
            return "TV";
         case 9:
            return "IPOD";
         default:
            return "NO MEDIA";
      }
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private int getMsbLsbResult(int var1, int var2) {
      return (var1 & 255) << 8 | var2 & 255;
   }

   private String getNowCd(int var1, int var2) {
      if (var2 == 15 && var1 == 1) {
         return " ☑Select            ";
      } else {
         return var1 == var2 ? "     ☑Select            " : "     □Unchecked  ";
      }
   }

   private String getPreStorage(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131768341);
      } else {
         var2 = " P" + var1;
      }

      return var2;
   }

   private SettingUpdateEntity getSettingUpdateEntity(String var1) {
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         return (SettingUpdateEntity)this.mSettingItemIndeHashMap.get(var1);
      } else {
         this.mSettingItemIndeHashMap.put(var1, new SettingUpdateEntity(-1, -1, (Object)null));
         return this.getSettingUpdateEntity(var1);
      }
   }

   private String getState(int var1) {
      String var2;
      if (var1 == 1) {
         var2 = "FM1";
      } else if (var1 == 2) {
         var2 = "FM2";
      } else if (var1 == 3) {
         var2 = "FM3";
      } else if (var1 == 4) {
         var2 = "AM1";
      } else if (var1 == 5) {
         var2 = "AM2";
      } else if (var1 == 6) {
         var2 = "MW";
      } else if (var1 == 7) {
         var2 = "LW";
      } else {
         var2 = "";
      }

      return var2;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String getUnit() {
      String var1;
      if (this.getState(this.mCanBusInfoInt[2]) != "FM1" && this.getState(this.mCanBusInfoInt[2]) != "FM2" && this.getState(this.mCanBusInfoInt[2]) != "FM3") {
         if (this.getState(this.mCanBusInfoInt[2]) != "AM1" && this.getState(this.mCanBusInfoInt[2]) != "AM2") {
            var1 = "";
         } else {
            var1 = "kHz";
         }
      } else {
         var1 = "MHz";
      }

      return var1;
   }

   private void initAmplifierData() {
      byte[] var5 = new byte[]{22, -83, 2, (byte)(GeneralAmplifierData.leftRight + 7)};
      byte[] var1 = new byte[]{22, -83, 3, (byte)(GeneralAmplifierData.frontRear + 7)};
      byte[] var2 = new byte[]{22, -83, 16, (byte)GeneralAmplifierData.bandBass};
      byte[] var3 = new byte[]{22, -83, 15, (byte)GeneralAmplifierData.bandTreble};
      TimerUtil var4 = new TimerUtil();
      var4.startTimer(new TimerTask(this, new byte[][]{var5, var1, var2, var3}, var4) {
         int i;
         final MsgMgr this$0;
         final byte[][] val$amplifierData;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$amplifierData = var2;
            this.val$timerUtil = var3;
            this.i = 0;
         }

         public void run() {
            int var1 = this.i;
            byte[][] var2 = this.val$amplifierData;
            if (var1 < var2.length) {
               CanbusMsgSender.sendMsg(var2[var1]);
               ++this.i;
            } else {
               this.val$timerUtil.stopTimer();
            }

         }
      }, 0L, 100L);
   }

   private void initSettingsItem(Context var1) {
      this.mSettingItemIndeHashMap = new HashMap();
      SparseArray var4 = new SparseArray();
      List var6 = this.getUiMgr(var1).getSettingUiSet(var1).getList();

      for(int var2 = 0; var2 < var6.size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var6.get(var2)).getItemList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var7 = ((SettingPageUiSet.ListBean.ItemListBean)var5.get(var3)).getTitleSrn();
            var4.append(var2 << 8 | var3, var7);
            this.mSettingItemIndeHashMap.put(var7, new SettingUpdateEntity(var2, var3, (Object)null));
         }
      }

   }

   private boolean is0x83MsgRepeat(byte[] var1) {
      if (Arrays.equals(var1, this.m0x83InfoCopy)) {
         return true;
      } else {
         this.m0x83InfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isAirMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusAirInfoCopy)) {
         return true;
      } else {
         this.mCanBusAirInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isAirFirst) {
            isAirFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isBtnPanelMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusBtnPanelInfoCopy)) {
         return true;
      } else {
         this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isDoorMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusDoorInfoCopy)) {
         return true;
      } else {
         this.mCanBusDoorInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isDoorFirst) {
            isDoorFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private boolean isOnlyDoorMsgShow() {
      if (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) != GeneralDoorData.isLeftFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) != GeneralDoorData.isRightFrontDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) != GeneralDoorData.isRightRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) != GeneralDoorData.isLeftRearDoorOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) != GeneralDoorData.isBackOpen) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_seat_master_driver_belt_tie", false) != GeneralDoorData.isSeatMasterDriverBeltTie) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_seat_co_pilot_belt_tie", false) != GeneralDoorData.isSeatCoPilotBeltTie) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_seat_r_l_belt_tie", false) != GeneralDoorData.isSeatRLBeltTie) {
         return true;
      } else if (SharePreUtil.getBoolValue(this.mContext, "is_seat_r_m_belt_tie", false) != GeneralDoorData.isSeatRMBeltTie) {
         return true;
      } else {
         return SharePreUtil.getBoolValue(this.mContext, "is_seat_r_r_belt_tie", false) != GeneralDoorData.isSeatRRBeltTie;
      }
   }

   private boolean isOnlyOutDoorDataChange() {
      return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0F) != (float)outDoorTemp;
   }

   private boolean isRadarMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusRadarInfoCopy)) {
         return true;
      } else {
         this.mCanBusRadarInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isSwcDataMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcDataCopy)) {
         return true;
      } else {
         this.mCanBusSwcDataCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isSwcMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusSwcInfoCopy)) {
         return true;
      } else {
         this.mCanBusSwcInfoCopy = Arrays.copyOf(var1, var1.length);
         return false;
      }
   }

   private boolean isWarningMsgReturn(byte[] var1) {
      if (Arrays.equals(var1, this.mCanBusWarnInfoCopy)) {
         return true;
      } else {
         this.mCanBusWarnInfoCopy = Arrays.copyOf(var1, var1.length);
         if (isWarnFirst) {
            isWarnFirst = false;
            return true;
         } else {
            return false;
         }
      }
   }

   private void keyControl0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        if (var1 != 64) {
                           switch (var1) {
                              case 8:
                                 this.realKeyClick1(46);
                                 break;
                              case 9:
                                 this.realKeyClick1(45);
                                 break;
                              case 10:
                                 this.realKeyClick1(52);
                                 break;
                              case 11:
                                 this.realKeyClick1(2);
                                 break;
                              default:
                                 switch (var1) {
                                    case 13:
                                       this.realKeyClick1(47);
                                       break;
                                    case 14:
                                       this.realKeyClick1(48);
                                       break;
                                    case 15:
                                       this.realKeyClick1(49);
                                       break;
                                    case 16:
                                       this.realKeyClick1(50);
                                       break;
                                    case 17:
                                       this.realKeyClick1(21);
                                       break;
                                    case 18:
                                       this.realKeyClick1(20);
                                       break;
                                    case 19:
                                       this.realKeyClick1(185);
                                       break;
                                    case 20:
                                       this.realKeyClick1(39);
                                       break;
                                    case 21:
                                       Intent var2 = new Intent(this.mContext, WarningActivity.class);
                                       var2.addFlags(268435456);
                                       this.mContext.startActivity(var2);
                                       break;
                                    case 22:
                                       this.startDrivingPage();
                                 }
                           }
                        } else {
                           this.realKeyClick1(68);
                        }
                     } else {
                        this.realKeyClick1(14);
                     }
                  } else {
                     this.realKeyClick1(187);
                  }
               } else {
                  this.realKeyClick1(3);
               }
            } else {
               this.realKeyClick1(8);
            }
         } else {
            this.realKeyClick1(7);
         }
      } else {
         this.realKeyClick1(0);
      }

   }

   private void otherCar() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 17) {
               if (var1 != 64) {
                  if (var1 != 36) {
                     if (var1 != 37) {
                        switch (var1) {
                           case 6:
                              this.panelButtonClick(50);
                              break;
                           case 7:
                              this.panelButtonClick(46);
                              break;
                           case 8:
                              this.panelButtonClick(2);
                              break;
                           case 9:
                              this.panelButtonClick(3);
                              break;
                           case 10:
                              this.panelButtonClick(33);
                              break;
                           case 11:
                              this.panelButtonClick(34);
                              break;
                           case 12:
                              this.panelButtonClick(35);
                              break;
                           case 13:
                              this.panelButtonClick(36);
                              break;
                           case 14:
                              this.panelButtonClick(37);
                              break;
                           case 15:
                              this.panelButtonClick(38);
                              break;
                           default:
                              switch (var1) {
                                 case 22:
                                    this.panelButtonClick(129);
                                    break;
                                 case 23:
                                    this.panelButtonClick(21);
                                    break;
                                 case 24:
                                    this.panelButtonClick(20);
                                    break;
                                 case 25:
                                    this.panelButtonClick(47);
                                    break;
                                 case 26:
                                    this.panelButtonClick(48);
                                    break;
                                 default:
                                    switch (var1) {
                                       case 39:
                                          this.panelButtonClick(39);
                                          break;
                                       case 40:
                                          this.panelButtonClick(94);
                                          break;
                                       case 41:
                                          this.panelButtonClick(59);
                                          break;
                                       case 42:
                                          this.panelButtonClick(40);
                                          break;
                                       case 43:
                                          this.panelButtonClick(14);
                                          break;
                                       case 44:
                                          this.panelButtonClick(52);
                                          break;
                                       case 45:
                                          this.panelButtonClick(15);
                                          break;
                                       case 46:
                                          this.panelButtonClick(128);
                                          break;
                                       case 47:
                                          this.panelButtonClick(151);
                                          break;
                                       default:
                                          switch (var1) {
                                             case 49:
                                                this.panelButtonClick(41);
                                                break;
                                             case 50:
                                                this.panelButtonClick(1);
                                                break;
                                             case 51:
                                                this.panelButtonClick(58);
                                          }
                                    }
                              }
                        }
                     } else {
                        this.panelButtonClick(4105);
                     }
                  } else {
                     this.panelButtonClick(49);
                  }
               } else {
                  this.panelButtonClick(42);
               }
            } else {
               this.panelButtonClick(31);
            }
         } else {
            this.panelButtonClick(134);
         }
      } else {
         this.panelButtonClick(0);
      }

   }

   private void panelButtonClick(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[3]);
   }

   private void realKeyClick1(int var1) {
      Context var2 = this.mContext;
      int[] var3 = this.mCanBusInfoInt;
      this.realKeyClick5(var2, var1, var3[4], var3[5]);
   }

   private void realKeyClick4(int var1) {
      this.realKeyClick4(this.mContext, var1);
   }

   private void reportID3(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         if (!var1.equals(this.mId3SongTitle)) {
            this.mId3SongTitle = var1;

            try {
               byte[] var3 = DataHandleUtils.exceptBOMHead(var1.getBytes("utf-8"));
               CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -28}, var3), 34, 32));
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }
   }

   private String resolveLeftAndRightTemp(int var1) {
      String var2;
      if (254 == var1) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      }

      return var2;
   }

   private String resolveOutDoorTem() {
      int var1 = this.mCanBusInfoInt[13];
      String var2;
      if (var1 >= 0 && var1 <= 250) {
         var2 = (float)((double)var1 * 0.5 - 40.0) + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private void set0x72CarSetEnable(Context var1) {
      this.setting_0x72_canBusInfo_Enable = this.mCanBusInfoByte;
      this.mCanBusInfoInt = this.setting_0x79_canBusInfo_Enable;
      this.set0x79VehicleSet2();
   }

   private void set0x76VehicleSet() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
      ArrayList var3 = new ArrayList();
      var3.add(this.getSettingUpdateEntity("_143_0x76_d0_b7").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))).setEnable(DataHandleUtils.getBoolBit7(this.setting_0x71_canBusInfo_Enable[2])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d0_b6").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))).setEnable(DataHandleUtils.getBoolBit6(this.setting_0x71_canBusInfo_Enable[2])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d0_b45").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)).setEnable(DataHandleUtils.getBoolBit5(this.setting_0x71_canBusInfo_Enable[2])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d0_b3").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))).setEnable(DataHandleUtils.getBoolBit3(this.setting_0x71_canBusInfo_Enable[2])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d0_b02").setValue(var1).setProgress(var1));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b7").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]))).setEnable(DataHandleUtils.getBoolBit7(this.setting_0x71_canBusInfo_Enable[3])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b6").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))).setEnable(DataHandleUtils.getBoolBit6(this.setting_0x71_canBusInfo_Enable[3])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b5").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]))).setEnable(DataHandleUtils.getBoolBit5(this.setting_0x71_canBusInfo_Enable[3])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b4").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]))).setEnable(DataHandleUtils.getBoolBit4(this.setting_0x71_canBusInfo_Enable[3])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b3").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]))).setEnable(DataHandleUtils.getBoolBit3(this.setting_0x71_canBusInfo_Enable[3])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b2").setValue(this.getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))).setEnable(DataHandleUtils.getBoolBit2(this.setting_0x71_canBusInfo_Enable[3])));
      var3.add(this.getSettingUpdateEntity("_143_0x76_d1_b01").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)).setEnable(DataHandleUtils.getBoolBit1(this.setting_0x71_canBusInfo_Enable[3])));
      int var2 = this.getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
      System.putInt(this.mContext.getContentResolver(), "left0right3", var2);
      System.putInt(this.mContext.getContentResolver(), "left0right4", var1);
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x79VehicleSet2() {
      ArrayList var4 = new ArrayList();
      SettingUpdateEntity var5 = this.getSettingUpdateEntity("_143_setting_1").setValue(this.mCanBusInfoInt[2] >> 7 & 1);
      byte var1 = this.setting_0x72_canBusInfo_Enable[2];
      boolean var3 = false;
      boolean var2;
      if ((var1 >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_2").setValue(this.mCanBusInfoInt[2] >> 6 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 6 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_4").setValue(this.mCanBusInfoInt[2] >> 5 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 5 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_5").setValue(this.mCanBusInfoInt[2] >> 4 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 4 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_6").setValue(this.mCanBusInfoInt[2] >> 3 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 3 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_7").setValue(this.mCanBusInfoInt[2] >> 2 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 2 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_8").setValue(this.mCanBusInfoInt[2] >> 1 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 1 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_9").setValue(this.mCanBusInfoInt[2] >> 0 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 0 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_10").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2));
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 7 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_11").setValue(this.mCanBusInfoInt[3] >> 5 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 5 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_12").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2));
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 6 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_13").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2));
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 4 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_14").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 4 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_16").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 4)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 4));
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 3 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_20").setValue(this.mCanBusInfoInt[4] >> 1 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 2 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_21").setValue(this.mCanBusInfoInt[4] >> 0 & 1);
      if ((this.setting_0x72_canBusInfo_Enable[3] >> 1 & 1) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.add(var5.setEnable(var2));
      var5 = this.getSettingUpdateEntity("_143_setting_19").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
      var2 = var3;
      if ((this.setting_0x72_canBusInfo_Enable[2] >> 0 & 1) == 1) {
         var2 = true;
      }

      var4.add(var5.setEnable(var2));
      var4.add(this.getSettingUpdateEntity("_143_setting_22").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)).setEnable(DataHandleUtils.getBoolBit0(this.setting_0x72_canBusInfo_Enable[3])));
      var4.add(this.getSettingUpdateEntity("_143_setting_23").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)).setEnable(DataHandleUtils.getBoolBit7(this.setting_0x72_canBusInfo_Enable[4])));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
      this.setting_0x79_canBusInfo_Enable = this.mCanBusInfoInt;
   }

   private void set0x81RememberedSpeedData() {
      byte[] var22 = this.mCanBusInfoByte;
      this.m0x81Command = Arrays.copyOf(var22, var22.length);
      ArrayList var26 = new ArrayList();
      int var8 = this.mCanBusInfoInt[2];
      boolean var1;
      if ((var8 >> 7 & 1) == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      int var13 = DataHandleUtils.getIntFromByteWithBit(var8, 6, 1);
      int[] var23 = this.mCanBusInfoInt;
      int var2 = var23[2];
      int var11 = var2 >> 5 & 1;
      int var10 = var2 >> 4 & 1;
      int var9 = var2 >> 3 & 1;
      int var12 = var2 >> 2 & 1;
      int var19 = var2 >> 1 & 1;
      int var15 = var23[3];
      int var17 = var23[4];
      int var14 = var23[5];
      int var16 = var23[6];
      int var18 = var23[7];
      int var20 = var23[8];
      int var7 = var23[11];
      boolean var21;
      if ((var7 >> 7 & 1) == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      boolean var24;
      if ((var7 >> 6 & 1) == 1) {
         var24 = true;
      } else {
         var24 = false;
      }

      boolean var3;
      if ((var7 >> 5 & 1) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var4;
      if ((var7 >> 4 & 1) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var5;
      if ((var7 >> 3 & 1) == 1) {
         var5 = true;
      } else {
         var5 = false;
      }

      boolean var6;
      if ((var7 >> 2 & 1) == 1) {
         var6 = true;
      } else {
         var6 = false;
      }

      boolean var25;
      if ((var7 >> 1 & 1) == 1) {
         var25 = true;
      } else {
         var25 = false;
      }

      var26.add(this.getSettingUpdateEntity("_143_speed_setting2").setValue(var8 >> 7 & 1).setEnable(var21));
      SettingUpdateEntity var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting3").setValue(var13);
      if (var1 && var24) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting4").setValue(var11);
      if (var1 && var3) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting5").setValue(var10);
      if (var1 && var4) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting6").setValue(var9);
      if (var1 && var5) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting7").setValue(var12);
      if (var1 && var6) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting8").setValue(var19);
      if (var1 && var25) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting9").setValue(var15).setProgress(this.mCanBusInfoInt[3]);
      if (var1 && var24 && var13 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting10").setValue(var17).setProgress(this.mCanBusInfoInt[4]);
      if (var1 && var3 && var11 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting11").setValue(var14).setProgress(this.mCanBusInfoInt[5]);
      if (var1 && var4 && var10 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting12").setValue(var16).setProgress(this.mCanBusInfoInt[6]);
      if (var1 && var5 && var9 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting13").setValue(var18).setProgress(this.mCanBusInfoInt[7]);
      if (var1 && var6 && var12 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x81_setting14").setValue(var20).setProgress(this.mCanBusInfoInt[8]);
      if (var1 && var25 && var19 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      this.updateGeneralSettingData(var26);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x82CruiseSpeedData() {
      byte[] var22 = this.mCanBusInfoByte;
      this.m0x82Command = Arrays.copyOf(var22, var22.length);
      ArrayList var26 = new ArrayList();
      int var8 = this.mCanBusInfoInt[2];
      boolean var1;
      if ((var8 >> 7 & 1) == 1) {
         var1 = true;
      } else {
         var1 = false;
      }

      int var9 = DataHandleUtils.getIntFromByteWithBit(var8, 6, 1);
      int[] var23 = this.mCanBusInfoInt;
      int var2 = var23[2];
      int var13 = var2 >> 5 & 1;
      int var11 = var2 >> 4 & 1;
      int var12 = var2 >> 3 & 1;
      int var10 = var2 >> 2 & 1;
      int var18 = var2 >> 1 & 1;
      int var14 = var23[3];
      int var17 = var23[4];
      int var16 = var23[5];
      int var20 = var23[6];
      int var15 = var23[7];
      int var19 = var23[8];
      int var7 = var23[11];
      boolean var21;
      if ((var7 >> 7 & 1) == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      boolean var24;
      if ((var7 >> 6 & 1) == 1) {
         var24 = true;
      } else {
         var24 = false;
      }

      boolean var3;
      if ((var7 >> 5 & 1) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var4;
      if ((var7 >> 4 & 1) == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      boolean var5;
      if ((var7 >> 3 & 1) == 1) {
         var5 = true;
      } else {
         var5 = false;
      }

      boolean var6;
      if ((var7 >> 2 & 1) == 1) {
         var6 = true;
      } else {
         var6 = false;
      }

      boolean var25;
      if ((var7 >> 1 & 1) == 1) {
         var25 = true;
      } else {
         var25 = false;
      }

      var26.add(this.getSettingUpdateEntity("_143_0x82_setting_1").setValue(var8 >> 7 & 1).setEnable(var21));
      SettingUpdateEntity var27 = this.getSettingUpdateEntity("_143_spee_0x82d_setting3").setValue(var9);
      if (var1 && var24) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting4").setValue(var13);
      if (var1 && var3) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting5").setValue(var11);
      if (var1 && var4) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting6").setValue(var12);
      if (var1 && var5) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting7").setValue(var10);
      if (var1 && var6) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting8").setValue(var18);
      if (var1 && var25) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting9").setValue(var14).setProgress(this.mCanBusInfoInt[3]);
      if (var1 && var24 && var9 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting10").setValue(var17).setProgress(var17);
      if (var1 && var3 && var13 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting11").setValue(var16).setProgress(this.mCanBusInfoInt[5]);
      if (var1 && var4 && var11 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting12").setValue(var20).setProgress(this.mCanBusInfoInt[6]);
      if (var1 && var5 && var12 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting13").setValue(var15).setProgress(this.mCanBusInfoInt[7]);
      if (var1 && var6 && var10 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      var27 = this.getSettingUpdateEntity("_143_speed_0x82_setting14").setValue(var19).setProgress(this.mCanBusInfoInt[8]);
      if (var1 && var25 && var18 == 1) {
         var21 = true;
      } else {
         var21 = false;
      }

      var26.add(var27.setEnable(var21));
      this.updateGeneralSettingData(var26);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x83Setting() {
      int var1 = this.mCanBusInfoInt[2];
      if (var1 != 0) {
         if (var1 == 1) {
            this.contentMsg = this.mContext.getString(2131757459);
         } else {
            this.contentMsg = this.mContext.getString(2131757460);
         }

         if (this.view == null) {
            this.view = LayoutInflater.from(this.getActivity()).inflate(2131558513, (ViewGroup)null, true);
         }

         if (this.dialog == null) {
            this.dialog = (new AlertDialog.Builder(this.getActivity())).setView(this.view).create();
         }

         if (this.content == null) {
            this.content = (TextView)this.view.findViewById(2131361915);
         }

         this.content.setText(this.contentMsg);
         if (this.i_know == null) {
            this.i_know = (Button)this.view.findViewById(2131362380);
         }

         this.i_know.setOnClickListener(new View.OnClickListener(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.dialog.dismiss();
            }
         });
         (new CountDownTimer(this, 3000L, 1000L) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onFinish() {
               this.this$0.dialog.dismiss();
            }

            public void onTick(long var1) {
            }
         }).start();
         this.dialog.setCancelable(false);
         this.dialog.getWindow().setBackgroundDrawableResource(17170445);
         this.dialog.getWindow().setType(2003);
         this.dialog.show();
      } else {
         AlertDialog var2 = this.dialog;
         if (var2 != null) {
            var2.dismiss();
         }
      }

   }

   private void set0x85SportDate() {
      byte[] var1 = this.mCanBusInfoByte;
      this.m0x85Command = Arrays.copyOf(var1, var1.length);
      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(4, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0x93CDInfo() {
      if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
         Context var1 = this.mContext;
         this.startDrivingDataActivity(var1, this.getUiMgr(var1).getDrivingPageIndexes(this.mContext, "_143_0x93_state"));
      }

      ArrayList var2 = new ArrayList();
      if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state1"), "POWER OFF"));
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state2"), "POWER OFF"));
      } else {
         var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state1"), this.getModel(this.mCanBusInfoInt[3])));
         if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state2"), this.mCanBusInfoInt[4] + "LEVER"));
         } else {
            var2.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state2"), "Don't show"));
         }
      }

      this.updateGeneralDriveData(var2);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void set0x97CDInfo() {
      if (this.first0x97 == 1) {
         this.first0x97 = 2;
         if (this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd") != -1) {
            Context var3 = this.mContext;
            this.startDrivingDataActivity(var3, this.getUiMgr(var3).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"));
         }
      }

      ArrayList var6 = new ArrayList();
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_time"), this.mCanBusInfoInt[7] + ":" + this.mCanBusInfoInt[8]));
      int var1 = this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd");
      int var2 = this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_number");
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      StringBuilder var7 = var5.append(this.getMsbLsbResult(var4[6], var4[5])).append("/");
      int[] var8 = this.mCanBusInfoInt;
      var6.add(new DriverUpdateEntity(var1, var2, var7.append(this.getMsbLsbResult(var8[12], var8[11])).toString()));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_state"), this.getCdState(this.mCanBusInfoInt[10])));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_model"), this.getCdPlayModel(this.mCanBusInfoInt[9])));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc1"), this.getHaveNoState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) + this.getNowCd(1, this.mCanBusInfoInt[2]) + this.getCdType(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]))));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc2"), this.getHaveNoState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) + this.getNowCd(2, this.mCanBusInfoInt[2]) + this.getCdType(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]))));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc3"), this.getHaveNoState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) + this.getNowCd(3, this.mCanBusInfoInt[2]) + this.getCdType(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]))));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc4"), this.getHaveNoState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) + this.getNowCd(4, this.mCanBusInfoInt[2]) + this.getCdType(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]))));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc5"), this.getHaveNoState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) + this.getNowCd(5, this.mCanBusInfoInt[2]) + this.getCdType(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]))));
      var6.add(new DriverUpdateEntity(this.getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), this.getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc6"), this.getHaveNoState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) + this.getNowCd(6, this.mCanBusInfoInt[2]) + this.getCdType(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]))));
      this.updateGeneralDriveData(var6);
      this.updateDriveDataActivity((Bundle)null);
      var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_143_add_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_143_add_function", "_143_add_function1"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)));
      var6.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_143_add_function"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_143_add_function", "_143_add_function2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1)));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC1Unit(Context var1) {
      byte[] var5 = this.mCanBusInfoByte;
      this.m0xC1Command = Arrays.copyOf(var5, var5.length);
      int var2 = this.mCanBusInfoInt[3] >> 5;
      boolean var4 = true;
      if (var2 != 0) {
         if (var2 == 1) {
            this.mTemperatureUnit = this.getTempUnitC(var1);
         }
      } else {
         this.mTemperatureUnit = this.getTempUnitF(var1);
      }

      var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2);
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 == 2) {
               this.mFuelUnit = "mpg";
            }
         } else {
            this.mFuelUnit = "km/l";
         }
      } else {
         this.mFuelUnit = "L/100km";
      }

      ArrayList var6 = new ArrayList();
      SettingUpdateEntity var7 = this.getSettingUpdateEntity("vm_golf7_vehicle_setup_units_temperature").setValue(this.mCanBusInfoInt[3] >> 5 & 1);
      boolean var3;
      if ((this.mCanBusInfoInt[2] >> 5 & 1) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      var6.add(var7.setEnable(var3));
      var7 = this.getSettingUpdateEntity("vm_golf7_vehicle_setup_units_consumption").setValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2));
      if ((this.mCanBusInfoInt[2] >> 3 & 1) == 1) {
         var3 = var4;
      } else {
         var3 = false;
      }

      var6.add(var7.setEnable(var3));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   private void set0xC2TimeInfo() {
      String var1;
      if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
         var1 = "TimeFormat:24H  ";
      } else {
         var1 = "TimeFormat:12H  ";
      }

      ArrayList var2 = new ArrayList();
      var2.add((new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_143_0xc2_time"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_143_0xc2_time", "_143_0xc2_time1"), var1 + "20" + this.mCanBusInfoInt[2] + "/" + this.mCanBusInfoInt[3] + "/" + this.mCanBusInfoInt[4] + "   " + this.mCanBusInfoInt[5] + ":" + this.mCanBusInfoInt[6])).setValueStr(true));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void setAirData() {
      GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
      GeneralAirData.mono = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
      boolean var2;
      if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
         var2 = true;
      } else {
         var2 = false;
      }

      GeneralAirData.ac = var2;
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ^ true;
      GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
      GeneralAirData.front_left_blow_window = false;
      GeneralAirData.front_right_blow_window = false;
      GeneralAirData.front_left_blow_foot = false;
      GeneralAirData.front_right_blow_foot = false;
      GeneralAirData.front_left_blow_head = false;
      GeneralAirData.front_right_blow_head = false;
      GeneralAirData.front_auto_wind_model = false;
      int var1;
      if (this.getCurrentCanDifferentId() == 27) {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  switch (var1) {
                     case 11:
                        GeneralAirData.front_left_blow_window = true;
                        break;
                     case 12:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        break;
                     case 13:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        break;
                     case 14:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_left_blow_head = true;
               }
            } else {
               GeneralAirData.front_left_blow_foot = true;
               GeneralAirData.front_left_blow_head = true;
            }
         } else {
            GeneralAirData.front_left_blow_foot = true;
         }

         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
         if (var1 != 3) {
            if (var1 != 5) {
               if (var1 != 6) {
                  switch (var1) {
                     case 11:
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 12:
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 13:
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 14:
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_right_blow_head = true;
                  }
               } else {
                  GeneralAirData.front_right_blow_head = true;
               }
            } else {
               GeneralAirData.front_right_blow_foot = true;
               GeneralAirData.front_right_blow_head = true;
            }
         } else {
            GeneralAirData.front_right_blow_foot = true;
         }
      } else {
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
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
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                     case 14:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
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
      }

      GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
      outDoorTemp = this.mCanBusInfoInt[13];
      if (this.isOnlyOutDoorDataChange()) {
         SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", (float)outDoorTemp);
         this.updateOutDoorTemp(this.mContext, this.resolveOutDoorTem());
      } else {
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setBack() {
      int var1 = this.backLight;
      int var2 = this.mCanBusInfoInt[7];
      if (var1 != var2) {
         this.backLight = var2;
         this.setBacklightLevel(var2 / 3 + 1);
      }

   }

   private void setBackLightUP(boolean var1) {
      int var2;
      if (var1) {
         var2 = this.nowLight;
         if (var2 != 5) {
            this.setBacklightLevel(var2 + 1);
            ++this.nowLight;
         }
      } else {
         var2 = this.nowLight;
         if (var2 != 1) {
            this.setBacklightLevel(var2 - 1);
            --this.nowLight;
         }
      }

   }

   private void setBlackLight0x11() {
      if (this.pinBlackTag != DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])) {
         if (!this.pinBlackTag) {
            this.setBacklightLevel(1);
         } else {
            this.setBacklightLevel(5);
         }

         this.pinBlackTag = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
      }

   }

   private void setCarDoorInfo() {
      GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
      GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
      GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
      GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
      GeneralDoorData.isShowMasterDriverSeatBelt = true;
      GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
      if (this.equal(this.mDifferentId, 12, 52)) {
         GeneralDoorData.isShowCoPilotSeatBelt = true;
         GeneralDoorData.isShowRLSeatBelt = true;
         GeneralDoorData.isShowRMSeatBelt = true;
         GeneralDoorData.isShowRRSeatBelt = true;
         GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
         GeneralDoorData.isSeatRLBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
         GeneralDoorData.isSeatRMBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
         GeneralDoorData.isSeatRRBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
      }

      if (this.isOnlyDoorMsgShow()) {
         SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
         SharePreUtil.setBoolValue(this.mContext, "is_seat_master_driver_belt_tie", GeneralDoorData.isSeatMasterDriverBeltTie);
         SharePreUtil.setBoolValue(this.mContext, "is_seat_co_pilot_belt_tie", GeneralDoorData.isSeatCoPilotBeltTie);
         SharePreUtil.setBoolValue(this.mContext, "is_seat_r_l_belt_tie", GeneralDoorData.isSeatRLBeltTie);
         SharePreUtil.setBoolValue(this.mContext, "is_seat_r_m_belt_tie", GeneralDoorData.isSeatRMBeltTie);
         SharePreUtil.setBoolValue(this.mContext, "is_seat_r_r_belt_tie", GeneralDoorData.isSeatRRBeltTie);
         this.updateDoorView(this.mContext);
      }

   }

   private void setCarStatusInfo() {
      int var1 = this.mCanBusInfoInt[3];
      String var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     var2 = "";
                  } else {
                     var2 = "D";
                  }
               } else {
                  var2 = "R";
               }
            } else {
               var2 = "N";
            }
         } else {
            var2 = "P";
         }
      } else {
         var2 = this.mContext.getResources().getString(2131768909);
      }

      if (this.mCanBusInfoInt[3] == 4) {
         this.isDGear = true;
      } else {
         this.isDGear = false;
      }

      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingComputerInfo0() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 0, var3.append((float)((double)(var2[2] * 256 + var2[3]) * 0.1)).append(this.mFuelUnit).toString()));
      var3 = new StringBuilder();
      var2 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(1, 1, var3.append(var2[4] * 256 + var2[5]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void setDrivingComputerInfo1() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(2, 0, var2.append((float)((double)(var3[2] * 256 + var3[3]) * 0.1)).append(this.mFuelUnit).toString()));
      var1.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[5] + "km/h"));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(2, 2, var5.append(var4[6] * 256 + var4[7]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[5]);
   }

   private void setDrivingComputerInfo2() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      int[] var3 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 0, var2.append((float)((double)(var3[2] * 256 + var3[3]) * 0.1)).append(this.mFuelUnit).toString()));
      var1.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "km/h"));
      StringBuilder var5 = new StringBuilder();
      int[] var4 = this.mCanBusInfoInt;
      var1.add(new DriverUpdateEntity(3, 2, var5.append(var4[6] * 256 + var4[7]).append("km").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      this.updateSpeedInfo(this.mCanBusInfoInt[5]);
   }

   private void setEpsTrackInfo0x11() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(var1[9], var1[8], 0, 540, 16);
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setFrontRearRadar() {
      GeneralParkData.isShowDistanceNotShowLocationUi = false;
      RadarInfoUtil.mMinIsClose = true;
      RadarInfoUtil.mDisableData = 8;
      int[] var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setRearRadarLocationData(7, var1[2] + 1, var1[3] + 1, var1[4] + 1, var1[5] + 1);
      var1 = this.mCanBusInfoInt;
      RadarInfoUtil.setFrontRadarLocationData(7, var1[6] + 1, var1[7] + 1, var1[8] + 1, var1[9] + 1);
      GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
      this.updateParkUi((Bundle)null, this.mContext);
   }

   private void setLanguage() {
      int var2 = this.mCanBusInfoInt[2];
      int var1 = 16;
      if (var2 <= 16 && var2 != 0) {
         var1 = var2 - 1;
      } else {
         switch (var2) {
            case 18:
               break;
            case 19:
            case 21:
            case 24:
            case 27:
            default:
               var1 = 0;
               break;
            case 20:
               var1 = 17;
               break;
            case 22:
               var1 = 18;
               break;
            case 23:
               var1 = 19;
               break;
            case 25:
               var1 = 21;
               break;
            case 26:
               var1 = 20;
               break;
            case 28:
               var1 = 22;
               break;
            case 29:
               var1 = 23;
               break;
            case 30:
               var1 = 24;
               break;
            case 31:
               var1 = 25;
               break;
            case 32:
               var1 = 26;
               break;
            case 33:
               var1 = 27;
         }
      }

      language = var2;
      ArrayList var3 = new ArrayList();
      var3.add(this.getSettingUpdateEntity("_143_0x94_language").setValue(var1));
      this.updateGeneralSettingData(var3);
      this.updateSettingActivity((Bundle)null);
   }

   private void setLightMode() {
      int var1 = this.nowLightMode;
      if (var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 125, 10, 0});
         this.nowLightMode = 1;
      } else if (var1 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 125, 10, 1});
         this.nowLightMode = 2;
      } else if (var1 == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, 125, 10, 2});
         this.nowLightMode = 0;
      }

   }

   private void setOriginalPanel() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 == 1) {
         if (var2[3] > voice_btn_data) {
            this.realKeyClick4(7);
            voice_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < voice_btn_data) {
            this.realKeyClick4(8);
            voice_btn_data = this.mCanBusInfoInt[3];
         }
      } else if (var1 == 2) {
         if (var2[3] > up_dn_btn_data) {
            this.realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      } else if (var1 == 3) {
         if (var2[3] > up_dn_btn_data) {
            this.realKeyClick4(20);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(21);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      } else if (var1 == 4) {
         if (var2[3] > up_dn_btn_data) {
            this.realKeyClick4(48);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(47);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      } else if (var1 == 5) {
         if (var2[3] > up_dn_btn_data) {
            this.realKeyClick4(33);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }

         if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            this.realKeyClick4(34);
            up_dn_btn_data = this.mCanBusInfoInt[3];
         }
      }

   }

   private void setPanelButton(int[] var1) {
      int var2 = this.mDifferentId;
      if (var2 != 3) {
         if (var2 != 4 && var2 != 5) {
            if (var2 != 9) {
               if (var2 != 10) {
                  if (var2 != 12) {
                     if (var2 != 13) {
                        if (var2 != 15) {
                           if (var2 != 16) {
                              if (var2 != 20) {
                                 if (var2 != 21) {
                                    if (var2 != 27) {
                                       if (var2 != 65) {
                                          if (var2 != 52) {
                                             if (var2 != 53) {
                                                this.otherCar();
                                             } else {
                                                this.car_DS5L_And_DS6_Low();
                                             }
                                          } else {
                                             this.car_DS5_Low();
                                          }
                                       } else {
                                          this.car_DS4_Low();
                                       }
                                    } else {
                                       this.car_407_Year_2006();
                                    }
                                 } else {
                                    this.car_508_Year_2015_have_amplifier_High();
                                 }
                              } else {
                                 this.car_4008_Year_2017();
                              }
                           } else {
                              this.car_408_Year_2014();
                           }
                        } else {
                           this.car_DS4_High();
                        }
                     } else {
                        this.car_DS5L_And_DS6_High();
                     }
                  } else {
                     this.car_DS5_High();
                  }
               } else {
                  this.car_508_High();
               }
            } else {
               this.car_508_Low();
            }
         } else {
            this.car_C5();
         }
      } else {
         this.car_C4L();
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWarningInfo(Context var1) {
      String var3 = Integer.toHexString(this.mCanBusInfoInt[2]);
      String var2 = var3;
      if (var3.length() == 1) {
         var2 = "0" + var3;
      }

      String var4 = Integer.toHexString(this.mCanBusInfoInt[3]);
      var3 = var4;
      if (var4.length() == 1) {
         var3 = "0" + var4;
      }

      var2 = "psa_hiword_" + var2 + var3;
      ArrayList var5 = new ArrayList();
      var5.add(new WarningEntity(CommUtil.getStrByResId(this.mContext, var2)));
      if (var5.size() > 0 && !var2.equals(var1.getString(2131769668)) && CommUtil.getStrByResId(this.mContext, var2) != null) {
         this.startWarningActivity(this.mContext);
      }

      GeneralWarningDataData.dataList = var5;
      this.updateWarningActivity((Bundle)null);
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void run() {
            if (SystemUtil.isForeground(this.val$context, WarningActivity.class.getName())) {
               this.this$0.finishActivity();
            }

         }
      }, 5000L);
   }

   private void settingLeft0Right12Data(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(new SettingUpdateEntity(0, 12, var1));
      this.updateGeneralSettingData(var2);
      this.updateSettingActivity((Bundle)null);
   }

   private void showRadarOrNot(byte[] var1) {
      if (!this.isRadarMsgReturn(var1)) {
         boolean var2 = this.isDGear;
         if (var2) {
            this.forceReverse(this.mContext, var2);
            this.startTask();
         }
      }

   }

   private void startDrivingPage() {
      Log.d("cwh", "startDrivingPage");
      int var1 = this.nextItem;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.currentItem = 3;
               this.nextItem = 0;
               this.startDrivingDataActivity(this.mContext, 3);
            }
         } else {
            this.currentItem = 2;
            this.nextItem = 2;
            this.startDrivingDataActivity(this.mContext, 2);
         }
      } else {
         this.currentItem = 1;
         this.nextItem = 1;
         this.startDrivingDataActivity(this.mContext, 1);
      }

   }

   private void startTask() {
      Timer var1 = this.mTimer;
      if (var1 == null) {
         var1 = new Timer();
         this.mTimer = var1;
         var1.schedule(new TimerTask(this) {
            final MsgMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               MsgMgr var1 = this.this$0;
               var1.forceReverse(var1.mContext, false);
            }
         }, 5000L);
      } else {
         var1.cancel();
         this.mTimer = null;
         this.startTask();
      }

   }

   private void switch0xA2Method(String var1, String var2, int var3) {
      byte var6;
      int var7;
      label24: {
         boolean var8 = var1.equals("FM1");
         var6 = 1;
         var7 = 1;
         double var4;
         if (var8) {
            var4 = Double.parseDouble(var2);
            var6 = (byte)var7;
         } else if (var1.equals("FM2")) {
            var6 = 2;
            var4 = Double.parseDouble(var2);
         } else {
            if (!var1.equals("FM3")) {
               if (var1.equals("AM1")) {
                  var6 = 4;
                  var7 = Integer.parseInt(var2);
               } else if (var1.equals("AM2")) {
                  var6 = 5;
                  var7 = Integer.parseInt(var2);
               } else {
                  var7 = 0;
               }
               break label24;
            }

            var6 = 3;
            var4 = Double.parseDouble(var2);
         }

         var7 = (int)(var4 * 100.0);
      }

      this.getUiMgr(this.mContext).send0xA2Info(var6, this.getLsb(var7), this.getMsb(var7), var3);
   }

   private void switch0xA3Method(String var1, String var2, int var3) {
      byte var4;
      boolean var5;
      label44: {
         boolean var6 = var1.equals("FM1");
         var5 = true;
         var4 = 0;
         if (var6) {
            var4 = 2;
         } else {
            if (!var1.equals("FM2")) {
               if (!var1.equals("FM3") && (var1.equals("AM1") || var1.equals("AM2"))) {
                  var4 = 1;
               } else {
                  var5 = false;
               }
               break label44;
            }

            var4 = 3;
         }

         var5 = false;
      }

      int var7;
      if (var5) {
         var7 = Integer.parseInt(var2);
      } else {
         var7 = (int)(Double.parseDouble(var2) * 100.0);
      }

      switch (var3) {
         case 1:
            this.getUiMgr(this.mContext).data1_0xA3 = this.getLsb(var7);
            this.getUiMgr(this.mContext).data2_0xA3 = this.getMsb(var7);
            break;
         case 2:
            this.getUiMgr(this.mContext).data3_0xA3 = this.getLsb(var7);
            this.getUiMgr(this.mContext).data4_0xA3 = this.getMsb(var7);
            break;
         case 3:
            this.getUiMgr(this.mContext).data5_0xA3 = this.getLsb(var7);
            this.getUiMgr(this.mContext).data6_0xA3 = this.getMsb(var7);
            break;
         case 4:
            this.getUiMgr(this.mContext).data7_0xA3 = this.getLsb(var7);
            this.getUiMgr(this.mContext).data8_0xA3 = this.getMsb(var7);
            break;
         case 5:
            this.getUiMgr(this.mContext).data9_0xA3 = this.getLsb(var7);
            this.getUiMgr(this.mContext).data10_0xA3 = this.getMsb(var7);
            break;
         case 6:
            this.getUiMgr(this.mContext).data11_0xA3 = this.getLsb(var7);
            this.getUiMgr(this.mContext).data12_0xA3 = this.getMsb(var7);
      }

      this.getUiMgr(this.mContext).send0xA3Info(var4);
   }

   private void switch0xE1Method(String var1, String var2, int var3) {
      int var5 = var3;
      if (var3 == 256) {
         var5 = 0;
      }

      byte var4 = this.getAllBandTypeData(var1, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var1)) {
         if (var2.length() == 4) {
            var1 = (new DecimalFormat("00")).format((long)var5) + " " + var2 + "     ";
         } else {
            var1 = (new DecimalFormat("00")).format((long)var5) + " 0" + var2 + "     ";
         }
      } else if (var2.length() == 5) {
         var1 = (new DecimalFormat("00")).format((long)var5) + "  " + var2.substring(0, var2.length() - 1) + "    ";
      } else {
         var1 = (new DecimalFormat("00")).format((long)var5) + " " + var2.substring(0, var2.length() - 1) + "    ";
      }

      byte[] var6 = var1.getBytes();
      var6 = DataHandleUtils.byteMerger(new byte[]{22, -31, var4}, var6);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), var6);
   }

   private void toCarPc() {
      int var1 = this.nowPcState;
      if (var1 == 1) {
         this.startDrivingDataActivity(this.mContext, 1);
         this.nowPcState = 2;
      } else if (var1 == 2) {
         this.startDrivingDataActivity(this.mContext, 2);
         this.nowPcState = 3;
      } else if (var1 == 3) {
         this.startDrivingDataActivity(this.mContext, 3);
         this.nowPcState = 1;
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mEachId = this.getCurrentEachCanId();
      this.mDifferentId = this.getCurrentCanDifferentId();
      SelectCanTypeUtil.enableApp(var1, Constant.OriginalDeviceActivity);
      this.mUiMgr = this.getUiMgr(var1);
      this.initSettingsItem(var1);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      this.getUiMgr(this.mContext).Data1_0xA1 = 8;
      this.getUiMgr(this.mContext).send0xA1HostState();
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).Data1_0xA1 = 3;
      this.getUiMgr(this.mContext).send0xA1HostState();
   }

   public void btMusicId3InfoChange(String var1, String var2, String var3) {
      super.btMusicId3InfoChange(var1, var2, var3);
      this.reportID3(var1);
      this.getUiMgr(this.mContext).Data1_0xA1 = 9;
      this.getUiMgr(this.mContext).send0xA1HostState();
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = new byte[9];
      Arrays.fill(var1, (byte)32);
      java.lang.System.arraycopy("A2DP".getBytes(), 0, var1, 0, "A2DP".getBytes().length);
      var1 = DataHandleUtils.byteMerger("000".getBytes(), var1);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, var1));
   }

   public void btPhoneStatusInfoChange(int var1, byte[] var2, boolean var3, boolean var4, boolean var5, boolean var6, int var7, int var8, Bundle var9) {
      super.btPhoneStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.getUiMgr(this.mContext).Data1_0xA1 = 7;
      this.getUiMgr(this.mContext).send0xA1HostState();
   }

   public void btPhoneTalkingWithTimeInfoChange(byte[] var1, boolean var2, boolean var3, int var4) {
      super.btPhoneTalkingWithTimeInfoChange(var1, var2, var3, var4);
      var1 = ("    " + (new DecimalFormat("00")).format((long)(var4 / 60 % 60)) + (new DecimalFormat("00")).format((long)(var4 % 60)) + "    ").getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var5 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var5;
      Integer var4 = 1;
      int var3 = var5[1];
      if (var3 != 33) {
         if (var3 != 34) {
            if (var3 != 49) {
               if (var3 != 118) {
                  if (var3 != 121) {
                     if (var3 != 133) {
                        if (var3 != 151) {
                           if (var3 != 240) {
                              if (var3 != 65) {
                                 if (var3 != 66) {
                                    if (var3 != 113) {
                                       if (var3 != 114) {
                                          if (var3 != 147) {
                                             if (var3 != 148) {
                                                if (var3 != 193) {
                                                   if (var3 != 194) {
                                                      switch (var3) {
                                                         case 17:
                                                            this.keyControl0x11();
                                                            this.setBack();
                                                            this.setEpsTrackInfo0x11();
                                                            this.setBlackLight0x11();
                                                            break;
                                                         case 18:
                                                            if (this.isDoorMsgReturn(var2)) {
                                                               return;
                                                            }

                                                            this.setCarDoorInfo();
                                                            this.setCarStatusInfo();
                                                            break;
                                                         case 19:
                                                            this.setDrivingComputerInfo0();
                                                            break;
                                                         case 20:
                                                            this.setDrivingComputerInfo1();
                                                            break;
                                                         case 21:
                                                            this.setDrivingComputerInfo2();
                                                            break;
                                                         default:
                                                            switch (var3) {
                                                               case 129:
                                                                  if (this.equal(this.mDifferentId, 3, 14, 16, 26)) {
                                                                     this.set0x81RememberedSpeedData();
                                                                  }
                                                                  break;
                                                               case 130:
                                                                  if (this.equal(this.mDifferentId, 16, 26)) {
                                                                     this.set0x82CruiseSpeedData();
                                                                  }
                                                                  break;
                                                               case 131:
                                                                  if (this.equal(this.mDifferentId, 16)) {
                                                                     if (this.is0x83MsgRepeat(var2)) {
                                                                        return;
                                                                     }

                                                                     this.set0x83Setting();
                                                                  }
                                                            }
                                                      }
                                                   } else {
                                                      this.set0xC2TimeInfo();
                                                   }
                                                } else {
                                                   this.set0xC1Unit(var1);
                                                }
                                             } else {
                                                this.setLanguage();
                                             }
                                          } else {
                                             this.set0x93CDInfo();
                                          }
                                       } else {
                                          this.set0x72CarSetEnable(var1);
                                       }
                                    } else {
                                       this.setting_0x71_canBusInfo_Enable = var5;
                                       this.mCanBusInfoInt = this.setting_0x76_canBusInfo_Enable;
                                       this.set0x76VehicleSet();
                                    }
                                 } else {
                                    if (this.equal(this.mDifferentId, 4, 5, 12, 52, 13, 53, 23)) {
                                       return;
                                    }

                                    if (this.isWarningMsgReturn(var2)) {
                                       return;
                                    }

                                    this.setWarningInfo(var1);
                                 }
                              } else {
                                 this.setFrontRearRadar();
                                 this.showRadarOrNot(var2);
                              }
                           } else {
                              this.setVersionInfo();
                           }
                        } else {
                           this.set0x97CDInfo();
                        }
                     } else if (this.equal(this.mDifferentId, 16)) {
                        this.set0x85SportDate();
                     }
                  } else if (this.equal(this.mDifferentId, var4, 2, 3, 11, 16, 19, 20, 21, 13, 53, 24, 25, 26)) {
                     this.set0x79VehicleSet2();
                  }
               } else {
                  if (this.equal(this.mDifferentId, var4, 2, 3, 11, 13, 53, 14, 15, 65, 16, 18, 19, 20, 24, 25, 26)) {
                     this.set0x76VehicleSet();
                  }

                  this.setting_0x76_canBusInfo_Enable = this.mCanBusInfoInt;
               }
            } else {
               if (this.isAirMsgReturn(var2)) {
                  return;
               }

               this.setAirData();
            }
         } else {
            if (this.isBtnPanelMsgReturn(var2)) {
               return;
            }

            this.setOriginalPanel();
         }
      } else {
         this.setPanelButton(var5);
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.getUiMgr(this.mContext).D0B5_0xA1 = 1;
      this.getUiMgr(this.mContext).D0B6_0xA1 = 1;
      if (var2) {
         this.getUiMgr(this.mContext).Data2_0xA1 = 0;
      } else {
         this.getUiMgr(this.mContext).Data2_0xA1 = var1 * 6;
      }

      this.getUiMgr(this.mContext).send0xA1HostState();
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, (boolean)var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var10, 0, 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -92, 1, 1, 0, (byte)this.getLsb(var5), (byte)this.getMsb(var5), (byte)(var2 / 60), (byte)(var2 % 60), 32, 2, (byte)this.getLsb(var6), (byte)this.getMsb(var6)});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.getUiMgr(this.mContext).Data1_0xA1 = 8;
      this.getUiMgr(this.mContext).send0xA1HostState();
   }

   byte[] get0x72Enable() {
      return this.m0x72Enable1;
   }

   byte[] get0x81Data() {
      return this.m0x81Command;
   }

   byte[] get0x82Data() {
      return this.m0x82Command;
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      IntentFilter var2 = new IntentFilter();
      var2.addAction("REVERSING_SOUND");
      var1.registerReceiver(this.receiver, var2);
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)this.getCurrentEachCanId(), 0});
      this.initAmplifierData();
      this.getUiMgr(var1).D0B7_0xA1 = 1;
      this.getUiMgr(var1).send0xA1HostState();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = (new DecimalFormat("00")).format((long)var6);
      var12 = (new DecimalFormat("00")).format((long)var7);
      byte[] var25 = (String.format((new DecimalFormat("000")).format((long)(var9 * 256 + var3))) + " " + var11 + var12 + "    ").getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), var25);
      this.reportID3(var21);
      if (var1 == 9) {
         this.getUiMgr(this.mContext).Data1_0xA1 = 2;
         this.getUiMgr(this.mContext).send0xA1HostState();
      } else {
         this.getUiMgr(this.mContext).Data1_0xA1 = 6;
         this.getUiMgr(this.mContext).send0xA1HostState();
      }

   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      this.switch0xE1Method(var2, var3, var1);
      this.getUiMgr(this.mContext).Data1_0xA1 = 1;
      this.getUiMgr(this.mContext).send0xA1HostState();
      this.switch0xA2Method(var2, var3, var1);
      this.switch0xA3Method(var2, var3, var1);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.bytes = new byte[]{22, -31};
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.compensateZero(this.bytes, 15));
   }

   public void toast(String var1) {
      this.runOnUi(new CallBackInterface(this, var1) {
         final MsgMgr this$0;
         final String val$contentMsg;

         {
            this.this$0 = var1;
            this.val$contentMsg = var2;
         }

         public void callback() {
            Toast.makeText(this.this$0.getActivity(), this.val$contentMsg, 0).show();
         }
      });
   }

   public void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void updateSettings(Context var1, int var2, int var3, String var4, int var5) {
      SharePreUtil.setIntValue(var1, var4, var5);
      ArrayList var6 = new ArrayList();
      var6.add(new SettingUpdateEntity(var2, var3, var5));
      this.updateGeneralSettingData(var6);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = (new DecimalFormat("00")).format((long)var6);
      var11 = (new DecimalFormat("00")).format((long)var7);
      byte[] var18 = (String.format((new DecimalFormat("000")).format((long)((var9 & 255) * 256 + (var3 & 255)))) + " " + var8 + var11 + "    ").getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -31, 13}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), var18);
      if (var1 == 9) {
         this.getUiMgr(this.mContext).Data1_0xA1 = 2;
         this.getUiMgr(this.mContext).send0xA1HostState();
      } else {
         this.getUiMgr(this.mContext).Data1_0xA1 = 6;
         this.getUiMgr(this.mContext).send0xA1HostState();
      }

   }
}
