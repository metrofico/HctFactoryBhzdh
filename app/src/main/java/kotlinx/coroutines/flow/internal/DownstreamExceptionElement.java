package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"},
   d2 = {"Lkotlinx/coroutines/flow/internal/DownstreamExceptionElement;", "Lkotlin/coroutines/CoroutineContext$Element;", "e", "", "(Ljava/lang/Throwable;)V", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "Key", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class DownstreamExceptionElement implements CoroutineContext.Element {
   public static final Key Key = new Key((DefaultConstructorMarker)null);
   public final Throwable e;
   private final CoroutineContext.Key key;

   public DownstreamExceptionElement(Throwable var1) {
      this.e = var1;
      this.key = (CoroutineContext.Key)Key;
   }

   public Object fold(Object var1, Function2 var2) {
      return DefaultImpls.fold(this, var1, var2);
   }

   public Element get(CoroutineContext.Key var1) {
      return DefaultImpls.get(this, var1);
   }

   public CoroutineContext.Key getKey() {
      return this.key;
   }

   public CoroutineContext minusKey(CoroutineContext.Key var1) {
      return DefaultImpls.minusKey(this, var1);
   }

   public CoroutineContext plus(CoroutineContext var1) {
      return DefaultImpls.plus(this, var1);
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"},
      d2 = {"Lkotlinx/coroutines/flow/internal/DownstreamExceptionElement$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/flow/internal/DownstreamExceptionElement;", "()V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Key implements CoroutineContext.Key {
      private Key() {
      }

      // $FF: synthetic method
      public Key(DefaultConstructorMarker var1) {
         this();
      }
   }
}
