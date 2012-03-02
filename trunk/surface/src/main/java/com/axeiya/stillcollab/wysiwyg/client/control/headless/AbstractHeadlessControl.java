package com.axeiya.stillcollab.wysiwyg.client.control.headless;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;

abstract public class AbstractHeadlessControl extends AbstractControl {

  private Inserter inserter;

  public AbstractHeadlessControl(Inserter inserter) {
    this.inserter = inserter;
  }

  @Override
  public Inserter getInserter() {
    return inserter;
  }

}
