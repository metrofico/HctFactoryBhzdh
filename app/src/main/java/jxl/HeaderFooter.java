package jxl;

public final class HeaderFooter extends jxl.biff.HeaderFooter {
   public HeaderFooter() {
   }

   public HeaderFooter(String var1) {
      super(var1);
   }

   public HeaderFooter(HeaderFooter var1) {
      super((jxl.biff.HeaderFooter)var1);
   }

   public void clear() {
      super.clear();
   }

   protected jxl.biff.HeaderFooter.Contents createContents() {
      return new Contents();
   }

   protected jxl.biff.HeaderFooter.Contents createContents(String var1) {
      return new Contents(var1);
   }

   protected jxl.biff.HeaderFooter.Contents createContents(jxl.biff.HeaderFooter.Contents var1) {
      return new Contents((Contents)var1);
   }

   public Contents getCentre() {
      return (Contents)super.getCentreText();
   }

   public Contents getLeft() {
      return (Contents)super.getLeftText();
   }

   public Contents getRight() {
      return (Contents)super.getRightText();
   }

   public String toString() {
      return super.toString();
   }

   public static class Contents extends jxl.biff.HeaderFooter.Contents {
      Contents() {
      }

      Contents(String var1) {
         super(var1);
      }

      Contents(Contents var1) {
         super((jxl.biff.HeaderFooter.Contents)var1);
      }

      public void append(String var1) {
         super.append(var1);
      }

      public void appendDate() {
         super.appendDate();
      }

      public void appendPageNumber() {
         super.appendPageNumber();
      }

      public void appendTime() {
         super.appendTime();
      }

      public void appendTotalPages() {
         super.appendTotalPages();
      }

      public void appendWorkSheetName() {
         super.appendWorkSheetName();
      }

      public void appendWorkbookName() {
         super.appendWorkbookName();
      }

      public void clear() {
         super.clear();
      }

      public boolean empty() {
         return super.empty();
      }

      public void setFontName(String var1) {
         super.setFontName(var1);
      }

      public boolean setFontSize(int var1) {
         return super.setFontSize(var1);
      }

      public void toggleBold() {
         super.toggleBold();
      }

      public void toggleDoubleUnderline() {
         super.toggleDoubleUnderline();
      }

      public void toggleItalics() {
         super.toggleItalics();
      }

      public void toggleOutline() {
         super.toggleOutline();
      }

      public void toggleShadow() {
         super.toggleShadow();
      }

      public void toggleStrikethrough() {
         super.toggleStrikethrough();
      }

      public void toggleSubScript() {
         super.toggleSubScript();
      }

      public void toggleSuperScript() {
         super.toggleSuperScript();
      }

      public void toggleUnderline() {
         super.toggleUnderline();
      }
   }
}
