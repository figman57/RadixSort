import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Ian Anderson
 * 4/5/19
 */

public class CardRendererText extends Panel {

    private CardStorage sortBox;
    private Panel slot1;
    private Panel slot2;
    private Panel slot3;
    private Panel slot4;
    private Panel slot5;
    private Panel slot6;
    private Panel slot7;
    private Panel slot8;
    private Panel unSort;
    private Button swap;
    public CardRendererText()
    {
        super(new LinearLayout(Direction.HORIZONTAL));
        sortBox = CardStorage.getInstance();
        slot1 = new Panel();
        slot2 = new Panel();
        slot3 = new Panel();
        slot4 = new Panel();
        slot5 = new Panel();
        slot6 = new Panel();
        slot7 = new Panel();
        slot8 = new Panel();
        unSort = new Panel();
        swap = new Button("Switch");
        swap.addListener(e -> {
            WindowBasedTextGUI gui = (WindowBasedTextGUI) getTextGUI();
            gui.cycleActiveWindow(true);
        });
        addComponent(slot1.withBorder(Borders.singleLine("Slot 1")));
        addComponent(slot2.withBorder(Borders.singleLine("Slot 2")));
        addComponent(slot3.withBorder(Borders.singleLine("Slot 3")));
        addComponent(slot4.withBorder(Borders.singleLine("Slot 4")));
        addComponent(slot5.withBorder(Borders.singleLine("Slot 5")));
        addComponent(slot6.withBorder(Borders.singleLine("Slot 6")));
        addComponent(slot7.withBorder(Borders.singleLine("Slot 7")));
        addComponent(slot8.withBorder(Borders.singleLine("Slot 8")));
        addComponent(unSort.withBorder(Borders.singleLine("Unsorted")));
        addComponent(swap);
        updateCards();
    }

    public void updateCards()
    {

        ArrayList<ArrayList<Label>> cardSlots = drawCards();
        slot1 = slot1.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(0).size(); i++)
        {
            slot1.addComponent(cardSlots.get(0).get(i));
        }
        slot2 = slot2.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(1).size(); i++)
        {
            slot2.addComponent(cardSlots.get(1).get(i));
        }
        slot3 = slot3.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(2).size(); i++)
        {
            slot3.addComponent(cardSlots.get(2).get(i));
        }
        slot4 = slot4.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(3).size(); i++)
        {
            slot4.addComponent(cardSlots.get(3).get(i));
        }
        slot5 = slot5.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(4).size(); i++)
        {
            slot5.addComponent(cardSlots.get(4).get(i));
        }
        slot6 = slot6.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(5).size(); i++)
        {
            slot6.addComponent(cardSlots.get(5).get(i));
        }
        slot7 = slot7.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(6).size(); i++)
        {
            slot7.addComponent(cardSlots.get(6).get(i));
        }
        slot8 = slot8.removeAllComponents();
        for(int i = 0; i < sortBox.getCardSlot(7).size(); i++)
        {
            slot8.addComponent(cardSlots.get(7).get(i));
        }
        unSort = unSort.removeAllComponents();
        for(int i = 0; i < sortBox.getUnsortedCards().size(); i++)
        {
            unSort.addComponent(cardSlots.get(8).get(i));
        }
    }
    public ArrayList<ArrayList<Label>> drawCards()
    {
        ArrayList<ArrayList<Label>> cardStacks = new ArrayList<>();
        if(!sortBox.getUnsortedCards().isEmpty() || !sortBox.getCardSlot(0).isEmpty()
                ||!sortBox.getCardSlot(1).isEmpty() || !sortBox.getCardSlot(2).isEmpty()
                ||!sortBox.getCardSlot(3).isEmpty() || !sortBox.getCardSlot(4).isEmpty()
                ||!sortBox.getCardSlot(5).isEmpty() || !sortBox.getCardSlot(6).isEmpty()
                ||!sortBox.getCardSlot(7).isEmpty())
        {
            for(int i = 0; i <= 8; i++)
            {
                cardStacks.add(new ArrayList<Label>());
            }

            for(int i = 0; i <= 8; i++)
            {
                int boxSize;
                if(i == 8)
                {
                    boxSize = sortBox.getUnsortedCards().size();
                }
                else
                {
                    boxSize = sortBox.getCardSlot(i).size();
                }
                for(int j = 0; j < boxSize; j++)
                {
                    Color currentCardColor;
                    if(i == 8)
                    {
                        currentCardColor = sortBox.getUnsortedCards().get(j).getColor();
                    }
                    else
                    {
                        currentCardColor = sortBox.getCardSlot(i).get(j).getColor();
                    }
                    cardStacks.get(i).add(new Label(""));
                    cardStacks.get(i).get(j).setForegroundColor(TextColor.Indexed.fromRGB(currentCardColor.getRed(), currentCardColor.getGreen(), currentCardColor.getBlue()));
                    cardStacks.get(i).get(j).setText("#########");
                }
            }
        }
        return cardStacks;
    }
}
