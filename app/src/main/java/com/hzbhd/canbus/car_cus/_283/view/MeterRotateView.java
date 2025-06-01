package com.hzbhd.canbus.car_cus._283.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MeterRotateView extends ConstraintLayout {
   private ImageView imageDown;
   private ImageView imagePointer;
   private ImageView imageSelected;
   private ImageView imageSelectedAnimator;
   private ImageView imageUp;
   private int[] leftImages;
   private int[] leftTitles;
   private OnUpDownClickListener mOnUpDownClickListener;
   private RelativeLayout relativeLayout;
   private int[] rightImages;
   private int[] rightTitles;
   private TextView textView;

   public MeterRotateView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MeterRotateView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MeterRotateView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.leftImages = new int[]{2131231970, 2131232051, 2131232015, 2131231943, 2131232047, 2131231972, 2131232017, 2131232019};
      this.leftTitles = new int[]{2131760681, 2131760688, 2131760689, 2131760690, 2131760693, 2131760694, 2131760696, 2131760697};
      this.rightImages = new int[]{2131231951, 2131232047, 2131231979, 2131231974, 2131232047, 2131231972, 2131232017, 2131232019};
      this.rightTitles = new int[]{2131760695, 2131760701, 2131760691, 2131760689, 2131760693, 2131760694, 2131760696, 2131760697};
      LayoutInflater.from(var1).inflate(2131558484, this, true);
   }

   public int getLeftImagesLength() {
      return this.leftImages.length;
   }

   public int getRightImagesLength() {
      return this.rightImages.length;
   }

   // $FF: synthetic method
   void lambda$onFinishInflate$0$com_hzbhd_canbus_car_cus__283_view_MeterRotateView(View var1) {
      OnUpDownClickListener var2 = this.mOnUpDownClickListener;
      if (var2 != null) {
         var2.onUp(this);
      }

   }

   // $FF: synthetic method
   void lambda$onFinishInflate$1$com_hzbhd_canbus_car_cus__283_view_MeterRotateView(View var1) {
      OnUpDownClickListener var2 = this.mOnUpDownClickListener;
      if (var2 != null) {
         var2.onDown(this);
      }

   }

   // $FF: synthetic method
   void lambda$onFinishInflate$2$com_hzbhd_canbus_car_cus__283_view_MeterRotateView(View var1) {
      OnUpDownClickListener var2 = this.mOnUpDownClickListener;
      if (var2 != null) {
         var2.onCenter(this);
      }

   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.imageUp = (ImageView)this.findViewById(2131362471);
      this.imageDown = (ImageView)this.findViewById(2131362467);
      this.imagePointer = (ImageView)this.findViewById(2131362468);
      this.imageSelected = (ImageView)this.findViewById(2131362469);
      this.imageSelectedAnimator = (ImageView)this.findViewById(2131362470);
      this.relativeLayout = (RelativeLayout)this.findViewById(2131363090);
      this.textView = (TextView)this.findViewById(2131363519);
      this.imageUp.setOnClickListener(new MeterRotateView$$ExternalSyntheticLambda0(this));
      this.imageDown.setOnClickListener(new MeterRotateView$$ExternalSyntheticLambda1(this));
      this.imageSelected.setOnClickListener(new MeterRotateView$$ExternalSyntheticLambda2(this));
   }

   public void setLeftShow() {
      ConstraintLayout.LayoutParams var1 = (ConstraintLayout.LayoutParams)this.relativeLayout.getLayoutParams();
      var1.endToEnd = -1;
      var1.setMarginStart((int)this.getContext().getResources().getDimension(2131169744));
      this.relativeLayout.setLayoutParams(var1);
   }

   public void setOnLongClickListener(View.OnLongClickListener var1) {
      this.relativeLayout.setOnLongClickListener(var1);
      this.imageSelected.setOnLongClickListener(var1);
   }

   public void setOnUpDownClickListener(OnUpDownClickListener var1) {
      this.mOnUpDownClickListener = var1;
   }

   public void setPointerVisible(int var1) {
      this.imagePointer.setVisibility(var1);
   }

   public void setRightShow() {
      ConstraintLayout.LayoutParams var1 = (ConstraintLayout.LayoutParams)this.relativeLayout.getLayoutParams();
      var1.startToStart = -1;
      var1.setMarginEnd((int)this.getContext().getResources().getDimension(2131169744));
      this.relativeLayout.setLayoutParams(var1);
   }

   public void setRotatePointer(int var1) {
      ObjectAnimator var2 = ObjectAnimator.ofFloat(this.imagePointer, "rotation", new float[]{(float)var1});
      var2.setDuration(200L);
      var2.start();
   }

   public void setSelectLeftImage(int var1) {
      if (var1 >= 0) {
         int[] var2 = this.leftImages;
         if (var1 < var2.length) {
            this.imageSelected.setImageResource(var2[var1]);
            this.imageSelectedAnimator.setImageResource(this.leftImages[var1]);
            this.textView.setText(this.leftTitles[var1]);
         }
      }

   }

   public void setSelectRightImage(int var1) {
      if (var1 >= 0) {
         int[] var2 = this.rightImages;
         if (var1 < var2.length) {
            this.imageSelected.setImageResource(var2[var1]);
            this.imageSelectedAnimator.setImageResource(this.rightImages[var1]);
            this.textView.setText(this.rightTitles[var1]);
         }
      }

   }

   public void setSelectedImageVisible(int var1) {
      this.imageSelected.setVisibility(var1);
      this.imageSelectedAnimator.setVisibility(var1);
   }

   public void setVisibleImage(int var1) {
      this.imageUp.setVisibility(var1);
      this.imageDown.setVisibility(var1);
   }

   public void startAnimatorPointer(int var1) {
      ImageView var4 = this.imagePointer;
      float var3 = (float)(var1 + 35);
      float var2 = (float)(var1 + 5);
      ObjectAnimator var5 = ObjectAnimator.ofFloat(var4, "rotation", new float[]{-150.0F, var3, var2, (float)(var1 + 15), (float)(var1 - 5), var2, (float)(var1 - 3), (float)(var1 + 3), (float)(var1 - 1), (float)(var1 + 1), (float)var1});
      var5.setDuration(2000L);
      var5.start();
   }

   public void startDisPlay2Animator(boolean var1) {
      ImageView var6 = this.imageSelected;
      int var4 = (int)var6.getX();
      int var2 = (int)var6.getY();
      int var3 = (int)this.getContext().getResources().getDimension(2131178338);
      AnimatorSet var8 = new AnimatorSet();
      ObjectAnimator var5 = ObjectAnimator.ofFloat(var6, "translationX", new float[]{(float)(-var4)});
      ObjectAnimator var9 = ObjectAnimator.ofFloat(var6, "translationY", new float[]{(float)(-(var2 - var3))});
      ObjectAnimator var7 = ObjectAnimator.ofFloat(var6, "alpha", new float[]{1.0F, 0.7F});
      var8.playTogether(new Animator[]{var5, var9, var7});
      var9.setDuration(600L);
      var5.setDuration(800L);
      var7.setDuration(600L);
      var8.start();
      var6.setEnabled(false);
      var8.addListener(new Animator.AnimatorListener(this, var6) {
         final MeterRotateView this$0;
         final ImageView val$relative;

         {
            this.this$0 = var1;
            this.val$relative = var2;
         }

         public void onAnimationCancel(Animator var1) {
            this.val$relative.setTranslationY(0.0F);
            this.val$relative.setTranslationX(0.0F);
            this.val$relative.setAlpha(1.0F);
            this.val$relative.setEnabled(true);
         }

         public void onAnimationEnd(Animator var1) {
            this.val$relative.setTranslationY(0.0F);
            this.val$relative.setTranslationX(0.0F);
            this.val$relative.setAlpha(1.0F);
            this.val$relative.setEnabled(true);
         }

         public void onAnimationRepeat(Animator var1) {
         }

         public void onAnimationStart(Animator var1) {
         }
      });
   }

   public interface OnUpDownClickListener {
      void onCenter(View var1);

      void onDown(View var1);

      void onUp(View var1);
   }
}
