package com.hzbhd.canbus.car._59;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private OnConfirmDialogListener mConfirm = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         if (var1 == 0) {
            switch (var2) {
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, 110, 3, 0});
                  Toast.makeText(this.this$0.mContext, 2131769827, 0).show();
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, 27, 1, 1, 1, -1});
                  Toast.makeText(this.this$0.mContext, 2131766883, 0).show();
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, 27, 2, 1, 1, -1});
                  Toast.makeText(this.this$0.mContext, 2131766883, 0).show();
            }
         } else if (var1 == 1 && var2 == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
         }

      }
   };
   private Context mContext;
   private OnSettingItemClickListener mItemClick = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         if (var1 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 75, 4, (byte)(1 - var2)});
         }

      }
   };
   private OnSettingItemSelectListener mItemSelect = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 == 1 && var2 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -14, 16, (byte)var3});
            }
         } else if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 3) {
                     if (var2 != 5) {
                        if (var2 != 6) {
                           if (var2 == 7) {
                              CanbusMsgSender.sendMsg(new byte[]{22, 122, 1, (byte)(var3 + 1)});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, 122, 2, (byte)(var3 + 1)});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 122, 3, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 108, 1, (byte)(var3 + 1)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 108, 2, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 106, 3, (byte)(var3 + 1)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 106, 4, (byte)var3});
         }

      }
   };
   private OnSettingItemSeekbarSelectListener mOnSeekBar = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 0) {
            if (var2 == 4) {
               CanbusMsgSender.sendMsg(new byte[]{22, 109, 1, (byte)(var3 + 5)});
            }
         } else if (var1 == 1 && var2 < 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte)(var2 + 1), (byte)var3});
         }

      }
   };
   private ParkPageUiSet mParkPageUiSet;
   private SettingPageUiSet mSettingPageUiSet;

   public UiMgr(Context var1) {
      try {
         this.mContext = var1;
         SettingPageUiSet var2 = this.getSettingUiSet(var1);
         this.mSettingPageUiSet = var2;
         var2.setOnSettingItemSelectListener(this.mItemSelect);
         this.mSettingPageUiSet.setOnSettingConfirmDialogListener(this.mConfirm);
         this.mSettingPageUiSet.setOnSettingItemClickListener(this.mItemClick);
         this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSeekBar);
         ParkPageUiSet var4 = this.getParkPageUiSet(var1);
         this.mParkPageUiSet = var4;
         OnPanoramicItemClickListener var5 = new OnPanoramicItemClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickItem(int var1) {
               if (var1 != 0) {
                  if (var1 != 1) {
                     if (var1 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, -1});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -14, 5, -1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 4, -1});
               }

            }
         };
         var4.setOnPanoramicItemClickListener(var5);
      } catch (Exception var3) {
         var3.printStackTrace();
         Log.d("cwh", "错误报告：" + var3);
      }

   }

   public void setShowRadar(boolean var1) {
      this.mParkPageUiSet.setShowRadar(var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
