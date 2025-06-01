package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000F\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0005\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a9\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\t\"\u0002H\u0001H\u0007¢\u0006\u0002\u0010\n\u001a\u0019\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\b\u001a\u00020\f\"\u00020\u000bH\u0007\u001a\u0019\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\n\u0010\b\u001a\u00020\u000e\"\u00020\rH\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\n\u0010\b\u001a\u00020\u0010\"\u00020\u000fH\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0000\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\n\u0010\b\u001a\u00020\u0012\"\u00020\u0011H\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0013H\u0087\b\u001a!\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013H\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\n\u0010\b\u001a\u00020\u0014\"\u00020\u0013H\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0015H\u0087\b\u001a!\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b\u001a\u001c\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\n\u0010\b\u001a\u00020\u0016\"\u00020\u0015H\u0007\u001a-\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0005\u001a5\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\u0006\u001a\u0002H\u0001H\u0007¢\u0006\u0002\u0010\u0007\u001a9\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\t\"\u0002H\u0001H\u0007¢\u0006\u0002\u0010\n\u001a\u0019\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\b\u001a!\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000bH\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\b\u001a\u00020\f\"\u00020\u000bH\u0007\u001a\u0019\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0087\b\u001a!\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\n\u0010\b\u001a\u00020\u000e\"\u00020\rH\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000fH\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f2\n\u0010\b\u001a\u00020\u0010\"\u00020\u000fH\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\n\u0010\b\u001a\u00020\u0012\"\u00020\u0011H\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0013H\u0087\b\u001a!\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013H\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00132\n\u0010\b\u001a\u00020\u0014\"\u00020\u0013H\u0007\u001a\u0019\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0015H\u0087\b\u001a!\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\b\u001a\u001c\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\n\u0010\b\u001a\u00020\u0016\"\u00020\u0015H\u0007¨\u0006\u0018"},
   d2 = {"maxOf", "T", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "other", "", "(Ljava/lang/Comparable;[Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "", "", "", "", "", "", "minOf", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/comparisons/ComparisonsKt"
)
class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
   public ComparisonsKt___ComparisonsJvmKt() {
   }

   private static final byte maxOf(byte var0, byte var1) {
      return (byte)Math.max(var0, var1);
   }

   private static final byte maxOf(byte var0, byte var1, byte var2) {
      return (byte)Math.max(var0, Math.max(var1, var2));
   }

   public static final byte maxOf(byte var0, byte... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = (byte)Math.max(var0, var1[var2]);
      }

      return var0;
   }

   private static final double maxOf(double var0, double var2) {
      return Math.max(var0, var2);
   }

   private static final double maxOf(double var0, double var2, double var4) {
      return Math.max(var0, Math.max(var2, var4));
   }

   public static final double maxOf(double var0, double... var2) {
      Intrinsics.checkNotNullParameter(var2, "other");
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         var0 = Math.max(var0, var2[var3]);
      }

      return var0;
   }

   private static final float maxOf(float var0, float var1) {
      return Math.max(var0, var1);
   }

   private static final float maxOf(float var0, float var1, float var2) {
      return Math.max(var0, Math.max(var1, var2));
   }

   public static final float maxOf(float var0, float... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = Math.max(var0, var1[var2]);
      }

      return var0;
   }

   private static final int maxOf(int var0, int var1) {
      return Math.max(var0, var1);
   }

   private static final int maxOf(int var0, int var1, int var2) {
      return Math.max(var0, Math.max(var1, var2));
   }

   public static final int maxOf(int var0, int... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var4 = var1.length;
      byte var3 = 0;
      int var2 = var0;

      for(var0 = var3; var0 < var4; ++var0) {
         var2 = Math.max(var2, var1[var0]);
      }

      return var2;
   }

   private static final long maxOf(long var0, long var2) {
      return Math.max(var0, var2);
   }

   private static final long maxOf(long var0, long var2, long var4) {
      return Math.max(var0, Math.max(var2, var4));
   }

   public static final long maxOf(long var0, long... var2) {
      Intrinsics.checkNotNullParameter(var2, "other");
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         var0 = Math.max(var0, var2[var3]);
      }

      return var0;
   }

   public static final Comparable maxOf(Comparable var0, Comparable var1) {
      Intrinsics.checkNotNullParameter(var0, "a");
      Intrinsics.checkNotNullParameter(var1, "b");
      if (var0.compareTo(var1) < 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final Comparable maxOf(Comparable var0, Comparable var1, Comparable var2) {
      Intrinsics.checkNotNullParameter(var0, "a");
      Intrinsics.checkNotNullParameter(var1, "b");
      Intrinsics.checkNotNullParameter(var2, "c");
      return ComparisonsKt.maxOf(var0, ComparisonsKt.maxOf(var1, var2));
   }

   public static final Comparable maxOf(Comparable var0, Comparable... var1) {
      Intrinsics.checkNotNullParameter(var0, "a");
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = ComparisonsKt.maxOf(var0, var1[var2]);
      }

      return var0;
   }

   private static final short maxOf(short var0, short var1) {
      return (short)Math.max(var0, var1);
   }

   private static final short maxOf(short var0, short var1, short var2) {
      return (short)Math.max(var0, Math.max(var1, var2));
   }

   public static final short maxOf(short var0, short... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = (short)Math.max(var0, var1[var2]);
      }

      return var0;
   }

   private static final byte minOf(byte var0, byte var1) {
      return (byte)Math.min(var0, var1);
   }

   private static final byte minOf(byte var0, byte var1, byte var2) {
      return (byte)Math.min(var0, Math.min(var1, var2));
   }

   public static final byte minOf(byte var0, byte... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = (byte)Math.min(var0, var1[var2]);
      }

      return var0;
   }

   private static final double minOf(double var0, double var2) {
      return Math.min(var0, var2);
   }

   private static final double minOf(double var0, double var2, double var4) {
      return Math.min(var0, Math.min(var2, var4));
   }

   public static final double minOf(double var0, double... var2) {
      Intrinsics.checkNotNullParameter(var2, "other");
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         var0 = Math.min(var0, var2[var3]);
      }

      return var0;
   }

   private static final float minOf(float var0, float var1) {
      return Math.min(var0, var1);
   }

   private static final float minOf(float var0, float var1, float var2) {
      return Math.min(var0, Math.min(var1, var2));
   }

   public static final float minOf(float var0, float... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = Math.min(var0, var1[var2]);
      }

      return var0;
   }

   private static final int minOf(int var0, int var1) {
      return Math.min(var0, var1);
   }

   private static final int minOf(int var0, int var1, int var2) {
      return Math.min(var0, Math.min(var1, var2));
   }

   public static final int minOf(int var0, int... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = Math.min(var0, var1[var2]);
      }

      return var0;
   }

   private static final long minOf(long var0, long var2) {
      return Math.min(var0, var2);
   }

   private static final long minOf(long var0, long var2, long var4) {
      return Math.min(var0, Math.min(var2, var4));
   }

   public static final long minOf(long var0, long... var2) {
      Intrinsics.checkNotNullParameter(var2, "other");
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         var0 = Math.min(var0, var2[var3]);
      }

      return var0;
   }

   public static final Comparable minOf(Comparable var0, Comparable var1) {
      Intrinsics.checkNotNullParameter(var0, "a");
      Intrinsics.checkNotNullParameter(var1, "b");
      if (var0.compareTo(var1) > 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final Comparable minOf(Comparable var0, Comparable var1, Comparable var2) {
      Intrinsics.checkNotNullParameter(var0, "a");
      Intrinsics.checkNotNullParameter(var1, "b");
      Intrinsics.checkNotNullParameter(var2, "c");
      return ComparisonsKt.minOf(var0, ComparisonsKt.minOf(var1, var2));
   }

   public static final Comparable minOf(Comparable var0, Comparable... var1) {
      Intrinsics.checkNotNullParameter(var0, "a");
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = ComparisonsKt.minOf(var0, var1[var2]);
      }

      return var0;
   }

   private static final short minOf(short var0, short var1) {
      return (short)Math.min(var0, var1);
   }

   private static final short minOf(short var0, short var1, short var2) {
      return (short)Math.min(var0, Math.min(var1, var2));
   }

   public static final short minOf(short var0, short... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = (short)Math.min(var0, var1[var2]);
      }

      return var0;
   }
}
