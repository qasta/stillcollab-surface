package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H1Inserter;

public class Heading1 extends AbstractToggleControl {

  public Heading1() {
    super(new H1Inserter());
    getUi().setText("H1");
  }
}
