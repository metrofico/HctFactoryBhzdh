package com.hzbhd.canbus.adapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CanTypeAllEntity implements Parcelable {
   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
      public CanTypeAllEntity createFromParcel(Parcel var1) {
         return new CanTypeAllEntity(var1);
      }

      public CanTypeAllEntity[] newArray(int var1) {
         return new CanTypeAllEntity[var1];
      }
   };
   private int baud_rate;
   private int can_different_id;
   private int can_type_id;
   private String car_category;
   private String car_model;
   private String description;
   private int each_can_id;
   String english_car_category;
   String english_car_model;
   String english_protocol_company;
   String english_years;
   private int is_app_handle_key;
   private int is_show_app;
   private int is_use_new_app;
   private int is_use_new_camera;
   private int pack_type;
   private String protocol_company;
   private boolean selected;
   private String years;

   public CanTypeAllEntity() {
   }

   public CanTypeAllEntity(Parcel var1) {
      this.protocol_company = var1.readString();
      this.car_category = var1.readString();
      this.car_model = var1.readString();
      this.years = var1.readString();
      this.english_protocol_company = var1.readString();
      this.english_car_category = var1.readString();
      this.english_car_model = var1.readString();
      this.english_years = var1.readString();
      this.can_type_id = var1.readInt();
      this.can_different_id = var1.readInt();
      this.each_can_id = var1.readInt();
      this.baud_rate = var1.readInt();
      this.is_app_handle_key = var1.readInt();
      this.pack_type = var1.readInt();
      this.is_show_app = var1.readInt();
      this.is_use_new_camera = var1.readInt();
      this.is_use_new_app = var1.readInt();
      this.description = var1.readString();
   }

   public CanTypeAllEntity(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, String var18) {
      this.protocol_company = var1;
      this.car_category = var2;
      this.car_model = var3;
      this.years = var4;
      this.english_protocol_company = var5;
      this.english_car_category = var6;
      this.english_car_model = var7;
      this.english_years = var8;
      this.can_type_id = var9;
      this.can_different_id = var10;
      this.each_can_id = var11;
      this.baud_rate = var12;
      this.is_app_handle_key = var13;
      this.pack_type = var14;
      this.is_show_app = var15;
      this.is_use_new_camera = var16;
      this.is_use_new_app = var17;
      this.description = var18;
   }

   public int describeContents() {
      return 0;
   }

   public int getBaud_rate() {
      return this.baud_rate;
   }

   public int getCan_different_id() {
      return this.can_different_id;
   }

   public int getCan_type_id() {
      return this.can_type_id;
   }

   public String getCar_category() {
      return this.car_category;
   }

   public String getCar_model() {
      return this.car_model;
   }

   public String getDescription() {
      return this.description;
   }

   public int getEach_can_id() {
      return this.each_can_id;
   }

   public String getEnglish_car_category() {
      return this.english_car_category;
   }

   public String getEnglish_car_model() {
      return this.english_car_model;
   }

   public String getEnglish_protocol_company() {
      return this.english_protocol_company;
   }

   public String getEnglish_years() {
      return this.english_years;
   }

   public int getIs_app_handle_key() {
      return this.is_app_handle_key;
   }

   public int getIs_show_app() {
      return this.is_show_app;
   }

   public int getIs_use_new_app() {
      return this.is_use_new_app;
   }

   public int getIs_use_new_camera() {
      return this.is_use_new_camera;
   }

   public int getPack_type() {
      return this.pack_type;
   }

   public String getProtocol_company() {
      return this.protocol_company;
   }

   public String getYears() {
      return this.years;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void readFromParcel(Parcel var1) {
      this.protocol_company = var1.readString();
      this.car_category = var1.readString();
      this.car_model = var1.readString();
      this.years = var1.readString();
      this.english_protocol_company = var1.readString();
      this.english_car_category = var1.readString();
      this.english_car_model = var1.readString();
      this.english_years = var1.readString();
      this.can_type_id = var1.readInt();
      this.can_different_id = var1.readInt();
      this.each_can_id = var1.readInt();
      this.baud_rate = var1.readInt();
      this.is_app_handle_key = var1.readInt();
      this.pack_type = var1.readInt();
      this.is_show_app = var1.readInt();
      this.is_use_new_camera = var1.readInt();
      this.is_use_new_app = var1.readInt();
      this.description = var1.readString();
   }

   public void setBaud_rate(int var1) {
      this.baud_rate = var1;
   }

   public void setCan_different_id(int var1) {
      this.can_different_id = var1;
   }

   public void setCan_type_id(int var1) {
      this.can_type_id = var1;
   }

   public void setCar_category(String var1) {
      this.car_category = var1;
   }

   public void setCar_model(String var1) {
      this.car_model = var1;
   }

   public void setDescription(String var1) {
      this.description = var1;
   }

   public void setEach_can_id(int var1) {
      this.each_can_id = var1;
   }

   public void setEnglish_car_category(String var1) {
      this.english_car_category = var1;
   }

   public void setEnglish_car_model(String var1) {
      this.english_car_model = var1;
   }

   public void setEnglish_protocol_company(String var1) {
      this.english_protocol_company = var1;
   }

   public void setEnglish_years(String var1) {
      this.english_years = var1;
   }

   public void setIs_app_handle_key(int var1) {
      this.is_app_handle_key = var1;
   }

   public void setIs_show_app(int var1) {
      this.is_show_app = var1;
   }

   public void setIs_use_new_app(int var1) {
      this.is_use_new_app = var1;
   }

   public void setIs_use_new_camera(int var1) {
      this.is_use_new_camera = var1;
   }

   public void setPack_type(int var1) {
      this.pack_type = var1;
   }

   public void setProtocol_company(String var1) {
      this.protocol_company = var1;
   }

   public void setSelected(boolean var1) {
      this.selected = var1;
   }

   public void setYears(String var1) {
      this.years = var1;
   }

   public void writeToParcel(Parcel var1, int var2) {
      var1.writeString(this.protocol_company);
      var1.writeString(this.car_category);
      var1.writeString(this.car_model);
      var1.writeString(this.years);
      var1.writeString(this.english_protocol_company);
      var1.writeString(this.english_car_category);
      var1.writeString(this.english_car_model);
      var1.writeString(this.english_years);
      var1.writeInt(this.can_type_id);
      var1.writeInt(this.can_different_id);
      var1.writeInt(this.each_can_id);
      var1.writeInt(this.baud_rate);
      var1.writeInt(this.is_app_handle_key);
      var1.writeInt(this.pack_type);
      var1.writeInt(this.is_show_app);
      var1.writeInt(this.is_use_new_camera);
      var1.writeInt(this.is_use_new_app);
      var1.writeString(this.description);
   }
}
