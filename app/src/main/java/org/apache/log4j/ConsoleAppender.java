package org.apache.log4j;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.log4j.helpers.LogLog;

public class ConsoleAppender extends WriterAppender {
   public static final String SYSTEM_ERR = "System.err";
   public static final String SYSTEM_OUT = "System.out";
   private boolean follow;
   protected String target;

   public ConsoleAppender() {
      this.target = "System.out";
      this.follow = false;
   }

   public ConsoleAppender(Layout var1) {
      this(var1, "System.out");
   }

   public ConsoleAppender(Layout var1, String var2) {
      this.target = "System.out";
      this.follow = false;
      this.setLayout(var1);
      this.setTarget(var2);
      this.activateOptions();
   }

   public void activateOptions() {
      if (this.follow) {
         if (this.target.equals("System.err")) {
            this.setWriter(this.createWriter(new SystemErrStream()));
         } else {
            this.setWriter(this.createWriter(new SystemOutStream()));
         }
      } else if (this.target.equals("System.err")) {
         this.setWriter(this.createWriter(System.err));
      } else {
         this.setWriter(this.createWriter(System.out));
      }

      super.activateOptions();
   }

   protected final void closeWriter() {
      if (this.follow) {
         super.closeWriter();
      }

   }

   public final boolean getFollow() {
      return this.follow;
   }

   public String getTarget() {
      return this.target;
   }

   public final void setFollow(boolean var1) {
      this.follow = var1;
   }

   public void setTarget(String var1) {
      String var2 = var1.trim();
      if ("System.out".equalsIgnoreCase(var2)) {
         this.target = "System.out";
      } else if ("System.err".equalsIgnoreCase(var2)) {
         this.target = "System.err";
      } else {
         this.targetWarn(var1);
      }

   }

   void targetWarn(String var1) {
      LogLog.warn("[" + var1 + "] should be System.out or System.err.");
      LogLog.warn("Using previously set target, System.out by default.");
   }

   private static class SystemErrStream extends OutputStream {
      public SystemErrStream() {
      }

      public void close() {
      }

      public void flush() {
         System.err.flush();
      }

      public void write(int var1) throws IOException {
         System.err.write(var1);
      }

      public void write(byte[] var1) throws IOException {
         System.err.write(var1);
      }

      public void write(byte[] var1, int var2, int var3) throws IOException {
         System.err.write(var1, var2, var3);
      }
   }

   private static class SystemOutStream extends OutputStream {
      public SystemOutStream() {
      }

      public void close() {
      }

      public void flush() {
         System.out.flush();
      }

      public void write(int var1) throws IOException {
         System.out.write(var1);
      }

      public void write(byte[] var1) throws IOException {
         System.out.write(var1);
      }

      public void write(byte[] var1, int var2, int var3) throws IOException {
         System.out.write(var1, var2, var3);
      }
   }
}
