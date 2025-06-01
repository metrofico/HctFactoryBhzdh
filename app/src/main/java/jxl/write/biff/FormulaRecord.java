package jxl.write.biff;

import jxl.CellReferenceHelper;
import jxl.CellType;
import jxl.Sheet;
import jxl.WorkbookSettings;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;
import jxl.write.WritableCell;

public class FormulaRecord extends CellValue implements FormulaData {
   private static Logger logger = Logger.getLogger(FormulaRecord.class);
   private CellValue copiedFrom;
   private byte[] formulaBytes;
   private String formulaString;
   private String formulaToParse;
   private FormulaParser parser;

   public FormulaRecord(int var1, int var2, String var3) {
      super(Type.FORMULA2, var1, var2);
      this.formulaToParse = var3;
      this.copiedFrom = null;
   }

   public FormulaRecord(int var1, int var2, String var3, CellFormat var4) {
      super(Type.FORMULA, var1, var2, var4);
      this.formulaToParse = var3;
      this.copiedFrom = null;
   }

   protected FormulaRecord(int var1, int var2, FormulaRecord var3) {
      super(Type.FORMULA, var1, var2, (CellValue)var3);
      this.copiedFrom = var3;
      byte[] var4 = new byte[var3.formulaBytes.length];
      this.formulaBytes = var4;
      System.arraycopy(var3.formulaBytes, 0, var4, 0, var4.length);
   }

   protected FormulaRecord(int var1, int var2, ReadFormulaRecord var3) {
      super(Type.FORMULA, var1, var2, (CellValue)var3);

      try {
         this.copiedFrom = var3;
         this.formulaBytes = var3.getFormulaBytes();
      } catch (FormulaException var4) {
         logger.error("", var4);
      }

   }

   private void initialize(WorkbookSettings var1, ExternalSheet var2, WorkbookMethods var3) {
      if (this.copiedFrom != null) {
         this.initializeCopiedFormula(var1, var2, var3);
      } else {
         FormulaParser var4 = new FormulaParser(this.formulaToParse, var2, var3, var1);
         this.parser = var4;

         try {
            var4.parse();
            this.formulaString = this.parser.getFormula();
            this.formulaBytes = this.parser.getBytes();
         } catch (FormulaException var6) {
            logger.warn(var6.getMessage() + " when parsing formula " + this.formulaToParse + " in cell " + this.getSheet().getName() + "!" + CellReferenceHelper.getCellReference(this.getColumn(), this.getRow()));

            try {
               this.formulaToParse = "ERROR(1)";
               var4 = new FormulaParser(this.formulaToParse, var2, var3, var1);
               this.parser = var4;
               var4.parse();
               this.formulaString = this.parser.getFormula();
               this.formulaBytes = this.parser.getBytes();
            } catch (FormulaException var5) {
               logger.error("", var5);
            }
         }

      }
   }

   private void initializeCopiedFormula(WorkbookSettings var1, ExternalSheet var2, WorkbookMethods var3) {
      FormulaParser var4;
      try {
         var4 = new FormulaParser(this.formulaBytes, this, var2, var3, var1);
         this.parser = var4;
         var4.parse();
         this.parser.adjustRelativeCellReferences(this.getColumn() - this.copiedFrom.getColumn(), this.getRow() - this.copiedFrom.getRow());
         this.formulaString = this.parser.getFormula();
         this.formulaBytes = this.parser.getBytes();
      } catch (FormulaException var6) {
         try {
            this.formulaToParse = "ERROR(1)";
            var4 = new FormulaParser(this.formulaToParse, var2, var3, var1);
            this.parser = var4;
            var4.parse();
            this.formulaString = this.parser.getFormula();
            this.formulaBytes = this.parser.getBytes();
         } catch (FormulaException var5) {
            logger.error("", var5);
         }
      }

   }

   void columnInserted(Sheet var1, int var2, int var3) {
      FormulaParser var5 = this.parser;
      boolean var4;
      if (var1 == this.getSheet()) {
         var4 = true;
      } else {
         var4 = false;
      }

      var5.columnInserted(var2, var3, var4);
      this.formulaBytes = this.parser.getBytes();
   }

   void columnRemoved(Sheet var1, int var2, int var3) {
      FormulaParser var5 = this.parser;
      boolean var4;
      if (var1 == this.getSheet()) {
         var4 = true;
      } else {
         var4 = false;
      }

      var5.columnRemoved(var2, var3, var4);
      this.formulaBytes = this.parser.getBytes();
   }

   public WritableCell copyTo(int var1, int var2) {
      Assert.verify(false);
      return null;
   }

   public String getContents() {
      return this.formulaString;
   }

   public byte[] getData() {
      byte[] var1 = super.getData();
      byte[] var3 = this.getFormulaData();
      byte[] var2 = new byte[var3.length + var1.length];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      System.arraycopy(var3, 0, var2, var1.length, var3.length);
      return var2;
   }

   public byte[] getFormulaData() {
      byte[] var1 = this.formulaBytes;
      byte[] var2 = new byte[var1.length + 16];
      System.arraycopy(var1, 0, var2, 16, var1.length);
      var2[6] = 16;
      var2[7] = 64;
      var2[12] = -32;
      var2[13] = -4;
      var2[8] = (byte)(var2[8] | 2);
      IntegerHelper.getTwoBytes(this.formulaBytes.length, var2, 14);
      return var2;
   }

   public CellType getType() {
      return CellType.ERROR;
   }

   void rowInserted(Sheet var1, int var2, int var3) {
      FormulaParser var5 = this.parser;
      boolean var4;
      if (var1 == this.getSheet()) {
         var4 = true;
      } else {
         var4 = false;
      }

      var5.rowInserted(var2, var3, var4);
      this.formulaBytes = this.parser.getBytes();
   }

   void rowRemoved(Sheet var1, int var2, int var3) {
      FormulaParser var5 = this.parser;
      boolean var4;
      if (var1 == this.getSheet()) {
         var4 = true;
      } else {
         var4 = false;
      }

      var5.rowRemoved(var2, var3, var4);
      this.formulaBytes = this.parser.getBytes();
   }

   void setCellDetails(FormattingRecords var1, SharedStrings var2, WritableSheetImpl var3) {
      super.setCellDetails(var1, var2, var3);
      this.initialize(var3.getWorkbookSettings(), var3.getWorkbook(), var3.getWorkbook());
      var3.getWorkbook().addRCIRCell(this);
   }
}
