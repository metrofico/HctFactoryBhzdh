package com.hzbhd.canbus.car._275;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 1});
         }

      }
   };

   public UiMgr(Context var1) {
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
   }
}
