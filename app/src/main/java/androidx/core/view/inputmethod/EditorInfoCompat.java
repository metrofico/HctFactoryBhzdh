package androidx.core.view.inputmethod;

import android.os.Bundle;
import android.os.Build.VERSION;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.core.util.Preconditions;

public final class EditorInfoCompat {
   private static final String CONTENT_MIME_TYPES_INTEROP_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
   private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
   private static final String CONTENT_SELECTION_END_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END";
   private static final String CONTENT_SELECTION_HEAD_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD";
   private static final String CONTENT_SURROUNDING_TEXT_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT";
   private static final String[] EMPTY_STRING_ARRAY = new String[0];
   public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
   public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;
   static final int MAX_INITIAL_SELECTION_LENGTH = 1024;
   static final int MEMORY_EFFICIENT_TEXT_LENGTH = 2048;

   public static String[] getContentMimeTypes(EditorInfo var0) {
      if (VERSION.SDK_INT >= 25) {
         String[] var3 = var0.contentMimeTypes;
         if (var3 == null) {
            var3 = EMPTY_STRING_ARRAY;
         }

         return var3;
      } else if (var0.extras == null) {
         return EMPTY_STRING_ARRAY;
      } else {
         String[] var2 = var0.extras.getStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
         String[] var1 = var2;
         if (var2 == null) {
            var1 = var0.extras.getStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
         }

         if (var1 == null) {
            var1 = EMPTY_STRING_ARRAY;
         }

         return var1;
      }
   }

   public static CharSequence getInitialSelectedText(EditorInfo var0, int var1) {
      if (VERSION.SDK_INT >= 30) {
         return var0.getInitialSelectedText(var1);
      } else if (var0.extras == null) {
         return null;
      } else {
         int var3 = Math.min(var0.initialSelStart, var0.initialSelEnd);
         int var5 = Math.max(var0.initialSelStart, var0.initialSelEnd);
         int var2 = var0.extras.getInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD");
         int var4 = var0.extras.getInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END");
         if (var0.initialSelStart >= 0 && var0.initialSelEnd >= 0 && var4 - var2 == var5 - var3) {
            CharSequence var6 = var0.extras.getCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT");
            if (var6 == null) {
               return null;
            } else {
               Object var7;
               if ((var1 & 1) != 0) {
                  var7 = var6.subSequence(var2, var4);
               } else {
                  var7 = TextUtils.substring(var6, var2, var4);
               }

               return (CharSequence)var7;
            }
         } else {
            return null;
         }
      }
   }

   public static CharSequence getInitialTextAfterCursor(EditorInfo var0, int var1, int var2) {
      if (VERSION.SDK_INT >= 30) {
         return var0.getInitialTextAfterCursor(var1, var2);
      } else if (var0.extras == null) {
         return null;
      } else {
         CharSequence var4 = var0.extras.getCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT");
         if (var4 == null) {
            return null;
         } else {
            int var3 = var0.extras.getInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END");
            var1 = Math.min(var1, var4.length() - var3);
            Object var5;
            if ((var2 & 1) != 0) {
               var5 = var4.subSequence(var3, var1 + var3);
            } else {
               var5 = TextUtils.substring(var4, var3, var1 + var3);
            }

            return (CharSequence)var5;
         }
      }
   }

   public static CharSequence getInitialTextBeforeCursor(EditorInfo var0, int var1, int var2) {
      if (VERSION.SDK_INT >= 30) {
         return var0.getInitialTextBeforeCursor(var1, var2);
      } else if (var0.extras == null) {
         return null;
      } else {
         CharSequence var4 = var0.extras.getCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT");
         if (var4 == null) {
            return null;
         } else {
            int var3 = var0.extras.getInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD");
            var1 = Math.min(var1, var3);
            Object var5;
            if ((var2 & 1) != 0) {
               var5 = var4.subSequence(var3 - var1, var3);
            } else {
               var5 = TextUtils.substring(var4, var3 - var1, var3);
            }

            return (CharSequence)var5;
         }
      }
   }

   static int getProtocol(EditorInfo var0) {
      if (VERSION.SDK_INT >= 25) {
         return 1;
      } else if (var0.extras == null) {
         return 0;
      } else {
         boolean var2 = var0.extras.containsKey("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
         boolean var1 = var0.extras.containsKey("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
         if (var2 && var1) {
            return 4;
         } else if (var2) {
            return 3;
         } else {
            return var1 ? 2 : 0;
         }
      }
   }

   private static boolean isCutOnSurrogate(CharSequence var0, int var1, int var2) {
      if (var2 != 0) {
         return var2 != 1 ? false : Character.isHighSurrogate(var0.charAt(var1));
      } else {
         return Character.isLowSurrogate(var0.charAt(var1));
      }
   }

   private static boolean isPasswordInputType(int var0) {
      var0 &= 4095;
      boolean var1;
      if (var0 != 129 && var0 != 225 && var0 != 18) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public static void setContentMimeTypes(EditorInfo var0, String[] var1) {
      if (VERSION.SDK_INT >= 25) {
         var0.contentMimeTypes = var1;
      } else {
         if (var0.extras == null) {
            var0.extras = new Bundle();
         }

         var0.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", var1);
         var0.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", var1);
      }

   }

   public static void setInitialSurroundingSubText(EditorInfo var0, CharSequence var1, int var2) {
      Preconditions.checkNotNull(var1);
      if (VERSION.SDK_INT >= 30) {
         var0.setInitialSurroundingSubText(var1, var2);
      } else {
         int var3;
         if (var0.initialSelStart > var0.initialSelEnd) {
            var3 = var0.initialSelEnd;
         } else {
            var3 = var0.initialSelStart;
         }

         int var4 = var3 - var2;
         if (var0.initialSelStart > var0.initialSelEnd) {
            var3 = var0.initialSelStart;
         } else {
            var3 = var0.initialSelEnd;
         }

         int var5 = var3 - var2;
         var3 = var1.length();
         if (var2 >= 0 && var4 >= 0 && var5 <= var3) {
            if (isPasswordInputType(var0.inputType)) {
               setSurroundingText(var0, (CharSequence)null, 0, 0);
            } else if (var3 <= 2048) {
               setSurroundingText(var0, var1, var4, var5);
            } else {
               trimLongSurroundingText(var0, var1, var4, var5);
            }
         } else {
            setSurroundingText(var0, (CharSequence)null, 0, 0);
         }
      }
   }

   public static void setInitialSurroundingText(EditorInfo var0, CharSequence var1) {
      if (VERSION.SDK_INT >= 30) {
         var0.setInitialSurroundingSubText(var1, 0);
      } else {
         setInitialSurroundingSubText(var0, var1, 0);
      }

   }

   private static void setSurroundingText(EditorInfo var0, CharSequence var1, int var2, int var3) {
      if (var0.extras == null) {
         var0.extras = new Bundle();
      }

      SpannableStringBuilder var4;
      if (var1 != null) {
         var4 = new SpannableStringBuilder(var1);
      } else {
         var4 = null;
      }

      var0.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", var4);
      var0.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", var2);
      var0.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", var3);
   }

   private static void trimLongSurroundingText(EditorInfo var0, CharSequence var1, int var2, int var3) {
      int var7 = var3 - var2;
      int var4;
      if (var7 > 1024) {
         var4 = 0;
      } else {
         var4 = var7;
      }

      int var6 = var1.length();
      int var5 = 2048 - var4;
      int var8 = Math.min(var6 - var3, var5 - Math.min(var2, (int)((double)var5 * 0.8)));
      var6 = Math.min(var2, var5 - var8);
      int var9 = var2 - var6;
      var5 = var6;
      var2 = var9;
      if (isCutOnSurrogate(var1, var9, 0)) {
         var2 = var9 + 1;
         var5 = var6 - 1;
      }

      var6 = var8;
      if (isCutOnSurrogate(var1, var3 + var8 - 1, 1)) {
         var6 = var8 - 1;
      }

      if (var4 != var7) {
         var1 = TextUtils.concat(new CharSequence[]{var1.subSequence(var2, var2 + var5), var1.subSequence(var3, var6 + var3)});
      } else {
         var1 = var1.subSequence(var2, var5 + var4 + var6 + var2);
      }

      var2 = var5 + 0;
      setSurroundingText(var0, var1, var2, var4 + var2);
   }

   private static class Api30Impl {
      static CharSequence getInitialSelectedText(EditorInfo var0, int var1) {
         return var0.getInitialSelectedText(var1);
      }

      static CharSequence getInitialTextAfterCursor(EditorInfo var0, int var1, int var2) {
         return var0.getInitialTextAfterCursor(var1, var2);
      }

      static CharSequence getInitialTextBeforeCursor(EditorInfo var0, int var1, int var2) {
         return var0.getInitialTextBeforeCursor(var1, var2);
      }

      static void setInitialSurroundingSubText(EditorInfo var0, CharSequence var1, int var2) {
         var0.setInitialSurroundingSubText(var1, var2);
      }
   }
}
