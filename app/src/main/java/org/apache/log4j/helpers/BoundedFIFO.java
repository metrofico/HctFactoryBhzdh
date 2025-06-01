package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class BoundedFIFO {
   LoggingEvent[] buf;
   int first = 0;
   int maxSize;
   int next = 0;
   int numElements = 0;

   public BoundedFIFO(int var1) {
      if (var1 >= 1) {
         this.maxSize = var1;
         this.buf = new LoggingEvent[var1];
      } else {
         throw new IllegalArgumentException("The maxSize argument (" + var1 + ") is not a positive integer.");
      }
   }

   public LoggingEvent get() {
      int var1 = this.numElements;
      if (var1 == 0) {
         return null;
      } else {
         LoggingEvent[] var3 = this.buf;
         int var2 = this.first;
         LoggingEvent var4 = var3[var2];
         var3[var2] = null;
         ++var2;
         this.first = var2;
         if (var2 == this.maxSize) {
            this.first = 0;
         }

         this.numElements = var1 - 1;
         return var4;
      }
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public boolean isFull() {
      boolean var1;
      if (this.numElements == this.maxSize) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public int length() {
      return this.numElements;
   }

   int min(int var1, int var2) {
      if (var1 >= var2) {
         var1 = var2;
      }

      return var1;
   }

   public void put(LoggingEvent var1) {
      int var2 = this.numElements;
      int var3 = this.maxSize;
      if (var2 != var3) {
         LoggingEvent[] var5 = this.buf;
         int var4 = this.next;
         var5[var4] = var1;
         ++var4;
         this.next = var4;
         if (var4 == var3) {
            this.next = 0;
         }

         this.numElements = var2 + 1;
      }

   }

   public void resize(int var1) {
      synchronized(this){}

      Throwable var10000;
      label414: {
         int var2;
         boolean var10001;
         try {
            var2 = this.maxSize;
         } catch (Throwable var46) {
            var10000 = var46;
            var10001 = false;
            break label414;
         }

         if (var1 == var2) {
            return;
         }

         int var3;
         LoggingEvent[] var4;
         try {
            var4 = new LoggingEvent[var1];
            var3 = this.min(this.min(var2 - this.first, var1), this.numElements);
            System.arraycopy(this.buf, this.first, var4, 0, var3);
            var2 = this.numElements;
         } catch (Throwable var45) {
            var10000 = var45;
            var10001 = false;
            break label414;
         }

         if (var3 < var2 && var3 < var1) {
            try {
               var2 = this.min(var2 - var3, var1 - var3);
               System.arraycopy(this.buf, 0, var4, var3, var2);
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label414;
            }
         } else {
            var2 = 0;
         }

         try {
            this.buf = var4;
            this.maxSize = var1;
            this.first = 0;
         } catch (Throwable var43) {
            var10000 = var43;
            var10001 = false;
            break label414;
         }

         var2 += var3;

         try {
            this.numElements = var2;
            this.next = var2;
         } catch (Throwable var42) {
            var10000 = var42;
            var10001 = false;
            break label414;
         }

         if (var2 == var1) {
            try {
               this.next = 0;
            } catch (Throwable var41) {
               var10000 = var41;
               var10001 = false;
               break label414;
            }
         }

         return;
      }

      Throwable var47 = var10000;
      throw var47;
   }

   public boolean wasEmpty() {
      int var1 = this.numElements;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public boolean wasFull() {
      int var1 = this.numElements;
      boolean var2 = true;
      if (var1 + 1 != this.maxSize) {
         var2 = false;
      }

      return var2;
   }
}
