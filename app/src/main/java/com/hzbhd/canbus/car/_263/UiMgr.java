package com.hzbhd.canbus.car._263;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnSettingItemClickListener mOnSettingItemSelectListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemClickListener(this.mOnSettingItemSelectListener);
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               this.this$0.sendData(new byte[]{22, -112, 20});
               this.this$0.sendData(new byte[]{22, -112, 51});
               this.this$0.sendData(new byte[]{22, -112, 36});
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 0 && var2 == 0) {
               Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
               this.this$0.sendData(new byte[]{22, -58, 1, 1});
            }

         }
      });
   }
}
