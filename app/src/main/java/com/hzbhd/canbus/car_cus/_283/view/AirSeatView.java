package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

public class AirSeatView {
   private static final int LEFT_FRONT_HOT = 3;
   private static final int LEFT_FRONT_VENTILATE = 1;
   private static final int RIGHT_FRONT_HOT = 4;
   private static final int RIGHT_FRONT_VENTILATE = 2;
   private BtnView btn_left_front_hot_1;
   private BtnView btn_left_front_hot_2;
   private BtnView btn_left_front_hot_3;
   private BtnView btn_left_front_ventilate_1;
   private BtnView btn_left_front_ventilate_2;
   private BtnView btn_left_front_ventilate_3;
   private BtnView btn_right_front_hot_1;
   private BtnView btn_right_front_hot_2;
   private BtnView btn_right_front_hot_3;
   private BtnView btn_right_front_ventilate_1;
   private BtnView btn_right_front_ventilate_2;
   private BtnView btn_right_front_ventilate_3;
   private boolean isShowing = false;
   private Context mContext;
   private WindowManager.LayoutParams mLayoutParams;
   private Runnable mRunnable = new Runnable(this) {
      final AirSeatView this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.dismissView();
      }
   };
   private View mView;
   private WindowManager mWindowManager;
   private TextView tv_left_front_hot;
   private TextView tv_left_front_ventilate;
   private TextView tv_right_front_hot;
   private TextView tv_right_front_ventilate;

   public AirSeatView(Context var1) {
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558441, (ViewGroup)null);
      this.mWindowManager = (WindowManager)this.mContext.getSystemService("window");
      this.initView();
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2038;
         this.mLayoutParams.gravity = 80;
         this.mLayoutParams.width = -1;
         this.mLayoutParams.height = -2;
         this.mLayoutParams.format = 1;
         this.mLayoutParams.flags = 8;
      }

      if (!this.isShowing) {
         this.mWindowManager.addView(this.mView, this.mLayoutParams);
         this.isShowing = true;
      }

      this.mView.removeCallbacks(this.mRunnable);
      this.mView.postDelayed(this.mRunnable, 5000L);
   }

   private void dismissView() {
      WindowManager var1 = this.mWindowManager;
      if (var1 != null) {
         View var2 = this.mView;
         if (var2 != null) {
            var1.removeView(var2);
            this.isShowing = false;
         }
      }

   }

   private void initView() {
      this.tv_left_front_ventilate = (TextView)this.mView.findViewById(2131363639);
      this.tv_right_front_ventilate = (TextView)this.mView.findViewById(2131363681);
      this.tv_left_front_hot = (TextView)this.mView.findViewById(2131363636);
      this.tv_right_front_hot = (TextView)this.mView.findViewById(2131363678);
      this.btn_left_front_ventilate_1 = (BtnView)this.mView.findViewById(2131362028);
      this.btn_left_front_ventilate_2 = (BtnView)this.mView.findViewById(2131362029);
      this.btn_left_front_ventilate_3 = (BtnView)this.mView.findViewById(2131362030);
      this.btn_right_front_ventilate_1 = (BtnView)this.mView.findViewById(2131362049);
      this.btn_right_front_ventilate_2 = (BtnView)this.mView.findViewById(2131362050);
      this.btn_right_front_ventilate_3 = (BtnView)this.mView.findViewById(2131362051);
      this.btn_left_front_hot_1 = (BtnView)this.mView.findViewById(2131362025);
      this.btn_left_front_hot_2 = (BtnView)this.mView.findViewById(2131362026);
      this.btn_left_front_hot_3 = (BtnView)this.mView.findViewById(2131362027);
      this.btn_right_front_hot_1 = (BtnView)this.mView.findViewById(2131362046);
      this.btn_right_front_hot_2 = (BtnView)this.mView.findViewById(2131362047);
      this.btn_right_front_hot_3 = (BtnView)this.mView.findViewById(2131362048);
      this.setClick(1, this.tv_left_front_ventilate, this.btn_left_front_ventilate_1, this.btn_left_front_ventilate_2, this.btn_left_front_ventilate_3);
      this.setClick(2, this.tv_right_front_ventilate, this.btn_right_front_ventilate_1, this.btn_right_front_ventilate_2, this.btn_right_front_ventilate_3);
      this.setClick(3, this.tv_left_front_hot, this.btn_left_front_hot_1, this.btn_left_front_hot_2, this.btn_left_front_hot_3);
      this.setClick(4, this.tv_right_front_hot, this.btn_right_front_hot_1, this.btn_right_front_hot_2, this.btn_right_front_hot_3);
   }

   // $FF: synthetic method
   static void lambda$setClick$0(int var0, View var1) {
      if (var0 != 1) {
         if (var0 != 2) {
            if (var0 != 3) {
               if (var0 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 0});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 0});
      }

   }

   // $FF: synthetic method
   static void lambda$setClick$1(int var0, View var1) {
      if (var0 != 1) {
         if (var0 != 2) {
            if (var0 != 3) {
               if (var0 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 1});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 1});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$setClick$2(int var0, View var1) {
      if (var0 != 1) {
         if (var0 != 2) {
            if (var0 != 3) {
               if (var0 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 2});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 2});
      }

   }

   // $FF: synthetic method
   static void lambda$setClick$3(int var0, View var1) {
      if (var0 != 1) {
         if (var0 != 2) {
            if (var0 != 3) {
               if (var0 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, 3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, 3});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, 3});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, 3});
      }

   }

   private void setClick(int var1, TextView var2, BtnView var3, BtnView var4, BtnView var5) {
      var2.setOnClickListener(new AirSeatView$$ExternalSyntheticLambda0(var1));
      var3.setOnClickListener(new AirSeatView$$ExternalSyntheticLambda1(var1));
      var4.setOnClickListener(new AirSeatView$$ExternalSyntheticLambda2(var1));
      var5.setOnClickListener(new AirSeatView$$ExternalSyntheticLambda3(var1));
   }

   private void setGrade(int var1, TextView var2, BtnView var3, BtnView var4, BtnView var5) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  var2.setText(2131760436);
                  var3.setSelect(true);
                  var4.setSelect(true);
                  var5.setSelect(true);
               }
            } else {
               var2.setText(2131760436);
               var3.setSelect(true);
               var4.setSelect(true);
               var5.setSelect(false);
            }
         } else {
            var2.setText(2131760436);
            var3.setSelect(true);
            var4.setSelect(false);
            var5.setSelect(false);
         }
      } else {
         var2.setText(2131760435);
         var3.setSelect(false);
         var4.setSelect(false);
         var5.setSelect(false);
      }

   }

   public void refreshUi() {
      this.setGrade(GeneralDzData.left_front_ventilate, this.tv_left_front_ventilate, this.btn_left_front_ventilate_1, this.btn_left_front_ventilate_2, this.btn_left_front_ventilate_3);
      this.setGrade(GeneralDzData.right_front_ventilate, this.tv_right_front_ventilate, this.btn_right_front_ventilate_1, this.btn_right_front_ventilate_2, this.btn_right_front_ventilate_3);
      this.setGrade(GeneralDzData.left_front_hot, this.tv_left_front_hot, this.btn_left_front_hot_1, this.btn_left_front_hot_2, this.btn_left_front_hot_3);
      this.setGrade(GeneralDzData.right_front_hot, this.tv_right_front_hot, this.btn_right_front_hot_1, this.btn_right_front_hot_2, this.btn_right_front_hot_3);
      this.addViewToWindow();
   }
}
