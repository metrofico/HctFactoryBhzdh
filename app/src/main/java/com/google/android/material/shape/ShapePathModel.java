package com.google.android.material.shape;

public class ShapePathModel {
   private static final CornerTreatment DEFAULT_CORNER_TREATMENT = new CornerTreatment();
   private static final EdgeTreatment DEFAULT_EDGE_TREATMENT = new EdgeTreatment();
   private EdgeTreatment bottomEdge;
   private CornerTreatment bottomLeftCorner;
   private CornerTreatment bottomRightCorner;
   private EdgeTreatment leftEdge;
   private EdgeTreatment rightEdge;
   private EdgeTreatment topEdge;
   private CornerTreatment topLeftCorner;
   private CornerTreatment topRightCorner;

   public ShapePathModel() {
      CornerTreatment var1 = DEFAULT_CORNER_TREATMENT;
      this.topLeftCorner = var1;
      this.topRightCorner = var1;
      this.bottomRightCorner = var1;
      this.bottomLeftCorner = var1;
      EdgeTreatment var2 = DEFAULT_EDGE_TREATMENT;
      this.topEdge = var2;
      this.rightEdge = var2;
      this.bottomEdge = var2;
      this.leftEdge = var2;
   }

   public EdgeTreatment getBottomEdge() {
      return this.bottomEdge;
   }

   public CornerTreatment getBottomLeftCorner() {
      return this.bottomLeftCorner;
   }

   public CornerTreatment getBottomRightCorner() {
      return this.bottomRightCorner;
   }

   public EdgeTreatment getLeftEdge() {
      return this.leftEdge;
   }

   public EdgeTreatment getRightEdge() {
      return this.rightEdge;
   }

   public EdgeTreatment getTopEdge() {
      return this.topEdge;
   }

   public CornerTreatment getTopLeftCorner() {
      return this.topLeftCorner;
   }

   public CornerTreatment getTopRightCorner() {
      return this.topRightCorner;
   }

   public void setAllCorners(CornerTreatment var1) {
      this.topLeftCorner = var1;
      this.topRightCorner = var1;
      this.bottomRightCorner = var1;
      this.bottomLeftCorner = var1;
   }

   public void setAllEdges(EdgeTreatment var1) {
      this.leftEdge = var1;
      this.topEdge = var1;
      this.rightEdge = var1;
      this.bottomEdge = var1;
   }

   public void setBottomEdge(EdgeTreatment var1) {
      this.bottomEdge = var1;
   }

   public void setBottomLeftCorner(CornerTreatment var1) {
      this.bottomLeftCorner = var1;
   }

   public void setBottomRightCorner(CornerTreatment var1) {
      this.bottomRightCorner = var1;
   }

   public void setCornerTreatments(CornerTreatment var1, CornerTreatment var2, CornerTreatment var3, CornerTreatment var4) {
      this.topLeftCorner = var1;
      this.topRightCorner = var2;
      this.bottomRightCorner = var3;
      this.bottomLeftCorner = var4;
   }

   public void setEdgeTreatments(EdgeTreatment var1, EdgeTreatment var2, EdgeTreatment var3, EdgeTreatment var4) {
      this.leftEdge = var1;
      this.topEdge = var2;
      this.rightEdge = var3;
      this.bottomEdge = var4;
   }

   public void setLeftEdge(EdgeTreatment var1) {
      this.leftEdge = var1;
   }

   public void setRightEdge(EdgeTreatment var1) {
      this.rightEdge = var1;
   }

   public void setTopEdge(EdgeTreatment var1) {
      this.topEdge = var1;
   }

   public void setTopLeftCorner(CornerTreatment var1) {
      this.topLeftCorner = var1;
   }

   public void setTopRightCorner(CornerTreatment var1) {
      this.topRightCorner = var1;
   }
}
