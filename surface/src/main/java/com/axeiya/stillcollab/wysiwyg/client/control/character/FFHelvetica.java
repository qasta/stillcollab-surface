package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.FontFamilyInserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.FontFamilyInserter.FontFamilyConfig;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class FFHelvetica extends AbstractControl implements ClickHandler, IsWidget {
  private Button ui;
  private FontFamilyInserter inserter;
  
  private static final FontFamilyConfig config = new FontFamilyConfig("Helvetica");

  public FFHelvetica() {
    ui = new Button("Helvetica");
    this.inserter = new FontFamilyInserter();
    ui.addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    execute(new Command() {
      @Override
      public void execute() {
        inserter.insert(currentSurface.getSelection(), config);
      }
    });
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
  }

  public Button getUi() {
    return ui;
  }

  public Inserter getInserter() {
    return inserter;
  }

  @Override
  public Widget asWidget() {
    return ui;
  }
}
