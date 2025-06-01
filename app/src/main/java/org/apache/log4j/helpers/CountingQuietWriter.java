package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;

public class CountingQuietWriter extends QuietWriter {
   protected long count;

   public CountingQuietWriter(Writer var1, ErrorHandler var2) {
      super(var1, var2);
   }

   public long getCount() {
      return this.count;
   }

   public void setCount(long var1) {
      this.count = var1;
   }

   public void write(String var1) {
      try {
         super.out.write(var1);
         this.count += (long)var1.length();
      } catch (IOException var2) {
         super.errorHandler.error("Write failure.", var2, 1);
      }

   }
}
