package org.apache.log4j;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

class RollingCalendar extends GregorianCalendar {
   int type = -1;

   RollingCalendar() {
   }

   RollingCalendar(TimeZone var1, Locale var2) {
      super(var1, var2);
   }

   public Date getNextCheckDate(Date var1) {
      this.setTime(var1);
      int var2 = this.type;
      if (var2 != 0) {
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 4) {
                     if (var2 != 5) {
                        throw new IllegalStateException("Unknown periodicity type.");
                     }

                     this.set(5, 1);
                     this.set(11, 0);
                     this.set(12, 0);
                     this.set(13, 0);
                     this.set(14, 0);
                     this.add(2, 1);
                  } else {
                     this.set(7, this.getFirstDayOfWeek());
                     this.set(11, 0);
                     this.set(12, 0);
                     this.set(13, 0);
                     this.set(14, 0);
                     this.add(3, 1);
                  }
               } else {
                  this.set(11, 0);
                  this.set(12, 0);
                  this.set(13, 0);
                  this.set(14, 0);
                  this.add(5, 1);
               }
            } else {
               this.set(12, 0);
               this.set(13, 0);
               this.set(14, 0);
               if (this.get(11) < 12) {
                  this.set(11, 12);
               } else {
                  this.set(11, 0);
                  this.add(5, 1);
               }
            }
         } else {
            this.set(12, 0);
            this.set(13, 0);
            this.set(14, 0);
            this.add(11, 1);
         }
      } else {
         this.set(13, 0);
         this.set(14, 0);
         this.add(12, 1);
      }

      return this.getTime();
   }

   public long getNextCheckMillis(Date var1) {
      return this.getNextCheckDate(var1).getTime();
   }

   void setType(int var1) {
      this.type = var1;
   }
}
