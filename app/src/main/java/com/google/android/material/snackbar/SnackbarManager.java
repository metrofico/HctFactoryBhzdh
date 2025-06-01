package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

class SnackbarManager {
   private static final int LONG_DURATION_MS = 2750;
   static final int MSG_TIMEOUT = 0;
   private static final int SHORT_DURATION_MS = 1500;
   private static SnackbarManager snackbarManager;
   private SnackbarRecord currentSnackbar;
   private final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback(this) {
      final SnackbarManager this$0;

      {
         this.this$0 = var1;
      }

      public boolean handleMessage(Message var1) {
         if (var1.what != 0) {
            return false;
         } else {
            this.this$0.handleTimeout((SnackbarRecord)var1.obj);
            return true;
         }
      }
   });
   private final Object lock = new Object();
   private SnackbarRecord nextSnackbar;

   private SnackbarManager() {
   }

   private boolean cancelSnackbarLocked(SnackbarRecord var1, int var2) {
      Callback var3 = (Callback)var1.callback.get();
      if (var3 != null) {
         this.handler.removeCallbacksAndMessages(var1);
         var3.dismiss(var2);
         return true;
      } else {
         return false;
      }
   }

   static SnackbarManager getInstance() {
      if (snackbarManager == null) {
         snackbarManager = new SnackbarManager();
      }

      return snackbarManager;
   }

   private boolean isCurrentSnackbarLocked(Callback var1) {
      SnackbarRecord var3 = this.currentSnackbar;
      boolean var2;
      if (var3 != null && var3.isSnackbar(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private boolean isNextSnackbarLocked(Callback var1) {
      SnackbarRecord var3 = this.nextSnackbar;
      boolean var2;
      if (var3 != null && var3.isSnackbar(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private void scheduleTimeoutLocked(SnackbarRecord var1) {
      if (var1.duration != -2) {
         int var2 = 2750;
         if (var1.duration > 0) {
            var2 = var1.duration;
         } else if (var1.duration == -1) {
            var2 = 1500;
         }

         this.handler.removeCallbacksAndMessages(var1);
         Handler var3 = this.handler;
         var3.sendMessageDelayed(Message.obtain(var3, 0, var1), (long)var2);
      }
   }

   private void showNextSnackbarLocked() {
      SnackbarRecord var1 = this.nextSnackbar;
      if (var1 != null) {
         this.currentSnackbar = var1;
         this.nextSnackbar = null;
         Callback var2 = (Callback)var1.callback.get();
         if (var2 != null) {
            var2.show();
         } else {
            this.currentSnackbar = null;
         }
      }

   }

   public void dismiss(Callback var1, int var2) {
      Object var3 = this.lock;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label200: {
         label204: {
            try {
               if (this.isCurrentSnackbarLocked(var1)) {
                  this.cancelSnackbarLocked(this.currentSnackbar, var2);
                  break label204;
               }
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label200;
            }

            try {
               if (this.isNextSnackbarLocked(var1)) {
                  this.cancelSnackbarLocked(this.nextSnackbar, var2);
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label200;
            }
         }

         label190:
         try {
            return;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label190;
         }
      }

      while(true) {
         Throwable var24 = var10000;

         try {
            throw var24;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            continue;
         }
      }
   }

   void handleTimeout(SnackbarRecord var1) {
      Object var2 = this.lock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label183: {
         label182: {
            try {
               if (this.currentSnackbar != var1 && this.nextSnackbar != var1) {
                  break label182;
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label183;
            }

            try {
               this.cancelSnackbarLocked(var1, 2);
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label183;
            }
         }

         label173:
         try {
            return;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            break label173;
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

   public boolean isCurrent(Callback param1) {
      // $FF: Couldn't be decompiled
   }

   public boolean isCurrentOrNext(Callback var1) {
      Object var3 = this.lock;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label151: {
         boolean var2;
         label150: {
            label149: {
               try {
                  if (!this.isCurrentSnackbarLocked(var1) && !this.isNextSnackbarLocked(var1)) {
                     break label149;
                  }
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label151;
               }

               var2 = true;
               break label150;
            }

            var2 = false;
         }

         label140:
         try {
            return var2;
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label140;
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

   public void onDismissed(Callback var1) {
      Object var2 = this.lock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label130: {
         try {
            if (this.isCurrentSnackbarLocked(var1)) {
               this.currentSnackbar = null;
               if (this.nextSnackbar != null) {
                  this.showNextSnackbarLocked();
               }
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label130;
         }

         label127:
         try {
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label127;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public void onShown(Callback var1) {
      Object var2 = this.lock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (this.isCurrentSnackbarLocked(var1)) {
               this.scheduleTimeoutLocked(this.currentSnackbar);
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
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public void pauseTimeout(Callback var1) {
      Object var2 = this.lock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label130: {
         try {
            if (this.isCurrentSnackbarLocked(var1) && !this.currentSnackbar.paused) {
               this.currentSnackbar.paused = true;
               this.handler.removeCallbacksAndMessages(this.currentSnackbar);
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label130;
         }

         label127:
         try {
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label127;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public void restoreTimeoutIfPaused(Callback var1) {
      Object var2 = this.lock;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label130: {
         try {
            if (this.isCurrentSnackbarLocked(var1) && this.currentSnackbar.paused) {
               this.currentSnackbar.paused = false;
               this.scheduleTimeoutLocked(this.currentSnackbar);
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label130;
         }

         label127:
         try {
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label127;
         }
      }

      while(true) {
         Throwable var15 = var10000;

         try {
            throw var15;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public void show(int var1, Callback var2) {
      Object var3 = this.lock;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label489: {
         try {
            if (this.isCurrentSnackbarLocked(var2)) {
               this.currentSnackbar.duration = var1;
               this.handler.removeCallbacksAndMessages(this.currentSnackbar);
               this.scheduleTimeoutLocked(this.currentSnackbar);
               return;
            }
         } catch (Throwable var60) {
            var10000 = var60;
            var10001 = false;
            break label489;
         }

         label479: {
            try {
               if (this.isNextSnackbarLocked(var2)) {
                  this.nextSnackbar.duration = var1;
                  break label479;
               }
            } catch (Throwable var59) {
               var10000 = var59;
               var10001 = false;
               break label489;
            }

            try {
               SnackbarRecord var4 = new SnackbarRecord(var1, var2);
               this.nextSnackbar = var4;
            } catch (Throwable var58) {
               var10000 = var58;
               var10001 = false;
               break label489;
            }
         }

         SnackbarRecord var61;
         try {
            var61 = this.currentSnackbar;
         } catch (Throwable var57) {
            var10000 = var57;
            var10001 = false;
            break label489;
         }

         if (var61 != null) {
            try {
               if (this.cancelSnackbarLocked(var61, 4)) {
                  return;
               }
            } catch (Throwable var56) {
               var10000 = var56;
               var10001 = false;
               break label489;
            }
         }

         label463:
         try {
            this.currentSnackbar = null;
            this.showNextSnackbarLocked();
            return;
         } catch (Throwable var55) {
            var10000 = var55;
            var10001 = false;
            break label463;
         }
      }

      while(true) {
         Throwable var62 = var10000;

         try {
            throw var62;
         } catch (Throwable var54) {
            var10000 = var54;
            var10001 = false;
            continue;
         }
      }
   }

   interface Callback {
      void dismiss(int var1);

      void show();
   }

   private static class SnackbarRecord {
      final WeakReference callback;
      int duration;
      boolean paused;

      SnackbarRecord(int var1, Callback var2) {
         this.callback = new WeakReference(var2);
         this.duration = var1;
      }

      boolean isSnackbar(Callback var1) {
         boolean var2;
         if (var1 != null && this.callback.get() == var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }
   }
}
