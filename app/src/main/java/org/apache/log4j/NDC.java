package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.log4j.helpers.LogLog;

public class NDC {
   static final int REAP_THRESHOLD = 5;
   static Hashtable ht = new Hashtable();
   static int pushCounter = 0;

   private NDC() {
   }

   public static void clear() {
      Stack var0 = getCurrentStack();
      if (var0 != null) {
         var0.setSize(0);
      }

   }

   public static Stack cloneStack() {
      Stack var0 = getCurrentStack();
      return var0 == null ? null : (Stack)var0.clone();
   }

   public static String get() {
      Stack var0 = getCurrentStack();
      return var0 != null && !var0.isEmpty() ? ((DiagnosticContext)var0.peek()).fullMessage : null;
   }

   private static Stack getCurrentStack() {
      Hashtable var0 = ht;
      return var0 != null ? (Stack)var0.get(Thread.currentThread()) : null;
   }

   public static int getDepth() {
      Stack var0 = getCurrentStack();
      return var0 == null ? 0 : var0.size();
   }

   public static void inherit(Stack var0) {
      if (var0 != null) {
         ht.put(Thread.currentThread(), var0);
      }

   }

   private static void lazyRemove() {
      Hashtable var3 = ht;
      if (var3 != null) {
         synchronized(var3){}

         Throwable var10000;
         label595: {
            int var0;
            boolean var10001;
            try {
               var0 = pushCounter + 1;
               pushCounter = var0;
            } catch (Throwable var62) {
               var10000 = var62;
               var10001 = false;
               break label595;
            }

            if (var0 <= 5) {
               label568:
               try {
                  return;
               } catch (Throwable var56) {
                  var10000 = var56;
                  var10001 = false;
                  break label568;
               }
            } else {
               label601: {
                  byte var1 = 0;

                  Vector var4;
                  Enumeration var6;
                  try {
                     pushCounter = 0;
                     var4 = new Vector();
                     var6 = ht.keys();
                  } catch (Throwable var59) {
                     var10000 = var59;
                     var10001 = false;
                     break label601;
                  }

                  label588:
                  while(true) {
                     var0 = 0;

                     Thread var5;
                     while(true) {
                        try {
                           if (!var6.hasMoreElements()) {
                              break label588;
                           }
                        } catch (Throwable var60) {
                           var10000 = var60;
                           var10001 = false;
                           break label601;
                        }

                        if (var0 > 4) {
                           break label588;
                        }

                        try {
                           var5 = (Thread)var6.nextElement();
                           if (!var5.isAlive()) {
                              break;
                           }
                        } catch (Throwable var61) {
                           var10000 = var61;
                           var10001 = false;
                           break label601;
                        }

                        ++var0;
                     }

                     try {
                        var4.addElement(var5);
                     } catch (Throwable var58) {
                        var10000 = var58;
                        var10001 = false;
                        break label601;
                     }
                  }

                  try {
                     ;
                  } catch (Throwable var57) {
                     var10000 = var57;
                     var10001 = false;
                     break label601;
                  }

                  int var2 = var4.size();

                  for(var0 = var1; var0 < var2; ++var0) {
                     Thread var63 = (Thread)var4.elementAt(var0);
                     LogLog.debug("Lazy NDC removal for thread [" + var63.getName() + "] (" + ht.size() + ").");
                     ht.remove(var63);
                  }

                  return;
               }
            }
         }

         Throwable var64 = var10000;
         throw var64;
      }
   }

   public static String peek() {
      Stack var0 = getCurrentStack();
      return var0 != null && !var0.isEmpty() ? ((DiagnosticContext)var0.peek()).message : "";
   }

   public static String pop() {
      Stack var0 = getCurrentStack();
      return var0 != null && !var0.isEmpty() ? ((DiagnosticContext)var0.pop()).message : "";
   }

   public static void push(String var0) {
      Stack var1 = getCurrentStack();
      if (var1 == null) {
         DiagnosticContext var3 = new DiagnosticContext(var0, (DiagnosticContext)null);
         Stack var2 = new Stack();
         Thread var4 = Thread.currentThread();
         ht.put(var4, var2);
         var2.push(var3);
      } else if (var1.isEmpty()) {
         var1.push(new DiagnosticContext(var0, (DiagnosticContext)null));
      } else {
         var1.push(new DiagnosticContext(var0, (DiagnosticContext)var1.peek()));
      }

   }

   public static void remove() {
      ht.remove(Thread.currentThread());
      lazyRemove();
   }

   public static void setMaxDepth(int var0) {
      Stack var1 = getCurrentStack();
      if (var1 != null && var0 < var1.size()) {
         var1.setSize(var0);
      }

   }

   private static class DiagnosticContext {
      String fullMessage;
      String message;

      DiagnosticContext(String var1, DiagnosticContext var2) {
         this.message = var1;
         if (var2 != null) {
            this.fullMessage = var2.fullMessage + ' ' + var1;
         } else {
            this.fullMessage = var1;
         }

      }
   }
}
