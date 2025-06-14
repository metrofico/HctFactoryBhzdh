package kotlin.text;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000>\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\b\u001a\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u001a\u0012\u0010\u0012\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002¨\u0006\u0014"},
   d2 = {"fromInt", "", "T", "Lkotlin/text/FlagEnum;", "", "value", "", "findNext", "Lkotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "from", "input", "", "matchEntire", "range", "Lkotlin/ranges/IntRange;", "Ljava/util/regex/MatchResult;", "groupIndex", "toInt", "", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class RegexKt {
   // $FF: synthetic method
   public static final MatchResult access$findNext(Matcher var0, int var1, CharSequence var2) {
      return findNext(var0, var1, var2);
   }

   // $FF: synthetic method
   public static final MatchResult access$matchEntire(Matcher var0, CharSequence var1) {
      return matchEntire(var0, var1);
   }

   // $FF: synthetic method
   public static final IntRange access$range(java.util.regex.MatchResult var0) {
      return range(var0);
   }

   // $FF: synthetic method
   public static final IntRange access$range(java.util.regex.MatchResult var0, int var1) {
      return range(var0, var1);
   }

   // $FF: synthetic method
   public static final int access$toInt(Iterable var0) {
      return toInt(var0);
   }

   private static final MatchResult findNext(Matcher var0, int var1, CharSequence var2) {
      MatchResult var3;
      if (!var0.find(var1)) {
         var3 = null;
      } else {
         var3 = (MatchResult)(new MatcherMatchResult(var0, var2));
      }

      return var3;
   }

   // $FF: synthetic method
   private static final Set fromInt(int var0) {
      Intrinsics.reifiedOperationMarker(4, "T");
      Class var1 = (Class)Enum.class;
      EnumSet var3 = EnumSet.allOf(Enum.class);
      EnumSet var2 = (EnumSet)var3;
      Intrinsics.checkNotNullExpressionValue(var3, "");
      Iterable var5 = (Iterable)var3;
      Intrinsics.needClassReification();
      CollectionsKt.retainAll(var5, (Function1)(new Function1(var0) {
         final int $value;

         public {
            this.$value = var1;
         }

         public final Boolean invoke(Enum var1) {
            int var2 = this.$value;
            FlagEnum var4 = (FlagEnum)var1;
            boolean var3;
            if ((var2 & var4.getMask()) == var4.getValue()) {
               var3 = true;
            } else {
               var3 = false;
            }

            return var3;
         }
      }));
      Set var6 = Collections.unmodifiableSet((Set)var3);
      Intrinsics.checkNotNullExpressionValue(var6, "unmodifiableSet(EnumSet.…mask == it.value }\n    })");
      Set var4 = (Set)var6;
      return var6;
   }

   private static final MatchResult matchEntire(Matcher var0, CharSequence var1) {
      MatchResult var2;
      if (!var0.matches()) {
         var2 = null;
      } else {
         var2 = (MatchResult)(new MatcherMatchResult(var0, var1));
      }

      return var2;
   }

   private static final IntRange range(java.util.regex.MatchResult var0) {
      return RangesKt.until(var0.start(), var0.end());
   }

   private static final IntRange range(java.util.regex.MatchResult var0, int var1) {
      return RangesKt.until(var0.start(var1), var0.end(var1));
   }

   private static final int toInt(Iterable var0) {
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 |= ((FlagEnum)var2.next()).getValue()) {
      }

      return var1;
   }
}
