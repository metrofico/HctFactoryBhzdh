package nfore.android.bt.pbap;

import android.database.Cursor;
import java.io.Serializable;
import java.util.Set;

public class VCardPack implements Serializable {
   private String HistoryDate;
   private String HistoryTime;
   private int _id;
   private String cellPhone_Address;
   private String firstName;
   private String first_CityNameAddress;
   private String first_CountryAddress;
   private String first_FederalStateAddress;
   private String first_StreetAddress;
   private String first_ZipCodeAddress;
   private String fullName;
   private String lastName;
   private Set phoneNumbers;
   private String second_CityNameAddress;
   private String second_CountryAddress;
   private String second_FederalStateAddress;
   private String second_StreetAddress;
   private String second_ZipCodeAddress;
   private String storageType;

   public VCardPack() {
   }

   public VCardPack(Cursor var1) {
      int var8 = var1.getColumnIndex("_id");
      int var17 = var1.getColumnIndex("FullName");
      int var6 = var1.getColumnIndex("FirstName");
      int var11 = var1.getColumnIndex("LastName");
      int var12 = var1.getColumnIndex("First_StreetAddress");
      int var16 = var1.getColumnIndex("First_CityNameAddress");
      int var5 = var1.getColumnIndex("First_FederalStateAddress");
      int var7 = var1.getColumnIndex("First_ZipCodeAddress");
      int var3 = var1.getColumnIndex("First_CountryAddress");
      int var13 = var1.getColumnIndex("Second_StreetAddress");
      int var9 = var1.getColumnIndex("Second_CityNameAddress");
      int var10 = var1.getColumnIndex("Second_FederalStateAddress");
      int var15 = var1.getColumnIndex("Second_ZipCodeAddress");
      int var14 = var1.getColumnIndex("Second_CountryAddress");
      int var4 = var1.getColumnIndex("CellPhone_Address");
      int var2 = var1.getColumnIndex("StorageType");
      this.set_id(var1.getInt(var8));
      this.setFullName(var1.getString(var17));
      this.setFirstName(var1.getString(var6));
      this.setLastName(var1.getString(var11));
      this.setFirst_StreetAddress(var1.getString(var12));
      this.setFirst_CityNameAddress(var1.getString(var16));
      this.setFirst_FederalStateAddress(var1.getString(var5));
      this.setFirst_ZipCodeAddress(var1.getString(var7));
      this.setFirst_CountryAddress(var1.getString(var3));
      this.setSecond_StreetAddress(var1.getString(var13));
      this.setSecond_CityNameAddress(var1.getString(var9));
      this.setSecond_FederalStateAddress(var1.getString(var10));
      this.setSecond_ZipCodeAddress(var1.getString(var15));
      this.setSecond_CountryAddress(var1.getString(var14));
      this.setCellPhone_Address(var1.getString(var4));
      this.setStorageType(var1.getString(var2));
   }

   public String getCellPhone_Address() {
      return this.cellPhone_Address;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public String getFirst_CityNameAddress() {
      return this.first_CityNameAddress;
   }

   public String getFirst_CountryAddress() {
      return this.first_CountryAddress;
   }

   public String getFirst_FederalStateAddress() {
      return this.first_FederalStateAddress;
   }

   public String getFirst_StreetAddress() {
      return this.first_StreetAddress;
   }

   public String getFirst_ZipCodeAddress() {
      return this.first_ZipCodeAddress;
   }

   public String getFullName() {
      return this.fullName;
   }

   public String getHistoryDate() {
      return this.HistoryDate;
   }

   public String getHistoryTime() {
      return this.HistoryTime;
   }

   public String getLastName() {
      return this.lastName;
   }

   public Set getPhoneNumbers() {
      return this.phoneNumbers;
   }

   public String getSecond_CityNameAddress() {
      return this.second_CityNameAddress;
   }

   public String getSecond_CountryAddress() {
      return this.second_CountryAddress;
   }

   public String getSecond_FederalStateAddress() {
      return this.second_FederalStateAddress;
   }

   public String getSecond_StreetAddress() {
      return this.second_StreetAddress;
   }

   public String getSecond_ZipCodeAddress() {
      return this.second_ZipCodeAddress;
   }

   public String getStorageType() {
      return this.storageType;
   }

   public int get_id() {
      return this._id;
   }

   public void setCellPhone_Address(String var1) {
      this.cellPhone_Address = var1;
   }

   public void setFirstName(String var1) {
      this.firstName = var1;
   }

   public void setFirst_CityNameAddress(String var1) {
      this.first_CityNameAddress = var1;
   }

   public void setFirst_CountryAddress(String var1) {
      this.first_CountryAddress = var1;
   }

   public void setFirst_FederalStateAddress(String var1) {
      this.first_FederalStateAddress = var1;
   }

   public void setFirst_StreetAddress(String var1) {
      this.first_StreetAddress = var1;
   }

   public void setFirst_ZipCodeAddress(String var1) {
      this.first_ZipCodeAddress = var1;
   }

   public void setFullName(String var1) {
      this.fullName = var1;
   }

   public void setHistoryDate(String var1) {
      this.HistoryDate = var1;
   }

   public void setHistoryTime(String var1) {
      this.HistoryTime = var1;
   }

   public void setLastName(String var1) {
      this.lastName = var1;
   }

   public void setPhoneNumbers(Set var1) {
      this.phoneNumbers = var1;
   }

   public void setSecond_CityNameAddress(String var1) {
      this.second_CityNameAddress = var1;
   }

   public void setSecond_CountryAddress(String var1) {
      this.second_CountryAddress = var1;
   }

   public void setSecond_FederalStateAddress(String var1) {
      this.second_FederalStateAddress = var1;
   }

   public void setSecond_StreetAddress(String var1) {
      this.second_StreetAddress = var1;
   }

   public void setSecond_ZipCodeAddress(String var1) {
      this.second_ZipCodeAddress = var1;
   }

   public void setStorageType(String var1) {
      this.storageType = var1;
   }

   public void set_id(int var1) {
      this._id = var1;
   }
}
