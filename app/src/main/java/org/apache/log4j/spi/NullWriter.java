package org.apache.log4j.spi;

import java.io.Writer;

class NullWriter extends Writer {
   public void close() {
   }

   public void flush() {
   }

   public void write(char[] var1, int var2, int var3) {
   }
}
