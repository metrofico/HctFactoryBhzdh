package org.apache.log4j.lf5;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LogLevel implements Serializable {
   public static final LogLevel CONFIG;
   public static final LogLevel DEBUG;
   public static final LogLevel ERROR;
   public static final LogLevel FATAL;
   public static final LogLevel FINE;
   public static final LogLevel FINER;
   public static final LogLevel FINEST;
   public static final LogLevel INFO;
   public static final LogLevel SEVERE;
   public static final LogLevel WARN;
   public static final LogLevel WARNING;
   private static LogLevel[] _allDefaultLevels;
   private static LogLevel[] _jdk14Levels;
   private static LogLevel[] _log4JLevels;
   private static Map _logLevelColorMap;
   private static Map _logLevelMap;
   private static Map _registeredLogLevelMap;
   protected String _label;
   protected int _precedence;

   static {
      byte var1 = 0;
      LogLevel var10 = new LogLevel("FATAL", 0);
      FATAL = var10;
      LogLevel var3 = new LogLevel("ERROR", 1);
      ERROR = var3;
      LogLevel var11 = new LogLevel("WARN", 2);
      WARN = var11;
      LogLevel var9 = new LogLevel("INFO", 3);
      INFO = var9;
      LogLevel var8 = new LogLevel("DEBUG", 4);
      DEBUG = var8;
      LogLevel var12 = new LogLevel("SEVERE", 1);
      SEVERE = var12;
      LogLevel var2 = new LogLevel("WARNING", 2);
      WARNING = var2;
      LogLevel var5 = new LogLevel("CONFIG", 4);
      CONFIG = var5;
      LogLevel var6 = new LogLevel("FINE", 5);
      FINE = var6;
      LogLevel var4 = new LogLevel("FINER", 6);
      FINER = var4;
      LogLevel var7 = new LogLevel("FINEST", 7);
      FINEST = var7;
      _registeredLogLevelMap = new HashMap();
      _log4JLevels = new LogLevel[]{var10, var3, var11, var9, var8};
      _jdk14Levels = new LogLevel[]{var12, var2, var9, var5, var6, var4, var7};
      _allDefaultLevels = new LogLevel[]{var10, var3, var11, var9, var8, var12, var2, var5, var6, var4, var7};
      _logLevelMap = new HashMap();
      int var0 = 0;

      while(true) {
         LogLevel[] var13 = _allDefaultLevels;
         if (var0 >= var13.length) {
            _logLevelColorMap = new HashMap();
            var0 = var1;

            while(true) {
               var13 = _allDefaultLevels;
               if (var0 >= var13.length) {
                  return;
               }

               _logLevelColorMap.put(var13[var0], Color.black);
               ++var0;
            }
         }

         _logLevelMap.put(var13[var0].getLabel(), _allDefaultLevels[var0]);
         ++var0;
      }
   }

   public LogLevel(String var1, int var2) {
      this._label = var1;
      this._precedence = var2;
   }

   public static List getAllDefaultLevels() {
      return Arrays.asList(_allDefaultLevels);
   }

   public static List getJdk14Levels() {
      return Arrays.asList(_jdk14Levels);
   }

   public static List getLog4JLevels() {
      return Arrays.asList(_log4JLevels);
   }

   public static Map getLogLevelColorMap() {
      return _logLevelColorMap;
   }

   public static LogLevel register(LogLevel var0) {
      if (var0 == null) {
         return null;
      } else {
         return _logLevelMap.get(var0.getLabel()) == null ? (LogLevel)_registeredLogLevelMap.put(var0.getLabel(), var0) : null;
      }
   }

   public static void register(List var0) {
      if (var0 != null) {
         Iterator var1 = var0.iterator();

         while(var1.hasNext()) {
            register((LogLevel)var1.next());
         }
      }

   }

   public static void register(LogLevel[] var0) {
      if (var0 != null) {
         for(int var1 = 0; var1 < var0.length; ++var1) {
            register(var0[var1]);
         }
      }

   }

   public static void resetLogLevelColorMap() {
      _logLevelColorMap.clear();
      int var0 = 0;

      while(true) {
         LogLevel[] var1 = _allDefaultLevels;
         if (var0 >= var1.length) {
            return;
         }

         _logLevelColorMap.put(var1[var0], Color.black);
         ++var0;
      }
   }

   public static LogLevel valueOf(String var0) throws LogLevelFormatException {
      String var1;
      LogLevel var2;
      LogLevel var3;
      if (var0 != null) {
         var1 = var0.trim().toUpperCase();
         var3 = (LogLevel)_logLevelMap.get(var1);
      } else {
         var2 = null;
         var1 = var0;
         var3 = var2;
      }

      var2 = var3;
      if (var3 == null) {
         var2 = var3;
         if (_registeredLogLevelMap.size() > 0) {
            var2 = (LogLevel)_registeredLogLevelMap.get(var1);
         }
      }

      if (var2 != null) {
         return var2;
      } else {
         StringBuffer var4 = new StringBuffer();
         var4.append("Error while trying to parse (" + var1 + ") into");
         var4.append(" a LogLevel.");
         throw new LogLevelFormatException(var4.toString());
      }
   }

   public boolean encompasses(LogLevel var1) {
      return var1.getPrecedence() <= this.getPrecedence();
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof LogLevel && this.getPrecedence() == ((LogLevel)var1).getPrecedence()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public String getLabel() {
      return this._label;
   }

   protected int getPrecedence() {
      return this._precedence;
   }

   public int hashCode() {
      return this._label.hashCode();
   }

   public void setLogLevelColorMap(LogLevel var1, Color var2) {
      _logLevelColorMap.remove(var1);
      Color var3 = var2;
      if (var2 == null) {
         var3 = Color.black;
      }

      _logLevelColorMap.put(var1, var3);
   }

   public String toString() {
      return this._label;
   }
}
