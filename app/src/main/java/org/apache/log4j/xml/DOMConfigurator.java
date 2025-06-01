package org.apache.log4j.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RendererSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DOMConfigurator implements Configurator {
   static final String ADDITIVITY_ATTR = "additivity";
   static final String APPENDER_REF_TAG = "appender-ref";
   static final String APPENDER_TAG = "appender";
   static final String CATEGORY = "category";
   static final String CATEGORY_FACTORY_TAG = "categoryFactory";
   static final String CLASS_ATTR = "class";
   static final String CONFIGURATION_TAG = "log4j:configuration";
   static final String CONFIG_DEBUG_ATTR = "configDebug";
   static final String EMPTY_STR = "";
   static final String ERROR_HANDLER_TAG = "errorHandler";
   static final String FILTER_TAG = "filter";
   static final String INTERNAL_DEBUG_ATTR = "debug";
   static final String LAYOUT_TAG = "layout";
   static final String LEVEL_TAG = "level";
   static final String LOGGER = "logger";
   static final String LOGGER_REF = "logger-ref";
   static final String NAME_ATTR = "name";
   static final String OLD_CONFIGURATION_TAG = "configuration";
   static final Class[] ONE_STRING_PARAM;
   static final String PARAM_TAG = "param";
   static final String PRIORITY_TAG = "priority";
   static final String REF_ATTR = "ref";
   static final String RENDERED_CLASS_ATTR = "renderedClass";
   static final String RENDERER_TAG = "renderer";
   static final String RENDERING_CLASS_ATTR = "renderingClass";
   static final String ROOT_REF = "root-ref";
   static final String ROOT_TAG = "root";
   static final String THRESHOLD_ATTR = "threshold";
   static final String VALUE_ATTR = "value";
   static Class class$java$lang$String;
   static Class class$org$apache$log4j$spi$ErrorHandler;
   static Class class$org$apache$log4j$spi$Filter;
   static Class class$org$apache$log4j$spi$LoggerFactory;
   static final String dbfKey = "javax.xml.parsers.DocumentBuilderFactory";
   Hashtable appenderBag = new Hashtable();
   Properties props;
   LoggerRepository repository;

   static {
      Class var1 = class$java$lang$String;
      Class var0 = var1;
      if (var1 == null) {
         var0 = class$("java.lang.String");
         class$java$lang$String = var0;
      }

      ONE_STRING_PARAM = new Class[]{var0};
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

   public static void configure(String var0) throws FactoryConfigurationError {
      (new DOMConfigurator()).doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configure(URL var0) throws FactoryConfigurationError {
      (new DOMConfigurator()).doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configure(Element var0) {
      (new DOMConfigurator()).doConfigure(var0, LogManager.getLoggerRepository());
   }

   public static void configureAndWatch(String var0) {
      configureAndWatch(var0, 60000L);
   }

   public static void configureAndWatch(String var0, long var1) {
      XMLWatchdog var3 = new XMLWatchdog(var0);
      var3.setDelay(var1);
      var3.start();
   }

   private final void doConfigure(ParseAction var1, LoggerRepository var2) throws FactoryConfigurationError {
      this.repository = var2;

      DocumentBuilderFactory var7;
      try {
         StringBuffer var6 = new StringBuffer();
         LogLog.debug(var6.append("System property is :").append(OptionConverter.getSystemProperty("javax.xml.parsers.DocumentBuilderFactory", (String)null)).toString());
         var7 = DocumentBuilderFactory.newInstance();
         LogLog.debug("Standard DocumentBuilderFactory search succeded.");
         StringBuffer var3 = new StringBuffer();
         LogLog.debug(var3.append("DocumentBuilderFactory is: ").append(var7.getClass().getName()).toString());
      } catch (FactoryConfigurationError var5) {
         LogLog.debug("Could not instantiate a DocumentBuilderFactory.", var5.getException());
         throw var5;
      }

      try {
         var7.setValidating(true);
         DocumentBuilder var8 = var7.newDocumentBuilder();
         SAXErrorHandler var9 = new SAXErrorHandler();
         var8.setErrorHandler(var9);
         Log4jEntityResolver var10 = new Log4jEntityResolver();
         var8.setEntityResolver(var10);
         this.parse(var1.parse(var8).getDocumentElement());
      } catch (Exception var4) {
         LogLog.error("Could not parse " + var1.toString() + ".", var4);
      }

   }

   public void doConfigure(InputStream var1, LoggerRepository var2) throws FactoryConfigurationError {
      this.doConfigure((ParseAction)(new DOMConfigurator$3(this, var1)), var2);
   }

   public void doConfigure(Reader var1, LoggerRepository var2) throws FactoryConfigurationError {
      this.doConfigure((ParseAction)(new DOMConfigurator$4(this, var1)), var2);
   }

   public void doConfigure(String var1, LoggerRepository var2) {
      this.doConfigure((ParseAction)(new DOMConfigurator$1(this, var1)), var2);
   }

   public void doConfigure(URL var1, LoggerRepository var2) {
      this.doConfigure((ParseAction)(new DOMConfigurator$2(this, var1)), var2);
   }

   public void doConfigure(Element var1, LoggerRepository var2) {
      this.repository = var2;
      this.parse(var1);
   }

   protected void doConfigure(InputSource var1, LoggerRepository var2) throws FactoryConfigurationError {
      if (var1.getSystemId() == null) {
         var1.setSystemId("dummy://log4j.dtd");
      }

      this.doConfigure((ParseAction)(new DOMConfigurator$5(this, var1)), var2);
   }

   protected Appender findAppenderByName(Document var1, String var2) {
      Appender var4 = (Appender)this.appenderBag.get(var2);
      if (var4 != null) {
         return var4;
      } else {
         NodeList var5 = var1.getElementsByTagName("appender");
         int var3 = 0;

         Element var6;
         while(true) {
            if (var3 >= var5.getLength()) {
               var6 = null;
               break;
            }

            Node var8 = var5.item(var3);
            if (var2.equals(var8.getAttributes().getNamedItem("name").getNodeValue())) {
               var6 = (Element)var8;
               break;
            }

            ++var3;
         }

         if (var6 == null) {
            LogLog.error("No appender named [" + var2 + "] could be found.");
            return null;
         } else {
            Appender var7 = this.parseAppender(var6);
            this.appenderBag.put(var2, var7);
            return var7;
         }
      }
   }

   protected Appender findAppenderByReference(Element var1) {
      String var2 = this.subst(var1.getAttribute("ref"));
      return this.findAppenderByName(var1.getOwnerDocument(), var2);
   }

   protected void parse(Element var1) {
      String var5 = var1.getTagName();
      if (!var5.equals("log4j:configuration")) {
         if (!var5.equals("configuration")) {
            LogLog.error("DOM element is - not a <log4j:configuration> element.");
            return;
         }

         LogLog.warn("The <configuration> element has been deprecated.");
         LogLog.warn("Use the <log4j:configuration> element instead.");
      }

      var5 = this.subst(var1.getAttribute("debug"));
      LogLog.debug("debug attribute= \"" + var5 + "\".");
      if (!var5.equals("") && !var5.equals("null")) {
         LogLog.setInternalDebugging(OptionConverter.toBoolean(var5, true));
      } else {
         LogLog.debug("Ignoring debug attribute.");
      }

      var5 = this.subst(var1.getAttribute("configDebug"));
      if (!var5.equals("") && !var5.equals("null")) {
         LogLog.warn("The \"configDebug\" attribute is deprecated.");
         LogLog.warn("Use the \"debug\" attribute instead.");
         LogLog.setInternalDebugging(OptionConverter.toBoolean(var5, true));
      }

      var5 = this.subst(var1.getAttribute("threshold"));
      LogLog.debug("Threshold =\"" + var5 + "\".");
      if (!"".equals(var5) && !"null".equals(var5)) {
         this.repository.setThreshold(var5);
      }

      NodeList var7 = var1.getChildNodes();
      int var4 = var7.getLength();
      byte var3 = 0;

      int var2;
      Node var8;
      Element var9;
      for(var2 = 0; var2 < var4; ++var2) {
         var8 = var7.item(var2);
         if (var8.getNodeType() == 1) {
            var9 = (Element)var8;
            if (var9.getTagName().equals("categoryFactory")) {
               this.parseCategoryFactory(var9);
            }
         }
      }

      for(var2 = var3; var2 < var4; ++var2) {
         var8 = var7.item(var2);
         if (var8.getNodeType() == 1) {
            var9 = (Element)var8;
            String var6 = var9.getTagName();
            if (!var6.equals("category") && !var6.equals("logger")) {
               if (var6.equals("root")) {
                  this.parseRoot(var9);
               } else if (var6.equals("renderer")) {
                  this.parseRenderer(var9);
               }
            } else {
               this.parseCategory(var9);
            }
         }
      }

   }

   protected Appender parseAppender(Element var1) {
      String var5 = this.subst(var1.getAttribute("class"));
      LogLog.debug("Class name: [" + var5 + ']');

      Exception var10000;
      label93: {
         int var3;
         Appender var6;
         boolean var10001;
         NodeList var20;
         PropertySetter var22;
         try {
            var6 = (Appender)Loader.loadClass(var5).newInstance();
            var22 = new PropertySetter(var6);
            var6.setName(this.subst(var1.getAttribute("name")));
            var20 = var1.getChildNodes();
            var3 = var20.getLength();
         } catch (Exception var19) {
            var10000 = var19;
            var10001 = false;
            break label93;
         }

         int var2 = 0;

         while(true) {
            label94: {
               if (var2 >= var3) {
                  try {
                     var22.activate();
                     return var6;
                  } catch (Exception var11) {
                     var10000 = var11;
                     var10001 = false;
                     break;
                  }
               }

               Element var8;
               try {
                  Node var7 = var20.item(var2);
                  if (var7.getNodeType() != 1) {
                     break label94;
                  }

                  var8 = (Element)var7;
                  if (var8.getTagName().equals("param")) {
                     this.setParameter(var8, var22);
                     break label94;
                  }
               } catch (Exception var16) {
                  var10000 = var16;
                  var10001 = false;
                  break;
               }

               try {
                  if (var8.getTagName().equals("layout")) {
                     var6.setLayout(this.parseLayout(var8));
                     break label94;
                  }
               } catch (Exception var18) {
                  var10000 = var18;
                  var10001 = false;
                  break;
               }

               try {
                  if (var8.getTagName().equals("filter")) {
                     this.parseFilters(var8, var6);
                     break label94;
                  }
               } catch (Exception var15) {
                  var10000 = var15;
                  var10001 = false;
                  break;
               }

               try {
                  if (var8.getTagName().equals("errorHandler")) {
                     this.parseErrorHandler(var8, var6);
                     break label94;
                  }
               } catch (Exception var17) {
                  var10000 = var17;
                  var10001 = false;
                  break;
               }

               boolean var4;
               String var23;
               try {
                  if (!var8.getTagName().equals("appender-ref")) {
                     break label94;
                  }

                  var23 = this.subst(var8.getAttribute("ref"));
                  var4 = var6 instanceof AppenderAttachable;
               } catch (Exception var14) {
                  var10000 = var14;
                  var10001 = false;
                  break;
               }

               if (var4) {
                  try {
                     AppenderAttachable var9 = (AppenderAttachable)var6;
                     StringBuffer var10 = new StringBuffer();
                     LogLog.debug(var10.append("Attaching appender named [").append(var23).append("] to appender named [").append(var6.getName()).append("].").toString());
                     var9.addAppender(this.findAppenderByReference(var8));
                  } catch (Exception var13) {
                     var10000 = var13;
                     var10001 = false;
                     break;
                  }
               } else {
                  try {
                     StringBuffer var24 = new StringBuffer();
                     LogLog.error(var24.append("Requesting attachment of appender named [").append(var23).append("] to appender named [").append(var6.getName()).append("] which does not implement org.apache.log4j.spi.AppenderAttachable.").toString());
                  } catch (Exception var12) {
                     var10000 = var12;
                     var10001 = false;
                     break;
                  }
               }
            }

            ++var2;
         }
      }

      Exception var21 = var10000;
      LogLog.error("Could not create an Appender. Reported error follows.", var21);
      return null;
   }

   protected void parseCategory(Element var1) {
      String var4 = this.subst(var1.getAttribute("name"));
      String var3 = this.subst(var1.getAttribute("class"));
      Logger var7;
      if ("".equals(var3)) {
         LogLog.debug("Retreiving an instance of org.apache.log4j.Logger.");
         var7 = this.repository.getLogger(var4);
      } else {
         LogLog.debug("Desired logger sub-class: [" + var3 + ']');

         try {
            var7 = (Logger)Loader.loadClass(var3).getMethod("getLogger", ONE_STRING_PARAM).invoke((Object)null, var4);
         } catch (Exception var6) {
            LogLog.error("Could not retrieve category [" + var4 + "]. Reported error follows.", var6);
            return;
         }
      }

      synchronized(var7) {
         boolean var2 = OptionConverter.toBoolean(this.subst(var1.getAttribute("additivity")), true);
         StringBuffer var8 = new StringBuffer();
         LogLog.debug(var8.append("Setting [").append(var7.getName()).append("] additivity to [").append(var2).append("].").toString());
         var7.setAdditivity(var2);
         this.parseChildrenOfLoggerElement(var1, var7, false);
      }
   }

   protected void parseCategoryFactory(Element var1) {
      String var6 = this.subst(var1.getAttribute("class"));
      if ("".equals(var6)) {
         LogLog.error("Category Factory tag class attribute not found.");
         LogLog.debug("No Category Factory configured.");
      } else {
         LogLog.debug("Desired category factory: [" + var6 + ']');
         Class var5 = class$org$apache$log4j$spi$LoggerFactory;
         Class var4 = var5;
         if (var5 == null) {
            var4 = class$("org.apache.log4j.spi.LoggerFactory");
            class$org$apache$log4j$spi$LoggerFactory = var4;
         }

         PropertySetter var8 = new PropertySetter(OptionConverter.instantiateByClassName(var6, var4, (Object)null));
         NodeList var7 = var1.getChildNodes();
         int var3 = var7.getLength();

         for(int var2 = 0; var2 < var3; ++var2) {
            Node var9 = var7.item(var2);
            if (var9.getNodeType() == 1) {
               Element var10 = (Element)var9;
               if (var10.getTagName().equals("param")) {
                  this.setParameter(var10, var8);
               }
            }
         }
      }

   }

   protected void parseChildrenOfLoggerElement(Element var1, Logger var2, boolean var3) {
      PropertySetter var6 = new PropertySetter(var2);
      var2.removeAllAppenders();
      NodeList var9 = var1.getChildNodes();
      int var5 = var9.getLength();

      for(int var4 = 0; var4 < var5; ++var4) {
         Node var7 = var9.item(var4);
         if (var7.getNodeType() == 1) {
            Element var8 = (Element)var7;
            String var10 = var8.getTagName();
            if (var10.equals("appender-ref")) {
               Appender var11 = this.findAppenderByReference(var8);
               String var12 = this.subst(var8.getAttribute("ref"));
               if (var11 != null) {
                  LogLog.debug("Adding appender named [" + var12 + "] to category [" + var2.getName() + "].");
               } else {
                  LogLog.debug("Appender named [" + var12 + "] not found.");
               }

               var2.addAppender(var11);
            } else if (var10.equals("level")) {
               this.parseLevel(var8, var2, var3);
            } else if (var10.equals("priority")) {
               this.parseLevel(var8, var2, var3);
            } else if (var10.equals("param")) {
               this.setParameter(var8, var6);
            }
         }
      }

      var6.activate();
   }

   protected void parseErrorHandler(Element var1, Appender var2) {
      String var7 = this.subst(var1.getAttribute("class"));
      Class var6 = class$org$apache$log4j$spi$ErrorHandler;
      Class var5 = var6;
      if (var6 == null) {
         var5 = class$("org.apache.log4j.spi.ErrorHandler");
         class$org$apache$log4j$spi$ErrorHandler = var5;
      }

      ErrorHandler var11 = (ErrorHandler)OptionConverter.instantiateByClassName(var7, var5, (Object)null);
      if (var11 != null) {
         var11.setAppender(var2);
         PropertySetter var10 = new PropertySetter(var11);
         NodeList var9 = var1.getChildNodes();
         int var4 = var9.getLength();

         for(int var3 = 0; var3 < var4; ++var3) {
            Node var12 = var9.item(var3);
            if (var12.getNodeType() == 1) {
               Element var8 = (Element)var12;
               var7 = var8.getTagName();
               if (var7.equals("param")) {
                  this.setParameter(var8, var10);
               } else if (var7.equals("appender-ref")) {
                  var11.setBackupAppender(this.findAppenderByReference(var8));
               } else if (var7.equals("logger-ref")) {
                  var7 = var8.getAttribute("ref");
                  var11.setLogger(this.repository.getLogger(var7));
               } else if (var7.equals("root-ref")) {
                  var11.setLogger(this.repository.getRootLogger());
               }
            }
         }

         var10.activate();
         var2.setErrorHandler(var11);
      }

   }

   protected void parseFilters(Element var1, Appender var2) {
      String var7 = this.subst(var1.getAttribute("class"));
      Class var6 = class$org$apache$log4j$spi$Filter;
      Class var5 = var6;
      if (var6 == null) {
         var5 = class$("org.apache.log4j.spi.Filter");
         class$org$apache$log4j$spi$Filter = var5;
      }

      Filter var9 = (Filter)OptionConverter.instantiateByClassName(var7, var5, (Object)null);
      if (var9 != null) {
         PropertySetter var10 = new PropertySetter(var9);
         NodeList var8 = var1.getChildNodes();
         int var4 = var8.getLength();

         for(int var3 = 0; var3 < var4; ++var3) {
            Node var11 = var8.item(var3);
            if (var11.getNodeType() == 1) {
               Element var12 = (Element)var11;
               if (var12.getTagName().equals("param")) {
                  this.setParameter(var12, var10);
               }
            }
         }

         var10.activate();
         LogLog.debug("Adding filter of type [" + var9.getClass() + "] to appender named [" + var2.getName() + "].");
         var2.addFilter(var9);
      }

   }

   protected Layout parseLayout(Element var1) {
      String var4 = this.subst(var1.getAttribute("class"));
      LogLog.debug("Parsing layout of class: \"" + var4 + "\"");

      Exception var10000;
      label42: {
         int var3;
         PropertySetter var5;
         NodeList var10;
         Layout var12;
         boolean var10001;
         try {
            var12 = (Layout)Loader.loadClass(var4).newInstance();
            var5 = new PropertySetter(var12);
            var10 = var1.getChildNodes();
            var3 = var10.getLength();
         } catch (Exception var9) {
            var10000 = var9;
            var10001 = false;
            break label42;
         }

         int var2 = 0;

         while(true) {
            if (var2 >= var3) {
               try {
                  var5.activate();
                  return var12;
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break;
               }
            }

            try {
               Node var6 = var10.item(var2);
               if (var6.getNodeType() == 1) {
                  Element var13 = (Element)var6;
                  if (var13.getTagName().equals("param")) {
                     this.setParameter(var13, var5);
                  }
               }
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break;
            }

            ++var2;
         }
      }

      Exception var11 = var10000;
      LogLog.error("Could not create the Layout. Reported error follows.", var11);
      return null;
   }

   protected void parseLevel(Element var1, Logger var2, boolean var3) {
      String var4 = var2.getName();
      if (var3) {
         var4 = "root";
      }

      String var5 = this.subst(var1.getAttribute("value"));
      LogLog.debug("Level value for " + var4 + " is  [" + var5 + "].");
      if (!"inherited".equalsIgnoreCase(var5) && !"null".equalsIgnoreCase(var5)) {
         String var7 = this.subst(var1.getAttribute("class"));
         if ("".equals(var7)) {
            var2.setLevel(OptionConverter.toLevel(var5, Level.DEBUG));
         } else {
            LogLog.debug("Desired Level sub-class: [" + var7 + ']');

            try {
               var2.setLevel((Level)Loader.loadClass(var7).getMethod("toLevel", ONE_STRING_PARAM).invoke((Object)null, var5));
            } catch (Exception var6) {
               LogLog.error("Could not create level [" + var5 + "]. Reported error follows.", var6);
               return;
            }
         }
      } else if (var3) {
         LogLog.error("Root level cannot be inherited. Ignoring directive.");
      } else {
         var2.setLevel((Level)null);
      }

      LogLog.debug(var4 + " level set to " + var2.getLevel());
   }

   protected void parseRenderer(Element var1) {
      String var2 = this.subst(var1.getAttribute("renderingClass"));
      String var3 = this.subst(var1.getAttribute("renderedClass"));
      LoggerRepository var4 = this.repository;
      if (var4 instanceof RendererSupport) {
         RendererMap.addRenderer((RendererSupport)var4, var3, var2);
      }

   }

   protected void parseRoot(Element var1) {
      Logger var2 = this.repository.getRootLogger();
      synchronized(var2) {
         this.parseChildrenOfLoggerElement(var1, var2, true);
      }
   }

   protected void setParameter(Element var1, PropertySetter var2) {
      var2.setProperty(this.subst(var1.getAttribute("name")), this.subst(OptionConverter.convertSpecialChars(var1.getAttribute("value"))));
   }

   protected String subst(String var1) {
      try {
         String var2 = OptionConverter.substVars(var1, this.props);
         return var2;
      } catch (IllegalArgumentException var3) {
         LogLog.warn("Could not perform variable substitution.", var3);
         return var1;
      }
   }

   private interface ParseAction {
      Document parse(DocumentBuilder var1) throws SAXException, IOException;
   }
}
