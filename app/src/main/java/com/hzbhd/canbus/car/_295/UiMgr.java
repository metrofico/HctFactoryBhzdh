package com.hzbhd.canbus.car._295;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private int currentClickFront;
   private int currentClickRear;
   private int currentWindLv;
   private Context mContext;
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_cold_level - 1, 37);
      }

      public void onClickPlus() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_cold_level + 1, 37);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_heat_level - 1, 33);
      }

      public void onClickPlus() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_left_seat_heat_level + 1, 33);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_cold_level - 1, 38);
      }

      public void onClickPlus() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_cold_level + 1, 38);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_heat_level - 1, 34);
      }

      public void onClickPlus() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.front_right_seat_heat_level + 1, 34);
      }
   };
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         boolean var4 = GeneralAirData.fahrenheit_celsius;
         short var2 = 254;
         int var1;
         int var3;
         if (var4) {
            var3 = (int)(MsgMgr.leftFrontTemp - 1.0F);
            var1 = var2;
            if (MsgMgr.leftFrontTemp != 32.0F) {
               if (MsgMgr.leftFrontTemp == 254.0F) {
                  var1 = var2;
               } else {
                  var1 = var3;
               }
            }

            if (MsgMgr.leftFrontTemp == 255.0F) {
               var1 = 59;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte)var1});
         } else {
            var3 = (int)(MsgMgr.leftFrontTemp - 2.0F);
            var1 = var2;
            if (MsgMgr.leftFrontTemp != 122.0F) {
               if (MsgMgr.leftFrontTemp == 254.0F) {
                  var1 = var2;
               } else {
                  var1 = var3;
               }
            }

            if (MsgMgr.leftFrontTemp == 255.0F) {
               var1 = 170;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte)var1});
         }

      }

      public void onClickUp() {
         boolean var5 = GeneralAirData.fahrenheit_celsius;
         short var3 = 255;
         int var2;
         int var4;
         if (var5) {
            var4 = (int)(MsgMgr.leftFrontTemp + 1.0F);
            var2 = var3;
            if (MsgMgr.leftFrontTemp != 59.0F) {
               if (MsgMgr.leftFrontTemp == 255.0F) {
                  var2 = var3;
               } else {
                  var2 = var4;
               }
            }

            if (MsgMgr.leftFrontTemp == 254.0F) {
               var2 = 32;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte)var2});
         } else {
            var4 = (int)(MsgMgr.leftFrontTemp + 2.0F);
            var2 = var3;
            if (MsgMgr.leftFrontTemp != 170.0F) {
               if (MsgMgr.leftFrontTemp == 255.0F) {
                  var2 = var3;
               } else {
                  var2 = var4;
               }
            }

            float var1 = MsgMgr.leftFrontTemp;
            byte var6 = 122;
            if (var1 == 254.0F) {
               var2 = 122;
            }

            if (var2 == 123) {
               var2 = var6;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte)var2});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         boolean var4 = GeneralAirData.fahrenheit_celsius;
         short var2 = 254;
         int var1;
         int var3;
         if (var4) {
            var3 = (int)(MsgMgr.rightFrontTemp - 1.0F);
            var1 = var2;
            if (MsgMgr.rightFrontTemp != 32.0F) {
               if (MsgMgr.rightFrontTemp == 254.0F) {
                  var1 = var2;
               } else {
                  var1 = var3;
               }
            }

            if (MsgMgr.rightFrontTemp == 255.0F) {
               var1 = 59;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte)var1});
         } else {
            var3 = (int)(MsgMgr.rightFrontTemp - 2.0F);
            var1 = var2;
            if (MsgMgr.rightFrontTemp != 122.0F) {
               if (MsgMgr.rightFrontTemp == 254.0F) {
                  var1 = var2;
               } else {
                  var1 = var3;
               }
            }

            if (MsgMgr.rightFrontTemp == 255.0F) {
               var1 = 170;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte)var1});
         }

      }

      public void onClickUp() {
         boolean var5 = GeneralAirData.fahrenheit_celsius;
         short var3 = 255;
         int var2;
         int var4;
         if (var5) {
            var4 = (int)(MsgMgr.rightFrontTemp + 1.0F);
            var2 = var3;
            if (MsgMgr.rightFrontTemp != 59.0F) {
               if (MsgMgr.rightFrontTemp == 255.0F) {
                  var2 = var3;
               } else {
                  var2 = var4;
               }
            }

            if (MsgMgr.rightFrontTemp == 254.0F) {
               var2 = 32;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte)var2});
         } else {
            var4 = (int)(MsgMgr.rightFrontTemp + 2.0F);
            var2 = var3;
            if (MsgMgr.rightFrontTemp != 170.0F) {
               if (MsgMgr.rightFrontTemp == 255.0F) {
                  var2 = var3;
               } else {
                  var2 = var4;
               }
            }

            float var1 = MsgMgr.rightFrontTemp;
            byte var6 = 122;
            if (var1 == 254.0F) {
               var2 = 122;
            }

            if (var2 == 123) {
               var2 = var6;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte)var2});
         }

      }
   };
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
         } else {
            var1 = this.this$0.currentWindLv;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     this.this$0.currentWindLv = 0;
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 2});
                  }
               } else {
                  this.this$0.currentWindLv = 2;
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 1});
               }
            } else {
               this.this$0.currentWindLv = 1;
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 0});
            }
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirBtnCtrl(2, GeneralAirData.power);
      }
   };
   private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.currentClickFront = 0;
                           CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
                        }
                     } else {
                        this.this$0.currentClickFront = 5;
                        this.this$0.sendAirClick(26);
                     }
                  } else {
                     this.this$0.currentClickFront = 4;
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
                  }
               } else {
                  this.this$0.currentClickFront = 3;
                  this.this$0.sendAirClick(25);
               }
            } else {
               this.this$0.currentClickFront = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(24);
         }

      }

      public void onRightSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.currentClickFront = 0;
                           CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
                        }
                     } else {
                        this.this$0.currentClickFront = 5;
                        this.this$0.sendAirClick(26);
                     }
                  } else {
                     this.this$0.currentClickFront = 4;
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
                  }
               } else {
                  this.this$0.currentClickFront = 3;
                  this.this$0.sendAirClick(25);
               }
            } else {
               this.this$0.currentClickFront = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(24);
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAirBtnCtrl(36, GeneralAirData.seat_steering_wheel_synchronization);
                  }
               } else {
                  this.this$0.sendAirBtnCtrl(35, GeneralAirData.steering_wheel_heating);
               }
            } else {
               this.this$0.sendAirBtnCtrl(32, GeneralAirData.clean_air);
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, (byte)(GeneralAirData.rear_lock ^ 1)});
         }

      }
   };
   private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     GeneralAirData.in_out_cycle ^= true;
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, (byte)(GeneralAirData.in_out_cycle ^ 1)});
                     Log.d("cwh", "GeneralAirData.in_out_cycle     " + GeneralAirData.in_out_cycle);
                  }
               } else {
                  this.this$0.sendAirBtnCtrl(15, GeneralAirData.ac);
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
            }
         } else {
            this.this$0.sendAirBtnCtrl(17, GeneralAirData.sync);
         }

      }
   };
   private OnSettingItemSelectListener mOnItemSelectedListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         switch (var1) {
            case 0:
               switch (var3) {
                  case 0:
                     this.this$0.sendDrivingDataMsg(1);
                     return;
                  case 1:
                     this.this$0.sendDrivingDataMsg(2);
                     return;
                  case 2:
                     this.this$0.sendDrivingDataMsg(3);
                     return;
                  case 3:
                     this.this$0.sendDrivingDataMsg(4);
                     return;
                  case 4:
                     this.this$0.sendDrivingDataMsg(5);
                     return;
                  case 5:
                     this.this$0.sendDrivingDataMsg(6);
                     return;
                  case 6:
                     this.this$0.sendDrivingDataMsg(8);
                     return;
                  case 7:
                     this.this$0.sendDrivingDataMsg(9);
                     return;
                  default:
                     return;
               }
            case 1:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 3) {
                           if (var2 != 4) {
                              if (var2 == 5) {
                                 this.this$0.sendSettingLeft1Msg(6, var3);
                              }
                           } else {
                              this.this$0.sendSettingLeft1Msg(5, var3);
                           }
                        } else {
                           this.this$0.sendSettingLeft1Msg(4, var3);
                        }
                     } else {
                        this.this$0.sendSettingLeft1Msg(3, var3);
                     }
                  } else {
                     this.this$0.sendSettingLeft1Msg(2, var3);
                  }
               } else {
                  this.this$0.sendSettingLeft1Msg(1, var3);
               }
               break;
            case 2:
               switch (var2) {
                  case 0:
                     this.this$0.sendSettingLeft2Msg(2, var3);
                     return;
                  case 1:
                     this.this$0.sendSettingLeft2Msg(1, var3);
                     return;
                  case 2:
                     this.this$0.sendSettingLeft2Msg(4, var3);
                     return;
                  case 3:
                     this.this$0.sendSettingLeft2Msg(5, var3);
                     return;
                  case 4:
                     this.this$0.sendSettingLeft2Msg(6, var3);
                     return;
                  case 5:
                     this.this$0.sendSettingLeft2Msg(7, var3);
                     return;
                  case 6:
                     this.this$0.sendSettingLeft2Msg(8, var3);
                     return;
                  case 7:
                     this.this$0.sendSettingLeft2Msg(9, var3);
                     return;
                  case 8:
                     this.this$0.sendSettingLeft2Msg(3, var3);
                     return;
                  default:
                     return;
               }
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -118, 3, (byte)var3});
               break;
            case 4:
               if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 75, 2, (byte)var3});
                  if (var3 == 1) {
                     MsgMgr.carSpeedWarning = 1;
                  } else {
                     MsgMgr.carSpeedWarning = 0;
                  }
               }

               if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte)var3});
               }
               break;
            case 5:
               switch (var2) {
                  case 0:
                     this.this$0.sendSettingLeft5Msg(9, var3 + 1);
                     return;
                  case 1:
                     this.this$0.sendSettingLeft5Msg(11, var3);
                     return;
                  case 2:
                     this.this$0.sendSettingLeft5Msg(8, var3 + 1);
                     return;
                  case 3:
                     this.this$0.sendSettingLeft5Msg(1, var3);
                     return;
                  case 4:
                     this.this$0.sendSettingLeft5Msg(2, var3);
                     return;
                  case 5:
                     this.this$0.sendSettingLeft5Msg(3, var3);
                     return;
                  case 6:
                     this.this$0.sendSettingLeft5Msg(12, var3);
                     return;
                  case 7:
                     this.this$0.sendSettingLeft5Msg(4, var3);
                     return;
                  case 8:
                     this.this$0.sendSettingLeft5Msg(5, var3);
                     return;
                  case 9:
                     this.this$0.sendSettingLeft5Msg(6, var3);
                     return;
                  case 10:
                     this.this$0.sendSettingLeft5Msg(13, var3);
                     return;
                  case 11:
                     this.this$0.sendSettingLeft5Msg(7, var3);
                     return;
                  case 12:
                     this.this$0.sendSettingLeft5Msg(10, var3);
                     return;
                  default:
                     return;
               }
            case 6:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 3) {
                           if (var2 != 4) {
                              if (var2 != 10) {
                                 if (var2 == 11) {
                                    this.this$0.sendSettingLeft6Msg(12, var3);
                                 }
                              } else {
                                 this.this$0.sendSettingLeft6Msg(10, var3);
                              }
                           } else {
                              this.this$0.sendSettingLeft6Msg(11, var3);
                           }
                        } else {
                           this.this$0.sendSettingLeft6Msg(9, var3);
                        }
                     } else {
                        this.this$0.sendSettingLeft6Msg(8, var3);
                     }
                  } else {
                     this.this$0.sendSettingLeft6Msg(1, var3);
                  }
               } else {
                  this.this$0.sendSettingLeft6Msg(7, 2 - var3);
               }
               break;
            case 7:
               switch (var2) {
                  case 0:
                     this.this$0.sendSettingLeft7Msg(3, var3);
                     return;
                  case 1:
                     this.this$0.sendSettingLeft7Msg(6, var3);
                     return;
                  case 2:
                     this.this$0.sendSettingLeft7Msg(4, var3);
                     return;
                  case 3:
                     this.this$0.sendSettingLeft7Msg(5, var3);
                     return;
                  case 4:
                     this.this$0.sendSettingLeft7Msg(1, var3);
                     return;
                  case 5:
                     this.this$0.sendSettingLeft7Msg(2, var3);
                     return;
                  case 6:
                     this.this$0.sendSettingLeft7Msg(16, var3);
                     return;
                  default:
                     return;
               }
            case 8:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 2) {
                        this.this$0.sendSettingLeft7Msg(17, var3);
                     }
                  } else {
                     this.this$0.sendSettingLeft7Msg(18, var3 + 1);
                  }
               } else {
                  this.this$0.sendSettingLeft7Msg(12, var3);
               }
               break;
            case 9:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 3) {
                           if (var2 == 4) {
                              this.this$0.sendSettingLeft9Msg(5, var3);
                           }
                        } else {
                           this.this$0.sendSettingLeft9Msg(4, var3);
                        }
                     } else {
                        this.this$0.sendSettingLeft9Msg(3, var3);
                     }
                  } else {
                     this.this$0.sendSettingLeft9Msg(2, var3);
                  }
               } else {
                  this.this$0.sendSettingLeft9Msg(1, var3);
               }
               break;
            case 10:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 3) {
                           if (var2 != 4) {
                              if (var2 == 5) {
                                 this.this$0.sendSettingLeft10Msg(6, var3);
                              }
                           } else {
                              this.this$0.sendSettingLeft10Msg(5, var3);
                           }
                        } else {
                           this.this$0.sendSettingLeft10Msg(4, var3);
                        }
                     } else {
                        this.this$0.sendSettingLeft10Msg(3, var3);
                     }
                  } else {
                     this.this$0.sendSettingLeft10Msg(2, var3);
                  }
               } else {
                  this.this$0.sendSettingLeft10Msg(1, var3);
               }
               break;
            case 11:
               switch (var2) {
                  case 0:
                     this.this$0.sendSettingLeft11Msg(1, var3);
                     return;
                  case 1:
                     this.this$0.sendSettingLeft11Msg(2, var3);
                     return;
                  case 2:
                     this.this$0.sendSettingLeft11Msg(3, var3);
                     return;
                  case 3:
                     this.this$0.sendSettingLeft11Msg(4, var3);
                     return;
                  case 4:
                     this.this$0.sendSettingLeft11Msg(5, var3);
                     return;
                  case 5:
                     this.this$0.sendSettingLeft11Msg(6, var3);
                     return;
                  case 6:
                     this.this$0.sendSettingLeft11Msg(7, var3);
                     return;
                  case 7:
                     this.this$0.sendSettingLeft11Msg(8, var3);
                     return;
                  case 8:
                     this.this$0.sendSettingLeft11Msg(9, var3);
                     return;
                  case 9:
                     this.this$0.sendSettingLeft11Msg(10, var3);
                     return;
                  default:
                     return;
               }
            case 12:
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, (byte)(2 - var3)});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 2, (byte)(2 - var3)});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, (byte)(2 - var3)});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 4, (byte)(var3 + 1)});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 5, (byte)(var3 + 1)});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 7, (byte)(var3 + 1)});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -54, 6, (byte)(var3 + 1)});
               }
            case 14:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           if (var3 != 0) {
                              if (var3 != 1) {
                                 if (var3 != 2) {
                                    if (var3 == 3) {
                                       CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 3});
                                    }
                                 } else {
                                    CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 2});
                                 }
                              } else {
                                 CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
                              }
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
                           }

                           this.this$0.mMsgMgr.updateAirSet(14, 3, var3);
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, (byte)var3});
               }
            case 13:
            case 15:
            case 17:
            default:
               break;
            case 16:
               if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           this.this$0.sendDrivingDataMsg(35);
                        }
                     } else {
                        this.this$0.sendDrivingDataMsg(34);
                     }
                  } else {
                     this.this$0.sendDrivingDataMsg(33);
                  }
               } else {
                  this.this$0.sendDrivingDataMsg(32);
               }
               break;
            case 18:
               CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
               this.this$0.mMsgMgr.updateLanguageSet(18, 0, var3);
               break;
            case 19:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 3) {
                        this.this$0.sendSettingLeft17Msg(3, var3);
                     }
                  } else {
                     switch (var3) {
                        case 0:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, -2});
                           break;
                        case 1:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 16});
                           break;
                        case 2:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 17});
                           break;
                        case 3:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 18});
                           break;
                        case 4:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 19});
                           break;
                        case 5:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 20});
                           break;
                        case 6:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 21});
                           break;
                        case 7:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 22});
                           break;
                        case 8:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 23});
                           break;
                        case 9:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 24});
                           break;
                        case 10:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 25});
                           break;
                        case 11:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 26});
                           break;
                        case 12:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 27});
                           break;
                        case 13:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 28});
                           break;
                        case 14:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, 29});
                           break;
                        case 15:
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 2, -1});
                     }
                  }
               } else if (var3 != 0) {
                  if (var3 != 1) {
                     if (var3 != 2) {
                        if (var3 == 3) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, -1});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, 13});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, 10});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, 5});
               }
         }

      }
   };
   private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnRearAirButtomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirBtnCtrl(39, GeneralAirData.rear_power);
            }
         } else {
            GeneralAirData.rear_auto ^= true;
            this.this$0.sendAirBtnCtrl(40, GeneralAirData.rear_auto);
         }

      }
   };
   private OnAirSeatClickListener mOnRearAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         int var1 = this.this$0.currentClickRear;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.currentClickRear = 0;
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
               }
            } else {
               this.this$0.currentClickRear = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 2});
            }
         } else {
            this.this$0.currentClickRear = 1;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 1});
         }

      }

      public void onRightSeatClick() {
         int var1 = this.this$0.currentClickRear;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.currentClickRear = 0;
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 3});
               }
            } else {
               this.this$0.currentClickRear = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 2});
            }
         } else {
            this.this$0.currentClickRear = 1;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 1});
         }

      }
   };
   private OnSettingItemSeekbarSelectListener mOnSeekBarChangeListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 4) {
            if (var1 != 15) {
               if (var1 == 19) {
                  if (var2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 77, 4, (byte)(var3 / 10)});
                  }

                  return;
               }

               if (var1 == 6) {
                  if (var2 != 5) {
                     if (var2 != 6) {
                        if (var2 != 7) {
                           if (var2 == 8) {
                              CanbusMsgSender.sendMsg(new byte[]{22, 74, 5, (byte)var3});
                              return;
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, 74, 4, (byte)var3});
                        }

                        return;
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 74, 3, (byte)var3});
                        return;
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 74, 2, (byte)var3});
                     return;
                  }
               }

               if (var1 == 7) {
                  switch (var2) {
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 10, (byte)var3});
                        return;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 11, (byte)var3});
                        return;
                     case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 7, (byte)var3});
                        return;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 8, (byte)var3});
                        return;
                     case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 9, (byte)var3});
                        return;
                     default:
                        return;
                  }
               }

               if (var1 != 8) {
                  return;
               }

               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 15, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 109, 14, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 13, (byte)var3});
               }
            }

            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -14, 12, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 11, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, (byte)var3});
            }
         } else if (var2 == 2) {
            if (MsgMgr.carSpeedWarning == 0) {
               Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131767984), 0).show();
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 75, 3, (byte)var3});
            }
         }

      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mRearLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.rear_left_seat_heat_level - 1, 43);
      }

      public void onClickPlus() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.rear_left_seat_heat_level + 1, 43);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mRearRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.rear_right_seat_heat_level - 1, 44);
      }

      public void onClickPlus() {
         this.this$0.sendSeatHeatColdMsg(GeneralAirData.rear_right_seat_heat_level + 1, 44);
      }
   };
   private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         boolean var4 = GeneralAirData.fahrenheit_celsius;
         short var2 = 254;
         int var1;
         int var3;
         if (var4) {
            var3 = (int)(MsgMgr.rearTemp - 1.0F);
            var1 = var2;
            if (MsgMgr.rearTemp != 32.0F) {
               if (MsgMgr.rearTemp == 254.0F) {
                  var1 = var2;
               } else {
                  var1 = var3;
               }
            }

            if (MsgMgr.rearTemp == 255.0F) {
               var1 = 59;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte)var1});
         } else {
            var3 = (int)(MsgMgr.rearTemp - 2.0F);
            var1 = var2;
            if (MsgMgr.rearTemp != 122.0F) {
               if (MsgMgr.rearTemp == 254.0F) {
                  var1 = var2;
               } else {
                  var1 = var3;
               }
            }

            if (MsgMgr.rearTemp == 255.0F) {
               var1 = 170;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte)var1});
         }

      }

      public void onClickUp() {
         boolean var5 = GeneralAirData.fahrenheit_celsius;
         short var3 = 255;
         int var2;
         int var4;
         if (var5) {
            var4 = (int)(MsgMgr.rearTemp + 1.0F);
            var2 = var3;
            if (MsgMgr.rearTemp != 59.0F) {
               if (MsgMgr.rearTemp == 255.0F) {
                  var2 = var3;
               } else {
                  var2 = var4;
               }
            }

            if (MsgMgr.rearTemp == 254.0F) {
               var2 = 32;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte)var2});
         } else {
            var4 = (int)(MsgMgr.rearTemp + 2.0F);
            var2 = var3;
            if (MsgMgr.rearTemp != 170.0F) {
               if (MsgMgr.rearTemp == 255.0F) {
                  var2 = var3;
               } else {
                  var2 = var4;
               }
            }

            float var1 = MsgMgr.rearTemp;
            byte var6 = 122;
            if (var1 == 254.0F) {
               var2 = 122;
            }

            if (var2 == 123) {
               var2 = var6;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte)var2});
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         int var2 = MsgMgr.frontWindLv - 1;
         int var1 = var2;
         if (var2 < 0) {
            var1 = 0;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte)var1});
      }

      public void onClickRight() {
         int var2 = MsgMgr.frontWindLv + 1;
         int var1 = var2;
         if (var2 > 7) {
            var1 = 7;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte)var1});
      }
   };
   private OnAirWindSpeedUpDownClickListener mSetRearWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         int var2 = GeneralAirData.rear_wind_level - 1;
         int var1 = var2;
         if (var2 < 0) {
            var1 = 0;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte)var1});
      }

      public void onClickRight() {
         int var2 = GeneralAirData.rear_wind_level + 1;
         int var1 = var2;
         if (var2 > 7) {
            var1 = 7;
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte)var1});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnItemSelectedListener);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSeekBarChangeListener);
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
      var3.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
      var3.getRearArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mRearLeftSeatHeatColdListener, this.mRearRightSeatHeatColdListener});
      var3.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearTempSetViewOnUpDownClickListenerCenter, null});
      var3.getRearArea().setSetWindSpeedViewOnClickListener(this.mSetRearWindSpeedViewOnClickListener);
      var3.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirButtomBtnClickListener});
      var3.getRearArea().setOnAirSeatClickListener(this.mOnRearAirSeatClickListener);
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 4) {
                     if (var1 != 11) {
                        if (var1 == 13) {
                           switch (var2) {
                              case 0:
                                 this.this$0.sendSettingResetData(128);
                                 break;
                              case 1:
                                 this.this$0.sendSettingResetData(64);
                                 break;
                              case 2:
                                 this.this$0.sendSettingResetData(32);
                                 break;
                              case 3:
                                 this.this$0.sendSettingResetData(16);
                                 break;
                              case 4:
                                 this.this$0.sendSettingResetData(8);
                                 break;
                              case 5:
                                 this.this$0.sendSettingResetData(4);
                                 break;
                              case 6:
                                 this.this$0.sendSettingResetData(2);
                           }
                        }
                     } else if (var2 == 10) {
                        Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                        this.this$0.sendData(new byte[]{22, 123, 11});
                     } else if (var2 == 11) {
                        Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                        this.this$0.sendData(new byte[]{22, 123, 12});
                     }
                  } else if (var2 == 0) {
                     this.this$0.sendData(new byte[]{22, 75, 1, 1});
                  }
               } else if (var2 == 9) {
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                  this.this$0.sendData(new byte[]{22, -117, 9, 10});
               }
            } else if (var2 == 6) {
               Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
               this.this$0.sendData(new byte[]{22, -117, 5, 7});
            }

         }
      });
   }

   private void sendAirBtnCtrl(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, this.setWidgetData(var2)});
   }

   private void sendAirClick(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, 1});
   }

   private void sendDrivingDataMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var1, 0});
   }

   private void sendSeatHeatColdMsg(int var1, int var2) {
      int var3 = var1;
      if (var1 >= 3) {
         var3 = 3;
      }

      var1 = var3;
      if (var3 <= 0) {
         var1 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var2, (byte)var1});
   }

   private void sendSettingLeft10Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft11Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 123, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft17Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 77, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft1Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, 5, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft2Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, 9, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft5Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft6Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 74, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft7Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte)var1, (byte)var2});
   }

   private void sendSettingLeft9Msg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte)var1, (byte)var2});
   }

   private void sendSettingResetData(int var1) {
      Context var2 = this.mContext;
      Toast.makeText(var2, var2.getText(2131769831), 0).show();
      this.sendData(new byte[]{22, 26, (byte)var1});
   }

   private byte setWidgetData(boolean var1) {
      return (byte)(var1 ^ 1);
   }
}
