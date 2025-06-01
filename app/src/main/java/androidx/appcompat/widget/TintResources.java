package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
   private final WeakReference mContextRef;

   public TintResources(Context var1, Resources var2) {
      super(var2);
      this.mContextRef = new WeakReference(var1);
   }

   public Drawable getDrawable(int var1) throws Resources.NotFoundException {
      Drawable var2 = this.getDrawableCanonical(var1);
      Context var3 = (Context)this.mContextRef.get();
      if (var2 != null && var3 != null) {
         ResourceManagerInternal.get().tintDrawableUsingColorFilter(var3, var1, var2);
      }

      return var2;
   }
}
