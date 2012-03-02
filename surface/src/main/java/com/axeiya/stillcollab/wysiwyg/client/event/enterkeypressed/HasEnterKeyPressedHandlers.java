package com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasEnterKeyPressedHandlers extends HasHandlers {

	HandlerRegistration addEnterKeyPressedHandler(EnterKeyPressedHandler handler);
}
