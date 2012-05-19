package com.axeiya.stillcollab.wysiwyg.client;

import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.HasEnterKeyPressedHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed.HasHotKeyPressedHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange.HasSelectedSurfaceChangeHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.HasSelectionChangeHandlers;
import com.axeiya.stillcollab.wysiwyg.client.processor.InPlaceProcessor;
import com.axeiya.stillcollab.wysiwyg.client.processor.Processor;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Range;
import com.axeiya.stillcollab.wysiwyg.client.ranges.SurfaceSelection;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;

public interface Surface extends HasSelectionChangeHandlers, HasEnterKeyPressedHandlers,
    HasHotKeyPressedHandlers, HasSelectedSurfaceChangeHandlers, IsWidget, HasValue<String>,
    HasFocusHandlers {

  SurfaceSelection getSelection();

  void setSelection(Range range);

  void notifyUpdate();

  void focus();

  void addPostProcessor(Processor postProcessor);

  void addPreProcessor(Processor preProcessor);

  void processOnce(InPlaceProcessor processor);

  com.google.gwt.dom.client.Element getElement();

  boolean isEditable();

  void setEditable(boolean editable);

}