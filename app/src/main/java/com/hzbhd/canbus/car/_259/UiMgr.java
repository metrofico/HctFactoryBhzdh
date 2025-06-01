package com.hzbhd.canbus.car._259;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 1});
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 22, 0});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 21, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 21, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 41, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 0) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte)var3, -1, -1});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var3, -1, -1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte)var3, -1, -1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3, -1, -1});
               }
            } else if (this.this$0.getCurrentCarId() == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var3, -1, -1});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var3, -1, -1});
            }
         } else if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 == 4 && this.this$0.getCurrentCarId() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 20, (byte)var3, -1, -1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 111, 19, (byte)var3, -1, -1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 18, (byte)var3, -1, -1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 17, (byte)var3, -1, -1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 111, 16, (byte)var3, -1, -1});
         }

      }
   };
   private ParkPageUiSet mParkPageUiSet;
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
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
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
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
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.getCurrentCarId() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
         }

      }

      public void onClickUp() {
         if (this.this$0.getCurrentCarId() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      this.mParkPageUiSet = this.getParkPageUiSet(var1);
      AirPageUiSet var2 = this.getAirUiSet(var1);
      if (this.getCurrentCarId() == 1) {
         var2.getFrontArea().setShowPmValue(false);
      } else {
         var2.getFrontArea().setShowPmValue(true);
      }

      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, null, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
   }

   public void setShowRadar(boolean var1) {
      this.mParkPageUiSet.setShowRadar(var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            this.removeSettingRightItemByNameList(this.mContext, new String[]{"geely_fortification_prompt_type", "skylight"});
         }
      } else {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"geely_auto_close_the_window_after_locking", "geely_close_the_sunroof_after_locking", "geely_electronic_assist_mode", "geely_remote_lock_feedback", "geely_daytime_running_lights"});
      }

   }
}
