package androidx.constraintlayout.solver.widgets;

import java.util.HashSet;
import java.util.Iterator;

public class ResolutionNode {
   public static final int REMOVED = 2;
   public static final int RESOLVED = 1;
   public static final int UNRESOLVED = 0;
   HashSet dependents = new HashSet(2);
   int state = 0;

   public void addDependent(ResolutionNode var1) {
      this.dependents.add(var1);
   }

   public void didResolve() {
      this.state = 1;
      Iterator var1 = this.dependents.iterator();

      while(var1.hasNext()) {
         ((ResolutionNode)var1.next()).resolve();
      }

   }

   public void invalidate() {
      this.state = 0;
      Iterator var1 = this.dependents.iterator();

      while(var1.hasNext()) {
         ((ResolutionNode)var1.next()).invalidate();
      }

   }

   public void invalidateAnchors() {
      if (this instanceof ResolutionAnchor) {
         this.state = 0;
      }

      Iterator var1 = this.dependents.iterator();

      while(var1.hasNext()) {
         ((ResolutionNode)var1.next()).invalidateAnchors();
      }

   }

   public boolean isResolved() {
      int var1 = this.state;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void remove(ResolutionDimension var1) {
   }

   public void reset() {
      this.state = 0;
      this.dependents.clear();
   }

   public void resolve() {
   }
}
