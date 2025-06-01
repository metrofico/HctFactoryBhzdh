package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aM\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001aO\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001aM\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001aO\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a'\u0010\u0011\u001a\u00020\f2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a3\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a;\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a3\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a;\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a;\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u001a;\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"},
   d2 = {"fixedRateTimer", "Ljava/util/Timer;", "name", "", "daemon", "", "startAt", "Ljava/util/Date;", "period", "", "action", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "initialDelay", "timer", "timerTask", "schedule", "time", "delay", "scheduleAtFixedRate", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class TimersKt {
   private static final Timer fixedRateTimer(String var0, boolean var1, long var2, long var4, Function1 var6) {
      Intrinsics.checkNotNullParameter(var6, "action");
      Timer var7 = timer(var0, var1);
      var7.scheduleAtFixedRate((TimerTask)(new TimerTask(var6) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var4);
      return var7;
   }

   private static final Timer fixedRateTimer(String var0, boolean var1, Date var2, long var3, Function1 var5) {
      Intrinsics.checkNotNullParameter(var2, "startAt");
      Intrinsics.checkNotNullParameter(var5, "action");
      Timer var6 = timer(var0, var1);
      var6.scheduleAtFixedRate((TimerTask)(new TimerTask(var5) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var3);
      return var6;
   }

   // $FF: synthetic method
   static Timer fixedRateTimer$default(String var0, boolean var1, long var2, long var4, Function1 var6, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var0 = null;
      }

      if ((var7 & 2) != 0) {
         var1 = false;
      }

      if ((var7 & 4) != 0) {
         var2 = 0L;
      }

      Intrinsics.checkNotNullParameter(var6, "action");
      Timer var9 = timer(var0, var1);
      var9.scheduleAtFixedRate((TimerTask)(new TimerTask(var6) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var4);
      return var9;
   }

   // $FF: synthetic method
   static Timer fixedRateTimer$default(String var0, boolean var1, Date var2, long var3, Function1 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var0 = null;
      }

      if ((var6 & 2) != 0) {
         var1 = false;
      }

      Intrinsics.checkNotNullParameter(var2, "startAt");
      Intrinsics.checkNotNullParameter(var5, "action");
      Timer var8 = timer(var0, var1);
      var8.scheduleAtFixedRate((TimerTask)(new TimerTask(var5) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var3);
      return var8;
   }

   private static final TimerTask schedule(Timer var0, long var1, long var3, Function1 var5) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var5, "action");
      TimerTask var6 = (TimerTask)(new TimerTask(var5) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
      var0.schedule(var6, var1, var3);
      return var6;
   }

   private static final TimerTask schedule(Timer var0, long var1, Function1 var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var3, "action");
      TimerTask var4 = (TimerTask)(new TimerTask(var3) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
      var0.schedule(var4, var1);
      return var4;
   }

   private static final TimerTask schedule(Timer var0, Date var1, long var2, Function1 var4) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "time");
      Intrinsics.checkNotNullParameter(var4, "action");
      TimerTask var5 = (TimerTask)(new TimerTask(var4) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
      var0.schedule(var5, var1, var2);
      return var5;
   }

   private static final TimerTask schedule(Timer var0, Date var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "time");
      Intrinsics.checkNotNullParameter(var2, "action");
      TimerTask var3 = (TimerTask)(new TimerTask(var2) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
      var0.schedule(var3, var1);
      return var3;
   }

   private static final TimerTask scheduleAtFixedRate(Timer var0, long var1, long var3, Function1 var5) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var5, "action");
      TimerTask var6 = (TimerTask)(new TimerTask(var5) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
      var0.scheduleAtFixedRate(var6, var1, var3);
      return var6;
   }

   private static final TimerTask scheduleAtFixedRate(Timer var0, Date var1, long var2, Function1 var4) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "time");
      Intrinsics.checkNotNullParameter(var4, "action");
      TimerTask var5 = (TimerTask)(new TimerTask(var4) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
      var0.scheduleAtFixedRate(var5, var1, var2);
      return var5;
   }

   public static final Timer timer(String var0, boolean var1) {
      Timer var2;
      if (var0 == null) {
         var2 = new Timer(var1);
      } else {
         var2 = new Timer(var0, var1);
      }

      return var2;
   }

   private static final Timer timer(String var0, boolean var1, long var2, long var4, Function1 var6) {
      Intrinsics.checkNotNullParameter(var6, "action");
      Timer var7 = timer(var0, var1);
      var7.schedule((TimerTask)(new TimerTask(var6) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var4);
      return var7;
   }

   private static final Timer timer(String var0, boolean var1, Date var2, long var3, Function1 var5) {
      Intrinsics.checkNotNullParameter(var2, "startAt");
      Intrinsics.checkNotNullParameter(var5, "action");
      Timer var6 = timer(var0, var1);
      var6.schedule((TimerTask)(new TimerTask(var5) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var3);
      return var6;
   }

   // $FF: synthetic method
   static Timer timer$default(String var0, boolean var1, long var2, long var4, Function1 var6, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var0 = null;
      }

      if ((var7 & 2) != 0) {
         var1 = false;
      }

      if ((var7 & 4) != 0) {
         var2 = 0L;
      }

      Intrinsics.checkNotNullParameter(var6, "action");
      Timer var9 = timer(var0, var1);
      var9.schedule((TimerTask)(new TimerTask(var6) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var4);
      return var9;
   }

   // $FF: synthetic method
   static Timer timer$default(String var0, boolean var1, Date var2, long var3, Function1 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var0 = null;
      }

      if ((var6 & 2) != 0) {
         var1 = false;
      }

      Intrinsics.checkNotNullParameter(var2, "startAt");
      Intrinsics.checkNotNullParameter(var5, "action");
      Timer var8 = timer(var0, var1);
      var8.schedule((TimerTask)(new TimerTask(var5) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      }), var2, var3);
      return var8;
   }

   private static final TimerTask timerTask(Function1 var0) {
      Intrinsics.checkNotNullParameter(var0, "action");
      return (TimerTask)(new TimerTask(var0) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void run() {
            this.$action.invoke(this);
         }
      });
   }
}
