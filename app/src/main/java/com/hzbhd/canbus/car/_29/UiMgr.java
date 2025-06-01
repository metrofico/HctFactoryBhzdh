package com.hzbhd.canbus.car._29;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private AirPageUiSet mAirPageUiSet;
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(3, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(1, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(2, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(0, var1);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(16);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(15);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      AirPageUiSet var2 = this.getAirUiSet(var1);
      this.mAirPageUiSet = var2;
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontLeft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      this.mAirPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      this.mAirPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(12);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(11);
         }
      });
      this.mAirPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirCommand(21);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirCommand(21);
         }
      });
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -7, (byte)(5 - var2), (byte)var3});
            } else if (var1 == 1) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var3, -1, -1});
               }
            } else if (var1 == 2) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var3, -1, -1});
               } else if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3, -1, -1});
               }
            } else if (var1 == 3) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 4, (byte)var3, -1, -1});
               } else if (var2 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 5, (byte)var3, -1, -1});
               }
            }

         }
      });
      var4.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 4 && var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var3});
            }

         }
      });
      var4.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (this.this$0.mMsgMgr != null) {
               this.this$0.mMsgMgr.updateSettingData(var1);
            }

         }
      });
      ParkPageUiSet var5 = this.getParkPageUiSet(var1);
      var5.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            boolean var3 = SharePreUtil.getBoolValue(this.this$0.mContext, "share_29_is_reversing", false);
            Log.i("ljq", "onClickItem: isRev = " + var3);
            byte var2;
            if (var3) {
               var2 = 6;
            } else {
               var2 = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -7, 1, (byte)(var1 + var2)});
         }
      });
      var5.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1, var5) {
         final UiMgr this$0;
         final Context val$context;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$parkPageUiSet = var3;
         }

         public void addViewToWindows() {
            boolean var2 = CommUtil.isBackCamera(this.val$context);
            boolean var1 = CommUtil.isPanoramic(this.val$context);
            SharePreUtil.setBoolValue(this.val$context, "share_29_is_reversing", var2);
            Log.i("_29_UiMgr", "onAddView: mIsBackCamera: " + var2 + ", mIsParkPanoramic: " + var1);
            if (this.val$parkPageUiSet.getPanoramicBtnList() != null) {
               this.val$parkPageUiSet.getPanoramicBtnList().clear();
            }

            ArrayList var3 = new ArrayList();
            if (var2) {
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_all"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_rear"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_left_rear"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_rear_right_rear"));
               var3.add(new ParkPageUiSet.Bean(0, "_29_parking_mode", ""));
            } else if (var1) {
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_all"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_front"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_left_front"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_front_right_front"));
               var3.add(new ParkPageUiSet.Bean(1, "", "hyundai_only_rear"));
            }

            this.val$parkPageUiSet.setPanoramicBtnList(var3);
         }
      });
      AmplifierPageUiSet var3 = this.getAmplifierPageUiSet(var1);
      var3.setSeekBarVolumeMax(this.getAmplifierMaxVolume());
      var3.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
            if (var3 != 1) {
               if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 6 + 10)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 6 + 10)});
            }

         }
      });
      var3.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
            if (var3 != 1) {
               if (var3 != 2) {
                  if (var3 != 3) {
                     if (var3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var2});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)(var2 + 6)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)(var2 + 6)});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)(var2 + 6)});
            }

         }
      });
   }

   private int getAmplifierMaxVolume() {
      int var1 = this.mDifferent;
      if (var1 != 0) {
         if (var1 == 2) {
            return 35;
         }

         if (var1 != 3) {
            return 40;
         }
      }

      return 30;
   }

   private void sendAirCommand(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendAirCommand(int var1, int var2) {
      String var3 = this.mAirPageUiSet.getFrontArea().getLineBtnAction()[var1][var2];
      var3.hashCode();
      var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -1470045433:
            if (var3.equals("front_defog")) {
               var4 = 0;
            }
            break;
         case 3106:
            if (var3.equals("ac")) {
               var4 = 1;
            }
            break;
         case 3005871:
            if (var3.equals("auto")) {
               var4 = 2;
            }
            break;
         case 3545755:
            if (var3.equals("sync")) {
               var4 = 3;
            }
            break;
         case 106858757:
            if (var3.equals("power")) {
               var4 = 4;
            }
            break;
         case 756225563:
            if (var3.equals("in_out_cycle")) {
               var4 = 5;
            }
      }

      switch (var4) {
         case 0:
            this.sendAirCommand(5);
            break;
         case 1:
            this.sendAirCommand(2);
            break;
         case 2:
            this.sendAirCommand(4);
            break;
         case 3:
            this.sendAirCommand(3);
            break;
         case 4:
            this.sendAirCommand(1);
            break;
         case 5:
            this.sendAirCommand(7);
      }

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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
