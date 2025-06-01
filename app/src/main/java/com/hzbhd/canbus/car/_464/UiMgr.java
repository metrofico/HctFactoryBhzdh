package com.hzbhd.canbus.car._464;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private boolean cdFasterTag = false;
   private boolean cdSlowerTag = false;
   private boolean dvdFasterTag = false;
   private boolean dvdSlowerTag = false;
   private Context mContext;
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd0x2E(2);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd0x2E(3);
      }
   };
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd0x2E(4);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd0x2E(5);
      }
   };
   private MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirCmd0x2E(40);
            }
         } else {
            this.this$0.sendAirCmd0x2E(21);
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirCmd0x2E(20);
            }
         } else {
            this.this$0.sendAirCmd0x2E(18);
         }

      }
   };
   private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.sendAirCmd0x2E(36);
      }

      public void onRightSeatClick() {
         this.this$0.sendAirCmd0x2E(37);
      }
   };
   private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirCmd0x2E(25);
               }
            } else {
               this.this$0.sendAirCmd0x2E(23);
            }
         } else {
            this.this$0.sendAirCmd0x2E(1);
         }

      }
   };
   private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 3) {
               if (var1 == 4) {
                  this.this$0.sendAirCmd0x2E(39);
               }
            } else {
               this.this$0.sendAirCmd0x2E(57);
            }
         } else {
            this.this$0.sendAirCmd0x2E(16);
         }

      }
   };
   private OnAirBtnClickListener mOnRearAirButtomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirCmd0x2E(52);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mRearLeftTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd0x2E(41);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd0x2E(42);
      }
   };
   private OnAirTemperatureUpDownClickListener mRearRightTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd0x2E(48);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd0x2E(49);
      }
   };
   private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCmd0x2E(50);
      }

      public void onClickUp() {
         this.this$0.sendAirCmd0x2E(51);
      }
   };
   private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirCmd0x2E(9);
      }

      public void onClickRight() {
         this.this$0.sendAirCmd0x2E(10);
      }
   };
   private OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
      }

      public void destroy() {
      }
   };
   private OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         StringBuilder var4;
         if (var3 != 1) {
            if (var3 == 2) {
               var4 = (new StringBuilder()).append("左右=");
               var2 += 7;
               Log.d("AMPL", var4.append(var2).toString());
               this.this$0.send0x84(1, var2);
            }
         } else {
            var4 = (new StringBuilder()).append("前后=");
            var2 = -var2 + 7;
            Log.d("AMPL", var4.append(var2).toString());
            this.this$0.send0x84(1, var2);
         }

      }
   };
   private OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            StringBuilder var4;
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     var4 = (new StringBuilder()).append("低音=");
                     var2 += 2;
                     Log.d("AMPL", var4.append(var2).toString());
                     this.this$0.send0x84(4, var2);
                  }
               } else {
                  var4 = (new StringBuilder()).append("中音=");
                  var2 += 2;
                  Log.d("AMPL", var4.append(var2).toString());
                  this.this$0.send0x84(6, var2);
               }
            } else {
               var4 = (new StringBuilder()).append("高音=");
               var2 += 2;
               Log.d("AMPL", var4.append(var2).toString());
               this.this$0.send0x84(5, var2);
            }
         } else {
            Log.d("AMPL", "音量=" + var2);
            this.this$0.send0x84(7, var2);
         }

      }
   };
   private OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      private void setDeviceBottomBtnEnvent(int var1) {
         UiMgr var2 = this.this$0;
         if (var2.getMsgMgr(var2.mContext).nowModeTag == 255) {
            switch (var1) {
               case 0:
                  this.this$0.send0x84(22, 0);
                  break;
               case 1:
                  this.this$0.send0x84(20, 0);
                  break;
               case 2:
                  if (this.this$0.cdSlowerTag) {
                     this.this$0.cdSlowerTag = false;
                     this.this$0.send0x84(25, 0);
                  } else {
                     this.this$0.cdSlowerTag = true;
                     this.this$0.send0x84(25, 1);
                  }
                  break;
               case 3:
                  this.this$0.send0x84(26, 0);
                  break;
               case 4:
                  if (this.this$0.cdFasterTag) {
                     this.this$0.cdFasterTag = false;
                     this.this$0.send0x84(24, 0);
                  } else {
                     this.this$0.cdFasterTag = true;
                     this.this$0.send0x84(24, 1);
                  }
                  break;
               case 5:
                  this.this$0.send0x84(19, 0);
                  break;
               case 6:
                  this.this$0.send0x84(21, 0);
                  break;
               case 7:
                  this.this$0.send0x84(16, 0);
                  break;
               case 8:
                  this.this$0.send0x84(17, 0);
            }
         } else {
            var2 = this.this$0;
            if (var2.getMsgMgr(var2.mContext).nowModeTag == 254) {
               switch (var1) {
                  case 0:
                     this.this$0.send0x84(73, 0);
                     break;
                  case 1:
                     if (this.this$0.dvdSlowerTag) {
                        this.this$0.dvdSlowerTag = false;
                        this.this$0.send0x84(76, 0);
                     } else {
                        this.this$0.dvdSlowerTag = true;
                        this.this$0.send0x84(76, 1);
                     }
                     break;
                  case 2:
                     this.this$0.send0x84(64, 0);
                     break;
                  case 3:
                     this.this$0.send0x84(65, 0);
                     break;
                  case 4:
                     this.this$0.send0x84(66, 0);
                     break;
                  case 5:
                     if (this.this$0.dvdFasterTag) {
                        this.this$0.dvdFasterTag = false;
                        this.this$0.send0x84(75, 0);
                     } else {
                        this.this$0.dvdFasterTag = true;
                        this.this$0.send0x84(75, 1);
                     }
                     break;
                  case 6:
                     this.this$0.send0x84(74, 0);
               }
            } else {
               var2 = this.this$0;
               if (var2.getMsgMgr(var2.mContext).nowModeTag == 251) {
                  if (var1 != 0) {
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 != 3) {
                              if (var1 == 4) {
                                 this.this$0.send0x84(37, 0);
                              }
                           } else {
                              this.this$0.send0x84(34, 0);
                           }
                        } else {
                           this.this$0.send0x84(36, 0);
                        }
                     } else {
                        this.this$0.send0x84(35, 1);
                     }
                  } else {
                     this.this$0.send0x84(38, 0);
                  }
               }
            }
         }

      }

      public void onClickBottomBtnItem(int var1) {
         this.setDeviceBottomBtnEnvent(var1);
      }
   };
   private OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_464_ampl")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_464_ampl", "_464_ampl_1")) {
               this.this$0.send0x84(12, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_464_ampl", "_464_ampl_4")) {
               this.this$0.send0x84(8, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_464_ampl", "_464_ampl_5")) {
               this.this$0.send0x84(10, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_464_settings_swc")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_464_settings_swc", "_464_settings_swc0")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -29, (byte)var3});
            }
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
      var2.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mRearLeftTempSetViewOnUpDownClickListenerCenter, this.mRearTempSetViewOnUpDownClickListenerCenter, this.mRearRightTempSetViewOnUpDownClickListenerCenter});
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirButtomBtnClickListener});
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(this.mContext);
      var3.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var3.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var3.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      this.getOriginalCarDevicePageUiSet(var1).setOnOriginalBottomBtnClickListeners(this.onOriginalBottomBtnClickListener);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirCmd0x2E(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 46, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 46, var2, 0});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
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
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
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

   public void send0x84(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
