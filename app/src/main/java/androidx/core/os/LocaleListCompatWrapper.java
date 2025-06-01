package androidx.core.os;

import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

final class LocaleListCompatWrapper implements LocaleListInterface {
   private static final Locale EN_LATN = LocaleListCompat.forLanguageTagCompat("en-Latn");
   private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
   private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
   private static final Locale[] sEmptyList = new Locale[0];
   private final Locale[] mList;
   private final String mStringRepresentation;

   LocaleListCompatWrapper(Locale... var1) {
      if (var1.length == 0) {
         this.mList = sEmptyList;
         this.mStringRepresentation = "";
      } else {
         ArrayList var3 = new ArrayList();
         HashSet var4 = new HashSet();
         StringBuilder var5 = new StringBuilder();

         for(int var2 = 0; var2 < var1.length; ++var2) {
            Locale var6 = var1[var2];
            if (var6 == null) {
               throw new NullPointerException("list[" + var2 + "] is null");
            }

            if (!var4.contains(var6)) {
               var6 = (Locale)var6.clone();
               var3.add(var6);
               toLanguageTag(var5, var6);
               if (var2 < var1.length - 1) {
                  var5.append(',');
               }

               var4.add(var6);
            }
         }

         this.mList = (Locale[])var3.toArray(new Locale[var3.size()]);
         this.mStringRepresentation = var5.toString();
      }

   }

   private Locale computeFirstMatch(Collection var1, boolean var2) {
      int var3 = this.computeFirstMatchIndex(var1, var2);
      Locale var4;
      if (var3 == -1) {
         var4 = null;
      } else {
         var4 = this.mList[var3];
      }

      return var4;
   }

   private int computeFirstMatchIndex(Collection var1, boolean var2) {
      Locale[] var5 = this.mList;
      if (var5.length == 1) {
         return 0;
      } else if (var5.length == 0) {
         return -1;
      } else {
         int var3;
         label38: {
            if (var2) {
               var3 = this.findFirstMatchIndex(EN_LATN);
               if (var3 == 0) {
                  return 0;
               }

               if (var3 < Integer.MAX_VALUE) {
                  break label38;
               }
            }

            var3 = Integer.MAX_VALUE;
         }

         Iterator var6 = var1.iterator();

         while(var6.hasNext()) {
            int var4 = this.findFirstMatchIndex(LocaleListCompat.forLanguageTagCompat((String)var6.next()));
            if (var4 == 0) {
               return 0;
            }

            if (var4 < var3) {
               var3 = var4;
            }
         }

         if (var3 == Integer.MAX_VALUE) {
            return 0;
         } else {
            return var3;
         }
      }
   }

   private int findFirstMatchIndex(Locale var1) {
      int var2 = 0;

      while(true) {
         Locale[] var3 = this.mList;
         if (var2 >= var3.length) {
            return Integer.MAX_VALUE;
         }

         if (matchScore(var1, var3[var2]) > 0) {
            return var2;
         }

         ++var2;
      }
   }

   private static String getLikelyScript(Locale var0) {
      if (VERSION.SDK_INT >= 21) {
         String var1 = var0.getScript();
         if (!var1.isEmpty()) {
            return var1;
         }
      }

      return "";
   }

   private static boolean isPseudoLocale(Locale var0) {
      boolean var1;
      if (!LOCALE_EN_XA.equals(var0) && !LOCALE_AR_XB.equals(var0)) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private static int matchScore(Locale var0, Locale var1) {
      boolean var4 = var0.equals(var1);
      byte var3 = 1;
      if (var4) {
         return 1;
      } else if (!var0.getLanguage().equals(var1.getLanguage())) {
         return 0;
      } else if (!isPseudoLocale(var0) && !isPseudoLocale(var1)) {
         String var5 = getLikelyScript(var0);
         if (var5.isEmpty()) {
            String var6 = var0.getCountry();
            byte var2 = var3;
            if (!var6.isEmpty()) {
               if (var6.equals(var1.getCountry())) {
                  var2 = var3;
               } else {
                  var2 = 0;
               }
            }

            return var2;
         } else {
            return var5.equals(getLikelyScript(var1));
         }
      } else {
         return 0;
      }
   }

   static void toLanguageTag(StringBuilder var0, Locale var1) {
      var0.append(var1.getLanguage());
      String var2 = var1.getCountry();
      if (var2 != null && !var2.isEmpty()) {
         var0.append('-');
         var0.append(var1.getCountry());
      }

   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof LocaleListCompatWrapper)) {
         return false;
      } else {
         Locale[] var3 = ((LocaleListCompatWrapper)var1).mList;
         if (this.mList.length != var3.length) {
            return false;
         } else {
            int var2 = 0;

            while(true) {
               Locale[] var4 = this.mList;
               if (var2 >= var4.length) {
                  return true;
               }

               if (!var4[var2].equals(var3[var2])) {
                  return false;
               }

               ++var2;
            }
         }
      }
   }

   public Locale get(int var1) {
      Locale var3;
      if (var1 >= 0) {
         Locale[] var2 = this.mList;
         if (var1 < var2.length) {
            var3 = var2[var1];
            return var3;
         }
      }

      var3 = null;
      return var3;
   }

   public Locale getFirstMatch(String[] var1) {
      return this.computeFirstMatch(Arrays.asList(var1), false);
   }

   public Object getLocaleList() {
      return null;
   }

   public int hashCode() {
      int var2 = 1;
      int var1 = 0;

      while(true) {
         Locale[] var3 = this.mList;
         if (var1 >= var3.length) {
            return var2;
         }

         var2 = var2 * 31 + var3[var1].hashCode();
         ++var1;
      }
   }

   public int indexOf(Locale var1) {
      int var2 = 0;

      while(true) {
         Locale[] var3 = this.mList;
         if (var2 >= var3.length) {
            return -1;
         }

         if (var3[var2].equals(var1)) {
            return var2;
         }

         ++var2;
      }
   }

   public boolean isEmpty() {
      boolean var1;
      if (this.mList.length == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public int size() {
      return this.mList.length;
   }

   public String toLanguageTags() {
      return this.mStringRepresentation;
   }

   public String toString() {
      StringBuilder var3 = new StringBuilder();
      var3.append("[");
      int var1 = 0;

      while(true) {
         Locale[] var2 = this.mList;
         if (var1 >= var2.length) {
            var3.append("]");
            return var3.toString();
         }

         var3.append(var2[var1]);
         if (var1 < this.mList.length - 1) {
            var3.append(',');
         }

         ++var1;
      }
   }
}
