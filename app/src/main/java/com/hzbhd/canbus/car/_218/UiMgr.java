package com.hzbhd.canbus.car._218;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private static final String SHARE_218_SETTING_DATA0 = "share_218_setting_data0";
   private int data0 = 0;
   private int data1 = 1;
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      this.initSetting(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var7.hashCode();
            int var5 = var7.hashCode();
            int var4 = -1;
            switch (var5) {
               case 2109787597:
                  if (var7.equals("_218_setting_0_0")) {
                     var4 = 0;
                  }
                  break;
               case 2109787598:
                  if (var7.equals("_218_setting_0_1")) {
                     var4 = 1;
                  }
                  break;
               case 2109787599:
                  if (var7.equals("_218_setting_0_2")) {
                     var4 = 2;
                  }
                  break;
               case 2109787600:
                  if (var7.equals("_218_setting_0_3")) {
                     var4 = 3;
                  }
                  break;
               case 2109787601:
                  if (var7.equals("_218_setting_0_4")) {
                     var4 = 4;
                  }
                  break;
               case 2109788558:
                  if (var7.equals("_218_setting_1_0")) {
                     var4 = 5;
                  }
                  break;
               case 2109788559:
                  if (var7.equals("_218_setting_1_1")) {
                     var4 = 6;
                  }
            }

            boolean var6;
            UiMgr var8;
            switch (var4) {
               case 0:
                  var8 = this.this$0;
                  var4 = var8.data0;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data0 = DataHandleUtils.setIntByteWithBit(var4, 0, var6);
                  break;
               case 1:
                  var8 = this.this$0;
                  var4 = var8.data0;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data0 = DataHandleUtils.setIntByteWithBit(var4, 1, var6);
                  break;
               case 2:
                  var8 = this.this$0;
                  var4 = var8.data0;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data0 = DataHandleUtils.setIntByteWithBit(var4, 2, var6);
                  break;
               case 3:
                  var8 = this.this$0;
                  var4 = var8.data0;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data0 = DataHandleUtils.setIntByteWithBit(var4, 3, var6);
                  break;
               case 4:
                  var8 = this.this$0;
                  var4 = var8.data0;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data0 = DataHandleUtils.setIntByteWithBit(var4, 4, var6);
                  break;
               case 5:
                  var8 = this.this$0;
                  var4 = var8.data1;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data1 = DataHandleUtils.setIntByteWithBit(var4, 0, var6);
                  break;
               case 6:
                  var8 = this.this$0;
                  var4 = var8.data1;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var8.data1 = DataHandleUtils.setIntByteWithBit(var4, 1, var6);
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -94, (byte)this.this$0.data0, (byte)FutureUtil.instance.isDiscExist(), 0, 0});
            this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            SharePreUtil.setIntValue(this.val$context, "share_218_setting_data0", this.this$0.data0);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initSetting(Context var1) {
      this.data0 = SharePreUtil.getIntValue(var1, "share_218_setting_data0", 0);
      this.getMsgMgr(var1).updateSetting(this.data0);
   }

   int getData0() {
      return this.data0;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
