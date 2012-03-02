package com.axeiya.stillcollab.client;

import com.axeiya.stillcollab.wysiwyg.client.ContentEditableSurface;
import com.axeiya.stillcollab.wysiwyg.client.DefaultToolBar;
import com.axeiya.stillcollab.wysiwyg.client.Surface;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class WysiwygSample implements EntryPoint {

  @Override
  public void onModuleLoad() {
    // Surface editor = new RichTextAreaSurface();
    Surface editor2 = new ContentEditableSurface();
    editor2.setValue("<p>mon contenu de test</p>");

    // RootPanel.get().add(new Label("toto tata"));
    DefaultToolBar toolBar = new DefaultToolBar();
    // toolBar.addManagedSurface(editor);
    toolBar.addManagedSurface(editor2);
    RootPanel.get().add(toolBar);
    // RootLayoutPanel.get().setWidgetLeftRight(toolBar, 0, Unit.PX, 0, Unit.PX);
    // RootLayoutPanel.get().setWidgetTopHeight(toolBar, 0, Unit.PX, 40, Unit.PX);

    // RootPanel.get().add(editor);
    // RootLayoutPanel.get().setWidgetLeftWidth(editor, 0, Unit.PX, 800, Unit.PX);
    // RootLayoutPanel.get().setWidgetTopHeight(editor, 40, Unit.PX, 400, Unit.PX);

    RootPanel.get().add(editor2);
    // RootLayoutPanel.get().setWidgetLeftWidth(editor2, 0, Unit.PX, 800, Unit.PX);
    // RootLayoutPanel.get().setWidgetTopHeight(editor2, 440, Unit.PX, 400, Unit.PX);

    // Button click = new Button("click");
    // click.addClickHandler(new ClickHandler() {
    // @Override
    // public void onClick(ClickEvent event) {
    // Window.alert(Selection.getSelection().toHtml());
    // Window.alert(Selection.getSelection().getRangeAt(0).toHtml());
    // }
    // });
    // RootPanel.get().add(click);
  }

}
