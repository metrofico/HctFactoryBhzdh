package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

public class RowRecord extends RecordData {
   private static final int defaultHeightIndicator = 255;
   private static Logger logger = Logger.getLogger(RowRecord.class);
   private boolean collapsed;
   private boolean defaultFormat;
   private boolean groupStart;
   private boolean matchesDefFontHeight;
   private int outlineLevel;
   private int rowHeight;
   private int rowNumber;
   private int xfIndex;

   RowRecord(Record var1) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      boolean var4 = false;
      this.rowNumber = IntegerHelper.getInt(var5[0], var5[1]);
      this.rowHeight = IntegerHelper.getInt(var5[6], var5[7]);
      int var2 = IntegerHelper.getInt(var5[12], var5[13], var5[14], var5[15]);
      this.outlineLevel = var2 & 7;
      boolean var3;
      if ((var2 & 16) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.groupStart = var3;
      if ((var2 & 32) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.collapsed = var3;
      if ((var2 & 64) == 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.matchesDefFontHeight = var3;
      var3 = var4;
      if ((var2 & 128) != 0) {
         var3 = true;
      }

      this.defaultFormat = var3;
      this.xfIndex = (var2 & 268369920) >> 16;
   }

   public boolean getGroupStart() {
      return this.groupStart;
   }

   public int getOutlineLevel() {
      return this.outlineLevel;
   }

   public int getRowHeight() {
      return this.rowHeight;
   }

   public int getRowNumber() {
      return this.rowNumber;
   }

   public int getXFIndex() {
      return this.xfIndex;
   }

   public boolean hasDefaultFormat() {
      return this.defaultFormat;
   }

   public boolean isCollapsed() {
      return this.collapsed;
   }

   boolean isDefaultHeight() {
      boolean var1;
      if (this.rowHeight == 255) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean matchesDefaultFontHeight() {
      return this.matchesDefFontHeight;
   }
}
