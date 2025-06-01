package com.hzbhd.canbus.car._250;

import android.content.Context;
import android.util.Log;
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
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   protected static final int CAR_ID_AX3_2019 = 6;
   protected static final int CAR_ID_AX5_2016 = 2;
   protected static final int CAR_ID_AX7_2015_2017 = 1;
   protected static final int CAR_ID_AX7_2018 = 3;
   protected static final int CAR_ID_AX7_2018_WITH_360 = 4;
   protected static final int CAR_ID_AX7_2019_2020 = 5;
   protected static final int CAR_ID_DEFAULT = 0;
   protected static final int CAR_ID_XUANYI = 7;
   private static int mFrontWindMode;
   private AirPageUiSet airPageUiSet;
   private Context mContext;
   private int mDifferent;
   private FrontArea mFrontArea;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAcCmd(UiMgr.AIR_CMD.REAR_DEFOG);
               }
            } else {
               this.this$0.sendAcCmd(UiMgr.AIR_CMD.LOOP);
            }
         } else {
            this.this$0.sendAcCmd(UiMgr.AIR_CMD.AUTO);
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
               this.this$0.sendAcCmd(UiMgr.AIR_CMD.AC);
            }
         } else {
            this.this$0.sendAcCmd(UiMgr.AIR_CMD.POWER);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirInfo(1, 0);
         this.this$0.sendAirInfo(0, 0);
      }

      public void onClickUp() {
         this.this$0.sendAirInfo(2, 0);
         this.this$0.sendAirInfo(0, 0);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirInfo(0, 1);
         this.this$0.sendAirInfo(0, 0);
      }

      public void onClickUp() {
         this.this$0.sendAirInfo(0, 2);
         this.this$0.sendAirInfo(0, 0);
      }
   };
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.airPageUiSet = this.getAirUiSet(var1);
      this.settingPageUiSet = this.getSettingUiSet(var1);
      this.mFrontArea = this.airPageUiSet.getFrontArea();
      this.mDifferent = this.getCurrentCarId();
      this.sendcarInfo(this.mContext);
      this.airPageUiSet.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAcCmd(UiMgr.AIR_CMD.MODE);
         }

         public void onRightSeatClick() {
            this.this$0.sendAcCmd(UiMgr.AIR_CMD.MODE);
         }
      });
      FrontArea var4 = this.mFrontArea;
      OnAirBtnClickListener var5 = this.mOnAirBtnClickListenerFrontTop;
      boolean var3 = false;
      var4.setOnAirBtnClickListeners(new OnAirBtnClickListener[]{var5, null, null, this.mOnAirBtnClickListenerFrontBottom});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAcCmd(UiMgr.AIR_CMD.WIND_DN);
         }

         public void onClickRight() {
            this.this$0.sendAcCmd(UiMgr.AIR_CMD.WIND_UP);
         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      if (this.isHaveCam360()) {
         boolean var2;
         if (SharePreUtil.getIntValue(var1, "__250_SAVE_RADAR_DISP", 0) == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var6.setShowRadar(var2);
         var2 = var3;
         if (SharePreUtil.getIntValue(var1, "__250_SAVE_RADAR_DISP", 0) == 0) {
            var2 = true;
         }

         var6.setShowCusPanoramicView(var2);
      } else {
         this.resetRadarAnd360();
      }

      this.settingPageUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var1) {
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
      if (this.isHaveCam360()) {
         this.mMsgMgr.initRadarDisp(this.mContext);
      }

      var6.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var6) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void addViewToWindows() {
            if (this.val$parkPageUiSet.getPanoramicBtnList() != null) {
               this.val$parkPageUiSet.getPanoramicBtnList().clear();
            }

            this.val$parkPageUiSet.setShowPanoramic(true);
            ArrayList var1 = new ArrayList();
            if (this.this$0.mDifferent == 5) {
               var1.add(new ParkPageUiSet.Bean(0, "_250_full", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_front_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_rear_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_left_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_right_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_exit", ""));
            } else if (this.this$0.mDifferent == 7) {
               var1.add(new ParkPageUiSet.Bean(0, "_250_full", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_front_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_rear_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_left_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_right_camera_2d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_swith_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_front_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_rear_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_left_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_right_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_left_up_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_right_up_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_left_down_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_right_down_camera_3d", ""));
               var1.add(new ParkPageUiSet.Bean(0, "_250_exit", ""));
            } else {
               var1 = null;
               this.val$parkPageUiSet.setShowPanoramic(false);
            }

            this.val$parkPageUiSet.setPanoramicBtnList(var1);
         }
      });
      var6.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var6) {
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
               case -2027642942:
                  if (var3.equals("_250_rear_camera_2d")) {
                     var4 = 0;
                  }
                  break;
               case -2027642911:
                  if (var3.equals("_250_rear_camera_3d")) {
                     var4 = 1;
                  }
                  break;
               case -1942780315:
                  if (var3.equals("_250_left_camera_2d")) {
                     var4 = 2;
                  }
                  break;
               case -1942780284:
                  if (var3.equals("_250_left_camera_3d")) {
                     var4 = 3;
                  }
                  break;
               case -1705162449:
                  if (var3.equals("_250_exit")) {
                     var4 = 4;
                  }
                  break;
               case -1705135456:
                  if (var3.equals("_250_full")) {
                     var4 = 5;
                  }
                  break;
               case -1530788760:
                  if (var3.equals("_250_swith_3d")) {
                     var4 = 6;
                  }
                  break;
               case -1040876018:
                  if (var3.equals("_250_left_up_camera_3d")) {
                     var4 = 7;
                  }
                  break;
               case -849805691:
                  if (var3.equals("_250_front_camera_2d")) {
                     var4 = 8;
                  }
                  break;
               case -849805660:
                  if (var3.equals("_250_front_camera_3d")) {
                     var4 = 9;
                  }
                  break;
               case -142810731:
                  if (var3.equals("_250_left_down_camera_3d")) {
                     var4 = 10;
                  }
                  break;
               case 578154715:
                  if (var3.equals("_250_right_up_camera_3d")) {
                     var4 = 11;
                  }
                  break;
               case 967562530:
                  if (var3.equals("_250_right_down_camera_3d")) {
                     var4 = 12;
                  }
                  break;
               case 1059518367:
                  if (var3.equals("_250_exit_full")) {
                     var4 = 13;
                  }
                  break;
               case 1888734904:
                  if (var3.equals("_250_right_camera_2d")) {
                     var4 = 14;
                  }
                  break;
               case 1888734935:
                  if (var3.equals("_250_right_camera_3d")) {
                     var4 = 15;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 5});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 18});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 19});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 0});
                  MsgMgr.mLast360st = 0;
                  FutureUtil.instance.detectParkPanoramic(false, 0);
                  break;
               case 5:
                  if (MsgMgr.mIs360Full) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -120, 7});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -120, 6});
                  }
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 8});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 21});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 2});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 17});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 23});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 22});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 24});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 7});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 4});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 20});
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemTouchListener(new OnSettingItemTouchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onTouchItem(int var1, int var2, View var3, MotionEvent var4) {
            String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            if (this.this$0.getCurrentCarId() == 4) {
               var5.hashCode();
               if (var5.equals("str_250_0_4") && var4.getAction() == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -122, 1});
               }
            } else {
               var5.hashCode();
               if (var5.equals("str_250_0_4") && var4.getAction() == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -120, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, -112, 55});
                  FutureUtil.instance.detectParkPanoramic(true, 1);
               }
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (this.this$0.getCurrentCarId() == 4) {
               if (var1 == 0) {
                  boolean var5 = false;
                  boolean var4;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 == 3) {
                           SharePreUtil.setIntValue(this.this$0.mContext, "__250_SAVE_RADAR_DISP", var3);
                           this.this$0.mMsgMgr.initRadarDisp(this.this$0.mContext);
                           UiMgr var6 = this.this$0;
                           ParkPageUiSet var7 = var6.getParkPageUiSet(var6.mContext);
                           if (var3 == 1) {
                              var4 = true;
                           } else {
                              var4 = false;
                           }

                           var7.setShowRadar(var4);
                           var4 = var5;
                           if (var3 == 0) {
                              var4 = true;
                           }

                           var7.setShowCusPanoramicView(var4);
                        }
                     } else {
                        var1 = MsgMgr.m0x27SettingData;
                        if (var3 == 1) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)DataHandleUtils.setIntByteWithBit(var1, 0, var4)});
                     }
                  } else {
                     var1 = MsgMgr.m0x27SettingData;
                     if (var3 == 1) {
                        var4 = true;
                     } else {
                        var4 = false;
                     }

                     CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)DataHandleUtils.setIntByteWithBit(var1, 1, var4)});
                  }
               }
            } else {
               this.this$0.setItemsCmd(var1, var2, var3);
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -204429238:
                  if (var4.equals("_250_auto_lock")) {
                     var5 = 0;
                  }
                  break;
               case 686086529:
                  if (var4.equals("_250_Lane_Keeping_Assist_System")) {
                     var5 = 1;
                  }
                  break;
               case 1949295592:
                  if (var4.equals("_250_Traffic_sign_recognition")) {
                     var5 = 2;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -119, 36, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -119, 39, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -119, 41, (byte)var3});
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   protected static void sendAirCommandFrontWindMode(byte[] var0) {
      int var1 = mFrontWindMode;
      var0[4] = (new byte[]{16, 8, 4, 2})[var1];
      CanbusMsgSender.sendMsg(var0);
      var1 = mFrontWindMode + 1;
      mFrontWindMode = var1;
      if (var1 >= 4) {
         mFrontWindMode = 0;
      }

   }

   private void sendAirInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte)var1, (byte)var2, 0});
   }

   private byte sendInfo(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               return (byte)(var1 != 3 ? 5 : 4);
            } else {
               return 3;
            }
         } else {
            return 2;
         }
      } else {
         return 1;
      }
   }

   private void sendcarInfo(Context var1) {
      int var2 = this.mDifferent;
      if (var2 != 1) {
         if (var2 != 3) {
            if (var2 != 4) {
               if (var2 != 10) {
                  if (var2 == 11) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -123, 9});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 8});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
      }

   }

   private void setItemsCmd(int var1, int var2, int var3) {
      byte[] var6;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        switch (var2) {
                           case 0:
                              var6 = new byte[]{22, -119, 31, (byte)var3};
                              CanbusMsgSender.sendMsg(var6);
                              break;
                           case 1:
                              var6 = new byte[]{22, -119, 32, (byte)var3};
                              CanbusMsgSender.sendMsg(var6);
                              break;
                           case 2:
                              var6 = new byte[]{22, -119, 33, (byte)var3};
                              CanbusMsgSender.sendMsg(var6);
                              break;
                           case 3:
                              var6 = new byte[]{22, -119, 34, (byte)var3};
                              CanbusMsgSender.sendMsg(var6);
                              break;
                           case 4:
                              Log.i("enter radar", "enter radar");
                              SharePreUtil.setIntValue(this.mContext, "__250_SAVE_RADAR_DISP", var3);
                              this.mMsgMgr.initRadarDisp(this.mContext);
                              ParkPageUiSet var5 = this.getParkPageUiSet(this.mContext);
                              boolean var4;
                              if (var3 == 1) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var5.setShowRadar(var4);
                              if (var3 == 0) {
                                 var4 = true;
                              } else {
                                 var4 = false;
                              }

                              var5.setShowCusPanoramicView(var4);
                              this.getMsgMgr(this.mContext).updateSettings(this.mContext, "__250_SAVE_RADAR_DISP", var1, var2, var3);
                              break;
                           case 5:
                              Log.i("enter cam", "enter cam");
                              CanbusMsgSender.sendMsg(new byte[]{22, -120, 1});
                              FutureUtil.instance.detectParkPanoramic(true, 1);
                              break;
                           case 6:
                              CanbusMsgSender.sendMsg(new byte[]{22, -119, 35, (byte)(var3 + 1)});
                              break;
                           case 7:
                              CanbusMsgSender.sendMsg(new byte[]{22, -119, 37, this.sendInfo(var3)});
                              break;
                           case 8:
                              CanbusMsgSender.sendMsg(new byte[]{22, -119, 38, (byte)(var3 + 1)});
                        }
                     }
                  } else if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           if (var2 == 3) {
                              var6 = new byte[]{22, -119, 30, (byte)((byte)var3 + 1)};
                              CanbusMsgSender.sendMsg(var6);
                           }
                        } else {
                           var6 = new byte[]{22, -119, 29, (byte)var3};
                           CanbusMsgSender.sendMsg(var6);
                        }
                     } else {
                        var6 = new byte[]{22, -119, 28, (byte)var3};
                        CanbusMsgSender.sendMsg(var6);
                     }
                  } else {
                     var6 = new byte[]{22, -119, 27, (byte)var3};
                     CanbusMsgSender.sendMsg(var6);
                  }
               } else if (var2 != 0) {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 == 3) {
                           var6 = new byte[]{22, -119, 26, (byte)((byte)var3 + 1)};
                           CanbusMsgSender.sendMsg(var6);
                        }
                     } else {
                        var6 = new byte[]{22, -119, 25, (byte)var3};
                        CanbusMsgSender.sendMsg(var6);
                     }
                  } else {
                     var6 = new byte[]{22, -119, 24, (byte)var3};
                     CanbusMsgSender.sendMsg(var6);
                  }
               } else {
                  var6 = new byte[]{22, -119, 23, (byte)((byte)var3 + 1)};
                  CanbusMsgSender.sendMsg(var6);
               }
            } else if (var2 != 0) {
               if (var2 == 1) {
                  var6 = new byte[]{22, -119, 15, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
               }
            } else {
               var6 = new byte[]{22, -119, 14, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
            }
         } else {
            switch (var2) {
               case 0:
                  var6 = new byte[]{22, -119, 16, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
                  break;
               case 1:
                  var6 = new byte[]{22, -119, 17, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
                  break;
               case 2:
                  var6 = new byte[]{22, -119, 18, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
                  break;
               case 3:
                  var6 = new byte[]{22, -119, 19, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
                  break;
               case 4:
                  var6 = new byte[]{22, -119, 20, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
                  break;
               case 5:
                  var6 = new byte[]{22, -119, 21, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
                  break;
               case 6:
                  var6 = new byte[]{22, -119, 22, (byte)var3};
                  CanbusMsgSender.sendMsg(var6);
            }
         }
      } else {
         switch (var2) {
            case 0:
               var6 = new byte[]{22, -119, 1, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 1:
               var6 = new byte[]{22, -119, 2, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 2:
               var6 = new byte[]{22, -119, 3, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 3:
               var6 = new byte[]{22, -119, 4, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 4:
               var6 = new byte[]{22, -119, 5, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 5:
               var6 = new byte[]{22, -119, 6, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 6:
               var6 = new byte[]{22, -119, 7, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 7:
               var6 = new byte[]{22, -119, 8, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 8:
               var6 = new byte[]{22, -119, 9, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 9:
               var6 = new byte[]{22, -119, 11, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 10:
               var6 = new byte[]{22, -119, 10, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
               break;
            case 11:
               var6 = new byte[]{22, -119, 13, (byte)var3};
               CanbusMsgSender.sendMsg(var6);
         }
      }

      String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var7.hashCode();
      if (var7.equals("_250_Lane_Keeping_Assist_System_Assist_Mode")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -119, 40, (byte)var3});
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

   boolean isHaveCam360() {
      return this.getCurrentCarId() == 4 || this.getCurrentCarId() == 5 || this.getCurrentCarId() == 7;
   }

   void resetRadarAnd360() {
      ParkPageUiSet var1 = this.getParkPageUiSet(this.mContext);
      var1.setShowRadar(true);
      var1.setShowCusPanoramicView(false);
   }

   void sendAcCmd(AIR_CMD var1) {
      byte[] var2 = new byte[]{22, -57, 0, 0, 0, 0, 0, 0};
      switch (null.$SwitchMap$com$hzbhd$canbus$car$_250$UiMgr$AIR_CMD[var1.ordinal()]) {
         case 1:
            var2[2] = -128;
            CanbusMsgSender.sendMsg(var2);
            var2[2] = 0;
            CanbusMsgSender.sendMsg(var2);
            break;
         case 2:
            var2[2] = 2;
            CanbusMsgSender.sendMsg(var2);
            var2[2] = 0;
            CanbusMsgSender.sendMsg(var2);
            break;
         case 3:
            var2[3] = 1;
            CanbusMsgSender.sendMsg(var2);
            var2[3] = 0;
            CanbusMsgSender.sendMsg(var2);
            break;
         case 4:
            var2[3] = 2;
            CanbusMsgSender.sendMsg(var2);
            var2[3] = 0;
            CanbusMsgSender.sendMsg(var2);
            break;
         case 5:
            var2[2] = 32;
            CanbusMsgSender.sendMsg(var2);
            var2[2] = 0;
            CanbusMsgSender.sendMsg(var2);
            break;
         case 6:
            if (GeneralAirData.in_out_cycle) {
               var2[2] = 4;
               CanbusMsgSender.sendMsg(var2);
               var2[2] = 0;
               CanbusMsgSender.sendMsg(var2);
            } else {
               var2[2] = 8;
               CanbusMsgSender.sendMsg(var2);
               var2[2] = 0;
               CanbusMsgSender.sendMsg(var2);
            }
            break;
         case 7:
            var2[3] = 4;
            CanbusMsgSender.sendMsg(var2);
            var2[3] = 0;
            CanbusMsgSender.sendMsg(var2);
            break;
         case 8:
            sendAirCommandFrontWindMode(var2);
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.mDifferent;
      if (var2 == 0 || var2 == 1 || var2 == 2 || var2 == 3 || var2 == 6) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      if (this.mDifferent == 4) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_250_original_car_set", "_250_profiles", "_250_personalized_instrument_settings", "_250_and_ambient_lighting_brightness_meter", "_250_power_tailgate", "_250_vehicle_settings"});
      } else {
         this.getMsgMgr(this.mContext).updateSettings(var1, "__250_SAVE_RADAR_DISP", this.getSettingLeftIndexes(var1, "_250_vehicle_settings"), this.getSettingRightIndex(var1, "_250_vehicle_settings", "_278_radar_disp"), SharePreUtil.getIntValue(var1, "__250_SAVE_RADAR_DISP", 0));
      }

      if (this.mDifferent != 4) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"car_setting"});
      }

      if (this.mDifferent != 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_250_Combination_meter_style_settings"});
      }

      if (this.mDifferent != 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_250_auto_lock"});
      }

      if (this.mDifferent != 14) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_250_theme_change"});
      }

      if (this.mDifferent != 8) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_250_Drive_assistance"});
      }

      var2 = this.mDifferent;
      if (var2 != 8 && var2 != 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_250_Language"});
      }

      var2 = this.mDifferent;
      if (var2 == 8 || var2 == 13) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_250_language"});
      }

   }

   static enum AIR_CMD {
      private static final AIR_CMD[] $VALUES;
      AC,
      AUTO,
      LOOP,
      MODE,
      POWER,
      REAR_DEFOG,
      WIND_DN,
      WIND_UP;

      static {
         AIR_CMD var1 = new AIR_CMD("POWER", 0);
         POWER = var1;
         AIR_CMD var2 = new AIR_CMD("AC", 1);
         AC = var2;
         AIR_CMD var7 = new AIR_CMD("WIND_DN", 2);
         WIND_DN = var7;
         AIR_CMD var6 = new AIR_CMD("WIND_UP", 3);
         WIND_UP = var6;
         AIR_CMD var4 = new AIR_CMD("AUTO", 4);
         AUTO = var4;
         AIR_CMD var0 = new AIR_CMD("LOOP", 5);
         LOOP = var0;
         AIR_CMD var5 = new AIR_CMD("REAR_DEFOG", 6);
         REAR_DEFOG = var5;
         AIR_CMD var3 = new AIR_CMD("MODE", 7);
         MODE = var3;
         $VALUES = new AIR_CMD[]{var1, var2, var7, var6, var4, var0, var5, var3};
      }
   }
}
