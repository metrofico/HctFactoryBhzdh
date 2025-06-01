package com.hzbhd.canbus.car._345;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private String BALANCE = "BALANCE";
   private String BASS = "BASS";
   private String FADER = "FADER";
   private String MIDTONE = "MIDTONE";
   private String TREBLE = "TREBLE";
   private String VOL = "VOL";
   OnAirBtnClickListener bottomButton = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private int day = 1;
   int differentId;
   int eachId;
   private int hour = 0;
   OnAirBtnClickListener leftButton = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.send0xA8Info(2);
         }

      }
   };
   OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0xA8Info(14);
      }

      public void onClickUp() {
         this.this$0.send0xA8Info(13);
      }
   };
   private int mBit0 = 0;
   private int mBit1 = 0;
   private int mBit2 = 0;
   private int mBit3 = 0;
   private int mBit4 = 0;
   private int mBit5 = 0;
   private int mBit6 = 0;
   private int mBit7 = 0;
   Context mContext;
   MsgMgr mMsgMgr;
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.send0xA8Info(31);
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
         this.this$0.send0xA8Info(32);
      }
   };
   private int minute = 0;
   private int month = 1;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(33);
      }
   };
   OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
         this.this$0.initAmplifierInfo();
      }

      public void destroy() {
      }
   };
   OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               this.this$0.sendAmplifierInfo(1, var2);
            }
         } else {
            this.this$0.sendAmplifierInfo(2, var2);
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
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
                     this.this$0.sendAmplifierInfo(4, var2 - 9);
                  }
               } else {
                  this.this$0.sendAmplifierInfo(3, var2 - 9);
               }
            } else {
               this.this$0.sendAmplifierInfo(5, var2 - 9);
            }
         } else {
            this.this$0.sendAmplifierInfo(0, var2);
         }

      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(20);
         this.this$0.activeRequestData(65, 2);
         this.this$0.activeRequestData(22);
         this.this$0.activeRequestData(36);
      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_setting_time_info")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 != 4) {
                           if (var2 == 5) {
                              this.this$0.second = var3;
                              var4 = this.this$0;
                              var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                              this.this$0.sendTimeInfo();
                           }
                        } else {
                           this.this$0.minute = var3;
                           var4 = this.this$0;
                           var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                           this.this$0.sendTimeInfo();
                        }
                     } else {
                        this.this$0.hour = var3;
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                        this.this$0.sendTimeInfo();
                     }
                  } else {
                     this.this$0.day = var3;
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                     this.this$0.sendTimeInfo();
                  }
               } else {
                  this.this$0.month = var3;
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
                  this.this$0.sendTimeInfo();
               }
            } else {
               this.this$0.year = var3 - 2000;
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               this.this$0.sendTimeInfo();
            }
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_paring_info")) {
            if (var2 != 1) {
               if (var2 == 2) {
                  this.this$0.sendCarControlInfo(4, var3);
               }
            } else {
               this.this$0.sendCarControlInfo(0, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_setting_car_info")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           if (var3 == 0) {
                              this.this$0.mBit2 = 0;
                              this.this$0.mBit1 = 0;
                              this.this$0.mBit0 = 0;
                           } else if (var3 == 1) {
                              this.this$0.mBit2 = 0;
                              this.this$0.mBit1 = 0;
                              this.this$0.mBit0 = 1;
                           } else if (var3 == 2) {
                              this.this$0.mBit2 = 0;
                              this.this$0.mBit1 = 1;
                              this.this$0.mBit0 = 0;
                           } else if (var3 == 3) {
                              this.this$0.mBit2 = 0;
                              this.this$0.mBit1 = 1;
                              this.this$0.mBit0 = 1;
                           } else if (var3 == 4) {
                              this.this$0.mBit2 = 1;
                              this.this$0.mBit1 = 0;
                              this.this$0.mBit0 = 0;
                           } else if (var3 == 5) {
                              this.this$0.mBit2 = 1;
                              this.this$0.mBit1 = 0;
                              this.this$0.mBit0 = 1;
                           }

                           this.this$0.sendOtherSettingInfo();
                        }
                     } else {
                        this.this$0.mBit3 = var3;
                        this.this$0.sendOtherSettingInfo();
                     }
                  } else {
                     this.this$0.mBit4 = var3;
                     this.this$0.sendOtherSettingInfo();
                  }
               } else {
                  this.this$0.mBit5 = var3;
                  this.this$0.sendOtherSettingInfo();
               }
            } else {
               if (var3 == 0) {
                  this.this$0.mBit7 = 0;
                  this.this$0.mBit6 = 0;
               } else if (var3 == 1) {
                  this.this$0.mBit7 = 0;
                  this.this$0.mBit6 = 1;
               } else if (var3 == 2) {
                  this.this$0.mBit7 = 1;
                  this.this$0.mBit6 = 0;
               } else if (var3 == 3) {
                  this.this$0.mBit7 = 1;
                  this.this$0.mBit6 = 1;
               }

               this.this$0.sendOtherSettingInfo();
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_setting_car_info32")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     this.this$0.sendCarControlInfo(3, var3 + 1);
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
                  }
               } else {
                  this.this$0.sendCarControlInfo(2, var3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
               }
            } else {
               this.this$0.sendCarControlInfo(1, var3);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_345_bmw_car")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_345_bmw_car", "_345_bmw_car_0")) {
               this.this$0.sendCarControlInfo(3, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_345_bmw_car", "_345_bmw_car_1")) {
               this.this$0.sendCarControlInfo(5, var3 + 1);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_345_bmw_car", "_345_bmw_car_2")) {
               this.this$0.sendCarControlInfo(6, var3 + 6);
            }
         }

      }
   };
   OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         UiMgr var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_246_paring_info")) {
            this.this$0.activeRequestData(37);
         }

         var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_246_setting_time_info")) {
            this.this$0.activeRequestData(65, 16);
         }

         var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_246_setting_car_info")) {
            this.this$0.activeRequestData(65, 17);
            this.this$0.getOtherSettingInfo();
         }

         var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_246_setting_car_info32")) {
            this.this$0.getOtherSettingInfo();
         }

      }
   };
   OnPanelKeyPositionListener panelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         switch (var1) {
            case 0:
               this.this$0.send0xA8Info(3);
               break;
            case 1:
               this.this$0.send0xA8Info(6);
               break;
            case 2:
               this.this$0.send0xA8Info(23);
               break;
            case 3:
               this.this$0.send0xA8Info(129);
               break;
            case 4:
               this.this$0.send0xA8Info(130);
               break;
            case 5:
               this.this$0.send0xA8Info(131);
               break;
            case 6:
               this.this$0.send0xA8Info(132);
               break;
            case 7:
               this.this$0.send0xA8Info(133);
               break;
            case 8:
               this.this$0.send0xA8Info(134);
               break;
            case 9:
               this.this$0.send0xA8Info(135);
               break;
            case 10:
               this.this$0.send0xA8Info(136);
               break;
            case 11:
               this.this$0.send0xA8Info(137);
               break;
            case 12:
               this.this$0.send0xA8Info(138);
               break;
            case 13:
               this.this$0.send0xA8Info(139);
               break;
            case 14:
               this.this$0.send0xA8Info(141);
               break;
            case 15:
               this.this$0.send0xA8Info(142);
               break;
            case 16:
               this.this$0.send0xA8Info(143);
               break;
            case 17:
               this.this$0.send0xA8Info(144);
               break;
            case 18:
               this.this$0.send0xA8Info(140);
         }

      }
   };
   OnAirBtnClickListener rightButton = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.send0xA8Info(16);
      }

      public void onClickUp() {
         this.this$0.send0xA8Info(15);
      }
   };
   OnAirSeatClickListener seatListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.send0xA8Info(17);
      }

      public void onRightSeatClick() {
         this.this$0.send0xA8Info(17);
      }
   };
   private int second = 0;
   OnAirWindSpeedUpDownClickListener setWind = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.send0xA8Info(12);
      }

      public void onClickRight() {
         this.this$0.send0xA8Info(11);
      }
   };
   OnAirBtnClickListener topButton = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.send0xA8Info(21);
                  }
               } else {
                  this.this$0.send0xA8Info(30);
               }
            } else {
               this.this$0.send0xA8Info(1);
            }
         } else {
            this.this$0.send0xA8Info(0);
         }

      }
   };
   private int year = 2000;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      FrontArea var3 = var2.getFrontArea();
      if (this.differentId == 1) {
         var3.setAllBtnCanClick(true);
         var3.setCanSetLeftTemp(true);
         var3.setCanSetRightTemp(true);
         var3.setCanSetWindSpeed(true);
         var3.setCanSetSeatHeat(true);
      }

      var3.setOnAirPageStatusListener(this.onAirPageStatusListener);
      var3.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topButton, this.leftButton, this.rightButton, this.bottomButton});
      var3.setSetWindSpeedViewOnClickListener(this.setWind);
      var3.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
      var3.setOnAirSeatClickListener(this.seatListener);
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, null, null});
      SettingPageUiSet var4 = this.getSettingUiSet(this.mContext);
      var4.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var4.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var4.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(this.mContext);
      var5.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var5.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var5.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(this.panelKeyPositionListener);
   }

   private void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private void activeRequestData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void getOtherSettingInfo() {
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 0, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "" + 0, 0));
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 1, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "" + 1, 0));
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 2, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "" + 2, 0));
   }

   private void initUi() {
      this.removeMainPrjBtnByName(this.mContext, "amplifier");
      if (this.differentId != 1) {
         this.removeMainPrjBtnByName(this.mContext, "panel_key");
      }

      if (this.eachId != 3) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_246_setting_time_info", "_246_setting_car_info"});
      }

      int var1 = this.eachId;
      if (var1 != 8 && var1 != 9) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_246_setting_car_info32", "_246_paring_info"});
      }

      if (this.getSettingLeftIndexes(this.mContext, "_246_paring_info") == -1 && this.getSettingLeftIndexes(this.mContext, "_246_setting_time_info") == -1 && this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") == -1 && this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info32") == -1) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

   }

   private void intiData() {
      this.initAmplifierInfo();
      this.selectionCarModel();
   }

   private void saveAmplifierInfo(int var1, int var2) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        SharePreUtil.setIntValue(this.mContext, this.TREBLE, var2);
                     }
                  } else {
                     SharePreUtil.setIntValue(this.mContext, this.MIDTONE, var2);
                  }
               } else {
                  SharePreUtil.setIntValue(this.mContext, this.BASS, var2);
               }
            } else {
               SharePreUtil.setIntValue(this.mContext, this.FADER, var2);
            }
         } else {
            SharePreUtil.setIntValue(this.mContext, this.BALANCE, var2);
         }
      } else {
         SharePreUtil.setIntValue(this.mContext, this.VOL, var2);
      }

   }

   private void send0xA8Info(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 0});
   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte)var1, (byte)var2});
      this.saveAmplifierInfo(var1, var2);
   }

   private void sendCarControlInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
   }

   private void sendCarModel(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)var1});
   }

   private void sendOtherSettingInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -90, 17, (byte)this.getDecimalFrom8Bit(this.mBit7, this.mBit6, this.mBit5, this.mBit4, this.mBit3, this.mBit2, this.mBit1, this.mBit0)});
   }

   private void sendTimeInfo() {
      CanbusMsgSender.sendMsg(new byte[]{22, -90, 16, (byte)this.year, (byte)this.month, (byte)this.day, (byte)this.hour, (byte)this.minute, (byte)this.second});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 65, 16});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var5 = var7.iterator();

         while(var5.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   void initAmplifierInfo() {
      GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, this.BALANCE, 0);
      GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, this.FADER, 0);
      GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, this.VOL, 28);
      GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, this.BASS, 0) + 9;
      GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(this.mContext, this.MIDTONE, 0) + 9;
      GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, this.TREBLE, 0) + 9;
      this.sendAmplifierInfo(0, SharePreUtil.getIntValue(this.mContext, this.VOL, 28));
      this.sendAmplifierInfo(1, SharePreUtil.getIntValue(this.mContext, this.BALANCE, 0));
      this.sendAmplifierInfo(2, SharePreUtil.getIntValue(this.mContext, this.FADER, 0));
      this.sendAmplifierInfo(3, SharePreUtil.getIntValue(this.mContext, this.BASS, 0));
      this.sendAmplifierInfo(4, SharePreUtil.getIntValue(this.mContext, this.MIDTONE, 0));
      this.sendAmplifierInfo(5, SharePreUtil.getIntValue(this.mContext, this.TREBLE, 0));
      this.getMsgMgr(this.mContext).updateAmplifier();
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void selectionCarModel() {
      switch (this.eachId) {
         case 1:
            this.sendCarModel(1);
            break;
         case 2:
            this.sendCarModel(2);
            break;
         case 3:
            this.sendCarModel(3);
            break;
         case 4:
            this.sendCarModel(4);
            break;
         case 5:
            this.sendCarModel(4);
            break;
         case 6:
            this.sendCarModel(4);
            break;
         case 7:
            this.sendCarModel(4);
            break;
         case 8:
            this.sendCarModel(5);
            break;
         case 9:
            this.sendCarModel(6);
            break;
         case 10:
            this.sendCarModel(7);
            break;
         case 11:
            this.sendCarModel(8);
      }

   }

   public void sendIconInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte)this.getDecimalFrom8Bit(0, 0, 0, 0, 0, var1, var2, 0)});
   }

   public void sendMediaPalyInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void sendPhoneNumber() {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 6, 1, 84, 101, 108, 32, 79, 102, 102});
   }

   public void sendPhoneNumber(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendRadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void sendSourceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
      this.intiData();
   }
}
