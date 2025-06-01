package androidx.core.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Collection;

public final class PathUtils {
   private PathUtils() {
   }

   public static Collection flatten(Path var0) {
      return flatten(var0, 0.5F);
   }

   public static Collection flatten(Path var0, float var1) {
      float[] var11 = var0.approximate(var1);
      int var8 = var11.length / 3;
      ArrayList var12 = new ArrayList(var8);

      for(int var7 = 1; var7 < var8; ++var7) {
         int var9 = var7 * 3;
         int var10 = (var7 - 1) * 3;
         float var5 = var11[var9];
         float var6 = var11[var9 + 1];
         float var3 = var11[var9 + 2];
         float var2 = var11[var10];
         float var4 = var11[var10 + 1];
         var1 = var11[var10 + 2];
         if (var5 != var2 && (var6 != var4 || var3 != var1)) {
            var12.add(new PathSegment(new PointF(var4, var1), var2, new PointF(var6, var3), var5));
         }
      }

      return var12;
   }
}
