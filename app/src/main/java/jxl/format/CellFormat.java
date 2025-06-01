package jxl.format;

public interface CellFormat {
   Alignment getAlignment();

   Colour getBackgroundColour();

   BorderLineStyle getBorder(Border var1);

   Colour getBorderColour(Border var1);

   BorderLineStyle getBorderLine(Border var1);

   Font getFont();

   Format getFormat();

   int getIndentation();

   Orientation getOrientation();

   Pattern getPattern();

   VerticalAlignment getVerticalAlignment();

   boolean getWrap();

   boolean hasBorders();

   boolean isLocked();

   boolean isShrinkToFit();
}
