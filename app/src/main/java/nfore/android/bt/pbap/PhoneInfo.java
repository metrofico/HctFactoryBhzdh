package nfore.android.bt.pbap;

import java.io.Serializable;

public class PhoneInfo implements Serializable {
   private String calledHistoryDate;
   private String calledHistoryTime;
   private String phoneNumber;
   private String phoneType;
   private String phoneTypeName;

   public String getCalledHistoryDate() {
      return this.calledHistoryDate;
   }

   public String getCalledHistoryTime() {
      return this.calledHistoryTime;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public String getPhoneType() {
      return this.phoneType;
   }

   public String getPhoneTypeName() {
      return this.phoneTypeName;
   }

   public void setCalledHistoryDate(String var1) {
      this.calledHistoryDate = var1;
   }

   public void setCalledHistoryTime(String var1) {
      this.calledHistoryTime = var1;
   }

   public void setPhoneNumber(String var1) {
      this.phoneNumber = var1;
   }

   public void setPhoneType(String var1) {
      this.phoneType = var1;
   }

   public void setPhoneTypeName(String var1) {
      this.phoneTypeName = var1;
   }
}
