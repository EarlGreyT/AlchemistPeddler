package de.materna.alchemistpeddler.tui;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.graphics.Theme;
import com.googlecode.lanterna.graphics.ThemeDefinition;
import com.googlecode.lanterna.graphics.ThemeStyle;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.WindowDecorationRenderer;
import com.googlecode.lanterna.gui2.WindowPostRenderer;
import java.util.EnumSet;

public class BlackTheme implements Theme {
    @Override
    public ThemeDefinition getDefaultDefinition() {
      return new ThemeDefinition() {
        @Override
        public ThemeStyle getNormal() {
          return new ThemeStyle() {
            @Override
            public TextColor getForeground() {
              return ANSI.WHITE;
            }

            @Override
            public TextColor getBackground() {
              return ANSI.BLACK;
            }

            @Override
            public EnumSet<SGR> getSGRs() {
              return LanternaThemes.getDefaultTheme().getDefaultDefinition().getNormal()
                  .getSGRs();
            }
          };
        }

        @Override
        public ThemeStyle getPreLight() {
          return getNormal();
        }

        @Override
        public ThemeStyle getSelected() {
          return getNormal();
        }

        @Override
        public ThemeStyle getActive() {
          return getNormal();
        }

        @Override
        public ThemeStyle getInsensitive() {
          return getNormal();
        }

        @Override
        public ThemeStyle getCustom(String s) {
          return getNormal();
        }

        @Override
        public ThemeStyle getCustom(String s, ThemeStyle themeStyle) {
          return getNormal();
        }

        @Override
        public boolean getBooleanProperty(String s, boolean b) {
          return LanternaThemes.getDefaultTheme().getDefaultDefinition().getBooleanProperty(s,b);
        }

        @Override
        public boolean isCursorVisible() {
          return LanternaThemes.getDefaultTheme().getDefaultDefinition().isCursorVisible();
        }

        @Override
        public char getCharacter(String s, char c) {
          return LanternaThemes.getDefaultTheme().getDefaultDefinition().getCharacter(s,c);
        }

        @Override
        public <T extends Component> ComponentRenderer<T> getRenderer(Class<T> aClass) {
          return LanternaThemes.getDefaultTheme().getDefaultDefinition().getRenderer(aClass);
        }
      };
    }

    @Override
    public ThemeDefinition getDefinition(Class<?> aClass) {
      return getDefaultDefinition();
    }

    @Override
    public WindowPostRenderer getWindowPostRenderer() {
      return LanternaThemes.getDefaultTheme().getWindowPostRenderer();
    }

    @Override
    public WindowDecorationRenderer getWindowDecorationRenderer() {
      return LanternaThemes.getDefaultTheme().getWindowDecorationRenderer();
    }
}
