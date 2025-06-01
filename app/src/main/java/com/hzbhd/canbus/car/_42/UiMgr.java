package com.hzbhd.canbus.car._42;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private AirActivity mActivity;
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      OriginalCarDevicePageUiSet var2 = this.getOriginalCarDevicePageUiSet(var1);
      var2.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
         }
      });
      var2.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickTopBtnItem(int var1) {
         }
      });
      var2.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickBottomBtnItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -59, 4});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -59, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -59, 3});
            }

         }
      });
      var2.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
         }
      });
      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == 0 && var2 == 3) {
               CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 1});
            }

         }
      });
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() == 0) {
         this.removeMainPrjBtn(this.mContext, 0, 1);
      }

   }
}
