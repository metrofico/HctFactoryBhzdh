package org.apache.log4j.xml;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class DOMConfigurator$5 implements DOMConfigurator.ParseAction {
   private final DOMConfigurator this$0;
   private final InputSource val$inputSource;

   DOMConfigurator$5(DOMConfigurator var1, InputSource var2) {
      this.this$0 = var1;
      this.val$inputSource = var2;
   }

   public Document parse(DocumentBuilder var1) throws SAXException, IOException {
      return var1.parse(this.val$inputSource);
   }

   public String toString() {
      return "input source [" + this.val$inputSource.toString() + "]";
   }
}
