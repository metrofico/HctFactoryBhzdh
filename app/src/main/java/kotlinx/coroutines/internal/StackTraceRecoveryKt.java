package kotlinx.coroutines.internal;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u001a\u0014\u0010\u0006\u001a\u00060\u0007j\u0002`\b2\u0006\u0010\t\u001a\u00020\u0001H\u0007\u001a9\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u0002H\u000b2\u0006\u0010\u000e\u001a\u0002H\u000b2\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0010H\u0002¢\u0006\u0002\u0010\u0011\u001a\u001e\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00102\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002\u001a1\u0010\u0016\u001a\u00020\u00172\u0010\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00192\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u0010H\u0002¢\u0006\u0002\u0010\u001a\u001a\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\fH\u0080Hø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a+\u0010\u001f\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000b2\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H\u0002¢\u0006\u0002\u0010 \u001a\u001f\u0010!\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\"\u001a,\u0010!\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000b2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030#H\u0080\b¢\u0006\u0002\u0010$\u001a \u0010%\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0080\b¢\u0006\u0002\u0010\"\u001a\u001f\u0010&\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\u001d\u001a\u0002H\u000bH\u0000¢\u0006\u0002\u0010\"\u001a1\u0010'\u001a\u0018\u0012\u0004\u0012\u0002H\u000b\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00190(\"\b\b\u0000\u0010\u000b*\u00020\f*\u0002H\u000bH\u0002¢\u0006\u0002\u0010)\u001a\u001c\u0010*\u001a\u00020+*\u00060\u0007j\u0002`\b2\n\u0010,\u001a\u00060\u0007j\u0002`\bH\u0002\u001a#\u0010-\u001a\u00020.*\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00192\u0006\u0010/\u001a\u00020\u0001H\u0002¢\u0006\u0002\u00100\u001a\u0014\u00101\u001a\u00020\u0017*\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000\u001a\u0010\u00102\u001a\u00020+*\u00060\u0007j\u0002`\bH\u0000\u001a\u001b\u00103\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f*\u0002H\u000bH\u0002¢\u0006\u0002\u0010\"\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u00104\"\u00020\u00142\u00020\u0014*\f\b\u0000\u00105\"\u00020\u00072\u00020\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"},
   d2 = {"baseContinuationImplClass", "", "baseContinuationImplClassName", "kotlin.jvm.PlatformType", "stackTraceRecoveryClass", "stackTraceRecoveryClassName", "artificialFrame", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "message", "createFinalException", "E", "", "cause", "result", "resultStackTrace", "Ljava/util/ArrayDeque;", "(Ljava/lang/Throwable;Ljava/lang/Throwable;Ljava/util/ArrayDeque;)Ljava/lang/Throwable;", "createStackTrace", "continuation", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "mergeRecoveredTraces", "", "recoveredStacktrace", "", "([Ljava/lang/StackTraceElement;Ljava/util/ArrayDeque;)V", "recoverAndThrow", "", "exception", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverFromStackFrame", "(Ljava/lang/Throwable;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Ljava/lang/Throwable;", "recoverStackTrace", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "Lkotlin/coroutines/Continuation;", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Throwable;", "unwrap", "unwrapImpl", "causeAndStacktrace", "Lkotlin/Pair;", "(Ljava/lang/Throwable;)Lkotlin/Pair;", "elementWiseEquals", "", "e", "frameIndex", "", "methodName", "([Ljava/lang/StackTraceElement;Ljava/lang/String;)I", "initCause", "isArtificial", "sanitizeStackTrace", "CoroutineStackFrame", "StackTraceElement", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class StackTraceRecoveryKt {
   private static final String baseContinuationImplClass = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
   private static final String baseContinuationImplClassName;
   private static final String stackTraceRecoveryClass = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
   private static final String stackTraceRecoveryClassName;

   static {
      String var2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
      Object var1 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";

      Result.Companion var0;
      Object var10;
      label83:
      try {
         var0 = Result.Companion;
         var10 = Result.constructor_impl(Class.forName("kotlin.coroutines.jvm.internal.BaseContinuationImpl").getCanonicalName());
      } catch (Throwable var9) {
         var0 = Result.Companion;
         var10 = Result.constructor_impl(ResultKt.createFailure(var9));
         break label83;
      }

      if (Result.exceptionOrNull_impl(var10) == null) {
         var1 = var10;
      }

      baseContinuationImplClassName = (String)var1;

      label77:
      try {
         var0 = Result.Companion;
         var10 = Result.constructor_impl(Class.forName("kotlinx.coroutines.internal.StackTraceRecoveryKt").getCanonicalName());
      } catch (Throwable var8) {
         var0 = Result.Companion;
         var10 = Result.constructor_impl(ResultKt.createFailure(var8));
         break label77;
      }

      var1 = var2;
      if (Result.exceptionOrNull_impl(var10) == null) {
         var1 = var10;
      }

      stackTraceRecoveryClassName = (String)var1;
   }

   // $FF: synthetic method
   public static void CoroutineStackFrame$annotations() {
   }

   // $FF: synthetic method
   public static void StackTraceElement$annotations() {
   }

   // $FF: synthetic method
   public static final Throwable access$recoverFromStackFrame(Throwable var0, CoroutineStackFrame var1) {
      return recoverFromStackFrame(var0, var1);
   }

   public static final StackTraceElement artificialFrame(String var0) {
      return new StackTraceElement("\b\b\b(" + var0, "\b", "\b", -1);
   }

   private static final Pair causeAndStacktrace(Throwable var0) {
      Throwable var3 = var0.getCause();
      Pair var5;
      if (var3 != null && Intrinsics.areEqual((Object)var3.getClass(), (Object)var0.getClass())) {
         StackTraceElement[] var4 = var0.getStackTrace();
         int var2 = var4.length;
         int var1 = 0;

         boolean var6;
         while(true) {
            if (var1 >= var2) {
               var6 = false;
               break;
            }

            if (isArtificial(var4[var1])) {
               var6 = true;
               break;
            }

            ++var1;
         }

         if (var6) {
            var5 = TuplesKt.to(var3, var4);
         } else {
            var5 = TuplesKt.to(var0, new StackTraceElement[0]);
         }
      } else {
         var5 = TuplesKt.to(var0, new StackTraceElement[0]);
      }

      return var5;
   }

   private static final Throwable createFinalException(Throwable var0, Throwable var1, ArrayDeque var2) {
      var2.addFirst(artificialFrame("Coroutine boundary"));
      StackTraceElement[] var6 = var0.getStackTrace();
      int var5 = frameIndex(var6, baseContinuationImplClassName);
      byte var4 = 0;
      if (var5 == -1) {
         Object[] var8 = ((Collection)var2).toArray(new StackTraceElement[0]);
         if (var8 != null) {
            var1.setStackTrace((StackTraceElement[])var8);
            return var1;
         } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
         }
      } else {
         StackTraceElement[] var7 = new StackTraceElement[var2.size() + var5];

         int var3;
         for(var3 = 0; var3 < var5; ++var3) {
            var7[var3] = var6[var3];
         }

         Iterator var9 = ((Iterable)var2).iterator();

         for(var3 = var4; var9.hasNext(); ++var3) {
            var7[var5 + var3] = (StackTraceElement)var9.next();
         }

         var1.setStackTrace(var7);
         return var1;
      }
   }

   private static final ArrayDeque createStackTrace(CoroutineStackFrame var0) {
      ArrayDeque var2 = new ArrayDeque();
      StackTraceElement var3 = var0.getStackTraceElement();
      CoroutineStackFrame var1 = var0;
      if (var3 != null) {
         var2.add(var3);
         var1 = var0;
      }

      while(true) {
         var0 = var1;
         if (!(var1 instanceof CoroutineStackFrame)) {
            var0 = null;
         }

         if (var0 == null) {
            break;
         }

         var0 = var0.getCallerFrame();
         if (var0 == null) {
            break;
         }

         var3 = var0.getStackTraceElement();
         var1 = var0;
         if (var3 != null) {
            var2.add(var3);
            var1 = var0;
         }
      }

      return var2;
   }

   private static final boolean elementWiseEquals(StackTraceElement var0, StackTraceElement var1) {
      boolean var2;
      if (var0.getLineNumber() == var1.getLineNumber() && Intrinsics.areEqual((Object)var0.getMethodName(), (Object)var1.getMethodName()) && Intrinsics.areEqual((Object)var0.getFileName(), (Object)var1.getFileName()) && Intrinsics.areEqual((Object)var0.getClassName(), (Object)var1.getClassName())) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static final int frameIndex(StackTraceElement[] var0, String var1) {
      int var3 = var0.length;
      int var2 = 0;

      while(true) {
         if (var2 >= var3) {
            var2 = -1;
            break;
         }

         if (Intrinsics.areEqual((Object)var1, (Object)var0[var2].getClassName())) {
            break;
         }

         ++var2;
      }

      return var2;
   }

   public static final void initCause(Throwable var0, Throwable var1) {
      var0.initCause(var1);
   }

   public static final boolean isArtificial(StackTraceElement var0) {
      return StringsKt.startsWith$default(var0.getClassName(), "\b\b\b", false, 2, (Object)null);
   }

   private static final void mergeRecoveredTraces(StackTraceElement[] var0, ArrayDeque var1) {
      int var3 = var0.length;
      int var2 = 0;

      while(true) {
         if (var2 >= var3) {
            var2 = -1;
            break;
         }

         if (isArtificial(var0[var2])) {
            break;
         }

         ++var2;
      }

      var3 = var2 + 1;
      var2 = var0.length - 1;
      if (var2 >= var3) {
         while(true) {
            if (elementWiseEquals(var0[var2], (StackTraceElement)var1.getLast())) {
               var1.removeLast();
            }

            var1.addFirst(var0[var2]);
            if (var2 == var3) {
               break;
            }

            --var2;
         }
      }

   }

   public static final Object recoverAndThrow(Throwable var0, Continuation var1) {
      if (DebugKt.getRECOVER_STACK_TRACES()) {
         if (!(var1 instanceof CoroutineStackFrame)) {
            throw var0;
         } else {
            throw access$recoverFromStackFrame(var0, (CoroutineStackFrame)var1);
         }
      } else {
         throw var0;
      }
   }

   private static final Object recoverAndThrow$$forInline(Throwable var0, Continuation var1) {
      if (DebugKt.getRECOVER_STACK_TRACES()) {
         InlineMarker.mark(0);
         if (!(var1 instanceof CoroutineStackFrame)) {
            throw var0;
         } else {
            throw access$recoverFromStackFrame(var0, (CoroutineStackFrame)var1);
         }
      } else {
         throw var0;
      }
   }

   private static final Throwable recoverFromStackFrame(Throwable var0, CoroutineStackFrame var1) {
      Pair var2 = causeAndStacktrace(var0);
      Throwable var3 = (Throwable)var2.component1();
      StackTraceElement[] var5 = (StackTraceElement[])var2.component2();
      Throwable var4 = ExceptionsConstuctorKt.tryCopyException(var3);
      Throwable var7 = var0;
      if (var4 != null) {
         if (Intrinsics.areEqual((Object)var4.getMessage(), (Object)var3.getMessage()) ^ true) {
            return var0;
         }

         ArrayDeque var6 = createStackTrace(var1);
         if (var6.isEmpty()) {
            return var0;
         }

         if (var3 != var0) {
            mergeRecoveredTraces(var5, var6);
         }

         var7 = createFinalException(var3, var4, var6);
      }

      return var7;
   }

   public static final Throwable recoverStackTrace(Throwable var0) {
      if (!DebugKt.getRECOVER_STACK_TRACES()) {
         return var0;
      } else {
         Throwable var1 = ExceptionsConstuctorKt.tryCopyException(var0);
         if (var1 != null) {
            var0 = sanitizeStackTrace(var1);
         }

         return var0;
      }
   }

   public static final Throwable recoverStackTrace(Throwable var0, Continuation var1) {
      Throwable var2 = var0;
      if (DebugKt.getRECOVER_STACK_TRACES()) {
         if (!(var1 instanceof CoroutineStackFrame)) {
            var2 = var0;
         } else {
            var2 = access$recoverFromStackFrame(var0, (CoroutineStackFrame)var1);
         }
      }

      return var2;
   }

   private static final Throwable sanitizeStackTrace(Throwable var0) {
      StackTraceElement[] var7 = var0.getStackTrace();
      int var4 = var7.length;
      int var3 = frameIndex(var7, stackTraceRecoveryClassName);
      int var1 = frameIndex(var7, baseContinuationImplClassName);
      byte var2 = 0;
      if (var1 == -1) {
         var1 = 0;
      } else {
         var1 = var4 - var1;
      }

      var4 = var4 - var3 - var1;
      StackTraceElement[] var6 = new StackTraceElement[var4];

      for(var1 = var2; var1 < var4; ++var1) {
         StackTraceElement var5;
         if (var1 == 0) {
            var5 = artificialFrame("Coroutine boundary");
         } else {
            var5 = var7[var3 + 1 + var1 - 1];
         }

         var6[var1] = var5;
      }

      var0.setStackTrace(var6);
      return var0;
   }

   public static final Throwable unwrap(Throwable var0) {
      if (DebugKt.getRECOVER_STACK_TRACES()) {
         var0 = unwrapImpl(var0);
      }

      return var0;
   }

   public static final Throwable unwrapImpl(Throwable var0) {
      Throwable var6 = var0.getCause();
      if (var6 != null) {
         boolean var4 = Intrinsics.areEqual((Object)var6.getClass(), (Object)var0.getClass());
         boolean var2 = true;
         if (!(var4 ^ true)) {
            StackTraceElement[] var5 = var0.getStackTrace();
            int var3 = var5.length;
            int var1 = 0;

            boolean var7;
            while(true) {
               if (var1 >= var3) {
                  var7 = false;
                  break;
               }

               if (isArtificial(var5[var1])) {
                  var7 = var2;
                  break;
               }

               ++var1;
            }

            if (var7) {
               return var6;
            }
         }
      }

      return var0;
   }
}
