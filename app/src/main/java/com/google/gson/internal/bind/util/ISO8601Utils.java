package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
   private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");
   private static final String UTC_ID = "UTC";

   private static boolean checkOffset(String var0, int var1, char var2) {
      boolean var3;
      if (var1 < var0.length() && var0.charAt(var1) == var2) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static String format(Date var0) {
      return format(var0, false, TIMEZONE_UTC);
   }

   public static String format(Date var0, boolean var1) {
      return format(var0, var1, TIMEZONE_UTC);
   }

   public static String format(Date var0, boolean var1, TimeZone var2) {
      GregorianCalendar var7 = new GregorianCalendar(var2, Locale.US);
      var7.setTime(var0);
      int var4;
      if (var1) {
         var4 = 4;
      } else {
         var4 = 0;
      }

      int var5;
      if (var2.getRawOffset() == 0) {
         var5 = 1;
      } else {
         var5 = 6;
      }

      StringBuilder var8 = new StringBuilder(19 + var4 + var5);
      padInt(var8, var7.get(1), 4);
      char var3 = '-';
      var8.append('-');
      padInt(var8, var7.get(2) + 1, 2);
      var8.append('-');
      padInt(var8, var7.get(5), 2);
      var8.append('T');
      padInt(var8, var7.get(11), 2);
      var8.append(':');
      padInt(var8, var7.get(12), 2);
      var8.append(':');
      padInt(var8, var7.get(13), 2);
      if (var1) {
         var8.append('.');
         padInt(var8, var7.get(14), 3);
      }

      var4 = var2.getOffset(var7.getTimeInMillis());
      if (var4 != 0) {
         int var6 = var4 / '\uea60';
         var5 = Math.abs(var6 / 60);
         var6 = Math.abs(var6 % 60);
         if (var4 >= 0) {
            var3 = '+';
         }

         var8.append(var3);
         padInt(var8, var5, 2);
         var8.append(':');
         padInt(var8, var6, 2);
      } else {
         var8.append('Z');
      }

      return var8.toString();
   }

   private static int indexOfNonDigit(String var0, int var1) {
      while(true) {
         if (var1 < var0.length()) {
            char var2 = var0.charAt(var1);
            if (var2 >= '0' && var2 <= '9') {
               ++var1;
               continue;
            }

            return var1;
         }

         return var0.length();
      }
   }

   private static void padInt(StringBuilder var0, int var1, int var2) {
      String var3 = Integer.toString(var1);

      for(var1 = var2 - var3.length(); var1 > 0; --var1) {
         var0.append('0');
      }

      var0.append(var3);
   }

   public static Date parse(String var0, ParsePosition var1) throws ParseException {
      String var16;
      String var100;
      Object var103;
      label392: {
         IndexOutOfBoundsException var107;
         label391: {
            NumberFormatException var106;
            label390: {
               IllegalArgumentException var10000;
               label395: {
                  int var3;
                  boolean var10001;
                  try {
                     var3 = var1.getIndex();
                  } catch (IndexOutOfBoundsException var93) {
                     var107 = var93;
                     var10001 = false;
                     break label391;
                  } catch (NumberFormatException var94) {
                     var106 = var94;
                     var10001 = false;
                     break label390;
                  } catch (IllegalArgumentException var95) {
                     var10000 = var95;
                     var10001 = false;
                     break label395;
                  }

                  int var4 = var3 + 4;

                  int var9;
                  try {
                     var9 = parseInt(var0, var3, var4);
                  } catch (IndexOutOfBoundsException var90) {
                     var107 = var90;
                     var10001 = false;
                     break label391;
                  } catch (NumberFormatException var91) {
                     var106 = var91;
                     var10001 = false;
                     break label390;
                  } catch (IllegalArgumentException var92) {
                     var10000 = var92;
                     var10001 = false;
                     break label395;
                  }

                  var3 = var4;

                  label380: {
                     try {
                        if (!checkOffset(var0, var4, '-')) {
                           break label380;
                        }
                     } catch (IndexOutOfBoundsException var87) {
                        var107 = var87;
                        var10001 = false;
                        break label391;
                     } catch (NumberFormatException var88) {
                        var106 = var88;
                        var10001 = false;
                        break label390;
                     } catch (IllegalArgumentException var89) {
                        var10000 = var89;
                        var10001 = false;
                        break label395;
                     }

                     var3 = var4 + 1;
                  }

                  var4 = var3 + 2;

                  int var10;
                  try {
                     var10 = parseInt(var0, var3, var4);
                  } catch (IndexOutOfBoundsException var84) {
                     var107 = var84;
                     var10001 = false;
                     break label391;
                  } catch (NumberFormatException var85) {
                     var106 = var85;
                     var10001 = false;
                     break label390;
                  } catch (IllegalArgumentException var86) {
                     var10000 = var86;
                     var10001 = false;
                     break label395;
                  }

                  var3 = var4;

                  label369: {
                     try {
                        if (!checkOffset(var0, var4, '-')) {
                           break label369;
                        }
                     } catch (IndexOutOfBoundsException var81) {
                        var107 = var81;
                        var10001 = false;
                        break label391;
                     } catch (NumberFormatException var82) {
                        var106 = var82;
                        var10001 = false;
                        break label390;
                     } catch (IllegalArgumentException var83) {
                        var10000 = var83;
                        var10001 = false;
                        break label395;
                     }

                     var3 = var4 + 1;
                  }

                  int var7 = var3 + 2;

                  int var11;
                  boolean var13;
                  try {
                     var11 = parseInt(var0, var3, var7);
                     var13 = checkOffset(var0, var7, 'T');
                  } catch (IndexOutOfBoundsException var78) {
                     var107 = var78;
                     var10001 = false;
                     break label391;
                  } catch (NumberFormatException var79) {
                     var106 = var79;
                     var10001 = false;
                     break label390;
                  } catch (IllegalArgumentException var80) {
                     var10000 = var80;
                     var10001 = false;
                     break label395;
                  }

                  if (!var13) {
                     try {
                        if (var0.length() <= var7) {
                           GregorianCalendar var105 = new GregorianCalendar(var9, var10 - 1, var11);
                           var1.setIndex(var7);
                           return var105.getTime();
                        }
                     } catch (IndexOutOfBoundsException var75) {
                        var107 = var75;
                        var10001 = false;
                        break label391;
                     } catch (NumberFormatException var76) {
                        var106 = var76;
                        var10001 = false;
                        break label390;
                     } catch (IllegalArgumentException var77) {
                        var10000 = var77;
                        var10001 = false;
                        break label395;
                     }
                  }

                  int var5;
                  int var6;
                  label355: {
                     if (var13) {
                        var3 = var7 + 1;
                        var5 = var3 + 2;

                        try {
                           var4 = parseInt(var0, var3, var5);
                        } catch (IndexOutOfBoundsException var60) {
                           var107 = var60;
                           var10001 = false;
                           break label391;
                        } catch (NumberFormatException var61) {
                           var106 = var61;
                           var10001 = false;
                           break label390;
                        } catch (IllegalArgumentException var62) {
                           var10000 = var62;
                           var10001 = false;
                           break label395;
                        }

                        var3 = var5;

                        label351: {
                           try {
                              if (!checkOffset(var0, var5, ':')) {
                                 break label351;
                              }
                           } catch (IndexOutOfBoundsException var72) {
                              var107 = var72;
                              var10001 = false;
                              break label391;
                           } catch (NumberFormatException var73) {
                              var106 = var73;
                              var10001 = false;
                              break label390;
                           } catch (IllegalArgumentException var74) {
                              var10000 = var74;
                              var10001 = false;
                              break label395;
                           }

                           var3 = var5 + 1;
                        }

                        var5 = var3 + 2;

                        try {
                           var6 = parseInt(var0, var3, var5);
                        } catch (IndexOutOfBoundsException var57) {
                           var107 = var57;
                           var10001 = false;
                           break label391;
                        } catch (NumberFormatException var58) {
                           var106 = var58;
                           var10001 = false;
                           break label390;
                        } catch (IllegalArgumentException var59) {
                           var10000 = var59;
                           var10001 = false;
                           break label395;
                        }

                        var3 = var5;

                        label345: {
                           try {
                              if (!checkOffset(var0, var5, ':')) {
                                 break label345;
                              }
                           } catch (IndexOutOfBoundsException var69) {
                              var107 = var69;
                              var10001 = false;
                              break label391;
                           } catch (NumberFormatException var70) {
                              var106 = var70;
                              var10001 = false;
                              break label390;
                           } catch (IllegalArgumentException var71) {
                              var10000 = var71;
                              var10001 = false;
                              break label395;
                           }

                           var3 = var5 + 1;
                        }

                        label340: {
                           char var97;
                           try {
                              if (var0.length() <= var3) {
                                 break label340;
                              }

                              var97 = var0.charAt(var3);
                           } catch (IndexOutOfBoundsException var66) {
                              var107 = var66;
                              var10001 = false;
                              break label391;
                           } catch (NumberFormatException var67) {
                              var106 = var67;
                              var10001 = false;
                              break label390;
                           } catch (IllegalArgumentException var68) {
                              var10000 = var68;
                              var10001 = false;
                              break label395;
                           }

                           if (var97 != 'Z' && var97 != '+' && var97 != '-') {
                              var7 = var3 + 2;

                              try {
                                 var3 = parseInt(var0, var3, var7);
                              } catch (IndexOutOfBoundsException var54) {
                                 var107 = var54;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var55) {
                                 var106 = var55;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var56) {
                                 var10000 = var56;
                                 var10001 = false;
                                 break label395;
                              }

                              var5 = var3;
                              if (var3 > 59) {
                                 var5 = var3;
                                 if (var3 < 63) {
                                    var5 = 59;
                                 }
                              }

                              int var8;
                              label329: {
                                 try {
                                    if (checkOffset(var0, var7, '.')) {
                                       break label329;
                                    }
                                 } catch (IndexOutOfBoundsException var63) {
                                    var107 = var63;
                                    var10001 = false;
                                    break label391;
                                 } catch (NumberFormatException var64) {
                                    var106 = var64;
                                    var10001 = false;
                                    break label390;
                                 } catch (IllegalArgumentException var65) {
                                    var10000 = var65;
                                    var10001 = false;
                                    break label395;
                                 }

                                 var3 = var4;
                                 var8 = 0;
                                 var4 = var6;
                                 var6 = var8;
                                 break label355;
                              }

                              var8 = var7 + 1;

                              int var12;
                              try {
                                 var7 = indexOfNonDigit(var0, var8 + 1);
                                 var12 = Math.min(var7, var8 + 3);
                                 var3 = parseInt(var0, var8, var12);
                              } catch (IndexOutOfBoundsException var51) {
                                 var107 = var51;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var52) {
                                 var106 = var52;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var53) {
                                 var10000 = var53;
                                 var10001 = false;
                                 break label395;
                              }

                              var8 = var12 - var8;
                              if (var8 != 1) {
                                 if (var8 == 2) {
                                    var3 *= 10;
                                 }
                              } else {
                                 var3 *= 100;
                              }

                              var8 = var3;
                              var3 = var4;
                              var4 = var6;
                              var6 = var8;
                              break label355;
                           }
                        }

                        var7 = var3;
                        var3 = var4;
                        var4 = var6;
                     } else {
                        var3 = 0;
                        var4 = 0;
                     }

                     var6 = 0;
                     var5 = 0;
                  }

                  char var2;
                  label310: {
                     try {
                        if (var0.length() > var7) {
                           var2 = var0.charAt(var7);
                           break label310;
                        }
                     } catch (IndexOutOfBoundsException var48) {
                        var107 = var48;
                        var10001 = false;
                        break label391;
                     } catch (NumberFormatException var49) {
                        var106 = var49;
                        var10001 = false;
                        break label390;
                     } catch (IllegalArgumentException var50) {
                        var10000 = var50;
                        var10001 = false;
                        break label395;
                     }

                     try {
                        IllegalArgumentException var14 = new IllegalArgumentException("No time zone indicator");
                        throw var14;
                     } catch (IndexOutOfBoundsException var45) {
                        var107 = var45;
                        var10001 = false;
                        break label391;
                     } catch (NumberFormatException var46) {
                        var106 = var46;
                        var10001 = false;
                        break label390;
                     } catch (IllegalArgumentException var47) {
                        var10000 = var47;
                        var10001 = false;
                        break label395;
                     }
                  }

                  TimeZone var99;
                  label401: {
                     if (var2 == 'Z') {
                        try {
                           var99 = TIMEZONE_UTC;
                        } catch (IndexOutOfBoundsException var33) {
                           var107 = var33;
                           var10001 = false;
                           break label391;
                        } catch (NumberFormatException var34) {
                           var106 = var34;
                           var10001 = false;
                           break label390;
                        } catch (IllegalArgumentException var35) {
                           var10000 = var35;
                           var10001 = false;
                           break label395;
                        }

                        ++var7;
                     } else {
                        label400: {
                           StringBuilder var15;
                           if (var2 != '+' && var2 != '-') {
                              try {
                                 var15 = new StringBuilder();
                                 IndexOutOfBoundsException var101 = new IndexOutOfBoundsException(var15.append("Invalid time zone indicator '").append(var2).append("'").toString());
                                 throw var101;
                              } catch (IndexOutOfBoundsException var18) {
                                 var107 = var18;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var19) {
                                 var106 = var19;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var20) {
                                 var10000 = var20;
                                 var10001 = false;
                                 break label395;
                              }
                           }

                           String var98;
                           label402: {
                              label293:
                              try {
                                 var98 = var0.substring(var7);
                                 if (var98.length() < 5) {
                                    break label293;
                                 }
                                 break label402;
                              } catch (IndexOutOfBoundsException var42) {
                                 var107 = var42;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var43) {
                                 var106 = var43;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var44) {
                                 var10000 = var44;
                                 var10001 = false;
                                 break label395;
                              }

                              try {
                                 var15 = new StringBuilder();
                                 var98 = var15.append(var98).append("00").toString();
                              } catch (IndexOutOfBoundsException var30) {
                                 var107 = var30;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var31) {
                                 var106 = var31;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var32) {
                                 var10000 = var32;
                                 var10001 = false;
                                 break label395;
                              }
                           }

                           label399: {
                              try {
                                 var7 += var98.length();
                                 if (!"+0000".equals(var98) && !"+00:00".equals(var98)) {
                                    break label399;
                                 }
                              } catch (IndexOutOfBoundsException var39) {
                                 var107 = var39;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var40) {
                                 var106 = var40;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var41) {
                                 var10000 = var41;
                                 var10001 = false;
                                 break label395;
                              }

                              try {
                                 var99 = TIMEZONE_UTC;
                                 break label400;
                              } catch (IndexOutOfBoundsException var27) {
                                 var107 = var27;
                                 var10001 = false;
                                 break label391;
                              } catch (NumberFormatException var28) {
                                 var106 = var28;
                                 var10001 = false;
                                 break label390;
                              } catch (IllegalArgumentException var29) {
                                 var10000 = var29;
                                 var10001 = false;
                                 break label395;
                              }
                           }

                           try {
                              var15 = new StringBuilder();
                              var100 = var15.append("GMT").append(var98).toString();
                              var99 = TimeZone.getTimeZone(var100);
                              var16 = var99.getID();
                              if (!var16.equals(var100) && !var16.replace(":", "").equals(var100)) {
                                 break label401;
                              }
                           } catch (IndexOutOfBoundsException var36) {
                              var107 = var36;
                              var10001 = false;
                              break label391;
                           } catch (NumberFormatException var37) {
                              var106 = var37;
                              var10001 = false;
                              break label390;
                           } catch (IllegalArgumentException var38) {
                              var10000 = var38;
                              var10001 = false;
                              break label395;
                           }
                        }
                     }

                     try {
                        GregorianCalendar var104 = new GregorianCalendar(var99);
                        var104.setLenient(false);
                        var104.set(1, var9);
                        var104.set(2, var10 - 1);
                        var104.set(5, var11);
                        var104.set(11, var3);
                        var104.set(12, var4);
                        var104.set(13, var5);
                        var104.set(14, var6);
                        var1.setIndex(var7);
                        return var104.getTime();
                     } catch (IndexOutOfBoundsException var24) {
                        var107 = var24;
                        var10001 = false;
                        break label391;
                     } catch (NumberFormatException var25) {
                        var106 = var25;
                        var10001 = false;
                        break label390;
                     } catch (IllegalArgumentException var26) {
                        var10000 = var26;
                        var10001 = false;
                        break label395;
                     }
                  }

                  try {
                     StringBuilder var17 = new StringBuilder();
                     IndexOutOfBoundsException var102 = new IndexOutOfBoundsException(var17.append("Mismatching time zone indicator: ").append(var100).append(" given, resolves to ").append(var99.getID()).toString());
                     throw var102;
                  } catch (IndexOutOfBoundsException var21) {
                     var107 = var21;
                     var10001 = false;
                     break label391;
                  } catch (NumberFormatException var22) {
                     var106 = var22;
                     var10001 = false;
                     break label390;
                  } catch (IllegalArgumentException var23) {
                     var10000 = var23;
                     var10001 = false;
                  }
               }

               var103 = var10000;
               break label392;
            }

            var103 = var106;
            break label392;
         }

         var103 = var107;
      }

      if (var0 == null) {
         var0 = null;
      } else {
         var0 = '"' + var0 + '"';
      }

      label254: {
         var16 = ((Exception)var103).getMessage();
         if (var16 != null) {
            var100 = var16;
            if (!var16.isEmpty()) {
               break label254;
            }
         }

         var100 = "(" + var103.getClass().getName() + ")";
      }

      ParseException var96 = new ParseException("Failed to parse date [" + var0 + "]: " + var100, var1.getIndex());
      var96.initCause((Throwable)var103);
      throw var96;
   }

   private static int parseInt(String var0, int var1, int var2) throws NumberFormatException {
      if (var1 >= 0 && var2 <= var0.length() && var1 <= var2) {
         int var3;
         int var4;
         if (var1 < var2) {
            var3 = var1 + 1;
            var4 = Character.digit(var0.charAt(var1), 10);
            if (var4 < 0) {
               throw new NumberFormatException("Invalid number: " + var0.substring(var1, var2));
            }

            var4 = -var4;
         } else {
            var4 = 0;
            var3 = var1;
         }

         while(var3 < var2) {
            int var5 = Character.digit(var0.charAt(var3), 10);
            if (var5 < 0) {
               throw new NumberFormatException("Invalid number: " + var0.substring(var1, var2));
            }

            var4 = var4 * 10 - var5;
            ++var3;
         }

         return -var4;
      } else {
         throw new NumberFormatException(var0);
      }
   }
}
