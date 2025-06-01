package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TintableCheckedTextView;

public class AppCompatCheckedTextView extends CheckedTextView implements TintableCheckedTextView, TintableBackgroundView, EmojiCompatConfigurationView {
   private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private final AppCompatCheckedTextViewHelper mCheckedHelper;
   private final AppCompatTextHelper mTextHelper;

   public AppCompatCheckedTextView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatCheckedTextView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.checkedTextViewStyle);
   }

   public AppCompatCheckedTextView(Context var1, AttributeSet var2, int var3) {
      super(TintContextWrapper.wrap(var1), var2, var3);
      ThemeUtils.checkAppCompatTheme(this, this.getContext());
      AppCompatTextHelper var4 = new AppCompatTextHelper(this);
      this.mTextHelper = var4;
      var4.loadFromAttributes(var2, var3);
      var4.applyCompoundDrawablesTints();
      AppCompatBackgroundHelper var5 = new AppCompatBackgroundHelper(this);
      this.mBackgroundTintHelper = var5;
      var5.loadFromAttributes(var2, var3);
      AppCompatCheckedTextViewHelper var6 = new AppCompatCheckedTextViewHelper(this);
      this.mCheckedHelper = var6;
      var6.loadFromAttributes(var2, var3);
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
      AppCompatTextHelper var1 = this.mTextHelper;
      if (var1 != null) {
         var1.applyCompoundDrawablesTints();
      }

      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.applySupportBackgroundTint();
      }

      AppCompatCheckedTextViewHelper var3 = this.mCheckedHelper;
      if (var3 != null) {
         var3.applyCheckMarkTint();
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

   public ColorStateList getSupportCheckMarkTintList() {
      AppCompatCheckedTextViewHelper var1 = this.mCheckedHelper;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getSupportCheckMarkTintList();
      } else {
         var2 = null;
      }

      return var2;
   }

   public PorterDuff.Mode getSupportCheckMarkTintMode() {
      AppCompatCheckedTextViewHelper var1 = this.mCheckedHelper;
      PorterDuff.Mode var2;
      if (var1 != null) {
         var2 = var1.getSupportCheckMarkTintMode();
      } else {
         var2 = null;
      }

      return var2;
   }

   public boolean isEmojiCompatEnabled() {
      return this.getEmojiTextViewHelper().isEnabled();
   }

   public InputConnection onCreateInputConnection(EditorInfo var1) {
      return AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(var1), var1, this);
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

   public void setCheckMarkDrawable(int var1) {
      this.setCheckMarkDrawable(AppCompatResources.getDrawable(this.getContext(), var1));
   }

   public void setCheckMarkDrawable(Drawable var1) {
      super.setCheckMarkDrawable(var1);
      AppCompatCheckedTextViewHelper var2 = this.mCheckedHelper;
      if (var2 != null) {
         var2.onSetCheckMarkDrawable();
      }

   }

   public void setCustomSelectionActionModeCallback(ActionMode.Callback var1) {
      super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, var1));
   }

   public void setEmojiCompatEnabled(boolean var1) {
      this.getEmojiTextViewHelper().setEnabled(var1);
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

   public void setSupportCheckMarkTintList(ColorStateList var1) {
      AppCompatCheckedTextViewHelper var2 = this.mCheckedHelper;
      if (var2 != null) {
         var2.setSupportCheckMarkTintList(var1);
      }

   }

   public void setSupportCheckMarkTintMode(PorterDuff.Mode var1) {
      AppCompatCheckedTextViewHelper var2 = this.mCheckedHelper;
      if (var2 != null) {
         var2.setSupportCheckMarkTintMode(var1);
      }

   }

   public void setTextAppearance(Context var1, int var2) {
      super.setTextAppearance(var1, var2);
      AppCompatTextHelper var3 = this.mTextHelper;
      if (var3 != null) {
         var3.onSetTextAppearance(var1, var2);
      }

   }
}
