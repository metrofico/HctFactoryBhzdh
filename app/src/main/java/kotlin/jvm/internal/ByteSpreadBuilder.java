package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
   d2 = {"Lkotlin/jvm/internal/ByteSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ByteSpreadBuilder extends PrimitiveSpreadBuilder {
   private final byte[] values;

   public ByteSpreadBuilder(int var1) {
      super(var1);
      this.values = new byte[var1];
   }

   public final void add(byte var1) {
      byte[] var3 = this.values;
      int var2 = this.getPosition();
      this.setPosition(var2 + 1);
      var3[var2] = var1;
   }

   protected int getSize(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "<this>");
      return var1.length;
   }

   public final byte[] toArray() {
      return (byte[])this.toArray(this.values, new byte[this.size()]);
   }
}
