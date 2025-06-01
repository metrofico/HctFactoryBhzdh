package org.apache.log4j.lf5.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.SwingUtilities;
import org.apache.log4j.lf5.Log4JLogRecord;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogLevelFormatException;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.apache.log4j.lf5.viewer.LogFactor5LoadingDialog;

public class LogFileParser implements Runnable {
   public static final String ATTRIBUTE_DELIMITER = "[slf5s.";
   public static final String CATEGORY_DELIMITER = "[slf5s.CATEGORY]";
   public static final String DATE_DELIMITER = "[slf5s.DATE]";
   public static final String LOCATION_DELIMITER = "[slf5s.LOCATION]";
   public static final String MESSAGE_DELIMITER = "[slf5s.MESSAGE]";
   public static final String NDC_DELIMITER = "[slf5s.NDC]";
   public static final String PRIORITY_DELIMITER = "[slf5s.PRIORITY]";
   public static final String RECORD_DELIMITER = "[slf5s.start]";
   public static final String THREAD_DELIMITER = "[slf5s.THREAD]";
   private static SimpleDateFormat _sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss,S");
   private InputStream _in;
   LogFactor5LoadingDialog _loadDialog;
   private LogBrokerMonitor _monitor;

   public LogFileParser(File var1) throws IOException, FileNotFoundException {
      this((InputStream)(new FileInputStream(var1)));
   }

   public LogFileParser(InputStream var1) throws IOException {
      this._in = var1;
   }

   // $FF: synthetic method
   static void access$000(LogFileParser var0) {
      var0.destroyDialog();
   }

   private LogRecord createLogRecord(String var1) {
      if (var1 != null && var1.trim().length() != 0) {
         Log4JLogRecord var2 = new Log4JLogRecord();
         var2.setMillis(this.parseDate(var1));
         var2.setLevel(this.parsePriority(var1));
         var2.setCategory(this.parseCategory(var1));
         var2.setLocation(this.parseLocation(var1));
         var2.setThreadDescription(this.parseThread(var1));
         var2.setNDC(this.parseNDC(var1));
         var2.setMessage(this.parseMessage(var1));
         var2.setThrownStackTrace(this.parseThrowable(var1));
         return var2;
      } else {
         return null;
      }
   }

   private void destroyDialog() {
      this._loadDialog.hide();
      this._loadDialog.dispose();
   }

   private String getAttribute(int var1, String var2) {
      int var3 = var2.lastIndexOf("[slf5s.", var1 - 1);
      return var3 == -1 ? var2.substring(0, var1) : var2.substring(var2.indexOf("]", var3) + 1, var1).trim();
   }

   private String loadLogFile(InputStream var1) throws IOException {
      BufferedInputStream var3 = new BufferedInputStream(var1);
      int var2 = var3.available();
      StringBuffer var4;
      if (var2 > 0) {
         var4 = new StringBuffer(var2);
      } else {
         var4 = new StringBuffer(1024);
      }

      while(true) {
         var2 = var3.read();
         if (var2 == -1) {
            var3.close();
            return var4.toString();
         }

         var4.append((char)var2);
      }
   }

   private String parseAttribute(String var1, String var2) {
      int var3 = var2.indexOf(var1);
      return var3 == -1 ? null : this.getAttribute(var3, var2);
   }

   private String parseCategory(String var1) {
      return this.parseAttribute("[slf5s.CATEGORY]", var1);
   }

   private long parseDate(String var1) {
      long var2 = 0L;

      boolean var10001;
      try {
         var1 = this.parseAttribute("[slf5s.DATE]", var1);
      } catch (ParseException var7) {
         var10001 = false;
         return var2;
      }

      if (var1 == null) {
         return 0L;
      } else {
         long var4;
         try {
            var4 = _sdf.parse(var1).getTime();
         } catch (ParseException var6) {
            var10001 = false;
            return var2;
         }

         var2 = var4;
         return var2;
      }
   }

   private String parseLocation(String var1) {
      return this.parseAttribute("[slf5s.LOCATION]", var1);
   }

   private String parseMessage(String var1) {
      return this.parseAttribute("[slf5s.MESSAGE]", var1);
   }

   private String parseNDC(String var1) {
      return this.parseAttribute("[slf5s.NDC]", var1);
   }

   private LogLevel parsePriority(String var1) {
      var1 = this.parseAttribute("[slf5s.PRIORITY]", var1);
      if (var1 != null) {
         try {
            LogLevel var3 = LogLevel.valueOf(var1);
            return var3;
         } catch (LogLevelFormatException var2) {
            return LogLevel.DEBUG;
         }
      } else {
         return LogLevel.DEBUG;
      }
   }

   private String parseThread(String var1) {
      return this.parseAttribute("[slf5s.THREAD]", var1);
   }

   private String parseThrowable(String var1) {
      return this.getAttribute(var1.length(), var1);
   }

   protected void displayError(String var1) {
      new LogFactor5ErrorDialog(this._monitor.getBaseFrame(), var1);
   }

   public void parse(LogBrokerMonitor var1) throws RuntimeException {
      this._monitor = var1;
      (new Thread(this)).start();
   }

   public void run() {
      this._loadDialog = new LogFactor5LoadingDialog(this._monitor.getBaseFrame(), "Loading file...");

      label97: {
         label96: {
            label101: {
               boolean var10001;
               String var5;
               try {
                  var5 = this.loadLogFile(this._in);
               } catch (RuntimeException var22) {
                  var10001 = false;
                  break label96;
               } catch (IOException var23) {
                  var10001 = false;
                  break label101;
               }

               int var2 = 0;
               boolean var1 = false;

               while(true) {
                  int var3;
                  try {
                     var3 = var5.indexOf("[slf5s.start]", var2);
                  } catch (RuntimeException var16) {
                     var10001 = false;
                     break label96;
                  } catch (IOException var17) {
                     var10001 = false;
                     break;
                  }

                  LogRecord var4;
                  if (var3 == -1) {
                     label79: {
                        try {
                           if (var2 >= var5.length()) {
                              break label79;
                           }
                        } catch (RuntimeException var14) {
                           var10001 = false;
                           break label96;
                        } catch (IOException var15) {
                           var10001 = false;
                           break;
                        }

                        if (var1) {
                           try {
                              var4 = this.createLogRecord(var5.substring(var2));
                           } catch (RuntimeException var12) {
                              var10001 = false;
                              break label96;
                           } catch (IOException var13) {
                              var10001 = false;
                              break;
                           }

                           if (var4 != null) {
                              try {
                                 this._monitor.addMessage(var4);
                              } catch (RuntimeException var10) {
                                 var10001 = false;
                                 break label96;
                              } catch (IOException var11) {
                                 var10001 = false;
                                 break;
                              }
                           }
                        }
                     }

                     if (var1) {
                        try {
                           LogFileParser$1 var24 = new LogFileParser$1(this);
                           SwingUtilities.invokeLater(var24);
                           break label97;
                        } catch (RuntimeException var6) {
                           var10001 = false;
                           break label96;
                        } catch (IOException var7) {
                           var10001 = false;
                           break;
                        }
                     } else {
                        try {
                           RuntimeException var25 = new RuntimeException("Invalid log file format");
                           throw var25;
                        } catch (RuntimeException var8) {
                           var10001 = false;
                           break label96;
                        } catch (IOException var9) {
                           var10001 = false;
                           break;
                        }
                     }
                  }

                  try {
                     var4 = this.createLogRecord(var5.substring(var2, var3));
                  } catch (RuntimeException var20) {
                     var10001 = false;
                     break label96;
                  } catch (IOException var21) {
                     var10001 = false;
                     break;
                  }

                  var1 = true;
                  if (var4 != null) {
                     try {
                        this._monitor.addMessage(var4);
                     } catch (RuntimeException var18) {
                        var10001 = false;
                        break label96;
                     } catch (IOException var19) {
                        var10001 = false;
                        break;
                     }
                  }

                  var2 = var3 + 13;
               }
            }

            this.destroyDialog();
            this.displayError("Error - Unable to load log file!");
            break label97;
         }

         this.destroyDialog();
         this.displayError("Error - Invalid log file format.\nPlease see documentation on how to load log files.");
      }

      this._in = null;
   }
}
