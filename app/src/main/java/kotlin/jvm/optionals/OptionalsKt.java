package kotlin.jvm.optionals;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(
   d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004H\u0007\u001a4\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\n\b\u0001\u0010\u0002*\u0004\b\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0007\u001a\u0002H\u0006H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a>\u0010\t\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\n\b\u0001\u0010\u0002*\u0004\b\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a#\u0010\f\u001a\u0004\u0018\u0001H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007¢\u0006\u0002\u0010\r\u001a;\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0010\b\u0001\u0010\u000f*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0011\u001a\u0002H\u000fH\u0007¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0014\"\b\b\u0000\u0010\u0002*\u00020\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004H\u0007\u001a$\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0016\"\b\b\u0000\u0010\u0002*\u00020\u0003*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004H\u0007\u0082\u0002\u000b\n\u0002\b9\n\u0005\b\u009920\u0001¨\u0006\u0017"},
   d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "T", "", "Ljava/util/Optional;", "getOrDefault", "R", "defaultValue", "(Ljava/util/Optional;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Ljava/util/Optional;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrNull", "(Ljava/util/Optional;)Ljava/lang/Object;", "toCollection", "C", "", "destination", "(Ljava/util/Optional;Ljava/util/Collection;)Ljava/util/Collection;", "toList", "", "toSet", "", "kotlin-stdlib-jdk8"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class OptionalsKt {
   public static final Sequence asSequence(Optional var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Sequence var1;
      if (var0.isPresent()) {
         var1 = SequencesKt.sequenceOf(new Object[]{var0.get()});
      } else {
         var1 = SequencesKt.emptySequence();
      }

      return var1;
   }

   public static final Object getOrDefault(Optional var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0.isPresent()) {
         var1 = var0.get();
      }

      return var1;
   }

   public static final Object getOrElse(Optional var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      Object var2;
      if (var0.isPresent()) {
         var2 = var0.get();
      } else {
         var2 = var1.invoke();
      }

      return var2;
   }

   public static final Object getOrNull(Optional var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.orElse((Object)null);
   }

   public static final Collection toCollection(Optional var0, Collection var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      if (var0.isPresent()) {
         Object var2 = var0.get();
         Intrinsics.checkNotNullExpressionValue(var2, "get()");
         var1.add(var2);
      }

      return var1;
   }

   public static final List toList(Optional var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      List var1;
      if (var0.isPresent()) {
         var1 = CollectionsKt.listOf(var0.get());
      } else {
         var1 = CollectionsKt.emptyList();
      }

      return var1;
   }

   public static final Set toSet(Optional var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Set var1;
      if (var0.isPresent()) {
         var1 = SetsKt.setOf(var0.get());
      } else {
         var1 = SetsKt.emptySet();
      }

      return var1;
   }
}
