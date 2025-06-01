package org.apache.log4j.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

class DOMConfigurator$1 implements DOMConfigurator.ParseAction {
   private final DOMConfigurator this$0;
   private final String val$filename;

   DOMConfigurator$1(DOMConfigurator var1, String var2) {
      this.this$0 = var1;
      this.val$filename = var2;
   }

   public Document parse(DocumentBuilder var1) throws SAXException, IOException {
      return var1.parse(new File(this.val$filename));
   }

   public String toString() {
      return "file [" + this.val$filename + "]";
   }
}
