package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar extends ProgressBar {
   private static final int MIN_DELAY_MS = 500;
   private static final int MIN_SHOW_TIME_MS = 500;
   private final Runnable mDelayedHide;
   private final Runnable mDelayedShow;
   boolean mDismissed;
   boolean mPostedHide;
   boolean mPostedShow;
   long mStartTime;

   // $FF: synthetic method
   public static void $r8$lambda$Ije3417V0uZgdBrD9pbxQ2_AHiI(ContentLoadingProgressBar var0) {
      var0.hideOnUiThread();
   }

   // $FF: synthetic method
   public static void $r8$lambda$tmknj5M20Tn8TaJxR587u_39ZDQ(ContentLoadingProgressBar var0) {
      var0.showOnUiThread();
   }

   public ContentLoadingProgressBar(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ContentLoadingProgressBar(Context var1, AttributeSet var2) {
      super(var1, var2, 0);
      this.mStartTime = -1L;
      this.mPostedHide = false;
      this.mPostedShow = false;
      this.mDismissed = false;
      this.mDelayedHide = new ContentLoadingProgressBar$$ExternalSyntheticLambda2(this);
      this.mDelayedShow = new ContentLoadingProgressBar$$ExternalSyntheticLambda3(this);
   }

   private void hideOnUiThread() {
      this.mDismissed = true;
      this.removeCallbacks(this.mDelayedShow);
      this.mPostedShow = false;
      long var3 = System.currentTimeMillis();
      long var1 = this.mStartTime;
      var3 -= var1;
      if (var3 < 500L && var1 != -1L) {
         if (!this.mPostedHide) {
            this.postDelayed(this.mDelayedHide, 500L - var3);
            this.mPostedHide = true;
         }
      } else {
         this.setVisibility(8);
      }

   }

   private void removeCallbacks() {
      this.removeCallbacks(this.mDelayedHide);
      this.removeCallbacks(this.mDelayedShow);
   }

   private void showOnUiThread() {
      this.mStartTime = -1L;
      this.mDismissed = false;
      this.removeCallbacks(this.mDelayedHide);
      this.mPostedHide = false;
      if (!this.mPostedShow) {
         this.postDelayed(this.mDelayedShow, 500L);
         this.mPostedShow = true;
      }

   }

   public void hide() {
      this.post(new ContentLoadingProgressBar$$ExternalSyntheticLambda1(this));
   }

   // $FF: synthetic method
   void lambda$new$0$androidx_core_widget_ContentLoadingProgressBar() {
      this.mPostedHide = false;
      this.mStartTime = -1L;
      this.setVisibility(8);
   }

   // $FF: synthetic method
   void lambda$new$1$androidx_core_widget_ContentLoadingProgressBar() {
      this.mPostedShow = false;
      if (!this.mDismissed) {
         this.mStartTime = System.currentTimeMillis();
         this.setVisibility(0);
      }

   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.removeCallbacks();
   }

   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.removeCallbacks();
   }

   public void show() {
      this.post(new ContentLoadingProgressBar$$ExternalSyntheticLambda0(this));
   }
}
