package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentListener;

public final class TextViewOnReceiveContentListener implements OnReceiveContentListener {
   private static final String LOG_TAG = "ReceiveContent";

   private static CharSequence coerceToText(Context var0, ClipData.Item var1, int var2) {
      return VERSION.SDK_INT >= 16 ? TextViewOnReceiveContentListener.Api16Impl.coerce(var0, var1, var2) : TextViewOnReceiveContentListener.ApiImpl.coerce(var0, var1, var2);
   }

   private static void replaceSelection(Editable var0, CharSequence var1) {
      int var3 = Selection.getSelectionStart(var0);
      int var4 = Selection.getSelectionEnd(var0);
      int var2 = Math.max(0, Math.min(var3, var4));
      var3 = Math.max(0, Math.max(var3, var4));
      Selection.setSelection(var0, var3);
      var0.replace(var2, var3, var1);
   }

   public ContentInfoCompat onReceiveContent(View var1, ContentInfoCompat var2) {
      if (Log.isLoggable("ReceiveContent", 3)) {
         Log.d("ReceiveContent", "onReceive: " + var2);
      }

      if (var2.getSource() == 2) {
         return var2;
      } else {
         ClipData var7 = var2.getClip();
         int var6 = var2.getFlags();
         TextView var10 = (TextView)var1;
         Editable var9 = (Editable)var10.getText();
         Context var11 = var10.getContext();
         int var3 = 0;

         boolean var5;
         for(boolean var4 = false; var3 < var7.getItemCount(); var4 = var5) {
            CharSequence var8 = coerceToText(var11, var7.getItemAt(var3), var6);
            var5 = var4;
            if (var8 != null) {
               if (!var4) {
                  replaceSelection(var9, var8);
                  var5 = true;
               } else {
                  var9.insert(Selection.getSelectionEnd(var9), "\n");
                  var9.insert(Selection.getSelectionEnd(var9), var8);
                  var5 = var4;
               }
            }

            ++var3;
         }

         return null;
      }
   }

   private static final class Api16Impl {
      static CharSequence coerce(Context var0, ClipData.Item var1, int var2) {
         if ((var2 & 1) != 0) {
            CharSequence var4 = var1.coerceToText(var0);
            Object var3 = var4;
            if (var4 instanceof Spanned) {
               var3 = var4.toString();
            }

            return (CharSequence)var3;
         } else {
            return var1.coerceToStyledText(var0);
         }
      }
   }

   private static final class ApiImpl {
      static CharSequence coerce(Context var0, ClipData.Item var1, int var2) {
         CharSequence var4 = var1.coerceToText(var0);
         Object var3 = var4;
         if ((var2 & 1) != 0) {
            var3 = var4;
            if (var4 instanceof Spanned) {
               var3 = var4.toString();
            }
         }

         return (CharSequence)var3;
      }
   }
}
