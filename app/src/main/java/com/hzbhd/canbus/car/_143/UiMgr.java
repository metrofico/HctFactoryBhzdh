package com.hzbhd.canbus.car._143;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import android.util.Log;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   int D0B1 = 0;
   int D0B1_0x8B = 0;
   int D0B2 = 0;
   int D0B2_0x8B = 0;
   int D0B3 = 0;
   int D0B3_0x8B = 0;
   int D0B4 = 0;
   int D0B4_0x8B = 0;
   int D0B5 = 0;
   int D0B5_0x8B = 0;
   public int D0B5_0xA1 = 0;
   int D0B6 = 0;
   int D0B6_0x8B = 0;
   public int D0B6_0xA1 = 0;
   int D0B7 = 0;
   int D0B7_0x8B = 0;
   public int D0B7_0xA1 = 0;
   int Data1 = 0;
   int Data1_0x8B = 0;
   public int Data1_0xA1 = 0;
   int Data2 = 0;
   int Data2_0x8B = 0;
   public int Data2_0xA1 = 0;
   int Data3 = 0;
   int Data3_0x8B = 0;
   int Data4 = 0;
   int Data4_0x8B = 0;
   int Data5 = 0;
   int Data5_0x8B = 0;
   int Data6 = 0;
   int Data6_0x8B = 0;
   String KEY_VOL_DISTRIBUTION = "KEY_VOL_DISTRIBUTION";
   String KEY_VOL_LOUD = "KEY_VOL_LOUD";
   String KEY_VOL_SELECT = "KEY_VOL_SELECT";
   String KEY_VOL_SET = "KEY_VOL_SET";
   private final String TAG = "_143_UiMgr";
   private int currentClickFront;
   private int currentWindLv;
   public int data10_0xA3;
   public int data11_0xA3;
   public int data12_0xA3;
   public int data1_0xA3;
   public int data2_0xA3;
   public int data3_0xA3;
   public int data4_0xA3;
   public int data5_0xA3;
   public int data6_0xA3;
   public int data7_0xA3;
   public int data8_0xA3;
   public int data9_0xA3;
   byte[] m0x8ACommand = new byte[2];
   private Context mContext;
   private int mDifferentId;
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAirBtnCtrl(6, GeneralAirData.rear_defog);
                  }
               } else {
                  this.this$0.sendAirBtnCtrl(5, GeneralAirData.front_defog);
               }
            } else {
               this.this$0.sendAirBtnCtrl(16, GeneralAirData.aqs);
            }
         } else {
            this.this$0.sendAirBtnCtrl(7, GeneralAirData.in_out_cycle);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirBtnCtrl(4, GeneralAirData.auto);
      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirBtnCtrl(1, GeneralAirData.power);
      }
   };
   private OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener(this) {
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
                           CanbusMsgSender.sendMsg(new byte[]{22, 59, 10, 0});
                        }
                     } else {
                        this.this$0.currentClickFront = 5;
                        this.this$0.sendAirClick(10);
                     }
                  } else {
                     this.this$0.currentClickFront = 4;
                     CanbusMsgSender.sendMsg(new byte[]{22, 59, 9, 0});
                  }
               } else {
                  this.this$0.currentClickFront = 3;
                  this.this$0.sendAirClick(9);
               }
            } else {
               this.this$0.currentClickFront = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 59, 8, 0});
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
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           this.this$0.currentClickFront = 0;
                           CanbusMsgSender.sendMsg(new byte[]{22, 59, 10, 0});
                        }
                     } else {
                        this.this$0.currentClickFront = 5;
                        this.this$0.sendAirClick(10);
                     }
                  } else {
                     this.this$0.currentClickFront = 4;
                     CanbusMsgSender.sendMsg(new byte[]{22, 59, 9, 0});
                  }
               } else {
                  this.this$0.currentClickFront = 3;
                  this.this$0.sendAirClick(9);
               }
            } else {
               this.this$0.currentClickFront = 2;
               CanbusMsgSender.sendMsg(new byte[]{22, 59, 8, 0});
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
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
                     var1 = this.this$0.currentWindLv;
                     if (var1 != 0) {
                        if (var1 != 1) {
                           if (var1 == 2) {
                              this.this$0.currentWindLv = 0;
                              CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 2});
                           }
                        } else {
                           this.this$0.currentWindLv = 2;
                           CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 1});
                        }
                     } else {
                        this.this$0.currentWindLv = 1;
                        CanbusMsgSender.sendMsg(new byte[]{22, 59, 14, 0});
                     }
                  }
               } else {
                  this.this$0.sendAirBtnCtrl(2, GeneralAirData.ac);
               }
            } else {
               this.this$0.sendAirBtnCtrl(15, GeneralAirData.mono);
            }
         } else {
            this.this$0.sendAirBtnCtrl(3, GeneralAirData.ac_max);
         }

      }
   };
   private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         switch (var1) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 22, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 22, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 23, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 23, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 38, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 38, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 25, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 25, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 36, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 36, 0});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 26, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 26, 0});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 37, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 37, 0});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 24, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 24, 0});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 39, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 33, 39, 0});
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 2});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 11, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 12, 2});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 12, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 13, 2});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 59, 13, 1});
      }
   };

   public UiMgr(Context var1) {
      Exception var10000;
      label235: {
         int var2;
         byte[] var3;
         boolean var10001;
         try {
            this.mContext = var1;
            var2 = this.getCurrentCarId();
            this.mDifferentId = var2;
            var3 = this.m0x8ACommand;
         } catch (Exception var22) {
            var10000 = var22;
            var10001 = false;
            break label235;
         }

         var3[0] = 22;
         var3[1] = -118;
         if (var2 == 4 || var2 == 5 || var2 == 12 || var2 == 52 || var2 == 13 || var2 == 53 || var2 == 23) {
            try {
               this.removeMainPrjBtnByName(var1, "warning");
            } catch (Exception var21) {
               var10000 = var21;
               var10001 = false;
               break label235;
            }
         }

         try {
            var2 = this.mDifferentId;
         } catch (Exception var20) {
            var10000 = var20;
            var10001 = false;
            break label235;
         }

         if (var2 != 6 && var2 != 8 && var2 != 17) {
            try {
               this.removeMainPrjBtnByName(var1, "panel_key");
            } catch (Exception var19) {
               var10000 = var19;
               var10001 = false;
               break label235;
            }
         }

         label202: {
            try {
               if (this.mDifferentId != 21) {
                  this.removeMainPrjBtnByName(var1, "amplifier");
                  this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_0xAD_setting1"});
                  break label202;
               }
            } catch (Exception var18) {
               var10000 = var18;
               var10001 = false;
               break label235;
            }

            try {
               this.initAmpContent(var1);
            } catch (Exception var17) {
               var10000 = var17;
               var10001 = false;
               break label235;
            }
         }

         try {
            var2 = this.mDifferentId;
         } catch (Exception var16) {
            var10000 = var16;
            var10001 = false;
            break label235;
         }

         if (var2 != 1 && var2 != 2 && var2 != 3 && var2 != 11 && var2 != 13 && var2 != 53 && var2 != 14 && var2 != 15 && var2 != 65 && var2 != 16 && var2 != 18 && var2 != 19 && var2 != 20 && var2 != 24 && var2 != 25 && var2 != 26 && var2 != 27) {
            try {
               this.removeSettingLeftItemByNameList(this.mContext, new String[]{"car_set"});
            } catch (Exception var15) {
               var10000 = var15;
               var10001 = false;
               break label235;
            }
         }

         try {
            var2 = this.mDifferentId;
         } catch (Exception var14) {
            var10000 = var14;
            var10001 = false;
            break label235;
         }

         if (var2 != 1 && var2 != 2 && var2 != 3 && var2 != 11 && var2 != 16 && var2 != 53 && var2 != 13 && var2 != 19 && var2 != 20 && var2 != 21 && var2 != 26 && var2 != 24 && var2 != 25 && var2 != 27) {
            try {
               this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_car_setting_2"});
            } catch (Exception var13) {
               var10000 = var13;
               var10001 = false;
               break label235;
            }
         }

         try {
            var2 = this.mDifferentId;
         } catch (Exception var12) {
            var10000 = var12;
            var10001 = false;
            break label235;
         }

         if (var2 != 3 && var2 != 14 && var2 != 16 && var2 != 26) {
            try {
               this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_speed_setting1"});
            } catch (Exception var11) {
               var10000 = var11;
               var10001 = false;
               break label235;
            }
         }

         try {
            var2 = this.mDifferentId;
         } catch (Exception var10) {
            var10000 = var10;
            var10001 = false;
            break label235;
         }

         if (var2 != 16 && var2 != 26) {
            try {
               this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_0x82_setting_1"});
            } catch (Exception var9) {
               var10000 = var9;
               var10001 = false;
               break label235;
            }
         }

         try {
            if (this.mDifferentId != 16) {
               this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_143_0x85_setting"});
            }
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label235;
         }

         label127: {
            AirPageUiSet var27;
            try {
               AmplifierPageUiSet var25 = this.getAmplifierPageUiSet(var1);
               OnAmplifierSeekBarListener var4 = new OnAmplifierSeekBarListener(this) {
                  final UiMgr this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
                     int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
                     if (var3 != 1) {
                        if (var3 == 2) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 15, (byte)var2});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 16, (byte)var2});
                     }

                  }
               };
               var25.setOnAmplifierSeekBarListener(var4);
               OnAmplifierPositionListener var28 = new OnAmplifierPositionListener(this) {
                  final UiMgr this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
                     int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
                     if (var3 != 1) {
                        if (var3 == 2) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 7)});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 7)});
                     }

                  }
               };
               var25.setOnAmplifierPositionListener(var28);
               SettingPageUiSet var26 = this.getSettingUiSet(var1);
               OnConfirmDialogListener var30 = new OnConfirmDialogListener(this) {
                  final UiMgr this$0;

                  {
                     this.this$0 = var1;
                  }

                  private void send0x1BInfo(int var1) {
                     byte var2 = (byte)var1;
                     CanbusMsgSender.sendMsg(new byte[]{22, 27, var2, var2, 1, -1});
                     UiMgr var3 = this.this$0;
                     var3.getMsgMgr(var3.mContext).toast("RESET SUCCESS!");
                  }

                  public void onConfirmClick(int var1, int var2) {
                     UiMgr var3 = this.this$0;
                     if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_143_comp_page")) {
                        var3 = this.this$0;
                        if (var2 == var3.getSettingRightIndex(var3.mContext, "_143_comp_page", "_143_comp_page1")) {
                           this.send0x1BInfo(2);
                        }

                        var3 = this.this$0;
                        if (var2 == var3.getSettingRightIndex(var3.mContext, "_143_comp_page", "_143_comp_page2")) {
                           this.send0x1BInfo(3);
                        }
                     }

                  }
               };
               var26.setOnSettingConfirmDialogListener(var30);
               OnSettingItemSelectListener var31 = new OnSettingItemSelectListener(this, var1, var26) {
                  final UiMgr this$0;
                  final Context val$context;
                  final SettingPageUiSet val$settingPageUiSet;

                  {
                     this.this$0 = var1;
                     this.val$context = var2;
                     this.val$settingPageUiSet = var3;
                  }

                  public void onClickItem(int var1, int var2, int var3) {
                     int var4;
                     if (var1 == 0) {
                        switch (var2) {
                           case 0:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 8, (byte)var3});
                              break;
                           case 1:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 11, (byte)var3});
                              break;
                           case 2:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 9, (byte)var3});
                              break;
                           case 3:
                              var4 = System.getInt(this.val$context.getContentResolver(), "left0right4", 0);
                              boolean var6;
                              if (var3 == 1) {
                                 var6 = true;
                              } else {
                                 var6 = false;
                              }

                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 10, (byte)DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var6), var4, 0, 7)});
                           case 4:
                           default:
                              break;
                           case 5:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 1, (byte)var3});
                              break;
                           case 6:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 2, (byte)var3});
                              break;
                           case 7:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 12, (byte)var3});
                              break;
                           case 8:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 13, (byte)var3});
                              break;
                           case 9:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 4, (byte)var3});
                              break;
                           case 10:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 5, (byte)var3});
                              break;
                           case 11:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 6, (byte)var3});
                              break;
                           case 12:
                              CanbusMsgSender.sendMsg(new byte[]{22, 123, 3, (byte)var3});
                              Intent var7 = new Intent("REVERSING_SOUND");
                              var7.putExtra("selectPos1", var3);
                              this.this$0.mContext.sendBroadcast(var7);
                        }
                     }

                     byte var5;
                     byte var9;
                     String var10;
                     label334: {
                        var10 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                        var10.hashCode();
                        var4 = var10.hashCode();
                        var5 = -1;
                        switch (var4) {
                           case -2043604067:
                              if (var10.equals("vm_golf7_vehicle_setup_units_temperature")) {
                                 var9 = 0;
                                 break label334;
                              }
                              break;
                           case -1422868826:
                              if (var10.equals("_143_0x94_language")) {
                                 var9 = 1;
                                 break label334;
                              }
                              break;
                           case -1273166604:
                              if (var10.equals("_143_setting_1")) {
                                 var9 = 2;
                                 break label334;
                              }
                              break;
                           case -1273166603:
                              if (var10.equals("_143_setting_2")) {
                                 var9 = 3;
                                 break label334;
                              }
                              break;
                           case -1273166602:
                              if (var10.equals("_143_setting_3")) {
                                 var9 = 4;
                                 break label334;
                              }
                              break;
                           case -1273166601:
                              if (var10.equals("_143_setting_4")) {
                                 var9 = 5;
                                 break label334;
                              }
                              break;
                           case -1273166600:
                              if (var10.equals("_143_setting_5")) {
                                 var9 = 6;
                                 break label334;
                              }
                              break;
                           case -1273166599:
                              if (var10.equals("_143_setting_6")) {
                                 var9 = 7;
                                 break label334;
                              }
                              break;
                           case -1273166598:
                              if (var10.equals("_143_setting_7")) {
                                 var9 = 8;
                                 break label334;
                              }
                              break;
                           case -1273166597:
                              if (var10.equals("_143_setting_8")) {
                                 var9 = 9;
                                 break label334;
                              }
                              break;
                           case -1273166596:
                              if (var10.equals("_143_setting_9")) {
                                 var9 = 10;
                                 break label334;
                              }
                              break;
                           case -1069177459:
                              if (var10.equals("_143_0x85_setting1")) {
                                 var9 = 11;
                                 break label334;
                              }
                              break;
                           case -813459012:
                              if (var10.equals("_143_setting_10")) {
                                 var9 = 12;
                                 break label334;
                              }
                              break;
                           case -813459011:
                              if (var10.equals("_143_setting_11")) {
                                 var9 = 13;
                                 break label334;
                              }
                              break;
                           case -813459010:
                              if (var10.equals("_143_setting_12")) {
                                 var9 = 14;
                                 break label334;
                              }
                              break;
                           case -813459009:
                              if (var10.equals("_143_setting_13")) {
                                 var9 = 15;
                                 break label334;
                              }
                              break;
                           case -813459008:
                              if (var10.equals("_143_setting_14")) {
                                 var9 = 16;
                                 break label334;
                              }
                              break;
                           case -813459007:
                              if (var10.equals("_143_setting_15")) {
                                 var9 = 17;
                                 break label334;
                              }
                              break;
                           case -813459005:
                              if (var10.equals("_143_setting_17")) {
                                 var9 = 18;
                                 break label334;
                              }
                              break;
                           case -813459004:
                              if (var10.equals("_143_setting_18")) {
                                 var9 = 19;
                                 break label334;
                              }
                              break;
                           case -813459003:
                              if (var10.equals("_143_setting_19")) {
                                 var9 = 20;
                                 break label334;
                              }
                              break;
                           case -813458981:
                              if (var10.equals("_143_setting_20")) {
                                 var9 = 21;
                                 break label334;
                              }
                              break;
                           case -813458980:
                              if (var10.equals("_143_setting_21")) {
                                 var9 = 22;
                                 break label334;
                              }
                              break;
                           case -813458979:
                              if (var10.equals("_143_setting_22")) {
                                 var9 = 23;
                                 break label334;
                              }
                              break;
                           case -813458978:
                              if (var10.equals("_143_setting_23")) {
                                 var9 = 24;
                                 break label334;
                              }
                              break;
                           case 1081491908:
                              if (var10.equals("vm_golf7_vehicle_setup_units_consumption")) {
                                 var9 = 25;
                                 break label334;
                              }
                              break;
                           case 1285394218:
                              if (var10.equals("_143_0xAD_setting4")) {
                                 var9 = 26;
                                 break label334;
                              }
                              break;
                           case 1285394219:
                              if (var10.equals("_143_0xAD_setting5")) {
                                 var9 = 27;
                                 break label334;
                              }
                              break;
                           case 1285394220:
                              if (var10.equals("_143_0xAD_setting6")) {
                                 var9 = 28;
                                 break label334;
                              }
                              break;
                           case 1285394221:
                              if (var10.equals("_143_0xAD_setting7")) {
                                 var9 = 29;
                                 break label334;
                              }
                        }

                        var9 = -1;
                     }

                     UiMgr var11;
                     byte[] var12;
                     label290:
                     switch (var9) {
                        case 0:
                           var12 = new byte[]{22, -54, 3, (byte)(2 - var3)};
                           CanbusMsgSender.sendMsg(var12);
                           break;
                        case 1:
                           if (var3 < 16) {
                              var12 = new byte[]{22, -102, 1, (byte)(var3 + 1)};
                              CanbusMsgSender.sendMsg(var12);
                           } else {
                              switch (var3) {
                                 case 16:
                                    var12 = new byte[]{22, -102, 1, 18};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 17:
                                    var12 = new byte[]{22, -102, 1, 20};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 18:
                                    var12 = new byte[]{22, -102, 1, 22};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 19:
                                    var12 = new byte[]{22, -102, 1, 23};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 20:
                                    var12 = new byte[]{22, -102, 1, 26};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 21:
                                    var12 = new byte[]{22, -102, 1, 25};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 22:
                                    var12 = new byte[]{22, -102, 1, 28};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 23:
                                    var12 = new byte[]{22, -102, 1, 29};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 24:
                                    var12 = new byte[]{22, -102, 1, 30};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 25:
                                    var12 = new byte[]{22, -102, 1, 31};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 26:
                                    var12 = new byte[]{22, -102, 1, 32};
                                    CanbusMsgSender.sendMsg(var12);
                                    break label290;
                                 case 27:
                                    var12 = new byte[]{22, -102, 1, 33};
                                    CanbusMsgSender.sendMsg(var12);
                              }
                           }
                           break;
                        case 2:
                           this.this$0.send0x7DDataCommand(1, var3);
                           break;
                        case 3:
                           this.this$0.send0x7DDataCommand(2, var3);
                           break;
                        case 4:
                           this.this$0.send0x7DDataCommand(3, var3);
                           break;
                        case 5:
                           this.this$0.send0x7DDataCommand(4, var3);
                           break;
                        case 6:
                           this.this$0.send0x7DDataCommand(5, var3);
                           break;
                        case 7:
                           this.this$0.send0x7DDataCommand(6, var3);
                           break;
                        case 8:
                           this.this$0.send0x7DDataCommand(7, var3);
                           break;
                        case 9:
                           this.this$0.send0x7DDataCommand(8, var3);
                           break;
                        case 10:
                           this.this$0.send0x7DDataCommand(9, var3);
                           break;
                        case 11:
                           var12 = new byte[]{22, -116, (byte)var3, -1};
                           CanbusMsgSender.sendMsg(var12);
                           break;
                        case 12:
                           this.this$0.send0x7DDataCommand(10, var3);
                           break;
                        case 13:
                           this.this$0.send0x7DDataCommand(11, var3);
                           break;
                        case 14:
                           this.this$0.send0x7DDataCommand(12, var3);
                           break;
                        case 15:
                           this.this$0.send0x7DDataCommand(13, var3);
                           break;
                        case 16:
                           this.this$0.send0x7DDataCommand(14, var3);
                           break;
                        case 17:
                           this.this$0.send0x7DDataCommand(15, var3);
                           break;
                        case 18:
                           this.this$0.send0x7DDataCommand(17, var3);
                           break;
                        case 19:
                           this.this$0.send0x7DDataCommand(18, var3);
                           break;
                        case 20:
                           this.this$0.send0x7DDataCommand(19, var3);
                           break;
                        case 21:
                           this.this$0.send0x7DDataCommand(20, var3);
                           break;
                        case 22:
                           this.this$0.send0x7DDataCommand(21, var3);
                           break;
                        case 23:
                           this.this$0.send0x7DDataCommand(22, var3);
                           break;
                        case 24:
                           this.this$0.send0x7DDataCommand(23, var3);
                           break;
                        case 25:
                           var12 = new byte[]{22, -54, 5, (byte)(var3 + 1)};
                           CanbusMsgSender.sendMsg(var12);
                           break;
                        case 26:
                           var12 = new byte[]{22, -83, 11, (byte)var3};
                           CanbusMsgSender.sendMsg(var12);
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_VOL_DISTRIBUTION, var3);
                           break;
                        case 27:
                           var12 = new byte[]{22, -83, 12, (byte)var3};
                           CanbusMsgSender.sendMsg(var12);
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_VOL_LOUD, var3);
                           break;
                        case 28:
                           var12 = new byte[]{22, -83, 13, (byte)var3};
                           CanbusMsgSender.sendMsg(var12);
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_VOL_SET, var3);
                           break;
                        case 29:
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 14, (byte)var3});
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.KEY_VOL_SELECT, var3);
                     }

                     var10 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
                     String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var10.hashCode();
                     if (!var10.equals("_143_0x82_setting_1")) {
                        if (var10.equals("_143_speed_setting1")) {
                           var8.hashCode();
                           switch (var8.hashCode()) {
                              case -1517720165:
                                 if (!var8.equals("_143_speed_0x81_setting3")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 0;
                                 }
                                 break;
                              case -1517720164:
                                 if (!var8.equals("_143_speed_0x81_setting4")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 1;
                                 }
                                 break;
                              case -1517720163:
                                 if (!var8.equals("_143_speed_0x81_setting5")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 2;
                                 }
                                 break;
                              case -1517720162:
                                 if (!var8.equals("_143_speed_0x81_setting6")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 3;
                                 }
                                 break;
                              case -1517720161:
                                 if (!var8.equals("_143_speed_0x81_setting7")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 4;
                                 }
                                 break;
                              case -1517720160:
                                 if (!var8.equals("_143_speed_0x81_setting8")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 5;
                                 }
                                 break;
                              case -67070680:
                                 if (!var8.equals("_143_speed_setting2")) {
                                    var9 = var5;
                                 } else {
                                    var9 = 6;
                                 }
                                 break;
                              default:
                                 var9 = var5;
                           }

                           switch (var9) {
                              case 0:
                                 this.this$0.D0B6 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 1:
                                 this.this$0.D0B5 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 2:
                                 this.this$0.D0B4 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 3:
                                 this.this$0.D0B3 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 4:
                                 this.this$0.D0B2 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 5:
                                 this.this$0.D0B1 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 6:
                                 this.this$0.D0B7 = var3;
                                 this.this$0.send0x8AData16Command();
                           }
                        }
                     } else {
                        var8.hashCode();
                        switch (var8.hashCode()) {
                           case -1983841901:
                              if (!var8.equals("_143_0x82_setting_1")) {
                                 var9 = var5;
                              } else {
                                 var9 = 0;
                              }
                              break;
                           case -1714233670:
                              if (!var8.equals("_143_speed_0x82_setting3")) {
                                 var9 = var5;
                              } else {
                                 var9 = 1;
                              }
                              break;
                           case -1714233669:
                              if (!var8.equals("_143_speed_0x82_setting4")) {
                                 var9 = var5;
                              } else {
                                 var9 = 2;
                              }
                              break;
                           case -1714233668:
                              if (!var8.equals("_143_speed_0x82_setting5")) {
                                 var9 = var5;
                              } else {
                                 var9 = 3;
                              }
                              break;
                           case -1714233667:
                              if (!var8.equals("_143_speed_0x82_setting6")) {
                                 var9 = var5;
                              } else {
                                 var9 = 4;
                              }
                              break;
                           case -1714233666:
                              if (!var8.equals("_143_speed_0x82_setting7")) {
                                 var9 = var5;
                              } else {
                                 var9 = 5;
                              }
                              break;
                           case -1714233665:
                              if (!var8.equals("_143_speed_0x82_setting8")) {
                                 var9 = var5;
                              } else {
                                 var9 = 6;
                              }
                              break;
                           default:
                              var9 = var5;
                        }

                        switch (var9) {
                           case 0:
                              this.this$0.D0B7_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 1:
                              this.this$0.D0B6_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 2:
                              this.this$0.D0B5_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 3:
                              this.this$0.D0B4_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 4:
                              this.this$0.D0B3_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 5:
                              this.this$0.D0B2_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 6:
                              this.this$0.D0B1_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                        }
                     }

                     var11 = this.this$0;
                     if (var1 == var11.getSettingLeftIndexes(var11.mContext, "_143_car_setting_2")) {
                        var11 = this.this$0;
                        if (var2 == var11.getSettingRightIndex(var11.mContext, "_143_car_setting_2", "_143_setting_3")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 125, 3, (byte)var3});
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(var1, var2, var3);
                        }

                        var11 = this.this$0;
                        if (var2 == var11.getSettingRightIndex(var11.mContext, "_143_car_setting_2", "_143_setting_17")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 125, 17, (byte)var3});
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(var1, var2, var3);
                        }

                        var11 = this.this$0;
                        if (var2 == var11.getSettingRightIndex(var11.mContext, "_143_car_setting_2", "_143_setting_18")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 125, 18, (byte)var3});
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(var1, var2, var3);
                        }

                        var11 = this.this$0;
                        if (var2 == var11.getSettingRightIndex(var11.mContext, "_143_car_setting_2", "_143_setting_15")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 125, 15, (byte)var3});
                           var11 = this.this$0;
                           var11.getMsgMgr(var11.mContext).updateSettings(var1, var2, var3);
                        }
                     }

                     var11 = this.this$0;
                     if (var1 == var11.getSettingLeftIndexes(var11.mContext, "_143_add_function")) {
                        var11 = this.this$0;
                        if (var2 == var11.getSettingRightIndex(var11.mContext, "_143_add_function", "_143_add_function1")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 123, 14, (byte)var3});
                        }

                        var11 = this.this$0;
                        if (var2 == var11.getSettingRightIndex(var11.mContext, "_143_add_function", "_143_add_function2")) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 123, 15, (byte)var3});
                        }
                     }

                  }
               };
               var26.setOnSettingItemSelectListener(var31);
               OnSettingItemSeekbarSelectListener var32 = new OnSettingItemSeekbarSelectListener(this, var1, var26) {
                  final UiMgr this$0;
                  final Context val$context;
                  final SettingPageUiSet val$settingPageUiSet;

                  {
                     this.this$0 = var1;
                     this.val$context = var2;
                     this.val$settingPageUiSet = var3;
                  }

                  public void onClickItem(int var1, int var2, int var3) {
                     byte var5 = 4;
                     if (var1 == 0 && var2 == 4) {
                        boolean var6;
                        if (System.getInt(this.val$context.getContentResolver(), "left0right3", 0) == 1) {
                           var6 = true;
                        } else {
                           var6 = false;
                        }

                        CanbusMsgSender.sendMsg(new byte[]{22, 123, 10, (byte)DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, var6), var3, 0, 7)});
                     }

                     byte var4;
                     String var7;
                     label142: {
                        var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                        var7.hashCode();
                        switch (var7) {
                           case "_143_setting_16":
                              var4 = 0;
                              break label142;
                           case "_143_0xAD_setting2":
                              var4 = 1;
                              break label142;
                           case "_143_0xAD_setting3":
                              var4 = 2;
                              break label142;
                           case "_143_0xAD_setting8":
                              var4 = 3;
                              break label142;
                           case "_143_0xAD_setting9":
                              var4 = 4;
                              break label142;
                        }

                        var4 = -1;
                     }

                     switch (var4) {
                        case 0:
                           CanbusMsgSender.sendMsg(new byte[]{22, 125, 16, (byte)var3});
                           break;
                        case 1:
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)var3});
                           break;
                        case 2:
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)var3});
                           break;
                        case 3:
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 15, (byte)var3});
                           break;
                        case 4:
                           CanbusMsgSender.sendMsg(new byte[]{22, -83, 16, (byte)var3});
                     }

                     String var8 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
                     var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var8.hashCode();
                     byte var9;
                     if (!var8.equals("_143_0x82_setting_1")) {
                        if (var8.equals("_143_speed_setting1")) {
                           label115: {
                              var7.hashCode();
                              switch (var7) {
                                 case "_143_speed_0x81_setting9":
                                    var9 = 0;
                                    break label115;
                                 case "_143_speed_0x81_setting10":
                                    var9 = 1;
                                    break label115;
                                 case "_143_speed_0x81_setting11":
                                    var9 = 2;
                                    break label115;
                                 case "_143_speed_0x81_setting12":
                                    var9 = 3;
                                    break label115;
                                 case "_143_speed_0x81_setting13":
                                    var9 = var5;
                                 case "_143_speed_0x81_setting14":
                                    var9 = 5;
                                    break label115;
                              }

                              var9 = -1;
                           }

                           switch (var9) {
                              case 0:
                                 this.this$0.Data1 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 1:
                                 this.this$0.Data2 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 2:
                                 this.this$0.Data3 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 3:
                                 this.this$0.Data4 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 4:
                                 this.this$0.Data5 = var3;
                                 this.this$0.send0x8AData16Command();
                                 break;
                              case 5:
                                 this.this$0.Data6 = var3;
                                 this.this$0.send0x8AData16Command();
                           }
                        }
                     } else {
                        label128: {
                           var7.hashCode();
                           switch (var7) {
                              case "_143_speed_0x82_setting9":
                                 var9 = 0;
                                 break label128;
                              case "_143_speed_0x82_setting10":
                                 var9 = 1;
                                 break label128;
                              case "_143_speed_0x82_setting11":
                                 var9 = 2;
                                 break label128;
                              case "_143_speed_0x82_setting12":
                                 var9 = 3;
                                 break label128;
                              case "_143_speed_0x82_setting13":
                                 var9 = var5;
                              case "_143_speed_0x82_setting14":
                                 var9 = 5;
                                 break label128;
                           }

                           var9 = -1;
                        }

                        switch (var9) {
                           case 0:
                              this.this$0.Data1_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 1:
                              this.this$0.Data2_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 2:
                              this.this$0.Data3_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 3:
                              this.this$0.Data4_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 4:
                              this.this$0.Data5_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                              break;
                           case 5:
                              this.this$0.Data6_0x8B = var3;
                              this.this$0.send0x8BData16Command();
                        }
                     }

                  }
               };
               var26.setOnSettingItemSeekbarSelectListener(var32);
               var27 = this.getAirUiSet(var1);
               var27.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
               var27.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
               var27.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
               var27.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
               if (this.mDifferentId == 27) {
                  var27.getFrontArea().setCanSetWindSpeed(false);
                  var27.getFrontArea().setCanSetRightTemp(false);
                  var27.getFrontArea().setCanSetLeftTemp(false);
                  var27.getFrontArea().setDisableBtnArray(new String[]{"ac_max", "auto_wind_lv", "auto", "power", "in_out_cycle", "aqs", "front_defog", "rear_defog"});
                  break label127;
               }
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break label235;
            }

            try {
               var27.getFrontArea().setCanSetWindSpeed(true);
               var27.getFrontArea().setCanSetRightTemp(true);
               var27.getFrontArea().setCanSetLeftTemp(true);
               var27.getFrontArea().setDisableBtnArray(new String[0]);
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break label235;
            }
         }

         try {
            PanelKeyPageUiSet var24 = this.getPanelKeyPageUiSet(var1);
            OnPanelKeyPositionTouchListener var29 = new OnPanelKeyPositionTouchListener(this, var24) {
               final UiMgr this$0;
               final PanelKeyPageUiSet val$panelKeyPageUiSet;

               {
                  this.this$0 = var1;
                  this.val$panelKeyPageUiSet = var2;
               }

               public void onTouch(int var1, MotionEvent var2) {
                  String var4 = (String)((List)Objects.requireNonNull(this.val$panelKeyPageUiSet.getList())).get(var1);
                  var4.hashCode();
                  int var3 = var4.hashCode();
                  byte var5 = -1;
                  switch (var3) {
                     case -996207145:
                        if (var4.equals("panel_btn_esc")) {
                           var5 = 0;
                        }
                        break;
                     case -817696940:
                        if (var4.equals("panel_btn_dark")) {
                           var5 = 1;
                        }
                        break;
                     case -817683328:
                        if (var4.equals("panel_btn_down")) {
                           var5 = 2;
                        }
                        break;
                     case -817455131:
                        if (var4.equals("panel_btn_left")) {
                           var5 = 3;
                        }
                        break;
                     case -817425091:
                        if (var4.equals("panel_btn_menu")) {
                           var5 = 4;
                        }
                        break;
                     case -817415807:
                        if (var4.equals("panel_btn_mode")) {
                           var5 = 5;
                        }
                        break;
                     case 434355710:
                        if (var4.equals("panel_btn_right")) {
                           var5 = 6;
                        }
                        break;
                     case 1630432570:
                        if (var4.equals("panel_btn_ok")) {
                           var5 = 7;
                        }
                        break;
                     case 1630432761:
                        if (var4.equals("panel_btn_up")) {
                           var5 = 8;
                        }
                  }

                  switch (var5) {
                     case 0:
                        var5 = 37;
                        break;
                     case 1:
                        var5 = 39;
                        break;
                     case 2:
                        var5 = 24;
                        break;
                     case 3:
                        var5 = 25;
                        break;
                     case 4:
                        var5 = 22;
                        break;
                     case 5:
                        var5 = 38;
                        break;
                     case 6:
                        var5 = 26;
                        break;
                     case 7:
                        var5 = 36;
                        break;
                     case 8:
                        var5 = 23;
                        break;
                     default:
                        var5 = 0;
                  }

                  if (var2.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 33, (byte)var5, 1});
                  }

                  if (var2.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 33, (byte)var5, 0});
                  }

               }
            };
            var24.setOnPanelKeyPositionTouchListener(var29);
            return;
         } catch (Exception var5) {
            var10000 = var5;
            var10001 = false;
         }
      }

      Exception var23 = var10000;
      var23.printStackTrace();
      Log.d("cwh", "错误类型：" + var23);
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

   private void initAmpContent(Context var1) {
      MsgMgr var4 = this.getMsgMgr(var1);
      int var2 = this.getSettingLeftIndexes(var1, "_143_0xAD_setting1");
      int var3 = this.getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting4");
      String var5 = this.KEY_VOL_DISTRIBUTION;
      var4.updateSettings(var1, var2, var3, var5, SharePreUtil.getIntValue(this.mContext, var5, 0));
      CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, (byte)SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_DISTRIBUTION, 0)});
      var4 = this.getMsgMgr(var1);
      var3 = this.getSettingLeftIndexes(var1, "_143_0xAD_setting1");
      var2 = this.getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting5");
      var5 = this.KEY_VOL_LOUD;
      var4.updateSettings(var1, var3, var2, var5, SharePreUtil.getIntValue(this.mContext, var5, 0));
      MsgMgr var7 = this.getMsgMgr(var1);
      var3 = this.getSettingLeftIndexes(var1, "_143_0xAD_setting1");
      var2 = this.getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting6");
      String var6 = this.KEY_VOL_SET;
      var7.updateSettings(var1, var3, var2, var6, SharePreUtil.getIntValue(this.mContext, var6, 0));
      var4 = this.getMsgMgr(var1);
      var2 = this.getSettingLeftIndexes(var1, "_143_0xAD_setting1");
      var3 = this.getSettingRightIndex(this.mContext, "_143_0xAD_setting1", "_143_0xAD_setting7");
      var5 = this.KEY_VOL_SELECT;
      var4.updateSettings(var1, var2, var3, var5, SharePreUtil.getIntValue(this.mContext, var5, 0));
      CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, (byte)SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_DISTRIBUTION, 0)});
      CanbusMsgSender.sendMsg(new byte[]{22, -83, 12, (byte)SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_LOUD, 0)});
      CanbusMsgSender.sendMsg(new byte[]{22, -83, 13, (byte)SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_SET, 0)});
      CanbusMsgSender.sendMsg(new byte[]{22, -83, 14, (byte)SharePreUtil.getIntValue(this.mContext, this.KEY_VOL_SELECT, 0)});
   }

   private void send0x7DDataCommand(int var1, int var2) {
      byte[] var3 = this.getMsgMgr(this.mContext).get0x72Enable();
      var3[0] = 22;
      var3[1] = 125;
      var3[2] = (byte)var1;
      var3[3] = (byte)var2;
      CanbusMsgSender.sendMsg(var3);
   }

   private void send0x8AData16Command() {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)this.getDecimalFrom8Bit(this.D0B7, this.D0B6, this.D0B5, this.D0B4, this.D0B3, this.D0B2, this.D0B1, 0), (byte)this.Data1, (byte)this.Data2, (byte)this.Data3, (byte)this.Data4, (byte)this.Data5, (byte)this.Data6, 0, 0, 0});
   }

   private void send0x8BData16Command() {
      CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)this.getDecimalFrom8Bit(this.D0B7_0x8B, this.D0B6_0x8B, this.D0B5_0x8B, this.D0B4_0x8B, this.D0B3_0x8B, this.D0B2_0x8B, this.D0B1_0x8B, 0), (byte)this.Data1_0x8B, (byte)this.Data2_0x8B, (byte)this.Data3_0x8B, (byte)this.Data4_0x8B, (byte)this.Data5_0x8B, (byte)this.Data6_0x8B, 0, 0, 0});
   }

   private void sendAirBtnCtrl(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, this.setWidgetData(var2)});
   }

   private void sendAirClick(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 59, (byte)var1, 1});
   }

   private static int setIntByteWithBit(int var0, int var1, int var2) {
      return var0 ^ (var2 ^ var0 >> var1 & 1) << var1;
   }

   private byte setWidgetData(boolean var1) {
      return (byte)(var1 ^ 1);
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var5.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var5.get(var4)).getTitleSrn().equals(var2)) {
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

   public void send0xA1HostState() {
      CanbusMsgSender.sendMsg(new byte[]{22, -95, (byte)this.getDecimalFrom8Bit(this.D0B7_0xA1, this.D0B6_0xA1, this.D0B5_0xA1, 0, 0, 0, 0, 0), (byte)this.Data1_0xA1, (byte)this.Data2_0xA1});
      this.D0B6_0xA1 = 0;
   }

   public void send0xA2Info(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte)var1, (byte)var2, (byte)var3, (byte)var4, 0, 0});
   }

   public void send0xA3Info(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte)var1, (byte)this.data1_0xA3, (byte)this.data2_0xA3, (byte)this.data3_0xA3, (byte)this.data4_0xA3, (byte)this.data5_0xA3, (byte)this.data6_0xA3, (byte)this.data7_0xA3, (byte)this.data8_0xA3, (byte)this.data9_0xA3, (byte)this.data10_0xA3, (byte)this.data11_0xA3, (byte)this.data12_0xA3});
   }
}
