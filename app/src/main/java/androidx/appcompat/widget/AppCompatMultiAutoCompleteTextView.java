package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.TintableBackgroundView;

public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements TintableBackgroundView, EmojiCompatConfigurationView {
   private static final int[] TINT_ATTRS = new int[]{16843126};
   private final AppCompatEmojiEditTextHelper mAppCompatEmojiEditTextHelper;
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private final AppCompatTextHelper mTextHelper;

   public AppCompatMultiAutoCompleteTextView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatMultiAutoCompleteTextView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.autoCompleteTextViewStyle);
   }

   public AppCompatMultiAutoCompleteTextView(Context var1, AttributeSet var2, int var3) {
      super(TintContextWrapper.wrap(var1), var2, var3);
      ThemeUtils.checkAppCompatTheme(this, this.getContext());
      TintTypedArray var4 = TintTypedArray.obtainStyledAttributes(this.getContext(), var2, TINT_ATTRS, var3, 0);
      if (var4.hasValue(0)) {
         this.setDropDownBackgroundDrawable(var4.getDrawable(0));
      }

      var4.recycle();
      AppCompatBackgroundHelper var5 = new AppCompatBackgroundHelper(this);
      this.mBackgroundTintHelper = var5;
      var5.loadFromAttributes(var2, var3);
      AppCompatTextHelper var6 = new AppCompatTextHelper(this);
      this.mTextHelper = var6;
      var6.loadFromAttributes(var2, var3);
      var6.applyCompoundDrawablesTints();
      AppCompatEmojiEditTextHelper var7 = new AppCompatEmojiEditTextHelper(this);
      this.mAppCompatEmojiEditTextHelper = var7;
      var7.loadFromAttributes(var2, var3);
      this.initEmojiKeyListener(var7);
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

   void initEmojiKeyListener(AppCompatEmojiEditTextHelper var1) {
      KeyListener var4 = this.getKeyListener();
      if (var1.isEmojiCapableKeyListener(var4)) {
         boolean var3 = super.isFocusable();
         int var2 = super.getInputType();
         KeyListener var5 = var1.getKeyListener(var4);
         if (var5 == var4) {
            return;
         }

         super.setKeyListener(var5);
         super.setRawInputType(var2);
         super.setFocusable(var3);
      }

   }

   public boolean isEmojiCompatEnabled() {
      return this.mAppCompatEmojiEditTextHelper.isEnabled();
   }

   public InputConnection onCreateInputConnection(EditorInfo var1) {
      InputConnection var2 = AppCompatHintHelper.onCreateInputConnection(super.onCreateInputConnection(var1), var1, this);
      return this.mAppCompatEmojiEditTextHelper.onCreateInputConnection(var2, var1);
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

   public void setDropDownBackgroundResource(int var1) {
      this.setDropDownBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), var1));
   }

   public void setEmojiCompatEnabled(boolean var1) {
      this.mAppCompatEmojiEditTextHelper.setEnabled(var1);
   }

   public void setKeyListener(KeyListener var1) {
      super.setKeyListener(this.mAppCompatEmojiEditTextHelper.getKeyListener(var1));
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

   public void setTextAppearance(Context var1, int var2) {
      super.setTextAppearance(var1, var2);
      AppCompatTextHelper var3 = this.mTextHelper;
      if (var3 != null) {
         var3.onSetTextAppearance(var1, var2);
      }

   }
}
