package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class BtnItemView extends RelativeLayout {
   private String mAction;
   private boolean mCanClick;
   private ImageButton mIb;
   private ImageView mIv;
   private OnItemClickListener mOnItemClickListener;

   public BtnItemView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public BtnItemView(Context var1, String var2, boolean var3) {
      super(var1);
      this.initViews(var1, var2, var3);
   }

   private void initViews(Context var1, String var2, boolean var3) {
      View var5 = LayoutInflater.from(var1).inflate(2131558853, this);
      this.mIv = (ImageView)var5.findViewById(2131362578);
      ImageButton var6 = (ImageButton)var5.findViewById(2131362429);
      this.mIb = var6;
      var6.setOnClickListener(new View.OnClickListener(this) {
         final BtnItemView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnItemClickListener != null) {
               this.this$0.mOnItemClickListener.onClick();
            }

         }
      });
      this.mCanClick = var3;
      this.mIb.setEnabled(var3);
      if (this.mCanClick) {
         this.mIb.setBackgroundResource(2131232313);
      } else {
         this.mIb.setBackgroundResource(2131232312);
      }

      this.mAction = var2;
      var2.hashCode();
      switch (var2) {
         case "rear_blow_positive":
         case "blow_positive":
            this.mIv.setImageResource(2131234028);
            break;
         case "right_set_seat_cold":
            this.mIv.setImageResource(2131234037);
            break;
         case "right_set_seat_heat":
            this.mIv.setImageResource(2131234038);
            break;
         case "steering_wheel_heating":
            this.mIv.setImageResource(2131234048);
            break;
         case "pollrn_removal":
            this.mIv.setImageResource(2131234013);
            break;
         case "small_wind_light":
            this.mIv.setImageResource(2131233979);
            break;
         case "windshield_deicing":
            this.mIv.setImageResource(2131233994);
            break;
         case "front_defog":
            this.mIv.setImageResource(2131234001);
            break;
         case "auto_manual":
         case "auto":
         case "auto_1_2":
            this.mIv.setImageResource(2131233972);
            break;
         case "ac_max":
            this.mIv.setImageResource(2131233968);
            break;
         case "auto_2":
            this.mIv.setImageResource(2131233971);
            break;
         case "auto_f":
            this.mIv.setImageResource(2131233977);
            break;
         case "auto_r":
            this.mIv.setImageResource(2131233978);
            break;
         case "clean_air":
            this.mIv.setImageResource(2131233993);
            break;
         case "ac_auto":
            this.mIv.setImageResource(2131233974);
            break;
         case "ac_econ":
         case "ac":
         case "rear_ac":
            this.mIv.setImageResource(2131233967);
            break;
         case "rear_blow_head_foot":
         case "blow_head_foot":
            this.mIv.setImageResource(2131234026);
            break;
         case "manual":
            this.mIv.setImageResource(2131234007);
            break;
         case "three_zone":
            this.mIv.setImageResource(2131233966);
            break;
         case "normal":
            this.mIv.setImageResource(2131234014);
            break;
         case "bose_center":
            this.mIv.setImageResource(2131233983);
            break;
         case "rear_auto":
            this.mIv.setImageResource(2131233978);
            break;
         case "rear_dual":
            this.mIv.setImageResource(2131233996);
            break;
         case "rear_lock":
            this.mIv.setImageResource(2131234031);
            break;
         case "rear_sync":
         case "sync":
            this.mIv.setImageResource(2131234045);
            break;
         case "rear_defog":
            this.mIv.setImageResource(2131234035);
            break;
         case "rear_power":
            this.mIv.setImageResource(2131234017);
            break;
         case "rear_blow_foot":
            this.mIv.setImageResource(2131234020);
            break;
         case "rear_blow_head":
            this.mIv.setImageResource(2131234019);
            break;
         case "left_set_seat_cold":
            this.mIv.setImageResource(2131234036);
            break;
         case "left_set_seat_heat":
            this.mIv.setImageResource(2131234039);
            break;
         case "blow_negative":
            this.mIv.setImageResource(2131234027);
            break;
         case "seat_steering_wheel_synchronization":
            this.mIv.setImageResource(2131234040);
            break;
         case "max_front":
            this.mIv.setImageResource(2131234009);
            break;
         case "big_wind_light":
            this.mIv.setImageResource(2131233975);
            break;
         case "front_window_heat":
            this.mIv.setImageResource(2131234002);
            break;
         case "auto_cycle":
            this.mIv.setImageResource(2131232333);
            break;
         case "auto_defog":
            this.mIv.setImageResource(2131233976);
            break;
         case "amb":
            this.mIv.setImageResource(2131233969);
            break;
         case "aqs":
            this.mIv.setImageResource(2131233970);
            break;
         case "eco":
            this.mIv.setImageResource(2131233997);
            break;
         case "ion":
            this.mIv.setImageResource(2131234005);
            break;
         case "off":
            this.mIv.setImageResource(2131234015);
            break;
         case "left_seat_heat":
            this.mIv.setImageResource(2131234110);
            break;
         case "dual":
            this.mIv.setImageResource(2131233996);
            break;
         case "econ":
            this.mIv.setImageResource(2131233998);
            break;
         case "fast":
            this.mIv.setImageResource(2131233999);
            break;
         case "mono":
            this.mIv.setImageResource(2131234012);
            break;
         case "rear":
            this.mIv.setImageResource(2131234030);
            break;
         case "rest":
            this.mIv.setImageResource(2131234033);
            break;
         case "soft":
            this.mIv.setImageResource(2131234042);
            break;
         case "sync_temperature":
            this.mIv.setImageResource(2131234047);
            break;
         case "in_out_auto_cycle":
            this.mIv.setImageResource(2131233991);
            break;
         case "power":
            this.mIv.setImageResource(2131234017);
            break;
         case "setup":
            this.mIv.setImageResource(2131234041);
            break;
         case "swing":
            this.mIv.setImageResource(2131234044);
            break;
         case "blow_window":
            this.mIv.setImageResource(2131234021);
            break;
         case "cycle_in_out_close":
            this.mIv.setImageResource(2131233991);
            break;
         case "negative_ion":
            this.mIv.setImageResource(2131234000);
            break;
         case "max_cool":
            this.mIv.setImageResource(2131234008);
            break;
         case "max_heat":
            this.mIv.setImageResource(2131234010);
            break;
         case "in_out_cycle":
            this.mIv.setImageResource(2131233991);
            break;
         case "climate":
            this.mIv.setImageResource(2131233995);
            break;
         case "auto_wind_mode":
            this.mIv.setImageResource(2131234053);
            break;
         case "auto_wind_light":
            this.mIv.setImageResource(2131234056);
            break;
         case "auto_wind_strong":
            this.mIv.setImageResource(2131234055);
            break;
         case "right_seat_heat":
            this.mIv.setImageResource(2131234110);
            break;
         case "blow_foot":
            this.mIv.setImageResource(2131234020);
            break;
         case "blow_head":
            this.mIv.setImageResource(2131234019);
            break;
         case "blow_window_foot":
            this.mIv.setImageResource(2131234023);
            break;
         case "hybrid_ac":
            this.mIv.setImageResource(2131234004);
            break;
         case "left_right_tem_sync":
            this.mIv.setImageResource(2131234045);
      }

   }

   public void setImageResource(int var1) {
      this.mIv.setImageResource(var1);
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void turn(boolean var1) {
      if (var1) {
         if (this.mCanClick) {
            this.mIb.setBackgroundResource(2131232315);
         } else {
            this.mIb.setBackgroundResource(2131232314);
         }
      } else if (this.mCanClick) {
         this.mIb.setBackgroundResource(2131232313);
      } else {
         this.mIb.setBackgroundResource(2131232312);
      }

   }

   public interface OnItemClickListener {
      void onClick();
   }
}
