package kotlinx.coroutines.internal;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\u0004H\u0082\bJ1\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\bH\u0002¢\u0006\u0002\u0010\u0010J*\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012\"\u0004\b\u0000\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0000¢\u0006\u0002\b\u0014J/\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012\"\u0004\b\u0000\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J,\u0010\u001d\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e*\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\u001e0!H\u0082\b¢\u0006\u0002\u0010\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"},
   d2 = {"Lkotlinx/coroutines/internal/FastServiceLoader;", "", "()V", "PREFIX", "", "createInstanceOf", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "baseClass", "Ljava/lang/Class;", "serviceClass", "getProviderInstance", "S", "name", "loader", "Ljava/lang/ClassLoader;", "service", "(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Object;", "load", "", "loadMainDispatcherFactory", "loadMainDispatcherFactory$kotlinx_coroutines_core", "loadProviders", "loadProviders$kotlinx_coroutines_core", "parse", "url", "Ljava/net/URL;", "parseFile", "r", "Ljava/io/BufferedReader;", "use", "R", "Ljava/util/jar/JarFile;", "block", "Lkotlin/Function1;", "(Ljava/util/jar/JarFile;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class FastServiceLoader {
   public static final FastServiceLoader INSTANCE = new FastServiceLoader();
   private static final String PREFIX = "META-INF/services/";

   private FastServiceLoader() {
   }

   private final MainDispatcherFactory createInstanceOf(Class var1, String var2) {
      MainDispatcherFactory var4;
      try {
         var4 = (MainDispatcherFactory)var1.cast(Class.forName(var2, true, var1.getClassLoader()).getDeclaredConstructor().newInstance());
      } catch (ClassNotFoundException var3) {
         var4 = null;
      }

      return var4;
   }

   private final Object getProviderInstance(String var1, ClassLoader var2, Class var3) {
      Class var4 = Class.forName(var1, false, var2);
      if (var3.isAssignableFrom(var4)) {
         return var3.cast(var4.getDeclaredConstructor().newInstance());
      } else {
         throw (Throwable)(new IllegalArgumentException(("Expected service of class " + var3 + ", but found " + var4).toString()));
      }
   }

   private final List load(Class var1, ClassLoader var2) {
      boolean var5 = false;

      List var7;
      try {
         var5 = true;
         this.loadProviders$kotlinx_coroutines_core(var1, var2);
         var5 = false;
      } finally {
         if (var5) {
            var7 = CollectionsKt.toList((Iterable)ServiceLoader.load(var1, var2));
            return var7;
         }
      }

      return var7;
   }

   private final List parse(URL var1) {
      String var2 = var1.toString();
      Throwable var119;
      if (StringsKt.startsWith$default(var2, "jar", false, 2, (Object)null)) {
         String var117 = StringsKt.substringBefore$default(StringsKt.substringAfter$default(var2, "jar:file:", (String)null, 2, (Object)null), '!', (String)null, 2, (Object)null);
         var2 = StringsKt.substringAfter$default(var2, "!/", (String)null, 2, (Object)null);
         JarFile var118 = new JarFile(var117, false);
         Throwable var3 = (Throwable)null;

         List var125;
         label708: {
            Throwable var10000;
            label709: {
               boolean var10001;
               Closeable var123;
               try {
                  ZipEntry var4 = new ZipEntry(var2);
                  InputStreamReader var121 = new InputStreamReader(var118.getInputStream(var4), "UTF-8");
                  BufferedReader var5 = new BufferedReader((Reader)var121);
                  var123 = (Closeable)var5;
                  var3 = (Throwable)null;
               } catch (Throwable var115) {
                  var10000 = var115;
                  var10001 = false;
                  break label709;
               }

               try {
                  BufferedReader var124 = (BufferedReader)var123;
                  var125 = INSTANCE.parseFile(var124);
               } catch (Throwable var114) {
                  Throwable var126 = var114;

                  try {
                     throw var126;
                  } finally {
                     try {
                        CloseableKt.closeFinally(var123, var126);
                     } catch (Throwable var111) {
                        var10000 = var111;
                        var10001 = false;
                        break label709;
                     }
                  }
               }

               label694:
               try {
                  CloseableKt.closeFinally(var123, (Throwable)null);
                  break label708;
               } catch (Throwable var113) {
                  var10000 = var113;
                  var10001 = false;
                  break label694;
               }
            }

            var119 = var10000;

            try {
               throw var119;
            } finally {
               try {
                  var118.close();
               } catch (Throwable var106) {
                  ExceptionsKt.addSuppressed(var119, var106);
                  throw var119;
               }
            }
         }

         try {
            var118.close();
            return var125;
         } finally {
            ;
         }
      } else {
         Closeable var116 = (Closeable)(new BufferedReader((Reader)(new InputStreamReader(var1.openStream()))));
         var119 = (Throwable)null;

         List var122;
         try {
            BufferedReader var120 = (BufferedReader)var116;
            var122 = INSTANCE.parseFile(var120);
         } catch (Throwable var110) {
            var119 = var110;

            try {
               throw var119;
            } finally {
               CloseableKt.closeFinally(var116, var110);
            }
         }

         CloseableKt.closeFinally(var116, (Throwable)null);
         return var122;
      }
   }

   private final List parseFile(BufferedReader var1) {
      Set var6 = (Set)(new LinkedHashSet());

      while(true) {
         String var7 = var1.readLine();
         if (var7 == null) {
            return CollectionsKt.toList((Iterable)var6);
         }

         var7 = StringsKt.substringBefore$default(var7, "#", (String)null, 2, (Object)null);
         if (var7 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
         }

         var7 = StringsKt.trim((CharSequence)var7).toString();
         CharSequence var8 = (CharSequence)var7;
         boolean var5 = false;
         int var3 = 0;

         boolean var9;
         while(true) {
            if (var3 >= var8.length()) {
               var9 = true;
               break;
            }

            char var2 = var8.charAt(var3);
            boolean var4;
            if (var2 != '.' && !Character.isJavaIdentifierPart(var2)) {
               var4 = false;
            } else {
               var4 = true;
            }

            if (!var4) {
               var9 = false;
               break;
            }

            ++var3;
         }

         if (!var9) {
            throw (Throwable)(new IllegalArgumentException(("Illegal service provider class name: " + var7).toString()));
         }

         var9 = var5;
         if (var8.length() > 0) {
            var9 = true;
         }

         if (var9) {
            var6.add(var7);
         }
      }
   }

   private final Object use(JarFile var1, Function1 var2) {
      Throwable var3 = (Throwable)null;

      Object var25;
      try {
         var25 = var2.invoke(var1);
      } catch (Throwable var23) {
         Throwable var24 = var23;

         try {
            throw var24;
         } finally {
            InlineMarker.finallyStart(1);

            try {
               var1.close();
            } catch (Throwable var20) {
               ExceptionsKt.addSuppressed(var23, var20);
               throw var23;
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);

      try {
         var1.close();
      } finally {
         ;
      }

      InlineMarker.finallyEnd(1);
      return var25;
   }

   public final List loadMainDispatcherFactory$kotlinx_coroutines_core() {
      // $FF: Couldn't be decompiled
   }

   public final List loadProviders$kotlinx_coroutines_core(Class var1, ClassLoader var2) {
      ArrayList var3 = Collections.list(var2.getResources("META-INF/services/" + var1.getName()));
      Intrinsics.checkNotNullExpressionValue(var3, "java.util.Collections.list(this)");
      Iterable var4 = (Iterable)((List)var3);
      Collection var6 = (Collection)(new ArrayList());
      Iterator var8 = var4.iterator();

      while(var8.hasNext()) {
         URL var5 = (URL)var8.next();
         CollectionsKt.addAll(var6, (Iterable)INSTANCE.parse(var5));
      }

      Set var7 = CollectionsKt.toSet((Iterable)((List)var6));
      if (!(((Collection)var7).isEmpty() ^ true)) {
         throw (Throwable)(new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString()));
      } else {
         var4 = (Iterable)var7;
         var6 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var4, 10)));
         Iterator var10 = var4.iterator();

         while(var10.hasNext()) {
            String var9 = (String)var10.next();
            var6.add(INSTANCE.getProviderInstance(var9, var2, var1));
         }

         return (List)var6;
      }
   }
}
