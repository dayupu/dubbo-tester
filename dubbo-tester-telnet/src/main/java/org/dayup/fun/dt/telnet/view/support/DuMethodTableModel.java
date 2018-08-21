package org.dayup.fun.dt.telnet.view.support;

import org.dayup.fun.dt.telnet.bean.DuMethod;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DuMethodTableModel extends AbstractTableModel implements Serializable {

    private List<DuMethod> data = new ArrayList<>();
    private String[] header = {"", "Method", "Return", "Params"};
    private int[] widths = {50, 200, 200, 500};

    public void clear() {
        data.clear();
    }

    public void setData(List<DuMethod> datas) {
        clear();
        this.data = datas;
        fireTableDataChanged();
    }

    public void initColumnSizes(JTable table) {
        TableColumn column;
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            column = columnModel.getColumn(i);
            column.setMinWidth(widths[i]);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex);
            case 1:
                return data.get(rowIndex).getName();
            case 2:
                return data.get(rowIndex).getReturnType();
            case 3:
                return data.get(rowIndex).getParamTypes().toString();
        }
        return null;
    }

    public DuMethod getRowAt(int rowIndex) {
        return data.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }


}
