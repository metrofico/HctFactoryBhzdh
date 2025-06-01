package com.hzbhd.canbus.car._383;

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
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public String ID383_VOICE_LANGUAGE = "ID383_VOICE_LANGUAGE";
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
   MsgMgr mMsgMgr;
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
         int var4 = (int)var1.getY() * 600 / (int)this.this$0.mContext.getResources().getDimension(2131170367);
         int var2 = var3 & 255;
         var3 = var3 >> 8 & 255;
         int var5 = var4 & 255;
         var4 = var4 >> 8 & 255;
         if (var1.getAction() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte)var3, (byte)var2, (byte)var4, (byte)var5});
         } else if (var1.getAction() == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte)var3, (byte)var2, (byte)var4, (byte)var5});
         } else if (var1.getAction() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte)var3, (byte)var2, (byte)var4, (byte)var5});
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
         UiMgr var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "car_set")) {
            byte var4;
            byte var5;
            if (this.this$0.isDX7()) {
               var5 = (byte)(var2 + 4);
               var4 = (byte)var3;
               CanbusMsgSender.sendMsg(new byte[]{22, -125, var5, var4});
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, var4});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, var4});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, var4});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, var4});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, var4});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, var4});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, var4});
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
            String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var8.hashCode();
            if (var8.equals("_306_value_11")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)MsgMgr.hour, (byte)MsgMgr.minute, (byte)var3});
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_306_360")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_306_360", "_306_360")) {
               SharePreUtil.setIntValue(this.this$0.mContext, "ID306_360BUTTON_VISIBILITY_STATE", var3);
               var6 = this.this$0;
               var6.getMsgMgr(var6.mContext).updateSettings(var1, var2, SharePreUtil.getIntValue(this.this$0.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 0));
               var6 = this.this$0;
               var6.getMsgMgr(var6.mContext).showDialogAndRestartApp(this.this$0.mContext.getString(2131762036));
            }

            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_306_360", "_306_avm_switch")) {
               Context var9 = this.this$0.mContext;
               UiMgr var7 = this.this$0;
               SharePreUtil.setIntValue(var9, var7.getMsgMgr(var7.mContext).ID306_AVM_CONGIF_TAG, var3);
               var6 = this.this$0;
               var6.getMsgMgr(var6.mContext).updateSettings(var1, var2, var3);
            }
         }

         var6 = this.this$0;
         if (var1 == var6.getSettingLeftIndexes(var6.mContext, "_306_voice_language")) {
            var6 = this.this$0;
            if (var2 == var6.getSettingRightIndex(var6.mContext, "_306_voice_language", "_306_voice_language")) {
               if (var3 == 0) {
                  var6 = this.this$0;
                  var6.getMsgMgr(var6.mContext);
                  MsgMgr.mLanguage = "_zh";
               } else {
                  var6 = this.this$0;
                  var6.getMsgMgr(var6.mContext);
                  MsgMgr.mLanguage = "_en";
               }

               SharePreUtil.setIntValue(this.this$0.mContext, this.this$0.ID383_VOICE_LANGUAGE, var3);
               var6 = this.this$0;
               var6.getMsgMgr(var6.mContext).updateSettings(var1, var2, SharePreUtil.getIntValue(this.this$0.mContext, "ID383_VOICE_LANGUAGE", 0));
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
      this.removeDriveDateItemForTitles(var1, new String[]{"_306_title_4"});
      if (this.getCurrentCarId() == 1) {
         this.removeMainPrjBtnByName(var1, "air");
         this.removeMainPrjBtnByName(var1, "tire_info");
      }

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
      this.mParkPageUiSet.setOnPanoramicItemTouchListener(new UiMgr$$ExternalSyntheticLambda0());
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void intiUi() {
      this.removeFrontAirButtonByName(this.mContext, "dual");
      if (this.isDX7()) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_306_voice_language"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_1", "_306_value_2", "_306_value_3"});
         this.mParkPageUiSet.setOnPanoramicItemTouchListener(this.mOnPanoramicItemTouchListener);
      }

      if (this.isDX5High()) {
         this.mParkPageUiSet.setShowRadar(false);
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_306_value_4", "_306_value_5", "_306_value_6", "_306_value_7", "_306_title_2", "_306_title_3", "_306_value_8"});
         this.removeMainPrjBtnByName(this.mContext, "drive_data");
         this.removeMainPrjBtnByName(this.mContext, "tire_info");
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"car_set"});
         this.mParkPageUiSet.setCusVideoStartX((int)this.mContext.getResources().getDimension(2131171164));
      }

      if (this.getCurrentCarId() == 2) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_306_360"), this.getSettingRightIndex(this.mContext, "_306_360", "_306_360"), SharePreUtil.getIntValue(this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 0));
      MsgMgr var3 = this.getMsgMgr(this.mContext);
      int var1 = this.getSettingLeftIndexes(this.mContext, "_306_360");
      int var2 = this.getSettingRightIndex(this.mContext, "_306_360", "_306_avm_switch");
      Context var4 = this.mContext;
      var3.updateSettings(var1, var2, SharePreUtil.getIntValue(var4, this.getMsgMgr(var4).ID306_AVM_CONGIF_TAG, 0));
   }

   // $FF: synthetic method
   static void lambda$new$0(MotionEvent var0) {
      int var3 = (int)var0.getX();
      int var1 = 600 - (int)var0.getY();
      if (var1 >= 0) {
         if (var3 <= 1024) {
            int var2 = var0.getAction();
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte)(var3 >> 8), (byte)var3, (byte)(var1 >> 8), (byte)var1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte)(var3 >> 8), (byte)var3, (byte)(var1 >> 8), (byte)var1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte)(var3 >> 8), (byte)var3, (byte)(var1 >> 8), (byte)var1});
            }

         }
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
      if (this.isDx5Low()) {
         SharePreUtil.setIntValue(this.mContext, "ID306_360BUTTON_VISIBILITY_STATE", 1);
      }

      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   boolean isDX5High() {
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

   boolean isDx3() {
      boolean var1;
      if (this.getCurrentCarId() == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean isDx5Low() {
      boolean var1;
      if (this.getCurrentCarId() == 3) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.intiUi();
   }
}
