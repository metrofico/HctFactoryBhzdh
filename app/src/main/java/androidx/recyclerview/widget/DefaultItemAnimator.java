package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultItemAnimator extends SimpleItemAnimator {
   private static final boolean DEBUG = false;
   private static TimeInterpolator sDefaultInterpolator;
   ArrayList mAddAnimations = new ArrayList();
   ArrayList mAdditionsList = new ArrayList();
   ArrayList mChangeAnimations = new ArrayList();
   ArrayList mChangesList = new ArrayList();
   ArrayList mMoveAnimations = new ArrayList();
   ArrayList mMovesList = new ArrayList();
   private ArrayList mPendingAdditions = new ArrayList();
   private ArrayList mPendingChanges = new ArrayList();
   private ArrayList mPendingMoves = new ArrayList();
   private ArrayList mPendingRemovals = new ArrayList();
   ArrayList mRemoveAnimations = new ArrayList();

   private void animateRemoveImpl(RecyclerView.ViewHolder var1) {
      View var2 = var1.itemView;
      ViewPropertyAnimator var3 = var2.animate();
      this.mRemoveAnimations.add(var1);
      var3.setDuration(this.getRemoveDuration()).alpha(0.0F).setListener(new AnimatorListenerAdapter(this, var1, var3, var2) {
         final DefaultItemAnimator this$0;
         final ViewPropertyAnimator val$animation;
         final RecyclerView.ViewHolder val$holder;
         final View val$view;

         {
            this.this$0 = var1;
            this.val$holder = var2;
            this.val$animation = var3;
            this.val$view = var4;
         }

         public void onAnimationEnd(Animator var1) {
            this.val$animation.setListener((Animator.AnimatorListener)null);
            this.val$view.setAlpha(1.0F);
            this.this$0.dispatchRemoveFinished(this.val$holder);
            this.this$0.mRemoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
         }

         public void onAnimationStart(Animator var1) {
            this.this$0.dispatchRemoveStarting(this.val$holder);
         }
      }).start();
   }

   private void endChangeAnimation(List var1, RecyclerView.ViewHolder var2) {
      for(int var3 = var1.size() - 1; var3 >= 0; --var3) {
         ChangeInfo var4 = (ChangeInfo)var1.get(var3);
         if (this.endChangeAnimationIfNecessary(var4, var2) && var4.oldHolder == null && var4.newHolder == null) {
            var1.remove(var4);
         }
      }

   }

   private void endChangeAnimationIfNecessary(ChangeInfo var1) {
      if (var1.oldHolder != null) {
         this.endChangeAnimationIfNecessary(var1, var1.oldHolder);
      }

      if (var1.newHolder != null) {
         this.endChangeAnimationIfNecessary(var1, var1.newHolder);
      }

   }

   private boolean endChangeAnimationIfNecessary(ChangeInfo var1, RecyclerView.ViewHolder var2) {
      RecyclerView.ViewHolder var4 = var1.newHolder;
      boolean var3 = false;
      if (var4 == var2) {
         var1.newHolder = null;
      } else {
         if (var1.oldHolder != var2) {
            return false;
         }

         var1.oldHolder = null;
         var3 = true;
      }

      var2.itemView.setAlpha(1.0F);
      var2.itemView.setTranslationX(0.0F);
      var2.itemView.setTranslationY(0.0F);
      this.dispatchChangeFinished(var2, var3);
      return true;
   }

   private void resetAnimation(RecyclerView.ViewHolder var1) {
      if (sDefaultInterpolator == null) {
         sDefaultInterpolator = (new ValueAnimator()).getInterpolator();
      }

      var1.itemView.animate().setInterpolator(sDefaultInterpolator);
      this.endAnimation(var1);
   }

   public boolean animateAdd(RecyclerView.ViewHolder var1) {
      this.resetAnimation(var1);
      var1.itemView.setAlpha(0.0F);
      this.mPendingAdditions.add(var1);
      return true;
   }

   void animateAddImpl(RecyclerView.ViewHolder var1) {
      View var2 = var1.itemView;
      ViewPropertyAnimator var3 = var2.animate();
      this.mAddAnimations.add(var1);
      var3.alpha(1.0F).setDuration(this.getAddDuration()).setListener(new AnimatorListenerAdapter(this, var1, var2, var3) {
         final DefaultItemAnimator this$0;
         final ViewPropertyAnimator val$animation;
         final RecyclerView.ViewHolder val$holder;
         final View val$view;

         {
            this.this$0 = var1;
            this.val$holder = var2;
            this.val$view = var3;
            this.val$animation = var4;
         }

         public void onAnimationCancel(Animator var1) {
            this.val$view.setAlpha(1.0F);
         }

         public void onAnimationEnd(Animator var1) {
            this.val$animation.setListener((Animator.AnimatorListener)null);
            this.this$0.dispatchAddFinished(this.val$holder);
            this.this$0.mAddAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
         }

         public void onAnimationStart(Animator var1) {
            this.this$0.dispatchAddStarting(this.val$holder);
         }
      }).start();
   }

   public boolean animateChange(RecyclerView.ViewHolder var1, RecyclerView.ViewHolder var2, int var3, int var4, int var5, int var6) {
      if (var1 == var2) {
         return this.animateMove(var1, var3, var4, var5, var6);
      } else {
         float var8 = var1.itemView.getTranslationX();
         float var9 = var1.itemView.getTranslationY();
         float var7 = var1.itemView.getAlpha();
         this.resetAnimation(var1);
         int var11 = (int)((float)(var5 - var3) - var8);
         int var10 = (int)((float)(var6 - var4) - var9);
         var1.itemView.setTranslationX(var8);
         var1.itemView.setTranslationY(var9);
         var1.itemView.setAlpha(var7);
         if (var2 != null) {
            this.resetAnimation(var2);
            var2.itemView.setTranslationX((float)(-var11));
            var2.itemView.setTranslationY((float)(-var10));
            var2.itemView.setAlpha(0.0F);
         }

         this.mPendingChanges.add(new ChangeInfo(var1, var2, var3, var4, var5, var6));
         return true;
      }
   }

   void animateChangeImpl(ChangeInfo var1) {
      RecyclerView.ViewHolder var2 = var1.oldHolder;
      View var3 = null;
      View var5;
      if (var2 == null) {
         var5 = null;
      } else {
         var5 = var2.itemView;
      }

      RecyclerView.ViewHolder var4 = var1.newHolder;
      if (var4 != null) {
         var3 = var4.itemView;
      }

      if (var5 != null) {
         ViewPropertyAnimator var7 = var5.animate().setDuration(this.getChangeDuration());
         this.mChangeAnimations.add(var1.oldHolder);
         var7.translationX((float)(var1.toX - var1.fromX));
         var7.translationY((float)(var1.toY - var1.fromY));
         var7.alpha(0.0F).setListener(new AnimatorListenerAdapter(this, var1, var7, var5) {
            final DefaultItemAnimator this$0;
            final ChangeInfo val$changeInfo;
            final ViewPropertyAnimator val$oldViewAnim;
            final View val$view;

            {
               this.this$0 = var1;
               this.val$changeInfo = var2;
               this.val$oldViewAnim = var3;
               this.val$view = var4;
            }

            public void onAnimationEnd(Animator var1) {
               this.val$oldViewAnim.setListener((Animator.AnimatorListener)null);
               this.val$view.setAlpha(1.0F);
               this.val$view.setTranslationX(0.0F);
               this.val$view.setTranslationY(0.0F);
               this.this$0.dispatchChangeFinished(this.val$changeInfo.oldHolder, true);
               this.this$0.mChangeAnimations.remove(this.val$changeInfo.oldHolder);
               this.this$0.dispatchFinishedWhenDone();
            }

            public void onAnimationStart(Animator var1) {
               this.this$0.dispatchChangeStarting(this.val$changeInfo.oldHolder, true);
            }
         }).start();
      }

      if (var3 != null) {
         ViewPropertyAnimator var6 = var3.animate();
         this.mChangeAnimations.add(var1.newHolder);
         var6.translationX(0.0F).translationY(0.0F).setDuration(this.getChangeDuration()).alpha(1.0F).setListener(new AnimatorListenerAdapter(this, var1, var6, var3) {
            final DefaultItemAnimator this$0;
            final ChangeInfo val$changeInfo;
            final View val$newView;
            final ViewPropertyAnimator val$newViewAnimation;

            {
               this.this$0 = var1;
               this.val$changeInfo = var2;
               this.val$newViewAnimation = var3;
               this.val$newView = var4;
            }

            public void onAnimationEnd(Animator var1) {
               this.val$newViewAnimation.setListener((Animator.AnimatorListener)null);
               this.val$newView.setAlpha(1.0F);
               this.val$newView.setTranslationX(0.0F);
               this.val$newView.setTranslationY(0.0F);
               this.this$0.dispatchChangeFinished(this.val$changeInfo.newHolder, false);
               this.this$0.mChangeAnimations.remove(this.val$changeInfo.newHolder);
               this.this$0.dispatchFinishedWhenDone();
            }

            public void onAnimationStart(Animator var1) {
               this.this$0.dispatchChangeStarting(this.val$changeInfo.newHolder, false);
            }
         }).start();
      }

   }

   public boolean animateMove(RecyclerView.ViewHolder var1, int var2, int var3, int var4, int var5) {
      View var8 = var1.itemView;
      var2 += (int)var1.itemView.getTranslationX();
      int var7 = var3 + (int)var1.itemView.getTranslationY();
      this.resetAnimation(var1);
      var3 = var4 - var2;
      int var6 = var5 - var7;
      if (var3 == 0 && var6 == 0) {
         this.dispatchMoveFinished(var1);
         return false;
      } else {
         if (var3 != 0) {
            var8.setTranslationX((float)(-var3));
         }

         if (var6 != 0) {
            var8.setTranslationY((float)(-var6));
         }

         this.mPendingMoves.add(new MoveInfo(var1, var2, var7, var4, var5));
         return true;
      }
   }

   void animateMoveImpl(RecyclerView.ViewHolder var1, int var2, int var3, int var4, int var5) {
      View var6 = var1.itemView;
      var2 = var4 - var2;
      var3 = var5 - var3;
      if (var2 != 0) {
         var6.animate().translationX(0.0F);
      }

      if (var3 != 0) {
         var6.animate().translationY(0.0F);
      }

      ViewPropertyAnimator var7 = var6.animate();
      this.mMoveAnimations.add(var1);
      var7.setDuration(this.getMoveDuration()).setListener(new AnimatorListenerAdapter(this, var1, var2, var6, var3, var7) {
         final DefaultItemAnimator this$0;
         final ViewPropertyAnimator val$animation;
         final int val$deltaX;
         final int val$deltaY;
         final RecyclerView.ViewHolder val$holder;
         final View val$view;

         {
            this.this$0 = var1;
            this.val$holder = var2;
            this.val$deltaX = var3;
            this.val$view = var4;
            this.val$deltaY = var5;
            this.val$animation = var6;
         }

         public void onAnimationCancel(Animator var1) {
            if (this.val$deltaX != 0) {
               this.val$view.setTranslationX(0.0F);
            }

            if (this.val$deltaY != 0) {
               this.val$view.setTranslationY(0.0F);
            }

         }

         public void onAnimationEnd(Animator var1) {
            this.val$animation.setListener((Animator.AnimatorListener)null);
            this.this$0.dispatchMoveFinished(this.val$holder);
            this.this$0.mMoveAnimations.remove(this.val$holder);
            this.this$0.dispatchFinishedWhenDone();
         }

         public void onAnimationStart(Animator var1) {
            this.this$0.dispatchMoveStarting(this.val$holder);
         }
      }).start();
   }

   public boolean animateRemove(RecyclerView.ViewHolder var1) {
      this.resetAnimation(var1);
      this.mPendingRemovals.add(var1);
      return true;
   }

   public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder var1, List var2) {
      boolean var3;
      if (var2.isEmpty() && !super.canReuseUpdatedViewHolder(var1, var2)) {
         var3 = false;
      } else {
         var3 = true;
      }

      return var3;
   }

   void cancelAll(List var1) {
      for(int var2 = var1.size() - 1; var2 >= 0; --var2) {
         ((RecyclerView.ViewHolder)var1.get(var2)).itemView.animate().cancel();
      }

   }

   void dispatchFinishedWhenDone() {
      if (!this.isRunning()) {
         this.dispatchAnimationsFinished();
      }

   }

   public void endAnimation(RecyclerView.ViewHolder var1) {
      View var4 = var1.itemView;
      var4.animate().cancel();

      int var2;
      for(var2 = this.mPendingMoves.size() - 1; var2 >= 0; --var2) {
         if (((MoveInfo)this.mPendingMoves.get(var2)).holder == var1) {
            var4.setTranslationY(0.0F);
            var4.setTranslationX(0.0F);
            this.dispatchMoveFinished(var1);
            this.mPendingMoves.remove(var2);
         }
      }

      this.endChangeAnimation(this.mPendingChanges, var1);
      if (this.mPendingRemovals.remove(var1)) {
         var4.setAlpha(1.0F);
         this.dispatchRemoveFinished(var1);
      }

      if (this.mPendingAdditions.remove(var1)) {
         var4.setAlpha(1.0F);
         this.dispatchAddFinished(var1);
      }

      ArrayList var5;
      for(var2 = this.mChangesList.size() - 1; var2 >= 0; --var2) {
         var5 = (ArrayList)this.mChangesList.get(var2);
         this.endChangeAnimation(var5, var1);
         if (var5.isEmpty()) {
            this.mChangesList.remove(var2);
         }
      }

      for(var2 = this.mMovesList.size() - 1; var2 >= 0; --var2) {
         var5 = (ArrayList)this.mMovesList.get(var2);

         for(int var3 = var5.size() - 1; var3 >= 0; --var3) {
            if (((MoveInfo)var5.get(var3)).holder == var1) {
               var4.setTranslationY(0.0F);
               var4.setTranslationX(0.0F);
               this.dispatchMoveFinished(var1);
               var5.remove(var3);
               if (var5.isEmpty()) {
                  this.mMovesList.remove(var2);
               }
               break;
            }
         }
      }

      for(var2 = this.mAdditionsList.size() - 1; var2 >= 0; --var2) {
         var5 = (ArrayList)this.mAdditionsList.get(var2);
         if (var5.remove(var1)) {
            var4.setAlpha(1.0F);
            this.dispatchAddFinished(var1);
            if (var5.isEmpty()) {
               this.mAdditionsList.remove(var2);
            }
         }
      }

      this.mRemoveAnimations.remove(var1);
      this.mAddAnimations.remove(var1);
      this.mChangeAnimations.remove(var1);
      this.mMoveAnimations.remove(var1);
      this.dispatchFinishedWhenDone();
   }

   public void endAnimations() {
      int var1;
      MoveInfo var3;
      View var4;
      for(var1 = this.mPendingMoves.size() - 1; var1 >= 0; --var1) {
         var3 = (MoveInfo)this.mPendingMoves.get(var1);
         var4 = var3.holder.itemView;
         var4.setTranslationY(0.0F);
         var4.setTranslationX(0.0F);
         this.dispatchMoveFinished(var3.holder);
         this.mPendingMoves.remove(var1);
      }

      for(var1 = this.mPendingRemovals.size() - 1; var1 >= 0; --var1) {
         this.dispatchRemoveFinished((RecyclerView.ViewHolder)this.mPendingRemovals.get(var1));
         this.mPendingRemovals.remove(var1);
      }

      RecyclerView.ViewHolder var6;
      for(var1 = this.mPendingAdditions.size() - 1; var1 >= 0; --var1) {
         var6 = (RecyclerView.ViewHolder)this.mPendingAdditions.get(var1);
         var6.itemView.setAlpha(1.0F);
         this.dispatchAddFinished(var6);
         this.mPendingAdditions.remove(var1);
      }

      for(var1 = this.mPendingChanges.size() - 1; var1 >= 0; --var1) {
         this.endChangeAnimationIfNecessary((ChangeInfo)this.mPendingChanges.get(var1));
      }

      this.mPendingChanges.clear();
      if (this.isRunning()) {
         int var2;
         for(var1 = this.mMovesList.size() - 1; var1 >= 0; --var1) {
            ArrayList var5 = (ArrayList)this.mMovesList.get(var1);

            for(var2 = var5.size() - 1; var2 >= 0; --var2) {
               var3 = (MoveInfo)var5.get(var2);
               var4 = var3.holder.itemView;
               var4.setTranslationY(0.0F);
               var4.setTranslationX(0.0F);
               this.dispatchMoveFinished(var3.holder);
               var5.remove(var2);
               if (var5.isEmpty()) {
                  this.mMovesList.remove(var5);
               }
            }
         }

         for(var1 = this.mAdditionsList.size() - 1; var1 >= 0; --var1) {
            ArrayList var8 = (ArrayList)this.mAdditionsList.get(var1);

            for(var2 = var8.size() - 1; var2 >= 0; --var2) {
               var6 = (RecyclerView.ViewHolder)var8.get(var2);
               var6.itemView.setAlpha(1.0F);
               this.dispatchAddFinished(var6);
               var8.remove(var2);
               if (var8.isEmpty()) {
                  this.mAdditionsList.remove(var8);
               }
            }
         }

         for(var1 = this.mChangesList.size() - 1; var1 >= 0; --var1) {
            ArrayList var7 = (ArrayList)this.mChangesList.get(var1);

            for(var2 = var7.size() - 1; var2 >= 0; --var2) {
               this.endChangeAnimationIfNecessary((ChangeInfo)var7.get(var2));
               if (var7.isEmpty()) {
                  this.mChangesList.remove(var7);
               }
            }
         }

         this.cancelAll(this.mRemoveAnimations);
         this.cancelAll(this.mMoveAnimations);
         this.cancelAll(this.mAddAnimations);
         this.cancelAll(this.mChangeAnimations);
         this.dispatchAnimationsFinished();
      }
   }

   public boolean isRunning() {
      boolean var1;
      if (this.mPendingAdditions.isEmpty() && this.mPendingChanges.isEmpty() && this.mPendingMoves.isEmpty() && this.mPendingRemovals.isEmpty() && this.mMoveAnimations.isEmpty() && this.mRemoveAnimations.isEmpty() && this.mAddAnimations.isEmpty() && this.mChangeAnimations.isEmpty() && this.mMovesList.isEmpty() && this.mAdditionsList.isEmpty() && this.mChangesList.isEmpty()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public void runPendingAnimations() {
      boolean var4 = this.mPendingRemovals.isEmpty() ^ true;
      boolean var1 = this.mPendingMoves.isEmpty() ^ true;
      boolean var2 = this.mPendingChanges.isEmpty() ^ true;
      boolean var3 = this.mPendingAdditions.isEmpty() ^ true;
      if (var4 || var1 || var3 || var2) {
         Iterator var11 = this.mPendingRemovals.iterator();

         while(var11.hasNext()) {
            this.animateRemoveImpl((RecyclerView.ViewHolder)var11.next());
         }

         this.mPendingRemovals.clear();
         Runnable var12;
         ArrayList var13;
         if (var1) {
            var13 = new ArrayList();
            var13.addAll(this.mPendingMoves);
            this.mMovesList.add(var13);
            this.mPendingMoves.clear();
            var12 = new Runnable(this, var13) {
               final DefaultItemAnimator this$0;
               final ArrayList val$moves;

               {
                  this.this$0 = var1;
                  this.val$moves = var2;
               }

               public void run() {
                  Iterator var1 = this.val$moves.iterator();

                  while(var1.hasNext()) {
                     MoveInfo var2 = (MoveInfo)var1.next();
                     this.this$0.animateMoveImpl(var2.holder, var2.fromX, var2.fromY, var2.toX, var2.toY);
                  }

                  this.val$moves.clear();
                  this.this$0.mMovesList.remove(this.val$moves);
               }
            };
            if (var4) {
               ViewCompat.postOnAnimationDelayed(((MoveInfo)var13.get(0)).holder.itemView, var12, this.getRemoveDuration());
            } else {
               var12.run();
            }
         }

         if (var2) {
            var13 = new ArrayList();
            var13.addAll(this.mPendingChanges);
            this.mChangesList.add(var13);
            this.mPendingChanges.clear();
            var12 = new Runnable(this, var13) {
               final DefaultItemAnimator this$0;
               final ArrayList val$changes;

               {
                  this.this$0 = var1;
                  this.val$changes = var2;
               }

               public void run() {
                  Iterator var2 = this.val$changes.iterator();

                  while(var2.hasNext()) {
                     ChangeInfo var1 = (ChangeInfo)var2.next();
                     this.this$0.animateChangeImpl(var1);
                  }

                  this.val$changes.clear();
                  this.this$0.mChangesList.remove(this.val$changes);
               }
            };
            if (var4) {
               ViewCompat.postOnAnimationDelayed(((ChangeInfo)var13.get(0)).oldHolder.itemView, var12, this.getRemoveDuration());
            } else {
               var12.run();
            }
         }

         if (var3) {
            var13 = new ArrayList();
            var13.addAll(this.mPendingAdditions);
            this.mAdditionsList.add(var13);
            this.mPendingAdditions.clear();
            var12 = new Runnable(this, var13) {
               final DefaultItemAnimator this$0;
               final ArrayList val$additions;

               {
                  this.this$0 = var1;
                  this.val$additions = var2;
               }

               public void run() {
                  Iterator var1 = this.val$additions.iterator();

                  while(var1.hasNext()) {
                     RecyclerView.ViewHolder var2 = (RecyclerView.ViewHolder)var1.next();
                     this.this$0.animateAddImpl(var2);
                  }

                  this.val$additions.clear();
                  this.this$0.mAdditionsList.remove(this.val$additions);
               }
            };
            if (!var4 && !var1 && !var2) {
               var12.run();
            } else {
               long var9 = 0L;
               long var5;
               if (var4) {
                  var5 = this.getRemoveDuration();
               } else {
                  var5 = 0L;
               }

               long var7;
               if (var1) {
                  var7 = this.getMoveDuration();
               } else {
                  var7 = 0L;
               }

               if (var2) {
                  var9 = this.getChangeDuration();
               }

               var7 = Math.max(var7, var9);
               ViewCompat.postOnAnimationDelayed(((RecyclerView.ViewHolder)var13.get(0)).itemView, var12, var5 + var7);
            }
         }

      }
   }

   private static class ChangeInfo {
      public int fromX;
      public int fromY;
      public RecyclerView.ViewHolder newHolder;
      public RecyclerView.ViewHolder oldHolder;
      public int toX;
      public int toY;

      private ChangeInfo(RecyclerView.ViewHolder var1, RecyclerView.ViewHolder var2) {
         this.oldHolder = var1;
         this.newHolder = var2;
      }

      ChangeInfo(RecyclerView.ViewHolder var1, RecyclerView.ViewHolder var2, int var3, int var4, int var5, int var6) {
         this(var1, var2);
         this.fromX = var3;
         this.fromY = var4;
         this.toX = var5;
         this.toY = var6;
      }

      public String toString() {
         return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
      }
   }

   private static class MoveInfo {
      public int fromX;
      public int fromY;
      public RecyclerView.ViewHolder holder;
      public int toX;
      public int toY;

      MoveInfo(RecyclerView.ViewHolder var1, int var2, int var3, int var4, int var5) {
         this.holder = var1;
         this.fromX = var2;
         this.fromY = var3;
         this.toX = var4;
         this.toY = var5;
      }
   }
}
