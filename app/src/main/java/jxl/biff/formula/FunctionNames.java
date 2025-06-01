package jxl.biff.formula;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import jxl.common.Logger;

public class FunctionNames {
   private static Logger logger = Logger.getLogger(FunctionNames.class);
   private HashMap functions;
   private HashMap names;

   public FunctionNames(Locale var1) {
      ResourceBundle var3 = ResourceBundle.getBundle("functions", var1);
      Function[] var4 = Function.getFunctions();
      this.names = new HashMap(var4.length);
      this.functions = new HashMap(var4.length);

      for(int var2 = 0; var2 < var4.length; ++var2) {
         Function var5 = var4[var2];
         String var6 = var5.getPropertyName();
         if (var6.length() != 0) {
            var6 = var3.getString(var6);
         } else {
            var6 = null;
         }

         if (var6 != null) {
            this.names.put(var5, var6);
            this.functions.put(var6, var5);
         }
      }

   }

   Function getFunction(String var1) {
      return (Function)this.functions.get(var1);
   }

   String getName(Function var1) {
      return (String)this.names.get(var1);
   }
}
