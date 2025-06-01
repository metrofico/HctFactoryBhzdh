package com.hzbhd.canbus.car._21;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Timer;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_21_IS_SUPPORT_PANORAMIC = "share_21_is_support_panoramic";
   private static final String SHARE_21_LANGUAGE = "share_21_language";
   private static Timer mTimer;
   private static TimerTask mTimerTask;
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   private View mMyPanoramicView;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(3, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(2, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(0, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(1, var1);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(30);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(31);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(32);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(33);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(28);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(29);
         }
      });
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirCommand(25);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirCommand(25);
         }
      });
      this.getMsgMgr(var1).updateSetting(0, 0, SharePreUtil.getIntValue(var1, "share_21_language", 0));
      this.getMsgMgr(var1).updateSetting(1, 0, SharePreUtil.getBoolValue(var1, "share_21_is_support_panoramic", false));
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            boolean var4 = false;
            if (var1 == 0) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)var3});
                  SharePreUtil.setIntValue(this.this$0.mContext, "share_21_language", var3);
                  this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
               }
            } else if (var1 == 1 && var2 == 0) {
               Context var5 = this.val$context;
               if (var3 == 1) {
                  var4 = true;
               }

               SharePreUtil.setBoolValue(var5, "share_21_is_support_panoramic", var4);
               this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
               UiMgr var6;
               if (var3 == 0) {
                  var6 = this.this$0;
                  var6.getMsgMgr(var6.mContext).hideButton();
               } else {
                  var6 = this.this$0;
                  var6.getMsgMgr(var6.mContext).showButton();
               }
            }

         }
      });
      var3.setOnSettingItemTouchListener(new OnSettingItemTouchListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onTouchItem(int var1, int var2, View var3, MotionEvent var4) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var5.hashCode();
            if (var5.equals("_55_0xE8_data4")) {
               if (var4.getAction() == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 3});
               } else if (var4.getAction() == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, 4});
               }
            }

         }
      });
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
            boolean var1 = SharePreUtil.getBoolValue(this.val$context, "share_21_is_support_panoramic", false);
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

   private void resolveAirBtn(int var1, int var2) {
      String var3 = this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2];
      if (!TextUtils.isEmpty(var3)) {
         var3.hashCode();
         switch (var3) {
            case "front_defog":
               this.sendAirCommand(21);
               break;
            case "rear_defog":
               this.sendAirCommand(22);
               break;
            case "blow_positive":
               this.sendAirCommand(25);
               break;
            case "front_window_heat":
               this.sendAirCommand(37);
               break;
            case "ac":
               this.sendAirCommand(17);
               break;
            case "auto":
               this.sendAirCommand(20);
               break;
            case "dual":
               this.sendAirCommand(23);
               break;
            case "power":
               this.sendAirCommand(16);
               break;
            case "in_out_cycle":
               this.sendAirCommand(19);
         }

      }
   }

   private void sendAirCommand(int var1) {
      byte var2 = (byte)var1;
      this.sendUpDownCommand(new byte[]{22, -126, var2, 1}, new byte[]{22, -126, var2, 0}, 100L);
   }

   private void sendUpDownCommand(byte[] var1, byte[] var2, long var3) {
      CanbusMsgSender.sendMsg(var1);
      this.startTimer(new TimerTask(this, var2) {
         final UiMgr this$0;
         final byte[] val$up;

         {
            this.this$0 = var1;
            this.val$up = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(this.val$up);
            this.this$0.stopTimer();
         }
      }, var3);
   }

   private void startTimer(TimerTask var1, long var2) {
      if (var1 != null) {
         if (mTimer == null) {
            mTimer = new Timer();
         }

         mTimerTask = var1;
         mTimer.schedule(var1, var2);
      }
   }

   private void startTimer(TimerTask var1, long var2, int var4) {
      if (var1 != null) {
         if (mTimer == null) {
            mTimer = new Timer();
         }

         mTimerTask = var1;
         mTimer.schedule(var1, var2, (long)var4);
      }
   }

   private void stopTimer() {
      TimerTask var1 = mTimerTask;
      if (var1 != null) {
         var1.cancel();
         mTimerTask = null;
      }

      Timer var2 = mTimer;
      if (var2 != null) {
         var2.cancel();
         mTimer = null;
      }

   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1, this.getCurrentCarId());
      }

      return this.mMyPanoramicView;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
