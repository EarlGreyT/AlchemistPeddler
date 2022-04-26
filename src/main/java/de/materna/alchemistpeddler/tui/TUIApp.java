package de.materna.alchemistpeddler.tui;


import com.googlecode.lanterna.gui2.Container;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import de.materna.alchemistpeddler.gameuicommunication.CityGraph;
import de.materna.alchemistpeddler.gameuicommunication.PlayerEventGenerator;
import java.io.IOException;


public class TUIApp implements PlayerEventGenerator {

  public static GameController gameController = new GameController();
  public static GameWindow gameWindow;
  public static WindowBasedTextGUI gui;

  public static void main(String[] args) throws IOException, InterruptedException {
    gameController.startNewGame();
    DefaultTerminalFactory factory = new DefaultTerminalFactory();
    factory.setTerminalEmulatorFrameAutoCloseTrigger(
        TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);
    Terminal term = factory.createTerminal();
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
