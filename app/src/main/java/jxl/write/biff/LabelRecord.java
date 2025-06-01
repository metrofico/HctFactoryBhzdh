package jxl.write.biff;

import jxl.CellType;
import jxl.LabelCell;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;

public abstract class LabelRecord extends CellValue {
   private static Logger logger = Logger.getLogger(LabelRecord.class);
   private String contents;
   private int index;
   private SharedStrings sharedStrings;

   protected LabelRecord(int var1, int var2, String var3) {
      super(Type.LABELSST, var1, var2);
      this.contents = var3;
      if (var3 == null) {
         this.contents = "";
      }

   }

   protected LabelRecord(int var1, int var2, String var3, CellFormat var4) {
      super(Type.LABELSST, var1, var2, var4);
      this.contents = var3;
      if (var3 == null) {
         this.contents = "";
      }

   }

   protected LabelRecord(int var1, int var2, LabelRecord var3) {
      super(Type.LABELSST, var1, var2, (CellValue)var3);
      this.contents = var3.contents;
   }

   protected LabelRecord(LabelCell var1) {
      super(Type.LABELSST, var1);
      String var2 = var1.getString();
      this.contents = var2;
      if (var2 == null) {
         this.contents = "";
      }

   }

   public String getContents() {
      return this.contents;
   }

   public byte[] getData() {
      byte[] var1 = super.getData();
      byte[] var2 = new byte[var1.length + 4];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      IntegerHelper.getFourBytes(this.index, var2, var1.length);
      return var2;
   }

   public String getString() {
      return this.contents;
   }

   public CellType getType() {
      return CellType.LABEL;
   }

   void setCellDetails(FormattingRecords var1, SharedStrings var2, WritableSheetImpl var3) {
      super.setCellDetails(var1, var2, var3);
      this.sharedStrings = var2;
      int var4 = var2.getIndex(this.contents);
      this.index = var4;
      this.contents = this.sharedStrings.get(var4);
   }

   protected void setString(String var1) {
      String var4 = var1;
      if (var1 == null) {
         var4 = "";
      }

      this.contents = var4;
      if (this.isReferenced()) {
         boolean var3;
         if (this.sharedStrings != null) {
            var3 = true;
         } else {
            var3 = false;
         }

         Assert.verify(var3);
         int var2 = this.sharedStrings.getIndex(this.contents);
         this.index = var2;
         this.contents = this.sharedStrings.get(var2);
      }
   }
}
