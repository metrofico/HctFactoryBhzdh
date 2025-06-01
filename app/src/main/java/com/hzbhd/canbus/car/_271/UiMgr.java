package com.hzbhd.canbus.car._271;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (this.this$0.getCurrentCarId() == 0) {
            if (var1 == 0 && var2 == 0) {
               this.this$0.onSettingSender(1, var3);
            }

            if (var1 == 2 && var2 == 4) {
               this.this$0.onSettingSender(11, var3);
            }
         } else if (var1 == 0 && var2 == 0) {
            this.this$0.onSettingSender(1, var3);
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (this.this$0.getCurrentCarId() == 0) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     if (var2 != 0) {
                        if (var2 != 1) {
                           if (var2 != 2) {
                              if (var2 == 3) {
                                 this.this$0.onSettingSender(10, var3);
                              }
                           } else {
                              this.this$0.onSettingSender(9, var3);
                           }
                        } else {
                           this.this$0.onSettingSender(8, var3);
                        }
                     } else {
                        this.this$0.onSettingSender(7, var3);
                     }
                  }
               } else if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 2) {
                        this.this$0.onSettingSender(6, var3);
                     }
                  } else {
                     this.this$0.onSettingSender(5, var3);
                  }
               } else {
                  this.this$0.onSettingSender(4, var3);
               }
            } else if (var2 != 1) {
               if (var2 == 2) {
                  this.this$0.onSettingSender(3, var3);
               }
            } else {
               this.this$0.onSettingSender(2, var3);
            }
         } else if (var1 != 0) {
            if (var1 == 1) {
               if (var2 == 0) {
                  this.this$0.onSettingSender(5, var3);
               } else {
                  this.this$0.onSettingSender(6, var3);
               }
            }
         } else if (var2 != 1) {
            if (var2 == 2) {
               this.this$0.onSettingSender(3, var3);
            }
         } else {
            this.this$0.onSettingSender(2, var3);
         }

      }
   };
   private ParkPageUiSet mParkPageUiSet;
   private OnAirBtnClickListener mSetOnAirBtnClickListeners1 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.onAirSender(22);
               }
            } else {
               this.this$0.onAirSender(21);
            }
         } else {
            this.this$0.onAirSender(5);
         }

      }
   };
   private OnAirBtnClickListener mSetOnAirBtnClickListeners2 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.onAirSender(4);
      }
   };
   private OnAirBtnClickListener mSetOnAirBtnClickListeners4 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.onAirSender(7);
            }
         } else {
            this.this$0.onAirSender(2);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mSetTempSetViewOnUpDownClickListeners1 = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.onAirSender(14);
      }

      public void onClickUp() {
         this.this$0.onAirSender(13);
      }
   };
   private OnAirWindSpeedUpDownClickListener msetSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.onAirSender(12);
      }

      public void onClickRight() {
         this.this$0.onAirSender(11);
      }
   };

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mSetOnAirBtnClickListeners1, this.mSetOnAirBtnClickListeners2, null, this.mSetOnAirBtnClickListeners4});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.msetSetWindSpeedViewOnClickListener);
      FrontArea var5 = var3.getFrontArea();
      OnAirTemperatureUpDownClickListener var4 = this.mSetTempSetViewOnUpDownClickListeners1;
      var5.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var4, null, var4});
   }

   private void onAirSender(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   private void onSettingSender(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, -1, -1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            this.removeFrontAirButton(var1, 1, 0, 2);
            this.removeSettingLeftItem(var1, 2, 2);
            this.removeSettingRightItem(var1, 1, 0, 2);
         }
      } else {
         this.removeFrontAirButton(var1, 0, 2, 2);
         this.removeMainPrjBtn(var1, 0, 2);
         this.removeSettingRightItem(var1, 0, 2, 2);
      }

   }
}
