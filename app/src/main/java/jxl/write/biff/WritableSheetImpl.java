package jxl.write.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Pattern;
import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellReferenceHelper;
import jxl.CellType;
import jxl.CellView;
import jxl.HeaderFooter;
import jxl.Hyperlink;
import jxl.Image;
import jxl.LabelCell;
import jxl.Range;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.WorkbookSettings;
import jxl.biff.AutoFilter;
import jxl.biff.CellFinder;
import jxl.biff.ConditionalFormat;
import jxl.biff.DVParser;
import jxl.biff.DataValidation;
import jxl.biff.EmptyCell;
import jxl.biff.FormattingRecords;
import jxl.biff.IndexMapping;
import jxl.biff.NumFormatRecordsException;
import jxl.biff.SheetRangeImpl;
import jxl.biff.WorkspaceInformationRecord;
import jxl.biff.XFRecord;
import jxl.biff.drawing.Chart;
import jxl.biff.drawing.ComboBox;
import jxl.biff.drawing.Drawing;
import jxl.biff.drawing.DrawingGroupObject;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;
import jxl.format.Font;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableHyperlink;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

class WritableSheetImpl implements WritableSheet {
   private static final char[] illegalSheetNameCharacters = new char[]{'*', ':', '?', '\\'};
   private static final String[] imageTypes = new String[]{"png"};
   private static Logger logger = Logger.getLogger(WritableSheetImpl.class);
   private static final int maxSheetNameLength = 31;
   private static final int numRowsPerSheet = 65536;
   private static final int rowGrowSize = 10;
   private AutoFilter autoFilter;
   private TreeSet autosizedColumns;
   private ButtonPropertySetRecord buttonPropertySet;
   private boolean chartOnly;
   private ArrayList columnBreaks;
   private TreeSet columnFormats;
   private ComboBox comboBox;
   private ArrayList conditionalFormats;
   private DataValidation dataValidation;
   private ArrayList drawings;
   private boolean drawingsModified;
   private FormattingRecords formatRecords;
   private ArrayList hyperlinks;
   private ArrayList images;
   private int maxColumnOutlineLevel;
   private int maxRowOutlineLevel;
   private MergedCells mergedCells;
   private String name;
   private int numColumns;
   private int numRows;
   private File outputFile;
   private PLSRecord plsRecord;
   private ArrayList rowBreaks;
   private RowRecord[] rows;
   private SheetSettings settings;
   private SharedStrings sharedStrings;
   private SheetWriter sheetWriter;
   private ArrayList validatedCells;
   private WritableWorkbookImpl workbook;
   private WorkbookSettings workbookSettings;

   public WritableSheetImpl(String var1, File var2, FormattingRecords var3, SharedStrings var4, WorkbookSettings var5, WritableWorkbookImpl var6) {
      this.name = this.validateName(var1);
      this.outputFile = var2;
      this.rows = new RowRecord[0];
      this.numRows = 0;
      this.numColumns = 0;
      this.chartOnly = false;
      this.workbook = var6;
      this.formatRecords = var3;
      this.sharedStrings = var4;
      this.workbookSettings = var5;
      this.drawingsModified = false;
      this.columnFormats = new TreeSet(new ColumnInfoComparator());
      this.autosizedColumns = new TreeSet();
      this.hyperlinks = new ArrayList();
      this.mergedCells = new MergedCells(this);
      this.rowBreaks = new ArrayList();
      this.columnBreaks = new ArrayList();
      this.drawings = new ArrayList();
      this.images = new ArrayList();
      this.conditionalFormats = new ArrayList();
      this.validatedCells = new ArrayList();
      this.settings = new SheetSettings(this);
      this.sheetWriter = new SheetWriter(this.outputFile, this, this.workbookSettings);
   }

   private void autosizeColumn(int var1) {
      ColumnInfoRecord var10 = this.getColumnInfo(var1);
      Font var8 = var10.getCellFormat().getFont();
      Font var11 = WritableWorkbook.NORMAL_STYLE.getFont();
      int var3 = 0;

      int var2;
      int var4;
      for(var2 = 0; var3 < this.numRows; var2 = var4) {
         CellValue var7 = null;
         RowRecord var9 = this.rows[var3];
         if (var9 != null) {
            var7 = var9.getCell(var1);
         }

         var4 = var2;
         if (var7 != null) {
            String var12 = var7.getContents();
            Font var14 = var7.getCellFormat().getFont();
            Font var13 = var14;
            if (var14.equals(var11)) {
               var13 = var8;
            }

            int var6;
            label28: {
               var6 = var13.getPointSize();
               int var5 = var12.length();
               if (!var13.isItalic()) {
                  var4 = var5;
                  if (var13.getBoldWeight() <= 400) {
                     break label28;
                  }
               }

               var4 = var5 + 2;
            }

            var4 = Math.max(var2, var4 * var6 * 256);
         }

         ++var3;
      }

      var10.setWidth(var2 / var11.getPointSize());
   }

   private void autosizeColumns() {
      Iterator var1 = this.autosizedColumns.iterator();

      while(var1.hasNext()) {
         this.autosizeColumn((Integer)var1.next());
      }

   }

   private DrawingGroupObject[] getDrawings() {
      DrawingGroupObject[] var1 = new DrawingGroupObject[this.drawings.size()];
      return (DrawingGroupObject[])this.drawings.toArray(var1);
   }

   private WorkspaceInformationRecord getWorkspaceOptions() {
      return this.sheetWriter.getWorkspaceOptions();
   }

   private String validateName(String var1) {
      int var2 = var1.length();
      byte var3 = 0;
      String var4 = var1;
      if (var2 > 31) {
         logger.warn("Sheet name " + var1 + " too long - truncating");
         var4 = var1.substring(0, 31);
      }

      var2 = var3;
      var1 = var4;
      if (var4.charAt(0) == '\'') {
         logger.warn("Sheet naming cannot start with ' - removing");
         var1 = var4.substring(1);
         var2 = var3;
      }

      while(true) {
         char[] var5 = illegalSheetNameCharacters;
         if (var2 >= var5.length) {
            return var1;
         }

         var4 = var1.replace(var5[var2], '@');
         if (var1 != var4) {
            logger.warn(var5[var2] + " is not a valid character within a sheet name - replacing");
         }

         ++var2;
         var1 = var4;
      }
   }

   public void addCell(WritableCell var1) throws WriteException, RowsExceededException {
      if (var1.getType() != CellType.EMPTY || var1 == null || var1.getCellFormat() != null) {
         CellValue var6 = (CellValue)var1;
         if (var6.isReferenced()) {
            throw new JxlWriteException(JxlWriteException.cellReferenced);
         } else {
            int var3 = var1.getRow();
            RowRecord var7 = this.getRowRecord(var3);
            CellValue var8 = var7.getCell(var6.getColumn());
            boolean var2;
            if (var8 != null && var8.getCellFeatures() != null && var8.getCellFeatures().getDVParser() != null && var8.getCellFeatures().getDVParser().extendedCellsValidation()) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var1.getCellFeatures() != null && var1.getCellFeatures().hasDataValidation() && var2) {
               DVParser var9 = var8.getCellFeatures().getDVParser();
               logger.warn("Cannot add cell at " + CellReferenceHelper.getCellReference(var6) + " because it is part of the shared cell validation group " + CellReferenceHelper.getCellReference(var9.getFirstColumn(), var9.getFirstRow()) + "-" + CellReferenceHelper.getCellReference(var9.getLastColumn(), var9.getLastRow()));
            } else {
               if (var2) {
                  WritableCellFeatures var5 = var1.getWritableCellFeatures();
                  WritableCellFeatures var4 = var5;
                  if (var5 == null) {
                     var4 = new WritableCellFeatures();
                     var1.setCellFeatures(var4);
                  }

                  var4.shareDataValidation(var8.getCellFeatures());
               }

               var7.addCell(var6);
               this.numRows = Math.max(var3 + 1, this.numRows);
               this.numColumns = Math.max(this.numColumns, var7.getMaxColumn());
               var6.setCellDetails(this.formatRecords, this.sharedStrings, this);
            }
         }
      }
   }

   public void addColumnPageBreak(int var1) {
      Iterator var3 = this.columnBreaks.iterator();
      boolean var2 = false;

      while(var3.hasNext() && !var2) {
         if ((Integer)var3.next() == var1) {
            var2 = true;
         }
      }

      if (!var2) {
         this.columnBreaks.add(new Integer(var1));
      }

   }

   void addDrawing(DrawingGroupObject var1) {
      this.drawings.add(var1);
      Assert.verify(var1 instanceof Drawing ^ true);
   }

   public void addHyperlink(WritableHyperlink var1) throws WriteException, RowsExceededException {
      Cell var6 = this.getCell(var1.getColumn(), var1.getRow());
      String var4;
      String var5;
      if (!var1.isFile() && !var1.isUNC()) {
         if (var1.isURL()) {
            var5 = var1.getContents();
            var4 = var5;
            if (var5 == null) {
               var4 = var1.getURL().toString();
            }
         } else if (var1.isLocation()) {
            var4 = var1.getContents();
         } else {
            var4 = null;
         }
      } else {
         var5 = var1.getContents();
         var4 = var5;
         if (var5 == null) {
            var4 = var1.getFile().getPath();
         }
      }

      if (var6.getType() == CellType.LABEL) {
         Label var7 = (Label)var6;
         var7.setString(var4);
         WritableCellFormat var8 = new WritableCellFormat(var7.getCellFormat());
         var8.setFont(WritableWorkbook.HYPERLINK_FONT);
         var7.setCellFormat(var8);
      } else {
         this.addCell(new Label(var1.getColumn(), var1.getRow(), var4, WritableWorkbook.HYPERLINK_STYLE));
      }

      for(int var2 = var1.getRow(); var2 <= var1.getLastRow(); ++var2) {
         for(int var3 = var1.getColumn(); var3 <= var1.getLastColumn(); ++var3) {
            if (var2 != var1.getRow() && var3 != var1.getColumn() && this.rows.length < var1.getLastColumn()) {
               RowRecord var9 = this.rows[var2];
               if (var9 != null) {
                  var9.removeCell(var3);
               }
            }
         }
      }

      var1.initialize(this);
      this.hyperlinks.add(var1);
   }

   public void addImage(WritableImage var1) {
      java.io.File var6 = var1.getImageFile();
      byte var5 = 1;
      int var2;
      boolean var4;
      String var7;
      if (var6 != null) {
         String var11 = var6.getName();
         var2 = var11.lastIndexOf(46);
         if (var2 != -1) {
            var11 = var11.substring(var2 + 1);
         } else {
            var11 = "";
         }

         int var3 = 0;
         boolean var10 = false;

         while(true) {
            String[] var8 = imageTypes;
            var7 = var11;
            var4 = var10;
            if (var3 >= var8.length) {
               break;
            }

            var7 = var11;
            var4 = var10;
            if (var10) {
               break;
            }

            if (var11.equalsIgnoreCase(var8[var3])) {
               var10 = true;
            }

            ++var3;
         }
      } else {
         var7 = "?";
         var4 = true;
      }

      if (var4) {
         this.workbook.addDrawing(var1);
         this.drawings.add(var1);
         this.images.add(var1);
      } else {
         StringBuffer var9 = new StringBuffer("Image type ");
         var9.append(var7);
         var9.append(" not supported.  Supported types are ");
         var9.append(imageTypes[0]);
         var2 = var5;

         while(true) {
            String[] var12 = imageTypes;
            if (var2 >= var12.length) {
               logger.warn(var9.toString());
               break;
            }

            var9.append(", ");
            var9.append(var12[var2]);
            ++var2;
         }
      }

   }

   public void addRowPageBreak(int var1) {
      Iterator var3 = this.rowBreaks.iterator();
      boolean var2 = false;

      while(var3.hasNext() && !var2) {
         if ((Integer)var3.next() == var1) {
            var2 = true;
         }
      }

      if (!var2) {
         this.rowBreaks.add(new Integer(var1));
      }

   }

   void addValidationCell(CellValue var1) {
      this.validatedCells.add(var1);
   }

   public void applySharedDataValidation(WritableCell var1, int var2, int var3) throws WriteException {
      if (var1.getWritableCellFeatures() != null && var1.getWritableCellFeatures().hasDataValidation()) {
         int var5 = var1.getColumn();
         int var4 = var1.getRow();
         int var6 = this.numRows;
         int var8 = var4 + var3;
         int var9 = Math.min(var6 - 1, var8);

         for(var6 = var4; var6 <= var9; ++var6) {
            RowRecord var12 = this.rows[var6];
            if (var12 != null) {
               int var7 = var12.getMaxColumn();
               int var10 = var5 + var2;
               int var11 = Math.min(var7 - 1, var10);

               for(var7 = var5; var7 <= var11; ++var7) {
                  if (var7 != var5 || var6 != var4) {
                     CellValue var16 = this.rows[var6].getCell(var7);
                     if (var16 != null && var16.getWritableCellFeatures() != null && var16.getWritableCellFeatures().hasDataValidation()) {
                        logger.warn("Cannot apply data validation from " + CellReferenceHelper.getCellReference(var5, var4) + " to " + CellReferenceHelper.getCellReference(var10, var8) + " as cell " + CellReferenceHelper.getCellReference(var7, var6) + " already has a data validation");
                        return;
                     }
                  }
               }
            }
         }

         WritableCellFeatures var17 = var1.getWritableCellFeatures();
         var17.getDVParser().extendCellValidation(var2, var3);

         for(var3 = var4; var3 <= var8; ++var3) {
            RowRecord var15 = this.getRowRecord(var3);

            for(var6 = var5; var6 <= var5 + var2; ++var6) {
               if (var6 != var5 || var3 != var4) {
                  CellValue var13 = var15.getCell(var6);
                  WritableCellFeatures var14;
                  if (var13 == null) {
                     Blank var18 = new Blank(var6, var3);
                     var14 = new WritableCellFeatures();
                     var14.shareDataValidation(var17);
                     var18.setCellFeatures(var14);
                     this.addCell(var18);
                  } else {
                     var14 = var13.getWritableCellFeatures();
                     if (var14 != null) {
                        var14.shareDataValidation(var17);
                     } else {
                        var14 = new WritableCellFeatures();
                        var14.shareDataValidation(var17);
                        var13.setCellFeatures(var14);
                     }
                  }
               }
            }
         }

      } else {
         logger.warn("Cannot extend data validation for " + CellReferenceHelper.getCellReference(var1.getColumn(), var1.getRow()) + " as it has no data validation");
      }
   }

   void checkMergedBorders() {
      this.sheetWriter.setWriteData(this.rows, this.rowBreaks, this.columnBreaks, this.hyperlinks, this.mergedCells, this.columnFormats, this.maxRowOutlineLevel, this.maxColumnOutlineLevel);
      this.sheetWriter.setDimensions(this.getRows(), this.getColumns());
      this.sheetWriter.checkMergedBorders();
   }

   void copy(Sheet var1) {
      this.settings = new SheetSettings(var1.getSettings(), this);
      SheetCopier var2 = new SheetCopier(var1, this);
      var2.setColumnFormats(this.columnFormats);
      var2.setFormatRecords(this.formatRecords);
      var2.setHyperlinks(this.hyperlinks);
      var2.setMergedCells(this.mergedCells);
      var2.setRowBreaks(this.rowBreaks);
      var2.setColumnBreaks(this.columnBreaks);
      var2.setSheetWriter(this.sheetWriter);
      var2.setDrawings(this.drawings);
      var2.setImages(this.images);
      var2.setConditionalFormats(this.conditionalFormats);
      var2.setValidatedCells(this.validatedCells);
      var2.copySheet();
      this.dataValidation = var2.getDataValidation();
      this.comboBox = var2.getComboBox();
      this.plsRecord = var2.getPLSRecord();
      this.chartOnly = var2.isChartOnly();
      this.buttonPropertySet = var2.getButtonPropertySet();
      this.numRows = var2.getRows();
      this.autoFilter = var2.getAutoFilter();
      this.maxRowOutlineLevel = var2.getMaxRowOutlineLevel();
      this.maxColumnOutlineLevel = var2.getMaxColumnOutlineLevel();
   }

   void copy(WritableSheet var1) {
      this.settings = new SheetSettings(var1.getSettings(), this);
      WritableSheetImpl var2 = (WritableSheetImpl)var1;
      WritableSheetCopier var3 = new WritableSheetCopier(var1, this);
      var3.setColumnFormats(var2.columnFormats, this.columnFormats);
      var3.setMergedCells(var2.mergedCells, this.mergedCells);
      var3.setRows(var2.rows);
      var3.setRowBreaks(var2.rowBreaks, this.rowBreaks);
      var3.setColumnBreaks(var2.columnBreaks, this.columnBreaks);
      var3.setDataValidation(var2.dataValidation);
      var3.setSheetWriter(this.sheetWriter);
      var3.setDrawings(var2.drawings, this.drawings, this.images);
      var3.setWorkspaceOptions(var2.getWorkspaceOptions());
      var3.setPLSRecord(var2.plsRecord);
      var3.setButtonPropertySetRecord(var2.buttonPropertySet);
      var3.setHyperlinks(var2.hyperlinks, this.hyperlinks);
      var3.setValidatedCells(this.validatedCells);
      var3.copySheet();
      this.dataValidation = var3.getDataValidation();
      this.plsRecord = var3.getPLSRecord();
      this.buttonPropertySet = var3.getButtonPropertySet();
   }

   public Cell findCell(String var1) {
      return (new CellFinder(this)).findCell(var1);
   }

   public Cell findCell(String var1, int var2, int var3, int var4, int var5, boolean var6) {
      return (new CellFinder(this)).findCell(var1, var2, var3, var4, var5, var6);
   }

   public Cell findCell(Pattern var1, int var2, int var3, int var4, int var5, boolean var6) {
      return (new CellFinder(this)).findCell(var1, var2, var3, var4, var5, var6);
   }

   public LabelCell findLabelCell(String var1) {
      return (new CellFinder(this)).findLabelCell(var1);
   }

   public Cell getCell(int var1, int var2) {
      return this.getWritableCell(var1, var2);
   }

   public Cell getCell(String var1) {
      return this.getCell(CellReferenceHelper.getColumn(var1), CellReferenceHelper.getRow(var1));
   }

   Chart[] getCharts() {
      return this.sheetWriter.getCharts();
   }

   public Cell[] getColumn(int var1) {
      int var2 = this.numRows - 1;
      byte var4 = 0;
      boolean var3 = false;

      while(var2 >= 0 && !var3) {
         if (this.getCell(var1, var2).getType() != CellType.EMPTY) {
            var3 = true;
         } else {
            --var2;
         }
      }

      Cell[] var5 = new Cell[var2 + 1];

      for(int var6 = var4; var6 <= var2; ++var6) {
         var5[var6] = this.getCell(var1, var6);
      }

      return var5;
   }

   public CellFormat getColumnFormat(int var1) {
      return this.getColumnView(var1).getFormat();
   }

   ColumnInfoRecord getColumnInfo(int var1) {
      Iterator var6 = this.columnFormats.iterator();
      ColumnInfoRecord var4 = null;
      boolean var2 = false;
      ColumnInfoRecord var3 = null;

      while(var6.hasNext() && !var2) {
         ColumnInfoRecord var5 = (ColumnInfoRecord)var6.next();
         var3 = var5;
         if (var5.getColumn() >= var1) {
            var2 = true;
            var3 = var5;
         }
      }

      if (!var2) {
         return null;
      } else {
         if (var3.getColumn() == var1) {
            var4 = var3;
         }

         return var4;
      }
   }

   public int[] getColumnPageBreaks() {
      int[] var2 = new int[this.columnBreaks.size()];
      Iterator var3 = this.columnBreaks.iterator();

      for(int var1 = 0; var3.hasNext(); ++var1) {
         var2[var1] = (Integer)var3.next();
      }

      return var2;
   }

   public CellView getColumnView(int var1) {
      ColumnInfoRecord var2 = this.getColumnInfo(var1);
      CellView var3 = new CellView();
      if (var2 != null) {
         var3.setDimension(var2.getWidth() / 256);
         var3.setSize(var2.getWidth());
         var3.setHidden(var2.getHidden());
         var3.setFormat(var2.getCellFormat());
      } else {
         var3.setDimension(this.settings.getDefaultColumnWidth() / 256);
         var3.setSize(this.settings.getDefaultColumnWidth() * 256);
      }

      return var3;
   }

   public int getColumnWidth(int var1) {
      return this.getColumnView(var1).getDimension();
   }

   public int getColumns() {
      return this.numColumns;
   }

   ComboBox getComboBox() {
      return this.comboBox;
   }

   public DataValidation getDataValidation() {
      return this.dataValidation;
   }

   public Image getDrawing(int var1) {
      return (Image)this.images.get(var1);
   }

   final FooterRecord getFooter() {
      return this.sheetWriter.getFooter();
   }

   final HeaderRecord getHeader() {
      return this.sheetWriter.getHeader();
   }

   public Hyperlink[] getHyperlinks() {
      Hyperlink[] var2 = new Hyperlink[this.hyperlinks.size()];

      for(int var1 = 0; var1 < this.hyperlinks.size(); ++var1) {
         var2[var1] = (Hyperlink)this.hyperlinks.get(var1);
      }

      return var2;
   }

   public WritableImage getImage(int var1) {
      return (WritableImage)this.images.get(var1);
   }

   public Range[] getMergedCells() {
      return this.mergedCells.getMergedCells();
   }

   public String getName() {
      return this.name;
   }

   public int getNumberOfImages() {
      return this.images.size();
   }

   public Cell[] getRow(int var1) {
      int var2 = this.numColumns - 1;
      byte var4 = 0;
      boolean var3 = false;

      while(var2 >= 0 && !var3) {
         if (this.getCell(var2, var1).getType() != CellType.EMPTY) {
            var3 = true;
         } else {
            --var2;
         }
      }

      Cell[] var5 = new Cell[var2 + 1];

      for(int var6 = var4; var6 <= var2; ++var6) {
         var5[var6] = this.getCell(var6, var1);
      }

      return var5;
   }

   public int getRowHeight(int var1) {
      return this.getRowView(var1).getDimension();
   }

   RowRecord getRowInfo(int var1) {
      if (var1 >= 0) {
         RowRecord[] var2 = this.rows;
         if (var1 <= var2.length) {
            return var2[var1];
         }
      }

      return null;
   }

   public int[] getRowPageBreaks() {
      int[] var3 = new int[this.rowBreaks.size()];
      Iterator var2 = this.rowBreaks.iterator();

      for(int var1 = 0; var2.hasNext(); ++var1) {
         var3[var1] = (Integer)var2.next();
      }

      return var3;
   }

   RowRecord getRowRecord(int var1) throws RowsExceededException {
      if (var1 < 65536) {
         RowRecord[] var3 = this.rows;
         if (var1 >= var3.length) {
            RowRecord[] var2 = new RowRecord[Math.max(var3.length + 10, var1 + 1)];
            this.rows = var2;
            System.arraycopy(var3, 0, var2, 0, var3.length);
         }

         RowRecord var5 = this.rows[var1];
         RowRecord var4 = var5;
         if (var5 == null) {
            var4 = new RowRecord(var1, this);
            this.rows[var1] = var4;
         }

         return var4;
      } else {
         throw new RowsExceededException();
      }
   }

   public CellView getRowView(int var1) {
      CellView var2 = new CellView();

      label59: {
         boolean var10001;
         RowRecord var3;
         try {
            var3 = this.getRowRecord(var1);
         } catch (RowsExceededException var9) {
            var10001 = false;
            break label59;
         }

         label64: {
            if (var3 != null) {
               label63: {
                  try {
                     if (var3.isDefaultHeight()) {
                        break label63;
                     }
                  } catch (RowsExceededException var8) {
                     var10001 = false;
                     break label59;
                  }

                  try {
                     if (var3.isCollapsed()) {
                        var2.setHidden(true);
                        break label64;
                     }
                  } catch (RowsExceededException var7) {
                     var10001 = false;
                     break label59;
                  }

                  try {
                     var2.setDimension(var3.getRowHeight());
                     var2.setSize(var3.getRowHeight());
                     break label64;
                  } catch (RowsExceededException var6) {
                     var10001 = false;
                     break label59;
                  }
               }
            }

            try {
               var2.setDimension(this.settings.getDefaultRowHeight());
               var2.setSize(this.settings.getDefaultRowHeight());
            } catch (RowsExceededException var5) {
               var10001 = false;
               break label59;
            }
         }

         try {
            return var2;
         } catch (RowsExceededException var4) {
            var10001 = false;
         }
      }

      var2.setDimension(this.settings.getDefaultRowHeight());
      var2.setSize(this.settings.getDefaultRowHeight());
      return var2;
   }

   public int getRows() {
      return this.numRows;
   }

   public SheetSettings getSettings() {
      return this.settings;
   }

   WritableWorkbookImpl getWorkbook() {
      return this.workbook;
   }

   WorkbookSettings getWorkbookSettings() {
      return this.workbookSettings;
   }

   public WritableCell getWritableCell(int var1, int var2) {
      CellValue var6;
      label16: {
         RowRecord[] var3 = this.rows;
         if (var2 < var3.length) {
            RowRecord var5 = var3[var2];
            if (var5 != null) {
               var6 = var5.getCell(var1);
               break label16;
            }
         }

         var6 = null;
      }

      Object var4 = var6;
      if (var6 == null) {
         var4 = new EmptyCell(var1, var2);
      }

      return (WritableCell)var4;
   }

   public WritableCell getWritableCell(String var1) {
      return this.getWritableCell(CellReferenceHelper.getColumn(var1), CellReferenceHelper.getRow(var1));
   }

   public WritableHyperlink[] getWritableHyperlinks() {
      WritableHyperlink[] var2 = new WritableHyperlink[this.hyperlinks.size()];

      for(int var1 = 0; var1 < this.hyperlinks.size(); ++var1) {
         var2[var1] = (WritableHyperlink)this.hyperlinks.get(var1);
      }

      return var2;
   }

   void importSheet(Sheet var1) {
      this.settings = new SheetSettings(var1.getSettings(), this);
      SheetCopier var2 = new SheetCopier(var1, this);
      var2.setColumnFormats(this.columnFormats);
      var2.setFormatRecords(this.formatRecords);
      var2.setHyperlinks(this.hyperlinks);
      var2.setMergedCells(this.mergedCells);
      var2.setRowBreaks(this.rowBreaks);
      var2.setColumnBreaks(this.columnBreaks);
      var2.setSheetWriter(this.sheetWriter);
      var2.setDrawings(this.drawings);
      var2.setImages(this.images);
      var2.setValidatedCells(this.validatedCells);
      var2.importSheet();
      this.dataValidation = var2.getDataValidation();
      this.comboBox = var2.getComboBox();
      this.plsRecord = var2.getPLSRecord();
      this.chartOnly = var2.isChartOnly();
      this.buttonPropertySet = var2.getButtonPropertySet();
      this.numRows = var2.getRows();
      this.maxRowOutlineLevel = var2.getMaxRowOutlineLevel();
      this.maxColumnOutlineLevel = var2.getMaxColumnOutlineLevel();
   }

   public void insertColumn(int var1) {
      if (var1 >= 0 && var1 < this.numColumns) {
         int var2;
         for(var2 = 0; var2 < this.numRows; ++var2) {
            RowRecord var4 = this.rows[var2];
            if (var4 != null) {
               var4.insertColumn(var1);
            }
         }

         Iterator var7 = this.hyperlinks.iterator();

         while(var7.hasNext()) {
            ((HyperlinkRecord)var7.next()).insertColumn(var1);
         }

         var7 = this.columnFormats.iterator();

         while(var7.hasNext()) {
            ColumnInfoRecord var5 = (ColumnInfoRecord)var7.next();
            if (var5.getColumn() >= var1) {
               var5.incrementColumn();
            }
         }

         if (this.autosizedColumns.size() > 0) {
            TreeSet var8 = new TreeSet();
            Iterator var9 = this.autosizedColumns.iterator();

            while(var9.hasNext()) {
               Integer var6 = (Integer)var9.next();
               if (var6 >= var1) {
                  var8.add(new Integer(var6 + 1));
               } else {
                  var8.add(var6);
               }
            }

            this.autosizedColumns = var8;
         }

         DataValidation var10 = this.dataValidation;
         if (var10 != null) {
            var10.insertColumn(var1);
         }

         ArrayList var11 = this.validatedCells;
         if (var11 != null && var11.size() > 0) {
            var7 = this.validatedCells.iterator();

            while(var7.hasNext()) {
               CellFeatures var12 = ((CellValue)var7.next()).getCellFeatures();
               if (var12.getDVParser() != null) {
                  var12.getDVParser().insertColumn(var1);
               }
            }
         }

         this.mergedCells.insertColumn(var1);
         ArrayList var13 = new ArrayList();

         for(var7 = this.columnBreaks.iterator(); var7.hasNext(); var13.add(new Integer(var2))) {
            int var3 = (Integer)var7.next();
            var2 = var3;
            if (var3 >= var1) {
               var2 = var3 + 1;
            }
         }

         this.columnBreaks = var13;
         var7 = this.conditionalFormats.iterator();

         while(var7.hasNext()) {
            ((ConditionalFormat)var7.next()).insertColumn(var1);
         }

         if (this.workbookSettings.getFormulaAdjust()) {
            this.workbook.columnInserted(this, var1);
         }

         ++this.numColumns;
      }

   }

   public void insertRow(int var1) {
      if (var1 >= 0) {
         int var2 = this.numRows;
         if (var1 < var2) {
            RowRecord[] var4 = this.rows;
            if (var2 == var4.length) {
               this.rows = new RowRecord[var4.length + 10];
            } else {
               this.rows = new RowRecord[var4.length];
            }

            System.arraycopy(var4, 0, this.rows, 0, var1);
            RowRecord[] var5 = this.rows;
            var2 = var1 + 1;
            System.arraycopy(var4, var1, var5, var2, this.numRows - var1);

            for(; var2 <= this.numRows; ++var2) {
               RowRecord var6 = this.rows[var2];
               if (var6 != null) {
                  var6.incrementRow();
               }
            }

            Iterator var7 = this.hyperlinks.iterator();

            while(var7.hasNext()) {
               ((HyperlinkRecord)var7.next()).insertRow(var1);
            }

            DataValidation var8 = this.dataValidation;
            if (var8 != null) {
               var8.insertRow(var1);
            }

            ArrayList var9 = this.validatedCells;
            if (var9 != null && var9.size() > 0) {
               var7 = this.validatedCells.iterator();

               while(var7.hasNext()) {
                  CellFeatures var10 = ((CellValue)var7.next()).getCellFeatures();
                  if (var10.getDVParser() != null) {
                     var10.getDVParser().insertRow(var1);
                  }
               }
            }

            this.mergedCells.insertRow(var1);
            var9 = new ArrayList();

            for(Iterator var11 = this.rowBreaks.iterator(); var11.hasNext(); var9.add(new Integer(var2))) {
               int var3 = (Integer)var11.next();
               var2 = var3;
               if (var3 >= var1) {
                  var2 = var3 + 1;
               }
            }

            this.rowBreaks = var9;
            var7 = this.conditionalFormats.iterator();

            while(var7.hasNext()) {
               ((ConditionalFormat)var7.next()).insertRow(var1);
            }

            if (this.workbookSettings.getFormulaAdjust()) {
               this.workbook.rowInserted(this, var1);
            }

            ++this.numRows;
         }
      }

   }

   boolean isChartOnly() {
      return this.chartOnly;
   }

   public boolean isHidden() {
      return this.settings.isHidden();
   }

   public boolean isProtected() {
      return this.settings.isProtected();
   }

   public Range mergeCells(int var1, int var2, int var3, int var4) throws WriteException, RowsExceededException {
      if (var3 < var1 || var4 < var2) {
         logger.warn("Cannot merge cells - top left and bottom right incorrectly specified");
      }

      if (var3 >= this.numColumns || var4 >= this.numRows) {
         this.addCell(new Blank(var3, var4));
      }

      SheetRangeImpl var5 = new SheetRangeImpl(this, var1, var2, var3, var4);
      this.mergedCells.add(var5);
      return var5;
   }

   void rationalize(IndexMapping var1, IndexMapping var2, IndexMapping var3) {
      Iterator var6 = this.columnFormats.iterator();

      while(var6.hasNext()) {
         ((ColumnInfoRecord)var6.next()).rationalize(var1);
      }

      byte var5 = 0;
      int var4 = 0;

      while(true) {
         RowRecord[] var7 = this.rows;
         if (var4 >= var7.length) {
            Chart[] var9 = this.getCharts();

            for(var4 = var5; var4 < var9.length; ++var4) {
               var9[var4].rationalize(var1, var2, var3);
            }

            return;
         }

         RowRecord var8 = var7[var4];
         if (var8 != null) {
            var8.rationalize(var1);
         }

         ++var4;
      }
   }

   public void removeColumn(int var1) {
      if (var1 >= 0 && var1 < this.numColumns) {
         int var2;
         for(var2 = 0; var2 < this.numRows; ++var2) {
            RowRecord var4 = this.rows[var2];
            if (var4 != null) {
               var4.removeColumn(var1);
            }
         }

         Iterator var5 = this.hyperlinks.iterator();

         while(true) {
            while(var5.hasNext()) {
               HyperlinkRecord var7 = (HyperlinkRecord)var5.next();
               if (var7.getColumn() == var1 && var7.getLastColumn() == var1) {
                  var5.remove();
               } else {
                  var7.removeColumn(var1);
               }
            }

            DataValidation var8 = this.dataValidation;
            if (var8 != null) {
               var8.removeColumn(var1);
            }

            ArrayList var9 = this.validatedCells;
            Iterator var10;
            if (var9 != null && var9.size() > 0) {
               var10 = this.validatedCells.iterator();

               while(var10.hasNext()) {
                  CellFeatures var11 = ((CellValue)var10.next()).getCellFeatures();
                  if (var11.getDVParser() != null) {
                     var11.getDVParser().removeColumn(var1);
                  }
               }
            }

            this.mergedCells.removeColumn(var1);
            ArrayList var12 = new ArrayList();
            var10 = this.columnBreaks.iterator();

            while(var10.hasNext()) {
               int var3 = (Integer)var10.next();
               if (var3 != var1) {
                  var2 = var3;
                  if (var3 > var1) {
                     var2 = var3 - 1;
                  }

                  var12.add(new Integer(var2));
               }
            }

            this.columnBreaks = var12;
            Iterator var6 = this.columnFormats.iterator();
            ColumnInfoRecord var13 = null;

            while(var6.hasNext()) {
               ColumnInfoRecord var14 = (ColumnInfoRecord)var6.next();
               if (var14.getColumn() == var1) {
                  var13 = var14;
               } else if (var14.getColumn() > var1) {
                  var14.decrementColumn();
               }
            }

            if (var13 != null) {
               this.columnFormats.remove(var13);
            }

            if (this.autosizedColumns.size() > 0) {
               TreeSet var16 = new TreeSet();
               var6 = this.autosizedColumns.iterator();

               while(var6.hasNext()) {
                  Integer var15 = (Integer)var6.next();
                  if (var15 != var1) {
                     if (var15 > var1) {
                        var16.add(new Integer(var15 - 1));
                     } else {
                        var16.add(var15);
                     }
                  }
               }

               this.autosizedColumns = var16;
            }

            var10 = this.conditionalFormats.iterator();

            while(var10.hasNext()) {
               ((ConditionalFormat)var10.next()).removeColumn(var1);
            }

            if (this.workbookSettings.getFormulaAdjust()) {
               this.workbook.columnRemoved(this, var1);
            }

            --this.numColumns;
            break;
         }
      }

   }

   void removeDataValidation(CellValue var1) {
      DataValidation var2 = this.dataValidation;
      if (var2 != null) {
         var2.removeDataValidation(var1.getColumn(), var1.getRow());
      }

      ArrayList var3 = this.validatedCells;
      if (var3 != null && !var3.remove(var1)) {
         logger.warn("Could not remove validated cell " + CellReferenceHelper.getCellReference(var1));
      }

   }

   void removeDrawing(DrawingGroupObject var1) {
      int var2 = this.drawings.size();
      this.drawings.remove(var1);
      int var3 = this.drawings.size();
      boolean var4 = true;
      this.drawingsModified = true;
      if (var3 != var2 - 1) {
         var4 = false;
      }

      Assert.verify(var4);
   }

   public void removeHyperlink(WritableHyperlink var1) {
      this.removeHyperlink(var1, false);
   }

   public void removeHyperlink(WritableHyperlink var1, boolean var2) {
      ArrayList var3 = this.hyperlinks;
      var3.remove(var3.indexOf(var1));
      if (!var2) {
         if (this.rows.length > var1.getRow() && this.rows[var1.getRow()] != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         this.rows[var1.getRow()].removeCell(var1.getColumn());
      }

   }

   public void removeImage(WritableImage var1) {
      this.drawings.remove(var1);
      this.images.remove(var1);
      this.drawingsModified = true;
      this.workbook.removeDrawing(var1);
   }

   public void removeRow(int var1) {
      if (var1 >= 0 && var1 < this.numRows) {
         RowRecord[] var5 = this.rows;
         RowRecord[] var4 = new RowRecord[var5.length];
         this.rows = var4;
         System.arraycopy(var5, 0, var4, 0, var1);
         int var2 = var1 + 1;
         System.arraycopy(var5, var2, this.rows, var1, this.numRows - var2);

         for(var2 = var1; var2 < this.numRows; ++var2) {
            RowRecord var6 = this.rows[var2];
            if (var6 != null) {
               var6.decrementRow();
            }
         }

         Iterator var7 = this.hyperlinks.iterator();

         while(true) {
            while(var7.hasNext()) {
               HyperlinkRecord var9 = (HyperlinkRecord)var7.next();
               if (var9.getRow() == var1 && var9.getLastRow() == var1) {
                  var7.remove();
               } else {
                  var9.removeRow(var1);
               }
            }

            DataValidation var8 = this.dataValidation;
            if (var8 != null) {
               var8.removeRow(var1);
            }

            ArrayList var10 = this.validatedCells;
            if (var10 != null && var10.size() > 0) {
               var7 = this.validatedCells.iterator();

               while(var7.hasNext()) {
                  CellFeatures var11 = ((CellValue)var7.next()).getCellFeatures();
                  if (var11.getDVParser() != null) {
                     var11.getDVParser().removeRow(var1);
                  }
               }
            }

            this.mergedCells.removeRow(var1);
            var10 = new ArrayList();
            Iterator var12 = this.rowBreaks.iterator();

            while(var12.hasNext()) {
               int var3 = (Integer)var12.next();
               if (var3 != var1) {
                  var2 = var3;
                  if (var3 > var1) {
                     var2 = var3 - 1;
                  }

                  var10.add(new Integer(var2));
               }
            }

            this.rowBreaks = var10;
            var7 = this.conditionalFormats.iterator();

            while(var7.hasNext()) {
               ((ConditionalFormat)var7.next()).removeRow(var1);
            }

            if (this.workbookSettings.getFormulaAdjust()) {
               this.workbook.rowRemoved(this, var1);
            }

            --this.numRows;
            return;
         }
      } else {
         if (this.workbookSettings.getFormulaAdjust()) {
            this.workbook.rowRemoved(this, var1);
         }

      }
   }

   public void removeSharedDataValidation(WritableCell var1) throws WriteException {
      WritableCellFeatures var5 = var1.getWritableCellFeatures();
      if (var5 != null && var5.hasDataValidation()) {
         DVParser var4 = var5.getDVParser();
         if (!var4.extendedCellsValidation()) {
            var5.removeDataValidation();
            return;
         }

         if (var4.extendedCellsValidation() && (var1.getColumn() != var4.getFirstColumn() || var1.getRow() != var4.getFirstRow())) {
            logger.warn("Cannot remove data validation from " + CellReferenceHelper.getCellReference(var4.getFirstColumn(), var4.getFirstRow()) + "-" + CellReferenceHelper.getCellReference(var4.getLastColumn(), var4.getLastRow()) + " because the selected cell " + CellReferenceHelper.getCellReference(var1) + " is not the top left cell in the range");
            return;
         }

         for(int var2 = var4.getFirstRow(); var2 <= var4.getLastRow(); ++var2) {
            for(int var3 = var4.getFirstColumn(); var3 <= var4.getLastColumn(); ++var3) {
               CellValue var6 = this.rows[var2].getCell(var3);
               if (var6 != null) {
                  var6.getWritableCellFeatures().removeSharedDataValidation();
                  var6.removeCellFeatures();
               }
            }
         }

         DataValidation var7 = this.dataValidation;
         if (var7 != null) {
            var7.removeSharedDataValidation(var4.getFirstColumn(), var4.getFirstRow(), var4.getLastColumn(), var4.getLastRow());
         }
      }

   }

   public void setColumnGroup(int var1, int var2, boolean var3) throws WriteException, RowsExceededException {
      int var4 = var1;
      if (var2 < var1) {
         logger.warn("Cannot merge cells - top and bottom rows incorrectly specified");
         var4 = var1;
      }

      while(var4 <= var2) {
         ColumnInfoRecord var6 = this.getColumnInfo(var4);
         ColumnInfoRecord var5 = var6;
         if (var6 == null) {
            this.setColumnView(var4, new CellView());
            var5 = this.getColumnInfo(var4);
         }

         var5.incrementOutlineLevel();
         var5.setCollapsed(var3);
         this.maxColumnOutlineLevel = Math.max(this.maxColumnOutlineLevel, var5.getOutlineLevel());
         ++var4;
      }

   }

   public void setColumnView(int var1, int var2) {
      CellView var3 = new CellView();
      var3.setSize(var2 * 256);
      this.setColumnView(var1, var3);
   }

   public void setColumnView(int var1, int var2, CellFormat var3) {
      CellView var4 = new CellView();
      var4.setSize(var2 * 256);
      var4.setFormat(var3);
      this.setColumnView(var1, var4);
   }

   public void setColumnView(int var1, CellView var2) {
      XFRecord var5 = (XFRecord)var2.getFormat();
      Object var4 = var5;
      if (var5 == null) {
         var4 = this.getWorkbook().getStyles().getNormalStyle();
      }

      label75: {
         boolean var10001;
         try {
            if (!((XFRecord)var4).isInitialized()) {
               this.formatRecords.addStyle((XFRecord)var4);
            }
         } catch (NumFormatRecordsException var11) {
            var10001 = false;
            break label75;
         }

         int var3;
         label76: {
            try {
               if (var2.depUsed()) {
                  var3 = var2.getDimension() * 256;
                  break label76;
               }
            } catch (NumFormatRecordsException var13) {
               var10001 = false;
               break label75;
            }

            try {
               var3 = var2.getSize();
            } catch (NumFormatRecordsException var10) {
               var10001 = false;
               break label75;
            }
         }

         try {
            if (var2.isAutosize()) {
               TreeSet var15 = this.autosizedColumns;
               Integer var6 = new Integer(var1);
               var15.add(var6);
            }
         } catch (NumFormatRecordsException var9) {
            var10001 = false;
            break label75;
         }

         ColumnInfoRecord var16;
         try {
            var16 = new ColumnInfoRecord(var1, var3, (XFRecord)var4);
            if (var2.isHidden()) {
               var16.setHidden(true);
            }
         } catch (NumFormatRecordsException var8) {
            var10001 = false;
            break label75;
         }

         try {
            if (!this.columnFormats.contains(var16)) {
               this.columnFormats.add(var16);
               return;
            }
         } catch (NumFormatRecordsException var12) {
            var10001 = false;
            break label75;
         }

         try {
            this.columnFormats.remove(var16);
            this.columnFormats.add(var16);
            return;
         } catch (NumFormatRecordsException var7) {
            var10001 = false;
         }
      }

      logger.warn("Maximum number of format records exceeded.  Using default format.");
      ColumnInfoRecord var14 = new ColumnInfoRecord(var1, var2.getDimension() * 256, WritableWorkbook.NORMAL_STYLE);
      if (!this.columnFormats.contains(var14)) {
         this.columnFormats.add(var14);
      }

   }

   void setComboBox(ComboBox var1) {
      this.comboBox = var1;
   }

   public void setFooter(String var1, String var2, String var3) {
      HeaderFooter var4 = new HeaderFooter();
      var4.getLeft().append(var1);
      var4.getCentre().append(var2);
      var4.getRight().append(var3);
      this.settings.setFooter(var4);
   }

   public void setHeader(String var1, String var2, String var3) {
      HeaderFooter var4 = new HeaderFooter();
      var4.getLeft().append(var1);
      var4.getCentre().append(var2);
      var4.getRight().append(var3);
      this.settings.setHeader(var4);
   }

   public void setHidden(boolean var1) {
      this.settings.setHidden(var1);
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public void setPageSetup(PageOrientation var1) {
      this.settings.setOrientation(var1);
   }

   public void setPageSetup(PageOrientation var1, double var2, double var4) {
      this.settings.setOrientation(var1);
      this.settings.setHeaderMargin(var2);
      this.settings.setFooterMargin(var4);
   }

   public void setPageSetup(PageOrientation var1, PaperSize var2, double var3, double var5) {
      this.settings.setPaperSize(var2);
      this.settings.setOrientation(var1);
      this.settings.setHeaderMargin(var3);
      this.settings.setFooterMargin(var5);
   }

   public void setProtected(boolean var1) {
      this.settings.setProtected(var1);
   }

   public void setRowGroup(int var1, int var2, boolean var3) throws WriteException, RowsExceededException {
      int var4 = var1;
      if (var2 < var1) {
         logger.warn("Cannot merge cells - top and bottom rows incorrectly specified");
         var4 = var1;
      }

      while(var4 <= var2) {
         RowRecord var5 = this.getRowRecord(var4);
         ++var4;
         this.numRows = Math.max(var4, this.numRows);
         var5.incrementOutlineLevel();
         var5.setCollapsed(var3);
         this.maxRowOutlineLevel = Math.max(this.maxRowOutlineLevel, var5.getOutlineLevel());
      }

   }

   public void setRowView(int var1, int var2) throws RowsExceededException {
      CellView var3 = new CellView();
      var3.setSize(var2);
      var3.setHidden(false);
      this.setRowView(var1, var3);
   }

   public void setRowView(int var1, int var2, boolean var3) throws RowsExceededException {
      CellView var4 = new CellView();
      var4.setSize(var2);
      var4.setHidden(var3);
      this.setRowView(var1, var4);
   }

   public void setRowView(int var1, CellView var2) throws RowsExceededException {
      RowRecord var5 = this.getRowRecord(var1);
      XFRecord var4 = (XFRecord)var2.getFormat();
      XFRecord var3 = var4;
      if (var4 != null) {
         label21: {
            var3 = var4;

            try {
               if (var4.isInitialized()) {
                  break label21;
               }

               this.formatRecords.addStyle(var4);
            } catch (NumFormatRecordsException var6) {
               logger.warn("Maximum number of format records exceeded.  Using default format.");
               var3 = null;
               break label21;
            }

            var3 = var4;
         }
      }

      var5.setRowDetails(var2.getSize(), false, var2.isHidden(), 0, false, var3);
      this.numRows = Math.max(this.numRows, var1 + 1);
   }

   public void setRowView(int var1, boolean var2) throws RowsExceededException {
      CellView var3 = new CellView();
      var3.setHidden(var2);
      this.setRowView(var1, var3);
   }

   public void setSelected() {
      this.settings.setSelected();
   }

   public void unmergeCells(Range var1) {
      this.mergedCells.unmergeCells(var1);
   }

   public void unsetColumnGroup(int var1, int var2) throws WriteException, RowsExceededException {
      int var3 = var1;
      if (var2 < var1) {
         logger.warn("Cannot merge cells - top and bottom rows incorrectly specified");
         var3 = var1;
      }

      while(var3 <= var2) {
         this.getColumnInfo(var3).decrementOutlineLevel();
         ++var3;
      }

      this.maxColumnOutlineLevel = 0;

      ColumnInfoRecord var4;
      for(Iterator var5 = this.columnFormats.iterator(); var5.hasNext(); this.maxColumnOutlineLevel = Math.max(this.maxColumnOutlineLevel, var4.getOutlineLevel())) {
         var4 = (ColumnInfoRecord)var5.next();
      }

   }

   public void unsetRowGroup(int var1, int var2) throws WriteException, RowsExceededException {
      if (var2 < var1) {
         logger.warn("Cannot merge cells - top and bottom rows incorrectly specified");
      }

      int var4 = var1;
      int var3 = var2;
      if (var2 >= this.numRows) {
         logger.warn("" + var2 + " is greater than the sheet bounds");
         var3 = this.numRows - 1;
         var4 = var1;
      }

      while(var4 <= var3) {
         this.rows[var4].decrementOutlineLevel();
         ++var4;
      }

      this.maxRowOutlineLevel = 0;
      var1 = this.rows.length;

      while(true) {
         var2 = var1 - 1;
         if (var1 <= 0) {
            return;
         }

         this.maxRowOutlineLevel = Math.max(this.maxRowOutlineLevel, this.rows[var2].getOutlineLevel());
         var1 = var2;
      }
   }

   public void write() throws IOException {
      boolean var2 = this.drawingsModified;
      boolean var1 = var2;
      if (this.workbook.getDrawingGroup() != null) {
         var1 = var2 | this.workbook.getDrawingGroup().hasDrawingsOmitted();
      }

      if (this.autosizedColumns.size() > 0) {
         this.autosizeColumns();
      }

      this.sheetWriter.setWriteData(this.rows, this.rowBreaks, this.columnBreaks, this.hyperlinks, this.mergedCells, this.columnFormats, this.maxRowOutlineLevel, this.maxColumnOutlineLevel);
      this.sheetWriter.setDimensions(this.getRows(), this.getColumns());
      this.sheetWriter.setSettings(this.settings);
      this.sheetWriter.setPLS(this.plsRecord);
      this.sheetWriter.setDrawings(this.drawings, var1);
      this.sheetWriter.setButtonPropertySet(this.buttonPropertySet);
      this.sheetWriter.setDataValidation(this.dataValidation, this.validatedCells);
      this.sheetWriter.setConditionalFormats(this.conditionalFormats);
      this.sheetWriter.setAutoFilter(this.autoFilter);
      this.sheetWriter.write();
   }

   private static class ColumnInfoComparator implements Comparator {
      private ColumnInfoComparator() {
      }

      // $FF: synthetic method
      ColumnInfoComparator(Object var1) {
         this();
      }

      public int compare(Object var1, Object var2) {
         if (var1 == var2) {
            return 0;
         } else {
            Assert.verify(var1 instanceof ColumnInfoRecord);
            Assert.verify(var2 instanceof ColumnInfoRecord);
            ColumnInfoRecord var3 = (ColumnInfoRecord)var1;
            ColumnInfoRecord var4 = (ColumnInfoRecord)var2;
            return var3.getColumn() - var4.getColumn();
         }
      }

      public boolean equals(Object var1) {
         boolean var2;
         if (var1 == this) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }
   }
}
