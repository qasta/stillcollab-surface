package com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed;

import com.google.gwt.event.shared.EventHandler;


public interface EnterKeyPressedHandler extends EventHandler {
	

	void onEnterKeyPressed(EnterKeyPressedEvent event);
}