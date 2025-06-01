package androidx.core.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
   private static final String LOG_TAG = "AtomicFile";
   private final File mBaseName;
   private final File mLegacyBackupName;
   private final File mNewName;

   public AtomicFile(File var1) {
      this.mBaseName = var1;
      this.mNewName = new File(var1.getPath() + ".new");
      this.mLegacyBackupName = new File(var1.getPath() + ".bak");
   }

   private static void rename(File var0, File var1) {
      if (var1.isDirectory() && !var1.delete()) {
         Log.e("AtomicFile", "Failed to delete file which is a directory " + var1);
      }

      if (!var0.renameTo(var1)) {
         Log.e("AtomicFile", "Failed to rename " + var0 + " to " + var1);
      }

   }

   private static boolean sync(FileOutputStream var0) {
      try {
         var0.getFD().sync();
         return true;
      } catch (IOException var1) {
         return false;
      }
   }

   public void delete() {
      this.mBaseName.delete();
      this.mNewName.delete();
      this.mLegacyBackupName.delete();
   }

   public void failWrite(FileOutputStream var1) {
      if (var1 != null) {
         if (!sync(var1)) {
            Log.e("AtomicFile", "Failed to sync file output stream");
         }

         try {
            var1.close();
         } catch (IOException var2) {
            Log.e("AtomicFile", "Failed to close file output stream", var2);
         }

         if (!this.mNewName.delete()) {
            Log.e("AtomicFile", "Failed to delete new file " + this.mNewName);
         }

      }
   }

   public void finishWrite(FileOutputStream var1) {
      if (var1 != null) {
         if (!sync(var1)) {
            Log.e("AtomicFile", "Failed to sync file output stream");
         }

         try {
            var1.close();
         } catch (IOException var2) {
            Log.e("AtomicFile", "Failed to close file output stream", var2);
         }

         rename(this.mNewName, this.mBaseName);
      }
   }

   public File getBaseFile() {
      return this.mBaseName;
   }

   public FileInputStream openRead() throws FileNotFoundException {
      if (this.mLegacyBackupName.exists()) {
         rename(this.mLegacyBackupName, this.mBaseName);
      }

      if (this.mNewName.exists() && this.mBaseName.exists() && !this.mNewName.delete()) {
         Log.e("AtomicFile", "Failed to delete outdated new file " + this.mNewName);
      }

      return new FileInputStream(this.mBaseName);
   }

   public byte[] readFully() throws IOException {
      FileInputStream var6 = this.openRead();

      Throwable var10000;
      label229: {
         byte[] var4;
         boolean var10001;
         try {
            var4 = new byte[var6.available()];
         } catch (Throwable var26) {
            var10000 = var26;
            var10001 = false;
            break label229;
         }

         int var1 = 0;

         while(true) {
            int var2;
            try {
               var2 = var6.read(var4, var1, var4.length - var1);
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break;
            }

            if (var2 <= 0) {
               var6.close();
               return var4;
            }

            var2 += var1;

            int var3;
            try {
               var3 = var6.available();
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break;
            }

            var1 = var2;

            byte[] var5;
            try {
               if (var3 <= var4.length - var2) {
                  continue;
               }

               var5 = new byte[var3 + var2];
               System.arraycopy(var4, 0, var5, 0, var2);
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break;
            }

            var4 = var5;
            var1 = var2;
         }
      }

      Throwable var27 = var10000;
      var6.close();
      throw var27;
   }

   public FileOutputStream startWrite() throws IOException {
      if (this.mLegacyBackupName.exists()) {
         rename(this.mLegacyBackupName, this.mBaseName);
      }

      FileOutputStream var1;
      try {
         var1 = new FileOutputStream(this.mNewName);
         return var1;
      } catch (FileNotFoundException var3) {
         if (this.mNewName.getParentFile().mkdirs()) {
            try {
               var1 = new FileOutputStream(this.mNewName);
               return var1;
            } catch (FileNotFoundException var2) {
               throw new IOException("Failed to create new file " + this.mNewName, var2);
            }
         } else {
            throw new IOException("Failed to create directory for " + this.mNewName);
         }
      }
   }
}
