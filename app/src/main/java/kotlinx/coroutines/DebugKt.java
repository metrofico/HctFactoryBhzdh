package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0081\b\u001a\b\u0010\u0016\u001a\u00020\u0013H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000\"\u0014\u0010\u000f\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0003\"\u000e\u0010\u0011\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0017"},
   d2 = {"ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG", "getDEBUG", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES", "STACKTRACE_RECOVERY_PROPERTY_NAME", "assert", "", "value", "Lkotlin/Function0;", "resetCoroutineId", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class DebugKt {
   private static final boolean ASSERTIONS_ENABLED;
   private static final AtomicLong COROUTINE_ID;
   private static final boolean DEBUG;
   public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
   public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
   public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
   public static final String DEBUG_PROPERTY_VALUE_ON = "on";
   private static final boolean RECOVER_STACK_TRACES;
   public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

   static {
      String var4;
      label45: {
         boolean var1;
         boolean var3;
         label48: {
            var3 = false;
            ASSERTIONS_ENABLED = false;
            var4 = SystemPropsKt.systemProp("kotlinx.coroutines.debug");
            if (var4 != null) {
               label47: {
                  int var0 = var4.hashCode();
                  if (var0 != 0) {
                     if (var0 != 3551) {
                        if (var0 != 109935) {
                           if (var0 != 3005871 || !var4.equals("auto")) {
                              break label45;
                           }
                        } else if (!var4.equals("off")) {
                           break label45;
                        }
                        break label47;
                     }

                     if (!var4.equals("on")) {
                        break label45;
                     }
                  } else if (!var4.equals("")) {
                     break label45;
                  }

                  var1 = true;
                  break label48;
               }
            }

            var1 = false;
         }

         DEBUG = var1;
         boolean var2 = var3;
         if (var1) {
            var2 = var3;
            if (SystemPropsKt.systemProp("kotlinx.coroutines.stacktrace.recovery", true)) {
               var2 = true;
            }
         }

         RECOVER_STACK_TRACES = var2;
         COROUTINE_ID = new AtomicLong(0L);
         return;
      }

      throw (Throwable)(new IllegalStateException(("System property 'kotlinx.coroutines.debug' has unrecognized value '" + var4 + '\'').toString()));
   }

   private static final void assert(Function0 var0) {
      if (getASSERTIONS_ENABLED() && !(Boolean)var0.invoke()) {
         throw (Throwable)(new AssertionError());
      }
   }

   public static final boolean getASSERTIONS_ENABLED() {
      return ASSERTIONS_ENABLED;
   }

   public static final AtomicLong getCOROUTINE_ID() {
      return COROUTINE_ID;
   }

   public static final boolean getDEBUG() {
      return DEBUG;
   }

   public static final boolean getRECOVER_STACK_TRACES() {
      return RECOVER_STACK_TRACES;
   }

   public static final void resetCoroutineId() {
      COROUTINE_ID.set(0L);
   }
}
