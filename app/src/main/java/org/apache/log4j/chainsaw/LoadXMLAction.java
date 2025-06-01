package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class LoadXMLAction extends AbstractAction {
   private static final Logger LOG;
   static Class class$org$apache$log4j$chainsaw$LoadXMLAction;
   private final JFileChooser mChooser;
   private final XMLFileHandler mHandler;
   private final JFrame mParent;
   private final XMLReader mParser;

   static {
      Class var1 = class$org$apache$log4j$chainsaw$LoadXMLAction;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("org.apache.log4j.chainsaw.LoadXMLAction");
         class$org$apache$log4j$chainsaw$LoadXMLAction = var0;
      }

      LOG = Logger.getLogger(var0);
   }

   LoadXMLAction(JFrame var1, MyTableModel var2) throws SAXException, ParserConfigurationException {
      JFileChooser var3 = new JFileChooser();
      this.mChooser = var3;
      var3.setMultiSelectionEnabled(false);
      var3.setFileSelectionMode(0);
      this.mParent = var1;
      XMLFileHandler var4 = new XMLFileHandler(var2);
      this.mHandler = var4;
      XMLReader var5 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
      this.mParser = var5;
      var5.setContentHandler(var4);
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   private int loadFile(String var1) throws SAXException, IOException {
      XMLReader var3 = this.mParser;
      synchronized(var3) {
         StringBuffer var4 = new StringBuffer();
         var4.append("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
         var4.append("<!DOCTYPE log4j:eventSet ");
         var4.append("[<!ENTITY data SYSTEM \"file:///");
         var4.append(var1);
         var4.append("\">]>\n");
         var4.append("<log4j:eventSet xmlns:log4j=\"Claira\">\n");
         var4.append("&data;\n");
         var4.append("</log4j:eventSet>\n");
         StringReader var7 = new StringReader(var4.toString());
         InputSource var5 = new InputSource(var7);
         this.mParser.parse(var5);
         int var2 = this.mHandler.getNumEvents();
         return var2;
      }
   }

   public void actionPerformed(ActionEvent var1) {
      Logger var3 = LOG;
      var3.info("load file called");
      if (this.mChooser.showOpenDialog(this.mParent) == 0) {
         var3.info("Need to load a file");
         File var5 = this.mChooser.getSelectedFile();
         var3.info("loading the contents of " + var5.getAbsolutePath());

         try {
            int var2 = this.loadFile(var5.getAbsolutePath());
            JFrame var7 = this.mParent;
            StringBuffer var6 = new StringBuffer();
            JOptionPane.showMessageDialog(var7, var6.append("Loaded ").append(var2).append(" events.").toString(), "CHAINSAW", 1);
         } catch (Exception var4) {
            LOG.warn("caught an exception loading the file", var4);
            JOptionPane.showMessageDialog(this.mParent, "Error parsing file - " + var4.getMessage(), "CHAINSAW", 0);
         }
      }

   }
}
