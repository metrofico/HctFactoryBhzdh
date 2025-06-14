package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

public class AppCompatDialogFragment extends DialogFragment {
   public AppCompatDialogFragment() {
   }

   public AppCompatDialogFragment(int var1) {
      super(var1);
   }

   public Dialog onCreateDialog(Bundle var1) {
      return new AppCompatDialog(this.getContext(), this.getTheme());
   }

   public void setupDialog(Dialog var1, int var2) {
      if (var1 instanceof AppCompatDialog) {
         AppCompatDialog var3 = (AppCompatDialog)var1;
         if (var2 != 1 && var2 != 2) {
            if (var2 != 3) {
               return;
            }

            var1.getWindow().addFlags(24);
         }

         var3.supportRequestWindowFeature(1);
      } else {
         super.setupDialog(var1, var2);
      }

   }
}
