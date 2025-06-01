package kotlin.collections;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0010\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0014\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010\u0016\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010\u0018\u001a*\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001H\u0001ø\u0001\u0000¢\u0006\u0004\b!\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""},
   d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "fromIndex", "toIndex", "sortArray-4UcCI2c", "sortArray-oBK06Vg", "sortArray--nroSd4", "sortArray-Aa5vz7o", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class UArraySortingKt {
   private static final int partition__nroSd4(long[] var0, int var1, int var2) {
      long var5 = ULongArray.get_s_VKNKU(var0, (var1 + var2) / 2);

      while(var1 <= var2) {
         int var3 = var1;

         while(true) {
            int var4 = var2;
            if (UnsignedKt.ulongCompare(ULongArray.get_s_VKNKU(var0, var3), var5) >= 0) {
               while(UnsignedKt.ulongCompare(ULongArray.get_s_VKNKU(var0, var4), var5) > 0) {
                  --var4;
               }

               var1 = var3;
               var2 = var4;
               if (var3 <= var4) {
                  long var7 = ULongArray.get_s_VKNKU(var0, var3);
                  ULongArray.set_k8EXiF4(var0, var3, ULongArray.get_s_VKNKU(var0, var4));
                  ULongArray.set_k8EXiF4(var0, var4, var7);
                  var1 = var3 + 1;
                  var2 = var4 - 1;
               }
               break;
            }

            ++var3;
         }
      }

      return var1;
   }

   private static final int partition_4UcCI2c(byte[] var0, int var1, int var2) {
      byte var6 = UByteArray.get_w2LRezQ(var0, (var1 + var2) / 2);

      while(var1 <= var2) {
         int var5 = var1;

         while(true) {
            byte var7 = UByteArray.get_w2LRezQ(var0, var5);
            var1 = var6 & 255;
            int var4 = var2;
            if (Intrinsics.compare(var7 & 255, var1) >= 0) {
               while(Intrinsics.compare(UByteArray.get_w2LRezQ(var0, var4) & 255, var1) > 0) {
                  --var4;
               }

               var1 = var5;
               var2 = var4;
               if (var5 <= var4) {
                  byte var3 = UByteArray.get_w2LRezQ(var0, var5);
                  UByteArray.set_VurrAj0(var0, var5, UByteArray.get_w2LRezQ(var0, var4));
                  UByteArray.set_VurrAj0(var0, var4, var3);
                  var1 = var5 + 1;
                  var2 = var4 - 1;
               }
               break;
            }

            ++var5;
         }
      }

      return var1;
   }

   private static final int partition_Aa5vz7o(short[] var0, int var1, int var2) {
      short var6 = UShortArray.get_Mh2AYeg(var0, (var1 + var2) / 2);

      while(var1 <= var2) {
         int var4 = var1;

         while(true) {
            short var7 = UShortArray.get_Mh2AYeg(var0, var4);
            var1 = var6 & '\uffff';
            int var5 = var2;
            if (Intrinsics.compare(var7 & '\uffff', var1) >= 0) {
               while(Intrinsics.compare(UShortArray.get_Mh2AYeg(var0, var5) & '\uffff', var1) > 0) {
                  --var5;
               }

               var1 = var4;
               var2 = var5;
               if (var4 <= var5) {
                  short var3 = UShortArray.get_Mh2AYeg(var0, var4);
                  UShortArray.set_01HTLdE(var0, var4, UShortArray.get_Mh2AYeg(var0, var5));
                  UShortArray.set_01HTLdE(var0, var5, var3);
                  var1 = var4 + 1;
                  var2 = var5 - 1;
               }
               break;
            }

            ++var4;
         }
      }

      return var1;
   }

   private static final int partition_oBK06Vg(int[] var0, int var1, int var2) {
      int var5 = UIntArray.get_pVg5ArA(var0, (var1 + var2) / 2);

      while(var1 <= var2) {
         int var4 = var1;

         while(true) {
            int var3 = var2;
            if (UnsignedKt.uintCompare(UIntArray.get_pVg5ArA(var0, var4), var5) >= 0) {
               while(UnsignedKt.uintCompare(UIntArray.get_pVg5ArA(var0, var3), var5) > 0) {
                  --var3;
               }

               var1 = var4;
               var2 = var3;
               if (var4 <= var3) {
                  var1 = UIntArray.get_pVg5ArA(var0, var4);
                  UIntArray.set_VXSXFK8(var0, var4, UIntArray.get_pVg5ArA(var0, var3));
                  UIntArray.set_VXSXFK8(var0, var3, var1);
                  var1 = var4 + 1;
                  var2 = var3 - 1;
               }
               break;
            }

            ++var4;
         }
      }

      return var1;
   }

   private static final void quickSort__nroSd4(long[] var0, int var1, int var2) {
      int var3 = partition__nroSd4(var0, var1, var2);
      int var4 = var3 - 1;
      if (var1 < var4) {
         quickSort__nroSd4(var0, var1, var4);
      }

      if (var3 < var2) {
         quickSort__nroSd4(var0, var3, var2);
      }

   }

   private static final void quickSort_4UcCI2c(byte[] var0, int var1, int var2) {
      int var3 = partition_4UcCI2c(var0, var1, var2);
      int var4 = var3 - 1;
      if (var1 < var4) {
         quickSort_4UcCI2c(var0, var1, var4);
      }

      if (var3 < var2) {
         quickSort_4UcCI2c(var0, var3, var2);
      }

   }

   private static final void quickSort_Aa5vz7o(short[] var0, int var1, int var2) {
      int var3 = partition_Aa5vz7o(var0, var1, var2);
      int var4 = var3 - 1;
      if (var1 < var4) {
         quickSort_Aa5vz7o(var0, var1, var4);
      }

      if (var3 < var2) {
         quickSort_Aa5vz7o(var0, var3, var2);
      }

   }

   private static final void quickSort_oBK06Vg(int[] var0, int var1, int var2) {
      int var3 = partition_oBK06Vg(var0, var1, var2);
      int var4 = var3 - 1;
      if (var1 < var4) {
         quickSort_oBK06Vg(var0, var1, var4);
      }

      if (var3 < var2) {
         quickSort_oBK06Vg(var0, var3, var2);
      }

   }

   public static final void sortArray__nroSd4(long[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "array");
      quickSort__nroSd4(var0, var1, var2 - 1);
   }

   public static final void sortArray_4UcCI2c(byte[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "array");
      quickSort_4UcCI2c(var0, var1, var2 - 1);
   }

   public static final void sortArray_Aa5vz7o(short[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "array");
      quickSort_Aa5vz7o(var0, var1, var2 - 1);
   }

   public static final void sortArray_oBK06Vg(int[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "array");
      quickSort_oBK06Vg(var0, var1, var2 - 1);
   }
}
