package org.apache.log4j.jmx;

import java.lang.reflect.Method;

class MethodUnion {
   Method readMethod;
   Method writeMethod;

   MethodUnion(Method var1, Method var2) {
      this.readMethod = var1;
      this.writeMethod = var2;
   }
}
