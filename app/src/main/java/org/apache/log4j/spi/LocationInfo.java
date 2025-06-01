package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;

public class LocationInfo implements Serializable {
   public static final String NA = "?";
   static boolean inVisualAge;
   private static PrintWriter pw;
   static final long serialVersionUID = -1325822038990805636L;
   private static StringWriter sw = new StringWriter();
   transient String className;
   transient String fileName;
   public String fullInfo;
   transient String lineNumber;
   transient String methodName;

   static {
      pw = new PrintWriter(sw);
      inVisualAge = false;

      label23:
      try {
         Class.forName("com.ibm.uvm.tools.DebugSupport");
         inVisualAge = true;
         LogLog.debug("Detected IBM VisualAge environment.");
      } finally {
         break label23;
      }

   }

   public LocationInfo(Throwable var1, String var2) {
      if (var1 != null) {
         StringWriter var5 = sw;
         String var7;
         synchronized(var5) {
            var1.printStackTrace(pw);
            var7 = sw.toString();
            sw.getBuffer().setLength(0);
         }

         int var3 = var7.lastIndexOf(var2);
         if (var3 != -1) {
            var3 = var7.indexOf(Layout.LINE_SEP, var3);
            if (var3 != -1) {
               var3 += Layout.LINE_SEP_LEN;
               int var4 = var7.indexOf(Layout.LINE_SEP, var3);
               if (var4 != -1) {
                  if (!inVisualAge) {
                     var3 = var7.lastIndexOf("at ", var4);
                     if (var3 == -1) {
                        return;
                     }

                     var3 += 3;
                  }

                  this.fullInfo = var7.substring(var3, var4);
               }
            }
         }
      }
   }

   public String getClassName() {
      String var3 = this.fullInfo;
      if (var3 == null) {
         return "?";
      } else {
         if (this.className == null) {
            int var1 = var3.lastIndexOf(40);
            if (var1 == -1) {
               this.className = "?";
            } else {
               int var2 = this.fullInfo.lastIndexOf(46, var1);
               var1 = 0;
               if (inVisualAge) {
                  var1 = this.fullInfo.lastIndexOf(32, var2) + 1;
               }

               if (var2 == -1) {
                  this.className = "?";
               } else {
                  this.className = this.fullInfo.substring(var1, var2);
               }
            }
         }

         return this.className;
      }
   }

   public String getFileName() {
      String var3 = this.fullInfo;
      if (var3 == null) {
         return "?";
      } else {
         if (this.fileName == null) {
            int var1 = var3.lastIndexOf(58);
            if (var1 == -1) {
               this.fileName = "?";
            } else {
               int var2 = this.fullInfo.lastIndexOf(40, var1 - 1);
               this.fileName = this.fullInfo.substring(var2 + 1, var1);
            }
         }

         return this.fileName;
      }
   }

   public String getLineNumber() {
      String var3 = this.fullInfo;
      if (var3 == null) {
         return "?";
      } else {
         if (this.lineNumber == null) {
            int var2 = var3.lastIndexOf(41);
            int var1 = this.fullInfo.lastIndexOf(58, var2 - 1);
            if (var1 == -1) {
               this.lineNumber = "?";
            } else {
               this.lineNumber = this.fullInfo.substring(var1 + 1, var2);
            }
         }

         return this.lineNumber;
      }
   }

   public String getMethodName() {
      String var3 = this.fullInfo;
      if (var3 == null) {
         return "?";
      } else {
         if (this.methodName == null) {
            int var2 = var3.lastIndexOf(40);
            int var1 = this.fullInfo.lastIndexOf(46, var2);
            if (var1 == -1) {
               this.methodName = "?";
            } else {
               this.methodName = this.fullInfo.substring(var1 + 1, var2);
            }
         }

         return this.methodName;
      }
   }
}
