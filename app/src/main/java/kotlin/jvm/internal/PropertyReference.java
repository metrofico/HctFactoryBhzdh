package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;

public abstract class PropertyReference extends CallableReference implements KProperty {
   public PropertyReference() {
   }

   public PropertyReference(Object var1) {
      super(var1);
   }

   public PropertyReference(Object var1, Class var2, String var3, String var4, int var5) {
      boolean var6 = true;
      if ((var5 & 1) != 1) {
         var6 = false;
      }

      super(var1, var2, var3, var4, var6);
   }

   public boolean equals(Object var1) {
      boolean var2 = true;
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof PropertyReference)) {
         return var1 instanceof KProperty ? var1.equals(this.compute()) : false;
      } else {
         PropertyReference var3 = (PropertyReference)var1;
         if (!this.getOwner().equals(var3.getOwner()) || !this.getName().equals(var3.getName()) || !this.getSignature().equals(var3.getSignature()) || !Intrinsics.areEqual(this.getBoundReceiver(), var3.getBoundReceiver())) {
            var2 = false;
         }

         return var2;
      }
   }

   protected KProperty getReflected() {
      return (KProperty)super.getReflected();
   }

   public int hashCode() {
      return (this.getOwner().hashCode() * 31 + this.getName().hashCode()) * 31 + this.getSignature().hashCode();
   }

   public boolean isConst() {
      return this.getReflected().isConst();
   }

   public boolean isLateinit() {
      return this.getReflected().isLateinit();
   }

   public String toString() {
      KCallable var1 = this.compute();
      return var1 != this ? var1.toString() : "property " + this.getName() + " (Kotlin reflection is not available)";
   }
}
