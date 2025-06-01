package com.hzbhd.canbus.car._103;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 33, 1, -1});
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -112, 23, 0});
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     switch (var2) {
                        case 1:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
                           break;
                        case 2:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                           break;
                        case 3:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte)var3});
                           break;
                        case 4:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, (byte)var3});
                           break;
                        case 5:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                           break;
                        case 6:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                           break;
                        case 7:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte)var3});
                           break;
                        case 8:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                           break;
                        case 9:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                           break;
                        case 10:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                           break;
                        case 11:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                           break;
                        case 12:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
                           break;
                        case 13:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
                           break;
                        case 14:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var3});
                           break;
                        case 15:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var3});
                           break;
                        case 16:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var3});
                           break;
                        case 17:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 64, (byte)var3});
                           break;
                        case 18:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var3});
                           break;
                        case 19:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
                           break;
                        case 20:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte)var3});
                           break;
                        case 21:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 81, (byte)var3});
                           break;
                        case 22:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 82, (byte)var3});
                           break;
                        case 23:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 83, (byte)var3});
                           break;
                        case 24:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 84, (byte)var3});
                           break;
                        case 25:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 96, (byte)var3});
                           break;
                        case 26:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 97, (byte)var3});
                     }
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)var3, 0});
               }
            } else {
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var3});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte)var3});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, (byte)var3});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 13, (byte)var3});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 14, (byte)var3});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)var3});
               }
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 != 0) {
               if (var1 == 2 && var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 1});
               }
            } else {
               switch (var2) {
                  case 8:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 16});
                     break;
                  case 9:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 17});
                     break;
                  case 10:
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 18});
               }
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 7) {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)(var3 + 5)});
            }

         }
      });
      this.getAirUiSet(var1).getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontLeft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)(-var2 + 11)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)(var2 + 11)});
            }

         }
      });
      var3.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
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
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)(var2 + 2)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)(var2 + 2)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)(var2 + 2)});
            }

         }
      });
   }
}
