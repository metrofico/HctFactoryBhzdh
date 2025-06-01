package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"},
   d2 = {"Lkotlin/io/LineReader;", "", "()V", "BUFFER_SIZE", "", "byteBuf", "Ljava/nio/ByteBuffer;", "bytes", "", "charBuf", "Ljava/nio/CharBuffer;", "chars", "", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "compactBytes", "decode", "endOfInput", "decodeEndOfInput", "nBytes", "nChars", "readLine", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "resetAll", "", "trimStringBuilder", "updateCharset", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class LineReader {
   private static final int BUFFER_SIZE = 32;
   public static final LineReader INSTANCE = new LineReader();
   private static final ByteBuffer byteBuf;
   private static final byte[] bytes;
   private static final CharBuffer charBuf;
   private static final char[] chars;
   private static CharsetDecoder decoder;
   private static boolean directEOL;
   private static final StringBuilder sb;

   static {
      byte[] var1 = new byte[32];
      bytes = var1;
      char[] var0 = new char[32];
      chars = var0;
      ByteBuffer var3 = ByteBuffer.wrap(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "wrap(bytes)");
      byteBuf = var3;
      CharBuffer var2 = CharBuffer.wrap(var0);
      Intrinsics.checkNotNullExpressionValue(var2, "wrap(chars)");
      charBuf = var2;
      sb = new StringBuilder();
   }

   private LineReader() {
   }

   private final int compactBytes() {
      ByteBuffer var2 = byteBuf;
      var2.compact();
      int var1 = var2.position();
      var2.position(0);
      return var1;
   }

   private final int decode(boolean var1) {
      while(true) {
         CharsetDecoder var4 = decoder;
         CharsetDecoder var3 = var4;
         if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            var3 = null;
         }

         ByteBuffer var5 = byteBuf;
         CharBuffer var7 = charBuf;
         CoderResult var6 = var3.decode(var5, var7, var1);
         Intrinsics.checkNotNullExpressionValue(var6, "decoder.decode(byteBuf, charBuf, endOfInput)");
         if (var6.isError()) {
            this.resetAll();
            var6.throwException();
         }

         int var2 = var7.position();
         if (!var6.isOverflow()) {
            return var2;
         }

         StringBuilder var9 = sb;
         char[] var8 = chars;
         --var2;
         var9.append(var8, 0, var2);
         var7.position(0);
         var7.limit(32);
         var7.put(var8[var2]);
      }
   }

   private final int decodeEndOfInput(int var1, int var2) {
      ByteBuffer var5 = byteBuf;
      var5.limit(var1);
      charBuf.position(var2);
      var1 = this.decode(true);
      CharsetDecoder var4 = decoder;
      CharsetDecoder var3 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("decoder");
         var3 = null;
      }

      var3.reset();
      var5.position(0);
      return var1;
   }

   private final void resetAll() {
      CharsetDecoder var2 = decoder;
      CharsetDecoder var1 = var2;
      if (var2 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("decoder");
         var1 = null;
      }

      var1.reset();
      byteBuf.position(0);
      sb.setLength(0);
   }

   private final void trimStringBuilder() {
      StringBuilder var1 = sb;
      var1.setLength(32);
      var1.trimToSize();
   }

   private final void updateCharset(Charset var1) {
      CharsetDecoder var7 = var1.newDecoder();
      Intrinsics.checkNotNullExpressionValue(var7, "charset.newDecoder()");
      decoder = var7;
      ByteBuffer var5 = byteBuf;
      var5.clear();
      CharBuffer var6 = charBuf;
      var6.clear();
      var5.put((byte)10);
      var5.flip();
      CharsetDecoder var4 = decoder;
      var7 = var4;
      if (var4 == null) {
         Intrinsics.throwUninitializedPropertyAccessException("decoder");
         var7 = null;
      }

      boolean var3 = false;
      var7.decode(var5, var6, false);
      boolean var2 = var3;
      if (var6.position() == 1) {
         var2 = var3;
         if (var6.get(0) == '\n') {
            var2 = true;
         }
      }

      directEOL = var2;
      this.resetAll();
   }

   public final String readLine(InputStream var1, Charset var2) {
      synchronized(this){}

      Throwable var10000;
      label2897: {
         CharsetDecoder var9;
         boolean var10001;
         try {
            Intrinsics.checkNotNullParameter(var1, "inputStream");
            Intrinsics.checkNotNullParameter(var2, "charset");
            var9 = decoder;
         } catch (Throwable var315) {
            var10000 = var315;
            var10001 = false;
            break label2897;
         }

         label2889: {
            if (var9 != null) {
               CharsetDecoder var8 = var9;
               if (var9 == null) {
                  try {
                     Intrinsics.throwUninitializedPropertyAccessException("decoder");
                  } catch (Throwable var313) {
                     var10000 = var313;
                     var10001 = false;
                     break label2897;
                  }

                  var8 = null;
               }

               try {
                  if (Intrinsics.areEqual((Object)var8.charset(), (Object)var2)) {
                     break label2889;
                  }
               } catch (Throwable var314) {
                  var10000 = var314;
                  var10001 = false;
                  break label2897;
               }
            }

            try {
               this.updateCharset(var2);
            } catch (Throwable var312) {
               var10000 = var312;
               var10001 = false;
               break label2897;
            }
         }

         int var3 = 0;
         int var4 = 0;

         boolean var6;
         while(true) {
            int var7;
            try {
               var7 = var1.read();
            } catch (Throwable var308) {
               var10000 = var308;
               var10001 = false;
               break label2897;
            }

            var6 = true;
            int var5;
            if (var7 == -1) {
               try {
                  var5 = ((CharSequence)sb).length();
               } catch (Throwable var305) {
                  var10000 = var305;
                  var10001 = false;
                  break label2897;
               }

               boolean var323;
               if (var5 == 0) {
                  var323 = true;
               } else {
                  var323 = false;
               }

               if (var323 && var3 == 0 && var4 == 0) {
                  return null;
               }

               try {
                  var4 = this.decodeEndOfInput(var3, var4);
                  break;
               } catch (Throwable var304) {
                  var10000 = var304;
                  var10001 = false;
                  break label2897;
               }
            }

            byte[] var320;
            try {
               var320 = bytes;
            } catch (Throwable var307) {
               var10000 = var307;
               var10001 = false;
               break label2897;
            }

            label2900: {
               var5 = var3 + 1;
               var320[var3] = (byte)var7;
               if (var7 != 10 && var5 != 32) {
                  try {
                     if (directEOL) {
                        break label2900;
                     }
                  } catch (Throwable var311) {
                     var10000 = var311;
                     var10001 = false;
                     break label2897;
                  }
               }

               ByteBuffer var321;
               try {
                  var321 = byteBuf;
                  var321.limit(var5);
                  charBuf.position(var4);
                  var4 = this.decode(false);
               } catch (Throwable var306) {
                  var10000 = var306;
                  var10001 = false;
                  break label2897;
               }

               if (var4 > 0) {
                  try {
                     if (chars[var4 - 1] == '\n') {
                        var321.position(0);
                        break;
                     }
                  } catch (Throwable var310) {
                     var10000 = var310;
                     var10001 = false;
                     break label2897;
                  }
               }

               try {
                  var3 = this.compactBytes();
                  continue;
               } catch (Throwable var309) {
                  var10000 = var309;
                  var10001 = false;
                  break label2897;
               }
            }

            var3 = var5;
         }

         var3 = var4;
         if (var4 > 0) {
            char[] var316;
            try {
               var316 = chars;
            } catch (Throwable var303) {
               var10000 = var303;
               var10001 = false;
               break label2897;
            }

            var3 = var4;
            if (var316[var4 - 1] == '\n') {
               --var4;
               var3 = var4;
               if (var4 > 0) {
                  var3 = var4;
                  if (var316[var4 - 1] == '\r') {
                     var3 = var4 - 1;
                  }
               }
            }
         }

         boolean var324;
         StringBuilder var317;
         label2838: {
            label2837: {
               try {
                  var317 = sb;
                  if (((CharSequence)var317).length() == 0) {
                     break label2837;
                  }
               } catch (Throwable var302) {
                  var10000 = var302;
                  var10001 = false;
                  break label2897;
               }

               var324 = false;
               break label2838;
            }

            var324 = var6;
         }

         if (var324) {
            label2824: {
               String var318;
               try {
                  var318 = new String(chars, 0, var3);
               } catch (Throwable var299) {
                  var10000 = var299;
                  var10001 = false;
                  break label2824;
               }

               return var318;
            }
         } else {
            label2901: {
               String var322;
               try {
                  var317.append(chars, 0, var3);
                  var322 = var317.toString();
                  Intrinsics.checkNotNullExpressionValue(var322, "sb.toString()");
                  if (var317.length() > 32) {
                     this.trimStringBuilder();
                  }
               } catch (Throwable var301) {
                  var10000 = var301;
                  var10001 = false;
                  break label2901;
               }

               try {
                  var317.setLength(0);
               } catch (Throwable var300) {
                  var10000 = var300;
                  var10001 = false;
                  break label2901;
               }

               return var322;
            }
         }
      }

      Throwable var319 = var10000;
      throw var319;
   }
}
