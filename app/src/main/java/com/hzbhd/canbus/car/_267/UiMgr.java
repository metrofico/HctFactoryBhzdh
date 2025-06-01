package com.hzbhd.canbus.car._267;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -17, (byte)var3});
            }

         }
      });
      var2.setOnSettingItemTouchListener(new OnSettingItemTouchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onTouchItem(int var1, int var2, View var3, MotionEvent var4) {
            if (var1 == 1) {
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           if (var4.getAction() == 0) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -111, 4, 1});
                           }

                           if (var4.getAction() == 1) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -111, 4, 0});
                           }
                        }
                     } else {
                        if (var4.getAction() == 0) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -111, 3, 1});
                        }

                        if (var4.getAction() == 1) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -111, 3, 0});
                        }
                     }
                  } else {
                     if (var4.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -111, 2, 1});
                     }

                     if (var4.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -111, 2, 0});
                     }
                  }
               } else {
                  if (var4.getAction() == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -111, 1, 1});
                  }

                  if (var4.getAction() == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -111, 1, 0});
                  }
               }
            }

         }
      });
   }
}
