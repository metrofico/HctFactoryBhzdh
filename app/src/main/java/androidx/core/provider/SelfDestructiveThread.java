package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;

@Deprecated
public class SelfDestructiveThread {
   private static final int MSG_DESTRUCTION = 0;
   private static final int MSG_INVOKE_RUNNABLE = 1;
   private Handler.Callback mCallback = new Handler.Callback(this) {
      final SelfDestructiveThread this$0;

      {
         this.this$0 = var1;
      }

      public boolean handleMessage(Message var1) {
         int var2 = var1.what;
         if (var2 != 0) {
            if (var2 != 1) {
               return true;
            } else {
               this.this$0.onInvokeRunnable((Runnable)var1.obj);
               return true;
            }
         } else {
            this.this$0.onDestruction();
            return true;
         }
      }
   };
   private final int mDestructAfterMillisec;
   private int mGeneration;
   private Handler mHandler;
   private final Object mLock = new Object();
   private final int mPriority;
   private HandlerThread mThread;
   private final String mThreadName;

   public SelfDestructiveThread(String var1, int var2, int var3) {
      this.mThreadName = var1;
      this.mPriority = var2;
      this.mDestructAfterMillisec = var3;
      this.mGeneration = 0;
   }

   private void post(Runnable var1) {
      Object var2 = this.mLock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label122: {
         Handler var17;
         try {
            if (this.mThread == null) {
               HandlerThread var3 = new HandlerThread(this.mThreadName, this.mPriority);
               this.mThread = var3;
               var3.start();
               var17 = new Handler(this.mThread.getLooper(), this.mCallback);
               this.mHandler = var17;
               ++this.mGeneration;
            }
         } catch (Throwable var15) {
            var10000 = var15;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            this.mHandler.removeMessages(0);
            var17 = this.mHandler;
            var17.sendMessage(var17.obtainMessage(1, var1));
            return;
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label119;
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

   public int getGeneration() {
      // $FF: Couldn't be decompiled
   }

   public boolean isRunning() {
      Object var2 = this.mLock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label134: {
         boolean var1;
         label133: {
            label132: {
               try {
                  if (this.mThread != null) {
                     break label132;
                  }
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label134;
               }

               var1 = false;
               break label133;
            }

            var1 = true;
         }

         label126:
         try {
            return var1;
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label126;
         }
      }

      while(true) {
         Throwable var3 = var10000;

         try {
            throw var3;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            continue;
         }
      }
   }

   void onDestruction() {
      Object var1 = this.mLock;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label123: {
         try {
            if (this.mHandler.hasMessages(1)) {
               return;
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label123;
         }

         label117:
         try {
            this.mThread.quit();
            this.mThread = null;
            this.mHandler = null;
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label117;
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

   void onInvokeRunnable(Runnable param1) {
      // $FF: Couldn't be decompiled
   }

   public void postAndReply(Callable var1, ReplyCallback var2) {
      this.post(new Runnable(this, var1, CalleeHandler.create(), var2) {
         final SelfDestructiveThread this$0;
         final Callable val$callable;
         final Handler val$calleeHandler;
         final ReplyCallback val$reply;

         {
            this.this$0 = var1;
            this.val$callable = var2;
            this.val$calleeHandler = var3;
            this.val$reply = var4;
         }

         public void run() {
            Object var1;
            try {
               var1 = this.val$callable.call();
            } catch (Exception var2) {
               var1 = null;
            }

            this.val$calleeHandler.post(new Runnable(this, var1) {
               final <undefinedtype> this$1;
               final Object val$result;

               {
                  this.this$1 = var1;
                  this.val$result = var2;
               }

               public void run() {
                  this.this$1.val$reply.onReply(this.val$result);
               }
            });
         }
      });
   }

   public Object postAndWait(Callable param1, int param2) throws InterruptedException {
      // $FF: Couldn't be decompiled
   }

   public interface ReplyCallback {
      void onReply(Object var1);
   }
}
