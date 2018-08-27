package org.dayup.fish.supplier.component.tree;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;

public class DuNode extends DefaultMutableTreeNode implements Serializable {


    private static final long serialVersionUID = -1746744225427309886L;

    private DuNodeType type;

    private String text;

    public DuNode(DuNodeType type, String text) {
        super();
        this.type = type;
        this.text = text;
    }

    public DuNode(DuNodeType type, String text, Object object) {
        super(object);
        this.type = type;
        this.text = text;
    }


    public DuNodeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return text;
    }
}
