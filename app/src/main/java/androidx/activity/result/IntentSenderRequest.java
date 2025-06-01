package androidx.activity.result;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;

public final class IntentSenderRequest implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public IntentSenderRequest createFromParcel(Parcel var1) {
         return new IntentSenderRequest(var1);
      }

      public IntentSenderRequest[] newArray(int var1) {
         return new IntentSenderRequest[var1];
      }
   };
   private final Intent mFillInIntent;
   private final int mFlagsMask;
   private final int mFlagsValues;
   private final IntentSender mIntentSender;

   IntentSenderRequest(IntentSender var1, Intent var2, int var3, int var4) {
      this.mIntentSender = var1;
      this.mFillInIntent = var2;
      this.mFlagsMask = var3;
      this.mFlagsValues = var4;
   }

   IntentSenderRequest(Parcel var1) {
      this.mIntentSender = (IntentSender)var1.readParcelable(IntentSender.class.getClassLoader());
      this.mFillInIntent = (Intent)var1.readParcelable(Intent.class.getClassLoader());
      this.mFlagsMask = var1.readInt();
      this.mFlagsValues = var1.readInt();
   }

   public int describeContents() {
      return 0;
   }

   public Intent getFillInIntent() {
      return this.mFillInIntent;
   }

   public int getFlagsMask() {
      return this.mFlagsMask;
   }

   public int getFlagsValues() {
      return this.mFlagsValues;
   }

   public IntentSender getIntentSender() {
      return this.mIntentSender;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeParcelable(this.mIntentSender, var2);
      var1.writeParcelable(this.mFillInIntent, var2);
      var1.writeInt(this.mFlagsMask);
      var1.writeInt(this.mFlagsValues);
   }

   public static final class Builder {
      private Intent mFillInIntent;
      private int mFlagsMask;
      private int mFlagsValues;
      private IntentSender mIntentSender;

      public Builder(PendingIntent var1) {
         this(var1.getIntentSender());
      }

      public Builder(IntentSender var1) {
         this.mIntentSender = var1;
      }

      public IntentSenderRequest build() {
         return new IntentSenderRequest(this.mIntentSender, this.mFillInIntent, this.mFlagsMask, this.mFlagsValues);
      }

      public Builder setFillInIntent(Intent var1) {
         this.mFillInIntent = var1;
         return this;
      }

      public Builder setFlags(int var1, int var2) {
         this.mFlagsValues = var1;
         this.mFlagsMask = var2;
         return this;
      }
   }
}
