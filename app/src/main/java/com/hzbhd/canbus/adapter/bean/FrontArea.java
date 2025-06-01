package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;

public class FrontArea implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public FrontArea createFromParcel(Parcel var1) {
         return new FrontArea(var1);
      }

      public FrontArea[] newArray(int var1) {
         return new FrontArea[var1];
      }
   };
   private String[] disableBtnArray;
   private boolean isAllBtnCanClick;
   private boolean isCanSetCenterTemp;
   private boolean isCanSetLeftTemp;
   private boolean isCanSetRightTemp;
   private boolean isCanSetSeatCold;
   private boolean isCanSetSeatHeat;
   private boolean isCanSetWindSpeed;
   private boolean isHaveLeftRightWindSpeed;
   private boolean isShowCenterWheel;
   private boolean isShowLeftWheel;
   private boolean isShowPmValue;
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
   private OnAirWindSpeedUpDownClickListener setRightWindSpeedViewOnClickListener;
   private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener;
   private String[] smallWindowStatusArray;
   private OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners;
   private OnAirTempTouchListener[] tempTouchListeners;
   private int windMaxLevel;

   protected FrontArea(Parcel var1) {
      String[] var4 = var1.createStringArray();
      boolean var3 = false;
      this.lineBtnAction = new String[][]{var4, var1.createStringArray(), var1.createStringArray(), var1.createStringArray()};
      boolean var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isAllBtnCanClick = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isShowLeftWheel = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isShowCenterWheel = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isShowRightWheel = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isCanSetLeftTemp = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isCanSetCenterTemp = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isCanSetRightTemp = var2;
      this.windMaxLevel = var1.readInt();
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isCanSetWindSpeed = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isHaveLeftRightWindSpeed = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isShowSeatHeat = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isCanSetSeatHeat = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isShowSeatCold = var2;
      if (var1.readByte() != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isCanSetSeatCold = var2;
      var2 = var3;
      if (var1.readByte() != 0) {
         var2 = true;
      }

      this.isShowPmValue = var2;
      this.seatHeatSrnArray = var1.createStringArray();
      this.seatColdSrnArray = var1.createStringArray();
      this.disableBtnArray = var1.createStringArray();
      this.smallWindowStatusArray = var1.createStringArray();
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

   public boolean getIsHaveLeftRightWindSpeed() {
      return this.isHaveLeftRightWindSpeed;
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

   public OnAirWindSpeedUpDownClickListener getSetRightWindSpeedViewOnClickListener() {
      return this.setRightWindSpeedViewOnClickListener;
   }

   public OnAirWindSpeedUpDownClickListener getSetWindSpeedViewOnClickListener() {
      return this.setWindSpeedViewOnClickListener;
   }

   public String[] getSmallWindowStatusArray() {
      return this.smallWindowStatusArray;
   }

   public OnAirTemperatureUpDownClickListener[] getTempSetViewOnUpDownClickListeners() {
      return this.tempSetViewOnUpDownClickListeners;
   }

   public OnAirTempTouchListener[] getTempTouchListeners() {
      return this.tempTouchListeners;
   }

   public int getWindMaxLevel() {
      return this.windMaxLevel;
   }

   public boolean isAllBtnCanClick() {
      return this.isAllBtnCanClick;
   }

   public boolean isCanSetCenterTemp() {
      return this.isCanSetCenterTemp;
   }

   public boolean isCanSetLeftTemp() {
      return this.isCanSetLeftTemp;
   }

   public boolean isCanSetRightTemp() {
      return this.isCanSetRightTemp;
   }

   public boolean isCanSetSeatCold() {
      return this.isCanSetSeatCold;
   }

   public boolean isCanSetSeatHeat() {
      return this.isCanSetSeatHeat;
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

   public boolean isShowPmValue() {
      return this.isShowPmValue;
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

   public void setCanSetCenterTemp(boolean var1) {
      this.isCanSetCenterTemp = var1;
   }

   public void setCanSetLeftTemp(boolean var1) {
      this.isCanSetLeftTemp = var1;
   }

   public void setCanSetRightTemp(boolean var1) {
      this.isCanSetRightTemp = var1;
   }

   public void setCanSetSeatCold(boolean var1) {
      this.isCanSetSeatCold = var1;
   }

   public void setCanSetSeatHeat(boolean var1) {
      this.isCanSetSeatHeat = var1;
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

   public void setHaveLeftRightWindSpeed(boolean var1) {
      this.isHaveLeftRightWindSpeed = var1;
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

   public void setSetRightWindSpeedViewOnClickListener(OnAirWindSpeedUpDownClickListener var1) {
      this.setRightWindSpeedViewOnClickListener = var1;
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

   public void setShowPmValue(boolean var1) {
      this.isShowPmValue = var1;
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

   public void setSmallWindowStatusArray(String[] var1) {
      this.smallWindowStatusArray = var1;
   }

   public void setTempSetViewOnUpDownClickListeners(OnAirTemperatureUpDownClickListener[] var1) {
      this.tempSetViewOnUpDownClickListeners = var1;
   }

   public void setTempTouchListeners(OnAirTempTouchListener[] var1) {
      this.tempTouchListeners = var1;
   }

   public void setWindMaxLevel(int var1) {
      this.windMaxLevel = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeStringArray(this.lineBtnAction[0]);
      var1.writeStringArray(this.lineBtnAction[1]);
      var1.writeStringArray(this.lineBtnAction[2]);
      var1.writeStringArray(this.lineBtnAction[3]);
      var1.writeByte((byte)this.isAllBtnCanClick);
      var1.writeByte((byte)this.isShowLeftWheel);
      var1.writeByte((byte)this.isShowCenterWheel);
      var1.writeByte((byte)this.isShowRightWheel);
      var1.writeByte((byte)this.isCanSetLeftTemp);
      var1.writeByte((byte)this.isCanSetCenterTemp);
      var1.writeByte((byte)this.isCanSetRightTemp);
      var1.writeInt(this.windMaxLevel);
      var1.writeByte((byte)this.isCanSetWindSpeed);
      var1.writeByte((byte)this.isHaveLeftRightWindSpeed);
      var1.writeByte((byte)this.isShowSeatHeat);
      var1.writeByte((byte)this.isCanSetSeatHeat);
      var1.writeByte((byte)this.isShowSeatCold);
      var1.writeByte((byte)this.isCanSetSeatCold);
      var1.writeByte((byte)this.isShowPmValue);
      var1.writeStringArray(this.seatHeatSrnArray);
      var1.writeStringArray(this.seatColdSrnArray);
      var1.writeStringArray(this.disableBtnArray);
      var1.writeStringArray(this.smallWindowStatusArray);
   }
}
