package jxl;

import java.io.File;
import jxl.common.LengthUnit;

public interface Image {
   double getColumn();

   double getHeight();

   double getHeight(LengthUnit var1);

   double getHorizontalResolution(LengthUnit var1);

   byte[] getImageData();

   File getImageFile();

   int getImageHeight();

   int getImageWidth();

   double getRow();

   double getVerticalResolution(LengthUnit var1);

   double getWidth();

   double getWidth(LengthUnit var1);
}
