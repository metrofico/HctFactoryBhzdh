package org.apache.log4j.or;

import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.RendererSupport;

public class RendererMap {
   static Class class$org$apache$log4j$or$ObjectRenderer;
   static ObjectRenderer defaultRenderer = new DefaultRenderer();
   Hashtable map = new Hashtable();

   public static void addRenderer(RendererSupport var0, String var1, String var2) {
      LogLog.debug("Rendering class: [" + var2 + "], Rendered class: [" + var1 + "].");
      Class var4 = class$org$apache$log4j$or$ObjectRenderer;
      Class var3 = var4;
      if (var4 == null) {
         var3 = class$("org.apache.log4j.or.ObjectRenderer");
         class$org$apache$log4j$or$ObjectRenderer = var3;
      }

      ObjectRenderer var6 = (ObjectRenderer)OptionConverter.instantiateByClassName(var2, var3, (Object)null);
      if (var6 == null) {
         LogLog.error("Could not instantiate renderer [" + var2 + "].");
      } else {
         try {
            var0.setRenderer(Loader.loadClass(var1), var6);
         } catch (ClassNotFoundException var5) {
            LogLog.error("Could not find class [" + var1 + "].", var5);
         }

      }
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public void clear() {
      this.map.clear();
   }

   public String findAndRender(Object var1) {
      return var1 == null ? null : this.get(var1.getClass()).doRender(var1);
   }

   public ObjectRenderer get(Class var1) {
      while(var1 != null) {
         ObjectRenderer var2 = (ObjectRenderer)this.map.get(var1);
         if (var2 != null) {
            return var2;
         }

         var2 = this.searchInterfaces(var1);
         if (var2 != null) {
            return var2;
         }

         var1 = var1.getSuperclass();
      }

      return defaultRenderer;
   }

   public ObjectRenderer get(Object var1) {
      return var1 == null ? null : this.get(var1.getClass());
   }

   public ObjectRenderer getDefaultRenderer() {
      return defaultRenderer;
   }

   public void put(Class var1, ObjectRenderer var2) {
      this.map.put(var1, var2);
   }

   ObjectRenderer searchInterfaces(Class var1) {
      ObjectRenderer var3 = (ObjectRenderer)this.map.get(var1);
      if (var3 != null) {
         return var3;
      } else {
         Class[] var5 = var1.getInterfaces();

         for(int var2 = 0; var2 < var5.length; ++var2) {
            ObjectRenderer var4 = this.searchInterfaces(var5[var2]);
            if (var4 != null) {
               return var4;
            }
         }

         return null;
      }
   }
}
