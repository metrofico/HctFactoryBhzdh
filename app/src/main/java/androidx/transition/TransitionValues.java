package androidx.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TransitionValues {
   final ArrayList mTargetedTransitions = new ArrayList();
   public final Map values = new HashMap();
   public View view;

   public boolean equals(Object var1) {
      if (var1 instanceof TransitionValues) {
         View var2 = this.view;
         TransitionValues var3 = (TransitionValues)var1;
         if (var2 == var3.view && this.values.equals(var3.values)) {
            return true;
         }
      }

      return false;
   }

   public int hashCode() {
      return this.view.hashCode() * 31 + this.values.hashCode();
   }

   public String toString() {
      String var1 = "TransitionValues@" + Integer.toHexString(this.hashCode()) + ":\n";
      var1 = var1 + "    view = " + this.view + "\n";
      var1 = var1 + "    values:";

      String var3;
      for(Iterator var2 = this.values.keySet().iterator(); var2.hasNext(); var1 = var1 + "    " + var3 + ": " + this.values.get(var3) + "\n") {
         var3 = (String)var2.next();
      }

      return var1;
   }
}
