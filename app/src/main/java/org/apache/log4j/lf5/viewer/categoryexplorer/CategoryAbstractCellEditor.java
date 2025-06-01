package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

public class CategoryAbstractCellEditor implements TableCellEditor, TreeCellEditor {
   static Class class$javax$swing$event$CellEditorListener;
   protected ChangeEvent _changeEvent = null;
   protected int _clickCountToStart = 1;
   protected EventListenerList _listenerList = new EventListenerList();
   protected Object _value;

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public void addCellEditorListener(CellEditorListener var1) {
      EventListenerList var4 = this._listenerList;
      Class var3 = class$javax$swing$event$CellEditorListener;
      Class var2 = var3;
      if (var3 == null) {
         var2 = class$("javax.swing.event.CellEditorListener");
         class$javax$swing$event$CellEditorListener = var2;
      }

      var4.add(var2, var1);
   }

   public void cancelCellEditing() {
      this.fireEditingCanceled();
   }

   protected void fireEditingCanceled() {
      Object[] var4 = this._listenerList.getListenerList();

      for(int var1 = var4.length - 2; var1 >= 0; var1 -= 2) {
         Object var5 = var4[var1];
         Class var3 = class$javax$swing$event$CellEditorListener;
         Class var2 = var3;
         if (var3 == null) {
            var2 = class$("javax.swing.event.CellEditorListener");
            class$javax$swing$event$CellEditorListener = var2;
         }

         if (var5 == var2) {
            if (this._changeEvent == null) {
               this._changeEvent = new ChangeEvent(this);
            }

            ((CellEditorListener)var4[var1 + 1]).editingCanceled(this._changeEvent);
         }
      }

   }

   protected void fireEditingStopped() {
      Object[] var4 = this._listenerList.getListenerList();

      for(int var1 = var4.length - 2; var1 >= 0; var1 -= 2) {
         Object var5 = var4[var1];
         Class var3 = class$javax$swing$event$CellEditorListener;
         Class var2 = var3;
         if (var3 == null) {
            var2 = class$("javax.swing.event.CellEditorListener");
            class$javax$swing$event$CellEditorListener = var2;
         }

         if (var5 == var2) {
            if (this._changeEvent == null) {
               this._changeEvent = new ChangeEvent(this);
            }

            ((CellEditorListener)var4[var1 + 1]).editingStopped(this._changeEvent);
         }
      }

   }

   public Object getCellEditorValue() {
      return this._value;
   }

   public int getClickCountToStart() {
      return this._clickCountToStart;
   }

   public Component getTableCellEditorComponent(JTable var1, Object var2, boolean var3, int var4, int var5) {
      return null;
   }

   public Component getTreeCellEditorComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6) {
      return null;
   }

   public boolean isCellEditable(EventObject var1) {
      return !(var1 instanceof MouseEvent) || ((MouseEvent)var1).getClickCount() >= this._clickCountToStart;
   }

   public void removeCellEditorListener(CellEditorListener var1) {
      EventListenerList var4 = this._listenerList;
      Class var3 = class$javax$swing$event$CellEditorListener;
      Class var2 = var3;
      if (var3 == null) {
         var2 = class$("javax.swing.event.CellEditorListener");
         class$javax$swing$event$CellEditorListener = var2;
      }

      var4.remove(var2, var1);
   }

   public void setCellEditorValue(Object var1) {
      this._value = var1;
   }

   public void setClickCountToStart(int var1) {
      this._clickCountToStart = var1;
   }

   public boolean shouldSelectCell(EventObject var1) {
      return this.isCellEditable(var1) && (var1 == null || ((MouseEvent)var1).getClickCount() >= this._clickCountToStart);
   }

   public boolean stopCellEditing() {
      this.fireEditingStopped();
      return true;
   }
}
