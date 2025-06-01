package com.hzbhd.canbus.car._385;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_41_FRONT_CAMERA_SWITCH = "share_41_front_camera_switch";
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private MsgMgr msgMgr;
   private SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      this.mContext = var1;
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_284_support_right_view")) {
               UiMgr var5 = this.this$0;
               var5.getMsgMgr(var5.mContext).updateSettings(this.this$0.mContext, var1, var2, "F_camera", var3);
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   public void sendCarModel() {
      CanbusMsgSender.sendMsg(new byte[]{22, 36, 4, 17});
   }
}
