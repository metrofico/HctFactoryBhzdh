package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.HashMap;

@Deprecated
public abstract class JobIntentService extends Service {
   static final boolean DEBUG = false;
   static final String TAG = "JobIntentService";
   static final HashMap sClassWorkEnqueuer = new HashMap();
   static final Object sLock = new Object();
   final ArrayList mCompatQueue;
   WorkEnqueuer mCompatWorkEnqueuer;
   CommandProcessor mCurProcessor;
   boolean mDestroyed = false;
   boolean mInterruptIfStopped = false;
   CompatJobEngine mJobImpl;
   boolean mStopped = false;

   public JobIntentService() {
      if (VERSION.SDK_INT >= 26) {
         this.mCompatQueue = null;
      } else {
         this.mCompatQueue = new ArrayList();
      }

   }

   public static void enqueueWork(Context param0, ComponentName param1, int param2, Intent param3) {
      // $FF: Couldn't be decompiled
   }

   public static void enqueueWork(Context var0, Class var1, int var2, Intent var3) {
      enqueueWork(var0, new ComponentName(var0, var1), var2, var3);
   }

   static WorkEnqueuer getWorkEnqueuer(Context var0, ComponentName var1, boolean var2, int var3) {
      HashMap var6 = sClassWorkEnqueuer;
      WorkEnqueuer var5 = (WorkEnqueuer)var6.get(var1);
      Object var4 = var5;
      if (var5 == null) {
         Object var7;
         if (VERSION.SDK_INT >= 26) {
            if (!var2) {
               throw new IllegalArgumentException("Can't be here without a job id");
            }

            var7 = new JobWorkEnqueuer(var0, var1, var3);
         } else {
            var7 = new CompatWorkEnqueuer(var0, var1);
         }

         var6.put(var1, var7);
         var4 = var7;
      }

      return (WorkEnqueuer)var4;
   }

   GenericWorkItem dequeueWork() {
      CompatJobEngine var1 = this.mJobImpl;
      if (var1 != null) {
         return var1.dequeueWork();
      } else {
         ArrayList var15 = this.mCompatQueue;
         synchronized(var15){}

         Throwable var10000;
         boolean var10001;
         label137: {
            try {
               if (this.mCompatQueue.size() > 0) {
                  GenericWorkItem var16 = (GenericWorkItem)this.mCompatQueue.remove(0);
                  return var16;
               }
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label137;
            }

            label131:
            try {
               return null;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label131;
            }
         }

         while(true) {
            Throwable var2 = var10000;

            try {
               throw var2;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               continue;
            }
         }
      }
   }

   boolean doStopCurrentWork() {
      CommandProcessor var1 = this.mCurProcessor;
      if (var1 != null) {
         var1.cancel(this.mInterruptIfStopped);
      }

      this.mStopped = true;
      return this.onStopCurrentWork();
   }

   void ensureProcessorRunningLocked(boolean var1) {
      if (this.mCurProcessor == null) {
         this.mCurProcessor = new CommandProcessor(this);
         WorkEnqueuer var2 = this.mCompatWorkEnqueuer;
         if (var2 != null && var1) {
            var2.serviceProcessingStarted();
         }

         this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      }

   }

   public boolean isStopped() {
      return this.mStopped;
   }

   public IBinder onBind(Intent var1) {
      CompatJobEngine var2 = this.mJobImpl;
      return var2 != null ? var2.compatGetBinder() : null;
   }

   public void onCreate() {
      super.onCreate();
      if (VERSION.SDK_INT >= 26) {
         this.mJobImpl = new JobServiceEngineImpl(this);
         this.mCompatWorkEnqueuer = null;
      } else {
         this.mJobImpl = null;
         this.mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, this.getClass()), false, 0);
      }

   }

   public void onDestroy() {
      // $FF: Couldn't be decompiled
   }

   protected abstract void onHandleWork(Intent var1);

   public int onStartCommand(Intent var1, int var2, int var3) {
      if (this.mCompatQueue != null) {
         this.mCompatWorkEnqueuer.serviceStartReceived();
         ArrayList var4 = this.mCompatQueue;
         synchronized(var4){}

         Throwable var10000;
         boolean var10001;
         label197: {
            ArrayList var5;
            CompatWorkItem var6;
            try {
               var5 = this.mCompatQueue;
               var6 = new CompatWorkItem;
            } catch (Throwable var26) {
               var10000 = var26;
               var10001 = false;
               break label197;
            }

            if (var1 == null) {
               try {
                  var1 = new Intent();
               } catch (Throwable var25) {
                  var10000 = var25;
                  var10001 = false;
                  break label197;
               }
            }

            label184:
            try {
               var6.<init>(this, var1, var3);
               var5.add(var6);
               this.ensureProcessorRunningLocked(true);
               return 3;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label184;
            }
         }

         while(true) {
            Throwable var27 = var10000;

            try {
               throw var27;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               continue;
            }
         }
      } else {
         return 2;
      }
   }

   public boolean onStopCurrentWork() {
      return true;
   }

   void processorFinished() {
      ArrayList var1 = this.mCompatQueue;
      if (var1 != null) {
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label324: {
            ArrayList var2;
            try {
               this.mCurProcessor = null;
               var2 = this.mCompatQueue;
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label324;
            }

            label328: {
               if (var2 != null) {
                  try {
                     if (var2.size() > 0) {
                        this.ensureProcessorRunningLocked(false);
                        break label328;
                     }
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label324;
                  }
               }

               try {
                  if (!this.mDestroyed) {
                     this.mCompatWorkEnqueuer.serviceProcessingFinished();
                  }
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label324;
               }
            }

            label303:
            try {
               return;
            } catch (Throwable var29) {
               var10000 = var29;
               var10001 = false;
               break label303;
            }
         }

         while(true) {
            Throwable var33 = var10000;

            try {
               throw var33;
            } catch (Throwable var28) {
               var10000 = var28;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public void setInterruptIfStopped(boolean var1) {
      this.mInterruptIfStopped = var1;
   }

   final class CommandProcessor extends AsyncTask {
      final JobIntentService this$0;

      CommandProcessor(JobIntentService var1) {
         this.this$0 = var1;
      }

      protected Void doInBackground(Void... var1) {
         while(true) {
            GenericWorkItem var2 = this.this$0.dequeueWork();
            if (var2 == null) {
               return null;
            }

            this.this$0.onHandleWork(var2.getIntent());
            var2.complete();
         }
      }

      protected void onCancelled(Void var1) {
         this.this$0.processorFinished();
      }

      protected void onPostExecute(Void var1) {
         this.this$0.processorFinished();
      }
   }

   interface CompatJobEngine {
      IBinder compatGetBinder();

      GenericWorkItem dequeueWork();
   }

   static final class CompatWorkEnqueuer extends WorkEnqueuer {
      private final Context mContext;
      private final PowerManager.WakeLock mLaunchWakeLock;
      boolean mLaunchingService;
      private final PowerManager.WakeLock mRunWakeLock;
      boolean mServiceProcessing;

      CompatWorkEnqueuer(Context var1, ComponentName var2) {
         super(var2);
         this.mContext = var1.getApplicationContext();
         PowerManager var3 = (PowerManager)var1.getSystemService("power");
         PowerManager.WakeLock var4 = var3.newWakeLock(1, var2.getClassName() + ":launch");
         this.mLaunchWakeLock = var4;
         var4.setReferenceCounted(false);
         var4 = var3.newWakeLock(1, var2.getClassName() + ":run");
         this.mRunWakeLock = var4;
         var4.setReferenceCounted(false);
      }

      void enqueueWork(Intent var1) {
         var1 = new Intent(var1);
         var1.setComponent(this.mComponentName);
         if (this.mContext.startService(var1) != null) {
            synchronized(this){}

            Throwable var10000;
            boolean var10001;
            label152: {
               try {
                  if (!this.mLaunchingService) {
                     this.mLaunchingService = true;
                     if (!this.mServiceProcessing) {
                        this.mLaunchWakeLock.acquire(60000L);
                     }
                  }
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label152;
               }

               label149:
               try {
                  return;
               } catch (Throwable var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label149;
               }
            }

            while(true) {
               Throwable var14 = var10000;

               try {
                  throw var14;
               } catch (Throwable var11) {
                  var10000 = var11;
                  var10001 = false;
                  continue;
               }
            }
         }
      }

      public void serviceProcessingFinished() {
         synchronized(this){}

         Throwable var10000;
         boolean var10001;
         label197: {
            label196: {
               try {
                  if (!this.mServiceProcessing) {
                     break label196;
                  }

                  if (this.mLaunchingService) {
                     this.mLaunchWakeLock.acquire(60000L);
                  }
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label197;
               }

               try {
                  this.mServiceProcessing = false;
                  this.mRunWakeLock.release();
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  break label197;
               }
            }

            label189:
            try {
               return;
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               break label189;
            }
         }

         while(true) {
            Throwable var1 = var10000;

            try {
               throw var1;
            } catch (Throwable var18) {
               var10000 = var18;
               var10001 = false;
               continue;
            }
         }
      }

      public void serviceProcessingStarted() {
         synchronized(this){}

         Throwable var10000;
         boolean var10001;
         label122: {
            try {
               if (!this.mServiceProcessing) {
                  this.mServiceProcessing = true;
                  this.mRunWakeLock.acquire(600000L);
                  this.mLaunchWakeLock.release();
               }
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label122;
            }

            label119:
            try {
               return;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label119;
            }
         }

         while(true) {
            Throwable var1 = var10000;

            try {
               throw var1;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               continue;
            }
         }
      }

      public void serviceStartReceived() {
         // $FF: Couldn't be decompiled
      }
   }

   final class CompatWorkItem implements GenericWorkItem {
      final Intent mIntent;
      final int mStartId;
      final JobIntentService this$0;

      CompatWorkItem(JobIntentService var1, Intent var2, int var3) {
         this.this$0 = var1;
         this.mIntent = var2;
         this.mStartId = var3;
      }

      public void complete() {
         this.this$0.stopSelf(this.mStartId);
      }

      public Intent getIntent() {
         return this.mIntent;
      }
   }

   interface GenericWorkItem {
      void complete();

      Intent getIntent();
   }

   static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
      static final boolean DEBUG = false;
      static final String TAG = "JobServiceEngineImpl";
      final Object mLock = new Object();
      JobParameters mParams;
      final JobIntentService mService;

      JobServiceEngineImpl(JobIntentService var1) {
         super(var1);
         this.mService = var1;
      }

      public IBinder compatGetBinder() {
         return this.getBinder();
      }

      public GenericWorkItem dequeueWork() {
         Object var1 = this.mLock;
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label197: {
            JobParameters var2;
            try {
               var2 = this.mParams;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label197;
            }

            if (var2 == null) {
               label190:
               try {
                  return null;
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  break label190;
               }
            } else {
               label201: {
                  JobWorkItem var24;
                  try {
                     var24 = var2.dequeueWork();
                  } catch (Throwable var21) {
                     var10000 = var21;
                     var10001 = false;
                     break label201;
                  }

                  if (var24 != null) {
                     var24.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
                     return new WrapperWorkItem(this, var24);
                  }

                  return null;
               }
            }
         }

         while(true) {
            Throwable var23 = var10000;

            try {
               throw var23;
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               continue;
            }
         }
      }

      public boolean onStartJob(JobParameters var1) {
         this.mParams = var1;
         this.mService.ensureProcessorRunningLocked(false);
         return true;
      }

      public boolean onStopJob(JobParameters param1) {
         // $FF: Couldn't be decompiled
      }

      final class WrapperWorkItem implements GenericWorkItem {
         final JobWorkItem mJobWork;
         final JobServiceEngineImpl this$0;

         WrapperWorkItem(JobServiceEngineImpl var1, JobWorkItem var2) {
            this.this$0 = var1;
            this.mJobWork = var2;
         }

         public void complete() {
            Object var1 = this.this$0.mLock;
            synchronized(var1){}

            Throwable var10000;
            boolean var10001;
            label122: {
               try {
                  if (this.this$0.mParams != null) {
                     this.this$0.mParams.completeWork(this.mJobWork);
                  }
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label122;
               }

               label119:
               try {
                  return;
               } catch (Throwable var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label119;
               }
            }

            while(true) {
               Throwable var2 = var10000;

               try {
                  throw var2;
               } catch (Throwable var12) {
                  var10000 = var12;
                  var10001 = false;
                  continue;
               }
            }
         }

         public Intent getIntent() {
            return this.mJobWork.getIntent();
         }
      }
   }

   static final class JobWorkEnqueuer extends WorkEnqueuer {
      private final JobInfo mJobInfo;
      private final JobScheduler mJobScheduler;

      JobWorkEnqueuer(Context var1, ComponentName var2, int var3) {
         super(var2);
         this.ensureJobId(var3);
         this.mJobInfo = (new JobInfo.Builder(var3, this.mComponentName)).setOverrideDeadline(0L).build();
         this.mJobScheduler = (JobScheduler)var1.getApplicationContext().getSystemService("jobscheduler");
      }

      void enqueueWork(Intent var1) {
         this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(var1));
      }
   }

   abstract static class WorkEnqueuer {
      final ComponentName mComponentName;
      boolean mHasJobId;
      int mJobId;

      WorkEnqueuer(ComponentName var1) {
         this.mComponentName = var1;
      }

      abstract void enqueueWork(Intent var1);

      void ensureJobId(int var1) {
         if (!this.mHasJobId) {
            this.mHasJobId = true;
            this.mJobId = var1;
         } else if (this.mJobId != var1) {
            throw new IllegalArgumentException("Given job ID " + var1 + " is different than previous " + this.mJobId);
         }

      }

      public void serviceProcessingFinished() {
      }

      public void serviceProcessingStarted() {
      }

      public void serviceStartReceived() {
      }
   }
}
