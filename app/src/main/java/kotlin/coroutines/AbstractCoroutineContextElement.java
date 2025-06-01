package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
   d2 = {"Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)V", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class AbstractCoroutineContextElement implements CoroutineContext.Element {
   private final Key key;

   public AbstractCoroutineContextElement(Key var1) {
      Intrinsics.checkNotNullParameter(var1, "key");
      super();
      this.key = var1;
   }

   public Object fold(Object var1, Function2 var2) {
      return DefaultImpls.fold(this, var1, var2);
   }

   public Element get(Key var1) {
      return DefaultImpls.get(this, var1);
   }

   public Key getKey() {
      return this.key;
   }

   public CoroutineContext minusKey(Key var1) {
      return DefaultImpls.minusKey(this, var1);
   }

   public CoroutineContext plus(CoroutineContext var1) {
      return DefaultImpls.plus(this, var1);
   }
}
