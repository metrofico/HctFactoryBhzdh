package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty0;

public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
   public PropertyReference0() {
   }

   public PropertyReference0(Object var1) {
      super(var1);
   }

   public PropertyReference0(Object var1, Class var2, String var3, String var4, int var5) {
      super(var1, var2, var3, var4, var5);
   }

   protected KCallable computeReflected() {
      return Reflection.property0(this);
   }

   public Object getDelegate() {
      return ((KProperty0)this.getReflected()).getDelegate();
   }

   public KProperty0.Getter getGetter() {
      return ((KProperty0)this.getReflected()).getGetter();
   }

   public Object invoke() {
      return this.get();
   }
}
