package androidx.core.database;

import android.database.Cursor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\n\u001a\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\f\u001a\u001c\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\u000f\u001a\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¢\u0006\u0002\u0010\u0012\u001a\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¨\u0006\u0015"},
   d2 = {"getBlobOrNull", "", "Landroid/database/Cursor;", "index", "", "getDoubleOrNull", "", "(Landroid/database/Cursor;I)Ljava/lang/Double;", "getFloatOrNull", "", "(Landroid/database/Cursor;I)Ljava/lang/Float;", "getIntOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Integer;", "getLongOrNull", "", "(Landroid/database/Cursor;I)Ljava/lang/Long;", "getShortOrNull", "", "(Landroid/database/Cursor;I)Ljava/lang/Short;", "getStringOrNull", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class CursorKt {
   public static final byte[] getBlobOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getBlobOrNull");
      byte[] var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getBlob(var1);
      }

      return var2;
   }

   public static final Double getDoubleOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getDoubleOrNull");
      Double var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getDouble(var1);
      }

      return var2;
   }

   public static final Float getFloatOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getFloatOrNull");
      Float var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getFloat(var1);
      }

      return var2;
   }

   public static final Integer getIntOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getIntOrNull");
      Integer var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getInt(var1);
      }

      return var2;
   }

   public static final Long getLongOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getLongOrNull");
      Long var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getLong(var1);
      }

      return var2;
   }

   public static final Short getShortOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getShortOrNull");
      Short var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getShort(var1);
      }

      return var2;
   }

   public static final String getStringOrNull(Cursor var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$getStringOrNull");
      String var2;
      if (var0.isNull(var1)) {
         var2 = null;
      } else {
         var2 = var0.getString(var1);
      }

      return var2;
   }
}
