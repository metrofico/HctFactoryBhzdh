package com.hzbhd.canbus.car._287;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_287_IS_SUPPORT_PANORAMIC = "share_287_is_support_panoramic";
   private Context mContext;
   private MsgMgr mMsgMgr;
   private View mMyPanoramicView;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getMsgMgr(var1).updateSetting(0, 0, SharePreUtil.getBoolValue(var1, "share_287_is_support_panoramic", false));
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 0) {
               Context var5 = this.val$context;
               boolean var4 = true;
               if (var3 != 1) {
                  var4 = false;
               }

               SharePreUtil.setBoolValue(var5, "share_287_is_support_panoramic", var4);
               this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == 0 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
            }

         }
      });
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      var3.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1, var3) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$parkPageUiSet = var3;
         }

         public void addViewToWindows() {
            boolean var1 = SharePreUtil.getBoolValue(this.val$context, "share_287_is_support_panoramic", false);
            this.val$parkPageUiSet.setShowRadar(var1 ^ true);
            this.val$parkPageUiSet.setShowCusPanoramicView(var1);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
