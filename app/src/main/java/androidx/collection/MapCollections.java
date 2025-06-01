package androidx.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections {
   EntrySet mEntrySet;
   KeySet mKeySet;
   ValuesCollection mValues;

   public static boolean containsAllHelper(Map var0, Collection var1) {
      Iterator var2 = var1.iterator();

      do {
         if (!var2.hasNext()) {
            return true;
         }
      } while(var0.containsKey(var2.next()));

      return false;
   }

   public static boolean equalsSetHelper(Set var0, Object var1) {
      boolean var2 = true;
      if (var0 == var1) {
         return true;
      } else if (var1 instanceof Set) {
         Set var5 = (Set)var1;

         label27: {
            boolean var3;
            try {
               if (var0.size() != var5.size()) {
                  break label27;
               }

               var3 = var0.containsAll(var5);
            } catch (ClassCastException | NullPointerException var4) {
               return false;
            }

            if (var3) {
               return var2;
            }
         }

         var2 = false;
         return var2;
      } else {
         return false;
      }
   }

   public static boolean removeAllHelper(Map var0, Collection var1) {
      int var2 = var0.size();
      Iterator var4 = var1.iterator();

      while(var4.hasNext()) {
         var0.remove(var4.next());
      }

      boolean var3;
      if (var2 != var0.size()) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static boolean retainAllHelper(Map var0, Collection var1) {
      int var2 = var0.size();
      Iterator var4 = var0.keySet().iterator();

      while(var4.hasNext()) {
         if (!var1.contains(var4.next())) {
            var4.remove();
         }
      }

      boolean var3;
      if (var2 != var0.size()) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   protected abstract void colClear();

   protected abstract Object colGetEntry(int var1, int var2);

   protected abstract Map colGetMap();

   protected abstract int colGetSize();

   protected abstract int colIndexOfKey(Object var1);

   protected abstract int colIndexOfValue(Object var1);

   protected abstract void colPut(Object var1, Object var2);

   protected abstract void colRemoveAt(int var1);

   protected abstract Object colSetValue(int var1, Object var2);

   public Set getEntrySet() {
      if (this.mEntrySet == null) {
         this.mEntrySet = new EntrySet(this);
      }

      return this.mEntrySet;
   }

   public Set getKeySet() {
      if (this.mKeySet == null) {
         this.mKeySet = new KeySet(this);
      }

      return this.mKeySet;
   }

   public Collection getValues() {
      if (this.mValues == null) {
         this.mValues = new ValuesCollection(this);
      }

      return this.mValues;
   }

   public Object[] toArrayHelper(int var1) {
      int var3 = this.colGetSize();
      Object[] var4 = new Object[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         var4[var2] = this.colGetEntry(var2, var1);
      }

      return var4;
   }

   public Object[] toArrayHelper(Object[] var1, int var2) {
      int var4 = this.colGetSize();
      Object[] var5 = var1;
      if (var1.length < var4) {
         var5 = (Object[])Array.newInstance(var1.getClass().getComponentType(), var4);
      }

      for(int var3 = 0; var3 < var4; ++var3) {
         var5[var3] = this.colGetEntry(var3, var2);
      }

      if (var5.length > var4) {
         var5[var4] = null;
      }

      return var5;
   }

   final class ArrayIterator implements Iterator {
      boolean mCanRemove;
      int mIndex;
      final int mOffset;
      int mSize;
      final MapCollections this$0;

      ArrayIterator(MapCollections var1, int var2) {
         this.this$0 = var1;
         this.mCanRemove = false;
         this.mOffset = var2;
         this.mSize = var1.colGetSize();
      }

      public boolean hasNext() {
         boolean var1;
         if (this.mIndex < this.mSize) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public Object next() {
         if (this.hasNext()) {
            Object var1 = this.this$0.colGetEntry(this.mIndex, this.mOffset);
            ++this.mIndex;
            this.mCanRemove = true;
            return var1;
         } else {
            throw new NoSuchElementException();
         }
      }

      public void remove() {
         if (this.mCanRemove) {
            int var1 = this.mIndex - 1;
            this.mIndex = var1;
            --this.mSize;
            this.mCanRemove = false;
            this.this$0.colRemoveAt(var1);
         } else {
            throw new IllegalStateException();
         }
      }
   }

   final class EntrySet implements Set {
      final MapCollections this$0;

      EntrySet(MapCollections var1) {
         this.this$0 = var1;
      }

      public boolean add(Map.Entry var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection var1) {
         int var2 = this.this$0.colGetSize();
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            Map.Entry var5 = (Map.Entry)var4.next();
            this.this$0.colPut(var5.getKey(), var5.getValue());
         }

         boolean var3;
         if (var2 != this.this$0.colGetSize()) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      public void clear() {
         this.this$0.colClear();
      }

      public boolean contains(Object var1) {
         if (!(var1 instanceof Map.Entry)) {
            return false;
         } else {
            Map.Entry var3 = (Map.Entry)var1;
            int var2 = this.this$0.colIndexOfKey(var3.getKey());
            return var2 < 0 ? false : ContainerHelpers.equal(this.this$0.colGetEntry(var2, 1), var3.getValue());
         }
      }

      public boolean containsAll(Collection var1) {
         Iterator var2 = var1.iterator();

         do {
            if (!var2.hasNext()) {
               return true;
            }
         } while(this.contains(var2.next()));

         return false;
      }

      public boolean equals(Object var1) {
         return MapCollections.equalsSetHelper(this, var1);
      }

      public int hashCode() {
         int var2 = this.this$0.colGetSize() - 1;

         int var1;
         for(var1 = 0; var2 >= 0; --var2) {
            Object var6 = this.this$0.colGetEntry(var2, 0);
            Object var5 = this.this$0.colGetEntry(var2, 1);
            int var3;
            if (var6 == null) {
               var3 = 0;
            } else {
               var3 = var6.hashCode();
            }

            int var4;
            if (var5 == null) {
               var4 = 0;
            } else {
               var4 = var5.hashCode();
            }

            var1 += var3 ^ var4;
         }

         return var1;
      }

      public boolean isEmpty() {
         boolean var1;
         if (this.this$0.colGetSize() == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public Iterator iterator() {
         return this.this$0.new MapIterator(this.this$0);
      }

      public boolean remove(Object var1) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection var1) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection var1) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return this.this$0.colGetSize();
      }

      public Object[] toArray() {
         throw new UnsupportedOperationException();
      }

      public Object[] toArray(Object[] var1) {
         throw new UnsupportedOperationException();
      }
   }

   final class KeySet implements Set {
      final MapCollections this$0;

      KeySet(MapCollections var1) {
         this.this$0 = var1;
      }

      public boolean add(Object var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection var1) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         this.this$0.colClear();
      }

      public boolean contains(Object var1) {
         boolean var2;
         if (this.this$0.colIndexOfKey(var1) >= 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public boolean containsAll(Collection var1) {
         return MapCollections.containsAllHelper(this.this$0.colGetMap(), var1);
      }

      public boolean equals(Object var1) {
         return MapCollections.equalsSetHelper(this, var1);
      }

      public int hashCode() {
         int var1 = this.this$0.colGetSize() - 1;

         int var2;
         for(var2 = 0; var1 >= 0; --var1) {
            Object var4 = this.this$0.colGetEntry(var1, 0);
            int var3;
            if (var4 == null) {
               var3 = 0;
            } else {
               var3 = var4.hashCode();
            }

            var2 += var3;
         }

         return var2;
      }

      public boolean isEmpty() {
         boolean var1;
         if (this.this$0.colGetSize() == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public Iterator iterator() {
         return this.this$0.new ArrayIterator(this.this$0, 0);
      }

      public boolean remove(Object var1) {
         int var2 = this.this$0.colIndexOfKey(var1);
         if (var2 >= 0) {
            this.this$0.colRemoveAt(var2);
            return true;
         } else {
            return false;
         }
      }

      public boolean removeAll(Collection var1) {
         return MapCollections.removeAllHelper(this.this$0.colGetMap(), var1);
      }

      public boolean retainAll(Collection var1) {
         return MapCollections.retainAllHelper(this.this$0.colGetMap(), var1);
      }

      public int size() {
         return this.this$0.colGetSize();
      }

      public Object[] toArray() {
         return this.this$0.toArrayHelper(0);
      }

      public Object[] toArray(Object[] var1) {
         return this.this$0.toArrayHelper(var1, 0);
      }
   }

   final class MapIterator implements Iterator, Map.Entry {
      int mEnd;
      boolean mEntryValid;
      int mIndex;
      final MapCollections this$0;

      MapIterator(MapCollections var1) {
         this.this$0 = var1;
         this.mEntryValid = false;
         this.mEnd = var1.colGetSize() - 1;
         this.mIndex = -1;
      }

      public boolean equals(Object var1) {
         if (this.mEntryValid) {
            boolean var2 = var1 instanceof Map.Entry;
            boolean var3 = false;
            if (!var2) {
               return false;
            } else {
               Map.Entry var4 = (Map.Entry)var1;
               var2 = var3;
               if (ContainerHelpers.equal(var4.getKey(), this.this$0.colGetEntry(this.mIndex, 0))) {
                  var2 = var3;
                  if (ContainerHelpers.equal(var4.getValue(), this.this$0.colGetEntry(this.mIndex, 1))) {
                     var2 = true;
                  }
               }

               return var2;
            }
         } else {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         }
      }

      public Object getKey() {
         if (this.mEntryValid) {
            return this.this$0.colGetEntry(this.mIndex, 0);
         } else {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         }
      }

      public Object getValue() {
         if (this.mEntryValid) {
            return this.this$0.colGetEntry(this.mIndex, 1);
         } else {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         }
      }

      public boolean hasNext() {
         boolean var1;
         if (this.mIndex < this.mEnd) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public int hashCode() {
         if (this.mEntryValid) {
            MapCollections var3 = this.this$0;
            int var1 = this.mIndex;
            int var2 = 0;
            Object var4 = var3.colGetEntry(var1, 0);
            Object var5 = this.this$0.colGetEntry(this.mIndex, 1);
            if (var4 == null) {
               var1 = 0;
            } else {
               var1 = var4.hashCode();
            }

            if (var5 != null) {
               var2 = var5.hashCode();
            }

            return var1 ^ var2;
         } else {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         }
      }

      public Map.Entry next() {
         if (this.hasNext()) {
            ++this.mIndex;
            this.mEntryValid = true;
            return this;
         } else {
            throw new NoSuchElementException();
         }
      }

      public void remove() {
         if (this.mEntryValid) {
            this.this$0.colRemoveAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
         } else {
            throw new IllegalStateException();
         }
      }

      public Object setValue(Object var1) {
         if (this.mEntryValid) {
            return this.this$0.colSetValue(this.mIndex, var1);
         } else {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         }
      }

      public String toString() {
         return this.getKey() + "=" + this.getValue();
      }
   }

   final class ValuesCollection implements Collection {
      final MapCollections this$0;

      ValuesCollection(MapCollections var1) {
         this.this$0 = var1;
      }

      public boolean add(Object var1) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection var1) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         this.this$0.colClear();
      }

      public boolean contains(Object var1) {
         boolean var2;
         if (this.this$0.colIndexOfValue(var1) >= 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public boolean containsAll(Collection var1) {
         Iterator var2 = var1.iterator();

         do {
            if (!var2.hasNext()) {
               return true;
            }
         } while(this.contains(var2.next()));

         return false;
      }

      public boolean isEmpty() {
         boolean var1;
         if (this.this$0.colGetSize() == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public Iterator iterator() {
         return this.this$0.new ArrayIterator(this.this$0, 1);
      }

      public boolean remove(Object var1) {
         int var2 = this.this$0.colIndexOfValue(var1);
         if (var2 >= 0) {
            this.this$0.colRemoveAt(var2);
            return true;
         } else {
            return false;
         }
      }

      public boolean removeAll(Collection var1) {
         int var3 = this.this$0.colGetSize();
         int var2 = 0;

         int var4;
         boolean var6;
         for(var6 = false; var2 < var3; var3 = var4) {
            var4 = var3;
            int var5 = var2;
            if (var1.contains(this.this$0.colGetEntry(var2, 1))) {
               this.this$0.colRemoveAt(var2);
               var5 = var2 - 1;
               var4 = var3 - 1;
               var6 = true;
            }

            var2 = var5 + 1;
         }

         return var6;
      }

      public boolean retainAll(Collection var1) {
         int var3 = this.this$0.colGetSize();
         int var2 = 0;

         int var5;
         boolean var6;
         for(var6 = false; var2 < var3; var3 = var5) {
            var5 = var3;
            int var4 = var2;
            if (!var1.contains(this.this$0.colGetEntry(var2, 1))) {
               this.this$0.colRemoveAt(var2);
               var4 = var2 - 1;
               var5 = var3 - 1;
               var6 = true;
            }

            var2 = var4 + 1;
         }

         return var6;
      }

      public int size() {
         return this.this$0.colGetSize();
      }

      public Object[] toArray() {
         return this.this$0.toArrayHelper(1);
      }

      public Object[] toArray(Object[] var1) {
         return this.this$0.toArrayHelper(var1, 1);
      }
   }
}
