package de.materna.alchemistpeddler.tui;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Container;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.Window.Hint;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import de.materna.alchemistpeddler.gamelogic.Game;
import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class TUIApp implements PlayerEventGenerator {
  public static GameController gameController = new GameController();
  public static GameWindow gameWindow;
  public static WindowBasedTextGUI gui;
  public static void main(String[] args) throws IOException, InterruptedException {
    CityGraph.buildGraph();
    Terminal term = new DefaultTerminalFactory().createTerminal();
    Screen screen = new TerminalScreen(term);
    gui = new MultiWindowTextGUI(screen);
    gameWindow = new GameWindow();
    Container gameComponent = (Container) gameWindow.getComponent();
    gameWindow.setComponent(gameComponent);
    gui.addWindow(gameWindow);
    screen.startScreen();
    gameWindow.waitUntilClosed();
    screen.stopScreen();

  }
}
