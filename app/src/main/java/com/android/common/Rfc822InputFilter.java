package com.android.common;

import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

public class Rfc822InputFilter implements InputFilter {
   public CharSequence filter(CharSequence var1, int var2, int var3, Spanned var4, int var5, int var6) {
      if (var3 - var2 == 1 && var1.charAt(var2) == ' ') {
         boolean var7 = false;

         while(var5 > 0) {
            --var5;
            char var8 = var4.charAt(var5);
            if (var8 == ',') {
               return null;
            }

            if (var8 != '.') {
               if (var8 == '@') {
                  if (!var7) {
                     return null;
                  }

                  if (var1 instanceof Spanned) {
                     SpannableStringBuilder var9 = new SpannableStringBuilder(",");
                     var9.append(var1);
                     return var9;
                  }

                  return ", ";
               }
            } else {
               var7 = true;
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
