package androidx.recyclerview.widget;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class MessageThreadUtil implements ThreadUtil {
   public ThreadUtil.BackgroundCallback getBackgroundProxy(ThreadUtil.BackgroundCallback var1) {
      return new ThreadUtil.BackgroundCallback(this, var1) {
         static final int LOAD_TILE = 3;
         static final int RECYCLE_TILE = 4;
         static final int REFRESH = 1;
         static final int UPDATE_RANGE = 2;
         private Runnable mBackgroundRunnable;
         AtomicBoolean mBackgroundRunning;
         private final Executor mExecutor;
         final MessageQueue mQueue;
         final MessageThreadUtil this$0;
         final ThreadUtil.BackgroundCallback val$callback;

         {
            this.this$0 = var1;
            this.val$callback = var2;
            this.mQueue = new MessageQueue();
            this.mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
            this.mBackgroundRunning = new AtomicBoolean(false);
            this.mBackgroundRunnable = new Runnable(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void run() {
                  while(true) {
                     SyncQueueItem var2 = this.this$1.mQueue.next();
                     if (var2 == null) {
                        this.this$1.mBackgroundRunning.set(false);
                        return;
                     }

                     int var1 = var2.what;
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 != 3) {
                              if (var1 != 4) {
                                 Log.e("ThreadUtil", "Unsupported message, what=" + var2.what);
                              } else {
                                 TileList.Tile var3 = (TileList.Tile)var2.data;
                                 this.this$1.val$callback.recycleTile(var3);
                              }
                           } else {
                              this.this$1.val$callback.loadTile(var2.arg1, var2.arg2);
                           }
                        } else {
                           this.this$1.mQueue.removeMessages(2);
                           this.this$1.mQueue.removeMessages(3);
                           this.this$1.val$callback.updateRange(var2.arg1, var2.arg2, var2.arg3, var2.arg4, var2.arg5);
                        }
                     } else {
                        this.this$1.mQueue.removeMessages(1);
                        this.this$1.val$callback.refresh(var2.arg1);
                     }
                  }
               }
            };
         }

         private void maybeExecuteBackgroundRunnable() {
            if (this.mBackgroundRunning.compareAndSet(false, true)) {
               this.mExecutor.execute(this.mBackgroundRunnable);
            }

         }

         private void sendMessage(SyncQueueItem var1) {
            this.mQueue.sendMessage(var1);
            this.maybeExecuteBackgroundRunnable();
         }

         private void sendMessageAtFrontOfQueue(SyncQueueItem var1) {
            this.mQueue.sendMessageAtFrontOfQueue(var1);
            this.maybeExecuteBackgroundRunnable();
         }

         public void loadTile(int var1, int var2) {
            this.sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(3, var1, var2));
         }

         public void recycleTile(TileList.Tile var1) {
            this.sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(4, 0, var1));
         }

         public void refresh(int var1) {
            this.sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem.obtainMessage(1, var1, (Object)null));
         }

         public void updateRange(int var1, int var2, int var3, int var4, int var5) {
            this.sendMessageAtFrontOfQueue(MessageThreadUtil.SyncQueueItem.obtainMessage(2, var1, var2, var3, var4, var5, (Object)null));
         }
      };
   }

   public ThreadUtil.MainThreadCallback getMainThreadProxy(ThreadUtil.MainThreadCallback var1) {
      return new ThreadUtil.MainThreadCallback(this, var1) {
         static final int ADD_TILE = 2;
         static final int REMOVE_TILE = 3;
         static final int UPDATE_ITEM_COUNT = 1;
         private final Handler mMainThreadHandler;
         private Runnable mMainThreadRunnable;
         final MessageQueue mQueue;
         final MessageThreadUtil this$0;
         final ThreadUtil.MainThreadCallback val$callback;

         {
            this.this$0 = var1;
            this.val$callback = var2;
            this.mQueue = new MessageQueue();
            this.mMainThreadHandler = new Handler(Looper.getMainLooper());
            this.mMainThreadRunnable = new Runnable(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void run() {
                  for(SyncQueueItem var2 = this.this$1.mQueue.next(); var2 != null; var2 = this.this$1.mQueue.next()) {
                     int var1 = var2.what;
                     if (var1 != 1) {
                        if (var1 != 2) {
                           if (var1 != 3) {
                              Log.e("ThreadUtil", "Unsupported message, what=" + var2.what);
                           } else {
                              this.this$1.val$callback.removeTile(var2.arg1, var2.arg2);
                           }
                        } else {
                           TileList.Tile var3 = (TileList.Tile)var2.data;
                           this.this$1.val$callback.addTile(var2.arg1, var3);
                        }
                     } else {
                        this.this$1.val$callback.updateItemCount(var2.arg1, var2.arg2);
                     }
                  }

               }
            };
         }

         private void sendMessage(SyncQueueItem var1) {
            this.mQueue.sendMessage(var1);
            this.mMainThreadHandler.post(this.mMainThreadRunnable);
         }

         public void addTile(int var1, TileList.Tile var2) {
            this.sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(2, var1, var2));
         }

         public void removeTile(int var1, int var2) {
            this.sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(3, var1, var2));
         }

         public void updateItemCount(int var1, int var2) {
            this.sendMessage(MessageThreadUtil.SyncQueueItem.obtainMessage(1, var1, var2));
         }
      };
   }

   static class MessageQueue {
      private SyncQueueItem mRoot;

      SyncQueueItem next() {
         synchronized(this){}

         Throwable var10000;
         label78: {
            SyncQueueItem var1;
            boolean var10001;
            try {
               var1 = this.mRoot;
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label78;
            }

            if (var1 == null) {
               return null;
            }

            try {
               this.mRoot = var1.next;
            } catch (Throwable var6) {
               var10000 = var6;
               var10001 = false;
               break label78;
            }

            return var1;
         }

         Throwable var8 = var10000;
         throw var8;
      }

      void removeMessages(int var1) {
         synchronized(this){}

         while(true) {
            Throwable var10000;
            label366: {
               SyncQueueItem var2;
               boolean var10001;
               try {
                  var2 = this.mRoot;
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label366;
               }

               if (var2 != null) {
                  try {
                     if (var2.what == var1) {
                        var2 = this.mRoot;
                        this.mRoot = var2.next;
                        var2.recycle();
                        continue;
                     }
                  } catch (Throwable var33) {
                     var10000 = var33;
                     var10001 = false;
                     break label366;
                  }
               }

               SyncQueueItem var3;
               try {
                  var3 = this.mRoot;
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label366;
               }

               if (var3 == null) {
                  return;
               }

               try {
                  var2 = var3.next;
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label366;
               }

               while(true) {
                  if (var2 == null) {
                     return;
                  }

                  SyncQueueItem var4;
                  label341: {
                     try {
                        var4 = var2.next;
                        if (var2.what == var1) {
                           var3.next = var4;
                           var2.recycle();
                           break label341;
                        }
                     } catch (Throwable var30) {
                        var10000 = var30;
                        var10001 = false;
                        break;
                     }

                     var3 = var2;
                  }

                  var2 = var4;
               }
            }

            Throwable var35 = var10000;
            throw var35;
         }
      }

      void sendMessage(SyncQueueItem var1) {
         synchronized(this){}

         Throwable var10000;
         label215: {
            boolean var10001;
            SyncQueueItem var3;
            try {
               var3 = this.mRoot;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label215;
            }

            SyncQueueItem var2 = var3;
            if (var3 == null) {
               label195: {
                  try {
                     this.mRoot = var1;
                  } catch (Throwable var20) {
                     var10000 = var20;
                     var10001 = false;
                     break label195;
                  }

                  return;
               }
            } else {
               label216: {
                  while(true) {
                     try {
                        if (var2.next == null) {
                           break;
                        }

                        var2 = var2.next;
                     } catch (Throwable var22) {
                        var10000 = var22;
                        var10001 = false;
                        break label216;
                     }
                  }

                  try {
                     var2.next = var1;
                  } catch (Throwable var21) {
                     var10000 = var21;
                     var10001 = false;
                     break label216;
                  }

                  return;
               }
            }
         }

         Throwable var24 = var10000;
         throw var24;
      }

      void sendMessageAtFrontOfQueue(SyncQueueItem var1) {
         synchronized(this){}

         try {
            var1.next = this.mRoot;
            this.mRoot = var1;
         } finally {
            ;
         }

      }
   }

   static class SyncQueueItem {
      private static SyncQueueItem sPool;
      private static final Object sPoolLock = new Object();
      public int arg1;
      public int arg2;
      public int arg3;
      public int arg4;
      public int arg5;
      public Object data;
      SyncQueueItem next;
      public int what;

      static SyncQueueItem obtainMessage(int var0, int var1, int var2) {
         return obtainMessage(var0, var1, var2, 0, 0, 0, (Object)null);
      }

      static SyncQueueItem obtainMessage(int var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
         Object var8 = sPoolLock;
         synchronized(var8){}

         Throwable var10000;
         boolean var10001;
         label241: {
            SyncQueueItem var7;
            try {
               var7 = sPool;
            } catch (Throwable var38) {
               var10000 = var38;
               var10001 = false;
               break label241;
            }

            if (var7 == null) {
               try {
                  var7 = new SyncQueueItem();
               } catch (Throwable var37) {
                  var10000 = var37;
                  var10001 = false;
                  break label241;
               }
            } else {
               try {
                  sPool = var7.next;
                  var7.next = null;
               } catch (Throwable var36) {
                  var10000 = var36;
                  var10001 = false;
                  break label241;
               }
            }

            label228:
            try {
               var7.what = var0;
               var7.arg1 = var1;
               var7.arg2 = var2;
               var7.arg3 = var3;
               var7.arg4 = var4;
               var7.arg5 = var5;
               var7.data = var6;
               return var7;
            } catch (Throwable var35) {
               var10000 = var35;
               var10001 = false;
               break label228;
            }
         }

         while(true) {
            Throwable var39 = var10000;

            try {
               throw var39;
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               continue;
            }
         }
      }

      static SyncQueueItem obtainMessage(int var0, int var1, Object var2) {
         return obtainMessage(var0, var1, 0, 0, 0, 0, var2);
      }

      void recycle() {
         this.next = null;
         this.arg5 = 0;
         this.arg4 = 0;
         this.arg3 = 0;
         this.arg2 = 0;
         this.arg1 = 0;
         this.what = 0;
         this.data = null;
         Object var1 = sPoolLock;
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label176: {
            SyncQueueItem var2;
            try {
               var2 = sPool;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label176;
            }

            if (var2 != null) {
               try {
                  this.next = var2;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label176;
               }
            }

            label165:
            try {
               sPool = this;
               return;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label165;
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
   }
}
