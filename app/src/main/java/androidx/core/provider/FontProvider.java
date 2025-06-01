package androidx.core.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Build.VERSION;
import androidx.core.content.res.FontResourcesParserCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FontProvider {
   private static final Comparator sByteArrayComparator = new Comparator() {
      public int compare(byte[] var1, byte[] var2) {
         if (var1.length != var2.length) {
            return var1.length - var2.length;
         } else {
            for(int var3 = 0; var3 < var1.length; ++var3) {
               byte var5 = var1[var3];
               byte var4 = var2[var3];
               if (var5 != var4) {
                  return var5 - var4;
               }
            }

            return 0;
         }
      }
   };

   private FontProvider() {
   }

   private static List convertToByteArrayList(Signature[] var0) {
      ArrayList var2 = new ArrayList();

      for(int var1 = 0; var1 < var0.length; ++var1) {
         var2.add(var0[var1].toByteArray());
      }

      return var2;
   }

   private static boolean equalsByteArrayList(List var0, List var1) {
      if (var0.size() != var1.size()) {
         return false;
      } else {
         for(int var2 = 0; var2 < var0.size(); ++var2) {
            if (!Arrays.equals((byte[])var0.get(var2), (byte[])var1.get(var2))) {
               return false;
            }
         }

         return true;
      }
   }

   private static List getCertificates(FontRequest var0, Resources var1) {
      return var0.getCertificates() != null ? var0.getCertificates() : FontResourcesParserCompat.readCerts(var1, var0.getCertificatesArrayResId());
   }

   static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context var0, FontRequest var1, CancellationSignal var2) throws PackageManager.NameNotFoundException {
      ProviderInfo var3 = getProvider(var0.getPackageManager(), var1, var0.getResources());
      return var3 == null ? FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[])null) : FontsContractCompat.FontFamilyResult.create(0, query(var0, var1, var3.authority, var2));
   }

   static ProviderInfo getProvider(PackageManager var0, FontRequest var1, Resources var2) throws PackageManager.NameNotFoundException {
      String var5 = var1.getProviderAuthority();
      int var3 = 0;
      ProviderInfo var4 = var0.resolveContentProvider(var5, 0);
      if (var4 != null) {
         if (var4.packageName.equals(var1.getProviderPackage())) {
            List var6 = convertToByteArrayList(var0.getPackageInfo(var4.packageName, 64).signatures);
            Collections.sort(var6, sByteArrayComparator);

            for(List var7 = getCertificates(var1, var2); var3 < var7.size(); ++var3) {
               ArrayList var8 = new ArrayList((Collection)var7.get(var3));
               Collections.sort(var8, sByteArrayComparator);
               if (equalsByteArrayList(var6, var8)) {
                  return var4;
               }
            }

            return null;
         } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + var5 + ", but package was not " + var1.getProviderPackage());
         }
      } else {
         throw new PackageManager.NameNotFoundException("No package found for authority: " + var5);
      }
   }

   static FontsContractCompat.FontInfo[] query(Context var0, FontRequest var1, String var2, CancellationSignal var3) {
      ArrayList var14 = new ArrayList();
      Uri var16 = (new Uri.Builder()).scheme("content").authority(var2).build();
      Uri var17 = (new Uri.Builder()).scheme("content").authority(var2).appendPath("file").build();
      Object var15 = null;
      Cursor var485 = (Cursor)var15;

      Cursor var481;
      ArrayList var483;
      label4200: {
         ArrayList var486;
         label4204: {
            Throwable var10000;
            label4205: {
               boolean var10001;
               String[] var18;
               try {
                  var18 = new String[7];
               } catch (Throwable var480) {
                  var10000 = var480;
                  var10001 = false;
                  break label4205;
               }

               var18[0] = "_id";
               var18[1] = "file_id";
               var18[2] = "font_ttc_index";
               var18[3] = "font_variation_settings";
               var18[4] = "font_weight";
               var18[5] = "font_italic";
               var18[6] = "result_code";
               var485 = (Cursor)var15;

               label4206: {
                  label4207: {
                     try {
                        if (VERSION.SDK_INT <= 16) {
                           break label4207;
                        }
                     } catch (Throwable var479) {
                        var10000 = var479;
                        var10001 = false;
                        break label4205;
                     }

                     var485 = (Cursor)var15;

                     try {
                        var481 = var0.getContentResolver().query(var16, var18, "query = ?", new String[]{var1.getQuery()}, (String)null, var3);
                        break label4206;
                     } catch (Throwable var478) {
                        var10000 = var478;
                        var10001 = false;
                        break label4205;
                     }
                  }

                  var485 = (Cursor)var15;

                  try {
                     var481 = var0.getContentResolver().query(var16, var18, "query = ?", new String[]{var1.getQuery()}, (String)null);
                  } catch (Throwable var477) {
                     var10000 = var477;
                     var10001 = false;
                     break label4205;
                  }
               }

               var483 = var14;
               if (var481 == null) {
                  break label4200;
               }

               var483 = var14;
               var485 = var481;

               try {
                  if (var481.getCount() <= 0) {
                     break label4200;
                  }
               } catch (Throwable var476) {
                  var10000 = var476;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               int var9;
               try {
                  var9 = var481.getColumnIndex("result_code");
               } catch (Throwable var475) {
                  var10000 = var475;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               try {
                  var486 = new ArrayList;
               } catch (Throwable var474) {
                  var10000 = var474;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               try {
                  var486.<init>();
               } catch (Throwable var473) {
                  var10000 = var473;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               int var10;
               try {
                  var10 = var481.getColumnIndex("_id");
               } catch (Throwable var472) {
                  var10000 = var472;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               int var11;
               try {
                  var11 = var481.getColumnIndex("file_id");
               } catch (Throwable var471) {
                  var10000 = var471;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               int var7;
               try {
                  var7 = var481.getColumnIndex("font_ttc_index");
               } catch (Throwable var470) {
                  var10000 = var470;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               int var8;
               try {
                  var8 = var481.getColumnIndex("font_weight");
               } catch (Throwable var469) {
                  var10000 = var469;
                  var10001 = false;
                  break label4205;
               }

               var485 = var481;

               int var12;
               try {
                  var12 = var481.getColumnIndex("font_italic");
               } catch (Throwable var468) {
                  var10000 = var468;
                  var10001 = false;
                  break label4205;
               }

               while(true) {
                  var485 = var481;

                  try {
                     if (!var481.moveToNext()) {
                        break label4204;
                     }
                  } catch (Throwable var466) {
                     var10000 = var466;
                     var10001 = false;
                     break;
                  }

                  int var4;
                  if (var9 != -1) {
                     var485 = var481;

                     try {
                        var4 = var481.getInt(var9);
                     } catch (Throwable var465) {
                        var10000 = var465;
                        var10001 = false;
                        break;
                     }
                  } else {
                     var4 = 0;
                  }

                  int var5;
                  if (var7 != -1) {
                     var485 = var481;

                     try {
                        var5 = var481.getInt(var7);
                     } catch (Throwable var464) {
                        var10000 = var464;
                        var10001 = false;
                        break;
                     }
                  } else {
                     var5 = 0;
                  }

                  Uri var484;
                  if (var11 == -1) {
                     var485 = var481;

                     try {
                        var484 = ContentUris.withAppendedId(var16, var481.getLong(var10));
                     } catch (Throwable var463) {
                        var10000 = var463;
                        var10001 = false;
                        break;
                     }
                  } else {
                     var485 = var481;

                     try {
                        var484 = ContentUris.withAppendedId(var17, var481.getLong(var11));
                     } catch (Throwable var462) {
                        var10000 = var462;
                        var10001 = false;
                        break;
                     }
                  }

                  int var6;
                  if (var8 != -1) {
                     var485 = var481;

                     try {
                        var6 = var481.getInt(var8);
                     } catch (Throwable var461) {
                        var10000 = var461;
                        var10001 = false;
                        break;
                     }
                  } else {
                     var6 = 400;
                  }

                  boolean var13;
                  label4143: {
                     label4142: {
                        if (var12 != -1) {
                           var485 = var481;

                           try {
                              if (var481.getInt(var12) == 1) {
                                 break label4142;
                              }
                           } catch (Throwable var467) {
                              var10000 = var467;
                              var10001 = false;
                              break;
                           }
                        }

                        var13 = false;
                        break label4143;
                     }

                     var13 = true;
                  }

                  var485 = var481;

                  try {
                     var486.add(FontsContractCompat.FontInfo.create(var484, var5, var6, var13, var4));
                  } catch (Throwable var460) {
                     var10000 = var460;
                     var10001 = false;
                     break;
                  }
               }
            }

            Throwable var482 = var10000;
            if (var485 != null) {
               var485.close();
            }

            throw var482;
         }

         var483 = var486;
      }

      if (var481 != null) {
         var481.close();
      }

      return (FontsContractCompat.FontInfo[])var483.toArray(new FontsContractCompat.FontInfo[0]);
   }
}
