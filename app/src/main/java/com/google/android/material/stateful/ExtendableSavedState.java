package com.google.android.material.stateful;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.SimpleArrayMap;
import androidx.customview.view.AbsSavedState;

public class ExtendableSavedState extends AbsSavedState {
   public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
      public ExtendableSavedState createFromParcel(Parcel var1) {
         return new ExtendableSavedState(var1, (ClassLoader)null);
      }

      public ExtendableSavedState createFromParcel(Parcel var1, ClassLoader var2) {
         return new ExtendableSavedState(var1, var2);
      }

      public ExtendableSavedState[] newArray(int var1) {
         return new ExtendableSavedState[var1];
      }
   };
   public final SimpleArrayMap extendableStates;

   private ExtendableSavedState(Parcel var1, ClassLoader var2) {
      super(var1, var2);
      int var4 = var1.readInt();
      String[] var5 = new String[var4];
      var1.readStringArray(var5);
      Bundle[] var6 = new Bundle[var4];
      var1.readTypedArray(var6, Bundle.CREATOR);
      this.extendableStates = new SimpleArrayMap(var4);

      for(int var3 = 0; var3 < var4; ++var3) {
         this.extendableStates.put(var5[var3], var6[var3]);
      }

   }

   // $FF: synthetic method
   ExtendableSavedState(Parcel var1, ClassLoader var2, Object var3) {
      this(var1, var2);
   }

   public ExtendableSavedState(Parcelable var1) {
      super(var1);
      this.extendableStates = new SimpleArrayMap();
   }

   public String toString() {
      return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.extendableStates + "}";
   }

   public void writeToParcel(Parcel var1, int var2) {
      super.writeToParcel(var1, var2);
      int var3 = this.extendableStates.size();
      var1.writeInt(var3);
      String[] var5 = new String[var3];
      Bundle[] var4 = new Bundle[var3];

      for(var2 = 0; var2 < var3; ++var2) {
         var5[var2] = (String)this.extendableStates.keyAt(var2);
         var4[var2] = (Bundle)this.extendableStates.valueAt(var2);
      }

      var1.writeStringArray(var5);
      var1.writeTypedArray(var4, 0);
   }
}
