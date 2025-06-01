package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CheckedTextViewCompat;

class AppCompatCheckedTextViewHelper {
   private ColorStateList mCheckMarkTintList = null;
   private PorterDuff.Mode mCheckMarkTintMode = null;
   private boolean mHasCheckMarkTint = false;
   private boolean mHasCheckMarkTintMode = false;
   private boolean mSkipNextApply;
   private final CheckedTextView mView;

   AppCompatCheckedTextViewHelper(CheckedTextView var1) {
      this.mView = var1;
   }

   void applyCheckMarkTint() {
      Drawable var1 = CheckedTextViewCompat.getCheckMarkDrawable(this.mView);
      if (var1 != null && (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode)) {
         var1 = DrawableCompat.wrap(var1).mutate();
         if (this.mHasCheckMarkTint) {
            DrawableCompat.setTintList(var1, this.mCheckMarkTintList);
         }

         if (this.mHasCheckMarkTintMode) {
            DrawableCompat.setTintMode(var1, this.mCheckMarkTintMode);
         }

         if (var1.isStateful()) {
            var1.setState(this.mView.getDrawableState());
         }

         this.mView.setCheckMarkDrawable(var1);
      }

   }

   ColorStateList getSupportCheckMarkTintList() {
      return this.mCheckMarkTintList;
   }

   PorterDuff.Mode getSupportCheckMarkTintMode() {
      return this.mCheckMarkTintMode;
   }

   void loadFromAttributes(AttributeSet param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   void onSetCheckMarkDrawable() {
      if (this.mSkipNextApply) {
         this.mSkipNextApply = false;
      } else {
         this.mSkipNextApply = true;
         this.applyCheckMarkTint();
      }
   }

   void setSupportCheckMarkTintList(ColorStateList var1) {
      this.mCheckMarkTintList = var1;
      this.mHasCheckMarkTint = true;
      this.applyCheckMarkTint();
   }

   void setSupportCheckMarkTintMode(PorterDuff.Mode var1) {
      this.mCheckMarkTintMode = var1;
      this.mHasCheckMarkTintMode = true;
      this.applyCheckMarkTint();
   }
}
