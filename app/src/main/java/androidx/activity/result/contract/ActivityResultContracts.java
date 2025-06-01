package androidx.activity.result.contract;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public final class ActivityResultContracts {
   private ActivityResultContracts() {
   }

   public static class CreateDocument extends ActivityResultContract {
      public Intent createIntent(Context var1, String var2) {
         return (new Intent("android.intent.action.CREATE_DOCUMENT")).setType("*/*").putExtra("android.intent.extra.TITLE", var2);
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String var2) {
         return null;
      }

      public final Uri parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? var2.getData() : null;
      }
   }

   public static class GetContent extends ActivityResultContract {
      public Intent createIntent(Context var1, String var2) {
         return (new Intent("android.intent.action.GET_CONTENT")).addCategory("android.intent.category.OPENABLE").setType(var2);
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String var2) {
         return null;
      }

      public final Uri parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? var2.getData() : null;
      }
   }

   public static class GetMultipleContents extends ActivityResultContract {
      static List getClipDataUris(Intent var0) {
         LinkedHashSet var2 = new LinkedHashSet();
         if (var0.getData() != null) {
            var2.add(var0.getData());
         }

         ClipData var4 = var0.getClipData();
         if (var4 == null && var2.isEmpty()) {
            return Collections.emptyList();
         } else {
            if (var4 != null) {
               for(int var1 = 0; var1 < var4.getItemCount(); ++var1) {
                  Uri var3 = var4.getItemAt(var1).getUri();
                  if (var3 != null) {
                     var2.add(var3);
                  }
               }
            }

            return new ArrayList(var2);
         }
      }

      public Intent createIntent(Context var1, String var2) {
         return (new Intent("android.intent.action.GET_CONTENT")).addCategory("android.intent.category.OPENABLE").setType(var2).putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String var2) {
         return null;
      }

      public final List parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? getClipDataUris(var2) : Collections.emptyList();
      }
   }

   public static class OpenDocument extends ActivityResultContract {
      public Intent createIntent(Context var1, String[] var2) {
         return (new Intent("android.intent.action.OPEN_DOCUMENT")).putExtra("android.intent.extra.MIME_TYPES", var2).setType("*/*");
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String[] var2) {
         return null;
      }

      public final Uri parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? var2.getData() : null;
      }
   }

   public static class OpenDocumentTree extends ActivityResultContract {
      public Intent createIntent(Context var1, Uri var2) {
         Intent var3 = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
         if (VERSION.SDK_INT >= 26 && var2 != null) {
            var3.putExtra("android.provider.extra.INITIAL_URI", var2);
         }

         return var3;
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, Uri var2) {
         return null;
      }

      public final Uri parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? var2.getData() : null;
      }
   }

   public static class OpenMultipleDocuments extends ActivityResultContract {
      public Intent createIntent(Context var1, String[] var2) {
         return (new Intent("android.intent.action.OPEN_DOCUMENT")).putExtra("android.intent.extra.MIME_TYPES", var2).putExtra("android.intent.extra.ALLOW_MULTIPLE", true).setType("*/*");
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String[] var2) {
         return null;
      }

      public final List parseResult(int var1, Intent var2) {
         return var1 == -1 && var2 != null ? ActivityResultContracts.GetMultipleContents.getClipDataUris(var2) : Collections.emptyList();
      }
   }

   public static final class PickContact extends ActivityResultContract {
      public Intent createIntent(Context var1, Void var2) {
         return (new Intent("android.intent.action.PICK")).setType("vnd.android.cursor.dir/contact");
      }

      public Uri parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? var2.getData() : null;
      }
   }

   public static final class RequestMultiplePermissions extends ActivityResultContract {
      public static final String ACTION_REQUEST_PERMISSIONS = "androidx.activity.result.contract.action.REQUEST_PERMISSIONS";
      public static final String EXTRA_PERMISSIONS = "androidx.activity.result.contract.extra.PERMISSIONS";
      public static final String EXTRA_PERMISSION_GRANT_RESULTS = "androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS";

      static Intent createIntent(String[] var0) {
         return (new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS")).putExtra("androidx.activity.result.contract.extra.PERMISSIONS", var0);
      }

      public Intent createIntent(Context var1, String[] var2) {
         return createIntent(var2);
      }

      public ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String[] var2) {
         if (var2 != null && var2.length != 0) {
            ArrayMap var8 = new ArrayMap();
            int var5 = var2.length;
            int var3 = 0;

            boolean var4;
            for(var4 = true; var3 < var5; ++var3) {
               String var7 = var2[var3];
               boolean var6;
               if (ContextCompat.checkSelfPermission(var1, var7) == 0) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               var8.put(var7, var6);
               if (!var6) {
                  var4 = false;
               }
            }

            if (var4) {
               return new ActivityResultContract.SynchronousResult(var8);
            } else {
               return null;
            }
         } else {
            return new ActivityResultContract.SynchronousResult(Collections.emptyMap());
         }
      }

      public Map parseResult(int var1, Intent var2) {
         if (var1 != -1) {
            return Collections.emptyMap();
         } else if (var2 == null) {
            return Collections.emptyMap();
         } else {
            String[] var5 = var2.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
            int[] var8 = var2.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            if (var8 != null && var5 != null) {
               HashMap var6 = new HashMap();
               int var3 = var5.length;

               for(var1 = 0; var1 < var3; ++var1) {
                  String var7 = var5[var1];
                  boolean var4;
                  if (var8[var1] == 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  var6.put(var7, var4);
               }

               return var6;
            } else {
               return Collections.emptyMap();
            }
         }
      }
   }

   public static final class RequestPermission extends ActivityResultContract {
      public Intent createIntent(Context var1, String var2) {
         return ActivityResultContracts.RequestMultiplePermissions.createIntent(new String[]{var2});
      }

      public ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, String var2) {
         if (var2 == null) {
            return new ActivityResultContract.SynchronousResult(false);
         } else {
            return ContextCompat.checkSelfPermission(var1, var2) == 0 ? new ActivityResultContract.SynchronousResult(true) : null;
         }
      }

      public Boolean parseResult(int var1, Intent var2) {
         boolean var3 = false;
         if (var2 != null && var1 == -1) {
            int[] var4 = var2.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
            if (var4 != null && var4.length != 0) {
               if (var4[0] == 0) {
                  var3 = true;
               }

               return var3;
            }
         }

         return false;
      }
   }

   public static final class StartActivityForResult extends ActivityResultContract {
      public static final String EXTRA_ACTIVITY_OPTIONS_BUNDLE = "androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE";

      public Intent createIntent(Context var1, Intent var2) {
         return var2;
      }

      public ActivityResult parseResult(int var1, Intent var2) {
         return new ActivityResult(var1, var2);
      }
   }

   public static final class StartIntentSenderForResult extends ActivityResultContract {
      public static final String ACTION_INTENT_SENDER_REQUEST = "androidx.activity.result.contract.action.INTENT_SENDER_REQUEST";
      public static final String EXTRA_INTENT_SENDER_REQUEST = "androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST";
      public static final String EXTRA_SEND_INTENT_EXCEPTION = "androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION";

      public Intent createIntent(Context var1, IntentSenderRequest var2) {
         return (new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST")).putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", var2);
      }

      public ActivityResult parseResult(int var1, Intent var2) {
         return new ActivityResult(var1, var2);
      }
   }

   public static class TakePicture extends ActivityResultContract {
      public Intent createIntent(Context var1, Uri var2) {
         return (new Intent("android.media.action.IMAGE_CAPTURE")).putExtra("output", var2);
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, Uri var2) {
         return null;
      }

      public final Boolean parseResult(int var1, Intent var2) {
         boolean var3;
         if (var1 == -1) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }
   }

   public static class TakePicturePreview extends ActivityResultContract {
      public Intent createIntent(Context var1, Void var2) {
         return new Intent("android.media.action.IMAGE_CAPTURE");
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, Void var2) {
         return null;
      }

      public final Bitmap parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? (Bitmap)var2.getParcelableExtra("data") : null;
      }
   }

   public static class TakeVideo extends ActivityResultContract {
      public Intent createIntent(Context var1, Uri var2) {
         return (new Intent("android.media.action.VIDEO_CAPTURE")).putExtra("output", var2);
      }

      public final ActivityResultContract.SynchronousResult getSynchronousResult(Context var1, Uri var2) {
         return null;
      }

      public final Bitmap parseResult(int var1, Intent var2) {
         return var2 != null && var1 == -1 ? (Bitmap)var2.getParcelableExtra("data") : null;
      }
   }
}
