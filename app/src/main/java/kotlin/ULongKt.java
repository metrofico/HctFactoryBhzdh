package kotlin;

@Metadata(
   d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\nH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\fH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"},
   d2 = {"toULong", "Lkotlin/ULong;", "", "(B)J", "", "(D)J", "", "(F)J", "", "(I)J", "", "(J)J", "", "(S)J", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class ULongKt {
   private static final long toULong(byte var0) {
      return ULong.constructor_impl((long)var0);
   }

   private static final long toULong(double var0) {
      return UnsignedKt.doubleToULong(var0);
   }

   private static final long toULong(float var0) {
      return UnsignedKt.doubleToULong((double)var0);
   }

   private static final long toULong(int var0) {
      return ULong.constructor_impl((long)var0);
   }

   private static final long toULong(long var0) {
      return ULong.constructor_impl(var0);
   }

   private static final long toULong(short var0) {
      return ULong.constructor_impl((long)var0);
   }
}
