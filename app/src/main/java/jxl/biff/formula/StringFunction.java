package jxl.biff.formula;

import jxl.WorkbookSettings;
import jxl.common.Logger;

class StringFunction extends StringParseItem {
   private static Logger logger = Logger.getLogger(StringFunction.class);
   private Function function;
   private String functionString;

   StringFunction(String var1) {
      this.functionString = var1.substring(0, var1.length() - 1);
   }

   Function getFunction(WorkbookSettings var1) {
      if (this.function == null) {
         this.function = Function.getFunction(this.functionString, var1);
      }

      return this.function;
   }
}
