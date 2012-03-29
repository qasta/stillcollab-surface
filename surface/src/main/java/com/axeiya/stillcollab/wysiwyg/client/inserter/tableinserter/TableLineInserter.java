package com.axeiya.stillcollab.wysiwyg.client.inserter.tableinserter;

import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.axeiya.stillcollab.wysiwyg.client.ranges.SurfaceSelection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;

public class TableLineInserter extends Inserter {

  @Override
  public void insert(SurfaceSelection selection) {
    Node ancestor = selection.getSelection().getRange().getCommonAncestorContainer();

    // Ensuite on cherche à trouver le tr le plus proche
    Node endNode = selection.getSelection().getRange().getEndContainer();
    Node tr = DOMUtil.getFirstAncestorOfType(endNode, TableInserter.LINE_TAG);
    int columnCount = tr.getChildCount();
    Node newLine = tr.cloneNode(false);
    for (int i = 0; i < columnCount; i++) {
      Node td = tr.getChild(i).cloneNode(false);
      td.appendChild(DOMUtil.createFocusBr());
      newLine.appendChild(td);
    }
    tr.getParentElement().insertAfter(newLine, tr);
  }

  @Override
  public void remove(SurfaceSelection selection) {
    Node endNode = selection.getSelection().getRange().getEndContainer();
    Node tr = DOMUtil.getFirstAncestorOfType(endNode, TableInserter.LINE_TAG);
    if (tr != null) {
      tr.removeFromParent();
    }
  }

  @Override
  public boolean isSelectionAssignee(SurfaceSelection selection) {
    Node ancestor =
        DOMUtil.getFirstAncestorOfType(selection.getSelection().getRange().getStartContainer(),
            TableInserter.TABLE_TAG);
    return ancestor != null;
  }

  @Override
  protected boolean adjustSelectionAssignee(Element matchingAncestor, SurfaceSelection selection) {
    return true;
  }

}
