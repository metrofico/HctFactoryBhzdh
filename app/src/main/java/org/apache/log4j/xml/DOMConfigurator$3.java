package org.apache.log4j.xml;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class DOMConfigurator$3 implements DOMConfigurator.ParseAction {
   private final DOMConfigurator this$0;
   private final InputStream val$inputStream;

   DOMConfigurator$3(DOMConfigurator var1, InputStream var2) {
      this.this$0 = var1;
      this.val$inputStream = var2;
   }

   public Document parse(DocumentBuilder var1) throws SAXException, IOException {
      InputSource var2 = new InputSource(this.val$inputStream);
      var2.setSystemId("dummy://log4j.dtd");
      return var1.parse(var2);
   }

   public String toString() {
      return "input stream [" + this.val$inputStream.toString() + "]";
   }
}
