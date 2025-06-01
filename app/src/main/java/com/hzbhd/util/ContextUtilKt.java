package com.hzbhd.util;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001e\u0010\u0000\u001a\u00020\u00018\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\f"},
   d2 = {"baseContext", "Landroid/content/Context;", "getBaseContext", "()Landroid/content/Context;", "setBaseContext", "(Landroid/content/Context;)V", "baseShare", "Landroid/content/SharedPreferences;", "getBaseShare", "()Landroid/content/SharedPreferences;", "setBaseShare", "(Landroid/content/SharedPreferences;)V", "thread_util_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class ContextUtilKt {
   public static Context baseContext;
   public static SharedPreferences baseShare;

   public static final Context getBaseContext() {
      Context var0 = baseContext;
      if (var0 != null) {
         return var0;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("baseContext");
         return null;
      }
   }

   public static final SharedPreferences getBaseShare() {
      SharedPreferences var0 = baseShare;
      if (var0 != null) {
         return var0;
      } else {
         Intrinsics.throwUninitializedPropertyAccessException("baseShare");
         return null;
      }
   }

   public static final void setBaseContext(Context var0) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      baseContext = var0;
   }

   public static final void setBaseShare(SharedPreferences var0) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      baseShare = var0;
   }
}
