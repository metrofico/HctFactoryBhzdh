package androidx.arch.core.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class SafeIterableMap implements Iterable {
   private Entry mEnd;
   private WeakHashMap mIterators = new WeakHashMap();
   private int mSize = 0;
   Entry mStart;

   public Iterator descendingIterator() {
      DescendingIterator var1 = new DescendingIterator(this.mEnd, this.mStart);
      this.mIterators.put(var1, false);
      return var1;
   }

   public Map.Entry eldest() {
      return this.mStart;
   }

   public boolean equals(Object var1) {
      boolean var2 = true;
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof SafeIterableMap)) {
         return false;
      } else {
         SafeIterableMap var3 = (SafeIterableMap)var1;
         if (this.size() != var3.size()) {
            return false;
         } else {
            Iterator var6 = this.iterator();
            Iterator var5 = var3.iterator();

            while(true) {
               if (var6.hasNext() && var5.hasNext()) {
                  Map.Entry var4 = (Map.Entry)var6.next();
                  Object var7 = var5.next();
                  if ((var4 != null || var7 == null) && (var4 == null || var4.equals(var7))) {
                     continue;
                  }

                  return false;
               }

               if (var6.hasNext() || var5.hasNext()) {
                  var2 = false;
               }

               return var2;
            }
         }
      }
   }

   protected Entry get(Object var1) {
      Entry var2;
      for(var2 = this.mStart; var2 != null && !var2.mKey.equals(var1); var2 = var2.mNext) {
      }

      return var2;
   }

   public int hashCode() {
      Iterator var2 = this.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 += ((Map.Entry)var2.next()).hashCode()) {
      }

      return var1;
   }

   public Iterator iterator() {
      AscendingIterator var1 = new AscendingIterator(this.mStart, this.mEnd);
      this.mIterators.put(var1, false);
      return var1;
   }

   public IteratorWithAdditions iteratorWithAdditions() {
      IteratorWithAdditions var1 = new IteratorWithAdditions(this);
      this.mIterators.put(var1, false);
      return var1;
   }

   public Map.Entry newest() {
      return this.mEnd;
   }

   protected Entry put(Object var1, Object var2) {
      Entry var3 = new Entry(var1, var2);
      ++this.mSize;
      Entry var4 = this.mEnd;
      if (var4 == null) {
         this.mStart = var3;
         this.mEnd = var3;
         return var3;
      } else {
         var4.mNext = var3;
         var3.mPrevious = this.mEnd;
         this.mEnd = var3;
         return var3;
      }
   }

   public Object putIfAbsent(Object var1, Object var2) {
      Entry var3 = this.get(var1);
      if (var3 != null) {
         return var3.mValue;
      } else {
         this.put(var1, var2);
         return null;
      }
   }

   public Object remove(Object var1) {
      Entry var2 = this.get(var1);
      if (var2 == null) {
         return null;
      } else {
         --this.mSize;
         if (!this.mIterators.isEmpty()) {
            Iterator var3 = this.mIterators.keySet().iterator();

            while(var3.hasNext()) {
               ((SupportRemove)var3.next()).supportRemove(var2);
            }
         }

         if (var2.mPrevious != null) {
            var2.mPrevious.mNext = var2.mNext;
         } else {
            this.mStart = var2.mNext;
         }

         if (var2.mNext != null) {
            var2.mNext.mPrevious = var2.mPrevious;
         } else {
            this.mEnd = var2.mPrevious;
         }

         var2.mNext = null;
         var2.mPrevious = null;
         return var2.mValue;
      }
   }

   public int size() {
      return this.mSize;
   }

   public String toString() {
      StringBuilder var2 = new StringBuilder();
      var2.append("[");
      Iterator var1 = this.iterator();

      while(var1.hasNext()) {
         var2.append(((Map.Entry)var1.next()).toString());
         if (var1.hasNext()) {
            var2.append(", ");
         }
      }

      var2.append("]");
      return var2.toString();
   }

   static class AscendingIterator extends ListIterator {
      AscendingIterator(Entry var1, Entry var2) {
         super(var1, var2);
      }

      Entry backward(Entry var1) {
         return var1.mPrevious;
      }

      Entry forward(Entry var1) {
         return var1.mNext;
      }
   }

   private static class DescendingIterator extends ListIterator {
      DescendingIterator(Entry var1, Entry var2) {
         super(var1, var2);
      }

      Entry backward(Entry var1) {
         return var1.mNext;
      }

      Entry forward(Entry var1) {
         return var1.mPrevious;
      }
   }

   static class Entry implements Map.Entry {
      final Object mKey;
      Entry mNext;
      Entry mPrevious;
      final Object mValue;

      Entry(Object var1, Object var2) {
         this.mKey = var1;
         this.mValue = var2;
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof Entry)) {
            return false;
         } else {
            Entry var3 = (Entry)var1;
            if (!this.mKey.equals(var3.mKey) || !this.mValue.equals(var3.mValue)) {
               var2 = false;
            }

            return var2;
         }
      }

      public Object getKey() {
         return this.mKey;
      }

      public Object getValue() {
         return this.mValue;
      }

      public int hashCode() {
         return this.mKey.hashCode() ^ this.mValue.hashCode();
      }

      public Object setValue(Object var1) {
         throw new UnsupportedOperationException("An entry modification is not supported");
      }

      public String toString() {
         return this.mKey + "=" + this.mValue;
      }
   }

   private class IteratorWithAdditions implements Iterator, SupportRemove {
      private boolean mBeforeStart;
      private Entry mCurrent;
      final SafeIterableMap this$0;

      IteratorWithAdditions(SafeIterableMap var1) {
         this.this$0 = var1;
         this.mBeforeStart = true;
      }

      public boolean hasNext() {
         boolean var3 = this.mBeforeStart;
         boolean var2 = true;
         boolean var1 = true;
         if (var3) {
            if (this.this$0.mStart == null) {
               var1 = false;
            }

            return var1;
         } else {
            Entry var4 = this.mCurrent;
            if (var4 != null && var4.mNext != null) {
               var1 = var2;
            } else {
               var1 = false;
            }

            return var1;
         }
      }

      public Map.Entry next() {
         if (this.mBeforeStart) {
            this.mBeforeStart = false;
            this.mCurrent = this.this$0.mStart;
         } else {
            Entry var1 = this.mCurrent;
            if (var1 != null) {
               var1 = var1.mNext;
            } else {
               var1 = null;
            }

            this.mCurrent = var1;
         }

         return this.mCurrent;
      }

      public void supportRemove(Entry var1) {
         Entry var3 = this.mCurrent;
         if (var1 == var3) {
            var1 = var3.mPrevious;
            this.mCurrent = var1;
            boolean var2;
            if (var1 == null) {
               var2 = true;
            } else {
               var2 = false;
            }

            this.mBeforeStart = var2;
         }

      }
   }

   private abstract static class ListIterator implements Iterator, SupportRemove {
      Entry mExpectedEnd;
      Entry mNext;

      ListIterator(Entry var1, Entry var2) {
         this.mExpectedEnd = var2;
         this.mNext = var1;
      }

      private Entry nextNode() {
         Entry var2 = this.mNext;
         Entry var1 = this.mExpectedEnd;
         return var2 != var1 && var1 != null ? this.forward(var2) : null;
      }

      abstract Entry backward(Entry var1);

      abstract Entry forward(Entry var1);

      public boolean hasNext() {
         boolean var1;
         if (this.mNext != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public Map.Entry next() {
         Entry var1 = this.mNext;
         this.mNext = this.nextNode();
         return var1;
      }

      public void supportRemove(Entry var1) {
         if (this.mExpectedEnd == var1 && var1 == this.mNext) {
            this.mNext = null;
            this.mExpectedEnd = null;
         }

         Entry var2 = this.mExpectedEnd;
         if (var2 == var1) {
            this.mExpectedEnd = this.backward(var2);
         }

         if (this.mNext == var1) {
            this.mNext = this.nextNode();
         }

      }
   }

   interface SupportRemove {
      void supportRemove(Entry var1);
   }
}
