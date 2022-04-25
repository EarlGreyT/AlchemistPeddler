package de.materna.alchemistpeddler.tui;

import static de.materna.alchemistpeddler.tui.TUIApp.gameController;
import static de.materna.alchemistpeddler.tui.TUIApp.gui;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.tui.gamepanels.GamePanel;
import java.util.List;


public class GameWindow extends BasicWindow {

  public GameWindow() {
    super("AlchemistPeddler");
    setHints(List.of(Hint.FULL_SCREEN));
    setTheme(new BlackTheme());
    setComponent(new GamePanel());
  }

  public GamePanel getGamePanel(){
    return (GamePanel) getComponent();
  }

}
