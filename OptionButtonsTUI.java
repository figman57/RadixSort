/**
 * Ian Anderson
 * 4/4/19
 */
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.FileDialog;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class OptionButtonsTUI extends Panel{

    public OptionButtonsTUI()
    {
        setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        Button place = new Button("Add Card");
        Button remove = new Button("Remove Card");
        Button clear = new Button("Clear Cards");
        Button impText = new Button("Import Cards");
        Button random1000 = new Button("Generate 100");
        Button select = new Button("Color Picker");
        Button randomize = new Button("Randomize");
        Button sort1 = new Button("Sort by Name");
        Button sort2 = new Button("Sort by Category");
        Button sort3 = new Button("Sort by Color");
        Button sort4 = new Button("Sort by Sound");
        Button style = new Button("Change Style");
        Button asciiTable = new Button("ASCII Table");
        Button about = new Button("About...");
        Button exit = new Button("Close");
        Button swap = new Button("Switch Menu");
        CardStorage sortBox = CardStorage.getInstance();
        RadixSort radSort = new RadixSort();

        about.addListener(e ->
        {
            MessageDialog.showMessageDialog((WindowBasedTextGUI) getTextGUI(),
                    "About Radix Sort TUI",
                    "Radix Sort TUI 1.0" +
                            "\nBy Ian Anderson" +
                            "\nCurrent Memory Usage: " + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024) + " KB" +
                            "\nBuilt on " + LocalDate.now());
        });
        asciiTable.addListener(e ->
        {
            MessageDialog.showMessageDialog((WindowBasedTextGUI) getTextGUI(),
                    "ASCII Table",
                    "Dec  Char                           Dec  Char     Dec  Char     Dec  Char" +
                    "\n---------                           ---------     ---------     ----------" +
                    "\n  0  NUL (null)                      32  SPACE     64  @         96  `" +
                    "\n  1  SOH (start of heading)          33  !         65  A         97  a" +
                    "\n  2  STX (start of text)             34  \"         66  B         98  b" +
                    "\n  3  ETX (end of text)               35  #         67  C         99  c" +
                    "\n  4  EOT (end of transmission)       36  $         68  D        100  d" +
                    "\n  5  ENQ (enquiry)                   37  %         69  E        101  e" +
                    "\n  6  ACK (acknowledge)               38  &         70  F        102  f" +
                    "\n  7  BEL (bell)                      39  '         71  G        103  g" +
                    "\n  8  BS  (backspace)                 40  (         72  H        104  h" +
                    "\n  9  TAB (horizontal tab)            41  )         73  I        105  i" +
                    "\n 10  LF  (NL line feed, new line)    42  *         74  J        106  j" +
                    "\n 11  VT  (vertical tab)              43  +         75  K        107  k" +
                    "\n 12  FF  (NP form feed, new page)    44  ,         76  L        108  l" +
                    "\n 13  CR  (carriage return)           45  -         77  M        109  m" +
                    "\n 14  SO  (shift out)                 46  .         78  N        110  n" +
                    "\n 15  SI  (shift in)                  47  /         79  O        111  o" +
                    "\n 16  DLE (data link escape)          48  0         80  P        112  p" +
                    "\n 17  DC1 (device control 1)          49  1         81  Q        113  q" +
                    "\n 18  DC2 (device control 2)          50  2         82  R        114  r" +
                    "\n 19  DC3 (device control 3)          51  3         83  S        115  s" +
                    "\n 20  DC4 (device control 4)          52  4         84  T        116  t" +
                    "\n 21  NAK (negative acknowledge)      53  5         85  U        117  u" +
                    "\n 22  SYN (synchronous idle)          54  6         86  V        118  v" +
                    "\n 23  ETB (end of trans. block)       55  7         87  W        119  w" +
                    "\n 24  CAN (cancel)                    56  8         88  X        120  x" +
                    "\n 25  EM  (end of medium)             57  9         89  Y        121  y" +
                    "\n 26  SUB (substitute)                58  :         90  Z        122  z" +
                    "\n 27  ESC (escape)                    59  ;         91  [        123  {" +
                    "\n 28  FS  (file separator)            60  <         92  \\        124  |" +
                    "\n 29  GS  (group separator)           61  =         93  ]        125  }" +
                    "\n 30  RS  (record separator)          62  >         94  ^        126  ~" +
                    "\n 31  US  (unit separator)            63  ?         95  _        127  DEL");
        });
        exit.addListener(e -> {
            Window win = (Window) getBasePane();
            win.close();
        });
        swap.addListener(e -> {
            WindowBasedTextGUI gui = (WindowBasedTextGUI) getTextGUI();
            gui.cycleActiveWindow(true);
        });
        random1000.addListener(e ->
        {
            Random rand = new Random();
            for(int i = 0; i < 100; i++)
            {
                String name = "";
                String type = "";
                String desc = "";
                char a = (char) (rand.nextInt(57) + 65);
                char b = (char) (rand.nextInt(57) + 65);
                char c = (char) (rand.nextInt(57) + 65);
                char d = (char) (rand.nextInt(57) + 65);
                char m = (char) (rand.nextInt(57) + 65);
                char f = (char) (rand.nextInt(57) + 65);
                char g = (char) (rand.nextInt(57) + 65);
                char h = (char) (rand.nextInt(57) + 65);
                char j = (char) (rand.nextInt(57) + 65);
                //System.out.println(a);
                name += Character.toString(a) + b + c;
                type += Character.toString(d) + m + f;
                desc += Character.toString(g) + h + j;
                sortBox.addCardToUnsorted(new Card(name, type, desc, new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)), 1 + rand.nextInt(1999)));
            }
        });
        sort1.addListener(e ->
        {
            ArrayList<String> allTitles = new ArrayList<>();
            ArrayList<Card> allCards = new ArrayList<>();
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < sortBox.getCardSlot(i).size(); j++)
                {
                    allTitles.add(sortBox.getCardSlot(i).get(j).getName());
                    allCards.add(sortBox.getCardSlot(i).get(j));
                }
            }
            for(int i = 0; i < sortBox.getUnsortedCards().size(); i++)
            {
                allTitles.add(sortBox.getUnsortedCards().get(i).getName());
                allCards.add(sortBox.getUnsortedCards().get(i));
            }
            if(!allTitles.isEmpty())
            {
                sortBox.clearAllSlots();
                String[] arrayTitles = new String[allTitles.size()];
                String[] sortedTitles = radSort.sortStringAlt(allTitles.toArray(arrayTitles));
                ArrayList<String> sortedArrayList = new ArrayList<>(Arrays.asList(sortedTitles));
                int amountInEachBin = sortedTitles.length / 8;
                int currentTitlePlace = 0;
                for(int i = 0; i < 8; i++)
                {
                    for(int j = 0; j < amountInEachBin; j++)
                    {
                        String currentTitle = sortedArrayList.get(currentTitlePlace);
                        currentTitlePlace++;
                        int k = 0;
                        while (!currentTitle.equals(allCards.get(k).getName()))
                        {
                            k++;
                        }
                        sortBox.addCardToSlot(allCards.get(k), i);
                        allCards.remove(k);
                    }
                }
            }
        });
        sort2.addListener(e ->
        {
            ArrayList<String> allCategories = new ArrayList<>();
            ArrayList<Card> allCards = new ArrayList<>();
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < sortBox.getCardSlot(i).size(); j++)
                {
                    allCategories.add(sortBox.getCardSlot(i).get(j).getType());
                    allCards.add(sortBox.getCardSlot(i).get(j));
                }
            }
            for(int i = 0; i < sortBox.getUnsortedCards().size(); i++)
            {
                allCategories.add(sortBox.getUnsortedCards().get(i).getType());
                allCards.add(sortBox.getUnsortedCards().get(i));
            }
            if(!allCategories.isEmpty())
            {
                sortBox.clearAllSlots();
                String[] arrayTitles = new String[allCategories.size()];
                String[] sortedTitles = radSort.sortStringAlt(allCategories.toArray(arrayTitles));
                ArrayList<String> sortedArrayList = new ArrayList<>(Arrays.asList(sortedTitles));
                int amountInEachBin = sortedTitles.length / 8;
                int currentTitlePlace = 0;
                for(int i = 0; i < 8; i++)
                {
                    for(int j = 0; j < amountInEachBin; j++)
                    {
                        String currentTitle = sortedArrayList.get(currentTitlePlace);
                        currentTitlePlace++;
                        int k = 0;
                        while (!currentTitle.equals(allCards.get(k).getType()))
                        {
                            k++;
                        }
                        sortBox.addCardToSlot(allCards.get(k), i);
                        allCards.remove(k);
                    }
                }
            }
        });
        sort3.addListener(e ->
        {
            ArrayList<Integer> allColorInts = new ArrayList<>();
            ArrayList<Card> allCards = new ArrayList<>();
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < sortBox.getCardSlot(i).size(); j++)
                {
                    allColorInts.add(sortBox.getCardSlot(i).get(j).getHSBColorSingle());
                    allCards.add(sortBox.getCardSlot(i).get(j));
                }
            }
            for(int i = 0; i < sortBox.getUnsortedCards().size(); i++)
            {
                allColorInts.add(sortBox.getUnsortedCards().get(i).getHSBColorSingle());
                allCards.add(sortBox.getUnsortedCards().get(i));
            }
            if(!allColorInts.isEmpty())
            {
                sortBox.clearAllSlots();
                int[] intermediate = new int[allColorInts.size()];
                for(int i = 0; i < intermediate.length; i++)
                {
                    intermediate[i] = allColorInts.get(i);
                }
                int[] sortedTitles = radSort.sortInt(intermediate);
                ArrayList<ArrayList<Integer>> colorSets = new ArrayList<>();
                for(int i = 0; i < sortedTitles.length; i++)
                {
                    colorSets.add(new ArrayList<>());
                }
                String[] numStrings = new String[sortedTitles.length];
                for(int i = 0; i < numStrings.length; i++)
                {
                    numStrings[i] = Integer.toString(sortedTitles[i]);
                    while (numStrings[i].length() < 9)
                    {
                        numStrings[i] = "0" + numStrings[i];
                    }
                }
                for(int i = 0; i < allColorInts.size(); i++)
                {
                    for (int j = 0; j < 3; j++)
                    {
                        colorSets.get(i).add(Integer.parseInt(numStrings[i].substring(0, 3)));
                        numStrings[i] = numStrings[i].substring(3);
                    }
                }
                int amountInEachBin = sortedTitles.length / 8;
                int currentTitlePlace = 0;
                for(int i = 0; i < 8; i++)
                {
                    for(int j = 0; j < amountInEachBin; j++)
                    {
                        ArrayList<Integer> currentTitle = colorSets.get(currentTitlePlace);
                        currentTitlePlace++;
                        int k = 0;
                        while (!currentTitle.equals(allCards.get(k).getHSBColorArray()))
                        {
                            k++;
                        }
                        sortBox.addCardToSlot(allCards.get(k), i);
                        allCards.remove(k);
                    }
                }
            }
        });
        sort4.addListener(e ->
        {
            ArrayList<Integer> allFrequencies = new ArrayList<>();
            ArrayList<Card> allCards = new ArrayList<>();
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < sortBox.getCardSlot(i).size(); j++)
                {
                    allFrequencies.add(sortBox.getCardSlot(i).get(j).getSound());
                    allCards.add(sortBox.getCardSlot(i).get(j));
                }
            }
            for(int i = 0; i < sortBox.getUnsortedCards().size(); i++)
            {
                allFrequencies.add(sortBox.getUnsortedCards().get(i).getSound());
                allCards.add(sortBox.getUnsortedCards().get(i));
            }
            if(!allFrequencies.isEmpty())
            {
                sortBox.clearAllSlots();
                int[] intermediate = new int[allFrequencies.size()];
                for(int i = 0; i < intermediate.length; i++)
                {
                    intermediate[i] = allFrequencies.get(i);
                }
                int[] sortedTitles = radSort.sortInt(intermediate);
                ArrayList<Integer> soundArray = new ArrayList<>();
                for(Integer i: sortedTitles)
                {
                    soundArray.add(i);
                }
                int amountInEachBin = sortedTitles.length / 8;
                int currentTitlePlace = 0;
                for(int i = 0; i < 8; i++)
                {
                    for(int j = 0; j < amountInEachBin; j++)
                    {
                        int currentTitle = soundArray.get(currentTitlePlace);
                        currentTitlePlace++;
                        int k = 0;
                        while (currentTitle != allCards.get(k).getSound())
                        {
                            k++;
                        }
                        sortBox.addCardToSlot(allCards.get(k), i);
                        allCards.remove(k);
                    }
                }
            }
        });
        randomize.addListener(e ->
        {
            Random rand = new Random();
            ArrayList<Card> allCards = new ArrayList<>();
            for(int i = 0; i < 8; i++)
            {
                allCards.addAll(sortBox.getCardSlot(i));
            }
            allCards.addAll(sortBox.getUnsortedCards());
            sortBox.clearAllSlots();
            while (!allCards.isEmpty())
            {
                int slot = rand.nextInt(8);
                int card = rand.nextInt(allCards.size());
                sortBox.addCardToSlot(allCards.get(card), slot);
                allCards.remove(card);
            }
        });
        impText.addListener(e ->
        {
            FileDialogBuilder getFileMaker = new FileDialogBuilder();
            getFileMaker.setTitle("Card File Importer");
            getFileMaker.setDescription("Select a file:");
            getFileMaker.setActionLabel("Open");
            File importCardFile = getFileMaker.getSelectedFile();
            FileDialog getFile = getFileMaker.build();
            WindowBasedTextGUI gui = (WindowBasedTextGUI) getTextGUI();
            getFile.showDialog(gui);
                    try {
                        Scanner scan = new Scanner(importCardFile);
                        String name;
                        String desc;
                        String type;
                        int r;
                        int g;
                        int b;
                        int sound;
                        while (scan.hasNextLine())
                        {
                            name = scan.nextLine();
                            type = scan.nextLine();
                            desc = scan.nextLine();
                            r = scan.nextInt();
                            g = scan.nextInt();
                            b = scan.nextInt();
                            sound = scan.nextInt();
                            sortBox.addCardToUnsorted(new Card(name, type, desc, new Color(r, g, b), sound));
                            scan.nextLine();
                        }
                    }
                    catch (Exception Ignored)
                    {

                    }
        });
        clear.addListener(e -> sortBox.clearAllSlots());
        addComponent(place);
        addComponent(remove);
        addComponent(clear);
        addComponent(impText);
        addComponent(random1000);
        addComponent(select);
        addComponent(randomize);
        addComponent(sort1);
        addComponent(sort2);
        addComponent(sort3);
        addComponent(sort4);
        addComponent(style);
        addComponent(asciiTable);
        addComponent(about);
        addComponent(swap);
        addComponent(exit);
    }
}
