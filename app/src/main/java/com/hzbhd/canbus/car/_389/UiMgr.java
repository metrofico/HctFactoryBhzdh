package com.hzbhd.canbus.car._389;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private MsgMgr msgMgr;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("language_setup")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)var3});
               this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
            }

         }
      });
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   public void sendMediaInfo0x91(int var1, byte[] var2) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -110, (byte)var1}, var2));
   }

   public void sendMediaInfo0x93(int var1, byte[] var2) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -109, (byte)var1}, var2));
   }
}
