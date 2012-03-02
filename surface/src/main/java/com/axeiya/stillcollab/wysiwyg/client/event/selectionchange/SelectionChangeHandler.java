package com.axeiya.stillcollab.wysiwyg.client.event.selectionchange;

import com.google.gwt.event.shared.EventHandler;


public interface SelectionChangeHandler extends EventHandler {
	

	void onSelectionChange(SelectionChangeEvent event);
}