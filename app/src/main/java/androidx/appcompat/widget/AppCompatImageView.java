package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.TintableImageSourceView;

public class AppCompatImageView extends ImageView implements TintableBackgroundView, TintableImageSourceView {
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   private boolean mHasLevel;
   private final AppCompatImageHelper mImageHelper;

   public AppCompatImageView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatImageView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public AppCompatImageView(Context var1, AttributeSet var2, int var3) {
      super(TintContextWrapper.wrap(var1), var2, var3);
      this.mHasLevel = false;
      ThemeUtils.checkAppCompatTheme(this, this.getContext());
      AppCompatBackgroundHelper var4 = new AppCompatBackgroundHelper(this);
      this.mBackgroundTintHelper = var4;
      var4.loadFromAttributes(var2, var3);
      AppCompatImageHelper var5 = new AppCompatImageHelper(this);
      this.mImageHelper = var5;
      var5.loadFromAttributes(var2, var3);
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      if (var1 != null) {
         var1.applySupportBackgroundTint();
      }

      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null) {
         var2.applySupportImageTint();
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

   public ColorStateList getSupportImageTintList() {
      AppCompatImageHelper var1 = this.mImageHelper;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getSupportImageTintList();
      } else {
         var2 = null;
      }

      return var2;
   }

   public PorterDuff.Mode getSupportImageTintMode() {
      AppCompatImageHelper var1 = this.mImageHelper;
      PorterDuff.Mode var2;
      if (var1 != null) {
         var2 = var1.getSupportImageTintMode();
      } else {
         var2 = null;
      }

      return var2;
   }

   public boolean hasOverlappingRendering() {
      boolean var1;
      if (this.mImageHelper.hasOverlappingRendering() && super.hasOverlappingRendering()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
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

   public void setImageBitmap(Bitmap var1) {
      super.setImageBitmap(var1);
      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null) {
         var2.applySupportImageTint();
      }

   }

   public void setImageDrawable(Drawable var1) {
      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null && var1 != null && !this.mHasLevel) {
         var2.obtainLevelFromDrawable(var1);
      }

      super.setImageDrawable(var1);
      AppCompatImageHelper var3 = this.mImageHelper;
      if (var3 != null) {
         var3.applySupportImageTint();
         if (!this.mHasLevel) {
            this.mImageHelper.applyImageLevel();
         }
      }

   }

   public void setImageLevel(int var1) {
      super.setImageLevel(var1);
      this.mHasLevel = true;
   }

   public void setImageResource(int var1) {
      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null) {
         var2.setImageResource(var1);
      }

   }

   public void setImageURI(Uri var1) {
      super.setImageURI(var1);
      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null) {
         var2.applySupportImageTint();
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

   public void setSupportImageTintList(ColorStateList var1) {
      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null) {
         var2.setSupportImageTintList(var1);
      }

   }

   public void setSupportImageTintMode(PorterDuff.Mode var1) {
      AppCompatImageHelper var2 = this.mImageHelper;
      if (var2 != null) {
         var2.setSupportImageTintMode(var1);
      }

   }
}
