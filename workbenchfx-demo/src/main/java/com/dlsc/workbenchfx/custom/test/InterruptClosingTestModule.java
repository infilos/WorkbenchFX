package com.dlsc.workbenchfx.custom.test;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchDialog;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.util.concurrent.CompletableFuture;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class InterruptClosingTestModule extends WorkbenchModule {

  boolean closePossible;

  public InterruptClosingTestModule() {
    super("Interrupt Close", FontAwesomeIcon.QUESTION);
  }

  @Override
  public void init(Workbench workbench) {
    super.init(workbench);
    closePossible = false;
  }

  @Override
  public Node activate() {
    return new Label("This module will open up a dialog when trying to close it.");
  }

  @Override
  public boolean destroy() {
    System.out.println("DESTROY CALLED ON 1");
    getWorkbench().showConfirmationDialog("Confirmation", "Are you sure you want to close this module without saving?", buttonType -> {
      if (ButtonType.YES.equals(buttonType)) {
        System.out.println("Pressed: YES");
        close();
      }
    });
    return false;
  }



}
