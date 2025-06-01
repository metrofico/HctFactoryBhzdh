package jxl.biff.formula;

import jxl.Cell;
import jxl.WorkbookSettings;
import jxl.biff.WorkbookMethods;
import jxl.common.Assert;
import jxl.common.Logger;

public class FormulaParser {
   private static final Logger logger = Logger.getLogger(FormulaParser.class);
   private Parser parser;

   public FormulaParser(String var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4) {
      this.parser = new StringFormulaParser(var1, var2, var3, var4, ParseContext.DEFAULT);
   }

   public FormulaParser(String var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4, ParseContext var5) {
      this.parser = new StringFormulaParser(var1, var2, var3, var4, var5);
   }

   public FormulaParser(byte[] var1, Cell var2, ExternalSheet var3, WorkbookMethods var4, WorkbookSettings var5) throws FormulaException {
      if (var3.getWorkbookBof() != null && !var3.getWorkbookBof().isBiff8()) {
         throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
      } else {
         boolean var6;
         if (var4 != null) {
            var6 = true;
         } else {
            var6 = false;
         }

         Assert.verify(var6);
         this.parser = new TokenFormulaParser(var1, var2, var3, var4, var5, ParseContext.DEFAULT);
      }
   }

   public FormulaParser(byte[] var1, Cell var2, ExternalSheet var3, WorkbookMethods var4, WorkbookSettings var5, ParseContext var6) throws FormulaException {
      if (var3.getWorkbookBof() != null && !var3.getWorkbookBof().isBiff8()) {
         throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
      } else {
         boolean var7;
         if (var4 != null) {
            var7 = true;
         } else {
            var7 = false;
         }

         Assert.verify(var7);
         this.parser = new TokenFormulaParser(var1, var2, var3, var4, var5, var6);
      }
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      this.parser.adjustRelativeCellReferences(var1, var2);
   }

   public void columnInserted(int var1, int var2, boolean var3) {
      this.parser.columnInserted(var1, var2, var3);
   }

   public void columnRemoved(int var1, int var2, boolean var3) {
      this.parser.columnRemoved(var1, var2, var3);
   }

   public byte[] getBytes() {
      return this.parser.getBytes();
   }

   public String getFormula() throws FormulaException {
      return this.parser.getFormula();
   }

   public boolean handleImportedCellReferences() {
      return this.parser.handleImportedCellReferences();
   }

   public void parse() throws FormulaException {
      this.parser.parse();
   }

   public void rowInserted(int var1, int var2, boolean var3) {
      this.parser.rowInserted(var1, var2, var3);
   }

   public void rowRemoved(int var1, int var2, boolean var3) {
      this.parser.rowRemoved(var1, var2, var3);
   }
}
