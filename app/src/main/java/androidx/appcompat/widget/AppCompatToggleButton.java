package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.ToggleButton;
import androidx.core.view.TintableBackgroundView;

public class AppCompatToggleButton extends ToggleButton implements TintableBackgroundView, EmojiCompatConfigurationView {
   private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private final AppCompatTextHelper mTextHelper;

   public AppCompatToggleButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatToggleButton(Context var1, AttributeSet var2) {
      this(var1, var2, 16842827);
   }

   public AppCompatToggleButton(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      ThemeUtils.checkAppCompatTheme(this, this.getContext());
      AppCompatBackgroundHelper var4 = new AppCompatBackgroundHelper(this);
      this.mBackgroundTintHelper = var4;
      var4.loadFromAttributes(var2, var3);
      AppCompatTextHelper var5 = new AppCompatTextHelper(this);
      this.mTextHelper = var5;
      var5.loadFromAttributes(var2, var3);
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

   public boolean isEmojiCompatEnabled() {
      return this.getEmojiTextViewHelper().isEnabled();
   }

   public void setAllCaps(boolean var1) {
      super.setAllCaps(var1);
      this.getEmojiTextViewHelper().setAllCaps(var1);
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

   public void setEmojiCompatEnabled(boolean var1) {
      this.getEmojiTextViewHelper().setEnabled(var1);
   }

   public void setFilters(InputFilter[] var1) {
      super.setFilters(this.getEmojiTextViewHelper().getFilters(var1));
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
}
