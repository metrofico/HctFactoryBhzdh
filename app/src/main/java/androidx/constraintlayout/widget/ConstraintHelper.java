package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.Helper;
import java.util.Arrays;

public abstract class ConstraintHelper extends View {
   protected int mCount;
   protected Helper mHelperWidget;
   protected int[] mIds = new int[32];
   private String mReferenceIds;
   protected boolean mUseViewMeasure = false;
   protected Context myContext;

   public ConstraintHelper(Context var1) {
      super(var1);
      this.myContext = var1;
      this.init((AttributeSet)null);
   }

   public ConstraintHelper(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.myContext = var1;
      this.init(var2);
   }

   public ConstraintHelper(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.myContext = var1;
      this.init(var2);
   }

   private void addID(String var1) {
      if (var1 != null) {
         if (this.myContext != null) {
            var1 = var1.trim();

            int var3;
            try {
               var3 = R.id.class.getField(var1).getInt((Object)null);
            } catch (Exception var5) {
               var3 = 0;
            }

            int var2 = var3;
            if (var3 == 0) {
               var2 = this.myContext.getResources().getIdentifier(var1, "id", this.myContext.getPackageName());
            }

            var3 = var2;
            if (var2 == 0) {
               var3 = var2;
               if (this.isInEditMode()) {
                  var3 = var2;
                  if (this.getParent() instanceof ConstraintLayout) {
                     Object var4 = ((ConstraintLayout)this.getParent()).getDesignInformation(0, var1);
                     var3 = var2;
                     if (var4 != null) {
                        var3 = var2;
                        if (var4 instanceof Integer) {
                           var3 = (Integer)var4;
                        }
                     }
                  }
               }
            }

            if (var3 != 0) {
               this.setTag(var3, (Object)null);
            } else {
               Log.w("ConstraintHelper", "Could not find id of \"" + var1 + "\"");
            }

         }
      }
   }

   private void setIds(String var1) {
      if (var1 != null) {
         int var2 = 0;

         while(true) {
            int var3 = var1.indexOf(44, var2);
            if (var3 == -1) {
               this.addID(var1.substring(var2));
               return;
            }

            this.addID(var1.substring(var2, var3));
            var2 = var3 + 1;
         }
      }
   }

   public int[] getReferencedIds() {
      return Arrays.copyOf(this.mIds, this.mCount);
   }

   protected void init(AttributeSet var1) {
      if (var1 != null) {
         TypedArray var5 = this.getContext().obtainStyledAttributes(var1, R.styleable.ConstraintLayout_Layout);
         int var3 = var5.getIndexCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            int var4 = var5.getIndex(var2);
            if (var4 == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids) {
               String var6 = var5.getString(var4);
               this.mReferenceIds = var6;
               this.setIds(var6);
            }
         }
      }

   }

   public void onDraw(Canvas var1) {
   }

   protected void onMeasure(int var1, int var2) {
      if (this.mUseViewMeasure) {
         super.onMeasure(var1, var2);
      } else {
         this.setMeasuredDimension(0, 0);
      }

   }

   public void setReferencedIds(int[] var1) {
      int var2 = 0;

      for(this.mCount = 0; var2 < var1.length; ++var2) {
         this.setTag(var1[var2], (Object)null);
      }

   }

   public void setTag(int var1, Object var2) {
      int var3 = this.mCount;
      int[] var4 = this.mIds;
      if (var3 + 1 > var4.length) {
         this.mIds = Arrays.copyOf(var4, var4.length * 2);
      }

      var4 = this.mIds;
      var3 = this.mCount;
      var4[var3] = var1;
      this.mCount = var3 + 1;
   }

   public void updatePostLayout(ConstraintLayout var1) {
   }

   public void updatePostMeasure(ConstraintLayout var1) {
   }

   public void updatePreLayout(ConstraintLayout var1) {
      if (this.isInEditMode()) {
         this.setIds(this.mReferenceIds);
      }

      Helper var3 = this.mHelperWidget;
      if (var3 != null) {
         var3.removeAllIds();

         for(int var2 = 0; var2 < this.mCount; ++var2) {
            View var4 = var1.getViewById(this.mIds[var2]);
            if (var4 != null) {
               this.mHelperWidget.add(var1.getViewWidget(var4));
            }
         }

      }
   }

   public void validateParams() {
      if (this.mHelperWidget != null) {
         ViewGroup.LayoutParams var1 = this.getLayoutParams();
         if (var1 instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams)var1).widget = this.mHelperWidget;
         }

      }
   }
}
