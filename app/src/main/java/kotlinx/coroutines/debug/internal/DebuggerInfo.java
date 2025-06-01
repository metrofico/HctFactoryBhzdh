package kotlinx.coroutines.debug.internal;

import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.CoroutineName;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u0011\u0010\u001b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000f¨\u0006 "},
   d2 = {"Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "Ljava/io/Serializable;", "source", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/CoroutineContext;)V", "coroutineId", "", "getCoroutineId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "dispatcher", "", "getDispatcher", "()Ljava/lang/String;", "lastObservedStackTrace", "", "Ljava/lang/StackTraceElement;", "getLastObservedStackTrace", "()Ljava/util/List;", "lastObservedThreadName", "getLastObservedThreadName", "lastObservedThreadState", "getLastObservedThreadState", "name", "getName", "sequenceNumber", "getSequenceNumber", "()J", "state", "getState", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DebuggerInfo implements Serializable {
   private final Long coroutineId;
   private final String dispatcher;
   private final List lastObservedStackTrace;
   private final String lastObservedThreadName;
   private final String lastObservedThreadState;
   private final String name;
   private final long sequenceNumber;
   private final String state;

   public DebuggerInfo(DebugCoroutineInfoImpl var1, CoroutineContext var2) {
      CoroutineId var3 = (CoroutineId)var2.get((CoroutineContext.Key)CoroutineId.Key);
      Object var4 = null;
      Long var7;
      if (var3 != null) {
         var7 = var3.getId();
      } else {
         var7 = null;
      }

      this.coroutineId = var7;
      ContinuationInterceptor var10 = (ContinuationInterceptor)var2.get((CoroutineContext.Key)ContinuationInterceptor.Key);
      String var11;
      if (var10 != null) {
         var11 = var10.toString();
      } else {
         var11 = null;
      }

      this.dispatcher = var11;
      CoroutineName var5 = (CoroutineName)var2.get((CoroutineContext.Key)CoroutineName.Key);
      String var6;
      if (var5 != null) {
         var6 = var5.getName();
      } else {
         var6 = null;
      }

      label30: {
         this.name = var6;
         this.state = var1.getState();
         Thread var8 = var1.lastObservedThread;
         if (var8 != null) {
            Thread.State var9 = var8.getState();
            if (var9 != null) {
               var6 = var9.toString();
               break label30;
            }
         }

         var6 = null;
      }

      this.lastObservedThreadState = var6;
      Thread var12 = var1.lastObservedThread;
      var6 = (String)var4;
      if (var12 != null) {
         var6 = var12.getName();
      }

      this.lastObservedThreadName = var6;
      this.lastObservedStackTrace = var1.lastObservedStackTrace();
      this.sequenceNumber = var1.sequenceNumber;
   }

   public final Long getCoroutineId() {
      return this.coroutineId;
   }

   public final String getDispatcher() {
      return this.dispatcher;
   }

   public final List getLastObservedStackTrace() {
      return this.lastObservedStackTrace;
   }

   public final String getLastObservedThreadName() {
      return this.lastObservedThreadName;
   }

   public final String getLastObservedThreadState() {
      return this.lastObservedThreadState;
   }

   public final String getName() {
      return this.name;
   }

   public final long getSequenceNumber() {
      return this.sequenceNumber;
   }

   public final String getState() {
      return this.state;
   }
}
