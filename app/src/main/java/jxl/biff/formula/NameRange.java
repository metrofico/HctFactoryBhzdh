package jxl.biff.formula;

import jxl.biff.IntegerHelper;
import jxl.biff.NameRangeException;
import jxl.biff.WorkbookMethods;
import jxl.common.Assert;
import jxl.common.Logger;

class NameRange extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(NameRange.class);
   private int index;
   private String name;
   private WorkbookMethods nameTable;

   public NameRange(String var1, WorkbookMethods var2) throws FormulaException {
      this.name = var1;
      this.nameTable = var2;
      int var3 = var2.getNameIndex(var1);
      this.index = var3;
      if (var3 >= 0) {
         this.index = var3 + 1;
      } else {
         throw new FormulaException(FormulaException.CELL_NAME_NOT_FOUND, this.name);
      }
   }

   public NameRange(WorkbookMethods var1) {
      this.nameTable = var1;
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
   }

   byte[] getBytes() {
      byte[] var1 = new byte[5];
      var1[0] = Token.NAMED_RANGE.getValueCode();
      if (this.getParseContext() == ParseContext.DATA_VALIDATION) {
         var1[0] = Token.NAMED_RANGE.getReferenceCode();
      }

      IntegerHelper.getTwoBytes(this.index, var1, 1);
      return var1;
   }

   public void getString(StringBuffer var1) {
      var1.append(this.name);
   }

   void handleImportedCellReferences() {
      this.setInvalid();
   }

   public int read(byte[] var1, int var2) throws FormulaException {
      try {
         var2 = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
         this.index = var2;
         this.name = this.nameTable.getName(var2 - 1);
         return 4;
      } catch (NameRangeException var3) {
         throw new FormulaException(FormulaException.CELL_NAME_NOT_FOUND, "");
      }
   }
}
