package org.dayup.fish.view.assembly;

import com.alee.extended.tab.DocumentData;
import com.alee.extended.tab.WebDocumentPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.tree.WebTree;
import com.alee.laf.tree.WebTreeModel;
import org.dayup.fish.supplier.component.tree.DuNode;
import org.dayup.fish.supplier.component.tree.DuNodeType;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FishBodyPanel extends WebSplitPane {

    private static final int DIVIDER_BODY = 300;
    private static final int DIVIDER_LEFT = 200;
    private WebPanel leftTop;
    private WebPanel leftBottom;
    private Component right;

    public FishBodyPanel() {
        setOrientation(WebSplitPane.HORIZONTAL_SPLIT);
        setLeftComponent(left());
        setRightComponent(right());
        setDividerLocation(DIVIDER_BODY);
    }


    private Component left() {
        leftTop = new WebPanel(true);
        leftTop.add(createTree());
        leftBottom = new WebPanel(true);
        WebSplitPane left = new WebSplitPane(WebSplitPane.VERTICAL_SPLIT, leftTop, leftBottom);
        left.setDividerLocation(DIVIDER_LEFT);
        left.setOneTouchExpandable(true);
        this.createTree();
        return left;
    }

    private Component right() {
        WebPanel panel = new WebPanel(true);

        WebDocumentPane documentPane = new WebDocumentPane();
        documentPane.openDocument(new DocumentData("xx","xx", new WebPanel()));

        panel.add(documentPane);
        return panel;
    }


    private WebScrollPane createTree() {
        WebTree tree = new WebTree();

        DuNode root = new DuNode(DuNodeType.TITLE, "xxx");
        DuNode node1 = new DuNode(DuNodeType.TITLE, "xxx1");
        DuNode node10 = new DuNode(DuNodeType.TITLE, "xxx2");
        node1.add(node10);
        root.add(node1);
        tree.setModel(new WebTreeModel<DuNode>(root));
        tree.setSelectionMode(WebTree.DISCONTIGUOUS_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
//                System.out.println(e.getNewLeadSelectionPath().getPathCount());
            }
        });

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == tree && e.getClickCount() == 2) {
                    TreePath treePath = tree.getPathForLocation(e.getX(), e.getY());
                    if (treePath != null) {
                        DuNode node = (DuNode)treePath.getLastPathComponent();
                        System.out.println(node.getType());
                        System.out.println(node);
                    }
                }
            }
        });
        WebScrollPane scrollPane = new WebScrollPane(tree);
        return scrollPane;

    }
}
