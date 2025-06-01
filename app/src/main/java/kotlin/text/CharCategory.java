package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0002\b \b\u0086\u0001\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001-B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,¨\u0006."},
   d2 = {"Lkotlin/text/CharCategory;", "", "value", "", "code", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getValue", "()I", "contains", "", "char", "", "UNASSIGNED", "UPPERCASE_LETTER", "LOWERCASE_LETTER", "TITLECASE_LETTER", "MODIFIER_LETTER", "OTHER_LETTER", "NON_SPACING_MARK", "ENCLOSING_MARK", "COMBINING_SPACING_MARK", "DECIMAL_DIGIT_NUMBER", "LETTER_NUMBER", "OTHER_NUMBER", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "CONTROL", "FORMAT", "PRIVATE_USE", "SURROGATE", "DASH_PUNCTUATION", "START_PUNCTUATION", "END_PUNCTUATION", "CONNECTOR_PUNCTUATION", "OTHER_PUNCTUATION", "MATH_SYMBOL", "CURRENCY_SYMBOL", "MODIFIER_SYMBOL", "OTHER_SYMBOL", "INITIAL_QUOTE_PUNCTUATION", "FINAL_QUOTE_PUNCTUATION", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public enum CharCategory {
   private static final CharCategory[] $VALUES = $values();
   COMBINING_SPACING_MARK(8, "Mc"),
   CONNECTOR_PUNCTUATION(23, "Pc"),
   CONTROL(15, "Cc"),
   CURRENCY_SYMBOL(26, "Sc");

   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   DASH_PUNCTUATION(20, "Pd"),
   DECIMAL_DIGIT_NUMBER(9, "Nd"),
   ENCLOSING_MARK(7, "Me"),
   END_PUNCTUATION(22, "Pe"),
   FINAL_QUOTE_PUNCTUATION(30, "Pf"),
   FORMAT(16, "Cf"),
   INITIAL_QUOTE_PUNCTUATION(29, "Pi"),
   LETTER_NUMBER(10, "Nl"),
   LINE_SEPARATOR(13, "Zl"),
   LOWERCASE_LETTER(2, "Ll"),
   MATH_SYMBOL(25, "Sm"),
   MODIFIER_LETTER(4, "Lm"),
   MODIFIER_SYMBOL(27, "Sk"),
   NON_SPACING_MARK(6, "Mn"),
   OTHER_LETTER(5, "Lo"),
   OTHER_NUMBER(11, "No"),
   OTHER_PUNCTUATION(24, "Po"),
   OTHER_SYMBOL(28, "So"),
   PARAGRAPH_SEPARATOR(14, "Zp"),
   PRIVATE_USE(18, "Co"),
   SPACE_SEPARATOR(12, "Zs"),
   START_PUNCTUATION(21, "Ps"),
   SURROGATE(19, "Cs"),
   TITLECASE_LETTER(3, "Lt"),
   UNASSIGNED(0, "Cn"),
   UPPERCASE_LETTER(1, "Lu");

   private final String code;
   private final int value;

   // $FF: synthetic method
   private static final CharCategory[] $values() {
      return new CharCategory[]{UNASSIGNED, UPPERCASE_LETTER, LOWERCASE_LETTER, TITLECASE_LETTER, MODIFIER_LETTER, OTHER_LETTER, NON_SPACING_MARK, ENCLOSING_MARK, COMBINING_SPACING_MARK, DECIMAL_DIGIT_NUMBER, LETTER_NUMBER, OTHER_NUMBER, SPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATOR, CONTROL, FORMAT, PRIVATE_USE, SURROGATE, DASH_PUNCTUATION, START_PUNCTUATION, END_PUNCTUATION, CONNECTOR_PUNCTUATION, OTHER_PUNCTUATION, MATH_SYMBOL, CURRENCY_SYMBOL, MODIFIER_SYMBOL, OTHER_SYMBOL, INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION};
   }

   private CharCategory(int var3, String var4) {
      this.value = var3;
      this.code = var4;
   }

   public final boolean contains(char var1) {
      boolean var2;
      if (Character.getType(var1) == this.value) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public final String getCode() {
      return this.code;
   }

   public final int getValue() {
      return this.value;
   }

   @Metadata(
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/text/CharCategory$Companion;", "", "()V", "valueOf", "Lkotlin/text/CharCategory;", "category", "", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final CharCategory valueOf(int var1) {
         CharCategory var2;
         if ((new IntRange(0, 16)).contains(var1)) {
            var2 = CharCategory.values()[var1];
         } else {
            if (!(new IntRange(18, 30)).contains(var1)) {
               throw new IllegalArgumentException("Category #" + var1 + " is not defined.");
            }

            var2 = CharCategory.values()[var1 - 1];
         }

         return var2;
      }
   }
}
