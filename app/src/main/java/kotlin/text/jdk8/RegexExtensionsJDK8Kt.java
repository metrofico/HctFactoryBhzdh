package kotlin.text.jdk8;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;

@Metadata(
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0002¨\u0006\u0005"},
   d2 = {"get", "Lkotlin/text/MatchGroup;", "Lkotlin/text/MatchGroupCollection;", "name", "", "kotlin-stdlib-jdk8"},
   k = 2,
   mv = {1, 7, 1},
   pn = "kotlin.text",
   xi = 48
)
public final class RegexExtensionsJDK8Kt {
   public static final MatchGroup get(MatchGroupCollection var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "name");
      MatchNamedGroupCollection var2;
      if (var0 instanceof MatchNamedGroupCollection) {
         var2 = (MatchNamedGroupCollection)var0;
      } else {
         var2 = null;
      }

      if (var2 != null) {
         return var2.get(var1);
      } else {
         throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
      }
   }
}
