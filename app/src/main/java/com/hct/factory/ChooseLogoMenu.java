package com.hct.factory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemProperties;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChooseLogoMenu extends Activity {
   private static final Comparator sDescriptionComparator = new Comparator() {
      public int compare(Device var1, Device var2) {
         String var3 = var1.mTitle;
         String var4 = var2.mTitle;
         if (var1.mType.equals("flash")) {
            return -1;
         } else if (var2.mType.equals("flash")) {
            return 1;
         } else if (var3.compareTo(var4) > 0) {
            return 1;
         } else {
            return var3.compareTo(var4) < 0 ? -1 : 0;
         }
      }
   };
   private String PATH = "/cache/logo/customer.png";
   private final String TAG = "ChooseLogoMenu";
   private ArrayList fileList = null;
   private List mDevice;
   private ListfileAdapter mLogomenuAdapter;
   private ListView mLogomenuListView;
   private TextView mPath;
   private ImageView mPreimage;
   private StorageManager mStorageManager = null;
   private File rootFile = null;

   private boolean SaveLogo(String var1, int var2) {
      StringBuilder var9 = new StringBuilder();
      var9.append("SaveLogo:");
      var9.append(var1);
      Log.i("chun", var9.toString());
      DisplayMetrics var10 = new DisplayMetrics();
      this.getWindowManager().getDefaultDisplay().getRealMetrics(var10);
      BitmapFactory.Options var18 = new BitmapFactory.Options();
      var18.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(var1, var18);
      byte var8 = 1;
      int var5 = var10.widthPixels;
      int var6 = var10.heightPixels;
      int var7 = var8;
      if (var2 != 0) {
         if (var10.widthPixels > 800) {
            var5 = 640;
            var6 = 320;
            var7 = var8;
         } else {
            var5 = 512;
            var6 = 256;
            var7 = var8;
         }
      }

      while(var18.outWidth / var7 / 2 > var5 || var18.outHeight / var7 / 2 > var6) {
         var7 *= 2;
      }

      var18 = new BitmapFactory.Options();
      var18.inSampleSize = var7;
      var18.inPurgeable = true;
      var18.inInputShareable = true;
      Bitmap var15 = BitmapFactory.decodeFile(var1, var18);
      var2 = var18.outWidth;
      var7 = var18.outHeight;
      float var4 = (float)var5 / (float)var2;
      float var3 = (float)var6 / (float)var7;
      Matrix var19 = new Matrix();
      if (var4 < var3) {
         var19.postScale(var4, var4);
      } else {
         var19.postScale(var3, var3);
      }

      var15 = Bitmap.createBitmap(var15, 0, 0, var2, var7, var19, false);

      try {
         Runtime var20 = Runtime.getRuntime();
         StringBuilder var21 = new StringBuilder();
         var21.append("chmod 777 ");
         var21.append(this.PATH);
         var20.exec(var21.toString());
      } catch (Exception var14) {
         StringBuilder var16 = new StringBuilder();
         var16.append("chmod 777 ");
         var16.append(this.PATH);
         var16.append(" fail!");
         Log.i("ChooseLogoMenu", var16.toString());
         return false;
      }

      File var22 = new File(this.PATH);

      try {
         var22.createNewFile();
      } catch (IOException var13) {
         var9 = new StringBuilder();
         var9.append("createNewFile fail!  >>> ");
         var9.append(var13.toString());
         Log.i("ChooseLogoMenu", var9.toString());
         return false;
      }

      FileOutputStream var23;
      try {
         var23 = new FileOutputStream(var22);
      } catch (FileNotFoundException var12) {
         Log.i("ChooseLogoMenu", "FileOutputStream fail!");
         var12.printStackTrace();
         return false;
      }

      var15.compress(CompressFormat.PNG, 99, var23);

      try {
         var23.flush();
         var23.getFD().sync();
         var23.close();
         Runtime var17 = Runtime.getRuntime();
         var9 = new StringBuilder();
         var9.append("chmod 777 ");
         var9.append(this.PATH);
         var17.exec(var9.toString());
         return true;
      } catch (IOException var11) {
         Log.i("ChooseLogoMenu", "save fail!");
         var11.printStackTrace();
         return false;
      }
   }

   private boolean SaveMode(int var1) {
      boolean var3 = false;
      int var2 = this.mLogomenuAdapter.getSelectedItem();
      ArrayList var4 = this.fileList;
      if (var4 != null && var2 >= var4.size()) {
         Toast.makeText(this.getApplicationContext(), "It is not Image!", 0).show();
         return false;
      } else if (!((String)((HashMap)this.fileList.get(var2)).get("type")).equalsIgnoreCase("image")) {
         Toast.makeText(this.getApplicationContext(), "It is not Image!", 0).show();
         return false;
      } else {
         if (this.SaveLogo((String)((HashMap)this.fileList.get(var2)).get("path"), var1)) {
            Intent var5 = new Intent();
            var5.putExtra("logopath", this.PATH);
            this.setResult(1, var5);
            var3 = true;
         } else {
            Toast.makeText(this.getApplicationContext(), "Save fail!", 0).show();
         }

         return var3;
      }
   }

   private void SetPathName(String var1) {
      String var3 = "  ";
      String var2 = var3;
      if (!var1.equalsIgnoreCase("/mnt")) {
         if (var1.equalsIgnoreCase("/storage")) {
            var2 = var3;
         } else {
            var3 = var1;
            Iterator var4 = this.mDevice.iterator();

            while(true) {
               var2 = var3;
               if (!var4.hasNext()) {
                  break;
               }

               Device var6 = (Device)var4.next();
               if (var1.startsWith(var6.mPath)) {
                  var2 = var1.replace(var6.mPath, var6.mTitle);
                  break;
               }
            }
         }
      }

      TextView var5 = this.mPath;
      StringBuilder var7 = new StringBuilder();
      var7.append(var2);
      var7.append("/");
      var5.setText(var7.toString());
   }

   private Bitmap getLocalBitmap(File var1) {
      Object var3 = null;
      Bitmap var2 = (Bitmap)var3;
      if (var1.exists()) {
         try {
            BitmapFactory.Options var5 = new BitmapFactory.Options();
            var5.inSampleSize = 8;
            var5.inPurgeable = true;
            var5.inInputShareable = true;
            var2 = BitmapFactory.decodeFile(var1.getPath(), var5);
         } catch (Exception var4) {
            var4.printStackTrace();
            var2 = (Bitmap)var3;
         }
      }

      return var2;
   }

   private void showDir(File var1) {
      String var5 = ".";
      String var6 = var1.getPath();
      if (!var6.equals("/system/media") && !var6.equals("/mnt") && !var6.equals("/storage") && !var6.equals("/storage/emulated")) {
         label229: {
            String var7;
            File[] var8;
            try {
               if (!var1.exists()) {
                  break label229;
               }

               this.fileList.clear();
               var8 = var1.listFiles();
               var7 = var1.getPath();
               if (var1.getParentFile() != this.rootFile.getParentFile() && !var7.equals("/") && !var7.equals("/mnt")) {
                  HashMap var35 = new HashMap();
                  var35.put("path", var1.getPath());
                  var35.put("type", "root");
                  this.fileList.add(var35);
               }
            } catch (Exception var30) {
               break label229;
            }

            int var3;
            try {
               this.rootFile = var1;
               var3 = var8.length;
            } catch (Exception var29) {
               break label229;
            }

            String var32 = var6;

            for(int var2 = 0; var2 < var3; var32 = var7) {
               File var36 = var8[var2];

               label241: {
                  label203: {
                     label239: {
                        boolean var10001;
                        try {
                           if (!var36.exists()) {
                              break label203;
                           }
                        } catch (Exception var23) {
                           var10001 = false;
                           break;
                        }

                        try {
                           if (var36.getName().startsWith(var5)) {
                              break label239;
                           }
                        } catch (Exception var22) {
                           var10001 = false;
                           break;
                        }

                        HashMap var9;
                        try {
                           var9 = new HashMap;
                        } catch (Exception var21) {
                           var10001 = false;
                           break;
                        }

                        try {
                           var9.<init>();
                        } catch (Exception var20) {
                           var10001 = false;
                           break;
                        }

                        try {
                           var32 = var36.getPath();
                        } catch (Exception var19) {
                           var10001 = false;
                           break;
                        }

                        try {
                           var9.put("path", var32);
                        } catch (Exception var18) {
                           var10001 = false;
                           break;
                        }

                        label233: {
                           label234: {
                              try {
                                 if (var36.isDirectory()) {
                                    break label234;
                                 }
                              } catch (Exception var28) {
                                 var10001 = false;
                                 break;
                              }

                              try {
                                 var7 = var36.getPath();
                              } catch (Exception var16) {
                                 var10001 = false;
                                 break;
                              }

                              int var4;
                              try {
                                 var4 = var7.lastIndexOf(var5);
                              } catch (Exception var15) {
                                 var10001 = false;
                                 break;
                              }

                              if (var4 == -1) {
                                 var7 = var32;
                                 break label241;
                              }

                              String var10;
                              try {
                                 var10 = var7.substring(var4);
                              } catch (Exception var14) {
                                 var10001 = false;
                                 break;
                              }

                              label235: {
                                 try {
                                    if (var10.equalsIgnoreCase(".jpg")) {
                                       break label235;
                                    }
                                 } catch (Exception var26) {
                                    var10001 = false;
                                    break;
                                 }

                                 try {
                                    if (var10.equalsIgnoreCase(".png")) {
                                       break label235;
                                    }
                                 } catch (Exception var25) {
                                    var10001 = false;
                                    break;
                                 }

                                 try {
                                    if (var10.equalsIgnoreCase(".bmp")) {
                                       break label235;
                                    }
                                 } catch (Exception var24) {
                                    var10001 = false;
                                    break;
                                 }

                                 var7 = var32;

                                 try {
                                    if (!var10.equalsIgnoreCase(".jpeg")) {
                                       break label241;
                                    }
                                 } catch (Exception var13) {
                                    var10001 = false;
                                    break;
                                 }
                              }

                              try {
                                 var9.put("type", "image");
                                 break label233;
                              } catch (Exception var12) {
                                 var10001 = false;
                                 break;
                              }
                           }

                           label192: {
                              try {
                                 if (!var36.getParent().equalsIgnoreCase("/mnt")) {
                                    break label192;
                                 }
                              } catch (Exception var27) {
                                 var10001 = false;
                                 break;
                              }

                              var7 = var32;
                              break label241;
                           }

                           try {
                              var9.put("type", "floder");
                           } catch (Exception var17) {
                              var10001 = false;
                              break;
                           }
                        }

                        try {
                           this.fileList.add(var9);
                        } catch (Exception var11) {
                           var10001 = false;
                           break;
                        }

                        var7 = var32;
                        break label241;
                     }

                     var7 = var32;
                     break label241;
                  }

                  var7 = var32;
               }

               ++var2;
            }
         }
      } else {
         this.fileList.clear();
         this.rootFile = new File("/storage");
         this.refreshVolume();

         HashMap var31;
         for(Iterator var33 = this.mDevice.iterator(); var33.hasNext(); this.fileList.add(var31)) {
            Device var34 = (Device)var33.next();
            var31 = new HashMap();
            var31.put("path", var34.mPath);
            if (var34.mType.equals("flash")) {
               var31.put("type", var34.mType);
            } else {
               var31.put("type", var34.mTitle);
            }
         }
      }

      this.mLogomenuAdapter.setSelectedItem(0);
      this.mLogomenuAdapter.notifyDataSetInvalidated();
      this.SetPathName(this.rootFile.getPath());
   }

   public void LogoMenu(View var1) {
      int var2 = var1.getId();
      if (var2 != 2131099851) {
         if (var2 == 2131099857 && !this.SaveMode(1)) {
            return;
         }
      } else if (!this.SaveMode(0)) {
         return;
      }

      this.finish();
   }

   public void onBackPressed() {
      super.onBackPressed();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230725);
      this.mStorageManager = (StorageManager)this.getSystemService(StorageManager.class);
      this.mPath = (TextView)this.findViewById(2131099823);
      String var2 = SystemProperties.get("ro.board.platform");
      if (var2 != null && var2.startsWith("trinket")) {
         this.PATH = "/data/cache/logo/customer.png";
      }

      this.mPreimage = (ImageView)this.findViewById(2131099826);
      this.mLogomenuListView = (ListView)this.findViewById(2131099794);
      this.fileList = new ArrayList();
      this.mLogomenuAdapter = new ListfileAdapter(this, this, this.fileList);
      this.mLogomenuListView.setAdapter(this.mLogomenuAdapter);
      this.rootFile = new File("/mnt");
      this.showDir(this.rootFile);
      this.mLogomenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener(this) {
         final ChooseLogoMenu this$0;

         {
            this.this$0 = var1;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            String var7 = (String)((HashMap)this.this$0.fileList.get(var3)).get("path");
            File var6 = new File(var7);
            if (var3 == this.this$0.mLogomenuAdapter.getSelectedItem()) {
               if (var7.equals(this.this$0.rootFile.getPath())) {
                  if (!var7.equals("/")) {
                     this.this$0.showDir(var6.getParentFile());
                     this.this$0.mLogomenuListView.scrollTo(0, 0);
                     this.this$0.mLogomenuListView.requestFocus();
                     this.this$0.mLogomenuListView.setSelection(var3);
                  }

                  return;
               }

               if (var6.isDirectory()) {
                  this.this$0.showDir(var6);
                  this.this$0.mLogomenuListView.requestFocus();
                  this.this$0.mLogomenuListView.setSelection(0);
               } else {
                  this.this$0.mLogomenuAdapter.notifyDataSetInvalidated();
               }
            } else {
               this.this$0.mLogomenuAdapter.setSelectedItem(var3);
               this.this$0.mLogomenuAdapter.notifyDataSetInvalidated();
            }

         }
      });
   }

   protected void onDestroy() {
      super.onDestroy();
   }

   protected void refreshVolume() {
      ArrayList var1 = new ArrayList();
      String var2 = this.getString(2131296374);
      var1.add(new Device(this, Environment.getExternalStorageDirectory().getPath(), var2, "flash", (String)null));
      Iterator var3 = this.mStorageManager.getVolumes().iterator();

      while(var3.hasNext()) {
         VolumeInfo var5 = (VolumeInfo)var3.next();
         if (var5.getType() == 0 && var5.isMountedReadable()) {
            DiskInfo var4 = var5.getDisk();
            String var7;
            if (var4.isSd()) {
               var7 = this.mStorageManager.getBestVolumeDescription(var5);
               var1.add(new Device(this, var5.getPath().getPath(), var7, "sd", var5.getId()));
            } else if (var4.isUsb()) {
               var7 = this.mStorageManager.getBestVolumeDescription(var5);
               var1.add(new Device(this, var5.getPath().getPath(), var7, "usb", var5.getId()));
            }
         }
      }

      Collections.sort(var1, sDescriptionComparator);
      List var6 = this.mDevice;
      if (var6 == null) {
         this.mDevice = var1;
      } else {
         var6.clear();
         this.mDevice.addAll(var1);
      }
   }

   public class Device {
      public String mKey;
      public String mPath;
      public String mTitle;
      public String mType;
      final ChooseLogoMenu this$0;

      public Device(ChooseLogoMenu var1, String var2, String var3, String var4, String var5) {
         this.this$0 = var1;
         this.mPath = var2;
         this.mTitle = var3;
         this.mType = var4;
         this.mKey = var5;
      }
   }

   class ListfileAdapter extends BaseAdapter {
      private Context mContext;
      private LayoutInflater mInflater;
      private ArrayList mList;
      private int selectItem;
      final ChooseLogoMenu this$0;

      public ListfileAdapter(ChooseLogoMenu var1, Context var2, ArrayList var3) {
         this.this$0 = var1;
         this.selectItem = -1;
         this.mContext = var2;
         this.mInflater = LayoutInflater.from(this.mContext);
         this.mList = var3;
      }

      public int getCount() {
         return this.mList.size();
      }

      public Object getItem(int var1) {
         return this.mList.get(var1);
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public int getSelectedItem() {
         return this.selectItem;
      }

      public View getView(int var1, View var2, ViewGroup var3) {
         ViewHolder var7;
         if (var2 == null) {
            var2 = this.mInflater.inflate(2131230748, (ViewGroup)null);
            var7 = new ViewHolder(this);
            var7.Image = (ImageView)var2.findViewById(2131099761);
            var7.ImageName = (TextView)var2.findViewById(2131099762);
            var2.setTag(var7);
         } else {
            var7 = (ViewHolder)var2.getTag();
         }

         String var5 = (String)((HashMap)this.mList.get(var1)).get("path");
         String var6 = (String)((HashMap)this.mList.get(var1)).get("type");
         boolean var4 = false;
         if (var6.equalsIgnoreCase("root")) {
            var7.Image.setImageResource(2131034126);
            var7.ImageName.setText("..");
         } else if (var6.equalsIgnoreCase("floder")) {
            var7.Image.setImageResource(2131034125);
            var7.ImageName.setText(var5.substring(var5.lastIndexOf(47) + 1));
         } else if (var6.equalsIgnoreCase("flash")) {
            var7.Image.setImageResource(2131034127);
            var7.ImageName.setText(this.mContext.getString(2131296374));
         } else if (var6.equalsIgnoreCase("GPS")) {
            var7.Image.setImageResource(2131034129);
            var7.ImageName.setText(2131296380);
         } else if (var6.equalsIgnoreCase("SD")) {
            var7.Image.setImageResource(2131034129);
            var7.ImageName.setText(2131296479);
         } else if (var6.startsWith("USB")) {
            var7.Image.setImageResource(2131034132);
            var7.ImageName.setText(this.mContext.getString(2131296489, new Object[]{var6}));
         } else if (var6.equalsIgnoreCase("image")) {
            var7.Image.setImageResource(2131034128);
            var7.ImageName.setText(var5.substring(var5.lastIndexOf(47) + 1));
            var4 = true;
         }

         if (this.selectItem == var1) {
            var2.setBackgroundResource(2131034134);
            if (var4) {
               this.this$0.mPreimage.setImageBitmap(this.this$0.getLocalBitmap(new File(var5)));
            } else {
               this.this$0.mPreimage.setImageResource(2131034135);
            }
         } else {
            var2.setBackgroundResource(0);
         }

         return var2;
      }

      public void setSelectedItem(int var1) {
         this.selectItem = var1;
      }

      private class ViewHolder {
         ImageView Image;
         TextView ImageName;
         final ListfileAdapter this$1;

         private ViewHolder(ListfileAdapter var1) {
            this.this$1 = var1;
         }

         // $FF: synthetic method
         ViewHolder(ListfileAdapter var1, Object var2) {
            this(var1);
         }
      }
   }
}
