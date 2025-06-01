package com.android.common.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import java.util.Collection;

@Deprecated
public class DataUsageStatUpdater {
   private static final String TAG = DataUsageStatUpdater.class.getSimpleName();

   public DataUsageStatUpdater(Context var1) {
   }

   public boolean updateWithAddress(Collection var1) {
      return false;
   }

   public boolean updateWithPhoneNumber(Collection var1) {
      return false;
   }

   public boolean updateWithRfc822Address(Collection var1) {
      return false;
   }

   public static final class DataUsageFeedback {
      static final Uri FEEDBACK_URI;
      static final String USAGE_TYPE = "type";
      public static final String USAGE_TYPE_CALL = "call";
      public static final String USAGE_TYPE_LONG_TEXT = "long_text";
      public static final String USAGE_TYPE_SHORT_TEXT = "short_text";

      static {
         FEEDBACK_URI = Uri.withAppendedPath(Data.CONTENT_URI, "usagefeedback");
      }
   }
}
