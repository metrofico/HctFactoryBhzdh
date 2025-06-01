package androidx.core.text.util;

import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.core.util.PatternsCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat {
   private static final Comparator COMPARATOR = new Comparator() {
      public int compare(LinkSpec var1, LinkSpec var2) {
         if (var1.start < var2.start) {
            return -1;
         } else if (var1.start > var2.start) {
            return 1;
         } else if (var1.end < var2.end) {
            return 1;
         } else {
            return var1.end > var2.end ? -1 : 0;
         }
      }
   };
   private static final String[] EMPTY_STRING = new String[0];

   private LinkifyCompat() {
   }

   private static void addLinkMovementMethod(TextView var0) {
      if (!(var0.getMovementMethod() instanceof LinkMovementMethod) && var0.getLinksClickable()) {
         var0.setMovementMethod(LinkMovementMethod.getInstance());
      }

   }

   public static void addLinks(TextView var0, Pattern var1, String var2) {
      if (shouldAddLinksFallbackToFramework()) {
         Linkify.addLinks(var0, var1, var2);
      } else {
         addLinks((TextView)var0, var1, var2, (String[])null, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
      }
   }

   public static void addLinks(TextView var0, Pattern var1, String var2, Linkify.MatchFilter var3, Linkify.TransformFilter var4) {
      if (shouldAddLinksFallbackToFramework()) {
         Linkify.addLinks(var0, var1, var2, var3, var4);
      } else {
         addLinks((TextView)var0, var1, var2, (String[])null, var3, var4);
      }
   }

   public static void addLinks(TextView var0, Pattern var1, String var2, String[] var3, Linkify.MatchFilter var4, Linkify.TransformFilter var5) {
      if (shouldAddLinksFallbackToFramework()) {
         Linkify.addLinks(var0, var1, var2, var3, var4, var5);
      } else {
         SpannableString var6 = SpannableString.valueOf(var0.getText());
         if (addLinks((Spannable)var6, var1, var2, var3, var4, var5)) {
            var0.setText(var6);
            addLinkMovementMethod(var0);
         }

      }
   }

   public static boolean addLinks(Spannable var0, int var1) {
      if (shouldAddLinksFallbackToFramework()) {
         return Linkify.addLinks(var0, var1);
      } else if (var1 == 0) {
         return false;
      } else {
         URLSpan[] var3 = (URLSpan[])var0.getSpans(0, var0.length(), URLSpan.class);

         for(int var2 = var3.length - 1; var2 >= 0; --var2) {
            var0.removeSpan(var3[var2]);
         }

         if ((var1 & 4) != 0) {
            Linkify.addLinks(var0, 4);
         }

         ArrayList var4 = new ArrayList();
         if ((var1 & 1) != 0) {
            Pattern var6 = PatternsCompat.AUTOLINK_WEB_URL;
            Linkify.MatchFilter var5 = Linkify.sUrlMatchFilter;
            gatherLinks(var4, var0, var6, new String[]{"http://", "https://", "rtsp://"}, var5, (Linkify.TransformFilter)null);
         }

         if ((var1 & 2) != 0) {
            gatherLinks(var4, var0, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{"mailto:"}, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
         }

         if ((var1 & 8) != 0) {
            gatherMapLinks(var4, var0);
         }

         pruneOverlaps(var4, var0);
         if (var4.size() == 0) {
            return false;
         } else {
            Iterator var8 = var4.iterator();

            while(var8.hasNext()) {
               LinkSpec var7 = (LinkSpec)var8.next();
               if (var7.frameworkAddedSpan == null) {
                  applyLink(var7.url, var7.start, var7.end, var0);
               }
            }

            return true;
         }
      }
   }

   public static boolean addLinks(Spannable var0, Pattern var1, String var2) {
      return shouldAddLinksFallbackToFramework() ? Linkify.addLinks(var0, var1, var2) : addLinks((Spannable)var0, var1, var2, (String[])null, (Linkify.MatchFilter)null, (Linkify.TransformFilter)null);
   }

   public static boolean addLinks(Spannable var0, Pattern var1, String var2, Linkify.MatchFilter var3, Linkify.TransformFilter var4) {
      return shouldAddLinksFallbackToFramework() ? Linkify.addLinks(var0, var1, var2, var3, var4) : addLinks((Spannable)var0, var1, var2, (String[])null, var3, var4);
   }

   public static boolean addLinks(Spannable var0, Pattern var1, String var2, String[] var3, Linkify.MatchFilter var4, Linkify.TransformFilter var5) {
      if (shouldAddLinksFallbackToFramework()) {
         return Linkify.addLinks(var0, var1, var2, var3, var4, var5);
      } else {
         String var10 = var2;
         if (var2 == null) {
            var10 = "";
         }

         String[] var13;
         label42: {
            if (var3 != null) {
               var13 = var3;
               if (var3.length >= 1) {
                  break label42;
               }
            }

            var13 = EMPTY_STRING;
         }

         String[] var11 = new String[var13.length + 1];
         var11[0] = var10.toLowerCase(Locale.ROOT);

         int var6;
         String var14;
         for(var6 = 0; var6 < var13.length; var11[var6] = var14) {
            var14 = var13[var6];
            ++var6;
            if (var14 == null) {
               var14 = "";
            } else {
               var14 = var14.toLowerCase(Locale.ROOT);
            }
         }

         Matcher var12 = var1.matcher(var0);
         boolean var8 = false;

         while(var12.find()) {
            int var7 = var12.start();
            var6 = var12.end();
            boolean var9;
            if (var4 != null) {
               var9 = var4.acceptMatch(var0, var7, var6);
            } else {
               var9 = true;
            }

            if (var9) {
               applyLink(makeUrl(var12.group(0), var11, var12, var5), var7, var6, var0);
               var8 = true;
            }
         }

         return var8;
      }
   }

   public static boolean addLinks(TextView var0, int var1) {
      if (shouldAddLinksFallbackToFramework()) {
         return Linkify.addLinks(var0, var1);
      } else if (var1 == 0) {
         return false;
      } else {
         CharSequence var2 = var0.getText();
         if (var2 instanceof Spannable) {
            if (addLinks((Spannable)var2, var1)) {
               addLinkMovementMethod(var0);
               return true;
            } else {
               return false;
            }
         } else {
            SpannableString var3 = SpannableString.valueOf(var2);
            if (addLinks((Spannable)var3, var1)) {
               addLinkMovementMethod(var0);
               var0.setText(var3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private static void applyLink(String var0, int var1, int var2, Spannable var3) {
      var3.setSpan(new URLSpan(var0), var1, var2, 33);
   }

   private static String findAddress(String var0) {
      return VERSION.SDK_INT >= 28 ? WebView.findAddress(var0) : FindAddress.findAddress(var0);
   }

   private static void gatherLinks(ArrayList var0, Spannable var1, Pattern var2, String[] var3, Linkify.MatchFilter var4, Linkify.TransformFilter var5) {
      Matcher var8 = var2.matcher(var1);

      while(true) {
         int var6;
         int var7;
         do {
            if (!var8.find()) {
               return;
            }

            var6 = var8.start();
            var7 = var8.end();
         } while(var4 != null && !var4.acceptMatch(var1, var6, var7));

         LinkSpec var9 = new LinkSpec();
         var9.url = makeUrl(var8.group(0), var3, var8, var5);
         var9.start = var6;
         var9.end = var7;
         var0.add(var9);
      }
   }

   private static void gatherMapLinks(ArrayList param0, Spannable param1) {
      // $FF: Couldn't be decompiled
   }

   private static String makeUrl(String var0, String[] var1, Matcher var2, Linkify.TransformFilter var3) {
      String var7 = var0;
      if (var3 != null) {
         var7 = var3.transformUrl(var2, var0);
      }

      int var4 = 0;

      String var8;
      boolean var9;
      while(true) {
         int var5 = var1.length;
         boolean var6 = true;
         if (var4 >= var5) {
            var9 = false;
            var0 = var7;
            break;
         }

         var0 = var1[var4];
         if (var7.regionMatches(true, 0, var0, 0, var0.length())) {
            var8 = var1[var4];
            var9 = var6;
            var0 = var7;
            if (!var7.regionMatches(false, 0, var8, 0, var8.length())) {
               var0 = var1[var4] + var7.substring(var1[var4].length());
               var9 = var6;
            }
            break;
         }

         ++var4;
      }

      var8 = var0;
      if (!var9) {
         var8 = var0;
         if (var1.length > 0) {
            var8 = var1[0] + var0;
         }
      }

      return var8;
   }

   private static void pruneOverlaps(ArrayList var0, Spannable var1) {
      int var2 = var1.length();
      int var3 = 0;
      URLSpan[] var7 = (URLSpan[])var1.getSpans(0, var2, URLSpan.class);

      LinkSpec var6;
      for(var2 = 0; var2 < var7.length; ++var2) {
         var6 = new LinkSpec();
         var6.frameworkAddedSpan = var7[var2];
         var6.start = var1.getSpanStart(var7[var2]);
         var6.end = var1.getSpanEnd(var7[var2]);
         var0.add(var6);
      }

      Collections.sort(var0, COMPARATOR);
      int var4 = var0.size();
      var2 = var3;

      while(true) {
         while(var2 < var4 - 1) {
            var6 = (LinkSpec)var0.get(var2);
            int var5 = var2 + 1;
            LinkSpec var9 = (LinkSpec)var0.get(var5);
            if (var6.start <= var9.start && var6.end > var9.start) {
               if (var9.end > var6.end && var6.end - var6.start <= var9.end - var9.start) {
                  if (var6.end - var6.start < var9.end - var9.start) {
                     var3 = var2;
                  } else {
                     var3 = -1;
                  }
               } else {
                  var3 = var5;
               }

               if (var3 != -1) {
                  URLSpan var8 = ((LinkSpec)var0.get(var3)).frameworkAddedSpan;
                  if (var8 != null) {
                     var1.removeSpan(var8);
                  }

                  var0.remove(var3);
                  --var4;
                  continue;
               }
            }

            var2 = var5;
         }

         return;
      }
   }

   private static boolean shouldAddLinksFallbackToFramework() {
      boolean var0;
      if (VERSION.SDK_INT >= 28) {
         var0 = true;
      } else {
         var0 = false;
      }

      return var0;
   }

   private static class LinkSpec {
      int end;
      URLSpan frameworkAddedSpan;
      int start;
      String url;

      LinkSpec() {
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface LinkifyMask {
   }
}
