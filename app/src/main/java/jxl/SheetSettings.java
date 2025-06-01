package jxl;

import jxl.biff.SheetRangeImpl;
import jxl.common.Assert;
import jxl.format.PageOrder;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;

public final class SheetSettings {
   private static final int DEFAULT_DEFAULT_COLUMN_WIDTH = 8;
   public static final int DEFAULT_DEFAULT_ROW_HEIGHT = 255;
   private static final double DEFAULT_FOOTER_MARGIN = 0.5;
   private static final double DEFAULT_HEADER_MARGIN = 0.5;
   private static final double DEFAULT_HEIGHT_MARGIN = 1.0;
   private static final int DEFAULT_NORMAL_MAGNIFICATION = 100;
   private static final PageOrder DEFAULT_ORDER;
   private static final PageOrientation DEFAULT_ORIENTATION;
   private static final int DEFAULT_PAGE_BREAK_PREVIEW_MAGNIFICATION = 60;
   private static final PaperSize DEFAULT_PAPER_SIZE;
   private static final int DEFAULT_PRINT_RESOLUTION = 300;
   private static final double DEFAULT_WIDTH_MARGIN = 0.75;
   private static final int DEFAULT_ZOOM_FACTOR = 100;
   private boolean automaticFormulaCalculation;
   private double bottomMargin;
   private int copies;
   private int defaultColumnWidth;
   private int defaultRowHeight;
   private boolean displayZeroValues;
   private int fitHeight;
   private boolean fitToPages;
   private int fitWidth;
   private HeaderFooter footer;
   private double footerMargin;
   private HeaderFooter header;
   private double headerMargin;
   private boolean hidden;
   private boolean horizontalCentre;
   private int horizontalFreeze;
   private int horizontalPrintResolution;
   private double leftMargin;
   private int normalMagnification;
   private PageOrientation orientation;
   private int pageBreakPreviewMagnification;
   private boolean pageBreakPreviewMode;
   private PageOrder pageOrder;
   private int pageStart;
   private PaperSize paperSize;
   private String password;
   private int passwordHash;
   private Range printArea;
   private boolean printGridLines;
   private boolean printHeaders;
   private Range printTitlesCol;
   private Range printTitlesRow;
   private boolean recalculateFormulasBeforeSave;
   private double rightMargin;
   private int scaleFactor;
   private boolean selected;
   private Sheet sheet;
   private boolean sheetProtected;
   private boolean showGridLines;
   private double topMargin;
   private boolean verticalCentre;
   private int verticalFreeze;
   private int verticalPrintResolution;
   private int zoomFactor;

   static {
      DEFAULT_ORIENTATION = PageOrientation.PORTRAIT;
      DEFAULT_ORDER = PageOrder.RIGHT_THEN_DOWN;
      DEFAULT_PAPER_SIZE = PaperSize.A4;
   }

   public SheetSettings(Sheet var1) {
      this.sheet = var1;
      this.orientation = DEFAULT_ORIENTATION;
      this.pageOrder = DEFAULT_ORDER;
      this.paperSize = DEFAULT_PAPER_SIZE;
      this.sheetProtected = false;
      this.hidden = false;
      this.selected = false;
      this.headerMargin = 0.5;
      this.footerMargin = 0.5;
      this.horizontalPrintResolution = 300;
      this.verticalPrintResolution = 300;
      this.leftMargin = 0.75;
      this.rightMargin = 0.75;
      this.topMargin = 1.0;
      this.bottomMargin = 1.0;
      this.fitToPages = false;
      this.showGridLines = true;
      this.printGridLines = false;
      this.printHeaders = false;
      this.pageBreakPreviewMode = false;
      this.displayZeroValues = true;
      this.defaultColumnWidth = 8;
      this.defaultRowHeight = 255;
      this.zoomFactor = 100;
      this.pageBreakPreviewMagnification = 60;
      this.normalMagnification = 100;
      this.horizontalFreeze = 0;
      this.verticalFreeze = 0;
      this.copies = 1;
      this.header = new HeaderFooter();
      this.footer = new HeaderFooter();
      this.automaticFormulaCalculation = true;
      this.recalculateFormulasBeforeSave = true;
   }

   public SheetSettings(SheetSettings var1, Sheet var2) {
      boolean var3;
      if (var1 != null) {
         var3 = true;
      } else {
         var3 = false;
      }

      Assert.verify(var3);
      this.sheet = var2;
      this.orientation = var1.orientation;
      this.pageOrder = var1.pageOrder;
      this.paperSize = var1.paperSize;
      this.sheetProtected = var1.sheetProtected;
      this.hidden = var1.hidden;
      this.selected = false;
      this.headerMargin = var1.headerMargin;
      this.footerMargin = var1.footerMargin;
      this.scaleFactor = var1.scaleFactor;
      this.pageStart = var1.pageStart;
      this.fitWidth = var1.fitWidth;
      this.fitHeight = var1.fitHeight;
      this.horizontalPrintResolution = var1.horizontalPrintResolution;
      this.verticalPrintResolution = var1.verticalPrintResolution;
      this.leftMargin = var1.leftMargin;
      this.rightMargin = var1.rightMargin;
      this.topMargin = var1.topMargin;
      this.bottomMargin = var1.bottomMargin;
      this.fitToPages = var1.fitToPages;
      this.password = var1.password;
      this.passwordHash = var1.passwordHash;
      this.defaultColumnWidth = var1.defaultColumnWidth;
      this.defaultRowHeight = var1.defaultRowHeight;
      this.zoomFactor = var1.zoomFactor;
      this.pageBreakPreviewMagnification = var1.pageBreakPreviewMagnification;
      this.normalMagnification = var1.normalMagnification;
      this.showGridLines = var1.showGridLines;
      this.displayZeroValues = var1.displayZeroValues;
      this.pageBreakPreviewMode = var1.pageBreakPreviewMode;
      this.horizontalFreeze = var1.horizontalFreeze;
      this.verticalFreeze = var1.verticalFreeze;
      this.horizontalCentre = var1.horizontalCentre;
      this.verticalCentre = var1.verticalCentre;
      this.copies = var1.copies;
      this.header = new HeaderFooter(var1.header);
      this.footer = new HeaderFooter(var1.footer);
      this.automaticFormulaCalculation = var1.automaticFormulaCalculation;
      this.recalculateFormulasBeforeSave = var1.recalculateFormulasBeforeSave;
      if (var1.printArea != null) {
         this.printArea = new SheetRangeImpl(this.sheet, var1.getPrintArea().getTopLeft().getColumn(), var1.getPrintArea().getTopLeft().getRow(), var1.getPrintArea().getBottomRight().getColumn(), var1.getPrintArea().getBottomRight().getRow());
      }

      if (var1.printTitlesRow != null) {
         this.printTitlesRow = new SheetRangeImpl(this.sheet, var1.getPrintTitlesRow().getTopLeft().getColumn(), var1.getPrintTitlesRow().getTopLeft().getRow(), var1.getPrintTitlesRow().getBottomRight().getColumn(), var1.getPrintTitlesRow().getBottomRight().getRow());
      }

      if (var1.printTitlesCol != null) {
         this.printTitlesCol = new SheetRangeImpl(this.sheet, var1.getPrintTitlesCol().getTopLeft().getColumn(), var1.getPrintTitlesCol().getTopLeft().getRow(), var1.getPrintTitlesCol().getBottomRight().getColumn(), var1.getPrintTitlesCol().getBottomRight().getRow());
      }

   }

   public boolean getAutomaticFormulaCalculation() {
      return this.automaticFormulaCalculation;
   }

   public double getBottomMargin() {
      return this.bottomMargin;
   }

   public int getCopies() {
      return this.copies;
   }

   public int getDefaultColumnWidth() {
      return this.defaultColumnWidth;
   }

   public double getDefaultHeightMargin() {
      return 1.0;
   }

   public int getDefaultRowHeight() {
      return this.defaultRowHeight;
   }

   public double getDefaultWidthMargin() {
      return 0.75;
   }

   public boolean getDisplayZeroValues() {
      return this.displayZeroValues;
   }

   public int getFitHeight() {
      return this.fitHeight;
   }

   public boolean getFitToPages() {
      return this.fitToPages;
   }

   public int getFitWidth() {
      return this.fitWidth;
   }

   public HeaderFooter getFooter() {
      return this.footer;
   }

   public double getFooterMargin() {
      return this.footerMargin;
   }

   public HeaderFooter getHeader() {
      return this.header;
   }

   public double getHeaderMargin() {
      return this.headerMargin;
   }

   public int getHorizontalFreeze() {
      return this.horizontalFreeze;
   }

   public int getHorizontalPrintResolution() {
      return this.horizontalPrintResolution;
   }

   public double getLeftMargin() {
      return this.leftMargin;
   }

   public int getNormalMagnification() {
      return this.normalMagnification;
   }

   public PageOrientation getOrientation() {
      return this.orientation;
   }

   public int getPageBreakPreviewMagnification() {
      return this.pageBreakPreviewMagnification;
   }

   public boolean getPageBreakPreviewMode() {
      return this.pageBreakPreviewMode;
   }

   public PageOrder getPageOrder() {
      return this.pageOrder;
   }

   public int getPageStart() {
      return this.pageStart;
   }

   public PaperSize getPaperSize() {
      return this.paperSize;
   }

   public String getPassword() {
      return this.password;
   }

   public int getPasswordHash() {
      return this.passwordHash;
   }

   public Range getPrintArea() {
      return this.printArea;
   }

   public boolean getPrintGridLines() {
      return this.printGridLines;
   }

   public boolean getPrintHeaders() {
      return this.printHeaders;
   }

   public Range getPrintTitlesCol() {
      return this.printTitlesCol;
   }

   public Range getPrintTitlesRow() {
      return this.printTitlesRow;
   }

   public boolean getRecalculateFormulasBeforeSave() {
      return this.recalculateFormulasBeforeSave;
   }

   public double getRightMargin() {
      return this.rightMargin;
   }

   public int getScaleFactor() {
      return this.scaleFactor;
   }

   public boolean getShowGridLines() {
      return this.showGridLines;
   }

   public double getTopMargin() {
      return this.topMargin;
   }

   public int getVerticalFreeze() {
      return this.verticalFreeze;
   }

   public int getVerticalPrintResolution() {
      return this.verticalPrintResolution;
   }

   public int getZoomFactor() {
      return this.zoomFactor;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public boolean isHorizontalCentre() {
      return this.horizontalCentre;
   }

   public boolean isProtected() {
      return this.sheetProtected;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public boolean isVerticalCentre() {
      return this.verticalCentre;
   }

   public void setAutomaticFormulaCalculation(boolean var1) {
      this.automaticFormulaCalculation = var1;
   }

   public void setBottomMargin(double var1) {
      this.bottomMargin = var1;
   }

   public void setCopies(int var1) {
      this.copies = var1;
   }

   public void setDefaultColumnWidth(int var1) {
      this.defaultColumnWidth = var1;
   }

   public void setDefaultRowHeight(int var1) {
      this.defaultRowHeight = var1;
   }

   public void setDisplayZeroValues(boolean var1) {
      this.displayZeroValues = var1;
   }

   public void setFitHeight(int var1) {
      this.fitHeight = var1;
      this.fitToPages = true;
   }

   public void setFitToPages(boolean var1) {
      this.fitToPages = var1;
   }

   public void setFitWidth(int var1) {
      this.fitWidth = var1;
      this.fitToPages = true;
   }

   public void setFooter(HeaderFooter var1) {
      this.footer = var1;
   }

   public void setFooterMargin(double var1) {
      this.footerMargin = var1;
   }

   public void setHeader(HeaderFooter var1) {
      this.header = var1;
   }

   public void setHeaderMargin(double var1) {
      this.headerMargin = var1;
   }

   public void setHidden(boolean var1) {
      this.hidden = var1;
   }

   public void setHorizontalCentre(boolean var1) {
      this.horizontalCentre = var1;
   }

   public void setHorizontalFreeze(int var1) {
      this.horizontalFreeze = Math.max(var1, 0);
   }

   public void setHorizontalPrintResolution(int var1) {
      this.horizontalPrintResolution = var1;
   }

   public void setLeftMargin(double var1) {
      this.leftMargin = var1;
   }

   public void setNormalMagnification(int var1) {
      this.normalMagnification = var1;
   }

   public void setOrientation(PageOrientation var1) {
      this.orientation = var1;
   }

   public void setPageBreakPreviewMagnification(int var1) {
      this.pageBreakPreviewMagnification = var1;
   }

   public void setPageBreakPreviewMode(boolean var1) {
      this.pageBreakPreviewMode = var1;
   }

   public void setPageOrder(PageOrder var1) {
      this.pageOrder = var1;
   }

   public void setPageStart(int var1) {
      this.pageStart = var1;
   }

   public void setPaperSize(PaperSize var1) {
      this.paperSize = var1;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   public void setPasswordHash(int var1) {
      this.passwordHash = var1;
   }

   public void setPrintArea(int var1, int var2, int var3, int var4) {
      this.printArea = new SheetRangeImpl(this.sheet, var1, var2, var3, var4);
   }

   public void setPrintGridLines(boolean var1) {
      this.printGridLines = var1;
   }

   public void setPrintHeaders(boolean var1) {
      this.printHeaders = var1;
   }

   public void setPrintTitles(int var1, int var2, int var3, int var4) {
      this.setPrintTitlesRow(var1, var2);
      this.setPrintTitlesCol(var3, var4);
   }

   public void setPrintTitlesCol(int var1, int var2) {
      this.printTitlesCol = new SheetRangeImpl(this.sheet, var1, 0, var2, 65535);
   }

   public void setPrintTitlesRow(int var1, int var2) {
      this.printTitlesRow = new SheetRangeImpl(this.sheet, 0, var1, 255, var2);
   }

   public void setProtected(boolean var1) {
      this.sheetProtected = var1;
   }

   public void setRecalculateFormulasBeforeSave(boolean var1) {
      this.recalculateFormulasBeforeSave = var1;
   }

   public void setRightMargin(double var1) {
      this.rightMargin = var1;
   }

   public void setScaleFactor(int var1) {
      this.scaleFactor = var1;
      this.fitToPages = false;
   }

   public void setSelected() {
      this.setSelected(true);
   }

   public void setSelected(boolean var1) {
      this.selected = var1;
   }

   public void setShowGridLines(boolean var1) {
      this.showGridLines = var1;
   }

   public void setTopMargin(double var1) {
      this.topMargin = var1;
   }

   public void setVerticalCentre(boolean var1) {
      this.verticalCentre = var1;
   }

   public void setVerticalFreeze(int var1) {
      this.verticalFreeze = Math.max(var1, 0);
   }

   public void setVerticalPrintResolution(int var1) {
      this.verticalPrintResolution = var1;
   }

   public void setZoomFactor(int var1) {
      this.zoomFactor = var1;
   }
}
