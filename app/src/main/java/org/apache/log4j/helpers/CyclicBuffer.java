package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class CyclicBuffer {
   LoggingEvent[] ea;
   int first;
   int last;
   int maxSize;
   int numElems;

   public CyclicBuffer(int var1) throws IllegalArgumentException {
      if (var1 >= 1) {
         this.maxSize = var1;
         this.ea = new LoggingEvent[var1];
         this.first = 0;
         this.last = 0;
         this.numElems = 0;
      } else {
         throw new IllegalArgumentException("The maxSize argument (" + var1 + ") is not a positive integer.");
      }
   }

   public void add(LoggingEvent var1) {
      LoggingEvent[] var4 = this.ea;
      int var2 = this.last;
      var4[var2] = var1;
      int var3 = var2 + 1;
      this.last = var3;
      var2 = this.maxSize;
      if (var3 == var2) {
         this.last = 0;
      }

      var3 = this.numElems;
      if (var3 < var2) {
         this.numElems = var3 + 1;
      } else {
         var3 = this.first + 1;
         this.first = var3;
         if (var3 == var2) {
            this.first = 0;
         }
      }

   }

   public LoggingEvent get() {
      int var1 = this.numElems;
      LoggingEvent var2 = null;
      if (var1 > 0) {
         this.numElems = var1 - 1;
         LoggingEvent[] var3 = this.ea;
         var1 = this.first;
         var2 = var3[var1];
         var3[var1] = null;
         ++var1;
         this.first = var1;
         if (var1 == this.maxSize) {
            this.first = 0;
         }
      }

      return var2;
   }

   public LoggingEvent get(int var1) {
      return var1 >= 0 && var1 < this.numElems ? this.ea[(this.first + var1) % this.maxSize] : null;
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public int length() {
      return this.numElems;
   }

   public void resize(int var1) {
      if (var1 >= 0) {
         int var3 = this.numElems;
         if (var1 != var3) {
            LoggingEvent[] var6 = new LoggingEvent[var1];
            int var2 = var3;
            if (var1 < var3) {
               var2 = var1;
            }

            for(var3 = 0; var3 < var2; ++var3) {
               LoggingEvent[] var5 = this.ea;
               int var4 = this.first;
               var6[var3] = var5[var4];
               var5[var4] = null;
               ++var4;
               this.first = var4;
               if (var4 == this.numElems) {
                  this.first = 0;
               }
            }

            this.ea = var6;
            this.first = 0;
            this.numElems = var2;
            this.maxSize = var1;
            if (var2 == var1) {
               this.last = 0;
            } else {
               this.last = var2;
            }

         }
      } else {
         throw new IllegalArgumentException("Negative array size [" + var1 + "] not allowed.");
      }
   }
}
