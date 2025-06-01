package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

public class AsyncListDiffer {
   private static final Executor sMainThreadExecutor = new MainThreadExecutor();
   final AsyncDifferConfig mConfig;
   private List mList;
   private final List mListeners;
   Executor mMainThreadExecutor;
   int mMaxScheduledGeneration;
   private List mReadOnlyList;
   private final ListUpdateCallback mUpdateCallback;

   public AsyncListDiffer(ListUpdateCallback var1, AsyncDifferConfig var2) {
      this.mListeners = new CopyOnWriteArrayList();
      this.mReadOnlyList = Collections.emptyList();
      this.mUpdateCallback = var1;
      this.mConfig = var2;
      if (var2.getMainThreadExecutor() != null) {
         this.mMainThreadExecutor = var2.getMainThreadExecutor();
      } else {
         this.mMainThreadExecutor = sMainThreadExecutor;
      }

   }

   public AsyncListDiffer(RecyclerView.Adapter var1, DiffUtil.ItemCallback var2) {
      this((ListUpdateCallback)(new AdapterListUpdateCallback(var1)), (AsyncDifferConfig)(new AsyncDifferConfig.Builder(var2)).build());
   }

   private void onCurrentListChanged(List var1, Runnable var2) {
      Iterator var3 = this.mListeners.iterator();

      while(var3.hasNext()) {
         ((ListListener)var3.next()).onCurrentListChanged(var1, this.mReadOnlyList);
      }

      if (var2 != null) {
         var2.run();
      }

   }

   public void addListListener(ListListener var1) {
      this.mListeners.add(var1);
   }

   public List getCurrentList() {
      return this.mReadOnlyList;
   }

   void latchList(List var1, DiffUtil.DiffResult var2, Runnable var3) {
      List var4 = this.mReadOnlyList;
      this.mList = var1;
      this.mReadOnlyList = Collections.unmodifiableList(var1);
      var2.dispatchUpdatesTo(this.mUpdateCallback);
      this.onCurrentListChanged(var4, var3);
   }

   public void removeListListener(ListListener var1) {
      this.mListeners.remove(var1);
   }

   public void submitList(List var1) {
      this.submitList(var1, (Runnable)null);
   }

   public void submitList(List var1, Runnable var2) {
      int var3 = this.mMaxScheduledGeneration + 1;
      this.mMaxScheduledGeneration = var3;
      List var5 = this.mList;
      if (var1 == var5) {
         if (var2 != null) {
            var2.run();
         }

      } else {
         List var4 = this.mReadOnlyList;
         if (var1 == null) {
            var3 = var5.size();
            this.mList = null;
            this.mReadOnlyList = Collections.emptyList();
            this.mUpdateCallback.onRemoved(0, var3);
            this.onCurrentListChanged(var4, var2);
         } else if (var5 == null) {
            this.mList = var1;
            this.mReadOnlyList = Collections.unmodifiableList(var1);
            this.mUpdateCallback.onInserted(0, var1.size());
            this.onCurrentListChanged(var4, var2);
         } else {
            this.mConfig.getBackgroundThreadExecutor().execute(new Runnable(this, var5, var1, var3, var2) {
               final AsyncListDiffer this$0;
               final Runnable val$commitCallback;
               final List val$newList;
               final List val$oldList;
               final int val$runGeneration;

               {
                  this.this$0 = var1;
                  this.val$oldList = var2;
                  this.val$newList = var3;
                  this.val$runGeneration = var4;
                  this.val$commitCallback = var5;
               }

               public void run() {
                  DiffUtil.DiffResult var1 = DiffUtil.calculateDiff(new DiffUtil.Callback(this) {
                     final <undefinedtype> this$1;

                     {
                        this.this$1 = var1;
                     }

                     public boolean areContentsTheSame(int var1, int var2) {
                        Object var3 = this.this$1.val$oldList.get(var1);
                        Object var4 = this.this$1.val$newList.get(var2);
                        if (var3 != null && var4 != null) {
                           return this.this$1.this$0.mConfig.getDiffCallback().areContentsTheSame(var3, var4);
                        } else if (var3 == null && var4 == null) {
                           return true;
                        } else {
                           throw new AssertionError();
                        }
                     }

                     public boolean areItemsTheSame(int var1, int var2) {
                        Object var4 = this.this$1.val$oldList.get(var1);
                        Object var5 = this.this$1.val$newList.get(var2);
                        if (var4 != null && var5 != null) {
                           return this.this$1.this$0.mConfig.getDiffCallback().areItemsTheSame(var4, var5);
                        } else {
                           boolean var3;
                           if (var4 == null && var5 == null) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           return var3;
                        }
                     }

                     public Object getChangePayload(int var1, int var2) {
                        Object var4 = this.this$1.val$oldList.get(var1);
                        Object var3 = this.this$1.val$newList.get(var2);
                        if (var4 != null && var3 != null) {
                           return this.this$1.this$0.mConfig.getDiffCallback().getChangePayload(var4, var3);
                        } else {
                           throw new AssertionError();
                        }
                     }

                     public int getNewListSize() {
                        return this.this$1.val$newList.size();
                     }

                     public int getOldListSize() {
                        return this.this$1.val$oldList.size();
                     }
                  });
                  this.this$0.mMainThreadExecutor.execute(new Runnable(this, var1) {
                     final <undefinedtype> this$1;
                     final DiffUtil.DiffResult val$result;

                     {
                        this.this$1 = var1;
                        this.val$result = var2;
                     }

                     public void run() {
                        if (this.this$1.this$0.mMaxScheduledGeneration == this.this$1.val$runGeneration) {
                           this.this$1.this$0.latchList(this.this$1.val$newList, this.val$result, this.this$1.val$commitCallback);
                        }

                     }
                  });
               }
            });
         }
      }
   }

   public interface ListListener {
      void onCurrentListChanged(List var1, List var2);
   }

   private static class MainThreadExecutor implements Executor {
      final Handler mHandler = new Handler(Looper.getMainLooper());

      MainThreadExecutor() {
      }

      public void execute(Runnable var1) {
         this.mHandler.post(var1);
      }
   }
}
