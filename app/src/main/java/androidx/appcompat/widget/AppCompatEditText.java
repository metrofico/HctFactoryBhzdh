package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import androidx.appcompat.R;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentViewBehavior;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TextViewOnReceiveContentListener;

public class AppCompatEditText extends EditText implements TintableBackgroundView, OnReceiveContentViewBehavior, EmojiCompatConfigurationView {
   private final AppCompatEmojiEditTextHelper mAppCompatEmojiEditTextHelper;
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private final TextViewOnReceiveContentListener mDefaultOnReceiveContentListener;
   private final AppCompatTextClassifierHelper mTextClassifierHelper;
   private final AppCompatTextHelper mTextHelper;

   public AppCompatEditText(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatEditText(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.editTextStyle);
   }

   public AppCompatEditText(Context var1, AttributeSet var2, int var3) {
      super(TintContextWrapper.wrap(var1), var2, var3);
      ThemeUtils.checkAppCompatTheme(this, this.getContext());
      AppCompatBackgroundHelper var4 = new AppCompatBackgroundHelper(this);
      this.mBackgroundTintHelper = var4;
      var4.loadFromAttributes(var2, var3);
      AppCompatTextHelper var5 = new AppCompatTextHelper(this);
      this.mTextHelper = var5;
      var5.loadFromAttributes(var2, var3);
      var5.applyCompoundDrawablesTints();
      this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
      this.mDefaultOnReceiveContentListener = new TextViewOnReceiveContentListener();
      AppCompatEmojiEditTextHelper var6 = new AppCompatEmojiEditTextHelper(this);
      this.mAppCompatEmojiEditTextHelper = var6;
      var6.loadFromAttributes(var2, var3);
      this.initEmojiKeyListener(var6);
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

   public Editable getText() {
      return VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
   }

   public TextClassifier getTextClassifier() {
      if (VERSION.SDK_INT < 28) {
         AppCompatTextClassifierHelper var1 = this.mTextClassifierHelper;
         if (var1 != null) {
            return var1.getTextClassifier();
         }
      }

      return super.getTextClassifier();
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
      InputConnection var2 = super.onCreateInputConnection(var1);
      this.mTextHelper.populateSurroundingTextIfNeeded(this, var2, var1);
      InputConnection var3 = AppCompatHintHelper.onCreateInputConnection(var2, var1, this);
      var2 = var3;
      if (var3 != null) {
         var2 = var3;
         if (VERSION.SDK_INT <= 30) {
            String[] var4 = ViewCompat.getOnReceiveContentMimeTypes(this);
            var2 = var3;
            if (var4 != null) {
               EditorInfoCompat.setContentMimeTypes(var1, var4);
               var2 = InputConnectionCompat.createWrapper((View)this, (InputConnection)var3, (EditorInfo)var1);
            }
         }
      }

      return this.mAppCompatEmojiEditTextHelper.onCreateInputConnection(var2, var1);
   }

   public boolean onDragEvent(DragEvent var1) {
      return AppCompatReceiveContentHelper.maybeHandleDragEventViaPerformReceiveContent(this, var1) ? true : super.onDragEvent(var1);
   }

   public ContentInfoCompat onReceiveContent(ContentInfoCompat var1) {
      return this.mDefaultOnReceiveContentListener.onReceiveContent(this, var1);
   }

   public boolean onTextContextMenuItem(int var1) {
      return AppCompatReceiveContentHelper.maybeHandleMenuActionViaPerformReceiveContent(this, var1) ? true : super.onTextContextMenuItem(var1);
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

   public void setTextClassifier(TextClassifier var1) {
      if (VERSION.SDK_INT < 28) {
         AppCompatTextClassifierHelper var2 = this.mTextClassifierHelper;
         if (var2 != null) {
            var2.setTextClassifier(var1);
            return;
         }
      }

      super.setTextClassifier(var1);
   }
}
