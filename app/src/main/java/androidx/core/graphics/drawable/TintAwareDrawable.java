package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintAwareDrawable {
   void setTint(int var1);

   void setTintList(ColorStateList var1);

   void setTintMode(PorterDuff.Mode var1);
}
