package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J$\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0016H\u0016¨\u0006\u0018"},
   d2 = {"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Default", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class Random {
   public static final Default Default = new Default((DefaultConstructorMarker)null);
   private static final Random defaultRandom;

   static {
      defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
   }

   // $FF: synthetic method
   public static byte[] nextBytes$default(Random var0, byte[] var1, int var2, int var3, int var4, Object var5) {
      if (var5 == null) {
         if ((var4 & 2) != 0) {
            var2 = 0;
         }

         if ((var4 & 4) != 0) {
            var3 = var1.length;
         }

         return var0.nextBytes(var1, var2, var3);
      } else {
         throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
      }
   }

   public abstract int nextBits(int var1);

   public boolean nextBoolean() {
      boolean var1 = true;
      if (this.nextBits(1) == 0) {
         var1 = false;
      }

      return var1;
   }

   public byte[] nextBytes(int var1) {
      return this.nextBytes(new byte[var1]);
   }

   public byte[] nextBytes(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "array");
      return this.nextBytes(var1, 0, var1.length);
   }

   public byte[] nextBytes(byte[] var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "array");
      int var4 = var1.length;
      byte var5 = 0;
      boolean var8 = (new IntRange(0, var4)).contains(var2);
      boolean var6 = true;
      boolean var9;
      if (var8 && (new IntRange(0, var1.length)).contains(var3)) {
         var9 = true;
      } else {
         var9 = false;
      }

      if (!var9) {
         throw new IllegalArgumentException(("fromIndex (" + var2 + ") or toIndex (" + var3 + ") are out of range: 0.." + var1.length + '.').toString());
      } else {
         if (var2 <= var3) {
            var9 = var6;
         } else {
            var9 = false;
         }

         if (!var9) {
            throw new IllegalArgumentException(("fromIndex (" + var2 + ") must be not greater than toIndex (" + var3 + ").").toString());
         } else {
            int var10 = (var3 - var2) / 4;

            for(var4 = 0; var4 < var10; ++var4) {
               int var7 = this.nextInt();
               var1[var2] = (byte)var7;
               var1[var2 + 1] = (byte)(var7 >>> 8);
               var1[var2 + 2] = (byte)(var7 >>> 16);
               var1[var2 + 3] = (byte)(var7 >>> 24);
               var2 += 4;
            }

            var10 = var3 - var2;
            var4 = this.nextBits(var10 * 8);

            for(var3 = var5; var3 < var10; ++var3) {
               var1[var2 + var3] = (byte)(var4 >>> var3 * 8);
            }

            return var1;
         }
      }
   }

   public double nextDouble() {
      return PlatformRandomKt.doubleFromParts(this.nextBits(26), this.nextBits(27));
   }

   public double nextDouble(double var1) {
      return this.nextDouble(0.0, var1);
   }

   public double nextDouble(double var1, double var3) {
      double var5;
      label36: {
         RandomKt.checkRangeBounds(var1, var3);
         var5 = var3 - var1;
         if (Double.isInfinite(var5)) {
            boolean var11 = Double.isInfinite(var1);
            boolean var10 = true;
            boolean var9;
            if (!var11 && !Double.isNaN(var1)) {
               var9 = true;
            } else {
               var9 = false;
            }

            if (var9) {
               if (!Double.isInfinite(var3) && !Double.isNaN(var3)) {
                  var9 = var10;
               } else {
                  var9 = false;
               }

               if (var9) {
                  var5 = this.nextDouble();
                  double var7 = (double)2;
                  var5 *= var3 / var7 - var1 / var7;
                  var1 = var1 + var5 + var5;
                  break label36;
               }
            }
         }

         var1 += this.nextDouble() * var5;
      }

      var5 = var1;
      if (var1 >= var3) {
         var5 = Math.nextAfter(var3, Double.NEGATIVE_INFINITY);
      }

      return var5;
   }

   public float nextFloat() {
      return (float)this.nextBits(24) / 1.6777216E7F;
   }

   public int nextInt() {
      return this.nextBits(32);
   }

   public int nextInt(int var1) {
      return this.nextInt(0, var1);
   }

   public int nextInt(int var1, int var2) {
      RandomKt.checkRangeBounds(var1, var2);
      int var3 = var2 - var1;
      if (var3 <= 0 && var3 != Integer.MIN_VALUE) {
         int var5;
         boolean var6;
         do {
            var5 = this.nextInt();
            boolean var7 = false;
            var6 = var7;
            if (var1 <= var5) {
               var6 = var7;
               if (var5 < var2) {
                  var6 = true;
               }
            }
         } while(!var6);

         return var5;
      } else {
         int var4;
         if ((-var3 & var3) == var3) {
            var2 = this.nextBits(RandomKt.fastLog2(var3));
         } else {
            do {
               var4 = this.nextInt() >>> 1;
               var2 = var4 % var3;
            } while(var4 - var2 + (var3 - 1) < 0);
         }

         return var1 + var2;
      }
   }

   public long nextLong() {
      return ((long)this.nextInt() << 32) + (long)this.nextInt();
   }

   public long nextLong(long var1) {
      return this.nextLong(0L, var1);
   }

   public long nextLong(long var1, long var3) {
      RandomKt.checkRangeBounds(var1, var3);
      long var7 = var3 - var1;
      if (var7 > 0L) {
         long var9;
         if ((-var7 & var7) == var7) {
            int var11 = (int)var7;
            int var12 = (int)(var7 >>> 32);
            if (var11 != 0) {
               var11 = this.nextBits(RandomKt.fastLog2(var11));
            } else {
               if (var12 != 1) {
                  var3 = ((long)this.nextBits(RandomKt.fastLog2(var12)) << 32) + ((long)this.nextInt() & 4294967295L);
                  return var1 + var3;
               }

               var11 = this.nextInt();
            }

            var3 = (long)var11 & 4294967295L;
         } else {
            do {
               var9 = this.nextLong() >>> 1;
               var3 = var9 % var7;
            } while(var9 - var3 + (var7 - 1L) < 0L);
         }

         return var1 + var3;
      } else {
         boolean var5;
         do {
            var7 = this.nextLong();
            boolean var6 = false;
            var5 = var6;
            if (var1 <= var7) {
               var5 = var6;
               if (var7 < var3) {
                  var5 = true;
               }
            }
         } while(!var5);

         return var7;
      }
   }

   @Metadata(
      d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\u0018\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0019H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"},
      d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "writeReplace", "", "Serialized", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Default extends Random implements Serializable {
      private Default() {
      }

      // $FF: synthetic method
      public Default(DefaultConstructorMarker var1) {
         this();
      }

      private final Object writeReplace() {
         return Serialized.INSTANCE;
      }

      public int nextBits(int var1) {
         return Random.defaultRandom.nextBits(var1);
      }

      public boolean nextBoolean() {
         return Random.defaultRandom.nextBoolean();
      }

      public byte[] nextBytes(int var1) {
         return Random.defaultRandom.nextBytes(var1);
      }

      public byte[] nextBytes(byte[] var1) {
         Intrinsics.checkNotNullParameter(var1, "array");
         return Random.defaultRandom.nextBytes(var1);
      }

      public byte[] nextBytes(byte[] var1, int var2, int var3) {
         Intrinsics.checkNotNullParameter(var1, "array");
         return Random.defaultRandom.nextBytes(var1, var2, var3);
      }

      public double nextDouble() {
         return Random.defaultRandom.nextDouble();
      }

      public double nextDouble(double var1) {
         return Random.defaultRandom.nextDouble(var1);
      }

      public double nextDouble(double var1, double var3) {
         return Random.defaultRandom.nextDouble(var1, var3);
      }

      public float nextFloat() {
         return Random.defaultRandom.nextFloat();
      }

      public int nextInt() {
         return Random.defaultRandom.nextInt();
      }

      public int nextInt(int var1) {
         return Random.defaultRandom.nextInt(var1);
      }

      public int nextInt(int var1, int var2) {
         return Random.defaultRandom.nextInt(var1, var2);
      }

      public long nextLong() {
         return Random.defaultRandom.nextLong();
      }

      public long nextLong(long var1) {
         return Random.defaultRandom.nextLong(var1);
      }

      public long nextLong(long var1, long var3) {
         return Random.defaultRandom.nextLong(var1, var3);
      }

      @Metadata(
         d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\bÂ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
         d2 = {"Lkotlin/random/Random$Default$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "serialVersionUID", "", "readResolve", "", "kotlin-stdlib"},
         k = 1,
         mv = {1, 7, 1},
         xi = 48
      )
      private static final class Serialized implements Serializable {
         public static final Serialized INSTANCE = new Serialized();
         private static final long serialVersionUID = 0L;

         private final Object readResolve() {
            return Random.Default;
         }
      }
   }
}
