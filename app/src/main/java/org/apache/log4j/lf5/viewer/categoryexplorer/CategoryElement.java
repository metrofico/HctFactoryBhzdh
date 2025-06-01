package org.apache.log4j.lf5.viewer.categoryexplorer;

public class CategoryElement {
   protected String _categoryTitle;

   public CategoryElement() {
   }

   public CategoryElement(String var1) {
      this._categoryTitle = var1;
   }

   public String getTitle() {
      return this._categoryTitle;
   }

   public void setTitle(String var1) {
      this._categoryTitle = var1;
   }
}
