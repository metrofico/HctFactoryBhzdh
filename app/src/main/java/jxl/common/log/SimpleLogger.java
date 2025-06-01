package jxl.common.log;

import jxl.common.Logger;

public class SimpleLogger extends Logger {
   private boolean suppressWarnings = false;

   public void debug(Object var1) {
      if (!this.suppressWarnings) {
         System.out.print("Debug: ");
         System.out.println(var1);
      }

   }

   public void debug(Object var1, Throwable var2) {
      if (!this.suppressWarnings) {
         System.out.print("Debug: ");
         System.out.println(var1);
         var2.printStackTrace();
      }

   }

   public void error(Object var1) {
      System.err.print("Error: ");
      System.err.println(var1);
   }

   public void error(Object var1, Throwable var2) {
      System.err.print("Error: ");
      System.err.println(var1);
      var2.printStackTrace();
   }

   public void fatal(Object var1) {
      System.err.print("Fatal: ");
      System.err.println(var1);
   }

   public void fatal(Object var1, Throwable var2) {
      System.err.print("Fatal:  ");
      System.err.println(var1);
      var2.printStackTrace();
   }

   protected Logger getLoggerImpl(Class var1) {
      return this;
   }

   public void info(Object var1) {
      if (!this.suppressWarnings) {
         System.out.println(var1);
      }

   }

   public void info(Object var1, Throwable var2) {
      if (!this.suppressWarnings) {
         System.out.println(var1);
         var2.printStackTrace();
      }

   }

   public void setSuppressWarnings(boolean var1) {
      this.suppressWarnings = var1;
   }

   public void warn(Object var1) {
      if (!this.suppressWarnings) {
         System.err.print("Warning:  ");
         System.err.println(var1);
      }

   }

   public void warn(Object var1, Throwable var2) {
      if (!this.suppressWarnings) {
         System.err.print("Warning:  ");
         System.err.println(var1);
         var2.printStackTrace();
      }

   }
}
