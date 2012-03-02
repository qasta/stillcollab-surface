package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.QuoteInserter;

public class Quote extends AbstractToggleControl {

  public Quote() {
    super(new QuoteInserter());
    getUi().setText("Quote");
  }
}
