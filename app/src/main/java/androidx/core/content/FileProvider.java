package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class FileProvider extends ContentProvider {
   private static final String ATTR_NAME = "name";
   private static final String ATTR_PATH = "path";
   private static final String[] COLUMNS = new String[]{"_display_name", "_size"};
   private static final File DEVICE_ROOT = new File("/");
   private static final String DISPLAYNAME_FIELD = "displayName";
   private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
   private static final String TAG_CACHE_PATH = "cache-path";
   private static final String TAG_EXTERNAL = "external-path";
   private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
   private static final String TAG_EXTERNAL_FILES = "external-files-path";
   private static final String TAG_EXTERNAL_MEDIA = "external-media-path";
   private static final String TAG_FILES_PATH = "files-path";
   private static final String TAG_ROOT_PATH = "root-path";
   private static HashMap sCache = new HashMap();
   private PathStrategy mStrategy;

   private static File buildPath(File var0, String... var1) {
      int var3 = var1.length;

      File var4;
      for(int var2 = 0; var2 < var3; var0 = var4) {
         String var5 = var1[var2];
         var4 = var0;
         if (var5 != null) {
            var4 = new File(var0, var5);
         }

         ++var2;
      }

      return var0;
   }

   private static Object[] copyOf(Object[] var0, int var1) {
      Object[] var2 = new Object[var1];
      System.arraycopy(var0, 0, var2, 0, var1);
      return var2;
   }

   private static String[] copyOf(String[] var0, int var1) {
      String[] var2 = new String[var1];
      System.arraycopy(var0, 0, var2, 0, var1);
      return var2;
   }

   private static PathStrategy getPathStrategy(Context param0, String param1) {
      // $FF: Couldn't be decompiled
   }

   public static Uri getUriForFile(Context var0, String var1, File var2) {
      return getPathStrategy(var0, var1).getUriForFile(var2);
   }

   public static Uri getUriForFile(Context var0, String var1, File var2, String var3) {
      return getUriForFile(var0, var1, var2).buildUpon().appendQueryParameter("displayName", var3).build();
   }

   private static int modeToMode(String var0) {
      int var1;
      if ("r".equals(var0)) {
         var1 = 268435456;
      } else if (!"w".equals(var0) && !"wt".equals(var0)) {
         if ("wa".equals(var0)) {
            var1 = 704643072;
         } else if ("rw".equals(var0)) {
            var1 = 939524096;
         } else {
            if (!"rwt".equals(var0)) {
               throw new IllegalArgumentException("Invalid mode: " + var0);
            }

            var1 = 1006632960;
         }
      } else {
         var1 = 738197504;
      }

      return var1;
   }

   private static PathStrategy parsePathStrategy(Context var0, String var1) throws IOException, XmlPullParserException {
      SimplePathStrategy var4 = new SimplePathStrategy(var1);
      ProviderInfo var3 = var0.getPackageManager().resolveContentProvider(var1, 128);
      if (var3 != null) {
         XmlResourceParser var6 = var3.loadXmlMetaData(var0.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
         if (var6 != null) {
            while(true) {
               int var2 = var6.next();
               if (var2 == 1) {
                  return var4;
               }

               if (var2 == 2) {
                  String var8 = var6.getName();
                  var3 = null;
                  String var7 = var6.getAttributeValue((String)null, "name");
                  String var5 = var6.getAttributeValue((String)null, "path");
                  File var9;
                  if ("root-path".equals(var8)) {
                     var9 = DEVICE_ROOT;
                  } else if ("files-path".equals(var8)) {
                     var9 = var0.getFilesDir();
                  } else if ("cache-path".equals(var8)) {
                     var9 = var0.getCacheDir();
                  } else if ("external-path".equals(var8)) {
                     var9 = Environment.getExternalStorageDirectory();
                  } else {
                     File[] var10;
                     if ("external-files-path".equals(var8)) {
                        var10 = ContextCompat.getExternalFilesDirs(var0, (String)null);
                        var9 = var3;
                        if (var10.length > 0) {
                           var9 = var10[0];
                        }
                     } else if ("external-cache-path".equals(var8)) {
                        var10 = ContextCompat.getExternalCacheDirs(var0);
                        var9 = var3;
                        if (var10.length > 0) {
                           var9 = var10[0];
                        }
                     } else {
                        var9 = var3;
                        if (VERSION.SDK_INT >= 21) {
                           var9 = var3;
                           if ("external-media-path".equals(var8)) {
                              var10 = var0.getExternalMediaDirs();
                              var9 = var3;
                              if (var10.length > 0) {
                                 var9 = var10[0];
                              }
                           }
                        }
                     }
                  }

                  if (var9 != null) {
                     var4.addRoot(var7, buildPath(var9, var5));
                  }
               }
            }
         } else {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
         }
      } else {
         throw new IllegalArgumentException("Couldn't find meta-data for provider with authority " + var1);
      }
   }

   public void attachInfo(Context var1, ProviderInfo var2) {
      super.attachInfo(var1, var2);
      if (!var2.exported) {
         if (var2.grantUriPermissions) {
            this.mStrategy = getPathStrategy(var1, var2.authority.split(";")[0]);
         } else {
            throw new SecurityException("Provider must grant uri permissions");
         }
      } else {
         throw new SecurityException("Provider must not be exported");
      }
   }

   public int delete(Uri var1, String var2, String[] var3) {
      return this.mStrategy.getFileForUri(var1).delete();
   }

   public String getType(Uri var1) {
      File var3 = this.mStrategy.getFileForUri(var1);
      int var2 = var3.getName().lastIndexOf(46);
      if (var2 >= 0) {
         String var4 = var3.getName().substring(var2 + 1);
         var4 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var4);
         if (var4 != null) {
            return var4;
         }
      }

      return "application/octet-stream";
   }

   public Uri insert(Uri var1, ContentValues var2) {
      throw new UnsupportedOperationException("No external inserts");
   }

   public boolean onCreate() {
      return true;
   }

   public ParcelFileDescriptor openFile(Uri var1, String var2) throws FileNotFoundException {
      return ParcelFileDescriptor.open(this.mStrategy.getFileForUri(var1), modeToMode(var2));
   }

   public Cursor query(Uri var1, String[] var2, String var3, String[] var4, String var5) {
      File var15 = this.mStrategy.getFileForUri(var1);
      var3 = var1.getQueryParameter("displayName");
      String[] var11 = var2;
      if (var2 == null) {
         var11 = COLUMNS;
      }

      String[] var10 = new String[var11.length];
      Object[] var16 = new Object[var11.length];
      int var9 = var11.length;
      int var7 = 0;

      int var6;
      int var8;
      for(var6 = 0; var7 < var9; var6 = var8) {
         label27: {
            String var12 = var11[var7];
            if ("_display_name".equals(var12)) {
               var10[var6] = "_display_name";
               var8 = var6 + 1;
               if (var3 == null) {
                  var12 = var15.getName();
               } else {
                  var12 = var3;
               }

               var16[var6] = var12;
               var6 = var8;
            } else {
               var8 = var6;
               if (!"_size".equals(var12)) {
                  break label27;
               }

               var10[var6] = "_size";
               var8 = var6 + 1;
               var16[var6] = var15.length();
               var6 = var8;
            }

            var8 = var6;
         }

         ++var7;
      }

      var11 = copyOf(var10, var6);
      Object[] var14 = copyOf(var16, var6);
      MatrixCursor var13 = new MatrixCursor(var11, 1);
      var13.addRow(var14);
      return var13;
   }

   public int update(Uri var1, ContentValues var2, String var3, String[] var4) {
      throw new UnsupportedOperationException("No external updates");
   }

   interface PathStrategy {
      File getFileForUri(Uri var1);

      Uri getUriForFile(File var1);
   }

   static class SimplePathStrategy implements PathStrategy {
      private final String mAuthority;
      private final HashMap mRoots = new HashMap();

      SimplePathStrategy(String var1) {
         this.mAuthority = var1;
      }

      void addRoot(String var1, File var2) {
         if (!TextUtils.isEmpty(var1)) {
            File var3;
            try {
               var3 = var2.getCanonicalFile();
            } catch (IOException var4) {
               throw new IllegalArgumentException("Failed to resolve canonical path for " + var2, var4);
            }

            this.mRoots.put(var1, var3);
         } else {
            throw new IllegalArgumentException("Name must not be empty");
         }
      }

      public File getFileForUri(Uri var1) {
         String var4 = var1.getEncodedPath();
         int var2 = var4.indexOf(47, 1);
         String var3 = Uri.decode(var4.substring(1, var2));
         var4 = Uri.decode(var4.substring(var2 + 1));
         File var7 = (File)this.mRoots.get(var3);
         if (var7 != null) {
            File var6 = new File(var7, var4);

            File var8;
            try {
               var8 = var6.getCanonicalFile();
            } catch (IOException var5) {
               throw new IllegalArgumentException("Failed to resolve canonical path for " + var6);
            }

            if (var8.getPath().startsWith(var7.getPath())) {
               return var8;
            } else {
               throw new SecurityException("Resolved path jumped beyond configured root");
            }
         } else {
            throw new IllegalArgumentException("Unable to find configured root for " + var1);
         }
      }

      public Uri getUriForFile(File var1) {
         String var3;
         try {
            var3 = var1.getCanonicalPath();
         } catch (IOException var6) {
            throw new IllegalArgumentException("Failed to resolve canonical path for " + var1);
         }

         Map.Entry var7 = null;
         Iterator var4 = this.mRoots.entrySet().iterator();

         while(true) {
            Map.Entry var2;
            String var5;
            do {
               do {
                  if (!var4.hasNext()) {
                     if (var7 != null) {
                        String var9 = ((File)var7.getValue()).getPath();
                        if (var9.endsWith("/")) {
                           var9 = var3.substring(var9.length());
                        } else {
                           var9 = var3.substring(var9.length() + 1);
                        }

                        String var8 = Uri.encode((String)var7.getKey()) + '/' + Uri.encode(var9, "/");
                        return (new Uri.Builder()).scheme("content").authority(this.mAuthority).encodedPath(var8).build();
                     }

                     throw new IllegalArgumentException("Failed to find configured root that contains " + var3);
                  }

                  var2 = (Map.Entry)var4.next();
                  var5 = ((File)var2.getValue()).getPath();
               } while(!var3.startsWith(var5));
            } while(var7 != null && var5.length() <= ((File)var7.getValue()).getPath().length());

            var7 = var2;
         }
      }
   }
}
