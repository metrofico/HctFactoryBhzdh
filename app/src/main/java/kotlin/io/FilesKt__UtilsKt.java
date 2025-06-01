package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0007\u001a*\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0007\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"},
   d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/io/FilesKt"
)
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
   public FilesKt__UtilsKt() {
   }

   public static final boolean copyRecursively(File var0, File var1, boolean var2, Function2 var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      Intrinsics.checkNotNullParameter(var3, "onError");
      boolean var6 = var0.exists();
      boolean var5 = true;
      if (!var6) {
         if (var3.invoke(var0, new NoSuchFileException(var0, (File)null, "The source file doesn't exist.", 2, (DefaultConstructorMarker)null)) != OnErrorAction.TERMINATE) {
            var2 = var5;
         } else {
            var2 = false;
         }

         return var2;
      } else {
         boolean var10001;
         Iterator var19;
         try {
            FileTreeWalk var8 = FilesKt.walkTopDown(var0);
            Function2 var7 = new Function2(var3) {
               final Function2 $onError;

               {
                  this.$onError = var1;
               }

               public final void invoke(File var1, IOException var2) {
                  Intrinsics.checkNotNullParameter(var1, "f");
                  Intrinsics.checkNotNullParameter(var2, "e");
                  if (this.$onError.invoke(var1, var2) == OnErrorAction.TERMINATE) {
                     throw new TerminateException(var1);
                  }
               }
            };
            var19 = var8.onFail((Function2)var7).iterator();
         } catch (TerminateException var18) {
            var10001 = false;
            return false;
         }

         while(true) {
            File var20;
            try {
               while(true) {
                  if (!var19.hasNext()) {
                     return true;
                  }

                  var20 = (File)var19.next();
                  if (var20.exists()) {
                     break;
                  }

                  NoSuchFileException var9 = new NoSuchFileException(var20, (File)null, "The source file doesn't exist.", 2, (DefaultConstructorMarker)null);
                  if (var3.invoke(var20, var9) == OnErrorAction.TERMINATE) {
                     return false;
                  }
               }
            } catch (TerminateException var13) {
               var10001 = false;
               break;
            }

            File var22;
            label119: {
               try {
                  String var10 = FilesKt.toRelativeString(var20, var0);
                  var22 = new File(var1, var10);
                  if (!var22.exists() || var20.isDirectory() && var22.isDirectory()) {
                     break label119;
                  }
               } catch (TerminateException var17) {
                  var10001 = false;
                  break;
               }

               boolean var4;
               label96: {
                  label124: {
                     if (var2) {
                        label123: {
                           try {
                              if (var22.isDirectory()) {
                                 if (FilesKt.deleteRecursively(var22)) {
                                    break label124;
                                 }
                                 break label123;
                              }
                           } catch (TerminateException var16) {
                              var10001 = false;
                              break;
                           }

                           try {
                              if (var22.delete()) {
                                 break label124;
                              }
                           } catch (TerminateException var15) {
                              var10001 = false;
                              break;
                           }
                        }
                     }

                     var4 = true;
                     break label96;
                  }

                  var4 = false;
               }

               if (var4) {
                  try {
                     FileAlreadyExistsException var25 = new FileAlreadyExistsException(var20, var22, "The destination file already exists.");
                     if (var3.invoke(var22, var25) == OnErrorAction.TERMINATE) {
                        return false;
                     }
                     continue;
                  } catch (TerminateException var11) {
                     var10001 = false;
                     break;
                  }
               }
            }

            try {
               if (var20.isDirectory()) {
                  var22.mkdirs();
                  continue;
               }
            } catch (TerminateException var14) {
               var10001 = false;
               break;
            }

            Object var21;
            OnErrorAction var24;
            try {
               if (FilesKt.copyTo$default(var20, var22, var2, 0, 4, (Object)null).length() == var20.length()) {
                  continue;
               }

               IOException var23 = new IOException("Source file wasn't copied completely, length of destination file differs.");
               var21 = var3.invoke(var20, var23);
               var24 = OnErrorAction.TERMINATE;
            } catch (TerminateException var12) {
               var10001 = false;
               break;
            }

            if (var21 == var24) {
               return false;
            }
         }

         return false;
      }
   }

   // $FF: synthetic method
   public static boolean copyRecursively$default(File var0, File var1, boolean var2, Function2 var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = (Function2)null.INSTANCE;
      }

      return FilesKt.copyRecursively(var0, var1, var2, var3);
   }

   public static final File copyTo(File var0, File var1, boolean var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "target");
      if (var0.exists()) {
         if (var1.exists()) {
            if (!var2) {
               throw new FileAlreadyExistsException(var0, var1, "The destination file already exists.");
            }

            if (!var1.delete()) {
               throw new FileAlreadyExistsException(var0, var1, "Tried to overwrite the destination, but failed to delete it.");
            }
         }

         if (var0.isDirectory()) {
            if (!var1.mkdirs()) {
               throw new FileSystemException(var0, var1, "Failed to create target directory.");
            }
         } else {
            File var4 = var1.getParentFile();
            if (var4 != null) {
               var4.mkdirs();
            }

            Closeable var49 = (Closeable)(new FileInputStream(var0));

            label491: {
               Throwable var10000;
               label492: {
                  FileInputStream var5;
                  boolean var10001;
                  Closeable var52;
                  try {
                     var5 = (FileInputStream)var49;
                     FileOutputStream var51 = new FileOutputStream(var1);
                     var52 = (Closeable)var51;
                  } catch (Throwable var48) {
                     var10000 = var48;
                     var10001 = false;
                     break label492;
                  }

                  try {
                     FileOutputStream var6 = (FileOutputStream)var52;
                     ByteStreamsKt.copyTo((InputStream)var5, (OutputStream)var6, var3);
                  } catch (Throwable var47) {
                     Throwable var50 = var47;

                     try {
                        throw var50;
                     } finally {
                        try {
                           CloseableKt.closeFinally(var52, var50);
                        } catch (Throwable var44) {
                           var10000 = var44;
                           var10001 = false;
                           break label492;
                        }
                     }
                  }

                  label472:
                  try {
                     CloseableKt.closeFinally(var52, (Throwable)null);
                     break label491;
                  } catch (Throwable var46) {
                     var10000 = var46;
                     var10001 = false;
                     break label472;
                  }
               }

               Throwable var53 = var10000;

               try {
                  throw var53;
               } finally {
                  CloseableKt.closeFinally(var49, var53);
               }
            }

            CloseableKt.closeFinally(var49, (Throwable)null);
         }

         return var1;
      } else {
         throw new NoSuchFileException(var0, (File)null, "The source file doesn't exist.", 2, (DefaultConstructorMarker)null);
      }
   }

   // $FF: synthetic method
   public static File copyTo$default(File var0, File var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 8192;
      }

      return FilesKt.copyTo(var0, var1, var2, var3);
   }

   @Deprecated(
      message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead."
   )
   public static final File createTempDir(String var0, String var1, File var2) {
      Intrinsics.checkNotNullParameter(var0, "prefix");
      File var3 = File.createTempFile(var0, var1, var2);
      var3.delete();
      if (var3.mkdir()) {
         Intrinsics.checkNotNullExpressionValue(var3, "dir");
         return var3;
      } else {
         throw new IOException("Unable to create temporary directory " + var3 + '.');
      }
   }

   // $FF: synthetic method
   public static File createTempDir$default(String var0, String var1, File var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = "tmp";
      }

      if ((var3 & 2) != 0) {
         var1 = null;
      }

      if ((var3 & 4) != 0) {
         var2 = null;
      }

      return FilesKt.createTempDir(var0, var1, var2);
   }

   @Deprecated(
      message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile."
   )
   public static final File createTempFile(String var0, String var1, File var2) {
      Intrinsics.checkNotNullParameter(var0, "prefix");
      File var3 = File.createTempFile(var0, var1, var2);
      Intrinsics.checkNotNullExpressionValue(var3, "createTempFile(prefix, suffix, directory)");
      return var3;
   }

   // $FF: synthetic method
   public static File createTempFile$default(String var0, String var1, File var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var0 = "tmp";
      }

      if ((var3 & 2) != 0) {
         var1 = null;
      }

      if ((var3 & 4) != 0) {
         var2 = null;
      }

      return FilesKt.createTempFile(var0, var1, var2);
   }

   public static final boolean deleteRecursively(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = ((Sequence)FilesKt.walkBottomUp(var0)).iterator();

      label24:
      while(true) {
         boolean var1;
         for(var1 = true; var2.hasNext(); var1 = false) {
            var0 = (File)var2.next();
            if ((var0.delete() || !var0.exists()) && var1) {
               continue label24;
            }
         }

         return var1;
      }
   }

   public static final boolean endsWith(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      FilePathComponents var4 = FilesKt.toComponents(var0);
      FilePathComponents var5 = FilesKt.toComponents(var1);
      if (var5.isRooted()) {
         return Intrinsics.areEqual((Object)var0, (Object)var1);
      } else {
         int var2 = var4.getSize() - var5.getSize();
         boolean var3;
         if (var2 < 0) {
            var3 = false;
         } else {
            var3 = var4.getSegments().subList(var2, var4.getSize()).equals(var5.getSegments());
         }

         return var3;
      }
   }

   public static final boolean endsWith(File var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      return FilesKt.endsWith(var0, new File(var1));
   }

   public static final String getExtension(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      String var1 = var0.getName();
      Intrinsics.checkNotNullExpressionValue(var1, "name");
      return StringsKt.substringAfterLast(var1, '.', "");
   }

   public static final String getInvariantSeparatorsPath(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      String var1;
      if (File.separatorChar != '/') {
         var1 = var0.getPath();
         Intrinsics.checkNotNullExpressionValue(var1, "path");
         var1 = StringsKt.replace$default(var1, File.separatorChar, '/', false, 4, (Object)null);
      } else {
         var1 = var0.getPath();
         Intrinsics.checkNotNullExpressionValue(var1, "path");
      }

      return var1;
   }

   public static final String getNameWithoutExtension(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      String var1 = var0.getName();
      Intrinsics.checkNotNullExpressionValue(var1, "name");
      return StringsKt.substringBeforeLast$default(var1, ".", (String)null, 2, (Object)null);
   }

   public static final File normalize(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      FilePathComponents var1 = FilesKt.toComponents(var0);
      var0 = var1.getRoot();
      Iterable var2 = (Iterable)normalize$FilesKt__UtilsKt(var1.getSegments());
      String var3 = File.separator;
      Intrinsics.checkNotNullExpressionValue(var3, "separator");
      return FilesKt.resolve(var0, CollectionsKt.joinToString$default(var2, (CharSequence)var3, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null));
   }

   private static final List normalize$FilesKt__UtilsKt(List var0) {
      List var1 = (List)(new ArrayList(var0.size()));
      Iterator var4 = var0.iterator();

      while(true) {
         while(true) {
            while(true) {
               File var2;
               String var3;
               do {
                  if (!var4.hasNext()) {
                     return var1;
                  }

                  var2 = (File)var4.next();
                  var3 = var2.getName();
               } while(Intrinsics.areEqual((Object)var3, (Object)"."));

               if (Intrinsics.areEqual((Object)var3, (Object)"..")) {
                  if (!var1.isEmpty() && !Intrinsics.areEqual((Object)((File)CollectionsKt.last(var1)).getName(), (Object)"..")) {
                     var1.remove(var1.size() - 1);
                  } else {
                     var1.add(var2);
                  }
               } else {
                  var1.add(var2);
               }
            }
         }
      }
   }

   private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents var0) {
      return new FilePathComponents(var0.getRoot(), normalize$FilesKt__UtilsKt(var0.getSegments()));
   }

   public static final File relativeTo(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");
      return new File(FilesKt.toRelativeString(var0, var1));
   }

   public static final File relativeToOrNull(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");
      String var2 = toRelativeStringOrNull$FilesKt__UtilsKt(var0, var1);
      if (var2 != null) {
         var0 = new File(var2);
      } else {
         var0 = null;
      }

      return var0;
   }

   public static final File relativeToOrSelf(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");
      String var2 = toRelativeStringOrNull$FilesKt__UtilsKt(var0, var1);
      if (var2 != null) {
         var0 = new File(var2);
      }

      return var0;
   }

   public static final File resolve(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "relative");
      if (FilesKt.isRooted(var1)) {
         return var1;
      } else {
         String var4 = var0.toString();
         Intrinsics.checkNotNullExpressionValue(var4, "this.toString()");
         CharSequence var3 = (CharSequence)var4;
         boolean var2;
         if (var3.length() == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (!var2 && !StringsKt.endsWith$default(var3, File.separatorChar, false, 2, (Object)null)) {
            var0 = new File(var4 + File.separatorChar + var1);
         } else {
            var0 = new File(var4 + var1);
         }

         return var0;
      }
   }

   public static final File resolve(File var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "relative");
      return FilesKt.resolve(var0, new File(var1));
   }

   public static final File resolveSibling(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "relative");
      FilePathComponents var2 = FilesKt.toComponents(var0);
      if (var2.getSize() == 0) {
         var0 = new File("..");
      } else {
         var0 = var2.subPath(0, var2.getSize() - 1);
      }

      return FilesKt.resolve(FilesKt.resolve(var2.getRoot(), var0), var1);
   }

   public static final File resolveSibling(File var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "relative");
      return FilesKt.resolveSibling(var0, new File(var1));
   }

   public static final boolean startsWith(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      FilePathComponents var4 = FilesKt.toComponents(var0);
      FilePathComponents var5 = FilesKt.toComponents(var1);
      boolean var3 = Intrinsics.areEqual((Object)var4.getRoot(), (Object)var5.getRoot());
      boolean var2 = false;
      if (!var3) {
         return false;
      } else {
         if (var4.getSize() >= var5.getSize()) {
            var2 = var4.getSegments().subList(0, var5.getSize()).equals(var5.getSegments());
         }

         return var2;
      }
   }

   public static final boolean startsWith(File var0, String var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "other");
      return FilesKt.startsWith(var0, new File(var1));
   }

   public static final String toRelativeString(File var0, File var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "base");
      String var2 = toRelativeStringOrNull$FilesKt__UtilsKt(var0, var1);
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("this and base files have different roots: " + var0 + " and " + var1 + '.');
      }
   }

   private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File var0, File var1) {
      FilePathComponents var6 = normalize$FilesKt__UtilsKt(FilesKt.toComponents(var0));
      FilePathComponents var9 = normalize$FilesKt__UtilsKt(FilesKt.toComponents(var1));
      if (!Intrinsics.areEqual((Object)var6.getRoot(), (Object)var9.getRoot())) {
         return null;
      } else {
         int var4 = var9.getSize();
         int var5 = var6.getSize();
         int var2 = 0;

         int var3;
         for(var3 = Math.min(var5, var4); var2 < var3 && Intrinsics.areEqual(var6.getSegments().get(var2), var9.getSegments().get(var2)); ++var2) {
         }

         StringBuilder var8 = new StringBuilder();
         var3 = var4 - 1;
         if (var2 <= var3) {
            while(true) {
               if (Intrinsics.areEqual((Object)((File)var9.getSegments().get(var3)).getName(), (Object)"..")) {
                  return null;
               }

               var8.append("..");
               if (var3 != var2) {
                  var8.append(File.separatorChar);
               }

               if (var3 == var2) {
                  break;
               }

               --var3;
            }
         }

         if (var2 < var5) {
            if (var2 < var4) {
               var8.append(File.separatorChar);
            }

            Iterable var7 = (Iterable)CollectionsKt.drop((Iterable)var6.getSegments(), var2);
            Appendable var11 = (Appendable)var8;
            String var10 = File.separator;
            Intrinsics.checkNotNullExpressionValue(var10, "separator");
            CollectionsKt.joinTo$default(var7, var11, (CharSequence)var10, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 124, (Object)null);
         }

         return var8.toString();
      }
   }
}
