package jxl.biff.formula;

import jxl.JXLException;

public class FormulaException extends JXLException {
   public static final FormulaMessage BIFF8_SUPPORTED = new FormulaMessage("Only biff8 formulas are supported");
   static final FormulaMessage CELL_NAME_NOT_FOUND = new FormulaMessage("Could not find named cell");
   static final FormulaMessage INCORRECT_ARGUMENTS = new FormulaMessage("Incorrect arguments supplied to function");
   static final FormulaMessage LEXICAL_ERROR = new FormulaMessage("Lexical error:  ");
   static final FormulaMessage SHEET_REF_NOT_FOUND = new FormulaMessage("Could not find sheet");
   static final FormulaMessage UNRECOGNIZED_FUNCTION = new FormulaMessage("Unrecognized function");
   static final FormulaMessage UNRECOGNIZED_TOKEN = new FormulaMessage("Unrecognized token");

   public FormulaException(FormulaMessage var1) {
      super(var1.message);
   }

   public FormulaException(FormulaMessage var1, int var2) {
      super(var1.message + " " + var2);
   }

   public FormulaException(FormulaMessage var1, String var2) {
      super(var1.message + " " + var2);
   }

   private static class FormulaMessage {
      private String message;

      FormulaMessage(String var1) {
         this.message = var1;
      }

      public String getMessage() {
         return this.message;
      }
   }
}
