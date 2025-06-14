package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import java.io.File;

public abstract class DocumentFile {
   static final String TAG = "DocumentFile";
   private final DocumentFile mParent;

   DocumentFile(DocumentFile var1) {
      this.mParent = var1;
   }

   public static DocumentFile fromFile(File var0) {
      return new RawDocumentFile((DocumentFile)null, var0);
   }

   public static DocumentFile fromSingleUri(Context var0, Uri var1) {
      return VERSION.SDK_INT >= 19 ? new SingleDocumentFile((DocumentFile)null, var0, var1) : null;
   }

   public static DocumentFile fromTreeUri(Context var0, Uri var1) {
      return VERSION.SDK_INT >= 21 ? new TreeDocumentFile((DocumentFile)null, var0, DocumentsContract.buildDocumentUriUsingTree(var1, DocumentsContract.getTreeDocumentId(var1))) : null;
   }

   public static boolean isDocumentUri(Context var0, Uri var1) {
      return VERSION.SDK_INT >= 19 ? DocumentsContract.isDocumentUri(var0, var1) : false;
   }

   public abstract boolean canRead();

   public abstract boolean canWrite();

   public abstract DocumentFile createDirectory(String var1);

   public abstract DocumentFile createFile(String var1, String var2);

   public abstract boolean delete();

   public abstract boolean exists();

   public DocumentFile findFile(String var1) {
      DocumentFile[] var5 = this.listFiles();
      int var3 = var5.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         DocumentFile var4 = var5[var2];
         if (var1.equals(var4.getName())) {
            return var4;
         }
      }

      return null;
   }

   public abstract String getName();

   public DocumentFile getParentFile() {
      return this.mParent;
   }

   public abstract String getType();

   public abstract Uri getUri();

   public abstract boolean isDirectory();

   public abstract boolean isFile();

   public abstract boolean isVirtual();

   public abstract long lastModified();

   public abstract long length();

   public abstract DocumentFile[] listFiles();

   public abstract boolean renameTo(String var1);
}
