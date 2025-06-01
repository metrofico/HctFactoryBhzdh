package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ArrayMap extends SimpleArrayMap implements Map {
   MapCollections mCollections;

   public ArrayMap() {
   }

   public ArrayMap(int var1) {
      super(var1);
   }

   public ArrayMap(SimpleArrayMap var1) {
      super(var1);
   }

   private MapCollections getCollection() {
      if (this.mCollections == null) {
         this.mCollections = new MapCollections(this) {
            final ArrayMap this$0;

            {
               this.this$0 = var1;
            }

            protected void colClear() {
               this.this$0.clear();
            }

            protected Object colGetEntry(int var1, int var2) {
               return this.this$0.mArray[(var1 << 1) + var2];
            }

            protected Map colGetMap() {
               return this.this$0;
            }

            protected int colGetSize() {
               return this.this$0.mSize;
            }

            protected int colIndexOfKey(Object var1) {
               return this.this$0.indexOfKey(var1);
            }

            protected int colIndexOfValue(Object var1) {
               return this.this$0.indexOfValue(var1);
            }

            protected void colPut(Object var1, Object var2) {
               this.this$0.put(var1, var2);
            }

            protected void colRemoveAt(int var1) {
               this.this$0.removeAt(var1);
            }

            protected Object colSetValue(int var1, Object var2) {
               return this.this$0.setValueAt(var1, var2);
            }
         };
      }

      return this.mCollections;
   }

   public boolean containsAll(Collection var1) {
      return MapCollections.containsAllHelper(this, var1);
   }

   public Set entrySet() {
      return this.getCollection().getEntrySet();
   }

   public Set keySet() {
      return this.getCollection().getKeySet();
   }

   public void putAll(Map var1) {
      this.ensureCapacity(this.mSize + var1.size());
      Iterator var2 = var1.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry var3 = (Map.Entry)var2.next();
         this.put(var3.getKey(), var3.getValue());
      }

   }

   public boolean removeAll(Collection var1) {
      return MapCollections.removeAllHelper(this, var1);
   }

   public boolean retainAll(Collection var1) {
      return MapCollections.retainAllHelper(this, var1);
   }

   public Collection values() {
      return this.getCollection().getValues();
   }
}
