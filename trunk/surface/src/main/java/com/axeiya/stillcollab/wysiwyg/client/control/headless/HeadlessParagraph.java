package com.axeiya.stillcollab.wysiwyg.client.control.headless;

import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.PInserter;

/**
 * Utilisé pour capturer le [Enter] par défaut, sans avoir d'IHM correspondante
 * 
 * @author damien
 * 
 */
public class HeadlessParagraph extends AbstractHeadlessControl {

  public HeadlessParagraph() {
    super(new PInserter());
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {

  }
}
