package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H1Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H2Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H3Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H4Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H5Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H6Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.PInserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.ParagraphInserter;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ParagraphControl extends AbstractControl implements IsWidget, ChangeHandler {

  private ListBox paragraphSelector;
  private Map<Integer, Inserter> inserters = new HashMap<Integer, Inserter>();
  private PInserter pInserter = new PInserter();

  public ParagraphControl() {
    paragraphSelector = new ListBox();
    paragraphSelector.addChangeHandler(this);

    addParagraphStyle(CONSTANTS.paragraphNormal(), pInserter);
    addParagraphStyle(CONSTANTS.heading1(), new H1Inserter());
    addParagraphStyle(CONSTANTS.heading2(), new H2Inserter());
    addParagraphStyle(CONSTANTS.heading3(), new H3Inserter());
    addParagraphStyle(CONSTANTS.heading4(), new H4Inserter());
    addParagraphStyle(CONSTANTS.heading5(), new H5Inserter());
    addParagraphStyle(CONSTANTS.heading6(), new H6Inserter());
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    boolean found = false;
    int index = 1;
    Iterator<Inserter> inserter = inserters.values().iterator();
    // We skip the paragraphInserter because this is the fallback
    inserter.next();
    while (!found && inserter.hasNext()) {
      if (inserter.next().isSelectionAssignee(event.getSelection())) {
        found = true;
        paragraphSelector.setSelectedIndex(index);
      }
      index++;
    }
    if (!found) {
      // fallback
      paragraphSelector.setSelectedIndex(0);
    }
  }

  public void addParagraphStyle(String name, ParagraphInserter<?> inserter) {
    int index = paragraphSelector.getItemCount();
    paragraphSelector.addItem(name);
    inserters.put(index, inserter);
  }

  @Override
  public Inserter getInserter() {
    return pInserter;
  }

  @Override
  public Widget asWidget() {
    return paragraphSelector;
  }

  @Override
  public void onChange(ChangeEvent event) {
    final Inserter inserter = inserters.get(paragraphSelector.getSelectedIndex());
    execute(new Command() {
      @Override
      public void execute() {
        inserter.insert(currentSurface.getSelection());
      }
    });
  }

}
