package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap extends AbstractMap implements Serializable {
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
   int size;
   Node[] table;
   int threshold;

   public LinkedHashTreeMap() {
      this(NATURAL_ORDER);
   }

   public LinkedHashTreeMap(Comparator var1) {
      this.size = 0;
      this.modCount = 0;
      if (var1 == null) {
         var1 = NATURAL_ORDER;
      }

      this.comparator = var1;
      this.header = new Node();
      Node[] var2 = new Node[16];
      this.table = var2;
      this.threshold = var2.length / 2 + var2.length / 4;
   }

   private void doubleCapacity() {
      Node[] var1 = doubleCapacity(this.table);
      this.table = var1;
      this.threshold = var1.length / 2 + var1.length / 4;
   }

   static Node[] doubleCapacity(Node[] var0) {
      int var4 = var0.length;
      Node[] var7 = new Node[var4 * 2];
      AvlIterator var9 = new AvlIterator();
      AvlBuilder var8 = new AvlBuilder();
      AvlBuilder var10 = new AvlBuilder();

      label50:
      for(int var1 = 0; var1 < var4; ++var1) {
         Node var6 = var0[var1];
         if (var6 != null) {
            var9.reset(var6);
            int var3 = 0;
            int var2 = 0;

            while(true) {
               Node var5 = var9.next();
               if (var5 == null) {
                  var8.reset(var3);
                  var10.reset(var2);
                  var9.reset(var6);

                  while(true) {
                     var5 = var9.next();
                     if (var5 == null) {
                        var6 = null;
                        if (var3 > 0) {
                           var5 = var8.root();
                        } else {
                           var5 = null;
                        }

                        var7[var1] = var5;
                        var5 = var6;
                        if (var2 > 0) {
                           var5 = var10.root();
                        }

                        var7[var1 + var4] = var5;
                        continue label50;
                     }

                     if ((var5.hash & var4) == 0) {
                        var8.add(var5);
                     } else {
                        var10.add(var5);
                     }
                  }
               }

               if ((var5.hash & var4) == 0) {
                  ++var3;
               } else {
                  ++var2;
               }
            }
         }
      }

      return var7;
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
               Node var9 = var1.left;
               Node var8 = var1.right;
               byte var5 = 0;
               byte var6 = 0;
               int var3;
               if (var9 != null) {
                  var3 = var9.height;
               } else {
                  var3 = 0;
               }

               int var4;
               if (var8 != null) {
                  var4 = var8.height;
               } else {
                  var4 = 0;
               }

               int var7 = var3 - var4;
               Node var10;
               if (var7 == -2) {
                  var10 = var8.left;
                  var9 = var8.right;
                  if (var9 != null) {
                     var3 = var9.height;
                  } else {
                     var3 = 0;
                  }

                  var4 = var6;
                  if (var10 != null) {
                     var4 = var10.height;
                  }

                  var3 = var4 - var3;
                  if (var3 == -1 || var3 == 0 && !var2) {
                     this.rotateLeft(var1);
                  } else {
                     this.rotateRight(var8);
                     this.rotateLeft(var1);
                  }

                  if (!var2) {
                     break label86;
                  }
               } else if (var7 == 2) {
                  var8 = var9.left;
                  var10 = var9.right;
                  if (var10 != null) {
                     var3 = var10.height;
                  } else {
                     var3 = 0;
                  }

                  var4 = var5;
                  if (var8 != null) {
                     var4 = var8.height;
                  }

                  var3 = var4 - var3;
                  if (var3 == 1 || var3 == 0 && !var2) {
                     this.rotateRight(var1);
                  } else {
                     this.rotateLeft(var9);
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
      Node var4 = var1.parent;
      var1.parent = null;
      if (var2 != null) {
         var2.parent = var4;
      }

      if (var4 != null) {
         if (var4.left == var1) {
            var4.left = var2;
         } else {
            var4.right = var2;
         }
      } else {
         int var3 = var1.hash;
         Node[] var5 = this.table;
         var5[var3 & var5.length - 1] = var2;
      }

   }

   private void rotateLeft(Node var1) {
      Node var5 = var1.left;
      Node var6 = var1.right;
      Node var7 = var6.left;
      Node var8 = var6.right;
      var1.right = var7;
      if (var7 != null) {
         var7.parent = var1;
      }

      this.replaceInParent(var1, var6);
      var6.left = var1;
      var1.parent = var6;
      byte var4 = 0;
      int var2;
      if (var5 != null) {
         var2 = var5.height;
      } else {
         var2 = 0;
      }

      int var3;
      if (var7 != null) {
         var3 = var7.height;
      } else {
         var3 = 0;
      }

      var1.height = Math.max(var2, var3) + 1;
      var3 = var1.height;
      var2 = var4;
      if (var8 != null) {
         var2 = var8.height;
      }

      var6.height = Math.max(var3, var2) + 1;
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

   private static int secondaryHash(int var0) {
      var0 ^= var0 >>> 20 ^ var0 >>> 12;
      return var0 >>> 4 ^ var0 >>> 7 ^ var0;
   }

   private Object writeReplace() throws ObjectStreamException {
      return new LinkedHashMap(this);
   }

   public void clear() {
      Arrays.fill(this.table, (Object)null);
      this.size = 0;
      ++this.modCount;
      Node var3 = this.header;

      Node var2;
      for(Node var1 = var3.next; var1 != var3; var1 = var2) {
         var2 = var1.next;
         var1.prev = null;
         var1.next = null;
      }

      var3.prev = var3;
      var3.next = var3;
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
      Comparator var10 = this.comparator;
      Node[] var9 = this.table;
      int var5 = secondaryHash(var1.hashCode());
      int var4 = var9.length - 1 & var5;
      Node var6 = var9[var4];
      int var3;
      Node var7;
      if (var6 != null) {
         Comparable var8;
         if (var10 == NATURAL_ORDER) {
            var8 = (Comparable)var1;
         } else {
            var8 = null;
         }

         while(true) {
            if (var8 != null) {
               var3 = var8.compareTo(var6.key);
            } else {
               var3 = var10.compare(var1, var6.key);
            }

            if (var3 == 0) {
               return var6;
            }

            if (var3 < 0) {
               var7 = var6.left;
            } else {
               var7 = var6.right;
            }

            if (var7 == null) {
               break;
            }

            var6 = var7;
         }
      } else {
         var3 = 0;
      }

      if (!var2) {
         return null;
      } else {
         var7 = this.header;
         Node var11;
         if (var6 == null) {
            if (var10 == NATURAL_ORDER && !(var1 instanceof Comparable)) {
               throw new ClassCastException(var1.getClass().getName() + " is not Comparable");
            }

            var11 = new Node(var6, var1, var5, var7, var7.prev);
            var9[var4] = var11;
         } else {
            var11 = new Node(var6, var1, var5, var7, var7.prev);
            if (var3 < 0) {
               var6.left = var11;
            } else {
               var6.right = var11;
            }

            this.rebalance(var6, true);
         }

         var3 = this.size++;
         if (var3 > this.threshold) {
            this.doubleCapacity();
         }

         ++this.modCount;
         return var11;
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
         Node var4 = this.find(var1, true);
         Object var3 = var4.value;
         var4.value = var2;
         return var3;
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
         var1.prev = null;
         var1.next = null;
      }

      Node var6 = var1.left;
      Node var7 = var1.right;
      Node var5 = var1.parent;
      int var4 = 0;
      if (var6 != null && var7 != null) {
         if (var6.height > var7.height) {
            var5 = var6.last();
         } else {
            var5 = var7.first();
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
         } else if (var7 != null) {
            this.replaceInParent(var1, var7);
            var1.right = null;
         } else {
            this.replaceInParent(var1, (Node)null);
         }

         this.rebalance(var5, false);
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

   static final class AvlBuilder {
      private int leavesSkipped;
      private int leavesToSkip;
      private int size;
      private Node stack;

      void add(Node var1) {
         var1.right = null;
         var1.parent = null;
         var1.left = null;
         var1.height = 1;
         int var2 = this.leavesToSkip;
         int var3;
         if (var2 > 0) {
            var3 = this.size;
            if ((var3 & 1) == 0) {
               this.size = var3 + 1;
               this.leavesToSkip = var2 - 1;
               ++this.leavesSkipped;
            }
         }

         var1.parent = this.stack;
         this.stack = var1;
         var2 = this.size + 1;
         this.size = var2;
         var3 = this.leavesToSkip;
         if (var3 > 0 && (var2 & 1) == 0) {
            this.size = var2 + 1;
            this.leavesToSkip = var3 - 1;
            ++this.leavesSkipped;
         }

         var2 = 4;

         while(true) {
            var3 = this.size;
            int var4 = var2 - 1;
            if ((var3 & var4) != var4) {
               return;
            }

            var3 = this.leavesSkipped;
            Node var5;
            if (var3 == 0) {
               Node var6 = this.stack;
               var1 = var6.parent;
               var5 = var1.parent;
               var1.parent = var5.parent;
               this.stack = var1;
               var1.left = var5;
               var1.right = var6;
               var1.height = var6.height + 1;
               var5.parent = var1;
               var6.parent = var1;
            } else if (var3 == 1) {
               var5 = this.stack;
               var1 = var5.parent;
               this.stack = var1;
               var1.right = var5;
               var1.height = var5.height + 1;
               var5.parent = var1;
               this.leavesSkipped = 0;
            } else if (var3 == 2) {
               this.leavesSkipped = 0;
            }

            var2 *= 2;
         }
      }

      void reset(int var1) {
         this.leavesToSkip = Integer.highestOneBit(var1) * 2 - 1 - var1;
         this.size = 0;
         this.leavesSkipped = 0;
         this.stack = null;
      }

      Node root() {
         Node var1 = this.stack;
         if (var1.parent == null) {
            return var1;
         } else {
            throw new IllegalStateException();
         }
      }
   }

   static class AvlIterator {
      private Node stackTop;

      public Node next() {
         Node var4 = this.stackTop;
         if (var4 == null) {
            return null;
         } else {
            Node var2 = var4.parent;
            var4.parent = null;
            Node var1 = var4.right;

            while(true) {
               Node var3 = var2;
               var2 = var1;
               if (var1 == null) {
                  this.stackTop = var3;
                  return var4;
               }

               var1.parent = var3;
               var1 = var1.left;
            }
         }
      }

      void reset(Node var1) {
         Node var2;
         Node var3;
         for(var2 = null; var1 != null; var1 = var3) {
            var1.parent = var2;
            var3 = var1.left;
            var2 = var1;
         }

         this.stackTop = var2;
      }
   }

   final class EntrySet extends AbstractSet {
      final LinkedHashTreeMap this$0;

      EntrySet(LinkedHashTreeMap var1) {
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
      final LinkedHashTreeMap this$0;

      KeySet(LinkedHashTreeMap var1) {
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
      final LinkedHashTreeMap this$0;

      LinkedTreeMapIterator(LinkedHashTreeMap var1) {
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
      final int hash;
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
         this.hash = -1;
         this.prev = this;
         this.next = this;
      }

      Node(Node var1, Object var2, int var3, Node var4, Node var5) {
         this.parent = var1;
         this.key = var2;
         this.hash = var3;
         this.height = 1;
         this.next = var4;
         this.prev = var5;
         var5.next = this;
         var4.prev = this;
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
