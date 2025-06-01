package com.hzbhd.canbus.car._304;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._304.util.Util;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Objects;

public class UiMgr extends AbstractUiMgr {
   public static final int PANO_BTN_ALL = 4;
   public static final int PANO_BTN_EXIT = 6;
   public static final int PANO_BTN_FRONT = 0;
   public static final int PANO_BTN_LEFT = 2;
   public static final int PANO_BTN_REAR = 1;
   public static final int PANO_BTN_RIGHT = 3;
   public static final int PANO_BTN_SINGLE = 7;
   public static final int PANO_BTN_VOICE = 5;
   private final int MSG_SEND_AIR_COMMAND_UP = 16;
   private final String TAG = "_304_UiMgr";
   private byte[] mAmbientCommand = new byte[]{22, -79, 0, 0, 0, 0, 0};
   private byte[] mAvmCommand = new byte[]{22, -80, 64, 0};
   private int mDiiferentId = this.getCurrentCarId();
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 16) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1.arg1, 0});
         }

      }
   };
   private MsgMgr mMsgMgr;
   private CusPanoramicView mPanoramicView;
   private int mVideoMode;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda0(this, var2));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda1(this, var2));
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda2(var2));
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda3(var2));
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      var4.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda4(this));
      var4.setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda5(this, var1, var4));
      var4.setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda6(this));
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda7(this, var3), null, null, null});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(2);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(3);
         }
      }, null, null});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(9);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(10);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   // $FF: synthetic method
   static void lambda$new$2(SettingPageUiSet var0, int var1, int var2) {
      Objects.requireNonNull(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn());
   }

   // $FF: synthetic method
   static void lambda$new$3(SettingPageUiSet var0, int var1, int var2) {
      Objects.requireNonNull(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn());
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, 1});
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -406139246:
            if (var1.equals("ptc_heating")) {
               var2 = 0;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 1;
            }
            break;
         case 95131878:
            if (var1.equals("cycle")) {
               var2 = 2;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 3;
            }
            break;
         case 1018451744:
            if (var1.equals("blow_head_foot")) {
               var2 = 4;
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               var2 = 5;
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               var2 = 6;
            }
            break;
         case 1568764496:
            if (var1.equals("blow_window_foot")) {
               var2 = 7;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(29);
            break;
         case 1:
            this.sendAirCommand(23);
            break;
         case 2:
            if (GeneralAirData.in_out_cycle) {
               this.sendAirCommand(27);
            } else {
               this.sendAirCommand(26);
            }
            break;
         case 3:
            this.sendAirCommand(28);
            break;
         case 4:
            this.sendAirCommand(49);
            break;
         case 5:
            this.sendAirCommand(50);
            break;
         case 6:
            this.sendAirCommand(48);
            break;
         case 7:
            this.sendAirCommand(51);
      }

   }

   private void sendImageMode(int var1) {
      byte[] var3 = this.mAvmCommand;
      byte var2 = (byte)(var3[3] & 243);
      var3[3] = var2;
      var3[3] = (byte)(var1 << 2 & 12 | var2);
      CanbusMsgSender.sendMsg(var3);
   }

   private void sendImageSwitch(int var1) {
      byte[] var3 = this.mAvmCommand;
      byte var2 = (byte)(var3[2] & 191);
      var3[2] = var2;
      var3[2] = (byte)(var1 << 6 & 64 | var2);
      CanbusMsgSender.sendMsg(var3);
   }

   private void sendImageView(int var1) {
      byte[] var3 = this.mAvmCommand;
      byte var2 = (byte)(var3[2] & 207);
      var3[2] = var2;
      var3[2] = (byte)(var1 << 4 & 48 | var2);
      CanbusMsgSender.sendMsg(var3);
   }

   private void sendKeyAvmClose() {
      FutureUtil.instance.reqMcuKey(new byte[]{-47, 0});
      Util.sendAvmCommand(0);
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mPanoramicView == null) {
         CusPanoramicView var2 = new CusPanoramicView(var1);
         this.mPanoramicView = var2;
         var2.initView(this.getParkPageUiSet(var1));
      }

      return this.mPanoramicView;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__304_UiMgr(SettingPageUiSet var1, int var2, int var3, int var4) {
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      var5.hashCode();
      var3 = var5.hashCode();
      byte var7 = -1;
      switch (var3) {
         case -1807645313:
            if (var5.equals("_304_atoms_lamp_b")) {
               var7 = 0;
            }
            break;
         case -1807645308:
            if (var5.equals("_304_atoms_lamp_g")) {
               var7 = 1;
            }
            break;
         case -1807645297:
            if (var5.equals("_304_atoms_lamp_r")) {
               var7 = 2;
            }
            break;
         case -792513678:
            if (var5.equals("_250_ambient_light_brightness")) {
               var7 = 3;
            }
      }

      byte[] var6;
      switch (var7) {
         case 0:
            var6 = this.mAmbientCommand;
            var6[4] = (byte)var4;
            CanbusMsgSender.sendMsg(var6);
            break;
         case 1:
            var6 = this.mAmbientCommand;
            var6[3] = (byte)var4;
            CanbusMsgSender.sendMsg(var6);
            break;
         case 2:
            var6 = this.mAmbientCommand;
            var6[2] = (byte)var4;
            CanbusMsgSender.sendMsg(var6);
            break;
         case 3:
            var6 = this.mAmbientCommand;
            var6[5] = (byte)var4;
            CanbusMsgSender.sendMsg(var6);
      }

   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__304_UiMgr(SettingPageUiSet var1, int var2, int var3, int var4) {
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      var5.hashCode();
      if (var5.equals("_304_atoms_lamp_control")) {
         byte[] var6 = this.mAmbientCommand;
         var6[6] = (byte)(var4 + 1);
         CanbusMsgSender.sendMsg(var6);
      }

   }

   // $FF: synthetic method
   void lambda$new$4$com_hzbhd_canbus_car__304_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 == 7) {
                           this.sendImageMode(2);
                        }
                     } else {
                        this.sendKeyAvmClose();
                     }
                  } else {
                     this.sendImageMode(0);
                  }
               } else {
                  this.sendImageView(3);
               }
            } else {
               this.sendImageView(2);
            }
         } else {
            this.sendImageView(1);
         }
      } else {
         this.sendImageView(0);
      }

   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__304_UiMgr(Context var1, ParkPageUiSet var2) {
      DisplayMetrics var7 = var1.getResources().getDisplayMetrics();
      int var5 = var1.getResources().getDimensionPixelOffset(2131165207);
      int var6 = var1.getResources().getDimensionPixelOffset(2131167416);
      int var4 = var7.widthPixels - var5;
      int var3 = (int)((float)var7.heightPixels / (float)var7.widthPixels * (float)var4);
      Log.i("_304_UiMgr", "UiMgr: \nstartX:" + var5 + "\nstartY:" + var6 + "\nvideoWidth:" + var4 + "\nvideoHeight:" + var3);
      var2.setCusVideoStartX(var5);
      var2.setCusVideoStartY(var6);
      var2.setCusVideoWidth(var4);
      var2.setCusVideoHeight(var3);
   }

   // $FF: synthetic method
   void lambda$new$6$com_hzbhd_canbus_car__304_UiMgr() {
      FutureUtil var4 = FutureUtil.instance;
      int var1 = this.mDiiferentId;
      boolean var3 = true;
      boolean var2;
      if (var1 == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setReversingTrackSwitch(var2);
      var4 = FutureUtil.instance;
      if (this.mDiiferentId == 0) {
         var2 = var3;
      } else {
         var2 = false;
      }

      var4.setReversingBaseline(var2);
   }

   // $FF: synthetic method
   void lambda$new$7$com_hzbhd_canbus_car__304_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }
}
