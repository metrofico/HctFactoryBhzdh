package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

public class AppCompatImageHelper {
   private TintInfo mImageTint;
   private TintInfo mInternalImageTint;
   private int mLevel = 0;
   private TintInfo mTmpInfo;
   private final ImageView mView;

   public AppCompatImageHelper(ImageView var1) {
      this.mView = var1;
   }

   private boolean applyFrameworkTintUsingColorFilter(Drawable var1) {
      if (this.mTmpInfo == null) {
         this.mTmpInfo = new TintInfo();
      }

      TintInfo var2 = this.mTmpInfo;
      var2.clear();
      ColorStateList var3 = ImageViewCompat.getImageTintList(this.mView);
      if (var3 != null) {
         var2.mHasTintList = true;
         var2.mTintList = var3;
      }

      PorterDuff.Mode var4 = ImageViewCompat.getImageTintMode(this.mView);
      if (var4 != null) {
         var2.mHasTintMode = true;
         var2.mTintMode = var4;
      }

      if (!var2.mHasTintList && !var2.mHasTintMode) {
         return false;
      } else {
         AppCompatDrawableManager.tintDrawable(var1, var2, this.mView.getDrawableState());
         return true;
      }
   }

   private boolean shouldApplyFrameworkTintUsingColorFilter() {
      int var1 = VERSION.SDK_INT;
      boolean var2 = true;
      if (var1 > 21) {
         if (this.mInternalImageTint == null) {
            var2 = false;
         }

         return var2;
      } else {
         return var1 == 21;
      }
   }

   void applyImageLevel() {
      if (this.mView.getDrawable() != null) {
         this.mView.getDrawable().setLevel(this.mLevel);
      }

   }

   void applySupportImageTint() {
      Drawable var1 = this.mView.getDrawable();
      if (var1 != null) {
         DrawableUtils.fixDrawable(var1);
      }

      if (var1 != null) {
         if (this.shouldApplyFrameworkTintUsingColorFilter() && this.applyFrameworkTintUsingColorFilter(var1)) {
            return;
         }

         TintInfo var2 = this.mImageTint;
         if (var2 != null) {
            AppCompatDrawableManager.tintDrawable(var1, var2, this.mView.getDrawableState());
         } else {
            var2 = this.mInternalImageTint;
            if (var2 != null) {
               AppCompatDrawableManager.tintDrawable(var1, var2, this.mView.getDrawableState());
            }
         }
      }

   }

   ColorStateList getSupportImageTintList() {
      TintInfo var1 = this.mImageTint;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.mTintList;
      } else {
         var2 = null;
      }

      return var2;
   }

   PorterDuff.Mode getSupportImageTintMode() {
      TintInfo var1 = this.mImageTint;
      PorterDuff.Mode var2;
      if (var1 != null) {
         var2 = var1.mTintMode;
      } else {
         var2 = null;
      }

      return var2;
   }

   boolean hasOverlappingRendering() {
      Drawable var1 = this.mView.getBackground();
      return VERSION.SDK_INT < 21 || !(var1 instanceof RippleDrawable);
   }

   public void loadFromAttributes(AttributeSet var1, int var2) {
      TintTypedArray var4 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), var1, R.styleable.AppCompatImageView, var2, 0);
      ImageView var3 = this.mView;
      ViewCompat.saveAttributeDataForStyleable(var3, var3.getContext(), R.styleable.AppCompatImageView, var1, var4.getWrappedTypeArray(), var2, 0);

      label484: {
         Throwable var10000;
         label488: {
            boolean var10001;
            Drawable var63;
            try {
               var63 = this.mView.getDrawable();
            } catch (Throwable var60) {
               var10000 = var60;
               var10001 = false;
               break label488;
            }

            Drawable var61 = var63;
            if (var63 == null) {
               try {
                  var2 = var4.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1);
               } catch (Throwable var59) {
                  var10000 = var59;
                  var10001 = false;
                  break label488;
               }

               var61 = var63;
               if (var2 != -1) {
                  try {
                     var63 = AppCompatResources.getDrawable(this.mView.getContext(), var2);
                  } catch (Throwable var58) {
                     var10000 = var58;
                     var10001 = false;
                     break label488;
                  }

                  var61 = var63;
                  if (var63 != null) {
                     try {
                        this.mView.setImageDrawable(var63);
                     } catch (Throwable var57) {
                        var10000 = var57;
                        var10001 = false;
                        break label488;
                     }

                     var61 = var63;
                  }
               }
            }

            if (var61 != null) {
               try {
                  DrawableUtils.fixDrawable(var61);
               } catch (Throwable var56) {
                  var10000 = var56;
                  var10001 = false;
                  break label488;
               }
            }

            try {
               if (var4.hasValue(R.styleable.AppCompatImageView_tint)) {
                  ImageViewCompat.setImageTintList(this.mView, var4.getColorStateList(R.styleable.AppCompatImageView_tint));
               }
            } catch (Throwable var55) {
               var10000 = var55;
               var10001 = false;
               break label488;
            }

            label462:
            try {
               if (var4.hasValue(R.styleable.AppCompatImageView_tintMode)) {
                  ImageViewCompat.setImageTintMode(this.mView, DrawableUtils.parseTintMode(var4.getInt(R.styleable.AppCompatImageView_tintMode, -1), (PorterDuff.Mode)null));
               }
               break label484;
            } catch (Throwable var54) {
               var10000 = var54;
               var10001 = false;
               break label462;
            }
         }

         Throwable var62 = var10000;
         var4.recycle();
         throw var62;
      }

      var4.recycle();
   }

   void obtainLevelFromDrawable(Drawable var1) {
      this.mLevel = var1.getLevel();
   }

   public void setImageResource(int var1) {
      if (var1 != 0) {
         Drawable var2 = AppCompatResources.getDrawable(this.mView.getContext(), var1);
         if (var2 != null) {
            DrawableUtils.fixDrawable(var2);
         }

         this.mView.setImageDrawable(var2);
      } else {
         this.mView.setImageDrawable((Drawable)null);
      }

      this.applySupportImageTint();
   }

   void setInternalImageTint(ColorStateList var1) {
      if (var1 != null) {
         if (this.mInternalImageTint == null) {
            this.mInternalImageTint = new TintInfo();
         }

         this.mInternalImageTint.mTintList = var1;
         this.mInternalImageTint.mHasTintList = true;
      } else {
         this.mInternalImageTint = null;
      }

      this.applySupportImageTint();
   }

   void setSupportImageTintList(ColorStateList var1) {
      if (this.mImageTint == null) {
         this.mImageTint = new TintInfo();
      }

      this.mImageTint.mTintList = var1;
      this.mImageTint.mHasTintList = true;
      this.applySupportImageTint();
   }

   void setSupportImageTintMode(PorterDuff.Mode var1) {
      if (this.mImageTint == null) {
         this.mImageTint = new TintInfo();
      }

      this.mImageTint.mTintMode = var1;
      this.mImageTint.mHasTintMode = true;
      this.applySupportImageTint();
   }
}
