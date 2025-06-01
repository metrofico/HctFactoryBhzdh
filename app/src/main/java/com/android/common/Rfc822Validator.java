package com.android.common;

import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.widget.AutoCompleteTextView;
import java.util.regex.Pattern;

@Deprecated
public class Rfc822Validator implements AutoCompleteTextView.Validator {
   private static final String DOMAIN_REGEXP = "(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef][a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61})?[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]\\.)+[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef][a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]";
   private static final String EMAIL_ADDRESS_LOCALPART_REGEXP = "((?!\\s)[\\.\\w!#$%&'*+\\-/=?^`{|}~\u0080-\ufffe])+";
   private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("((?!\\s)[\\.\\w!#$%&'*+\\-/=?^`{|}~\u0080-\ufffe])+@(([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef][a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61})?[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]\\.)+[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef][a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61}[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]");
   private static final String GOOD_IRI_CHAR = "a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef";
   private static final String LABEL_REGEXP = "([a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef][a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef\\-]{0,61})?[a-zA-Z0-9 -\ud7ff豈-﷏ﷰ-\uffef]";
   private String mDomain;
   private boolean mRemoveInvalid = false;

   public Rfc822Validator(String var1) {
      this.mDomain = var1;
   }

   private String removeIllegalCharacters(String var1) {
      StringBuilder var5 = new StringBuilder();
      int var4 = var1.length();

      for(int var3 = 0; var3 < var4; ++var3) {
         char var2 = var1.charAt(var3);
         if (var2 > ' ' && var2 <= '~' && var2 != '(' && var2 != ')' && var2 != '<' && var2 != '>' && var2 != '@' && var2 != ',' && var2 != ';' && var2 != ':' && var2 != '\\' && var2 != '"' && var2 != '[' && var2 != ']') {
            var5.append(var2);
         }
      }

      return var5.toString();
   }

   public CharSequence fixText(CharSequence var1) {
      if (TextUtils.getTrimmedLength(var1) == 0) {
         return "";
      } else {
         Rfc822Token[] var6 = Rfc822Tokenizer.tokenize(var1);
         StringBuilder var5 = new StringBuilder();

         for(int var2 = 0; var2 < var6.length; ++var2) {
            String var10 = var6[var2].getAddress();
            if (!this.mRemoveInvalid || this.isValid(var10)) {
               int var4 = var10.indexOf(64);
               Rfc822Token var7;
               StringBuilder var8;
               if (var4 < 0) {
                  if (this.mDomain != null) {
                     var7 = var6[var2];
                     var8 = new StringBuilder();
                     var8.append(this.removeIllegalCharacters(var10));
                     var8.append("@");
                     var8.append(this.mDomain);
                     var7.setAddress(var8.toString());
                  }
               } else {
                  boolean var3 = false;
                  String var9 = this.removeIllegalCharacters(var10.substring(0, var4));
                  if (TextUtils.isEmpty(var9)) {
                     continue;
                  }

                  var10 = this.removeIllegalCharacters(var10.substring(var4 + 1));
                  if (var10.length() == 0) {
                     var3 = true;
                  }

                  if (!var3 || this.mDomain != null) {
                     var7 = var6[var2];
                     var8 = new StringBuilder();
                     var8.append(var9);
                     var8.append("@");
                     if (var3) {
                        var10 = this.mDomain;
                     }

                     var8.append(var10);
                     var7.setAddress(var8.toString());
                  }
               }

               var5.append(var6[var2].toString());
               if (var2 + 1 < var6.length) {
                  var5.append(", ");
               }
            }
         }

         return var5;
      }
   }

   public boolean isValid(CharSequence var1) {
      Rfc822Token[] var4 = Rfc822Tokenizer.tokenize(var1);
      int var2 = var4.length;
      boolean var3 = false;
      if (var2 == 1 && EMAIL_ADDRESS_PATTERN.matcher(var4[0].getAddress()).matches()) {
         var3 = true;
      }

      return var3;
   }

   public void setRemoveInvalid(boolean var1) {
      this.mRemoveInvalid = var1;
   }
}
