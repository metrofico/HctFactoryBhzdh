package kotlinx.coroutines.debug;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0011"},
   d2 = {"Lkotlinx/coroutines/debug/AgentPremain;", "", "()V", "enableCreationStackTraces", "", "isInstalledStatically", "()Z", "setInstalledStatically", "(Z)V", "installSignalHandler", "", "premain", "args", "", "instrumentation", "Ljava/lang/instrument/Instrumentation;", "DebugProbesTransformer", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class AgentPremain {
   public static final AgentPremain INSTANCE = new AgentPremain();
   private static final boolean enableCreationStackTraces;
   private static boolean isInstalledStatically;

   static {
      Object var2 = null;

      Boolean var17;
      Object var19;
      label161: {
         Throwable var10000;
         label165: {
            String var16;
            boolean var10001;
            try {
               Result.Companion var1 = Result.Companion;
               var16 = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label165;
            }

            if (var16 != null) {
               try {
                  var17 = Boolean.parseBoolean(var16);
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label165;
               }
            } else {
               var17 = null;
            }

            label152:
            try {
               var19 = Result.constructor_impl(var17);
               break label161;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label152;
            }
         }

         Throwable var18 = var10000;
         Result.Companion var3 = Result.Companion;
         var19 = Result.constructor_impl(ResultKt.createFailure(var18));
      }

      if (Result.isFailure_impl(var19)) {
         var19 = var2;
      }

      var17 = (Boolean)var19;
      boolean var0;
      if (var17 != null) {
         var0 = var17;
      } else {
         var0 = DebugProbesImpl.INSTANCE.getEnableCreationStackTraces();
      }

      enableCreationStackTraces = var0;
   }

   private AgentPremain() {
   }

   private final void installSignalHandler() {
      try {
         Signal var1 = new Signal("TRAP");
         Signal.handle(var1, (SignalHandler)null.INSTANCE);
      } finally {
         return;
      }

   }

   @JvmStatic
   public static final void premain(String var0, Instrumentation var1) {
      isInstalledStatically = true;
      var1.addTransformer((ClassFileTransformer) DebugProbesTransformer.INSTANCE);
      DebugProbesImpl.INSTANCE.setEnableCreationStackTraces(enableCreationStackTraces);
      DebugProbesImpl.INSTANCE.install();
      INSTANCE.installSignalHandler();
   }

   public final boolean isInstalledStatically() {
      return isInstalledStatically;
   }

   public final void setInstalledStatically(boolean var1) {
      isInstalledStatically = var1;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u000e"},
      d2 = {"Lkotlinx/coroutines/debug/AgentPremain$DebugProbesTransformer;", "Ljava/lang/instrument/ClassFileTransformer;", "()V", "transform", "", "loader", "Ljava/lang/ClassLoader;", "className", "", "classBeingRedefined", "Ljava/lang/Class;", "protectionDomain", "Ljava/security/ProtectionDomain;", "classfileBuffer", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class DebugProbesTransformer implements ClassFileTransformer {
      public static final DebugProbesTransformer INSTANCE = new DebugProbesTransformer();

      private DebugProbesTransformer() {
      }

      public byte[] transform(ClassLoader var1, String var2, Class var3, ProtectionDomain var4, byte[] var5) {
         if (Intrinsics.areEqual((Object)var2, (Object)"kotlin/coroutines/jvm/internal/DebugProbesKt") ^ true) {
            return null;
         } else {
            AgentPremain.INSTANCE.setInstalledStatically(true);
            return ByteStreamsKt.readBytes(var1.getResourceAsStream("DebugProbesKt.bin"));
         }
      }
   }
}
