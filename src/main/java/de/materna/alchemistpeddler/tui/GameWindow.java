package de.materna.alchemistpeddler.tui;

import com.googlecode.lanterna.gui2.BasicWindow;
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
