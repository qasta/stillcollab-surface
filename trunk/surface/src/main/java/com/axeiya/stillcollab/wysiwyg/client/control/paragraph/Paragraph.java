package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.PInserter;

public class Paragraph extends AbstractToggleControl {

  public Paragraph() {
    super(new PInserter());
    getUi().setText("P");
  }
}
