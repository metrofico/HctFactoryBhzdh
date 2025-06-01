package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingProgressView;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarSettingLightView extends RelativeLayout {
   private ExecutorService executorService;
   private boolean isSeekbar;
   private List listAmbientTen;
   private List listAmbientThree;
   private List listAmbientTwenty;
   private List listOpenTime;
   private List listTravel;
   private Context mContext;
   private Handler mHandler;
   private View mView;
   private SettingDialogView sdv_ambient_ten;
   private SettingDialogView sdv_ambient_three;
   private SettingDialogView sdv_ambient_twenty;
   private SettingProgressView sdv_coming;
   private SettingProgressView sdv_leaving;
   private SettingDialogView sdv_openTime;
   private SettingDialogView sdv_travel;
   private SettingProgressView spv_ambient_car_all;
   private SettingProgressView spv_ambient_car_in;
   private SettingProgressView spv_ambient_car_right;
   private SettingProgressView spv_big_light;
   private SettingProgressView spv_door;
   private SettingProgressView spv_footwell;
   private SettingProgressView spv_instrument;
   private SettingProgressView spv_light_system;
   private SettingSelectView ssv_assist;
   private SettingSelectView ssv_automatic;
   private SettingSelectView ssv_bend;
   private SettingSelectView ssv_day;
   private SettingSelectView ssv_laneChange;

   public CarSettingLightView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingLightView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingLightView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.executorService = Executors.newSingleThreadExecutor();
      this.listOpenTime = new ArrayList();
      this.listTravel = new ArrayList();
      this.listAmbientThree = new ArrayList();
      this.listAmbientTen = new ArrayList();
      this.listAmbientTwenty = new ArrayList();
      this.isSeekbar = false;
      this.mHandler = new Handler(this, Looper.getMainLooper()) {
         final CarSettingLightView this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 0) {
               this.this$0.isSeekbar = false;
            }

         }
      };
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558453, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new CarSettingLightView$$ExternalSyntheticLambda3(this), 1000L);
   }

   private void initClick() {
      CarSettingLightView$$ExternalSyntheticLambda0 var2 = new CarSettingLightView$$ExternalSyntheticLambda0(this);
      CarSettingLightView$$ExternalSyntheticLambda1 var1 = new CarSettingLightView$$ExternalSyntheticLambda1();
      CarSettingLightView$$ExternalSyntheticLambda2 var3 = new CarSettingLightView$$ExternalSyntheticLambda2(this);
      this.ssv_assist.setOnItemClickListener(var2);
      this.ssv_bend.setOnItemClickListener(var2);
      this.ssv_laneChange.setOnItemClickListener(var2);
      this.ssv_automatic.setOnItemClickListener(var2);
      this.ssv_day.setOnItemClickListener(var2);
      this.sdv_openTime.setOnItemClickListener(var1);
      this.sdv_travel.setOnItemClickListener(var1);
      this.sdv_ambient_three.setOnItemClickListener(var1);
      this.sdv_ambient_ten.setOnItemClickListener(var1);
      this.sdv_ambient_twenty.setOnItemClickListener(var1);
      this.spv_instrument.setOnItemClickListener(var3);
      this.spv_door.setOnItemClickListener(var3);
      this.spv_footwell.setOnItemClickListener(var3);
      this.sdv_coming.setOnItemClickListener(var3);
      this.sdv_leaving.setOnItemClickListener(var3);
      this.spv_ambient_car_in.setOnItemClickListener(var3);
      this.spv_ambient_car_right.setOnItemClickListener(var3);
      this.spv_ambient_car_all.setOnItemClickListener(var3);
      this.spv_big_light.setOnItemClickListener(var3);
      this.spv_light_system.setOnItemClickListener(var3);
   }

   private void initView() {
      this.ssv_assist = (SettingSelectView)this.mView.findViewById(2131363381);
      this.ssv_bend = (SettingSelectView)this.mView.findViewById(2131363389);
      this.ssv_laneChange = (SettingSelectView)this.mView.findViewById(2131363405);
      this.ssv_automatic = (SettingSelectView)this.mView.findViewById(2131363386);
      this.ssv_day = (SettingSelectView)this.mView.findViewById(2131363393);
      this.sdv_openTime = (SettingDialogView)this.mView.findViewById(2131363276);
      this.sdv_travel = (SettingDialogView)this.mView.findViewById(2131363284);
      this.sdv_ambient_three = (SettingDialogView)this.mView.findViewById(2131363263);
      this.sdv_ambient_ten = (SettingDialogView)this.mView.findViewById(2131363262);
      this.sdv_ambient_twenty = (SettingDialogView)this.mView.findViewById(2131363264);
      this.spv_instrument = (SettingProgressView)this.mView.findViewById(2131363356);
      this.spv_door = (SettingProgressView)this.mView.findViewById(2131363352);
      this.spv_footwell = (SettingProgressView)this.mView.findViewById(2131363353);
      this.sdv_coming = (SettingProgressView)this.mView.findViewById(2131363265);
      this.sdv_leaving = (SettingProgressView)this.mView.findViewById(2131363273);
      this.spv_ambient_car_in = (SettingProgressView)this.mView.findViewById(2131363349);
      this.spv_ambient_car_right = (SettingProgressView)this.mView.findViewById(2131363350);
      this.spv_ambient_car_all = (SettingProgressView)this.mView.findViewById(2131363348);
      this.spv_big_light = (SettingProgressView)this.mView.findViewById(2131363351);
      this.spv_light_system = (SettingProgressView)this.mView.findViewById(2131363357);
      this.executorService.execute(new Runnable(this) {
         final CarSettingLightView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listAmbientThree.add(new SettingDialogBean(this.this$0.mContext.getString(2131760453)));
            this.this$0.listAmbientThree.add(new SettingDialogBean(this.this$0.mContext.getString(2131760454)));
            this.this$0.listAmbientThree.add(new SettingDialogBean(this.this$0.mContext.getString(2131760466)));
            this.this$0.listAmbientThree.add(new SettingDialogBean(this.this$0.mContext.getString(2131760477)));
            this.this$0.sdv_ambient_three.setListFirst(this.this$0.listAmbientThree);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingLightView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760453)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760488)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760492)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760493)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760494)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760495)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760496)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760455)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760456)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760457)));
            this.this$0.listAmbientTen.add(new SettingDialogBean(this.this$0.mContext.getString(2131760458)));
            this.this$0.sdv_ambient_ten.setListFirst(this.this$0.listAmbientTen);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingLightView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760453)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760488)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760492)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760493)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760494)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760495)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760496)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760455)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760456)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760457)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760458)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760459)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760460)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760461)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760462)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760463)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760464)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760465)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760467)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760468)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760469)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760481)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760482)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760483)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760484)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760485)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760486)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760487)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760489)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760490)));
            this.this$0.listAmbientTwenty.add(new SettingDialogBean(this.this$0.mContext.getString(2131760491)));
            this.this$0.sdv_ambient_twenty.setListFirst(this.this$0.listAmbientTwenty);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingLightView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listOpenTime.add(new SettingDialogBean(this.this$0.mContext.getString(2131760664)));
            this.this$0.listOpenTime.add(new SettingDialogBean(this.this$0.mContext.getString(2131760667)));
            this.this$0.listOpenTime.add(new SettingDialogBean(this.this$0.mContext.getString(2131760665)));
            this.this$0.sdv_openTime.setListFirst(this.this$0.listOpenTime);
         }
      });
      this.executorService.execute(new Runnable(this) {
         final CarSettingLightView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listTravel.add(new SettingDialogBean(this.this$0.mContext.getString(2131760666)));
            this.this$0.listTravel.add(new SettingDialogBean(this.this$0.mContext.getString(2131760668)));
            this.this$0.sdv_travel.setListFirst(this.this$0.listTravel);
         }
      });
      this.spv_light_system.setMinProgress(1);
      this.spv_light_system.setAll(4, 1.0F, "");
      this.spv_big_light.setAll(6, 1.0F, "");
      this.spv_ambient_car_in.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.spv_ambient_car_right.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.spv_ambient_car_all.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.spv_instrument.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.spv_door.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.spv_footwell.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.sdv_coming.setAll(6, 5.0F, this.mContext.getString(2131760670));
      this.sdv_leaving.setAll(6, 5.0F, this.mContext.getString(2131760670));
      this.sdv_coming.setBigAndSmallValueText(this.mContext.getString(2131760598), "");
      this.sdv_leaving.setBigAndSmallValueText(this.mContext.getString(2131760598), "");
      this.spv_light_system.setBigAndSmallValueText("1.0", "");
      this.spv_big_light.setBigAndSmallValueText(this.mContext.getString(2131760598), "");
      this.spv_ambient_car_in.setBigAndSmallValueText(this.mContext.getString(2131760497), "");
      this.spv_ambient_car_right.setBigAndSmallValueText(this.mContext.getString(2131760497), "");
      this.spv_ambient_car_all.setBigAndSmallValueText(this.mContext.getString(2131760497), "");
   }

   // $FF: synthetic method
   static void lambda$initClick$2(View var0, int var1) {
      int var2 = var0.getId();
      if (var2 != 2131363276) {
         if (var2 != 2131363284) {
            switch (var2) {
               case 2131363262:
                  if (var1 == 0) {
                     MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)var1});
                  } else {
                     MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)(var1 + 3)});
                  }
                  break;
               case 2131363263:
                  MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)var1});
                  break;
               case 2131363264:
                  if (var1 == 0) {
                     MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)var1});
                  } else {
                     MessageSender.sendMsg(new byte[]{22, 109, 12, (byte)(var1 + 3)});
                  }
            }
         } else {
            MessageSender.sendMsg(new byte[]{22, 109, 6, (byte)var1});
         }
      } else {
         MessageSender.sendMsg(new byte[]{22, 109, 3, (byte)var1});
      }

   }

   private void sendMsg(byte var1, boolean var2) {
      MessageSender.sendMsg((byte)109, var1, var2);
   }

   // $FF: synthetic method
   void lambda$initClick$1$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingLightView(View var1, boolean var2) {
      switch (var1.getId()) {
         case 2131363381:
            this.sendMsg((byte)1, var2);
            break;
         case 2131363386:
            this.sendMsg((byte)4, var2);
            break;
         case 2131363389:
            this.sendMsg((byte)2, var2);
            break;
         case 2131363393:
            this.sendMsg((byte)16, var2);
            break;
         case 2131363405:
            this.sendMsg((byte)5, var2);
      }

   }

   // $FF: synthetic method
   void lambda$initClick$3$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingLightView(View var1, int var2) {
      this.isSeekbar = true;
      int var3 = var1.getId();
      if (var3 != 2131363265) {
         if (var3 != 2131363273) {
            switch (var3) {
               case 2131363348:
                  MessageSender.sendMsg(new byte[]{22, 109, 15, (byte)((int)((float)var2 * this.spv_ambient_car_all.getInterval()))});
                  break;
               case 2131363349:
                  MessageSender.sendMsg(new byte[]{22, 109, 13, (byte)((int)((float)var2 * this.spv_ambient_car_in.getInterval()))});
                  break;
               case 2131363350:
                  MessageSender.sendMsg(new byte[]{22, 109, 14, (byte)((int)((float)var2 * this.spv_ambient_car_right.getInterval()))});
                  break;
               case 2131363351:
                  MessageSender.sendMsg(new byte[]{22, 109, 17, (byte)((int)((float)var2 * this.spv_big_light.getInterval()))});
                  break;
               case 2131363352:
                  MessageSender.sendMsg(new byte[]{22, 109, 8, (byte)((int)((float)var2 * this.spv_door.getInterval()))});
                  break;
               case 2131363353:
                  MessageSender.sendMsg(new byte[]{22, 109, 9, (byte)((int)((float)var2 * this.spv_footwell.getInterval()))});
                  break;
               default:
                  switch (var3) {
                     case 2131363356:
                        MessageSender.sendMsg(new byte[]{22, 109, 7, (byte)(var2 * (int)this.spv_instrument.getInterval())});
                        break;
                     case 2131363357:
                        MessageSender.sendMsg(new byte[]{22, 109, 18, (byte)((int)((float)var2 * this.spv_light_system.getInterval()))});
                  }
            }
         } else {
            MessageSender.sendMsg(new byte[]{22, 109, 11, (byte)((int)((float)var2 * this.sdv_leaving.getInterval()))});
         }
      } else {
         MessageSender.sendMsg(new byte[]{22, 109, 10, (byte)((int)((float)var2 * this.sdv_coming.getInterval()))});
      }

   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingLightView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.ssv_assist.setSelect(GeneralDzData.light_assis);
      this.ssv_bend.setSelect(GeneralDzData.light_bend);
      this.ssv_laneChange.setSelect(GeneralDzData.light_change);
      this.ssv_automatic.setSelect(GeneralDzData.light_automatic);
      this.ssv_day.setSelect(GeneralDzData.light_day);
      this.sdv_openTime.setSelect(GeneralDzData.light_openTime);
      this.sdv_travel.setSelect(GeneralDzData.light_travel);
      if (GeneralDzData.light_ambient >= 14) {
         this.sdv_ambient_twenty.setSelect(GeneralDzData.light_ambient - 3);
         this.sdv_ambient_ten.setSelect(0);
         this.sdv_ambient_three.setSelect(0);
      } else if (GeneralDzData.light_ambient >= 4) {
         this.sdv_ambient_twenty.setSelect(GeneralDzData.light_ambient - 3);
         this.sdv_ambient_ten.setSelect(GeneralDzData.light_ambient - 3);
         this.sdv_ambient_three.setSelect(0);
      } else if (GeneralDzData.light_ambient > 0) {
         this.sdv_ambient_twenty.setSelect(0);
         this.sdv_ambient_ten.setSelect(0);
         this.sdv_ambient_three.setSelect(GeneralDzData.light_ambient);
      } else if (GeneralDzData.light_ambient == 0) {
         this.sdv_ambient_twenty.setSelect(0);
         this.sdv_ambient_ten.setSelect(0);
         this.sdv_ambient_three.setSelect(0);
      }

      if (!this.isSeekbar) {
         this.spv_instrument.setValueProgress(GeneralDzData.light_instrument);
         this.spv_door.setValueProgress(GeneralDzData.light_door);
         this.spv_footwell.setValueProgress(GeneralDzData.light_footwell);
         this.sdv_coming.setValueProgress(GeneralDzData.light_coming);
         this.sdv_leaving.setValueProgress(GeneralDzData.light_leaving);
         this.spv_ambient_car_in.setValueProgress(GeneralDzData.light_ambient_in);
         this.spv_ambient_car_right.setValueProgress(GeneralDzData.light_ambient_right);
         this.spv_ambient_car_all.setValueProgress(GeneralDzData.light_ambient_all);
         this.spv_big_light.setValueProgress(GeneralDzData.light_big);
         this.spv_light_system.setValueProgress(GeneralDzData.light_system);
      } else {
         this.mHandler.removeMessages(0);
         this.mHandler.sendEmptyMessageDelayed(0, 200L);
      }

   }
}
