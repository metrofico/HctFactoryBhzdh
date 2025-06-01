package org.apache.log4j.helpers;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class QuietWriter extends FilterWriter {
   protected ErrorHandler errorHandler;

   public QuietWriter(Writer var1, ErrorHandler var2) {
      super(var1);
      this.setErrorHandler(var2);
   }

   public void flush() {
      try {
         super.out.flush();
      } catch (IOException var2) {
         this.errorHandler.error("Failed to flush writer,", var2, 2);
      }

   }

   public void setErrorHandler(ErrorHandler var1) {
      if (var1 != null) {
         this.errorHandler = var1;
      } else {
         throw new IllegalArgumentException("Attempted to set null ErrorHandler.");
      }
   }

   public void write(String var1) {
      try {
         super.out.write(var1);
      } catch (IOException var3) {
         this.errorHandler.error("Failed to write [" + var1 + "].", var3, 1);
      }

   }
}
