package androidx.core.view;

import android.os.CancellationSignal;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.collection.SimpleArrayMap;

public final class WindowInsetsControllerCompat {
   public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;
   public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
   public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
   private final Impl mImpl;

   public WindowInsetsControllerCompat(Window var1, View var2) {
      if (VERSION.SDK_INT >= 30) {
         this.mImpl = new Impl30(var1, this);
      } else if (VERSION.SDK_INT >= 26) {
         this.mImpl = new Impl26(var1, var2);
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new Impl23(var1, var2);
      } else if (VERSION.SDK_INT >= 20) {
         this.mImpl = new Impl20(var1, var2);
      } else {
         this.mImpl = new Impl();
      }

   }

   private WindowInsetsControllerCompat(WindowInsetsController var1) {
      if (VERSION.SDK_INT >= 30) {
         this.mImpl = new Impl30(var1, this);
      } else {
         this.mImpl = new Impl();
      }

   }

   public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(WindowInsetsController var0) {
      return new WindowInsetsControllerCompat(var0);
   }

   public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
      this.mImpl.addOnControllableInsetsChangedListener(var1);
   }

   public void controlWindowInsetsAnimation(int var1, long var2, Interpolator var4, CancellationSignal var5, WindowInsetsAnimationControlListenerCompat var6) {
      this.mImpl.controlWindowInsetsAnimation(var1, var2, var4, var5, var6);
   }

   public int getSystemBarsBehavior() {
      return this.mImpl.getSystemBarsBehavior();
   }

   public void hide(int var1) {
      this.mImpl.hide(var1);
   }

   public boolean isAppearanceLightNavigationBars() {
      return this.mImpl.isAppearanceLightNavigationBars();
   }

   public boolean isAppearanceLightStatusBars() {
      return this.mImpl.isAppearanceLightStatusBars();
   }

   public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
      this.mImpl.removeOnControllableInsetsChangedListener(var1);
   }

   public void setAppearanceLightNavigationBars(boolean var1) {
      this.mImpl.setAppearanceLightNavigationBars(var1);
   }

   public void setAppearanceLightStatusBars(boolean var1) {
      this.mImpl.setAppearanceLightStatusBars(var1);
   }

   public void setSystemBarsBehavior(int var1) {
      this.mImpl.setSystemBarsBehavior(var1);
   }

   public void show(int var1) {
      this.mImpl.show(var1);
   }

   private static class Impl {
      Impl() {
      }

      void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
      }

      void controlWindowInsetsAnimation(int var1, long var2, Interpolator var4, CancellationSignal var5, WindowInsetsAnimationControlListenerCompat var6) {
      }

      int getSystemBarsBehavior() {
         return 0;
      }

      void hide(int var1) {
      }

      public boolean isAppearanceLightNavigationBars() {
         return false;
      }

      public boolean isAppearanceLightStatusBars() {
         return false;
      }

      void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
      }

      public void setAppearanceLightNavigationBars(boolean var1) {
      }

      public void setAppearanceLightStatusBars(boolean var1) {
      }

      void setSystemBarsBehavior(int var1) {
      }

      void show(int var1) {
      }
   }

   private static class Impl20 extends Impl {
      private final View mView;
      protected final Window mWindow;

      Impl20(Window var1, View var2) {
         this.mWindow = var1;
         this.mView = var2;
      }

      private void hideForType(int var1) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 8) {
                  ((InputMethodManager)this.mWindow.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mWindow.getDecorView().getWindowToken(), 0);
               }

            } else {
               this.setSystemUiFlag(2);
            }
         } else {
            this.setSystemUiFlag(4);
         }
      }

      private void showForType(int var1) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.unsetSystemUiFlag(2);
            } else {
               if (var1 == 8) {
                  View var2 = this.mView;
                  if (var2 == null || !var2.isInEditMode() && !var2.onCheckIsTextEditor()) {
                     var2 = this.mWindow.getCurrentFocus();
                  } else {
                     var2.requestFocus();
                  }

                  View var3 = var2;
                  if (var2 == null) {
                     var3 = this.mWindow.findViewById(16908290);
                  }

                  if (var3 != null && var3.hasWindowFocus()) {
                     var3.post(new Runnable(this, var3) {
                        final Impl20 this$0;
                        final View val$finalView;

                        {
                           this.this$0 = var1;
                           this.val$finalView = var2;
                        }

                        public void run() {
                           ((InputMethodManager)this.val$finalView.getContext().getSystemService("input_method")).showSoftInput(this.val$finalView, 0);
                        }
                     });
                  }
               }

            }
         } else {
            this.unsetSystemUiFlag(4);
            this.unsetWindowFlag(1024);
         }
      }

      void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
      }

      void controlWindowInsetsAnimation(int var1, long var2, Interpolator var4, CancellationSignal var5, WindowInsetsAnimationControlListenerCompat var6) {
      }

      int getSystemBarsBehavior() {
         return 0;
      }

      void hide(int var1) {
         for(int var2 = 1; var2 <= 256; var2 <<= 1) {
            if ((var1 & var2) != 0) {
               this.hideForType(var2);
            }
         }

      }

      void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
      }

      void setSystemBarsBehavior(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.unsetSystemUiFlag(2048);
                  this.setSystemUiFlag(4096);
               }
            } else {
               this.unsetSystemUiFlag(4096);
               this.setSystemUiFlag(2048);
            }
         } else {
            this.unsetSystemUiFlag(6144);
         }

      }

      protected void setSystemUiFlag(int var1) {
         View var2 = this.mWindow.getDecorView();
         var2.setSystemUiVisibility(var1 | var2.getSystemUiVisibility());
      }

      protected void setWindowFlag(int var1) {
         this.mWindow.addFlags(var1);
      }

      void show(int var1) {
         for(int var2 = 1; var2 <= 256; var2 <<= 1) {
            if ((var1 & var2) != 0) {
               this.showForType(var2);
            }
         }

      }

      protected void unsetSystemUiFlag(int var1) {
         View var2 = this.mWindow.getDecorView();
         var2.setSystemUiVisibility(~var1 & var2.getSystemUiVisibility());
      }

      protected void unsetWindowFlag(int var1) {
         this.mWindow.clearFlags(var1);
      }
   }

   private static class Impl23 extends Impl20 {
      Impl23(Window var1, View var2) {
         super(var1, var2);
      }

      public boolean isAppearanceLightStatusBars() {
         boolean var1;
         if ((this.mWindow.getDecorView().getSystemUiVisibility() & 8192) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void setAppearanceLightStatusBars(boolean var1) {
         if (var1) {
            this.unsetWindowFlag(67108864);
            this.setWindowFlag(Integer.MIN_VALUE);
            this.setSystemUiFlag(8192);
         } else {
            this.unsetSystemUiFlag(8192);
         }

      }
   }

   private static class Impl26 extends Impl23 {
      Impl26(Window var1, View var2) {
         super(var1, var2);
      }

      public boolean isAppearanceLightNavigationBars() {
         boolean var1;
         if ((this.mWindow.getDecorView().getSystemUiVisibility() & 16) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void setAppearanceLightNavigationBars(boolean var1) {
         if (var1) {
            this.unsetWindowFlag(134217728);
            this.setWindowFlag(Integer.MIN_VALUE);
            this.setSystemUiFlag(16);
         } else {
            this.unsetSystemUiFlag(16);
         }

      }
   }

   private static class Impl30 extends Impl {
      final WindowInsetsControllerCompat mCompatController;
      final WindowInsetsController mInsetsController;
      private final SimpleArrayMap mListeners;
      protected Window mWindow;

      Impl30(Window var1, WindowInsetsControllerCompat var2) {
         this(var1.getInsetsController(), var2);
         this.mWindow = var1;
      }

      Impl30(WindowInsetsController var1, WindowInsetsControllerCompat var2) {
         this.mListeners = new SimpleArrayMap();
         this.mInsetsController = var1;
         this.mCompatController = var2;
      }

      void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
         if (!this.mListeners.containsKey(var1)) {
            WindowInsetsController.OnControllableInsetsChangedListener var2 = new WindowInsetsController.OnControllableInsetsChangedListener(this, var1) {
               final Impl30 this$0;
               final OnControllableInsetsChangedListener val$listener;

               {
                  this.this$0 = var1;
                  this.val$listener = var2;
               }

               public void onControllableInsetsChanged(WindowInsetsController var1, int var2) {
                  if (this.this$0.mInsetsController == var1) {
                     this.val$listener.onControllableInsetsChanged(this.this$0.mCompatController, var2);
                  }

               }
            };
            this.mListeners.put(var1, var2);
            this.mInsetsController.addOnControllableInsetsChangedListener(var2);
         }
      }

      void controlWindowInsetsAnimation(int var1, long var2, Interpolator var4, CancellationSignal var5, WindowInsetsAnimationControlListenerCompat var6) {
         WindowInsetsAnimationControlListener var7 = new WindowInsetsAnimationControlListener(this, var6) {
            private WindowInsetsAnimationControllerCompat mCompatAnimController;
            final Impl30 this$0;
            final WindowInsetsAnimationControlListenerCompat val$listener;

            {
               this.this$0 = var1;
               this.val$listener = var2;
               this.mCompatAnimController = null;
            }

            public void onCancelled(WindowInsetsAnimationController var1) {
               WindowInsetsAnimationControlListenerCompat var2 = this.val$listener;
               WindowInsetsAnimationControllerCompat var3;
               if (var1 == null) {
                  var3 = null;
               } else {
                  var3 = this.mCompatAnimController;
               }

               var2.onCancelled(var3);
            }

            public void onFinished(WindowInsetsAnimationController var1) {
               this.val$listener.onFinished(this.mCompatAnimController);
            }

            public void onReady(WindowInsetsAnimationController var1, int var2) {
               WindowInsetsAnimationControllerCompat var3 = new WindowInsetsAnimationControllerCompat(var1);
               this.mCompatAnimController = var3;
               this.val$listener.onReady(var3, var2);
            }
         };
         this.mInsetsController.controlWindowInsetsAnimation(var1, var2, var4, var5, var7);
      }

      int getSystemBarsBehavior() {
         return this.mInsetsController.getSystemBarsBehavior();
      }

      void hide(int var1) {
         this.mInsetsController.hide(var1);
      }

      public boolean isAppearanceLightNavigationBars() {
         boolean var1;
         if ((this.mInsetsController.getSystemBarsAppearance() & 16) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isAppearanceLightStatusBars() {
         boolean var1;
         if ((this.mInsetsController.getSystemBarsAppearance() & 8) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener var1) {
         WindowInsetsController.OnControllableInsetsChangedListener var2 = (WindowInsetsController.OnControllableInsetsChangedListener)this.mListeners.remove(var1);
         if (var2 != null) {
            this.mInsetsController.removeOnControllableInsetsChangedListener(var2);
         }

      }

      public void setAppearanceLightNavigationBars(boolean var1) {
         if (var1) {
            this.mInsetsController.setSystemBarsAppearance(16, 16);
         } else {
            this.mInsetsController.setSystemBarsAppearance(0, 16);
         }

      }

      public void setAppearanceLightStatusBars(boolean var1) {
         if (var1) {
            if (this.mWindow != null) {
               this.unsetSystemUiFlag(8192);
            }

            this.mInsetsController.setSystemBarsAppearance(8, 8);
         } else {
            this.mInsetsController.setSystemBarsAppearance(0, 8);
         }

      }

      void setSystemBarsBehavior(int var1) {
         this.mInsetsController.setSystemBarsBehavior(var1);
      }

      void show(int var1) {
         this.mInsetsController.show(var1);
      }

      protected void unsetSystemUiFlag(int var1) {
         View var2 = this.mWindow.getDecorView();
         var2.setSystemUiVisibility(~var1 & var2.getSystemUiVisibility());
      }
   }

   public interface OnControllableInsetsChangedListener {
      void onControllableInsetsChanged(WindowInsetsControllerCompat var1, int var2);
   }
}
