package androidx.core.content.pm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ShortcutXmlParser {
   private static final String ATTR_SHORTCUT_ID = "shortcutId";
   private static final Object GET_INSTANCE_LOCK = new Object();
   private static final String META_DATA_APP_SHORTCUTS = "android.app.shortcuts";
   private static final String TAG = "ShortcutXmlParser";
   private static final String TAG_SHORTCUT = "shortcut";
   private static volatile ArrayList sShortcutIds;

   private ShortcutXmlParser() {
   }

   private static String getAttributeValue(XmlPullParser var0, String var1) {
      String var3 = var0.getAttributeValue("http://schemas.android.com/apk/res/android", var1);
      String var2 = var3;
      if (var3 == null) {
         var2 = var0.getAttributeValue((String)null, var1);
      }

      return var2;
   }

   public static List getShortcutIds(Context var0) {
      if (sShortcutIds == null) {
         Object var1 = GET_INSTANCE_LOCK;
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label144: {
            try {
               if (sShortcutIds == null) {
                  ArrayList var2 = new ArrayList();
                  sShortcutIds = var2;
                  sShortcutIds.addAll(parseShortcutIds(var0));
               }
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label144;
            }

            label141:
            try {
               return sShortcutIds;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label141;
            }
         }

         while(true) {
            Throwable var15 = var10000;

            try {
               throw var15;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               continue;
            }
         }
      } else {
         return sShortcutIds;
      }
   }

   private static XmlResourceParser getXmlResourceParser(Context var0, ActivityInfo var1) {
      XmlResourceParser var2 = var1.loadXmlMetaData(var0.getPackageManager(), "android.app.shortcuts");
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("Failed to open android.app.shortcuts meta-data resource of " + var1.name);
      }
   }

   public static List parseShortcutIds(XmlPullParser var0) throws IOException, XmlPullParserException {
      ArrayList var3 = new ArrayList(1);

      while(true) {
         int var1 = var0.next();
         if (var1 == 1 || var1 == 3 && var0.getDepth() <= 0) {
            return var3;
         }

         int var2 = var0.getDepth();
         String var4 = var0.getName();
         if (var1 == 2 && var2 == 2 && "shortcut".equals(var4)) {
            var4 = getAttributeValue(var0, "shortcutId");
            if (var4 != null) {
               var3.add(var4);
            }
         }
      }
   }

   private static Set parseShortcutIds(Context var0) {
      HashSet var1 = new HashSet();
      Intent var2 = new Intent("android.intent.action.MAIN");
      var2.addCategory("android.intent.category.LAUNCHER");
      var2.setPackage(var0.getPackageName());
      List var31 = var0.getPackageManager().queryIntentActivities(var2, 128);
      if (var31 != null && var31.size() != 0) {
         Exception var10000;
         label210: {
            Iterator var32;
            boolean var10001;
            try {
               var32 = var31.iterator();
            } catch (Exception var28) {
               var10000 = var28;
               var10001 = false;
               break label210;
            }

            while(true) {
               ActivityInfo var3;
               Bundle var4;
               try {
                  if (!var32.hasNext()) {
                     return var1;
                  }

                  var3 = ((ResolveInfo)var32.next()).activityInfo;
                  var4 = var3.metaData;
               } catch (Exception var27) {
                  var10000 = var27;
                  var10001 = false;
                  break;
               }

               if (var4 != null) {
                  XmlResourceParser var34;
                  try {
                     if (!var4.containsKey("android.app.shortcuts")) {
                        continue;
                     }

                     var34 = getXmlResourceParser(var0, var3);
                  } catch (Exception var26) {
                     var10000 = var26;
                     var10001 = false;
                     break;
                  }

                  try {
                     var1.addAll(parseShortcutIds((XmlPullParser)var34));
                  } catch (Throwable var25) {
                     Throwable var29 = var25;
                     if (var34 != null) {
                        try {
                           var34.close();
                        } catch (Throwable var23) {
                           Throwable var33 = var23;

                           label186:
                           try {
                              var29.addSuppressed(var33);
                              break label186;
                           } catch (Exception var22) {
                              var10000 = var22;
                              var10001 = false;
                              break;
                           }
                        }
                     }

                     try {
                        throw var29;
                     } catch (Exception var21) {
                        var10000 = var21;
                        var10001 = false;
                        break;
                     }
                  }

                  if (var34 != null) {
                     try {
                        var34.close();
                     } catch (Exception var24) {
                        var10000 = var24;
                        var10001 = false;
                        break;
                     }
                  }
               }
            }
         }

         Exception var30 = var10000;
         Log.e("ShortcutXmlParser", "Failed to parse the Xml resource: ", var30);
      }

      return var1;
   }
}
