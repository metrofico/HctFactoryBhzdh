package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;

public interface ActivityResultCaller {
   ActivityResultLauncher registerForActivityResult(ActivityResultContract var1, ActivityResultCallback var2);

   ActivityResultLauncher registerForActivityResult(ActivityResultContract var1, ActivityResultRegistry var2, ActivityResultCallback var3);
}
