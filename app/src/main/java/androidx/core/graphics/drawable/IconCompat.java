package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.Preconditions;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
   private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25F;
   private static final int AMBIENT_SHADOW_ALPHA = 30;
   private static final float BLUR_FACTOR = 0.010416667F;
   static final PorterDuff.Mode DEFAULT_TINT_MODE;
   private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
   static final String EXTRA_INT1 = "int1";
   static final String EXTRA_INT2 = "int2";
   static final String EXTRA_OBJ = "obj";
   static final String EXTRA_STRING1 = "string1";
   static final String EXTRA_TINT_LIST = "tint_list";
   static final String EXTRA_TINT_MODE = "tint_mode";
   static final String EXTRA_TYPE = "type";
   private static final float ICON_DIAMETER_FACTOR = 0.9166667F;
   private static final int KEY_SHADOW_ALPHA = 61;
   private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334F;
   private static final String TAG = "IconCompat";
   public static final int TYPE_ADAPTIVE_BITMAP = 5;
   public static final int TYPE_BITMAP = 1;
   public static final int TYPE_DATA = 3;
   public static final int TYPE_RESOURCE = 2;
   public static final int TYPE_UNKNOWN = -1;
   public static final int TYPE_URI = 4;
   public static final int TYPE_URI_ADAPTIVE_BITMAP = 6;
   public byte[] mData;
   public int mInt1;
   public int mInt2;
   Object mObj1;
   public Parcelable mParcelable;
   public String mString1;
   public ColorStateList mTintList;
   PorterDuff.Mode mTintMode;
   public String mTintModeStr;
   public int mType;

   static {
      DEFAULT_TINT_MODE = Mode.SRC_IN;
   }

   public IconCompat() {
      this.mType = -1;
      this.mData = null;
      this.mParcelable = null;
      this.mInt1 = 0;
      this.mInt2 = 0;
      this.mTintList = null;
      this.mTintMode = DEFAULT_TINT_MODE;
      this.mTintModeStr = null;
   }

   private IconCompat(int var1) {
      this.mData = null;
      this.mParcelable = null;
      this.mInt1 = 0;
      this.mInt2 = 0;
      this.mTintList = null;
      this.mTintMode = DEFAULT_TINT_MODE;
      this.mTintModeStr = null;
      this.mType = var1;
   }

   public static IconCompat createFromBundle(Bundle var0) {
      int var1 = var0.getInt("type");
      IconCompat var2 = new IconCompat(var1);
      var2.mInt1 = var0.getInt("int1");
      var2.mInt2 = var0.getInt("int2");
      var2.mString1 = var0.getString("string1");
      if (var0.containsKey("tint_list")) {
         var2.mTintList = (ColorStateList)var0.getParcelable("tint_list");
      }

      if (var0.containsKey("tint_mode")) {
         var2.mTintMode = Mode.valueOf(var0.getString("tint_mode"));
      }

      switch (var1) {
         case -1:
         case 1:
         case 5:
            var2.mObj1 = var0.getParcelable("obj");
            break;
         case 0:
         default:
            Log.w("IconCompat", "Unknown type " + var1);
            return null;
         case 2:
         case 4:
         case 6:
            var2.mObj1 = var0.getString("obj");
            break;
         case 3:
            var2.mObj1 = var0.getByteArray("obj");
      }

      return var2;
   }

   public static IconCompat createFromIcon(Context var0, Icon var1) {
      Preconditions.checkNotNull(var1);
      int var2 = getType(var1);
      IconCompat var5;
      if (var2 != 2) {
         if (var2 != 4) {
            if (var2 != 6) {
               var5 = new IconCompat(-1);
               var5.mObj1 = var1;
               return var5;
            } else {
               return createWithAdaptiveBitmapContentUri(getUri(var1));
            }
         } else {
            return createWithContentUri(getUri(var1));
         }
      } else {
         String var3 = getResPackage(var1);

         try {
            var5 = createWithResource(getResources(var0, var3), var3, getResId(var1));
            return var5;
         } catch (Resources.NotFoundException var4) {
            throw new IllegalArgumentException("Icon resource cannot be found");
         }
      }
   }

   public static IconCompat createFromIcon(Icon var0) {
      Preconditions.checkNotNull(var0);
      int var1 = getType(var0);
      if (var1 != 2) {
         if (var1 != 4) {
            if (var1 != 6) {
               IconCompat var2 = new IconCompat(-1);
               var2.mObj1 = var0;
               return var2;
            } else {
               return createWithAdaptiveBitmapContentUri(getUri(var0));
            }
         } else {
            return createWithContentUri(getUri(var0));
         }
      } else {
         return createWithResource((Resources)null, getResPackage(var0), getResId(var0));
      }
   }

   public static IconCompat createFromIconOrNullIfZeroResId(Icon var0) {
      return getType(var0) == 2 && getResId(var0) == 0 ? null : createFromIcon(var0);
   }

   static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap var0, boolean var1) {
      int var6 = (int)((float)Math.min(var0.getWidth(), var0.getHeight()) * 0.6666667F);
      Bitmap var9 = Bitmap.createBitmap(var6, var6, Config.ARGB_8888);
      Canvas var10 = new Canvas(var9);
      Paint var11 = new Paint(3);
      float var2 = (float)var6;
      float var5 = 0.5F * var2;
      float var3 = 0.9166667F * var5;
      if (var1) {
         float var4 = 0.010416667F * var2;
         var11.setColor(0);
         var11.setShadowLayer(var4, 0.0F, var2 * 0.020833334F, 1023410176);
         var10.drawCircle(var5, var5, var3, var11);
         var11.setShadowLayer(var4, 0.0F, 0.0F, 503316480);
         var10.drawCircle(var5, var5, var3, var11);
         var11.clearShadowLayer();
      }

      var11.setColor(-16777216);
      BitmapShader var7 = new BitmapShader(var0, TileMode.CLAMP, TileMode.CLAMP);
      Matrix var8 = new Matrix();
      var8.setTranslate((float)(-(var0.getWidth() - var6) / 2), (float)(-(var0.getHeight() - var6) / 2));
      var7.setLocalMatrix(var8);
      var11.setShader(var7);
      var10.drawCircle(var5, var5, var3, var11);
      var10.setBitmap((Bitmap)null);
      return var9;
   }

   public static IconCompat createWithAdaptiveBitmap(Bitmap var0) {
      if (var0 != null) {
         IconCompat var1 = new IconCompat(5);
         var1.mObj1 = var0;
         return var1;
      } else {
         throw new IllegalArgumentException("Bitmap must not be null.");
      }
   }

   public static IconCompat createWithAdaptiveBitmapContentUri(Uri var0) {
      if (var0 != null) {
         return createWithAdaptiveBitmapContentUri(var0.toString());
      } else {
         throw new IllegalArgumentException("Uri must not be null.");
      }
   }

   public static IconCompat createWithAdaptiveBitmapContentUri(String var0) {
      if (var0 != null) {
         IconCompat var1 = new IconCompat(6);
         var1.mObj1 = var0;
         return var1;
      } else {
         throw new IllegalArgumentException("Uri must not be null.");
      }
   }

   public static IconCompat createWithBitmap(Bitmap var0) {
      if (var0 != null) {
         IconCompat var1 = new IconCompat(1);
         var1.mObj1 = var0;
         return var1;
      } else {
         throw new IllegalArgumentException("Bitmap must not be null.");
      }
   }

   public static IconCompat createWithContentUri(Uri var0) {
      if (var0 != null) {
         return createWithContentUri(var0.toString());
      } else {
         throw new IllegalArgumentException("Uri must not be null.");
      }
   }

   public static IconCompat createWithContentUri(String var0) {
      if (var0 != null) {
         IconCompat var1 = new IconCompat(4);
         var1.mObj1 = var0;
         return var1;
      } else {
         throw new IllegalArgumentException("Uri must not be null.");
      }
   }

   public static IconCompat createWithData(byte[] var0, int var1, int var2) {
      if (var0 != null) {
         IconCompat var3 = new IconCompat(3);
         var3.mObj1 = var0;
         var3.mInt1 = var1;
         var3.mInt2 = var2;
         return var3;
      } else {
         throw new IllegalArgumentException("Data must not be null.");
      }
   }

   public static IconCompat createWithResource(Context var0, int var1) {
      if (var0 != null) {
         return createWithResource(var0.getResources(), var0.getPackageName(), var1);
      } else {
         throw new IllegalArgumentException("Context must not be null.");
      }
   }

   public static IconCompat createWithResource(Resources var0, String var1, int var2) {
      if (var1 != null) {
         if (var2 != 0) {
            IconCompat var3 = new IconCompat(2);
            var3.mInt1 = var2;
            if (var0 != null) {
               try {
                  var3.mObj1 = var0.getResourceName(var2);
               } catch (Resources.NotFoundException var4) {
                  throw new IllegalArgumentException("Icon resource cannot be found");
               }
            } else {
               var3.mObj1 = var1;
            }

            var3.mString1 = var1;
            return var3;
         } else {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
         }
      } else {
         throw new IllegalArgumentException("Package must not be null.");
      }
   }

   private static int getResId(Icon var0) {
      if (VERSION.SDK_INT >= 28) {
         return var0.getResId();
      } else {
         try {
            int var1 = (Integer)var0.getClass().getMethod("getResId").invoke(var0);
            return var1;
         } catch (IllegalAccessException var2) {
            Log.e("IconCompat", "Unable to get icon resource", var2);
            return 0;
         } catch (InvocationTargetException var3) {
            Log.e("IconCompat", "Unable to get icon resource", var3);
            return 0;
         } catch (NoSuchMethodException var4) {
            Log.e("IconCompat", "Unable to get icon resource", var4);
            return 0;
         }
      }
   }

   private static String getResPackage(Icon var0) {
      if (VERSION.SDK_INT >= 28) {
         return var0.getResPackage();
      } else {
         try {
            String var4 = (String)var0.getClass().getMethod("getResPackage").invoke(var0);
            return var4;
         } catch (IllegalAccessException var1) {
            Log.e("IconCompat", "Unable to get icon package", var1);
            return null;
         } catch (InvocationTargetException var2) {
            Log.e("IconCompat", "Unable to get icon package", var2);
            return null;
         } catch (NoSuchMethodException var3) {
            Log.e("IconCompat", "Unable to get icon package", var3);
            return null;
         }
      }
   }

   private static Resources getResources(Context var0, String var1) {
      if ("android".equals(var1)) {
         return Resources.getSystem();
      } else {
         PackageManager var5 = var0.getPackageManager();

         PackageManager.NameNotFoundException var10000;
         label31: {
            boolean var10001;
            ApplicationInfo var2;
            try {
               var2 = var5.getApplicationInfo(var1, 8192);
            } catch (PackageManager.NameNotFoundException var4) {
               var10000 = var4;
               var10001 = false;
               break label31;
            }

            if (var2 == null) {
               return null;
            }

            try {
               Resources var7 = var5.getResourcesForApplication(var2);
               return var7;
            } catch (PackageManager.NameNotFoundException var3) {
               var10000 = var3;
               var10001 = false;
            }
         }

         PackageManager.NameNotFoundException var6 = var10000;
         Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", var1), var6);
         return null;
      }
   }

   private static int getType(Icon var0) {
      if (VERSION.SDK_INT >= 28) {
         return var0.getType();
      } else {
         try {
            int var1 = (Integer)var0.getClass().getMethod("getType").invoke(var0);
            return var1;
         } catch (IllegalAccessException var3) {
            Log.e("IconCompat", "Unable to get icon type " + var0, var3);
            return -1;
         } catch (InvocationTargetException var4) {
            Log.e("IconCompat", "Unable to get icon type " + var0, var4);
            return -1;
         } catch (NoSuchMethodException var5) {
            Log.e("IconCompat", "Unable to get icon type " + var0, var5);
            return -1;
         }
      }
   }

   private static Uri getUri(Icon var0) {
      if (VERSION.SDK_INT >= 28) {
         return var0.getUri();
      } else {
         try {
            Uri var4 = (Uri)var0.getClass().getMethod("getUri").invoke(var0);
            return var4;
         } catch (IllegalAccessException var1) {
            Log.e("IconCompat", "Unable to get icon uri", var1);
            return null;
         } catch (InvocationTargetException var2) {
            Log.e("IconCompat", "Unable to get icon uri", var2);
            return null;
         } catch (NoSuchMethodException var3) {
            Log.e("IconCompat", "Unable to get icon uri", var3);
            return null;
         }
      }
   }

   private Drawable loadDrawableInner(Context var1) {
      InputStream var2;
      switch (this.mType) {
         case 1:
            return new BitmapDrawable(var1.getResources(), (Bitmap)this.mObj1);
         case 2:
            String var3 = this.getResPackage();
            String var6 = var3;
            if (TextUtils.isEmpty(var3)) {
               var6 = var1.getPackageName();
            }

            Resources var7 = getResources(var1, var6);

            try {
               Drawable var5 = ResourcesCompat.getDrawable(var7, this.mInt1, var1.getTheme());
               return var5;
            } catch (RuntimeException var4) {
               Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", this.mInt1, this.mObj1), var4);
               break;
            }
         case 3:
            return new BitmapDrawable(var1.getResources(), BitmapFactory.decodeByteArray((byte[])this.mObj1, this.mInt1, this.mInt2));
         case 4:
            var2 = this.getUriInputStream(var1);
            if (var2 != null) {
               return new BitmapDrawable(var1.getResources(), BitmapFactory.decodeStream(var2));
            }
            break;
         case 5:
            return new BitmapDrawable(var1.getResources(), createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
         case 6:
            var2 = this.getUriInputStream(var1);
            if (var2 != null) {
               if (VERSION.SDK_INT >= 26) {
                  return new AdaptiveIconDrawable((Drawable)null, new BitmapDrawable(var1.getResources(), BitmapFactory.decodeStream(var2)));
               }

               return new BitmapDrawable(var1.getResources(), createLegacyIconFromAdaptiveIcon(BitmapFactory.decodeStream(var2), false));
            }
      }

      return null;
   }

   private static String typeToString(int var0) {
      switch (var0) {
         case 1:
            return "BITMAP";
         case 2:
            return "RESOURCE";
         case 3:
            return "DATA";
         case 4:
            return "URI";
         case 5:
            return "BITMAP_MASKABLE";
         case 6:
            return "URI_MASKABLE";
         default:
            return "UNKNOWN";
      }
   }

   public void addToShortcutIntent(Intent var1, Drawable var2, Context var3) {
      this.checkResource(var3);
      int var4 = this.mType;
      Bitmap var15;
      if (var4 == 1) {
         Bitmap var16 = (Bitmap)this.mObj1;
         var15 = var16;
         if (var2 != null) {
            var15 = var16.copy(var16.getConfig(), true);
         }
      } else if (var4 != 2) {
         if (var4 != 5) {
            throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
         }

         var15 = createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, true);
      } else {
         label80: {
            PackageManager.NameNotFoundException var10000;
            label70: {
               boolean var10001;
               try {
                  var3 = var3.createPackageContext(this.getResPackage(), 0);
               } catch (PackageManager.NameNotFoundException var13) {
                  var10000 = var13;
                  var10001 = false;
                  break label70;
               }

               if (var2 == null) {
                  try {
                     var1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(var3, this.mInt1));
                     return;
                  } catch (PackageManager.NameNotFoundException var8) {
                     var10000 = var8;
                     var10001 = false;
                  }
               } else {
                  label66: {
                     Drawable var6;
                     label65: {
                        label82: {
                           label62:
                           try {
                              var6 = ContextCompat.getDrawable(var3, this.mInt1);
                              if (var6.getIntrinsicWidth() > 0 && var6.getIntrinsicHeight() > 0) {
                                 break label62;
                              }
                              break label82;
                           } catch (PackageManager.NameNotFoundException var12) {
                              var10000 = var12;
                              var10001 = false;
                              break label66;
                           }

                           try {
                              var15 = Bitmap.createBitmap(var6.getIntrinsicWidth(), var6.getIntrinsicHeight(), Config.ARGB_8888);
                              break label65;
                           } catch (PackageManager.NameNotFoundException var11) {
                              var10000 = var11;
                              var10001 = false;
                              break label66;
                           }
                        }

                        try {
                           var4 = ((ActivityManager)var3.getSystemService("activity")).getLauncherLargeIconSize();
                           var15 = Bitmap.createBitmap(var4, var4, Config.ARGB_8888);
                        } catch (PackageManager.NameNotFoundException var10) {
                           var10000 = var10;
                           var10001 = false;
                           break label66;
                        }
                     }

                     try {
                        var6.setBounds(0, 0, var15.getWidth(), var15.getHeight());
                        Canvas var7 = new Canvas(var15);
                        var6.draw(var7);
                        break label80;
                     } catch (PackageManager.NameNotFoundException var9) {
                        var10000 = var9;
                        var10001 = false;
                     }
                  }
               }
            }

            PackageManager.NameNotFoundException var14 = var10000;
            throw new IllegalArgumentException("Can't find package " + this.mObj1, var14);
         }
      }

      if (var2 != null) {
         var4 = var15.getWidth();
         int var5 = var15.getHeight();
         var2.setBounds(var4 / 2, var5 / 2, var4, var5);
         var2.draw(new Canvas(var15));
      }

      var1.putExtra("android.intent.extra.shortcut.ICON", var15);
   }

   public void checkResource(Context var1) {
      if (this.mType == 2) {
         Object var3 = this.mObj1;
         if (var3 != null) {
            String var8 = (String)var3;
            if (!var8.contains(":")) {
               return;
            }

            String var5 = var8.split(":", -1)[1];
            String var4 = var5.split("/", -1)[0];
            var5 = var5.split("/", -1)[1];
            String var7 = var8.split(":", -1)[0];
            if ("0_resource_name_obfuscated".equals(var5)) {
               Log.i("IconCompat", "Found obfuscated resource, not trying to update resource id for it");
               return;
            }

            String var6 = this.getResPackage();
            int var2 = getResources(var1, var6).getIdentifier(var5, var4, var7);
            if (this.mInt1 != var2) {
               Log.i("IconCompat", "Id has changed for " + var6 + " " + var8);
               this.mInt1 = var2;
            }
         }
      }

   }

   public Bitmap getBitmap() {
      if (this.mType == -1 && VERSION.SDK_INT >= 23) {
         Object var2 = this.mObj1;
         return var2 instanceof Bitmap ? (Bitmap)var2 : null;
      } else {
         int var1 = this.mType;
         if (var1 == 1) {
            return (Bitmap)this.mObj1;
         } else if (var1 == 5) {
            return createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, true);
         } else {
            throw new IllegalStateException("called getBitmap() on " + this);
         }
      }
   }

   public int getResId() {
      if (this.mType == -1 && VERSION.SDK_INT >= 23) {
         return getResId((Icon)this.mObj1);
      } else if (this.mType == 2) {
         return this.mInt1;
      } else {
         throw new IllegalStateException("called getResId() on " + this);
      }
   }

   public String getResPackage() {
      if (this.mType == -1 && VERSION.SDK_INT >= 23) {
         return getResPackage((Icon)this.mObj1);
      } else if (this.mType == 2) {
         return TextUtils.isEmpty(this.mString1) ? ((String)this.mObj1).split(":", -1)[0] : this.mString1;
      } else {
         throw new IllegalStateException("called getResPackage() on " + this);
      }
   }

   public int getType() {
      return this.mType == -1 && VERSION.SDK_INT >= 23 ? getType((Icon)this.mObj1) : this.mType;
   }

   public Uri getUri() {
      if (this.mType == -1 && VERSION.SDK_INT >= 23) {
         return getUri((Icon)this.mObj1);
      } else {
         int var1 = this.mType;
         if (var1 != 4 && var1 != 6) {
            throw new IllegalStateException("called getUri() on " + this);
         } else {
            return Uri.parse((String)this.mObj1);
         }
      }
   }

   public InputStream getUriInputStream(Context var1) {
      Uri var2 = this.getUri();
      String var3 = var2.getScheme();
      if (!"content".equals(var3) && !"file".equals(var3)) {
         try {
            File var7 = new File((String)this.mObj1);
            FileInputStream var8 = new FileInputStream(var7);
            return var8;
         } catch (FileNotFoundException var4) {
            Log.w("IconCompat", "Unable to load image from path: " + var2, var4);
         }
      } else {
         try {
            InputStream var6 = var1.getContentResolver().openInputStream(var2);
            return var6;
         } catch (Exception var5) {
            Log.w("IconCompat", "Unable to load image from URI: " + var2, var5);
         }
      }

      return null;
   }

   public Drawable loadDrawable(Context var1) {
      this.checkResource(var1);
      if (VERSION.SDK_INT >= 23) {
         return this.toIcon(var1).loadDrawable(var1);
      } else {
         Drawable var2 = this.loadDrawableInner(var1);
         if (var2 != null && (this.mTintList != null || this.mTintMode != DEFAULT_TINT_MODE)) {
            var2.mutate();
            DrawableCompat.setTintList(var2, this.mTintList);
            DrawableCompat.setTintMode(var2, this.mTintMode);
         }

         return var2;
      }
   }

   public void onPostParceling() {
      this.mTintMode = Mode.valueOf(this.mTintModeStr);
      Parcelable var3;
      switch (this.mType) {
         case -1:
            var3 = this.mParcelable;
            if (var3 == null) {
               throw new IllegalArgumentException("Invalid icon");
            }

            this.mObj1 = var3;
         case 0:
         default:
            break;
         case 1:
         case 5:
            var3 = this.mParcelable;
            if (var3 != null) {
               this.mObj1 = var3;
            } else {
               byte[] var4 = this.mData;
               this.mObj1 = var4;
               this.mType = 3;
               this.mInt1 = 0;
               this.mInt2 = var4.length;
            }
            break;
         case 2:
         case 4:
         case 6:
            String var1 = new String(this.mData, Charset.forName("UTF-16"));
            this.mObj1 = var1;
            if (this.mType == 2 && this.mString1 == null) {
               String var2 = (String)var1;
               this.mString1 = var1.split(":", -1)[0];
            }
            break;
         case 3:
            this.mObj1 = this.mData;
      }

   }

   public void onPreParceling(boolean var1) {
      this.mTintModeStr = this.mTintMode.name();
      switch (this.mType) {
         case -1:
            if (var1) {
               throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
            }

            this.mParcelable = (Parcelable)this.mObj1;
         case 0:
         default:
            break;
         case 1:
         case 5:
            if (var1) {
               Bitmap var3 = (Bitmap)this.mObj1;
               ByteArrayOutputStream var2 = new ByteArrayOutputStream();
               var3.compress(CompressFormat.PNG, 90, var2);
               this.mData = var2.toByteArray();
            } else {
               this.mParcelable = (Parcelable)this.mObj1;
            }
            break;
         case 2:
            this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
            break;
         case 3:
            this.mData = (byte[])this.mObj1;
            break;
         case 4:
         case 6:
            this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
      }

   }

   public IconCompat setTint(int var1) {
      return this.setTintList(ColorStateList.valueOf(var1));
   }

   public IconCompat setTintList(ColorStateList var1) {
      this.mTintList = var1;
      return this;
   }

   public IconCompat setTintMode(PorterDuff.Mode var1) {
      this.mTintMode = var1;
      return this;
   }

   public Bundle toBundle() {
      Bundle var1 = new Bundle();
      switch (this.mType) {
         case -1:
            var1.putParcelable("obj", (Parcelable)this.mObj1);
            break;
         case 0:
         default:
            throw new IllegalArgumentException("Invalid icon");
         case 1:
         case 5:
            var1.putParcelable("obj", (Bitmap)this.mObj1);
            break;
         case 2:
         case 4:
         case 6:
            var1.putString("obj", (String)this.mObj1);
            break;
         case 3:
            var1.putByteArray("obj", (byte[])this.mObj1);
      }

      var1.putInt("type", this.mType);
      var1.putInt("int1", this.mInt1);
      var1.putInt("int2", this.mInt2);
      var1.putString("string1", this.mString1);
      ColorStateList var2 = this.mTintList;
      if (var2 != null) {
         var1.putParcelable("tint_list", var2);
      }

      PorterDuff.Mode var3 = this.mTintMode;
      if (var3 != DEFAULT_TINT_MODE) {
         var1.putString("tint_mode", var3.name());
      }

      return var1;
   }

   @Deprecated
   public Icon toIcon() {
      return this.toIcon((Context)null);
   }

   public Icon toIcon(Context var1) {
      Icon var3;
      switch (this.mType) {
         case -1:
            return (Icon)this.mObj1;
         case 0:
         default:
            throw new IllegalArgumentException("Unknown type");
         case 1:
            var3 = Icon.createWithBitmap((Bitmap)this.mObj1);
            break;
         case 2:
            var3 = Icon.createWithResource(this.getResPackage(), this.mInt1);
            break;
         case 3:
            var3 = Icon.createWithData((byte[])this.mObj1, this.mInt1, this.mInt2);
            break;
         case 4:
            var3 = Icon.createWithContentUri((String)this.mObj1);
            break;
         case 5:
            if (VERSION.SDK_INT >= 26) {
               var3 = Icon.createWithAdaptiveBitmap((Bitmap)this.mObj1);
            } else {
               var3 = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap)this.mObj1, false));
            }
            break;
         case 6:
            if (VERSION.SDK_INT >= 30) {
               var3 = Icon.createWithAdaptiveBitmapContentUri(this.getUri());
            } else {
               if (var1 == null) {
                  throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + this.getUri());
               }

               InputStream var4 = this.getUriInputStream(var1);
               if (var4 == null) {
                  throw new IllegalStateException("Cannot load adaptive icon from uri: " + this.getUri());
               }

               if (VERSION.SDK_INT >= 26) {
                  var3 = Icon.createWithAdaptiveBitmap(BitmapFactory.decodeStream(var4));
               } else {
                  var3 = Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon(BitmapFactory.decodeStream(var4), false));
               }
            }
      }

      ColorStateList var2 = this.mTintList;
      if (var2 != null) {
         var3.setTintList(var2);
      }

      PorterDuff.Mode var5 = this.mTintMode;
      if (var5 != DEFAULT_TINT_MODE) {
         var3.setTintMode(var5);
      }

      return var3;
   }

   public String toString() {
      if (this.mType == -1) {
         return String.valueOf(this.mObj1);
      } else {
         StringBuilder var1 = (new StringBuilder("Icon(typ=")).append(typeToString(this.mType));
         switch (this.mType) {
            case 1:
            case 5:
               var1.append(" size=").append(((Bitmap)this.mObj1).getWidth()).append("x").append(((Bitmap)this.mObj1).getHeight());
               break;
            case 2:
               var1.append(" pkg=").append(this.mString1).append(" id=").append(String.format("0x%08x", this.getResId()));
               break;
            case 3:
               var1.append(" len=").append(this.mInt1);
               if (this.mInt2 != 0) {
                  var1.append(" off=").append(this.mInt2);
               }
               break;
            case 4:
            case 6:
               var1.append(" uri=").append(this.mObj1);
         }

         if (this.mTintList != null) {
            var1.append(" tint=");
            var1.append(this.mTintList);
         }

         if (this.mTintMode != DEFAULT_TINT_MODE) {
            var1.append(" mode=").append(this.mTintMode);
         }

         var1.append(")");
         return var1.toString();
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface IconType {
   }
}
