package kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(
   d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 02\u00060\u0001j\u0002`\u0002:\u000201B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bB\u000f\b\u0001\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u001bH\u0007J\u0010\u0010 \u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u0011\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086\u0004J\u0018\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u001bH\u0007J\"\u0010#\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170%J\u0016\u0010#\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004J\u0016\u0010'\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u0004J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040)2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010*\u001a\u00020\u001bJ \u0010+\u001a\b\u0012\u0004\u0012\u00020\u00040\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010*\u001a\u00020\u001bH\u0007J\u0006\u0010,\u001a\u00020\rJ\b\u0010-\u001a\u00020\u0004H\u0016J\b\u0010.\u001a\u00020/H\u0002R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u00062"},
   d2 = {"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", "", "(Ljava/lang/String;Ljava/util/Set;)V", "nativePattern", "Ljava/util/regex/Pattern;", "(Ljava/util/regex/Pattern;)V", "_options", "getOptions", "()Ljava/util/Set;", "getPattern", "()Ljava/lang/String;", "containsMatchIn", "", "input", "", "find", "Lkotlin/text/MatchResult;", "startIndex", "", "findAll", "Lkotlin/sequences/Sequence;", "matchAt", "index", "matchEntire", "matches", "matchesAt", "replace", "transform", "Lkotlin/Function1;", "replacement", "replaceFirst", "split", "", "limit", "splitToSequence", "toPattern", "toString", "writeReplace", "", "Companion", "Serialized", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class Regex implements Serializable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private Set _options;
   private final Pattern nativePattern;

   public Regex(String var1) {
      Intrinsics.checkNotNullParameter(var1, "pattern");
      Pattern var2 = Pattern.compile(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "compile(pattern)");
      this(var2);
   }

   public Regex(String var1, Set var2) {
      Intrinsics.checkNotNullParameter(var1, "pattern");
      Intrinsics.checkNotNullParameter(var2, "options");
      Pattern var3 = Pattern.compile(var1, Companion.ensureUnicodeCase(RegexKt.access$toInt((Iterable)var2)));
      Intrinsics.checkNotNullExpressionValue(var3, "compile(pattern, ensureU…odeCase(options.toInt()))");
      this(var3);
   }

   public Regex(String var1, RegexOption var2) {
      Intrinsics.checkNotNullParameter(var1, "pattern");
      Intrinsics.checkNotNullParameter(var2, "option");
      Pattern var3 = Pattern.compile(var1, Companion.ensureUnicodeCase(var2.getValue()));
      Intrinsics.checkNotNullExpressionValue(var3, "compile(pattern, ensureUnicodeCase(option.value))");
      this(var3);
   }

   public Regex(Pattern var1) {
      Intrinsics.checkNotNullParameter(var1, "nativePattern");
      super();
      this.nativePattern = var1;
   }

   // $FF: synthetic method
   public static MatchResult find$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.find(var1, var2);
   }

   // $FF: synthetic method
   public static Sequence findAll$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.findAll(var1, var2);
   }

   // $FF: synthetic method
   public static List split$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.split(var1, var2);
   }

   // $FF: synthetic method
   public static Sequence splitToSequence$default(Regex var0, CharSequence var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      return var0.splitToSequence(var1, var2);
   }

   private final Object writeReplace() {
      String var1 = this.nativePattern.pattern();
      Intrinsics.checkNotNullExpressionValue(var1, "nativePattern.pattern()");
      return new Serialized(var1, this.nativePattern.flags());
   }

   public final boolean containsMatchIn(CharSequence var1) {
      Intrinsics.checkNotNullParameter(var1, "input");
      return this.nativePattern.matcher(var1).find();
   }

   public final MatchResult find(CharSequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Matcher var3 = this.nativePattern.matcher(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "nativePattern.matcher(input)");
      return RegexKt.access$findNext(var3, var2, var1);
   }

   public final Sequence findAll(CharSequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      if (var2 >= 0 && var2 <= var1.length()) {
         return SequencesKt.generateSequence((Function0)(new Function0(this, var1, var2) {
            final CharSequence $input;
            final int $startIndex;
            final Regex this$0;

            {
               this.this$0 = var1;
               this.$input = var2;
               this.$startIndex = var3;
            }

            public final MatchResult invoke() {
               return this.this$0.find(this.$input, this.$startIndex);
            }
         }), (Function1)null.INSTANCE);
      } else {
         throw new IndexOutOfBoundsException("Start index out of bounds: " + var2 + ", input length: " + var1.length());
      }
   }

   public final Set getOptions() {
      Set var3 = this._options;
      Set var2 = var3;
      if (var3 == null) {
         int var1 = this.nativePattern.flags();
         EnumSet var4 = EnumSet.allOf(RegexOption.class);
         Intrinsics.checkNotNullExpressionValue(var4, "");
         CollectionsKt.retainAll((Iterable)var4, (Function1)(new Function1(var1) {
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
         var2 = Collections.unmodifiableSet((Set)var4);
         Intrinsics.checkNotNullExpressionValue(var2, "unmodifiableSet(EnumSet.…mask == it.value }\n    })");
         this._options = var2;
      }

      return var2;
   }

   public final String getPattern() {
      String var1 = this.nativePattern.pattern();
      Intrinsics.checkNotNullExpressionValue(var1, "nativePattern.pattern()");
      return var1;
   }

   public final MatchResult matchAt(CharSequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Matcher var3 = this.nativePattern.matcher(var1).useAnchoringBounds(false).useTransparentBounds(true).region(var2, var1.length());
      MatcherMatchResult var4;
      if (var3.lookingAt()) {
         Intrinsics.checkNotNullExpressionValue(var3, "this");
         var4 = new MatcherMatchResult(var3, var1);
      } else {
         var4 = null;
      }

      return (MatchResult)var4;
   }

   public final MatchResult matchEntire(CharSequence var1) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Matcher var2 = this.nativePattern.matcher(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "nativePattern.matcher(input)");
      return RegexKt.access$matchEntire(var2, var1);
   }

   public final boolean matches(CharSequence var1) {
      Intrinsics.checkNotNullParameter(var1, "input");
      return this.nativePattern.matcher(var1).matches();
   }

   public final boolean matchesAt(CharSequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      return this.nativePattern.matcher(var1).useAnchoringBounds(false).useTransparentBounds(true).region(var2, var1.length()).lookingAt();
   }

   public final String replace(CharSequence var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      String var3 = this.nativePattern.matcher(var1).replaceAll(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "nativePattern.matcher(in…).replaceAll(replacement)");
      return var3;
   }

   public final String replace(CharSequence var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Intrinsics.checkNotNullParameter(var2, "transform");
      int var3 = 0;
      MatchResult var6 = find$default(this, var1, 0, 2, (Object)null);
      if (var6 == null) {
         return var1.toString();
      } else {
         int var5 = var1.length();
         StringBuilder var8 = new StringBuilder(var5);

         int var4;
         MatchResult var7;
         do {
            var8.append(var1, var3, var6.getRange().getStart());
            var8.append((CharSequence)var2.invoke(var6));
            var4 = var6.getRange().getEndInclusive() + 1;
            var7 = var6.next();
            if (var4 >= var5) {
               break;
            }

            var3 = var4;
            var6 = var7;
         } while(var7 != null);

         if (var4 < var5) {
            var8.append(var1, var4, var5);
         }

         String var9 = var8.toString();
         Intrinsics.checkNotNullExpressionValue(var9, "sb.toString()");
         return var9;
      }
   }

   public final String replaceFirst(CharSequence var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Intrinsics.checkNotNullParameter(var2, "replacement");
      String var3 = this.nativePattern.matcher(var1).replaceFirst(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "nativePattern.matcher(in…replaceFirst(replacement)");
      return var3;
   }

   public final List split(CharSequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      StringsKt.requireNonNegativeLimit(var2);
      Matcher var5 = this.nativePattern.matcher(var1);
      if (var2 != 1 && var5.find()) {
         int var3 = 10;
         if (var2 > 0) {
            var3 = RangesKt.coerceAtMost(var2, 10);
         }

         ArrayList var6 = new ArrayList(var3);
         byte var7 = 0;
         int var4 = var2 - 1;
         var2 = var7;

         do {
            var6.add(var1.subSequence(var2, var5.start()).toString());
            var3 = var5.end();
            if (var4 >= 0 && var6.size() == var4) {
               break;
            }

            var2 = var3;
         } while(var5.find());

         var6.add(var1.subSequence(var3, var1.length()).toString());
         return (List)var6;
      } else {
         return CollectionsKt.listOf(var1.toString());
      }
   }

   public final Sequence splitToSequence(CharSequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "input");
      StringsKt.requireNonNegativeLimit(var2);
      return SequencesKt.sequence((Function2)(new Function2(this, var1, var2, (Continuation)null) {
         final CharSequence $input;
         final int $limit;
         int I$0;
         private Object L$0;
         Object L$1;
         int label;
         final Regex this$0;

         {
            this.this$0 = var1;
            this.$input = var2;
            this.$limit = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.this$0, this.$input, this.$limit, var2);
            var3.L$0 = var1;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var10 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            byte var3 = 0;
            <undefinedtype> var5;
            Matcher var6;
            SequenceScope var8;
            String var11;
            Continuation var12;
            SequenceScope var13;
            if (var2 != 0) {
               if (var2 == 1) {
                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }

               if (var2 != 2) {
                  if (var2 != 3) {
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  ResultKt.throwOnFailure(var1);
                  return Unit.INSTANCE;
               }

               var2 = this.I$0;
               var6 = (Matcher)this.L$1;
               SequenceScope var7 = (SequenceScope)this.L$0;
               ResultKt.throwOnFailure(var1);
               var5 = this;
               var13 = var7;
            } else {
               ResultKt.throwOnFailure(var1);
               var8 = (SequenceScope)this.L$0;
               Matcher var9 = this.this$0.nativePattern.matcher(this.$input);
               if (this.$limit == 1 || !var9.find()) {
                  String var15 = this.$input.toString();
                  Continuation var14 = (Continuation)this;
                  this.label = 1;
                  if (var8.yield(var15, var14) == var10) {
                     return var10;
                  }

                  return Unit.INSTANCE;
               }

               var2 = 0;
               var11 = this.$input.subSequence(var3, var9.start()).toString();
               var12 = (Continuation)this;
               this.L$0 = var8;
               this.L$1 = var9;
               this.I$0 = var2;
               this.label = 2;
               var6 = var9;
               var13 = var8;
               var5 = this;
               if (var8.yield(var11, var12) == var10) {
                  return var10;
               }
            }

            do {
               int var4 = var6.end();
               ++var2;
               if (var2 != var5.$limit - 1) {
                  var8 = var13;
                  if (var6.find()) {
                     var11 = var5.$input.subSequence(var4, var6.start()).toString();
                     var12 = (Continuation)var5;
                     var5.L$0 = var13;
                     var5.L$1 = var6;
                     var5.I$0 = var2;
                     var5.label = 2;
                     var6 = var6;
                     var13 = var13;
                     var5 = var5;
                     continue;
                  }
               }

               CharSequence var16 = var5.$input;
               String var17 = var16.subSequence(var4, var16.length()).toString();
               Continuation var18 = (Continuation)var5;
               var5.L$0 = null;
               var5.L$1 = null;
               var5.label = 3;
               if (var13.yield(var17, var18) == var10) {
                  return var10;
               }

               return Unit.INSTANCE;
            } while(var8.yield(var11, var12) != var10);

            return var10;
         }
      }));
   }

   public final Pattern toPattern() {
      return this.nativePattern;
   }

   public String toString() {
      String var1 = this.nativePattern.toString();
      Intrinsics.checkNotNullExpressionValue(var1, "nativePattern.toString()");
      return var1;
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007¨\u0006\f"},
      d2 = {"Lkotlin/text/Regex$Companion;", "", "()V", "ensureUnicodeCase", "", "flags", "escape", "", "literal", "escapeReplacement", "fromLiteral", "Lkotlin/text/Regex;", "kotlin-stdlib"},
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

      private final int ensureUnicodeCase(int var1) {
         int var2 = var1;
         if ((var1 & 2) != 0) {
            var2 = var1 | 64;
         }

         return var2;
      }

      public final String escape(String var1) {
         Intrinsics.checkNotNullParameter(var1, "literal");
         var1 = Pattern.quote(var1);
         Intrinsics.checkNotNullExpressionValue(var1, "quote(literal)");
         return var1;
      }

      public final String escapeReplacement(String var1) {
         Intrinsics.checkNotNullParameter(var1, "literal");
         var1 = Matcher.quoteReplacement(var1);
         Intrinsics.checkNotNullExpressionValue(var1, "quoteReplacement(literal)");
         return var1;
      }

      public final Regex fromLiteral(String var1) {
         Intrinsics.checkNotNullParameter(var1, "literal");
         return new Regex(var1, RegexOption.LITERAL);
      }
   }

   @Metadata(
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"},
      d2 = {"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "pattern", "", "flags", "", "(Ljava/lang/String;I)V", "getFlags", "()I", "getPattern", "()Ljava/lang/String;", "readResolve", "", "Companion", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Serialized implements Serializable {
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      private static final long serialVersionUID = 0L;
      private final int flags;
      private final String pattern;

      public Serialized(String var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "pattern");
         super();
         this.pattern = var1;
         this.flags = var2;
      }

      private final Object readResolve() {
         Pattern var1 = Pattern.compile(this.pattern, this.flags);
         Intrinsics.checkNotNullExpressionValue(var1, "compile(pattern, flags)");
         return new Regex(var1);
      }

      public final int getFlags() {
         return this.flags;
      }

      public final String getPattern() {
         return this.pattern;
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
         d2 = {"Lkotlin/text/Regex$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"},
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
      }
   }
}
