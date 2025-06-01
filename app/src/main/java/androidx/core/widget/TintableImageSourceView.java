package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintableImageSourceView {
   ColorStateList getSupportImageTintList();

   PorterDuff.Mode getSupportImageTintMode();

   void setSupportImageTintList(ColorStateList var1);

   void setSupportImageTintMode(PorterDuff.Mode var1);
}
