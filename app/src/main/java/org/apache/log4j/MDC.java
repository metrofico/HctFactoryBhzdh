package org.apache.log4j;

import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.ThreadLocalMap;

public class MDC {
   static final int HT_SIZE = 7;
   static final MDC mdc = new MDC();
   boolean java1;
   Object tlm;

   private MDC() {
      boolean var1 = Loader.isJava1();
      this.java1 = var1;
      if (!var1) {
         this.tlm = new ThreadLocalMap();
      }

   }

   public static Object get(String var0) {
      return mdc.get0(var0);
   }

   private Object get0(String var1) {
      if (this.java1) {
         return null;
      } else {
         Hashtable var2 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         return var2 != null && var1 != null ? var2.get(var1) : null;
      }
   }

   public static Hashtable getContext() {
      return mdc.getContext0();
   }

   private Hashtable getContext0() {
      return this.java1 ? null : (Hashtable)((ThreadLocalMap)this.tlm).get();
   }

   public static void put(String var0, Object var1) {
      mdc.put0(var0, var1);
   }

   private void put0(String var1, Object var2) {
      if (!this.java1) {
         Hashtable var4 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         Hashtable var3 = var4;
         if (var4 == null) {
            var3 = new Hashtable(7);
            ((ThreadLocalMap)this.tlm).set(var3);
         }

         var3.put(var1, var2);
      }
   }

   public static void remove(String var0) {
      mdc.remove0(var0);
   }

   private void remove0(String var1) {
      if (!this.java1) {
         Hashtable var2 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         if (var2 != null) {
            var2.remove(var1);
         }
      }

   }
}
