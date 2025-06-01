package com.hzbhd.canbus.car._56;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
   private int mCurrentCarId;
   private int mFrontLeftBlowModel = 9;
   private int mFrontRightBlowModel = 9;
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
         if (var1 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, (byte)(1 ^ GeneralAirData.ac)});
         } else {
            UiMgr var2 = this.this$0;
            var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
         }

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
   OnAirSeatHeatColdMinPlusClickListener mOnMinPlusClickListenerHeatFrontLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendAirCommand(17);
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
         this.this$0.sendAirCommand(18);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(16);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(15);
      }
   };
   private int mOrientation;
   private ParkPageUiSet mParkPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      int var2 = this.getCurrentCarId();
      this.mCurrentCarId = var2;
      if (var2 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 33, 2});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, 2});
      }

      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.mOrientation = var1.getResources().getConfiguration().orientation;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      String[][] var4 = var3.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var4[0];
      this.mAirBtnListFrontLeft = var4[1];
      this.mAirBtnListFrontRight = var4[2];
      this.mAirBtnListFrontBottom = var4[3];
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnMinPlusClickListenerHeatFrontLeft, this.mOnMinPlusClickListenerHeatFrontRight, null, null});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            int var2 = GeneralAirData.front_wind_level - 1;
            int var1 = var2;
            if (var2 < 0) {
               var1 = 0;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte)var1});
         }

         public void onClickRight() {
            int var2 = GeneralAirData.front_wind_level + 1;
            int var1 = var2;
            if (var2 > 7) {
               var1 = 7;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, 25, (byte)var1});
         }
      });
      var3.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            UiMgr var1 = this.this$0;
            var1.sendAirCommand(UiMgr.access$008(var1));
            if (this.this$0.mFrontLeftBlowModel > 10 && this.this$0.mFrontLeftBlowModel < 23) {
               this.this$0.mFrontLeftBlowModel = 23;
            }

            if (this.this$0.mFrontLeftBlowModel > 24) {
               this.this$0.mFrontLeftBlowModel = 9;
            }

         }

         public void onRightSeatClick() {
            UiMgr var1 = this.this$0;
            var1.sendAirCommand(UiMgr.access$208(var1));
            if (this.this$0.mFrontRightBlowModel > 10 && this.this$0.mFrontRightBlowModel < 23) {
               this.this$0.mFrontRightBlowModel = 23;
            }

            if (this.this$0.mFrontRightBlowModel > 24) {
               this.this$0.mFrontRightBlowModel = 9;
            }

         }
      });
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      var5.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var5, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            int var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getId();
            switch (var1) {
               case 2:
                  if (var4 != 0) {
                     if (var4 != 1) {
                        if (var4 != 2) {
                           if (var4 != 3) {
                              if (var4 == 4) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, 108, 5, (byte)var3});
                              }
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, 108, 4, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, 108, 3, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 108, 2, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 108, 1, (byte)(var3 + 1)});
                  }
                  break;
               case 3:
                  if (var4 != 0) {
                     if (var4 != 1) {
                        if (var4 != 2) {
                           if (var4 == 3) {
                              CanbusMsgSender.sendMsg(new byte[]{22, 107, 4, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, 107, 3, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 107, 2, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 107, 1, (byte)var3});
                  }
                  break;
               case 4:
                  if (var4 != 0) {
                     if (var4 != 1) {
                        if (var4 == 2) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 109, 3, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 2, (byte)(var3 + 1)});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte)var3});
                  }
                  break;
               case 5:
                  if (var4 != 0) {
                     if (var4 != 1) {
                        if (var4 != 2) {
                           if (var4 != 3) {
                              if (var4 == 4) {
                                 CanbusMsgSender.sendMsg(new byte[]{22, 110, 7, (byte)var3});
                              }
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, 110, 4, (byte)(var3 + 1)});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, 110, 3, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 2, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 110, 1, (byte)(var3 + 1)});
                  }
                  break;
               case 6:
                  switch (var4) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)(var3 + 1)});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)(var3 + 1)});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte)(var3 + 1)});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var3});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 8, (byte)var3});
                        return;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 7, (byte)var3});
                        return;
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 7:
                  if (var2 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
                  }

                  SharePreUtil.setIntValue(this.val$context, "55_language", var3);

                  try {
                     if (this.this$0.mMsgMgr != null) {
                        this.this$0.mMsgMgr.updateLanguage(var3);
                     }
                  } catch (Exception var6) {
                     var6.printStackTrace();
                  }
            }

         }
      });
      var5.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            var2 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getId();
            if (var1 != 0) {
               if (var1 != 5) {
                  return;
               }
            } else if (var2 != 3) {
               if (var2 == 14) {
                  Toast.makeText(this.this$0.mContext, 2131769831, 1).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
            }

            if (var2 != 7) {
               if (var2 == 8) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 110, 6, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 110, 5, 1});
            }

         }
      });
      var5.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var5) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getId();
            if (var1 == 1) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, 1});
               } else if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, 0});
               }
            }

         }
      });
      var5.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            try {
               this.this$0.mMsgMgr.updateSettingData(var1);
            } catch (Exception var3) {
               var3.printStackTrace();
            }

         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var6;
      var6.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -14, 3, -1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 2, -1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 1, -1});
            }

         }
      });
      this.mParkPageUiSet.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onTouchItem(MotionEvent var1) {
            int var6 = (int)var1.getX();
            int var5 = (int)var1.getY();
            var6 = var6 * 1560 / CommUtil.getDimenByResId(this.val$context, "dp1024");
            var5 = var5 * 1900 / CommUtil.getDimenByResId(this.val$context, "dp600");
            byte var3 = (byte)(var6 & 255);
            byte var2 = (byte)(var6 >> 8 & 255);
            byte var4 = (byte)(var5 & 255);
            CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, var2, var3, (byte)(var5 >> 8 & 255), var4, 0});
         }
      });
   }

   // $FF: synthetic method
   static int access$008(UiMgr var0) {
      int var1 = var0.mFrontLeftBlowModel++;
      return var1;
   }

   // $FF: synthetic method
   static int access$208(UiMgr var0) {
      int var1 = var0.mFrontRightBlowModel++;
      return var1;
   }

   private void sendAirCommand(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 0});
         }
      }).start();
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
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 2;
            }
            break;
         case 3545755:
            if (var1.equals("sync")) {
               var2 = 3;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 4;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 5;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(5);
            break;
         case 1:
            this.sendAirCommand(6);
            break;
         case 2:
            this.sendAirCommand(4);
            break;
         case 3:
            this.sendAirCommand(3);
            break;
         case 4:
            this.sendAirCommand(1);
            break;
         case 5:
            this.sendAirCommand(7);
      }

   }

   public void setShowRadar(boolean var1) {
      this.mParkPageUiSet.setShowRadar(var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
