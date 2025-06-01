package com.hzbhd.canbus.car._276;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private int mDifferentId;
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         // $FF: Couldn't be decompiled
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferentId = this.getCurrentCarId();
      if (!MyApplication.IS_SET) {
         this.updateUiByDifferentCar(var1);
         MyApplication.IS_SET = true;
      }

      ParkPageUiSet var2 = this.getParkPageUiSet(this.mContext);
      if (this.mDifferentId == 1) {
         var2.setShowRadar(false);
      }

      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var3.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == 3) {
               if (var2 != 0) {
                  if (var2 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 27, 3, 3, 1, -1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 27, 2, 2, 1, -1});
               }
            }

         }
      });
   }

   // $FF: synthetic method
   static int access$000(UiMgr var0) {
      return var0.mDifferentId;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.mDifferentId;
      if (var2 != 0) {
         switch (var2) {
            case 2:
            case 7:
            case 9:
            case 10:
               this.removeSettingRightItem(this.mContext, 2, 3, 3);
               this.removeSettingRightItem(this.mContext, 2, 3, 3);
               break;
            case 3:
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_0"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"light_ctrl_3"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_8"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_9"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_10"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_5"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_7"});
               break;
            case 4:
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_0"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"light_ctrl_3"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_8"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_9"});
               this.removeSettingRightItemByNameList(this.mContext, new String[]{"_276_setting_10"});
               break;
            case 5:
            case 6:
               this.removeSettingRightItem(this.mContext, 2, 3, 12);
               this.removeSettingRightItem(this.mContext, 2, 5, 8);
               break;
            case 8:
               this.removeMainPrjBtnByName(this.mContext, "setting");
         }
      } else {
         this.removeMainPrjBtn(this.mContext, 0, 1);
      }

   }
}
