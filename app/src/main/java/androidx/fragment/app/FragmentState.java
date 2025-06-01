package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

final class FragmentState implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public FragmentState createFromParcel(Parcel var1) {
         return new FragmentState(var1);
      }

      public FragmentState[] newArray(int var1) {
         return new FragmentState[var1];
      }
   };
   final Bundle mArguments;
   final String mClassName;
   final int mContainerId;
   final boolean mDetached;
   final int mFragmentId;
   final boolean mFromLayout;
   final boolean mHidden;
   final int mMaxLifecycleState;
   final boolean mRemoving;
   final boolean mRetainInstance;
   Bundle mSavedFragmentState;
   final String mTag;
   final String mWho;

   FragmentState(Parcel var1) {
      this.mClassName = var1.readString();
      this.mWho = var1.readString();
      int var2 = var1.readInt();
      boolean var4 = true;
      boolean var3;
      if (var2 != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.mFromLayout = var3;
      this.mFragmentId = var1.readInt();
      this.mContainerId = var1.readInt();
      this.mTag = var1.readString();
      if (var1.readInt() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.mRetainInstance = var3;
      if (var1.readInt() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.mRemoving = var3;
      if (var1.readInt() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.mDetached = var3;
      this.mArguments = var1.readBundle();
      if (var1.readInt() != 0) {
         var3 = var4;
      } else {
         var3 = false;
      }

      this.mHidden = var3;
      this.mSavedFragmentState = var1.readBundle();
      this.mMaxLifecycleState = var1.readInt();
   }

   FragmentState(Fragment var1) {
      this.mClassName = var1.getClass().getName();
      this.mWho = var1.mWho;
      this.mFromLayout = var1.mFromLayout;
      this.mFragmentId = var1.mFragmentId;
      this.mContainerId = var1.mContainerId;
      this.mTag = var1.mTag;
      this.mRetainInstance = var1.mRetainInstance;
      this.mRemoving = var1.mRemoving;
      this.mDetached = var1.mDetached;
      this.mArguments = var1.mArguments;
      this.mHidden = var1.mHidden;
      this.mMaxLifecycleState = var1.mMaxState.ordinal();
   }

   public int describeContents() {
      return 0;
   }

   public String toString() {
      StringBuilder var1 = new StringBuilder(128);
      var1.append("FragmentState{");
      var1.append(this.mClassName);
      var1.append(" (");
      var1.append(this.mWho);
      var1.append(")}:");
      if (this.mFromLayout) {
         var1.append(" fromLayout");
      }

      if (this.mContainerId != 0) {
         var1.append(" id=0x");
         var1.append(Integer.toHexString(this.mContainerId));
      }

      String var2 = this.mTag;
      if (var2 != null && !var2.isEmpty()) {
         var1.append(" tag=");
         var1.append(this.mTag);
      }

      if (this.mRetainInstance) {
         var1.append(" retainInstance");
      }

      if (this.mRemoving) {
         var1.append(" removing");
      }

      if (this.mDetached) {
         var1.append(" detached");
      }

      if (this.mHidden) {
         var1.append(" hidden");
      }

      return var1.toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.mClassName);
      var1.writeString(this.mWho);
      var1.writeInt(this.mFromLayout);
      var1.writeInt(this.mFragmentId);
      var1.writeInt(this.mContainerId);
      var1.writeString(this.mTag);
      var1.writeInt(this.mRetainInstance);
      var1.writeInt(this.mRemoving);
      var1.writeInt(this.mDetached);
      var1.writeBundle(this.mArguments);
      var1.writeInt(this.mHidden);
      var1.writeBundle(this.mSavedFragmentState);
      var1.writeInt(this.mMaxLifecycleState);
   }
}
