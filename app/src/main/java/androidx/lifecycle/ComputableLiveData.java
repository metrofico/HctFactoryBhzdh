package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ComputableLiveData {
   final AtomicBoolean mComputing;
   final Executor mExecutor;
   final AtomicBoolean mInvalid;
   final Runnable mInvalidationRunnable;
   final LiveData mLiveData;
   final Runnable mRefreshRunnable;

   public ComputableLiveData() {
      this(ArchTaskExecutor.getIOThreadExecutor());
   }

   public ComputableLiveData(Executor var1) {
      this.mInvalid = new AtomicBoolean(true);
      this.mComputing = new AtomicBoolean(false);
      this.mRefreshRunnable = new Runnable(this) {
         final ComputableLiveData this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            boolean var1;
            do {
               AtomicBoolean var2 = this.this$0.mComputing;
               var1 = false;
               if (var2.compareAndSet(false, true)) {
                  Object var9 = null;
                  var1 = false;

                  while(true) {
                     label127: {
                        Throwable var10000;
                        label135: {
                           boolean var10001;
                           try {
                              if (this.this$0.mInvalid.compareAndSet(true, false)) {
                                 var9 = this.this$0.compute();
                                 break label127;
                              }
                           } catch (Throwable var8) {
                              var10000 = var8;
                              var10001 = false;
                              break label135;
                           }

                           if (var1) {
                              try {
                                 this.this$0.mLiveData.postValue(var9);
                              } catch (Throwable var7) {
                                 var10000 = var7;
                                 var10001 = false;
                                 break label135;
                              }
                           }

                           this.this$0.mComputing.set(false);
                           break;
                        }

                        Throwable var10 = var10000;
                        this.this$0.mComputing.set(false);
                        throw var10;
                     }

                     var1 = true;
                  }
               }
            } while(var1 && this.this$0.mInvalid.get());

         }
      };
      this.mInvalidationRunnable = new Runnable(this) {
         final ComputableLiveData this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            boolean var1 = this.this$0.mLiveData.hasActiveObservers();
            if (this.this$0.mInvalid.compareAndSet(false, true) && var1) {
               this.this$0.mExecutor.execute(this.this$0.mRefreshRunnable);
            }

         }
      };
      this.mExecutor = var1;
      this.mLiveData = new LiveData(this) {
         final ComputableLiveData this$0;

         {
            this.this$0 = var1;
         }

         protected void onActive() {
            this.this$0.mExecutor.execute(this.this$0.mRefreshRunnable);
         }
      };
   }

   protected abstract Object compute();

   public LiveData getLiveData() {
      return this.mLiveData;
   }

   public void invalidate() {
      ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
   }
}
