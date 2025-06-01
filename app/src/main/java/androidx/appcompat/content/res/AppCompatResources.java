package androidx.appcompat.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.ContextCompat;

public final class AppCompatResources {
   private AppCompatResources() {
   }

   public static ColorStateList getColorStateList(Context var0, int var1) {
      return ContextCompat.getColorStateList(var0, var1);
   }

   public static Drawable getDrawable(Context var0, int var1) {
      return ResourceManagerInternal.get().getDrawable(var0, var1);
   }
}
