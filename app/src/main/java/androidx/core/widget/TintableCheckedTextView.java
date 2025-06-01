package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintableCheckedTextView {
   ColorStateList getSupportCheckMarkTintList();

   PorterDuff.Mode getSupportCheckMarkTintMode();

   void setSupportCheckMarkTintList(ColorStateList var1);

   void setSupportCheckMarkTintMode(PorterDuff.Mode var1);
}
