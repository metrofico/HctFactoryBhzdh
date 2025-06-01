package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

public abstract class LiveData {
   static final Object NOT_SET = new Object();
   static final int START_VERSION = -1;
   int mActiveCount = 0;
   private boolean mChangingActiveState;
   private volatile Object mData;
   final Object mDataLock = new Object();
   private boolean mDispatchInvalidated;
   private boolean mDispatchingValue;
   private SafeIterableMap mObservers = new SafeIterableMap();
   volatile Object mPendingData;
   private final Runnable mPostValueRunnable;
   private int mVersion;

   public LiveData() {
      Object var1 = NOT_SET;
      this.mPendingData = var1;
      this.mPostValueRunnable = new Runnable(this) {
         final LiveData this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            // $FF: Couldn't be decompiled
         }
      };
      this.mData = var1;
      this.mVersion = -1;
   }

   public LiveData(Object var1) {
      this.mPendingData = NOT_SET;
      this.mPostValueRunnable = new Runnable(this) {
         final LiveData this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            // $FF: Couldn't be decompiled
         }
      };
      this.mData = var1;
      this.mVersion = 0;
   }

   static void assertMainThread(String var0) {
      if (!ArchTaskExecutor.getInstance().isMainThread()) {
         throw new IllegalStateException("Cannot invoke " + var0 + " on a background thread");
      }
   }

   private void considerNotify(ObserverWrapper var1) {
      if (var1.mActive) {
         if (!var1.shouldBeActive()) {
            var1.activeStateChanged(false);
         } else {
            int var2 = var1.mLastVersion;
            int var3 = this.mVersion;
            if (var2 < var3) {
               var1.mLastVersion = var3;
               var1.mObserver.onChanged(this.mData);
            }
         }
      }
   }

   void changeActiveCounter(int var1) {
      int var2 = this.mActiveCount;
      this.mActiveCount = var1 + var2;
      if (!this.mChangingActiveState) {
         this.mChangingActiveState = true;

         while(true) {
            int var3;
            label228: {
               Throwable var10000;
               label235: {
                  boolean var10001;
                  try {
                     var3 = this.mActiveCount;
                  } catch (Throwable var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label235;
                  }

                  if (var2 == var3) {
                     this.mChangingActiveState = false;
                     return;
                  }

                  boolean var17;
                  if (var2 == 0 && var3 > 0) {
                     var17 = true;
                  } else {
                     var17 = false;
                  }

                  boolean var18;
                  if (var2 > 0 && var3 == 0) {
                     var18 = true;
                  } else {
                     var18 = false;
                  }

                  if (var17) {
                     label219:
                     try {
                        this.onActive();
                     } catch (Throwable var14) {
                        var10000 = var14;
                        var10001 = false;
                        break label219;
                     }
                  } else {
                     if (!var18) {
                        break label228;
                     }

                     label221:
                     try {
                        this.onInactive();
                     } catch (Throwable var15) {
                        var10000 = var15;
                        var10001 = false;
                        break label221;
                     }
                  }
                  break label228;
               }

               Throwable var4 = var10000;
               this.mChangingActiveState = false;
               throw var4;
            }

            var2 = var3;
         }
      }
   }

   void dispatchingValue(ObserverWrapper var1) {
      if (this.mDispatchingValue) {
         this.mDispatchInvalidated = true;
      } else {
         this.mDispatchingValue = true;

         do {
            this.mDispatchInvalidated = false;
            ObserverWrapper var2;
            if (var1 != null) {
               this.considerNotify(var1);
               var2 = null;
            } else {
               SafeIterableMap.IteratorWithAdditions var3 = this.mObservers.iteratorWithAdditions();

               while(true) {
                  var2 = var1;
                  if (!var3.hasNext()) {
                     break;
                  }

                  this.considerNotify((ObserverWrapper)((Map.Entry)var3.next()).getValue());
                  if (this.mDispatchInvalidated) {
                     var2 = var1;
                     break;
                  }
               }
            }

            var1 = var2;
         } while(this.mDispatchInvalidated);

         this.mDispatchingValue = false;
      }
   }

   public Object getValue() {
      Object var1 = this.mData;
      return var1 != NOT_SET ? var1 : null;
   }

   int getVersion() {
      return this.mVersion;
   }

   public boolean hasActiveObservers() {
      boolean var1;
      if (this.mActiveCount > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean hasObservers() {
      boolean var1;
      if (this.mObservers.size() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void observe(LifecycleOwner var1, Observer var2) {
      assertMainThread("observe");
      if (var1.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) {
         LifecycleBoundObserver var3 = new LifecycleBoundObserver(this, var1, var2);
         ObserverWrapper var4 = (ObserverWrapper)this.mObservers.putIfAbsent(var2, var3);
         if (var4 != null && !var4.isAttachedTo(var1)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
         } else if (var4 == null) {
            var1.getLifecycle().addObserver(var3);
         }
      }
   }

   public void observeForever(Observer var1) {
      assertMainThread("observeForever");
      AlwaysActiveObserver var2 = new AlwaysActiveObserver(this, var1);
      ObserverWrapper var3 = (ObserverWrapper)this.mObservers.putIfAbsent(var1, var2);
      if (!(var3 instanceof LifecycleBoundObserver)) {
         if (var3 == null) {
            var2.activeStateChanged(true);
         }
      } else {
         throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
      }
   }

   protected void onActive() {
   }

   protected void onInactive() {
   }

   protected void postValue(Object var1) {
      Object var3 = this.mDataLock;
      synchronized(var3){}

      boolean var2;
      label164: {
         Throwable var10000;
         boolean var10001;
         label159: {
            label158: {
               label157: {
                  try {
                     if (this.mPendingData == NOT_SET) {
                        break label157;
                     }
                  } catch (Throwable var15) {
                     var10000 = var15;
                     var10001 = false;
                     break label159;
                  }

                  var2 = false;
                  break label158;
               }

               var2 = true;
            }

            label151:
            try {
               this.mPendingData = var1;
               break label164;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label151;
            }
         }

         while(true) {
            Throwable var16 = var10000;

            try {
               throw var16;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               continue;
            }
         }
      }

      if (var2) {
         ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
      }
   }

   public void removeObserver(Observer var1) {
      assertMainThread("removeObserver");
      ObserverWrapper var2 = (ObserverWrapper)this.mObservers.remove(var1);
      if (var2 != null) {
         var2.detachObserver();
         var2.activeStateChanged(false);
      }
   }

   public void removeObservers(LifecycleOwner var1) {
      assertMainThread("removeObservers");
      Iterator var3 = this.mObservers.iterator();

      while(var3.hasNext()) {
         Map.Entry var2 = (Map.Entry)var3.next();
         if (((ObserverWrapper)var2.getValue()).isAttachedTo(var1)) {
            this.removeObserver((Observer)var2.getKey());
         }
      }

   }

   protected void setValue(Object var1) {
      assertMainThread("setValue");
      ++this.mVersion;
      this.mData = var1;
      this.dispatchingValue((ObserverWrapper)null);
   }

   private class AlwaysActiveObserver extends ObserverWrapper {
      final LiveData this$0;

      AlwaysActiveObserver(LiveData var1, Observer var2) {
         super(var1, var2);
         this.this$0 = var1;
      }

      boolean shouldBeActive() {
         return true;
      }
   }

   class LifecycleBoundObserver extends ObserverWrapper implements LifecycleEventObserver {
      final LifecycleOwner mOwner;
      final LiveData this$0;

      LifecycleBoundObserver(LiveData var1, LifecycleOwner var2, Observer var3) {
         super(var1, var3);
         this.this$0 = var1;
         this.mOwner = var2;
      }

      void detachObserver() {
         this.mOwner.getLifecycle().removeObserver(this);
      }

      boolean isAttachedTo(LifecycleOwner var1) {
         boolean var2;
         if (this.mOwner == var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
         Lifecycle.State var4 = this.mOwner.getLifecycle().getCurrentState();
         if (var4 == Lifecycle.State.DESTROYED) {
            this.this$0.removeObserver(this.mObserver);
         } else {
            Lifecycle.State var3;
            for(Lifecycle.State var5 = null; var5 != var4; var4 = var3) {
               this.activeStateChanged(this.shouldBeActive());
               var3 = this.mOwner.getLifecycle().getCurrentState();
               var5 = var4;
            }

         }
      }

      boolean shouldBeActive() {
         return this.mOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
      }
   }

   private abstract class ObserverWrapper {
      boolean mActive;
      int mLastVersion;
      final Observer mObserver;
      final LiveData this$0;

      ObserverWrapper(LiveData var1, Observer var2) {
         this.this$0 = var1;
         this.mLastVersion = -1;
         this.mObserver = var2;
      }

      void activeStateChanged(boolean var1) {
         if (var1 != this.mActive) {
            this.mActive = var1;
            LiveData var3 = this.this$0;
            byte var2;
            if (var1) {
               var2 = 1;
            } else {
               var2 = -1;
            }

            var3.changeActiveCounter(var2);
            if (this.mActive) {
               this.this$0.dispatchingValue(this);
            }

         }
      }

      void detachObserver() {
      }

      boolean isAttachedTo(LifecycleOwner var1) {
         return false;
      }

      abstract boolean shouldBeActive();
   }
}
