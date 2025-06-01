package nfore.android.bt.servicemanager;

import android.os.Parcel;
import android.os.Parcelable;

public class HfpStatusInfo implements Parcelable {
   public static final Creator CREATOR = new Creator() {
      public HfpStatusInfo createFromParcel(Parcel var1) {
         return new HfpStatusInfo(var1);
      }

      public HfpStatusInfo[] newArray(int var1) {
         return new HfpStatusInfo[var1];
      }
   };
   private int battchgState;
   private int callState;
   private int callbackState;
   private int callheldState;
   private int callsetupState;
   private String deviceAddress;
   private String deviceName;
   private int mainState;
   private int roamState;
   private int scoState;
   private int serviceState;
   private int signalState;
   private int singleType;

   public HfpStatusInfo() {
   }

   public HfpStatusInfo(Parcel var1) {
      this.readFromParcel(var1);
   }

   public int describeContents() {
      return 0;
   }

   public int getBattchgState() {
      return this.battchgState;
   }

   public int getCallState() {
      return this.callState;
   }

   public int getCallbackState() {
      return this.callbackState;
   }

   public int getCallheldState() {
      return this.callheldState;
   }

   public int getCallsetupState() {
      return this.callsetupState;
   }

   public String getDeviceAddress() {
      return this.deviceAddress;
   }

   public String getDeviceName() {
      return this.deviceName;
   }

   public int getMainState() {
      return this.mainState;
   }

   public int getRoamState() {
      return this.roamState;
   }

   public int getScoState() {
      return this.scoState;
   }

   public int getServiceState() {
      return this.serviceState;
   }

   public int getSignalState() {
      return this.signalState;
   }

   public int getSingleType() {
      return this.singleType;
   }

   public void readFromParcel(Parcel var1) {
      this.deviceAddress = var1.readString();
      this.deviceName = var1.readString();
      this.mainState = var1.readInt();
      this.scoState = var1.readInt();
      this.serviceState = var1.readInt();
      this.callState = var1.readInt();
      this.callsetupState = var1.readInt();
      this.callheldState = var1.readInt();
      this.signalState = var1.readInt();
      this.roamState = var1.readInt();
      this.battchgState = var1.readInt();
      this.callbackState = var1.readInt();
      this.singleType = var1.readInt();
   }

   public void setBattchgState(int var1) {
      this.battchgState = var1;
   }

   public void setCallState(int var1) {
      this.callState = var1;
   }

   public void setCallbackState(int var1) {
      this.callbackState = var1;
   }

   public void setCallheldState(int var1) {
      this.callheldState = var1;
   }

   public void setCallsetupState(int var1) {
      this.callsetupState = var1;
   }

   public void setDeviceAddress(String var1) {
      this.deviceAddress = var1;
   }

   public void setDeviceName(String var1) {
      this.deviceName = var1;
   }

   public void setMainState(int var1) {
      this.mainState = var1;
   }

   public void setRoamState(int var1) {
      this.roamState = var1;
   }

   public void setScoState(int var1) {
      this.scoState = var1;
   }

   public void setServiceState(int var1) {
      this.serviceState = var1;
   }

   public void setSignalState(int var1) {
      this.signalState = var1;
   }

   public void setSingleType(int var1) {
      this.singleType = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.deviceAddress);
      var1.writeString(this.deviceName);
      var1.writeInt(this.mainState);
      var1.writeInt(this.scoState);
      var1.writeInt(this.serviceState);
      var1.writeInt(this.callState);
      var1.writeInt(this.callsetupState);
      var1.writeInt(this.callheldState);
      var1.writeInt(this.signalState);
      var1.writeInt(this.roamState);
      var1.writeInt(this.battchgState);
      var1.writeInt(this.callbackState);
      var1.writeInt(this.singleType);
   }
}
