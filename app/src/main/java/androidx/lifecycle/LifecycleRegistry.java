package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LifecycleRegistry extends Lifecycle {
   private int mAddingObserverCounter;
   private final boolean mEnforceMainThread;
   private boolean mHandlingEvent;
   private final WeakReference mLifecycleOwner;
   private boolean mNewEventOccurred;
   private FastSafeIterableMap mObserverMap;
   private ArrayList mParentStates;
   private Lifecycle.State mState;

   public LifecycleRegistry(LifecycleOwner var1) {
      this(var1, true);
   }

   private LifecycleRegistry(LifecycleOwner var1, boolean var2) {
      this.mObserverMap = new FastSafeIterableMap();
      this.mAddingObserverCounter = 0;
      this.mHandlingEvent = false;
      this.mNewEventOccurred = false;
      this.mParentStates = new ArrayList();
      this.mLifecycleOwner = new WeakReference(var1);
      this.mState = Lifecycle.State.INITIALIZED;
      this.mEnforceMainThread = var2;
   }

   private void backwardPass(LifecycleOwner var1) {
      Iterator var5 = this.mObserverMap.descendingIterator();

      while(var5.hasNext() && !this.mNewEventOccurred) {
         Map.Entry var3 = (Map.Entry)var5.next();
         ObserverWithState var4 = (ObserverWithState)var3.getValue();

         while(var4.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains((LifecycleObserver)var3.getKey())) {
            Lifecycle.Event var2 = Lifecycle.Event.downFrom(var4.mState);
            if (var2 == null) {
               throw new IllegalStateException("no event down from " + var4.mState);
            }

            this.pushParentState(var2.getTargetState());
            var4.dispatchEvent(var1, var2);
            this.popParentState();
         }
      }

   }

   private Lifecycle.State calculateTargetState(LifecycleObserver var1) {
      Map.Entry var3 = this.mObserverMap.ceil(var1);
      Lifecycle.State var2 = null;
      Lifecycle.State var4;
      if (var3 != null) {
         var4 = ((ObserverWithState)var3.getValue()).mState;
      } else {
         var4 = null;
      }

      if (!this.mParentStates.isEmpty()) {
         ArrayList var5 = this.mParentStates;
         var2 = (Lifecycle.State)var5.get(var5.size() - 1);
      }

      return min(min(this.mState, var4), var2);
   }

   public static LifecycleRegistry createUnsafe(LifecycleOwner var0) {
      return new LifecycleRegistry(var0, false);
   }

   private void enforceMainThreadIfNeeded(String var1) {
      if (this.mEnforceMainThread && !ArchTaskExecutor.getInstance().isMainThread()) {
         throw new IllegalStateException("Method " + var1 + " must be called on the main thread");
      }
   }

   private void forwardPass(LifecycleOwner var1) {
      SafeIterableMap.IteratorWithAdditions var2 = this.mObserverMap.iteratorWithAdditions();

      while(var2.hasNext() && !this.mNewEventOccurred) {
         Map.Entry var4 = (Map.Entry)var2.next();
         ObserverWithState var5 = (ObserverWithState)var4.getValue();

         while(var5.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains((LifecycleObserver)var4.getKey())) {
            this.pushParentState(var5.mState);
            Lifecycle.Event var3 = Lifecycle.Event.upFrom(var5.mState);
            if (var3 == null) {
               throw new IllegalStateException("no event up from " + var5.mState);
            }

            var5.dispatchEvent(var1, var3);
            this.popParentState();
         }
      }

   }

   private boolean isSynced() {
      int var1 = this.mObserverMap.size();
      boolean var2 = true;
      if (var1 == 0) {
         return true;
      } else {
         Lifecycle.State var4 = ((ObserverWithState)this.mObserverMap.eldest().getValue()).mState;
         Lifecycle.State var3 = ((ObserverWithState)this.mObserverMap.newest().getValue()).mState;
         if (var4 != var3 || this.mState != var3) {
            var2 = false;
         }

         return var2;
      }
   }

   static Lifecycle.State min(Lifecycle.State var0, Lifecycle.State var1) {
      Lifecycle.State var2 = var0;
      if (var1 != null) {
         var2 = var0;
         if (var1.compareTo(var0) < 0) {
            var2 = var1;
         }
      }

      return var2;
   }

   private void moveToState(Lifecycle.State var1) {
      if (this.mState != var1) {
         this.mState = var1;
         if (!this.mHandlingEvent && this.mAddingObserverCounter == 0) {
            this.mHandlingEvent = true;
            this.sync();
            this.mHandlingEvent = false;
         } else {
            this.mNewEventOccurred = true;
         }
      }
   }

   private void popParentState() {
      ArrayList var1 = this.mParentStates;
      var1.remove(var1.size() - 1);
   }

   private void pushParentState(Lifecycle.State var1) {
      this.mParentStates.add(var1);
   }

   private void sync() {
      LifecycleOwner var1 = (LifecycleOwner)this.mLifecycleOwner.get();
      if (var1 != null) {
         while(!this.isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo(((ObserverWithState)this.mObserverMap.eldest().getValue()).mState) < 0) {
               this.backwardPass(var1);
            }

            Map.Entry var2 = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && var2 != null && this.mState.compareTo(((ObserverWithState)var2.getValue()).mState) > 0) {
               this.forwardPass(var1);
            }
         }

         this.mNewEventOccurred = false;
      } else {
         throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
      }
   }

   public void addObserver(LifecycleObserver var1) {
      this.enforceMainThreadIfNeeded("addObserver");
      Lifecycle.State var3;
      if (this.mState == Lifecycle.State.DESTROYED) {
         var3 = Lifecycle.State.DESTROYED;
      } else {
         var3 = Lifecycle.State.INITIALIZED;
      }

      ObserverWithState var4 = new ObserverWithState(var1, var3);
      if ((ObserverWithState)this.mObserverMap.putIfAbsent(var1, var4) == null) {
         LifecycleOwner var5 = (LifecycleOwner)this.mLifecycleOwner.get();
         if (var5 != null) {
            boolean var2;
            if (this.mAddingObserverCounter == 0 && !this.mHandlingEvent) {
               var2 = false;
            } else {
               var2 = true;
            }

            var3 = this.calculateTargetState(var1);
            ++this.mAddingObserverCounter;

            while(var4.mState.compareTo(var3) < 0 && this.mObserverMap.contains(var1)) {
               this.pushParentState(var4.mState);
               Lifecycle.Event var6 = Lifecycle.Event.upFrom(var4.mState);
               if (var6 == null) {
                  throw new IllegalStateException("no event up from " + var4.mState);
               }

               var4.dispatchEvent(var5, var6);
               this.popParentState();
               var3 = this.calculateTargetState(var1);
            }

            if (!var2) {
               this.sync();
            }

            --this.mAddingObserverCounter;
         }
      }
   }

   public Lifecycle.State getCurrentState() {
      return this.mState;
   }

   public int getObserverCount() {
      this.enforceMainThreadIfNeeded("getObserverCount");
      return this.mObserverMap.size();
   }

   public void handleLifecycleEvent(Lifecycle.Event var1) {
      this.enforceMainThreadIfNeeded("handleLifecycleEvent");
      this.moveToState(var1.getTargetState());
   }

   @Deprecated
   public void markState(Lifecycle.State var1) {
      this.enforceMainThreadIfNeeded("markState");
      this.setCurrentState(var1);
   }

   public void removeObserver(LifecycleObserver var1) {
      this.enforceMainThreadIfNeeded("removeObserver");
      this.mObserverMap.remove(var1);
   }

   public void setCurrentState(Lifecycle.State var1) {
      this.enforceMainThreadIfNeeded("setCurrentState");
      this.moveToState(var1);
   }

   static class ObserverWithState {
      LifecycleEventObserver mLifecycleObserver;
      Lifecycle.State mState;

      ObserverWithState(LifecycleObserver var1, Lifecycle.State var2) {
         this.mLifecycleObserver = Lifecycling.lifecycleEventObserver(var1);
         this.mState = var2;
      }

      void dispatchEvent(LifecycleOwner var1, Lifecycle.Event var2) {
         Lifecycle.State var3 = var2.getTargetState();
         this.mState = LifecycleRegistry.min(this.mState, var3);
         this.mLifecycleObserver.onStateChanged(var1, var2);
         this.mState = var3;
      }
   }
}
