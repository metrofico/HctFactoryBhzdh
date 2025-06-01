package androidx.core.view.inputmethod;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.ViewCompat;

public final class InputConnectionCompat {
   private static final String COMMIT_CONTENT_ACTION = "androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
   private static final String COMMIT_CONTENT_CONTENT_URI_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
   private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
   private static final String COMMIT_CONTENT_DESCRIPTION_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
   private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
   private static final String COMMIT_CONTENT_FLAGS_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
   private static final String COMMIT_CONTENT_FLAGS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
   private static final String COMMIT_CONTENT_INTEROP_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
   private static final String COMMIT_CONTENT_LINK_URI_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
   private static final String COMMIT_CONTENT_LINK_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
   private static final String COMMIT_CONTENT_OPTS_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
   private static final String COMMIT_CONTENT_OPTS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
   private static final String COMMIT_CONTENT_RESULT_INTEROP_RECEIVER_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
   private static final String COMMIT_CONTENT_RESULT_RECEIVER_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
   private static final String EXTRA_INPUT_CONTENT_INFO = "androidx.core.view.extra.INPUT_CONTENT_INFO";
   public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
   private static final String LOG_TAG = "InputConnectionCompat";

   public static boolean commitContent(InputConnection var0, EditorInfo var1, InputContentInfoCompat var2, int var3, Bundle var4) {
      ClipDescription var9 = var2.getDescription();
      String[] var8 = EditorInfoCompat.getContentMimeTypes(var1);
      int var7 = var8.length;
      boolean var6 = false;
      int var5 = 0;

      boolean var11;
      while(true) {
         if (var5 >= var7) {
            var11 = false;
            break;
         }

         if (var9.hasMimeType(var8[var5])) {
            var11 = true;
            break;
         }

         ++var5;
      }

      if (!var11) {
         return false;
      } else if (VERSION.SDK_INT >= 25) {
         return var0.commitContent((InputContentInfo)var2.unwrap(), var3, var4);
      } else {
         var7 = EditorInfoCompat.getProtocol(var1);
         if (var7 != 2) {
            var11 = var6;
            if (var7 != 3) {
               var11 = var6;
               if (var7 != 4) {
                  return false;
               }
            }
         } else {
            var11 = true;
         }

         Bundle var12 = new Bundle();
         String var10;
         if (var11) {
            var10 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
         } else {
            var10 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
         }

         var12.putParcelable(var10, var2.getContentUri());
         if (var11) {
            var10 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
         } else {
            var10 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
         }

         var12.putParcelable(var10, var2.getDescription());
         if (var11) {
            var10 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
         } else {
            var10 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
         }

         var12.putParcelable(var10, var2.getLinkUri());
         if (var11) {
            var10 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
         } else {
            var10 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
         }

         var12.putInt(var10, var3);
         if (var11) {
            var10 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
         } else {
            var10 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
         }

         var12.putParcelable(var10, var4);
         if (var11) {
            var10 = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
         } else {
            var10 = "androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
         }

         return var0.performPrivateCommand(var10, var12);
      }
   }

   private static OnCommitContentListener createOnCommitContentListenerUsingPerformReceiveContent(View var0) {
      Preconditions.checkNotNull(var0);
      return new OnCommitContentListener(var0) {
         final View val$view;

         {
            this.val$view = var1;
         }

         public boolean onCommitContent(InputContentInfoCompat var1, int var2, Bundle var3) {
            int var4 = VERSION.SDK_INT;
            boolean var5 = false;
            Bundle var6 = var3;
            if (var4 >= 25) {
               var6 = var3;
               if ((var2 & 1) != 0) {
                  try {
                     var1.requestPermission();
                  } catch (Exception var7) {
                     Log.w("InputConnectionCompat", "Can't insert content from IME; requestPermission() failed", var7);
                     return false;
                  }

                  InputContentInfo var9 = (InputContentInfo)var1.unwrap();
                  if (var3 == null) {
                     var3 = new Bundle();
                  } else {
                     var3 = new Bundle(var3);
                  }

                  var3.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", var9);
                  var6 = var3;
               }
            }

            ContentInfoCompat var8 = (new ContentInfoCompat.Builder(new ClipData(var1.getDescription(), new ClipData.Item(var1.getContentUri())), 2)).setLinkUri(var1.getLinkUri()).setExtras(var6).build();
            if (ViewCompat.performReceiveContent(this.val$view, var8) == null) {
               var5 = true;
            }

            return var5;
         }
      };
   }

   public static InputConnection createWrapper(View var0, InputConnection var1, EditorInfo var2) {
      return createWrapper(var1, var2, createOnCommitContentListenerUsingPerformReceiveContent(var0));
   }

   @Deprecated
   public static InputConnection createWrapper(InputConnection var0, EditorInfo var1, OnCommitContentListener var2) {
      ObjectsCompat.requireNonNull(var0, "inputConnection must be non-null");
      ObjectsCompat.requireNonNull(var1, "editorInfo must be non-null");
      ObjectsCompat.requireNonNull(var2, "onCommitContentListener must be non-null");
      if (VERSION.SDK_INT >= 25) {
         return new InputConnectionWrapper(var0, false, var2) {
            final OnCommitContentListener val$listener;

            {
               this.val$listener = var3;
            }

            public boolean commitContent(InputContentInfo var1, int var2, Bundle var3) {
               return this.val$listener.onCommitContent(InputContentInfoCompat.wrap(var1), var2, var3) ? true : super.commitContent(var1, var2, var3);
            }
         };
      } else {
         return (InputConnection)(EditorInfoCompat.getContentMimeTypes(var1).length == 0 ? var0 : new InputConnectionWrapper(var0, false, var2) {
            final OnCommitContentListener val$listener;

            {
               this.val$listener = var3;
            }

            public boolean performPrivateCommand(String var1, Bundle var2) {
               return InputConnectionCompat.handlePerformPrivateCommand(var1, var2, this.val$listener) ? true : super.performPrivateCommand(var1, var2);
            }
         });
      }
   }

   static boolean handlePerformPrivateCommand(String var0, Bundle var1, OnCommitContentListener var2) {
      byte var4 = 0;
      if (var1 == null) {
         return false;
      } else {
         boolean var3;
         if (TextUtils.equals("androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", var0)) {
            var3 = false;
         } else {
            if (!TextUtils.equals("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", var0)) {
               return false;
            }

            var3 = true;
         }

         if (var3) {
            var0 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
         } else {
            var0 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
         }

         ResultReceiver var6;
         try {
            var6 = (ResultReceiver)var1.getParcelable(var0);
         } finally {
            ;
         }

         if (var3) {
            var0 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
         } else {
            var0 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
         }

         byte var67;
         label832: {
            Throwable var10000;
            label833: {
               Uri var7;
               boolean var10001;
               try {
                  var7 = (Uri)var1.getParcelable(var0);
               } catch (Throwable var65) {
                  var10000 = var65;
                  var10001 = false;
                  break label833;
               }

               if (var3) {
                  var0 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
               } else {
                  var0 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
               }

               ClipDescription var8;
               try {
                  var8 = (ClipDescription)var1.getParcelable(var0);
               } catch (Throwable var64) {
                  var10000 = var64;
                  var10001 = false;
                  break label833;
               }

               if (var3) {
                  var0 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
               } else {
                  var0 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
               }

               Uri var9;
               try {
                  var9 = (Uri)var1.getParcelable(var0);
               } catch (Throwable var63) {
                  var10000 = var63;
                  var10001 = false;
                  break label833;
               }

               if (var3) {
                  var0 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
               } else {
                  var0 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
               }

               int var5;
               try {
                  var5 = var1.getInt(var0);
               } catch (Throwable var62) {
                  var10000 = var62;
                  var10001 = false;
                  break label833;
               }

               if (var3) {
                  var0 = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
               } else {
                  var0 = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
               }

               Bundle var68;
               try {
                  var68 = (Bundle)var1.getParcelable(var0);
               } catch (Throwable var61) {
                  var10000 = var61;
                  var10001 = false;
                  break label833;
               }

               var67 = var4;
               if (var7 == null) {
                  break label832;
               }

               var67 = var4;
               if (var8 == null) {
                  break label832;
               }

               label805:
               try {
                  InputContentInfoCompat var66 = new InputContentInfoCompat(var7, var8, var9);
                  var67 = var2.onCommitContent(var66, var5, var68);
                  break label832;
               } catch (Throwable var60) {
                  var10000 = var60;
                  var10001 = false;
                  break label805;
               }
            }

            Throwable var69 = var10000;
            if (var6 != null) {
               var6.send(0, (Bundle)null);
            }

            throw var69;
         }

         if (var6 != null) {
            var6.send(var67, (Bundle)null);
         }

         return (boolean)var67;
      }
   }

   public interface OnCommitContentListener {
      boolean onCommitContent(InputContentInfoCompat var1, int var2, Bundle var3);
   }
}
