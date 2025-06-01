package org.apache.log4j.or.sax;

import org.apache.log4j.or.ObjectRenderer;
import org.xml.sax.Attributes;

public class AttributesRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      if (var1 instanceof Attributes) {
         StringBuffer var5 = new StringBuffer();
         Attributes var6 = (Attributes)var1;
         int var4 = var6.getLength();
         boolean var3 = true;

         for(int var2 = 0; var2 < var4; ++var2) {
            if (var3) {
               var3 = false;
            } else {
               var5.append(", ");
            }

            var5.append(var6.getQName(var2));
            var5.append('=');
            var5.append(var6.getValue(var2));
         }

         return var5.toString();
      } else {
         return var1.toString();
      }
   }
}
