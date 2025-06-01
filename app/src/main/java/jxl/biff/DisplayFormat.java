package jxl.biff;

public interface DisplayFormat {
   int getFormatIndex();

   void initialize(int var1);

   boolean isBuiltIn();

   boolean isInitialized();
}
