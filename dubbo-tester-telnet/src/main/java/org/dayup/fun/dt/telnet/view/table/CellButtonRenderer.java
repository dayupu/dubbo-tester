package org.dayup.fun.dt.telnet.view.table;

import com.alee.laf.button.WebButton;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class CellButtonRenderer extends WebButton implements TableCellRenderer {


    public CellButtonRenderer(String text) {
        this.setText(text);
        this.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}
