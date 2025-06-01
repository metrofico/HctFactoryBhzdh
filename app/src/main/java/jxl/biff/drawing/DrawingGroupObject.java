package jxl.biff.drawing;

import java.io.IOException;
import jxl.write.biff.File;

public interface DrawingGroupObject {
   int getBlipId();

   DrawingGroup getDrawingGroup();

   double getHeight();

   byte[] getImageBytes() throws IOException;

   byte[] getImageData();

   String getImageFilePath();

   MsoDrawingRecord getMsoDrawingRecord();

   int getObjectId();

   Origin getOrigin();

   int getReferenceCount();

   int getShapeId();

   EscherContainer getSpContainer();

   ShapeType getType();

   double getWidth();

   double getX();

   double getY();

   boolean isFirst();

   boolean isFormObject();

   void setDrawingGroup(DrawingGroup var1);

   void setHeight(double var1);

   void setObjectId(int var1, int var2, int var3);

   void setReferenceCount(int var1);

   void setWidth(double var1);

   void setX(double var1);

   void setY(double var1);

   void writeAdditionalRecords(File var1) throws IOException;

   void writeTailRecords(File var1) throws IOException;
}
