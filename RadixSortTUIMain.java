/**
 * Ian Anderson
 * 4/4/19
 */
import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;

public class RadixSortTUIMain{

    public static void main(String[] args) throws InterruptedException
    {
        Terminal term = null;
        Screen screen;
        try
        {
            term = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(term);
            screen.startScreen();

            Timer timer = new Timer();
            WindowBasedTextGUI mainFrame = new MultiWindowTextGUI(screen);
            Window optionButtonWindow = new BasicWindow("Radix Sort TUI");
            Window cardButtonWindow = new BasicWindow("Card Buttons");
            Window machineSlotWindow = new BasicWindow();
            Panel optionContent = new Panel(new BorderLayout());
            Panel cardContent = new Panel(new BorderLayout());

            OptionButtonsTUI userOptionButtons = new OptionButtonsTUI();
            CardButtonsTUI machineCardButtons = new CardButtonsTUI();
            CardRendererText machineSlots = new CardRendererText();
            optionContent.addComponent(userOptionButtons);
            cardContent.addComponent(machineCardButtons);
            optionButtonWindow.setComponent(optionContent);
            cardButtonWindow.setComponent(cardContent);
            machineSlotWindow.setComponent(machineSlots);
            term.addResizeListener(new TerminalResizeListener() {
                @Override
                public void onResized(Terminal terminal, TerminalSize terminalSize)
                {
                    cardButtonWindow.setPosition(new TerminalPosition(1, terminalSize.getRows() - 6));
                    machineSlotWindow.setPosition(new TerminalPosition(1, 4));
                    machineSlots.updateCards();
                }
            });
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run()
                {
                    machineSlotWindow.setPosition(new TerminalPosition(1, 4));
                    machineSlots.updateCards();
                }
            }, 100, 100);
            cardButtonWindow.setPosition(new TerminalPosition(1, screen.getTerminalSize().getRows() - 6));
            cardButtonWindow.setHints(Collections.singletonList(Window.Hint.FIXED_POSITION));
            machineSlotWindow.setPosition(new TerminalPosition(1, 4));
            machineSlotWindow.setHints(Collections.singletonList(Window.Hint.FIXED_POSITION));
            mainFrame.addWindow(cardButtonWindow);
            mainFrame.addWindow(machineSlotWindow);
            mainFrame.addWindowAndWait(optionButtonWindow);
            mainFrame.getScreen().refresh();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(term != null)
            {
                try
                {
                    term.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}

