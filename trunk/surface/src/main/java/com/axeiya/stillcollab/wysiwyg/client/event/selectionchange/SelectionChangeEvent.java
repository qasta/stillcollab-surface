package com.axeiya.stillcollab.wysiwyg.client.event.selectionchange;

import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.google.gwt.event.shared.GwtEvent;

public class SelectionChangeEvent extends GwtEvent<SelectionChangeHandler> {

  private static Type<SelectionChangeHandler> TYPE;

  public static Type<SelectionChangeHandler> getType() {
    if (TYPE == null) {
      TYPE = new Type<SelectionChangeHandler>();
    }
    return TYPE;
  }

  private Selection selection;

  protected SelectionChangeEvent(Selection selection) {
    super();
    this.selection = selection;
  }

  public Selection getSelection() {
    return selection;
  }

  @Override
  protected void dispatch(SelectionChangeHandler handler) {
    handler.onSelectionChange(this);
  }

  @Override
  public GwtEvent.Type<SelectionChangeHandler> getAssociatedType() {
    return getType();
  }

  public static void fire(HasSelectionChangeHandlers source, Selection oldSelection) {
    source.fireEvent(new SelectionChangeEvent(oldSelection));
  }

  public static void fire(HasSelectionChangeHandlers source, SelectionChangeEvent event) {
    source.fireEvent(event);
  }
}
