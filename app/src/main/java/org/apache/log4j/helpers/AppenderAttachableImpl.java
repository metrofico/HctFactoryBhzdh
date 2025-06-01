package org.apache.log4j.helpers;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AppenderAttachableImpl implements AppenderAttachable {
   protected Vector appenderList;

   public void addAppender(Appender var1) {
      if (var1 != null) {
         if (this.appenderList == null) {
            this.appenderList = new Vector(1);
         }

         if (!this.appenderList.contains(var1)) {
            this.appenderList.addElement(var1);
         }

      }
   }

   public int appendLoopOnAppenders(LoggingEvent var1) {
      Vector var5 = this.appenderList;
      int var2 = 0;
      byte var3 = 0;
      if (var5 != null) {
         int var4 = var5.size();

         for(var2 = var3; var2 < var4; ++var2) {
            ((Appender)this.appenderList.elementAt(var2)).doAppend(var1);
         }

         var2 = var4;
      }

      return var2;
   }

   public Enumeration getAllAppenders() {
      Vector var1 = this.appenderList;
      return var1 == null ? null : var1.elements();
   }

   public Appender getAppender(String var1) {
      Vector var4 = this.appenderList;
      if (var4 != null && var1 != null) {
         int var3 = var4.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            Appender var5 = (Appender)this.appenderList.elementAt(var2);
            if (var1.equals(var5.getName())) {
               return var5;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public boolean isAttached(Appender var1) {
      Vector var4 = this.appenderList;
      if (var4 != null && var1 != null) {
         int var3 = var4.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            if ((Appender)this.appenderList.elementAt(var2) == var1) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void removeAllAppenders() {
      Vector var3 = this.appenderList;
      if (var3 != null) {
         int var2 = var3.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            ((Appender)this.appenderList.elementAt(var1)).close();
         }

         this.appenderList.removeAllElements();
         this.appenderList = null;
      }

   }

   public void removeAppender(String var1) {
      if (var1 != null) {
         Vector var4 = this.appenderList;
         if (var4 != null) {
            int var3 = var4.size();

            for(int var2 = 0; var2 < var3; ++var2) {
               if (var1.equals(((Appender)this.appenderList.elementAt(var2)).getName())) {
                  this.appenderList.removeElementAt(var2);
                  break;
               }
            }

            return;
         }
      }

   }

   public void removeAppender(Appender var1) {
      if (var1 != null) {
         Vector var2 = this.appenderList;
         if (var2 != null) {
            var2.removeElement(var1);
         }
      }

   }
}
