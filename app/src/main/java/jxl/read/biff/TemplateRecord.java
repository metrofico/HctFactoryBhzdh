package jxl.read.biff;

import jxl.biff.RecordData;
import jxl.common.Logger;

class TemplateRecord extends RecordData {
   private static Logger logger = Logger.getLogger(TemplateRecord.class);
   private boolean template = true;

   public TemplateRecord(Record var1) {
      super(var1);
   }

   public boolean getTemplate() {
      return this.template;
   }
}
