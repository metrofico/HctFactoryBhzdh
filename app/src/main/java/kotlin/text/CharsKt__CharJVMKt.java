package kotlin.text;

import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0001\u001a\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0000\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0011\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0018\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001a\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001b\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\n\u0010\u001c\u001a\u00020\u000f*\u00020\u0002\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\r\u0010!\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0014\u0010\"\u001a\u00020\u001e*\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\r\u0010#\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010$\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010%\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010&\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010'\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0014\u0010'\u001a\u00020\u001e*\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0007\u001a\r\u0010(\u001a\u00020\u0002*\u00020\u0002H\u0087\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006)"},
   d2 = {"category", "Lkotlin/text/CharCategory;", "", "getCategory", "(C)Lkotlin/text/CharCategory;", "directionality", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "checkRadix", "", "radix", "digitOf", "char", "isDefined", "", "isDigit", "isHighSurrogate", "isISOControl", "isIdentifierIgnorable", "isJavaIdentifierPart", "isJavaIdentifierStart", "isLetter", "isLetterOrDigit", "isLowSurrogate", "isLowerCase", "isTitleCase", "isUpperCase", "isWhitespace", "lowercase", "", "locale", "Ljava/util/Locale;", "lowercaseChar", "titlecase", "titlecaseChar", "toLowerCase", "toTitleCase", "toUpperCase", "uppercase", "uppercaseChar", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/CharsKt"
)
class CharsKt__CharJVMKt {
   public CharsKt__CharJVMKt() {
   }

   public static final int checkRadix(int var0) {
      if ((new IntRange(2, 36)).contains(var0)) {
         return var0;
      } else {
         throw new IllegalArgumentException("radix " + var0 + " was not in valid range " + new IntRange(2, 36));
      }
   }

   public static final int digitOf(char var0, int var1) {
      return Character.digit(var0, var1);
   }

   public static final CharCategory getCategory(char var0) {
      return CharCategory.Companion.valueOf(Character.getType(var0));
   }

   public static final CharDirectionality getDirectionality(char var0) {
      return CharDirectionality.Companion.valueOf(Character.getDirectionality(var0));
   }

   private static final boolean isDefined(char var0) {
      return Character.isDefined(var0);
   }

   private static final boolean isDigit(char var0) {
      return Character.isDigit(var0);
   }

   private static final boolean isHighSurrogate(char var0) {
      return Character.isHighSurrogate(var0);
   }

   private static final boolean isISOControl(char var0) {
      return Character.isISOControl(var0);
   }

   private static final boolean isIdentifierIgnorable(char var0) {
      return Character.isIdentifierIgnorable(var0);
   }

   private static final boolean isJavaIdentifierPart(char var0) {
      return Character.isJavaIdentifierPart(var0);
   }

   private static final boolean isJavaIdentifierStart(char var0) {
      return Character.isJavaIdentifierStart(var0);
   }

   private static final boolean isLetter(char var0) {
      return Character.isLetter(var0);
   }

   private static final boolean isLetterOrDigit(char var0) {
      return Character.isLetterOrDigit(var0);
   }

   private static final boolean isLowSurrogate(char var0) {
      return Character.isLowSurrogate(var0);
   }

   private static final boolean isLowerCase(char var0) {
      return Character.isLowerCase(var0);
   }

   private static final boolean isTitleCase(char var0) {
      return Character.isTitleCase(var0);
   }

   private static final boolean isUpperCase(char var0) {
      return Character.isUpperCase(var0);
   }

   public static final boolean isWhitespace(char var0) {
      boolean var1;
      if (!Character.isWhitespace(var0) && !Character.isSpaceChar(var0)) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private static final String lowercase(char var0) {
      String var1 = String.valueOf(var0);
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.String");
      var1 = var1.toLowerCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).toLowerCase(Locale.ROOT)");
      return var1;
   }

   public static final String lowercase(char var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var1, "locale");
      String var2 = String.valueOf(var0);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type java.lang.String");
      String var3 = var2.toLowerCase(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).toLowerCase(locale)");
      return var3;
   }

   private static final char lowercaseChar(char var0) {
      return Character.toLowerCase(var0);
   }

   public static final String titlecase(char var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var1, "locale");
      String var3 = CharsKt.uppercase(var0, var1);
      if (var3.length() > 1) {
         if (var0 != 329) {
            var0 = var3.charAt(0);
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.String");
            var3 = var3.substring(1);
            Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).substring(startIndex)");
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.String");
            var3 = var3.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            var3 = var0 + var3;
         }

         return var3;
      } else {
         String var2 = String.valueOf(var0);
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type java.lang.String");
         var2 = var2.toUpperCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
         return !Intrinsics.areEqual((Object)var3, (Object)var2) ? var3 : String.valueOf(Character.toTitleCase(var0));
      }
   }

   private static final char titlecaseChar(char var0) {
      return Character.toTitleCase(var0);
   }

   @Deprecated(
      message = "Use lowercaseChar() instead.",
      replaceWith = @ReplaceWith(
   expression = "lowercaseChar()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final char toLowerCase(char var0) {
      return Character.toLowerCase(var0);
   }

   @Deprecated(
      message = "Use titlecaseChar() instead.",
      replaceWith = @ReplaceWith(
   expression = "titlecaseChar()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final char toTitleCase(char var0) {
      return Character.toTitleCase(var0);
   }

   @Deprecated(
      message = "Use uppercaseChar() instead.",
      replaceWith = @ReplaceWith(
   expression = "uppercaseChar()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final char toUpperCase(char var0) {
      return Character.toUpperCase(var0);
   }

   private static final String uppercase(char var0) {
      String var1 = String.valueOf(var0);
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.String");
      var1 = var1.toUpperCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).toUpperCase(Locale.ROOT)");
      return var1;
   }

   public static final String uppercase(char var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var1, "locale");
      String var2 = String.valueOf(var0);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type java.lang.String");
      String var3 = var2.toUpperCase(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).toUpperCase(locale)");
      return var3;
   }

   private static final char uppercaseChar(char var0) {
      return Character.toUpperCase(var0);
   }
}
