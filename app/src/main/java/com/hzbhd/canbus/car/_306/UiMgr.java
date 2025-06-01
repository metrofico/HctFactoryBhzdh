package com.hzbhd.canbus.car._306;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
   private View mMyPanoramicView;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirCmd(13);
               }
            } else {
               this.this$0.sendAirCmd(1);
            }
         } else {
            this.this$0.sendAirCmd(10);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCmd(9);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirCmd(12);
            }
         } else {
            this.this$0.sendAirCmd(11);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCmd(8);
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirCmd(6);
      }

      public void onClickRight() {
         this.this$0.sendAirCmd(7);
      }
   };
   OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -117, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)(var1 + 1)});
         }

      }
   };
   OnPanoramicItemTouchListener mOnPanoramicItemTouchListener = new OnPanoramicItemTouchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onTouchItem(MotionEvent var1) {
         int var3 = (int)var1.getX() * 1024 / (int)this.this$0.mContext.getResources().getDimension(2131167469);
         int var5 = (int)var1.getY() * 600 / (int)this.this$0.mContext.getResources().getDimension(2131170367);
         int var2 = var3 & 255;
         int var4 = var3 >> 8 & 255;
         var3 = var5 & 255;
         var5 = var5 >> 8 & 255;
         if (var1.getAction() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte)var4, (byte)var2, (byte)var5, (byte)var3});
         } else if (var1.getAction() == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte)var4, (byte)var2, (byte)var5, (byte)var3});
         } else if (var1.getAction() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte)var4, (byte)var2, (byte)var5, (byte)var3});
         }

      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var4.hashCode();
         if (!var4.equals("_306_value_10")) {
            if (var4.equals("_306_value_9")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var3, (byte)MsgMgr.minute, (byte)MsgMgr.language});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)MsgMgr.hour, (byte)var3, (byte)MsgMgr.language});
         }

      }
   };
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 0) {
            byte var4;
            byte var5;
            if (this.this$0.isDX7()) {
               var4 = (byte)(var2 + 4);
               var5 = (byte)var3;
               CanbusMsgSender.sendMsg(new byte[]{22, -125, var4, var5});
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, var5});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, var5});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, var5});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, var5});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, var5});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, var5});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, var5});
               }
            } else {
               var5 = (byte)(var2 + 1);
               var4 = (byte)var3;
               CanbusMsgSender.sendMsg(new byte[]{22, -125, var5, var4});
               if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, var4});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, var4});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, var4});
               }
            }
         } else {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            if (var6.equals("_306_value_11")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)MsgMgr.hour, (byte)MsgMgr.minute, (byte)var3});
            }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd(2);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd(3);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd(4);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd(5);
      }
   };
   private ParkPageUiSet mParkPageUiSet;
   private SettingPageUiSet mSettingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
      AirPageUiSet var2 = this.getAirUiSet(var1);
      String[][] var3 = var2.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var3[0];
      this.mAirBtnListFrontLeft = var3[1];
      this.mAirBtnListFrontRight = var3[2];
      this.mAirBtnListFrontBottom = var3[3];
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      var2.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 40});
         }
      });
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 14, 1});
         }

         public void onRightSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, 14, 1});
         }
      });
      SettingPageUiSet var5 = this.getSettingUiSet(var1);
      this.mSettingPageUiSet = var5;
      var5.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      this.mSettingPageUiSet.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var4;
      var4.setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
   }

   private void intiUi() {
      this.removeFrontAirButtonByName(this.mContext, "dual");
      if (this.isDX7()) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_1", "_306_value_2", "_306_value_3"});
         this.mParkPageUiSet.setOnPanoramicItemTouchListener(this.mOnPanoramicItemTouchListener);
      }

      if (this.isDX5()) {
         this.mParkPageUiSet.setShowRadar(false);
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_4", "_306_value_5", "_306_value_6", "_306_value_7", "_306_title_2", "_306_title_3", "_306_value_8"});
         this.removeMainPrjBtnByName(this.mContext, "drive_data");
         this.removeMainPrjBtnByName(this.mContext, "setting");
         this.removeMainPrjBtnByName(this.mContext, "tire_info");
         this.mParkPageUiSet.setCusVideoStartX((int)this.mContext.getResources().getDimension(2131171164));
      }

      if (this.getCurrentCarId() == 2) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

   }

   private void sendAirCmd(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$data0;

         {
            this.this$0 = var1;
            this.val$data0 = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)this.val$data0, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)this.val$data0, 0});
         }
      }).start();
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -1423573049:
            if (var1.equals("ac_max")) {
               var2 = 1;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 2;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 3;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 4;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 5;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 6;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 7;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCmd(8);
            break;
         case 1:
            this.sendAirCmd(12);
            break;
         case 2:
            this.sendAirCmd(9);
            break;
         case 3:
            this.sendAirCmd(11);
            break;
         case 4:
            this.sendAirCmd(10);
            break;
         case 5:
            this.sendAirCmd(14);
            break;
         case 6:
            this.sendAirCmd(1);
            break;
         case 7:
            this.sendAirCmd(13);
      }

   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
   }

   boolean isDX5() {
      boolean var1;
      if (this.getCurrentCarId() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean isDX7() {
      int var1 = this.getCurrentCarId();
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.intiUi();
   }
}
