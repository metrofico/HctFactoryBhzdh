package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;
import androidx.appcompat.view.ContextThemeWrapper;

public interface ThemedSpinnerAdapter extends SpinnerAdapter {
   Resources.Theme getDropDownViewTheme();

   void setDropDownViewTheme(Resources.Theme var1);

   public static final class Helper {
      private final Context mContext;
      private LayoutInflater mDropDownInflater;
      private final LayoutInflater mInflater;

      public Helper(Context var1) {
         this.mContext = var1;
         this.mInflater = LayoutInflater.from(var1);
      }

      public LayoutInflater getDropDownViewInflater() {
         LayoutInflater var1 = this.mDropDownInflater;
         if (var1 == null) {
            var1 = this.mInflater;
         }

         return var1;
      }

      public Resources.Theme getDropDownViewTheme() {
         LayoutInflater var1 = this.mDropDownInflater;
         Resources.Theme var2;
         if (var1 == null) {
            var2 = null;
         } else {
            var2 = var1.getContext().getTheme();
         }

         return var2;
      }

      public void setDropDownViewTheme(Resources.Theme var1) {
         if (var1 == null) {
            this.mDropDownInflater = null;
         } else if (var1 == this.mContext.getTheme()) {
            this.mDropDownInflater = this.mInflater;
         } else {
            this.mDropDownInflater = LayoutInflater.from(new ContextThemeWrapper(this.mContext, var1));
         }

      }
   }
}
