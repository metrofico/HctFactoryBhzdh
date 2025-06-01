package com.hzbhd.canbus.car._118;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int DATA3;
   int DATA4;
   int UtilFUllWeekend;
   int UtilFullWork;
   int WeekEndMinuteAndHour;
   int WeekStartMinuteAndHour;
   int WorkEndMinuteAndHour;
   int WorkStartMinuteAndHour;
   int i = 0;
   int[] j = new int[]{5, 6, 7};
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
   int mCurrentCanDifferentId;
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      private void setFrontSearClick() {
         if (GeneralAirData.front_left_blow_head && !GeneralAirData.front_right_blow_foot && !GeneralAirData.front_right_blow_window) {
            this.this$0.sendAirCommand(25);
         } else if (GeneralAirData.front_left_blow_head && GeneralAirData.front_right_blow_foot && !GeneralAirData.front_right_blow_window) {
            this.this$0.sendAirCommand(26);
         } else if (!GeneralAirData.front_left_blow_head && GeneralAirData.front_right_blow_foot && !GeneralAirData.front_right_blow_window) {
            this.this$0.sendAirCommand(27);
         } else {
            this.this$0.sendAirCommand(24);
         }

      }

      public void onLeftSeatClick() {
         if (this.this$0.mCurrentCanDifferentId != 2 && this.this$0.mCurrentCanDifferentId != 9 && this.this$0.mCurrentCanDifferentId != 10 && this.this$0.mCurrentCanDifferentId != 11 && this.this$0.mCurrentCanDifferentId != 12 && this.this$0.mCurrentCanDifferentId != 14) {
            this.setFrontSearClick();
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.mCurrentCanDifferentId != 2 && this.this$0.mCurrentCanDifferentId != 9 && this.this$0.mCurrentCanDifferentId != 10 && this.this$0.mCurrentCanDifferentId != 11 && this.this$0.mCurrentCanDifferentId != 12 && this.this$0.mCurrentCanDifferentId != 14) {
            this.setFrontSearClick();
         }

      }
   };
   OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 3, (byte)(-var2 + 10)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -109, 2, (byte)(var2 + 10)});
         }

      }
   };
   OnAmplifierSeekBarListener mOnAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
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
                     CanbusMsgSender.sendMsg(new byte[]{22, -109, 1, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -109, 6, (byte)(var2 + 1)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 5, (byte)(var2 + 1)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -109, 4, (byte)(var2 + 1)});
         }

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
         if (this.this$0.mCurrentCanDifferentId == 5 || this.this$0.mCurrentCanDifferentId == 6 || this.this$0.mCurrentCanDifferentId == 7) {
            this.this$0.sendAirCommand(48);
         }

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
         if (this.this$0.mCurrentCanDifferentId == 5 || this.this$0.mCurrentCanDifferentId == 6 || this.this$0.mCurrentCanDifferentId == 7) {
            this.this$0.sendAirCommand(50);
         }

      }
   };
   OnOriginalBottomBtnClickListener mOnOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, 0, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, 0, 0});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -96, 20, 0, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -96, 19, 0, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, 0, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, 0, 0});
         }

      }
   };
   OnOriginalSongItemClickListener mOnOriginalSongItemClickListener = new OnOriginalSongItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onSongItemClick(int var1) {
         ++var1;
         byte var2 = (byte)('\uff00' & var1);
         byte var3 = (byte)(var1 & 255);
         CanbusMsgSender.sendMsg(new byte[]{22, -96, 15, var2, var3});
         CanbusMsgSender.sendMsg(new byte[]{22, -96, -126, var2, var3});
      }

      public void onSongItemLongClick(int var1) {
      }
   };
   OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 8, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 17, 0, 0});
         }

      }
   };
   OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         int var3 = var4.hashCode();
         byte var6 = -1;
         switch (var3) {
            case -2001097618:
               if (var4.equals("_118_setting_Title023")) {
                  var6 = 0;
               }
               break;
            case -2001097522:
               if (var4.equals("_118_setting_Title056")) {
                  var6 = 1;
               }
               break;
            case -2001097458:
               if (var4.equals("_118_setting_Title078")) {
                  var6 = 2;
               }
               break;
            case -1904456158:
               if (var4.equals("_118_setting_Title1011")) {
                  var6 = 3;
               }
               break;
            case -1450024860:
               if (var4.equals("_118_setting_Title01")) {
                  var6 = 4;
               }
               break;
            case -1450024857:
               if (var4.equals("_118_setting_Title04")) {
                  var6 = 5;
               }
               break;
            case -1450024852:
               if (var4.equals("_118_setting_Title09")) {
                  var6 = 6;
               }
               break;
            case -1450024821:
               if (var4.equals("_118_setting_Title19")) {
                  var6 = 7;
               }
               break;
            case -1450024799:
               if (var4.equals("_118_setting_Title20")) {
                  var6 = 8;
               }
               break;
            case -1450024798:
               if (var4.equals("_118_setting_Title21")) {
                  var6 = 9;
               }
               break;
            case -1450024797:
               if (var4.equals("_118_setting_Title22")) {
                  var6 = 10;
               }
               break;
            case -1450024796:
               if (var4.equals("_118_setting_Title23")) {
                  var6 = 11;
               }
               break;
            case -1450024795:
               if (var4.equals("_118_setting_Title24")) {
                  var6 = 12;
               }
               break;
            case -1450024794:
               if (var4.equals("_118_setting_Title25")) {
                  var6 = 13;
               }
               break;
            case -1450024793:
               if (var4.equals("_118_setting_Title26")) {
                  var6 = 14;
               }
               break;
            case -17558476:
               if (var4.equals("compass_calibration_status")) {
                  var6 = 15;
               }
         }

         UiMgr var5;
         UiMgr var7;
         SetTimeView var9;
         switch (var6) {
            case 0:
               var9 = new SetTimeView();
               var5 = this.this$0;
               var9.showDialog(var5.getMsgMgr(var5.mContext).getCurrentActivity(), this.this$0.mContext.getResources().getString(2131756913), false, false, false, true, true, this.this$0.timeResultListener);
               break;
            case 1:
               var9 = new SetTimeView();
               var5 = this.this$0;
               var9.showDialog(var5.getMsgMgr(var5.mContext).getCurrentActivity(), this.this$0.mContext.getResources().getString(2131756913), false, false, false, true, true, this.this$0.timeResultListener1);
               break;
            case 2:
               SetTimeView var8 = new SetTimeView();
               var7 = this.this$0;
               var8.showDialog(var7.getMsgMgr(var7.mContext).getCurrentActivity(), this.this$0.mContext.getResources().getString(2131756913), false, false, false, true, true, this.this$0.timeResultListener2);
               break;
            case 3:
               var9 = new SetTimeView();
               var5 = this.this$0;
               var9.showDialog(var5.getMsgMgr(var5.mContext).getCurrentActivity(), this.this$0.mContext.getResources().getString(2131756913), false, false, false, true, true, this.this$0.timeResultListener3);
               break;
            case 4:
               var7 = this.this$0;
               if (var7.getMsgMgr(var7.mContext).PlanStatus == 0) {
                  this.this$0.sendTimeData();
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 0, 0, 0, 0, 0});
               }
               break;
            case 5:
               var7 = this.this$0;
               if (var7.getMsgMgr(var7.mContext).WorkUtilFull == 1) {
                  this.this$0.UtilFullWork = 0;
               } else {
                  this.this$0.UtilFullWork = 1;
               }

               var7 = this.this$0;
               var7.DATA3 = var7.getDecimalFrom8Bit(var7.UtilFullWork, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendTimeData();
               break;
            case 6:
               var7 = this.this$0;
               if (var7.getMsgMgr(var7.mContext).WeekendUtilFull == 0) {
                  this.this$0.UtilFUllWeekend = 1;
               } else {
                  this.this$0.UtilFUllWeekend = 0;
               }

               var7 = this.this$0;
               var7.DATA4 = var7.getDecimalFrom8Bit(var7.UtilFUllWeekend, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendTimeData();
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 8, 1});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 7, 1});
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 6, 1});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 5, 1});
               break;
            case 11:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 4, 1});
               break;
            case 12:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 3, 1});
               break;
            case 13:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 2, 1});
               break;
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 1, 1});
               break;
            case 15:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 86, (byte)(var2 - 2)});
         }

      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
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
            case -2065696482:
               if (var4.equals("_118_setting_title_57")) {
                  var5 = 0;
               }
               break;
            case -2065696481:
               if (var4.equals("_118_setting_title_58")) {
                  var5 = 1;
               }
               break;
            case -2065696424:
               if (var4.equals("_118_setting_title_73")) {
                  var5 = 2;
               }
               break;
            case -2065696358:
               if (var4.equals("_118_setting_title_97")) {
                  var5 = 3;
               }
         }

         switch (var5) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, -109, (byte)var3});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, -110, (byte)var3});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 87, (byte)var3});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 8, (byte)var3});
         }

      }
   };
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      private byte resolvedata(int var1) {
         if (var1 == 0) {
            return 0;
         } else if (var1 == 1) {
            return 30;
         } else {
            return (byte)(var1 == 2 ? 60 : 90);
         }
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         var2 = var4.hashCode();
         byte var5 = -1;
         switch (var2) {
            case -2065696613:
               if (var4.equals("_118_setting_title_10")) {
                  var5 = 0;
               }
               break;
            case -2065696612:
               if (var4.equals("_118_setting_title_11")) {
                  var5 = 1;
               }
               break;
            case -2065696611:
               if (var4.equals("_118_setting_title_12")) {
                  var5 = 2;
               }
               break;
            case -2065696610:
               if (var4.equals("_118_setting_title_13")) {
                  var5 = 3;
               }
               break;
            case -2065696609:
               if (var4.equals("_118_setting_title_14")) {
                  var5 = 4;
               }
               break;
            case -2065696608:
               if (var4.equals("_118_setting_title_15")) {
                  var5 = 5;
               }
               break;
            case -2065696607:
               if (var4.equals("_118_setting_title_16")) {
                  var5 = 6;
               }
               break;
            case -2065696606:
               if (var4.equals("_118_setting_title_17")) {
                  var5 = 7;
               }
               break;
            case -2065696605:
               if (var4.equals("_118_setting_title_18")) {
                  var5 = 8;
               }
               break;
            case -2065696604:
               if (var4.equals("_118_setting_title_19")) {
                  var5 = 9;
               }
               break;
            case -2065696582:
               if (var4.equals("_118_setting_title_20")) {
                  var5 = 10;
               }
               break;
            case -2065696581:
               if (var4.equals("_118_setting_title_21")) {
                  var5 = 11;
               }
               break;
            case -2065696580:
               if (var4.equals("_118_setting_title_22")) {
                  var5 = 12;
               }
               break;
            case -2065696579:
               if (var4.equals("_118_setting_title_23")) {
                  var5 = 13;
               }
               break;
            case -2065696578:
               if (var4.equals("_118_setting_title_24")) {
                  var5 = 14;
               }
               break;
            case -2065696577:
               if (var4.equals("_118_setting_title_25")) {
                  var5 = 15;
               }
               break;
            case -2065696576:
               if (var4.equals("_118_setting_title_26")) {
                  var5 = 16;
               }
               break;
            case -2065696575:
               if (var4.equals("_118_setting_title_27")) {
                  var5 = 17;
               }
               break;
            case -2065696574:
               if (var4.equals("_118_setting_title_28")) {
                  var5 = 18;
               }
               break;
            case -2065696573:
               if (var4.equals("_118_setting_title_29")) {
                  var5 = 19;
               }
               break;
            case -2065696551:
               if (var4.equals("_118_setting_title_30")) {
                  var5 = 20;
               }
               break;
            case -2065696550:
               if (var4.equals("_118_setting_title_31")) {
                  var5 = 21;
               }
               break;
            case -2065696548:
               if (var4.equals("_118_setting_title_33")) {
                  var5 = 22;
               }
               break;
            case -2065696547:
               if (var4.equals("_118_setting_title_34")) {
                  var5 = 23;
               }
               break;
            case -2065696546:
               if (var4.equals("_118_setting_title_35")) {
                  var5 = 24;
               }
               break;
            case -2065696545:
               if (var4.equals("_118_setting_title_36")) {
                  var5 = 25;
               }
               break;
            case -2065696544:
               if (var4.equals("_118_setting_title_37")) {
                  var5 = 26;
               }
               break;
            case -2065696543:
               if (var4.equals("_118_setting_title_38")) {
                  var5 = 27;
               }
               break;
            case -2065696542:
               if (var4.equals("_118_setting_title_39")) {
                  var5 = 28;
               }
               break;
            case -2065696520:
               if (var4.equals("_118_setting_title_40")) {
                  var5 = 29;
               }
               break;
            case -2065696519:
               if (var4.equals("_118_setting_title_41")) {
                  var5 = 30;
               }
               break;
            case -2065696518:
               if (var4.equals("_118_setting_title_42")) {
                  var5 = 31;
               }
               break;
            case -2065696517:
               if (var4.equals("_118_setting_title_43")) {
                  var5 = 32;
               }
               break;
            case -2065696516:
               if (var4.equals("_118_setting_title_44")) {
                  var5 = 33;
               }
               break;
            case -2065696515:
               if (var4.equals("_118_setting_title_45")) {
                  var5 = 34;
               }
               break;
            case -2065696514:
               if (var4.equals("_118_setting_title_46")) {
                  var5 = 35;
               }
               break;
            case -2065696513:
               if (var4.equals("_118_setting_title_47")) {
                  var5 = 36;
               }
               break;
            case -2065696512:
               if (var4.equals("_118_setting_title_48")) {
                  var5 = 37;
               }
               break;
            case -2065696511:
               if (var4.equals("_118_setting_title_49")) {
                  var5 = 38;
               }
               break;
            case -2065696489:
               if (var4.equals("_118_setting_title_50")) {
                  var5 = 39;
               }
               break;
            case -2065696488:
               if (var4.equals("_118_setting_title_51")) {
                  var5 = 40;
               }
               break;
            case -2065696487:
               if (var4.equals("_118_setting_title_52")) {
                  var5 = 41;
               }
               break;
            case -2065696486:
               if (var4.equals("_118_setting_title_53")) {
                  var5 = 42;
               }
               break;
            case -2065696485:
               if (var4.equals("_118_setting_title_54")) {
                  var5 = 43;
               }
               break;
            case -2065696484:
               if (var4.equals("_118_setting_title_55")) {
                  var5 = 44;
               }
               break;
            case -2065696483:
               if (var4.equals("_118_setting_title_56")) {
                  var5 = 45;
               }
               break;
            case -2065696480:
               if (var4.equals("_118_setting_title_59")) {
                  var5 = 46;
               }
               break;
            case -2065696458:
               if (var4.equals("_118_setting_title_60")) {
                  var5 = 47;
               }
               break;
            case -2065696457:
               if (var4.equals("_118_setting_title_61")) {
                  var5 = 48;
               }
               break;
            case -2065696456:
               if (var4.equals("_118_setting_title_62")) {
                  var5 = 49;
               }
               break;
            case -2065696455:
               if (var4.equals("_118_setting_title_63")) {
                  var5 = 50;
               }
               break;
            case -2065696454:
               if (var4.equals("_118_setting_title_64")) {
                  var5 = 51;
               }
               break;
            case -2065696453:
               if (var4.equals("_118_setting_title_65")) {
                  var5 = 52;
               }
               break;
            case -2065696452:
               if (var4.equals("_118_setting_title_66")) {
                  var5 = 53;
               }
               break;
            case -2065696451:
               if (var4.equals("_118_setting_title_67")) {
                  var5 = 54;
               }
               break;
            case -2065696450:
               if (var4.equals("_118_setting_title_68")) {
                  var5 = 55;
               }
               break;
            case -2065696361:
               if (var4.equals("_118_setting_title_94")) {
                  var5 = 56;
               }
               break;
            case -2065696360:
               if (var4.equals("_118_setting_title_95")) {
                  var5 = 57;
               }
               break;
            case -2065696359:
               if (var4.equals("_118_setting_title_96")) {
                  var5 = 58;
               }
               break;
            case -2065696357:
               if (var4.equals("_118_setting_title_98")) {
                  var5 = 59;
               }
               break;
            case 387914494:
               if (var4.equals("_118_setting_title_109")) {
                  var5 = 60;
               }
               break;
            case 387914516:
               if (var4.equals("_118_setting_title_110")) {
                  var5 = 61;
               }
               break;
            case 387914517:
               if (var4.equals("_118_setting_title_111")) {
                  var5 = 62;
               }
               break;
            case 387914518:
               if (var4.equals("_118_setting_title_112")) {
                  var5 = 63;
               }
               break;
            case 387914520:
               if (var4.equals("_118_setting_title_114")) {
                  var5 = 64;
               }
               break;
            case 387914521:
               if (var4.equals("_118_setting_title_115")) {
                  var5 = 65;
               }
               break;
            case 387914522:
               if (var4.equals("_118_setting_title_116")) {
                  var5 = 66;
               }
               break;
            case 387914523:
               if (var4.equals("_118_setting_title_117")) {
                  var5 = 67;
               }
               break;
            case 1180290613:
               if (var4.equals("_118_setting_title_1")) {
                  var5 = 68;
               }
               break;
            case 1180290614:
               if (var4.equals("_118_setting_title_2")) {
                  var5 = 69;
               }
               break;
            case 1180290615:
               if (var4.equals("_118_setting_title_3")) {
                  var5 = 70;
               }
               break;
            case 1180290616:
               if (var4.equals("_118_setting_title_4")) {
                  var5 = 71;
               }
               break;
            case 1180290617:
               if (var4.equals("_118_setting_title_5")) {
                  var5 = 72;
               }
               break;
            case 1180290618:
               if (var4.equals("_118_setting_title_6")) {
                  var5 = 73;
               }
               break;
            case 1180290619:
               if (var4.equals("_118_setting_title_7")) {
                  var5 = 74;
               }
               break;
            case 1180290620:
               if (var4.equals("_118_setting_title_8")) {
                  var5 = 75;
               }
               break;
            case 1180290621:
               if (var4.equals("_118_setting_title_9")) {
                  var5 = 76;
               }
         }

         switch (var5) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 19, (byte)(var3 + 1)});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 20, (byte)(var3 + 1)});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 21, (byte)(var3 + 1)});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -121, (byte)(var3 + 1)});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 34, (byte)(var3 + 1)});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 35, (byte)(var3 + 1)});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 36, (byte)(var3 + 1)});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 37, (byte)(var3 + 1)});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 38, (byte)(var3 + 1)});
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 49, (byte)(var3 + 1)});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 50, this.this$0.resolvedata2(var3)});
               break;
            case 11:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -119, (byte)(var3 + 1)});
               break;
            case 12:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -120, (byte)(var3 + 1)});
               break;
            case 13:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -95, this.this$0.resolvedata2(var3)});
               break;
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 65, (byte)(var3 + 1)});
               break;
            case 15:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 66, (byte)(var3 + 1)});
               break;
            case 16:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 67, (byte)(var3 + 1)});
               break;
            case 17:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 68, (byte)(var3 + 1)});
               break;
            case 18:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 69, (byte)(var3 + 1)});
               break;
            case 19:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 70, (byte)var3});
               break;
            case 20:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 71, (byte)(var3 + 1)});
               break;
            case 21:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 81, (byte)(var3 + 1)});
               break;
            case 22:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, -125, (byte)(var3 + 1)});
               break;
            case 23:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -127, (byte)var3});
               break;
            case 24:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -126, (byte)var3});
               break;
            case 25:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 8, (byte)var3});
               break;
            case 26:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 10, (byte)(var3 + 1)});
               break;
            case 27:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 11, (byte)(var3 + 1)});
               break;
            case 28:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 13, (byte)var3});
               break;
            case 29:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 16, (byte)(var3 + 1)});
               break;
            case 30:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 22, (byte)(var3 + 1)});
               break;
            case 31:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 32, (byte)(var3 + 1)});
               break;
            case 32:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 40, (byte)(var3 + 1)});
               break;
            case 33:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 84, (byte)(var3 + 1)});
               break;
            case 34:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 18, this.resolvedata(var3)});
               break;
            case 35:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 12, (byte)var3});
               break;
            case 36:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -124, (byte)(var3 + 1)});
               break;
            case 37:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -123, (byte)var3});
               break;
            case 38:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 96, (byte)var3});
               break;
            case 39:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 113, (byte)var3});
               break;
            case 40:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 114, (byte)var3});
               break;
            case 41:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 81, (byte)(var3 + 1)});
               break;
            case 42:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -112, (byte)(var3 + 1)});
               break;
            case 43:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 82, (byte)var3});
               break;
            case 44:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 81, (byte)(var3 + 1)});
               break;
            case 45:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -96, (byte)var3});
               break;
            case 46:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 115, (byte)var3});
               break;
            case 47:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 116, (byte)var3});
               break;
            case 48:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 97, (byte)var3});
               break;
            case 49:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -107, (byte)(var3 + 1)});
               break;
            case 50:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 41, (byte)(var3 + 1)});
               break;
            case 51:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 117, (byte)(var3 + 1)});
               break;
            case 52:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -122, (byte)(var3 + 1)});
               break;
            case 53:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 98, (byte)var3});
               break;
            case 54:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 99, (byte)var3});
               break;
            case 55:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 83, (byte)(var3 + 1)});
               break;
            case 56:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 10, (byte)var3});
               break;
            case 57:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 11, (byte)var3});
               break;
            case 58:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 9, (byte)var3});
               break;
            case 59:
               CanbusMsgSender.sendMsg(new byte[]{22, -109, 7, (byte)var3});
               break;
            case 60:
               UiMgr var6 = this.this$0;
               var6.getMsgMgr(var6.mContext).updateCarModel(this.this$0.mContext, var3);
               CanbusMsgSender.sendMsg(new byte[]{22, -18, 96, (byte)var3});
               break;
            case 61:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -106, (byte)(var3 + 1)});
               break;
            case 62:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -80, (byte)var3});
               break;
            case 63:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -74, (byte)var3});
               break;
            case 64:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -78, (byte)var3});
               break;
            case 65:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -77, (byte)var3});
               break;
            case 66:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -76, (byte)var3});
               break;
            case 67:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, -75, (byte)var3});
               break;
            case 68:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 1, (byte)var3});
               break;
            case 69:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 2, (byte)var3});
               break;
            case 70:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 3, (byte)var3});
               break;
            case 71:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 4, (byte)(var3 + 1)});
               break;
            case 72:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 5, (byte)var3});
               break;
            case 73:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 6, (byte)(var3 + 1)});
               break;
            case 74:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 7, (byte)(var3 + 1)});
               break;
            case 75:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 14, (byte)(var3 + 1)});
               break;
            case 76:
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 17, this.resolvedata(var3)});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(30);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(31);
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(32);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(33);
      }
   };
   OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirCommand(28);
      }

      public void onClickRight() {
         this.this$0.sendAirCommand(29);
      }
   };
   OnAirBtnClickListener onAirBtnClickListener_rearBottom = new UiMgr$$ExternalSyntheticLambda1(this);
   OnAirBtnClickListener onAirBtnClickListener_rearTop = new UiMgr$$ExternalSyntheticLambda0(this);
   OnAirSeatClickListener onAirSeatClickListener_rear = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         UiMgr var4 = this.this$0;
         int[] var3 = var4.j;
         UiMgr var2 = this.this$0;
         int var1 = var2.i++;
         var4.sendAirConditionerData(var3[var1 % 3]);
      }

      public void onRightSeatClick() {
         UiMgr var2 = this.this$0;
         int[] var4 = var2.j;
         UiMgr var3 = this.this$0;
         int var1 = var3.i++;
         var2.sendAirConditionerData(var4[var1 % 3]);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_rear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirConditionerData(10);
      }

      public void onClickUp() {
         this.this$0.sendAirConditionerData(11);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener_rear = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirConditionerData(8);
      }

      public void onClickRight() {
         this.this$0.sendAirConditionerData(9);
      }
   };
   private SettingPageUiSet settingPageUiSet;
   SetTimeView.TimeResultListener timeResultListener = new SetTimeView.TimeResultListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void result(int var1, int var2, int var3, int var4, int var5) {
         UiMgr var6 = this.this$0;
         var6.WorkStartMinuteAndHour = var6.resolvedata4(var4, var5);
         this.this$0.sendtimemsg();
      }
   };
   SetTimeView.TimeResultListener timeResultListener1 = new SetTimeView.TimeResultListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void result(int var1, int var2, int var3, int var4, int var5) {
         UiMgr var6 = this.this$0;
         var6.WorkEndMinuteAndHour = var6.resolvedata4(var4, var5);
         this.this$0.sendtimemsg();
      }
   };
   SetTimeView.TimeResultListener timeResultListener2 = new SetTimeView.TimeResultListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void result(int var1, int var2, int var3, int var4, int var5) {
         UiMgr var6 = this.this$0;
         var6.WeekStartMinuteAndHour = var6.resolvedata4(var4, var5);
         this.this$0.sendtimemsg();
      }
   };
   SetTimeView.TimeResultListener timeResultListener3 = new SetTimeView.TimeResultListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void result(int var1, int var2, int var3, int var4, int var5) {
         UiMgr var6 = this.this$0;
         var6.WeekEndMinuteAndHour = var6.resolvedata4(var4, var5);
         this.this$0.sendtimemsg();
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mCurrentCanDifferentId = this.getCurrentCarId();
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var3;
      var3.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
      this.settingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      this.settingPageUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 7});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 11});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 4});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 7});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 9});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 10});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 11});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 16});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 17});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 25});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 34});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 35});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 48});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 49});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 50});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 51});
         }
      });
      this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (!var3.equals("_118_setting_title_71")) {
               if (var3.equals("_118_setting_Title14")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -105, 85, 1});
            }

         }
      });
      AirPageUiSet var5 = this.getAirUiSet(var1);
      int var2 = this.mCurrentCanDifferentId;
      if (var2 != 4 && var2 != 13) {
         var5.setHaveRearArea(false);
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 5 && var2 != 6 && var2 != 7) {
         this.removeFrontAirButtonByName(this.mContext, "left_set_seat_heat");
         this.removeFrontAirButtonByName(this.mContext, "right_set_seat_heat");
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 2 || var2 == 9 || var2 == 10 || var2 == 11 || var2 == 12 || var2 == 14) {
         var5.getFrontArea().setAllBtnCanClick(false);
         var5.getFrontArea().setCanSetWindSpeed(false);
         var5.getFrontArea().setCanSetLeftTemp(false);
         var5.getFrontArea().setCanSetRightTemp(false);
      }

      String[][] var4 = var5.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var4[0];
      this.mAirBtnListFrontLeft = var4[1];
      this.mAirBtnListFrontRight = var4[2];
      this.mAirBtnListFrontBottom = var4[3];
      var5.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
      var5.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
      var5.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var5.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var5.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListener_rearTop, null, null, this.onAirBtnClickListener_rearBottom});
      var5.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.onAirTemperatureUpDownClickListener_rear, null});
      var5.getRearArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener_rear);
      var5.getRearArea().setOnAirSeatClickListener(this.onAirSeatClickListener_rear);
      var5.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 5});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 6});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 21});
         }
      });
      AmplifierPageUiSet var6 = this.getAmplifierPageUiSet(var1);
      var6.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      var6.setOnAmplifierSeekBarListener(this.mOnAmplifierSeekBarListener);
      OriginalCarDevicePageUiSet var7 = this.getOriginalCarDevicePageUiSet(var1);
      var7.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
      var7.setOnOriginalBottomBtnClickListeners(this.mOnOriginalBottomBtnClickListener);
      var7.setOnOriginalSongItemClickListener(this.mOnOriginalSongItemClickListener);
      var7.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
         }
      });
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 10});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 50});
            CanbusMsgSender.sendMsg(new byte[]{22, -15, 51});
         }
      });
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

   // $FF: synthetic method
   static void lambda$sendAirCommand$2(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte)var0, 0});
   }

   private byte resolvedata2(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 1) {
         return 3;
      } else {
         return (byte)(var1 == 2 ? 20 : 40);
      }
   }

   private int resolvedata3(boolean var1) {
      return var1 ? 1 : 0;
   }

   private int resolvedata4(int var1, int var2) {
      byte var3;
      int var4;
      byte var8;
      label35: {
         var4 = 1;
         var3 = 0;
         if ((var2 < 0 || var2 >= 15) && var2 != 60) {
            if (var2 >= 15 && var2 < 30) {
               var3 = 1;
               var8 = 0;
               break label35;
            }

            if (var2 >= 30 && var2 < 45) {
               var8 = (byte)var4;
               break label35;
            }

            if (var2 >= 45 && var2 < 60) {
               var3 = 1;
               var8 = (byte)var4;
               break label35;
            }
         }

         var8 = 0;
      }

      int var6 = this.resolvedata3(DataHandleUtils.getBoolBit0(var1));
      int var5 = this.resolvedata3(DataHandleUtils.getBoolBit1(var1));
      int var7 = this.resolvedata3(DataHandleUtils.getBoolBit2(var1));
      var4 = this.resolvedata3(DataHandleUtils.getBoolBit3(var1));
      return this.getDecimalFrom8Bit(0, var8, var3, this.resolvedata3(DataHandleUtils.getBoolBit4(var1)), var4, var7, var5, var6);
   }

   private int resolvedata5(int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var1 == 1) {
         return 1;
      } else {
         return var1 == 2 ? 2 : 3;
      }
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -107, (byte)var1, 1});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda2(var1), 100L);
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1878365090:
            if (var1.equals("right_set_seat_cold")) {
               var2 = 0;
            }
            break;
         case -1878226070:
            if (var1.equals("right_set_seat_heat")) {
               var2 = 1;
            }
            break;
         case -1786872896:
            if (var1.equals("steering_wheel_heating")) {
               var2 = 2;
            }
            break;
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 3;
            }
            break;
         case -1423573049:
            if (var1.equals("ac_max")) {
               var2 = 4;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 5;
            }
            break;
         case -479653111:
            if (var1.equals("left_set_seat_cold")) {
               var2 = 6;
            }
            break;
         case -479514091:
            if (var1.equals("left_set_seat_heat")) {
               var2 = 7;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 8;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 9;
            }
            break;
         case 3545755:
            if (var1.equals("sync")) {
               var2 = 10;
            }
            break;
         case 99489345:
            if (var1.equals("in_out_auto_cycle")) {
               var2 = 11;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 12;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(51);
            break;
         case 1:
            this.sendAirCommand(50);
            break;
         case 2:
            this.sendAirCommand(52);
            break;
         case 3:
            this.sendAirCommand(21);
            break;
         case 4:
            this.sendAirCommand(18);
            break;
         case 5:
            this.sendAirCommand(22);
            break;
         case 6:
            this.sendAirCommand(49);
            break;
         case 7:
            this.sendAirCommand(48);
            break;
         case 8:
            this.sendAirCommand(17);
            break;
         case 9:
            this.sendAirCommand(20);
            break;
         case 10:
            this.sendAirCommand(23);
            break;
         case 11:
            this.sendAirCommand(19);
            break;
         case 12:
            if (GeneralAirData.power) {
               this.sendAirCommand(16);
            } else {
               this.sendAirCommand(9);
            }
      }

   }

   private void sendAirConditionerData(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -106, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -106, var2, 0});
   }

   private void sendTimeData() {
      int var1 = this.UtilFUllWeekend;
      if (var1 == 0 && this.UtilFullWork == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte)this.WorkStartMinuteAndHour, (byte)this.WorkEndMinuteAndHour, (byte)this.WeekStartMinuteAndHour, (byte)this.WeekEndMinuteAndHour});
      } else if (var1 == 1 && this.UtilFullWork == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte)this.WorkStartMinuteAndHour, (byte)this.WorkEndMinuteAndHour, (byte)this.WeekStartMinuteAndHour, (byte)this.DATA4});
      } else if (var1 == 0 && this.UtilFullWork == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte)this.WorkStartMinuteAndHour, (byte)this.DATA3, (byte)this.WeekStartMinuteAndHour, (byte)this.WeekEndMinuteAndHour});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte)this.WorkStartMinuteAndHour, (byte)this.DATA3, (byte)this.WeekStartMinuteAndHour, (byte)this.DATA4});
      }

   }

   private void sendtimemsg() {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1, (byte)this.WorkStartMinuteAndHour, (byte)this.WorkEndMinuteAndHour, (byte)this.WeekStartMinuteAndHour, (byte)this.WeekEndMinuteAndHour});
   }

   private int swapVal(int var1) {
      byte var2;
      if (var1 == 0) {
         var2 = 2;
      } else {
         var2 = 1;
      }

      return var2;
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
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
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

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__118_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 == 1) {
            this.sendAirConditionerData(3);
         }
      } else {
         this.sendAirConditionerData(1);
      }

   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__118_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 == 1) {
            this.sendAirConditionerData(2);
         }
      } else {
         this.sendAirConditionerData(4);
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.mCurrentCanDifferentId;
      if (var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_9", "_118_setting_title_16", "_118_setting_title_54"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 2 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeMainPrjBtnByName(this.mContext, "amplifier");
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_title_93"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_16"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 8 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_1"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_2"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 8 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 7 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_3"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 9 || var2 == 10 || var2 == 12 || var2 == 13 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_5"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_7"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 6 && var2 != 8 && var2 != 11 && var2 != 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_36"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_37", "_118_setting_title_39"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 3 && var2 != 4) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_38"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_46"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 6 && var2 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_8"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_40"});
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_title_91"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 3 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_61"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 4 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_34", "_118_setting_title_35", "_118_setting_title_33"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 4 && var2 != 5 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_47"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_45"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 8 && var2 != 11 && var2 != 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_10", "_118_setting_title_12"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 2 && var2 != 3 && var2 != 4 && var2 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_11"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_41"});
      }

      if (this.mCurrentCanDifferentId != 4) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_13", "_118_setting_title_22"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 2 && var2 != 3) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_53", "_118_setting_title_58", "_118_setting_title_57"});
      }

      if (this.mCurrentCanDifferentId != 3) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_62"});
      }

      if (this.mCurrentCanDifferentId != 12) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_110"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 2 && var2 != 3 && var2 != 5 && var2 != 6) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_42"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 5 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_15"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 6 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_17"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 4 && var2 != 5) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_18", "_118_setting_title_19", "_118_setting_title_4", "_118_setting_title_65"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 5 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_43"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 3 && var2 != 4 && var2 != 7 && var2 != 8 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_63"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 2 && var2 != 3 && var2 != 4 && var2 != 5 && var2 != 7 && var2 != 8 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_56"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 5 && var2 != 6 && var2 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_20"});
      }

      if (this.mCurrentCanDifferentId != 6) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_23"});
      }

      if (this.mCurrentCanDifferentId != 5) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_big_title_5"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 5 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_68"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5 && var2 != 8 && var2 != 11 && var2 != 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_44"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 2) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_71"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 4 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_48"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 3 && var2 != 4 && var2 != 8 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_66"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 3 && var2 != 8 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_67"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 0 || var2 == 1 || var2 == 8 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_50", "_118_setting_title_51"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 0 || var2 == 1 || var2 == 2 || var2 == 8 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_59", "_118_setting_title_60"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 == 0 || var2 == 1 || var2 == 2 || var2 == 3 || var2 == 8 || var2 == 9 || var2 == 10 || var2 == 12 || var2 == 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_64"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 4 && var2 != 5 && var2 != 8 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_31"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 0 && var2 != 1 && var2 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_6"});
      }

      var2 = this.mCurrentCanDifferentId;
      if (var2 != 4 && var2 != 8 && var2 != 11) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_21"});
      }

      if (this.mCurrentCanDifferentId != 7) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_setting_big_title_10"});
      }

      if (this.mCurrentCanDifferentId != 11) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_118_DriveInfo_HeadTitle_02"});
      }

      this.removeSettingRightItemByNameList(this.mContext, new String[]{"_118_setting_title_52", "_118_setting_title_55"});
   }
}
