package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.reflect.KFunction;

public class FunInterfaceConstructorReference extends FunctionReference implements Serializable {
   private final Class funInterface;

   public FunInterfaceConstructorReference(Class var1) {
      super(1);
      this.funInterface = var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof FunInterfaceConstructorReference)) {
         return false;
      } else {
         FunInterfaceConstructorReference var2 = (FunInterfaceConstructorReference)var1;
         return this.funInterface.equals(var2.funInterface);
      }
   }

   protected KFunction getReflected() {
      throw new UnsupportedOperationException("Functional interface constructor does not support reflection");
   }

   public int hashCode() {
      return this.funInterface.hashCode();
   }

   public String toString() {
      return "fun interface " + this.funInterface.getName();
   }
}
