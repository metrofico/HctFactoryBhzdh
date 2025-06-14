package androidx.core.text;

import java.nio.CharBuffer;
import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
   public static final TextDirectionHeuristicCompat ANYRTL_LTR;
   public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
   public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
   public static final TextDirectionHeuristicCompat LOCALE;
   public static final TextDirectionHeuristicCompat LTR = new TextDirectionHeuristicInternal((TextDirectionAlgorithm)null, false);
   public static final TextDirectionHeuristicCompat RTL = new TextDirectionHeuristicInternal((TextDirectionAlgorithm)null, true);
   private static final int STATE_FALSE = 1;
   private static final int STATE_TRUE = 0;
   private static final int STATE_UNKNOWN = 2;

   static {
      FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.FirstStrong.INSTANCE, false);
      FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.FirstStrong.INSTANCE, true);
      ANYRTL_LTR = new TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.AnyStrong.INSTANCE_RTL, false);
      LOCALE = TextDirectionHeuristicsCompat.TextDirectionHeuristicLocale.INSTANCE;
   }

   private TextDirectionHeuristicsCompat() {
   }

   static int isRtlText(int var0) {
      if (var0 != 0) {
         return var0 != 1 && var0 != 2 ? 2 : 0;
      } else {
         return 1;
      }
   }

   static int isRtlTextOrFormat(int var0) {
      if (var0 != 0) {
         if (var0 == 1 || var0 == 2) {
            return 0;
         }

         switch (var0) {
            case 14:
            case 15:
               break;
            case 16:
            case 17:
               return 0;
            default:
               return 2;
         }
      }

      return 1;
   }

   private static class AnyStrong implements TextDirectionAlgorithm {
      static final AnyStrong INSTANCE_RTL = new AnyStrong(true);
      private final boolean mLookForRtl;

      private AnyStrong(boolean var1) {
         this.mLookForRtl = var1;
      }

      public int checkRtl(CharSequence var1, int var2, int var3) {
         boolean var5 = false;

         for(int var4 = var2; var4 < var3 + var2; ++var4) {
            int var6 = TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(var1.charAt(var4)));
            if (var6 != 0) {
               if (var6 != 1) {
                  continue;
               }

               if (!this.mLookForRtl) {
                  return 1;
               }
            } else if (this.mLookForRtl) {
               return 0;
            }

            var5 = true;
         }

         if (var5) {
            return this.mLookForRtl;
         } else {
            return 2;
         }
      }
   }

   private static class FirstStrong implements TextDirectionAlgorithm {
      static final FirstStrong INSTANCE = new FirstStrong();

      public int checkRtl(CharSequence var1, int var2, int var3) {
         int var5 = 2;

         for(int var4 = var2; var4 < var3 + var2 && var5 == 2; ++var4) {
            var5 = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(var1.charAt(var4)));
         }

         return var5;
      }
   }

   private interface TextDirectionAlgorithm {
      int checkRtl(CharSequence var1, int var2, int var3);
   }

   private abstract static class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
      private final TextDirectionAlgorithm mAlgorithm;

      TextDirectionHeuristicImpl(TextDirectionAlgorithm var1) {
         this.mAlgorithm = var1;
      }

      private boolean doCheck(CharSequence var1, int var2, int var3) {
         var2 = this.mAlgorithm.checkRtl(var1, var2, var3);
         if (var2 != 0) {
            return var2 != 1 ? this.defaultIsRtl() : false;
         } else {
            return true;
         }
      }

      protected abstract boolean defaultIsRtl();

      public boolean isRtl(CharSequence var1, int var2, int var3) {
         if (var1 != null && var2 >= 0 && var3 >= 0 && var1.length() - var3 >= var2) {
            return this.mAlgorithm == null ? this.defaultIsRtl() : this.doCheck(var1, var2, var3);
         } else {
            throw new IllegalArgumentException();
         }
      }

      public boolean isRtl(char[] var1, int var2, int var3) {
         return this.isRtl((CharSequence)CharBuffer.wrap(var1), var2, var3);
      }
   }

   private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
      private final boolean mDefaultIsRtl;

      TextDirectionHeuristicInternal(TextDirectionAlgorithm var1, boolean var2) {
         super(var1);
         this.mDefaultIsRtl = var2;
      }

      protected boolean defaultIsRtl() {
         return this.mDefaultIsRtl;
      }
   }

   private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
      static final TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicLocale();

      TextDirectionHeuristicLocale() {
         super((TextDirectionAlgorithm)null);
      }

      protected boolean defaultIsRtl() {
         int var1 = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
         boolean var2 = true;
         if (var1 != 1) {
            var2 = false;
         }

         return var2;
      }
   }
}
