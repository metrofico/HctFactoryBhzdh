package com.hzbhd.canbus.car._248;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;

public class UiMgr extends AbstractUiMgr {
   private int chargeEndTimeHourProgress;
   private int chargeEndTimeMinuteProgress;
   private int chargeStartTimeHourProgress;
   private int chargeStartTimeMinuteProgress;
   private Context mContext;
   private int mCycelData = 0;
   private int mDifferent = this.getCurrentCarId();
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (this.this$0.getCurrentCarId() == 4) {
            int var2 = GeneralAirData.front_left_seat_heat_level - 1;
            int var1 = var2;
            if (var2 >= 3) {
               var1 = 3;
            }

            var2 = var1;
            if (var1 <= 0) {
               var2 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 59, 15, (byte)var2});
         }

      }

      public void onClickPlus() {
         if (this.this$0.getCurrentCarId() == 4) {
            int var2 = GeneralAirData.front_left_seat_heat_level + 1;
            int var1 = var2;
            if (var2 >= 3) {
               var1 = 3;
            }

            var2 = var1;
            if (var1 <= 0) {
               var2 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 59, 15, (byte)var2});
         }

      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (this.this$0.getCurrentCarId() == 4) {
            int var2 = GeneralAirData.front_right_seat_heat_level - 1;
            int var1 = var2;
            if (var2 >= 3) {
               var1 = 3;
            }

            var2 = var1;
            if (var1 <= 0) {
               var2 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 59, 16, (byte)var2});
         }

      }

      public void onClickPlus() {
         if (this.this$0.getCurrentCarId() == 4) {
            int var2 = GeneralAirData.front_right_seat_heat_level + 1;
            int var1 = var2;
            if (var2 >= 3) {
               var1 = 3;
            }

            var2 = var1;
            if (var1 <= 0) {
               var2 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 59, 16, (byte)var2});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerButtom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerCenter1 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         LogUtil.showLog("position:" + var1);
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerCenter2 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         LogUtil.showLog("position:" + var1);
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
         }

      }
   };
   private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == 1) {
            this.this$0.sendData(new byte[]{22, -112, 35});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var5;
         switch (var1) {
            case 0:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -101, 22, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 4, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 3, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -101, 2, (byte)var3});
               }
               break;
            case 1:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -101, 24, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 23, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 6, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -101, 5, (byte)var3});
               }
               break;
            case 2:
               if (var2 != 1) {
                  if (var2 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 11, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -101, 8, (byte)var3});
               }
               break;
            case 3:
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 12, (byte)var3});
                     return;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 13, (byte)var3});
                     return;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 14, (byte)var3});
                     return;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 15, (byte)var3});
                     return;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 16, (byte)var3});
                     return;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 17, (byte)var3});
                     return;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 25, (byte)var3});
                     return;
                  default:
                     return;
               }
            case 4:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 3) {
                           if (var2 == 4) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -101, 27, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -101, 21, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 20, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 19, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -101, 18, (byte)var3});
               }
               break;
            case 5:
               if (this.this$0.getCurrentCarId() == 0) {
                  if (var2 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte)var3, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                     return;
                  }

                  if (!"1".equals(String.valueOf(MsgMgr.chargeModel))) {
                     this.this$0.showToast(2131769498);
                     return;
                  }

                  boolean var4;
                  if (var2 == 5) {
                     var5 = this.this$0;
                     var1 = var5.mCycelData;
                     if (var3 == 1) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 7, var4);
                     CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                     return;
                  }

                  if (!"1".equals(String.valueOf(MsgMgr.recycleModel))) {
                     this.this$0.showToast(2131769497);
                     return;
                  }

                  switch (var2) {
                     case 6:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 6, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                        return;
                     case 7:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 5, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                        return;
                     case 8:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 4, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                        return;
                     case 9:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 3, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                        return;
                     case 10:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 2, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                        return;
                     case 11:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 1, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                        return;
                     case 12:
                        var5 = this.this$0;
                        var1 = var5.mCycelData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var5.mCycelData = DataHandleUtils.setIntByteWithBit(var1, 0, var4);
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, (byte)this.this$0.mCycelData, 0, 0, 0, 0});
                  }
               } else if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 107, 1, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 26, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -101, 1, (byte)var3});
               }
               break;
            case 6:
               if (this.this$0.getCurrentCarId() == 0) {
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 == 2) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 107, 1, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -101, 26, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -101, 1, (byte)var3});
                  }
               }
               break;
            case 7:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte)var3});
                  var5 = this.this$0;
                  var5.getMsgMgr(var5.mContext).updateSettings(this.this$0.mContext, "EnergyRecycle", var1, var2, var3);
               }
         }

      }
   };
   private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == 0) {
            this.this$0.sendData(new byte[]{22, -112, 64});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener monUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener monUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
      }
   };
   private MsgMgr msgMgr;
   private OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         byte var2;
         if (GeneralAirData.front_left_blow_window) {
            var2 = 9;
         } else if (GeneralAirData.front_left_blow_head) {
            var2 = 10;
         } else {
            boolean var3 = GeneralAirData.front_left_blow_foot;
            var2 = 8;
         }

         byte var1 = (byte)var2;
         CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
      }

      public void onRightSeatClick() {
         byte var2;
         if (GeneralAirData.front_right_blow_window) {
            var2 = 9;
         } else if (GeneralAirData.front_right_blow_head) {
            var2 = 10;
         } else {
            boolean var3 = GeneralAirData.front_right_blow_foot;
            var2 = 8;
         }

         byte var1 = (byte)var2;
         CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
      }
   };
   private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 2) {
            if (var1 == 5) {
               if (!"1".equals(String.valueOf(MsgMgr.chargeModel))) {
                  this.this$0.showToast(2131769498);
                  return;
               }

               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           this.this$0.chargeEndTimeMinuteProgress = var3;
                           CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
                        }
                     } else {
                        this.this$0.chargeEndTimeHourProgress = var3;
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
                     }
                  } else {
                     this.this$0.chargeStartTimeMinuteProgress = var3;
                     CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
                  }
               } else {
                  this.this$0.chargeStartTimeHourProgress = var3;
                  CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)this.this$0.chargeStartTimeHourProgress, (byte)this.this$0.chargeStartTimeMinuteProgress, (byte)this.this$0.chargeEndTimeHourProgress, (byte)this.this$0.chargeEndTimeMinuteProgress, 0, 0, 0, 0, 0});
               }
            }
         } else if (var2 != 0) {
            if (var2 != 2) {
               if (var2 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -101, 10, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -101, 9, (byte)var3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -101, 7, (byte)var3});
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 12, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 12, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      AirPageUiSet var4 = this.getAirUiSet(var1);
      var4.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var4.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.monUpDownClickListenerLeft, null, this.monUpDownClickListenerRight});
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerCenter1, this.mOnAirBtnClickListenerCenter2, this.mOnAirBtnClickListenerButtom});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.setWindSpeedViewOnClickListener);
      var4.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      var3.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var3) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void addViewToWindows() {
            ArrayList var2 = new ArrayList();
            int var1 = this.this$0.mDifferent;
            if (var1 != 1 && var1 != 4) {
               if (var1 != 5) {
                  var2 = null;
                  this.val$parkPageUiSet.setShowPanoramic(false);
               } else {
                  var2.add(new ParkPageUiSet.Bean(0, "_248_close", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_front_panoramic", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_front_right", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_rear_panoramic", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_horizontal_panoramic", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_vertical_panoramic", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_left_panoramic", ""));
                  var2.add(new ParkPageUiSet.Bean(0, "_248_right_panoramic", ""));
               }
            } else {
               var2.add(new ParkPageUiSet.Bean(0, "_248_close", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_248_front_panoramic", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_248_rear_panoramic", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_248_left_panoramic", ""));
               var2.add(new ParkPageUiSet.Bean(0, "_248_right_panoramic", ""));
            }

            this.val$parkPageUiSet.setPanoramicBtnList(var2);
         }
      });
      var3.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var3) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var3 = ((ParkPageUiSet.Bean)this.val$parkPageUiSet.getPanoramicBtnList().get(var1)).getTitleSrn();
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -741943874:
                  if (var3.equals("_248_front_right")) {
                     var4 = 0;
                  }
                  break;
               case -275084347:
                  if (var3.equals("_248_vertical_panoramic")) {
                     var4 = 1;
                  }
                  break;
               case -260375248:
                  if (var3.equals("_248_close")) {
                     var4 = 2;
                  }
                  break;
               case -20878245:
                  if (var3.equals("_248_right_panoramic")) {
                     var4 = 3;
                  }
                  break;
               case 151018739:
                  if (var3.equals("_248_horizontal_panoramic")) {
                     var4 = 4;
                  }
                  break;
               case 276447347:
                  if (var3.equals("_248_rear_panoramic")) {
                     var4 = 5;
                  }
                  break;
               case 361309974:
                  if (var3.equals("_248_left_panoramic")) {
                     var4 = 6;
                  }
                  break;
               case 1535548456:
                  if (var3.equals("_248_front_panoramic")) {
                     var4 = 7;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 2});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 5});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 6});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 8});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 4});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 7});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.mDifferent;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 == 5) {
                        this.removeMainPrjBtnByName(this.mContext, "setting");
                        this.removeMainPrjBtnByName(this.mContext, "air");
                     }
                  } else {
                     this.removeFrontAirButton(var1, 3, 3, 3);
                     this.removeSettingLeftItem(var1, 5, 6);
                  }
               } else {
                  this.removeFrontAirButton(var1, 0, 2, 2);
                  this.removeFrontAirButton(var1, 3, 3, 3);
                  this.removeSettingLeftItem(var1, 5, 6);
               }
            } else {
               this.removeSettingLeftItem(var1, 5, 6);
            }
         } else {
            this.removeFrontAirButton(var1, 0, 2, 2);
            this.removeSettingLeftItem(var1, 5, 6);
         }
      } else {
         this.removeFrontAirButton(var1, 0, 2, 2);
         this.removeFrontAirButton(var1, 3, 3, 3);
      }

   }
}
