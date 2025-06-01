package kotlin;

@Metadata(
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\f\n\u0002\b\u0006\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0000\u001a\u00020\u0001H\u0087\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"},
   d2 = {"code", "", "", "getCode$annotations", "(C)V", "getCode", "(C)I", "Char", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class CharCodeKt {
   private static final char Char(int var0) {
      if (var0 >= 0 && var0 <= 65535) {
         return (char)var0;
      } else {
         throw new IllegalArgumentException("Invalid Char code: " + var0);
      }
   }

   private static final int getCode(char var0) {
      return var0;
   }

   // $FF: synthetic method
   public static void getCode$annotations(char var0) {
   }
}
