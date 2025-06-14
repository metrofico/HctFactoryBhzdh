package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Transition implements Cloneable {
   static final boolean DBG = false;
   private static final int[] DEFAULT_MATCH_ORDER = new int[]{2, 1, 3, 4};
   private static final String LOG_TAG = "Transition";
   private static final int MATCH_FIRST = 1;
   public static final int MATCH_ID = 3;
   private static final String MATCH_ID_STR = "id";
   public static final int MATCH_INSTANCE = 1;
   private static final String MATCH_INSTANCE_STR = "instance";
   public static final int MATCH_ITEM_ID = 4;
   private static final String MATCH_ITEM_ID_STR = "itemId";
   private static final int MATCH_LAST = 4;
   public static final int MATCH_NAME = 2;
   private static final String MATCH_NAME_STR = "name";
   private static final PathMotion STRAIGHT_PATH_MOTION = new PathMotion() {
      public Path getPath(float var1, float var2, float var3, float var4) {
         Path var5 = new Path();
         var5.moveTo(var1, var2);
         var5.lineTo(var3, var4);
         return var5;
      }
   };
   private static ThreadLocal sRunningAnimators = new ThreadLocal();
   private ArrayList mAnimators;
   boolean mCanRemoveViews;
   ArrayList mCurrentAnimators;
   long mDuration = -1L;
   private TransitionValuesMaps mEndValues = new TransitionValuesMaps();
   private ArrayList mEndValuesList;
   private boolean mEnded;
   private EpicenterCallback mEpicenterCallback;
   private TimeInterpolator mInterpolator = null;
   private ArrayList mListeners;
   private int[] mMatchOrder;
   private String mName = this.getClass().getName();
   private ArrayMap mNameOverrides;
   private int mNumInstances;
   TransitionSet mParent = null;
   private PathMotion mPathMotion;
   private boolean mPaused;
   TransitionPropagation mPropagation;
   private ViewGroup mSceneRoot;
   private long mStartDelay = -1L;
   private TransitionValuesMaps mStartValues = new TransitionValuesMaps();
   private ArrayList mStartValuesList;
   private ArrayList mTargetChildExcludes = null;
   private ArrayList mTargetExcludes = null;
   private ArrayList mTargetIdChildExcludes = null;
   private ArrayList mTargetIdExcludes = null;
   ArrayList mTargetIds = new ArrayList();
   private ArrayList mTargetNameExcludes = null;
   private ArrayList mTargetNames = null;
   private ArrayList mTargetTypeChildExcludes = null;
   private ArrayList mTargetTypeExcludes = null;
   private ArrayList mTargetTypes = null;
   ArrayList mTargets = new ArrayList();

   public Transition() {
      this.mMatchOrder = DEFAULT_MATCH_ORDER;
      this.mSceneRoot = null;
      this.mCanRemoveViews = false;
      this.mCurrentAnimators = new ArrayList();
      this.mNumInstances = 0;
      this.mPaused = false;
      this.mEnded = false;
      this.mListeners = null;
      this.mAnimators = new ArrayList();
      this.mPathMotion = STRAIGHT_PATH_MOTION;
   }

   public Transition(Context var1, AttributeSet var2) {
      this.mMatchOrder = DEFAULT_MATCH_ORDER;
      this.mSceneRoot = null;
      this.mCanRemoveViews = false;
      this.mCurrentAnimators = new ArrayList();
      this.mNumInstances = 0;
      this.mPaused = false;
      this.mEnded = false;
      this.mListeners = null;
      this.mAnimators = new ArrayList();
      this.mPathMotion = STRAIGHT_PATH_MOTION;
      TypedArray var6 = var1.obtainStyledAttributes(var2, Styleable.TRANSITION);
      XmlResourceParser var8 = (XmlResourceParser)var2;
      long var4 = (long)TypedArrayUtils.getNamedInt(var6, var8, "duration", 1, -1);
      if (var4 >= 0L) {
         this.setDuration(var4);
      }

      var4 = (long)TypedArrayUtils.getNamedInt(var6, var8, "startDelay", 2, -1);
      if (var4 > 0L) {
         this.setStartDelay(var4);
      }

      int var3 = TypedArrayUtils.getNamedResourceId(var6, var8, "interpolator", 0, 0);
      if (var3 > 0) {
         this.setInterpolator(AnimationUtils.loadInterpolator(var1, var3));
      }

      String var7 = TypedArrayUtils.getNamedString(var6, var8, "matchOrder", 3);
      if (var7 != null) {
         this.setMatchOrder(parseMatchOrder(var7));
      }

      var6.recycle();
   }

   private void addUnmatched(ArrayMap var1, ArrayMap var2) {
      byte var5 = 0;
      int var4 = 0;

      while(true) {
         int var3 = var5;
         if (var4 >= var1.size()) {
            for(; var3 < var2.size(); ++var3) {
               TransitionValues var7 = (TransitionValues)var2.valueAt(var3);
               if (this.isValidTarget(var7.view)) {
                  this.mEndValuesList.add(var7);
                  this.mStartValuesList.add((Object)null);
               }
            }

            return;
         }

         TransitionValues var6 = (TransitionValues)var1.valueAt(var4);
         if (this.isValidTarget(var6.view)) {
            this.mStartValuesList.add(var6);
            this.mEndValuesList.add((Object)null);
         }

         ++var4;
      }
   }

   private static void addViewValues(TransitionValuesMaps var0, View var1, TransitionValues var2) {
      var0.mViewValues.put(var1, var2);
      int var3 = var1.getId();
      if (var3 >= 0) {
         if (var0.mIdValues.indexOfKey(var3) >= 0) {
            var0.mIdValues.put(var3, (Object)null);
         } else {
            var0.mIdValues.put(var3, var1);
         }
      }

      String var6 = ViewCompat.getTransitionName(var1);
      if (var6 != null) {
         if (var0.mNameValues.containsKey(var6)) {
            var0.mNameValues.put(var6, (Object)null);
         } else {
            var0.mNameValues.put(var6, var1);
         }
      }

      if (var1.getParent() instanceof ListView) {
         ListView var7 = (ListView)var1.getParent();
         if (var7.getAdapter().hasStableIds()) {
            long var4 = var7.getItemIdAtPosition(var7.getPositionForView(var1));
            if (var0.mItemIdValues.indexOfKey(var4) >= 0) {
               var1 = (View)var0.mItemIdValues.get(var4);
               if (var1 != null) {
                  ViewCompat.setHasTransientState(var1, false);
                  var0.mItemIdValues.put(var4, (Object)null);
               }
            } else {
               ViewCompat.setHasTransientState(var1, true);
               var0.mItemIdValues.put(var4, var1);
            }
         }
      }

   }

   private static boolean alreadyContains(int[] var0, int var1) {
      int var3 = var0[var1];

      for(int var2 = 0; var2 < var1; ++var2) {
         if (var0[var2] == var3) {
            return true;
         }
      }

      return false;
   }

   private void captureHierarchy(View var1, boolean var2) {
      if (var1 != null) {
         int var6 = var1.getId();
         ArrayList var7 = this.mTargetIdExcludes;
         if (var7 == null || !var7.contains(var6)) {
            var7 = this.mTargetExcludes;
            if (var7 == null || !var7.contains(var1)) {
               var7 = this.mTargetTypeExcludes;
               byte var4 = 0;
               int var3;
               int var5;
               if (var7 != null) {
                  var5 = var7.size();

                  for(var3 = 0; var3 < var5; ++var3) {
                     if (((Class)this.mTargetTypeExcludes.get(var3)).isInstance(var1)) {
                        return;
                     }
                  }
               }

               if (var1.getParent() instanceof ViewGroup) {
                  TransitionValues var9 = new TransitionValues();
                  var9.view = var1;
                  if (var2) {
                     this.captureStartValues(var9);
                  } else {
                     this.captureEndValues(var9);
                  }

                  var9.mTargetedTransitions.add(this);
                  this.capturePropagationValues(var9);
                  if (var2) {
                     addViewValues(this.mStartValues, var1, var9);
                  } else {
                     addViewValues(this.mEndValues, var1, var9);
                  }
               }

               if (var1 instanceof ViewGroup) {
                  var7 = this.mTargetIdChildExcludes;
                  if (var7 != null && var7.contains(var6)) {
                     return;
                  }

                  var7 = this.mTargetChildExcludes;
                  if (var7 != null && var7.contains(var1)) {
                     return;
                  }

                  var7 = this.mTargetTypeChildExcludes;
                  if (var7 != null) {
                     var5 = var7.size();

                     for(var3 = 0; var3 < var5; ++var3) {
                        if (((Class)this.mTargetTypeChildExcludes.get(var3)).isInstance(var1)) {
                           return;
                        }
                     }
                  }

                  ViewGroup var8 = (ViewGroup)var1;

                  for(var3 = var4; var3 < var8.getChildCount(); ++var3) {
                     this.captureHierarchy(var8.getChildAt(var3), var2);
                  }
               }

            }
         }
      }
   }

   private ArrayList excludeId(ArrayList var1, int var2, boolean var3) {
      ArrayList var4 = var1;
      if (var2 > 0) {
         if (var3) {
            var4 = Transition.ArrayListManager.add(var1, var2);
         } else {
            var4 = Transition.ArrayListManager.remove(var1, var2);
         }
      }

      return var4;
   }

   private static ArrayList excludeObject(ArrayList var0, Object var1, boolean var2) {
      ArrayList var3 = var0;
      if (var1 != null) {
         if (var2) {
            var3 = Transition.ArrayListManager.add(var0, var1);
         } else {
            var3 = Transition.ArrayListManager.remove(var0, var1);
         }
      }

      return var3;
   }

   private ArrayList excludeType(ArrayList var1, Class var2, boolean var3) {
      ArrayList var4 = var1;
      if (var2 != null) {
         if (var3) {
            var4 = Transition.ArrayListManager.add(var1, var2);
         } else {
            var4 = Transition.ArrayListManager.remove(var1, var2);
         }
      }

      return var4;
   }

   private ArrayList excludeView(ArrayList var1, View var2, boolean var3) {
      ArrayList var4 = var1;
      if (var2 != null) {
         if (var3) {
            var4 = Transition.ArrayListManager.add(var1, var2);
         } else {
            var4 = Transition.ArrayListManager.remove(var1, var2);
         }
      }

      return var4;
   }

   private static ArrayMap getRunningAnimators() {
      ArrayMap var1 = (ArrayMap)sRunningAnimators.get();
      ArrayMap var0 = var1;
      if (var1 == null) {
         var0 = new ArrayMap();
         sRunningAnimators.set(var0);
      }

      return var0;
   }

   private static boolean isValidMatch(int var0) {
      boolean var1 = true;
      if (var0 < 1 || var0 > 4) {
         var1 = false;
      }

      return var1;
   }

   private static boolean isValueChanged(TransitionValues var0, TransitionValues var1, String var2) {
      Object var5 = var0.values.get(var2);
      Object var6 = var1.values.get(var2);
      boolean var4 = true;
      boolean var3;
      if (var5 == null && var6 == null) {
         var3 = false;
      } else {
         var3 = var4;
         if (var5 != null) {
            if (var6 == null) {
               var3 = var4;
            } else {
               var3 = true ^ var5.equals(var6);
            }
         }
      }

      return var3;
   }

   private void matchIds(ArrayMap var1, ArrayMap var2, SparseArray var3, SparseArray var4) {
      int var6 = var3.size();

      for(int var5 = 0; var5 < var6; ++var5) {
         View var9 = (View)var3.valueAt(var5);
         if (var9 != null && this.isValidTarget(var9)) {
            View var8 = (View)var4.get(var3.keyAt(var5));
            if (var8 != null && this.isValidTarget(var8)) {
               TransitionValues var10 = (TransitionValues)var1.get(var9);
               TransitionValues var7 = (TransitionValues)var2.get(var8);
               if (var10 != null && var7 != null) {
                  this.mStartValuesList.add(var10);
                  this.mEndValuesList.add(var7);
                  var1.remove(var9);
                  var2.remove(var8);
               }
            }
         }
      }

   }

   private void matchInstances(ArrayMap var1, ArrayMap var2) {
      for(int var3 = var1.size() - 1; var3 >= 0; --var3) {
         View var4 = (View)var1.keyAt(var3);
         if (var4 != null && this.isValidTarget(var4)) {
            TransitionValues var5 = (TransitionValues)var2.remove(var4);
            if (var5 != null && var5.view != null && this.isValidTarget(var5.view)) {
               TransitionValues var6 = (TransitionValues)var1.removeAt(var3);
               this.mStartValuesList.add(var6);
               this.mEndValuesList.add(var5);
            }
         }
      }

   }

   private void matchItemIds(ArrayMap var1, ArrayMap var2, LongSparseArray var3, LongSparseArray var4) {
      int var6 = var3.size();

      for(int var5 = 0; var5 < var6; ++var5) {
         View var8 = (View)var3.valueAt(var5);
         if (var8 != null && this.isValidTarget(var8)) {
            View var10 = (View)var4.get(var3.keyAt(var5));
            if (var10 != null && this.isValidTarget(var10)) {
               TransitionValues var7 = (TransitionValues)var1.get(var8);
               TransitionValues var9 = (TransitionValues)var2.get(var10);
               if (var7 != null && var9 != null) {
                  this.mStartValuesList.add(var7);
                  this.mEndValuesList.add(var9);
                  var1.remove(var8);
                  var2.remove(var10);
               }
            }
         }
      }

   }

   private void matchNames(ArrayMap var1, ArrayMap var2, ArrayMap var3, ArrayMap var4) {
      int var6 = var3.size();

      for(int var5 = 0; var5 < var6; ++var5) {
         View var7 = (View)var3.valueAt(var5);
         if (var7 != null && this.isValidTarget(var7)) {
            View var8 = (View)var4.get(var3.keyAt(var5));
            if (var8 != null && this.isValidTarget(var8)) {
               TransitionValues var10 = (TransitionValues)var1.get(var7);
               TransitionValues var9 = (TransitionValues)var2.get(var8);
               if (var10 != null && var9 != null) {
                  this.mStartValuesList.add(var10);
                  this.mEndValuesList.add(var9);
                  var1.remove(var7);
                  var2.remove(var8);
               }
            }
         }
      }

   }

   private void matchStartAndEnd(TransitionValuesMaps var1, TransitionValuesMaps var2) {
      ArrayMap var5 = new ArrayMap(var1.mViewValues);
      ArrayMap var7 = new ArrayMap(var2.mViewValues);
      int var3 = 0;

      while(true) {
         int[] var6 = this.mMatchOrder;
         if (var3 >= var6.length) {
            this.addUnmatched(var5, var7);
            return;
         }

         int var4 = var6[var3];
         if (var4 != 1) {
            if (var4 != 2) {
               if (var4 != 3) {
                  if (var4 == 4) {
                     this.matchItemIds(var5, var7, var1.mItemIdValues, var2.mItemIdValues);
                  }
               } else {
                  this.matchIds(var5, var7, var1.mIdValues, var2.mIdValues);
               }
            } else {
               this.matchNames(var5, var7, var1.mNameValues, var2.mNameValues);
            }
         } else {
            this.matchInstances(var5, var7);
         }

         ++var3;
      }
   }

   private static int[] parseMatchOrder(String var0) {
      StringTokenizer var3 = new StringTokenizer(var0, ",");
      int[] var4 = new int[var3.countTokens()];

      for(int var1 = 0; var3.hasMoreTokens(); ++var1) {
         String var2 = var3.nextToken().trim();
         if ("id".equalsIgnoreCase(var2)) {
            var4[var1] = 3;
         } else if ("instance".equalsIgnoreCase(var2)) {
            var4[var1] = 1;
         } else if ("name".equalsIgnoreCase(var2)) {
            var4[var1] = 2;
         } else if ("itemId".equalsIgnoreCase(var2)) {
            var4[var1] = 4;
         } else {
            if (!var2.isEmpty()) {
               throw new InflateException("Unknown match type in matchOrder: '" + var2 + "'");
            }

            int[] var5 = new int[var4.length - 1];
            System.arraycopy(var4, 0, var5, 0, var1);
            --var1;
            var4 = var5;
         }
      }

      return var4;
   }

   private void runAnimator(Animator var1, ArrayMap var2) {
      if (var1 != null) {
         var1.addListener(new AnimatorListenerAdapter(this, var2) {
            final Transition this$0;
            final ArrayMap val$runningAnimators;

            {
               this.this$0 = var1;
               this.val$runningAnimators = var2;
            }

            public void onAnimationEnd(Animator var1) {
               this.val$runningAnimators.remove(var1);
               this.this$0.mCurrentAnimators.remove(var1);
            }

            public void onAnimationStart(Animator var1) {
               this.this$0.mCurrentAnimators.add(var1);
            }
         });
         this.animate(var1);
      }

   }

   public Transition addListener(TransitionListener var1) {
      if (this.mListeners == null) {
         this.mListeners = new ArrayList();
      }

      this.mListeners.add(var1);
      return this;
   }

   public Transition addTarget(int var1) {
      if (var1 != 0) {
         this.mTargetIds.add(var1);
      }

      return this;
   }

   public Transition addTarget(View var1) {
      this.mTargets.add(var1);
      return this;
   }

   public Transition addTarget(Class var1) {
      if (this.mTargetTypes == null) {
         this.mTargetTypes = new ArrayList();
      }

      this.mTargetTypes.add(var1);
      return this;
   }

   public Transition addTarget(String var1) {
      if (this.mTargetNames == null) {
         this.mTargetNames = new ArrayList();
      }

      this.mTargetNames.add(var1);
      return this;
   }

   protected void animate(Animator var1) {
      if (var1 == null) {
         this.end();
      } else {
         if (this.getDuration() >= 0L) {
            var1.setDuration(this.getDuration());
         }

         if (this.getStartDelay() >= 0L) {
            var1.setStartDelay(this.getStartDelay());
         }

         if (this.getInterpolator() != null) {
            var1.setInterpolator(this.getInterpolator());
         }

         var1.addListener(new AnimatorListenerAdapter(this) {
            final Transition this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationEnd(Animator var1) {
               this.this$0.end();
               var1.removeListener(this);
            }
         });
         var1.start();
      }

   }

   protected void cancel() {
      int var1;
      for(var1 = this.mCurrentAnimators.size() - 1; var1 >= 0; --var1) {
         ((Animator)this.mCurrentAnimators.get(var1)).cancel();
      }

      ArrayList var3 = this.mListeners;
      if (var3 != null && var3.size() > 0) {
         var3 = (ArrayList)this.mListeners.clone();
         int var2 = var3.size();

         for(var1 = 0; var1 < var2; ++var1) {
            ((TransitionListener)var3.get(var1)).onTransitionCancel(this);
         }
      }

   }

   public abstract void captureEndValues(TransitionValues var1);

   void capturePropagationValues(TransitionValues var1) {
      if (this.mPropagation != null && !var1.values.isEmpty()) {
         String[] var4 = this.mPropagation.getPropagationProperties();
         if (var4 == null) {
            return;
         }

         boolean var3 = false;
         int var2 = 0;

         boolean var5;
         while(true) {
            if (var2 >= var4.length) {
               var5 = true;
               break;
            }

            if (!var1.values.containsKey(var4[var2])) {
               var5 = var3;
               break;
            }

            ++var2;
         }

         if (!var5) {
            this.mPropagation.captureValues(var1);
         }
      }

   }

   public abstract void captureStartValues(TransitionValues var1);

   void captureValues(ViewGroup var1, boolean var2) {
      int var3;
      byte var5;
      View var12;
      label98: {
         this.clearValues(var2);
         var3 = this.mTargetIds.size();
         var5 = 0;
         if (var3 > 0 || this.mTargets.size() > 0) {
            ArrayList var7 = this.mTargetNames;
            if (var7 == null || var7.isEmpty()) {
               var7 = this.mTargetTypes;
               if (var7 == null || var7.isEmpty()) {
                  for(var3 = 0; var3 < this.mTargetIds.size(); ++var3) {
                     var12 = var1.findViewById((Integer)this.mTargetIds.get(var3));
                     if (var12 != null) {
                        TransitionValues var8 = new TransitionValues();
                        var8.view = var12;
                        if (var2) {
                           this.captureStartValues(var8);
                        } else {
                           this.captureEndValues(var8);
                        }

                        var8.mTargetedTransitions.add(this);
                        this.capturePropagationValues(var8);
                        if (var2) {
                           addViewValues(this.mStartValues, var12, var8);
                        } else {
                           addViewValues(this.mEndValues, var12, var8);
                        }
                     }
                  }

                  var3 = 0;

                  while(true) {
                     if (var3 >= this.mTargets.size()) {
                        break label98;
                     }

                     View var9 = (View)this.mTargets.get(var3);
                     TransitionValues var13 = new TransitionValues();
                     var13.view = var9;
                     if (var2) {
                        this.captureStartValues(var13);
                     } else {
                        this.captureEndValues(var13);
                     }

                     var13.mTargetedTransitions.add(this);
                     this.capturePropagationValues(var13);
                     if (var2) {
                        addViewValues(this.mStartValues, var9, var13);
                     } else {
                        addViewValues(this.mEndValues, var9, var13);
                     }

                     ++var3;
                  }
               }
            }
         }

         this.captureHierarchy(var1, var2);
      }

      if (!var2) {
         ArrayMap var10 = this.mNameOverrides;
         if (var10 != null) {
            int var6 = var10.size();
            ArrayList var11 = new ArrayList(var6);
            int var4 = 0;

            while(true) {
               var3 = var5;
               if (var4 >= var6) {
                  for(; var3 < var6; ++var3) {
                     var12 = (View)var11.get(var3);
                     if (var12 != null) {
                        String var15 = (String)this.mNameOverrides.valueAt(var3);
                        this.mStartValues.mNameValues.put(var15, var12);
                     }
                  }
                  break;
               }

               String var14 = (String)this.mNameOverrides.keyAt(var4);
               var11.add(this.mStartValues.mNameValues.remove(var14));
               ++var4;
            }
         }
      }

   }

   void clearValues(boolean var1) {
      if (var1) {
         this.mStartValues.mViewValues.clear();
         this.mStartValues.mIdValues.clear();
         this.mStartValues.mItemIdValues.clear();
      } else {
         this.mEndValues.mViewValues.clear();
         this.mEndValues.mIdValues.clear();
         this.mEndValues.mItemIdValues.clear();
      }

   }

   public Transition clone() {
      try {
         Transition var1 = (Transition)super.clone();
         ArrayList var2 = new ArrayList();
         var1.mAnimators = var2;
         TransitionValuesMaps var4 = new TransitionValuesMaps();
         var1.mStartValues = var4;
         var4 = new TransitionValuesMaps();
         var1.mEndValues = var4;
         var1.mStartValuesList = null;
         var1.mEndValuesList = null;
         return var1;
      } catch (CloneNotSupportedException var3) {
         return null;
      }
   }

   public Animator createAnimator(ViewGroup var1, TransitionValues var2, TransitionValues var3) {
      return null;
   }

   protected void createAnimators(ViewGroup var1, TransitionValuesMaps var2, TransitionValuesMaps var3, ArrayList var4, ArrayList var5) {
      ArrayMap var20 = getRunningAnimators();
      SparseIntArray var19 = new SparseIntArray();
      int var8 = var4.size();
      long var10 = Long.MAX_VALUE;

      int var6;
      long var12;
      int var25;
      for(var6 = 0; var6 < var8; var10 = var12) {
         TransitionValues var14 = (TransitionValues)var4.get(var6);
         TransitionValues var23 = (TransitionValues)var5.get(var6);
         TransitionValues var15 = var14;
         if (var14 != null) {
            var15 = var14;
            if (!var14.mTargetedTransitions.contains(this)) {
               var15 = null;
            }
         }

         TransitionValues var16 = var23;
         if (var23 != null) {
            var16 = var23;
            if (!var23.mTargetedTransitions.contains(this)) {
               var16 = null;
            }
         }

         label120: {
            if (var15 != null || var16 != null) {
               boolean var7;
               if (var15 != null && var16 != null && !this.isTransitionRequired(var15, var16)) {
                  var7 = false;
               } else {
                  var7 = true;
               }

               if (var7) {
                  Animator var24 = this.createAnimator(var1, var15, var16);
                  if (var24 != null) {
                     View var18;
                     Animator var27;
                     View var29;
                     if (var16 != null) {
                        var18 = var16.view;
                        String[] var21 = this.getTransitionProperties();
                        if (var18 != null && var21 != null && var21.length > 0) {
                           TransitionValues var17 = new TransitionValues();
                           var17.view = var18;
                           var14 = (TransitionValues)var3.mViewValues.get(var18);
                           var25 = var6;
                           int var9;
                           if (var14 != null) {
                              var9 = 0;

                              while(true) {
                                 var25 = var6;
                                 if (var9 >= var21.length) {
                                    break;
                                 }

                                 var17.values.put(var21[var9], var14.values.get(var21[var9]));
                                 ++var9;
                              }
                           }

                           var6 = var25;
                           var9 = var20.size();
                           var25 = 0;

                           while(true) {
                              if (var25 >= var9) {
                                 var14 = var17;
                                 break;
                              }

                              AnimationInfo var26 = (AnimationInfo)var20.get((Animator)var20.keyAt(var25));
                              if (var26.mValues != null && var26.mView == var18 && var26.mName.equals(this.getName()) && var26.mValues.equals(var17)) {
                                 var24 = null;
                                 var14 = var17;
                                 break;
                              }

                              ++var25;
                           }
                        } else {
                           var14 = null;
                        }

                        var23 = var14;
                        var27 = var24;
                        var29 = var18;
                     } else {
                        var29 = var15.view;
                        var18 = null;
                        var27 = var24;
                        var23 = var18;
                     }

                     var12 = var10;
                     var25 = var6;
                     if (var27 != null) {
                        TransitionPropagation var30 = this.mPropagation;
                        var12 = var10;
                        if (var30 != null) {
                           var12 = var30.getStartDelay(var1, this, var15, var16);
                           var19.put(this.mAnimators.size(), (int)var12);
                           var12 = Math.min(var12, var10);
                        }

                        var20.put(var27, new AnimationInfo(var29, this.getName(), this, ViewUtils.getWindowId(var1), var23));
                        this.mAnimators.add(var27);
                        var25 = var6;
                     }
                     break label120;
                  }
               }
            }

            var12 = var10;
            var25 = var6;
         }

         var6 = var25 + 1;
      }

      if (var10 != 0L) {
         for(var6 = 0; var6 < var19.size(); ++var6) {
            var25 = var19.keyAt(var6);
            Animator var22 = (Animator)this.mAnimators.get(var25);
            var22.setStartDelay((long)var19.valueAt(var6) - var10 + var22.getStartDelay());
         }
      }

   }

   protected void end() {
      int var1 = this.mNumInstances - 1;
      this.mNumInstances = var1;
      if (var1 == 0) {
         ArrayList var3 = this.mListeners;
         if (var3 != null && var3.size() > 0) {
            var3 = (ArrayList)this.mListeners.clone();
            int var2 = var3.size();

            for(var1 = 0; var1 < var2; ++var1) {
               ((TransitionListener)var3.get(var1)).onTransitionEnd(this);
            }
         }

         View var4;
         for(var1 = 0; var1 < this.mStartValues.mItemIdValues.size(); ++var1) {
            var4 = (View)this.mStartValues.mItemIdValues.valueAt(var1);
            if (var4 != null) {
               ViewCompat.setHasTransientState(var4, false);
            }
         }

         for(var1 = 0; var1 < this.mEndValues.mItemIdValues.size(); ++var1) {
            var4 = (View)this.mEndValues.mItemIdValues.valueAt(var1);
            if (var4 != null) {
               ViewCompat.setHasTransientState(var4, false);
            }
         }

         this.mEnded = true;
      }

   }

   public Transition excludeChildren(int var1, boolean var2) {
      this.mTargetIdChildExcludes = this.excludeId(this.mTargetIdChildExcludes, var1, var2);
      return this;
   }

   public Transition excludeChildren(View var1, boolean var2) {
      this.mTargetChildExcludes = this.excludeView(this.mTargetChildExcludes, var1, var2);
      return this;
   }

   public Transition excludeChildren(Class var1, boolean var2) {
      this.mTargetTypeChildExcludes = this.excludeType(this.mTargetTypeChildExcludes, var1, var2);
      return this;
   }

   public Transition excludeTarget(int var1, boolean var2) {
      this.mTargetIdExcludes = this.excludeId(this.mTargetIdExcludes, var1, var2);
      return this;
   }

   public Transition excludeTarget(View var1, boolean var2) {
      this.mTargetExcludes = this.excludeView(this.mTargetExcludes, var1, var2);
      return this;
   }

   public Transition excludeTarget(Class var1, boolean var2) {
      this.mTargetTypeExcludes = this.excludeType(this.mTargetTypeExcludes, var1, var2);
      return this;
   }

   public Transition excludeTarget(String var1, boolean var2) {
      this.mTargetNameExcludes = excludeObject(this.mTargetNameExcludes, var1, var2);
      return this;
   }

   void forceToEnd(ViewGroup var1) {
      ArrayMap var3 = getRunningAnimators();
      int var2 = var3.size();
      if (var1 != null) {
         WindowIdImpl var5 = ViewUtils.getWindowId(var1);
         --var2;

         for(; var2 >= 0; --var2) {
            AnimationInfo var4 = (AnimationInfo)var3.valueAt(var2);
            if (var4.mView != null && var5 != null && var5.equals(var4.mWindowId)) {
               ((Animator)var3.keyAt(var2)).end();
            }
         }
      }

   }

   public long getDuration() {
      return this.mDuration;
   }

   public Rect getEpicenter() {
      EpicenterCallback var1 = this.mEpicenterCallback;
      return var1 == null ? null : var1.onGetEpicenter(this);
   }

   public EpicenterCallback getEpicenterCallback() {
      return this.mEpicenterCallback;
   }

   public TimeInterpolator getInterpolator() {
      return this.mInterpolator;
   }

   TransitionValues getMatchedTransitionValues(View var1, boolean var2) {
      TransitionSet var7 = this.mParent;
      if (var7 != null) {
         return var7.getMatchedTransitionValues(var1, var2);
      } else {
         ArrayList var12;
         if (var2) {
            var12 = this.mStartValuesList;
         } else {
            var12 = this.mEndValuesList;
         }

         Object var8 = null;
         if (var12 == null) {
            return null;
         } else {
            int var6 = var12.size();
            byte var5 = -1;
            int var3 = 0;

            int var4;
            while(true) {
               var4 = var5;
               if (var3 >= var6) {
                  break;
               }

               TransitionValues var9 = (TransitionValues)var12.get(var3);
               if (var9 == null) {
                  return null;
               }

               if (var9.view == var1) {
                  var4 = var3;
                  break;
               }

               ++var3;
            }

            TransitionValues var10 = (TransitionValues)var8;
            if (var4 >= 0) {
               ArrayList var11;
               if (var2) {
                  var11 = this.mEndValuesList;
               } else {
                  var11 = this.mStartValuesList;
               }

               var10 = (TransitionValues)var11.get(var4);
            }

            return var10;
         }
      }
   }

   public String getName() {
      return this.mName;
   }

   public PathMotion getPathMotion() {
      return this.mPathMotion;
   }

   public TransitionPropagation getPropagation() {
      return this.mPropagation;
   }

   public long getStartDelay() {
      return this.mStartDelay;
   }

   public List getTargetIds() {
      return this.mTargetIds;
   }

   public List getTargetNames() {
      return this.mTargetNames;
   }

   public List getTargetTypes() {
      return this.mTargetTypes;
   }

   public List getTargets() {
      return this.mTargets;
   }

   public String[] getTransitionProperties() {
      return null;
   }

   public TransitionValues getTransitionValues(View var1, boolean var2) {
      TransitionSet var3 = this.mParent;
      if (var3 != null) {
         return var3.getTransitionValues(var1, var2);
      } else {
         TransitionValuesMaps var4;
         if (var2) {
            var4 = this.mStartValues;
         } else {
            var4 = this.mEndValues;
         }

         return (TransitionValues)var4.mViewValues.get(var1);
      }
   }

   public boolean isTransitionRequired(TransitionValues var1, TransitionValues var2) {
      boolean var6 = false;
      boolean var5 = var6;
      if (var1 != null) {
         var5 = var6;
         if (var2 != null) {
            String[] var7 = this.getTransitionProperties();
            if (var7 != null) {
               int var4 = var7.length;
               int var3 = 0;

               while(true) {
                  var5 = var6;
                  if (var3 >= var4) {
                     return var5;
                  }

                  if (isValueChanged(var1, var2, var7[var3])) {
                     break;
                  }

                  ++var3;
               }
            } else {
               Iterator var8 = var1.values.keySet().iterator();

               do {
                  var5 = var6;
                  if (!var8.hasNext()) {
                     return var5;
                  }
               } while(!isValueChanged(var1, var2, (String)var8.next()));
            }

            var5 = true;
         }
      }

      return var5;
   }

   boolean isValidTarget(View var1) {
      int var4 = var1.getId();
      ArrayList var5 = this.mTargetIdExcludes;
      if (var5 != null && var5.contains(var4)) {
         return false;
      } else {
         var5 = this.mTargetExcludes;
         if (var5 != null && var5.contains(var1)) {
            return false;
         } else {
            var5 = this.mTargetTypeExcludes;
            int var2;
            if (var5 != null) {
               int var3 = var5.size();

               for(var2 = 0; var2 < var3; ++var2) {
                  if (((Class)this.mTargetTypeExcludes.get(var2)).isInstance(var1)) {
                     return false;
                  }
               }
            }

            if (this.mTargetNameExcludes != null && ViewCompat.getTransitionName(var1) != null && this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(var1))) {
               return false;
            } else {
               if (this.mTargetIds.size() == 0 && this.mTargets.size() == 0) {
                  var5 = this.mTargetTypes;
                  if (var5 == null || var5.isEmpty()) {
                     var5 = this.mTargetNames;
                     if (var5 == null || var5.isEmpty()) {
                        return true;
                     }
                  }
               }

               if (!this.mTargetIds.contains(var4) && !this.mTargets.contains(var1)) {
                  var5 = this.mTargetNames;
                  if (var5 != null && var5.contains(ViewCompat.getTransitionName(var1))) {
                     return true;
                  } else {
                     if (this.mTargetTypes != null) {
                        for(var2 = 0; var2 < this.mTargetTypes.size(); ++var2) {
                           if (((Class)this.mTargetTypes.get(var2)).isInstance(var1)) {
                              return true;
                           }
                        }
                     }

                     return false;
                  }
               } else {
                  return true;
               }
            }
         }
      }
   }

   public void pause(View var1) {
      if (!this.mEnded) {
         ArrayMap var4 = getRunningAnimators();
         int var2 = var4.size();
         WindowIdImpl var5 = ViewUtils.getWindowId(var1);
         --var2;

         for(; var2 >= 0; --var2) {
            AnimationInfo var6 = (AnimationInfo)var4.valueAt(var2);
            if (var6.mView != null && var5.equals(var6.mWindowId)) {
               AnimatorUtils.pause((Animator)var4.keyAt(var2));
            }
         }

         ArrayList var7 = this.mListeners;
         if (var7 != null && var7.size() > 0) {
            var7 = (ArrayList)this.mListeners.clone();
            int var3 = var7.size();

            for(var2 = 0; var2 < var3; ++var2) {
               ((TransitionListener)var7.get(var2)).onTransitionPause(this);
            }
         }

         this.mPaused = true;
      }

   }

   void playTransition(ViewGroup var1) {
      this.mStartValuesList = new ArrayList();
      this.mEndValuesList = new ArrayList();
      this.matchStartAndEnd(this.mStartValues, this.mEndValues);
      ArrayMap var7 = getRunningAnimators();
      int var2 = var7.size();
      WindowIdImpl var8 = ViewUtils.getWindowId(var1);
      --var2;

      for(; var2 >= 0; --var2) {
         Animator var9 = (Animator)var7.keyAt(var2);
         if (var9 != null) {
            AnimationInfo var5 = (AnimationInfo)var7.get(var9);
            if (var5 != null && var5.mView != null && var8.equals(var5.mWindowId)) {
               TransitionValues var4 = var5.mValues;
               View var10 = var5.mView;
               TransitionValues var6 = this.getTransitionValues(var10, true);
               TransitionValues var11 = this.getMatchedTransitionValues(var10, true);
               boolean var3;
               if ((var6 != null || var11 != null) && var5.mTransition.isTransitionRequired(var4, var11)) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               if (var3) {
                  if (!var9.isRunning() && !var9.isStarted()) {
                     var7.remove(var9);
                  } else {
                     var9.cancel();
                  }
               }
            }
         }
      }

      this.createAnimators(var1, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
      this.runAnimators();
   }

   public Transition removeListener(TransitionListener var1) {
      ArrayList var2 = this.mListeners;
      if (var2 == null) {
         return this;
      } else {
         var2.remove(var1);
         if (this.mListeners.size() == 0) {
            this.mListeners = null;
         }

         return this;
      }
   }

   public Transition removeTarget(int var1) {
      if (var1 != 0) {
         this.mTargetIds.remove(var1);
      }

      return this;
   }

   public Transition removeTarget(View var1) {
      this.mTargets.remove(var1);
      return this;
   }

   public Transition removeTarget(Class var1) {
      ArrayList var2 = this.mTargetTypes;
      if (var2 != null) {
         var2.remove(var1);
      }

      return this;
   }

   public Transition removeTarget(String var1) {
      ArrayList var2 = this.mTargetNames;
      if (var2 != null) {
         var2.remove(var1);
      }

      return this;
   }

   public void resume(View var1) {
      if (this.mPaused) {
         if (!this.mEnded) {
            ArrayMap var4 = getRunningAnimators();
            int var2 = var4.size();
            WindowIdImpl var6 = ViewUtils.getWindowId(var1);
            --var2;

            for(; var2 >= 0; --var2) {
               AnimationInfo var5 = (AnimationInfo)var4.valueAt(var2);
               if (var5.mView != null && var6.equals(var5.mWindowId)) {
                  AnimatorUtils.resume((Animator)var4.keyAt(var2));
               }
            }

            ArrayList var7 = this.mListeners;
            if (var7 != null && var7.size() > 0) {
               var7 = (ArrayList)this.mListeners.clone();
               int var3 = var7.size();

               for(var2 = 0; var2 < var3; ++var2) {
                  ((TransitionListener)var7.get(var2)).onTransitionResume(this);
               }
            }
         }

         this.mPaused = false;
      }

   }

   protected void runAnimators() {
      this.start();
      ArrayMap var1 = getRunningAnimators();
      Iterator var2 = this.mAnimators.iterator();

      while(var2.hasNext()) {
         Animator var3 = (Animator)var2.next();
         if (var1.containsKey(var3)) {
            this.start();
            this.runAnimator(var3, var1);
         }
      }

      this.mAnimators.clear();
      this.end();
   }

   void setCanRemoveViews(boolean var1) {
      this.mCanRemoveViews = var1;
   }

   public Transition setDuration(long var1) {
      this.mDuration = var1;
      return this;
   }

   public void setEpicenterCallback(EpicenterCallback var1) {
      this.mEpicenterCallback = var1;
   }

   public Transition setInterpolator(TimeInterpolator var1) {
      this.mInterpolator = var1;
      return this;
   }

   public void setMatchOrder(int... var1) {
      if (var1 != null && var1.length != 0) {
         for(int var2 = 0; var2 < var1.length; ++var2) {
            if (!isValidMatch(var1[var2])) {
               throw new IllegalArgumentException("matches contains invalid value");
            }

            if (alreadyContains(var1, var2)) {
               throw new IllegalArgumentException("matches contains a duplicate value");
            }
         }

         this.mMatchOrder = (int[])var1.clone();
      } else {
         this.mMatchOrder = DEFAULT_MATCH_ORDER;
      }

   }

   public void setPathMotion(PathMotion var1) {
      if (var1 == null) {
         this.mPathMotion = STRAIGHT_PATH_MOTION;
      } else {
         this.mPathMotion = var1;
      }

   }

   public void setPropagation(TransitionPropagation var1) {
      this.mPropagation = var1;
   }

   Transition setSceneRoot(ViewGroup var1) {
      this.mSceneRoot = var1;
      return this;
   }

   public Transition setStartDelay(long var1) {
      this.mStartDelay = var1;
      return this;
   }

   protected void start() {
      if (this.mNumInstances == 0) {
         ArrayList var3 = this.mListeners;
         if (var3 != null && var3.size() > 0) {
            var3 = (ArrayList)this.mListeners.clone();
            int var2 = var3.size();

            for(int var1 = 0; var1 < var2; ++var1) {
               ((TransitionListener)var3.get(var1)).onTransitionStart(this);
            }
         }

         this.mEnded = false;
      }

      ++this.mNumInstances;
   }

   public String toString() {
      return this.toString("");
   }

   String toString(String var1) {
      var1 = var1 + this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()) + ": ";
      String var4 = var1;
      if (this.mDuration != -1L) {
         var4 = var1 + "dur(" + this.mDuration + ") ";
      }

      var1 = var4;
      if (this.mStartDelay != -1L) {
         var1 = var4 + "dly(" + this.mStartDelay + ") ";
      }

      var4 = var1;
      if (this.mInterpolator != null) {
         var4 = var1 + "interp(" + this.mInterpolator + ") ";
      }

      if (this.mTargetIds.size() <= 0) {
         var1 = var4;
         if (this.mTargets.size() <= 0) {
            return var1;
         }
      }

      var4 = var4 + "tgts(";
      int var2 = this.mTargetIds.size();
      byte var3 = 0;
      var1 = var4;
      if (var2 > 0) {
         var2 = 0;

         while(true) {
            var1 = var4;
            if (var2 >= this.mTargetIds.size()) {
               break;
            }

            var1 = var4;
            if (var2 > 0) {
               var1 = var4 + ", ";
            }

            var4 = var1 + this.mTargetIds.get(var2);
            ++var2;
         }
      }

      var4 = var1;
      if (this.mTargets.size() > 0) {
         var2 = var3;

         while(true) {
            var4 = var1;
            if (var2 >= this.mTargets.size()) {
               break;
            }

            var4 = var1;
            if (var2 > 0) {
               var4 = var1 + ", ";
            }

            var1 = var4 + this.mTargets.get(var2);
            ++var2;
         }
      }

      var1 = var4 + ")";
      return var1;
   }

   private static class AnimationInfo {
      String mName;
      Transition mTransition;
      TransitionValues mValues;
      View mView;
      WindowIdImpl mWindowId;

      AnimationInfo(View var1, String var2, Transition var3, WindowIdImpl var4, TransitionValues var5) {
         this.mView = var1;
         this.mName = var2;
         this.mValues = var5;
         this.mWindowId = var4;
         this.mTransition = var3;
      }
   }

   private static class ArrayListManager {
      static ArrayList add(ArrayList var0, Object var1) {
         ArrayList var2 = var0;
         if (var0 == null) {
            var2 = new ArrayList();
         }

         if (!var2.contains(var1)) {
            var2.add(var1);
         }

         return var2;
      }

      static ArrayList remove(ArrayList var0, Object var1) {
         ArrayList var2 = var0;
         if (var0 != null) {
            var0.remove(var1);
            var2 = var0;
            if (var0.isEmpty()) {
               var2 = null;
            }
         }

         return var2;
      }
   }

   public abstract static class EpicenterCallback {
      public abstract Rect onGetEpicenter(Transition var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface MatchOrder {
   }

   public interface TransitionListener {
      void onTransitionCancel(Transition var1);

      void onTransitionEnd(Transition var1);

      void onTransitionPause(Transition var1);

      void onTransitionResume(Transition var1);

      void onTransitionStart(Transition var1);
   }
}
