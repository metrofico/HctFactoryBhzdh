package androidx.appcompat.app;

import android.view.View;
import android.widget.AdapterView;

class NavItemSelectedListener implements AdapterView.OnItemSelectedListener {
   private final ActionBar.OnNavigationListener mListener;

   public NavItemSelectedListener(ActionBar.OnNavigationListener var1) {
      this.mListener = var1;
   }

   public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
      ActionBar.OnNavigationListener var6 = this.mListener;
      if (var6 != null) {
         var6.onNavigationItemSelected(var3, var4);
      }

   }

   public void onNothingSelected(AdapterView var1) {
   }
}
