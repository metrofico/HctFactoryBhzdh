package jxl.biff;

import jxl.common.Logger;

public abstract class HeaderFooter {
   private static final String BOLD_TOGGLE = "&B";
   private static final String CENTRE = "&C";
   private static final String DATE = "&D";
   private static final String DOUBLE_UNDERLINE_TOGGLE = "&E";
   private static final String ITALICS_TOGGLE = "&I";
   private static final String LEFT_ALIGN = "&L";
   private static final String OUTLINE_TOGGLE = "&O";
   private static final String PAGENUM = "&P";
   private static final String RIGHT_ALIGN = "&R";
   private static final String SHADOW_TOGGLE = "&H";
   private static final String STRIKETHROUGH_TOGGLE = "&S";
   private static final String SUBSCRIPT_TOGGLE = "&Y";
   private static final String SUPERSCRIPT_TOGGLE = "&X";
   private static final String TIME = "&T";
   private static final String TOTAL_PAGENUM = "&N";
   private static final String UNDERLINE_TOGGLE = "&U";
   private static final String WORKBOOK_NAME = "&F";
   private static final String WORKSHEET_NAME = "&A";
   private static Logger logger = Logger.getLogger(HeaderFooter.class);
   private Contents centre;
   private Contents left;
   private Contents right;

   protected HeaderFooter() {
      this.left = this.createContents();
      this.right = this.createContents();
      this.centre = this.createContents();
   }

   protected HeaderFooter(String var1) {
      if (var1 != null && var1.length() != 0) {
         int var5 = var1.indexOf("&L");
         int var4 = var1.indexOf("&R");
         int var3 = var1.indexOf("&C");
         if (var5 == -1 && var4 == -1 && var3 == -1) {
            this.centre = this.createContents(var1);
         } else {
            int var2;
            if (var5 != -1) {
               label84: {
                  var2 = var1.length();
                  if (var3 > var5) {
                     if (var4 <= var5 || var3 <= var4) {
                        var2 = var3;
                        break label84;
                     }
                  } else if (var4 <= var5) {
                     break label84;
                  }

                  var2 = var4;
               }

               this.left = this.createContents(var1.substring(var5 + 2, var2));
            }

            if (var4 != -1) {
               label70: {
                  var2 = var1.length();
                  if (var3 > var4) {
                     if (var5 <= var4 || var3 <= var5) {
                        var2 = var3;
                        break label70;
                     }
                  } else if (var5 <= var4) {
                     break label70;
                  }

                  var2 = var5;
               }

               this.right = this.createContents(var1.substring(var4 + 2, var2));
            }

            if (var3 != -1) {
               var2 = var1.length();
               if (var4 > var3) {
                  if (var5 > var3 && var4 > var5) {
                     var2 = var5;
                  } else {
                     var2 = var4;
                  }
               } else if (var5 > var3) {
                  var2 = var5;
               }

               this.centre = this.createContents(var1.substring(var3 + 2, var2));
            }
         }

         if (this.left == null) {
            this.left = this.createContents();
         }

         if (this.centre == null) {
            this.centre = this.createContents();
         }

         if (this.right == null) {
            this.right = this.createContents();
         }

      } else {
         this.left = this.createContents();
         this.right = this.createContents();
         this.centre = this.createContents();
      }
   }

   protected HeaderFooter(HeaderFooter var1) {
      this.left = this.createContents(var1.left);
      this.right = this.createContents(var1.right);
      this.centre = this.createContents(var1.centre);
   }

   protected void clear() {
      this.left.clear();
      this.right.clear();
      this.centre.clear();
   }

   protected abstract Contents createContents();

   protected abstract Contents createContents(String var1);

   protected abstract Contents createContents(Contents var1);

   protected Contents getCentreText() {
      return this.centre;
   }

   protected Contents getLeftText() {
      return this.left;
   }

   protected Contents getRightText() {
      return this.right;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      if (!this.left.empty()) {
         var1.append("&L");
         var1.append(this.left.getContents());
      }

      if (!this.centre.empty()) {
         var1.append("&C");
         var1.append(this.centre.getContents());
      }

      if (!this.right.empty()) {
         var1.append("&R");
         var1.append(this.right.getContents());
      }

      return var1.toString();
   }

   protected static class Contents {
      private StringBuffer contents;

      protected Contents() {
         this.contents = new StringBuffer();
      }

      protected Contents(String var1) {
         this.contents = new StringBuffer(var1);
      }

      protected Contents(Contents var1) {
         this.contents = new StringBuffer(var1.getContents());
      }

      private void appendInternal(char var1) {
         if (this.contents == null) {
            this.contents = new StringBuffer();
         }

         this.contents.append(var1);
      }

      private void appendInternal(String var1) {
         if (this.contents == null) {
            this.contents = new StringBuffer();
         }

         this.contents.append(var1);
      }

      protected void append(String var1) {
         this.appendInternal(var1);
      }

      protected void appendDate() {
         this.appendInternal("&D");
      }

      protected void appendPageNumber() {
         this.appendInternal("&P");
      }

      protected void appendTime() {
         this.appendInternal("&T");
      }

      protected void appendTotalPages() {
         this.appendInternal("&N");
      }

      protected void appendWorkSheetName() {
         this.appendInternal("&A");
      }

      protected void appendWorkbookName() {
         this.appendInternal("&F");
      }

      protected void clear() {
         this.contents = null;
      }

      protected boolean empty() {
         StringBuffer var1 = this.contents;
         return var1 == null || var1.length() == 0;
      }

      protected String getContents() {
         StringBuffer var1 = this.contents;
         String var2;
         if (var1 != null) {
            var2 = var1.toString();
         } else {
            var2 = "";
         }

         return var2;
      }

      protected void setFontName(String var1) {
         this.appendInternal("&\"");
         this.appendInternal(var1);
         this.appendInternal('"');
      }

      protected boolean setFontSize(int var1) {
         if (var1 >= 1 && var1 <= 99) {
            String var2;
            if (var1 < 10) {
               var2 = "0" + var1;
            } else {
               var2 = Integer.toString(var1);
            }

            this.appendInternal('&');
            this.appendInternal(var2);
            return true;
         } else {
            return false;
         }
      }

      protected void toggleBold() {
         this.appendInternal("&B");
      }

      protected void toggleDoubleUnderline() {
         this.appendInternal("&E");
      }

      protected void toggleItalics() {
         this.appendInternal("&I");
      }

      protected void toggleOutline() {
         this.appendInternal("&O");
      }

      protected void toggleShadow() {
         this.appendInternal("&H");
      }

      protected void toggleStrikethrough() {
         this.appendInternal("&S");
      }

      protected void toggleSubScript() {
         this.appendInternal("&Y");
      }

      protected void toggleSuperScript() {
         this.appendInternal("&X");
      }

      protected void toggleUnderline() {
         this.appendInternal("&U");
      }
   }
}
