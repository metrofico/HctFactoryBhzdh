package com.hzbhd.canbus.car._307;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var6.hashCode();
         int var5 = var6.hashCode();
         byte var4 = -1;
         switch (var5) {
            case 102584022:
               if (var6.equals("language_setup")) {
                  var4 = 0;
               }
               break;
            case 127625322:
               if (var6.equals("_307_title_10")) {
                  var4 = 1;
               }
               break;
            case 127625323:
               if (var6.equals("_307_title_11")) {
                  var4 = 2;
               }
               break;
            case 127625324:
               if (var6.equals("_307_title_12")) {
                  var4 = 3;
               }
               break;
            case 127625325:
               if (var6.equals("_307_title_13")) {
                  var4 = 4;
               }
               break;
            case 127625326:
               if (var6.equals("_307_title_14")) {
                  var4 = 5;
               }
               break;
            case 127625327:
               if (var6.equals("_307_title_15")) {
                  var4 = 6;
               }
               break;
            case 127625328:
               if (var6.equals("_307_title_16")) {
                  var4 = 7;
               }
               break;
            case 127625329:
               if (var6.equals("_307_title_17")) {
                  var4 = 8;
               }
               break;
            case 127625330:
               if (var6.equals("_307_title_18")) {
                  var4 = 9;
               }
               break;
            case 1805232265:
               if (var6.equals("_307_title_4")) {
                  var4 = 10;
               }
               break;
            case 1805232266:
               if (var6.equals("_307_title_5")) {
                  var4 = 11;
               }
               break;
            case 1805232267:
               if (var6.equals("_307_title_6")) {
                  var4 = 12;
               }
               break;
            case 1805232268:
               if (var6.equals("_307_title_7")) {
                  var4 = 13;
               }
               break;
            case 1805232269:
               if (var6.equals("_307_title_8")) {
                  var4 = 14;
               }
               break;
            case 1805232270:
               if (var6.equals("_307_title_9")) {
                  var4 = 15;
               }
         }

         UiMgr var7;
         switch (var4) {
            case 0:
               if (var3 <= 8) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
               } else if (var3 <= 11) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 2)});
               } else if (var3 <= 14) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 4)});
               } else if (var3 == 15) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 5)});
               } else if (var3 <= 22) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 6)});
               } else if (var3 <= 24) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 8)});
               } else if (var3 == 25) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 15});
               } else if (var3 == 26) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 34});
               }

               var7 = this.this$0;
               var7.getMsgMgr(var7.mContext).updateSettings(var1, var2, var3);
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 19, (byte)var3, 0, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 33, (byte)var3, 0, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 34, (byte)var3, 0, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 35, (byte)var3, 0, 0});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 49, (byte)var3, 0, 0});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 50, (byte)var3, 0, 0});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 51, (byte)var3, 0, 0});
               var7 = this.this$0;
               var7.getMsgMgr(var7.mContext).updateSettings(var1, var2, var3);
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 20, (byte)var3, 0, 0});
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var3, 0, 0});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var3, 0, 0});
               break;
            case 11:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var3, 0, 0});
               break;
            case 12:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3, 0, 0});
               break;
            case 13:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte)var3, 0, 0});
               break;
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 17, (byte)var3, 0, 0});
               break;
            case 15:
               CanbusMsgSender.sendMsg(new byte[]{22, 111, 18, (byte)var3, 0, 0});
         }

      }
   };
   private SettingPageUiSet mSettingPageUiSet;
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      this.mSettingPageUiSet = var2;
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() == 0) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

   }
}
