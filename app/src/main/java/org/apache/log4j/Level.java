package org.apache.log4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Level extends Priority implements Serializable {
   public static final Level ALL = new Level(Integer.MIN_VALUE, "ALL", 7);
   public static final Level DEBUG = new Level(10000, "DEBUG", 7);
   public static final Level ERROR = new Level(40000, "ERROR", 3);
   public static final Level FATAL = new Level(50000, "FATAL", 0);
   public static final Level INFO = new Level(20000, "INFO", 6);
   public static final Level OFF = new Level(Integer.MAX_VALUE, "OFF", 0);
   public static final Level TRACE = new Level(5000, "TRACE", 7);
   public static final int TRACE_INT = 5000;
   public static final Level WARN = new Level(30000, "WARN", 4);
   static Class class$org$apache$log4j$Level;
   static final long serialVersionUID = 3491141966387921974L;

   protected Level(int var1, String var2, int var3) {
      super(var1, var2, var3);
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   private void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException {
      var1.defaultReadObject();
      super.level = var1.readInt();
      super.syslogEquivalent = var1.readInt();
      super.levelStr = var1.readUTF();
      if (super.levelStr == null) {
         super.levelStr = "";
      }

   }

   private Object readResolve() throws ObjectStreamException {
      Class var3 = this.getClass();
      Class var2 = class$org$apache$log4j$Level;
      Class var1 = var2;
      if (var2 == null) {
         var1 = class$("org.apache.log4j.Level");
         class$org$apache$log4j$Level = var1;
      }

      return var3 == var1 ? toLevel(super.level) : this;
   }

   public static Level toLevel(int var0) {
      return toLevel(var0, DEBUG);
   }

   public static Level toLevel(int var0, Level var1) {
      if (var0 != Integer.MIN_VALUE) {
         if (var0 != 5000) {
            if (var0 != 10000) {
               if (var0 != 20000) {
                  if (var0 != 30000) {
                     if (var0 != 40000) {
                        if (var0 != 50000) {
                           return var0 != Integer.MAX_VALUE ? var1 : OFF;
                        } else {
                           return FATAL;
                        }
                     } else {
                        return ERROR;
                     }
                  } else {
                     return WARN;
                  }
               } else {
                  return INFO;
               }
            } else {
               return DEBUG;
            }
         } else {
            return TRACE;
         }
      } else {
         return ALL;
      }
   }

   public static Level toLevel(String var0) {
      return toLevel(var0, DEBUG);
   }

   public static Level toLevel(String var0, Level var1) {
      if (var0 == null) {
         return var1;
      } else {
         var0 = var0.toUpperCase();
         if (var0.equals("ALL")) {
            return ALL;
         } else if (var0.equals("DEBUG")) {
            return DEBUG;
         } else if (var0.equals("INFO")) {
            return INFO;
         } else if (var0.equals("WARN")) {
            return WARN;
         } else if (var0.equals("ERROR")) {
            return ERROR;
         } else if (var0.equals("FATAL")) {
            return FATAL;
         } else if (var0.equals("OFF")) {
            return OFF;
         } else {
            return var0.equals("TRACE") ? TRACE : var1;
         }
      }
   }

   private void writeObject(ObjectOutputStream var1) throws IOException {
      var1.defaultWriteObject();
      var1.writeInt(super.level);
      var1.writeInt(super.syslogEquivalent);
      var1.writeUTF(super.levelStr);
   }
}
