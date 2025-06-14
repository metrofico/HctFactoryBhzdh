package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TintableCompoundDrawablesView;

public class AppCompatButton extends Button implements TintableBackgroundView, AutoSizeableTextView, TintableCompoundDrawablesView, EmojiCompatConfigurationView {
   private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private final AppCompatTextHelper mTextHelper;

   public AppCompatButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatButton(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.buttonStyle);
   }

   public AppCompatButton(Context var1, AttributeSet var2, int var3) {
      super(TintContextWrapper.wrap(var1), var2, var3);
      ThemeUtils.checkAppCompatTheme(this, this.getContext());
      AppCompatBackgroundHelper var4 = new AppCompatBackgroundHelper(this);
      this.mBackgroundTintHelper = var4;
      var4.loadFromAttributes(var2, var3);
      AppCompatTextHelper var5 = new AppCompatTextHelper(this);
      this.mTextHelper = var5;
      var5.loadFromAttributes(var2, var3);
      var5.applyCompoundDrawablesTints();
      this.getEmojiTextViewHelper().loadFromAttributes(var2, var3);
   }

   private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
      if (this.mAppCompatEmojiTextHelper == null) {
         this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
      }

      return this.mAppCompatEmojiTextHelper;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      if (var1 != null) {
         var1.applySupportBackgroundTint();
      }

      AppCompatTextHelper var2 = this.mTextHelper;
      if (var2 != null) {
         var2.applyCompoundDrawablesTints();
      }

   }

   public int getAutoSizeMaxTextSize() {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         return super.getAutoSizeMaxTextSize();
      } else {
         AppCompatTextHelper var1 = this.mTextHelper;
         return var1 != null ? var1.getAutoSizeMaxTextSize() : -1;
      }
   }

   public int getAutoSizeMinTextSize() {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         return super.getAutoSizeMinTextSize();
      } else {
         AppCompatTextHelper var1 = this.mTextHelper;
         return var1 != null ? var1.getAutoSizeMinTextSize() : -1;
      }
   }

   public int getAutoSizeStepGranularity() {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         return super.getAutoSizeStepGranularity();
      } else {
         AppCompatTextHelper var1 = this.mTextHelper;
         return var1 != null ? var1.getAutoSizeStepGranularity() : -1;
      }
   }

   public int[] getAutoSizeTextAvailableSizes() {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         return super.getAutoSizeTextAvailableSizes();
      } else {
         AppCompatTextHelper var1 = this.mTextHelper;
         return var1 != null ? var1.getAutoSizeTextAvailableSizes() : new int[0];
      }
   }

   public int getAutoSizeTextType() {
      boolean var2 = PLATFORM_SUPPORTS_AUTOSIZE;
      byte var1 = 0;
      if (var2) {
         if (super.getAutoSizeTextType() == 1) {
            var1 = 1;
         }

         return var1;
      } else {
         AppCompatTextHelper var3 = this.mTextHelper;
         return var3 != null ? var3.getAutoSizeTextType() : 0;
      }
   }

   public ActionMode.Callback getCustomSelectionActionModeCallback() {
      return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
   }

   public ColorStateList getSupportBackgroundTintList() {
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getSupportBackgroundTintList();
      } else {
         var2 = null;
      }

      return var2;
   }

   public PorterDuff.Mode getSupportBackgroundTintMode() {
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      PorterDuff.Mode var2;
      if (var1 != null) {
         var2 = var1.getSupportBackgroundTintMode();
      } else {
         var2 = null;
      }

      return var2;
   }

   public ColorStateList getSupportCompoundDrawablesTintList() {
      return this.mTextHelper.getCompoundDrawableTintList();
   }

   public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
      return this.mTextHelper.getCompoundDrawableTintMode();
   }

   public boolean isEmojiCompatEnabled() {
      return this.getEmojiTextViewHelper().isEnabled();
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
      super.onInitializeAccessibilityEvent(var1);
      var1.setClassName(Button.class.getName());
   }

   public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo var1) {
      super.onInitializeAccessibilityNodeInfo(var1);
      var1.setClassName(Button.class.getName());
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      super.onLayout(var1, var2, var3, var4, var5);
      AppCompatTextHelper var6 = this.mTextHelper;
      if (var6 != null) {
         var6.onLayout(var1, var2, var3, var4, var5);
      }

   }

   protected void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
      super.onTextChanged(var1, var2, var3, var4);
      if (this.mTextHelper != null && !PLATFORM_SUPPORTS_AUTOSIZE && this.mTextHelper.isAutoSizeEnabled()) {
         this.mTextHelper.autoSizeText();
      }

   }

   public void setAllCaps(boolean var1) {
      super.setAllCaps(var1);
      this.getEmojiTextViewHelper().setAllCaps(var1);
   }

   public void setAutoSizeTextTypeUniformWithConfiguration(int var1, int var2, int var3, int var4) throws IllegalArgumentException {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         super.setAutoSizeTextTypeUniformWithConfiguration(var1, var2, var3, var4);
      } else {
         AppCompatTextHelper var5 = this.mTextHelper;
         if (var5 != null) {
            var5.setAutoSizeTextTypeUniformWithConfiguration(var1, var2, var3, var4);
         }
      }

   }

   public void setAutoSizeTextTypeUniformWithPresetSizes(int[] var1, int var2) throws IllegalArgumentException {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         super.setAutoSizeTextTypeUniformWithPresetSizes(var1, var2);
      } else {
         AppCompatTextHelper var3 = this.mTextHelper;
         if (var3 != null) {
            var3.setAutoSizeTextTypeUniformWithPresetSizes(var1, var2);
         }
      }

   }

   public void setAutoSizeTextTypeWithDefaults(int var1) {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         super.setAutoSizeTextTypeWithDefaults(var1);
      } else {
         AppCompatTextHelper var2 = this.mTextHelper;
         if (var2 != null) {
            var2.setAutoSizeTextTypeWithDefaults(var1);
         }
      }

   }

   public void setBackgroundDrawable(Drawable var1) {
      super.setBackgroundDrawable(var1);
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.onSetBackgroundDrawable(var1);
      }

   }

   public void setBackgroundResource(int var1) {
      super.setBackgroundResource(var1);
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.onSetBackgroundResource(var1);
      }

   }

   public void setCustomSelectionActionModeCallback(ActionMode.Callback var1) {
      super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, var1));
   }

   public void setEmojiCompatEnabled(boolean var1) {
      this.getEmojiTextViewHelper().setEnabled(var1);
   }

   public void setFilters(InputFilter[] var1) {
      super.setFilters(this.getEmojiTextViewHelper().getFilters(var1));
   }

   public void setSupportAllCaps(boolean var1) {
      AppCompatTextHelper var2 = this.mTextHelper;
      if (var2 != null) {
         var2.setAllCaps(var1);
      }

   }

   public void setSupportBackgroundTintList(ColorStateList var1) {
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.setSupportBackgroundTintList(var1);
      }

   }

   public void setSupportBackgroundTintMode(PorterDuff.Mode var1) {
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.setSupportBackgroundTintMode(var1);
      }

   }

   public void setSupportCompoundDrawablesTintList(ColorStateList var1) {
      this.mTextHelper.setCompoundDrawableTintList(var1);
      this.mTextHelper.applyCompoundDrawablesTints();
   }

   public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode var1) {
      this.mTextHelper.setCompoundDrawableTintMode(var1);
      this.mTextHelper.applyCompoundDrawablesTints();
   }

   public void setTextAppearance(Context var1, int var2) {
      super.setTextAppearance(var1, var2);
      AppCompatTextHelper var3 = this.mTextHelper;
      if (var3 != null) {
         var3.onSetTextAppearance(var1, var2);
      }

   }

   public void setTextSize(int var1, float var2) {
      if (PLATFORM_SUPPORTS_AUTOSIZE) {
         super.setTextSize(var1, var2);
      } else {
         AppCompatTextHelper var3 = this.mTextHelper;
         if (var3 != null) {
            var3.setTextSize(var1, var2);
         }
      }

   }
}
