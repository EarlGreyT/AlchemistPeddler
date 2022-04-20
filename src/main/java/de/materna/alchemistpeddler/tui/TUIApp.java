package de.materna.alchemistpeddler.tui;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import java.io.IOException;

public class TUIApp implements PlayerEventGenerator {
  private static GameController gameController = new GameController();
  public static void main(String[] args) throws IOException {
    Game game = new Game(gameController);
    Terminal term = new DefaultTerminalFactory().createTerminal();
    Screen screen = new TerminalScreen(term);
    WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);
    screen.startScreen();

// use GUI here until the GUI wants to exit

    screen.stopScreen();

  }
}
