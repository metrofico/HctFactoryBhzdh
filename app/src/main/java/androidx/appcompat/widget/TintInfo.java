package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

public class TintInfo {
   public boolean mHasTintList;
   public boolean mHasTintMode;
   public ColorStateList mTintList;
   public PorterDuff.Mode mTintMode;

   void clear() {
      this.mTintList = null;
      this.mHasTintList = false;
      this.mTintMode = null;
      this.mHasTintMode = false;
   }
}
