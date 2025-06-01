package jxl.read.biff;

import jxl.CellType;
import jxl.LabelCell;
import jxl.StringFormulaCell;
import jxl.WorkbookSettings;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;
import jxl.common.Logger;

class StringFormulaRecord extends CellValue implements LabelCell, FormulaData, StringFormulaCell {
   private static Logger logger = Logger.getLogger(StringFormulaRecord.class);
   private byte[] data;
   private ExternalSheet externalSheet;
   private String formulaString;
   private WorkbookMethods nameTable;
   private String value;

   public StringFormulaRecord(Record var1, FormattingRecords var2, ExternalSheet var3, WorkbookMethods var4, SheetImpl var5) {
      super(var1, var2, var5);
      this.externalSheet = var3;
      this.nameTable = var4;
      this.data = this.getRecord().getData();
      this.value = "";
   }

   public StringFormulaRecord(Record var1, File var2, FormattingRecords var3, ExternalSheet var4, WorkbookMethods var5, SheetImpl var6, WorkbookSettings var7) {
      super(var1, var3, var6);
      this.externalSheet = var4;
      this.nameTable = var5;
      this.data = this.getRecord().getData();
      int var9 = var2.getPos();
      var1 = var2.next();

      int var8;
      for(var8 = 0; var1.getType() != Type.STRING && var8 < 4; ++var8) {
         var1 = var2.next();
      }

      boolean var10;
      if (var8 < 4) {
         var10 = true;
      } else {
         var10 = false;
      }

      Assert.verify(var10, " @ " + var9);
      byte[] var11 = var1.getData();

      Record var14;
      for(Record var12 = var2.peek(); var12.getType() == Type.CONTINUE; var12 = var14) {
         var14 = var2.next();
         byte[] var13 = new byte[var11.length + var14.getLength() - 1];
         System.arraycopy(var11, 0, var13, 0, var11.length);
         System.arraycopy(var14.getData(), 1, var13, var11.length, var14.getLength() - 1);
         var14 = var2.peek();
         var11 = var13;
      }

      this.readString(var11, var7);
   }

   private void readString(byte[] var1, WorkbookSettings var2) {
      boolean var9 = false;
      int var5 = IntegerHelper.getInt(var1[0], var1[1]);
      if (var5 == 0) {
         this.value = "";
      } else {
         int var3 = 2;
         byte var6 = var1[2];
         if ((var6 & 15) != var6) {
            var5 = IntegerHelper.getInt(var1[0], (byte)0);
            var6 = var1[1];
         } else {
            var3 = 3;
         }

         boolean var7;
         if ((var6 & 4) != 0) {
            var7 = true;
         } else {
            var7 = false;
         }

         boolean var8;
         if ((var6 & 8) != 0) {
            var8 = true;
         } else {
            var8 = false;
         }

         int var4 = var3;
         if (var8) {
            var4 = var3 + 2;
         }

         var3 = var4;
         if (var7) {
            var3 = var4 + 4;
         }

         boolean var10 = var9;
         if ((var6 & 1) == 0) {
            var10 = true;
         }

         if (var10) {
            this.value = StringHelper.getString(var1, var5, var3, var2);
         } else {
            this.value = StringHelper.getUnicodeString(var1, var5, var3);
         }

      }
   }

   public String getContents() {
      return this.value;
   }

   public String getFormula() throws FormulaException {
      if (this.formulaString == null) {
         byte[] var2 = this.data;
         int var1 = var2.length - 22;
         byte[] var3 = new byte[var1];
         System.arraycopy(var2, 22, var3, 0, var1);
         FormulaParser var4 = new FormulaParser(var3, this, this.externalSheet, this.nameTable, this.getSheet().getWorkbook().getSettings());
         var4.parse();
         this.formulaString = var4.getFormula();
      }

      return this.formulaString;
   }

   public byte[] getFormulaData() throws FormulaException {
      if (this.getSheet().getWorkbook().getWorkbookBof().isBiff8()) {
         byte[] var1 = this.data;
         byte[] var2 = new byte[var1.length - 6];
         System.arraycopy(var1, 6, var2, 0, var1.length - 6);
         return var2;
      } else {
         throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
      }
   }

   public String getString() {
      return this.value;
   }

   public CellType getType() {
      return CellType.STRING_FORMULA;
   }
}
