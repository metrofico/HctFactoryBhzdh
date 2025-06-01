package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

public abstract class Lifecycle {
   AtomicReference mInternalScopeRef = new AtomicReference();

   public abstract void addObserver(LifecycleObserver var1);

   public abstract State getCurrentState();

   public abstract void removeObserver(LifecycleObserver var1);

   public static enum Event {
      private static final Event[] $VALUES;
      ON_ANY,
      ON_CREATE,
      ON_DESTROY,
      ON_PAUSE,
      ON_RESUME,
      ON_START,
      ON_STOP;

      static {
         Event var3 = new Event("ON_CREATE", 0);
         ON_CREATE = var3;
         Event var2 = new Event("ON_START", 1);
         ON_START = var2;
         Event var6 = new Event("ON_RESUME", 2);
         ON_RESUME = var6;
         Event var5 = new Event("ON_PAUSE", 3);
         ON_PAUSE = var5;
         Event var1 = new Event("ON_STOP", 4);
         ON_STOP = var1;
         Event var4 = new Event("ON_DESTROY", 5);
         ON_DESTROY = var4;
         Event var0 = new Event("ON_ANY", 6);
         ON_ANY = var0;
         $VALUES = new Event[]{var3, var2, var6, var5, var1, var4, var0};
      }

      public static Event downFrom(State var0) {
         int var1 = null.$SwitchMap$androidx$lifecycle$Lifecycle$State[var0.ordinal()];
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? null : ON_PAUSE;
            } else {
               return ON_STOP;
            }
         } else {
            return ON_DESTROY;
         }
      }

      public static Event downTo(State var0) {
         int var1 = null.$SwitchMap$androidx$lifecycle$Lifecycle$State[var0.ordinal()];
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 4 ? null : ON_DESTROY;
            } else {
               return ON_PAUSE;
            }
         } else {
            return ON_STOP;
         }
      }

      public static Event upFrom(State var0) {
         int var1 = null.$SwitchMap$androidx$lifecycle$Lifecycle$State[var0.ordinal()];
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 5 ? null : ON_CREATE;
            } else {
               return ON_RESUME;
            }
         } else {
            return ON_START;
         }
      }

      public static Event upTo(State var0) {
         int var1 = null.$SwitchMap$androidx$lifecycle$Lifecycle$State[var0.ordinal()];
         if (var1 != 1) {
            if (var1 != 2) {
               return var1 != 3 ? null : ON_RESUME;
            } else {
               return ON_START;
            }
         } else {
            return ON_CREATE;
         }
      }

      public State getTargetState() {
         switch (null.$SwitchMap$androidx$lifecycle$Lifecycle$Event[this.ordinal()]) {
            case 1:
            case 2:
               return Lifecycle.State.CREATED;
            case 3:
            case 4:
               return Lifecycle.State.STARTED;
            case 5:
               return Lifecycle.State.RESUMED;
            case 6:
               return Lifecycle.State.DESTROYED;
            default:
               throw new IllegalArgumentException(this + " has no target state");
         }
      }
   }

   public static enum State {
      private static final State[] $VALUES;
      CREATED,
      DESTROYED,
      INITIALIZED,
      RESUMED,
      STARTED;

      static {
         State var3 = new State("DESTROYED", 0);
         DESTROYED = var3;
         State var0 = new State("INITIALIZED", 1);
         INITIALIZED = var0;
         State var1 = new State("CREATED", 2);
         CREATED = var1;
         State var2 = new State("STARTED", 3);
         STARTED = var2;
         State var4 = new State("RESUMED", 4);
         RESUMED = var4;
         $VALUES = new State[]{var3, var0, var1, var2, var4};
      }

      public boolean isAtLeast(State var1) {
         boolean var2;
         if (this.compareTo(var1) >= 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }
   }
}
