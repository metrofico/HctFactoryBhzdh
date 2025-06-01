package jxl.write.biff;

import jxl.biff.FormattingRecords;
import jxl.biff.IndexMapping;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.biff.XFRecord;

class ColumnInfoRecord extends WritableRecordData {
   private boolean collapsed;
   private int column;
   private byte[] data;
   private boolean hidden;
   private int outlineLevel;
   private XFRecord style;
   private int width;
   private int xfIndex;

   public ColumnInfoRecord(int var1, int var2, XFRecord var3) {
      super(Type.COLINFO);
      this.column = var1;
      this.width = var2;
      this.style = var3;
      this.xfIndex = var3.getXFIndex();
      this.hidden = false;
   }

   public ColumnInfoRecord(jxl.read.biff.ColumnInfoRecord var1, int var2) {
      super(Type.COLINFO);
      this.column = var2;
      this.width = var1.getWidth();
      this.xfIndex = var1.getXFIndex();
      this.outlineLevel = var1.getOutlineLevel();
      this.collapsed = var1.getCollapsed();
   }

   public ColumnInfoRecord(jxl.read.biff.ColumnInfoRecord var1, int var2, FormattingRecords var3) {
      super(Type.COLINFO);
      this.column = var2;
      this.width = var1.getWidth();
      var2 = var1.getXFIndex();
      this.xfIndex = var2;
      this.style = var3.getXFRecord(var2);
      this.outlineLevel = var1.getOutlineLevel();
      this.collapsed = var1.getCollapsed();
   }

   public ColumnInfoRecord(ColumnInfoRecord var1) {
      super(Type.COLINFO);
      this.column = var1.column;
      this.width = var1.width;
      this.style = var1.style;
      this.xfIndex = var1.xfIndex;
      this.hidden = var1.hidden;
      this.outlineLevel = var1.outlineLevel;
      this.collapsed = var1.collapsed;
   }

   public void decrementColumn() {
      --this.column;
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

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ColumnInfoRecord)) {
         return false;
      } else {
         ColumnInfoRecord var3 = (ColumnInfoRecord)var1;
         if (this.column == var3.column && this.xfIndex == var3.xfIndex && this.width == var3.width && this.hidden == var3.hidden && this.outlineLevel == var3.outlineLevel && this.collapsed == var3.collapsed) {
            XFRecord var2 = this.style;
            return (var2 != null || var3.style == null) && (var2 == null || var3.style != null) ? var2.equals(var3.style) : false;
         } else {
            return false;
         }
      }
   }

   public XFRecord getCellFormat() {
      return this.style;
   }

   public boolean getCollapsed() {
      return this.collapsed;
   }

   public int getColumn() {
      return this.column;
   }

   public byte[] getData() {
      byte[] var3 = new byte[12];
      this.data = var3;
      IntegerHelper.getTwoBytes(this.column, var3, 0);
      IntegerHelper.getTwoBytes(this.column, this.data, 2);
      IntegerHelper.getTwoBytes(this.width, this.data, 4);
      IntegerHelper.getTwoBytes(this.xfIndex, this.data, 6);
      int var2 = this.outlineLevel << 8 | 6;
      int var1 = var2;
      if (this.hidden) {
         var1 = var2 | 1;
      }

      this.outlineLevel = (var1 & 1792) / 256;
      var2 = var1;
      if (this.collapsed) {
         var2 = var1 | 4096;
      }

      IntegerHelper.getTwoBytes(var2, this.data, 8);
      return this.data;
   }

   boolean getHidden() {
      return this.hidden;
   }

   public int getOutlineLevel() {
      return this.outlineLevel;
   }

   int getWidth() {
      return this.width;
   }

   public int getXfIndex() {
      return this.xfIndex;
   }

   public int hashCode() {
      int var2 = (((10823 + this.column) * 79 + this.xfIndex) * 79 + this.width) * 79 + this.hidden;
      XFRecord var3 = this.style;
      int var1 = var2;
      if (var3 != null) {
         var1 = var2 ^ var3.hashCode();
      }

      return var1;
   }

   public void incrementColumn() {
      ++this.column;
   }

   public void incrementOutlineLevel() {
      ++this.outlineLevel;
   }

   void rationalize(IndexMapping var1) {
      this.xfIndex = var1.getNewIndex(this.xfIndex);
   }

   public void setCellFormat(XFRecord var1) {
      this.style = var1;
   }

   public void setCollapsed(boolean var1) {
      this.collapsed = var1;
   }

   void setHidden(boolean var1) {
      this.hidden = var1;
   }

   public void setOutlineLevel(int var1) {
      this.outlineLevel = var1;
   }

   void setWidth(int var1) {
      this.width = var1;
   }
}
