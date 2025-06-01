package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import java.util.Map;

public class ChangeBounds extends Transition {
   private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = new Property(PointF.class, "bottomRight") {
      public PointF get(View var1) {
         return null;
      }

      public void set(View var1, PointF var2) {
         ViewUtils.setLeftTopRightBottom(var1, var1.getLeft(), var1.getTop(), Math.round(var2.x), Math.round(var2.y));
      }
   };
   private static final Property BOTTOM_RIGHT_PROPERTY = new Property(PointF.class, "bottomRight") {
      public PointF get(ViewBounds var1) {
         return null;
      }

      public void set(ViewBounds var1, PointF var2) {
         var1.setBottomRight(var2);
      }
   };
   private static final Property DRAWABLE_ORIGIN_PROPERTY = new Property(PointF.class, "boundsOrigin") {
      private Rect mBounds = new Rect();

      public PointF get(Drawable var1) {
         var1.copyBounds(this.mBounds);
         return new PointF((float)this.mBounds.left, (float)this.mBounds.top);
      }

      public void set(Drawable var1, PointF var2) {
         var1.copyBounds(this.mBounds);
         this.mBounds.offsetTo(Math.round(var2.x), Math.round(var2.y));
         var1.setBounds(this.mBounds);
      }
   };
   private static final Property POSITION_PROPERTY = new Property(PointF.class, "position") {
      public PointF get(View var1) {
         return null;
      }

      public void set(View var1, PointF var2) {
         int var4 = Math.round(var2.x);
         int var3 = Math.round(var2.y);
         ViewUtils.setLeftTopRightBottom(var1, var4, var3, var1.getWidth() + var4, var1.getHeight() + var3);
      }
   };
   private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
   private static final String PROPNAME_CLIP = "android:changeBounds:clip";
   private static final String PROPNAME_PARENT = "android:changeBounds:parent";
   private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
   private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
   private static final Property TOP_LEFT_ONLY_PROPERTY = new Property(PointF.class, "topLeft") {
      public PointF get(View var1) {
         return null;
      }

      public void set(View var1, PointF var2) {
         ViewUtils.setLeftTopRightBottom(var1, Math.round(var2.x), Math.round(var2.y), var1.getRight(), var1.getBottom());
      }
   };
   private static final Property TOP_LEFT_PROPERTY = new Property(PointF.class, "topLeft") {
      public PointF get(ViewBounds var1) {
         return null;
      }

      public void set(ViewBounds var1, PointF var2) {
         var1.setTopLeft(var2);
      }
   };
   private static RectEvaluator sRectEvaluator = new RectEvaluator();
   private static final String[] sTransitionProperties = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
   private boolean mReparent = false;
   private boolean mResizeClip = false;
   private int[] mTempLocation = new int[2];

   public ChangeBounds() {
   }

   public ChangeBounds(Context var1, AttributeSet var2) {
      super(var1, var2);
      TypedArray var4 = var1.obtainStyledAttributes(var2, Styleable.CHANGE_BOUNDS);
      boolean var3 = TypedArrayUtils.getNamedBoolean(var4, (XmlResourceParser)var2, "resizeClip", 0, false);
      var4.recycle();
      this.setResizeClip(var3);
   }

   private void captureValues(TransitionValues var1) {
      View var2 = var1.view;
      if (ViewCompat.isLaidOut(var2) || var2.getWidth() != 0 || var2.getHeight() != 0) {
         var1.values.put("android:changeBounds:bounds", new Rect(var2.getLeft(), var2.getTop(), var2.getRight(), var2.getBottom()));
         var1.values.put("android:changeBounds:parent", var1.view.getParent());
         if (this.mReparent) {
            var1.view.getLocationInWindow(this.mTempLocation);
            var1.values.put("android:changeBounds:windowX", this.mTempLocation[0]);
            var1.values.put("android:changeBounds:windowY", this.mTempLocation[1]);
         }

         if (this.mResizeClip) {
            var1.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(var2));
         }
      }

   }

   private boolean parentMatches(View var1, View var2) {
      boolean var5 = this.mReparent;
      boolean var4 = true;
      boolean var3 = var4;
      if (var5) {
         TransitionValues var6 = this.getMatchedTransitionValues(var1, true);
         if (var6 == null) {
            if (var1 == var2) {
               var3 = var4;
               return var3;
            }
         } else if (var2 == var6.view) {
            var3 = var4;
            return var3;
         }

         var3 = false;
      }

      return var3;
   }

   public void captureEndValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public void captureStartValues(TransitionValues var1) {
      this.captureValues(var1);
   }

   public Animator createAnimator(ViewGroup var1, TransitionValues var2, TransitionValues var3) {
      if (var2 != null && var3 != null) {
         Map var20 = var2.values;
         Map var21 = var3.values;
         ViewGroup var38 = (ViewGroup)var20.get("android:changeBounds:parent");
         ViewGroup var22 = (ViewGroup)var21.get("android:changeBounds:parent");
         if (var38 != null && var22 != null) {
            View var41 = var3.view;
            int var6;
            int var7;
            int var8;
            int var9;
            int var10;
            ObjectAnimator var32;
            if (this.parentMatches(var38, var22)) {
               Rect var23 = (Rect)var2.values.get("android:changeBounds:bounds");
               Rect var40 = (Rect)var3.values.get("android:changeBounds:bounds");
               var8 = var23.left;
               int var16 = var40.left;
               int var12 = var23.top;
               var10 = var40.top;
               int var19 = var23.right;
               int var14 = var40.right;
               int var11 = var23.bottom;
               int var18 = var40.bottom;
               var9 = var19 - var8;
               int var17 = var11 - var12;
               int var15 = var14 - var16;
               int var13 = var18 - var10;
               Rect var28 = (Rect)var2.values.get("android:changeBounds:clip");
               var40 = (Rect)var3.values.get("android:changeBounds:clip");
               if (var9 != 0 && var17 != 0 || var15 != 0 && var13 != 0) {
                  label146: {
                     byte var37;
                     if (var8 == var16 && var12 == var10) {
                        var37 = 0;
                     } else {
                        var37 = 1;
                     }

                     if (var19 == var14) {
                        var6 = var37;
                        if (var11 == var18) {
                           break label146;
                        }
                     }

                     var6 = var37 + 1;
                  }
               } else {
                  var6 = 0;
               }

               label149: {
                  if (var28 == null || var28.equals(var40)) {
                     var7 = var6;
                     if (var28 != null) {
                        break label149;
                     }

                     var7 = var6;
                     if (var40 == null) {
                        break label149;
                     }
                  }

                  var7 = var6 + 1;
               }

               if (var7 > 0) {
                  Path var24;
                  Object var26;
                  if (!this.mResizeClip) {
                     ViewUtils.setLeftTopRightBottom(var41, var8, var12, var19, var11);
                     if (var7 == 2) {
                        if (var9 == var15 && var17 == var13) {
                           var24 = this.getPathMotion().getPath((float)var8, (float)var12, (float)var16, (float)var10);
                           var26 = ObjectAnimatorUtils.ofPointF(var41, POSITION_PROPERTY, var24);
                        } else {
                           ViewBounds var30 = new ViewBounds(var41);
                           var24 = this.getPathMotion().getPath((float)var8, (float)var12, (float)var16, (float)var10);
                           var32 = ObjectAnimatorUtils.ofPointF(var30, TOP_LEFT_PROPERTY, var24);
                           var24 = this.getPathMotion().getPath((float)var19, (float)var11, (float)var14, (float)var18);
                           ObjectAnimator var42 = ObjectAnimatorUtils.ofPointF(var30, BOTTOM_RIGHT_PROPERTY, var24);
                           var26 = new AnimatorSet();
                           ((AnimatorSet)var26).playTogether(new Animator[]{var32, var42});
                           ((AnimatorSet)var26).addListener(new AnimatorListenerAdapter(this, var30) {
                              private ViewBounds mViewBounds;
                              final ChangeBounds this$0;
                              final ViewBounds val$viewBounds;

                              {
                                 this.this$0 = var1;
                                 this.val$viewBounds = var2;
                                 this.mViewBounds = var2;
                              }
                           });
                        }
                     } else if (var8 == var16 && var12 == var10) {
                        var24 = this.getPathMotion().getPath((float)var19, (float)var11, (float)var14, (float)var18);
                        var26 = ObjectAnimatorUtils.ofPointF(var41, BOTTOM_RIGHT_ONLY_PROPERTY, var24);
                     } else {
                        var24 = this.getPathMotion().getPath((float)var8, (float)var12, (float)var16, (float)var10);
                        var26 = ObjectAnimatorUtils.ofPointF(var41, TOP_LEFT_ONLY_PROPERTY, var24);
                     }
                  } else {
                     ViewUtils.setLeftTopRightBottom(var41, var8, var12, Math.max(var9, var15) + var8, Math.max(var17, var13) + var12);
                     ObjectAnimator var35;
                     if (var8 == var16 && var12 == var10) {
                        var35 = null;
                     } else {
                        var24 = this.getPathMotion().getPath((float)var8, (float)var12, (float)var16, (float)var10);
                        var35 = ObjectAnimatorUtils.ofPointF(var41, POSITION_PROPERTY, var24);
                     }

                     if (var28 == null) {
                        var28 = new Rect(0, 0, var9, var17);
                     }

                     Rect var34;
                     if (var40 == null) {
                        var34 = new Rect(0, 0, var15, var13);
                     } else {
                        var34 = var40;
                     }

                     ObjectAnimator var33;
                     if (!var28.equals(var34)) {
                        ViewCompat.setClipBounds(var41, var28);
                        var33 = ObjectAnimator.ofObject(var41, "clipBounds", sRectEvaluator, new Object[]{var28, var34});
                        var33.addListener(new AnimatorListenerAdapter(this, var41, var40, var16, var10, var14, var18) {
                           private boolean mIsCanceled;
                           final ChangeBounds this$0;
                           final int val$endBottom;
                           final int val$endLeft;
                           final int val$endRight;
                           final int val$endTop;
                           final Rect val$finalClip;
                           final View val$view;

                           {
                              this.this$0 = var1;
                              this.val$view = var2;
                              this.val$finalClip = var3;
                              this.val$endLeft = var4;
                              this.val$endTop = var5;
                              this.val$endRight = var6;
                              this.val$endBottom = var7;
                           }

                           public void onAnimationCancel(Animator var1) {
                              this.mIsCanceled = true;
                           }

                           public void onAnimationEnd(Animator var1) {
                              if (!this.mIsCanceled) {
                                 ViewCompat.setClipBounds(this.val$view, this.val$finalClip);
                                 ViewUtils.setLeftTopRightBottom(this.val$view, this.val$endLeft, this.val$endTop, this.val$endRight, this.val$endBottom);
                              }

                           }
                        });
                     } else {
                        var33 = null;
                     }

                     var26 = TransitionUtils.mergeAnimators(var35, var33);
                  }

                  if (var41.getParent() instanceof ViewGroup) {
                     ViewGroup var36 = (ViewGroup)var41.getParent();
                     ViewGroupUtils.suppressLayout(var36, true);
                     this.addListener(new TransitionListenerAdapter(this, var36) {
                        boolean mCanceled;
                        final ChangeBounds this$0;
                        final ViewGroup val$parent;

                        {
                           this.this$0 = var1;
                           this.val$parent = var2;
                           this.mCanceled = false;
                        }

                        public void onTransitionCancel(Transition var1) {
                           ViewGroupUtils.suppressLayout(this.val$parent, false);
                           this.mCanceled = true;
                        }

                        public void onTransitionEnd(Transition var1) {
                           if (!this.mCanceled) {
                              ViewGroupUtils.suppressLayout(this.val$parent, false);
                           }

                           var1.removeListener(this);
                        }

                        public void onTransitionPause(Transition var1) {
                           ViewGroupUtils.suppressLayout(this.val$parent, false);
                        }

                        public void onTransitionResume(Transition var1) {
                           ViewGroupUtils.suppressLayout(this.val$parent, true);
                        }
                     });
                  }

                  return (Animator)var26;
               }
            } else {
               var10 = (Integer)var2.values.get("android:changeBounds:windowX");
               var7 = (Integer)var2.values.get("android:changeBounds:windowY");
               var6 = (Integer)var3.values.get("android:changeBounds:windowX");
               var9 = (Integer)var3.values.get("android:changeBounds:windowY");
               if (var10 != var6 || var7 != var9) {
                  var1.getLocationInWindow(this.mTempLocation);
                  Bitmap var25 = Bitmap.createBitmap(var41.getWidth(), var41.getHeight(), Config.ARGB_8888);
                  var41.draw(new Canvas(var25));
                  BitmapDrawable var27 = new BitmapDrawable(var25);
                  float var4 = ViewUtils.getTransitionAlpha(var41);
                  ViewUtils.setTransitionAlpha(var41, 0.0F);
                  ViewUtils.getOverlay(var1).add(var27);
                  PathMotion var39 = this.getPathMotion();
                  int[] var29 = this.mTempLocation;
                  var8 = var29[0];
                  float var5 = (float)(var10 - var8);
                  var10 = var29[1];
                  Path var31 = var39.getPath(var5, (float)(var7 - var10), (float)(var6 - var8), (float)(var9 - var10));
                  var32 = ObjectAnimator.ofPropertyValuesHolder(var27, new PropertyValuesHolder[]{PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, var31)});
                  var32.addListener(new AnimatorListenerAdapter(this, var1, var27, var41, var4) {
                     final ChangeBounds this$0;
                     final BitmapDrawable val$drawable;
                     final ViewGroup val$sceneRoot;
                     final float val$transitionAlpha;
                     final View val$view;

                     {
                        this.this$0 = var1;
                        this.val$sceneRoot = var2;
                        this.val$drawable = var3;
                        this.val$view = var4;
                        this.val$transitionAlpha = var5;
                     }

                     public void onAnimationEnd(Animator var1) {
                        ViewUtils.getOverlay(this.val$sceneRoot).remove(this.val$drawable);
                        ViewUtils.setTransitionAlpha(this.val$view, this.val$transitionAlpha);
                     }
                  });
                  return var32;
               }
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public boolean getResizeClip() {
      return this.mResizeClip;
   }

   public String[] getTransitionProperties() {
      return sTransitionProperties;
   }

   public void setResizeClip(boolean var1) {
      this.mResizeClip = var1;
   }

   private static class ViewBounds {
      private int mBottom;
      private int mBottomRightCalls;
      private int mLeft;
      private int mRight;
      private int mTop;
      private int mTopLeftCalls;
      private View mView;

      ViewBounds(View var1) {
         this.mView = var1;
      }

      private void setLeftTopRightBottom() {
         ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
         this.mTopLeftCalls = 0;
         this.mBottomRightCalls = 0;
      }

      void setBottomRight(PointF var1) {
         this.mRight = Math.round(var1.x);
         this.mBottom = Math.round(var1.y);
         int var2 = this.mBottomRightCalls + 1;
         this.mBottomRightCalls = var2;
         if (this.mTopLeftCalls == var2) {
            this.setLeftTopRightBottom();
         }

      }

      void setTopLeft(PointF var1) {
         this.mLeft = Math.round(var1.x);
         this.mTop = Math.round(var1.y);
         int var2 = this.mTopLeftCalls + 1;
         this.mTopLeftCalls = var2;
         if (var2 == this.mBottomRightCalls) {
            this.setLeftTopRightBottom();
         }

      }
   }
}
