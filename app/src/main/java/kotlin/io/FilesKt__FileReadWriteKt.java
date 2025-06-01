package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\u0087\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u001aB\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010+\u001a\u0012\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010.\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010/\u001a\u000200*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\u0087\b\u0082\u0002\u000f\n\u0005\b\u009920\u0001\n\u0006\b\u0011(,0\u0001¨\u00061"},
   d2 = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Requires newer compiler version to be inlined correctly.", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/io/FilesKt"
)
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
   public FilesKt__FileReadWriteKt() {
   }

   public static final void appendBytes(File var0, byte[] var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "array");
      Closeable var9 = (Closeable)(new FileOutputStream(var0, true));

      try {
         ((FileOutputStream)var9).write(var1);
         Unit var10 = Unit.INSTANCE;
      } catch (Throwable var8) {
         Throwable var2 = var8;

         try {
            throw var2;
         } finally {
            CloseableKt.closeFinally(var9, var8);
         }
      }

      CloseableKt.closeFinally(var9, (Throwable)null);
   }

   public static final void appendText(File var0, String var1, Charset var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "text");
      Intrinsics.checkNotNullParameter(var2, "charset");
      byte[] var3 = var1.getBytes(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      FilesKt.appendBytes(var0, var3);
   }

   // $FF: synthetic method
   public static void appendText$default(File var0, String var1, Charset var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      FilesKt.appendText(var0, var1, var2);
   }

   private static final BufferedReader bufferedReader(File var0, Charset var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Reader var3 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream(var0)), var1));
      BufferedReader var4;
      if (var3 instanceof BufferedReader) {
         var4 = (BufferedReader)var3;
      } else {
         var4 = new BufferedReader(var3, var2);
      }

      return var4;
   }

   // $FF: synthetic method
   static BufferedReader bufferedReader$default(File var0, Charset var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      if ((var3 & 2) != 0) {
         var2 = 8192;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Reader var5 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream(var0)), var1));
      BufferedReader var6;
      if (var5 instanceof BufferedReader) {
         var6 = (BufferedReader)var5;
      } else {
         var6 = new BufferedReader(var5, var2);
      }

      return var6;
   }

   private static final BufferedWriter bufferedWriter(File var0, Charset var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Writer var3 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream(var0)), var1));
      BufferedWriter var4;
      if (var3 instanceof BufferedWriter) {
         var4 = (BufferedWriter)var3;
      } else {
         var4 = new BufferedWriter(var3, var2);
      }

      return var4;
   }

   // $FF: synthetic method
   static BufferedWriter bufferedWriter$default(File var0, Charset var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      if ((var3 & 2) != 0) {
         var2 = 8192;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Writer var5 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream(var0)), var1));
      BufferedWriter var6;
      if (var5 instanceof BufferedWriter) {
         var6 = (BufferedWriter)var5;
      } else {
         var6 = new BufferedWriter(var5, var2);
      }

      return var6;
   }

   public static final void forEachBlock(File var0, int var1, Function2 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "action");
      byte[] var3 = new byte[RangesKt.coerceAtLeast(var1, 512)];
      Closeable var35 = (Closeable)(new FileInputStream(var0));

      label229: {
         Throwable var10000;
         label224: {
            FileInputStream var4;
            boolean var10001;
            try {
               var4 = (FileInputStream)var35;
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               break label224;
            }

            while(true) {
               try {
                  var1 = var4.read(var3);
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break;
               }

               if (var1 <= 0) {
                  try {
                     Unit var37 = Unit.INSTANCE;
                     break label229;
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  var2.invoke(var3, var1);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var36 = var10000;

         try {
            throw var36;
         } finally {
            CloseableKt.closeFinally(var35, var36);
         }
      }

      CloseableKt.closeFinally(var35, (Throwable)null);
   }

   public static final void forEachBlock(File var0, Function2 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      FilesKt.forEachBlock(var0, 4096, var1);
   }

   public static final void forEachLine(File var0, Charset var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "action");
      TextStreamsKt.forEachLine((Reader)(new BufferedReader((Reader)(new InputStreamReader((InputStream)(new FileInputStream(var0)), var1)))), var2);
   }

   // $FF: synthetic method
   public static void forEachLine$default(File var0, Charset var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      FilesKt.forEachLine(var0, var1, var2);
   }

   private static final FileInputStream inputStream(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new FileInputStream(var0);
   }

   private static final FileOutputStream outputStream(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new FileOutputStream(var0);
   }

   private static final PrintWriter printWriter(File var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Writer var2 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream(var0)), var1));
      BufferedWriter var3;
      if (var2 instanceof BufferedWriter) {
         var3 = (BufferedWriter)var2;
      } else {
         var3 = new BufferedWriter(var2, 8192);
      }

      return new PrintWriter((Writer)var3);
   }

   // $FF: synthetic method
   static PrintWriter printWriter$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Writer var4 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream(var0)), var1));
      BufferedWriter var5;
      if (var4 instanceof BufferedWriter) {
         var5 = (BufferedWriter)var4;
      } else {
         var5 = new BufferedWriter(var4, 8192);
      }

      return new PrintWriter((Writer)var5);
   }

   public static final byte[] readBytes(File var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Closeable var8 = (Closeable)(new FileInputStream(var0));

      Throwable var10000;
      label889: {
         long var5;
         FileInputStream var10;
         boolean var10001;
         try {
            var10 = (FileInputStream)var8;
            var5 = var0.length();
         } catch (Throwable var120) {
            var10000 = var120;
            var10001 = false;
            break label889;
         }

         StringBuilder var122;
         OutOfMemoryError var124;
         if (var5 > 2147483647L) {
            label856:
            try {
               var122 = new StringBuilder();
               var124 = new OutOfMemoryError(var122.append("File ").append(var0).append(" is too big (").append(var5).append(" bytes) to fit in memory.").toString());
               throw var124;
            } catch (Throwable var113) {
               var10000 = var113;
               var10001 = false;
               break label856;
            }
         } else {
            label892: {
               int var3 = (int)var5;

               byte[] var7;
               try {
                  var7 = new byte[var3];
               } catch (Throwable var119) {
                  var10000 = var119;
                  var10001 = false;
                  break label892;
               }

               int var2 = var3;

               int var1;
               int var4;
               for(var1 = 0; var2 > 0; var1 += var4) {
                  try {
                     var4 = var10.read(var7, var1, var2);
                  } catch (Throwable var118) {
                     var10000 = var118;
                     var10001 = false;
                     break label892;
                  }

                  if (var4 < 0) {
                     break;
                  }

                  var2 -= var4;
               }

               byte[] var121;
               if (var2 > 0) {
                  try {
                     var121 = Arrays.copyOf(var7, var1);
                     Intrinsics.checkNotNullExpressionValue(var121, "copyOf(this, newSize)");
                  } catch (Throwable var115) {
                     var10000 = var115;
                     var10001 = false;
                     break label892;
                  }
               } else {
                  try {
                     var1 = var10.read();
                  } catch (Throwable var117) {
                     var10000 = var117;
                     var10001 = false;
                     break label892;
                  }

                  if (var1 == -1) {
                     var121 = var7;
                  } else {
                     ExposingBufferByteArrayOutputStream var9;
                     try {
                        var9 = new ExposingBufferByteArrayOutputStream(8193);
                        var9.write(var1);
                        ByteStreamsKt.copyTo$default((InputStream)var10, (OutputStream)var9, 0, 2, (Object)null);
                        var1 = var9.size() + var3;
                     } catch (Throwable var116) {
                        var10000 = var116;
                        var10001 = false;
                        break label892;
                     }

                     if (var1 < 0) {
                        try {
                           var122 = new StringBuilder();
                           var124 = new OutOfMemoryError(var122.append("File ").append(var0).append(" is too big to fit in memory.").toString());
                           throw var124;
                        } catch (Throwable var112) {
                           var10000 = var112;
                           var10001 = false;
                           break label892;
                        }
                     }

                     try {
                        var121 = var9.getBuffer();
                        var7 = Arrays.copyOf(var7, var1);
                        Intrinsics.checkNotNullExpressionValue(var7, "copyOf(this, newSize)");
                        var121 = ArraysKt.copyInto(var121, var7, var3, 0, var9.size());
                     } catch (Throwable var114) {
                        var10000 = var114;
                        var10001 = false;
                        break label892;
                     }
                  }
               }

               CloseableKt.closeFinally(var8, (Throwable)null);
               return var121;
            }
         }
      }

      Throwable var123 = var10000;

      try {
         throw var123;
      } finally {
         CloseableKt.closeFinally(var8, var123);
      }
   }

   public static final List readLines(File var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      ArrayList var2 = new ArrayList();
      FilesKt.forEachLine(var0, var1, (Function1)(new Function1(var2) {
         final ArrayList $result;

         {
            this.$result = var1;
         }

         public final void invoke(String var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            this.$result.add(var1);
         }
      }));
      return (List)var2;
   }

   // $FF: synthetic method
   public static List readLines$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return FilesKt.readLines(var0, var1);
   }

   public static final String readText(File var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Closeable var9 = (Closeable)(new InputStreamReader((InputStream)(new FileInputStream(var0)), var1));

      String var11;
      try {
         var11 = TextStreamsKt.readText((Reader)((InputStreamReader)var9));
      } catch (Throwable var8) {
         Throwable var10 = var8;

         try {
            throw var10;
         } finally {
            CloseableKt.closeFinally(var9, var8);
         }
      }

      CloseableKt.closeFinally(var9, (Throwable)null);
      return var11;
   }

   // $FF: synthetic method
   public static String readText$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return FilesKt.readText(var0, var1);
   }

   private static final InputStreamReader reader(File var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new InputStreamReader((InputStream)(new FileInputStream(var0)), var1);
   }

   // $FF: synthetic method
   static InputStreamReader reader$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new InputStreamReader((InputStream)(new FileInputStream(var0)), var1);
   }

   public static final Object useLines(File var0, Charset var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "block");
      Reader var9 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream(var0)), var1));
      BufferedReader var10;
      if (var9 instanceof BufferedReader) {
         var10 = (BufferedReader)var9;
      } else {
         var10 = new BufferedReader(var9, 8192);
      }

      Closeable var12 = (Closeable)var10;

      Object var13;
      try {
         var13 = var2.invoke(TextStreamsKt.lineSequence((BufferedReader)var12));
      } catch (Throwable var8) {
         Throwable var11 = var8;

         try {
            throw var11;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var12, var8);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var12, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var13;
   }

   // $FF: synthetic method
   public static Object useLines$default(File var0, Charset var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Reader var11 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream(var0)), var1));
      BufferedReader var12;
      if (var11 instanceof BufferedReader) {
         var12 = (BufferedReader)var11;
      } else {
         var12 = new BufferedReader(var11, 8192);
      }

      Closeable var13 = (Closeable)var12;

      Object var15;
      try {
         var15 = var2.invoke(TextStreamsKt.lineSequence((BufferedReader)var13));
      } catch (Throwable var10) {
         Throwable var14 = var10;

         try {
            throw var14;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var13, var10);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var13, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var15;
   }

   public static final void writeBytes(File var0, byte[] var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "array");
      Closeable var9 = (Closeable)(new FileOutputStream(var0));

      try {
         ((FileOutputStream)var9).write(var1);
         Unit var11 = Unit.INSTANCE;
      } catch (Throwable var8) {
         Throwable var10 = var8;

         try {
            throw var10;
         } finally {
            CloseableKt.closeFinally(var9, var8);
         }
      }

      CloseableKt.closeFinally(var9, (Throwable)null);
   }

   public static final void writeText(File var0, String var1, Charset var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "text");
      Intrinsics.checkNotNullParameter(var2, "charset");
      byte[] var3 = var1.getBytes(var2);
      Intrinsics.checkNotNullExpressionValue(var3, "this as java.lang.String).getBytes(charset)");
      FilesKt.writeBytes(var0, var3);
   }

   // $FF: synthetic method
   public static void writeText$default(File var0, String var1, Charset var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      FilesKt.writeText(var0, var1, var2);
   }

   private static final OutputStreamWriter writer(File var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new OutputStreamWriter((OutputStream)(new FileOutputStream(var0)), var1);
   }

   // $FF: synthetic method
   static OutputStreamWriter writer$default(File var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new OutputStreamWriter((OutputStream)(new FileOutputStream(var0)), var1);
   }
}
