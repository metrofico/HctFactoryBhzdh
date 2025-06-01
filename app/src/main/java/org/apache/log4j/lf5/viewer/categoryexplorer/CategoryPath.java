package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class CategoryPath {
   protected LinkedList _categoryElements = new LinkedList();

   public CategoryPath() {
   }

   public CategoryPath(String var1) {
      String var2 = var1;
      if (var1 == null) {
         var2 = "Debug";
      }

      var2.replace('/', '.');
      StringTokenizer var3 = new StringTokenizer(var2.replace('\\', '.'), ".");

      while(var3.hasMoreTokens()) {
         this.addCategoryElement(new CategoryElement(var3.nextToken()));
      }

   }

   public void addCategoryElement(CategoryElement var1) {
      this._categoryElements.addLast(var1);
   }

   public CategoryElement categoryElementAt(int var1) {
      return (CategoryElement)this._categoryElements.get(var1);
   }

   public boolean isEmpty() {
      boolean var1;
      if (this._categoryElements.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void removeAllCategoryElements() {
      this._categoryElements.clear();
   }

   public int size() {
      return this._categoryElements.size();
   }

   public String toString() {
      StringBuffer var2 = new StringBuffer(100);
      var2.append("\n");
      var2.append("===========================\n");
      var2.append("CategoryPath:                   \n");
      var2.append("---------------------------\n");
      var2.append("\nCategoryPath:\n\t");
      if (this.size() > 0) {
         for(int var1 = 0; var1 < this.size(); ++var1) {
            var2.append(this.categoryElementAt(var1).toString());
            var2.append("\n\t");
         }
      } else {
         var2.append("<<NONE>>");
      }

      var2.append("\n");
      var2.append("===========================\n");
      return var2.toString();
   }
}
