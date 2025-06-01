package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

public final class ActivityResult implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public ActivityResult createFromParcel(Parcel var1) {
         return new ActivityResult(var1);
      }

      public ActivityResult[] newArray(int var1) {
         return new ActivityResult[var1];
      }
   };
   private final Intent mData;
   private final int mResultCode;

   public ActivityResult(int var1, Intent var2) {
      this.mResultCode = var1;
      this.mData = var2;
   }

   ActivityResult(Parcel var1) {
      this.mResultCode = var1.readInt();
      Intent var2;
      if (var1.readInt() == 0) {
         var2 = null;
      } else {
         var2 = (Intent)Intent.CREATOR.createFromParcel(var1);
      }

      this.mData = var2;
   }

   public static String resultCodeToString(int var0) {
      if (var0 != -1) {
         return var0 != 0 ? String.valueOf(var0) : "RESULT_CANCELED";
      } else {
         return "RESULT_OK";
      }
   }

   public int describeContents() {
      return 0;
   }

   public Intent getData() {
      return this.mData;
   }

   public int getResultCode() {
      return this.mResultCode;
   }

   public String toString() {
      return "ActivityResult{resultCode=" + resultCodeToString(this.mResultCode) + ", data=" + this.mData + '}';
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.mResultCode);
      byte var3;
      if (this.mData == null) {
         var3 = 0;
      } else {
         var3 = 1;
      }

      var1.writeInt(var3);
      Intent var4 = this.mData;
      if (var4 != null) {
         var4.writeToParcel(var1, var2);
      }

   }
}
