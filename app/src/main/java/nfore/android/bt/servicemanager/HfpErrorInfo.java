package nfore.android.bt.servicemanager;

import android.os.Parcel;
import android.os.Parcelable;

public class HfpErrorInfo implements Parcelable {
   public static final Creator CREATOR = new Creator() {
      public HfpErrorInfo createFromParcel(Parcel var1) {
         return new HfpErrorInfo(var1);
      }

      public HfpErrorInfo[] newArray(int var1) {
         return new HfpErrorInfo[var1];
      }
   };
   private int errorCode;
   private String errorDesc;
   private String errorResponse;

   public HfpErrorInfo() {
   }

   public HfpErrorInfo(Parcel var1) {
      this.readFromParcel(var1);
   }

   public int describeContents() {
      return 0;
   }

   public int getErrorCode() {
      return this.errorCode;
   }

   public String getErrorDesc() {
      return this.errorDesc;
   }

   public String getErrorResponse() {
      return this.errorResponse;
   }

   public void readFromParcel(Parcel var1) {
      this.errorCode = var1.readInt();
      this.errorResponse = var1.readString();
      this.errorDesc = var1.readString();
   }

   public void setErrorCode(int var1) {
      this.errorCode = var1;
   }

   public void setErrorDesc(String var1) {
      this.errorDesc = var1;
   }

   public void setErrorResponse(String var1) {
      this.errorResponse = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeInt(this.errorCode);
      var1.writeString(this.errorResponse);
      var1.writeString(this.errorDesc);
   }
}
