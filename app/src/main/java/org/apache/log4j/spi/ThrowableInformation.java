package org.apache.log4j.spi;

import java.io.Serializable;

public class ThrowableInformation implements Serializable {
   static final long serialVersionUID = -4748765566864322735L;
   private String[] rep;
   private transient Throwable throwable;

   public ThrowableInformation(Throwable var1) {
      this.throwable = var1;
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public String[] getThrowableStrRep() {
      String[] var1 = this.rep;
      if (var1 != null) {
         return (String[])var1.clone();
      } else {
         VectorWriter var2 = new VectorWriter();
         this.throwable.printStackTrace(var2);
         var1 = var2.toStringArray();
         this.rep = var1;
         return var1;
      }
   }
}
