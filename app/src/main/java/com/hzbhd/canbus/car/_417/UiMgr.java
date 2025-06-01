package com.hzbhd.canbus.car._417;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.getSettingUiSet(this.mContext).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 0) {
               UiMgr var4;
               if (var3 == 1) {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).showButton();
               } else {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).hideButton();
               }
            }

         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           this.this$0.sendPanoramicInfo0xFD(2);
                        }
                     } else {
                        this.this$0.sendPanoramicInfo0xFD(6);
                     }
                  } else {
                     this.this$0.sendPanoramicInfo0xFD(5);
                  }
               } else {
                  this.this$0.sendPanoramicInfo0xFD(4);
               }
            } else {
               this.this$0.sendPanoramicInfo0xFD(3);
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   public void sendPanoramicInfo0xFD(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, (byte)var1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
