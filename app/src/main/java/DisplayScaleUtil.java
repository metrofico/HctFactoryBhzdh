import android.content.Context;
import android.util.DisplayMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"},
   d2 = {"LDisplayScaleUtil;", "", "()V", "scale", "", "context", "Landroid/content/Context;", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class DisplayScaleUtil {
   public static final DisplayScaleUtil INSTANCE = new DisplayScaleUtil();

   private DisplayScaleUtil() {
   }

   public final void scale(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      DisplayMetrics var2 = var1.getResources().getDisplayMetrics();
      var2.density = 1.0F;
      var2.scaledDensity = 1.0F;
      var2.densityDpi = 160;
   }
}
