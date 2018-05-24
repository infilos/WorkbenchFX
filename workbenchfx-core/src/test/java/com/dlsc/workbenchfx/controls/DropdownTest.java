package com.dlsc.workbenchfx.controls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dlsc.workbenchfx.view.controls.Dropdown;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Tests for {@link Dropdown}.
 */
@Tag("fast")
class DropdownTest extends ApplicationTest {
  // Dropdown items
  private String dropdownText;
  private FontAwesomeIconView dropdownIconView;
  private ImageView dropdownImageView;
  private MenuItem dropdownMenuItem;

  private Dropdown dropdown;

  @BeforeEach
  void setup() {
    // Initialization of items for Dropdown testing
    dropdownText = "Dropdown Text";
    dropdownIconView = new FontAwesomeIconView(FontAwesomeIcon.QUESTION);
    dropdownImageView = new ImageView(
        new Image(DropdownTest.class.getResource("../date-picker.png").toExternalForm())
    );
    dropdownMenuItem = new MenuItem("Menu Item");

    dropdown = Dropdown.of(dropdownText, dropdownIconView, dropdownMenuItem);
  }

  @Test
  void createDropdown() {
    dropdown = Dropdown.of(dropdownText);
    assertEquals(dropdownText, dropdown.getText());
    assertNull(dropdown.getIcon());
    assertSame(0, dropdown.getItems().size());

    dropdown = Dropdown.of(dropdownText, dropdownMenuItem);
    assertEquals(dropdownText, dropdown.getText());
    assertNull(dropdown.getIcon());
    assertSame(1, dropdown.getItems().size());

    dropdown = Dropdown.of(dropdownIconView);
    assertEquals(dropdownIconView, dropdown.getIcon());
    assertNull(dropdown.getText());
    assertSame(0, dropdown.getItems().size());

    dropdown = Dropdown.of(dropdownIconView, dropdownMenuItem);
    assertEquals(dropdownIconView, dropdown.getIcon());
    assertNull(dropdown.getText());
    assertSame(1, dropdown.getItems().size());

    dropdown = Dropdown.of(dropdownText, dropdownIconView);
    assertEquals(dropdownText, dropdown.getText());
    assertEquals(dropdownIconView, dropdown.getIcon());
    assertSame(0, dropdown.getItems().size());

    dropdown = Dropdown.of(dropdownText, dropdownIconView, dropdownMenuItem);
    assertEquals(dropdownText, dropdown.getText());
    assertEquals(dropdownIconView, dropdown.getIcon());
    assertSame(1, dropdown.getItems().size());

    dropdown = Dropdown.of(dropdownText, dropdownImageView, dropdownMenuItem);
    assertEquals(dropdownImageView, dropdown.getIcon());
  }

  @Test
  void invertDropdown() {
    assertFalse(dropdown.getInverted());
    dropdown.invertStyle();
    assertTrue(dropdown.getInverted());
    dropdown.invertStyle();
    assertFalse(dropdown.getInverted());
  }

  @Test
  void removeItemFromDropdown() {
    int initialSize = dropdown.getItems().size();
    dropdown.getItems().remove(0);
    assertSame(initialSize - 1, dropdown.getItems().size());
  }

  @Test
  void addItemFromDropdown() {
    int initialSize = dropdown.getItems().size();
    dropdown.getItems().add(new CustomMenuItem(new Label("New Item")));
    assertSame(initialSize + 1, dropdown.getItems().size());
  }
}