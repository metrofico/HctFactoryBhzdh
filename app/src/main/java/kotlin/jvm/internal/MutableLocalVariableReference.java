package kotlin.jvm.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.reflect.KDeclarationContainer;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\n"},
   d2 = {"Lkotlin/jvm/internal/MutableLocalVariableReference;", "Lkotlin/jvm/internal/MutablePropertyReference0;", "()V", "get", "", "getOwner", "Lkotlin/reflect/KDeclarationContainer;", "set", "", "value", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class MutableLocalVariableReference extends MutablePropertyReference0 {
   public Object get() {
      LocalVariableReferencesKt.access$notSupportedError();
      throw new KotlinNothingValueException();
   }

   public KDeclarationContainer getOwner() {
      LocalVariableReferencesKt.access$notSupportedError();
      throw new KotlinNothingValueException();
   }

   public void set(Object var1) {
      LocalVariableReferencesKt.access$notSupportedError();
      throw new KotlinNothingValueException();
   }
}
