package com.hzbhd.canbus.car._186;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.util.LogUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   static final String SHARE_188_IS_SUPPORT_PANORAMIC = "share_188_is_support_panoramic";
   private static final String SHARE_188_LANGUAGE = "share_188_language";
   OnAirBtnClickListener RearBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (LogUtil.log5()) {
            LogUtil.d("click this ");
         }

         this.this$0.sendAirData(0, 5);
         this.this$0.sendAirData(1, 5);
      }
   };
   private int eachID;
   private AirActivity mActivity;
   private Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   private View mMyPanoramicView;
   private MsgMgr msgMgr;
   OnAirBtnClickListener onAirBtnClickListener_frontBottom = new UiMgr$$ExternalSyntheticLambda3(this);
   OnAirBtnClickListener onAirBtnClickListener_frontLeft = new UiMgr$$ExternalSyntheticLambda1(this);
   OnAirBtnClickListener onAirBtnClickListener_frontRight = new UiMgr$$ExternalSyntheticLambda2(this);
   OnAirBtnClickListener onAirBtnClickListener_frontTop = new UiMgr$$ExternalSyntheticLambda0(this);
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
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener_rear = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData(5, 0);
      }

      public void onClickUp() {
         this.this$0.sendAirData(5, 1);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData(1, 0);
      }

      public void onClickRight() {
         this.this$0.sendAirData(1, 1);
      }
   };
   private byte[] stagedAirConditionKeyState = new byte[]{0, 0, 0, 0, 0, 0};

   public UiMgr(Context var1) {
      this.mContext = var1;
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      this.mDifferent = this.getCurrentCarId();
      this.eachID = this.getEachId();
      SettingPageUiSet var4 = this.getSettingUiSet(this.mContext);
      PauseableThread var5 = new PauseableThread();
      var5.start();
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var4, var1, var5) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;
         final PauseableThread val$thread;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
            this.val$thread = var4;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var8 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var8.hashCode();
            int var5 = var8.hashCode();
            boolean var7 = false;
            byte var4 = -1;
            switch (var5) {
               case -2052975702:
                  if (var8.equals("_186_bose_centerpoint")) {
                     var4 = 0;
                  }
                  break;
               case -1868019038:
                  if (var8.equals("_186_driver_sound_field")) {
                     var4 = 1;
                  }
                  break;
               case -1185880424:
                  if (var8.equals("_186_Auto_light_off_time_setting")) {
                     var4 = 2;
                  }
                  break;
               case -1090600587:
                  if (var8.equals("_186_Auto_light_sensitivity")) {
                     var4 = 3;
                  }
                  break;
               case 712683749:
                  if (var8.equals("support_panorama")) {
                     var4 = 4;
                  }
                  break;
               case 1504695456:
                  if (var8.equals("_186_amplifier_settings")) {
                     var4 = 5;
                  }
                  break;
               case 1806178504:
                  if (var8.equals("vm_golf7_language_setup")) {
                     var4 = 6;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 41, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 87, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 85, (byte)var3});
                  break;
               case 4:
                  SharePreUtil.setIntValue(this.val$context, "share_188_is_support_panoramic", var3);
                  this.this$0.getMsgMgr(this.val$context).updateSettingActivity(var1, var2, var3);
                  MsgMgr var10 = this.this$0.getMsgMgr(this.val$context);
                  boolean var6;
                  if (var3 == 1) {
                     var6 = true;
                  } else {
                     var6 = false;
                  }

                  var10.updateSettingActivity(var1, var2 + 1, "null_value", var6);
                  MsgMgr var9 = this.this$0.getMsgMgr(this.val$context);
                  Context var11 = this.val$context;
                  var6 = var7;
                  if (var3 == 1) {
                     var6 = true;
                  }

                  var9.updateBubble(var11, var6);
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte)var3});
                  this.val$thread.pauseThread();
                  this.val$thread.setSelectPos(var3);
                  this.val$thread.resumeThread();
                  break;
               case 6:
                  if (var3 <= 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)var3});
                     SharePreUtil.setIntValue(this.val$context, "share_188_language", var3);
                     this.this$0.getMsgMgr(this.val$context).updateSettingActivity(var1, var2, var3);
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)(var3 + 129)});
                  }
            }

         }
      });
      var4.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1847506324:
                  if (var4.equals("_186_Rear_Entertainment_System_Interface")) {
                     var5 = 0;
                  }
                  break;
               case -1780524959:
                  if (var4.equals("_186_blind_spot_detection")) {
                     var5 = 1;
                  }
                  break;
               case -1775782966:
                  if (var4.equals("_186_Left_speaker_output")) {
                     var5 = 2;
                  }
                  break;
               case -1542240099:
                  if (var4.equals("_186_Unlock_and_turn_on_the_lights")) {
                     var5 = 3;
                  }
                  break;
               case -1423161114:
                  if (var4.equals("_186_auto_retreat")) {
                     var5 = 4;
                  }
                  break;
               case -1102386185:
                  if (var4.equals("_186_Left_monitor_power_supply")) {
                     var5 = 5;
                  }
                  break;
               case -1101563136:
                  if (var4.equals("_186_switch_Unlock")) {
                     var5 = 6;
                  }
                  break;
               case -1047287737:
                  if (var4.equals("_186_moving_object_detection")) {
                     var5 = 7;
                  }
                  break;
               case -121562794:
                  if (var4.equals("_186_lane_departure_detection")) {
                     var5 = 8;
                  }
                  break;
               case 237525139:
                  if (var4.equals("_186_Right_speaker_output")) {
                     var5 = 9;
                  }
                  break;
               case 365087569:
                  if (var4.equals("_186_Automatically_power_on_the_monitor")) {
                     var5 = 10;
                  }
                  break;
               case 1023743488:
                  if (var4.equals("_186_Right_monitor_power_supply")) {
                     var5 = 11;
                  }
                  break;
               case 1462043251:
                  if (var4.equals("_186_Vehicle_speed_linkage_intermittent_wiper")) {
                     var5 = 12;
                  }
                  break;
               case 1975548841:
                  if (var4.equals("_186_Smart_key_unlock")) {
                     var5 = 13;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 83, 1});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 52, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 84, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 89, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 51, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 91, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 81, 1});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 82, 1});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 54, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 55, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 53, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 86, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 88, (byte)var3});
            }

         }
      });
      var4.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var4) {
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
      var4.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            boolean var5 = var6.equals("_186_asl");
            var2 = 49;
            var1 = 33;
            int var4;
            if (!var5) {
               if (var6.equals("_186_surround_volume")) {
                  var4 = var3 - this.this$0.mMsgMgr.mAmpSurroundValueNow;
                  if (var4 <= 0) {
                     var1 = var4;
                  }

                  if (var1 < 0) {
                     var1 = var2;
                  }

                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 40, (byte)var1});
                  this.this$0.mMsgMgr.mAmpSurroundValueNow = var3;
               }
            } else {
               var4 = var3 - this.this$0.mMsgMgr.mAmpAslValueNow;
               if (var4 <= 0) {
                  var1 = var4;
               }

               if (var1 >= 0) {
                  var2 = var1;
               }

               CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var2});
               this.this$0.mMsgMgr.mAmpAslValueNow = var3;
            }

         }
      });
      AmplifierPageUiSet var6 = this.getAmplifierPageUiSet(var1);
      var6.setOnAmplifierPositionListener(new OnAmplifierPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierPosition.LEFT_RIGHT) {
               this.this$0.sendAmplifierCommand(36, var2, GeneralAmplifierData.leftRight);
            } else if (var1 == AmplifierActivity.AmplifierPosition.FRONT_REAR) {
               this.this$0.sendAmplifierCommand(37, var2, GeneralAmplifierData.frontRear);
            }

         }
      });
      var6.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
            if (var1 == AmplifierActivity.AmplifierBand.VOLUME) {
               this.this$0.sendAmplifierCommand(33, var2, GeneralAmplifierData.volume);
            } else if (var1 == AmplifierActivity.AmplifierBand.BASS) {
               this.this$0.sendAmplifierCommand(34, var2, GeneralAmplifierData.bandBass);
            } else if (var1 == AmplifierActivity.AmplifierBand.TREBLE) {
               this.this$0.sendAmplifierCommand(35, var2, GeneralAmplifierData.bandTreble);
            }

         }
      });
      AirPageUiSet var7 = this.getAirUiSet(this.mContext);
      int var2 = this.eachID;
      if (var2 != 4 && var2 != 10 && var2 != 17 && var2 != 18) {
         var7.getFrontArea().setAllBtnCanClick(false);
         var7.getFrontArea().setCanSetWindSpeed(false);
         var7.getFrontArea().setCanSetLeftTemp(false);
         var7.getFrontArea().setCanSetRightTemp(false);
      }

      var7.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListener_frontTop, this.onAirBtnClickListener_frontLeft, this.onAirBtnClickListener_frontRight, this.onAirBtnClickListener_frontBottom});
      var7.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendAirData(0, 6);
         }

         public void onRightSeatClick() {
            this.this$0.sendAirData(0, 6);
         }
      });
      var7.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var7.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListener_frontLeft, null, this.onAirTemperatureUpDownClickListener_frontRight, null, this.onAirTemperatureUpDownClickListener_rear});
      var7.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
         }
      });
      var2 = this.eachID;
      boolean var3;
      if (var2 != 17 && var2 != 18) {
         var3 = false;
      } else {
         var3 = true;
      }

      var7.setHaveRearArea(var3);
      var7.getRearArea().setAllBtnCanClick(true);
      var7.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.RearBottomBtnListener});
      ParkPageUiSet var8 = this.getParkPageUiSet(var1);
      var8.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1, var8) {
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
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 3});
         }
      });
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 40});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 104});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 106});
         }
      });
      this.getBubbleUiSet(var1).setOnBubbleClickListener(new UiMgr$$ExternalSyntheticLambda4());
      Log.i("ljq", "UiMgr: " + this.getCurrentCarId());
      if (this.getCurrentCarId() == 1) {
         this.getParkPageUiSet(var1).setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this, var1) {
            final UiMgr this$0;
            final Context val$context;

            {
               this.this$0 = var1;
               this.val$context = var2;
            }

            public void onTouchItem(MotionEvent var1) {
               int var3 = (int)var1.getX() * 1024 / (int)this.val$context.getResources().getDimension(2131167469);
               int var4 = (int)var1.getY() * 1024 / (int)this.val$context.getResources().getDimension(2131170367);
               int var2 = var3 & 255;
               int var5 = var3 >> 8 & 255;
               var3 = var4 & 255;
               var4 = var4 >> 8 & 255;
               if (var1.getAction() == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte)var5, (byte)var2, (byte)var4, (byte)var3});
               } else if (var1.getAction() == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, 0, (byte)var5, (byte)var2, (byte)var4, (byte)var3});
               }

            }
         });
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   // $FF: synthetic method
   static void lambda$new$0() {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
   }

   private void sendAmplifierCommand(int var1, int var2, int var3) {
      var2 -= var3;
      byte var4;
      if (var2 > 0) {
         var4 = 33;
      } else {
         if (var2 >= 0) {
            return;
         }

         var4 = 49;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var4});
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new MyPanoramicView(var1);
      }

      return this.mMyPanoramicView;
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

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__186_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 == 1) {
            this.sendAirData(0, 1);
         }
      } else {
         this.sendAirData(0, 7);
      }

   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__186_UiMgr(int var1) {
      if (var1 == 0) {
         this.sendAirData(0, 4);
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__186_UiMgr(int var1) {
      if (var1 == 0) {
         this.sendAirData(1, 2);
      }

   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__186_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.sendAirData(1, 3);
            }
         } else if (this.getMsgMgr(this.mContext).cycle == 1) {
            this.sendAirData(0, 3);
            this.sendAirData(0, 2);
         } else if (this.getMsgMgr(this.mContext).cycle == 2) {
            this.sendAirData(0, 2);
            this.sendAirData(0, 3);
         } else if (this.getMsgMgr(this.mContext).cycle == 0) {
            this.sendAirData(0, 3);
         }
      } else {
         this.sendAirData(0, 5);
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

   void sendAirData(int var1, int var2, int var3, int var4) {
      byte[] var5 = this.stagedAirConditionKeyState;
      var5[var3] = (byte)DataHandleUtils.setIntByteWithBit(var5[var3], var4, false);
      var5 = this.stagedAirConditionKeyState;
      var5[var1] = (byte)DataHandleUtils.setIntByteWithBit(var5[var1], var2, true);
      var5 = this.stagedAirConditionKeyState;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, var5));
      var5 = this.stagedAirConditionKeyState;
      var5[var1] = (byte)DataHandleUtils.setIntByteWithBit(var5[var1], var2, false);
      var5 = this.stagedAirConditionKeyState;
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31}, var5));
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.eachID;
      if (var2 != 4 && var2 != 5 && var2 != 6 && var2 != 7 && var2 != 10 && var2 != 16 && var2 != 17 && var2 != 18) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      var2 = this.eachID;
      if (var2 != 5 && var2 != 7 && var2 != 13 && var2 != 16) {
         this.removeMainPrjBtnByName(var1, "drive_data");
      }

      if (this.eachID == 13) {
         this.removeDriveDateItemForTitles(var1, new String[]{"a_current_speed"});
      }

      var2 = this.eachID;
      if (var2 != 8 && var2 != 9 && var2 != 10 && var2 != 11 && var2 != 12 && var2 != 14 && var2 != 4 && var2 != 17 && var2 != 18) {
         this.removeSettingRightItemByNameList(var1, new String[]{"vm_golf7_language_setup"});
      }

      var2 = this.eachID;
      if (var2 != 2 && var2 != 7 && var2 != 15 && var2 != 16) {
         this.removeMainPrjBtnByName(var1, "amplifier");
         this.removeSettingLeftItemByNameList(var1, new String[]{"amplifier_setting"});
      }

      if (this.eachID != 15) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_186_Rear_Entertainment_System"});
      }

      var2 = this.eachID;
      if (var2 != 7 && var2 != 13) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_186_Original_car_settings", "_186_Original_car_settings2"});
      }

      if (this.eachID != 3) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_186_lane_departure_detection", "_186_blind_spot_detection"});
      }

      var2 = this.eachID;
      if (var2 != 3 && var2 != 9 && var2 != 12) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_186_moving_object_detection"});
      }

      var2 = this.eachID;
      if (var2 != 5 && var2 != 16) {
         this.removeMainPrjBtnByName(var1, "original_car_device");
      }

      var2 = this.eachID;
      if (var2 != 5 && var2 != 17 && var2 != 18) {
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"_186_time"});
      }

      if (this.eachID != 16) {
         this.removeMainPrjBtnByName(this.mContext, "tire_info");
      }

      if (this.eachID != 7) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_186_amplifier_settings"});
      }

   }
}
