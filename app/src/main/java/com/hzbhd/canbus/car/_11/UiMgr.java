package com.hzbhd.canbus.car._11;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
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
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_11_LANGUAGE = "share_11_language";
   private int mBtnHeight;
   private int mBtnThreeEnd;
   private int mBtnWidth;
   private Context mContext;
   private int mDifferent;
   private final Handler mHandler = new Handler(Looper.getMainLooper());
   private int mHistoricalLeft = 7;
   private int mMaxHight;
   private int mMaxWidth;
   private int mMinuteFuelLeft = 6;
   private MsgMgr mMsgMgr;
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerColdFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(12);
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
         this.this$0.sendAirCommand(14);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (this.this$0.mDifferent != 2) {
            this.this$0.sendAirCommand(12);
         }

      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(11);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         if (this.this$0.mDifferent != 2) {
            this.this$0.sendAirCommand(14);
         }

      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(2);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(3);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(4);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(5);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(38);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(39);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.getHybirdPageUiSet(var1).setOnHybirdPageStatusListener(new OnHybirdPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 31, 0});
         }
      });
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda1(this, var2), new UiMgr$$ExternalSyntheticLambda2(this, var2), new UiMgr$$ExternalSyntheticLambda3(this, var2), new UiMgr$$ExternalSyntheticLambda4(this, var2)});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, this.mOnMinPlusClickListenerColdFrontLeft, this.mOnMinPlusClickListenerColdFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(9);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(10);
         }
      });
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
         }

         public void onRightSeatClick() {
         }
      });
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new UiMgr$$ExternalSyntheticLambda5(this, var2)});
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
      if (this.mDifferent == 2) {
         var2.getFrontArea().setShowSeatCold(true);
      }

      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 != 4) {
                           if (var1 == 5) {
                              if (var2 != 0) {
                                 if (var2 != 1) {
                                    if (var2 == 2) {
                                       CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var3});
                                    }
                                 } else {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
                                 }
                              } else if (var3 == 0) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 1});
                              } else if (var3 == 1) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 8});
                              }
                           }
                        } else if (var2 != 1) {
                           if (var2 != 2) {
                              if (var2 == 3) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)var3});
                              }
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
                        }
                     } else if (var2 != 0) {
                        if (var2 != 1) {
                           if (var2 != 2) {
                              if (var2 != 3) {
                                 if (var2 != 4) {
                                    if (var2 == 5) {
                                       int var4;
                                       if (this.this$0.mDifferent == 1) {
                                          var4 = 0;
                                       } else {
                                          var4 = 2;
                                       }

                                       var4 += var3;
                                       CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var4});
                                       SharePreUtil.setIntValue(this.val$context, "share_11_language", var4);

                                       try {
                                          if (this.this$0.mMsgMgr != null && this.this$0.mDifferent == 1) {
                                             this.this$0.mMsgMgr.updateSetting(var1, var2, var3);
                                          }
                                       } catch (Exception var6) {
                                          var6.printStackTrace();
                                       }
                                    }
                                 } else {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -125, 43, (byte)var3});
                                 }
                              } else {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte)var3});
                              }
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
                     }
                  } else if (var2 != 0) {
                     if (var2 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                  }
               } else {
                  switch (var2) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
                        break;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
                        break;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
                        break;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
                     case 4:
                     default:
                        break;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                        break;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
                        break;
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
                        break;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var3});
                        break;
                     case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                        break;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var3});
                        break;
                     case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
                        break;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)var3});
                        break;
                     case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var3});
                        break;
                     case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var3});
                  }
               }
            } else if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 != 2) {
                     if (var2 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == this.this$0.mMinuteFuelLeft) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, 0});
               }
            } else if (var1 == this.this$0.mHistoricalLeft) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, 0});
               } else if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, 0});
               }
            }

         }
      });
      var3.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_326_radar_info_1")) {
               this.this$0.mMsgMgr.updateSettings(this.this$0.mContext, "FRONT_RADAR_KEY", var1, var2, var3);
            }

         }
      });
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 != 1) {
               if (var1 == 4 && var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
               }
            } else if (var2 == 4) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
            }

         }
      });
      if (this.mDifferent == 1) {
         ArrayList var4 = new ArrayList();
         var4.add("language_status_0");
         var4.add("language_status_1");
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var3.getList().get(3)).getItemList().get(5)).setValueSrnArray(var4);
         ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var3.getList().get(3)).getItemList().get(5)).setValue("null_value");
      }

      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            try {
               if (this.this$0.mMsgMgr != null) {
                  this.this$0.mMsgMgr.updateSettingData(var1);
               }
            } catch (Exception var3) {
               var3.printStackTrace();
            }

         }
      });
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
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
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
            }

         }
      });
      var5.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 7)});
               }
            } else {
               Log.i("ljq", "position: value = " + var2);
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(var2 + 7)});
            }

         }
      });
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               (new Thread(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void run() {
                     super.run();

                     try {
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
                        sleep(100L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
                        sleep(100L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 35, 0});
                        sleep(100L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 39, 0});
                     } catch (Exception var2) {
                        var2.printStackTrace();
                     }

                  }
               }).start();
            }

         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      Log.i("_11_", "UiMgr: different: " + this.mDifferent);
      Log.i("_11_", "UiMgr: dp495" + CommUtil.getDimenByResId(var1, "dp495"));
      Log.i("_11_", "UiMgr: dp190" + CommUtil.getDimenByResId(var1, "dp190"));
      var6.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onTouchItem(MotionEvent var1) {
            boolean var4 = CommUtil.isBackCamera(this.val$context);
            boolean var5 = CommUtil.isPanoramic(this.val$context);
            Log.i("_11_", "onTouchItem: isBackCamera: " + var4 + ", isParkPanoramic: " + var5);
            if (var1.getAction() == 0) {
               int var2 = (int)var1.getX();
               int var3 = (int)var1.getY();
               Log.i("_11_", "onTouchItem: x: " + var2 + ", y: " + var3);
               if (var3 <= CommUtil.getDimenByResId(this.val$context, "dp592") && var3 >= CommUtil.getDimenByResId(this.val$context, "dp495")) {
                  if (var4) {
                     Log.i("_11_", "onTouchItem: back");
                     if (var2 <= CommUtil.getDimenByResId(this.val$context, "dp190") && var2 >= CommUtil.getDimenByResId(this.val$context, "dp30")) {
                        Log.i("_11_", "onTouchItem: back_1");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 4});
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 42, 1});
                     } else if (var2 <= CommUtil.getDimenByResId(this.val$context, "dp708") && var2 >= CommUtil.getDimenByResId(this.val$context, "dp539")) {
                        Log.i("_11_", "onTouchItem: back_2");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 7});
                     } else if (var2 <= CommUtil.getDimenByResId(this.val$context, "dp1009") && var2 >= CommUtil.getDimenByResId(this.val$context, "dp850")) {
                        Log.i("_11_", "onTouchItem: back_3");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 3});
                     }
                  } else if (var5) {
                     Log.i("_11_", "onTouchItem: panoramic");
                     if (var2 <= CommUtil.getDimenByResId(this.val$context, "dp190") && var2 >= CommUtil.getDimenByResId(this.val$context, "dp30")) {
                        Log.i("_11_", "onTouchItem: panoramic_1");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 2});
                     } else if (var2 <= CommUtil.getDimenByResId(this.val$context, "dp708") && var2 >= CommUtil.getDimenByResId(this.val$context, "dp539")) {
                        Log.i("_11_", "onTouchItem: panoramic_2");
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, 1});
                     }
                  }
               }
            }

         }
      });
   }

   private void initPanoramicBtnPara(Context var1) {
      this.mMaxWidth = CommUtil.getDimenByResId(var1, "dp1024");
      this.mMaxHight = CommUtil.getDimenByResId(var1, "dp600");
      this.mBtnWidth = CommUtil.getDimenByResId(var1, "dp140");
      this.mBtnHeight = CommUtil.getDimenByResId(var1, "dp100");
      this.mBtnThreeEnd = this.mMaxWidth * 3 / 4 - 20;
   }

   private boolean isPanoramicUseBtn() {
      boolean var1;
      if (this.mDifferent == 6) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isPanoramicUseCoordinate() {
      int var1 = this.mDifferent;
      boolean var2;
      if (var1 != 4 && var1 != 7) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   // $FF: synthetic method
   static void lambda$sendAirCommand$5(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var0, 0});
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, 1});
      this.mHandler.postDelayed(new UiMgr$$ExternalSyntheticLambda0(var1), 100L);
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 1;
            }
            break;
         case -620266838:
            if (var1.equals("rear_power")) {
               var2 = 2;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 3;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 4;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 5;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 6;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 7;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 8;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 9;
            }
            break;
         case 1006620906:
            if (var1.equals("auto_wind_mode")) {
               var2 = 10;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(19);
            break;
         case 1:
            this.sendAirCommand(20);
            break;
         case 2:
            this.sendAirCommand(42);
            break;
         case 3:
            this.sendAirCommand(8);
            break;
         case 4:
            this.sendAirCommand(7);
            break;
         case 5:
            this.sendAirCommand(23);
            break;
         case 6:
            this.sendAirCommand(21);
            break;
         case 7:
            this.sendAirCommand(16);
            break;
         case 8:
            this.sendAirCommand(1);
            break;
         case 9:
            this.sendAirCommand(25);
            break;
         case 10:
            this.sendAirCommand(32);
      }

   }

   private void sendPanoramicCommand(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -6, -1, (byte)this.val$cmd, 0});
         }
      }).start();
      this.playBeep();
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
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

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__11_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__11_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__11_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__11_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__11_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getRearArea().getLineBtnAction()[3][var2]);
   }

   public void removeAmplifierModel() {
      this.removeMainPrjBtnByName(this.mContext, "amplifier");
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (!SharePreUtil.getBoolValue(this.mContext, "share_11_is_suppot_tire", true)) {
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

      if (!SharePreUtil.getBoolValue(this.mContext, "share_11_is_suppot_hybrid", true)) {
         this.removeMainPrjBtnByName(var1, "hybird");
      }

   }
}
