package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TextInputLayout extends LinearLayout {
   public static final int BOX_BACKGROUND_FILLED = 1;
   public static final int BOX_BACKGROUND_NONE = 0;
   public static final int BOX_BACKGROUND_OUTLINE = 2;
   private static final int INVALID_MAX_LENGTH = -1;
   private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
   private static final String LOG_TAG = "TextInputLayout";
   private ValueAnimator animator;
   private GradientDrawable boxBackground;
   private int boxBackgroundColor;
   private int boxBackgroundMode;
   private final int boxBottomOffsetPx;
   private final int boxCollapsedPaddingTopPx;
   private float boxCornerRadiusBottomEnd;
   private float boxCornerRadiusBottomStart;
   private float boxCornerRadiusTopEnd;
   private float boxCornerRadiusTopStart;
   private final int boxLabelCutoutPaddingPx;
   private int boxStrokeColor;
   private final int boxStrokeWidthDefaultPx;
   private final int boxStrokeWidthFocusedPx;
   private int boxStrokeWidthPx;
   final CollapsingTextHelper collapsingTextHelper;
   boolean counterEnabled;
   private int counterMaxLength;
   private final int counterOverflowTextAppearance;
   private boolean counterOverflowed;
   private final int counterTextAppearance;
   private TextView counterView;
   private ColorStateList defaultHintTextColor;
   private final int defaultStrokeColor;
   private final int disabledColor;
   EditText editText;
   private Drawable editTextOriginalDrawable;
   private int focusedStrokeColor;
   private ColorStateList focusedTextColor;
   private boolean hasPasswordToggleTintList;
   private boolean hasPasswordToggleTintMode;
   private boolean hasReconstructedEditTextBackground;
   private CharSequence hint;
   private boolean hintAnimationEnabled;
   private boolean hintEnabled;
   private boolean hintExpanded;
   private final int hoveredStrokeColor;
   private boolean inDrawableStateChanged;
   private final IndicatorViewController indicatorViewController;
   private final FrameLayout inputFrame;
   private boolean isProvidingHint;
   private Drawable originalEditTextEndDrawable;
   private CharSequence originalHint;
   private CharSequence passwordToggleContentDesc;
   private Drawable passwordToggleDrawable;
   private Drawable passwordToggleDummyDrawable;
   private boolean passwordToggleEnabled;
   private ColorStateList passwordToggleTintList;
   private PorterDuff.Mode passwordToggleTintMode;
   private CheckableImageButton passwordToggleView;
   private boolean passwordToggledVisible;
   private boolean restoringSavedState;
   private final Rect tmpRect;
   private final RectF tmpRectF;
   private Typeface typeface;

   public TextInputLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public TextInputLayout(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.textInputStyle);
   }

   public TextInputLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.indicatorViewController = new IndicatorViewController(this);
      this.tmpRect = new Rect();
      this.tmpRectF = new RectF();
      CollapsingTextHelper var9 = new CollapsingTextHelper(this);
      this.collapsingTextHelper = var9;
      this.setOrientation(1);
      this.setWillNotDraw(false);
      this.setAddStatesFromChildren(true);
      FrameLayout var8 = new FrameLayout(var1);
      this.inputFrame = var8;
      var8.setAddStatesFromChildren(true);
      this.addView(var8);
      var9.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
      var9.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
      var9.setCollapsedTextGravity(8388659);
      TintTypedArray var11 = ThemeEnforcement.obtainTintedStyledAttributes(var1, var2, R.styleable.TextInputLayout, var3, R.style.Widget_Design_TextInputLayout);
      this.hintEnabled = var11.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
      this.setHint(var11.getText(R.styleable.TextInputLayout_android_hint));
      this.hintAnimationEnabled = var11.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
      this.boxBottomOffsetPx = var1.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_bottom_offset);
      this.boxLabelCutoutPaddingPx = var1.getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_box_label_cutout_padding);
      this.boxCollapsedPaddingTopPx = var11.getDimensionPixelOffset(R.styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
      this.boxCornerRadiusTopStart = var11.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopStart, 0.0F);
      this.boxCornerRadiusTopEnd = var11.getDimension(R.styleable.TextInputLayout_boxCornerRadiusTopEnd, 0.0F);
      this.boxCornerRadiusBottomEnd = var11.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomEnd, 0.0F);
      this.boxCornerRadiusBottomStart = var11.getDimension(R.styleable.TextInputLayout_boxCornerRadiusBottomStart, 0.0F);
      this.boxBackgroundColor = var11.getColor(R.styleable.TextInputLayout_boxBackgroundColor, 0);
      this.focusedStrokeColor = var11.getColor(R.styleable.TextInputLayout_boxStrokeColor, 0);
      var3 = var1.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_default);
      this.boxStrokeWidthDefaultPx = var3;
      this.boxStrokeWidthFocusedPx = var1.getResources().getDimensionPixelSize(R.dimen.mtrl_textinput_box_stroke_width_focused);
      this.boxStrokeWidthPx = var3;
      this.setBoxBackgroundMode(var11.getInt(R.styleable.TextInputLayout_boxBackgroundMode, 0));
      if (var11.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
         ColorStateList var12 = var11.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
         this.focusedTextColor = var12;
         this.defaultHintTextColor = var12;
      }

      this.defaultStrokeColor = ContextCompat.getColor(var1, R.color.mtrl_textinput_default_box_stroke_color);
      this.disabledColor = ContextCompat.getColor(var1, R.color.mtrl_textinput_disabled_color);
      this.hoveredStrokeColor = ContextCompat.getColor(var1, R.color.mtrl_textinput_hovered_box_stroke_color);
      if (var11.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
         this.setHintTextAppearance(var11.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
      }

      var3 = var11.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
      boolean var5 = var11.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
      int var4 = var11.getResourceId(R.styleable.TextInputLayout_helperTextTextAppearance, 0);
      boolean var7 = var11.getBoolean(R.styleable.TextInputLayout_helperTextEnabled, false);
      CharSequence var10 = var11.getText(R.styleable.TextInputLayout_helperText);
      boolean var6 = var11.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
      this.setCounterMaxLength(var11.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
      this.counterTextAppearance = var11.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
      this.counterOverflowTextAppearance = var11.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
      this.passwordToggleEnabled = var11.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false);
      this.passwordToggleDrawable = var11.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable);
      this.passwordToggleContentDesc = var11.getText(R.styleable.TextInputLayout_passwordToggleContentDescription);
      if (var11.hasValue(R.styleable.TextInputLayout_passwordToggleTint)) {
         this.hasPasswordToggleTintList = true;
         this.passwordToggleTintList = var11.getColorStateList(R.styleable.TextInputLayout_passwordToggleTint);
      }

      if (var11.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode)) {
         this.hasPasswordToggleTintMode = true;
         this.passwordToggleTintMode = ViewUtils.parseTintMode(var11.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), (PorterDuff.Mode)null);
      }

      var11.recycle();
      this.setHelperTextEnabled(var7);
      this.setHelperText(var10);
      this.setHelperTextTextAppearance(var4);
      this.setErrorEnabled(var5);
      this.setErrorTextAppearance(var3);
      this.setCounterEnabled(var6);
      this.applyPasswordToggleTint();
      ViewCompat.setImportantForAccessibility(this, 2);
   }

   private void applyBoxAttributes() {
      if (this.boxBackground != null) {
         this.setBoxAttributes();
         EditText var3 = this.editText;
         if (var3 != null && this.boxBackgroundMode == 2) {
            if (var3.getBackground() != null) {
               this.editTextOriginalDrawable = this.editText.getBackground();
            }

            ViewCompat.setBackground(this.editText, (Drawable)null);
         }

         var3 = this.editText;
         if (var3 != null && this.boxBackgroundMode == 1) {
            Drawable var4 = this.editTextOriginalDrawable;
            if (var4 != null) {
               ViewCompat.setBackground(var3, var4);
            }
         }

         int var1 = this.boxStrokeWidthPx;
         if (var1 > -1) {
            int var2 = this.boxStrokeColor;
            if (var2 != 0) {
               this.boxBackground.setStroke(var1, var2);
            }
         }

         this.boxBackground.setCornerRadii(this.getCornerRadiiAsArray());
         this.boxBackground.setColor(this.boxBackgroundColor);
         this.invalidate();
      }
   }

   private void applyCutoutPadding(RectF var1) {
      var1.left -= (float)this.boxLabelCutoutPaddingPx;
      var1.top -= (float)this.boxLabelCutoutPaddingPx;
      var1.right += (float)this.boxLabelCutoutPaddingPx;
      var1.bottom += (float)this.boxLabelCutoutPaddingPx;
   }

   private void applyPasswordToggleTint() {
      Drawable var1 = this.passwordToggleDrawable;
      if (var1 != null && (this.hasPasswordToggleTintList || this.hasPasswordToggleTintMode)) {
         var1 = DrawableCompat.wrap(var1).mutate();
         this.passwordToggleDrawable = var1;
         if (this.hasPasswordToggleTintList) {
            DrawableCompat.setTintList(var1, this.passwordToggleTintList);
         }

         if (this.hasPasswordToggleTintMode) {
            DrawableCompat.setTintMode(this.passwordToggleDrawable, this.passwordToggleTintMode);
         }

         CheckableImageButton var3 = this.passwordToggleView;
         if (var3 != null) {
            var1 = var3.getDrawable();
            Drawable var2 = this.passwordToggleDrawable;
            if (var1 != var2) {
               this.passwordToggleView.setImageDrawable(var2);
            }
         }
      }

   }

   private void assignBoxBackgroundByMode() {
      int var1 = this.boxBackgroundMode;
      if (var1 == 0) {
         this.boxBackground = null;
      } else if (var1 == 2 && this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
         this.boxBackground = new CutoutDrawable();
      } else if (!(this.boxBackground instanceof GradientDrawable)) {
         this.boxBackground = new GradientDrawable();
      }

   }

   private int calculateBoxBackgroundTop() {
      EditText var2 = this.editText;
      if (var2 == null) {
         return 0;
      } else {
         int var1 = this.boxBackgroundMode;
         if (var1 != 1) {
            return var1 != 2 ? 0 : var2.getTop() + this.calculateLabelMarginTop();
         } else {
            return var2.getTop();
         }
      }
   }

   private int calculateCollapsedTextTopBounds() {
      int var1 = this.boxBackgroundMode;
      if (var1 != 1) {
         return var1 != 2 ? this.getPaddingTop() : this.getBoxBackground().getBounds().top - this.calculateLabelMarginTop();
      } else {
         return this.getBoxBackground().getBounds().top + this.boxCollapsedPaddingTopPx;
      }
   }

   private int calculateLabelMarginTop() {
      if (!this.hintEnabled) {
         return 0;
      } else {
         int var2 = this.boxBackgroundMode;
         float var1;
         if (var2 != 0 && var2 != 1) {
            if (var2 != 2) {
               return 0;
            }

            var1 = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0F;
         } else {
            var1 = this.collapsingTextHelper.getCollapsedTextHeight();
         }

         return (int)var1;
      }
   }

   private void closeCutout() {
      if (this.cutoutEnabled()) {
         ((CutoutDrawable)this.boxBackground).removeCutout();
      }

   }

   private void collapseHint(boolean var1) {
      ValueAnimator var2 = this.animator;
      if (var2 != null && var2.isRunning()) {
         this.animator.cancel();
      }

      if (var1 && this.hintAnimationEnabled) {
         this.animateToExpansionFraction(1.0F);
      } else {
         this.collapsingTextHelper.setExpansionFraction(1.0F);
      }

      this.hintExpanded = false;
      if (this.cutoutEnabled()) {
         this.openCutout();
      }

   }

   private boolean cutoutEnabled() {
      boolean var1;
      if (this.hintEnabled && !TextUtils.isEmpty(this.hint) && this.boxBackground instanceof CutoutDrawable) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void ensureBackgroundDrawableStateWorkaround() {
      int var1 = VERSION.SDK_INT;
      if (var1 == 21 || var1 == 22) {
         Drawable var2 = this.editText.getBackground();
         if (var2 != null) {
            if (!this.hasReconstructedEditTextBackground) {
               Drawable var3 = var2.getConstantState().newDrawable();
               if (var2 instanceof DrawableContainer) {
                  this.hasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)var2, var3.getConstantState());
               }

               if (!this.hasReconstructedEditTextBackground) {
                  ViewCompat.setBackground(this.editText, var3);
                  this.hasReconstructedEditTextBackground = true;
                  this.onApplyBoxBackgroundMode();
               }
            }

         }
      }
   }

   private void expandHint(boolean var1) {
      ValueAnimator var2 = this.animator;
      if (var2 != null && var2.isRunning()) {
         this.animator.cancel();
      }

      if (var1 && this.hintAnimationEnabled) {
         this.animateToExpansionFraction(0.0F);
      } else {
         this.collapsingTextHelper.setExpansionFraction(0.0F);
      }

      if (this.cutoutEnabled() && ((CutoutDrawable)this.boxBackground).hasCutout()) {
         this.closeCutout();
      }

      this.hintExpanded = true;
   }

   private Drawable getBoxBackground() {
      int var1 = this.boxBackgroundMode;
      if (var1 != 1 && var1 != 2) {
         throw new IllegalStateException();
      } else {
         return this.boxBackground;
      }
   }

   private float[] getCornerRadiiAsArray() {
      float var1;
      float var2;
      float var3;
      float var4;
      if (!ViewUtils.isLayoutRtl(this)) {
         var1 = this.boxCornerRadiusTopStart;
         var3 = this.boxCornerRadiusTopEnd;
         var4 = this.boxCornerRadiusBottomEnd;
         var2 = this.boxCornerRadiusBottomStart;
         return new float[]{var1, var1, var3, var3, var4, var4, var2, var2};
      } else {
         var4 = this.boxCornerRadiusTopEnd;
         var2 = this.boxCornerRadiusTopStart;
         var1 = this.boxCornerRadiusBottomStart;
         var3 = this.boxCornerRadiusBottomEnd;
         return new float[]{var4, var4, var2, var2, var1, var1, var3, var3};
      }
   }

   private boolean hasPasswordTransformation() {
      EditText var2 = this.editText;
      boolean var1;
      if (var2 != null && var2.getTransformationMethod() instanceof PasswordTransformationMethod) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void onApplyBoxBackgroundMode() {
      this.assignBoxBackgroundByMode();
      if (this.boxBackgroundMode != 0) {
         this.updateInputLayoutMargins();
      }

      this.updateTextInputBoxBounds();
   }

   private void openCutout() {
      if (this.cutoutEnabled()) {
         RectF var1 = this.tmpRectF;
         this.collapsingTextHelper.getCollapsedTextActualBounds(var1);
         this.applyCutoutPadding(var1);
         ((CutoutDrawable)this.boxBackground).setCutout(var1);
      }
   }

   private static void recursiveSetEnabled(ViewGroup var0, boolean var1) {
      int var3 = var0.getChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var4 = var0.getChildAt(var2);
         var4.setEnabled(var1);
         if (var4 instanceof ViewGroup) {
            recursiveSetEnabled((ViewGroup)var4, var1);
         }
      }

   }

   private void setBoxAttributes() {
      int var1 = this.boxBackgroundMode;
      if (var1 != 1) {
         if (var1 == 2 && this.focusedStrokeColor == 0) {
            this.focusedStrokeColor = this.focusedTextColor.getColorForState(this.getDrawableState(), this.focusedTextColor.getDefaultColor());
         }
      } else {
         this.boxStrokeWidthPx = 0;
      }

   }

   private void setEditText(EditText var1) {
      if (this.editText == null) {
         if (!(var1 instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
         }

         this.editText = var1;
         this.onApplyBoxBackgroundMode();
         this.setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
         if (!this.hasPasswordTransformation()) {
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
         }

         this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
         int var2 = this.editText.getGravity();
         this.collapsingTextHelper.setCollapsedTextGravity(var2 & -113 | 48);
         this.collapsingTextHelper.setExpandedTextGravity(var2);
         this.editText.addTextChangedListener(new TextWatcher(this) {
            final TextInputLayout this$0;

            {
               this.this$0 = var1;
            }

            public void afterTextChanged(Editable var1) {
               TextInputLayout var2 = this.this$0;
               var2.updateLabelState(var2.restoringSavedState ^ true);
               if (this.this$0.counterEnabled) {
                  this.this$0.updateCounter(var1.length());
               }

            }

            public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
            }

            public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
            }
         });
         if (this.defaultHintTextColor == null) {
            this.defaultHintTextColor = this.editText.getHintTextColors();
         }

         if (this.hintEnabled) {
            if (TextUtils.isEmpty(this.hint)) {
               CharSequence var3 = this.editText.getHint();
               this.originalHint = var3;
               this.setHint(var3);
               this.editText.setHint((CharSequence)null);
            }

            this.isProvidingHint = true;
         }

         if (this.counterView != null) {
            this.updateCounter(this.editText.getText().length());
         }

         this.indicatorViewController.adjustIndicatorPadding();
         this.updatePasswordToggleView();
         this.updateLabelState(false, true);
      } else {
         throw new IllegalArgumentException("We already have an EditText, can only have one");
      }
   }

   private void setHintInternal(CharSequence var1) {
      if (!TextUtils.equals(var1, this.hint)) {
         this.hint = var1;
         this.collapsingTextHelper.setText(var1);
         if (!this.hintExpanded) {
            this.openCutout();
         }
      }

   }

   private boolean shouldShowPasswordIcon() {
      boolean var1;
      if (!this.passwordToggleEnabled || !this.hasPasswordTransformation() && !this.passwordToggledVisible) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private void updateEditTextBackgroundBounds() {
      EditText var5 = this.editText;
      if (var5 != null) {
         Drawable var6 = var5.getBackground();
         if (var6 != null) {
            Drawable var8 = var6;
            if (androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(var6)) {
               var8 = var6.mutate();
            }

            Rect var9 = new Rect();
            DescendantOffsetUtils.getDescendantRect(this, this.editText, var9);
            Rect var7 = var8.getBounds();
            if (var7.left != var7.right) {
               var9 = new Rect();
               var8.getPadding(var9);
               int var1 = var7.left;
               int var3 = var9.left;
               int var2 = var7.right;
               int var4 = var9.right;
               var8.setBounds(var1 - var3, var7.top, var2 + var4 * 2, this.editText.getBottom());
            }

         }
      }
   }

   private void updateInputLayoutMargins() {
      LinearLayout.LayoutParams var2 = (LinearLayout.LayoutParams)this.inputFrame.getLayoutParams();
      int var1 = this.calculateLabelMarginTop();
      if (var1 != var2.topMargin) {
         var2.topMargin = var1;
         this.inputFrame.requestLayout();
      }

   }

   private void updateLabelState(boolean var1, boolean var2) {
      boolean var6 = this.isEnabled();
      EditText var7 = this.editText;
      boolean var4 = true;
      boolean var3;
      if (var7 != null && !TextUtils.isEmpty(var7.getText())) {
         var3 = true;
      } else {
         var3 = false;
      }

      var7 = this.editText;
      if (var7 == null || !var7.hasFocus()) {
         var4 = false;
      }

      boolean var5 = this.indicatorViewController.errorShouldBeShown();
      ColorStateList var8 = this.defaultHintTextColor;
      if (var8 != null) {
         this.collapsingTextHelper.setCollapsedTextColor(var8);
         this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
      }

      if (!var6) {
         this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
         this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
      } else if (var5) {
         this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
      } else {
         label61: {
            if (this.counterOverflowed) {
               TextView var9 = this.counterView;
               if (var9 != null) {
                  this.collapsingTextHelper.setCollapsedTextColor(var9.getTextColors());
                  break label61;
               }
            }

            if (var4) {
               var8 = this.focusedTextColor;
               if (var8 != null) {
                  this.collapsingTextHelper.setCollapsedTextColor(var8);
               }
            }
         }
      }

      if (!var3 && (!this.isEnabled() || !var4 && !var5)) {
         if (var2 || !this.hintExpanded) {
            this.expandHint(var1);
         }
      } else if (var2 || this.hintExpanded) {
         this.collapseHint(var1);
      }

   }

   private void updatePasswordToggleView() {
      if (this.editText != null) {
         CheckableImageButton var1;
         if (this.shouldShowPasswordIcon()) {
            if (this.passwordToggleView == null) {
               var1 = (CheckableImageButton)LayoutInflater.from(this.getContext()).inflate(R.layout.design_text_input_password_icon, this.inputFrame, false);
               this.passwordToggleView = var1;
               var1.setImageDrawable(this.passwordToggleDrawable);
               this.passwordToggleView.setContentDescription(this.passwordToggleContentDesc);
               this.inputFrame.addView(this.passwordToggleView);
               this.passwordToggleView.setOnClickListener(new View.OnClickListener(this) {
                  final TextInputLayout this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onClick(View var1) {
                     this.this$0.passwordVisibilityToggleRequested(false);
                  }
               });
            }

            EditText var4 = this.editText;
            if (var4 != null && ViewCompat.getMinimumHeight(var4) <= 0) {
               this.editText.setMinimumHeight(ViewCompat.getMinimumHeight(this.passwordToggleView));
            }

            this.passwordToggleView.setVisibility(0);
            this.passwordToggleView.setChecked(this.passwordToggledVisible);
            if (this.passwordToggleDummyDrawable == null) {
               this.passwordToggleDummyDrawable = new ColorDrawable();
            }

            this.passwordToggleDummyDrawable.setBounds(0, 0, this.passwordToggleView.getMeasuredWidth(), 1);
            Drawable[] var2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable var5 = var2[2];
            Drawable var3 = this.passwordToggleDummyDrawable;
            if (var5 != var3) {
               this.originalEditTextEndDrawable = var5;
            }

            TextViewCompat.setCompoundDrawablesRelative(this.editText, var2[0], var2[1], var3, var2[3]);
            this.passwordToggleView.setPadding(this.editText.getPaddingLeft(), this.editText.getPaddingTop(), this.editText.getPaddingRight(), this.editText.getPaddingBottom());
         } else {
            var1 = this.passwordToggleView;
            if (var1 != null && var1.getVisibility() == 0) {
               this.passwordToggleView.setVisibility(8);
            }

            if (this.passwordToggleDummyDrawable != null) {
               Drawable[] var6 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
               if (var6[2] == this.passwordToggleDummyDrawable) {
                  TextViewCompat.setCompoundDrawablesRelative(this.editText, var6[0], var6[1], this.originalEditTextEndDrawable, var6[3]);
                  this.passwordToggleDummyDrawable = null;
               }
            }
         }

      }
   }

   private void updateTextInputBoxBounds() {
      if (this.boxBackgroundMode != 0 && this.boxBackground != null && this.editText != null && this.getRight() != 0) {
         int var8 = this.editText.getLeft();
         int var7 = this.calculateBoxBackgroundTop();
         int var6 = this.editText.getRight();
         int var5 = this.editText.getBottom() + this.boxBottomOffsetPx;
         int var4 = var8;
         int var3 = var7;
         int var2 = var6;
         int var1 = var5;
         if (this.boxBackgroundMode == 2) {
            var1 = this.boxStrokeWidthFocusedPx;
            var4 = var8 + var1 / 2;
            var3 = var7 - var1 / 2;
            var2 = var6 - var1 / 2;
            var1 = var5 + var1 / 2;
         }

         this.boxBackground.setBounds(var4, var3, var2, var1);
         this.applyBoxAttributes();
         this.updateEditTextBackgroundBounds();
      }

   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      if (var1 instanceof EditText) {
         FrameLayout.LayoutParams var4 = new FrameLayout.LayoutParams(var3);
         var4.gravity = var4.gravity & -113 | 16;
         this.inputFrame.addView(var1, var4);
         this.inputFrame.setLayoutParams(var3);
         this.updateInputLayoutMargins();
         this.setEditText((EditText)var1);
      } else {
         super.addView(var1, var2, var3);
      }

   }

   void animateToExpansionFraction(float var1) {
      if (this.collapsingTextHelper.getExpansionFraction() != var1) {
         if (this.animator == null) {
            ValueAnimator var2 = new ValueAnimator();
            this.animator = var2;
            var2.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.animator.setDuration(167L);
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
               final TextInputLayout this$0;

               {
                  this.this$0 = var1;
               }

               public void onAnimationUpdate(ValueAnimator var1) {
                  this.this$0.collapsingTextHelper.setExpansionFraction((Float)var1.getAnimatedValue());
               }
            });
         }

         this.animator.setFloatValues(new float[]{this.collapsingTextHelper.getExpansionFraction(), var1});
         this.animator.start();
      }
   }

   boolean cutoutIsOpen() {
      boolean var1;
      if (this.cutoutEnabled() && ((CutoutDrawable)this.boxBackground).hasCutout()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void dispatchProvideAutofillStructure(ViewStructure var1, int var2) {
      if (this.originalHint != null) {
         EditText var4 = this.editText;
         if (var4 != null) {
            boolean var3 = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence var7 = var4.getHint();
            this.editText.setHint(this.originalHint);

            try {
               super.dispatchProvideAutofillStructure(var1, var2);
            } finally {
               this.editText.setHint(var7);
               this.isProvidingHint = var3;
            }

            return;
         }
      }

      super.dispatchProvideAutofillStructure(var1, var2);
   }

   protected void dispatchRestoreInstanceState(SparseArray var1) {
      this.restoringSavedState = true;
      super.dispatchRestoreInstanceState(var1);
      this.restoringSavedState = false;
   }

   public void draw(Canvas var1) {
      GradientDrawable var2 = this.boxBackground;
      if (var2 != null) {
         var2.draw(var1);
      }

      super.draw(var1);
      if (this.hintEnabled) {
         this.collapsingTextHelper.draw(var1);
      }

   }

   protected void drawableStateChanged() {
      if (!this.inDrawableStateChanged) {
         boolean var2 = true;
         this.inDrawableStateChanged = true;
         super.drawableStateChanged();
         int[] var3 = this.getDrawableState();
         if (!ViewCompat.isLaidOut(this) || !this.isEnabled()) {
            var2 = false;
         }

         this.updateLabelState(var2);
         this.updateEditTextBackground();
         this.updateTextInputBoxBounds();
         this.updateTextInputBoxState();
         CollapsingTextHelper var4 = this.collapsingTextHelper;
         boolean var1;
         if (var4 != null) {
            var1 = var4.setState(var3) | false;
         } else {
            var1 = false;
         }

         if (var1) {
            this.invalidate();
         }

         this.inDrawableStateChanged = false;
      }
   }

   public int getBoxBackgroundColor() {
      return this.boxBackgroundColor;
   }

   public float getBoxCornerRadiusBottomEnd() {
      return this.boxCornerRadiusBottomEnd;
   }

   public float getBoxCornerRadiusBottomStart() {
      return this.boxCornerRadiusBottomStart;
   }

   public float getBoxCornerRadiusTopEnd() {
      return this.boxCornerRadiusTopEnd;
   }

   public float getBoxCornerRadiusTopStart() {
      return this.boxCornerRadiusTopStart;
   }

   public int getBoxStrokeColor() {
      return this.focusedStrokeColor;
   }

   public int getCounterMaxLength() {
      return this.counterMaxLength;
   }

   CharSequence getCounterOverflowDescription() {
      if (this.counterEnabled && this.counterOverflowed) {
         TextView var1 = this.counterView;
         if (var1 != null) {
            return var1.getContentDescription();
         }
      }

      return null;
   }

   public ColorStateList getDefaultHintTextColor() {
      return this.defaultHintTextColor;
   }

   public EditText getEditText() {
      return this.editText;
   }

   public CharSequence getError() {
      CharSequence var1;
      if (this.indicatorViewController.isErrorEnabled()) {
         var1 = this.indicatorViewController.getErrorText();
      } else {
         var1 = null;
      }

      return var1;
   }

   public int getErrorCurrentTextColors() {
      return this.indicatorViewController.getErrorViewCurrentTextColor();
   }

   final int getErrorTextCurrentColor() {
      return this.indicatorViewController.getErrorViewCurrentTextColor();
   }

   public CharSequence getHelperText() {
      CharSequence var1;
      if (this.indicatorViewController.isHelperTextEnabled()) {
         var1 = this.indicatorViewController.getHelperText();
      } else {
         var1 = null;
      }

      return var1;
   }

   public int getHelperTextCurrentTextColor() {
      return this.indicatorViewController.getHelperTextViewCurrentTextColor();
   }

   public CharSequence getHint() {
      CharSequence var1;
      if (this.hintEnabled) {
         var1 = this.hint;
      } else {
         var1 = null;
      }

      return var1;
   }

   final float getHintCollapsedTextHeight() {
      return this.collapsingTextHelper.getCollapsedTextHeight();
   }

   final int getHintCurrentCollapsedTextColor() {
      return this.collapsingTextHelper.getCurrentCollapsedTextColor();
   }

   public CharSequence getPasswordVisibilityToggleContentDescription() {
      return this.passwordToggleContentDesc;
   }

   public Drawable getPasswordVisibilityToggleDrawable() {
      return this.passwordToggleDrawable;
   }

   public Typeface getTypeface() {
      return this.typeface;
   }

   public boolean isCounterEnabled() {
      return this.counterEnabled;
   }

   public boolean isErrorEnabled() {
      return this.indicatorViewController.isErrorEnabled();
   }

   final boolean isHelperTextDisplayed() {
      return this.indicatorViewController.helperTextIsDisplayed();
   }

   public boolean isHelperTextEnabled() {
      return this.indicatorViewController.isHelperTextEnabled();
   }

   public boolean isHintAnimationEnabled() {
      return this.hintAnimationEnabled;
   }

   public boolean isHintEnabled() {
      return this.hintEnabled;
   }

   final boolean isHintExpanded() {
      return this.hintExpanded;
   }

   public boolean isPasswordVisibilityToggleEnabled() {
      return this.passwordToggleEnabled;
   }

   boolean isProvidingHint() {
      return this.isProvidingHint;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      if (this.boxBackground != null) {
         this.updateTextInputBoxBounds();
      }

      if (this.hintEnabled) {
         EditText var8 = this.editText;
         if (var8 != null) {
            Rect var7 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, var8, var7);
            var2 = var7.left + this.editText.getCompoundPaddingLeft();
            var4 = var7.right - this.editText.getCompoundPaddingRight();
            int var6 = this.calculateCollapsedTextTopBounds();
            this.collapsingTextHelper.setExpandedBounds(var2, var7.top + this.editText.getCompoundPaddingTop(), var4, var7.bottom - this.editText.getCompoundPaddingBottom());
            this.collapsingTextHelper.setCollapsedBounds(var2, var6, var4, var5 - var3 - this.getPaddingBottom());
            this.collapsingTextHelper.recalculate();
            if (this.cutoutEnabled() && !this.hintExpanded) {
               this.openCutout();
            }
         }
      }

   }

   protected void onMeasure(int var1, int var2) {
      this.updatePasswordToggleView();
      super.onMeasure(var1, var2);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.setError(var2.error);
         if (var2.isPasswordToggledVisible) {
            this.passwordVisibilityToggleRequested(true);
         }

         this.requestLayout();
      }
   }

   public Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      if (this.indicatorViewController.errorShouldBeShown()) {
         var1.error = this.getError();
      }

      var1.isPasswordToggledVisible = this.passwordToggledVisible;
      return var1;
   }

   public void passwordVisibilityToggleRequested(boolean var1) {
      if (this.passwordToggleEnabled) {
         int var2 = this.editText.getSelectionEnd();
         if (this.hasPasswordTransformation()) {
            this.editText.setTransformationMethod((TransformationMethod)null);
            this.passwordToggledVisible = true;
         } else {
            this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            this.passwordToggledVisible = false;
         }

         this.passwordToggleView.setChecked(this.passwordToggledVisible);
         if (var1) {
            this.passwordToggleView.jumpDrawablesToCurrentState();
         }

         this.editText.setSelection(var2);
      }

   }

   public void setBoxBackgroundColor(int var1) {
      if (this.boxBackgroundColor != var1) {
         this.boxBackgroundColor = var1;
         this.applyBoxAttributes();
      }

   }

   public void setBoxBackgroundColorResource(int var1) {
      this.setBoxBackgroundColor(ContextCompat.getColor(this.getContext(), var1));
   }

   public void setBoxBackgroundMode(int var1) {
      if (var1 != this.boxBackgroundMode) {
         this.boxBackgroundMode = var1;
         this.onApplyBoxBackgroundMode();
      }
   }

   public void setBoxCornerRadii(float var1, float var2, float var3, float var4) {
      if (this.boxCornerRadiusTopStart != var1 || this.boxCornerRadiusTopEnd != var2 || this.boxCornerRadiusBottomEnd != var4 || this.boxCornerRadiusBottomStart != var3) {
         this.boxCornerRadiusTopStart = var1;
         this.boxCornerRadiusTopEnd = var2;
         this.boxCornerRadiusBottomEnd = var4;
         this.boxCornerRadiusBottomStart = var3;
         this.applyBoxAttributes();
      }

   }

   public void setBoxCornerRadiiResources(int var1, int var2, int var3, int var4) {
      this.setBoxCornerRadii(this.getContext().getResources().getDimension(var1), this.getContext().getResources().getDimension(var2), this.getContext().getResources().getDimension(var3), this.getContext().getResources().getDimension(var4));
   }

   public void setBoxStrokeColor(int var1) {
      if (this.focusedStrokeColor != var1) {
         this.focusedStrokeColor = var1;
         this.updateTextInputBoxState();
      }

   }

   public void setCounterEnabled(boolean var1) {
      if (this.counterEnabled != var1) {
         if (var1) {
            AppCompatTextView var2 = new AppCompatTextView(this.getContext());
            this.counterView = var2;
            var2.setId(R.id.textinput_counter);
            Typeface var3 = this.typeface;
            if (var3 != null) {
               this.counterView.setTypeface(var3);
            }

            this.counterView.setMaxLines(1);
            this.setTextAppearanceCompatWithErrorFallback(this.counterView, this.counterTextAppearance);
            this.indicatorViewController.addIndicator(this.counterView, 2);
            EditText var4 = this.editText;
            if (var4 == null) {
               this.updateCounter(0);
            } else {
               this.updateCounter(var4.getText().length());
            }
         } else {
            this.indicatorViewController.removeIndicator(this.counterView, 2);
            this.counterView = null;
         }

         this.counterEnabled = var1;
      }

   }

   public void setCounterMaxLength(int var1) {
      if (this.counterMaxLength != var1) {
         if (var1 > 0) {
            this.counterMaxLength = var1;
         } else {
            this.counterMaxLength = -1;
         }

         if (this.counterEnabled) {
            EditText var2 = this.editText;
            if (var2 == null) {
               var1 = 0;
            } else {
               var1 = var2.getText().length();
            }

            this.updateCounter(var1);
         }
      }

   }

   public void setDefaultHintTextColor(ColorStateList var1) {
      this.defaultHintTextColor = var1;
      this.focusedTextColor = var1;
      if (this.editText != null) {
         this.updateLabelState(false);
      }

   }

   public void setEnabled(boolean var1) {
      recursiveSetEnabled(this, var1);
      super.setEnabled(var1);
   }

   public void setError(CharSequence var1) {
      if (!this.indicatorViewController.isErrorEnabled()) {
         if (TextUtils.isEmpty(var1)) {
            return;
         }

         this.setErrorEnabled(true);
      }

      if (!TextUtils.isEmpty(var1)) {
         this.indicatorViewController.showError(var1);
      } else {
         this.indicatorViewController.hideError();
      }

   }

   public void setErrorEnabled(boolean var1) {
      this.indicatorViewController.setErrorEnabled(var1);
   }

   public void setErrorTextAppearance(int var1) {
      this.indicatorViewController.setErrorTextAppearance(var1);
   }

   public void setErrorTextColor(ColorStateList var1) {
      this.indicatorViewController.setErrorViewTextColor(var1);
   }

   public void setHelperText(CharSequence var1) {
      if (TextUtils.isEmpty(var1)) {
         if (this.isHelperTextEnabled()) {
            this.setHelperTextEnabled(false);
         }
      } else {
         if (!this.isHelperTextEnabled()) {
            this.setHelperTextEnabled(true);
         }

         this.indicatorViewController.showHelper(var1);
      }

   }

   public void setHelperTextColor(ColorStateList var1) {
      this.indicatorViewController.setHelperTextViewTextColor(var1);
   }

   public void setHelperTextEnabled(boolean var1) {
      this.indicatorViewController.setHelperTextEnabled(var1);
   }

   public void setHelperTextTextAppearance(int var1) {
      this.indicatorViewController.setHelperTextAppearance(var1);
   }

   public void setHint(CharSequence var1) {
      if (this.hintEnabled) {
         this.setHintInternal(var1);
         this.sendAccessibilityEvent(2048);
      }

   }

   public void setHintAnimationEnabled(boolean var1) {
      this.hintAnimationEnabled = var1;
   }

   public void setHintEnabled(boolean var1) {
      if (var1 != this.hintEnabled) {
         this.hintEnabled = var1;
         if (!var1) {
            this.isProvidingHint = false;
            if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
               this.editText.setHint(this.hint);
            }

            this.setHintInternal((CharSequence)null);
         } else {
            CharSequence var2 = this.editText.getHint();
            if (!TextUtils.isEmpty(var2)) {
               if (TextUtils.isEmpty(this.hint)) {
                  this.setHint(var2);
               }

               this.editText.setHint((CharSequence)null);
            }

            this.isProvidingHint = true;
         }

         if (this.editText != null) {
            this.updateInputLayoutMargins();
         }
      }

   }

   public void setHintTextAppearance(int var1) {
      this.collapsingTextHelper.setCollapsedTextAppearance(var1);
      this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
      if (this.editText != null) {
         this.updateLabelState(false);
         this.updateInputLayoutMargins();
      }

   }

   public void setPasswordVisibilityToggleContentDescription(int var1) {
      CharSequence var2;
      if (var1 != 0) {
         var2 = this.getResources().getText(var1);
      } else {
         var2 = null;
      }

      this.setPasswordVisibilityToggleContentDescription(var2);
   }

   public void setPasswordVisibilityToggleContentDescription(CharSequence var1) {
      this.passwordToggleContentDesc = var1;
      CheckableImageButton var2 = this.passwordToggleView;
      if (var2 != null) {
         var2.setContentDescription(var1);
      }

   }

   public void setPasswordVisibilityToggleDrawable(int var1) {
      Drawable var2;
      if (var1 != 0) {
         var2 = AppCompatResources.getDrawable(this.getContext(), var1);
      } else {
         var2 = null;
      }

      this.setPasswordVisibilityToggleDrawable(var2);
   }

   public void setPasswordVisibilityToggleDrawable(Drawable var1) {
      this.passwordToggleDrawable = var1;
      CheckableImageButton var2 = this.passwordToggleView;
      if (var2 != null) {
         var2.setImageDrawable(var1);
      }

   }

   public void setPasswordVisibilityToggleEnabled(boolean var1) {
      if (this.passwordToggleEnabled != var1) {
         this.passwordToggleEnabled = var1;
         if (!var1 && this.passwordToggledVisible) {
            EditText var2 = this.editText;
            if (var2 != null) {
               var2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
         }

         this.passwordToggledVisible = false;
         this.updatePasswordToggleView();
      }

   }

   public void setPasswordVisibilityToggleTintList(ColorStateList var1) {
      this.passwordToggleTintList = var1;
      this.hasPasswordToggleTintList = true;
      this.applyPasswordToggleTint();
   }

   public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode var1) {
      this.passwordToggleTintMode = var1;
      this.hasPasswordToggleTintMode = true;
      this.applyPasswordToggleTint();
   }

   void setTextAppearanceCompatWithErrorFallback(TextView var1, int var2) {
      boolean var3 = true;

      boolean var6;
      label25: {
         label24: {
            try {
               TextViewCompat.setTextAppearance(var1, var2);
               if (VERSION.SDK_INT < 23) {
                  break label24;
               }

               var2 = var1.getTextColors().getDefaultColor();
            } catch (Exception var5) {
               var6 = var3;
               break label25;
            }

            if (var2 == -65281) {
               var6 = var3;
               break label25;
            }
         }

         var6 = false;
      }

      if (var6) {
         TextViewCompat.setTextAppearance(var1, R.style.TextAppearance_AppCompat_Caption);
         var1.setTextColor(ContextCompat.getColor(this.getContext(), R.color.design_error));
      }

   }

   public void setTextInputAccessibilityDelegate(AccessibilityDelegate var1) {
      EditText var2 = this.editText;
      if (var2 != null) {
         ViewCompat.setAccessibilityDelegate(var2, var1);
      }

   }

   public void setTypeface(Typeface var1) {
      if (var1 != this.typeface) {
         this.typeface = var1;
         this.collapsingTextHelper.setTypefaces(var1);
         this.indicatorViewController.setTypefaces(var1);
         TextView var2 = this.counterView;
         if (var2 != null) {
            var2.setTypeface(var1);
         }
      }

   }

   void updateCounter(int var1) {
      boolean var4 = this.counterOverflowed;
      if (this.counterMaxLength == -1) {
         this.counterView.setText(String.valueOf(var1));
         this.counterView.setContentDescription((CharSequence)null);
         this.counterOverflowed = false;
      } else {
         if (ViewCompat.getAccessibilityLiveRegion(this.counterView) == 1) {
            ViewCompat.setAccessibilityLiveRegion(this.counterView, 0);
         }

         boolean var3;
         if (var1 > this.counterMaxLength) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.counterOverflowed = var3;
         if (var4 != var3) {
            TextView var5 = this.counterView;
            int var2;
            if (var3) {
               var2 = this.counterOverflowTextAppearance;
            } else {
               var2 = this.counterTextAppearance;
            }

            this.setTextAppearanceCompatWithErrorFallback(var5, var2);
            if (this.counterOverflowed) {
               ViewCompat.setAccessibilityLiveRegion(this.counterView, 1);
            }
         }

         this.counterView.setText(this.getContext().getString(R.string.character_counter_pattern, new Object[]{var1, this.counterMaxLength}));
         this.counterView.setContentDescription(this.getContext().getString(R.string.character_counter_content_description, new Object[]{var1, this.counterMaxLength}));
      }

      if (this.editText != null && var4 != this.counterOverflowed) {
         this.updateLabelState(false);
         this.updateTextInputBoxState();
         this.updateEditTextBackground();
      }

   }

   void updateEditTextBackground() {
      EditText var1 = this.editText;
      if (var1 != null) {
         Drawable var2 = var1.getBackground();
         if (var2 != null) {
            this.ensureBackgroundDrawableStateWorkaround();
            Drawable var3 = var2;
            if (androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(var2)) {
               var3 = var2.mutate();
            }

            if (this.indicatorViewController.errorShouldBeShown()) {
               var3.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), Mode.SRC_IN));
            } else {
               if (this.counterOverflowed) {
                  TextView var4 = this.counterView;
                  if (var4 != null) {
                     var3.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(var4.getCurrentTextColor(), Mode.SRC_IN));
                     return;
                  }
               }

               DrawableCompat.clearColorFilter(var3);
               this.editText.refreshDrawableState();
            }

         }
      }
   }

   void updateLabelState(boolean var1) {
      this.updateLabelState(var1, false);
   }

   void updateTextInputBoxState() {
      if (this.boxBackground != null && this.boxBackgroundMode != 0) {
         EditText var3 = this.editText;
         boolean var2 = true;
         boolean var1;
         if (var3 != null && var3.hasFocus()) {
            var1 = true;
         } else {
            var1 = false;
         }

         var3 = this.editText;
         if (var3 == null || !var3.isHovered()) {
            var2 = false;
         }

         if (this.boxBackgroundMode == 2) {
            if (!this.isEnabled()) {
               this.boxStrokeColor = this.disabledColor;
            } else if (this.indicatorViewController.errorShouldBeShown()) {
               this.boxStrokeColor = this.indicatorViewController.getErrorViewCurrentTextColor();
            } else {
               label72: {
                  if (this.counterOverflowed) {
                     TextView var4 = this.counterView;
                     if (var4 != null) {
                        this.boxStrokeColor = var4.getCurrentTextColor();
                        break label72;
                     }
                  }

                  if (var1) {
                     this.boxStrokeColor = this.focusedStrokeColor;
                  } else if (var2) {
                     this.boxStrokeColor = this.hoveredStrokeColor;
                  } else {
                     this.boxStrokeColor = this.defaultStrokeColor;
                  }
               }
            }

            if ((var2 || var1) && this.isEnabled()) {
               this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
            } else {
               this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
            }

            this.applyBoxAttributes();
         }
      }

   }

   public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
      private final TextInputLayout layout;

      public AccessibilityDelegate(TextInputLayout var1) {
         this.layout = var1;
      }

      public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
         super.onInitializeAccessibilityNodeInfo(var1, var2);
         EditText var12 = this.layout.getEditText();
         Editable var13;
         if (var12 != null) {
            var13 = var12.getText();
         } else {
            var13 = null;
         }

         CharSequence var11 = this.layout.getHint();
         CharSequence var10 = this.layout.getError();
         CharSequence var9 = this.layout.getCounterOverflowDescription();
         boolean var5 = TextUtils.isEmpty(var13) ^ true;
         boolean var6 = TextUtils.isEmpty(var11) ^ true;
         boolean var4 = TextUtils.isEmpty(var10) ^ true;
         boolean var8 = false;
         boolean var3;
         if (!var4 && TextUtils.isEmpty(var9)) {
            var3 = false;
         } else {
            var3 = true;
         }

         if (var5) {
            var2.setText(var13);
         } else if (var6) {
            var2.setText(var11);
         }

         if (var6) {
            var2.setHintText(var11);
            boolean var7 = var8;
            if (!var5) {
               var7 = var8;
               if (var6) {
                  var7 = true;
               }
            }

            var2.setShowingHintText(var7);
         }

         if (var3) {
            CharSequence var14;
            if (var4) {
               var14 = var10;
            } else {
               var14 = var9;
            }

            var2.setError(var14);
            var2.setContentInvalid(true);
         }

      }

      public void onPopulateAccessibilityEvent(View var1, AccessibilityEvent var2) {
         super.onPopulateAccessibilityEvent(var1, var2);
         EditText var4 = this.layout.getEditText();
         Editable var5;
         if (var4 != null) {
            var5 = var4.getText();
         } else {
            var5 = null;
         }

         Object var3 = var5;
         if (TextUtils.isEmpty(var5)) {
            var3 = this.layout.getHint();
         }

         if (!TextUtils.isEmpty((CharSequence)var3)) {
            var2.getText().add(var3);
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface BoxBackgroundMode {
   }

   static class SavedState extends AbsSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1, (ClassLoader)null);
         }

         public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
            return new SavedState(var1, var2);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      CharSequence error;
      boolean isPasswordToggledVisible;

      SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(var1);
         int var3 = var1.readInt();
         boolean var4 = true;
         if (var3 != 1) {
            var4 = false;
         }

         this.isPasswordToggledVisible = var4;
      }

      SavedState(Parcelable var1) {
         super(var1);
      }

      public String toString() {
         return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.error + "}";
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         TextUtils.writeToParcel(this.error, var1, var2);
         var1.writeInt(this.isPasswordToggledVisible);
      }
   }
}
