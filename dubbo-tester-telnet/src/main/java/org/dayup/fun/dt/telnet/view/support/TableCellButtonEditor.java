package org.dayup.fun.dt.telnet.view.support;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class TableCellButtonEditor extends DefaultCellEditor {

    private Object value;
    private JButton button;

    public TableCellButtonEditor(String text, ButtonClickCallback callback) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton(text);
        this.button.setOpaque(true);
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                callback.callback(value);
            }
        });
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value = value;
        return this.button;
    }


    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }
}
