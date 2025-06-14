package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.collection.ArrayMap;

class VersionedParcelParcel extends VersionedParcel {
   private static final boolean DEBUG = false;
   private static final String TAG = "VersionedParcelParcel";
   private int mCurrentField;
   private final int mEnd;
   private int mFieldId;
   private int mNextRead;
   private final int mOffset;
   private final Parcel mParcel;
   private final SparseIntArray mPositionLookup;
   private final String mPrefix;

   VersionedParcelParcel(Parcel var1) {
      this(var1, var1.dataPosition(), var1.dataSize(), "", new ArrayMap(), new ArrayMap(), new ArrayMap());
   }

   private VersionedParcelParcel(Parcel var1, int var2, int var3, String var4, ArrayMap var5, ArrayMap var6, ArrayMap var7) {
      super(var5, var6, var7);
      this.mPositionLookup = new SparseIntArray();
      this.mCurrentField = -1;
      this.mFieldId = -1;
      this.mParcel = var1;
      this.mOffset = var2;
      this.mEnd = var3;
      this.mNextRead = var2;
      this.mPrefix = var4;
   }

   public void closeField() {
      int var1 = this.mCurrentField;
      if (var1 >= 0) {
         var1 = this.mPositionLookup.get(var1);
         int var2 = this.mParcel.dataPosition();
         this.mParcel.setDataPosition(var1);
         this.mParcel.writeInt(var2 - var1);
         this.mParcel.setDataPosition(var2);
      }

   }

   protected VersionedParcel createSubParcel() {
      Parcel var4 = this.mParcel;
      int var3 = var4.dataPosition();
      int var2 = this.mNextRead;
      int var1 = var2;
      if (var2 == this.mOffset) {
         var1 = this.mEnd;
      }

      return new VersionedParcelParcel(var4, var3, var1, this.mPrefix + "  ", this.mReadCache, this.mWriteCache, this.mParcelizerCache);
   }

   public boolean readBoolean() {
      boolean var1;
      if (this.mParcel.readInt() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Bundle readBundle() {
      return this.mParcel.readBundle(this.getClass().getClassLoader());
   }

   public byte[] readByteArray() {
      int var1 = this.mParcel.readInt();
      if (var1 < 0) {
         return null;
      } else {
         byte[] var2 = new byte[var1];
         this.mParcel.readByteArray(var2);
         return var2;
      }
   }

   protected CharSequence readCharSequence() {
      return (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.mParcel);
   }

   public double readDouble() {
      return this.mParcel.readDouble();
   }

   public boolean readField(int var1) {
      while(true) {
         int var3 = this.mNextRead;
         int var2 = this.mEnd;
         boolean var4 = true;
         if (var3 >= var2) {
            if (this.mFieldId != var1) {
               var4 = false;
            }

            return var4;
         }

         var2 = this.mFieldId;
         if (var2 == var1) {
            return true;
         }

         if (String.valueOf(var2).compareTo(String.valueOf(var1)) > 0) {
            return false;
         }

         this.mParcel.setDataPosition(this.mNextRead);
         var2 = this.mParcel.readInt();
         this.mFieldId = this.mParcel.readInt();
         this.mNextRead += var2;
      }
   }

   public float readFloat() {
      return this.mParcel.readFloat();
   }

   public int readInt() {
      return this.mParcel.readInt();
   }

   public long readLong() {
      return this.mParcel.readLong();
   }

   public Parcelable readParcelable() {
      return this.mParcel.readParcelable(this.getClass().getClassLoader());
   }

   public String readString() {
      return this.mParcel.readString();
   }

   public IBinder readStrongBinder() {
      return this.mParcel.readStrongBinder();
   }

   public void setOutputField(int var1) {
      this.closeField();
      this.mCurrentField = var1;
      this.mPositionLookup.put(var1, this.mParcel.dataPosition());
      this.writeInt(0);
      this.writeInt(var1);
   }

   public void writeBoolean(boolean var1) {
      this.mParcel.writeInt(var1);
   }

   public void writeBundle(Bundle var1) {
      this.mParcel.writeBundle(var1);
   }

   public void writeByteArray(byte[] var1) {
      if (var1 != null) {
         this.mParcel.writeInt(var1.length);
         this.mParcel.writeByteArray(var1);
      } else {
         this.mParcel.writeInt(-1);
      }

   }

   public void writeByteArray(byte[] var1, int var2, int var3) {
      if (var1 != null) {
         this.mParcel.writeInt(var1.length);
         this.mParcel.writeByteArray(var1, var2, var3);
      } else {
         this.mParcel.writeInt(-1);
      }

   }

   protected void writeCharSequence(CharSequence var1) {
      TextUtils.writeToParcel(var1, this.mParcel, 0);
   }

   public void writeDouble(double var1) {
      this.mParcel.writeDouble(var1);
   }

   public void writeFloat(float var1) {
      this.mParcel.writeFloat(var1);
   }

   public void writeInt(int var1) {
      this.mParcel.writeInt(var1);
   }

   public void writeLong(long var1) {
      this.mParcel.writeLong(var1);
   }

   public void writeParcelable(Parcelable var1) {
      this.mParcel.writeParcelable(var1, 0);
   }

   public void writeString(String var1) {
      this.mParcel.writeString(var1);
   }

   public void writeStrongBinder(IBinder var1) {
      this.mParcel.writeStrongBinder(var1);
   }

   public void writeStrongInterface(IInterface var1) {
      this.mParcel.writeStrongInterface(var1);
   }
}
