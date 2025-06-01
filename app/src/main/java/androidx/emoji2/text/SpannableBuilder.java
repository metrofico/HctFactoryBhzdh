package androidx.emoji2.text;

import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import androidx.core.util.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class SpannableBuilder extends SpannableStringBuilder {
   private final Class mWatcherClass;
   private final List mWatchers = new ArrayList();

   SpannableBuilder(Class var1) {
      Preconditions.checkNotNull(var1, "watcherClass cannot be null");
      this.mWatcherClass = var1;
   }

   SpannableBuilder(Class var1, CharSequence var2) {
      super(var2);
      Preconditions.checkNotNull(var1, "watcherClass cannot be null");
      this.mWatcherClass = var1;
   }

   SpannableBuilder(Class var1, CharSequence var2, int var3, int var4) {
      super(var2, var3, var4);
      Preconditions.checkNotNull(var1, "watcherClass cannot be null");
      this.mWatcherClass = var1;
   }

   private void blockWatchers() {
      for(int var1 = 0; var1 < this.mWatchers.size(); ++var1) {
         ((WatcherWrapper)this.mWatchers.get(var1)).blockCalls();
      }

   }

   public static SpannableBuilder create(Class var0, CharSequence var1) {
      return new SpannableBuilder(var0, var1);
   }

   private void fireWatchers() {
      for(int var1 = 0; var1 < this.mWatchers.size(); ++var1) {
         ((WatcherWrapper)this.mWatchers.get(var1)).onTextChanged(this, 0, this.length(), this.length());
      }

   }

   private WatcherWrapper getWatcherFor(Object var1) {
      for(int var2 = 0; var2 < this.mWatchers.size(); ++var2) {
         WatcherWrapper var3 = (WatcherWrapper)this.mWatchers.get(var2);
         if (var3.mObject == var1) {
            return var3;
         }
      }

      return null;
   }

   private boolean isWatcher(Class var1) {
      boolean var2;
      if (this.mWatcherClass == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private boolean isWatcher(Object var1) {
      boolean var2;
      if (var1 != null && this.isWatcher(var1.getClass())) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private void unblockwatchers() {
      for(int var1 = 0; var1 < this.mWatchers.size(); ++var1) {
         ((WatcherWrapper)this.mWatchers.get(var1)).unblockCalls();
      }

   }

   public SpannableStringBuilder append(char var1) {
      super.append(var1);
      return this;
   }

   public SpannableStringBuilder append(CharSequence var1) {
      super.append(var1);
      return this;
   }

   public SpannableStringBuilder append(CharSequence var1, int var2, int var3) {
      super.append(var1, var2, var3);
      return this;
   }

   public SpannableStringBuilder append(CharSequence var1, Object var2, int var3) {
      super.append(var1, var2, var3);
      return this;
   }

   public void beginBatchEdit() {
      this.blockWatchers();
   }

   public SpannableStringBuilder delete(int var1, int var2) {
      super.delete(var1, var2);
      return this;
   }

   public void endBatchEdit() {
      this.unblockwatchers();
      this.fireWatchers();
   }

   public int getSpanEnd(Object var1) {
      Object var2 = var1;
      if (this.isWatcher(var1)) {
         WatcherWrapper var3 = this.getWatcherFor(var1);
         var2 = var1;
         if (var3 != null) {
            var2 = var3;
         }
      }

      return super.getSpanEnd(var2);
   }

   public int getSpanFlags(Object var1) {
      Object var2 = var1;
      if (this.isWatcher(var1)) {
         WatcherWrapper var3 = this.getWatcherFor(var1);
         var2 = var1;
         if (var3 != null) {
            var2 = var3;
         }
      }

      return super.getSpanFlags(var2);
   }

   public int getSpanStart(Object var1) {
      Object var2 = var1;
      if (this.isWatcher(var1)) {
         WatcherWrapper var3 = this.getWatcherFor(var1);
         var2 = var1;
         if (var3 != null) {
            var2 = var3;
         }
      }

      return super.getSpanStart(var2);
   }

   public Object[] getSpans(int var1, int var2, Class var3) {
      if (!this.isWatcher(var3)) {
         return super.getSpans(var1, var2, var3);
      } else {
         WatcherWrapper[] var4 = (WatcherWrapper[])super.getSpans(var1, var2, WatcherWrapper.class);
         Object[] var5 = (Object[])Array.newInstance(var3, var4.length);

         for(var1 = 0; var1 < var4.length; ++var1) {
            var5[var1] = var4[var1].mObject;
         }

         return var5;
      }
   }

   public SpannableStringBuilder insert(int var1, CharSequence var2) {
      super.insert(var1, var2);
      return this;
   }

   public SpannableStringBuilder insert(int var1, CharSequence var2, int var3, int var4) {
      super.insert(var1, var2, var3, var4);
      return this;
   }

   public int nextSpanTransition(int var1, int var2, Class var3) {
      Class var4;
      if (var3 != null) {
         var4 = var3;
         if (!this.isWatcher(var3)) {
            return super.nextSpanTransition(var1, var2, var4);
         }
      }

      var4 = WatcherWrapper.class;
      return super.nextSpanTransition(var1, var2, var4);
   }

   public void removeSpan(Object var1) {
      WatcherWrapper var2;
      if (this.isWatcher(var1)) {
         WatcherWrapper var3 = this.getWatcherFor(var1);
         var2 = var3;
         if (var3 != null) {
            var1 = var3;
            var2 = var3;
         }
      } else {
         var2 = null;
      }

      super.removeSpan(var1);
      if (var2 != null) {
         this.mWatchers.remove(var2);
      }

   }

   public SpannableStringBuilder replace(int var1, int var2, CharSequence var3) {
      this.blockWatchers();
      super.replace(var1, var2, var3);
      this.unblockwatchers();
      return this;
   }

   public SpannableStringBuilder replace(int var1, int var2, CharSequence var3, int var4, int var5) {
      this.blockWatchers();
      super.replace(var1, var2, var3, var4, var5);
      this.unblockwatchers();
      return this;
   }

   public void setSpan(Object var1, int var2, int var3, int var4) {
      Object var5 = var1;
      if (this.isWatcher(var1)) {
         var5 = new WatcherWrapper(var1);
         this.mWatchers.add(var5);
      }

      super.setSpan(var5, var2, var3, var4);
   }

   public CharSequence subSequence(int var1, int var2) {
      return new SpannableBuilder(this.mWatcherClass, this, var1, var2);
   }

   private static class WatcherWrapper implements TextWatcher, SpanWatcher {
      private final AtomicInteger mBlockCalls = new AtomicInteger(0);
      final Object mObject;

      WatcherWrapper(Object var1) {
         this.mObject = var1;
      }

      private boolean isEmojiSpan(Object var1) {
         return var1 instanceof EmojiSpan;
      }

      public void afterTextChanged(Editable var1) {
         ((TextWatcher)this.mObject).afterTextChanged(var1);
      }

      public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
         ((TextWatcher)this.mObject).beforeTextChanged(var1, var2, var3, var4);
      }

      final void blockCalls() {
         this.mBlockCalls.incrementAndGet();
      }

      public void onSpanAdded(Spannable var1, Object var2, int var3, int var4) {
         if (this.mBlockCalls.get() <= 0 || !this.isEmojiSpan(var2)) {
            ((SpanWatcher)this.mObject).onSpanAdded(var1, var2, var3, var4);
         }
      }

      public void onSpanChanged(Spannable var1, Object var2, int var3, int var4, int var5, int var6) {
         if (this.mBlockCalls.get() <= 0 || !this.isEmojiSpan(var2)) {
            ((SpanWatcher)this.mObject).onSpanChanged(var1, var2, var3, var4, var5, var6);
         }
      }

      public void onSpanRemoved(Spannable var1, Object var2, int var3, int var4) {
         if (this.mBlockCalls.get() <= 0 || !this.isEmojiSpan(var2)) {
            ((SpanWatcher)this.mObject).onSpanRemoved(var1, var2, var3, var4);
         }
      }

      public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
         ((TextWatcher)this.mObject).onTextChanged(var1, var2, var3, var4);
      }

      final void unblockCalls() {
         this.mBlockCalls.decrementAndGet();
      }
   }
}
