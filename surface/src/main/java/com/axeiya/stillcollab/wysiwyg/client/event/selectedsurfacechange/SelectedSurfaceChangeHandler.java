package com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange;

import com.google.gwt.event.shared.EventHandler;


public interface SelectedSurfaceChangeHandler extends EventHandler {
	

	void onSelectedSurfaceChange(SelectedSurfaceChangeEvent event);
}