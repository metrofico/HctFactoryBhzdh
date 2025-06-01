package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0086\b¢\u0006\u0002\u0010\b¨\u0006\t"},
   d2 = {"transaction", "T", "Landroid/database/sqlite/SQLiteDatabase;", "exclusive", "", "body", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroid/database/sqlite/SQLiteDatabase;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SQLiteDatabaseKt {
   public static final Object transaction(SQLiteDatabase var0, boolean var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$transaction");
      Intrinsics.checkParameterIsNotNull(var2, "body");
      if (var1) {
         var0.beginTransaction();
      } else {
         var0.beginTransactionNonExclusive();
      }

      Object var5;
      try {
         var5 = var2.invoke(var0);
         var0.setTransactionSuccessful();
      } finally {
         InlineMarker.finallyStart(1);
         var0.endTransaction();
         InlineMarker.finallyEnd(1);
      }

      return var5;
   }

   // $FF: synthetic method
   public static Object transaction$default(SQLiteDatabase var0, boolean var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = true;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$transaction");
      Intrinsics.checkParameterIsNotNull(var2, "body");
      if (var1) {
         var0.beginTransaction();
      } else {
         var0.beginTransactionNonExclusive();
      }

      Object var7;
      try {
         var7 = var2.invoke(var0);
         var0.setTransactionSuccessful();
      } finally {
         InlineMarker.finallyStart(1);
         var0.endTransaction();
         InlineMarker.finallyEnd(1);
      }

      return var7;
   }
}
