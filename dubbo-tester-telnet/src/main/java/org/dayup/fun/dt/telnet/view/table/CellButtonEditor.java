package org.dayup.fun.dt.telnet.view.table;

import com.alee.laf.button.WebButton;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButtonEditor extends DefaultCellEditor {

    private Object value;
    private JButton button;

    public CellButtonEditor(String text, CellButtonCallback callback) {
        super(new JTextField());
        this.setClickCountToStart(1);
        this.button = new JButton(text);
        this.button.setOpaque(true);
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callback.callback(value);
            }
        });
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value = value;
        return this.button;
    }

}
