package androidx.documentfile.provider;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class RawDocumentFile extends DocumentFile {
   private File mFile;

   RawDocumentFile(DocumentFile var1, File var2) {
      super(var1);
      this.mFile = var2;
   }

   private static boolean deleteContents(File var0) {
      File[] var5 = var0.listFiles();
      boolean var4 = true;
      boolean var3 = true;
      if (var5 != null) {
         int var2 = var5.length;
         int var1 = 0;

         while(true) {
            var4 = var3;
            if (var1 >= var2) {
               break;
            }

            var0 = var5[var1];
            var4 = var3;
            if (var0.isDirectory()) {
               var4 = var3 & deleteContents(var0);
            }

            var3 = var4;
            if (!var0.delete()) {
               Log.w("DocumentFile", "Failed to delete " + var0);
               var3 = false;
            }

            ++var1;
         }
      }

      return var4;
   }

   private static String getTypeForName(String var0) {
      int var1 = var0.lastIndexOf(46);
      if (var1 >= 0) {
         var0 = var0.substring(var1 + 1).toLowerCase();
         var0 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var0);
         if (var0 != null) {
            return var0;
         }
      }

      return "application/octet-stream";
   }

   public boolean canRead() {
      return this.mFile.canRead();
   }

   public boolean canWrite() {
      return this.mFile.canWrite();
   }

   public DocumentFile createDirectory(String var1) {
      File var2 = new File(this.mFile, var1);
      return !var2.isDirectory() && !var2.mkdir() ? null : new RawDocumentFile(this, var2);
   }

   public DocumentFile createFile(String var1, String var2) {
      String var3 = MimeTypeMap.getSingleton().getExtensionFromMimeType(var1);
      var1 = var2;
      if (var3 != null) {
         var1 = var2 + "." + var3;
      }

      File var5 = new File(this.mFile, var1);

      try {
         var5.createNewFile();
         RawDocumentFile var6 = new RawDocumentFile(this, var5);
         return var6;
      } catch (IOException var4) {
         Log.w("DocumentFile", "Failed to createFile: " + var4);
         return null;
      }
   }

   public boolean delete() {
      deleteContents(this.mFile);
      return this.mFile.delete();
   }

   public boolean exists() {
      return this.mFile.exists();
   }

   public String getName() {
      return this.mFile.getName();
   }

   public String getType() {
      return this.mFile.isDirectory() ? null : getTypeForName(this.mFile.getName());
   }

   public Uri getUri() {
      return Uri.fromFile(this.mFile);
   }

   public boolean isDirectory() {
      return this.mFile.isDirectory();
   }

   public boolean isFile() {
      return this.mFile.isFile();
   }

   public boolean isVirtual() {
      return false;
   }

   public long lastModified() {
      return this.mFile.lastModified();
   }

   public long length() {
      return this.mFile.length();
   }

   public DocumentFile[] listFiles() {
      ArrayList var4 = new ArrayList();
      File[] var3 = this.mFile.listFiles();
      if (var3 != null) {
         int var2 = var3.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            var4.add(new RawDocumentFile(this, var3[var1]));
         }
      }

      return (DocumentFile[])var4.toArray(new DocumentFile[var4.size()]);
   }

   public boolean renameTo(String var1) {
      File var2 = new File(this.mFile.getParentFile(), var1);
      if (this.mFile.renameTo(var2)) {
         this.mFile = var2;
         return true;
      } else {
         return false;
      }
   }
}
