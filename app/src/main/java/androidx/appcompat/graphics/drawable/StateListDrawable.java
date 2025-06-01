package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.resources.Compatibility;
import androidx.appcompat.resources.R;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.res.TypedArrayUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class StateListDrawable extends DrawableContainer {
   private static final boolean DEBUG = false;
   private static final String TAG = "StateListDrawable";
   private boolean mMutated;
   private StateListState mStateListState;

   StateListDrawable() {
      this((StateListState)null, (Resources)null);
   }

   StateListDrawable(StateListState var1) {
      if (var1 != null) {
         this.setConstantState(var1);
      }

   }

   StateListDrawable(StateListState var1, Resources var2) {
      this.setConstantState(new StateListState(var1, this, var2));
      this.onStateChange(this.getState());
   }

   private void inflateChildElements(Context var1, Resources var2, XmlPullParser var3, AttributeSet var4, Resources.Theme var5) throws XmlPullParserException, IOException {
      StateListState var11 = this.mStateListState;
      int var6 = var3.getDepth() + 1;

      while(true) {
         int var8 = var3.next();
         if (var8 == 1) {
            break;
         }

         int var7 = var3.getDepth();
         if (var7 < var6 && var8 == 3) {
            break;
         }

         if (var8 == 2 && var7 <= var6 && var3.getName().equals("item")) {
            TypedArray var10 = TypedArrayUtils.obtainAttributes(var2, var5, var4, R.styleable.StateListDrawableItem);
            Drawable var9 = null;
            var7 = var10.getResourceId(R.styleable.StateListDrawableItem_android_drawable, -1);
            if (var7 > 0) {
               var9 = ResourceManagerInternal.get().getDrawable(var1, var7);
            }

            var10.recycle();
            int[] var12 = this.extractStateSet(var4);
            Drawable var13 = var9;
            if (var9 == null) {
               while(true) {
                  var7 = var3.next();
                  if (var7 != 4) {
                     if (var7 != 2) {
                        throw new XmlPullParserException(var3.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                     }

                     if (VERSION.SDK_INT >= 21) {
                        var13 = Compatibility.Api21Impl.createFromXmlInner(var2, var3, var4, var5);
                     } else {
                        var13 = Drawable.createFromXmlInner(var2, var3, var4);
                     }
                     break;
                  }
               }
            }

            var11.addStateSet(var12, var13);
         }
      }

   }

   private void updateStateFromTypedArray(TypedArray var1) {
      StateListState var2 = this.mStateListState;
      if (VERSION.SDK_INT >= 21) {
         var2.mChangingConfigurations |= Compatibility.Api21Impl.getChangingConfigurations(var1);
      }

      var2.mVariablePadding = var1.getBoolean(R.styleable.StateListDrawable_android_variablePadding, var2.mVariablePadding);
      var2.mConstantSize = var1.getBoolean(R.styleable.StateListDrawable_android_constantSize, var2.mConstantSize);
      var2.mEnterFadeDuration = var1.getInt(R.styleable.StateListDrawable_android_enterFadeDuration, var2.mEnterFadeDuration);
      var2.mExitFadeDuration = var1.getInt(R.styleable.StateListDrawable_android_exitFadeDuration, var2.mExitFadeDuration);
      var2.mDither = var1.getBoolean(R.styleable.StateListDrawable_android_dither, var2.mDither);
   }

   public void addState(int[] var1, Drawable var2) {
      if (var2 != null) {
         this.mStateListState.addStateSet(var1, var2);
         this.onStateChange(this.getState());
      }

   }

   public void applyTheme(Resources.Theme var1) {
      super.applyTheme(var1);
      this.onStateChange(this.getState());
   }

   void clearMutated() {
      super.clearMutated();
      this.mMutated = false;
   }

   StateListState cloneConstantState() {
      return new StateListState(this.mStateListState, this, (Resources)null);
   }

   int[] extractStateSet(AttributeSet var1) {
      int var6 = var1.getAttributeCount();
      int[] var7 = new int[var6];
      int var2 = 0;

      int var3;
      int var4;
      for(var3 = 0; var2 < var6; var3 = var4) {
         int var5 = var1.getAttributeNameResource(var2);
         var4 = var3;
         if (var5 != 0) {
            var4 = var3;
            if (var5 != 16842960) {
               var4 = var3;
               if (var5 != 16843161) {
                  if (var1.getAttributeBooleanValue(var2, false)) {
                     var4 = var5;
                  } else {
                     var4 = -var5;
                  }

                  var7[var3] = var4;
                  var4 = var3 + 1;
               }
            }
         }

         ++var2;
      }

      return StateSet.trimStateSet(var7, var3);
   }

   int getStateCount() {
      return this.mStateListState.getChildCount();
   }

   Drawable getStateDrawable(int var1) {
      return this.mStateListState.getChild(var1);
   }

   int getStateDrawableIndex(int[] var1) {
      return this.mStateListState.indexOfStateSet(var1);
   }

   StateListState getStateListState() {
      return this.mStateListState;
   }

   int[] getStateSet(int var1) {
      return this.mStateListState.mStateSets[var1];
   }

   public void inflate(Context var1, Resources var2, XmlPullParser var3, AttributeSet var4, Resources.Theme var5) throws XmlPullParserException, IOException {
      TypedArray var6 = TypedArrayUtils.obtainAttributes(var2, var5, var4, R.styleable.StateListDrawable);
      this.setVisible(var6.getBoolean(R.styleable.StateListDrawable_android_visible, true), true);
      this.updateStateFromTypedArray(var6);
      this.updateDensity(var2);
      var6.recycle();
      this.inflateChildElements(var1, var2, var3, var4, var5);
      this.onStateChange(this.getState());
   }

   public boolean isStateful() {
      return true;
   }

   public Drawable mutate() {
      if (!this.mMutated && super.mutate() == this) {
         this.mStateListState.mutate();
         this.mMutated = true;
      }

      return this;
   }

   protected boolean onStateChange(int[] var1) {
      boolean var4 = super.onStateChange(var1);
      int var3 = this.mStateListState.indexOfStateSet(var1);
      int var2 = var3;
      if (var3 < 0) {
         var2 = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
      }

      if (!this.selectDrawable(var2) && !var4) {
         var4 = false;
      } else {
         var4 = true;
      }

      return var4;
   }

   void setConstantState(DrawableContainer.DrawableContainerState var1) {
      super.setConstantState(var1);
      if (var1 instanceof StateListState) {
         this.mStateListState = (StateListState)var1;
      }

   }

   static class StateListState extends DrawableContainer.DrawableContainerState {
      int[][] mStateSets;

      StateListState(StateListState var1, StateListDrawable var2, Resources var3) {
         super(var1, var2, var3);
         if (var1 != null) {
            this.mStateSets = var1.mStateSets;
         } else {
            this.mStateSets = new int[this.getCapacity()][];
         }

      }

      int addStateSet(int[] var1, Drawable var2) {
         int var3 = this.addChild(var2);
         this.mStateSets[var3] = var1;
         return var3;
      }

      public void growArray(int var1, int var2) {
         super.growArray(var1, var2);
         int[][] var3 = new int[var2][];
         System.arraycopy(this.mStateSets, 0, var3, 0, var1);
         this.mStateSets = var3;
      }

      int indexOfStateSet(int[] var1) {
         int[][] var4 = this.mStateSets;
         int var3 = this.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            if (StateSet.stateSetMatches(var4[var2], var1)) {
               return var2;
            }
         }

         return -1;
      }

      void mutate() {
         int[][] var2 = this.mStateSets;
         int[][] var3 = new int[var2.length][];

         for(int var1 = var2.length - 1; var1 >= 0; --var1) {
            int[] var4 = this.mStateSets[var1];
            if (var4 != null) {
               var4 = (int[])var4.clone();
            } else {
               var4 = null;
            }

            var3[var1] = var4;
         }

         this.mStateSets = var3;
      }

      public Drawable newDrawable() {
         return new StateListDrawable(this, (Resources)null);
      }

      public Drawable newDrawable(Resources var1) {
         return new StateListDrawable(this, var1);
      }
   }
}
