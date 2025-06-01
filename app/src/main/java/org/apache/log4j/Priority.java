package org.apache.log4j;

public class Priority {
   public static final int ALL_INT = Integer.MIN_VALUE;
   public static final Priority DEBUG = new Level(10000, "DEBUG", 7);
   public static final int DEBUG_INT = 10000;
   public static final Priority ERROR = new Level(40000, "ERROR", 3);
   public static final int ERROR_INT = 40000;
   public static final Priority FATAL = new Level(50000, "FATAL", 0);
   public static final int FATAL_INT = 50000;
   public static final Priority INFO = new Level(20000, "INFO", 6);
   public static final int INFO_INT = 20000;
   public static final int OFF_INT = Integer.MAX_VALUE;
   public static final Priority WARN = new Level(30000, "WARN", 4);
   public static final int WARN_INT = 30000;
   transient int level;
   transient String levelStr;
   transient int syslogEquivalent;

   protected Priority() {
      this.level = 10000;
      this.levelStr = "DEBUG";
      this.syslogEquivalent = 7;
   }

   protected Priority(int var1, String var2, int var3) {
      this.level = var1;
      this.levelStr = var2;
      this.syslogEquivalent = var3;
   }

   public static Priority[] getAllPossiblePriorities() {
      return new Priority[]{FATAL, ERROR, Level.WARN, INFO, DEBUG};
   }

   public static Priority toPriority(int var0) {
      return toPriority(var0, DEBUG);
   }

   public static Priority toPriority(int var0, Priority var1) {
      return Level.toLevel(var0, (Level)var1);
   }

   public static Priority toPriority(String var0) {
      return Level.toLevel(var0);
   }

   public static Priority toPriority(String var0, Priority var1) {
      return Level.toLevel(var0, (Level)var1);
   }

   public boolean equals(Object var1) {
      boolean var4 = var1 instanceof Priority;
      boolean var3 = false;
      boolean var2 = var3;
      if (var4) {
         Priority var5 = (Priority)var1;
         var2 = var3;
         if (this.level == var5.level) {
            var2 = true;
         }
      }

      return var2;
   }

   public final int getSyslogEquivalent() {
      return this.syslogEquivalent;
   }

   public boolean isGreaterOrEqual(Priority var1) {
      boolean var2;
      if (this.level >= var1.level) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public final int toInt() {
      return this.level;
   }

   public final String toString() {
      return this.levelStr;
   }
}
