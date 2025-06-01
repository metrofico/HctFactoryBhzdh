package com.hzbhd.canbus.car._447;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class ID447RadarView extends RelativeLayout {
   private ImageView rear_left;
   private ImageView rear_left_mid;
   private ImageView rear_right;
   private ImageView rear_right_mid;
   private View view;

   public ID447RadarView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ID447RadarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public ID447RadarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558618, this, true);
      this.view = var4;
      this.rear_left = (ImageView)var4.findViewById(2131363047);
      this.rear_left_mid = (ImageView)this.view.findViewById(2131363050);
      this.rear_right_mid = (ImageView)this.view.findViewById(2131363067);
      this.rear_right = (ImageView)this.view.findViewById(2131363063);
   }

   public void updateRadar() {
      BaseUtil.INSTANCE.runUi(new Function0(this) {
         final ID447RadarView this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            if (ID447Data.rearLeftRange == 0) {
               this.this$0.rear_left.setBackgroundResource(2131232134);
            } else if (ID447Data.rearLeftRange == 1) {
               this.this$0.rear_left.setBackgroundResource(2131232126);
            } else if (ID447Data.rearLeftRange == 2) {
               this.this$0.rear_left.setBackgroundResource(2131232127);
            } else if (ID447Data.rearLeftRange == 3) {
               this.this$0.rear_left.setBackgroundResource(2131232128);
            } else if (ID447Data.rearLeftRange == 4) {
               this.this$0.rear_left.setBackgroundResource(2131232129);
            }

            if (ID447Data.rearLeftMidRange == 0) {
               this.this$0.rear_left_mid.setBackgroundResource(2131232134);
            } else if (ID447Data.rearLeftMidRange == 1) {
               this.this$0.rear_left_mid.setBackgroundResource(2131232130);
            } else if (ID447Data.rearLeftMidRange == 2) {
               this.this$0.rear_left_mid.setBackgroundResource(2131232131);
            } else if (ID447Data.rearLeftMidRange == 3) {
               this.this$0.rear_left_mid.setBackgroundResource(2131232132);
            } else if (ID447Data.rearLeftMidRange == 4) {
               this.this$0.rear_left_mid.setBackgroundResource(2131232133);
            }

            if (ID447Data.rearRightMidRange == 0) {
               this.this$0.rear_right_mid.setBackgroundResource(2131232134);
            } else if (ID447Data.rearRightMidRange == 1) {
               this.this$0.rear_right_mid.setBackgroundResource(2131232122);
            } else if (ID447Data.rearRightMidRange == 2) {
               this.this$0.rear_right_mid.setBackgroundResource(2131232123);
            } else if (ID447Data.rearRightMidRange == 3) {
               this.this$0.rear_right_mid.setBackgroundResource(2131232124);
            } else if (ID447Data.rearRightMidRange == 4) {
               this.this$0.rear_right_mid.setBackgroundResource(2131232125);
            }

            if (ID447Data.rearRightRange == 0) {
               this.this$0.rear_right.setBackgroundResource(2131232134);
            } else if (ID447Data.rearRightRange == 1) {
               this.this$0.rear_right.setBackgroundResource(2131232118);
            } else if (ID447Data.rearRightRange == 2) {
               this.this$0.rear_right.setBackgroundResource(2131232119);
            } else if (ID447Data.rearRightRange == 3) {
               this.this$0.rear_right.setBackgroundResource(2131232120);
            } else if (ID447Data.rearRightRange == 4) {
               this.this$0.rear_right.setBackgroundResource(2131232121);
            }

            return null;
         }
      });
   }
}
