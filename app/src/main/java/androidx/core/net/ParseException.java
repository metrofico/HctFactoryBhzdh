package androidx.core.net;

public class ParseException extends RuntimeException {
   public final String response;

   ParseException(String var1) {
      super(var1);
      this.response = var1;
   }
}
