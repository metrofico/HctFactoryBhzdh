package org.apache.log4j.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser {
   static final int CLASS_LOCATION_CONVERTER = 1002;
   private static final int CONVERTER_STATE = 1;
   private static final int DOT_STATE = 3;
   private static final char ESCAPE_CHAR = '%';
   static final int FILE_LOCATION_CONVERTER = 1004;
   static final int FULL_LOCATION_CONVERTER = 1000;
   static final int LEVEL_CONVERTER = 2002;
   static final int LINE_LOCATION_CONVERTER = 1003;
   private static final int LITERAL_STATE = 0;
   private static final int MAX_STATE = 5;
   static final int MESSAGE_CONVERTER = 2004;
   static final int METHOD_LOCATION_CONVERTER = 1001;
   private static final int MINUS_STATE = 2;
   private static final int MIN_STATE = 4;
   static final int NDC_CONVERTER = 2003;
   static final int RELATIVE_TIME_CONVERTER = 2000;
   static final int THREAD_CONVERTER = 2001;
   static Class class$java$text$DateFormat;
   protected StringBuffer currentLiteral = new StringBuffer(32);
   protected FormattingInfo formattingInfo = new FormattingInfo();
   PatternConverter head;
   protected int i;
   protected String pattern;
   protected int patternLength;
   int state;
   PatternConverter tail;

   public PatternParser(String var1) {
      this.pattern = var1;
      this.patternLength = var1.length();
      this.state = 0;
   }

   private void addToList(PatternConverter var1) {
      if (this.head == null) {
         this.tail = var1;
         this.head = var1;
      } else {
         this.tail.next = var1;
         this.tail = var1;
      }

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

   protected void addConverter(PatternConverter var1) {
      this.currentLiteral.setLength(0);
      this.addToList(var1);
      this.state = 0;
      this.formattingInfo.reset();
   }

   protected String extractOption() {
      int var1 = this.i;
      if (var1 < this.patternLength && this.pattern.charAt(var1) == '{') {
         var1 = this.pattern.indexOf(125, this.i);
         int var2 = this.i;
         if (var1 > var2) {
            String var3 = this.pattern.substring(var2 + 1, var1);
            this.i = var1 + 1;
            return var3;
         }
      }

      return null;
   }

   protected int extractPrecisionOption() {
      String var5 = this.extractOption();
      byte var3 = 0;
      int var1 = var3;
      if (var5 != null) {
         label35: {
            NumberFormatException var4;
            label29: {
               int var2;
               try {
                  var2 = Integer.parseInt(var5);
               } catch (NumberFormatException var7) {
                  var4 = var7;
                  var1 = 0;
                  break label29;
               }

               var1 = var2;
               if (var2 > 0) {
                  return var1;
               }

               try {
                  StringBuffer var8 = new StringBuffer();
                  LogLog.error(var8.append("Precision option (").append(var5).append(") isn't a positive integer.").toString());
                  break label35;
               } catch (NumberFormatException var6) {
                  var4 = var6;
                  var1 = var2;
               }
            }

            LogLog.error("Category option \"" + var5 + "\" not a decimal integer.", var4);
            return var1;
         }

         var1 = var3;
      }

      return var1;
   }

   protected void finalizeConverter(char var1) {
      Object var2;
      if (var1 != 'C') {
         if (var1 != 'F') {
            String var5;
            if (var1 != 'X') {
               if (var1 != 'p') {
                  if (var1 != 'r') {
                     if (var1 != 't') {
                        if (var1 != 'x') {
                           if (var1 != 'L') {
                              if (var1 != 'M') {
                                 if (var1 != 'c') {
                                    if (var1 != 'd') {
                                       if (var1 != 'l') {
                                          if (var1 != 'm') {
                                             LogLog.error("Unexpected char [" + var1 + "] at position " + this.i + " in conversion patterrn.");
                                             var2 = new LiteralPatternConverter(this.currentLiteral.toString());
                                             this.currentLiteral.setLength(0);
                                          } else {
                                             var2 = new BasicPatternConverter(this.formattingInfo, 2004);
                                             this.currentLiteral.setLength(0);
                                          }
                                       } else {
                                          var2 = new LocationPatternConverter(this.formattingInfo, 1000);
                                          this.currentLiteral.setLength(0);
                                       }
                                    } else {
                                       var5 = this.extractOption();
                                       if (var5 == null) {
                                          var5 = "ISO8601";
                                       }

                                       if (var5.equalsIgnoreCase("ISO8601")) {
                                          var2 = new ISO8601DateFormat();
                                       } else if (var5.equalsIgnoreCase("ABSOLUTE")) {
                                          var2 = new AbsoluteTimeDateFormat();
                                       } else if (var5.equalsIgnoreCase("DATE")) {
                                          var2 = new DateTimeDateFormat();
                                       } else {
                                          label83: {
                                             SimpleDateFormat var6;
                                             try {
                                                var6 = new SimpleDateFormat(var5);
                                             } catch (IllegalArgumentException var4) {
                                                LogLog.error("Could not instantiate SimpleDateFormat with " + var5, var4);
                                                Class var3 = class$java$text$DateFormat;
                                                Class var7 = var3;
                                                if (var3 == null) {
                                                   var7 = class$("java.text.DateFormat");
                                                   class$java$text$DateFormat = var7;
                                                }

                                                var2 = (DateFormat)OptionConverter.instantiateByClassName("org.apache.log4j.helpers.ISO8601DateFormat", var7, (Object)null);
                                                break label83;
                                             }

                                             var2 = var6;
                                          }
                                       }

                                       var2 = new DatePatternConverter(this.formattingInfo, (DateFormat)var2);
                                       this.currentLiteral.setLength(0);
                                    }
                                 } else {
                                    var2 = new CategoryPatternConverter(this.formattingInfo, this.extractPrecisionOption());
                                    this.currentLiteral.setLength(0);
                                 }
                              } else {
                                 var2 = new LocationPatternConverter(this.formattingInfo, 1001);
                                 this.currentLiteral.setLength(0);
                              }
                           } else {
                              var2 = new LocationPatternConverter(this.formattingInfo, 1003);
                              this.currentLiteral.setLength(0);
                           }
                        } else {
                           var2 = new BasicPatternConverter(this.formattingInfo, 2003);
                           this.currentLiteral.setLength(0);
                        }
                     } else {
                        var2 = new BasicPatternConverter(this.formattingInfo, 2001);
                        this.currentLiteral.setLength(0);
                     }
                  } else {
                     var2 = new BasicPatternConverter(this.formattingInfo, 2000);
                     this.currentLiteral.setLength(0);
                  }
               } else {
                  var2 = new BasicPatternConverter(this.formattingInfo, 2002);
                  this.currentLiteral.setLength(0);
               }
            } else {
               var5 = this.extractOption();
               var2 = new MDCPatternConverter(this.formattingInfo, var5);
               this.currentLiteral.setLength(0);
            }
         } else {
            var2 = new LocationPatternConverter(this.formattingInfo, 1004);
            this.currentLiteral.setLength(0);
         }
      } else {
         var2 = new ClassNamePatternConverter(this.formattingInfo, this.extractPrecisionOption());
         this.currentLiteral.setLength(0);
      }

      this.addConverter((PatternConverter)var2);
   }

   public PatternConverter parse() {
      this.i = 0;

      while(true) {
         while(true) {
            char var1;
            int var2;
            FormattingInfo var5;
            label94:
            do {
               while(true) {
                  while(true) {
                     while(true) {
                        while(true) {
                           var2 = this.i;
                           if (var2 >= this.patternLength) {
                              if (this.currentLiteral.length() != 0) {
                                 this.addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
                              }

                              return this.head;
                           }

                           String var3 = this.pattern;
                           this.i = var2 + 1;
                           var1 = var3.charAt(var2);
                           var2 = this.state;
                           if (var2 != 0) {
                              if (var2 != 1) {
                                 if (var2 != 3) {
                                    if (var2 != 4) {
                                       continue label94;
                                    }

                                    this.currentLiteral.append(var1);
                                    if (var1 >= '0' && var1 <= '9') {
                                       var5 = this.formattingInfo;
                                       var5.min = var5.min * 10 + (var1 - 48);
                                    } else if (var1 == '.') {
                                       this.state = 3;
                                    } else {
                                       this.finalizeConverter(var1);
                                    }
                                 } else {
                                    this.currentLiteral.append(var1);
                                    if (var1 >= '0' && var1 <= '9') {
                                       this.formattingInfo.max = var1 - 48;
                                       this.state = 5;
                                    } else {
                                       LogLog.error("Error occured in position " + this.i + ".\n Was expecting digit, instead got char \"" + var1 + "\".");
                                       this.state = 0;
                                    }
                                 }
                              } else {
                                 this.currentLiteral.append(var1);
                                 if (var1 != '-') {
                                    if (var1 != '.') {
                                       if (var1 >= '0' && var1 <= '9') {
                                          this.formattingInfo.min = var1 - 48;
                                          this.state = 4;
                                       } else {
                                          this.finalizeConverter(var1);
                                       }
                                    } else {
                                       this.state = 3;
                                    }
                                 } else {
                                    this.formattingInfo.leftAlign = true;
                                 }
                              }
                           } else {
                              var2 = this.i;
                              if (var2 == this.patternLength) {
                                 this.currentLiteral.append(var1);
                              } else if (var1 == '%') {
                                 char var4 = this.pattern.charAt(var2);
                                 if (var4 != '%') {
                                    if (var4 != 'n') {
                                       if (this.currentLiteral.length() != 0) {
                                          this.addToList(new LiteralPatternConverter(this.currentLiteral.toString()));
                                       }

                                       this.currentLiteral.setLength(0);
                                       this.currentLiteral.append(var1);
                                       this.state = 1;
                                       this.formattingInfo.reset();
                                    } else {
                                       this.currentLiteral.append(Layout.LINE_SEP);
                                       ++this.i;
                                    }
                                 } else {
                                    this.currentLiteral.append(var1);
                                    ++this.i;
                                 }
                              } else {
                                 this.currentLiteral.append(var1);
                              }
                           }
                        }
                     }
                  }
               }
            } while(var2 != 5);

            this.currentLiteral.append(var1);
            if (var1 >= '0' && var1 <= '9') {
               var5 = this.formattingInfo;
               var5.max = var5.max * 10 + (var1 - 48);
            } else {
               this.finalizeConverter(var1);
               this.state = 0;
            }
         }
      }
   }

   private static class BasicPatternConverter extends PatternConverter {
      int type;

      BasicPatternConverter(FormattingInfo var1, int var2) {
         super(var1);
         this.type = var2;
      }

      public String convert(LoggingEvent var1) {
         switch (this.type) {
            case 2000:
               return Long.toString(var1.timeStamp - LoggingEvent.getStartTime());
            case 2001:
               return var1.getThreadName();
            case 2002:
               return var1.getLevel().toString();
            case 2003:
               return var1.getNDC();
            case 2004:
               return var1.getRenderedMessage();
            default:
               return null;
         }
      }
   }

   private class CategoryPatternConverter extends NamedPatternConverter {
      CategoryPatternConverter(FormattingInfo var2, int var3) {
         super(var2, var3);
      }

      String getFullyQualifiedName(LoggingEvent var1) {
         return var1.getLoggerName();
      }
   }

   private class ClassNamePatternConverter extends NamedPatternConverter {
      ClassNamePatternConverter(FormattingInfo var2, int var3) {
         super(var2, var3);
      }

      String getFullyQualifiedName(LoggingEvent var1) {
         return var1.getLocationInformation().getClassName();
      }
   }

   private static class DatePatternConverter extends PatternConverter {
      private Date date = new Date();
      private DateFormat df;

      DatePatternConverter(FormattingInfo var1, DateFormat var2) {
         super(var1);
         this.df = var2;
      }

      public String convert(LoggingEvent var1) {
         this.date.setTime(var1.timeStamp);

         String var3;
         try {
            var3 = this.df.format(this.date);
         } catch (Exception var2) {
            LogLog.error("Error occured while converting date.", var2);
            var3 = null;
         }

         return var3;
      }
   }

   private static class LiteralPatternConverter extends PatternConverter {
      private String literal;

      LiteralPatternConverter(String var1) {
         this.literal = var1;
      }

      public String convert(LoggingEvent var1) {
         return this.literal;
      }

      public final void format(StringBuffer var1, LoggingEvent var2) {
         var1.append(this.literal);
      }
   }

   private class LocationPatternConverter extends PatternConverter {
      int type;

      LocationPatternConverter(FormattingInfo var2, int var3) {
         super(var2);
         this.type = var3;
      }

      public String convert(LoggingEvent var1) {
         LocationInfo var2 = var1.getLocationInformation();
         switch (this.type) {
            case 1000:
               return var2.fullInfo;
            case 1001:
               return var2.getMethodName();
            case 1002:
            default:
               return null;
            case 1003:
               return var2.getLineNumber();
            case 1004:
               return var2.getFileName();
         }
      }
   }

   private static class MDCPatternConverter extends PatternConverter {
      private String key;

      MDCPatternConverter(FormattingInfo var1, String var2) {
         super(var1);
         this.key = var2;
      }

      public String convert(LoggingEvent var1) {
         Object var2 = var1.getMDC(this.key);
         return var2 == null ? null : var2.toString();
      }
   }

   private abstract static class NamedPatternConverter extends PatternConverter {
      int precision;

      NamedPatternConverter(FormattingInfo var1, int var2) {
         super(var1);
         this.precision = var2;
      }

      public String convert(LoggingEvent var1) {
         String var5 = this.getFullyQualifiedName(var1);
         if (this.precision <= 0) {
            return var5;
         } else {
            int var4 = var5.length();
            int var3 = var4 - 1;

            for(int var2 = this.precision; var2 > 0; --var2) {
               var3 = var5.lastIndexOf(46, var3 - 1);
               if (var3 == -1) {
                  return var5;
               }
            }

            return var5.substring(var3 + 1, var4);
         }
      }

      abstract String getFullyQualifiedName(LoggingEvent var1);
   }
}
