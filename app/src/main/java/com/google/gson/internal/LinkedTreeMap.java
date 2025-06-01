package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap extends AbstractMap implements Serializable {
   static final boolean $assertionsDisabled = false;
   private static final Comparator NATURAL_ORDER = new Comparator() {
      public int compare(Comparable var1, Comparable var2) {
         return var1.compareTo(var2);
      }
   };
   Comparator comparator;
   private EntrySet entrySet;
   final Node header;
   private KeySet keySet;
   int modCount;
   Node root;
   int size;

   public LinkedTreeMap() {
      this(NATURAL_ORDER);
   }

   public LinkedTreeMap(Comparator var1) {
      this.size = 0;
      this.modCount = 0;
      this.header = new Node();
      if (var1 == null) {
         var1 = NATURAL_ORDER;
      }

      this.comparator = var1;
   }

   private boolean equal(Object var1, Object var2) {
      boolean var3;
      if (var1 == var2 || var1 != null && var1.equals(var2)) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   private void rebalance(Node var1, boolean var2) {
      while(true) {
         label86: {
            if (var1 != null) {
               Node var8 = var1.left;
               Node var9 = var1.right;
               byte var5 = 0;
               byte var6 = 0;
               int var3;
               if (var8 != null) {
                  var3 = var8.height;
               } else {
                  var3 = 0;
               }

               int var4;
               if (var9 != null) {
                  var4 = var9.height;
               } else {
                  var4 = 0;
               }

               int var7 = var3 - var4;
               Node var10;
               if (var7 == -2) {
                  var8 = var9.left;
                  var10 = var9.right;
                  if (var10 != null) {
                     var3 = var10.height;
                  } else {
                     var3 = 0;
                  }

                  var4 = var6;
                  if (var8 != null) {
                     var4 = var8.height;
                  }

                  var3 = var4 - var3;
                  if (var3 == -1 || var3 == 0 && !var2) {
                     this.rotateLeft(var1);
                  } else {
                     this.rotateRight(var9);
                     this.rotateLeft(var1);
                  }

                  if (!var2) {
                     break label86;
                  }
               } else if (var7 == 2) {
                  var9 = var8.left;
                  var10 = var8.right;
                  if (var10 != null) {
                     var3 = var10.height;
                  } else {
                     var3 = 0;
                  }

                  var4 = var5;
                  if (var9 != null) {
                     var4 = var9.height;
                  }

                  var3 = var4 - var3;
                  if (var3 == 1 || var3 == 0 && !var2) {
                     this.rotateRight(var1);
                  } else {
                     this.rotateLeft(var8);
                     this.rotateRight(var1);
                  }

                  if (!var2) {
                     break label86;
                  }
               } else if (var7 == 0) {
                  var1.height = var3 + 1;
                  if (!var2) {
                     break label86;
                  }
               } else {
                  var1.height = Math.max(var3, var4) + 1;
                  if (var2) {
                     break label86;
                  }
               }
            }

            return;
         }

         var1 = var1.parent;
      }
   }

   private void replaceInParent(Node var1, Node var2) {
      Node var3 = var1.parent;
      var1.parent = null;
      if (var2 != null) {
         var2.parent = var3;
      }

      if (var3 != null) {
         if (var3.left == var1) {
            var3.left = var2;
         } else {
            var3.right = var2;
         }
      } else {
         this.root = var2;
      }

   }

   private void rotateLeft(Node var1) {
      Node var6 = var1.left;
      Node var8 = var1.right;
      Node var5 = var8.left;
      Node var7 = var8.right;
      var1.right = var5;
      if (var5 != null) {
         var5.parent = var1;
      }

      this.replaceInParent(var1, var8);
      var8.left = var1;
      var1.parent = var8;
      byte var4 = 0;
      int var2;
      if (var6 != null) {
         var2 = var6.height;
      } else {
         var2 = 0;
      }

      int var3;
      if (var5 != null) {
         var3 = var5.height;
      } else {
         var3 = 0;
      }

      var1.height = Math.max(var2, var3) + 1;
      var3 = var1.height;
      var2 = var4;
      if (var7 != null) {
         var2 = var7.height;
      }

      var8.height = Math.max(var3, var2) + 1;
   }

   private void rotateRight(Node var1) {
      Node var8 = var1.left;
      Node var7 = var1.right;
      Node var6 = var8.left;
      Node var5 = var8.right;
      var1.left = var5;
      if (var5 != null) {
         var5.parent = var1;
      }

      this.replaceInParent(var1, var8);
      var8.right = var1;
      var1.parent = var8;
      byte var4 = 0;
      int var2;
      if (var7 != null) {
         var2 = var7.height;
      } else {
         var2 = 0;
      }

      int var3;
      if (var5 != null) {
         var3 = var5.height;
      } else {
         var3 = 0;
      }

      var1.height = Math.max(var2, var3) + 1;
      var3 = var1.height;
      var2 = var4;
      if (var6 != null) {
         var2 = var6.height;
      }

      var8.height = Math.max(var3, var2) + 1;
   }

   private Object writeReplace() throws ObjectStreamException {
      return new LinkedHashMap(this);
   }

   public void clear() {
      this.root = null;
      this.size = 0;
      ++this.modCount;
      Node var1 = this.header;
      var1.prev = var1;
      var1.next = var1;
   }

   public boolean containsKey(Object var1) {
      boolean var2;
      if (this.findByObject(var1) != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public Set entrySet() {
      EntrySet var1 = this.entrySet;
      if (var1 == null) {
         var1 = new EntrySet(this);
         this.entrySet = var1;
      }

      return var1;
   }

   Node find(Object var1, boolean var2) {
      Comparator var7 = this.comparator;
      Node var4 = this.root;
      int var3;
      Node var5;
      if (var4 != null) {
         Comparable var6;
         if (var7 == NATURAL_ORDER) {
            var6 = (Comparable)var1;
         } else {
            var6 = null;
         }

         while(true) {
            if (var6 != null) {
               var3 = var6.compareTo(var4.key);
            } else {
               var3 = var7.compare(var1, var4.key);
            }

            if (var3 == 0) {
               return var4;
            }

            if (var3 < 0) {
               var5 = var4.left;
            } else {
               var5 = var4.right;
            }

            if (var5 == null) {
               break;
            }

            var4 = var5;
         }
      } else {
         var3 = 0;
      }

      if (!var2) {
         return null;
      } else {
         var5 = this.header;
         Node var8;
         if (var4 == null) {
            if (var7 == NATURAL_ORDER && !(var1 instanceof Comparable)) {
               throw new ClassCastException(var1.getClass().getName() + " is not Comparable");
            }

            var8 = new Node(var4, var1, var5, var5.prev);
            this.root = var8;
         } else {
            var8 = new Node(var4, var1, var5, var5.prev);
            if (var3 < 0) {
               var4.left = var8;
            } else {
               var4.right = var8;
            }

            this.rebalance(var4, true);
         }

         ++this.size;
         ++this.modCount;
         return var8;
      }
   }

   Node findByEntry(Map.Entry var1) {
      Node var3 = this.findByObject(var1.getKey());
      boolean var2;
      if (var3 != null && this.equal(var3.value, var1.getValue())) {
         var2 = true;
      } else {
         var2 = false;
      }

      Node var4;
      if (var2) {
         var4 = var3;
      } else {
         var4 = null;
      }

      return var4;
   }

   Node findByObject(Object var1) {
      Object var3 = null;
      Node var2 = (Node)var3;
      if (var1 != null) {
         try {
            var2 = this.find(var1, false);
         } catch (ClassCastException var4) {
            var2 = (Node)var3;
         }
      }

      return var2;
   }

   public Object get(Object var1) {
      Node var2 = this.findByObject(var1);
      if (var2 != null) {
         var1 = var2.value;
      } else {
         var1 = null;
      }

      return var1;
   }

   public Set keySet() {
      KeySet var1 = this.keySet;
      if (var1 == null) {
         var1 = new KeySet(this);
         this.keySet = var1;
      }

      return var1;
   }

   public Object put(Object var1, Object var2) {
      if (var1 != null) {
         Node var3 = this.find(var1, true);
         var1 = var3.value;
         var3.value = var2;
         return var1;
      } else {
         throw new NullPointerException("key == null");
      }
   }

   public Object remove(Object var1) {
      Node var2 = this.removeInternalByKey(var1);
      if (var2 != null) {
         var1 = var2.value;
      } else {
         var1 = null;
      }

      return var1;
   }

   void removeInternal(Node var1, boolean var2) {
      if (var2) {
         var1.prev.next = var1.next;
         var1.next.prev = var1.prev;
      }

      Node var6 = var1.left;
      Node var5 = var1.right;
      Node var7 = var1.parent;
      int var4 = 0;
      if (var6 != null && var5 != null) {
         if (var6.height > var5.height) {
            var5 = var6.last();
         } else {
            var5 = var5.first();
         }

         this.removeInternal(var5, false);
         var6 = var1.left;
         int var3;
         if (var6 != null) {
            var3 = var6.height;
            var5.left = var6;
            var6.parent = var5;
            var1.left = null;
         } else {
            var3 = 0;
         }

         var6 = var1.right;
         if (var6 != null) {
            var4 = var6.height;
            var5.right = var6;
            var6.parent = var5;
            var1.right = null;
         }

         var5.height = Math.max(var3, var4) + 1;
         this.replaceInParent(var1, var5);
      } else {
         if (var6 != null) {
            this.replaceInParent(var1, var6);
            var1.left = null;
         } else if (var5 != null) {
            this.replaceInParent(var1, var5);
            var1.right = null;
         } else {
            this.replaceInParent(var1, (Node)null);
         }

         this.rebalance(var7, false);
         --this.size;
         ++this.modCount;
      }
   }

   Node removeInternalByKey(Object var1) {
      Node var2 = this.findByObject(var1);
      if (var2 != null) {
         this.removeInternal(var2, true);
      }

      return var2;
   }

   public int size() {
      return this.size;
   }

   class EntrySet extends AbstractSet {
      final LinkedTreeMap this$0;

      EntrySet(LinkedTreeMap var1) {
         this.this$0 = var1;
      }

      public void clear() {
         this.this$0.clear();
      }

      public boolean contains(Object var1) {
         boolean var2;
         if (var1 instanceof Map.Entry && this.this$0.findByEntry((Map.Entry)var1) != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public Iterator iterator() {
         return new LinkedTreeMapIterator(this) {
            final EntrySet this$1;

            {
               this.this$1 = var1;
            }

            public Map.Entry next() {
               return this.nextNode();
            }
         };
      }

      public boolean remove(Object var1) {
         if (!(var1 instanceof Map.Entry)) {
            return false;
         } else {
            Node var2 = this.this$0.findByEntry((Map.Entry)var1);
            if (var2 == null) {
               return false;
            } else {
               this.this$0.removeInternal(var2, true);
               return true;
            }
         }
      }

      public int size() {
         return this.this$0.size;
      }
   }

   final class KeySet extends AbstractSet {
      final LinkedTreeMap this$0;

      KeySet(LinkedTreeMap var1) {
         this.this$0 = var1;
      }

      public void clear() {
         this.this$0.clear();
      }

      public boolean contains(Object var1) {
         return this.this$0.containsKey(var1);
      }

      public Iterator iterator() {
         return new LinkedTreeMapIterator(this) {
            final KeySet this$1;

            {
               this.this$1 = var1;
            }

            public Object next() {
               return this.nextNode().key;
            }
         };
      }

      public boolean remove(Object var1) {
         boolean var2;
         if (this.this$0.removeInternalByKey(var1) != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public int size() {
         return this.this$0.size;
      }
   }

   private abstract class LinkedTreeMapIterator implements Iterator {
      int expectedModCount;
      Node lastReturned;
      Node next;
      final LinkedTreeMap this$0;

      LinkedTreeMapIterator(LinkedTreeMap var1) {
         this.this$0 = var1;
         this.next = var1.header.next;
         this.lastReturned = null;
         this.expectedModCount = var1.modCount;
      }

      public final boolean hasNext() {
         boolean var1;
         if (this.next != this.this$0.header) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      final Node nextNode() {
         Node var1 = this.next;
         if (var1 != this.this$0.header) {
            if (this.this$0.modCount == this.expectedModCount) {
               this.next = var1.next;
               this.lastReturned = var1;
               return var1;
            } else {
               throw new ConcurrentModificationException();
            }
         } else {
            throw new NoSuchElementException();
         }
      }

      public final void remove() {
         Node var1 = this.lastReturned;
         if (var1 != null) {
            this.this$0.removeInternal(var1, true);
            this.lastReturned = null;
            this.expectedModCount = this.this$0.modCount;
         } else {
            throw new IllegalStateException();
         }
      }
   }

   static final class Node implements Map.Entry {
      int height;
      final Object key;
      Node left;
      Node next;
      Node parent;
      Node prev;
      Node right;
      Object value;

      Node() {
         this.key = null;
         this.prev = this;
         this.next = this;
      }

      Node(Node var1, Object var2, Node var3, Node var4) {
         this.parent = var1;
         this.key = var2;
         this.height = 1;
         this.next = var3;
         this.prev = var4;
         var4.next = this;
         var3.prev = this;
      }

      public boolean equals(Object var1) {
         boolean var4 = var1 instanceof Map.Entry;
         boolean var3 = false;
         boolean var2 = var3;
         if (var4) {
            Map.Entry var6 = (Map.Entry)var1;
            Object var5 = this.key;
            if (var5 == null) {
               var2 = var3;
               if (var6.getKey() != null) {
                  return var2;
               }
            } else {
               var2 = var3;
               if (!var5.equals(var6.getKey())) {
                  return var2;
               }
            }

            var5 = this.value;
            if (var5 == null) {
               var2 = var3;
               if (var6.getValue() != null) {
                  return var2;
               }
            } else {
               var2 = var3;
               if (!var5.equals(var6.getValue())) {
                  return var2;
               }
            }

            var2 = true;
         }

         return var2;
      }

      public Node first() {
         Node var1 = this.left;

         Node var2;
         Node var3;
         for(var2 = this; var1 != null; var1 = var3) {
            var3 = var1.left;
            var2 = var1;
         }

         return var2;
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public int hashCode() {
         Object var3 = this.key;
         int var2 = 0;
         int var1;
         if (var3 == null) {
            var1 = 0;
         } else {
            var1 = var3.hashCode();
         }

         var3 = this.value;
         if (var3 != null) {
            var2 = var3.hashCode();
         }

         return var1 ^ var2;
      }

      public Node last() {
         Node var1 = this.right;

         Node var2;
         Node var3;
         for(var2 = this; var1 != null; var1 = var3) {
            var3 = var1.right;
            var2 = var1;
         }

         return var2;
      }

      public Object setValue(Object var1) {
         Object var2 = this.value;
         this.value = var1;
         return var2;
      }

      public String toString() {
         return this.key + "=" + this.value;
      }
   }
}
