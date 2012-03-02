package com.axeiya.stillcollab.wysiwyg.client.inserter.action;

import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.google.gwt.dom.client.Element;

public abstract class InsertAction<E extends Element> {

  public void doAction(E element, Selection selection) {
    onAction(element, selection);
    DOMUtil.cleanBranch(element);
  }

  /**
   * Action à effectuer sur l'élément de DOM passé en paramètre ; toute opération DOM est possible
   * 
   * @param element Elément sur lequel effectuer l'action
   */
  abstract public void onAction(E element, Selection selection);

  /**
   * Action à effectuer lors de la suppression
   * 
   * @param element
   */
  public void onRevert(Element element, Selection selection) {
  }

  /**
   * Fourni un élément de référence (qui sera utilisé pour la récupération du tag HTML par exemple)
   * 
   * @return Elément de référence
   */
  abstract public E getEmptyElement();
}
