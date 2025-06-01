package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;

public class VectorEnabledTintResources extends ResourcesWrapper {
   public static final int MAX_SDK_WHERE_REQUIRED = 20;
   private static boolean sCompatVectorFromResourcesEnabled;
   private final WeakReference mContextRef;

   public VectorEnabledTintResources(Context var1, Resources var2) {
      super(var2);
      this.mContextRef = new WeakReference(var1);
   }

   public static boolean isCompatVectorFromResourcesEnabled() {
      return sCompatVectorFromResourcesEnabled;
   }

   public static void setCompatVectorFromResourcesEnabled(boolean var0) {
      sCompatVectorFromResourcesEnabled = var0;
   }

   public static boolean shouldBeUsed() {
      boolean var0;
      if (isCompatVectorFromResourcesEnabled() && VERSION.SDK_INT <= 20) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   public Drawable getDrawable(int var1) throws Resources.NotFoundException {
      Context var2 = (Context)this.mContextRef.get();
      return var2 != null ? ResourceManagerInternal.get().onDrawableLoadedFromResources(var2, this, var1) : this.getDrawableCanonical(var1);
   }
}
