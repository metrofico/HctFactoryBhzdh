package org.apache.log4j.config;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertyPrinter implements PropertyGetter.PropertyCallback {
   protected Hashtable appenderNames;
   protected boolean doCapitalize;
   protected Hashtable layoutNames;
   protected int numAppenders;
   protected PrintWriter out;

   public PropertyPrinter(PrintWriter var1) {
      this(var1, false);
   }

   public PropertyPrinter(PrintWriter var1, boolean var2) {
      this.numAppenders = 0;
      this.appenderNames = new Hashtable();
      this.layoutNames = new Hashtable();
      this.out = var1;
      this.doCapitalize = var2;
      this.print(var1);
      var1.flush();
   }

   public static String capitalize(String var0) {
      String var1 = var0;
      if (Character.isLowerCase(var0.charAt(0))) {
         if (var0.length() != 1) {
            var1 = var0;
            if (!Character.isLowerCase(var0.charAt(1))) {
               return var1;
            }
         }

         StringBuffer var2 = new StringBuffer(var0);
         var2.setCharAt(0, Character.toUpperCase(var0.charAt(0)));
         var1 = var2.toString();
      }

      return var1;
   }

   public static void main(String[] var0) {
      new PropertyPrinter(new PrintWriter(System.out));
   }

   public void foundProperty(Object var1, String var2, String var3, Object var4) {
      if (!(var1 instanceof Appender) || !"name".equals(var3)) {
         String var5 = var3;
         if (this.doCapitalize) {
            var5 = capitalize(var3);
         }

         this.out.println(var2 + var5 + "=" + var4.toString());
      }
   }

   protected String genAppName() {
      StringBuffer var2 = (new StringBuffer()).append("A");
      int var1 = this.numAppenders++;
      return var2.append(var1).toString();
   }

   protected boolean isGenAppName(String var1) {
      if (var1.length() >= 2 && var1.charAt(0) == 'A') {
         int var2 = 0;

         while(true) {
            if (var2 >= var1.length()) {
               return true;
            }

            if (var1.charAt(var2) < '0' || var1.charAt(var2) > '9') {
               break;
            }

            ++var2;
         }
      }

      return false;
   }

   public void print(PrintWriter var1) {
      this.printOptions(var1, Logger.getRootLogger());
      Enumeration var2 = LogManager.getCurrentLoggers();

      while(var2.hasMoreElements()) {
         this.printOptions(var1, (Logger)var2.nextElement());
      }

   }

   protected void printOptions(PrintWriter var1, Object var2, String var3) {
      var1.println(var3 + "=" + var2.getClass().getName());
      PropertyGetter.getProperties(var2, this, var3 + ".");
   }

   protected void printOptions(PrintWriter var1, Logger var2) {
      Enumeration var6 = var2.getAllAppenders();
      Level var3 = var2.getLevel();
      String var8;
      if (var3 == null) {
         var8 = "";
      } else {
         var8 = var3.toString();
      }

      String var4;
      String var5;
      for(; var6.hasMoreElements(); var8 = var8 + ", " + var5) {
         Appender var7 = (Appender)var6.nextElement();
         var4 = (String)this.appenderNames.get(var7);
         var5 = var4;
         if (var4 == null) {
            label40: {
               var5 = var7.getName();
               if (var5 != null) {
                  var4 = var5;
                  if (!this.isGenAppName(var5)) {
                     break label40;
                  }
               }

               var4 = this.genAppName();
            }

            this.appenderNames.put(var7, var4);
            this.printOptions(var1, var7, "log4j.appender." + var4);
            var5 = var4;
            if (var7.getLayout() != null) {
               this.printOptions(var1, var7.getLayout(), "log4j.appender." + var4 + ".layout");
               var5 = var4;
            }
         }
      }

      if (var2 == Logger.getRootLogger()) {
         var4 = "log4j.rootLogger";
      } else {
         var4 = "log4j.logger." + var2.getName();
      }

      if (var8 != "") {
         var1.println(var4 + "=" + var8);
      }

      if (!var2.getAdditivity() && var2 != Logger.getRootLogger()) {
         var1.println("log4j.additivity." + var2.getName() + "=false");
      }

   }
}
