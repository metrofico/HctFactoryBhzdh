package jxl.write;

import java.util.Date;
import jxl.DateCell;
import jxl.format.CellFormat;
import jxl.write.biff.DateRecord;

public class DateTime extends DateRecord implements WritableCell, DateCell {
   public static final GMTDate GMT = new GMTDate();

   public DateTime(int var1, int var2, Date var3) {
      super(var1, var2, var3);
   }

   public DateTime(int var1, int var2, Date var3, CellFormat var4) {
      super(var1, var2, var3, var4);
   }

   public DateTime(int var1, int var2, Date var3, CellFormat var4, GMTDate var5) {
      super(var1, var2, var3, var4, var5);
   }

   public DateTime(int var1, int var2, Date var3, CellFormat var4, boolean var5) {
      super(var1, var2, var3, var4, var5);
   }

   public DateTime(int var1, int var2, Date var3, GMTDate var4) {
      super(var1, var2, var3, var4);
   }

   protected DateTime(int var1, int var2, DateTime var3) {
      super(var1, var2, (DateRecord)var3);
   }

   public DateTime(DateCell var1) {
      super(var1);
   }

   public WritableCell copyTo(int var1, int var2) {
      return new DateTime(var1, var2, this);
   }

   public void setDate(Date var1) {
      super.setDate(var1);
   }

   public void setDate(Date var1, GMTDate var2) {
      super.setDate(var1, var2);
   }
}
