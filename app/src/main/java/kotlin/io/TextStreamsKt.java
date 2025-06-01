package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;

@Metadata(
   d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\u0087\b\u001a8\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\u0086\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001e\u0082\u0002\u000f\n\u0005\b\u009920\u0001\n\u0006\b\u0011(\u001f0\u0001¨\u0006 "},
   d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Requires newer compiler version to be inlined correctly.", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class TextStreamsKt {
   private static final BufferedReader buffered(Reader var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      BufferedReader var2;
      if (var0 instanceof BufferedReader) {
         var2 = (BufferedReader)var0;
      } else {
         var2 = new BufferedReader(var0, var1);
      }

      return var2;
   }

   private static final BufferedWriter buffered(Writer var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      BufferedWriter var2;
      if (var0 instanceof BufferedWriter) {
         var2 = (BufferedWriter)var0;
      } else {
         var2 = new BufferedWriter(var0, var1);
      }

      return var2;
   }

   // $FF: synthetic method
   static BufferedReader buffered$default(Reader var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 8192;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      BufferedReader var4;
      if (var0 instanceof BufferedReader) {
         var4 = (BufferedReader)var0;
      } else {
         var4 = new BufferedReader(var0, var1);
      }

      return var4;
   }

   // $FF: synthetic method
   static BufferedWriter buffered$default(Writer var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = 8192;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      BufferedWriter var4;
      if (var0 instanceof BufferedWriter) {
         var4 = (BufferedWriter)var0;
      } else {
         var4 = new BufferedWriter(var0, var1);
      }

      return var4;
   }

   public static final long copyTo(Reader var0, Writer var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "out");
      char[] var5 = new char[var2];
      var2 = var0.read(var5);

      long var3;
      for(var3 = 0L; var2 >= 0; var2 = var0.read(var5)) {
         var1.write(var5, 0, var2);
         var3 += (long)var2;
      }

      return var3;
   }

   // $FF: synthetic method
   public static long copyTo$default(Reader var0, Writer var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 8192;
      }

      return copyTo(var0, var1, var2);
   }

   public static final void forEachLine(Reader var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "action");
      BufferedReader var23;
      if (var0 instanceof BufferedReader) {
         var23 = (BufferedReader)var0;
      } else {
         var23 = new BufferedReader(var0, 8192);
      }

      Closeable var24 = (Closeable)var23;

      label220: {
         Throwable var10000;
         label215: {
            boolean var10001;
            Iterator var2;
            try {
               var2 = lineSequence((BufferedReader)var24).iterator();
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label215;
            }

            while(true) {
               try {
                  if (var2.hasNext()) {
                     var1.invoke(var2.next());
                     continue;
                  }
               } catch (Throwable var22) {
                  var10000 = var22;
                  var10001 = false;
                  break;
               }

               try {
                  Unit var25 = Unit.INSTANCE;
                  break label220;
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  break;
               }
            }
         }

         Throwable var26 = var10000;

         try {
            throw var26;
         } finally {
            CloseableKt.closeFinally(var24, var26);
         }
      }

      CloseableKt.closeFinally(var24, (Throwable)null);
   }

   public static final Sequence lineSequence(BufferedReader var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.constrainOnce((Sequence)(new LinesSequence(var0)));
   }

   public static final byte[] readBytes(URL var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Closeable var9 = (Closeable)var0.openStream();

      byte[] var10;
      try {
         InputStream var1 = (InputStream)var9;
         Intrinsics.checkNotNullExpressionValue(var1, "it");
         var10 = ByteStreamsKt.readBytes(var1);
      } catch (Throwable var8) {
         Throwable var2 = var8;

         try {
            throw var2;
         } finally {
            CloseableKt.closeFinally(var9, var8);
         }
      }

      CloseableKt.closeFinally(var9, (Throwable)null);
      return var10;
   }

   public static final List readLines(Reader var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ArrayList var1 = new ArrayList();
      forEachLine(var0, (Function1)(new Function1(var1) {
         final ArrayList $result;

         {
            this.$result = var1;
         }

         public final void invoke(String var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            this.$result.add(var1);
         }
      }));
      return (List)var1;
   }

   public static final String readText(Reader var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      StringWriter var1 = new StringWriter();
      copyTo$default(var0, (Writer)var1, 0, 2, (Object)null);
      String var2 = var1.toString();
      Intrinsics.checkNotNullExpressionValue(var2, "buffer.toString()");
      return var2;
   }

   private static final String readText(URL var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new String(readBytes(var0), var1);
   }

   // $FF: synthetic method
   static String readText$default(URL var0, Charset var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Charsets.UTF_8;
      }

      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new String(readBytes(var0), var1);
   }

   private static final StringReader reader(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return new StringReader(var0);
   }

   public static final Object useLines(Reader var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "block");
      BufferedReader var9;
      if (var0 instanceof BufferedReader) {
         var9 = (BufferedReader)var0;
      } else {
         var9 = new BufferedReader(var0, 8192);
      }

      Closeable var10 = (Closeable)var9;

      Object var12;
      try {
         var12 = var1.invoke(lineSequence((BufferedReader)var10));
      } catch (Throwable var8) {
         Throwable var11 = var8;

         try {
            throw var11;
         } finally {
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(var10, var8);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      CloseableKt.closeFinally(var10, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var12;
   }
}
