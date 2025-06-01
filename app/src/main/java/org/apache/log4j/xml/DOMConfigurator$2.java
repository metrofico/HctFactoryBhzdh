package org.apache.log4j.xml;

import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

class DOMConfigurator$2 implements DOMConfigurator.ParseAction {
   private final DOMConfigurator this$0;
   private final URL val$url;

   DOMConfigurator$2(DOMConfigurator var1, URL var2) {
      this.this$0 = var1;
      this.val$url = var2;
   }

   public Document parse(DocumentBuilder var1) throws SAXException, IOException {
      return var1.parse(this.val$url.toString());
   }

   public String toString() {
      return "url [" + this.val$url.toString() + "]";
   }
}
