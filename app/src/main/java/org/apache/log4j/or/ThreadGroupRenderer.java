package org.apache.log4j.or;

import org.apache.log4j.Layout;

public class ThreadGroupRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      if (!(var1 instanceof ThreadGroup)) {
         return var1.toString();
      } else {
         StringBuffer var4 = new StringBuffer();
         ThreadGroup var6 = (ThreadGroup)var1;
         var4.append("java.lang.ThreadGroup[name=");
         var4.append(var6.getName());
         var4.append(", maxpri=");
         var4.append(var6.getMaxPriority());
         var4.append("]");
         int var3 = var6.activeCount();
         Thread[] var5 = new Thread[var3];
         var6.enumerate(var5);

         for(int var2 = 0; var2 < var3; ++var2) {
            var4.append(Layout.LINE_SEP);
            var4.append("   Thread=[");
            var4.append(var5[var2].getName());
            var4.append(",");
            var4.append(var5[var2].getPriority());
            var4.append(",");
            var4.append(var5[var2].isDaemon());
            var4.append("]");
         }

         return var4.toString();
      }
   }
}
