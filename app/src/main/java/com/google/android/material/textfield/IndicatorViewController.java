package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.legacy.widget.Space;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import java.util.ArrayList;
import java.util.List;

final class IndicatorViewController {
   private static final int CAPTION_OPACITY_FADE_ANIMATION_DURATION = 167;
   private static final int CAPTION_STATE_ERROR = 1;
   private static final int CAPTION_STATE_HELPER_TEXT = 2;
   private static final int CAPTION_STATE_NONE = 0;
   private static final int CAPTION_TRANSLATE_Y_ANIMATION_DURATION = 217;
   static final int COUNTER_INDEX = 2;
   static final int ERROR_INDEX = 0;
   static final int HELPER_INDEX = 1;
   private Animator captionAnimator;
   private FrameLayout captionArea;
   private int captionDisplayed;
   private int captionToShow;
   private final float captionTranslationYPx;
   private int captionViewsAdded;
   private final Context context;
   private boolean errorEnabled;
   private CharSequence errorText;
   private int errorTextAppearance;
   private TextView errorView;
   private CharSequence helperText;
   private boolean helperTextEnabled;
   private int helperTextTextAppearance;
   private TextView helperTextView;
   private LinearLayout indicatorArea;
   private int indicatorsAdded;
   private final TextInputLayout textInputView;
   private Typeface typeface;

   public IndicatorViewController(TextInputLayout var1) {
      Context var2 = var1.getContext();
      this.context = var2;
      this.textInputView = var1;
      this.captionTranslationYPx = (float)var2.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
   }

   private boolean canAdjustIndicatorPadding() {
      boolean var1;
      if (this.indicatorArea != null && this.textInputView.getEditText() != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void createCaptionAnimators(List var1, boolean var2, TextView var3, int var4, int var5, int var6) {
      if (var3 != null && var2 && (var4 == var6 || var4 == var5)) {
         if (var6 == var4) {
            var2 = true;
         } else {
            var2 = false;
         }

         var1.add(this.createCaptionOpacityAnimator(var3, var2));
         if (var6 == var4) {
            var1.add(this.createCaptionTranslationYAnimator(var3));
         }
      }

   }

   private ObjectAnimator createCaptionOpacityAnimator(TextView var1, boolean var2) {
      float var3;
      if (var2) {
         var3 = 1.0F;
      } else {
         var3 = 0.0F;
      }

      ObjectAnimator var4 = ObjectAnimator.ofFloat(var1, View.ALPHA, new float[]{var3});
      var4.setDuration(167L);
      var4.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
      return var4;
   }

   private ObjectAnimator createCaptionTranslationYAnimator(TextView var1) {
      ObjectAnimator var2 = ObjectAnimator.ofFloat(var1, View.TRANSLATION_Y, new float[]{-this.captionTranslationYPx, 0.0F});
      var2.setDuration(217L);
      var2.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
      return var2;
   }

   private TextView getCaptionViewFromDisplayState(int var1) {
      if (var1 != 1) {
         return var1 != 2 ? null : this.helperTextView;
      } else {
         return this.errorView;
      }
   }

   private boolean isCaptionStateError(int var1) {
      boolean var2 = true;
      if (var1 != 1 || this.errorView == null || TextUtils.isEmpty(this.errorText)) {
         var2 = false;
      }

      return var2;
   }

   private boolean isCaptionStateHelperText(int var1) {
      boolean var2;
      if (var1 == 2 && this.helperTextView != null && !TextUtils.isEmpty(this.helperText)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private void setCaptionViewVisibilities(int var1, int var2) {
      if (var1 != var2) {
         TextView var3;
         if (var2 != 0) {
            var3 = this.getCaptionViewFromDisplayState(var2);
            if (var3 != null) {
               var3.setVisibility(0);
               var3.setAlpha(1.0F);
            }
         }

         if (var1 != 0) {
            var3 = this.getCaptionViewFromDisplayState(var1);
            if (var3 != null) {
               var3.setVisibility(4);
               if (var1 == 1) {
                  var3.setText((CharSequence)null);
               }
            }
         }

         this.captionDisplayed = var2;
      }
   }

   private void setTextViewTypeface(TextView var1, Typeface var2) {
      if (var1 != null) {
         var1.setTypeface(var2);
      }

   }

   private void setViewGroupGoneIfEmpty(ViewGroup var1, int var2) {
      if (var2 == 0) {
         var1.setVisibility(8);
      }

   }

   private boolean shouldAnimateCaptionView(TextView var1, CharSequence var2) {
      boolean var3;
      if (!ViewCompat.isLaidOut(this.textInputView) || !this.textInputView.isEnabled() || this.captionToShow == this.captionDisplayed && var1 != null && TextUtils.equals(var1.getText(), var2)) {
         var3 = false;
      } else {
         var3 = true;
      }

      return var3;
   }

   private void updateCaptionViewsVisibility(int var1, int var2, boolean var3) {
      if (var3) {
         AnimatorSet var5 = new AnimatorSet();
         this.captionAnimator = var5;
         ArrayList var4 = new ArrayList();
         this.createCaptionAnimators(var4, this.helperTextEnabled, this.helperTextView, 2, var1, var2);
         this.createCaptionAnimators(var4, this.errorEnabled, this.errorView, 1, var1, var2);
         AnimatorSetCompat.playTogether(var5, var4);
         var5.addListener(new AnimatorListenerAdapter(this, var2, this.getCaptionViewFromDisplayState(var1), var1, this.getCaptionViewFromDisplayState(var2)) {
            final IndicatorViewController this$0;
            final int val$captionToHide;
            final int val$captionToShow;
            final TextView val$captionViewToHide;
            final TextView val$captionViewToShow;

            {
               this.this$0 = var1;
               this.val$captionToShow = var2;
               this.val$captionViewToHide = var3;
               this.val$captionToHide = var4;
               this.val$captionViewToShow = var5;
            }

            public void onAnimationEnd(Animator var1) {
               this.this$0.captionDisplayed = this.val$captionToShow;
               this.this$0.captionAnimator = null;
               TextView var2 = this.val$captionViewToHide;
               if (var2 != null) {
                  var2.setVisibility(4);
                  if (this.val$captionToHide == 1 && this.this$0.errorView != null) {
                     this.this$0.errorView.setText((CharSequence)null);
                  }
               }

            }

            public void onAnimationStart(Animator var1) {
               TextView var2 = this.val$captionViewToShow;
               if (var2 != null) {
                  var2.setVisibility(0);
               }

            }
         });
         var5.start();
      } else {
         this.setCaptionViewVisibilities(var1, var2);
      }

      this.textInputView.updateEditTextBackground();
      this.textInputView.updateLabelState(var3);
      this.textInputView.updateTextInputBoxState();
   }

   void addIndicator(TextView var1, int var2) {
      if (this.indicatorArea == null && this.captionArea == null) {
         LinearLayout var3 = new LinearLayout(this.context);
         this.indicatorArea = var3;
         var3.setOrientation(0);
         this.textInputView.addView(this.indicatorArea, -1, -2);
         FrameLayout var5 = new FrameLayout(this.context);
         this.captionArea = var5;
         this.indicatorArea.addView(var5, -1, new FrameLayout.LayoutParams(-2, -2));
         Space var6 = new Space(this.context);
         LinearLayout.LayoutParams var4 = new LinearLayout.LayoutParams(0, 0, 1.0F);
         this.indicatorArea.addView(var6, var4);
         if (this.textInputView.getEditText() != null) {
            this.adjustIndicatorPadding();
         }
      }

      if (this.isCaptionView(var2)) {
         this.captionArea.setVisibility(0);
         this.captionArea.addView(var1);
         ++this.captionViewsAdded;
      } else {
         this.indicatorArea.addView(var1, var2);
      }

      this.indicatorArea.setVisibility(0);
      ++this.indicatorsAdded;
   }

   void adjustIndicatorPadding() {
      if (this.canAdjustIndicatorPadding()) {
         ViewCompat.setPaddingRelative(this.indicatorArea, ViewCompat.getPaddingStart(this.textInputView.getEditText()), 0, ViewCompat.getPaddingEnd(this.textInputView.getEditText()), 0);
      }

   }

   void cancelCaptionAnimator() {
      Animator var1 = this.captionAnimator;
      if (var1 != null) {
         var1.cancel();
      }

   }

   boolean errorIsDisplayed() {
      return this.isCaptionStateError(this.captionDisplayed);
   }

   boolean errorShouldBeShown() {
      return this.isCaptionStateError(this.captionToShow);
   }

   CharSequence getErrorText() {
      return this.errorText;
   }

   int getErrorViewCurrentTextColor() {
      TextView var2 = this.errorView;
      int var1;
      if (var2 != null) {
         var1 = var2.getCurrentTextColor();
      } else {
         var1 = -1;
      }

      return var1;
   }

   ColorStateList getErrorViewTextColors() {
      TextView var1 = this.errorView;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getTextColors();
      } else {
         var2 = null;
      }

      return var2;
   }

   CharSequence getHelperText() {
      return this.helperText;
   }

   ColorStateList getHelperTextViewColors() {
      TextView var1 = this.helperTextView;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getTextColors();
      } else {
         var2 = null;
      }

      return var2;
   }

   int getHelperTextViewCurrentTextColor() {
      TextView var2 = this.helperTextView;
      int var1;
      if (var2 != null) {
         var1 = var2.getCurrentTextColor();
      } else {
         var1 = -1;
      }

      return var1;
   }

   boolean helperTextIsDisplayed() {
      return this.isCaptionStateHelperText(this.captionDisplayed);
   }

   boolean helperTextShouldBeShown() {
      return this.isCaptionStateHelperText(this.captionToShow);
   }

   void hideError() {
      this.errorText = null;
      this.cancelCaptionAnimator();
      if (this.captionDisplayed == 1) {
         if (this.helperTextEnabled && !TextUtils.isEmpty(this.helperText)) {
            this.captionToShow = 2;
         } else {
            this.captionToShow = 0;
         }
      }

      this.updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, this.shouldAnimateCaptionView(this.errorView, (CharSequence)null));
   }

   void hideHelperText() {
      this.cancelCaptionAnimator();
      int var1 = this.captionDisplayed;
      if (var1 == 2) {
         this.captionToShow = 0;
      }

      this.updateCaptionViewsVisibility(var1, this.captionToShow, this.shouldAnimateCaptionView(this.helperTextView, (CharSequence)null));
   }

   boolean isCaptionView(int var1) {
      boolean var3 = true;
      boolean var2 = var3;
      if (var1 != 0) {
         if (var1 == 1) {
            var2 = var3;
         } else {
            var2 = false;
         }
      }

      return var2;
   }

   boolean isErrorEnabled() {
      return this.errorEnabled;
   }

   boolean isHelperTextEnabled() {
      return this.helperTextEnabled;
   }

   void removeIndicator(TextView var1, int var2) {
      if (this.indicatorArea != null) {
         label15: {
            if (this.isCaptionView(var2)) {
               FrameLayout var3 = this.captionArea;
               if (var3 != null) {
                  var2 = this.captionViewsAdded - 1;
                  this.captionViewsAdded = var2;
                  this.setViewGroupGoneIfEmpty(var3, var2);
                  this.captionArea.removeView(var1);
                  break label15;
               }
            }

            this.indicatorArea.removeView(var1);
         }

         var2 = this.indicatorsAdded - 1;
         this.indicatorsAdded = var2;
         this.setViewGroupGoneIfEmpty(this.indicatorArea, var2);
      }
   }

   void setErrorEnabled(boolean var1) {
      if (this.errorEnabled != var1) {
         this.cancelCaptionAnimator();
         if (var1) {
            AppCompatTextView var2 = new AppCompatTextView(this.context);
            this.errorView = var2;
            var2.setId(R.id.textinput_error);
            Typeface var3 = this.typeface;
            if (var3 != null) {
               this.errorView.setTypeface(var3);
            }

            this.setErrorTextAppearance(this.errorTextAppearance);
            this.errorView.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.errorView, 1);
            this.addIndicator(this.errorView, 0);
         } else {
            this.hideError();
            this.removeIndicator(this.errorView, 0);
            this.errorView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
         }

         this.errorEnabled = var1;
      }
   }

   void setErrorTextAppearance(int var1) {
      this.errorTextAppearance = var1;
      TextView var2 = this.errorView;
      if (var2 != null) {
         this.textInputView.setTextAppearanceCompatWithErrorFallback(var2, var1);
      }

   }

   void setErrorViewTextColor(ColorStateList var1) {
      TextView var2 = this.errorView;
      if (var2 != null) {
         var2.setTextColor(var1);
      }

   }

   void setHelperTextAppearance(int var1) {
      this.helperTextTextAppearance = var1;
      TextView var2 = this.helperTextView;
      if (var2 != null) {
         TextViewCompat.setTextAppearance(var2, var1);
      }

   }

   void setHelperTextEnabled(boolean var1) {
      if (this.helperTextEnabled != var1) {
         this.cancelCaptionAnimator();
         if (var1) {
            AppCompatTextView var2 = new AppCompatTextView(this.context);
            this.helperTextView = var2;
            var2.setId(R.id.textinput_helper_text);
            Typeface var3 = this.typeface;
            if (var3 != null) {
               this.helperTextView.setTypeface(var3);
            }

            this.helperTextView.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.helperTextView, 1);
            this.setHelperTextAppearance(this.helperTextTextAppearance);
            this.addIndicator(this.helperTextView, 1);
         } else {
            this.hideHelperText();
            this.removeIndicator(this.helperTextView, 1);
            this.helperTextView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
         }

         this.helperTextEnabled = var1;
      }
   }

   void setHelperTextViewTextColor(ColorStateList var1) {
      TextView var2 = this.helperTextView;
      if (var2 != null) {
         var2.setTextColor(var1);
      }

   }

   void setTypefaces(Typeface var1) {
      if (var1 != this.typeface) {
         this.typeface = var1;
         this.setTextViewTypeface(this.errorView, var1);
         this.setTextViewTypeface(this.helperTextView, var1);
      }

   }

   void showError(CharSequence var1) {
      this.cancelCaptionAnimator();
      this.errorText = var1;
      this.errorView.setText(var1);
      int var2 = this.captionDisplayed;
      if (var2 != 1) {
         this.captionToShow = 1;
      }

      this.updateCaptionViewsVisibility(var2, this.captionToShow, this.shouldAnimateCaptionView(this.errorView, var1));
   }

   void showHelper(CharSequence var1) {
      this.cancelCaptionAnimator();
      this.helperText = var1;
      this.helperTextView.setText(var1);
      int var2 = this.captionDisplayed;
      if (var2 != 2) {
         this.captionToShow = 2;
      }

      this.updateCaptionViewsVisibility(var2, this.captionToShow, this.shouldAnimateCaptionView(this.helperTextView, var1));
   }
}
