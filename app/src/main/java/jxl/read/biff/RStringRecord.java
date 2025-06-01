package jxl.read.biff;

import jxl.CellType;
import jxl.LabelCell;
import jxl.WorkbookSettings;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;

class RStringRecord extends CellValue implements LabelCell {
   public static Biff7 biff7 = new Biff7();
   private int length;
   private String string;

   public RStringRecord(Record var1, FormattingRecords var2, SheetImpl var3, WorkbookSettings var4, Biff7 var5) {
      super(var1, var2, var3);
      byte[] var7 = this.getRecord().getData();
      int var6 = IntegerHelper.getInt(var7[6], var7[7]);
      this.length = var6;
      this.string = StringHelper.getString(var7, var6, 8, var4);
   }

   public String getContents() {
      return this.string;
   }

   public String getString() {
      return this.string;
   }

   public CellType getType() {
      return CellType.LABEL;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
