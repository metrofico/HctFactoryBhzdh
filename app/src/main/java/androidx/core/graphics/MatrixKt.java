package androidx.core.graphics;

import android.graphics.Matrix;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0014\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u001a\u001a\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003\u001a\u001a\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u001a\u0015\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0086\n\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0001H\u0086\b¨\u0006\u0010"},
   d2 = {"rotationMatrix", "Landroid/graphics/Matrix;", "degrees", "", "px", "py", "scaleMatrix", "sx", "sy", "translationMatrix", "tx", "ty", "times", "m", "values", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class MatrixKt {
   public static final Matrix rotationMatrix(float var0, float var1, float var2) {
      Matrix var3 = new Matrix();
      var3.setRotate(var0, var1, var2);
      return var3;
   }

   // $FF: synthetic method
   public static Matrix rotationMatrix$default(float var0, float var1, float var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var1 = 0.0F;
      }

      if ((var3 & 4) != 0) {
         var2 = 0.0F;
      }

      return rotationMatrix(var0, var1, var2);
   }

   public static final Matrix scaleMatrix(float var0, float var1) {
      Matrix var2 = new Matrix();
      var2.setScale(var0, var1);
      return var2;
   }

   // $FF: synthetic method
   public static Matrix scaleMatrix$default(float var0, float var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var0 = 1.0F;
      }

      if ((var2 & 2) != 0) {
         var1 = 1.0F;
      }

      return scaleMatrix(var0, var1);
   }

   public static final Matrix times(Matrix var0, Matrix var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$times");
      Intrinsics.checkParameterIsNotNull(var1, "m");
      var0 = new Matrix(var0);
      var0.preConcat(var1);
      return var0;
   }

   public static final Matrix translationMatrix(float var0, float var1) {
      Matrix var2 = new Matrix();
      var2.setTranslate(var0, var1);
      return var2;
   }

   // $FF: synthetic method
   public static Matrix translationMatrix$default(float var0, float var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var0 = 0.0F;
      }

      if ((var2 & 2) != 0) {
         var1 = 0.0F;
      }

      return translationMatrix(var0, var1);
   }

   public static final float[] values(Matrix var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$values");
      float[] var1 = new float[9];
      var0.getValues(var1);
      return var1;
   }
}
