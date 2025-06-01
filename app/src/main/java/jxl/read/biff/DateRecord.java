package jxl.read.biff;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import jxl.CellFeatures;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.biff.FormattingRecords;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;

class DateRecord implements DateCell, CellFeaturesAccessor {
   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
   private static final TimeZone gmtZone = TimeZone.getTimeZone("GMT");
   private static Logger logger = Logger.getLogger(DateRecord.class);
   private static final long msInADay = 86400000L;
   private static final long msInASecond = 1000L;
   private static final int nonLeapDay = 61;
   private static final long secondsInADay = 86400L;
   private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
   private static final int utcOffsetDays = 25569;
   private static final int utcOffsetDays1904 = 24107;
   private CellFormat cellFormat;
   private int column;
   private Date date;
   private CellFeatures features;
   private DateFormat format;
   private FormattingRecords formattingRecords;
   private boolean initialized;
   private int row;
   private SheetImpl sheet;
   private boolean time;
   private int xfIndex;

   public DateRecord(NumberCell var1, int var2, FormattingRecords var3, boolean var4, SheetImpl var5) {
      this.row = var1.getRow();
      this.column = var1.getColumn();
      this.xfIndex = var2;
      this.formattingRecords = var3;
      this.sheet = var5;
      this.initialized = false;
      this.format = var3.getDateFormat(var2);
      double var8 = var1.getValue();
      if (Math.abs(var8) < 1.0) {
         if (this.format == null) {
            this.format = timeFormat;
         }

         this.time = true;
      } else {
         if (this.format == null) {
            this.format = dateFormat;
         }

         this.time = false;
      }

      double var6 = var8;
      if (!var4) {
         var6 = var8;
         if (!this.time) {
            var6 = var8;
            if (var8 < 61.0) {
               var6 = var8 + 1.0;
            }
         }
      }

      this.format.setTimeZone(gmtZone);
      short var10;
      if (var4) {
         var10 = 24107;
      } else {
         var10 = 25569;
      }

      this.date = new Date(Math.round((var6 - (double)var10) * 86400.0) * 1000L);
   }

   public CellFeatures getCellFeatures() {
      return this.features;
   }

   public CellFormat getCellFormat() {
      if (!this.initialized) {
         this.cellFormat = this.formattingRecords.getXFRecord(this.xfIndex);
         this.initialized = true;
      }

      return this.cellFormat;
   }

   public final int getColumn() {
      return this.column;
   }

   public String getContents() {
      return this.format.format(this.date);
   }

   public Date getDate() {
      return this.date;
   }

   public DateFormat getDateFormat() {
      boolean var1;
      if (this.format != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      Assert.verify(var1);
      return this.format;
   }

   public final int getRow() {
      return this.row;
   }

   protected final SheetImpl getSheet() {
      return this.sheet;
   }

   public CellType getType() {
      return CellType.DATE;
   }

   public boolean isHidden() {
      ColumnInfoRecord var1 = this.sheet.getColumnInfo(this.column);
      if (var1 != null && var1.getWidth() == 0) {
         return true;
      } else {
         RowRecord var2 = this.sheet.getRowInfo(this.row);
         return var2 != null && (var2.getRowHeight() == 0 || var2.isCollapsed());
      }
   }

   public boolean isTime() {
      return this.time;
   }

   public void setCellFeatures(CellFeatures var1) {
      this.features = var1;
   }
}
