package kotlinx.coroutines.debug.internal;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.AgentPremain;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\bÀ\u0002\u0018\u00002\u00020\u0013:\u0002\u008f\u0001B\t\b\u0002¢\u0006\u0004\b\u0001\u0010\u0002J3\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012J>\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\"\b\b\u0000\u0010\u0014*\u00020\u00132\u001c\u0010\u0018\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00028\u00000\u0015H\u0082\b¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001b\u0010\u000eJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000f¢\u0006\u0004\b\u001d\u0010\u0012J)\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\u001e\u001a\u00020\u00102\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f¢\u0006\u0004\b!\u0010\"J5\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010$\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010%2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b'\u0010(J?\u0010/\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020.0-2\u0006\u0010*\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0+2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b/\u00100J3\u00102\u001a\u00020)2\u0006\u00101\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0+2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b2\u00103J\u001d\u00105\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\f\u0018\u000104H\u0002¢\u0006\u0004\b5\u00106J\u0015\u00109\u001a\u00020#2\u0006\u00108\u001a\u000207¢\u0006\u0004\b9\u0010:J\r\u0010;\u001a\u00020\f¢\u0006\u0004\b;\u0010\u0002J%\u0010=\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b=\u0010>J\u001b\u0010@\u001a\u00020\f2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0002¢\u0006\u0004\b@\u0010AJ)\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0000¢\u0006\u0004\bB\u0010CJ\u001b\u0010G\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\bE\u0010FJ\u001b\u0010I\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\bH\u0010FJ'\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f\"\b\b\u0000\u0010\u0003*\u00020J2\u0006\u0010K\u001a\u00028\u0000H\u0002¢\u0006\u0004\bL\u0010MJ\u000f\u0010N\u001a\u00020\fH\u0002¢\u0006\u0004\bN\u0010\u0002J\u000f\u0010O\u001a\u00020\fH\u0002¢\u0006\u0004\bO\u0010\u0002J\r\u0010P\u001a\u00020\f¢\u0006\u0004\bP\u0010\u0002J\u001f\u0010R\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020Q2\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bR\u0010SJ#\u0010T\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bT\u0010UJ/\u0010T\u001a\u00020\f2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030\u00162\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bT\u0010VJ;\u0010^\u001a\u00020\f*\u0002072\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020X0W2\n\u0010\\\u001a\u00060Zj\u0002`[2\u0006\u0010]\u001a\u00020#H\u0002¢\u0006\u0004\b^\u0010_J\u0017\u0010`\u001a\u00020.*\u0006\u0012\u0002\b\u00030\u0016H\u0002¢\u0006\u0004\b`\u0010aJ\u001d\u0010?\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u0006\u0012\u0002\b\u00030\u0004H\u0002¢\u0006\u0004\b?\u0010bJ\u001a\u0010?\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u00020QH\u0082\u0010¢\u0006\u0004\b?\u0010cJ\u0016\u0010d\u001a\u0004\u0018\u00010Q*\u00020QH\u0082\u0010¢\u0006\u0004\bd\u0010eJ\u001b\u0010f\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\bf\u0010gR\u0016\u0010h\u001a\u00020#8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\bh\u0010iR\"\u0010k\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020X0j8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010lR \u0010p\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160m8B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR&\u0010q\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\u0004\u0012\u00020.0j8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bq\u0010lR\u0016\u0010s\u001a\u00020r8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bs\u0010tR\u0016\u0010v\u001a\u00020u8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010wR$\u0010x\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\f\u0018\u0001048\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010yR\"\u0010z\u001a\u00020.8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bz\u0010{\u001a\u0004\b|\u0010}\"\u0004\b~\u0010\u007fR\u0019\u0010\u0080\u0001\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0018\u0010\u0083\u0001\u001a\u00020.8@@\u0000X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010}R&\u0010\u0084\u0001\u001a\u00020.8\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010{\u001a\u0005\b\u0085\u0001\u0010}\"\u0005\b\u0086\u0001\u0010\u007fR\u001b\u0010\u0087\u0001\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R$\u0010\u008c\u0001\u001a\u00020#*\u0002078B@\u0002X\u0082\u0004¢\u0006\u000f\u0012\u0006\b\u008a\u0001\u0010\u008b\u0001\u001a\u0005\b\u0089\u0001\u0010:R\u001d\u0010\u008d\u0001\u001a\u00020.*\u00020\u001f8B@\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001¨\u0006\u0090\u0001"},
   d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl;", "<init>", "()V", "T", "Lkotlin/coroutines/Continuation;", "completion", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "frame", "createOwner", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/StackTraceFrame;)Lkotlin/coroutines/Continuation;", "Ljava/io/PrintStream;", "out", "", "dumpCoroutines", "(Ljava/io/PrintStream;)V", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "dumpCoroutinesInfo", "()Ljava/util/List;", "", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "Lkotlin/coroutines/CoroutineContext;", "create", "dumpCoroutinesInfoImpl", "(Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "dumpCoroutinesSynchronized", "Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "dumpDebuggerInfo", "info", "Ljava/lang/StackTraceElement;", "coroutineTrace", "enhanceStackTraceWithThreadDump", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;Ljava/util/List;)Ljava/util/List;", "", "state", "Ljava/lang/Thread;", "thread", "enhanceStackTraceWithThreadDumpImpl", "(Ljava/lang/String;Ljava/lang/Thread;Ljava/util/List;)Ljava/util/List;", "", "indexOfResumeWith", "", "actualTrace", "Lkotlin/Pair;", "", "findContinuationStartIndex", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)Lkotlin/Pair;", "frameIndex", "findIndexOfFrame", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)I", "Lkotlin/Function1;", "getDynamicAttach", "()Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/Job;", "job", "hierarchyToString", "(Lkotlinx/coroutines/Job;)Ljava/lang/String;", "install", "frames", "printStackTrace", "(Ljava/io/PrintStream;Ljava/util/List;)V", "owner", "probeCoroutineCompleted", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)V", "probeCoroutineCreated$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "probeCoroutineCreated", "probeCoroutineResumed$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)V", "probeCoroutineResumed", "probeCoroutineSuspended$kotlinx_coroutines_core", "probeCoroutineSuspended", "", "throwable", "sanitizeStackTrace", "(Ljava/lang/Throwable;)Ljava/util/List;", "startWeakRefCleanerThread", "stopWeakRefCleanerThread", "uninstall", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateRunningState", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Ljava/lang/String;)V", "updateState", "(Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "map", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "indent", "build", "(Lkotlinx/coroutines/Job;Ljava/util/Map;Ljava/lang/StringBuilder;Ljava/lang/String;)V", "isFinished", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)Z", "(Lkotlin/coroutines/Continuation;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "realCaller", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "toStackTraceFrame", "(Ljava/util/List;)Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "ARTIFICIAL_FRAME_MESSAGE", "Ljava/lang/String;", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "callerInfoCache", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "", "getCapturedCoroutines", "()Ljava/util/Set;", "capturedCoroutines", "capturedCoroutinesMap", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "coroutineStateLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Ljava/text/SimpleDateFormat;", "dateFormat", "Ljava/text/SimpleDateFormat;", "dynamicAttach", "Lkotlin/jvm/functions/Function1;", "enableCreationStackTraces", "Z", "getEnableCreationStackTraces", "()Z", "setEnableCreationStackTraces", "(Z)V", "installations", "I", "isInstalled$kotlinx_coroutines_core", "isInstalled", "sanitizeStackTraces", "getSanitizeStackTraces", "setSanitizeStackTraces", "weakRefCleanerThread", "Ljava/lang/Thread;", "getDebugString", "getDebugString$annotations", "(Lkotlinx/coroutines/Job;)V", "debugString", "isInternalMethod", "(Ljava/lang/StackTraceElement;)Z", "CoroutineOwner", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DebugProbesImpl {
   private static final String ARTIFICIAL_FRAME_MESSAGE = "Coroutine creation stacktrace";
   public static final DebugProbesImpl INSTANCE;
   private static final ConcurrentWeakMap callerInfoCache;
   private static final ConcurrentWeakMap capturedCoroutinesMap;
   private static final ReentrantReadWriteLock coroutineStateLock;
   private static final SimpleDateFormat dateFormat;
   static final DebugProbesImplSequenceNumberRefVolatile debugProbesImplSequenceNumberRefVolatile;
   private static final Function1 dynamicAttach;
   private static boolean enableCreationStackTraces;
   private static volatile int installations;
   private static boolean sanitizeStackTraces;
   static final AtomicLongFieldUpdater sequenceNumber$FU;
   private static Thread weakRefCleanerThread;

   static {
      DebugProbesImpl var0 = new DebugProbesImpl();
      INSTANCE = var0;
      dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      capturedCoroutinesMap = new ConcurrentWeakMap(false, 1, (DefaultConstructorMarker)null);
      debugProbesImplSequenceNumberRefVolatile = new DebugProbesImplSequenceNumberRefVolatile(0L);
      coroutineStateLock = new ReentrantReadWriteLock();
      sanitizeStackTraces = true;
      enableCreationStackTraces = true;
      dynamicAttach = var0.getDynamicAttach();
      callerInfoCache = new ConcurrentWeakMap(true);
      sequenceNumber$FU = AtomicLongFieldUpdater.newUpdater(DebugProbesImplSequenceNumberRefVolatile.class, "sequenceNumber");
   }

   private DebugProbesImpl() {
   }

   // $FF: synthetic method
   public static final ConcurrentWeakMap access$getCallerInfoCache$p(DebugProbesImpl var0) {
      return callerInfoCache;
   }

   // $FF: synthetic method
   public static final Set access$getCapturedCoroutines$p(DebugProbesImpl var0) {
      return var0.getCapturedCoroutines();
   }

   // $FF: synthetic method
   public static final ReentrantReadWriteLock access$getCoroutineStateLock$p(DebugProbesImpl var0) {
      return coroutineStateLock;
   }

   private final void build(Job var1, Map var2, StringBuilder var3, String var4) {
      DebugCoroutineInfoImpl var6 = (DebugCoroutineInfoImpl)var2.get(var1);
      String var5;
      if (var6 == null) {
         var5 = var4;
         if (!(var1 instanceof ScopeCoroutine)) {
            var3.append(var4 + this.getDebugString(var1) + '\n');
            var5 = var4 + "\t";
         }
      } else {
         StackTraceElement var8 = (StackTraceElement)CollectionsKt.firstOrNull(var6.lastObservedStackTrace());
         String var9 = var6.getState();
         var3.append(var4 + this.getDebugString(var1) + ", continuation is " + var9 + " at line " + var8 + '\n');
         var5 = var4 + "\t";
      }

      Iterator var7 = var1.getChildren().iterator();

      while(var7.hasNext()) {
         this.build((Job)var7.next(), var2, var3, var5);
      }

   }

   private final Continuation createOwner(Continuation var1, StackTraceFrame var2) {
      if (!this.isInstalled$kotlinx_coroutines_core()) {
         return var1;
      } else {
         CoroutineOwner var3 = new CoroutineOwner(var1, new DebugCoroutineInfoImpl(var1.getContext(), var2, sequenceNumber$FU.incrementAndGet(debugProbesImplSequenceNumberRefVolatile)), (CoroutineStackFrame)var2);
         ConcurrentWeakMap var4 = capturedCoroutinesMap;
         ((Map)var4).put(var3, true);
         if (!this.isInstalled$kotlinx_coroutines_core()) {
            var4.clear();
         }

         return (Continuation)var3;
      }
   }

   private final List dumpCoroutinesInfoImpl(Function2 var1) {
      ReentrantReadWriteLock var7 = access$getCoroutineStateLock$p(this);
      ReentrantReadWriteLock.ReadLock var8 = var7.readLock();
      int var2 = var7.getWriteHoldCount();
      byte var5 = 0;
      byte var4 = 0;
      if (var2 == 0) {
         var2 = var7.getReadHoldCount();
      } else {
         var2 = 0;
      }

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         var8.unlock();
      }

      ReentrantReadWriteLock.WriteLock var9 = var7.writeLock();
      var9.lock();

      Throwable var10000;
      label751: {
         boolean var10001;
         label750: {
            Iterator var11;
            Collection var77;
            try {
               DebugProbesImpl var73 = INSTANCE;
               if (!var73.isInstalled$kotlinx_coroutines_core()) {
                  break label750;
               }

               Iterable var74 = (Iterable)access$getCapturedCoroutines$p(var73);
               Comparator var10 = new Comparator() {
                  public final int compare(Object var1, Object var2) {
                     return ComparisonsKt.compareValues((Comparable)((CoroutineOwner)var1).info.sequenceNumber, (Comparable)((CoroutineOwner)var2).info.sequenceNumber);
                  }
               };
               var74 = (Iterable)CollectionsKt.sortedWith(var74, (Comparator)var10);
               ArrayList var76 = new ArrayList();
               var77 = (Collection)var76;
               var11 = var74.iterator();
            } catch (Throwable var69) {
               var10000 = var69;
               var10001 = false;
               break label751;
            }

            while(true) {
               boolean var6;
               CoroutineOwner var12;
               label762: {
                  try {
                     if (var11.hasNext()) {
                        var12 = (CoroutineOwner)var11.next();
                        var6 = access$isFinished(INSTANCE, var12);
                        break label762;
                     }
                  } catch (Throwable var68) {
                     var10000 = var68;
                     var10001 = false;
                     break label751;
                  }

                  List var70;
                  try {
                     var70 = (List)var77;
                  } catch (Throwable var63) {
                     var10000 = var63;
                     var10001 = false;
                     break label751;
                  }

                  InlineMarker.finallyStart(1);

                  for(var3 = var4; var3 < var2; ++var3) {
                     var8.lock();
                  }

                  var9.unlock();
                  InlineMarker.finallyEnd(1);
                  return var70;
               }

               Object var75 = null;
               if (!var6) {
                  CoroutineContext var13;
                  try {
                     var13 = var12.info.getContext();
                  } catch (Throwable var67) {
                     var10000 = var67;
                     var10001 = false;
                     break label751;
                  }

                  if (var13 != null) {
                     try {
                        var75 = var1.invoke(var12, var13);
                     } catch (Throwable var66) {
                        var10000 = var66;
                        var10001 = false;
                        break label751;
                     }
                  }
               }

               if (var75 != null) {
                  try {
                     var77.add(var75);
                  } catch (Throwable var65) {
                     var10000 = var65;
                     var10001 = false;
                     break label751;
                  }
               }
            }
         }

         label726:
         try {
            IllegalStateException var72 = new IllegalStateException("Debug probes are not installed".toString());
            throw (Throwable)var72;
         } catch (Throwable var64) {
            var10000 = var64;
            var10001 = false;
            break label726;
         }
      }

      Throwable var71 = var10000;
      InlineMarker.finallyStart(1);

      for(var3 = var5; var3 < var2; ++var3) {
         var8.lock();
      }

      var9.unlock();
      InlineMarker.finallyEnd(1);
      throw var71;
   }

   private final void dumpCoroutinesSynchronized(PrintStream var1) {
      ReentrantReadWriteLock var6 = coroutineStateLock;
      ReentrantReadWriteLock.ReadLock var7 = var6.readLock();
      int var2 = var6.getWriteHoldCount();
      byte var5 = 0;
      byte var4 = 0;
      if (var2 == 0) {
         var2 = var6.getReadHoldCount();
      } else {
         var2 = 0;
      }

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         var7.unlock();
      }

      ReentrantReadWriteLock.WriteLock var8 = var6.writeLock();
      var8.lock();

      Throwable var10000;
      label962: {
         boolean var10001;
         label972: {
            Iterator var10;
            try {
               DebugProbesImpl var91 = INSTANCE;
               if (!var91.isInstalled$kotlinx_coroutines_core()) {
                  break label972;
               }

               StringBuilder var9 = new StringBuilder();
               var1.print(var9.append("Coroutines dump ").append(dateFormat.format(System.currentTimeMillis())).toString());
               Sequence var95 = SequencesKt.filter(CollectionsKt.asSequence((Iterable)var91.getCapturedCoroutines()), (Function1)null.INSTANCE);
               Comparator var92 = new Comparator() {
                  public final int compare(Object var1, Object var2) {
                     return ComparisonsKt.compareValues((Comparable)((CoroutineOwner)var1).info.sequenceNumber, (Comparable)((CoroutineOwner)var2).info.sequenceNumber);
                  }
               };
               var10 = SequencesKt.sortedWith(var95, (Comparator)var92).iterator();
            } catch (Throwable var87) {
               var10000 = var87;
               var10001 = false;
               break label962;
            }

            while(true) {
               DebugCoroutineInfoImpl var11;
               List var12;
               CoroutineOwner var13;
               List var14;
               StringBuilder var93;
               String var94;
               DebugProbesImpl var96;
               label953: {
                  label952: {
                     try {
                        if (!var10.hasNext()) {
                           break;
                        }

                        var13 = (CoroutineOwner)var10.next();
                        var11 = var13.info;
                        var14 = var11.lastObservedStackTrace();
                        var96 = INSTANCE;
                        var12 = var96.enhanceStackTraceWithThreadDumpImpl(var11.getState(), var11.lastObservedThread, var14);
                        if (!Intrinsics.areEqual((Object)var11.getState(), (Object)"RUNNING")) {
                           break label952;
                        }
                     } catch (Throwable var86) {
                        var10000 = var86;
                        var10001 = false;
                        break label962;
                     }

                     if (var12 == var14) {
                        try {
                           var93 = new StringBuilder();
                           var94 = var93.append(var11.getState()).append(" (Last suspension stacktrace, not an actual stacktrace)").toString();
                           break label953;
                        } catch (Throwable var84) {
                           var10000 = var84;
                           var10001 = false;
                           break label962;
                        }
                     }
                  }

                  try {
                     var94 = var11.getState();
                  } catch (Throwable var83) {
                     var10000 = var83;
                     var10001 = false;
                     break label962;
                  }
               }

               try {
                  StringBuilder var15 = new StringBuilder();
                  var1.print(var15.append("\n\nCoroutine ").append(var13.delegate).append(", state: ").append(var94).toString());
                  if (var14.isEmpty()) {
                     var93 = new StringBuilder();
                     var1.print(var93.append("\n\tat ").append(StackTraceRecoveryKt.artificialFrame("Coroutine creation stacktrace")).toString());
                     var96.printStackTrace(var1, var11.getCreationStackTrace());
                     continue;
                  }
               } catch (Throwable var85) {
                  var10000 = var85;
                  var10001 = false;
                  break label962;
               }

               try {
                  var96.printStackTrace(var1, var12);
               } catch (Throwable var82) {
                  var10000 = var82;
                  var10001 = false;
                  break label962;
               }
            }

            try {
               Unit var88 = Unit.INSTANCE;
            } catch (Throwable var80) {
               var10000 = var80;
               var10001 = false;
               break label962;
            }

            for(var3 = var4; var3 < var2; ++var3) {
               var7.lock();
            }

            var8.unlock();
            return;
         }

         label928:
         try {
            IllegalStateException var90 = new IllegalStateException("Debug probes are not installed".toString());
            throw (Throwable)var90;
         } catch (Throwable var81) {
            var10000 = var81;
            var10001 = false;
            break label928;
         }
      }

      Throwable var89 = var10000;

      for(var3 = var5; var3 < var2; ++var3) {
         var7.lock();
      }

      var8.unlock();
      throw var89;
   }

   private final List enhanceStackTraceWithThreadDumpImpl(String var1, Thread var2, List var3) {
      if (!(Intrinsics.areEqual((Object)var1, (Object)"RUNNING") ^ true) && var2 != null) {
         Result.Companion var11;
         Object var12;
         label138:
         try {
            var11 = Result.Companion;
            DebugProbesImpl var13 = (DebugProbesImpl)this;
            var12 = Result.constructor_impl(var2.getStackTrace());
         } catch (Throwable var10) {
            var11 = Result.Companion;
            var12 = Result.constructor_impl(ResultKt.createFailure(var10));
            break label138;
         }

         Object var14 = var12;
         if (Result.isFailure_impl(var12)) {
            var14 = null;
         }

         StackTraceElement[] var15 = (StackTraceElement[])var14;
         if (var15 != null) {
            int var7 = var15.length;
            byte var6 = 0;
            int var4 = 0;

            while(true) {
               if (var4 >= var7) {
                  var4 = -1;
                  break;
               }

               StackTraceElement var16 = var15[var4];
               boolean var5;
               if (Intrinsics.areEqual((Object)var16.getClassName(), (Object)"kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual((Object)var16.getMethodName(), (Object)"resumeWith") && Intrinsics.areEqual((Object)var16.getFileName(), (Object)"ContinuationImpl.kt")) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               if (var5) {
                  break;
               }

               ++var4;
            }

            Pair var17 = this.findContinuationStartIndex(var4, var15, var3);
            int var8 = ((Number)var17.component1()).intValue();
            byte var20 = (Boolean)var17.component2();
            if (var8 == -1) {
               return var3;
            }

            ArrayList var18 = new ArrayList(var3.size() + var4 - var8 - 1 - var20);

            int var19;
            for(var19 = var6; var19 < var4 - var20; ++var19) {
               ((Collection)var18).add(var15[var19]);
            }

            var4 = var8 + 1;

            for(var19 = var3.size(); var4 < var19; ++var4) {
               ((Collection)var18).add(var3.get(var4));
            }

            return (List)var18;
         }
      }

      return var3;
   }

   private final Pair findContinuationStartIndex(int var1, StackTraceElement[] var2, List var3) {
      int var4 = this.findIndexOfFrame(var1 - 1, var2, var3);
      return var4 == -1 ? TuplesKt.to(this.findIndexOfFrame(var1 - 2, var2, var3), true) : TuplesKt.to(var4, false);
   }

   private final int findIndexOfFrame(int var1, StackTraceElement[] var2, List var3) {
      StackTraceElement var7 = (StackTraceElement)ArraysKt.getOrNull(var2, var1);
      byte var5 = -1;
      int var4 = var5;
      if (var7 != null) {
         Iterator var6 = var3.iterator();
         var1 = 0;

         while(true) {
            var4 = var5;
            if (!var6.hasNext()) {
               break;
            }

            StackTraceElement var8 = (StackTraceElement)var6.next();
            boolean var9;
            if (Intrinsics.areEqual((Object)var8.getFileName(), (Object)var7.getFileName()) && Intrinsics.areEqual((Object)var8.getClassName(), (Object)var7.getClassName()) && Intrinsics.areEqual((Object)var8.getMethodName(), (Object)var7.getMethodName())) {
               var9 = true;
            } else {
               var9 = false;
            }

            if (var9) {
               var4 = var1;
               break;
            }

            ++var1;
         }
      }

      return var4;
   }

   private final Set getCapturedCoroutines() {
      return capturedCoroutinesMap.keySet();
   }

   private final String getDebugString(Job var1) {
      String var2;
      if (var1 instanceof JobSupport) {
         var2 = ((JobSupport)var1).toDebugString();
      } else {
         var2 = var1.toString();
      }

      return var2;
   }

   // $FF: synthetic method
   private static void getDebugString$annotations(Job var0) {
   }

   private final Function1 getDynamicAttach() {
      Object var16;
      label115: {
         Throwable var10000;
         Result.Companion var1;
         label114: {
            boolean var10001;
            try {
               var1 = Result.Companion;
               DebugProbesImpl var15 = (DebugProbesImpl)this;
               var16 = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance();
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label114;
            }

            if (var16 != null) {
               label110:
               try {
                  var16 = Result.constructor_impl((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var16, 1));
                  break label115;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label110;
               }
            } else {
               label107:
               try {
                  NullPointerException var18 = new NullPointerException("null cannot be cast to non-null type (kotlin.Boolean) -> kotlin.Unit");
                  throw var18;
               } catch (Throwable var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label107;
               }
            }
         }

         Throwable var2 = var10000;
         var1 = Result.Companion;
         var16 = Result.constructor_impl(ResultKt.createFailure(var2));
      }

      Object var17 = var16;
      if (Result.isFailure_impl(var16)) {
         var17 = null;
      }

      return (Function1)var17;
   }

   private final boolean isFinished(CoroutineOwner var1) {
      CoroutineContext var2 = var1.info.getContext();
      if (var2 != null) {
         Job var3 = (Job)var2.get((CoroutineContext.Key)Job.Key);
         if (var3 != null) {
            if (!var3.isCompleted()) {
               return false;
            }

            capturedCoroutinesMap.remove(var1);
            return true;
         }
      }

      return false;
   }

   private final boolean isInternalMethod(StackTraceElement var1) {
      return StringsKt.startsWith$default(var1.getClassName(), "kotlinx.coroutines", false, 2, (Object)null);
   }

   private final CoroutineOwner owner(Continuation var1) {
      boolean var2 = var1 instanceof CoroutineStackFrame;
      Object var3 = null;
      if (!var2) {
         var1 = null;
      }

      CoroutineStackFrame var4 = (CoroutineStackFrame)var1;
      CoroutineOwner var5 = (CoroutineOwner)var3;
      if (var4 != null) {
         var5 = this.owner(var4);
      }

      return var5;
   }

   private final CoroutineOwner owner(CoroutineStackFrame var1) {
      while(true) {
         CoroutineOwner var2;
         if (var1 instanceof CoroutineOwner) {
            var2 = (CoroutineOwner)var1;
         } else {
            var1 = var1.getCallerFrame();
            if (var1 != null) {
               continue;
            }

            var2 = null;
         }

         return var2;
      }
   }

   private final void printStackTrace(PrintStream var1, List var2) {
      Iterator var4 = ((Iterable)var2).iterator();

      while(var4.hasNext()) {
         StackTraceElement var3 = (StackTraceElement)var4.next();
         var1.print("\n\tat " + var3);
      }

   }

   private final void probeCoroutineCompleted(CoroutineOwner var1) {
      capturedCoroutinesMap.remove(var1);
      CoroutineStackFrame var2 = var1.info.getLastObservedFrame$kotlinx_coroutines_core();
      if (var2 != null) {
         var2 = this.realCaller(var2);
         if (var2 != null) {
            callerInfoCache.remove(var2);
         }
      }

   }

   private final CoroutineStackFrame realCaller(CoroutineStackFrame var1) {
      while(true) {
         CoroutineStackFrame var2 = var1.getCallerFrame();
         if (var2 != null) {
            var1 = var2;
            if (var2.getStackTraceElement() == null) {
               continue;
            }

            return var2;
         }

         return null;
      }
   }

   private final List sanitizeStackTrace(Throwable var1) {
      StackTraceElement[] var7 = var1.getStackTrace();
      int var5 = var7.length;
      int var2 = var7.length;
      int var4 = -1;
      --var2;

      int var3;
      while(true) {
         var3 = var4;
         if (var2 < 0) {
            break;
         }

         if (Intrinsics.areEqual((Object)var7[var2].getClassName(), (Object)"kotlin.coroutines.jvm.internal.DebugProbesKt")) {
            var3 = var2;
            break;
         }

         --var2;
      }

      boolean var6 = sanitizeStackTraces;
      var2 = 0;
      StackTraceElement var10;
      if (!var6) {
         var4 = var5 - var3;

         ArrayList var12;
         for(var12 = new ArrayList(var4); var2 < var4; ++var2) {
            if (var2 == 0) {
               var10 = StackTraceRecoveryKt.artificialFrame("Coroutine creation stacktrace");
            } else {
               var10 = var7[var2 + var3];
            }

            var12.add(var10);
         }

         return (List)var12;
      } else {
         ArrayList var9 = new ArrayList(var5 - var3 + 1);
         Collection var8 = (Collection)var9;
         var8.add(StackTraceRecoveryKt.artificialFrame("Coroutine creation stacktrace"));
         ++var3;
         var4 = var5 - 1;

         for(boolean var11 = true; var3 < var4; ++var3) {
            var10 = var7[var3];
            if (!this.isInternalMethod(var10)) {
               var8.add(var10);
            } else {
               if (var11) {
                  var8.add(var10);
                  var11 = false;
                  continue;
               }

               if (this.isInternalMethod(var7[var3 + 1])) {
                  continue;
               }

               var8.add(var10);
            }

            var11 = true;
         }

         var8.add(var7[var4]);
         return (List)var9;
      }
   }

   private final void startWeakRefCleanerThread() {
      weakRefCleanerThread = ThreadsKt.thread$default(false, true, (ClassLoader)null, "Coroutines Debugger Cleaner", 0, (Function0)null.INSTANCE, 21, (Object)null);
   }

   private final void stopWeakRefCleanerThread() {
      Thread var1 = weakRefCleanerThread;
      if (var1 != null) {
         var1.interrupt();
      }

      var1 = (Thread)null;
      weakRefCleanerThread = null;
   }

   private final StackTraceFrame toStackTraceFrame(List var1) {
      boolean var2 = var1.isEmpty();
      StackTraceFrame var3 = null;
      Object var4 = null;
      if (!var2) {
         ListIterator var5 = var1.listIterator(var1.size());
         StackTraceFrame var6 = (StackTraceFrame)var4;

         while(true) {
            var3 = var6;
            if (!var5.hasPrevious()) {
               break;
            }

            StackTraceElement var7 = (StackTraceElement)var5.previous();
            var6 = new StackTraceFrame((CoroutineStackFrame)var6, var7);
         }
      }

      return var3;
   }

   private final void updateRunningState(CoroutineStackFrame var1, String var2) {
      ReentrantReadWriteLock.ReadLock var7 = coroutineStateLock.readLock();
      var7.lock();

      Throwable var10000;
      label972: {
         boolean var3;
         DebugProbesImpl var9;
         boolean var10001;
         try {
            var9 = INSTANCE;
            var3 = var9.isInstalled$kotlinx_coroutines_core();
         } catch (Throwable var119) {
            var10000 = var119;
            var10001 = false;
            break label972;
         }

         if (!var3) {
            var7.unlock();
            return;
         }

         DebugCoroutineInfoImpl var4;
         ConcurrentWeakMap var8;
         try {
            var8 = callerInfoCache;
            var4 = (DebugCoroutineInfoImpl)var8.remove(var1);
         } catch (Throwable var118) {
            var10000 = var118;
            var10001 = false;
            break label972;
         }

         if (var4 == null) {
            label971: {
               CoroutineOwner var123;
               try {
                  var123 = var9.owner(var1);
               } catch (Throwable var117) {
                  var10000 = var117;
                  var10001 = false;
                  break label972;
               }

               if (var123 != null) {
                  DebugCoroutineInfoImpl var6;
                  try {
                     var6 = var123.info;
                  } catch (Throwable var116) {
                     var10000 = var116;
                     var10001 = false;
                     break label972;
                  }

                  if (var6 != null) {
                     CoroutineStackFrame var124;
                     try {
                        var124 = var6.getLastObservedFrame$kotlinx_coroutines_core();
                     } catch (Throwable var115) {
                        var10000 = var115;
                        var10001 = false;
                        break label972;
                     }

                     CoroutineStackFrame var5;
                     if (var124 != null) {
                        try {
                           var5 = var9.realCaller(var124);
                        } catch (Throwable var114) {
                           var10000 = var114;
                           var10001 = false;
                           break label972;
                        }
                     } else {
                        var5 = null;
                     }

                     var4 = var6;
                     if (var5 != null) {
                        try {
                           var8.remove(var5);
                        } catch (Throwable var113) {
                           var10000 = var113;
                           var10001 = false;
                           break label972;
                        }

                        var4 = var6;
                     }
                     break label971;
                  }
               }

               var7.unlock();
               return;
            }
         }

         if (var1 != null) {
            label970: {
               try {
                  var4.updateState$kotlinx_coroutines_core(var2, (Continuation)var1);
                  var1 = var9.realCaller(var1);
               } catch (Throwable var111) {
                  var10000 = var111;
                  var10001 = false;
                  break label970;
               }

               if (var1 == null) {
                  var7.unlock();
                  return;
               }

               try {
                  ((Map)var8).put(var1, var4);
                  Unit var120 = Unit.INSTANCE;
               } catch (Throwable var110) {
                  var10000 = var110;
                  var10001 = false;
                  break label970;
               }

               var7.unlock();
               return;
            }
         } else {
            label933:
            try {
               NullPointerException var122 = new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<*>");
               throw var122;
            } catch (Throwable var112) {
               var10000 = var112;
               var10001 = false;
               break label933;
            }
         }
      }

      Throwable var121 = var10000;
      var7.unlock();
      throw var121;
   }

   private final void updateState(Continuation var1, String var2) {
      if (this.isInstalled$kotlinx_coroutines_core()) {
         if (Intrinsics.areEqual((Object)var2, (Object)"RUNNING") && KotlinVersion.CURRENT.isAtLeast(1, 3, 30)) {
            Continuation var5 = var1;
            if (!(var1 instanceof CoroutineStackFrame)) {
               var5 = null;
            }

            CoroutineStackFrame var4 = (CoroutineStackFrame)var5;
            if (var4 != null) {
               this.updateRunningState(var4, var2);
            }

         } else {
            CoroutineOwner var3 = this.owner(var1);
            if (var3 != null) {
               this.updateState(var3, var1, var2);
            }

         }
      }
   }

   private final void updateState(CoroutineOwner var1, Continuation var2, String var3) {
      ReentrantReadWriteLock.ReadLock var5 = coroutineStateLock.readLock();
      var5.lock();

      Throwable var10000;
      label78: {
         boolean var10001;
         boolean var4;
         try {
            var4 = INSTANCE.isInstalled$kotlinx_coroutines_core();
         } catch (Throwable var11) {
            var10000 = var11;
            var10001 = false;
            break label78;
         }

         if (!var4) {
            var5.unlock();
            return;
         }

         try {
            var1.info.updateState$kotlinx_coroutines_core(var3, var2);
            Unit var13 = Unit.INSTANCE;
         } catch (Throwable var10) {
            var10000 = var10;
            var10001 = false;
            break label78;
         }

         var5.unlock();
         return;
      }

      Throwable var12 = var10000;
      var5.unlock();
      throw var12;
   }

   public final void dumpCoroutines(PrintStream var1) {
      synchronized(var1){}

      try {
         INSTANCE.dumpCoroutinesSynchronized(var1);
         Unit var2 = Unit.INSTANCE;
      } finally {
         ;
      }

   }

   public final List dumpCoroutinesInfo() {
      ReentrantReadWriteLock var6 = access$getCoroutineStateLock$p(this);
      ReentrantReadWriteLock.ReadLock var7 = var6.readLock();
      int var1 = var6.getWriteHoldCount();
      byte var3 = 0;
      byte var4 = 0;
      if (var1 == 0) {
         var1 = var6.getReadHoldCount();
      } else {
         var1 = 0;
      }

      int var2;
      for(var2 = 0; var2 < var1; ++var2) {
         var7.unlock();
      }

      ReentrantReadWriteLock.WriteLock var8 = var6.writeLock();
      var8.lock();

      Throwable var10000;
      label751: {
         boolean var10001;
         label750: {
            Iterator var10;
            Collection var76;
            try {
               DebugProbesImpl var69 = INSTANCE;
               if (!var69.isInstalled$kotlinx_coroutines_core()) {
                  break label750;
               }

               Iterable var70 = (Iterable)access$getCapturedCoroutines$p(var69);
               Comparator var9 = new Comparator() {
                  public final int compare(Object var1, Object var2) {
                     return ComparisonsKt.compareValues((Comparable)((CoroutineOwner)var1).info.sequenceNumber, (Comparable)((CoroutineOwner)var2).info.sequenceNumber);
                  }
               };
               var70 = (Iterable)CollectionsKt.sortedWith(var70, (Comparator)var9);
               ArrayList var75 = new ArrayList();
               var76 = (Collection)var75;
               var10 = var70.iterator();
            } catch (Throwable var68) {
               var10000 = var68;
               var10001 = false;
               break label751;
            }

            while(true) {
               boolean var5;
               CoroutineOwner var12;
               label762: {
                  try {
                     if (var10.hasNext()) {
                        var12 = (CoroutineOwner)var10.next();
                        var5 = access$isFinished(INSTANCE, var12);
                        break label762;
                     }
                  } catch (Throwable var67) {
                     var10000 = var67;
                     var10001 = false;
                     break label751;
                  }

                  List var71;
                  try {
                     var71 = (List)var76;
                  } catch (Throwable var62) {
                     var10000 = var62;
                     var10001 = false;
                     break label751;
                  }

                  for(var2 = var4; var2 < var1; ++var2) {
                     var7.lock();
                  }

                  var8.unlock();
                  return var71;
               }

               DebugCoroutineInfo var72 = null;
               if (!var5) {
                  CoroutineContext var11;
                  try {
                     var11 = var12.info.getContext();
                  } catch (Throwable var66) {
                     var10000 = var66;
                     var10001 = false;
                     break label751;
                  }

                  if (var11 != null) {
                     try {
                        var72 = new DebugCoroutineInfo(var12.info, var11);
                     } catch (Throwable var65) {
                        var10000 = var65;
                        var10001 = false;
                        break label751;
                     }
                  }
               }

               if (var72 != null) {
                  try {
                     var76.add(var72);
                  } catch (Throwable var64) {
                     var10000 = var64;
                     var10001 = false;
                     break label751;
                  }
               }
            }
         }

         label726:
         try {
            IllegalStateException var74 = new IllegalStateException("Debug probes are not installed".toString());
            throw (Throwable)var74;
         } catch (Throwable var63) {
            var10000 = var63;
            var10001 = false;
            break label726;
         }
      }

      Throwable var73 = var10000;

      for(var2 = var3; var2 < var1; ++var2) {
         var7.lock();
      }

      var8.unlock();
      throw var73;
   }

   public final List dumpDebuggerInfo() {
      ReentrantReadWriteLock var6 = access$getCoroutineStateLock$p(this);
      ReentrantReadWriteLock.ReadLock var7 = var6.readLock();
      int var1 = var6.getWriteHoldCount();
      byte var3 = 0;
      byte var4 = 0;
      if (var1 == 0) {
         var1 = var6.getReadHoldCount();
      } else {
         var1 = 0;
      }

      int var2;
      for(var2 = 0; var2 < var1; ++var2) {
         var7.unlock();
      }

      ReentrantReadWriteLock.WriteLock var8 = var6.writeLock();
      var8.lock();

      Throwable var10000;
      label751: {
         boolean var10001;
         label750: {
            Iterator var10;
            Collection var76;
            try {
               DebugProbesImpl var69 = INSTANCE;
               if (!var69.isInstalled$kotlinx_coroutines_core()) {
                  break label750;
               }

               Iterable var70 = (Iterable)access$getCapturedCoroutines$p(var69);
               Comparator var9 = new Comparator() {
                  public final int compare(Object var1, Object var2) {
                     return ComparisonsKt.compareValues((Comparable)((CoroutineOwner)var1).info.sequenceNumber, (Comparable)((CoroutineOwner)var2).info.sequenceNumber);
                  }
               };
               var70 = (Iterable)CollectionsKt.sortedWith(var70, (Comparator)var9);
               ArrayList var75 = new ArrayList();
               var76 = (Collection)var75;
               var10 = var70.iterator();
            } catch (Throwable var68) {
               var10000 = var68;
               var10001 = false;
               break label751;
            }

            while(true) {
               boolean var5;
               CoroutineOwner var11;
               label762: {
                  try {
                     if (var10.hasNext()) {
                        var11 = (CoroutineOwner)var10.next();
                        var5 = access$isFinished(INSTANCE, var11);
                        break label762;
                     }
                  } catch (Throwable var67) {
                     var10000 = var67;
                     var10001 = false;
                     break label751;
                  }

                  List var71;
                  try {
                     var71 = (List)var76;
                  } catch (Throwable var62) {
                     var10000 = var62;
                     var10001 = false;
                     break label751;
                  }

                  for(var2 = var4; var2 < var1; ++var2) {
                     var7.lock();
                  }

                  var8.unlock();
                  return var71;
               }

               DebuggerInfo var72 = null;
               if (!var5) {
                  CoroutineContext var12;
                  try {
                     var12 = var11.info.getContext();
                  } catch (Throwable var66) {
                     var10000 = var66;
                     var10001 = false;
                     break label751;
                  }

                  if (var12 != null) {
                     try {
                        var72 = new DebuggerInfo(var11.info, var12);
                     } catch (Throwable var65) {
                        var10000 = var65;
                        var10001 = false;
                        break label751;
                     }
                  }
               }

               if (var72 != null) {
                  try {
                     var76.add(var72);
                  } catch (Throwable var64) {
                     var10000 = var64;
                     var10001 = false;
                     break label751;
                  }
               }
            }
         }

         label726:
         try {
            IllegalStateException var74 = new IllegalStateException("Debug probes are not installed".toString());
            throw (Throwable)var74;
         } catch (Throwable var63) {
            var10000 = var63;
            var10001 = false;
            break label726;
         }
      }

      Throwable var73 = var10000;

      for(var2 = var3; var2 < var1; ++var2) {
         var7.lock();
      }

      var8.unlock();
      throw var73;
   }

   public final List enhanceStackTraceWithThreadDump(DebugCoroutineInfo var1, List var2) {
      return this.enhanceStackTraceWithThreadDumpImpl(var1.getState(), var1.getLastObservedThread(), var2);
   }

   public final boolean getEnableCreationStackTraces() {
      return enableCreationStackTraces;
   }

   public final boolean getSanitizeStackTraces() {
      return sanitizeStackTraces;
   }

   public final String hierarchyToString(Job var1) {
      ReentrantReadWriteLock var7 = coroutineStateLock;
      ReentrantReadWriteLock.ReadLock var6 = var7.readLock();
      int var2 = var7.getWriteHoldCount();
      byte var5 = 0;
      byte var4 = 0;
      if (var2 == 0) {
         var2 = var7.getReadHoldCount();
      } else {
         var2 = 0;
      }

      int var3;
      for(var3 = 0; var3 < var2; ++var3) {
         var6.unlock();
      }

      ReentrantReadWriteLock.WriteLock var71 = var7.writeLock();
      var71.lock();

      Throwable var10000;
      label823: {
         Iterable var9;
         Collection var73;
         Iterator var75;
         boolean var10001;
         label822: {
            try {
               DebugProbesImpl var8 = INSTANCE;
               if (var8.isInstalled$kotlinx_coroutines_core()) {
                  var9 = (Iterable)var8.getCapturedCoroutines();
                  ArrayList var72 = new ArrayList();
                  var73 = (Collection)var72;
                  var75 = var9.iterator();
                  break label822;
               }
            } catch (Throwable var66) {
               var10000 = var66;
               var10001 = false;
               break label823;
            }

            try {
               IllegalStateException var67 = new IllegalStateException("Debug probes are not installed".toString());
               throw (Throwable)var67;
            } catch (Throwable var63) {
               var10000 = var63;
               var10001 = false;
               break label823;
            }
         }

         label816:
         while(true) {
            boolean var70;
            Object var10;
            label833: {
               label813: {
                  label812: {
                     try {
                        if (!var75.hasNext()) {
                           break label813;
                        }

                        var10 = var75.next();
                        if (((CoroutineOwner)var10).delegate.getContext().get((CoroutineContext.Key)Job.Key) != null) {
                           break label812;
                        }
                     } catch (Throwable var65) {
                        var10000 = var65;
                        var10001 = false;
                        break;
                     }

                     var70 = false;
                     break label833;
                  }

                  var70 = true;
                  break label833;
               }

               Map var76;
               Iterator var78;
               try {
                  var9 = (Iterable)((List)var73);
                  var3 = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(var9, 10)), 16);
                  LinkedHashMap var74 = new LinkedHashMap(var3);
                  var76 = (Map)var74;
                  var78 = var9.iterator();
               } catch (Throwable var61) {
                  var10000 = var61;
                  var10001 = false;
                  break;
               }

               while(true) {
                  try {
                     if (!var78.hasNext()) {
                        break;
                     }

                     Object var77 = var78.next();
                     var76.put(JobKt.getJob(((CoroutineOwner)var77).delegate.getContext()), ((CoroutineOwner)var77).info);
                  } catch (Throwable var62) {
                     var10000 = var62;
                     var10001 = false;
                     break label816;
                  }
               }

               String var69;
               try {
                  StringBuilder var79 = new StringBuilder();
                  INSTANCE.build(var1, var76, var79, "");
                  var69 = var79.toString();
                  Intrinsics.checkNotNullExpressionValue(var69, "StringBuilder().apply(builderAction).toString()");
               } catch (Throwable var60) {
                  var10000 = var60;
                  var10001 = false;
                  break;
               }

               for(var3 = var4; var3 < var2; ++var3) {
                  var6.lock();
               }

               var71.unlock();
               return var69;
            }

            if (var70) {
               try {
                  var73.add(var10);
               } catch (Throwable var64) {
                  var10000 = var64;
                  var10001 = false;
                  break;
               }
            }
         }
      }

      Throwable var68 = var10000;

      for(var3 = var5; var3 < var2; ++var3) {
         var6.lock();
      }

      var71.unlock();
      throw var68;
   }

   public final void install() {
      ReentrantReadWriteLock var9 = coroutineStateLock;
      ReentrantReadWriteLock.ReadLock var8 = var9.readLock();
      int var1 = var9.getWriteHoldCount();
      byte var6 = 0;
      byte var3 = 0;
      byte var4 = 0;
      byte var5 = 0;
      if (var1 == 0) {
         var1 = var9.getReadHoldCount();
      } else {
         var1 = 0;
      }

      int var2;
      for(var2 = 0; var2 < var1; ++var2) {
         var8.unlock();
      }

      ReentrantReadWriteLock.WriteLock var41 = var9.writeLock();
      var41.lock();

      Throwable var10000;
      label592: {
         boolean var10001;
         try {
            ++installations;
            var2 = installations;
         } catch (Throwable var40) {
            var10000 = var40;
            var10001 = false;
            break label592;
         }

         if (var2 > 1) {
            for(var2 = var5; var2 < var1; ++var2) {
               var8.lock();
            }

            var41.unlock();
            return;
         }

         boolean var7;
         try {
            INSTANCE.startWeakRefCleanerThread();
            var7 = AgentPremain.INSTANCE.isInstalledStatically();
         } catch (Throwable var39) {
            var10000 = var39;
            var10001 = false;
            break label592;
         }

         if (var7) {
            for(var2 = var6; var2 < var1; ++var2) {
               var8.lock();
            }

            var41.unlock();
            return;
         }

         Function1 var10;
         try {
            var10 = dynamicAttach;
         } catch (Throwable var38) {
            var10000 = var38;
            var10001 = false;
            break label592;
         }

         Unit var42;
         if (var10 != null) {
            try {
               var42 = (Unit)var10.invoke(true);
            } catch (Throwable var37) {
               var10000 = var37;
               var10001 = false;
               break label592;
            }
         }

         try {
            var42 = Unit.INSTANCE;
         } catch (Throwable var36) {
            var10000 = var36;
            var10001 = false;
            break label592;
         }

         for(var2 = var3; var2 < var1; ++var2) {
            var8.lock();
         }

         var41.unlock();
         return;
      }

      Throwable var43 = var10000;

      for(var2 = var4; var2 < var1; ++var2) {
         var8.lock();
      }

      var41.unlock();
      throw var43;
   }

   public final boolean isInstalled$kotlinx_coroutines_core() {
      boolean var1;
      if (installations > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final Continuation probeCoroutineCreated$kotlinx_coroutines_core(Continuation var1) {
      if (!this.isInstalled$kotlinx_coroutines_core()) {
         return var1;
      } else if (this.owner(var1) != null) {
         return var1;
      } else {
         StackTraceFrame var2;
         if (enableCreationStackTraces) {
            var2 = this.toStackTraceFrame(this.sanitizeStackTrace((Throwable)(new Exception())));
         } else {
            var2 = null;
         }

         return this.createOwner(var1, var2);
      }
   }

   public final void probeCoroutineResumed$kotlinx_coroutines_core(Continuation var1) {
      this.updateState(var1, "RUNNING");
   }

   public final void probeCoroutineSuspended$kotlinx_coroutines_core(Continuation var1) {
      this.updateState(var1, "SUSPENDED");
   }

   public final void setEnableCreationStackTraces(boolean var1) {
      enableCreationStackTraces = var1;
   }

   public final void setSanitizeStackTraces(boolean var1) {
      sanitizeStackTraces = var1;
   }

   public final void uninstall() {
      ReentrantReadWriteLock var9 = coroutineStateLock;
      ReentrantReadWriteLock.ReadLock var8 = var9.readLock();
      int var1 = var9.getWriteHoldCount();
      byte var5 = 0;
      byte var3 = 0;
      byte var6 = 0;
      byte var4 = 0;
      if (var1 == 0) {
         var1 = var9.getReadHoldCount();
      } else {
         var1 = 0;
      }

      int var2;
      for(var2 = 0; var2 < var1; ++var2) {
         var8.unlock();
      }

      ReentrantReadWriteLock.WriteLock var53 = var9.writeLock();
      var53.lock();

      Throwable var10000;
      label761: {
         DebugProbesImpl var10;
         boolean var10001;
         label750: {
            try {
               var10 = INSTANCE;
               if (var10.isInstalled$kotlinx_coroutines_core()) {
                  --installations;
                  var2 = installations;
                  break label750;
               }
            } catch (Throwable var52) {
               var10000 = var52;
               var10001 = false;
               break label761;
            }

            try {
               IllegalStateException var54 = new IllegalStateException("Agent was not installed".toString());
               throw (Throwable)var54;
            } catch (Throwable var51) {
               var10000 = var51;
               var10001 = false;
               break label761;
            }
         }

         if (var2 != 0) {
            for(var2 = var4; var2 < var1; ++var2) {
               var8.lock();
            }

            var53.unlock();
            return;
         }

         boolean var7;
         try {
            var10.stopWeakRefCleanerThread();
            capturedCoroutinesMap.clear();
            callerInfoCache.clear();
            var7 = AgentPremain.INSTANCE.isInstalledStatically();
         } catch (Throwable var50) {
            var10000 = var50;
            var10001 = false;
            break label761;
         }

         if (var7) {
            for(var2 = var5; var2 < var1; ++var2) {
               var8.lock();
            }

            var53.unlock();
            return;
         }

         Function1 var55;
         try {
            var55 = dynamicAttach;
         } catch (Throwable var49) {
            var10000 = var49;
            var10001 = false;
            break label761;
         }

         Unit var56;
         if (var55 != null) {
            try {
               var56 = (Unit)var55.invoke(false);
            } catch (Throwable var48) {
               var10000 = var48;
               var10001 = false;
               break label761;
            }
         }

         try {
            var56 = Unit.INSTANCE;
         } catch (Throwable var47) {
            var10000 = var47;
            var10001 = false;
            break label761;
         }

         for(var2 = var3; var2 < var1; ++var2) {
            var8.lock();
         }

         var53.unlock();
         return;
      }

      Throwable var57 = var10000;

      for(var2 = var6; var2 < var1; ++var2) {
         var8.lock();
      }

      var53.unlock();
      throw var57;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001e\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"},
      d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "info", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "frame", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private static final class CoroutineOwner implements Continuation, CoroutineStackFrame {
      public final Continuation delegate;
      private final CoroutineStackFrame frame;
      public final DebugCoroutineInfoImpl info;

      public CoroutineOwner(Continuation var1, DebugCoroutineInfoImpl var2, CoroutineStackFrame var3) {
         this.delegate = var1;
         this.info = var2;
         this.frame = var3;
      }

      public CoroutineStackFrame getCallerFrame() {
         CoroutineStackFrame var1 = this.frame;
         if (var1 != null) {
            var1 = var1.getCallerFrame();
         } else {
            var1 = null;
         }

         return var1;
      }

      public CoroutineContext getContext() {
         return this.delegate.getContext();
      }

      public StackTraceElement getStackTraceElement() {
         CoroutineStackFrame var1 = this.frame;
         StackTraceElement var2;
         if (var1 != null) {
            var2 = var1.getStackTraceElement();
         } else {
            var2 = null;
         }

         return var2;
      }

      public void resumeWith(Object var1) {
         DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
         this.delegate.resumeWith(var1);
      }

      public String toString() {
         return this.delegate.toString();
      }
   }
}
