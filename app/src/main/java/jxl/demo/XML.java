package jxl.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.Font;
import jxl.format.Pattern;

public class XML {
   private String encoding;
   private OutputStream out;
   private Workbook workbook;

   public XML(Workbook var1, OutputStream var2, String var3, boolean var4) throws IOException {
      this.encoding = var3;
      this.workbook = var1;
      this.out = var2;
      if (var3 == null || !var3.equals("UnicodeBig")) {
         this.encoding = "UTF8";
      }

      if (var4) {
         this.writeFormattedXML();
      } else {
         this.writeXML();
      }

   }

   private void writeFormattedXML() throws IOException {
      UnsupportedEncodingException var10000;
      label140: {
         BufferedWriter var4;
         boolean var10001;
         try {
            OutputStreamWriter var5 = new OutputStreamWriter(this.out, this.encoding);
            var4 = new BufferedWriter(var5);
            var4.write("<?xml version=\"1.0\" ?>");
            var4.newLine();
            var4.write("<!DOCTYPE workbook SYSTEM \"formatworkbook.dtd\">");
            var4.newLine();
            var4.newLine();
            var4.write("<workbook>");
            var4.newLine();
         } catch (UnsupportedEncodingException var23) {
            var10000 = var23;
            var10001 = false;
            break label140;
         }

         int var1 = 0;

         while(true) {
            StringBuilder var6;
            Sheet var25;
            try {
               if (var1 >= this.workbook.getNumberOfSheets()) {
                  break;
               }

               var25 = this.workbook.getSheet(var1);
               var4.write("  <sheet>");
               var4.newLine();
               var6 = new StringBuilder();
               var4.write(var6.append("    <name><![CDATA[").append(var25.getName()).append("]]></name>").toString());
               var4.newLine();
            } catch (UnsupportedEncodingException var17) {
               var10000 = var17;
               var10001 = false;
               break label140;
            }

            int var2 = 0;

            while(true) {
               Cell[] var7;
               try {
                  if (var2 >= var25.getRows()) {
                     break;
                  }

                  var6 = new StringBuilder();
                  var4.write(var6.append("    <row number=\"").append(var2).append("\">").toString());
                  var4.newLine();
                  var7 = var25.getRow(var2);
               } catch (UnsupportedEncodingException var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label140;
               }

               int var3 = 0;

               while(true) {
                  label143: {
                     try {
                        if (var3 >= var7.length) {
                           break;
                        }

                        if (var7[var3].getType() == CellType.EMPTY && var7[var3].getCellFormat() == null) {
                           break label143;
                        }
                     } catch (UnsupportedEncodingException var22) {
                        var10000 = var22;
                        var10001 = false;
                        break label140;
                     }

                     label145: {
                        StringBuilder var8;
                        CellFormat var26;
                        label114: {
                           try {
                              var26 = var7[var3].getCellFormat();
                              var8 = new StringBuilder();
                              var4.write(var8.append("      <col number=\"").append(var3).append("\">").toString());
                              var4.newLine();
                              var4.write("        <data>");
                              var8 = new StringBuilder();
                              var4.write(var8.append("<![CDATA[").append(var7[var3].getContents()).append("]]>").toString());
                              var4.write("</data>");
                              var4.newLine();
                              if (var7[var3].getCellFormat() == null) {
                                 break label145;
                              }

                              var8 = new StringBuilder();
                              var4.write(var8.append("        <format wrap=\"").append(var26.getWrap()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                align=\"").append(var26.getAlignment().getDescription()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                valign=\"").append(var26.getVerticalAlignment().getDescription()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                orientation=\"").append(var26.getOrientation().getDescription()).append("\"").toString());
                              var4.write(">");
                              var4.newLine();
                              Font var27 = var26.getFont();
                              StringBuilder var9 = new StringBuilder();
                              var4.write(var9.append("          <font name=\"").append(var27.getName()).append("\"").toString());
                              var4.newLine();
                              var9 = new StringBuilder();
                              var4.write(var9.append("                point_size=\"").append(var27.getPointSize()).append("\"").toString());
                              var4.newLine();
                              var9 = new StringBuilder();
                              var4.write(var9.append("                bold_weight=\"").append(var27.getBoldWeight()).append("\"").toString());
                              var4.newLine();
                              var9 = new StringBuilder();
                              var4.write(var9.append("                italic=\"").append(var27.isItalic()).append("\"").toString());
                              var4.newLine();
                              var9 = new StringBuilder();
                              var4.write(var9.append("                underline=\"").append(var27.getUnderlineStyle().getDescription()).append("\"").toString());
                              var4.newLine();
                              var9 = new StringBuilder();
                              var4.write(var9.append("                colour=\"").append(var27.getColour().getDescription()).append("\"").toString());
                              var4.newLine();
                              var9 = new StringBuilder();
                              var4.write(var9.append("                script=\"").append(var27.getScriptStyle().getDescription()).append("\"").toString());
                              var4.write(" />");
                              var4.newLine();
                              if (var26.getBackgroundColour() == Colour.DEFAULT_BACKGROUND && var26.getPattern() == Pattern.NONE) {
                                 break label114;
                              }
                           } catch (UnsupportedEncodingException var21) {
                              var10000 = var21;
                              var10001 = false;
                              break label140;
                           }

                           try {
                              var8 = new StringBuilder();
                              var4.write(var8.append("          <background colour=\"").append(var26.getBackgroundColour().getDescription()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                      pattern=\"").append(var26.getPattern().getDescription()).append("\"").toString());
                              var4.write(" />");
                              var4.newLine();
                           } catch (UnsupportedEncodingException var16) {
                              var10000 = var16;
                              var10001 = false;
                              break label140;
                           }
                        }

                        label105: {
                           try {
                              if (var26.getBorder(Border.TOP) == BorderLineStyle.NONE && var26.getBorder(Border.BOTTOM) == BorderLineStyle.NONE && var26.getBorder(Border.LEFT) == BorderLineStyle.NONE && var26.getBorder(Border.RIGHT) == BorderLineStyle.NONE) {
                                 break label105;
                              }
                           } catch (UnsupportedEncodingException var20) {
                              var10000 = var20;
                              var10001 = false;
                              break label140;
                           }

                           try {
                              var8 = new StringBuilder();
                              var4.write(var8.append("          <border top=\"").append(var26.getBorder(Border.TOP).getDescription()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                  bottom=\"").append(var26.getBorder(Border.BOTTOM).getDescription()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                  left=\"").append(var26.getBorder(Border.LEFT).getDescription()).append("\"").toString());
                              var4.newLine();
                              var8 = new StringBuilder();
                              var4.write(var8.append("                  right=\"").append(var26.getBorder(Border.RIGHT).getDescription()).append("\"").toString());
                              var4.write(" />");
                              var4.newLine();
                           } catch (UnsupportedEncodingException var15) {
                              var10000 = var15;
                              var10001 = false;
                              break label140;
                           }
                        }

                        try {
                           if (!var26.getFormat().getFormatString().equals("")) {
                              var4.write("          <format_string string=\"");
                              var4.write(var26.getFormat().getFormatString());
                              var4.write("\" />");
                              var4.newLine();
                           }
                        } catch (UnsupportedEncodingException var19) {
                           var10000 = var19;
                           var10001 = false;
                           break label140;
                        }

                        try {
                           var4.write("        </format>");
                           var4.newLine();
                        } catch (UnsupportedEncodingException var14) {
                           var10000 = var14;
                           var10001 = false;
                           break label140;
                        }
                     }

                     try {
                        var4.write("      </col>");
                        var4.newLine();
                     } catch (UnsupportedEncodingException var13) {
                        var10000 = var13;
                        var10001 = false;
                        break label140;
                     }
                  }

                  ++var3;
               }

               try {
                  var4.write("    </row>");
                  var4.newLine();
               } catch (UnsupportedEncodingException var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label140;
               }

               ++var2;
            }

            try {
               var4.write("  </sheet>");
               var4.newLine();
            } catch (UnsupportedEncodingException var11) {
               var10000 = var11;
               var10001 = false;
               break label140;
            }

            ++var1;
         }

         try {
            var4.write("</workbook>");
            var4.newLine();
            var4.flush();
            var4.close();
            return;
         } catch (UnsupportedEncodingException var10) {
            var10000 = var10;
            var10001 = false;
         }
      }

      UnsupportedEncodingException var24 = var10000;
      System.err.println(var24.toString());
   }

   private void writeXML() throws IOException {
      UnsupportedEncodingException var10000;
      label79: {
         BufferedWriter var4;
         boolean var10001;
         try {
            OutputStreamWriter var5 = new OutputStreamWriter(this.out, this.encoding);
            var4 = new BufferedWriter(var5);
            var4.write("<?xml version=\"1.0\" ?>");
            var4.newLine();
            var4.write("<!DOCTYPE workbook SYSTEM \"workbook.dtd\">");
            var4.newLine();
            var4.newLine();
            var4.write("<workbook>");
            var4.newLine();
         } catch (UnsupportedEncodingException var14) {
            var10000 = var14;
            var10001 = false;
            break label79;
         }

         int var1 = 0;

         while(true) {
            StringBuilder var6;
            Sheet var16;
            try {
               if (var1 >= this.workbook.getNumberOfSheets()) {
                  break;
               }

               var16 = this.workbook.getSheet(var1);
               var4.write("  <sheet>");
               var4.newLine();
               var6 = new StringBuilder();
               var4.write(var6.append("    <name><![CDATA[").append(var16.getName()).append("]]></name>").toString());
               var4.newLine();
            } catch (UnsupportedEncodingException var11) {
               var10000 = var11;
               var10001 = false;
               break label79;
            }

            int var2 = 0;

            while(true) {
               Cell[] var17;
               try {
                  if (var2 >= var16.getRows()) {
                     break;
                  }

                  var6 = new StringBuilder();
                  var4.write(var6.append("    <row number=\"").append(var2).append("\">").toString());
                  var4.newLine();
                  var17 = var16.getRow(var2);
               } catch (UnsupportedEncodingException var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label79;
               }

               int var3 = 0;

               while(true) {
                  try {
                     if (var3 >= var17.length) {
                        break;
                     }

                     if (var17[var3].getType() != CellType.EMPTY) {
                        StringBuilder var7 = new StringBuilder();
                        var4.write(var7.append("      <col number=\"").append(var3).append("\">").toString());
                        var7 = new StringBuilder();
                        var4.write(var7.append("<![CDATA[").append(var17[var3].getContents()).append("]]>").toString());
                        var4.write("</col>");
                        var4.newLine();
                     }
                  } catch (UnsupportedEncodingException var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label79;
                  }

                  ++var3;
               }

               try {
                  var4.write("    </row>");
                  var4.newLine();
               } catch (UnsupportedEncodingException var10) {
                  var10000 = var10;
                  var10001 = false;
                  break label79;
               }

               ++var2;
            }

            try {
               var4.write("  </sheet>");
               var4.newLine();
            } catch (UnsupportedEncodingException var9) {
               var10000 = var9;
               var10001 = false;
               break label79;
            }

            ++var1;
         }

         try {
            var4.write("</workbook>");
            var4.newLine();
            var4.flush();
            var4.close();
            return;
         } catch (UnsupportedEncodingException var8) {
            var10000 = var8;
            var10001 = false;
         }
      }

      UnsupportedEncodingException var15 = var10000;
      System.err.println(var15.toString());
   }
}
