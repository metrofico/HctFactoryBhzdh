package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.collection.SimpleArrayMap;
import java.util.List;

public class MotionSpec {
   private static final String TAG = "MotionSpec";
   private final SimpleArrayMap timings = new SimpleArrayMap();

   private static void addTimingFromAnimator(MotionSpec var0, Animator var1) {
      if (var1 instanceof ObjectAnimator) {
         ObjectAnimator var2 = (ObjectAnimator)var1;
         var0.setTiming(var2.getPropertyName(), MotionTiming.createFromAnimator(var2));
      } else {
         throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + var1);
      }
   }

   public static MotionSpec createFromAttribute(Context var0, TypedArray var1, int var2) {
      if (var1.hasValue(var2)) {
         var2 = var1.getResourceId(var2, 0);
         if (var2 != 0) {
            return createFromResource(var0, var2);
         }
      }

      return null;
   }

   public static MotionSpec createFromResource(Context param0, int param1) {
      // $FF: Couldn't be decompiled
   }

   private static MotionSpec createSpecFromAnimators(List var0) {
      MotionSpec var3 = new MotionSpec();
      int var2 = var0.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         addTimingFromAnimator(var3, (Animator)var0.get(var1));
      }

      return var3;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         MotionSpec var2 = (MotionSpec)var1;
         return this.timings.equals(var2.timings);
      } else {
         return false;
      }
   }

   public MotionTiming getTiming(String var1) {
      if (this.hasTiming(var1)) {
         return (MotionTiming)this.timings.get(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public long getTotalDuration() {
      int var2 = this.timings.size();
      long var3 = 0L;

      for(int var1 = 0; var1 < var2; ++var1) {
         MotionTiming var5 = (MotionTiming)this.timings.valueAt(var1);
         var3 = Math.max(var3, var5.getDelay() + var5.getDuration());
      }

      return var3;
   }

   public boolean hasTiming(String var1) {
      boolean var2;
      if (this.timings.get(var1) != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public int hashCode() {
      return this.timings.hashCode();
   }

   public void setTiming(String var1, MotionTiming var2) {
      this.timings.put(var1, var2);
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append('\n');
      var1.append(this.getClass().getName());
      var1.append('{');
      var1.append(Integer.toHexString(System.identityHashCode(this)));
      var1.append(" timings: ");
      var1.append(this.timings);
      var1.append("}\n");
      return var1.toString();
   }
}
