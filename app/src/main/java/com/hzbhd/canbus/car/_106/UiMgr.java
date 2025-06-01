package com.hzbhd.canbus.car._106;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.DataHandleUtils;

public class UiMgr extends AbstractUiMgr {
   MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void send0x86VolControl(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)this.getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(var1, 6, 1), DataHandleUtils.getIntFromByteWithBit(var1, 5, 1), DataHandleUtils.getIntFromByteWithBit(var1, 4, 1), DataHandleUtils.getIntFromByteWithBit(var1, 3, 1), DataHandleUtils.getIntFromByteWithBit(var1, 2, 1), DataHandleUtils.getIntFromByteWithBit(var1, 1, 1), DataHandleUtils.getIntFromByteWithBit(var1, 0, 1))});
   }

   public void send0xC0MediaInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void send0xC1Info(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
