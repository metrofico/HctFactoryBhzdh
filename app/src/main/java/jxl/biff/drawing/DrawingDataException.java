package jxl.biff.drawing;

public class DrawingDataException extends RuntimeException {
   private static String message;

   DrawingDataException() {
      super(message);
   }
}
