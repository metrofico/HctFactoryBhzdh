package org.apache.log4j.net;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.SyslogQuietWriter;
import org.apache.log4j.helpers.SyslogWriter;
import org.apache.log4j.spi.LoggingEvent;

public class SyslogAppender extends AppenderSkeleton {
   protected static final int FACILITY_OI = 1;
   public static final int LOG_AUTH = 32;
   public static final int LOG_AUTHPRIV = 80;
   public static final int LOG_CRON = 72;
   public static final int LOG_DAEMON = 24;
   public static final int LOG_FTP = 88;
   public static final int LOG_KERN = 0;
   public static final int LOG_LOCAL0 = 128;
   public static final int LOG_LOCAL1 = 136;
   public static final int LOG_LOCAL2 = 144;
   public static final int LOG_LOCAL3 = 152;
   public static final int LOG_LOCAL4 = 160;
   public static final int LOG_LOCAL5 = 168;
   public static final int LOG_LOCAL6 = 176;
   public static final int LOG_LOCAL7 = 184;
   public static final int LOG_LPR = 48;
   public static final int LOG_MAIL = 16;
   public static final int LOG_NEWS = 56;
   public static final int LOG_SYSLOG = 40;
   public static final int LOG_USER = 8;
   public static final int LOG_UUCP = 64;
   protected static final int SYSLOG_HOST_OI = 0;
   static final String TAB = "    ";
   boolean facilityPrinting;
   String facilityStr;
   SyslogQuietWriter sqw;
   int syslogFacility;
   String syslogHost;

   public SyslogAppender() {
      this.syslogFacility = 8;
      this.facilityPrinting = false;
      this.initSyslogFacilityStr();
   }

   public SyslogAppender(Layout var1, int var2) {
      this.syslogFacility = 8;
      this.facilityPrinting = false;
      super.layout = var1;
      this.syslogFacility = var2;
      this.initSyslogFacilityStr();
   }

   public SyslogAppender(Layout var1, String var2, int var3) {
      this(var1, var3);
      this.setSyslogHost(var2);
   }

   public static int getFacility(String var0) {
      String var1 = var0;
      if (var0 != null) {
         var1 = var0.trim();
      }

      if ("KERN".equalsIgnoreCase(var1)) {
         return 0;
      } else if ("USER".equalsIgnoreCase(var1)) {
         return 8;
      } else if ("MAIL".equalsIgnoreCase(var1)) {
         return 16;
      } else if ("DAEMON".equalsIgnoreCase(var1)) {
         return 24;
      } else if ("AUTH".equalsIgnoreCase(var1)) {
         return 32;
      } else if ("SYSLOG".equalsIgnoreCase(var1)) {
         return 40;
      } else if ("LPR".equalsIgnoreCase(var1)) {
         return 48;
      } else if ("NEWS".equalsIgnoreCase(var1)) {
         return 56;
      } else if ("UUCP".equalsIgnoreCase(var1)) {
         return 64;
      } else if ("CRON".equalsIgnoreCase(var1)) {
         return 72;
      } else if ("AUTHPRIV".equalsIgnoreCase(var1)) {
         return 80;
      } else if ("FTP".equalsIgnoreCase(var1)) {
         return 88;
      } else if ("LOCAL0".equalsIgnoreCase(var1)) {
         return 128;
      } else if ("LOCAL1".equalsIgnoreCase(var1)) {
         return 136;
      } else if ("LOCAL2".equalsIgnoreCase(var1)) {
         return 144;
      } else if ("LOCAL3".equalsIgnoreCase(var1)) {
         return 152;
      } else if ("LOCAL4".equalsIgnoreCase(var1)) {
         return 160;
      } else if ("LOCAL5".equalsIgnoreCase(var1)) {
         return 168;
      } else if ("LOCAL6".equalsIgnoreCase(var1)) {
         return 176;
      } else {
         return "LOCAL7".equalsIgnoreCase(var1) ? 184 : -1;
      }
   }

   public static String getFacilityString(int var0) {
      switch (var0) {
         case 0:
            return "kern";
         case 8:
            return "user";
         case 16:
            return "mail";
         case 24:
            return "daemon";
         case 32:
            return "auth";
         case 40:
            return "syslog";
         case 48:
            return "lpr";
         case 56:
            return "news";
         case 64:
            return "uucp";
         case 72:
            return "cron";
         case 80:
            return "authpriv";
         case 88:
            return "ftp";
         case 128:
            return "local0";
         case 136:
            return "local1";
         case 144:
            return "local2";
         case 152:
            return "local3";
         case 160:
            return "local4";
         case 168:
            return "local5";
         case 176:
            return "local6";
         case 184:
            return "local7";
         default:
            return null;
      }
   }

   private void initSyslogFacilityStr() {
      String var1 = getFacilityString(this.syslogFacility);
      this.facilityStr = var1;
      if (var1 == null) {
         System.err.println("\"" + this.syslogFacility + "\" is an unknown syslog facility. Defaulting to \"USER\".");
         this.syslogFacility = 8;
         this.facilityStr = "user:";
      } else {
         this.facilityStr = this.facilityStr + ":";
      }

   }

   public void activateOptions() {
   }

   public void append(LoggingEvent var1) {
      if (this.isAsSevereAsThreshold(var1.getLevel())) {
         if (this.sqw == null) {
            super.errorHandler.error("No syslog host is set for SyslogAppedender named \"" + super.name + "\".");
         } else {
            StringBuffer var5 = new StringBuffer();
            String var4;
            if (this.facilityPrinting) {
               var4 = this.facilityStr;
            } else {
               var4 = "";
            }

            var4 = var5.append(var4).append(super.layout.format(var1)).toString();
            this.sqw.setLevel(var1.getLevel().getSyslogEquivalent());
            this.sqw.write(var4);
            if (super.layout.ignoresThrowable()) {
               String[] var6 = var1.getThrowableStrRep();
               if (var6 != null) {
                  int var3 = var6.length;
                  if (var3 > 0) {
                     this.sqw.write(var6[0]);

                     for(int var2 = 1; var2 < var3; ++var2) {
                        this.sqw.write("    " + var6[var2].substring(1));
                     }
                  }
               }
            }

         }
      }
   }

   public void close() {
      synchronized(this){}

      try {
         super.closed = true;
         this.sqw = null;
      } finally {
         ;
      }

   }

   public String getFacility() {
      return getFacilityString(this.syslogFacility);
   }

   public boolean getFacilityPrinting() {
      return this.facilityPrinting;
   }

   public String getSyslogHost() {
      return this.syslogHost;
   }

   public boolean requiresLayout() {
      return true;
   }

   public void setFacility(String var1) {
      if (var1 != null) {
         int var2 = getFacility(var1);
         this.syslogFacility = var2;
         if (var2 == -1) {
            System.err.println("[" + var1 + "] is an unknown syslog facility. Defaulting to [USER].");
            this.syslogFacility = 8;
         }

         this.initSyslogFacilityStr();
         SyslogQuietWriter var3 = this.sqw;
         if (var3 != null) {
            var3.setSyslogFacility(this.syslogFacility);
         }

      }
   }

   public void setFacilityPrinting(boolean var1) {
      this.facilityPrinting = var1;
   }

   public void setSyslogHost(String var1) {
      this.sqw = new SyslogQuietWriter(new SyslogWriter(var1), this.syslogFacility, super.errorHandler);
      this.syslogHost = var1;
   }
}
