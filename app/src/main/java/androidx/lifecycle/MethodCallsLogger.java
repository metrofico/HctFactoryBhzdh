package androidx.lifecycle;

import java.util.HashMap;
import java.util.Map;

public class MethodCallsLogger {
   private Map mCalledMethods = new HashMap();

   public boolean approveCall(String var1, int var2) {
      Integer var5 = (Integer)this.mCalledMethods.get(var1);
      boolean var4 = false;
      int var3;
      if (var5 != null) {
         var3 = var5;
      } else {
         var3 = 0;
      }

      if ((var3 & var2) != 0) {
         var4 = true;
      }

      this.mCalledMethods.put(var1, var2 | var3);
      return var4 ^ true;
   }
}
