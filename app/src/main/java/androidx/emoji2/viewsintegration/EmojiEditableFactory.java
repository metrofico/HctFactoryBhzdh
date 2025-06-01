package androidx.emoji2.viewsintegration;

import android.text.Editable;
import androidx.emoji2.text.SpannableBuilder;

final class EmojiEditableFactory extends Editable.Factory {
   private static final Object INSTANCE_LOCK = new Object();
   private static volatile Editable.Factory sInstance;
   private static Class sWatcherClass;

   private EmojiEditableFactory() {
      try {
         sWatcherClass = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, this.getClass().getClassLoader());
      } finally {
         return;
      }

   }

   public static Editable.Factory getInstance() {
      if (sInstance == null) {
         Object var0 = INSTANCE_LOCK;
         synchronized(var0){}

         Throwable var10000;
         boolean var10001;
         label144: {
            try {
               if (sInstance == null) {
                  EmojiEditableFactory var1 = new EmojiEditableFactory();
                  sInstance = var1;
               }
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label144;
            }

            label141:
            try {
               return sInstance;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label141;
            }
         }

         while(true) {
            Throwable var14 = var10000;

            try {
               throw var14;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               continue;
            }
         }
      } else {
         return sInstance;
      }
   }

   public Editable newEditable(CharSequence var1) {
      Class var2 = sWatcherClass;
      return (Editable)(var2 != null ? SpannableBuilder.create(var2, var1) : super.newEditable(var1));
   }
}
