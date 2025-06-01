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

public class HybridView extends RelativeLayout {
   private ExecutorService executorService;
   private boolean isSeekbar;
   private List listA;
   private Context mContext;
   private Handler mHandler;
   private View mView;
   private SettingDialogView sdv;
   private SettingProgressView spv;
   private SettingProgressView spv_temp;
   private SettingSelectView ssv;

   public HybridView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public HybridView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public HybridView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.executorService = Executors.newSingleThreadExecutor();
      this.listA = new ArrayList();
      this.isSeekbar = false;
      this.mHandler = new Handler(this, Looper.getMainLooper()) {
         final HybridView this$0;

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
      this.mView = LayoutInflater.from(var1).inflate(2131558468, this, true);
      this.initView();
      this.initClick();
      (new Handler()).postDelayed(new HybridView$$ExternalSyntheticLambda5(this), 1000L);
   }

   private int getSelectPositionA(int var1) {
      if (var1 != 10) {
         if (var1 != 13) {
            return var1 != 255 ? 0 : 3;
         } else {
            return 2;
         }
      } else {
         return 1;
      }
   }

   private int getTempProgress(int var1) {
      if (var1 == 254) {
         return 0;
      } else if (var1 == 255) {
         return 27;
      } else {
         return var1 <= 59 && var1 > 32 ? var1 - 32 : 0;
      }
   }

   private void initClick() {
      this.ssv.setOnItemClickListener(new HybridView$$ExternalSyntheticLambda1());
      this.sdv.setOnItemClickListener(new HybridView$$ExternalSyntheticLambda2());
      this.spv.setOnItemClickListener(new HybridView$$ExternalSyntheticLambda3(this));
      this.spv_temp.setOnItemClickListener(new HybridView$$ExternalSyntheticLambda4(this));
   }

   private void initView() {
      this.sdv = (SettingDialogView)this.mView.findViewById(2131363260);
      this.ssv = (SettingSelectView)this.mView.findViewById(2131363374);
      this.spv = (SettingProgressView)this.mView.findViewById(2131363347);
      this.spv_temp = (SettingProgressView)this.mView.findViewById(2131363360);
      this.executorService.execute(new HybridView$$ExternalSyntheticLambda0(this));
      this.spv.setAll(10, 10.0F, this.mContext.getString(2131760669));
      this.spv.setBigAndSmallValueText("", "");
      this.spv_temp.setMaxAndMinProgress(0, 27);
      this.spv_temp.setInterval(0.5F);
      this.spv_temp.setMinProgress(16);
      this.spv_temp.setBigAndSmallValueText(this.getContext().getString(2131760634), this.getContext().getString(2131760643));
   }

   // $FF: synthetic method
   static void lambda$initClick$2(View var0, boolean var1) {
      MessageSender.sendMsg((byte)77, (byte)3, var1);
   }

   // $FF: synthetic method
   static void lambda$initClick$3(View var0, int var1) {
      byte var2;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               var2 = 5;
            } else {
               var2 = -1;
            }
         } else {
            var2 = 13;
         }
      } else {
         var2 = 10;
      }

      MessageSender.sendMsg(new byte[]{22, 77, 1, var2});
   }

   // $FF: synthetic method
   void lambda$initClick$4$com_hzbhd_canbus_car_cus__283_view_carsetting_HybridView(View var1, int var2) {
      this.isSeekbar = true;
      MessageSender.sendMsg(new byte[]{22, 77, 4, (byte)((int)((float)var2 * this.spv.getInterval()))});
   }

   // $FF: synthetic method
   void lambda$initClick$5$com_hzbhd_canbus_car_cus__283_view_carsetting_HybridView(View var1, int var2) {
      this.isSeekbar = true;
      byte var3;
      if (var2 == 16) {
         var3 = -2;
      } else if (var2 == 43) {
         var3 = -1;
      } else {
         var3 = (byte)(var2 + 16);
      }

      MessageSender.sendMsg(new byte[]{22, 77, 2, var3});
   }

   // $FF: synthetic method
   void lambda$initView$1$com_hzbhd_canbus_car_cus__283_view_carsetting_HybridView() {
      this.listA.add(new SettingDialogBean(this.getContext().getString(2131760632)));
      this.listA.add(new SettingDialogBean(this.getContext().getString(2131760630)));
      this.listA.add(new SettingDialogBean(this.getContext().getString(2131760631)));
      this.listA.add(new SettingDialogBean(this.getContext().getString(2131760633)));
      this.sdv.setListFirst(this.listA);
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car_cus__283_view_carsetting_HybridView() {
      this.refreshUi();
   }

   public void refreshUi() {
      this.ssv.setSelect(GeneralDzData.hybrid_air_use_electricity);
      this.sdv.setSelect(this.getSelectPositionA(GeneralDzData.hybrid_big_charge_current));
      if (!this.isSeekbar) {
         this.spv.setValueProgress(GeneralDzData.hybrid_low_charge);
         this.spv_temp.setProgressNoInterval(this.getTempProgress(GeneralDzData.hybrid_temperature_in));
      } else {
         this.mHandler.removeMessages(0);
         this.mHandler.sendEmptyMessageDelayed(0, 200L);
      }

   }
}
