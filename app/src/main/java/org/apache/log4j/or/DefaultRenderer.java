package org.apache.log4j.or;

class DefaultRenderer implements ObjectRenderer {
   public String doRender(Object var1) {
      return var1.toString();
   }
}
