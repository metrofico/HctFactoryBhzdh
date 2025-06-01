package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintableCompoundDrawablesView {
   ColorStateList getSupportCompoundDrawablesTintList();

   PorterDuff.Mode getSupportCompoundDrawablesTintMode();

   void setSupportCompoundDrawablesTintList(ColorStateList var1);

   void setSupportCompoundDrawablesTintMode(PorterDuff.Mode var1);
}
