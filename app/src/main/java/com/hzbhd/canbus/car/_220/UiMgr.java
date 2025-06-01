package com.hzbhd.canbus.car._220;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   protected static final int CAR_ID_EV_19 = 15;
   protected static final int CAR_ID_GA3S = 1;
   protected static final int CAR_ID_GA3_13_14 = 0;
   protected static final int CAR_ID_GA4_18 = 10;
   protected static final int CAR_ID_GA6_14_16 = 4;
   protected static final int CAR_ID_GA6_19 = 18;
   protected static final int CAR_ID_GAXL_13_15 = 6;
   protected static final int CAR_ID_GE3_18 = 13;
   protected static final int CAR_ID_GM6_19 = 17;
   protected static final int CAR_ID_GM8_20 = 20;
   protected static final int CAR_ID_GS3_17 = 8;
   protected static final int CAR_ID_GS3_21 = 24;
   protected static final int CAR_ID_GS3高配_17 = 23;
   protected static final int CAR_ID_GS4_14_17 = 5;
   protected static final int CAR_ID_GS4_18 = 12;
   protected static final int CAR_ID_GS4_19 = 14;
   protected static final int CAR_ID_GS4_20 = 19;
   protected static final int CAR_ID_GS4_22 = 27;
   protected static final int CAR_ID_GS4_HYBRID_18 = 11;
   protected static final int CAR_ID_GS4_HYBRID_20 = 26;
   protected static final int CAR_ID_GS5_12_14 = 2;
   protected static final int CAR_ID_GS5_20 = 22;
   protected static final int CAR_ID_GS5_SUPER_14_13 = 3;
   protected static final int CAR_ID_GS7_17 = 9;
   protected static final int CAR_ID_GS8_17 = 7;
   protected static final int CAR_ID_GS8_19 = 16;
   protected static final int CAR_ID_GS8_20 = 25;
   protected static final int CAR_ID_电动出租车 = 21;
   private int EachID;
   private int SupportPanoramicView = 0;
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private int mDifferent;
   private FrontArea mFrontArea;
   private MsgMgr mMsgMgr;
   private MyPanoramicView mMyPanoramicView;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirCommand(31);
               }
            } else {
               this.this$0.sendAirCommand(23);
            }
         } else {
            this.this$0.sendAirCommand(6);
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
            this.this$0.sendAirCommand(27);
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
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     this.this$0.sendAirCommand(32);
                  }
               } else {
                  this.this$0.sendAirCommand(1);
               }
            } else {
               this.this$0.sendAirCommand(30);
            }
         } else {
            this.this$0.sendAirCommand(21);
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
            this.this$0.sendAirCommand(3);
         }

      }
   };
   private OnSettingItemSeekbarSelectListener mOnSeekBarChangeListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
         String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var6.hashCode();
         var2 = var6.hashCode();
         byte var5 = -1;
         switch (var2) {
            case -1533139633:
               if (var6.equals("_220_Ambient_light_brightness")) {
                  var5 = 0;
               }
               break;
            case -1054915651:
               if (var6.equals("_220_Heat_Preservation")) {
                  var5 = 1;
               }
               break;
            case -713810043:
               if (var6.equals("_220_Ambient_light_Color")) {
                  var5 = 2;
               }
               break;
            case 600351815:
               if (var6.equals("charging_set_2")) {
                  var5 = 3;
               }
               break;
            case 600351816:
               if (var6.equals("charging_set_3")) {
                  var5 = 4;
               }
               break;
            case 600351817:
               if (var6.equals("charging_set_4")) {
                  var5 = 5;
               }
               break;
            case 600351818:
               if (var6.equals("charging_set_5")) {
                  var5 = 6;
               }
               break;
            case 670078931:
               if (var6.equals("_220_remote_poweron_time")) {
                  var5 = 7;
               }
               break;
            case 1015585429:
               if (var6.equals("_220_remote_start_time")) {
                  var5 = 8;
               }
         }

         switch (var5) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte)((byte)var3 - 1)});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -87, 6, (byte)var3});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 42, (byte)((byte)var3 - 1)});
               break;
            case 3:
               MsgMgr.mNewEnergyStartTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyStartTime, (byte)var3, 6, 5);
               this.this$0.sndNewEnergyCmdDatas();
               break;
            case 4:
               MsgMgr.mNewEnergyStartTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyStartTime, (byte)var3, 0, 6);
               this.this$0.sndNewEnergyCmdDatas();
               break;
            case 5:
               MsgMgr.mNewEnergyEndTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyEndTime, (byte)var3, 6, 5);
               this.this$0.sndNewEnergyCmdDatas();
               break;
            case 6:
               MsgMgr.mNewEnergyEndTime = DataHandleUtils.setIntFromByteWithBit(MsgMgr.mNewEnergyEndTime, (byte)var3, 0, 6);
               this.this$0.sndNewEnergyCmdDatas();
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
         }

      }
   };
   private OnSettingItemClickListener mOnSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var3.hashCode();
         var2 = var3.hashCode();
         byte var4 = -1;
         switch (var2) {
            case -1536212359:
               if (var3.equals("_220_right_seat_heat")) {
                  var4 = 0;
               }
               break;
            case -919962240:
               if (var3.equals("_23_enter_panoramic")) {
                  var4 = 1;
               }
               break;
            case 466028160:
               if (var3.equals("_220_left_seat_heat")) {
                  var4 = 2;
               }
         }

         switch (var4) {
            case 0:
               (new Thread(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void run() {
                     super.run();
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, 2});

                     try {
                        sleep(50L);
                     } catch (InterruptedException var2) {
                        var2.printStackTrace();
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, 1});
                  }
               }).start();
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -124, -1});
               UiMgr var5 = this.this$0;
               var5.getMsgMgr(var5.mContext).forceReverse(this.this$0.mContext, true);
               break;
            case 2:
               (new Thread(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void run() {
                     super.run();
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, 2});

                     try {
                        sleep(50L);
                     } catch (InterruptedException var2) {
                        var2.printStackTrace();
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, 1});
                  }
               }).start();
         }

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
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(29);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(28);
      }
   };
   private MsgMgr msgMgr;
   private SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.airPageUiSet = this.getAirUiSet(var1);
      this.settingPageUiSet = this.getSettingUiSet(var1);
      this.mFrontArea = this.airPageUiSet.getFrontArea();
      this.setIsHaveRearAir();
      this.mDifferent = this.getCurrentCarId();
      this.EachID = this.getEachId();
      this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      this.airPageUiSet.getRearArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirCommand(17);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirCommand(17);
         }
      });
      this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mOnUpDownClickListenerRear, null});
      this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 == 0) {
               this.this$0.sendAirCommand(2);
            }

         }
      }});
      this.mFrontArea.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
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
      ParkPageUiSet var5 = this.getParkPageUiSet(var1);
      int var2 = this.mDifferent;
      boolean var3;
      if (var2 != 7 && var2 != 7 && var2 != 16 && var2 != 16 && var2 != 9 && var2 != 6 && var2 != 5 && var2 != 11 && var2 != 12 && var2 != 14 && var2 != 19) {
         if (this.getCurrentCarId() == 3) {
            var5.setShowPanoramic(false);
            if (SharePreUtil.getIntValue(var1, "_220_SAVE_360", 0) == 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setShowRadar(var3);
            if (SharePreUtil.getIntValue(var1, "_220_SAVE_360", 0) == 1) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setShowCusPanoramicView(var3);
         } else {
            var5.setShowCusPanoramicView(false);
            var5.setShowPanoramic(false);
            var5.setShowRadar(true);
         }
      } else {
         if (SharePreUtil.getIntValue(var1, "_220_SAVE_360", 0) == 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setShowRadar(var3);
         if (SharePreUtil.getIntValue(var1, "_220_SAVE_360", 0) == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setShowPanoramic(var3);
         var5.setShowCusPanoramicView(false);
      }

      if (this.isHaveCam360()) {
         this.mMsgMgr.init360Disp(this.mContext);
      }

      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSeekbarSelectListener(this.mOnSeekBarChangeListener);
      var4.setOnSettingItemClickListener(this.mOnSettingItemClickListener);
      var4.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 0 && var2 == 12) {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, 12, 0});
               Context var3 = this.val$context;
               Toast.makeText(var3, var3.getText(2131769831), 0).show();
            }

         }
      });
      var5.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var5) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void addViewToWindows() {
            if (this.this$0.isHaveCam360()) {
               if (this.val$parkPageUiSet.getPanoramicBtnList() != null) {
                  this.val$parkPageUiSet.getPanoramicBtnList().clear();
               }

               new ArrayList();
               int var1 = this.this$0.mDifferent;
               Object var3 = null;
               List var2;
               if (var1 != 5 && this.this$0.mDifferent != 12 && this.this$0.mDifferent != 14 && this.this$0.mDifferent != 19 && this.this$0.mDifferent != 10 && this.this$0.mDifferent != 6) {
                  this.val$parkPageUiSet.setShowPanoramic(false);
                  var2 = null;
               } else {
                  var2 = this.this$0.initCamera360Btns();
               }

               if (SharePreUtil.getIntValue(this.this$0.mContext, "_220_SAVE_360", 0) == 0) {
                  this.val$parkPageUiSet.setShowPanoramic(false);
                  var2 = (List)var3;
               } else if (this.this$0.mDifferent != 3) {
                  this.val$parkPageUiSet.setShowPanoramic(true);
               }

               this.val$parkPageUiSet.setPanoramicBtnList(var2);
            }
         }
      });
      if (this.getCurrentCarId() == 9 || this.getCurrentCarId() == 7 || this.getCurrentCarId() == 16) {
         var5.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onTouchItem(MotionEvent var1) {
               UiMgr var2 = this.this$0;
               if (var2.isInThrumpchiRightTouchArea(var2.mContext, var1)) {
                  var2 = this.this$0;
                  var2.sendThrumpchiRightTouchCoord(var2.mContext, var1);
               } else {
                  var2 = this.this$0;
                  var2.sendTrumpchiBottomBtn1(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBottomBtn2(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBottomBtn3(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBtnUp(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBtnDn(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBtnLeft(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBtnRight(var2.mContext, var1);
                  var2 = this.this$0;
                  var2.sendTrumpchiBtnOk(var2.mContext, var1);
               }

            }
         });
      }

      var5.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var5) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var3 = ((ParkPageUiSet.Bean)this.val$parkPageUiSet.getPanoramicBtnList().get(var1)).getTitleSrn();
            var3.hashCode();
            int var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1875869257:
                  if (var3.equals("_194_front")) {
                     var4 = 0;
                  }
                  break;
               case -1865062998:
                  if (var3.equals("_194_right")) {
                     var4 = 1;
                  }
                  break;
               case 216558544:
                  if (var3.equals("_194_exit")) {
                     var4 = 2;
                  }
                  break;
               case 216748729:
                  if (var3.equals("_194_left")) {
                     var4 = 3;
                  }
                  break;
               case 216927318:
                  if (var3.equals("_194_rear")) {
                     var4 = 4;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 1, -1});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 8, -1});
                  break;
               case 2:
                  if (this.this$0.mDifferent == 6) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -124, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -89, 0, -1});
                  }
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 7, -1});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -89, 2, -1});
            }

         }
      });
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            this.this$0.setItemsCmd(var5, var3);
         }
      });
      var4.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (!var3.equals("_220_energy_charge_right_now")) {
               if (var3.equals("reset")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, -1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -87, 1, 1});
            }

         }
      });
      if (this.getCurrentCarId() == 5 || this.getCurrentCarId() == 10) {
         var5.setShowCusPanoramicView(false);
         var5.setShowPanoramic(true);
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private List initCamera360Btns() {
      ArrayList var1 = new ArrayList();
      if (this.getCurrentCarId() != 5 && this.getCurrentCarId() != 12 && this.getCurrentCarId() != 10) {
         if (this.getCurrentCarId() != 14 && this.getCurrentCarId() != 19) {
            if (this.getCurrentCarId() == 6) {
               var1.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
            } else {
               var1.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
            }
         } else {
            var1.add(new ParkPageUiSet.Bean(0, "_194_front", ""));
            var1.add(new ParkPageUiSet.Bean(0, "_194_rear", ""));
            var1.add(new ParkPageUiSet.Bean(0, "_194_left", ""));
            var1.add(new ParkPageUiSet.Bean(0, "_194_right", ""));
            var1.add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
         }
      } else {
         var1.add(new ParkPageUiSet.Bean(0, "_194_front", ""));
         var1.add(new ParkPageUiSet.Bean(0, "_194_rear", ""));
         var1.add(new ParkPageUiSet.Bean(0, "_194_left", ""));
         var1.add(new ParkPageUiSet.Bean(0, "_194_right", ""));
      }

      return var1;
   }

   private boolean isHaveCam360() {
      int var1 = this.getCurrentCarId();
      if (var1 != 3 && var1 != 9 && var1 != 14 && var1 != 16 && var1 != 19 && var1 != 22 && var1 != 5 && var1 != 6 && var1 != 7 && var1 != 11 && var1 != 12) {
         switch (var1) {
            case 25:
            case 26:
            case 27:
               break;
            default:
               return false;
         }
      }

      return true;
   }

   private boolean isInThrumpchiRightTouchArea(Context var1, MotionEvent var2) {
      float var3 = (float)((int)var2.getX());
      var2.getY();
      return var3 >= (float)((int)((float)CommUtil.getDimenByResId(var1, "dp1024")) * 7 / 10);
   }

   private void no_new_energy() {
      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_energy_set"});
   }

   private int plusVal(int var1) {
      return var1 + 1;
   }

   private void resetCamera360Items() {
      if (this.getCurrentCarId() != 6) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"support_panorama"});
      }

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
            CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)this.val$cmd, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -88, (byte)this.val$cmd, 0});
         }
      }).start();
   }

   private void sendThrumpchiRightTouchCoord(Context var1, MotionEvent var2) {
      float var5 = (float)((int)var2.getX());
      float var6 = (float)((int)var2.getY());
      float var4 = (float)CommUtil.getDimenByResId(var1, "dp1024");
      float var7 = (float)CommUtil.getDimenByResId(var1, "dp600");
      float var3 = 7.0F * var4 / 10.0F;
      int var8 = (int)(1280.0F / var4);
      int var9 = (int)(720.0F / var7);
      var8 = (int)((var5 - var3) * (float)var8 + 900.0F);
      int var11 = (int)(var6 * (float)var9);
      var9 = var11 / 16;
      int var10 = var8 / 256;
      if (var2.getAction() == 1) {
         Util.reportCanbusInfo(new byte[]{22, -55, 2, (byte)var9, (byte)(var11 % 16 << 4 | var10), (byte)(var8 % 256)});
      }

   }

   private void sendTrumpchiBottomBtn1(Context var1, MotionEvent var2) {
      float var5 = (float)((int)var2.getX());
      float var4 = (float)((int)var2.getY());
      float var3 = (float)CommUtil.getDimenByResId(var1, "dp239");
      float var6 = (float)CommUtil.getDimenByResId(var1, "dp530");
      if (var5 >= 0.0F && var5 <= var3 && var4 >= var6 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 6, 0, 0});
      }

   }

   private void sendTrumpchiBottomBtn2(Context var1, MotionEvent var2) {
      float var3 = (float)((int)var2.getX());
      float var5 = (float)((int)var2.getY());
      float var4 = (float)CommUtil.getDimenByResId(var1, "dp239");
      float var6 = (float)CommUtil.getDimenByResId(var1, "dp530");
      if (var3 > var4 && var3 <= var4 * 2.0F && var5 >= var6 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 7, 0, 0});
      }

   }

   private void sendTrumpchiBottomBtn3(Context var1, MotionEvent var2) {
      float var3 = (float)((int)var2.getX());
      float var4 = (float)((int)var2.getY());
      float var5 = (float)CommUtil.getDimenByResId(var1, "dp239");
      float var6 = (float)CommUtil.getDimenByResId(var1, "dp530");
      if (var3 > 2.0F * var5 && var3 <= var5 * 3.0F && var4 >= var6 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 8, 0, 0});
      }

   }

   private void sendTrumpchiBtnDn(Context var1, MotionEvent var2) {
      var2.getX();
      float var3 = (float)((int)var2.getY());
      float var4 = (float)CommUtil.getDimenByResId(var1, "dp366");
      float var5 = (float)CommUtil.getDimenByResId(var1, "dp485");
      if (var3 > var4 && var3 <= var5 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 2, 0, 0});
      }

   }

   private void sendTrumpchiBtnLeft(Context var1, MotionEvent var2) {
      float var5 = (float)((int)var2.getX());
      float var3 = (float)((int)var2.getY());
      float var6 = (float)CommUtil.getDimenByResId(var1, "dp256");
      float var7 = (float)CommUtil.getDimenByResId(var1, "dp350");
      float var4 = (float)CommUtil.getDimenByResId(var1, "dp282");
      if (var3 > var6 && var3 <= var7 && var5 > 0.0F && var5 <= var4 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 3, 0, 0});
      }

   }

   private void sendTrumpchiBtnOk(Context var1, MotionEvent var2) {
      float var4 = (float)((int)var2.getX());
      float var3 = (float)((int)var2.getY());
      float var7 = (float)CommUtil.getDimenByResId(var1, "dp256");
      float var6 = (float)CommUtil.getDimenByResId(var1, "dp366");
      float var8 = (float)CommUtil.getDimenByResId(var1, "dp282");
      float var5 = (float)CommUtil.getDimenByResId(var1, "dp427");
      if (var3 > var7 && var3 <= var6 && var4 > var8 && var4 <= var5 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 5, 0, 0});
      }

   }

   private void sendTrumpchiBtnRight(Context var1, MotionEvent var2) {
      float var6 = (float)((int)var2.getX());
      float var5 = (float)((int)var2.getY());
      float var8 = (float)CommUtil.getDimenByResId(var1, "dp256");
      float var7 = (float)CommUtil.getDimenByResId(var1, "dp350");
      float var3 = (float)CommUtil.getDimenByResId(var1, "dp427");
      float var4 = (float)CommUtil.getDimenByResId(var1, "dp716");
      if (var5 > var8 && var5 <= var7 && var6 > var3 && var6 <= var4 && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 4, 0, 0});
      }

   }

   private void sendTrumpchiBtnUp(Context var1, MotionEvent var2) {
      var2.getX();
      if ((float)((int)var2.getY()) <= (float)CommUtil.getDimenByResId(var1, "dp256") && var2.getAction() == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 1, 0, 0});
      }

   }

   private void setCarIdCmd() {
      switch (this.EachID) {
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 8:
            this.no_new_energy();
         case 7:
      }

      if (this.EachID != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_P_mode_exit", "_220_Turn_signals_activate_panorama"});
      }

      if (this.EachID != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Distraction_Reminder", "_220_Fatigue_Testing", "_220_Ventilate_While_Smoking", "_220_Decrease_The_Volume_During_A_Call", "_220_Sight_Bright_Screen", "_220_Gestures_To_Cut_The_Song", "_220_Convenient_For_Car"});
      }

      int var1 = this.EachID;
      if (var1 == 3 || var1 == 7 || var1 == 8 || var1 == 0) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_language_settings"});
      }

      var1 = this.EachID;
      if (var1 == 7 || var1 == 0) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_seat_set"});
      }

      if (this.EachID != 6) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_seat_welcome_light"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_smart_key_automatic_identification_seat"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_manually_adjustable_exterior_mirror_angle"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_automatically_adjusting_the_angle_of_the_outer_mirror"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Key_forget_reminder"});
      }

      if (this.EachID != 8) {
         this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"car_info"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Auto_Close_Windows"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Wiper_maintenance_mode"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Delayed_headlight_off"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Memorize_the_current_driving_mode"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Unlock_the_ventilation"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Air_conditioning_self_drying"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_frontrightdoor"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_rearrightdoor"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_frontleftdoor"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_rearleftdoor"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Skylight"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Sunshade"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_ElectricTailgateFunction"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_ElectricTailgate"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Electric_Tailgate_Open_Position"});
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_220_car_control"});
      }

      if (this.EachID != 2) {
         this.removeDriveDataPagesByHeadTitles(this.mContext, new String[]{"_220_add_function_page1"});
      }

      var1 = this.EachID;
      if (var1 == 6 || var1 == 0) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_steering_modes"});
      }

      var1 = this.EachID;
      if (var1 != 4 && var1 != 5 && var1 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_speed_lock"});
      }

      var1 = this.EachID;
      if (var1 == 2 || var1 == 5 || var1 == 8 || var1 == 0) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_remote_left_front_window_and_skylight"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_front_wiper_maintenance_functions"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_i_went_home_with"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_daytime_running_lights"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_auto_light_sensitivity"});
      }

      var1 = this.EachID;
      if (var1 == 1 || var1 == 3 || var1 == 0) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_left_seat_heat"});
      }

      var1 = this.EachID;
      if (var1 == 1 || var1 == 3 || var1 == 0 || var1 == 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_right_seat_heat"});
      }

      var1 = this.EachID;
      if (var1 == 5 || var1 == 0) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_automatic_folding_exterior_rear_view_mirror"});
      }

      if (this.EachID == 0) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_unlock_lock_tone"});
      }

      var1 = this.EachID;
      if (var1 == 0 || var1 == 4) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_intelligent_active_lock"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_smart_unlock_initiative"});
      }

      var1 = this.EachID;
      if (var1 == 0 || var1 == 4 || var1 == 5) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_intelligent_welcome_light_function"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Automatic_Wiper_Function"});
      }

      var1 = this.EachID;
      if (var1 != 1 && var1 != 3) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_ambient_light_control"});
      }

      var1 = this.EachID;
      if (var1 == 0 || var1 == 3 || var1 == 4 || var1 == 5) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_air_quality_sensor"});
      }

      var1 = this.EachID;
      if (var1 == 0 || var1 == 1 || var1 == 4) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_automatic_mode_wind"});
      }

      var1 = this.EachID;
      if (var1 != 1 && var1 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_open_trunk_induction"});
      }

      if (this.EachID != 1) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Ambient_light_brightness"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Ambient_light_Color"});
      }

      var1 = this.EachID;
      if (var1 != 3 && var1 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Intelligent_high_beam"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Active_brake_assist"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Cruise_mode"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Front_collision_warning"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Front_collision_warning_distance"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Lane_assist_switch"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Lane_assist"});
      }

      var1 = this.EachID;
      if (var1 != 3 && var1 != 6 && var1 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Wireless_charging"});
      }

      if (this.EachID != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_220_Low_speed_beep"});
      }

      var1 = this.EachID;
      if (var1 != 2 && var1 != 8) {
         this.removeMainPrjBtnByName(this.mContext, "drive_data");
      }

      switch (this.getCurrentCarId()) {
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 16:
            this.no_new_energy();
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 1});
            this.no_new_energy();
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 2});
            break;
         case 12:
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 4});
            this.no_new_energy();
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 3});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 5});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 6});
            this.no_new_energy();
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 7});
            this.no_new_energy();
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 8});
            this.no_new_energy();
      }

   }

   private void setIsHaveRearAir() {
      if (this.getCurrentCarId() != 7 && this.getCurrentCarId() != 16 && this.getCurrentCarId() != 25 && this.getCurrentCarId() != 17) {
         int var1 = this.EachID;
         if (var1 != 1 && var1 != 6) {
            this.airPageUiSet.setHaveRearArea(false);
            return;
         }
      }

      this.airPageUiSet.setHaveRearArea(true);
   }

   private void setItemsCmd(String var1, int var2) {
      var1.hashCode();
      int var4 = var1.hashCode();
      boolean var8 = false;
      boolean var9 = false;
      boolean var12 = false;
      boolean var10 = false;
      boolean var11 = false;
      boolean var5 = false;
      boolean var6 = false;
      boolean var7 = false;
      boolean var13 = true;
      int var3 = -1;
      switch (var4) {
         case -2124895205:
            if (var1.equals("_220_Delayed_headlight_off")) {
               var3 = 0;
            }
            break;
         case -2121592808:
            if (var1.equals("_220_rearrightdoor")) {
               var3 = 1;
            }
            break;
         case -2101623002:
            if (var1.equals("_220_speeding_alert")) {
               var3 = 2;
            }
            break;
         case -2026872673:
            if (var1.equals("_220_Cruise_mode")) {
               var3 = 3;
            }
            break;
         case -2003039537:
            if (var1.equals("_220_Sunshade")) {
               var3 = 4;
            }
            break;
         case -2000828773:
            if (var1.equals("_220_auto_when_the_compressor_status")) {
               var3 = 5;
            }
            break;
         case -1995717173:
            if (var1.equals("_220_Front_collision_warning")) {
               var3 = 6;
            }
            break;
         case -1979650045:
            if (var1.equals("_220_Skylight")) {
               var3 = 7;
            }
            break;
         case -1963353434:
            if (var1.equals("_220_language")) {
               var3 = 8;
            }
            break;
         case -1905420321:
            if (var1.equals("_220_Low_speed_beep")) {
               var3 = 9;
            }
            break;
         case -1901162969:
            if (var1.equals("_220_rearleftdoor")) {
               var3 = 10;
            }
            break;
         case -1888064510:
            if (var1.equals("_220_automatic_mode_wind")) {
               var3 = 11;
            }
            break;
         case -1872477900:
            if (var1.equals("_220_auto_light_sensitivity")) {
               var3 = 12;
            }
            break;
         case -1807487191:
            if (var1.equals("_220_remote_left_front_window_and_skylight")) {
               var3 = 13;
            }
            break;
         case -1760066865:
            if (var1.equals("_220_remote_unlock")) {
               var3 = 14;
            }
            break;
         case -1720286544:
            if (var1.equals("_220_steering_modes")) {
               var3 = 15;
            }
            break;
         case -1620441994:
            if (var1.equals("_220_Key_forget_reminder")) {
               var3 = 16;
            }
            break;
         case -1536212359:
            if (var1.equals("_220_right_seat_heat")) {
               var3 = 17;
            }
            break;
         case -1498096753:
            if (var1.equals("_220_energy_recycle_i_pedal")) {
               var3 = 18;
            }
            break;
         case -1459836163:
            if (var1.equals("_220_P_mode_exit")) {
               var3 = 19;
            }
            break;
         case -1409279546:
            if (var1.equals("_220_auto_unlock")) {
               var3 = 20;
            }
            break;
         case -1310751467:
            if (var1.equals("_220_Air_conditioning_self_drying")) {
               var3 = 21;
            }
            break;
         case -1253907986:
            if (var1.equals("_220_Lane_assist")) {
               var3 = 22;
            }
            break;
         case -1251555340:
            if (var1.equals("_220_intelligent_welcome_light_function")) {
               var3 = 23;
            }
            break;
         case -1237574730:
            if (var1.equals("_220_Intelligent_high_beam")) {
               var3 = 24;
            }
            break;
         case -1226835875:
            if (var1.equals("_220_air_quality_sensor")) {
               var3 = 25;
            }
            break;
         case -1064418657:
            if (var1.equals("_220_i_went_home_with")) {
               var3 = 26;
            }
            break;
         case -1003140119:
            if (var1.equals("_220_Front_collision_warning_distance")) {
               var3 = 27;
            }
            break;
         case -754233788:
            if (var1.equals("_220_Wireless_charging")) {
               var3 = 28;
            }
            break;
         case -624405314:
            if (var1.equals("_220_ElectricTailgateFunction")) {
               var3 = 29;
            }
            break;
         case -511308010:
            if (var1.equals("_220_Decrease_The_Volume_During_A_Call")) {
               var3 = 30;
            }
            break;
         case -500437698:
            if (var1.equals("_220_smart_key_automatic_identification_seat")) {
               var3 = 31;
            }
            break;
         case -483459599:
            if (var1.equals("_220_speed_lock")) {
               var3 = 32;
            }
            break;
         case -369497201:
            if (var1.equals("_220_and_left_auxiliary_line")) {
               var3 = 33;
            }
            break;
         case -364381731:
            if (var1.equals("_220_Distraction_Reminder")) {
               var3 = 34;
            }
            break;
         case -310817255:
            if (var1.equals("_220_unlock_lock_tone")) {
               var3 = 35;
            }
            break;
         case -206769063:
            if (var1.equals("_220_automatic_heating_passenger_seat")) {
               var3 = 36;
            }
            break;
         case -193573745:
            if (var1.equals("_220_frontrightdoor")) {
               var3 = 37;
            }
            break;
         case -79772807:
            if (var1.equals("_220_Turn_signals_activate_panorama")) {
               var3 = 38;
            }
            break;
         case -70694761:
            if (var1.equals("_220_front_wiper_maintenance_functions")) {
               var3 = 39;
            }
            break;
         case -37853488:
            if (var1.equals("_220_frontleftdoor")) {
               var3 = 40;
            }
            break;
         case 75114088:
            if (var1.equals("_220_Active_brake_assist")) {
               var3 = 41;
            }
            break;
         case 111379720:
            if (var1.equals("_220_energy_recycle_lev")) {
               var3 = 42;
            }
            break;
         case 342065334:
            if (var1.equals("_220_negative_ion_mode")) {
               var3 = 43;
            }
            break;
         case 389223571:
            if (var1.equals("_220_Intelligent_Perception_System")) {
               var3 = 44;
            }
            break;
         case 460497606:
            if (var1.equals("_220_ElectricTailgate")) {
               var3 = 45;
            }
            break;
         case 466028160:
            if (var1.equals("_220_left_seat_heat")) {
               var3 = 46;
            }
            break;
         case 489704252:
            if (var1.equals("_220_manually_adjustable_exterior_mirror_angle")) {
               var3 = 47;
            }
            break;
         case 561990276:
            if (var1.equals("_220_Memorize_the_current_driving_mode")) {
               var3 = 48;
            }
            break;
         case 600351815:
            if (var1.equals("charging_set_2")) {
               var3 = 49;
            }
            break;
         case 600351819:
            if (var1.equals("charging_set_6")) {
               var3 = 50;
            }
            break;
         case 615606949:
            if (var1.equals("_220_Lane_assist_switch")) {
               var3 = 51;
            }
            break;
         case 766972267:
            if (var1.equals("_220_Ventilate_While_Smoking")) {
               var3 = 52;
            }
            break;
         case 785457789:
            if (var1.equals("_220_Gestures_To_Cut_The_Song")) {
               var3 = 53;
            }
            break;
         case 816109113:
            if (var1.equals("loop_set_1")) {
               var3 = 54;
            }
            break;
         case 816109114:
            if (var1.equals("loop_set_2")) {
               var3 = 55;
            }
            break;
         case 816109115:
            if (var1.equals("loop_set_3")) {
               var3 = 56;
            }
            break;
         case 816109116:
            if (var1.equals("loop_set_4")) {
               var3 = 57;
            }
            break;
         case 816109117:
            if (var1.equals("loop_set_5")) {
               var3 = 58;
            }
            break;
         case 816109118:
            if (var1.equals("loop_set_6")) {
               var3 = 59;
            }
            break;
         case 816109119:
            if (var1.equals("loop_set_7")) {
               var3 = 60;
            }
            break;
         case 837090372:
            if (var1.equals("_220_fog_lights_steering_assist")) {
               var3 = 61;
            }
            break;
         case 896830732:
            if (var1.equals("_220_Convenient_For_Car")) {
               var3 = 62;
            }
            break;
         case 1031618892:
            if (var1.equals("_220_driver_mode")) {
               var3 = 63;
            }
            break;
         case 1186387950:
            if (var1.equals("_220_intelligent_active_lock")) {
               var3 = 64;
            }
            break;
         case 1253609831:
            if (var1.equals("_220_airconditioned_comfort_curve_settings")) {
               var3 = 65;
            }
            break;
         case 1304098434:
            if (var1.equals("_220_Unlock_the_ventilation")) {
               var3 = 66;
            }
            break;
         case 1364465153:
            if (var1.equals("_220_Sight_Bright_Screen")) {
               var3 = 67;
            }
            break;
         case 1411310665:
            if (var1.equals("_220_automatically_adjusting_the_angle_of_the_outer_mirror")) {
               var3 = 68;
            }
            break;
         case 1419739241:
            if (var1.equals("_220_the_instrument_cluster_alarm_volume")) {
               var3 = 69;
            }
            break;
         case 1483466642:
            if (var1.equals("_220_automatic_driving_seat_heating")) {
               var3 = 70;
            }
            break;
         case 1518455214:
            if (var1.equals("_220_Electric_Tailgate_Open_Position")) {
               var3 = 71;
            }
            break;
         case 1596681492:
            if (var1.equals("_220_and_the_right_auxiliary_line")) {
               var3 = 72;
            }
            break;
         case 1661375245:
            if (var1.equals("_220_seat_welcome_light")) {
               var3 = 73;
            }
            break;
         case 1671878986:
            if (var1.equals("_220_Fatigue_Testing")) {
               var3 = 74;
            }
            break;
         case 1700563150:
            if (var1.equals("_220_Automatic_Wiper_Function")) {
               var3 = 75;
            }
            break;
         case 1718316607:
            if (var1.equals("_220_automatic_folding_exterior_rear_view_mirror")) {
               var3 = 76;
            }
            break;
         case 1752510671:
            if (var1.equals("_220_open_trunk_induction")) {
               var3 = 77;
            }
            break;
         case 1859527651:
            if (var1.equals("_220_smart_unlock_initiative")) {
               var3 = 78;
            }
            break;
         case 1915332337:
            if (var1.equals("_220_Wiper_maintenance_mode")) {
               var3 = 79;
            }
            break;
         case 1943420554:
            if (var1.equals("_220_rear_wiper_automatic_reverse_function")) {
               var3 = 80;
            }
            break;
         case 2028924223:
            if (var1.equals("_220_ambient_light_control")) {
               var3 = 81;
            }
            break;
         case 2076384769:
            if (var1.equals("_220_daytime_running_lights")) {
               var3 = 82;
            }
            break;
         case 2134220680:
            if (var1.equals("_220_auto_outer_loop_control")) {
               var3 = 83;
            }
            break;
         case 2143978074:
            if (var1.equals("_220_Auto_Close_Windows")) {
               var3 = 84;
            }
      }

      switch (var3) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte)this.swapVal(var2)});
            break;
         case 1:
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 2});
            } else if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 3});
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 13});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 2, 14});
            }
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var2});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 46, (byte)this.swapVal(var2)});
            break;
         case 4:
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 5});
            } else if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 6});
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 17});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, 18});
            }
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)this.swapVal(var2)});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte)this.swapVal(var2)});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -120, 5, (byte)var2});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)this.plusVal(var2)});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte)this.plusVal(var2)});
            break;
         case 10:
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 2});
            } else if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 3});
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 13});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 4, 14});
            }
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)this.plusVal(var2)});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)this.plusVal(var2)});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)this.swapVal(var2)});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)this.swapVal(var2)});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)this.plusVal(var2)});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte)this.swapVal(var2)});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var2});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, 4, (byte)this.swapVal(var2)});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 57, (byte)this.swapVal(var2)});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)this.swapVal(var2)});
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 60, (byte)this.swapVal(var2)});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte)this.plusVal(var2)});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte)this.swapVal(var2)});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 43, (byte)this.swapVal(var2)});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)this.plusVal(var2)});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)this.plusVal(var2)});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte)this.plusVal(var2)});
            break;
         case 28:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 44, (byte)this.swapVal(var2)});
            break;
         case 29:
            CanbusMsgSender.sendMsg(new byte[]{22, -120, 6, (byte)(var2 + 1)});
            break;
         case 30:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 5, (byte)this.swapVal(var2)});
            break;
         case 31:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)this.swapVal(var2)});
            break;
         case 32:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)this.swapVal(var2)});
            break;
         case 33:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)this.swapVal(var2)});
            break;
         case 34:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, (byte)this.swapVal(var2)});
            break;
         case 35:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)this.swapVal(var2)});
            break;
         case 36:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)this.swapVal(var2)});
            break;
         case 37:
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 2});
            } else if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 3});
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 13});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 1, 14});
            }
            break;
         case 38:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 58, (byte)this.swapVal(var2)});
            break;
         case 39:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)this.swapVal(var2)});
            break;
         case 40:
            if (var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 2});
            } else if (var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 3});
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 13});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -120, 3, 14});
            }
            break;
         case 41:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 45, (byte)this.swapVal(var2)});
            break;
         case 42:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, 3, (byte)var2});
            break;
         case 43:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)this.swapVal(var2)});
            break;
         case 44:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, (byte)this.swapVal(var2)});
            break;
         case 45:
            CanbusMsgSender.sendMsg(new byte[]{22, -120, 7, (byte)(var2 + 1)});
            break;
         case 46:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var2});
            break;
         case 47:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte)this.swapVal(var2)});
            break;
         case 48:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 56, (byte)this.swapVal(var2)});
            break;
         case 49:
            if (this.isHaveCam360()) {
               SharePreUtil.setIntValue(this.mContext, "_220_SAVE_360", var2);
               this.mMsgMgr.init360Disp(this.mContext);
               ParkPageUiSet var14 = this.getParkPageUiSet(this.mContext);
               if (var2 == 0) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               var14.setShowRadar(var5);
               if (var2 == 1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               var14.setShowPanoramic(var5);
               if (this.mDifferent == 3) {
                  var5 = var6;
                  if (var2 == 1) {
                     var5 = true;
                  }

                  var14.setShowCusPanoramicView(var5);
               }
            }
            break;
         case 50:
            var3 = MsgMgr.mNewEnergyData;
            if (var2 == 1) {
               var5 = var13;
            } else {
               var5 = false;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 0, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 51:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)this.swapVal(var2)});
            break;
         case 52:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, (byte)this.swapVal(var2)});
            break;
         case 53:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 7, (byte)this.swapVal(var2)});
            break;
         case 54:
            var3 = MsgMgr.mNewEnergyData;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 1, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 55:
            var3 = MsgMgr.mNewEnergyData;
            var5 = var11;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 2, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 56:
            var3 = MsgMgr.mNewEnergyData;
            var5 = var10;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 3, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 57:
            var3 = MsgMgr.mNewEnergyData;
            var5 = var12;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 4, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 58:
            var3 = MsgMgr.mNewEnergyData;
            var5 = var9;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 5, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 59:
            var3 = MsgMgr.mNewEnergyData;
            var5 = var8;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 6, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 60:
            var3 = MsgMgr.mNewEnergyData;
            var5 = var7;
            if (var2 == 1) {
               var5 = true;
            }

            MsgMgr.mNewEnergyData = DataHandleUtils.setIntByteWithBit(var3, 7, var5);
            this.sndNewEnergyCmdDatas();
            break;
         case 61:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)this.swapVal(var2)});
            break;
         case 62:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 8, (byte)this.swapVal(var2)});
            break;
         case 63:
            CanbusMsgSender.sendMsg(new byte[]{22, -87, 5, (byte)this.swapVal(var2)});
            break;
         case 64:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte)this.swapVal(var2)});
            break;
         case 65:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)this.plusVal(var2)});
            break;
         case 66:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 59, (byte)this.swapVal(var2)});
            break;
         case 67:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 6, (byte)this.swapVal(var2)});
            break;
         case 68:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)this.plusVal(var2)});
            break;
         case 69:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)this.plusVal(var2)});
            break;
         case 70:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)this.swapVal(var2)});
            break;
         case 71:
            CanbusMsgSender.sendMsg(new byte[]{22, -120, 8, (byte)(var2 + 1)});
            break;
         case 72:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)this.swapVal(var2)});
            break;
         case 73:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)this.swapVal(var2)});
            break;
         case 74:
            if (this.EachID != 8) {
               return;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, (byte)this.swapVal(var2)});
            break;
         case 75:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte)this.swapVal(var2)});
            break;
         case 76:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)this.swapVal(var2)});
            break;
         case 77:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)this.plusVal(var2)});
            break;
         case 78:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte)this.swapVal(var2)});
            break;
         case 79:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte)this.swapVal(var2)});
            break;
         case 80:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)this.swapVal(var2)});
            break;
         case 81:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)this.swapVal(var2)});
            break;
         case 82:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)this.swapVal(var2)});
            break;
         case 83:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)this.plusVal(var2)});
            break;
         case 84:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte)this.swapVal(var2)});
      }

   }

   private void sndNewEnergyCmdDatas() {
      int var3 = MsgMgr.mNewEnergyStartTime;
      int var2 = DataHandleUtils.getIntFromByteWithBit(MsgMgr.mNewEnergyStartTime, 0, 8);
      int var1 = MsgMgr.mNewEnergyEndTime;
      int var4 = DataHandleUtils.getIntFromByteWithBit(MsgMgr.mNewEnergyEndTime, 0, 8);
      CanbusMsgSender.sendMsg(new byte[]{22, -87, 2, (byte)(var3 >> 8 & 255), (byte)var2, (byte)(var1 >> 8 & 255), (byte)var4, (byte)MsgMgr.mNewEnergyData});
   }

   private int swapVal(int var1) {
      byte var2;
      if (var1 == 0) {
         var2 = 2;
      } else {
         var2 = 1;
      }

      return var2;
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var5 = var6.iterator();

         while(var5.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.setIsHaveRearAir();
      this.setCarIdCmd();
   }
}
