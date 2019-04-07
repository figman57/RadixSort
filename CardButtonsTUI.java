/**
 * Ian Anderson
 * 4/4/19
 */
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;

import javax.sound.sampled.*;
import java.util.ArrayList;

public class CardButtonsTUI extends Panel{

    private CardStorage sortBox;

    public CardButtonsTUI()
    {
        setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        sortBox = CardStorage.getInstance();
        Button slot1 = new Button("Slot 1");
        Button slot2 = new Button("Slot 2");
        Button slot3 = new Button("Slot 3");
        Button slot4 = new Button("Slot 4");
        Button slot5 = new Button("Slot 5");
        Button slot6 = new Button("Slot 6");
        Button slot7 = new Button("Slot 7");
        Button slot8 = new Button("Slot 8");
        Button unSort = new Button("Unsorted Cards");
        Button play1 = new Button("Play Sounds (Normal)");
        Button play2 = new Button("Play Sounds (Retro)");
        Button swap = new Button("Switch Menu");

        slot1.addListener(e -> slotAction(0));
        slot2.addListener(e -> slotAction(1));
        slot3.addListener(e -> slotAction(2));
        slot4.addListener(e -> slotAction(3));
        slot5.addListener(e -> slotAction(4));
        slot6.addListener(e -> slotAction(5));
        slot7.addListener(e -> slotAction(6));
        slot8.addListener(e -> slotAction(7));
        unSort.addListener(e -> slotAction(8));
        play1.addListener(e -> playSounds(0));
        play2.addListener(e -> playSounds(1));

        swap.addListener(e -> {
            WindowBasedTextGUI gui = (WindowBasedTextGUI) getTextGUI();
            gui.cycleActiveWindow(true);
        });
        addComponent(slot1);
        addComponent(slot2);
        addComponent(slot3);
        addComponent(slot4);
        addComponent(slot5);
        addComponent(slot6);
        addComponent(slot7);
        addComponent(slot8);
        addComponent(unSort);
        addComponent(play1);
        addComponent(play2);
        addComponent(swap);
    }
    private void playSounds(int mode)
    {
        final AudioFormat cardMixer = new AudioFormat(16000, 16, 1, true, true);
        ArrayList<Integer> cardSounds = new ArrayList<>();
        for(int i = 0; i < 9; i++)
        {
            ArrayList<Card> currentSlotCards;
            if(i == 8)
            {
                currentSlotCards = sortBox.getUnsortedCards();
            }
            else
            {
                currentSlotCards = sortBox.getCardSlot(i);
            }
            for(int j = 0; j < currentSlotCards.size(); j++)
            {
                cardSounds.add(currentSlotCards.get(j).getSound());
            }
        }
        try {
            SourceDataLine line = AudioSystem.getSourceDataLine(cardMixer);
            line.open(cardMixer);
            line.start();
            //play Frequency = 200 Hz for 1 seconds
            for(int i = 0; i < cardSounds.size(); i++)
            {
                byte[] currentWave = generateSineWave(cardSounds.get(i),1, mode);
                line.write(currentWave, 0, currentWave.length);
            }
            line.drain();
            line.close();
        } catch (Exception f) { }
    }

    private static byte[] generateSineWave(int frequency, int seconds, int mode) {
        // 16kHz
        byte[] sin = new byte[(seconds * 16000) / 16];
        double samplingInterval = (double) (16000 / frequency);
        for (int i = 0; i < sin.length; i++) {
            double angle = (2 * Math.PI * i) / samplingInterval;
            {
                if(mode == 0)
                {
                    sin[i] = (byte) (Math.sin(angle) * 127);
                }
                else if(mode == 1)
                {
                    sin[i] = (byte) (Math.signum((Math.sin(angle) * 127)) * 20);
                }
            }
        }
        return sin;
    }
    private void slotAction(int slot)
    {
        ListSelectDialogBuilder slotList = new ListSelectDialogBuilder();
        if(slot < 8)
        {
            slotList.setTitle("Slot " + (slot + 1) + " Cards");
        }
        else
        {
            slotList.setTitle("Unsorted Cards");
        }
        ArrayList<Card> theCurrentCardSlot;
        if(slot == 8)
        {
            theCurrentCardSlot = sortBox.getUnsortedCards();
        }
        else
        {
            theCurrentCardSlot = sortBox.getCardSlot(slot);
        }
        if(!theCurrentCardSlot.isEmpty())
        {
            for(int i = 0; i < theCurrentCardSlot.size(); i++)
            {
                slotList.addListItem(theCurrentCardSlot.get(i));
            }
        }
        DialogWindow showCards = slotList.build();
        WindowBasedTextGUI gui = (WindowBasedTextGUI) getTextGUI();
        showCards.showDialog(gui);
    }
}
