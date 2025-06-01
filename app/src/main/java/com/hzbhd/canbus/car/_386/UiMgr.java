package com.hzbhd.canbus.car._386;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   AirPageUiSet airPageUiSet;
   AmplifierPageUiSet amplifierPageUiSet;
   int differentId;
   DriverDataPageUiSet driverDataPageUiSet;
   int eachId;
   Context mContext;
   private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirMessage(7);
            }
         } else {
            this.this$0.sendAirMessage(41);
         }

      }
   };
   private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(6);
         }

      }
   };
   private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(5);
         }

      }
   };
   private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirMessage(4);
               }
            } else {
               this.this$0.sendAirMessage(1);
            }
         } else {
            this.this$0.sendAirMessage(2);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(14);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(13);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         UiMgr var1 = this.this$0;
         var1.sendSettingMessage(29, var1.resolveHeat(var1.getMsgMgr(var1.mContext).memory));
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         UiMgr var1 = this.this$0;
         var1.sendSettingMessage(32, var1.resolveHeat(var1.getMsgMgr(var1.mContext).memory2));
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirMessage(17);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirMessage(18);
      }
   };
   private OnAirBtnClickListener mRearBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirMessage(19);
            }
         } else {
            this.this$0.sendAirMessage(44);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mRearCenterTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(33);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(32);
      }
   };
   private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(16);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(15);
      }
   };
   private MsgMgr msgMgr;
   ParkPageUiSet parkPageUiSet;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.airPageUiSet = this.getAirUiSet(this.mContext);
      this.settingPageUiSet = this.getSettingUiSet(this.mContext);
      this.amplifierPageUiSet = this.getAmplifierPageUiSet(this.mContext);
      this.parkPageUiSet = this.getParkPageUiSet(this.mContext);
      this.driverDataPageUiSet = this.getDriverDataPageUiSet(this.mContext);
      this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.sendPageStatus(98);
            this.this$0.sendPageStatus(166);
         }
      });
      this.driverDataPageUiSet.setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.sendPageStatus(50);
         }
      });
      this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            int var5 = var6.hashCode();
            byte var4 = -1;
            switch (var5) {
               case -903809344:
                  if (var6.equals("speed_linkage_volume_adjustment")) {
                     var4 = 0;
                  }
                  break;
               case -672453938:
                  if (var6.equals("surround_sound")) {
                     var4 = 1;
                  }
                  break;
               case 102584022:
                  if (var6.equals("language_setup")) {
                     var4 = 2;
                  }
                  break;
               case 1169063234:
                  if (var6.equals("_386_item_14")) {
                     var4 = 3;
                  }
                  break;
               case 1169063235:
                  if (var6.equals("_386_item_15")) {
                     var4 = 4;
                  }
                  break;
               case 1169063236:
                  if (var6.equals("_386_item_16")) {
                     var4 = 5;
                  }
                  break;
               case 1169063237:
                  if (var6.equals("_386_item_17")) {
                     var4 = 6;
                  }
                  break;
               case 1169063238:
                  if (var6.equals("_386_item_18")) {
                     var4 = 7;
                  }
                  break;
               case 1169063239:
                  if (var6.equals("_386_item_19")) {
                     var4 = 8;
                  }
                  break;
               case 1169063261:
                  if (var6.equals("_386_item_20")) {
                     var4 = 9;
                  }
                  break;
               case 1169063262:
                  if (var6.equals("_386_item_21")) {
                     var4 = 10;
                  }
                  break;
               case 1169063263:
                  if (var6.equals("_386_item_22")) {
                     var4 = 11;
                  }
                  break;
               case 1169063264:
                  if (var6.equals("_386_item_23")) {
                     var4 = 12;
                  }
                  break;
               case 1169063266:
                  if (var6.equals("_386_item_25")) {
                     var4 = 13;
                  }
                  break;
               case 1169063267:
                  if (var6.equals("_386_item_26")) {
                     var4 = 14;
                  }
                  break;
               case 1169063268:
                  if (var6.equals("_386_item_27")) {
                     var4 = 15;
                  }
                  break;
               case 1169063300:
                  if (var6.equals("_386_item_38")) {
                     var4 = 16;
                  }
            }

            UiMgr var7;
            switch (var4) {
               case 0:
                  this.this$0.sendAmplifier(7, var3);
                  break;
               case 1:
                  this.this$0.sendAmplifier(8, var3);
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
                  var7 = this.this$0;
                  var7.getMsgMgr(var7.mContext).updateSettings(var1, var2, var3);
                  break;
               case 3:
                  this.this$0.sendSettingMessage(16, var3);
                  break;
               case 4:
                  this.this$0.sendSettingMessage(17, var3);
                  break;
               case 5:
                  this.this$0.sendSettingMessage(18, var3);
                  break;
               case 6:
                  this.this$0.sendSettingMessage(19, var3);
                  break;
               case 7:
                  this.this$0.sendSettingMessage(20, var3);
                  break;
               case 8:
                  this.this$0.sendSettingMessage(21, var3);
                  break;
               case 9:
                  this.this$0.sendSettingMessage(22, var3);
                  break;
               case 10:
                  this.this$0.sendSettingMessage(23, var3);
                  break;
               case 11:
                  this.this$0.sendSettingMessage(24, var3);
                  break;
               case 12:
                  this.this$0.sendSettingMessage(25, var3);
                  break;
               case 13:
                  this.this$0.sendSettingMessage(26, var3);
                  break;
               case 14:
                  this.this$0.sendSettingMessage(27, var3);
                  break;
               case 15:
                  this.this$0.sendSettingMessage(28, var3);
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 12, (byte)var3});
                  var7 = this.this$0;
                  var7.getMsgMgr(var7.mContext).updateSettings(var1, var2, var3);
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1826742522:
                  if (var4.equals("_386_seat_chirapsia_driver")) {
                     var5 = 0;
                  }
                  break;
               case -1761750354:
                  if (var4.equals("_386_seat_chirapsia_copilot")) {
                     var5 = 1;
                  }
                  break;
               case -748255271:
                  if (var4.equals("_386_lumbar_support_copilot")) {
                     var5 = 2;
                  }
                  break;
               case 1669634171:
                  if (var4.equals("_386_lumbar_support_driver")) {
                     var5 = 3;
                  }
            }

            switch (var5) {
               case 0:
                  this.this$0.sendSettingMessage(30, var3);
                  break;
               case 1:
                  this.this$0.sendSettingMessage(33, var3);
                  break;
               case 2:
                  this.this$0.sendSettingMessage(34, var3);
                  break;
               case 3:
                  this.this$0.sendSettingMessage(31, var3);
            }

         }
      });
      this.amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  this.this$0.sendAmplifier(2, var2 - 246);
               }
            } else {
               this.this$0.sendAmplifier(3, var2 - 246);
            }

         }
      });
      this.amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 == 4) {
                        this.this$0.sendAmplifier(4, var2);
                     }
                  } else {
                     this.this$0.sendAmplifier(5, var2);
                  }
               } else {
                  this.this$0.sendAmplifier(6, var2);
               }
            } else {
               UiMgr var4 = this.this$0;
               if (var2 > var4.getMsgMgr(var4.mContext).volume) {
                  this.this$0.sendAmplifier(1, 1);
               }

               var4 = this.this$0;
               if (var2 < var4.getMsgMgr(var4.mContext).volume) {
                  this.this$0.sendAmplifier(1, 255);
               }
            }

         }
      });
      this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirMessage(12);
         }

         public void onClickRight() {
            this.this$0.sendAirMessage(11);
         }
      });
      this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.differentId != 2) {
               this.this$0.sendAirMessage(21);
            }
         }

         public void onRightSeatClick() {
            if (this.this$0.differentId != 2) {
               this.this$0.sendAirMessage(22);
            }
         }
      });
      this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mRearBottomBtnListener});
      this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirMessage(43);
         }

         public void onClickRight() {
            this.this$0.sendAirMessage(42);
         }
      });
      this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearCenterTempListener, null});
      this.airPageUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.differentId != 2) {
               this.this$0.sendAirMessage(45);
            }
         }

         public void onRightSeatClick() {
            if (this.this$0.differentId != 2) {
               this.this$0.sendAirMessage(45);
            }
         }
      });
      this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            UiMgr var2;
            if (var1 != 0) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 1});
                        }
                     } else {
                        var2 = this.this$0;
                        if (var2.getMsgMgr(var2.mContext).Down) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                           var2 = this.this$0;
                           var2.getMsgMgr(var2.mContext).updateSettingPano(4, false);
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                           var2 = this.this$0;
                           var2.getMsgMgr(var2.mContext).updateSettingPano(4, true);
                        }
                     }
                  } else {
                     var2 = this.this$0;
                     if (var2.getMsgMgr(var2.mContext).Up) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                        var2 = this.this$0;
                        var2.getMsgMgr(var2.mContext).updateSettingPano(3, false);
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                        var2 = this.this$0;
                        var2.getMsgMgr(var2.mContext).updateSettingPano(3, true);
                     }
                  }
               } else {
                  var2 = this.this$0;
                  if (var2.getMsgMgr(var2.mContext).Left) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                     var2 = this.this$0;
                     var2.getMsgMgr(var2.mContext).updateSettingPano(2, false);
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                     var2 = this.this$0;
                     var2.getMsgMgr(var2.mContext).updateSettingPano(2, true);
                  }
               }
            } else {
               var2 = this.this$0;
               if (var2.getMsgMgr(var2.mContext).Right) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 0});
                  var2 = this.this$0;
                  var2.getMsgMgr(var2.mContext).updateSettingPano(0, false);
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                  var2 = this.this$0;
                  var2.getMsgMgr(var2.mContext).updateSettingPano(0, true);
               }
            }

         }
      });
   }

   private int ResolveTrueOrFalse(boolean var1) {
      return var1 ? 0 : 1;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      }

      return this.msgMgr;
   }

   private int resolveHeat(int var1) {
      return var1 == 3 ? 0 : var1 + 1;
   }

   private int resolvedec(int var1) {
      return var1 == 0 ? 0 : var1 - 1;
   }

   private int resolveinc(int var1) {
      return var1 == 7 ? 7 : var1 + 1;
   }

   private void sendAirMessage(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }

   private void sendAmplifier(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte)var1, (byte)var2});
   }

   private void sendPageStatus(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
   }

   private void sendSettingMessage(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, -1, -1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.differentId == 2) {
         this.airPageUiSet.getFrontArea().setAllBtnCanClick(false);
         this.airPageUiSet.getRearArea().setAllBtnCanClick(false);
         this.airPageUiSet.getFrontArea().setCanSetWindSpeed(false);
         this.airPageUiSet.getRearArea().setCanSetWindSpeed(false);
         this.airPageUiSet.getFrontArea().setCanSetSeatCold(false);
         this.airPageUiSet.getFrontArea().setCanSetSeatHeat(false);
         this.airPageUiSet.getFrontArea().setCanSetLeftTemp(false);
         this.airPageUiSet.getFrontArea().setCanSetRightTemp(false);
         this.airPageUiSet.getRearArea().setShowCenterWheel(false);
      }

   }
}
