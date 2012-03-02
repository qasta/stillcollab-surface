package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H2Inserter;

public class Heading2 extends AbstractToggleControl {

  public Heading2() {
    super(new H2Inserter());
    getUi().setText("H2");
  }
}
