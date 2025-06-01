package com.hzbhd.canbus.car._96;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int currentClickFront;
   private int data1;
   private int data2;
   private int data3;
   private int frontRear;
   private boolean isRadio;
   private int leftRight;
   private AbstractBaseActivity mActivity;
   private OnAmplifierSeekBarListener mAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         Log.d("cwh", "progress = " + var2);
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)var2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)var2});
         }

      }
   };
   private OnOriginalCarDeviceBackPressedListener mBackPressed = new OnOriginalCarDeviceBackPressedListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onBackPressed() {
         CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -111, 0, 2}, 16));
      }
   };
   private OnOriginalBottomBtnClickListener mBottom = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         Log.d("cwh", "onClickBottomBtnItem:  isRadio  = " + this.this$0.isRadio);
         if (this.this$0.isRadio) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           if (var1 == 5) {
                              if (this.this$0.mScan == 0) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 1, 0, 0});
                                 this.this$0.mScan = 1;
                              } else {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -15, 5, 0, 0, 0});
                                 this.this$0.mScan = 0;
                              }
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 1, 0, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 1, 0, 0});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -15, 2, 0, 0, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -15, 1, 0, 0, 0});
               }
            } else if (this.this$0.mCurrentFreq == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 0, 0, 0});
               this.this$0.mCurrentFreq = 1;
            } else {
               this.this$0.mCurrentFreq = 0;
               CanbusMsgSender.sendMsg(new byte[]{22, -15, 3, 1, 0, 0});
            }
         } else if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -81, 7, 0});
                     }
                  } else if (this.this$0.value2 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -81, 3, 0});
                     this.this$0.value2 = 1;
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -81, 3, 1});
                     this.this$0.value2 = 0;
                  }
               } else if (this.this$0.mCurrentPlayStatusCd == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -81, 1, 0});
                  this.this$0.mCurrentPlayStatusCd = 1;
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -81, 2, 0});
                  this.this$0.mCurrentPlayStatusCd = 0;
               }
            } else if (this.this$0.value1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 0});
               this.this$0.value1 = 1;
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -81, 5, 1});
               this.this$0.value1 = 0;
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -81, 7, 1});
         }

      }
   };
   private String mBtStatus;
   private Context mContext;
   private int mCurrentFreq;
   private int mCurrentPlayStatusCd;
   private String[][] mDiagitalKeyboardActions;
   private String[][] mFeaturesKeyboardActions;
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
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
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
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
   private boolean mIsFeaturesKeyboard = true;
   private MsgMgr mMsgMgr;
   private OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               if (this.this$0.frontRear != var2) {
                  this.this$0.frontRear = var2;
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 7)});
               }

               Log.d("cwh", "value  = " + var2);
               Log.d("cwh", "frontRear  = " + this.this$0.frontRear);
            }
         } else {
            if (this.this$0.leftRight != var2) {
               this.this$0.leftRight = var2;
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 7)});
            }

            Log.d("cwh", "value  = " + var2);
            Log.d("cwh", "leftRight  = " + this.this$0.leftRight);
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
         } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
         }

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
               if (var1 == 2) {
                  this.this$0.currentClickFront = 0;
                  this.this$0.sendAirClick(10);
               }
            } else {
               this.this$0.currentClickFront = 2;
               this.this$0.sendAirClick(9);
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
         }

      }

      public void onRightSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.currentClickFront = 0;
                  this.this$0.sendAirClick(10);
               }
            } else {
               this.this$0.currentClickFront = 2;
               this.this$0.sendAirClick(9);
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
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
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 1});
                           CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 1});
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 0});
                     }
                  } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
                  }
               } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 0});
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
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
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
   private OnSettingItemSelectListener mOnItemSelectedListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         switch (var1) {
            case 0:
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 27});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
               }
               break;
            case 1:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 4, (byte)var3});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 6, (byte)var3});
               }
               break;
            case 2:
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 3) {
                           if (var2 == 4) {
                              CanbusMsgSender.sendMsg(new byte[]{22, 108, 7, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, 108, 6, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 5, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 108, 4, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 108, 3, (byte)var3});
               }
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)(var2 + 3), (byte)var3});
               break;
            case 4:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, (byte)var3});
               } else {
                  SharePreUtil.setIntValue(this.this$0.mContext, "_96_park_assess_item", var3);
                  this.this$0.mMsgMgr.updateOrignalSetting();
               }
               break;
            case 5:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3, 0, 0});
               }
               break;
            case 6:
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte)var3});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 8, (byte)var3});
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
         if (var1 != 0) {
            if (var1 == 1) {
               if (MsgMgr.overlook) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 1});
               }
            }
         } else if (MsgMgr.angleWide) {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
         }

      }
   };
   private OnAirBtnClickListener mOnRearAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 0});
         }

      }
   };
   OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private OnOriginalCarDevicePageStatusListener mPageStatus = new OnOriginalCarDevicePageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         UiMgr var3 = this.this$0;
         Context var4 = var3.mContext;
         this.this$0.mMsgMgr;
         boolean var2 = true;
         if (SharePreUtil.getIntValue(var4, "original_radio_cd", 1) != 1) {
            var2 = false;
         }

         var3.isRadio = var2;
         if (this.this$0.isRadio) {
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -111, -3, 2}, 16));
         } else {
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -111, -4, 2}, 16));
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 0});
      }
   };
   private int mScan;
   private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
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
   private OnAirWindSpeedUpDownClickListener mSetRearWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 0});
      }
   };
   private OnOriginalSongItemClickListener mSongItem = new OnOriginalSongItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onSongItemClick(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -15, 4, (byte)(var1 + 1), 0, 0});
      }

      public void onSongItemLongClick(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -15, 8, (byte)(var1 + 1), 0, 0});
      }
   };
   private SettingPageUiSet settingPageUiSet;
   private int value1;
   private int value2;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      (new DriverDataPageUiSet()).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 50});
         }
      });
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(this.mOnItemSelectedListener);
      this.settingPageUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_379_item_30")) {
               (new TypeInView()).showDialog(this.this$0.mMsgMgr.getCurrentActivity());
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
            if (var4.equals("_94_atmosphere_lamp")) {
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var3, 0, 0});
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (!var4.equals("S96_assit_warning")) {
               if (var4.equals("S96_Car_auxiliary_line")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 11, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 10, (byte)var3});
            }

         }
      });
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      var3.setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
      var3.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void addViewToWindows() {
         }
      });
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierSeekBarListener(this.mAmplifierSeekBarListener);
      var4.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      AirPageUiSet var5 = this.getAirUiSet(var1);
      var5.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
      var5.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
      var5.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
      var5.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
      var5.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
      var5.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearTempSetViewOnUpDownClickListenerCenter, null});
      var5.getRearArea().setSetWindSpeedViewOnClickListener(this.mSetRearWindSpeedViewOnClickListener);
      var5.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirBottomBtnClickListener});
      this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 6) {
               if (var2 == 2) {
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 10, 1});
               } else {
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, 1});
               }
            }

         }
      });
      OriginalCarDevicePageUiSet var6 = this.getOriginalCarDevicePageUiSet(var1);
      this.mOriginalCarDevicePageUiSet = var6;
      var6.setOnOriginalCarDeviceBackPressedListener(this.mBackPressed);
      this.mOriginalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(this.mBottom);
      this.mOriginalCarDevicePageUiSet.setOnOriginalSongItemClickListener(this.mSongItem);
      this.mOriginalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(this.mPageStatus);
      SyncPageUiSet var7 = this.getSyncPageUiSet(var1);
      this.mBtStatus = "btphone_off";
      var7.setOnSyncItemClickListener(new OnSyncItemClickListener(this, var7) {
         final UiMgr this$0;
         final SyncPageUiSet val$syncPageUiSet;

         {
            this.this$0 = var1;
            this.val$syncPageUiSet = var2;
         }

         public void onKeyboardBtnClick(String var1) {
            var1.hashCode();
            int var3 = var1.hashCode();
            byte var2 = -1;
            switch (var3) {
               case -1886439238:
                  if (var1.equals("number_0")) {
                     var2 = 0;
                  }
                  break;
               case -1886439237:
                  if (var1.equals("number_1")) {
                     var2 = 1;
                  }
                  break;
               case -1886439236:
                  if (var1.equals("number_2")) {
                     var2 = 2;
                  }
                  break;
               case -1886439235:
                  if (var1.equals("number_3")) {
                     var2 = 3;
                  }
                  break;
               case -1886439234:
                  if (var1.equals("number_4")) {
                     var2 = 4;
                  }
                  break;
               case -1886439233:
                  if (var1.equals("number_5")) {
                     var2 = 5;
                  }
                  break;
               case -1886439232:
                  if (var1.equals("number_6")) {
                     var2 = 6;
                  }
                  break;
               case -1886439231:
                  if (var1.equals("number_7")) {
                     var2 = 7;
                  }
                  break;
               case -1886439230:
                  if (var1.equals("number_8")) {
                     var2 = 8;
                  }
                  break;
               case -1886439229:
                  if (var1.equals("number_9")) {
                     var2 = 9;
                  }
                  break;
               case -988476804:
                  if (var1.equals("pickup")) {
                     var2 = 10;
                  }
                  break;
               case 3548:
                  if (var1.equals("ok")) {
                     var2 = 11;
                  }
                  break;
               case 3739:
                  if (var1.equals("up")) {
                     var2 = 12;
                  }
                  break;
               case 96964:
                  if (var1.equals("aux")) {
                     var2 = 13;
                  }
                  break;
               case 3089570:
                  if (var1.equals("down")) {
                     var2 = 14;
                  }
                  break;
               case 3237038:
                  if (var1.equals("info")) {
                     var2 = 15;
                  }
                  break;
               case 3317767:
                  if (var1.equals("left")) {
                     var2 = 16;
                  }
                  break;
               case 3377907:
                  if (var1.equals("next")) {
                     var2 = 17;
                  }
                  break;
               case 3449395:
                  if (var1.equals("prev")) {
                     var2 = 18;
                  }
                  break;
               case 3540562:
                  if (var1.equals("star")) {
                     var2 = 19;
                  }
                  break;
               case 3645646:
                  if (var1.equals("well")) {
                     var2 = 20;
                  }
                  break;
               case 108511772:
                  if (var1.equals("right")) {
                     var2 = 21;
                  }
                  break;
               case 1725083810:
                  if (var1.equals("btphone_on")) {
                     var2 = 22;
                  }
                  break;
               case 1937990412:
                  if (var1.equals("btphone_off")) {
                     var2 = 23;
                  }
            }

            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 20});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 21});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 22});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 23});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 24});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 25});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 26});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 27});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 28});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 29});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 18});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 16});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 12});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 17});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 13});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 19});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 14});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 11});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 10});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 32});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 33});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 15});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 31});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 2, 30});
            }

         }

         public void onKeyboardBtnLongClick(String var1) {
         }

         public void onLeftIconClick(String var1) {
            var1.hashCode();
            if (var1.equals("keyboard")) {
               if (this.this$0.mIsFeaturesKeyboard) {
                  this.this$0.mIsFeaturesKeyboard = false;
                  this.val$syncPageUiSet.setKeyboardActions(this.this$0.getDiagitalKeyboardActions());
               } else {
                  this.this$0.mIsFeaturesKeyboard = true;
                  this.val$syncPageUiSet.setKeyboardActions(this.this$0.getFeaturesKeyboardActions());
               }

               this.this$0.mMsgMgr.updateSync((Bundle)null);
            }

         }

         public void onListItemClick(int var1) {
         }

         public void onSoftKeyClick(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)GeneralSyncData.mSyncScreemNumber, 1, (byte)(var1 + 1)});
         }
      });
      if (this.getCurrentCarId() != 17) {
         this.removeMainPrjBtnByName(var1, "sync");
      } else {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

   }

   private String[][] getDiagitalKeyboardActions() {
      if (this.mDiagitalKeyboardActions == null) {
         this.mDiagitalKeyboardActions = new String[][]{{"number_1", "number_2", "number_3"}, {"number_4", "number_5", "number_6"}, {"number_7", "number_8", "number_9"}, {"star", "number_0", "well"}, {"pickup", this.mBtStatus}};
      }

      return this.mDiagitalKeyboardActions;
   }

   private String[][] getFeaturesKeyboardActions() {
      if (this.mFeaturesKeyboardActions == null) {
         String[] var1 = new String[]{"null", "down", "null"};
         this.mFeaturesKeyboardActions = new String[][]{{"null", "up", "null"}, {"left", "ok", "right"}, var1, {"info", "aux"}, {"prev", "next"}};
      }

      return this.mFeaturesKeyboardActions;
   }

   private void sendAirBtnCtrl(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var1, this.setWidgetData(var2)});
   }

   private void sendAirClick(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var1, 1});
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

      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var2, (byte)var1});
   }

   private void sendSettingResetData(int var1) {
      Context var2 = this.mContext;
      Toast.makeText(var2, var2.getText(2131769831), 0).show();
      this.sendData(new byte[]{22, 26, (byte)var1});
   }

   private byte setWidgetData(boolean var1) {
      return (byte)(var1 ^ 1);
   }

   protected AbstractBaseActivity getActivity() {
      return this.mActivity;
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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   void updateBtStatus(Context var1, int var2) {
      if (var2 == 1) {
         this.mBtStatus = "btphone_on";
      } else {
         this.mBtStatus = "btphone_off";
      }

      this.mDiagitalKeyboardActions = null;
      if (!this.mIsFeaturesKeyboard) {
         this.mIsFeaturesKeyboard = true;
         this.getSyncPageUiSet(var1).getOnSyncItemClickListener().onLeftIconClick("keyboard");
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() != 37) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"S96ColorTitle", "S96_Car_auxiliary_line", "S96_assit_warning"});
      }

      if (this.getCurrentCarId() == 37) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"light_ctrl_5"});
      }

   }
}
