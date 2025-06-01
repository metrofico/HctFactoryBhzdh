package com.hzbhd.canbus.car._303;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
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

      public void onLeftSeatClick() {
         this.this$0.setFrontSearClick();
      }

      public void onRightSeatClick() {
         this.this$0.setFrontSearClick();
      }
   };
   OnConfirmDialogListener mOnConfirmDialogListener = new UiMgr$$ExternalSyntheticLambda0();
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new UiMgr$$ExternalSyntheticLambda2();
   OnSettingItemSelectListener mOnSettingItemSelectListener = new UiMgr$$ExternalSyntheticLambda1();
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         int var1 = MsgMgr.mLeftTemp;
         if (var1 == 255) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte)(this.this$0.maxTemp - 1)});
         } else if (var1 < this.this$0.minTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, -2});
         } else if (var1 < this.this$0.maxTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte)(var1 - 1)});
         }

      }

      public void onClickUp() {
         int var1 = MsgMgr.mLeftTemp;
         if (var1 == 254) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte)this.this$0.minTemp});
         } else if (var1 == this.this$0.maxTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, -1});
         } else if (var1 >= this.this$0.minTemp && var1 < this.this$0.maxTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 8, (byte)(var1 + 1)});
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         int var1 = MsgMgr.mRightTemp;
         if (var1 == 255) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte)(this.this$0.maxTemp - 1)});
         } else if (var1 < this.this$0.minTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, -2});
         } else if (var1 < this.this$0.maxTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte)(var1 - 1)});
         }

      }

      public void onClickUp() {
         int var1 = MsgMgr.mRightTemp;
         if (var1 == 254) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte)this.this$0.minTemp});
         } else if (var1 == this.this$0.maxTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, -1});
         } else if (var1 >= this.this$0.minTemp && var1 < this.this$0.maxTemp) {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 9, (byte)(var1 + 1)});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.setWindClick(false);
      }

      public void onClickRight() {
         this.this$0.setWindClick(true);
      }
   };
   private int maxTemp = 60;
   private int minTemp = 32;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      String[][] var4 = var3.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var4[0];
      this.mAirBtnListFrontLeft = var4[1];
      this.mAirBtnListFrontRight = var4[2];
      this.mAirBtnListFrontBottom = var4[3];
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
      var3.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
   }

   // $FF: synthetic method
   static void lambda$new$0(int var0, int var1) {
      if (var0 == 0 && var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, 26, 0});
      } else if (var0 == 7 && var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -52, 1, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$1(int var0, int var1, int var2) {
      switch (var0) {
         case 1:
            if (var1 != 0) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 1, (byte)(var2 + 1)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 75, 2, (byte)var2});
            }
            break;
         case 2:
            if (var1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, 77, 1, (byte)var2});
            }
            break;
         case 3:
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 109, 14, (byte)var2});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 109, 13, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 109, 16, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 109, 18, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 109, 19, (byte)var2});
            }
            break;
         case 4:
            switch (var1) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var2});
                  return;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var2});
                  return;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var2});
                  return;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var2});
                  return;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 6, (byte)var2});
                  return;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 7, (byte)var2});
                  return;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 8, (byte)var2});
                  return;
               default:
                  return;
            }
         case 5:
            if (var1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, 122, 5, (byte)var2});
            }
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)(var1 + 1), (byte)(var2 + 1)});
            break;
         case 7:
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -52, 2, (byte)var2});
            }
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var2 + 1)});
      }

   }

   // $FF: synthetic method
   static void lambda$new$2(int var0, int var1, int var2) {
      if (var0 != 1) {
         if (var0 != 3) {
            if (var0 == 5) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 122, 4, (byte)var2});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 3, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 122, 2, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 122, 1, (byte)var2});
               }
            }
         } else if (var1 != 3) {
            if (var1 == 6) {
               CanbusMsgSender.sendMsg(new byte[]{22, 109, 17, (byte)var2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 109, 15, (byte)var2});
         }
      } else if (var1 != 1) {
         if (var1 == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -51, 2, (byte)var2});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 75, 3, (byte)var2});
      }

   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -825767279:
            if (var1.equals("auto_wind_lv")) {
               var2 = 0;
            }
            break;
         case -54617514:
            if (var1.equals("auto_cycle")) {
               var2 = 1;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 2;
            }
      }

      switch (var2) {
         case 0:
            if (GeneralAirData.auto_wind_lv == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
            } else if (GeneralAirData.auto_wind_lv == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 2});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
            }
            break;
         case 1:
            if (GeneralAirData.auto_cycle) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 1, 1});
            }
            break;
         case 2:
            if (GeneralAirData.power) {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 6, 1});
            }
      }

   }

   private void sendAirMsg(int var1, boolean var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, (byte)var2});
   }

   private void setFrontSearClick() {
      if (!GeneralAirData.front_left_blow_foot && !GeneralAirData.front_left_blow_head && !GeneralAirData.front_left_blow_window) {
         this.setWindMode(true, false, true);
      } else if (GeneralAirData.front_left_blow_foot && GeneralAirData.front_left_blow_head && GeneralAirData.front_left_blow_window) {
         this.setWindMode(false, false, false);
      } else if (GeneralAirData.front_left_blow_foot && GeneralAirData.front_left_blow_window) {
         this.setWindMode(true, false, false);
      } else if (GeneralAirData.front_left_blow_window && GeneralAirData.front_left_blow_head) {
         this.setWindMode(false, true, false);
      } else if (GeneralAirData.front_left_blow_foot && GeneralAirData.front_left_blow_head) {
         this.setWindMode(false, false, true);
      } else if (GeneralAirData.front_left_blow_window) {
         this.setWindMode(true, true, false);
      } else if (GeneralAirData.front_left_blow_head) {
         this.setWindMode(false, true, true);
      } else {
         this.setWindMode(true, true, true);
      }

   }

   private void setWindClick(boolean var1) {
      int var2 = GeneralAirData.front_wind_level;
      if (var1) {
         ++var2;
      } else {
         --var2;
      }

      if (var2 >= 0 && var2 <= 7) {
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 7, (byte)var2});
      }

   }

   private void setWindMode(boolean var1, boolean var2, boolean var3) {
      this.sendAirMsg(3, var1);

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var6) {
         var6.printStackTrace();
      }

      this.sendAirMsg(4, var2);

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var5) {
         var5.printStackTrace();
      }

      this.sendAirMsg(5, var3);
   }
}
