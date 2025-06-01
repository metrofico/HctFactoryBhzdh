package com.hzbhd.canbus.car._379;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   boolean CameraDelay;
   boolean DepressedView;
   boolean Force;
   AirPageUiSet airPageUiSet;
   AmplifierPageUiSet amplifierPageUiSet;
   int data003;
   int data047;
   int data101;
   int data123;
   int data147;
   int differentId;
   int eachId;
   Context mContext;
   private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        var2 = this.this$0;
                        var2.sendAirMessage(7, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).in_out_cycle));
                     }
                  } else {
                     var2 = this.this$0;
                     var2.sendAirMessage(14, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).dual));
                  }
               } else {
                  var2 = this.this$0;
                  var2.sendAirMessage(5, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).mac_heat));
               }
            } else {
               var2 = this.this$0;
               var2.sendAirMessage(6, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).rear_defog));
            }
         } else {
            var2 = this.this$0;
            var2.sendAirMessage(21, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).steering_wheel_heating));
         }

      }
   };
   private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2;
         if (var1 != 0) {
            if (var1 == 1) {
               var2 = this.this$0;
               var2.sendAirMessage(8, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).blowwindow));
            }
         } else {
            var2 = this.this$0;
            var2.sendAirMessage(9, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).blowhead));
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
            UiMgr var2 = this.this$0;
            var2.sendAirMessage(10, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).blowfoot));
         }

      }
   };
   private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     var2 = this.this$0;
                     var2.sendAirMessage(4, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).auto));
                  }
               } else {
                  var2 = this.this$0;
                  var2.sendAirMessage(1, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).power));
               }
            } else {
               var2 = this.this$0;
               var2.sendAirMessage(3, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).ac_max));
            }
         } else {
            var2 = this.this$0;
            var2.sendAirMessage(2, var2.ResolveTrueOrFalse(var2.getMsgMgr(var2.mContext).ac));
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(12, var1.getMsgMgr(var1.mContext).LeftTemp - 1);
      }

      public void onClickUp() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(12, var1.getMsgMgr(var1.mContext).LeftTemp + 1);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(19, var1.resolveHeat(var1.getMsgMgr(var1.mContext).LeftCold));
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(20, var1.resolveHeat(var1.getMsgMgr(var1.mContext).RightCold));
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(17, var1.resolveHeat(var1.getMsgMgr(var1.mContext).LeftHeat));
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(18, var1.resolveHeat(var1.getMsgMgr(var1.mContext).RightHeat));
      }
   };
   private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(13, var1.getMsgMgr(var1.mContext).RightTemp - 1);
      }

      public void onClickUp() {
         UiMgr var1 = this.this$0;
         var1.sendAirMessage(13, var1.getMsgMgr(var1.mContext).RightTemp + 1);
      }
   };
   private MsgMgr msgMgr;
   ParkPageUiSet parkPageUiSet;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.CameraDelay = this.getMsgMgr(this.mContext).CameraDelay;
      this.DepressedView = this.getMsgMgr(this.mContext).DepressedView;
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      this.airPageUiSet = var2;
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            UiMgr var1 = this.this$0;
            var1.sendAirMessage(11, var1.resolvedec(var1.getMsgMgr(var1.mContext).WindLevel));
         }

         public void onClickRight() {
            UiMgr var1 = this.this$0;
            var1.sendAirMessage(11, var1.resolveinc(var1.getMsgMgr(var1.mContext).WindLevel));
         }
      });
      this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      this.amplifierPageUiSet = var5;
      var5.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
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
                        this.this$0.sendAmplifier(1, var2);
                     }
                  } else {
                     this.this$0.sendAmplifier(6, var2);
                  }
               } else {
                  this.this$0.sendAmplifier(5, var2);
               }
            } else {
               this.this$0.sendAmplifier(4, var2);
            }

         }
      });
      this.amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            Log.d("lai", "position: " + var2);
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  this.this$0.sendAmplifier(3, var2 - 249);
               }
            } else {
               this.this$0.sendAmplifier(2, var2 - 249);
            }

         }
      });
      this.amplifierPageUiSet.setOnAmplifierResetPositionListener(new OnAmplifierResetPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void resetClick() {
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var3;
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
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
               case -26602129:
                  if (var6.equals("temperature_unit")) {
                     var4 = 2;
                  }
                  break;
               case 102584022:
                  if (var6.equals("language_setup")) {
                     var4 = 3;
                  }
                  break;
               case 238180612:
                  if (var6.equals("_379_item_01")) {
                     var4 = 4;
                  }
                  break;
               case 238180613:
                  if (var6.equals("_379_item_02")) {
                     var4 = 5;
                  }
                  break;
               case 238180614:
                  if (var6.equals("_379_item_03")) {
                     var4 = 6;
                  }
                  break;
               case 238180615:
                  if (var6.equals("_379_item_04")) {
                     var4 = 7;
                  }
                  break;
               case 238180616:
                  if (var6.equals("_379_item_05")) {
                     var4 = 8;
                  }
                  break;
               case 238180617:
                  if (var6.equals("_379_item_06")) {
                     var4 = 9;
                  }
                  break;
               case 238180618:
                  if (var6.equals("_379_item_07")) {
                     var4 = 10;
                  }
                  break;
               case 238180619:
                  if (var6.equals("_379_item_08")) {
                     var4 = 11;
                  }
                  break;
               case 238180620:
                  if (var6.equals("_379_item_09")) {
                     var4 = 12;
                  }
                  break;
               case 238180642:
                  if (var6.equals("_379_item_10")) {
                     var4 = 13;
                  }
                  break;
               case 238180644:
                  if (var6.equals("_379_item_12")) {
                     var4 = 14;
                  }
                  break;
               case 238180645:
                  if (var6.equals("_379_item_13")) {
                     var4 = 15;
                  }
                  break;
               case 238180646:
                  if (var6.equals("_379_item_14")) {
                     var4 = 16;
                  }
                  break;
               case 238180647:
                  if (var6.equals("_379_item_15")) {
                     var4 = 17;
                  }
                  break;
               case 238180648:
                  if (var6.equals("_379_item_16")) {
                     var4 = 18;
                  }
                  break;
               case 238180649:
                  if (var6.equals("_379_item_17")) {
                     var4 = 19;
                  }
                  break;
               case 911714181:
                  if (var6.equals("S96ColorTitle")) {
                     var4 = 20;
                  }
            }

            UiMgr var7;
            switch (var4) {
               case 0:
                  this.this$0.sendAmplifier(7, var3);
                  break;
               case 1:
                  this.this$0.sendAmplifier(8, var3);
                  var7 = this.this$0;
                  var7.getMsgMgr(var7.mContext).updateSettings(var1, var2, var3);
                  break;
               case 2:
                  this.this$0.sendSettingMessageTwo(4, var3);
                  break;
               case 3:
                  var7 = this.this$0;
                  var7.sendSettingMessageThr(var7.resolveSelectPosFour(var3));
                  break;
               case 4:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(8, var7.resolveSelectPosOne(var3));
                  break;
               case 5:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(9, var7.resolveSelectPosOne(var3));
                  break;
               case 6:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(10, var7.resolveSelectPosOne(var3));
                  break;
               case 7:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(11, var7.resolveSelectPosTwo(var3));
                  break;
               case 8:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(12, var7.resolveSelectPosTwo(var3));
                  break;
               case 9:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(14, var7.resolveSelectPosOne(var3));
                  break;
               case 10:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(15, var7.resolveSelectPosOne(var3));
                  break;
               case 11:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(16, var7.resolveSelectPosOne(var3));
                  break;
               case 12:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(17, var7.resolveSelectPosTwo(var3));
                  break;
               case 13:
                  var7 = this.this$0;
                  var7.sendSettingMessageOne(18, var7.resolveSelectPosTwo(var3));
                  break;
               case 14:
                  this.this$0.sendSettingMessageTwo(1, var3);
                  break;
               case 15:
                  this.this$0.resolveSelectPosData047(var3 + 3);
                  break;
               case 16:
                  this.this$0.resolveSelectPosData003(var3 + 1);
                  break;
               case 17:
                  this.this$0.resolveSelectPosData147(var3 + 1);
                  break;
               case 18:
                  this.this$0.resolveSelectPosData123(var3 + 1);
                  break;
               case 19:
                  this.this$0.resolveSelectPosData101(var3 + 1);
                  break;
               case 20:
                  this.this$0.sendSettingMessageOne(3, var3);
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
            if (var4.equals("_379_item_11")) {
               this.this$0.sendSettingMessageOne(2, var3);
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            var2 = var3.hashCode();
            byte var5 = -1;
            switch (var2) {
               case 238180704:
                  if (var3.equals("_379_item_30")) {
                     var5 = 0;
                  }
                  break;
               case 712995820:
                  if (var3.equals("reset_balance")) {
                     var5 = 1;
                  }
                  break;
               case 2024113841:
                  if (var3.equals("reset_all")) {
                     var5 = 2;
                  }
            }

            switch (var5) {
               case 0:
                  TypeInView var4 = new TypeInView();
                  UiMgr var6 = this.this$0;
                  var4.showDialog(var6.getMsgMgr(var6.mContext).getCurrentActivity());
                  break;
               case 1:
                  this.this$0.sendAmplifier(10, 1);
                  break;
               case 2:
                  this.this$0.sendAmplifier(11, 1);
            }

         }
      });
      ParkPageUiSet var4 = this.getParkPageUiSet(this.mContext);
      this.parkPageUiSet = var4;
      var4.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            this.this$0.Force = true;
            UiMgr var2;
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 6) {
                     this.this$0.Force = false;
                     var2 = this.this$0;
                     var2.getMsgMgr(var2.mContext).forceReverse(this.this$0.mContext, this.this$0.Force);
                  }
               } else {
                  var2 = this.this$0;
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, (byte)var2.ResolveTrueOrFalseTwo(var2.CameraDelay)});
               }
            } else {
               var2 = this.this$0;
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, (byte)var2.ResolveTrueOrFalseThree(var2.DepressedView)});
            }

         }
      });
   }

   private int ResolveTrueOrFalse(boolean var1) {
      return var1 ? 0 : 1;
   }

   private int ResolveTrueOrFalseThree(boolean var1) {
      byte var2 = 0;
      if (var1) {
         this.DepressedView = false;
      } else {
         this.DepressedView = true;
         var2 = 1;
      }

      return var2;
   }

   private int ResolveTrueOrFalseTwo(boolean var1) {
      byte var2 = 0;
      if (var1) {
         this.CameraDelay = false;
      } else {
         this.CameraDelay = true;
         var2 = 1;
      }

      return var2;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private int resolveHeat(int var1) {
      return var1 == 3 ? 0 : var1 + 1;
   }

   private void resolveSelectPosData003(int var1) {
      if (var1 >= 3) {
         this.data003 = var1 + 2;
      } else {
         this.data003 = var1;
      }

      this.sendSettingMessageFour();
   }

   private void resolveSelectPosData047(int var1) {
      this.data047 = var1;
      this.sendSettingMessageFour();
   }

   private void resolveSelectPosData101(int var1) {
      this.data101 = var1;
      this.sendSettingMessageFour();
   }

   private void resolveSelectPosData123(int var1) {
      this.data123 = var1;
      this.sendSettingMessageFour();
   }

   private void resolveSelectPosData147(int var1) {
      this.data147 = var1;
      this.sendSettingMessageFour();
   }

   private int resolveSelectPosFour(int var1) {
      return (new int[]{1, 2, 5, 7})[var1];
   }

   private int resolveSelectPosOne(int var1) {
      return (new int[]{255, 240, 241})[var1];
   }

   private int resolveSelectPosThr(int var1) {
      int var2 = var1;
      if (var1 == 7) {
         var2 = 0;
      }

      return var2;
   }

   private int resolveSelectPosTwo(int var1) {
      return (new int[]{255, 0, 1, 2})[var1];
   }

   private int resolvedec(int var1) {
      return var1 == 0 ? 0 : var1 - 1;
   }

   private int resolveinc(int var1) {
      return var1 == 7 ? 7 : var1 + 1;
   }

   private void sendAirMessage(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, (byte)var2});
   }

   private void sendAmplifier(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte)var1, (byte)var2});
   }

   private void sendSettingMessageFour() {
      int var4 = this.data047;
      int var1 = this.data003;
      int var5 = this.data147;
      int var2 = this.data123;
      int var3 = this.data101;
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)(var4 * 16 + var1), (byte)(var5 * 16 + var2 * 4 + var3)});
   }

   private void sendSettingMessageOne(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, 0, 0});
   }

   private void sendSettingMessageThr(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)var1});
   }

   private void sendSettingMessageTwo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte)var1, (byte)var2});
   }
}
