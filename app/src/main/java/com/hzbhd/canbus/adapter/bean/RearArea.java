package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;

public class RearArea implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public RearArea createFromParcel(Parcel var1) {
         return new RearArea(var1);
      }

      public RearArea[] newArray(int var1) {
         return new RearArea[var1];
      }
   };
   private String[] disableBtnArray;
   private boolean isAllBtnCanClick;
   private boolean isCanSetSeatCold;
   private boolean isCanSetSeatHeat;
   private boolean isCanSetTemp;
   private boolean isCanSetWindSpeed;
   private boolean isShowCenterWheel;
   private boolean isShowLeftWheel;
   private boolean isShowRightWheel;
   private boolean isShowSeatCold;
   private boolean isShowSeatHeat;
   private String[][] lineBtnAction;
   private OnAirBtnClickListener[] onAirBtnClickListeners;
   private OnAirPageStatusListener onAirPageStatusListener;
   private OnAirSeatClickListener onAirSeatClickListener;
   private String[] seatColdSrnArray;
   private OnAirSeatHeatColdMinPlusClickListener[] seatHeatColdClickListeners;
   private String[] seatHeatSrnArray;
   private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener;
   private OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners;
   private int windMaxLevel;

   protected RearArea(Parcel var1) {
      byte var2 = var1.readByte();
      boolean var4 = true;
      boolean var3;
      if (var2 != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isAllBtnCanClick = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isShowLeftWheel = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isShowCenterWheel = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isShowRightWheel = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isCanSetTemp = var3;
      this.windMaxLevel = var1.readInt();
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isCanSetWindSpeed = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isShowSeatHeat = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isCanSetSeatHeat = var3;
      if (var1.readByte() != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isShowSeatCold = var3;
      if (var1.readByte() != 0) {
         var3 = var4;
      } else {
         var3 = false;
      }

      this.isCanSetSeatCold = var3;
      this.seatHeatSrnArray = var1.createStringArray();
      this.seatColdSrnArray = var1.createStringArray();
      this.disableBtnArray = var1.createStringArray();
   }

   public int describeContents() {
      return 0;
   }

   public String[] getDisableBtnArray() {
      return this.disableBtnArray;
   }

   public String[] getDisableBtnS() {
      return this.disableBtnArray;
   }

   public String[][] getLineBtnAction() {
      return this.lineBtnAction;
   }

   public OnAirBtnClickListener[] getOnAirBtnClickListeners() {
      return this.onAirBtnClickListeners;
   }

   public OnAirPageStatusListener getOnAirPageStatusListener() {
      return this.onAirPageStatusListener;
   }

   public OnAirSeatClickListener getOnAirSeatClickListener() {
      return this.onAirSeatClickListener;
   }

   public String[] getSeatColdSrnArray() {
      return this.seatColdSrnArray;
   }

   public OnAirSeatHeatColdMinPlusClickListener[] getSeatHeatColdClickListeners() {
      return this.seatHeatColdClickListeners;
   }

   public String[] getSeatHeatSrnArray() {
      return this.seatHeatSrnArray;
   }

   public OnAirWindSpeedUpDownClickListener getSetWindSpeedViewOnClickListener() {
      return this.setWindSpeedViewOnClickListener;
   }

   public OnAirTemperatureUpDownClickListener[] getTempSetViewOnUpDownClickListeners() {
      return this.tempSetViewOnUpDownClickListeners;
   }

   public int getWindMaxLevel() {
      return this.windMaxLevel;
   }

   public boolean isAllBtnCanClick() {
      return this.isAllBtnCanClick;
   }

   public boolean isCanSetSeatCold() {
      return this.isCanSetSeatCold;
   }

   public boolean isCanSetSeatHeat() {
      return this.isCanSetSeatHeat;
   }

   public boolean isCanSetTemp() {
      return this.isCanSetTemp;
   }

   public boolean isCanSetWindSpeed() {
      return this.isCanSetWindSpeed;
   }

   public boolean isShowCenterWheel() {
      return this.isShowCenterWheel;
   }

   public boolean isShowLeftWheel() {
      return this.isShowLeftWheel;
   }

   public boolean isShowRightWheel() {
      return this.isShowRightWheel;
   }

   public boolean isShowSeatCold() {
      return this.isShowSeatCold;
   }

   public boolean isShowSeatHeat() {
      return this.isShowSeatHeat;
   }

   public void setAllBtnCanClick(boolean var1) {
      this.isAllBtnCanClick = var1;
   }

   public void setCanSetSeatCold(boolean var1) {
      this.isCanSetSeatCold = var1;
   }

   public void setCanSetSeatHeat(boolean var1) {
      this.isCanSetSeatHeat = var1;
   }

   public void setCanSetTemp(boolean var1) {
      this.isCanSetTemp = var1;
   }

   public void setCanSetWindSpeed(boolean var1) {
      this.isCanSetWindSpeed = var1;
   }

   public void setDisableBtnArray(String[] var1) {
      this.disableBtnArray = var1;
   }

   public void setDisableBtnS(String[] var1) {
      this.disableBtnArray = var1;
   }

   public void setLineBtnAction(String[][] var1) {
      this.lineBtnAction = var1;
   }

   public void setOnAirBtnClickListeners(OnAirBtnClickListener[] var1) {
      this.onAirBtnClickListeners = var1;
   }

   public void setOnAirPageStatusListener(OnAirPageStatusListener var1) {
      this.onAirPageStatusListener = var1;
   }

   public void setOnAirSeatClickListener(OnAirSeatClickListener var1) {
      this.onAirSeatClickListener = var1;
   }

   public void setSeatColdSrnArray(String[] var1) {
      this.seatColdSrnArray = var1;
   }

   public void setSeatHeatColdClickListeners(OnAirSeatHeatColdMinPlusClickListener[] var1) {
      this.seatHeatColdClickListeners = var1;
   }

   public void setSeatHeatSrnArray(String[] var1) {
      this.seatHeatSrnArray = var1;
   }

   public void setSetWindSpeedViewOnClickListener(OnAirWindSpeedUpDownClickListener var1) {
      this.setWindSpeedViewOnClickListener = var1;
   }

   public void setShowCenterWheel(boolean var1) {
      this.isShowCenterWheel = var1;
   }

   public void setShowLeftWheel(boolean var1) {
      this.isShowLeftWheel = var1;
   }

   public void setShowRightWheel(boolean var1) {
      this.isShowRightWheel = var1;
   }

   public void setShowSeatCold(boolean var1) {
      this.isShowSeatCold = var1;
   }

   public void setShowSeatHeat(boolean var1) {
      this.isShowSeatHeat = var1;
   }

   public void setTempSetViewOnUpDownClickListeners(OnAirTemperatureUpDownClickListener[] var1) {
      this.tempSetViewOnUpDownClickListeners = var1;
   }

   public void setWindMaxLevel(int var1) {
      this.windMaxLevel = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeByte((byte)this.isAllBtnCanClick);
      var1.writeByte((byte)this.isShowLeftWheel);
      var1.writeByte((byte)this.isShowCenterWheel);
      var1.writeByte((byte)this.isShowRightWheel);
      var1.writeByte((byte)this.isCanSetTemp);
      var1.writeInt(this.windMaxLevel);
      var1.writeByte((byte)this.isCanSetWindSpeed);
      var1.writeByte((byte)this.isShowSeatHeat);
      var1.writeByte((byte)this.isCanSetSeatHeat);
      var1.writeByte((byte)this.isShowSeatCold);
      var1.writeByte((byte)this.isCanSetSeatCold);
      var1.writeStringArray(this.seatHeatSrnArray);
      var1.writeStringArray(this.seatColdSrnArray);
      var1.writeStringArray(this.disableBtnArray);
   }
}
