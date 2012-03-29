package com.axeiya.stillcollab.wysiwyg.client.ranges;

import com.axeiya.stillcollab.wysiwyg.client.Surface;

public class SurfaceSelection {

  private Selection selection;
  private Surface associatedSurface;

  public SurfaceSelection(Selection selection, Surface associatedSurface) {
    this.selection = selection;
    this.associatedSurface = associatedSurface;
  }

  public Selection getSelection() {
    return selection;
  }

  public void setSelection(Selection selection) {
    this.selection = selection;
  }

  public Surface getAssociatedSurface() {
    return associatedSurface;
  }

  public void setAssociatedSurface(Surface associatedSurface) {
    this.associatedSurface = associatedSurface;
  }

}
