package org.apache.log4j.lf5.viewer.configure;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

public class MRUFileManager {
   private static final String CONFIG_FILE_NAME = "mru_file_manager";
   private static final int DEFAULT_MAX_SIZE = 3;
   private int _maxSize = 0;
   private LinkedList _mruFileList;

   public MRUFileManager() {
      this.load();
      this.setMaxSize(3);
   }

   public MRUFileManager(int var1) {
      this.load();
      this.setMaxSize(var1);
   }

   public static void createConfigurationDirectory() {
      String var0 = System.getProperty("user.home");
      String var1 = System.getProperty("file.separator");
      File var3 = new File(var0 + var1 + "lf5");
      if (!var3.exists()) {
         try {
            var3.mkdir();
         } catch (SecurityException var2) {
            var2.printStackTrace();
         }
      }

   }

   public Object getFile(int var1) {
      return var1 < this.size() ? this._mruFileList.get(var1) : null;
   }

   protected String getFilename() {
      String var1 = System.getProperty("user.home");
      String var2 = System.getProperty("file.separator");
      return var1 + var2 + "lf5" + var2 + "mru_file_manager";
   }

   public InputStream getInputStream(int var1) throws IOException, FileNotFoundException {
      if (var1 < this.size()) {
         Object var2 = this.getFile(var1);
         return var2 instanceof File ? this.getInputStream((File)var2) : this.getInputStream((URL)var2);
      } else {
         return null;
      }
   }

   protected InputStream getInputStream(File var1) throws IOException, FileNotFoundException {
      return new BufferedInputStream(new FileInputStream(var1));
   }

   protected InputStream getInputStream(URL var1) throws IOException {
      return var1.openStream();
   }

   public String[] getMRUFileList() {
      if (this.size() == 0) {
         return null;
      } else {
         String[] var2 = new String[this.size()];

         for(int var1 = 0; var1 < this.size(); ++var1) {
            Object var3 = this.getFile(var1);
            if (var3 instanceof File) {
               var2[var1] = ((File)var3).getAbsolutePath();
            } else {
               var2[var1] = var3.toString();
            }
         }

         return var2;
      }
   }

   protected void load() {
      createConfigurationDirectory();
      File var3 = new File(this.getFilename());
      if (var3.exists()) {
         label36: {
            boolean var10001;
            Iterator var8;
            try {
               FileInputStream var2 = new FileInputStream(var3);
               ObjectInputStream var1 = new ObjectInputStream(var2);
               this._mruFileList = (LinkedList)var1.readObject();
               var1.close();
               var8 = this._mruFileList.iterator();
            } catch (Exception var6) {
               var10001 = false;
               break label36;
            }

            while(true) {
               try {
                  if (!var8.hasNext()) {
                     return;
                  }
               } catch (Exception var5) {
                  var10001 = false;
                  break;
               }

               try {
                  Object var7 = var8.next();
                  if (!(var7 instanceof File) && !(var7 instanceof URL)) {
                     var8.remove();
                  }
               } catch (Exception var4) {
                  var10001 = false;
                  break;
               }
            }
         }

         this._mruFileList = new LinkedList();
      } else {
         this._mruFileList = new LinkedList();
      }

   }

   public void moveToTop(int var1) {
      LinkedList var2 = this._mruFileList;
      var2.add(0, var2.remove(var1));
   }

   public void save() {
      File var3 = new File(this.getFilename());

      try {
         FileOutputStream var2 = new FileOutputStream(var3);
         ObjectOutputStream var1 = new ObjectOutputStream(var2);
         var1.writeObject(this._mruFileList);
         var1.flush();
         var1.close();
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public void set(File var1) {
      this.setMRU(var1);
   }

   public void set(URL var1) {
      this.setMRU(var1);
   }

   protected void setMRU(Object var1) {
      int var2 = this._mruFileList.indexOf(var1);
      if (var2 == -1) {
         this._mruFileList.add(0, var1);
         this.setMaxSize(this._maxSize);
      } else {
         this.moveToTop(var2);
      }

   }

   protected void setMaxSize(int var1) {
      if (var1 < this._mruFileList.size()) {
         for(int var2 = 0; var2 < this._mruFileList.size() - var1; ++var2) {
            this._mruFileList.removeLast();
         }
      }

      this._maxSize = var1;
   }

   public int size() {
      return this._mruFileList.size();
   }
}
