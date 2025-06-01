package jxl.read.biff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import jxl.Cell;
import jxl.CellView;
import jxl.Hyperlink;
import jxl.Image;
import jxl.LabelCell;
import jxl.Range;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.WorkbookSettings;
import jxl.biff.AutoFilter;
import jxl.biff.BuiltInName;
import jxl.biff.CellFinder;
import jxl.biff.CellReferenceHelper;
import jxl.biff.ConditionalFormat;
import jxl.biff.DataValidation;
import jxl.biff.EmptyCell;
import jxl.biff.FormattingRecords;
import jxl.biff.Type;
import jxl.biff.WorkspaceInformationRecord;
import jxl.biff.drawing.Chart;
import jxl.biff.drawing.Drawing;
import jxl.biff.drawing.DrawingData;
import jxl.biff.drawing.DrawingGroupObject;
import jxl.common.Logger;
import jxl.format.CellFormat;

public class SheetImpl implements Sheet {
   private static Logger logger = Logger.getLogger(SheetImpl.class);
   private AutoFilter autoFilter;
   private ButtonPropertySetRecord buttonPropertySet;
   private Cell[][] cells;
   private ArrayList charts;
   private int[] columnBreaks;
   private ColumnInfoRecord[] columnInfos;
   private ArrayList columnInfosArray;
   private boolean columnInfosInitialized;
   private ArrayList conditionalFormats;
   private DataValidation dataValidation;
   private ArrayList drawings;
   private File excelFile;
   private FormattingRecords formattingRecords;
   private boolean hidden;
   private ArrayList hyperlinks;
   private ArrayList images;
   private ArrayList localNames;
   private int maxColumnOutlineLevel;
   private int maxRowOutlineLevel;
   private Range[] mergedCells;
   private String name;
   private boolean nineteenFour;
   private int numCols;
   private int numRows;
   private PLSRecord plsRecord;
   private int[] rowBreaks;
   private ArrayList rowProperties;
   private RowRecord[] rowRecords;
   private boolean rowRecordsInitialized;
   private SheetSettings settings;
   private ArrayList sharedFormulas;
   private SSTRecord sharedStrings;
   private BOFRecord sheetBof;
   private int startPosition;
   private WorkbookParser workbook;
   private BOFRecord workbookBof;
   private WorkbookSettings workbookSettings;
   private WorkspaceInformationRecord workspaceOptions;

   SheetImpl(File var1, SSTRecord var2, FormattingRecords var3, BOFRecord var4, BOFRecord var5, boolean var6, WorkbookParser var7) throws BiffException {
      this.excelFile = var1;
      this.sharedStrings = var2;
      this.formattingRecords = var3;
      this.sheetBof = var4;
      this.workbookBof = var5;
      this.columnInfosArray = new ArrayList();
      this.sharedFormulas = new ArrayList();
      this.hyperlinks = new ArrayList();
      this.rowProperties = new ArrayList(10);
      this.columnInfosInitialized = false;
      this.rowRecordsInitialized = false;
      this.nineteenFour = var6;
      this.workbook = var7;
      this.workbookSettings = var7.getSettings();
      this.startPosition = var1.getPos();
      if (this.sheetBof.isChart()) {
         this.startPosition -= this.sheetBof.getLength() + 4;
      }

      int var8 = 1;

      while(var8 >= 1) {
         Record var10 = var1.next();
         int var9 = var8;
         if (var10.getCode() == Type.EOF.value) {
            var9 = var8 - 1;
         }

         var8 = var9;
         if (var10.getCode() == Type.BOF.value) {
            var8 = var9 + 1;
         }
      }

   }

   private void initializeImages() {
      if (this.images == null) {
         this.images = new ArrayList();
         DrawingGroupObject[] var3 = this.getDrawings();

         for(int var1 = 0; var1 < var3.length; ++var1) {
            DrawingGroupObject var2 = var3[var1];
            if (var2 instanceof Drawing) {
               this.images.add(var2);
            }
         }

      }
   }

   void addLocalName(NameRecord var1) {
      if (this.localNames == null) {
         this.localNames = new ArrayList();
      }

      this.localNames.add(var1);
   }

   final void clear() {
      Cell[][] var1 = (Cell[][])null;
      this.cells = null;
      this.mergedCells = null;
      this.columnInfosArray.clear();
      this.sharedFormulas.clear();
      this.hyperlinks.clear();
      this.columnInfosInitialized = false;
      if (!this.workbookSettings.getGCDisabled()) {
         System.gc();
      }

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

   public AutoFilter getAutoFilter() {
      return this.autoFilter;
   }

   public ButtonPropertySetRecord getButtonPropertySet() {
      return this.buttonPropertySet;
   }

   public Cell getCell(int var1, int var2) {
      if (this.cells == null) {
         this.readSheet();
      }

      Cell var4 = this.cells[var2][var1];
      Object var3 = var4;
      if (var4 == null) {
         var3 = new EmptyCell(var1, var2);
         this.cells[var2][var1] = (Cell)var3;
      }

      return (Cell)var3;
   }

   public Cell getCell(String var1) {
      return this.getCell(CellReferenceHelper.getColumn(var1), CellReferenceHelper.getRow(var1));
   }

   public final Chart[] getCharts() {
      int var2 = this.charts.size();
      Chart[] var3 = new Chart[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = (Chart)this.charts.get(var1);
      }

      return var3;
   }

   public Cell[] getColumn(int var1) {
      if (this.cells == null) {
         this.readSheet();
      }

      int var2 = this.numRows - 1;
      byte var4 = 0;
      boolean var3 = false;

      while(var2 >= 0 && !var3) {
         if (this.cells[var2][var1] != null) {
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

   public ColumnInfoRecord getColumnInfo(int var1) {
      ColumnInfoRecord var5;
      if (!this.columnInfosInitialized) {
         Iterator var6 = this.columnInfosArray.iterator();

         while(var6.hasNext()) {
            var5 = (ColumnInfoRecord)var6.next();
            int var3 = Math.max(0, var5.getStartColumn());
            int var4 = Math.min(this.columnInfos.length - 1, var5.getEndColumn());

            for(int var2 = var3; var2 <= var4; ++var2) {
               this.columnInfos[var2] = var5;
            }

            if (var4 < var3) {
               this.columnInfos[var3] = var5;
            }
         }

         this.columnInfosInitialized = true;
      }

      ColumnInfoRecord[] var7 = this.columnInfos;
      if (var1 < var7.length) {
         var5 = var7[var1];
      } else {
         var5 = null;
      }

      return var5;
   }

   public ColumnInfoRecord[] getColumnInfos() {
      ColumnInfoRecord[] var2 = new ColumnInfoRecord[this.columnInfosArray.size()];

      for(int var1 = 0; var1 < this.columnInfosArray.size(); ++var1) {
         var2[var1] = (ColumnInfoRecord)this.columnInfosArray.get(var1);
      }

      return var2;
   }

   public final int[] getColumnPageBreaks() {
      return this.columnBreaks;
   }

   public CellView getColumnView(int var1) {
      ColumnInfoRecord var3 = this.getColumnInfo(var1);
      CellView var2 = new CellView();
      if (var3 != null) {
         var2.setDimension(var3.getWidth() / 256);
         var2.setSize(var3.getWidth());
         var2.setHidden(var3.getHidden());
         var2.setFormat(this.formattingRecords.getXFRecord(var3.getXFIndex()));
      } else {
         var2.setDimension(this.settings.getDefaultColumnWidth());
         var2.setSize(this.settings.getDefaultColumnWidth() * 256);
      }

      return var2;
   }

   public int getColumnWidth(int var1) {
      return this.getColumnView(var1).getSize() / 256;
   }

   public int getColumns() {
      if (this.cells == null) {
         this.readSheet();
      }

      return this.numCols;
   }

   public ConditionalFormat[] getConditionalFormats() {
      ConditionalFormat[] var1 = new ConditionalFormat[this.conditionalFormats.size()];
      return (ConditionalFormat[])this.conditionalFormats.toArray(var1);
   }

   public DataValidation getDataValidation() {
      return this.dataValidation;
   }

   public Image getDrawing(int var1) {
      if (this.images == null) {
         this.initializeImages();
      }

      return (Image)this.images.get(var1);
   }

   public DrawingData getDrawingData() {
      SheetReader var1 = new SheetReader(this.excelFile, this.sharedStrings, this.formattingRecords, this.sheetBof, this.workbookBof, this.nineteenFour, this.workbook, this.startPosition, this);
      var1.read();
      return var1.getDrawingData();
   }

   public final DrawingGroupObject[] getDrawings() {
      DrawingGroupObject[] var1 = new DrawingGroupObject[this.drawings.size()];
      return (DrawingGroupObject[])this.drawings.toArray(var1);
   }

   public Hyperlink[] getHyperlinks() {
      Hyperlink[] var2 = new Hyperlink[this.hyperlinks.size()];

      for(int var1 = 0; var1 < this.hyperlinks.size(); ++var1) {
         var2[var1] = (Hyperlink)this.hyperlinks.get(var1);
      }

      return var2;
   }

   public int getMaxColumnOutlineLevel() {
      return this.maxColumnOutlineLevel;
   }

   public int getMaxRowOutlineLevel() {
      return this.maxRowOutlineLevel;
   }

   public Range[] getMergedCells() {
      Range[] var2 = this.mergedCells;
      Range[] var1 = var2;
      if (var2 == null) {
         var1 = new Range[0];
      }

      return var1;
   }

   public String getName() {
      return this.name;
   }

   public int getNumberOfImages() {
      if (this.images == null) {
         this.initializeImages();
      }

      return this.images.size();
   }

   public PLSRecord getPLS() {
      return this.plsRecord;
   }

   public Cell[] getRow(int var1) {
      if (this.cells == null) {
         this.readSheet();
      }

      int var2 = this.numCols - 1;
      byte var4 = 0;
      boolean var3 = false;

      while(var2 >= 0 && !var3) {
         if (this.cells[var1][var2] != null) {
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
      RowRecord var3;
      if (!this.rowRecordsInitialized) {
         this.rowRecords = new RowRecord[this.getRows()];
         Iterator var4 = this.rowProperties.iterator();

         while(var4.hasNext()) {
            var3 = (RowRecord)var4.next();
            int var2 = var3.getRowNumber();
            RowRecord[] var5 = this.rowRecords;
            if (var2 < var5.length) {
               var5[var2] = var3;
            }
         }

         this.rowRecordsInitialized = true;
      }

      RowRecord[] var6 = this.rowRecords;
      if (var1 < var6.length) {
         var3 = var6[var1];
      } else {
         var3 = null;
      }

      return var3;
   }

   public final int[] getRowPageBreaks() {
      return this.rowBreaks;
   }

   public RowRecord[] getRowProperties() {
      int var2 = this.rowProperties.size();
      RowRecord[] var3 = new RowRecord[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = (RowRecord)this.rowProperties.get(var1);
      }

      return var3;
   }

   public CellView getRowView(int var1) {
      RowRecord var3 = this.getRowInfo(var1);
      CellView var2 = new CellView();
      if (var3 != null) {
         var2.setDimension(var3.getRowHeight());
         var2.setSize(var3.getRowHeight());
         var2.setHidden(var3.isCollapsed());
         if (var3.hasDefaultFormat()) {
            var2.setFormat(this.formattingRecords.getXFRecord(var3.getXFIndex()));
         }
      } else {
         var2.setDimension(this.settings.getDefaultRowHeight());
         var2.setSize(this.settings.getDefaultRowHeight());
      }

      return var2;
   }

   public int getRows() {
      if (this.cells == null) {
         this.readSheet();
      }

      return this.numRows;
   }

   public SheetSettings getSettings() {
      return this.settings;
   }

   public BOFRecord getSheetBof() {
      return this.sheetBof;
   }

   public WorkbookParser getWorkbook() {
      return this.workbook;
   }

   public BOFRecord getWorkbookBof() {
      return this.workbookBof;
   }

   public WorkspaceInformationRecord getWorkspaceOptions() {
      return this.workspaceOptions;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public boolean isProtected() {
      return this.settings.isProtected();
   }

   final void readSheet() {
      if (!this.sheetBof.isWorksheet()) {
         this.numRows = 0;
         this.numCols = 0;
         this.cells = new Cell[0][0];
      }

      SheetReader var3 = new SheetReader(this.excelFile, this.sharedStrings, this.formattingRecords, this.sheetBof, this.workbookBof, this.nineteenFour, this.workbook, this.startPosition, this);
      var3.read();
      this.numRows = var3.getNumRows();
      this.numCols = var3.getNumCols();
      this.cells = var3.getCells();
      this.rowProperties = var3.getRowProperties();
      this.columnInfosArray = var3.getColumnInfosArray();
      this.hyperlinks = var3.getHyperlinks();
      this.conditionalFormats = var3.getConditionalFormats();
      this.autoFilter = var3.getAutoFilter();
      this.charts = var3.getCharts();
      this.drawings = var3.getDrawings();
      this.dataValidation = var3.getDataValidation();
      this.mergedCells = var3.getMergedCells();
      SheetSettings var2 = var3.getSettings();
      this.settings = var2;
      var2.setHidden(this.hidden);
      this.rowBreaks = var3.getRowBreaks();
      this.columnBreaks = var3.getColumnBreaks();
      this.workspaceOptions = var3.getWorkspaceOptions();
      this.plsRecord = var3.getPLS();
      this.buttonPropertySet = var3.getButtonPropertySet();
      this.maxRowOutlineLevel = var3.getMaxRowOutlineLevel();
      this.maxColumnOutlineLevel = var3.getMaxColumnOutlineLevel();
      if (!this.workbookSettings.getGCDisabled()) {
         System.gc();
      }

      ArrayList var5;
      if (this.columnInfosArray.size() > 0) {
         var5 = this.columnInfosArray;
         this.columnInfos = new ColumnInfoRecord[((ColumnInfoRecord)var5.get(var5.size() - 1)).getEndColumn() + 1];
      } else {
         this.columnInfos = new ColumnInfoRecord[0];
      }

      var5 = this.localNames;
      if (var5 != null) {
         Iterator var6 = var5.iterator();

         while(true) {
            while(var6.hasNext()) {
               NameRecord var7 = (NameRecord)var6.next();
               if (var7.getBuiltInName() == BuiltInName.PRINT_AREA) {
                  if (var7.getRanges().length > 0) {
                     NameRecord.NameRange var8 = var7.getRanges()[0];
                     this.settings.setPrintArea(var8.getFirstColumn(), var8.getFirstRow(), var8.getLastColumn(), var8.getLastRow());
                  }
               } else if (var7.getBuiltInName() == BuiltInName.PRINT_TITLES) {
                  for(int var1 = 0; var1 < var7.getRanges().length; ++var1) {
                     NameRecord.NameRange var4 = var7.getRanges()[var1];
                     if (var4.getFirstColumn() == 0 && var4.getLastColumn() == 255) {
                        this.settings.setPrintTitlesRow(var4.getFirstRow(), var4.getLastRow());
                     } else {
                        this.settings.setPrintTitlesCol(var4.getFirstColumn(), var4.getLastColumn());
                     }
                  }
               }
            }

            return;
         }
      }
   }

   final void setHidden(boolean var1) {
      this.hidden = var1;
   }

   final void setName(String var1) {
      this.name = var1;
   }
}
