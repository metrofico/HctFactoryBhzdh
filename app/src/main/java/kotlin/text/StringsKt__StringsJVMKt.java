package kotlin.text;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\u0019\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a)\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a\f\u0010\u0017\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0014\u0010\u0017\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\u001d\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010 \u001a\u00020\u0011*\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\f\u0010$\u001a\u00020\u0002*\u00020\u0014H\u0007\u001a \u0010$\u001a\u00020\u0002*\u00020\u00142\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\u0019\u0010&\u001a\u00020#*\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'H\u0087\u0004\u001a \u0010&\u001a\u00020#*\u0004\u0018\u00010'2\b\u0010!\u001a\u0004\u0018\u00010'2\u0006\u0010\"\u001a\u00020#H\u0007\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0087\b\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010(\u001a\u00020'H\u0087\b\u001a\f\u0010)\u001a\u00020\u0002*\u00020\u0002H\u0007\u001a\u0014\u0010)\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\f\u0010*\u001a\u00020\u0002*\u00020\rH\u0007\u001a*\u0010*\u001a\u00020\u0002*\u00020\r2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\f\u0010,\u001a\u00020\r*\u00020\u0002H\u0007\u001a*\u0010,\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\u001c\u0010-\u001a\u00020#*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a \u0010/\u001a\u00020#*\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a2\u00100\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00104\u001a6\u00100\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0004\b5\u00104\u001a*\u00100\u001a\u00020\u0002*\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00106\u001a:\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00107\u001a>\u00100\u001a\u00020\u0002*\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0004\b5\u00107\u001a2\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00108\u001a\r\u00109\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\n\u0010:\u001a\u00020#*\u00020'\u001a\r\u0010;\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010;\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u001d\u0010<\u001a\u00020\u0011*\u00020\u00022\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010<\u001a\u00020\u0011*\u00020\u00022\u0006\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010A\u001a\u00020\u0011*\u00020\u00022\u0006\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010B\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010C\u001a\u00020\u0011H\u0087\b\u001a4\u0010D\u001a\u00020#*\u00020'2\u0006\u0010E\u001a\u00020\u00112\u0006\u0010!\u001a\u00020'2\u0006\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a4\u0010D\u001a\u00020#*\u00020\u00022\u0006\u0010E\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0012\u0010G\u001a\u00020\u0002*\u00020'2\u0006\u0010H\u001a\u00020\u0011\u001a$\u0010I\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010I\u001a\u00020\u0002*\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020>2\u0006\u0010K\u001a\u00020>2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\"\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00020P*\u00020'2\u0006\u0010Q\u001a\u00020R2\b\b\u0002\u0010S\u001a\u00020\u0011\u001a\u001c\u0010T\u001a\u00020#*\u00020\u00022\u0006\u0010U\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010T\u001a\u00020#*\u00020\u00022\u0006\u0010U\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0015\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u0017\u0010W\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\r\u0010X\u001a\u00020\u0014*\u00020\u0002H\u0087\b\u001a3\u0010X\u001a\u00020\u0014*\u00020\u00022\u0006\u0010Y\u001a\u00020\u00142\b\b\u0002\u0010Z\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a \u0010X\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\r\u0010[\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010[\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u0017\u0010\\\u001a\u00020R*\u00020\u00022\b\b\u0002\u0010]\u001a\u00020\u0011H\u0087\b\u001a\r\u0010^\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010^\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\r\u0010_\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010_\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\"%\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006`"},
   d2 = {"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", "charset", "Ljava/nio/charset/Charset;", "offset", "", "length", "chars", "", "codePoints", "", "capitalize", "locale", "Ljava/util/Locale;", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "concatToString", "startIndex", "contentEquals", "", "charSequence", "decapitalize", "decodeToString", "throwOnInvalidSequence", "encodeToByteArray", "endsWith", "suffix", "equals", "format", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "formatNullable", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "lowercase", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", "regex", "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "uppercase", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
   public StringsKt__StringsJVMKt() {
   }

   private static final String String(StringBuffer var0) {
      Intrinsics.checkNotNullParameter(var0, "stringBuffer");
      return new String(var0);
   }

   private static final String String(StringBuilder var0) {
      Intrinsics.checkNotNullParameter(var0, "stringBuilder");
      return new String(var0);
   }

   private static final String String(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "bytes");
      return new String(var0, Charsets.UTF_8);
   }

   private static final String String(byte[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "bytes");
      return new String(var0, var1, var2, Charsets.UTF_8);
   }

   private static final String String(byte[] var0, int var1, int var2, Charset var3) {
      Intrinsics.checkNotNullParameter(var0, "bytes");
      Intrinsics.checkNotNullParameter(var3, "charset");
      return new String(var0, var1, var2, var3);
   }

   private static final String String(byte[] var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "bytes");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new String(var0, var1);
   }

   private static final String String(char[] var0) {
      Intrinsics.checkNotNullParameter(var0, "chars");
      return new String(var0);
   }

   private static final String String(char[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "chars");
      return new String(var0, var1, var2);
   }

   private static final String String(int[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "codePoints");
      return new String(var0, var1, var2);
   }

   @Deprecated(
      message = "Use replaceFirstChar instead.",
      replaceWith = @ReplaceWith(
   expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }",
   imports = {"java.util.Locale"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static final String capitalize(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Locale var1 = Locale.getDefault();
      Intrinsics.checkNotNullExpressionValue(var1, "getDefault()");
      return StringsKt.capitalize(var0, var1);
   }

   @Deprecated(
      message = "Use replaceFirstChar instead.",
      replaceWith = @ReplaceWith(
   expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static final String capitalize(String var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      boolean var4;
      if (((CharSequence)var0).length() > 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      String var5 = var0;
      if (var4) {
         char var3 = var0.charAt(0);
         var5 = var0;
         if (Character.isLowerCase(var3)) {
            StringBuilder var8 = new StringBuilder();
            char var2 = Character.toTitleCase(var3);
            if (var2 != Character.toUpperCase(var3)) {
               var8.append(var2);
            } else {
               String var6 = var0.substring(0, 1);
               Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String…ing(startIndex, endIndex)");
               Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type java.lang.String");
               String var7 = var6.toUpperCase(var1);
               Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String).toUpperCase(locale)");
               var8.append(var7);
            }

            var0 = var0.substring(1);
            Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).substring(startIndex)");
            var8.append(var0);
            var5 = var8.toString();
            Intrinsics.checkNotNullExpressionValue(var5, "StringBuilder().apply(builderAction).toString()");
         }
      }

      return var5;
   }

   private static final int codePointAt(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.codePointAt(var1);
   }

   private static final int codePointBefore(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.codePointBefore(var1);
   }

   private static final int codePointCount(String var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.codePointCount(var1, var2);
   }

   public static final int compareTo(String var0, String var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      return var2 ? var0.compareToIgnoreCase(var1) : var0.compareTo(var1);
   }

   // $FF: synthetic method
   public static int compareTo$default(String var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.compareTo(var0, var1, var2);
   }

   public static final String concatToString(char[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new String(var0);
   }

   public static final String concatToString(char[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(var1, var2, var0.length);
      return new String(var0, var1, var2 - var1);
   }

   // $FF: synthetic method
   public static String concatToString$default(char[] var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 0;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.length;
      }

      return StringsKt.concatToString(var0, var1, var2);
   }

   public static final boolean contentEquals(CharSequence var0, CharSequence var1) {
      boolean var2;
      if (var0 instanceof String && var1 != null) {
         var2 = ((String)var0).contentEquals(var1);
      } else {
         var2 = StringsKt.contentEqualsImpl(var0, var1);
      }

      return var2;
   }

   public static final boolean contentEquals(CharSequence var0, CharSequence var1, boolean var2) {
      if (var2) {
         var2 = StringsKt.contentEqualsIgnoreCaseImpl(var0, var1);
      } else {
         var2 = StringsKt.contentEquals(var0, var1);
      }

      return var2;
   }

   private static final boolean contentEquals(String var0, CharSequence var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charSequence");
      return var0.contentEquals(var1);
   }

   private static final boolean contentEquals(String var0, StringBuffer var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "stringBuilder");
      return var0.contentEquals(var1);
   }

   @Deprecated(
      message = "Use replaceFirstChar instead.",
      replaceWith = @ReplaceWith(
   expression = "replaceFirstChar { it.lowercase(Locale.getDefault()) }",
   imports = {"java.util.Locale"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static final String decapitalize(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      boolean var1;
      if (((CharSequence)var0).length() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      String var2 = var0;
      if (var1) {
         var2 = var0;
         if (!Character.isLowerCase(var0.charAt(0))) {
            StringBuilder var4 = new StringBuilder();
            String var3 = var0.substring(0, 1);
            Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.String");
            var3 = var3.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).toLowerCase()");
            var4 = var4.append(var3);
            var0 = var0.substring(1);
            Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).substring(startIndex)");
            var2 = var4.append(var0).toString();
         }
      }

      return var2;
   }

   @Deprecated(
      message = "Use replaceFirstChar instead.",
      replaceWith = @ReplaceWith(
   expression = "replaceFirstChar { it.lowercase(locale) }",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   public static final String decapitalize(String var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      boolean var2;
      if (((CharSequence)var0).length() > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      String var3 = var0;
      if (var2) {
         var3 = var0;
         if (!Character.isLowerCase(var0.charAt(0))) {
            StringBuilder var7 = new StringBuilder();
            String var4 = var0.substring(0, 1);
            Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String…ing(startIndex, endIndex)");
            Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type java.lang.String");
            String var5 = var4.toLowerCase(var1);
            Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).toLowerCase(locale)");
            StringBuilder var6 = var7.append(var5);
            var0 = var0.substring(1);
            Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).substring(startIndex)");
            var3 = var6.append(var0).toString();
         }
      }

      return var3;
   }

   public static final String decodeToString(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new String(var0, Charsets.UTF_8);
   }

   public static final String decodeToString(byte[] var0, int var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(var1, var2, var0.length);
      if (!var3) {
         return new String(var0, var1, var2 - var1, Charsets.UTF_8);
      } else {
         String var4 = Charsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap(var0, var1, var2 - var1)).toString();
         Intrinsics.checkNotNullExpressionValue(var4, "decoder.decode(ByteBuffe…- startIndex)).toString()");
         return var4;
      }
   }

   // $FF: synthetic method
   public static String decodeToString$default(byte[] var0, int var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.decodeToString(var0, var1, var2, var3);
   }

   public static final byte[] encodeToByteArray(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      byte[] var1 = var0.getBytes(Charsets.UTF_8);
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).getBytes(charset)");
      return var1;
   }

   public static final byte[] encodeToByteArray(String var0, int var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(var1, var2, var0.length());
      byte[] var5;
      if (!var3) {
         String var7 = var0.substring(var1, var2);
         Intrinsics.checkNotNullExpressionValue(var7, "this as java.lang.String…ing(startIndex, endIndex)");
         Charset var6 = Charsets.UTF_8;
         Intrinsics.checkNotNull(var7, "null cannot be cast to non-null type java.lang.String");
         var5 = var7.getBytes(var6);
         Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).getBytes(charset)");
         return var5;
      } else {
         ByteBuffer var4 = Charsets.UTF_8.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).encode(CharBuffer.wrap((CharSequence)var0, var1, var2));
         if (var4.hasArray() && var4.arrayOffset() == 0) {
            var1 = var4.remaining();
            var5 = var4.array();
            Intrinsics.checkNotNull(var5);
            if (var1 == var5.length) {
               var5 = var4.array();
               Intrinsics.checkNotNullExpressionValue(var5, "{\n        byteBuffer.array()\n    }");
               return var5;
            }
         }

         var5 = new byte[var4.remaining()];
         var4.get(var5);
         return var5;
      }
   }

   // $FF: synthetic method
   public static byte[] encodeToByteArray$default(String var0, int var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = 0;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.length();
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.encodeToByteArray(var0, var1, var2, var3);
   }

   public static final boolean endsWith(String var0, String var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "suffix");
      return !var2 ? var0.endsWith(var1) : StringsKt.regionMatches(var0, var0.length() - var1.length(), var1, 0, var1.length(), true);
   }

   // $FF: synthetic method
   public static boolean endsWith$default(String var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.endsWith(var0, var1, var2);
   }

   public static final boolean equals(String var0, String var1, boolean var2) {
      if (var0 == null) {
         if (var1 == null) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      } else {
         if (!var2) {
            var2 = var0.equals(var1);
         } else {
            var2 = var0.equalsIgnoreCase(var1);
         }

         return var2;
      }
   }

   // $FF: synthetic method
   public static boolean equals$default(String var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.equals(var0, var1, var2);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use Kotlin compiler 1.4 to avoid deprecation warning."
   )
   @DeprecatedSinceKotlin(
      hiddenSince = "1.4"
   )
   private static final String format(String var0, Locale var1, Object... var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      Intrinsics.checkNotNullParameter(var2, "args");
      var0 = String.format(var1, var0, Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var0, "format(locale, this, *args)");
      return var0;
   }

   private static final String format(String var0, Object... var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "args");
      var0 = String.format(var0, Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var0, "format(this, *args)");
      return var0;
   }

   private static final String format(StringCompanionObject var0, String var1, Object... var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "format");
      Intrinsics.checkNotNullParameter(var2, "args");
      String var3 = String.format(var1, Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var3, "format(format, *args)");
      return var3;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use Kotlin compiler 1.4 to avoid deprecation warning."
   )
   @DeprecatedSinceKotlin(
      hiddenSince = "1.4"
   )
   private static final String format(StringCompanionObject var0, Locale var1, String var2, Object... var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      Intrinsics.checkNotNullParameter(var2, "format");
      Intrinsics.checkNotNullParameter(var3, "args");
      String var4 = String.format(var1, var2, Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var4, "format(locale, format, *args)");
      return var4;
   }

   private static final String formatNullable(String var0, Locale var1, Object... var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "args");
      var0 = String.format(var1, var0, Arrays.copyOf(var2, var2.length));
      Intrinsics.checkNotNullExpressionValue(var0, "format(locale, this, *args)");
      return var0;
   }

   private static final String formatNullable(StringCompanionObject var0, Locale var1, String var2, Object... var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "format");
      Intrinsics.checkNotNullParameter(var3, "args");
      String var4 = String.format(var1, var2, Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var4, "format(locale, format, *args)");
      return var4;
   }

   public static final Comparator getCASE_INSENSITIVE_ORDER(StringCompanionObject var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Comparator var1 = String.CASE_INSENSITIVE_ORDER;
      Intrinsics.checkNotNullExpressionValue(var1, "CASE_INSENSITIVE_ORDER");
      return var1;
   }

   private static final String intern(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.intern();
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).intern()");
      return var0;
   }

   public static final boolean isBlank(CharSequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var1 = var0.length();
      boolean var2 = false;
      if (var1 != 0) {
         boolean var4;
         label35: {
            Iterable var3 = (Iterable)StringsKt.getIndices(var0);
            if (!(var3 instanceof Collection) || !((Collection)var3).isEmpty()) {
               Iterator var5 = var3.iterator();

               while(var5.hasNext()) {
                  if (!CharsKt.isWhitespace(var0.charAt(((IntIterator)var5).nextInt()))) {
                     var4 = false;
                     break label35;
                  }
               }
            }

            var4 = true;
         }

         if (!var4) {
            return var2;
         }
      }

      var2 = true;
      return var2;
   }

   private static final String lowercase(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.toLowerCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toLowerCase(Locale.ROOT)");
      return var0;
   }

   private static final String lowercase(String var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      var0 = var0.toLowerCase(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toLowerCase(locale)");
      return var0;
   }

   private static final int nativeIndexOf(String var0, char var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.indexOf(var1, var2);
   }

   private static final int nativeIndexOf(String var0, String var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "str");
      return var0.indexOf(var1, var2);
   }

   private static final int nativeLastIndexOf(String var0, char var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.lastIndexOf(var1, var2);
   }

   private static final int nativeLastIndexOf(String var0, String var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "str");
      return var0.lastIndexOf(var1, var2);
   }

   private static final int offsetByCodePoints(String var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return var0.offsetByCodePoints(var1, var2);
   }

   public static final boolean regionMatches(CharSequence var0, int var1, CharSequence var2, int var3, int var4, boolean var5) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "other");
      return var0 instanceof String && var2 instanceof String ? StringsKt.regionMatches((String)var0, var1, (String)var2, var3, var4, var5) : StringsKt.regionMatchesImpl(var0, var1, var2, var3, var4, var5);
   }

   public static final boolean regionMatches(String var0, int var1, String var2, int var3, int var4, boolean var5) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "other");
      if (!var5) {
         var5 = var0.regionMatches(var1, var2, var3, var4);
      } else {
         var5 = var0.regionMatches(var5, var1, var2, var3, var4);
      }

      return var5;
   }

   // $FF: synthetic method
   public static boolean regionMatches$default(CharSequence var0, int var1, CharSequence var2, int var3, int var4, boolean var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = false;
      }

      return StringsKt.regionMatches(var0, var1, var2, var3, var4, var5);
   }

   // $FF: synthetic method
   public static boolean regionMatches$default(String var0, int var1, String var2, int var3, int var4, boolean var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = false;
      }

      return StringsKt.regionMatches(var0, var1, var2, var3, var4, var5);
   }

   public static final String repeat(CharSequence var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      byte var4 = 0;
      boolean var3;
      if (var1 >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (!var3) {
         throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + var1 + '.').toString());
      } else {
         String var6 = "";
         String var5 = var6;
         if (var1 != 0) {
            if (var1 != 1) {
               int var8 = var0.length();
               var5 = var6;
               if (var8 != 0) {
                  if (var8 != 1) {
                     StringBuilder var10 = new StringBuilder(var0.length() * var1);
                     IntIterator var9 = (new IntRange(1, var1)).iterator();

                     while(var9.hasNext()) {
                        var9.nextInt();
                        var10.append(var0);
                     }

                     var5 = var10.toString();
                     Intrinsics.checkNotNullExpressionValue(var5, "{\n                    va…tring()\n                }");
                  } else {
                     char var2 = var0.charAt(0);
                     char[] var7 = new char[var1];

                     for(var8 = var4; var8 < var1; ++var8) {
                        var7[var8] = var2;
                     }

                     var5 = new String(var7);
                  }
               }
            } else {
               var5 = var0.toString();
            }
         }

         return var5;
      }
   }

   public static final String replace(String var0, char var1, char var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!var3) {
         var0 = var0.replace(var1, var2);
         Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String…replace(oldChar, newChar)");
         return var0;
      } else {
         StringBuilder var7 = new StringBuilder(var0.length());
         CharSequence var8 = (CharSequence)var0;

         for(int var6 = 0; var6 < var8.length(); ++var6) {
            char var5 = var8.charAt(var6);
            char var4 = var5;
            if (CharsKt.equals(var5, var1, var3)) {
               var4 = var2;
            }

            var7.append(var4);
         }

         var0 = var7.toString();
         Intrinsics.checkNotNullExpressionValue(var0, "StringBuilder(capacity).…builderAction).toString()");
         return var0;
      }
   }

   public static final String replace(String var0, String var1, String var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "oldValue");
      Intrinsics.checkNotNullParameter(var2, "newValue");
      CharSequence var10 = (CharSequence)var0;
      int var5 = 0;
      int var4 = StringsKt.indexOf(var10, var1, 0, var3);
      if (var4 < 0) {
         return var0;
      } else {
         int var8 = var1.length();
         int var9 = RangesKt.coerceAtLeast(var8, 1);
         int var6 = var0.length() - var8 + var2.length();
         if (var6 < 0) {
            throw new OutOfMemoryError();
         } else {
            StringBuilder var11 = new StringBuilder(var6);

            int var7;
            do {
               var11.append(var10, var5, var4).append(var2);
               var6 = var4 + var8;
               if (var4 >= var0.length()) {
                  break;
               }

               var7 = StringsKt.indexOf(var10, var1, var4 + var9, var3);
               var5 = var6;
               var4 = var7;
            } while(var7 > 0);

            var0 = var11.append(var10, var6, var0.length()).toString();
            Intrinsics.checkNotNullExpressionValue(var0, "stringBuilder.append(this, i, length).toString()");
            return var0;
         }
      }
   }

   // $FF: synthetic method
   public static String replace$default(String var0, char var1, char var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.replace(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String replace$default(String var0, String var1, String var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.replace(var0, var1, var2, var3);
   }

   public static final String replaceFirst(String var0, char var1, char var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.indexOf$default(var5, var1, 0, var3, 2, (Object)null);
      if (var4 >= 0) {
         var0 = StringsKt.replaceRange(var5, var4, var4 + 1, (CharSequence)String.valueOf(var2)).toString();
      }

      return var0;
   }

   public static final String replaceFirst(String var0, String var1, String var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "oldValue");
      Intrinsics.checkNotNullParameter(var2, "newValue");
      CharSequence var5 = (CharSequence)var0;
      int var4 = StringsKt.indexOf$default(var5, var1, 0, var3, 2, (Object)null);
      if (var4 >= 0) {
         var0 = StringsKt.replaceRange(var5, var4, var1.length() + var4, (CharSequence)var2).toString();
      }

      return var0;
   }

   // $FF: synthetic method
   public static String replaceFirst$default(String var0, char var1, char var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.replaceFirst(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static String replaceFirst$default(String var0, String var1, String var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.replaceFirst(var0, var1, var2, var3);
   }

   public static final List split(CharSequence var0, Pattern var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "regex");
      StringsKt.requireNonNegativeLimit(var2);
      int var3 = var2;
      if (var2 == 0) {
         var3 = -1;
      }

      String[] var4 = var1.split(var0, var3);
      Intrinsics.checkNotNullExpressionValue(var4, "regex.split(this, if (limit == 0) -1 else limit)");
      return ArraysKt.asList((Object[])var4);
   }

   // $FF: synthetic method
   public static List split$default(CharSequence var0, Pattern var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return StringsKt.split(var0, var1, var2);
   }

   public static final boolean startsWith(String var0, String var1, int var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      return !var3 ? var0.startsWith(var1, var2) : StringsKt.regionMatches(var0, var2, var1, 0, var1.length(), var3);
   }

   public static final boolean startsWith(String var0, String var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "prefix");
      return !var2 ? var0.startsWith(var1) : StringsKt.regionMatches(var0, 0, var1, 0, var1.length(), var2);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(String var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.startsWith(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(String var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.startsWith(var0, var1, var2);
   }

   private static final String substring(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.substring(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).substring(startIndex)");
      return var0;
   }

   private static final String substring(String var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.substring(var1, var2);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String…ing(startIndex, endIndex)");
      return var0;
   }

   private static final byte[] toByteArray(String var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      byte[] var2 = var0.getBytes(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).getBytes(charset)");
      return var2;
   }

   // $FF: synthetic method
   static byte[] toByteArray$default(String var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      byte[] var4 = var0.getBytes(var1);
      Intrinsics.checkNotNullExpressionValue(var4, "this as java.lang.String).getBytes(charset)");
      return var4;
   }

   private static final char[] toCharArray(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      char[] var1 = var0.toCharArray();
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).toCharArray()");
      return var1;
   }

   public static final char[] toCharArray(String var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(var1, var2, var0.length());
      char[] var3 = new char[var2 - var1];
      var0.getChars(var1, var2, var3, 0);
      return var3;
   }

   private static final char[] toCharArray(String var0, char[] var1, int var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      var0.getChars(var3, var4, var1, var2);
      return var1;
   }

   // $FF: synthetic method
   public static char[] toCharArray$default(String var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 0;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.length();
      }

      return StringsKt.toCharArray(var0, var1, var2);
   }

   // $FF: synthetic method
   static char[] toCharArray$default(String var0, char[] var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = 0;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.length();
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "destination");
      var0.getChars(var3, var4, var1, var2);
      return var1;
   }

   @Deprecated(
      message = "Use lowercase() instead.",
      replaceWith = @ReplaceWith(
   expression = "lowercase(Locale.getDefault())",
   imports = {"java.util.Locale"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final String toLowerCase(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.toLowerCase();
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toLowerCase()");
      return var0;
   }

   @Deprecated(
      message = "Use lowercase() instead.",
      replaceWith = @ReplaceWith(
   expression = "lowercase(locale)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final String toLowerCase(String var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      var0 = var0.toLowerCase(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toLowerCase(locale)");
      return var0;
   }

   private static final Pattern toPattern(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Pattern var2 = Pattern.compile(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var2, "compile(this, flags)");
      return var2;
   }

   // $FF: synthetic method
   static Pattern toPattern$default(String var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 0;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Pattern var4 = Pattern.compile(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var4, "compile(this, flags)");
      return var4;
   }

   @Deprecated(
      message = "Use uppercase() instead.",
      replaceWith = @ReplaceWith(
   expression = "uppercase(Locale.getDefault())",
   imports = {"java.util.Locale"}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final String toUpperCase(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.toUpperCase();
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toUpperCase()");
      return var0;
   }

   @Deprecated(
      message = "Use uppercase() instead.",
      replaceWith = @ReplaceWith(
   expression = "uppercase(locale)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.5"
   )
   private static final String toUpperCase(String var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      var0 = var0.toUpperCase(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toUpperCase(locale)");
      return var0;
   }

   private static final String uppercase(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = var0.toUpperCase(Locale.ROOT);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toUpperCase(Locale.ROOT)");
      return var0;
   }

   private static final String uppercase(String var0, Locale var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "locale");
      var0 = var0.toUpperCase(var1);
      Intrinsics.checkNotNullExpressionValue(var0, "this as java.lang.String).toUpperCase(locale)");
      return var0;
   }
}
