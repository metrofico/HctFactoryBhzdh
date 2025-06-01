package org.apache.log4j.xml;

import java.io.IOException;
import java.io.Reader;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class DOMConfigurator$4 implements DOMConfigurator.ParseAction {
   private final DOMConfigurator this$0;
   private final Reader val$reader;

   DOMConfigurator$4(DOMConfigurator var1, Reader var2) {
      this.this$0 = var1;
      this.val$reader = var2;
   }

   public Document parse(DocumentBuilder var1) throws SAXException, IOException {
      InputSource var2 = new InputSource(this.val$reader);
      var2.setSystemId("dummy://log4j.dtd");
      return var1.parse(var2);
   }

   public String toString() {
      return "reader [" + this.val$reader.toString() + "]";
   }
}
