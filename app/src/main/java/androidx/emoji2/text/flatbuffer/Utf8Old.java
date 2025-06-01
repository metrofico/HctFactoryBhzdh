package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;

public class Utf8Old extends Utf8 {
   private static final ThreadLocal CACHE = ThreadLocal.withInitial(new Utf8Old$$ExternalSyntheticLambda0());

   // $FF: synthetic method
   static Cache lambda$static$0() {
      return new Cache();
   }

   public String decodeUtf8(ByteBuffer var1, int var2, int var3) {
      CharsetDecoder var4 = ((Cache)CACHE.get()).decoder;
      var4.reset();
      var1 = var1.duplicate();
      var1.position(var2);
      var1.limit(var2 + var3);

      try {
         String var6 = var4.decode(var1).toString();
         return var6;
      } catch (CharacterCodingException var5) {
         throw new IllegalArgumentException("Bad encoding", var5);
      }
   }

   public void encodeUtf8(CharSequence var1, ByteBuffer var2) {
      Cache var3 = (Cache)CACHE.get();
      if (var3.lastInput != var1) {
         this.encodedLength(var1);
      }

      var2.put(var3.lastOutput);
   }

   public int encodedLength(CharSequence var1) {
      Cache var3 = (Cache)CACHE.get();
      int var2 = (int)((float)var1.length() * var3.encoder.maxBytesPerChar());
      if (var3.lastOutput == null || var3.lastOutput.capacity() < var2) {
         var3.lastOutput = ByteBuffer.allocate(Math.max(128, var2));
      }

      var3.lastOutput.clear();
      var3.lastInput = var1;
      CharBuffer var5;
      if (var1 instanceof CharBuffer) {
         var5 = (CharBuffer)var1;
      } else {
         var5 = CharBuffer.wrap(var1);
      }

      CoderResult var6 = var3.encoder.encode(var5, var3.lastOutput, true);
      if (var6.isError()) {
         try {
            var6.throwException();
         } catch (CharacterCodingException var4) {
            throw new IllegalArgumentException("bad character encoding", var4);
         }
      }

      var3.lastOutput.flip();
      return var3.lastOutput.remaining();
   }

   private static class Cache {
      final CharsetDecoder decoder;
      final CharsetEncoder encoder;
      CharSequence lastInput = null;
      ByteBuffer lastOutput = null;

      Cache() {
         this.encoder = StandardCharsets.UTF_8.newEncoder();
         this.decoder = StandardCharsets.UTF_8.newDecoder();
      }
   }
}
