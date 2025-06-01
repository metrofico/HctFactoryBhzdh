package com.hzbhd.canbus.car._4;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private String PARKING_MODE_KEY = "PARKING_MODE_KEY";
   private int mBit0 = 0;
   private int mBit1 = 0;
   private int mBit2 = 0;
   private int mBit3 = 0;
   private int mBit4 = 0;
   private int mBit5 = 0;
   private int mBit6 = 0;
   private int mBit7 = 0;
   Context mContext;
   MsgMgr mMsgMgr;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(33);
      }
   };
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

                     this.this$0.sendAmplifierInfo(4, var2 - 9);
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

                  this.this$0.sendAmplifierInfo(3, var2 - 9);
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

               this.this$0.sendAmplifierInfo(5, var2 - 9);
            }
         } else {
            this.this$0.sendAmplifierInfo(0, var2);
         }

      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(20);
         this.this$0.activeRequestData(65, 2);
         this.this$0.activeRequestData(22);
         this.this$0.activeRequestData(36);
      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_1004_amp") && var2 == 0) {
            this.this$0.sendAmplifierInfo(6, var3);
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

      }
   };
   OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         UiMgr var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_246_paring_info")) {
            this.this$0.activeRequestData(37);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getAirUiSet(var1).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var2.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(this.mContext);
      var3.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var3.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var3.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
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

   private void initUi() {
   }

   private void intiData() {
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_paring_info"), 2, SharePreUtil.getIntValue(this.mContext, this.PARKING_MODE_KEY, 0));
   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte)var1, (byte)var2});
   }

   private void sendCarControlInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
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

      return 404;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return 404;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void sendIconInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte)this.getDecimalFrom8Bit(0, 0, 0, 0, 0, var1, var2, 0)});
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

   public void sendVolInfo(int var1, boolean var2) {
      if (var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)this.getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(var1, 6, 1), DataHandleUtils.getIntFromByteWithBit(var1, 5, 1), DataHandleUtils.getIntFromByteWithBit(var1, 4, 1), DataHandleUtils.getIntFromByteWithBit(var1, 3, 1), DataHandleUtils.getIntFromByteWithBit(var1, 2, 1), DataHandleUtils.getIntFromByteWithBit(var1, 1, 1), DataHandleUtils.getIntFromByteWithBit(var1, 0, 1))});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
      this.intiData();
   }
}
