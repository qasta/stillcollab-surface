package com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasHotKeyPressedHandlers extends HasHandlers {

	HandlerRegistration addHotKeyPressedHandler(HotKeyPressedHandler handler);
}
