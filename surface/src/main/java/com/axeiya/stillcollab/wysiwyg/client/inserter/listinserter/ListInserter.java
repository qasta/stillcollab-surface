package com.axeiya.stillcollab.wysiwyg.client.inserter.listinserter;

import java.util.List;

import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.EnterKeyPressedEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.EnterKeyPressedHandler;
import com.axeiya.stillcollab.wysiwyg.client.inserter.action.InsertAction;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.BlockInserter;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Range;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;

public abstract class ListInserter<E extends Element> extends BlockInserter<E> implements
    EnterKeyPressedHandler {

  protected ListInserter(InsertAction<E> action) {
    super(action);
  }

  abstract protected List<String> getTagCollection();

  public void remove(Selection selection) {
    Range range = selection.getRange();

    Element ancestor = getCommonMatchingAncestor(selection);
    if (ancestor != null && ancestor.getParentElement() != null) {
      Node startNode = range.getStartContainer();
      Node endNode = range.getEndContainer();
      // nouvelle liste recueillant les lis après la sélection
      Element list =
          startNode.getOwnerDocument().createElement(action.getEmptyElement().getTagName());
      ancestor.getParentNode().insertAfter(list, ancestor);
      // on fait la liste des li concernés par la sélection

      Node nextSibling, child = ancestor.getFirstChild();
      boolean started = false, finished = false;
      while (child != null) {
        nextSibling = child.getNextSibling();
        if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equalsIgnoreCase("li")) {
          if (child.isOrHasChild(startNode)) {
            started = true;
          }
          if (started) {
            if (!finished) {
              if (child.isOrHasChild(endNode)) {
                finished = true;
              }
              child.removeFromParent();
              ancestor.getParentNode().insertBefore(child, list);
              action.onRevert((Element) child, selection);
            } else {
              child.removeFromParent();
              list.appendChild(child);
            }
          }
        }
        child = nextSibling;
      }
      DOMUtil.cleanBranch(ancestor);
      DOMUtil.cleanBranch(list);
    }
  }

  @Override
  public void onEnterKeyPressed(EnterKeyPressedEvent event) {
    if (getCommonMatchingAncestor(event.getSelection()) != null) {
      event.setHandled(true);
    }
  }

  @Override
  public void insert(Selection selection) {
    // on détermine si on est dans une liste d'un autre type, et si elle est vide
    Element commonAncestor = getAllMatchingAncestor(selection);
    if (commonAncestor != null
        && !commonAncestor.getTagName().equals(action.getEmptyElement().getTagName())
        && getLiCount(commonAncestor) <= 1) {
      // dans ce cas, on vérifie si la liste est vide
      Element container =
          commonAncestor.getOwnerDocument().createElement(action.getEmptyElement().getTagName());
      Node nextSibling, child = commonAncestor.getFirstChild();
      while (child != null) {
        nextSibling = child.getNextSibling();
        container.appendChild(child);
        child = nextSibling;
      }
      commonAncestor.getParentElement().insertAfter(container, commonAncestor);
      commonAncestor.removeFromParent();
    } else {
      super.insert(selection);
    }
  }

  protected Element getAllMatchingAncestor(Selection selection) {
    Element ancestor = (Element) selection.getRange().getCommonAncestorContainer();
    return DOMUtil.getFirstAncestorInTypes(ancestor, getTagCollection());
  }

  protected int getLiCount(Element listElement) {
    int liCount = 0;
    Element child = listElement.getFirstChildElement();
    while (child != null) {
      if (child.getTagName().equalsIgnoreCase("li")) {
        liCount++;
      }
      child = child.getNextSiblingElement();
    }
    return liCount;
  }

}
