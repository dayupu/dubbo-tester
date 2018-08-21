package org.dayup.fun.dt.telnet.view.support;

import org.dayup.fun.dt.telnet.bean.DuService;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DuServiceTableModel extends AbstractTableModel implements Serializable {

    private List<DuService> data = new ArrayList<>();
    private String[] header = {"Interface"};


    public void clear() {
        data.clear();
    }

    public void setData(List<DuService> datas) {
        clear();
        this.data = datas;
        fireTableDataChanged();
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
                return data.get(rowIndex).getInterfaceClass();
        }
        return null;
    }

    public DuService getRowAt(int rowIndex) {
        return data.get(rowIndex);
    }


}
