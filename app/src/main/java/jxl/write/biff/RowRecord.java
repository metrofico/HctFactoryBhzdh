package jxl.write.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.CellType;
import jxl.biff.CellReferenceHelper;
import jxl.biff.IndexMapping;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.biff.XFRecord;
import jxl.common.Logger;
import jxl.write.Number;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;

class RowRecord extends WritableRecordData {
   private static int defaultHeightIndicator = 255;
   private static final int growSize = 10;
   private static final Logger logger = Logger.getLogger(RowRecord.class);
   private static int maxColumns = 256;
   private static final int maxRKValue = 536870911;
   private static final int minRKValue = -536870912;
   private CellValue[] cells;
   private boolean collapsed;
   private byte[] data;
   private boolean defaultFormat;
   private boolean groupStart;
   private boolean matchesDefFontHeight;
   private int numColumns;
   private int outlineLevel;
   private int rowHeight;
   private int rowNumber;
   private WritableSheet sheet;
   private XFRecord style;
   private int xfIndex;

   public RowRecord(int var1, WritableSheet var2) {
      super(Type.ROW);
      this.rowNumber = var1;
      this.cells = new CellValue[0];
      this.numColumns = 0;
      this.rowHeight = defaultHeightIndicator;
      this.collapsed = false;
      this.matchesDefFontHeight = true;
      this.sheet = var2;
   }

   private void writeIntegerValues(ArrayList var1, File var2) throws IOException {
      if (var1.size() != 0) {
         if (var1.size() >= 3) {
            var2.write(new MulRKRecord(var1));
         } else {
            Iterator var3 = var1.iterator();

            while(var3.hasNext()) {
               var2.write((CellValue)var3.next());
            }
         }

         var1.clear();
      }
   }

   public void addCell(CellValue var1) {
      int var2 = var1.getColumn();
      if (var2 >= maxColumns) {
         logger.warn("Could not add cell at " + CellReferenceHelper.getCellReference(var1.getRow(), var1.getColumn()) + " because it exceeds the maximum column limit");
      } else {
         CellValue[] var3 = this.cells;
         if (var2 >= var3.length) {
            CellValue[] var4 = new CellValue[Math.max(var3.length + 10, var2 + 1)];
            this.cells = var4;
            System.arraycopy(var3, 0, var4, 0, var3.length);
         }

         CellValue var5 = this.cells[var2];
         if (var5 != null) {
            WritableCellFeatures var6 = var5.getWritableCellFeatures();
            if (var6 != null) {
               var6.removeComment();
               if (var6.getDVParser() != null && !var6.getDVParser().extendedCellsValidation()) {
                  var6.removeDataValidation();
               }
            }
         }

         this.cells[var2] = var1;
         this.numColumns = Math.max(var2 + 1, this.numColumns);
      }
   }

   public void decrementOutlineLevel() {
      int var1 = this.outlineLevel;
      if (var1 > 0) {
         this.outlineLevel = var1 - 1;
      }

      if (this.outlineLevel == 0) {
         this.collapsed = false;
      }

   }

   void decrementRow() {
      --this.rowNumber;
      int var1 = 0;

      while(true) {
         CellValue[] var2 = this.cells;
         if (var1 >= var2.length) {
            return;
         }

         CellValue var3 = var2[var1];
         if (var3 != null) {
            var3.decrementRow();
         }

         ++var1;
      }
   }

   public CellValue getCell(int var1) {
      CellValue var2;
      if (var1 >= 0 && var1 < this.numColumns) {
         var2 = this.cells[var1];
      } else {
         var2 = null;
      }

      return var2;
   }

   public byte[] getData() {
      byte[] var3 = new byte[16];
      int var2 = this.rowHeight;
      int var1 = var2;
      if (this.sheet.getSettings().getDefaultRowHeight() != 255) {
         var1 = var2;
         if (var2 == defaultHeightIndicator) {
            var1 = this.sheet.getSettings().getDefaultRowHeight();
         }
      }

      IntegerHelper.getTwoBytes(this.rowNumber, var3, 0);
      IntegerHelper.getTwoBytes(this.numColumns, var3, 4);
      IntegerHelper.getTwoBytes(var1, var3, 6);
      var1 = this.outlineLevel + 256;
      var2 = var1;
      if (this.groupStart) {
         var2 = var1 | 16;
      }

      var1 = var2;
      if (this.collapsed) {
         var1 = var2 | 32;
      }

      var2 = var1;
      if (!this.matchesDefFontHeight) {
         var2 = var1 | 64;
      }

      var1 = var2;
      if (this.defaultFormat) {
         var1 = var2 | 128 | this.xfIndex << 16;
      }

      IntegerHelper.getFourBytes(var1, var3, 12);
      return var3;
   }

   public boolean getGroupStart() {
      return this.groupStart;
   }

   public int getMaxColumn() {
      return this.numColumns;
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

   XFRecord getStyle() {
      return this.style;
   }

   boolean hasDefaultFormat() {
      return this.defaultFormat;
   }

   public void incrementOutlineLevel() {
      ++this.outlineLevel;
   }

   void incrementRow() {
      ++this.rowNumber;
      int var1 = 0;

      while(true) {
         CellValue[] var2 = this.cells;
         if (var1 >= var2.length) {
            return;
         }

         CellValue var3 = var2[var1];
         if (var3 != null) {
            var3.incrementRow();
         }

         ++var1;
      }
   }

   void insertColumn(int var1) {
      int var2 = this.numColumns;
      if (var1 < var2) {
         CellValue[] var3 = this.cells;
         if (var2 >= var3.length - 1) {
            this.cells = new CellValue[var3.length + 10];
         } else {
            this.cells = new CellValue[var3.length];
         }

         System.arraycopy(var3, 0, this.cells, 0, var1);
         CellValue[] var4 = this.cells;
         var2 = var1 + 1;
         System.arraycopy(var3, var1, var4, var2, this.numColumns - var1);
         var1 = var2;

         while(true) {
            var2 = this.numColumns;
            if (var1 > var2) {
               this.numColumns = Math.min(var2 + 1, maxColumns);
               return;
            }

            CellValue var5 = this.cells[var1];
            if (var5 != null) {
               var5.incrementColumn();
            }

            ++var1;
         }
      }
   }

   public boolean isCollapsed() {
      return this.collapsed;
   }

   public boolean isDefaultHeight() {
      boolean var1;
      if (this.rowHeight == defaultHeightIndicator) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean matchesDefaultFontHeight() {
      return this.matchesDefFontHeight;
   }

   void rationalize(IndexMapping var1) {
      if (this.defaultFormat) {
         this.xfIndex = var1.getNewIndex(this.xfIndex);
      }

   }

   public void removeCell(int var1) {
      if (var1 < this.numColumns) {
         this.cells[var1] = null;
      }
   }

   void removeColumn(int var1) {
      if (var1 < this.numColumns) {
         CellValue[] var3 = this.cells;
         CellValue[] var4 = new CellValue[var3.length];
         this.cells = var4;
         System.arraycopy(var3, 0, var4, 0, var1);
         int var2 = var1 + 1;
         System.arraycopy(var3, var2, this.cells, var1, this.numColumns - var2);

         while(true) {
            var2 = this.numColumns;
            if (var1 >= var2) {
               this.numColumns = var2 - 1;
               return;
            }

            CellValue var5 = this.cells[var1];
            if (var5 != null) {
               var5.decrementColumn();
            }

            ++var1;
         }
      }
   }

   public void setCollapsed(boolean var1) {
      this.collapsed = var1;
   }

   public void setGroupStart(boolean var1) {
      this.groupStart = var1;
   }

   public void setOutlineLevel(int var1) {
      this.outlineLevel = var1;
   }

   void setRowDetails(int var1, boolean var2, boolean var3, int var4, boolean var5, XFRecord var6) {
      this.rowHeight = var1;
      this.collapsed = var3;
      this.matchesDefFontHeight = var2;
      this.outlineLevel = var4;
      this.groupStart = var5;
      if (var6 != null) {
         this.defaultFormat = true;
         this.style = var6;
         this.xfIndex = var6.getXFIndex();
      }

   }

   public void setRowHeight(int var1) {
      if (var1 == 0) {
         this.setCollapsed(true);
         this.matchesDefFontHeight = false;
      } else {
         this.rowHeight = var1;
         this.matchesDefFontHeight = false;
      }

   }

   public void write(File var1) throws IOException {
      var1.write(this);
   }

   public void writeCells(File var1) throws IOException {
      ArrayList var4 = new ArrayList();

      for(int var2 = 0; var2 < this.numColumns; ++var2) {
         CellValue var5 = this.cells[var2];
         if (var5 == null) {
            this.writeIntegerValues(var4, var1);
         } else {
            boolean var3;
            label33: {
               if (var5.getType() == CellType.NUMBER) {
                  Number var6 = (Number)this.cells[var2];
                  if (var6.getValue() == (double)((int)var6.getValue()) && var6.getValue() < 5.36870911E8 && var6.getValue() > -5.36870912E8 && var6.getCellFeatures() == null) {
                     var3 = true;
                     break label33;
                  }
               }

               var3 = false;
            }

            if (var3) {
               var4.add(this.cells[var2]);
            } else {
               this.writeIntegerValues(var4, var1);
               var1.write(this.cells[var2]);
               if (this.cells[var2].getType() == CellType.STRING_FORMULA) {
                  var1.write(new StringRecord(this.cells[var2].getContents()));
               }
            }
         }
      }

      this.writeIntegerValues(var4, var1);
   }
}
