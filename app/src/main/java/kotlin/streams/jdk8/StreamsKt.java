package kotlin.streams.jdk8;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(
   d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0005H\u0007\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\tH\u0007\u001a\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\t\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0001H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f*\u00020\u0003H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f*\u00020\u0005H\u0007\u001a\u0012\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f*\u00020\u0007H\u0007\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\tH\u0007¨\u0006\r"},
   d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "", "Ljava/util/stream/DoubleStream;", "", "Ljava/util/stream/IntStream;", "", "Ljava/util/stream/LongStream;", "T", "Ljava/util/stream/Stream;", "asStream", "toList", "", "kotlin-stdlib-jdk8"},
   k = 2,
   mv = {1, 7, 1},
   pn = "kotlin.streams",
   xi = 48
)
public final class StreamsKt {
   // $FF: synthetic method
   public static Spliterator $r8$lambda$bdU4_xB_0bnfvMo_xyX7v8aTfMQ(Sequence var0) {
      return asStream$lambda_4(var0);
   }

   public static final Sequence asSequence(DoubleStream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Sequence)(new Sequence(var0) {
         final DoubleStream $this_asSequence$inlined;

         public {
            this.$this_asSequence$inlined = var1;
         }

         public Iterator iterator() {
            PrimitiveIterator.OfDouble var1 = this.$this_asSequence$inlined.iterator();
            Intrinsics.checkNotNullExpressionValue(var1, "iterator()");
            return (Iterator)var1;
         }
      });
   }

   public static final Sequence asSequence(IntStream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Sequence)(new Sequence(var0) {
         final IntStream $this_asSequence$inlined;

         public {
            this.$this_asSequence$inlined = var1;
         }

         public Iterator iterator() {
            PrimitiveIterator.OfInt var1 = this.$this_asSequence$inlined.iterator();
            Intrinsics.checkNotNullExpressionValue(var1, "iterator()");
            return (Iterator)var1;
         }
      });
   }

   public static final Sequence asSequence(LongStream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Sequence)(new Sequence(var0) {
         final LongStream $this_asSequence$inlined;

         public {
            this.$this_asSequence$inlined = var1;
         }

         public Iterator iterator() {
            PrimitiveIterator.OfLong var1 = this.$this_asSequence$inlined.iterator();
            Intrinsics.checkNotNullExpressionValue(var1, "iterator()");
            return (Iterator)var1;
         }
      });
   }

   public static final Sequence asSequence(Stream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Sequence)(new Sequence(var0) {
         final Stream $this_asSequence$inlined;

         public {
            this.$this_asSequence$inlined = var1;
         }

         public Iterator iterator() {
            Iterator var1 = this.$this_asSequence$inlined.iterator();
            Intrinsics.checkNotNullExpressionValue(var1, "iterator()");
            return var1;
         }
      });
   }

   public static final Stream asStream(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Stream var1 = StreamSupport.stream(new StreamsKt$$ExternalSyntheticLambda0(var0), 16, false);
      Intrinsics.checkNotNullExpressionValue(var1, "stream({ Spliterators.sp…literator.ORDERED, false)");
      return var1;
   }

   private static final Spliterator asStream$lambda_4(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "$this_asStream");
      return Spliterators.spliteratorUnknownSize(var0.iterator(), 16);
   }

   public static final List toList(DoubleStream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      double[] var1 = var0.toArray();
      Intrinsics.checkNotNullExpressionValue(var1, "toArray()");
      return ArraysKt.asList(var1);
   }

   public static final List toList(IntStream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int[] var1 = var0.toArray();
      Intrinsics.checkNotNullExpressionValue(var1, "toArray()");
      return ArraysKt.asList(var1);
   }

   public static final List toList(LongStream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      long[] var1 = var0.toArray();
      Intrinsics.checkNotNullExpressionValue(var1, "toArray()");
      return ArraysKt.asList(var1);
   }

   public static final List toList(Stream var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object var1 = var0.collect(Collectors.toList());
      Intrinsics.checkNotNullExpressionValue(var1, "collect(Collectors.toList<T>())");
      return (List)var1;
   }
}
