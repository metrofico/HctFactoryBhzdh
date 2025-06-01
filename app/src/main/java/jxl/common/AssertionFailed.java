package jxl.common;

public class AssertionFailed extends RuntimeException {
   public AssertionFailed() {
      this.printStackTrace();
   }

   public AssertionFailed(String var1) {
      super(var1);
   }
}
