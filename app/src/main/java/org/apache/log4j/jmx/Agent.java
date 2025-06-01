package org.apache.log4j.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import org.apache.log4j.Logger;

public class Agent {
   static Class class$org$apache$log4j$jmx$Agent;
   static Logger log;

   static {
      Class var1 = class$org$apache$log4j$jmx$Agent;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.jmx.Agent");
         class$org$apache$log4j$jmx$Agent = var0;
      }

      log = Logger.getLogger(var0);
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

   public void start() {
      MBeanServer var1 = MBeanServerFactory.createMBeanServer();
      HtmlAdaptorServer var2 = new HtmlAdaptorServer();

      try {
         log.info("Registering HtmlAdaptorServer instance.");
         ObjectName var3 = new ObjectName("Adaptor:name=html,port=8082");
         var1.registerMBean(var2, var3);
         log.info("Registering HierarchyDynamicMBean instance.");
         HierarchyDynamicMBean var6 = new HierarchyDynamicMBean();
         ObjectName var4 = new ObjectName("log4j:hiearchy=default");
         var1.registerMBean(var6, var4);
      } catch (Exception var5) {
         log.error("Problem while regitering MBeans instances.", var5);
         return;
      }

      var2.start();
   }
}
