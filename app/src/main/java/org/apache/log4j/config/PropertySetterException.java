package org.apache.log4j.config;

public class PropertySetterException extends Exception {
   protected Throwable rootCause;

   public PropertySetterException(String var1) {
      super(var1);
   }

   public PropertySetterException(Throwable var1) {
      this.rootCause = var1;
   }

   public String getMessage() {
      String var2 = super.getMessage();
      String var1 = var2;
      if (var2 == null) {
         Throwable var3 = this.rootCause;
         var1 = var2;
         if (var3 != null) {
            var1 = var3.getMessage();
         }
      }

      return var1;
   }
}
