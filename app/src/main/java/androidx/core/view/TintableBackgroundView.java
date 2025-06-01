package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintableBackgroundView {
   ColorStateList getSupportBackgroundTintList();

   PorterDuff.Mode getSupportBackgroundTintMode();

   void setSupportBackgroundTintList(ColorStateList var1);

   void setSupportBackgroundTintMode(PorterDuff.Mode var1);
}
