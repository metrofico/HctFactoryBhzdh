package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public abstract class Utf8 {
   private static Utf8 DEFAULT;

   public static Utf8 getDefault() {
      if (DEFAULT == null) {
         DEFAULT = new Utf8Safe();
      }

      return DEFAULT;
   }

   public static void setDefault(Utf8 var0) {
      DEFAULT = var0;
   }

   public abstract String decodeUtf8(ByteBuffer var1, int var2, int var3);

   public abstract void encodeUtf8(CharSequence var1, ByteBuffer var2);

   public abstract int encodedLength(CharSequence var1);

   static class DecodeUtil {
      static void handleFourBytes(byte var0, byte var1, byte var2, byte var3, char[] var4, int var5) throws IllegalArgumentException {
         if (!isNotTrailingByte(var1) && (var0 << 28) + var1 + 112 >> 30 == 0 && !isNotTrailingByte(var2) && !isNotTrailingByte(var3)) {
            var0 = (var0 & 7) << 18 | trailingByteValue(var1) << 12 | trailingByteValue(var2) << 6 | trailingByteValue(var3);
            var4[var5] = highSurrogate(var0);
            var4[var5 + 1] = lowSurrogate(var0);
         } else {
            throw new IllegalArgumentException("Invalid UTF-8");
         }
      }

      static void handleOneByte(byte var0, char[] var1, int var2) {
         var1[var2] = (char)var0;
      }

      static void handleThreeBytes(byte var0, byte var1, byte var2, char[] var3, int var4) throws IllegalArgumentException {
         if (!isNotTrailingByte(var1) && (var0 != -32 || var1 >= -96) && (var0 != -19 || var1 < -96) && !isNotTrailingByte(var2)) {
            var3[var4] = (char)((var0 & 15) << 12 | trailingByteValue(var1) << 6 | trailingByteValue(var2));
         } else {
            throw new IllegalArgumentException("Invalid UTF-8");
         }
      }

      static void handleTwoBytes(byte var0, byte var1, char[] var2, int var3) throws IllegalArgumentException {
         if (var0 >= -62) {
            if (!isNotTrailingByte(var1)) {
               var2[var3] = (char)((var0 & 31) << 6 | trailingByteValue(var1));
            } else {
               throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
         } else {
            throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
         }
      }

      private static char highSurrogate(int var0) {
         return (char)((var0 >>> 10) + 'ퟀ');
      }

      private static boolean isNotTrailingByte(byte var0) {
         boolean var1;
         if (var0 > -65) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      static boolean isOneByte(byte var0) {
         boolean var1;
         if (var0 >= 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      static boolean isThreeBytes(byte var0) {
         boolean var1;
         if (var0 < -16) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      static boolean isTwoBytes(byte var0) {
         boolean var1;
         if (var0 < -32) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      private static char lowSurrogate(int var0) {
         return (char)((var0 & 1023) + '\udc00');
      }

      private static int trailingByteValue(byte var0) {
         return var0 & 63;
      }
   }

   static class UnpairedSurrogateException extends IllegalArgumentException {
      UnpairedSurrogateException(int var1, int var2) {
         super("Unpaired surrogate at index " + var1 + " of " + var2);
      }
   }
}
