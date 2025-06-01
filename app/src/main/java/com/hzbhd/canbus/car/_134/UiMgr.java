package com.hzbhd.canbus.car._134;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onBackPressed() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
      }
   };
   private OnOriginalCarDevicePageStatusListener mOnOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 18});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 19});
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 0});
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 2});
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 3});
      }
   };
   private OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         switch (var1) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 7});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 9});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 15, (byte)var3});
               }
            }
         } else {
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 0, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 20, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 22, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 21, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 12, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 13, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, (byte)var3});
            }
         }

      }
   };
   private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 1) {
            if (var2 != 3) {
               if (var2 != 4) {
                  if (var2 == 5) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 19, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -124, 14, (byte)var3});
            }
         }

      }
   };

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 2) {
               Context var3;
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 23, 0});
                  var3 = this.val$context;
                  Toast.makeText(var3, var3.getText(2131769831), 0).show();
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -124, 24, 0});
                  var3 = this.val$context;
                  Toast.makeText(var3, var3.getText(2131769831), 0).show();
               }
            }

         }
      });
      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      var3.setOnOriginalCarDevicePageStatusListener(this.mOnOriginalCarDevicePageStatusListener);
      var3.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
      var3.setOnOriginalCarDeviceBackPressedListener(this.mOnOriginalCarDeviceBackPressedListener);
      var3.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this) {
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
                              CanbusMsgSender.sendMsg(new byte[]{22, -126, 12});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 13});
            }

         }
      });
   }
}
