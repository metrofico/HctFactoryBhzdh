package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public interface TintableCompoundButton {
   ColorStateList getSupportButtonTintList();

   PorterDuff.Mode getSupportButtonTintMode();

   void setSupportButtonTintList(ColorStateList var1);

   void setSupportButtonTintMode(PorterDuff.Mode var1);
}
