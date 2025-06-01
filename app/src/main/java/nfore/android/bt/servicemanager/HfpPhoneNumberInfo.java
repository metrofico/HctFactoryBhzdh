package nfore.android.bt.servicemanager;

import android.os.Parcel;
import android.os.Parcelable;

public class HfpPhoneNumberInfo implements Parcelable {
   public static final Creator CREATOR = new Creator() {
      public HfpPhoneNumberInfo createFromParcel(Parcel var1) {
         return new HfpPhoneNumberInfo(var1);
      }

      public HfpPhoneNumberInfo[] newArray(int var1) {
         return new HfpPhoneNumberInfo[var1];
      }
   };
   private int dir;
   private int idx;
   private int mode;
   private int mpty;
   private String number;
   private int status;
   private int type;

   public HfpPhoneNumberInfo() {
   }

   public HfpPhoneNumberInfo(Parcel var1) {
      this.readFromParcel(var1);
   }

   public HfpPhoneNumberInfo clone() {
      HfpPhoneNumberInfo var1 = new HfpPhoneNumberInfo();
      var1.setIdx(this.idx);
      var1.setDir(this.dir);
      var1.setStatus(this.status);
      var1.setMode(this.mode);
      var1.setMpty(this.mpty);
      var1.setType(this.type);
      var1.setNumber(this.number);
      return var1;
   }

   public int describeContents() {
      return 0;
   }

   public int getDir() {
      return this.dir;
   }

   public int getIdx() {
      return this.idx;
   }

   public int getMode() {
      return this.mode;
   }

   public int getMpty() {
      return this.mpty;
   }

   public String getNumber() {
      return this.number;
   }

   public int getStatus() {
      return this.status;
   }

   public int getType() {
      return this.type;
   }

   public void readFromParcel(Parcel var1) {
      this.idx = var1.readInt();
      this.dir = var1.readInt();
      this.status = var1.readInt();
      this.mode = var1.readInt();
      this.mpty = var1.readInt();
      this.type = var1.readInt();
      this.number = var1.readString();
   }

   public void setDir(int var1) {
      this.dir = var1;
   }

   public void setIdx(int var1) {
      this.idx = var1;
   }

   public void setMode(int var1) {
      this.mode = var1;
   }

   public void setMpty(int var1) {
      this.mpty = var1;
   }

   public void setNumber(String var1) {
      this.number = var1;
   }

   public void setStatus(int var1) {
      this.status = var1;
   }

   public void setType(int var1) {
      this.type = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.idx);
      var1.writeInt(this.dir);
      var1.writeInt(this.status);
      var1.writeInt(this.mode);
      var1.writeInt(this.mpty);
      var1.writeInt(this.type);
      var1.writeString(this.number);
   }
}
