package org.dayup.fun.dt.telnet.view.table;

import org.dayup.fun.dt.telnet.dto.DubboApi;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DubboApiTableModel extends AbstractTableModel implements Serializable {

    private List<DubboApi> data = new ArrayList<>();
    private String[] header = {"Interface"};


    public void clear() {
        data.clear();
    }

    public void setData(List<DubboApi> datas) {
        clear();
        this.data = datas;
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
                return data.get(rowIndex).getApiClass();
        }
        return null;
    }

    public DubboApi getRowAt(int rowIndex) {
        return data.get(rowIndex);
    }


}
