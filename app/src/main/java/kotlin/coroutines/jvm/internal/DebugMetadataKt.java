package kotlin.coroutines.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b*\u00020\bH\u0001¢\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\bH\u0001¢\u0006\u0002\b\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class DebugMetadataKt {
   private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

   private static final void checkDebugMetadataVersion(int var0, int var1) {
      if (var1 > var0) {
         throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + var0 + ", got " + var1 + ". Please update the Kotlin standard library.").toString());
      }
   }

   private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl var0) {
      return (DebugMetadata)var0.getClass().getAnnotation(DebugMetadata.class);
   }

   private static final int getLabel(BaseContinuationImpl param0) {
      // $FF: Couldn't be decompiled
   }

   public static final String[] getSpilledVariableFieldMapping(BaseContinuationImpl var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      DebugMetadata var5 = getDebugMetadataAnnotation(var0);
      if (var5 == null) {
         return null;
      } else {
         checkDebugMetadataVersion(1, var5.v());
         ArrayList var4 = new ArrayList();
         int var2 = getLabel(var0);
         int[] var6 = var5.i();
         int var3 = var6.length;

         for(int var1 = 0; var1 < var3; ++var1) {
            if (var6[var1] == var2) {
               var4.add(var5.s()[var1]);
               var4.add(var5.n()[var1]);
            }
         }

         Object[] var7 = ((Collection)var4).toArray(new String[0]);
         Intrinsics.checkNotNull(var7, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
         return (String[])var7;
      }
   }

   public static final StackTraceElement getStackTraceElement(BaseContinuationImpl var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      DebugMetadata var2 = getDebugMetadataAnnotation(var0);
      if (var2 == null) {
         return null;
      } else {
         checkDebugMetadataVersion(1, var2.v());
         int var1 = getLabel(var0);
         if (var1 < 0) {
            var1 = -1;
         } else {
            var1 = var2.l()[var1];
         }

         String var3 = ModuleNameRetriever.INSTANCE.getModuleName(var0);
         if (var3 == null) {
            var3 = var2.c();
         } else {
            var3 = var3 + '/' + var2.c();
         }

         return new StackTraceElement(var3, var2.m(), var2.f(), var1);
      }
   }
}
