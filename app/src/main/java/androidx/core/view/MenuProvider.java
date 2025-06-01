package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public interface MenuProvider {
   void onCreateMenu(Menu var1, MenuInflater var2);

   boolean onMenuItemSelected(MenuItem var1);
}
