package org.apache.log4j.lf5.viewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.apache.log4j.lf5.util.LogFileParser;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.apache.log4j.lf5.viewer.configure.MRUFileManager;

public class LogBrokerMonitor {
   public static final String DETAILED_VIEW = "Detailed";
   protected String _NDCTextFilter = "";
   protected boolean _callSystemExitOnClose;
   protected CategoryExplorerTree _categoryExplorerTree;
   protected List _columns;
   protected ConfigurationManager _configurationManager;
   protected String _currentView;
   protected List _displayedLogBrokerProperties;
   protected File _fileLocation;
   protected String _fontName;
   protected int _fontSize;
   protected JComboBox _fontSizeCombo;
   protected boolean _isDisposed;
   protected Dimension _lastTableViewportSize;
   protected LogLevel _leastSevereDisplayedLogLevel;
   protected List _levels;
   protected boolean _loadSystemFonts;
   protected Object _lock;
   protected Map _logLevelMenuItems;
   protected JFrame _logMonitorFrame;
   protected int _logMonitorFrameHeight = 500;
   protected int _logMonitorFrameWidth = 550;
   protected Map _logTableColumnMenuItems;
   protected JScrollPane _logTableScrollPane;
   protected MRUFileManager _mruFileManager;
   protected String _searchText;
   protected JLabel _statusLabel;
   protected LogTable _table;
   protected boolean _trackTableScrollPane;

   public LogBrokerMonitor(List var1) {
      this._leastSevereDisplayedLogLevel = LogLevel.DEBUG;
      this._lock = new Object();
      this._fontSize = 10;
      this._fontName = "Dialog";
      this._currentView = "Detailed";
      this._loadSystemFonts = false;
      this._trackTableScrollPane = true;
      this._callSystemExitOnClose = false;
      this._displayedLogBrokerProperties = new Vector();
      this._logLevelMenuItems = new HashMap();
      this._logTableColumnMenuItems = new HashMap();
      this._columns = null;
      this._isDisposed = false;
      this._configurationManager = null;
      this._mruFileManager = null;
      this._fileLocation = null;
      this._levels = var1;
      this._columns = LogTableColumn.getLogTableColumns();
      String var2 = System.getProperty("monitor.exit");
      String var3 = var2;
      if (var2 == null) {
         var3 = "false";
      }

      if (var3.trim().toLowerCase().equals("true")) {
         this._callSystemExitOnClose = true;
      }

      this.initComponents();
      this._logMonitorFrame.addWindowListener(new LogBrokerMonitorWindowAdaptor(this));
   }

   public void addDisplayedProperty(Object var1) {
      this._displayedLogBrokerProperties.add(var1);
   }

   public void addMessage(LogRecord var1) {
      if (!this._isDisposed) {
         SwingUtilities.invokeLater(new LogBrokerMonitor$2(this, var1));
      }
   }

   protected void addTableModelProperties() {
      FilteredLogTableModel var1 = this._table.getFilteredLogTableModel();
      this.addDisplayedProperty(new LogBrokerMonitor$5(this));
      this.addDisplayedProperty(new LogBrokerMonitor$6(this, var1));
   }

   protected void centerFrame(JFrame var1) {
      Dimension var3 = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension var2 = var1.getSize();
      var1.setLocation((var3.width - var2.width) / 2, (var3.height - var2.height) / 2);
   }

   protected int changeFontSizeCombo(JComboBox var1, int var2) {
      int var7 = var1.getItemCount();
      int var3 = 0;
      Object var8 = var1.getItemAt(0);

      int var4;
      int var5;
      for(var4 = Integer.parseInt(String.valueOf(var8)); var3 < var7; var4 = var5) {
         Object var10 = var1.getItemAt(var3);
         int var6 = Integer.parseInt(String.valueOf(var10));
         Object var9 = var8;
         var5 = var4;
         if (var4 < var6) {
            var9 = var8;
            var5 = var4;
            if (var6 <= var2) {
               var9 = var10;
               var5 = var6;
            }
         }

         ++var3;
         var8 = var9;
      }

      var1.setSelectedItem(var8);
      return var4;
   }

   protected void clearDetailTextArea() {
      this._table._detailTextArea.setText("");
   }

   protected void closeAfterConfirm() {
      StringBuffer var2 = new StringBuffer();
      if (!this._callSystemExitOnClose) {
         var2.append("Are you sure you want to close the logging ");
         var2.append("console?\n");
         var2.append("(Note: This will not shut down the Virtual Machine,\n");
         var2.append("or the Swing event thread.)");
      } else {
         var2.append("Are you sure you want to exit?\n");
         var2.append("This will shut down the Virtual Machine.\n");
      }

      String var1;
      if (this._callSystemExitOnClose) {
         var1 = "Are you sure you want to exit?";
      } else {
         var1 = "Are you sure you want to dispose of the Logging Console?";
      }

      if (JOptionPane.showConfirmDialog(this._logMonitorFrame, var2.toString(), var1, 2, 3, (Icon)null) == 0) {
         this.dispose();
      }

   }

   protected JMenuItem createAllLogLevelsMenuItem() {
      JMenuItem var1 = new JMenuItem("Show all LogLevels");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$8(this));
      return var1;
   }

   protected JMenuItem createAllLogTableColumnsMenuItem() {
      JMenuItem var1 = new JMenuItem("Show all Columns");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$14(this));
      return var1;
   }

   protected JMenuItem createCloseMI() {
      JMenuItem var1 = new JMenuItem("Close");
      var1.setMnemonic('c');
      var1.setAccelerator(KeyStroke.getKeyStroke("control Q"));
      var1.addActionListener(new LogBrokerMonitor$18(this));
      return var1;
   }

   protected JMenuItem createConfigureMaxRecords() {
      JMenuItem var1 = new JMenuItem("Set Max Number of Records");
      var1.setMnemonic('m');
      var1.addActionListener(new LogBrokerMonitor$23(this));
      return var1;
   }

   protected JMenu createConfigureMenu() {
      JMenu var1 = new JMenu("Configure");
      var1.setMnemonic('c');
      var1.add(this.createConfigureSave());
      var1.add(this.createConfigureReset());
      var1.add(this.createConfigureMaxRecords());
      return var1;
   }

   protected JMenuItem createConfigureReset() {
      JMenuItem var1 = new JMenuItem("Reset");
      var1.setMnemonic('r');
      var1.addActionListener(new LogBrokerMonitor$22(this));
      return var1;
   }

   protected JMenuItem createConfigureSave() {
      JMenuItem var1 = new JMenuItem("Save");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$21(this));
      return var1;
   }

   protected JTextArea createDetailTextArea() {
      JTextArea var1 = new JTextArea();
      var1.setFont(new Font("Monospaced", 0, 14));
      var1.setTabSize(3);
      var1.setLineWrap(true);
      var1.setWrapStyleWord(false);
      return var1;
   }

   protected JMenuItem createEditFindMI() {
      JMenuItem var1 = new JMenuItem("Find");
      var1.setMnemonic('f');
      var1.setAccelerator(KeyStroke.getKeyStroke("control F"));
      var1.addActionListener(new LogBrokerMonitor$26(this));
      return var1;
   }

   protected JMenuItem createEditFindNextMI() {
      JMenuItem var1 = new JMenuItem("Find Next");
      var1.setMnemonic('n');
      var1.setAccelerator(KeyStroke.getKeyStroke("F3"));
      var1.addActionListener(new LogBrokerMonitor$25(this));
      return var1;
   }

   protected JMenu createEditMenu() {
      JMenu var1 = new JMenu("Edit");
      var1.setMnemonic('e');
      var1.add(this.createEditFindMI());
      var1.add(this.createEditFindNextMI());
      var1.addSeparator();
      var1.add(this.createEditSortNDCMI());
      var1.add(this.createEditRestoreAllNDCMI());
      return var1;
   }

   protected JMenuItem createEditRestoreAllNDCMI() {
      JMenuItem var1 = new JMenuItem("Restore all NDCs");
      var1.setMnemonic('r');
      var1.addActionListener(new LogBrokerMonitor$28(this));
      return var1;
   }

   protected JMenuItem createEditSortNDCMI() {
      JMenuItem var1 = new JMenuItem("Sort by NDC");
      var1.setMnemonic('s');
      var1.addActionListener(new LogBrokerMonitor$27(this));
      return var1;
   }

   protected JMenuItem createExitMI() {
      JMenuItem var1 = new JMenuItem("Exit");
      var1.setMnemonic('x');
      var1.addActionListener(new LogBrokerMonitor$20(this));
      return var1;
   }

   protected JMenu createFileMenu() {
      JMenu var1 = new JMenu("File");
      var1.setMnemonic('f');
      var1.add(this.createOpenMI());
      var1.add(this.createOpenURLMI());
      var1.addSeparator();
      var1.add(this.createCloseMI());
      this.createMRUFileListMI(var1);
      var1.addSeparator();
      var1.add(this.createExitMI());
      return var1;
   }

   protected JMenu createHelpMenu() {
      JMenu var1 = new JMenu("Help");
      var1.setMnemonic('h');
      var1.add(this.createHelpProperties());
      return var1;
   }

   protected JMenuItem createHelpProperties() {
      JMenuItem var1 = new JMenuItem("LogFactor5 Properties");
      var1.setMnemonic('l');
      var1.addActionListener(new LogBrokerMonitor$24(this));
      return var1;
   }

   protected JMenu createLogLevelColorMenu() {
      JMenu var1 = new JMenu("Configure LogLevel Colors");
      var1.setMnemonic('c');
      Iterator var2 = this.getLogLevels();

      while(var2.hasNext()) {
         var1.add(this.createSubMenuItem((LogLevel)var2.next()));
      }

      return var1;
   }

   protected JComboBox createLogLevelCombo() {
      JComboBox var2 = new JComboBox();
      Iterator var1 = this.getLogLevels();

      while(var1.hasNext()) {
         var2.addItem(var1.next());
      }

      var2.setSelectedItem(this._leastSevereDisplayedLogLevel);
      var2.addActionListener(new LogBrokerMonitor$32(this));
      var2.setMaximumSize(var2.getPreferredSize());
      return var2;
   }

   protected JMenu createLogLevelMenu() {
      JMenu var1 = new JMenu("Log Level");
      var1.setMnemonic('l');
      Iterator var2 = this.getLogLevels();

      while(var2.hasNext()) {
         var1.add(this.getMenuItem((LogLevel)var2.next()));
      }

      var1.addSeparator();
      var1.add(this.createAllLogLevelsMenuItem());
      var1.add(this.createNoLogLevelsMenuItem());
      var1.addSeparator();
      var1.add(this.createLogLevelColorMenu());
      var1.add(this.createResetLogLevelColorMenuItem());
      return var1;
   }

   protected LogRecordFilter createLogRecordFilter() {
      return new LogBrokerMonitor$3(this);
   }

   protected JCheckBoxMenuItem createLogTableColumnMenuItem(LogTableColumn var1) {
      JCheckBoxMenuItem var2 = new JCheckBoxMenuItem(var1.toString());
      var2.setSelected(true);
      var2.setMnemonic(var1.toString().charAt(0));
      var2.addActionListener(new LogBrokerMonitor$13(this));
      return var2;
   }

   protected void createMRUFileListMI(JMenu var1) {
      String[] var4 = this._mruFileManager.getMRUFileList();
      if (var4 != null) {
         var1.addSeparator();

         int var3;
         for(int var2 = 0; var2 < var4.length; var2 = var3) {
            StringBuffer var5 = new StringBuffer();
            var3 = var2 + 1;
            JMenuItem var6 = new JMenuItem(var5.append(var3).append(" ").append(var4[var2]).toString());
            var6.setMnemonic(var3);
            var6.addActionListener(new LogBrokerMonitor$19(this));
            var1.add(var6);
         }
      }

   }

   protected JMenuBar createMenuBar() {
      JMenuBar var1 = new JMenuBar();
      var1.add(this.createFileMenu());
      var1.add(this.createEditMenu());
      var1.add(this.createLogLevelMenu());
      var1.add(this.createViewMenu());
      var1.add(this.createConfigureMenu());
      var1.add(this.createHelpMenu());
      return var1;
   }

   protected JCheckBoxMenuItem createMenuItem(LogLevel var1) {
      JCheckBoxMenuItem var2 = new JCheckBoxMenuItem(var1.toString());
      var2.setSelected(true);
      var2.setMnemonic(var1.toString().charAt(0));
      var2.addActionListener(new LogBrokerMonitor$12(this));
      return var2;
   }

   protected LogRecordFilter createNDCLogRecordFilter(String var1) {
      this._NDCTextFilter = var1;
      return new LogBrokerMonitor$4(this);
   }

   protected JMenuItem createNoLogLevelsMenuItem() {
      JMenuItem var1 = new JMenuItem("Hide all LogLevels");
      var1.setMnemonic('h');
      var1.addActionListener(new LogBrokerMonitor$9(this));
      return var1;
   }

   protected JMenuItem createNoLogTableColumnsMenuItem() {
      JMenuItem var1 = new JMenuItem("Hide all Columns");
      var1.setMnemonic('h');
      var1.addActionListener(new LogBrokerMonitor$15(this));
      return var1;
   }

   protected JMenuItem createOpenMI() {
      JMenuItem var1 = new JMenuItem("Open...");
      var1.setMnemonic('o');
      var1.addActionListener(new LogBrokerMonitor$16(this));
      return var1;
   }

   protected JMenuItem createOpenURLMI() {
      JMenuItem var1 = new JMenuItem("Open URL...");
      var1.setMnemonic('u');
      var1.addActionListener(new LogBrokerMonitor$17(this));
      return var1;
   }

   protected JMenuItem createResetLogLevelColorMenuItem() {
      JMenuItem var1 = new JMenuItem("Reset LogLevel Colors");
      var1.setMnemonic('r');
      var1.addActionListener(new LogBrokerMonitor$10(this));
      return var1;
   }

   protected JPanel createStatusArea() {
      JPanel var2 = new JPanel();
      JLabel var1 = new JLabel("No log records to display.");
      this._statusLabel = var1;
      var1.setHorizontalAlignment(2);
      var2.setBorder(BorderFactory.createEtchedBorder());
      var2.setLayout(new FlowLayout(0, 0, 0));
      var2.add(var1);
      return var2;
   }

   protected JMenuItem createSubMenuItem(LogLevel var1) {
      JMenuItem var2 = new JMenuItem(var1.toString());
      var2.setMnemonic(var1.toString().charAt(0));
      var2.addActionListener(new LogBrokerMonitor$11(this, var2, var1));
      return var2;
   }

   protected JToolBar createToolBar() {
      JToolBar var4 = new JToolBar();
      var4.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
      JComboBox var6 = new JComboBox();
      JComboBox var5 = new JComboBox();
      this._fontSizeCombo = var5;
      ClassLoader var3 = this.getClass().getClassLoader();
      ClassLoader var2 = var3;
      if (var3 == null) {
         var2 = ClassLoader.getSystemClassLoader();
      }

      URL var8 = var2.getResource("org/apache/log4j/lf5/viewer/images/channelexplorer_new.gif");
      ImageIcon var7 = null;
      if (var8 != null) {
         var7 = new ImageIcon(var8);
      }

      JButton var9 = new JButton("Clear Log Table");
      if (var7 != null) {
         var9.setIcon(var7);
      }

      var9.setToolTipText("Clear Log Table.");
      var9.addActionListener(new LogBrokerMonitor$29(this));
      Toolkit var10 = Toolkit.getDefaultToolkit();
      String[] var11;
      if (this._loadSystemFonts) {
         var11 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      } else {
         var11 = var10.getFontList();
      }

      for(int var1 = 0; var1 < var11.length; ++var1) {
         var6.addItem(var11[var1]);
      }

      var6.setSelectedItem(this._fontName);
      var6.addActionListener(new LogBrokerMonitor$30(this));
      var5.addItem("8");
      var5.addItem("9");
      var5.addItem("10");
      var5.addItem("12");
      var5.addItem("14");
      var5.addItem("16");
      var5.addItem("18");
      var5.addItem("24");
      var5.setSelectedItem(String.valueOf(this._fontSize));
      var5.addActionListener(new LogBrokerMonitor$31(this));
      var4.add(new JLabel(" Font: "));
      var4.add(var6);
      var4.add(var5);
      var4.addSeparator();
      var4.addSeparator();
      var4.add(var9);
      var9.setAlignmentY(0.5F);
      var9.setAlignmentX(0.5F);
      var6.setMaximumSize(var6.getPreferredSize());
      var5.setMaximumSize(var5.getPreferredSize());
      return var4;
   }

   protected JMenu createViewMenu() {
      JMenu var1 = new JMenu("View");
      var1.setMnemonic('v');
      Iterator var2 = this.getLogTableColumns();

      while(var2.hasNext()) {
         var1.add(this.getLogTableColumnMenuItem((LogTableColumn)var2.next()));
      }

      var1.addSeparator();
      var1.add(this.createAllLogTableColumnsMenuItem());
      var1.add(this.createNoLogTableColumnsMenuItem());
      return var1;
   }

   public void dispose() {
      this._logMonitorFrame.dispose();
      this._isDisposed = true;
      if (this._callSystemExitOnClose) {
         System.exit(0);
      }

   }

   protected int findRecord(int var1, String var2, List var3) {
      byte var5 = 0;
      if (var1 < 0) {
         var1 = 0;
      } else {
         ++var1;
      }

      int var6 = var3.size();

      int var4;
      for(var4 = var1; var4 < var6; ++var4) {
         if (this.matches((LogRecord)var3.get(var4), var2)) {
            return var4;
         }
      }

      for(var4 = var5; var4 < var1; ++var4) {
         if (this.matches((LogRecord)var3.get(var4), var2)) {
            return var4;
         }
      }

      return -1;
   }

   protected void findSearchText() {
      String var1 = this._searchText;
      if (var1 != null && var1.length() != 0) {
         this.selectRow(this.findRecord(this.getFirstSelectedRow(), var1, this._table.getFilteredLogTableModel().getFilteredRecords()));
      }

   }

   public JFrame getBaseFrame() {
      return this._logMonitorFrame;
   }

   public boolean getCallSystemExitOnClose() {
      return this._callSystemExitOnClose;
   }

   public CategoryExplorerTree getCategoryExplorerTree() {
      return this._categoryExplorerTree;
   }

   public DateFormatManager getDateFormatManager() {
      return this._table.getDateFormatManager();
   }

   protected int getFirstSelectedRow() {
      return this._table.getSelectionModel().getMinSelectionIndex();
   }

   public Map getLogLevelMenuItems() {
      return this._logLevelMenuItems;
   }

   protected Iterator getLogLevels() {
      return this._levels.iterator();
   }

   protected JCheckBoxMenuItem getLogTableColumnMenuItem(LogTableColumn var1) {
      JCheckBoxMenuItem var3 = (JCheckBoxMenuItem)this._logTableColumnMenuItems.get(var1);
      JCheckBoxMenuItem var2 = var3;
      if (var3 == null) {
         var2 = this.createLogTableColumnMenuItem(var1);
         this._logTableColumnMenuItems.put(var1, var2);
      }

      return var2;
   }

   public Map getLogTableColumnMenuItems() {
      return this._logTableColumnMenuItems;
   }

   protected Iterator getLogTableColumns() {
      return this._columns.iterator();
   }

   protected JCheckBoxMenuItem getMenuItem(LogLevel var1) {
      JCheckBoxMenuItem var3 = (JCheckBoxMenuItem)this._logLevelMenuItems.get(var1);
      JCheckBoxMenuItem var2 = var3;
      if (var3 == null) {
         var2 = this.createMenuItem(var1);
         this._logLevelMenuItems.put(var1, var2);
      }

      return var2;
   }

   public String getNDCTextFilter() {
      return this._NDCTextFilter;
   }

   protected String getRecordsDisplayedMessage() {
      FilteredLogTableModel var1 = this._table.getFilteredLogTableModel();
      return this.getStatusText(var1.getRowCount(), var1.getTotalRowCount());
   }

   protected String getStatusText(int var1, int var2) {
      StringBuffer var3 = new StringBuffer();
      var3.append("Displaying: ");
      var3.append(var1);
      var3.append(" records out of a total of: ");
      var3.append(var2);
      var3.append(" records.");
      return var3.toString();
   }

   public JCheckBoxMenuItem getTableColumnMenuItem(LogTableColumn var1) {
      return this.getLogTableColumnMenuItem(var1);
   }

   public void hide() {
      this._logMonitorFrame.setVisible(false);
   }

   protected void initComponents() {
      JFrame var1 = new JFrame("LogFactor5");
      this._logMonitorFrame = var1;
      var1.setDefaultCloseOperation(0);
      URL var4 = this.getClass().getResource("/org/apache/log4j/lf5/viewer/images/lf5_small_icon.gif");
      if (var4 != null) {
         this._logMonitorFrame.setIconImage((new ImageIcon(var4)).getImage());
      }

      this.updateFrameSize();
      JTextArea var2 = this.createDetailTextArea();
      JScrollPane var5 = new JScrollPane(var2);
      LogTable var7 = new LogTable(var2);
      this._table = var7;
      this.setView(this._currentView, var7);
      this._table.setFont(new Font(this._fontName, 0, this._fontSize));
      JScrollPane var8 = new JScrollPane(this._table);
      this._logTableScrollPane = var8;
      if (this._trackTableScrollPane) {
         var8.getVerticalScrollBar().addAdjustmentListener(new TrackingAdjustmentListener());
      }

      JSplitPane var9 = new JSplitPane();
      var9.setOneTouchExpandable(true);
      var9.setOrientation(0);
      var9.setLeftComponent(this._logTableScrollPane);
      var9.setRightComponent(var5);
      var9.setDividerLocation(350);
      this._categoryExplorerTree = new CategoryExplorerTree();
      this._table.getFilteredLogTableModel().setLogRecordFilter(this.createLogRecordFilter());
      JScrollPane var3 = new JScrollPane(this._categoryExplorerTree);
      var3.setPreferredSize(new Dimension(130, 400));
      this._mruFileManager = new MRUFileManager();
      JSplitPane var6 = new JSplitPane();
      var6.setOneTouchExpandable(true);
      var6.setRightComponent(var9);
      var6.setLeftComponent(var3);
      var6.setDividerLocation(130);
      this._logMonitorFrame.getRootPane().setJMenuBar(this.createMenuBar());
      this._logMonitorFrame.getContentPane().add(var6, "Center");
      this._logMonitorFrame.getContentPane().add(this.createToolBar(), "North");
      this._logMonitorFrame.getContentPane().add(this.createStatusArea(), "South");
      this.makeLogTableListenToCategoryExplorer();
      this.addTableModelProperties();
      this._configurationManager = new ConfigurationManager(this, this._table);
   }

   protected boolean loadLogFile(File var1) {
      boolean var2;
      try {
         LogFileParser var3 = new LogFileParser(var1);
         var3.parse(this);
      } catch (IOException var4) {
         new LogFactor5ErrorDialog(this.getBaseFrame(), "Error reading " + var1.getName());
         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   protected boolean loadLogFile(URL var1) {
      boolean var2;
      try {
         LogFileParser var3 = new LogFileParser(var1.openStream());
         var3.parse(this);
      } catch (IOException var4) {
         new LogFactor5ErrorDialog(this.getBaseFrame(), "Error reading URL:" + var1.getFile());
         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   protected void makeLogTableListenToCategoryExplorer() {
      LogBrokerMonitor$7 var1 = new LogBrokerMonitor$7(this);
      this._categoryExplorerTree.getExplorerModel().addActionListener(var1);
   }

   protected boolean matches(LogRecord var1, String var2) {
      String var3 = var1.getMessage();
      String var4 = var1.getNDC();
      if ((var3 != null || var4 != null) && var2 != null) {
         return var3.toLowerCase().indexOf(var2.toLowerCase()) != -1 || var4.toLowerCase().indexOf(var2.toLowerCase()) != -1;
      } else {
         return false;
      }
   }

   protected void pause(int var1) {
      long var2 = (long)var1;

      try {
         Thread.sleep(var2);
      } catch (InterruptedException var5) {
      }

   }

   protected void refresh(JTextArea var1) {
      String var2 = var1.getText();
      var1.setText("");
      var1.setText(var2);
   }

   protected void refreshDetailTextArea() {
      this.refresh(this._table._detailTextArea);
   }

   protected void requestClose() {
      this.setCallSystemExitOnClose(false);
      this.closeAfterConfirm();
   }

   protected void requestExit() {
      this._mruFileManager.save();
      this.setCallSystemExitOnClose(true);
      this.closeAfterConfirm();
   }

   protected void requestOpen() {
      JFileChooser var1;
      if (this._fileLocation == null) {
         var1 = new JFileChooser();
      } else {
         var1 = new JFileChooser(this._fileLocation);
      }

      if (var1.showOpenDialog(this._logMonitorFrame) == 0) {
         File var2 = var1.getSelectedFile();
         if (this.loadLogFile(var2)) {
            this._fileLocation = var1.getSelectedFile();
            this._mruFileManager.set(var2);
            this.updateMRUList();
         }
      }

   }

   protected void requestOpenMRU(ActionEvent var1) {
      StringTokenizer var6 = new StringTokenizer(var1.getActionCommand());
      String var3 = var6.nextToken().trim();
      String var7 = var6.nextToken("\n");

      try {
         int var2 = Integer.parseInt(var3) - 1;
         InputStream var4 = this._mruFileManager.getInputStream(var2);
         LogFileParser var8 = new LogFileParser(var4);
         var8.parse(this);
         this._mruFileManager.moveToTop(var2);
         this.updateMRUList();
      } catch (Exception var5) {
         new LogFactor5ErrorDialog(this.getBaseFrame(), "Unable to load file " + var7);
      }

   }

   protected void requestOpenURL() {
      String var2 = (new LogFactor5InputDialog(this.getBaseFrame(), "Open URL", "URL:")).getText();
      if (var2 != null) {
         String var1 = var2;
         if (var2.indexOf("://") == -1) {
            var1 = "http://" + var2;
         }

         try {
            URL var4 = new URL(var1);
            if (this.loadLogFile(var4)) {
               this._mruFileManager.set(var4);
               this.updateMRUList();
            }
         } catch (MalformedURLException var3) {
            new LogFactor5ErrorDialog(this.getBaseFrame(), "Error reading URL.");
         }
      }

   }

   protected void resetConfiguration() {
      this._configurationManager.reset();
   }

   protected void saveConfiguration() {
      this._configurationManager.save();
   }

   protected void selectAllLogLevels(boolean var1) {
      Iterator var2 = this.getLogLevels();

      while(var2.hasNext()) {
         this.getMenuItem((LogLevel)var2.next()).setSelected(var1);
      }

   }

   protected void selectAllLogTableColumns(boolean var1) {
      Iterator var2 = this.getLogTableColumns();

      while(var2.hasNext()) {
         this.getLogTableColumnMenuItem((LogTableColumn)var2.next()).setSelected(var1);
      }

   }

   protected void selectRow(int var1) {
      if (var1 == -1) {
         String var2 = this._searchText + " not found.";
         JOptionPane.showMessageDialog(this._logMonitorFrame, var2, "Text not found", 1);
      } else {
         LF5SwingUtils.selectRow(var1, this._table, this._logTableScrollPane);
      }
   }

   public void setCallSystemExitOnClose(boolean var1) {
      this._callSystemExitOnClose = var1;
   }

   public void setDateFormatManager(DateFormatManager var1) {
      this._table.setDateFormatManager(var1);
   }

   public void setFontSize(int var1) {
      this.changeFontSizeCombo(this._fontSizeCombo, var1);
   }

   protected void setFontSize(Component var1, int var2) {
      Font var3 = var1.getFont();
      var1.setFont(new Font(var3.getFontName(), var3.getStyle(), var2));
   }

   protected void setFontSizeSilently(int var1) {
      this._fontSize = var1;
      this.setFontSize(this._table._detailTextArea, var1);
      this.selectRow(0);
      this.setFontSize(this._table, var1);
   }

   public void setFrameSize(int var1, int var2) {
      Dimension var3 = Toolkit.getDefaultToolkit().getScreenSize();
      if (var1 > 0 && var1 < var3.width) {
         this._logMonitorFrameWidth = var1;
      }

      if (var2 > 0 && var2 < var3.height) {
         this._logMonitorFrameHeight = var2;
      }

      this.updateFrameSize();
   }

   protected void setLeastSevereDisplayedLogLevel(LogLevel var1) {
      if (var1 != null && this._leastSevereDisplayedLogLevel != var1) {
         this._leastSevereDisplayedLogLevel = var1;
         this._table.getFilteredLogTableModel().refresh();
         this.updateStatusLabel();
      }

   }

   public void setMaxNumberOfLogRecords(int var1) {
      this._table.getFilteredLogTableModel().setMaxNumberOfLogRecords(var1);
   }

   protected void setMaxRecordConfiguration() {
      String var2 = (new LogFactor5InputDialog(this.getBaseFrame(), "Set Max Number of Records", "", 10)).getText();
      if (var2 != null) {
         try {
            this.setMaxNumberOfLogRecords(Integer.parseInt(var2));
         } catch (NumberFormatException var3) {
            new LogFactor5ErrorDialog(this.getBaseFrame(), "'" + var2 + "' is an invalid parameter.\nPlease try again.");
            this.setMaxRecordConfiguration();
         }
      }

   }

   public void setNDCLogRecordFilter(String var1) {
      this._table.getFilteredLogTableModel().setLogRecordFilter(this.createNDCLogRecordFilter(var1));
   }

   protected void setNDCTextFilter(String var1) {
      if (var1 == null) {
         this._NDCTextFilter = "";
      } else {
         this._NDCTextFilter = var1;
      }

   }

   protected void setSearchText(String var1) {
      this._searchText = var1;
   }

   public void setTitle(String var1) {
      this._logMonitorFrame.setTitle(var1 + " - LogFactor5");
   }

   protected void setView(String var1, LogTable var2) {
      if ("Detailed".equals(var1)) {
         var2.setDetailedView();
         this._currentView = var1;
      } else {
         throw new IllegalArgumentException(var1 + "does not match a supported view.");
      }
   }

   public void show() {
      this.show(0);
   }

   public void show(int var1) {
      if (!this._logMonitorFrame.isVisible()) {
         SwingUtilities.invokeLater(new LogBrokerMonitor$1(this, var1));
      }
   }

   protected void showLogLevelColorChangeDialog(JMenuItem var1, LogLevel var2) {
      Color var3 = JColorChooser.showDialog(this._logMonitorFrame, "Choose LogLevel Color", var1.getForeground());
      if (var3 != null) {
         var2.setLogLevelColorMap(var2, var3);
         this._table.getFilteredLogTableModel().refresh();
      }

   }

   protected void showPropertiesDialog(String var1) {
      JOptionPane.showMessageDialog(this._logMonitorFrame, this._displayedLogBrokerProperties.toArray(), var1, -1);
   }

   protected void sortByNDC() {
      String var1 = this._NDCTextFilter;
      if (var1 != null && var1.length() != 0) {
         this._table.getFilteredLogTableModel().setLogRecordFilter(this.createNDCLogRecordFilter(var1));
      }

   }

   protected void trackTableScrollPane() {
   }

   protected void updateFrameSize() {
      this._logMonitorFrame.setSize(this._logMonitorFrameWidth, this._logMonitorFrameHeight);
      this.centerFrame(this._logMonitorFrame);
   }

   protected void updateMRUList() {
      JMenu var1 = this._logMonitorFrame.getJMenuBar().getMenu(0);
      var1.removeAll();
      var1.add(this.createOpenMI());
      var1.add(this.createOpenURLMI());
      var1.addSeparator();
      var1.add(this.createCloseMI());
      this.createMRUFileListMI(var1);
      var1.addSeparator();
      var1.add(this.createExitMI());
   }

   protected void updateStatusLabel() {
      this._statusLabel.setText(this.getRecordsDisplayedMessage());
   }

   protected List updateView() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this._columns.iterator();

      while(var2.hasNext()) {
         LogTableColumn var3 = (LogTableColumn)var2.next();
         if (this.getLogTableColumnMenuItem(var3).isSelected()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   class LogBrokerMonitorWindowAdaptor extends WindowAdapter {
      protected LogBrokerMonitor _monitor;

      public LogBrokerMonitorWindowAdaptor(LogBrokerMonitor var2) {
         this._monitor = var2;
      }

      public void windowClosing(WindowEvent var1) {
         this._monitor.requestClose();
      }
   }
}
