package com.hzbhd.commontools.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.util.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class DocumentTool {
   private static String[] PERMISSON_STORAGE = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
   private static final int REQUEST_EXTERNAL_STORAGE = 1;
   private static final String TAG = "DocumentTool";

   public static void addFile(String var0) {
      try {
         File var2 = new File(var0);
         if (!var2.exists()) {
            boolean var1 = var2.createNewFile();
            StringBuilder var4;
            if (LogUtil.log5()) {
               var4 = new StringBuilder();
               LogUtil.d(var4.append("文件创建状态--->").append(var1).toString());
            }

            if (LogUtil.log5()) {
               var4 = new StringBuilder();
               LogUtil.d(var4.append("文件所在路径：").append(var2.toString()).toString());
            }

            deleteFile(var2);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static void addFolder(String var0) {
      try {
         File var2 = new File(var0);
         StringBuilder var4;
         if (!var2.exists()) {
            boolean var1 = var2.mkdirs();
            if (LogUtil.log5()) {
               var4 = new StringBuilder();
               LogUtil.d(var4.append("文件夹创建状态--->").append(var1).toString());
            }
         }

         if (LogUtil.log5()) {
            var4 = new StringBuilder();
            LogUtil.d(var4.append("文件夹所在目录：").append(var2.toString()).toString());
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public static void checkFilePath(File var0, boolean var1) {
      if (LogUtil.log5()) {
         StringBuilder var3 = (new StringBuilder()).append("checkFilePath file=");
         boolean var2;
         if (var0 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         LogUtil.d(var3.append(var2).append(" , !file.ex=").append(true ^ var0.exists()).toString());
      }

      if (var0 != null) {
         File var4 = var0;
         if (!var1) {
            var4 = var0.getParentFile();
         }

         if (var4 != null && !var4.exists()) {
            var4.mkdirs();
         }
      }

   }

   public static void checkFilePath(String var0, boolean var1) {
      File var2 = new File(var0);
      Log.d("DocumentTool", "checkFilePath file=" + true + " , !file.ex=" + (true ^ var2.exists()));
      File var3 = var2;
      if (!var1) {
         var3 = var2.getParentFile();
      }

      if (var3 != null && !var3.exists()) {
         var3.mkdirs();
      }

   }

   public static int copyDir(String var0, String var1) {
      File var4 = new File(var0);
      if (!var4.exists()) {
         return -1;
      } else {
         File[] var5 = var4.listFiles();
         File var3 = new File(var1);
         if (!var3.exists()) {
            var3.mkdirs();
         }

         for(int var2 = 0; var2 < var5.length; ++var2) {
            if (var5[var2].isDirectory()) {
               copyDir(var5[var2].getPath() + "/", var5[var2].getName() + "/");
            } else {
               copyFile(var5[var2].getPath(), var1 + var5[var2].getName());
            }
         }

         return 0;
      }
   }

   public static int copyFile(File var0, File var1) {
      Exception var10000;
      label27: {
         boolean var10001;
         int var2;
         FileInputStream var3;
         FileOutputStream var7;
         byte[] var9;
         try {
            var3 = new FileInputStream(var0);
            var7 = new FileOutputStream(var1);
            var9 = new byte[1024];
            var2 = var3.read(var9);
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label27;
         }

         if (var2 > 0) {
            try {
               var7.write(var9, 0, var2);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label27;
            }
         }

         try {
            var3.close();
            var7.flush();
            var7.close();
            return 0;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var8 = var10000;
      var8.printStackTrace();
      return -1;
   }

   public static int copyFile(String var0, String var1) {
      Exception var10000;
      label27: {
         boolean var10001;
         int var2;
         FileInputStream var3;
         FileOutputStream var7;
         byte[] var9;
         try {
            var3 = new FileInputStream(var0);
            var7 = new FileOutputStream(var1);
            var9 = new byte[1024];
            var2 = var3.read(var9);
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label27;
         }

         if (var2 > 0) {
            try {
               var7.write(var9, 0, var2);
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label27;
            }
         }

         try {
            var3.close();
            var7.flush();
            var7.close();
            return 0;
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      Exception var8 = var10000;
      var8.printStackTrace();
      return -1;
   }

   public static void copyFileFormAsset(Context var0, String var1, String var2) {
      if (!(new File(var2)).exists()) {
         Exception var10000;
         label35: {
            InputStream var9;
            FileOutputStream var11;
            byte[] var12;
            boolean var10001;
            try {
               var9 = var0.getAssets().open(var1);
               File var4 = new File(var2);
               var11 = new FileOutputStream(var4);
               var12 = new byte[1024];
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break label35;
            }

            while(true) {
               int var3;
               try {
                  var3 = var9.read(var12);
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
                  break;
               }

               if (var3 == -1) {
                  try {
                     var9.close();
                     var11.flush();
                     var11.getFD().sync();
                     var11.close();
                     return;
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  var11.write(var12, 0, var3);
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }
            }
         }

         Exception var10 = var10000;
         var10.printStackTrace();
      }

   }

   public static void deleteAllFile(String var0) {
      deleteFile(new File(var0));
   }

   public static void deleteFile(File var0) {
      if (var0.exists()) {
         boolean var2;
         if (var0.isFile()) {
            var2 = var0.delete();
            if (LogUtil.log5()) {
               LogUtil.d("文件删除状态--->" + var2);
            }
         } else if (var0.isDirectory()) {
            File[] var3 = var0.listFiles();

            for(int var1 = 0; var1 < var3.length; ++var1) {
               deleteFile(var3[var1]);
            }

            var2 = var0.delete();
            if (LogUtil.log5()) {
               LogUtil.d("文件夹删除状态--->" + var2);
            }
         }
      }

   }

   public static void deleteFile(String var0) {
      File var3 = new File(var0);
      if (var3.exists()) {
         boolean var2;
         if (var3.isFile()) {
            var2 = var3.delete();
            if (LogUtil.log5()) {
               LogUtil.d("文件删除状态--->" + var2);
            }
         } else if (var3.isDirectory()) {
            File[] var4 = var3.listFiles();

            for(int var1 = 0; var1 < var4.length; ++var1) {
               deleteFile(var4[var1]);
            }

            var2 = var3.delete();
            if (LogUtil.log5()) {
               LogUtil.d("文件夹删除状态--->" + var2);
            }
         }
      }

   }

   public static String getAssetsString(Context var0, String var1) {
      AssetManager var9 = var0.getResources().getAssets();

      IOException var10000;
      label34: {
         BufferedReader var2;
         StringBuilder var4;
         InputStreamReader var10;
         InputStream var12;
         boolean var10001;
         try {
            var12 = var9.open(var1);
            var10 = new InputStreamReader(var12, "UTF-8");
            var2 = new BufferedReader(var10);
            var4 = new StringBuilder();
         } catch (IOException var8) {
            var10000 = var8;
            var10001 = false;
            break label34;
         }

         while(true) {
            String var3;
            try {
               var3 = var2.readLine();
            } catch (IOException var6) {
               var10000 = var6;
               var10001 = false;
               break;
            }

            if (var3 == null) {
               try {
                  var2.close();
                  var10.close();
                  var12.close();
                  String var13 = var4.toString();
                  return var13;
               } catch (IOException var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }
            }

            try {
               var4.append(var3);
            } catch (IOException var7) {
               var10000 = var7;
               var10001 = false;
               break;
            }
         }
      }

      IOException var11 = var10000;
      var11.printStackTrace();
      return "";
   }

   public static List getFileList(String var0) {
      ArrayList var3 = new ArrayList();
      File[] var5 = (new File(var0)).listFiles();
      if (var5 == null) {
         if (LogUtil.log7()) {
            LogUtil.d("getFileList: error=空目录");
         }
      } else {
         int var2 = var5.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            File var4 = var5[var1];
            if (!var4.isDirectory() && !var4.isHidden()) {
               if (LogUtil.log5()) {
                  LogUtil.d("getFileList: 绝对路径=[" + var4.getAbsolutePath() + "]");
               }

               var3.add(var4.getAbsolutePath());
            }
         }
      }

      return var3;
   }

   public static long getFileSize(String var0) {
      File var4 = new File(var0);
      long var1;
      if (var4.exists()) {
         try {
            var1 = var4.length();
            return var1;
         } catch (Exception var3) {
            var3.printStackTrace();
            if (LogUtil.log7()) {
               LogUtil.d("getFileSize: " + var3.getMessage());
            }
         }
      } else if (LogUtil.log7()) {
         LogUtil.d("getFileSize: 获取文件大小：文件不存在！！");
      }

      var1 = 0L;
      return var1;
   }

   public static String getFolderName(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return var0;
      } else {
         int var1 = var0.lastIndexOf(File.separator);
         if (var1 == -1) {
            var0 = "";
         } else {
            var0 = var0.substring(0, var1);
         }

         return var0;
      }
   }

   public static boolean hasFileExists(String var0) {
      File var1 = new File(var0);
      return var1.exists() && var1.listFiles().length > 0;
   }

   public static boolean isFileExists(String var0) {
      return (new File(var0)).exists();
   }

   public static boolean isFolderExists(String var0) {
      boolean var1 = TextUtils.isEmpty(var0);
      boolean var2 = false;
      if (var1) {
         return false;
      } else {
         File var3 = new File(var0);
         var1 = var2;
         if (var3.exists()) {
            var1 = var2;
            if (var3.isDirectory()) {
               var1 = true;
            }
         }

         return var1;
      }
   }

   public static String readFileContent(String var0) {
      Exception var10000;
      label36: {
         FileInputStream var2;
         StringBuffer var4;
         byte[] var9;
         boolean var10001;
         try {
            File var3 = new File(var0);
            var9 = new byte['耀'];
            var2 = new FileInputStream(var3);
            var4 = new StringBuffer("");
         } catch (Exception var8) {
            var10000 = var8;
            var10001 = false;
            break label36;
         }

         while(true) {
            int var1;
            try {
               var1 = var2.read(var9);
            } catch (Exception var6) {
               var10000 = var6;
               var10001 = false;
               break;
            }

            if (var1 <= 0) {
               try {
                  var2.close();
                  var0 = var4.toString();
                  return var0;
               } catch (Exception var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }
            }

            try {
               String var11 = new String(var9, 0, var1);
               var4.append(var11);
            } catch (Exception var7) {
               var10000 = var7;
               var10001 = false;
               break;
            }
         }
      }

      Exception var10 = var10000;
      var10.printStackTrace();
      if (LogUtil.log7()) {
         LogUtil.d("readFileContent: " + var10.getMessage());
      }

      return null;
   }

   public static boolean renameFile(String var0, String var1) {
      return (new File(var0)).renameTo(new File(var1));
   }

   public static void writeData(String var0, String var1) {
      try {
         File var2 = new File(var0);
         FileOutputStream var4 = new FileOutputStream(var2, false);
         var4.write(var1.getBytes("UTF-8"));
         if (LogUtil.log5()) {
            StringBuilder var5 = new StringBuilder();
            LogUtil.d(var5.append("将数据写入到文件中：").append(var1).toString());
         }

         var4.flush();
         var4.getFD().sync();
         var4.close();
      } catch (Exception var3) {
         var3.printStackTrace();
         if (LogUtil.log7()) {
            LogUtil.d("writeData: error:" + var3.getMessage());
         }
      }

   }

   public static void writeData(String var0, String var1, boolean var2) {
      try {
         File var3 = new File(var0);
         FileOutputStream var5 = new FileOutputStream(var3, var2);
         var5.write(var1.getBytes("UTF-8"));
         if (LogUtil.log5()) {
            StringBuilder var6 = new StringBuilder();
            LogUtil.d(var6.append("将数据写入到文件中：").append(var1).toString());
         }

         var5.flush();
         var5.getFD().sync();
         var5.close();
      } catch (Exception var4) {
         var4.printStackTrace();
         if (LogUtil.log7()) {
            LogUtil.d("writeData: error:" + var4.getMessage());
         }
      }

   }

   public static void writtenFileData(String var0, String var1) {
      try {
         File var2 = new File(var0);
         RandomAccessFile var4 = new RandomAccessFile(var2, "rw");
         var4.seek(var2.length());
         var4.write(var1.getBytes("UTF-8"));
         if (LogUtil.log5()) {
            StringBuilder var5 = new StringBuilder();
            LogUtil.d(var5.append("要续写进去的数据：").append(var1).toString());
         }

         var4.getFD().sync();
         var4.close();
      } catch (Exception var3) {
         var3.printStackTrace();
         if (LogUtil.log7()) {
            LogUtil.d("writtenFileData: " + var3.getMessage());
         }
      }

   }
}
