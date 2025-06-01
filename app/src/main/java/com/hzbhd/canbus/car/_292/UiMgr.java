package com.hzbhd.canbus.car._292;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnSettingItemSelectListener mItemSelect = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  if (var2 != 0) {
                     if (var2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var3});
                  }
               }
            } else {
               switch (var2) {
                  case 0:
                     this.this$0.sendSettingMsg(12, var3);
                     break;
                  case 1:
                     this.this$0.sendSettingMsg(13, var3);
                     break;
                  case 2:
                     this.this$0.sendSettingMsg(14, var3);
                     break;
                  case 3:
                     this.this$0.sendSettingMsg(15, var3);
                     break;
                  case 4:
                     this.this$0.sendSettingMsg(24, var3);
                     break;
                  case 5:
                     this.this$0.sendSettingMsg(22, var3);
                     break;
                  case 6:
                     this.this$0.sendSettingMsg(23, var3);
                     break;
                  case 7:
                     this.this$0.sendSettingMsg(16, var3);
                     break;
                  case 8:
                     this.this$0.sendSettingMsg(17, var3);
                     break;
                  case 9:
                     this.this$0.sendSettingMsg(18, var3);
                     break;
                  case 10:
                     this.this$0.sendSettingMsg(19, var3);
                     break;
                  case 11:
                     this.this$0.sendSettingMsg(20, var3);
                     break;
                  case 12:
                     this.this$0.sendSettingMsg(21, var3);
                     break;
                  case 13:
                     this.this$0.sendSettingMsg(28, var3);
                     break;
                  case 14:
                     this.this$0.sendSettingMsg(27, var3);
                     break;
                  case 15:
                     this.this$0.sendSettingMsg(26, var3);
                     break;
                  case 16:
                     this.this$0.sendSettingMsg(25, var3);
               }
            }
         } else if (var2 < 10) {
            this.this$0.sendSettingMsg(var2, var3);
         } else {
            this.this$0.sendSettingMsg(var2 + 1, var3);
         }

      }
   };
   private OnOnStarClickListener mOnOnStarPhone = new OnOnStarClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void exit() {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
      }

      public void handOff() {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
      }

      public void handOn(String var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
      }

      public void init() {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
      }

      public void numberClick(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)(var1 | 128)});
      }
   };
   private OnStartPageUiSet mOnStartPageUiSet;
   private ParkPageUiSet mParkPageUiSet;
   private OnPanoramicItemTouchListener mParkTouch = new OnPanoramicItemTouchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onTouchItem(MotionEvent var1) {
         int var7 = (int)var1.getX();
         int var6 = (int)var1.getY();
         var7 = var7 * 800 / CommUtil.getDimenByResId(this.this$0.mContext, "dp1024");
         var6 = var6 * 480 / CommUtil.getDimenByResId(this.this$0.mContext, "dp600");
         byte var4 = (byte)(var7 & 255);
         byte var3 = (byte)(var7 >> 8 & 255);
         byte var2 = (byte)(var6 & 255);
         byte var5 = (byte)(var6 >> 8 & 255);
         if (var1.getAction() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -29, 1, var4, var3, var2, var5});
         } else if (var1.getAction() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -29, 0, var4, var3, var2, var5});
         }

         this.this$0.playBeep();
      }
   };
   private OnSettingItemSeekbarSelectListener mSeekBar = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 2 && var2 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)var3});
         }

      }
   };
   private SettingPageUiSet mSettingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mSettingPageUiSet = this.getSettingUiSet(var1);
      this.mOnStartPageUiSet = this.getOnStartPageUiSet(this.mContext);
      this.mParkPageUiSet = this.getParkPageUiSet(this.mContext);
      this.mSettingPageUiSet.setOnSettingItemSelectListener(this.mItemSelect);
      this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mSeekBar);
      this.mOnStartPageUiSet.setOnOnStarClickListener(this.mOnOnStarPhone);
      this.mParkPageUiSet.setOnPanoramicItemTouchListener(this.mParkTouch);
   }

   private void sendSettingMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }
}
