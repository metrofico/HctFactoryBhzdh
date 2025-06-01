package com.hzbhd.canbus.car._188;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_188_IS_SUPPORT_PANORAMIC = "share_188_is_support_panoramic";
   private static final String SHARE_188_LANGUAGE = "share_188_language";
   private AirActivity mActivity;
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   private View mMyPanoramicView;
   OnAirBtnClickListener onAirBtnClickListener_frontBottom = new UiMgr$$ExternalSyntheticLambda3(this);
   OnAirBtnClickListener onAirBtnClickListener_frontLeft = new UiMgr$$ExternalSyntheticLambda1(this);
   OnAirBtnClickListener onAirBtnClickListener_frontRight = new UiMgr$$ExternalSyntheticLambda2(this);
   OnAirBtnClickListener onAirBtnClickListener_frontTop = new UiMgr$$ExternalSyntheticLambda0(this);
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.sendAirData(0, 6);
      }

      public void onRightSeatClick() {
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_frontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(3, 0);
      }

      public void onClickUp() {
         this.this$0.sendAirData(3, 1);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_frontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(4, 0);
      }

      public void onClickUp() {
         this.this$0.sendAirData(4, 1);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData(1, 1);
      }

      public void onClickRight() {
         this.this$0.sendAirData(1, 0);
      }
   };
   private byte[] stagedAirConditionKeyState = new byte[]{0, 0, 0, 0, 0};

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
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
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            boolean var4 = var6.equals("support_panorama");
            boolean var5 = false;
            if (!var4) {
               if (var6.equals("vm_golf7_language_setup")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)var3});
                  SharePreUtil.setIntValue(this.val$context, "share_188_language", var3);
                  this.this$0.getMsgMgr(this.val$context).updateSettingActivity(var1, var2, var3);
               }
            } else {
               SharePreUtil.setIntValue(this.val$context, "share_188_is_support_panoramic", var3);
               this.this$0.getMsgMgr(this.val$context).updateSettingActivity(var1, var2, var3);
               MsgMgr var8 = this.this$0.getMsgMgr(this.val$context);
               if (var3 == 1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var8.updateSettingActivity(var1, var2 + 1, "null_value", var4);
               var8 = this.this$0.getMsgMgr(this.val$context);
               Context var7 = this.val$context;
               var4 = var5;
               if (var3 == 1) {
                  var4 = true;
               }

               var8.updateBubble(var7, var4);
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1780524959:
                  if (var3.equals("_186_blind_spot_detection")) {
                     var4 = 0;
                  }
                  break;
               case -1047287737:
                  if (var3.equals("_186_moving_object_detection")) {
                     var4 = 1;
                  }
                  break;
               case -121562794:
                  if (var3.equals("_186_lane_departure_detection")) {
                     var4 = 2;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, 83, 1});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, 81, 1});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, 82, 1});
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_55_0xE8_data4")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(this.mContext);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListener_frontTop, this.onAirBtnClickListener_frontLeft, this.onAirBtnClickListener_frontRight, this.onAirBtnClickListener_frontBottom});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListener_frontLeft, null, this.onAirTemperatureUpDownClickListener_frontRight});
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      var4.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1, var4) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$parkPageUiSet = var3;
         }

         public void addViewToWindows() {
            Context var2 = this.val$context;
            boolean var1 = false;
            if (SharePreUtil.getIntValue(var2, "share_188_is_support_panoramic", 0) == 1) {
               var1 = true;
            }

            this.val$parkPageUiSet.setShowRadar(var1 ^ true);
            this.val$parkPageUiSet.setShowCusPanoramicView(var1);
         }
      });
      this.getBubbleUiSet(var1).setOnBubbleClickListener(new UiMgr$$ExternalSyntheticLambda4());
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   // $FF: synthetic method
   static void lambda$new$0() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__188_UiMgr(int var1) {
      if (var1 == 1) {
         this.sendAirData(0, 7);
      }

   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__188_UiMgr(int var1) {
      if (var1 == 0) {
         this.sendAirData(0, 4);
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__188_UiMgr(int var1) {
      if (var1 == 0) {
         this.sendAirData(1, 2);
      }

   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__188_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 == 1) {
            this.sendAirData(1, 3);
         }
      } else {
         this.sendAirData(0, 1);
      }

   }

   void sendAirData(int var1, int var2) {
      byte[] var3 = this.stagedAirConditionKeyState;
      var3[var1] = (byte)DataHandleUtils.setIntByteWithBit(var3[var1], var2, true);
      var3 = this.stagedAirConditionKeyState;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, var3));
      var3 = this.stagedAirConditionKeyState;
      var3[var1] = (byte)DataHandleUtils.setIntByteWithBit(var3[var1], var2, false);
      var3 = this.stagedAirConditionKeyState;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, var3));
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.mDifferent;
      boolean var3 = false;
      MsgMgr var4;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  this.removeSettingLeftItemByNameList(var1, new String[]{"vm_golf7_language_setup", "_186_driving_aids"});
                  this.getMsgMgr(var1).updateSettingActivity(0, 0, SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0));
                  var4 = this.getMsgMgr(var1);
                  if (SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0) == 1) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var4.updateSettingActivity(0, 1, "null_value", var3);
               }
            } else {
               this.removeSettingLeftItemByNameList(var1, new String[]{"vm_golf7_language_setup"});
               this.removeSettingLeftItemByNameList(var1, new String[]{"panorama_setting"});
            }
         } else {
            this.removeSettingLeftItemByNameList(var1, new String[]{"_186_driving_aids"});
            this.removeSettingLeftItemByNameList(var1, new String[]{"vm_golf7_language_setup"});
            this.getMsgMgr(var1).updateSettingActivity(0, 0, SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0));
            var4 = this.getMsgMgr(var1);
            if (SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            var4.updateSettingActivity(0, 1, "null_value", var3);
         }
      } else {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_186_driving_aids"});
         this.getMsgMgr(var1).updateSettingActivity(0, 0, SharePreUtil.getIntValue(var1, "share_188_language", 0));
         this.getMsgMgr(var1).updateSettingActivity(1, 0, SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0));
         var4 = this.getMsgMgr(var1);
         if (SharePreUtil.getIntValue(var1, "share_188_is_support_panoramic", 0) == 1) {
            var3 = true;
         }

         var4.updateSettingActivity(1, 1, "null_value", var3);
      }

   }
}
