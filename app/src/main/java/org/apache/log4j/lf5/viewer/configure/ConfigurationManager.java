package org.apache.log4j.lf5.viewer.configure;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogLevelFormatException;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogTable;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import org.apache.log4j.lf5.viewer.LogTableColumnFormatException;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerModel;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConfigurationManager {
   private static final String BLUE = "blue";
   private static final String CATEGORY = "category";
   private static final String COLOR = "color";
   private static final String COLORLEVEL = "colorlevel";
   private static final String COLUMN = "column";
   private static final String CONFIG_FILE_NAME = "lf5_configuration.xml";
   private static final String EXPANDED = "expanded";
   private static final String FIRST_CATEGORY_NAME = "Categories";
   private static final String GREEN = "green";
   private static final String LEVEL = "level";
   private static final String NAME = "name";
   private static final String NDCTEXTFILTER = "searchtext";
   private static final String PATH = "path";
   private static final String RED = "red";
   private static final String SELECTED = "selected";
   private LogBrokerMonitor _monitor;
   private LogTable _table;

   public ConfigurationManager(LogBrokerMonitor var1, LogTable var2) {
      this._monitor = var1;
      this._table = var2;
      this.load();
   }

   private void closeConfigurationXML(StringBuffer var1) {
      var1.append("</configuration>\r\n");
   }

   private void exportLogLevelColorXMLElement(String var1, Color var2, StringBuffer var3) {
      var3.append("\t\t<").append("colorlevel").append(" ").append("name");
      var3.append("=\"").append(var1).append("\" ");
      var3.append("red").append("=\"").append(var2.getRed()).append("\" ");
      var3.append("green").append("=\"").append(var2.getGreen()).append("\" ");
      var3.append("blue").append("=\"").append(var2.getBlue());
      var3.append("\"/>\r\n");
   }

   private void exportLogLevelXMLElement(String var1, boolean var2, StringBuffer var3) {
      var3.append("\t\t<").append("level").append(" ").append("name");
      var3.append("=\"").append(var1).append("\" ");
      var3.append("selected").append("=\"").append(var2);
      var3.append("\"/>\r\n");
   }

   private void exportLogTableColumnXMLElement(String var1, boolean var2, StringBuffer var3) {
      var3.append("\t\t<").append("column").append(" ").append("name");
      var3.append("=\"").append(var1).append("\" ");
      var3.append("selected").append("=\"").append(var2);
      var3.append("\"/>\r\n");
   }

   private void exportXMLElement(CategoryNode var1, TreePath var2, StringBuffer var3) {
      CategoryExplorerTree var4 = this._monitor.getCategoryExplorerTree();
      var3.append("\t<").append("category").append(" ");
      var3.append("name").append("=\"").append(var1.getTitle()).append("\" ");
      var3.append("path").append("=\"").append(treePathToString(var2)).append("\" ");
      var3.append("expanded").append("=\"").append(var4.isExpanded(var2)).append("\" ");
      var3.append("selected").append("=\"").append(var1.isSelected()).append("\"/>\r\n");
   }

   private void openConfigurationXML(StringBuffer var1) {
      var1.append("<configuration>\r\n");
   }

   private void openXMLDocument(StringBuffer var1) {
      var1.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
   }

   private void processConfigurationNode(CategoryNode var1, StringBuffer var2) {
      CategoryExplorerModel var3 = this._monitor.getCategoryExplorerTree().getExplorerModel();
      Enumeration var4 = var1.breadthFirstEnumeration();

      while(var4.hasMoreElements()) {
         var1 = (CategoryNode)var4.nextElement();
         this.exportXMLElement(var1, var3.getTreePathToRoot(var1), var2);
      }

   }

   private void processLogLevelColors(Map var1, Map var2, StringBuffer var3) {
      var3.append("\t<loglevelcolors>\r\n");
      Iterator var4 = var1.keySet().iterator();

      while(var4.hasNext()) {
         LogLevel var5 = (LogLevel)var4.next();
         Color var6 = (Color)var2.get(var5);
         this.exportLogLevelColorXMLElement(var5.getLabel(), var6, var3);
      }

      var3.append("\t</loglevelcolors>\r\n");
   }

   private void processLogLevels(Map var1, StringBuffer var2) {
      var2.append("\t<loglevels>\r\n");
      Iterator var4 = var1.keySet().iterator();

      while(var4.hasNext()) {
         LogLevel var3 = (LogLevel)var4.next();
         JCheckBoxMenuItem var5 = (JCheckBoxMenuItem)var1.get(var3);
         this.exportLogLevelXMLElement(var3.getLabel(), var5.isSelected(), var2);
      }

      var2.append("\t</loglevels>\r\n");
   }

   private void processLogRecordFilter(String var1, StringBuffer var2) {
      var2.append("\t<").append("searchtext").append(" ");
      var2.append("name").append("=\"").append(var1).append("\"");
      var2.append("/>\r\n");
   }

   private void processLogTableColumns(List var1, StringBuffer var2) {
      var2.append("\t<logtablecolumns>\r\n");
      Iterator var5 = var1.iterator();

      while(var5.hasNext()) {
         LogTableColumn var3 = (LogTableColumn)var5.next();
         JCheckBoxMenuItem var4 = this._monitor.getTableColumnMenuItem(var3);
         this.exportLogTableColumnXMLElement(var3.getLabel(), var4.isSelected(), var2);
      }

      var2.append("\t</logtablecolumns>\r\n");
   }

   public static String treePathToString(TreePath var0) {
      StringBuffer var2 = new StringBuffer();
      Object[] var4 = var0.getPath();

      for(int var1 = 1; var1 < var4.length; ++var1) {
         CategoryNode var3 = (CategoryNode)var4[var1];
         if (var1 > 1) {
            var2.append(".");
         }

         var2.append(var3.getTitle());
      }

      return var2.toString();
   }

   protected void collapseTree() {
      CategoryExplorerTree var2 = this._monitor.getCategoryExplorerTree();

      for(int var1 = var2.getRowCount() - 1; var1 > 0; --var1) {
         var2.collapseRow(var1);
      }

   }

   protected void deleteConfigurationFile() {
      try {
         File var1 = new File(this.getFilename());
         if (var1.exists()) {
            var1.delete();
         }
      } catch (SecurityException var2) {
         System.err.println("Cannot delete " + this.getFilename() + " because a security violation occured.");
      }

   }

   protected String getFilename() {
      String var2 = System.getProperty("user.home");
      String var1 = System.getProperty("file.separator");
      return var2 + var1 + "lf5" + var1 + "lf5_configuration.xml";
   }

   protected String getValue(NamedNodeMap var1, String var2) {
      return var1.getNamedItem(var2).getNodeValue();
   }

   protected void load() {
      File var1 = new File(this.getFilename());
      if (var1.exists()) {
         try {
            Document var3 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var1);
            this.processRecordFilter(var3);
            this.processCategories(var3);
            this.processLogLevels(var3);
            this.processLogLevelColors(var3);
            this.processLogTableColumns(var3);
         } catch (Exception var2) {
            System.err.println("Unable process configuration file at " + this.getFilename() + ". Error Message=" + var2.getMessage());
         }
      }

   }

   protected void processCategories(Document var1) {
      CategoryExplorerTree var4 = this._monitor.getCategoryExplorerTree();
      CategoryExplorerModel var5 = var4.getExplorerModel();
      NodeList var7 = var1.getElementsByTagName("category");
      byte var3 = this.getValue(var7.item(0).getAttributes(), "name").equalsIgnoreCase("Categories");

      for(int var2 = var7.getLength() - 1; var2 >= var3; --var2) {
         NamedNodeMap var8 = var7.item(var2).getAttributes();
         CategoryNode var6 = var5.addCategory(new CategoryPath(this.getValue(var8, "path")));
         var6.setSelected(this.getValue(var8, "selected").equalsIgnoreCase("true"));
         this.getValue(var8, "expanded").equalsIgnoreCase("true");
         var4.expandPath(var5.getTreePathToRoot(var6));
      }

   }

   protected void processLogLevelColors(Document var1) {
      NodeList var10 = var1.getElementsByTagName("colorlevel");
      LogLevel.getLogLevelColorMap();

      for(int var2 = 0; var2 < var10.getLength(); ++var2) {
         Node var6 = var10.item(var2);
         if (var6 == null) {
            return;
         }

         NamedNodeMap var11 = var6.getAttributes();
         String var7 = this.getValue(var11, "name");

         Color var12;
         LogLevel var13;
         boolean var10001;
         try {
            var13 = LogLevel.valueOf(var7);
            int var3 = Integer.parseInt(this.getValue(var11, "red"));
            int var4 = Integer.parseInt(this.getValue(var11, "green"));
            int var5 = Integer.parseInt(this.getValue(var11, "blue"));
            var12 = new Color(var3, var4, var5);
         } catch (LogLevelFormatException var9) {
            var10001 = false;
            continue;
         }

         if (var13 != null) {
            try {
               var13.setLogLevelColorMap(var13, var12);
            } catch (LogLevelFormatException var8) {
               var10001 = false;
            }
         }
      }

   }

   protected void processLogLevels(Document var1) {
      NodeList var7 = var1.getElementsByTagName("level");
      Map var3 = this._monitor.getLogLevelMenuItems();

      for(int var2 = 0; var2 < var7.getLength(); ++var2) {
         NamedNodeMap var4 = var7.item(var2).getAttributes();
         String var5 = this.getValue(var4, "name");

         try {
            ((JCheckBoxMenuItem)var3.get(LogLevel.valueOf(var5))).setSelected(this.getValue(var4, "selected").equalsIgnoreCase("true"));
         } catch (LogLevelFormatException var6) {
         }
      }

   }

   protected void processLogTableColumns(Document var1) {
      NodeList var4 = var1.getElementsByTagName("column");
      Map var9 = this._monitor.getLogTableColumnMenuItems();
      ArrayList var3 = new ArrayList();

      for(int var2 = 0; var2 < var4.getLength(); ++var2) {
         Node var5 = var4.item(var2);
         if (var5 == null) {
            return;
         }

         NamedNodeMap var10 = var5.getAttributes();
         String var6 = this.getValue(var10, "name");

         try {
            LogTableColumn var7 = LogTableColumn.valueOf(var6);
            JCheckBoxMenuItem var11 = (JCheckBoxMenuItem)var9.get(var7);
            var11.setSelected(this.getValue(var10, "selected").equalsIgnoreCase("true"));
            if (var11.isSelected()) {
               var3.add(var7);
            }
         } catch (LogTableColumnFormatException var8) {
         }

         if (var3.isEmpty()) {
            this._table.setDetailedView();
         } else {
            this._table.setView(var3);
         }
      }

   }

   protected void processRecordFilter(Document var1) {
      Node var2 = var1.getElementsByTagName("searchtext").item(0);
      if (var2 != null) {
         String var3 = this.getValue(var2.getAttributes(), "name");
         if (var3 != null && !var3.equals("")) {
            this._monitor.setNDCLogRecordFilter(var3);
         }

      }
   }

   public void reset() {
      this.deleteConfigurationFile();
      this.collapseTree();
      this.selectAllNodes();
   }

   public void save() {
      CategoryNode var2 = this._monitor.getCategoryExplorerTree().getExplorerModel().getRootCategoryNode();
      StringBuffer var1 = new StringBuffer(2048);
      this.openXMLDocument(var1);
      this.openConfigurationXML(var1);
      this.processLogRecordFilter(this._monitor.getNDCTextFilter(), var1);
      this.processLogLevels(this._monitor.getLogLevelMenuItems(), var1);
      this.processLogLevelColors(this._monitor.getLogLevelMenuItems(), LogLevel.getLogLevelColorMap(), var1);
      this.processLogTableColumns(LogTableColumn.getLogTableColumns(), var1);
      this.processConfigurationNode(var2, var1);
      this.closeConfigurationXML(var1);
      this.store(var1.toString());
   }

   protected void selectAllNodes() {
      Enumeration var1 = this._monitor.getCategoryExplorerTree().getExplorerModel().getRootCategoryNode().breadthFirstEnumeration();

      while(var1.hasMoreElements()) {
         ((CategoryNode)var1.nextElement()).setSelected(true);
      }

   }

   protected void store(String var1) {
      try {
         FileWriter var2 = new FileWriter(this.getFilename());
         PrintWriter var3 = new PrintWriter(var2);
         var3.print(var1);
         var3.close();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }
}
