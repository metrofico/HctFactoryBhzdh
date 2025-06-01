package com.hzbhd.canbus.car._249;

import android.content.Context;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getAirUiSet(var1).setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == 1) {
               this.this$0.sendData(new byte[]{22, -112, 35});
               this.this$0.sendData(new byte[]{22, -112, 54});
               this.this$0.sendData(new byte[]{22, -112, 127});
            }

         }
      });
   }
}
