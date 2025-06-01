package jxl.write.biff;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import jxl.CellType;
import jxl.DateCell;
import jxl.biff.DoubleHelper;
import jxl.biff.Type;
import jxl.common.Logger;
import jxl.format.CellFormat;
import jxl.write.DateFormats;
import jxl.write.WritableCellFormat;

public abstract class DateRecord extends CellValue {
   static final WritableCellFormat defaultDateFormat;
   private static Logger logger = Logger.getLogger(DateRecord.class);
   private static final long msInADay = 86400000L;
   private static final int nonLeapDay = 61;
   private static final int utcOffsetDays = 25569;
   private Date date;
   private boolean time;
   private double value;

   static {
      defaultDateFormat = new WritableCellFormat(DateFormats.DEFAULT);
   }

   protected DateRecord(int var1, int var2, Date var3) {
      this(var1, var2, var3, defaultDateFormat, false);
   }

   protected DateRecord(int var1, int var2, Date var3, CellFormat var4) {
      super(Type.NUMBER, var1, var2, var4);
      this.date = var3;
      this.calculateValue(true);
   }

   protected DateRecord(int var1, int var2, Date var3, CellFormat var4, GMTDate var5) {
      super(Type.NUMBER, var1, var2, var4);
      this.date = var3;
      this.calculateValue(false);
   }

   protected DateRecord(int var1, int var2, Date var3, CellFormat var4, boolean var5) {
      super(Type.NUMBER, var1, var2, var4);
      this.date = var3;
      this.time = var5;
      this.calculateValue(false);
   }

   protected DateRecord(int var1, int var2, Date var3, GMTDate var4) {
      this(var1, var2, var3, defaultDateFormat, false);
   }

   protected DateRecord(int var1, int var2, DateRecord var3) {
      super(Type.NUMBER, var1, var2, (CellValue)var3);
      this.value = var3.value;
      this.time = var3.time;
      this.date = var3.date;
   }

   protected DateRecord(DateCell var1) {
      super(Type.NUMBER, var1);
      this.date = var1.getDate();
      this.time = var1.isTime();
      this.calculateValue(false);
   }

   private void calculateValue(boolean var1) {
      long var6 = 0L;
      long var4;
      if (var1) {
         Calendar var8 = Calendar.getInstance();
         var8.setTime(this.date);
         var6 = (long)var8.get(15);
         var4 = (long)var8.get(16);
      } else {
         var4 = 0L;
      }

      double var2 = (double)(this.date.getTime() + var6 + var4) / 8.64E7 + 25569.0;
      this.value = var2;
      var1 = this.time;
      if (!var1 && var2 < 61.0) {
         this.value = var2 - 1.0;
      }

      if (var1) {
         var2 = this.value;
         this.value = var2 - (double)((int)var2);
      }

   }

   public String getContents() {
      return this.date.toString();
   }

   public byte[] getData() {
      byte[] var1 = super.getData();
      byte[] var2 = new byte[var1.length + 8];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      DoubleHelper.getIEEEBytes(this.value, var2, var1.length);
      return var2;
   }

   public Date getDate() {
      return this.date;
   }

   public DateFormat getDateFormat() {
      return null;
   }

   public CellType getType() {
      return CellType.DATE;
   }

   public boolean isTime() {
      return this.time;
   }

   protected void setDate(Date var1) {
      this.date = var1;
      this.calculateValue(true);
   }

   protected void setDate(Date var1, GMTDate var2) {
      this.date = var1;
      this.calculateValue(false);
   }

   protected static final class GMTDate {
      public GMTDate() {
      }
   }
}
