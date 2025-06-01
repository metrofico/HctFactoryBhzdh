package androidx.startup;

import android.content.Context;
import java.util.List;

public interface Initializer {
   Object create(Context var1);

   List dependencies();
}
