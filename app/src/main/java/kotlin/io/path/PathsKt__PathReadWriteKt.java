package kotlin.io.path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a%\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u001e\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a:\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010\u0015\u001a:\u0010\u0016\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010\u0018\u001a=\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2!\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00010\u001bH\u0087\bø\u0001\u0000\u001a&\u0010 \u001a\u00020!*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010\"\u001a&\u0010#\u001a\u00020$*\u00020\u00022\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010%\u001a\r\u0010&\u001a\u00020\u0004*\u00020\u0002H\u0087\b\u001a\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0087\b\u001a\u0016\u0010)\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a0\u0010*\u001a\u00020+*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010,\u001a?\u0010-\u001a\u0002H.\"\u0004\b\u0000\u0010.*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0018\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u000b\u0012\u0004\u0012\u0002H.0\u001bH\u0087\bø\u0001\u0000¢\u0006\u0002\u00100\u001a.\u00101\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u00102\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u00104\u001a>\u00103\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u00105\u001a7\u00106\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0007¢\u0006\u0002\u00107\u001a0\u00108\u001a\u000209*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0013\"\u00020\u0014H\u0087\b¢\u0006\u0002\u0010:\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"},
   d2 = {"appendBytes", "", "Ljava/nio/file/Path;", "array", "", "appendLines", "lines", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/sequences/Sequence;", "appendText", "text", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "options", "", "Ljava/nio/file/OpenOption;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedReader;", "bufferedWriter", "Ljava/io/BufferedWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;I[Ljava/nio/file/OpenOption;)Ljava/io/BufferedWriter;", "forEachLine", "action", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "line", "inputStream", "Ljava/io/InputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStream;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/InputStreamReader;", "useLines", "T", "block", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)V", "writeLines", "(Ljava/nio/file/Path;Ljava/lang/Iterable;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Lkotlin/sequences/Sequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", "writeText", "(Ljava/nio/file/Path;Ljava/lang/CharSequence;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)V", "writer", "Ljava/io/OutputStreamWriter;", "(Ljava/nio/file/Path;Ljava/nio/charset/Charset;[Ljava/nio/file/OpenOption;)Ljava/io/OutputStreamWriter;", "kotlin-stdlib-jdk7"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/io/path/PathsKt"
)
class PathsKt__PathReadWriteKt {
   public PathsKt__PathReadWriteKt() {
   }

   private static final void appendBytes(Path var0, byte[] var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "array");
      Files.write(var0, var1, new OpenOption[]{(OpenOption)StandardOpenOption.APPEND});
   }

   private static final Path appendLines(Path var0, Iterable var1, Charset var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      var0 = Files.write(var0, var1, var2, (OpenOption)StandardOpenOption.APPEND);
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines, chars…tandardOpenOption.APPEND)");
      return var0;
   }

   private static final Path appendLines(Path var0, Sequence var1, Charset var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      var0 = Files.write(var0, SequencesKt.asIterable(var1), var2, (OpenOption)StandardOpenOption.APPEND);
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines.asIter…tandardOpenOption.APPEND)");
      return var0;
   }

   // $FF: synthetic method
   static Path appendLines$default(Path var0, Iterable var1, Charset var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      var0 = Files.write(var0, var1, var2, (OpenOption)StandardOpenOption.APPEND);
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines, chars…tandardOpenOption.APPEND)");
      return var0;
   }

   // $FF: synthetic method
   static Path appendLines$default(Path var0, Sequence var1, Charset var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      var0 = Files.write(var0, SequencesKt.asIterable(var1), var2, (OpenOption)StandardOpenOption.APPEND);
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines.asIter…tandardOpenOption.APPEND)");
      return var0;
   }

   public static final void appendText(Path var0, CharSequence var1, Charset var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "text");
      Intrinsics.checkNotNullParameter(var2, "charset");
      OutputStream var9 = Files.newOutputStream(var0, (OpenOption)StandardOpenOption.APPEND);
      Intrinsics.checkNotNullExpressionValue(var9, "newOutputStream(this, StandardOpenOption.APPEND)");
      Closeable var10 = (Closeable)(new OutputStreamWriter(var9, var2));

      try {
         ((OutputStreamWriter)var10).append(var1);
      } catch (Throwable var8) {
         Throwable var11 = var8;

         try {
            throw var11;
         } finally {
            CloseableKt.closeFinally(var10, var8);
         }
      }

      CloseableKt.closeFinally(var10, (Throwable)null);
   }

   // $FF: synthetic method
   public static void appendText$default(Path var0, CharSequence var1, Charset var2, int var3, Object var4) throws IOException {
      if ((var3 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      PathsKt.appendText(var0, var1, var2);
   }

   private static final BufferedReader bufferedReader(Path var0, Charset var1, int var2, OpenOption... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      return new BufferedReader((Reader)(new InputStreamReader(Files.newInputStream(var0, (OpenOption[])Arrays.copyOf(var3, var3.length)), var1)), var2);
   }

   // $FF: synthetic method
   static BufferedReader bufferedReader$default(Path var0, Charset var1, int var2, OpenOption[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      if ((var4 & 2) != 0) {
         var2 = 8192;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      return new BufferedReader((Reader)(new InputStreamReader(Files.newInputStream(var0, (OpenOption[])Arrays.copyOf(var3, var3.length)), var1)), var2);
   }

   private static final BufferedWriter bufferedWriter(Path var0, Charset var1, int var2, OpenOption... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      return new BufferedWriter((Writer)(new OutputStreamWriter(Files.newOutputStream(var0, (OpenOption[])Arrays.copyOf(var3, var3.length)), var1)), var2);
   }

   // $FF: synthetic method
   static BufferedWriter bufferedWriter$default(Path var0, Charset var1, int var2, OpenOption[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      if ((var4 & 2) != 0) {
         var2 = 8192;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      return new BufferedWriter((Writer)(new OutputStreamWriter(Files.newOutputStream(var0, (OpenOption[])Arrays.copyOf(var3, var3.length)), var1)), var2);
   }

   private static final void forEachLine(Path var0, Charset var1, Function1 var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "action");
      BufferedReader var23 = Files.newBufferedReader(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var23, "newBufferedReader(this, charset)");
      Closeable var24 = (Closeable)((BufferedReader)((Reader)var23));

      label194: {
         Throwable var10000;
         label195: {
            boolean var10001;
            Iterator var25;
            try {
               var25 = TextStreamsKt.lineSequence((BufferedReader)var24).iterator();
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label195;
            }

            while(true) {
               try {
                  if (!var25.hasNext()) {
                     break;
                  }

                  var2.invoke(var25.next());
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break label195;
               }
            }

            label178:
            try {
               Unit var27 = Unit.INSTANCE;
               break label194;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label178;
            }
         }

         Throwable var26 = var10000;

         try {
            throw var26;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var24, var26);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var24, (Throwable)null);
      InlineMarker.finallyEnd(1);
   }

   // $FF: synthetic method
   static void forEachLine$default(Path var0, Charset var1, Function1 var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "action");
      BufferedReader var25 = Files.newBufferedReader(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var25, "newBufferedReader(this, charset)");
      Closeable var26 = (Closeable)((BufferedReader)((Reader)var25));

      label214: {
         Throwable var10000;
         label215: {
            boolean var10001;
            Iterator var27;
            try {
               var27 = TextStreamsKt.lineSequence((BufferedReader)var26).iterator();
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label215;
            }

            while(true) {
               try {
                  if (!var27.hasNext()) {
                     break;
                  }

                  var2.invoke(var27.next());
               } catch (Throwable var24) {
                  var10000 = var24;
                  var10001 = false;
                  break label215;
               }
            }

            label196:
            try {
               Unit var29 = Unit.INSTANCE;
               break label214;
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label196;
            }
         }

         Throwable var28 = var10000;

         try {
            throw var28;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var26, var28);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var26, (Throwable)null);
      InlineMarker.finallyEnd(1);
   }

   private static final InputStream inputStream(Path var0, OpenOption... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      InputStream var2 = Files.newInputStream(var0, (OpenOption[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var2, "newInputStream(this, *options)");
      return var2;
   }

   private static final OutputStream outputStream(Path var0, OpenOption... var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "options");
      OutputStream var2 = Files.newOutputStream(var0, (OpenOption[])Arrays.copyOf(var1, var1.length));
      Intrinsics.checkNotNullExpressionValue(var2, "newOutputStream(this, *options)");
      return var2;
   }

   private static final byte[] readBytes(Path var0) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      byte[] var1 = Files.readAllBytes(var0);
      Intrinsics.checkNotNullExpressionValue(var1, "readAllBytes(this)");
      return var1;
   }

   private static final List readLines(Path var0, Charset var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      List var2 = Files.readAllLines(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var2, "readAllLines(this, charset)");
      return var2;
   }

   // $FF: synthetic method
   static List readLines$default(Path var0, Charset var1, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      List var4 = Files.readAllLines(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var4, "readAllLines(this, charset)");
      return var4;
   }

   public static final String readText(Path var0, Charset var1) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Closeable var9 = (Closeable)(new InputStreamReader(Files.newInputStream(var0, (OpenOption[])Arrays.copyOf(new OpenOption[0], 0)), var1));

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
   public static String readText$default(Path var0, Charset var1, int var2, Object var3) throws IOException {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      return PathsKt.readText(var0, var1);
   }

   private static final InputStreamReader reader(Path var0, Charset var1, OpenOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "options");
      return new InputStreamReader(Files.newInputStream(var0, (OpenOption[])Arrays.copyOf(var2, var2.length)), var1);
   }

   // $FF: synthetic method
   static InputStreamReader reader$default(Path var0, Charset var1, OpenOption[] var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "options");
      return new InputStreamReader(Files.newInputStream(var0, (OpenOption[])Arrays.copyOf(var2, var2.length)), var1);
   }

   private static final Object useLines(Path var0, Charset var1, Function1 var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "block");
      Closeable var9 = (Closeable)Files.newBufferedReader(var0, var1);

      Object var11;
      try {
         BufferedReader var10 = (BufferedReader)var9;
         Intrinsics.checkNotNullExpressionValue(var10, "it");
         var11 = var2.invoke(TextStreamsKt.lineSequence(var10));
      } catch (Throwable var8) {
         Throwable var12 = var8;

         try {
            throw var12;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var9, var8);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var9, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var11;
   }

   // $FF: synthetic method
   static Object useLines$default(Path var0, Charset var1, Function1 var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "block");
      Closeable var11 = (Closeable)Files.newBufferedReader(var0, var1);

      Object var13;
      try {
         BufferedReader var12 = (BufferedReader)var11;
         Intrinsics.checkNotNullExpressionValue(var12, "it");
         var13 = var2.invoke(TextStreamsKt.lineSequence(var12));
      } catch (Throwable var10) {
         Throwable var14 = var10;

         try {
            throw var14;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var11, var10);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var11, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var13;
   }

   private static final void writeBytes(Path var0, byte[] var1, OpenOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "array");
      Intrinsics.checkNotNullParameter(var2, "options");
      Files.write(var0, var1, (OpenOption[])Arrays.copyOf(var2, var2.length));
   }

   private static final Path writeLines(Path var0, Iterable var1, Charset var2, OpenOption... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      var0 = Files.write(var0, var1, var2, (OpenOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines, charset, *options)");
      return var0;
   }

   private static final Path writeLines(Path var0, Sequence var1, Charset var2, OpenOption... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      var0 = Files.write(var0, SequencesKt.asIterable(var1), var2, (OpenOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines.asIterable(), charset, *options)");
      return var0;
   }

   // $FF: synthetic method
   static Path writeLines$default(Path var0, Iterable var1, Charset var2, OpenOption[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      var0 = Files.write(var0, var1, var2, (OpenOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines, charset, *options)");
      return var0;
   }

   // $FF: synthetic method
   static Path writeLines$default(Path var0, Sequence var1, Charset var2, OpenOption[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "lines");
      Intrinsics.checkNotNullParameter(var2, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      var0 = Files.write(var0, SequencesKt.asIterable(var1), var2, (OpenOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var0, "write(this, lines.asIterable(), charset, *options)");
      return var0;
   }

   public static final void writeText(Path var0, CharSequence var1, Charset var2, OpenOption... var3) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "text");
      Intrinsics.checkNotNullParameter(var2, "charset");
      Intrinsics.checkNotNullParameter(var3, "options");
      OutputStream var10 = Files.newOutputStream(var0, (OpenOption[])Arrays.copyOf(var3, var3.length));
      Intrinsics.checkNotNullExpressionValue(var10, "newOutputStream(this, *options)");
      Closeable var11 = (Closeable)(new OutputStreamWriter(var10, var2));

      try {
         ((OutputStreamWriter)var11).append(var1);
      } catch (Throwable var9) {
         Throwable var12 = var9;

         try {
            throw var12;
         } finally {
            CloseableKt.closeFinally(var11, var9);
         }
      }

      CloseableKt.closeFinally(var11, (Throwable)null);
   }

   // $FF: synthetic method
   public static void writeText$default(Path var0, CharSequence var1, Charset var2, OpenOption[] var3, int var4, Object var5) throws IOException {
      if ((var4 & 2) != 0) {
         var2 = Charsets.UTF_8;
      }

      PathsKt.writeText(var0, var1, var2, var3);
   }

   private static final OutputStreamWriter writer(Path var0, Charset var1, OpenOption... var2) throws IOException {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "options");
      return new OutputStreamWriter(Files.newOutputStream(var0, (OpenOption[])Arrays.copyOf(var2, var2.length)), var1);
   }

   // $FF: synthetic method
   static OutputStreamWriter writer$default(Path var0, Charset var1, OpenOption[] var2, int var3, Object var4) throws IOException {
      if ((var3 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      Intrinsics.checkNotNullParameter(var2, "options");
      return new OutputStreamWriter(Files.newOutputStream(var0, (OpenOption[])Arrays.copyOf(var2, var2.length)), var1);
   }
}
