package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet {
   final ArrayList mAnimators = new ArrayList();
   private long mDuration = -1L;
   private Interpolator mInterpolator;
   private boolean mIsStarted;
   ViewPropertyAnimatorListener mListener;
   private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter(this) {
      private int mProxyEndCount;
      private boolean mProxyStarted;
      final ViewPropertyAnimatorCompatSet this$0;

      {
         this.this$0 = var1;
         this.mProxyStarted = false;
         this.mProxyEndCount = 0;
      }

      public void onAnimationEnd(View var1) {
         int var2 = this.mProxyEndCount + 1;
         this.mProxyEndCount = var2;
         if (var2 == this.this$0.mAnimators.size()) {
            if (this.this$0.mListener != null) {
               this.this$0.mListener.onAnimationEnd((View)null);
            }

            this.onEnd();
         }

      }

      public void onAnimationStart(View var1) {
         if (!this.mProxyStarted) {
            this.mProxyStarted = true;
            if (this.this$0.mListener != null) {
               this.this$0.mListener.onAnimationStart((View)null);
            }

         }
      }

      void onEnd() {
         this.mProxyEndCount = 0;
         this.mProxyStarted = false;
         this.this$0.onAnimationsEnded();
      }
   };

   public void cancel() {
      if (this.mIsStarted) {
         Iterator var1 = this.mAnimators.iterator();

         while(var1.hasNext()) {
            ((ViewPropertyAnimatorCompat)var1.next()).cancel();
         }

         this.mIsStarted = false;
      }
   }

   void onAnimationsEnded() {
      this.mIsStarted = false;
   }

   public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat var1) {
      if (!this.mIsStarted) {
         this.mAnimators.add(var1);
      }

      return this;
   }

   public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat var1, ViewPropertyAnimatorCompat var2) {
      this.mAnimators.add(var1);
      var2.setStartDelay(var1.getDuration());
      this.mAnimators.add(var2);
      return this;
   }

   public ViewPropertyAnimatorCompatSet setDuration(long var1) {
      if (!this.mIsStarted) {
         this.mDuration = var1;
      }

      return this;
   }

   public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator var1) {
      if (!this.mIsStarted) {
         this.mInterpolator = var1;
      }

      return this;
   }

   public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener var1) {
      if (!this.mIsStarted) {
         this.mListener = var1;
      }

      return this;
   }

   public void start() {
      if (!this.mIsStarted) {
         ViewPropertyAnimatorCompat var5;
         for(Iterator var3 = this.mAnimators.iterator(); var3.hasNext(); var5.start()) {
            var5 = (ViewPropertyAnimatorCompat)var3.next();
            long var1 = this.mDuration;
            if (var1 >= 0L) {
               var5.setDuration(var1);
            }

            Interpolator var4 = this.mInterpolator;
            if (var4 != null) {
               var5.setInterpolator(var4);
            }

            if (this.mListener != null) {
               var5.setListener(this.mProxyListener);
            }
         }

         this.mIsStarted = true;
      }
   }
}
