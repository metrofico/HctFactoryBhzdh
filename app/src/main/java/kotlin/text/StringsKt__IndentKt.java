package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"},
   d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__IndentKt extends StringsKt__AppendableKt {
   public StringsKt__IndentKt() {
   }

   private static final Function1 getIndentFunction$StringsKt__IndentKt(String var0) {
      boolean var1;
      if (((CharSequence)var0).length() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      Function1 var2;
      if (var1) {
         var2 = (Function1)null.INSTANCE;
      } else {
         var2 = (Function1)(new Function1(var0) {
            final String $indent;

            {
               this.$indent = var1;
            }

            public final String invoke(String var1) {
               Intrinsics.checkNotNullParameter(var1, "line");
               return this.$indent + var1;
            }
         });
      }

      return var2;
   }

   private static final int indentWidth$StringsKt__IndentKt(String var0) {
      CharSequence var3 = (CharSequence)var0;
      int var2 = var3.length();
      int var1 = 0;

      while(true) {
         if (var1 >= var2) {
            var1 = -1;
            break;
         }

         if (CharsKt.isWhitespace(var3.charAt(var1)) ^ true) {
            break;
         }

         ++var1;
      }

      var2 = var1;
      if (var1 == -1) {
         var2 = var0.length();
      }

      return var2;
   }

   public static final String prependIndent(String var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "indent");
      return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence((CharSequence)var0), (Function1)(new Function1(var1) {
         final String $indent;

         {
            this.$indent = var1;
         }

         public final String invoke(String var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            String var2;
            if (StringsKt.isBlank((CharSequence)var1)) {
               var2 = var1;
               if (var1.length() < this.$indent.length()) {
                  var2 = this.$indent;
               }
            } else {
               var2 = this.$indent + var1;
            }

            return var2;
         }
      })), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   // $FF: synthetic method
   public static String prependIndent$default(String var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "    ";
      }

      return StringsKt.prependIndent(var0, var1);
   }

   private static final String reindent$StringsKt__IndentKt(List var0, int var1, Function1 var2, Function1 var3) {
      int var5 = CollectionsKt.getLastIndex(var0);
      Iterable var10 = (Iterable)var0;
      Collection var7 = (Collection)(new ArrayList());
      Iterator var8 = var10.iterator();

      String var12;
      for(int var4 = 0; var8.hasNext(); ++var4) {
         Object var11 = var8.next();
         if (var4 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         String var6 = (String)var11;
         if ((var4 == 0 || var4 == var5) && StringsKt.isBlank((CharSequence)var6)) {
            var12 = null;
         } else {
            String var9 = (String)var3.invoke(var6);
            var12 = var6;
            if (var9 != null) {
               var12 = (String)var2.invoke(var9);
               if (var12 == null) {
                  var12 = var6;
               }
            }
         }

         if (var12 != null) {
            var7.add(var12);
         }
      }

      var12 = ((StringBuilder)CollectionsKt.joinTo$default((Iterable)((List)var7), (Appendable)(new StringBuilder(var1)), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null)).toString();
      Intrinsics.checkNotNullExpressionValue(var12, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
      return var12;
   }

   public static final String replaceIndent(String var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "newIndent");
      List var10 = StringsKt.lines((CharSequence)var0);
      Iterable var8 = (Iterable)var10;
      Collection var9 = (Collection)(new ArrayList());
      Iterator var11 = var8.iterator();

      while(var11.hasNext()) {
         Object var12 = var11.next();
         if (StringsKt.isBlank((CharSequence)((String)var12)) ^ true) {
            var9.add(var12);
         }
      }

      Iterable var18 = (Iterable)((List)var9);
      var9 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var18, 10)));
      var11 = var18.iterator();

      while(var11.hasNext()) {
         var9.add(indentWidth$StringsKt__IndentKt((String)var11.next()));
      }

      Integer var15 = (Integer)CollectionsKt.minOrNull((Iterable)((List)var9));
      int var3 = 0;
      int var2;
      if (var15 != null) {
         var2 = var15;
      } else {
         var2 = 0;
      }

      int var4 = var0.length();
      int var6 = var1.length();
      int var7 = var10.size();
      Function1 var16 = getIndentFunction$StringsKt__IndentKt(var1);
      int var5 = CollectionsKt.getLastIndex(var10);
      Collection var17 = (Collection)(new ArrayList());

      for(Iterator var14 = var8.iterator(); var14.hasNext(); ++var3) {
         Object var13 = var14.next();
         if (var3 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         var1 = (String)var13;
         if ((var3 == 0 || var3 == var5) && StringsKt.isBlank((CharSequence)var1)) {
            var0 = null;
         } else {
            String var19 = StringsKt.drop(var1, var2);
            var0 = var1;
            if (var19 != null) {
               var0 = (String)var16.invoke(var19);
               if (var0 == null) {
                  var0 = var1;
               }
            }
         }

         if (var0 != null) {
            var17.add(var0);
         }
      }

      var0 = ((StringBuilder)CollectionsKt.joinTo$default((Iterable)((List)var17), (Appendable)(new StringBuilder(var4 + var6 * var7)), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null)).toString();
      Intrinsics.checkNotNullExpressionValue(var0, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
      return var0;
   }

   // $FF: synthetic method
   public static String replaceIndent$default(String var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "";
      }

      return StringsKt.replaceIndent(var0, var1);
   }

   public static final String replaceIndentByMargin(String var0, String var1, String var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "newIndent");
      Intrinsics.checkNotNullParameter(var2, "marginPrefix");
      if (!(StringsKt.isBlank((CharSequence)var2) ^ true)) {
         throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
      } else {
         List var10 = StringsKt.lines((CharSequence)var0);
         int var8 = var0.length();
         int var5 = var1.length();
         int var7 = var10.size();
         Function1 var11 = getIndentFunction$StringsKt__IndentKt(var1);
         int var6 = CollectionsKt.getLastIndex(var10);
         Iterable var14 = (Iterable)var10;
         Collection var12 = (Collection)(new ArrayList());
         Iterator var13 = var14.iterator();

         for(int var4 = 0; var13.hasNext(); ++var4) {
            Object var15 = var13.next();
            if (var4 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            String var17 = (String)var15;
            var1 = null;
            if ((var4 == 0 || var4 == var6) && StringsKt.isBlank((CharSequence)var17)) {
               var0 = null;
            } else {
               CharSequence var16 = (CharSequence)var17;
               int var9 = var16.length();
               int var3 = 0;

               while(true) {
                  if (var3 >= var9) {
                     var3 = -1;
                     break;
                  }

                  if (CharsKt.isWhitespace(var16.charAt(var3)) ^ true) {
                     break;
                  }

                  ++var3;
               }

               if (var3 != -1 && StringsKt.startsWith$default(var17, var2, var3, false, 4, (Object)null)) {
                  var9 = var2.length();
                  Intrinsics.checkNotNull(var17, "null cannot be cast to non-null type java.lang.String");
                  var1 = var17.substring(var3 + var9);
                  Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).substring(startIndex)");
               }

               var0 = var17;
               if (var1 != null) {
                  var0 = (String)var11.invoke(var1);
                  if (var0 == null) {
                     var0 = var17;
                  }
               }
            }

            if (var0 != null) {
               var12.add(var0);
            }
         }

         var0 = ((StringBuilder)CollectionsKt.joinTo$default((Iterable)((List)var12), (Appendable)(new StringBuilder(var8 + var5 * var7)), (CharSequence)"\n", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null)).toString();
         Intrinsics.checkNotNullExpressionValue(var0, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
         return var0;
      }
   }

   // $FF: synthetic method
   public static String replaceIndentByMargin$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = "";
      }

      if ((var3 & 2) != 0) {
         var2 = "|";
      }

      return StringsKt.replaceIndentByMargin(var0, var1, var2);
   }

   public static final String trimIndent(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.replaceIndent(var0, "");
   }

   public static final String trimMargin(String var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "marginPrefix");
      return StringsKt.replaceIndentByMargin(var0, "", var1);
   }

   // $FF: synthetic method
   public static String trimMargin$default(String var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "|";
      }

      return StringsKt.trimMargin(var0, var1);
   }
}
