package jxl.write.biff;

import java.io.IOException;
import java.io.OutputStream;

interface ExcelDataOutput {
   void close() throws IOException;

   int getPosition() throws IOException;

   void setData(byte[] var1, int var2) throws IOException;

   void write(byte[] var1) throws IOException;

   void writeData(OutputStream var1) throws IOException;
}
