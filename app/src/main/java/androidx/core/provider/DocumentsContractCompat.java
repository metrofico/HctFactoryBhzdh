package androidx.core.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import java.io.FileNotFoundException;
import java.util.List;

public final class DocumentsContractCompat {
   private static final String PATH_TREE = "tree";

   private DocumentsContractCompat() {
   }

   public static Uri buildChildDocumentsUri(String var0, String var1) {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.buildChildDocumentsUri(var0, var1) : null;
   }

   public static Uri buildChildDocumentsUriUsingTree(Uri var0, String var1) {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.buildChildDocumentsUriUsingTree(var0, var1) : null;
   }

   public static Uri buildDocumentUri(String var0, String var1) {
      return VERSION.SDK_INT >= 19 ? DocumentsContract.buildDocumentUri(var0, var1) : null;
   }

   public static Uri buildDocumentUriUsingTree(Uri var0, String var1) {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.buildDocumentUriUsingTree(var0, var1) : null;
   }

   public static Uri buildTreeDocumentUri(String var0, String var1) {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.buildTreeDocumentUri(var0, var1) : null;
   }

   public static Uri createDocument(ContentResolver var0, Uri var1, String var2, String var3) throws FileNotFoundException {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.createDocument(var0, var1, var2, var3) : null;
   }

   public static String getDocumentId(Uri var0) {
      return VERSION.SDK_INT >= 19 ? DocumentsContract.getDocumentId(var0) : null;
   }

   public static String getTreeDocumentId(Uri var0) {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.getTreeDocumentId(var0) : null;
   }

   public static boolean isDocumentUri(Context var0, Uri var1) {
      return VERSION.SDK_INT >= 19 ? DocumentsContract.isDocumentUri(var0, var1) : false;
   }

   public static boolean isTreeUri(Uri var0) {
      int var1 = VERSION.SDK_INT;
      boolean var3 = false;
      if (var1 < 21) {
         return false;
      } else if (VERSION.SDK_INT < 24) {
         List var4 = var0.getPathSegments();
         boolean var2 = var3;
         if (var4.size() >= 2) {
            var2 = var3;
            if ("tree".equals(var4.get(0))) {
               var2 = true;
            }
         }

         return var2;
      } else {
         return DocumentsContract.isTreeUri(var0);
      }
   }

   public static boolean removeDocument(ContentResolver var0, Uri var1, Uri var2) throws FileNotFoundException {
      if (VERSION.SDK_INT >= 24) {
         return DocumentsContract.removeDocument(var0, var1, var2);
      } else {
         return VERSION.SDK_INT >= 19 ? DocumentsContract.deleteDocument(var0, var1) : false;
      }
   }

   public static Uri renameDocument(ContentResolver var0, Uri var1, String var2) throws FileNotFoundException {
      return VERSION.SDK_INT >= 21 ? DocumentsContract.renameDocument(var0, var1, var2) : null;
   }

   public static final class DocumentCompat {
      public static final int FLAG_VIRTUAL_DOCUMENT = 512;

      private DocumentCompat() {
      }
   }

   private static class DocumentsContractApi19Impl {
      public static Uri buildDocumentUri(String var0, String var1) {
         return DocumentsContract.buildDocumentUri(var0, var1);
      }

      static boolean deleteDocument(ContentResolver var0, Uri var1) throws FileNotFoundException {
         return DocumentsContract.deleteDocument(var0, var1);
      }

      static String getDocumentId(Uri var0) {
         return DocumentsContract.getDocumentId(var0);
      }

      static boolean isDocumentUri(Context var0, Uri var1) {
         return DocumentsContract.isDocumentUri(var0, var1);
      }
   }

   private static class DocumentsContractApi21Impl {
      static Uri buildChildDocumentsUri(String var0, String var1) {
         return DocumentsContract.buildChildDocumentsUri(var0, var1);
      }

      static Uri buildChildDocumentsUriUsingTree(Uri var0, String var1) {
         return DocumentsContract.buildChildDocumentsUriUsingTree(var0, var1);
      }

      static Uri buildDocumentUriUsingTree(Uri var0, String var1) {
         return DocumentsContract.buildDocumentUriUsingTree(var0, var1);
      }

      public static Uri buildTreeDocumentUri(String var0, String var1) {
         return DocumentsContract.buildTreeDocumentUri(var0, var1);
      }

      static Uri createDocument(ContentResolver var0, Uri var1, String var2, String var3) throws FileNotFoundException {
         return DocumentsContract.createDocument(var0, var1, var2, var3);
      }

      static String getTreeDocumentId(Uri var0) {
         return DocumentsContract.getTreeDocumentId(var0);
      }

      static Uri renameDocument(ContentResolver var0, Uri var1, String var2) throws FileNotFoundException {
         return DocumentsContract.renameDocument(var0, var1, var2);
      }
   }

   private static class DocumentsContractApi24Impl {
      static boolean isTreeUri(Uri var0) {
         return DocumentsContract.isTreeUri(var0);
      }

      static boolean removeDocument(ContentResolver var0, Uri var1, Uri var2) throws FileNotFoundException {
         return DocumentsContract.removeDocument(var0, var1, var2);
      }
   }
}
