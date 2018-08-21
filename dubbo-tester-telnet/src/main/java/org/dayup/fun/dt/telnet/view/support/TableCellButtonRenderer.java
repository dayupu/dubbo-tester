package org.dayup.fun.dt.telnet.view.support;

import com.alee.laf.button.WebButton;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class TableCellButtonRenderer extends WebButton implements TableCellRenderer {


    public TableCellButtonRenderer(String text) {
        this.setText(text);
        this.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}
