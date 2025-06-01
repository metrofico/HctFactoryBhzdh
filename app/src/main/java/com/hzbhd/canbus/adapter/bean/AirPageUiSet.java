package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.hzbhd.canbus.adapter.interfaces.OnAirInitListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;

public class AirPageUiSet implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public AirPageUiSet createFromParcel(Parcel var1) {
         return new AirPageUiSet(var1);
      }

      public AirPageUiSet[] newArray(int var1) {
         return new AirPageUiSet[var1];
      }
   };
   private FrontArea frontArea;
   private boolean isHaveRearArea;
   private OnAirInitListener onAirInitListener;
   private OnAirPageStatusListener onAirPageStatusListener;
   private RearArea rearArea;

   public AirPageUiSet(Parcel var1) {
      boolean var2 = false;
      this.isHaveRearArea = false;
      this.frontArea = (FrontArea)var1.readTypedObject(FrontArea.CREATOR);
      this.rearArea = (RearArea)var1.readTypedObject(RearArea.CREATOR);
      if (var1.readByte() != 0) {
         var2 = true;
      }

      this.isHaveRearArea = var2;
   }

   public int describeContents() {
      return 0;
   }

   public FrontArea getFrontArea() {
      return this.frontArea;
   }

   public OnAirInitListener getOnAirInitListener() {
      return this.onAirInitListener;
   }

   public OnAirPageStatusListener getOnAirPageStatusListener() {
      return this.onAirPageStatusListener;
   }

   public RearArea getRearArea() {
      return this.rearArea;
   }

   public boolean isHaveRearArea() {
      return this.isHaveRearArea;
   }

   public void setFrontArea(FrontArea var1) {
      this.frontArea = var1;
   }

   public void setHaveRearArea(boolean var1) {
      this.isHaveRearArea = var1;
   }

   public void setOnAirInitListener(OnAirInitListener var1) {
      this.onAirInitListener = var1;
   }

   public void setOnAirPageStatusListener(OnAirPageStatusListener var1) {
      this.onAirPageStatusListener = var1;
   }

   public void setRearArea(RearArea var1) {
      this.rearArea = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeTypedObject(this.frontArea, 0);
      var1.writeTypedObject(this.rearArea, 0);
      var1.writeByte((byte)this.isHaveRearArea);
   }
}
