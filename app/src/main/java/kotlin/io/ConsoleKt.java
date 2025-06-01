package kotlin.io;

import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\t\u0010\r\u001a\u00020\u0001H\u0087\b\u001a\u0013\u0010\r\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\b\u001a\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u001a\b\u0010\u0010\u001a\u00020\u000fH\u0007\u001a\n\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0007¨\u0006\u0012"},
   d2 = {"print", "", "message", "", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "readln", "readlnOrNull", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class ConsoleKt {
   private static final void print(byte var0) {
      System.out.print(var0);
   }

   private static final void print(char var0) {
      System.out.print(var0);
   }

   private static final void print(double var0) {
      System.out.print(var0);
   }

   private static final void print(float var0) {
      System.out.print(var0);
   }

   private static final void print(int var0) {
      System.out.print(var0);
   }

   private static final void print(long var0) {
      System.out.print(var0);
   }

   private static final void print(Object var0) {
      System.out.print(var0);
   }

   private static final void print(short var0) {
      System.out.print(var0);
   }

   private static final void print(boolean var0) {
      System.out.print(var0);
   }

   private static final void print(char[] var0) {
      Intrinsics.checkNotNullParameter(var0, "message");
      System.out.print(var0);
   }

   private static final void println() {
      System.out.println();
   }

   private static final void println(byte var0) {
      System.out.println(var0);
   }

   private static final void println(char var0) {
      System.out.println(var0);
   }

   private static final void println(double var0) {
      System.out.println(var0);
   }

   private static final void println(float var0) {
      System.out.println(var0);
   }

   private static final void println(int var0) {
      System.out.println(var0);
   }

   private static final void println(long var0) {
      System.out.println(var0);
   }

   private static final void println(Object var0) {
      System.out.println(var0);
   }

   private static final void println(short var0) {
      System.out.println(var0);
   }

   private static final void println(boolean var0) {
      System.out.println(var0);
   }

   private static final void println(char[] var0) {
      Intrinsics.checkNotNullParameter(var0, "message");
      System.out.println(var0);
   }

   public static final String readLine() {
      LineReader var1 = LineReader.INSTANCE;
      InputStream var2 = System.in;
      Intrinsics.checkNotNullExpressionValue(var2, "`in`");
      Charset var0 = Charset.defaultCharset();
      Intrinsics.checkNotNullExpressionValue(var0, "defaultCharset()");
      return var1.readLine(var2, var0);
   }

   public static final String readln() {
      String var0 = readlnOrNull();
      if (var0 != null) {
         return var0;
      } else {
         throw new ReadAfterEOFException("EOF has already been reached");
      }
   }

   public static final String readlnOrNull() {
      return readLine();
   }
}
