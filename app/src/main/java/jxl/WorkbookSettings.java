package jxl;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import jxl.biff.CountryCode;
import jxl.biff.formula.FunctionNames;
import jxl.common.Logger;

public final class WorkbookSettings {
   private static final int DEFAULT_ARRAY_GROW_SIZE = 1048576;
   private static final int DEFAULT_INITIAL_FILE_SIZE = 5242880;
   public static final int HIDEOBJ_HIDE_ALL = 2;
   public static final int HIDEOBJ_SHOW_ALL = 0;
   public static final int HIDEOBJ_SHOW_PLACEHOLDERS = 1;
   private static Logger logger = Logger.getLogger(WorkbookSettings.class);
   private int arrayGrowSize;
   private boolean autoFilterDisabled;
   private boolean cellValidationDisabled;
   private int characterSet;
   private boolean drawingsDisabled;
   private String encoding;
   private boolean excel9file;
   private String excelDisplayLanguage;
   private String excelRegionalSettings;
   private boolean formulaReferenceAdjustDisabled;
   private FunctionNames functionNames;
   private boolean gcDisabled;
   private int hideobj;
   private boolean ignoreBlankCells;
   private int initialFileSize;
   private Locale locale;
   private HashMap localeFunctionNames;
   private boolean mergedCellCheckingDisabled;
   private boolean namesDisabled;
   private boolean propertySetsDisabled;
   private boolean rationalizationDisabled;
   private boolean refreshAll;
   private boolean template;
   private File temporaryFileDuringWriteDirectory;
   private boolean useTemporaryFileDuringWrite;
   private boolean windowProtected;
   private String writeAccess;

   public WorkbookSettings() {
      boolean var1 = false;
      this.excel9file = false;
      this.initialFileSize = 5242880;
      this.arrayGrowSize = 1048576;
      this.localeFunctionNames = new HashMap();
      this.excelDisplayLanguage = CountryCode.USA.getCode();
      this.excelRegionalSettings = CountryCode.UK.getCode();
      this.refreshAll = false;
      this.template = false;
      this.excel9file = false;
      this.windowProtected = false;
      this.hideobj = 0;

      SecurityException var13;
      SecurityException var10000;
      boolean var10001;
      label84: {
         label88: {
            try {
               this.setSuppressWarnings(Boolean.getBoolean("jxl.nowarnings"));
               this.drawingsDisabled = Boolean.getBoolean("jxl.nodrawings");
               this.namesDisabled = Boolean.getBoolean("jxl.nonames");
               this.gcDisabled = Boolean.getBoolean("jxl.nogc");
               this.rationalizationDisabled = Boolean.getBoolean("jxl.norat");
               this.mergedCellCheckingDisabled = Boolean.getBoolean("jxl.nomergedcellchecks");
               this.formulaReferenceAdjustDisabled = Boolean.getBoolean("jxl.noformulaadjust");
               this.propertySetsDisabled = Boolean.getBoolean("jxl.nopropertysets");
               this.ignoreBlankCells = Boolean.getBoolean("jxl.ignoreblanks");
               this.cellValidationDisabled = Boolean.getBoolean("jxl.nocellvalidation");
            } catch (SecurityException var12) {
               var10000 = var12;
               var10001 = false;
               break label88;
            }

            label79: {
               try {
                  if (Boolean.getBoolean("jxl.autofilter")) {
                     break label79;
                  }
               } catch (SecurityException var11) {
                  var10000 = var11;
                  var10001 = false;
                  break label88;
               }

               var1 = true;
            }

            String var2;
            try {
               this.autoFilterDisabled = var1;
               this.useTemporaryFileDuringWrite = Boolean.getBoolean("jxl.usetemporaryfileduringwrite");
               var2 = System.getProperty("jxl.temporaryfileduringwritedirectory");
            } catch (SecurityException var10) {
               var10000 = var10;
               var10001 = false;
               break label88;
            }

            if (var2 != null) {
               try {
                  File var3 = new File(var2);
                  this.temporaryFileDuringWriteDirectory = var3;
               } catch (SecurityException var9) {
                  var10000 = var9;
                  var10001 = false;
                  break label88;
               }
            }

            try {
               this.encoding = System.getProperty("file.encoding");
               break label84;
            } catch (SecurityException var8) {
               var10000 = var8;
               var10001 = false;
            }
         }

         var13 = var10000;
         logger.warn("Error accessing system properties.", var13);
      }

      label62: {
         label61: {
            label90: {
               label58:
               try {
                  if (System.getProperty("jxl.lang") != null && System.getProperty("jxl.country") != null) {
                     break label58;
                  }
                  break label90;
               } catch (SecurityException var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label62;
               }

               try {
                  Locale var14 = new Locale(System.getProperty("jxl.lang"), System.getProperty("jxl.country"));
                  this.locale = var14;
                  break label61;
               } catch (SecurityException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label62;
               }
            }

            try {
               this.locale = Locale.getDefault();
            } catch (SecurityException var5) {
               var10000 = var5;
               var10001 = false;
               break label62;
            }
         }

         try {
            if (System.getProperty("jxl.encoding") != null) {
               this.encoding = System.getProperty("jxl.encoding");
            }

            return;
         } catch (SecurityException var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      var13 = var10000;
      logger.warn("Error accessing system properties.", var13);
      this.locale = Locale.getDefault();
   }

   public int getArrayGrowSize() {
      return this.arrayGrowSize;
   }

   public boolean getAutoFilterDisabled() {
      return this.autoFilterDisabled;
   }

   public boolean getCellValidationDisabled() {
      return this.cellValidationDisabled;
   }

   public int getCharacterSet() {
      return this.characterSet;
   }

   public boolean getDrawingsDisabled() {
      return this.drawingsDisabled;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public boolean getExcel9File() {
      return this.excel9file;
   }

   public String getExcelDisplayLanguage() {
      return this.excelDisplayLanguage;
   }

   public String getExcelRegionalSettings() {
      return this.excelRegionalSettings;
   }

   public boolean getFormulaAdjust() {
      return this.formulaReferenceAdjustDisabled ^ true;
   }

   public FunctionNames getFunctionNames() {
      if (this.functionNames == null) {
         FunctionNames var1 = (FunctionNames)this.localeFunctionNames.get(this.locale);
         this.functionNames = var1;
         if (var1 == null) {
            var1 = new FunctionNames(this.locale);
            this.functionNames = var1;
            this.localeFunctionNames.put(this.locale, var1);
         }
      }

      return this.functionNames;
   }

   public boolean getGCDisabled() {
      return this.gcDisabled;
   }

   public int getHideobj() {
      return this.hideobj;
   }

   public boolean getIgnoreBlanks() {
      return this.ignoreBlankCells;
   }

   public int getInitialFileSize() {
      return this.initialFileSize;
   }

   public Locale getLocale() {
      return this.locale;
   }

   public boolean getMergedCellCheckingDisabled() {
      return this.mergedCellCheckingDisabled;
   }

   public boolean getNamesDisabled() {
      return this.namesDisabled;
   }

   public boolean getPropertySetsDisabled() {
      return this.propertySetsDisabled;
   }

   public boolean getRationalizationDisabled() {
      return this.rationalizationDisabled;
   }

   public boolean getRefreshAll() {
      return this.refreshAll;
   }

   public boolean getTemplate() {
      return this.template;
   }

   public File getTemporaryFileDuringWriteDirectory() {
      return this.temporaryFileDuringWriteDirectory;
   }

   public boolean getUseTemporaryFileDuringWrite() {
      return this.useTemporaryFileDuringWrite;
   }

   public boolean getWindowProtected() {
      return this.windowProtected;
   }

   public String getWriteAccess() {
      return this.writeAccess;
   }

   public void setArrayGrowSize(int var1) {
      this.arrayGrowSize = var1;
   }

   public void setAutoFilterDisabled(boolean var1) {
      this.autoFilterDisabled = var1;
   }

   public void setCellValidationDisabled(boolean var1) {
      this.cellValidationDisabled = var1;
   }

   public void setCharacterSet(int var1) {
      this.characterSet = var1;
   }

   public void setDrawingsDisabled(boolean var1) {
      this.drawingsDisabled = var1;
   }

   public void setEncoding(String var1) {
      this.encoding = var1;
   }

   public void setExcel9File(boolean var1) {
      this.excel9file = var1;
   }

   public void setExcelDisplayLanguage(String var1) {
      this.excelDisplayLanguage = var1;
   }

   public void setExcelRegionalSettings(String var1) {
      this.excelRegionalSettings = var1;
   }

   public void setFormulaAdjust(boolean var1) {
      this.formulaReferenceAdjustDisabled = var1 ^ true;
   }

   public void setGCDisabled(boolean var1) {
      this.gcDisabled = var1;
   }

   public void setHideobj(int var1) {
      this.hideobj = var1;
   }

   public void setIgnoreBlanks(boolean var1) {
      this.ignoreBlankCells = var1;
   }

   public void setInitialFileSize(int var1) {
      this.initialFileSize = var1;
   }

   public void setLocale(Locale var1) {
      this.locale = var1;
   }

   public void setMergedCellChecking(boolean var1) {
      this.mergedCellCheckingDisabled = var1 ^ true;
   }

   public void setNamesDisabled(boolean var1) {
      this.namesDisabled = var1;
   }

   public void setPropertySets(boolean var1) {
      this.propertySetsDisabled = var1 ^ true;
   }

   public void setRationalization(boolean var1) {
      this.rationalizationDisabled = var1 ^ true;
   }

   public void setRefreshAll(boolean var1) {
      this.refreshAll = var1;
   }

   public void setSuppressWarnings(boolean var1) {
      logger.setSuppressWarnings(var1);
   }

   public void setTemplate(boolean var1) {
      this.template = var1;
   }

   public void setTemporaryFileDuringWriteDirectory(File var1) {
      this.temporaryFileDuringWriteDirectory = var1;
   }

   public void setUseTemporaryFileDuringWrite(boolean var1) {
      this.useTemporaryFileDuringWrite = var1;
   }

   public void setWindowProtected(boolean var1) {
      this.windowProtected = this.windowProtected;
   }

   public void setWriteAccess(String var1) {
      this.writeAccess = var1;
   }
}
