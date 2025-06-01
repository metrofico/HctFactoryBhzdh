package com.hzbhd.canbus.car._1;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private String PARKING_MODE_KEY = "PARKING_MODE_KEY";
   private int a = 0;
   private int b = 0;
   private int c = 0;
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
         this.this$0.activeRequestData(39);
      }

      public void destroy() {
      }
   };
   OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         UiMgr var4;
         if (var3 != 1) {
            if (var3 == 2) {
               if (var2 < 0) {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext);
                  MsgMgr.leftRightTag = -1;
               } else {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext);
                  MsgMgr.leftRightTag = 1;
               }

               this.this$0.sendAmplifierInfo(1, var2);
               AmpUtil.saveAmpSendValue(this.this$0.mContext, 6, var2);
            }
         } else {
            if (var2 < 0) {
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext);
               MsgMgr.frontRearTag = -1;
            } else {
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext);
               MsgMgr.frontRearTag = 1;
            }

            this.this$0.sendAmplifierInfo(2, var2);
            AmpUtil.saveAmpSendValue(this.this$0.mContext, 5, var2);
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            UiMgr var4;
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     if (var2 < 9) {
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext);
                        MsgMgr.bandMiddleTag = -1;
                     } else {
                        var4 = this.this$0;
                        var4.getMsgMgr(var4.mContext);
                        MsgMgr.bandMiddleTag = 1;
                     }

                     var4 = this.this$0;
                     var2 -= 9;
                     var4.sendAmplifierInfo(5, var2);
                     AmpUtil.saveAmpSendValue(this.this$0.mContext, 3, var2);
                  }
               } else {
                  if (var2 < 9) {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext);
                     MsgMgr.bandBassTag = -1;
                  } else {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext);
                     MsgMgr.bandBassTag = 1;
                  }

                  var4 = this.this$0;
                  var2 -= 9;
                  var4.sendAmplifierInfo(3, var2);
                  AmpUtil.saveAmpSendValue(this.this$0.mContext, 4, var2);
               }
            } else {
               if (var2 < 9) {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext);
                  MsgMgr.bandTrebleTag = -1;
               } else {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext);
                  MsgMgr.bandTrebleTag = 1;
               }

               var4 = this.this$0;
               var2 -= 9;
               var4.sendAmplifierInfo(4, var2);
               AmpUtil.saveAmpSendValue(this.this$0.mContext, 2, var2);
            }
         } else {
            this.this$0.sendAmplifierInfo(0, var2);
            AmpUtil.saveAmpSendValue(this.this$0.mContext, 1, var2);
         }

      }
   };
   OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         switch (var1) {
            case 0:
               this.this$0.sendOriginalCarDeviceInfo(4, 0);
               break;
            case 1:
               this.this$0.sendOriginalCarDeviceInfo(6, 0);
               break;
            case 2:
               if (this.this$0.a == 0) {
                  this.this$0.a = 1;
                  this.this$0.sendOriginalCarDeviceInfo(10, 0);
               } else {
                  this.this$0.a = 0;
                  this.this$0.sendOriginalCarDeviceInfo(11, 0);
               }
               break;
            case 3:
               if (this.this$0.c == 0) {
                  this.this$0.c = 1;
                  this.this$0.sendOriginalCarDeviceInfo(1, 0);
               } else {
                  this.this$0.c = 0;
                  this.this$0.sendOriginalCarDeviceInfo(2, 0);
               }
               break;
            case 4:
               if (this.this$0.b == 0) {
                  this.this$0.b = 1;
                  this.this$0.sendOriginalCarDeviceInfo(8, 0);
               } else {
                  this.this$0.b = 0;
                  this.this$0.sendOriginalCarDeviceInfo(9, 0);
               }
               break;
            case 5:
               this.this$0.sendOriginalCarDeviceInfo(5, 0);
               break;
            case 6:
               this.this$0.sendOriginalCarDeviceInfo(3, 0);
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      private void send0xA7TireInfo() {
         CanbusMsgSender.sendMsg(new byte[]{22, -89, 1});
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_1001_tire_3")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_1001_tire_3", "_1001_tire_3")) {
               this.send0xA7TireInfo();
            }
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_paring_info")) {
            if (var2 != 1) {
               if (var2 == 2) {
                  this.this$0.sendCarControlInfo(2, var3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.PARKING_MODE_KEY, var1, var2, var3);
               }
            } else {
               this.this$0.sendCarControlInfo(0, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_1001_bt2")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_1001_bt2", "_1001_bt_7")) {
               this.this$0.sendBtInfo(1, var3);
            }
         }

      }
   };
   OnSettingItemSwitchListener onSettingItemSwitchListener = new OnSettingItemSwitchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onSwitch(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_326_radar_info_1")) {
            var4 = this.this$0;
            var4.getSettingRightIndex(var4.mContext, "_326_radar_info_1", "_326_radar_info_1");
            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, "FRONT_RADAR_KEY", var1, var2, var3);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(this.mContext);
      var2.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var2.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var2.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      this.getOriginalCarDevicePageUiSet(this.mContext).setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemClickListener(this.onSettingItemClickListener);
      var3.setOnSettingItemSwitchListener(this.onSettingItemSwitchListener);
   }

   private void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private void activeRequestData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
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

   private void initAmp(Context var1) {
      (new Thread(new Runnable(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            (new AmpUtil()).initAmpData(this.this$0.mContext);
         }
      })).start();
      (new AmpUtil()).intiAmpUi(var1);
   }

   private void initUi() {
   }

   private void intiData() {
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_paring_info"), 2, SharePreUtil.getIntValue(this.mContext, this.PARKING_MODE_KEY, 0));
   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte)var1, (byte)var2});
   }

   private void sendBtInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte)var1, (byte)var2});
   }

   private void sendCarControlInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
   }

   private void sendOriginalCarDeviceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -95, (byte)var1, (byte)var2});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var5 = var7.iterator();

         while(var5.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
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

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void sendMediaPalyInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void sendPhoneNumber() {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 6, 1, 84, 101, 108, 32, 79, 102, 102});
   }

   public void sendPhoneNumber(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendRadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void sendSourceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
      this.intiData();
      this.initAmp(var1);
   }
}
