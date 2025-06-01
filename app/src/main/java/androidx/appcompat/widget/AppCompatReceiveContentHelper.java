package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;

final class AppCompatReceiveContentHelper {
   private static final String LOG_TAG = "ReceiveContent";

   private AppCompatReceiveContentHelper() {
   }

   static boolean maybeHandleDragEventViaPerformReceiveContent(View var0, DragEvent var1) {
      if (VERSION.SDK_INT < 31 && VERSION.SDK_INT >= 24 && var1.getLocalState() == null && ViewCompat.getOnReceiveContentMimeTypes(var0) != null) {
         Activity var3 = tryGetActivity(var0);
         if (var3 == null) {
            Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + var0);
            return false;
         }

         if (var1.getAction() == 1) {
            return var0 instanceof TextView ^ true;
         }

         if (var1.getAction() == 3) {
            boolean var2;
            if (var0 instanceof TextView) {
               var2 = AppCompatReceiveContentHelper.OnDropApi24Impl.onDropForTextView(var1, (TextView)var0, var3);
            } else {
               var2 = AppCompatReceiveContentHelper.OnDropApi24Impl.onDropForView(var1, var0, var3);
            }

            return var2;
         }
      }

      return false;
   }

   static boolean maybeHandleMenuActionViaPerformReceiveContent(TextView var0, int var1) {
      int var3 = VERSION.SDK_INT;
      byte var2 = 0;
      if (var3 < 31 && ViewCompat.getOnReceiveContentMimeTypes(var0) != null && (var1 == 16908322 || var1 == 16908337)) {
         ClipboardManager var4 = (ClipboardManager)var0.getContext().getSystemService("clipboard");
         ClipData var6;
         if (var4 == null) {
            var6 = null;
         } else {
            var6 = var4.getPrimaryClip();
         }

         if (var6 != null && var6.getItemCount() > 0) {
            ContentInfoCompat.Builder var7 = new ContentInfoCompat.Builder(var6, 1);
            byte var5;
            if (var1 == 16908322) {
               var5 = var2;
            } else {
               var5 = 1;
            }

            ViewCompat.performReceiveContent(var0, var7.setFlags(var5).build());
         }

         return true;
      } else {
         return false;
      }
   }

   static Activity tryGetActivity(View var0) {
      for(Context var1 = var0.getContext(); var1 instanceof ContextWrapper; var1 = ((ContextWrapper)var1).getBaseContext()) {
         if (var1 instanceof Activity) {
            return (Activity)var1;
         }
      }

      return null;
   }

   private static final class OnDropApi24Impl {
      static boolean onDropForTextView(DragEvent var0, TextView var1, Activity var2) {
         var2.requestDragAndDropPermissions(var0);
         int var3 = var1.getOffsetForPosition(var0.getX(), var0.getY());
         var1.beginBatchEdit();

         try {
            Selection.setSelection((Spannable)var1.getText(), var3);
            ContentInfoCompat.Builder var6 = new ContentInfoCompat.Builder(var0.getClipData(), 3);
            ViewCompat.performReceiveContent(var1, var6.build());
         } finally {
            var1.endBatchEdit();
         }

         return true;
      }

      static boolean onDropForView(DragEvent var0, View var1, Activity var2) {
         var2.requestDragAndDropPermissions(var0);
         ViewCompat.performReceiveContent(var1, (new ContentInfoCompat.Builder(var0.getClipData(), 3)).build());
         return true;
      }
   }
}
