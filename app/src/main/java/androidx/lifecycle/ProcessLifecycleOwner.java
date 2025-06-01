package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Build.VERSION;

public class ProcessLifecycleOwner implements LifecycleOwner {
   static final long TIMEOUT_MS = 700L;
   private static final ProcessLifecycleOwner sInstance = new ProcessLifecycleOwner();
   private Runnable mDelayedPauseRunnable = new Runnable(this) {
      final ProcessLifecycleOwner this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.dispatchPauseIfNeeded();
         this.this$0.dispatchStopIfNeeded();
      }
   };
   private Handler mHandler;
   ReportFragment.ActivityInitializationListener mInitializationListener = new ReportFragment.ActivityInitializationListener(this) {
      final ProcessLifecycleOwner this$0;

      {
         this.this$0 = var1;
      }

      public void onCreate() {
      }

      public void onResume() {
         this.this$0.activityResumed();
      }

      public void onStart() {
         this.this$0.activityStarted();
      }
   };
   private boolean mPauseSent = true;
   private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
   private int mResumedCounter = 0;
   private int mStartedCounter = 0;
   private boolean mStopSent = true;

   private ProcessLifecycleOwner() {
   }

   public static LifecycleOwner get() {
      return sInstance;
   }

   static void init(Context var0) {
      sInstance.attach(var0);
   }

   void activityPaused() {
      int var1 = this.mResumedCounter - 1;
      this.mResumedCounter = var1;
      if (var1 == 0) {
         this.mHandler.postDelayed(this.mDelayedPauseRunnable, 700L);
      }

   }

   void activityResumed() {
      int var1 = this.mResumedCounter + 1;
      this.mResumedCounter = var1;
      if (var1 == 1) {
         if (this.mPauseSent) {
            this.mRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            this.mPauseSent = false;
         } else {
            this.mHandler.removeCallbacks(this.mDelayedPauseRunnable);
         }
      }

   }

   void activityStarted() {
      int var1 = this.mStartedCounter + 1;
      this.mStartedCounter = var1;
      if (var1 == 1 && this.mStopSent) {
         this.mRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
         this.mStopSent = false;
      }

   }

   void activityStopped() {
      --this.mStartedCounter;
      this.dispatchStopIfNeeded();
   }

   void attach(Context var1) {
      this.mHandler = new Handler();
      this.mRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
      ((Application)var1.getApplicationContext()).registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks(this) {
         final ProcessLifecycleOwner this$0;

         {
            this.this$0 = var1;
         }

         public void onActivityCreated(Activity var1, Bundle var2) {
            if (VERSION.SDK_INT < 29) {
               ReportFragment.get(var1).setProcessListener(this.this$0.mInitializationListener);
            }

         }

         public void onActivityPaused(Activity var1) {
            this.this$0.activityPaused();
         }

         public void onActivityPreCreated(Activity var1, Bundle var2) {
            var1.registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onActivityPostResumed(Activity var1) {
                  this.this$1.this$0.activityResumed();
               }

               public void onActivityPostStarted(Activity var1) {
                  this.this$1.this$0.activityStarted();
               }
            });
         }

         public void onActivityStopped(Activity var1) {
            this.this$0.activityStopped();
         }
      });
   }

   void dispatchPauseIfNeeded() {
      if (this.mResumedCounter == 0) {
         this.mPauseSent = true;
         this.mRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
      }

   }

   void dispatchStopIfNeeded() {
      if (this.mStartedCounter == 0 && this.mPauseSent) {
         this.mRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
         this.mStopSent = true;
      }

   }

   public Lifecycle getLifecycle() {
      return this.mRegistry;
   }
}
