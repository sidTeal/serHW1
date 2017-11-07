package tisdale.project.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/******************************************************
***  Class Name: SellBookFrame
***  Class Author: Chris Tisdale 
******************************************************
*** Purpose of the class:
*** This class builds the SellBookFrame.
****************************************************** 
*** October 3, 2017
******************************************************
*** September 20:   Created classes:    StoreItem, Book, Movie, Painting.
*** September 26:   Created classes:    TisdaleProject3 and tested class functionality.
*** September 27:   Created classes:    InventoryGUI, GUIBuilder, GUIComboBoxActionHandler,
***                                     AddBookFrame, AddMovieFrame, AddPaintingFrame.
*** September 28:   Created classes:    SellBookFrame, SellMovieFrame, SellPaintingFrame,
***                                     DisplayBookFrame, DisplayMovieFrame, DisplayPaitingFrame.
*** October 2, 3:      Final comments and testing.
******************************************************
***  
*******************************************************/
public class SellBookFrame extends JFrame {

    JLabel sellLabel;
    static JComboBox bookList;

/*****************************************************
*** SellBookFrame Constructor
*** Author: Chris Tisdale
******************************************************
*** Purpose: Creates instance of SellBookFrame. Adds labels and combo box, as well as button action listener to the combobox.
******************************************************
*** Date: September 28
******************************************************/
    public SellBookFrame() {
        JPanel mainPanel = new JPanel();
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Sell Book");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());

        String[] bookTitles = new String[InventoryGUI.books.size() + 1];
        bookTitles[0] = "";

        for (int i = 0; i < InventoryGUI.books.size(); i++) {
            bookTitles[i + 1] = InventoryGUI.books.get(i).getTitle();
        }

        sellLabel = new JLabel("   Sell: ");

        bookList = new JComboBox(bookTitles);
        ComboBoxActionHandler lForComboBox = new ComboBoxActionHandler();
        bookList.addActionListener(lForComboBox);

        GUIBuilder.addComponent(mainPanel, sellLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, bookList, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(300, 400);
        this.setResizable(true);
    }
    
/******************************************************
***  Class Name: ComboBoxActionHandler
***  Class Author: Chris Tisdale 
******************************************************
*** Purpose of the class:
*** This class serves as an action handler for the combo box
****************************************************** 
*** October 3, 2017
******************************************************
*** September 20:   Created classes:    StoreItem, Book, Movie, Painting.
*** September 26:   Created classes:    TisdaleProject3 and tested class functionality.
*** September 27:   Created classes:    InventoryGUI, GUIBuilder, GUIComboBoxActionHandler,
***                                     AddBookFrame, AddMovieFrame, AddPaitingFrame.
*** September 28:   Created classes:    SellBookFrame, SellMovieFrame, SellPaintingFrame,
***                                     DisplayBookFrame, DisplayMovieFrame, DisplayPaitingFrame.
*** October 2:      Final comments and testing.
******************************************************
***  
*******************************************************/
    public class ComboBoxActionHandler implements ActionListener {
        
/*****************************************************
*** Method Name: actionPerformed
*** Author: Chris Tisdale
******************************************************
*** Purpose of the Method: removes chosen book from inventory and shows dialog pop up confirming sale
*** Method parameters: ActionEvent
*** Return value: void
******************************************************
*** Date: September 28
******************************************************/
        @Override
        public void actionPerformed(ActionEvent e) {

            String item = bookList.getSelectedItem().toString();
            if (item == "") {
                return;
            } else {
                for (int i = 0; i <= InventoryGUI.books.size(); i++) {
                    if (InventoryGUI.books.get(i).getTitle() == item) {
                        InventoryGUI.books.get(i).remove();
                        InventoryGUI.books.remove(i);
                        bookList.setSelectedItem("");

                        JFrame frame;
                        frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "Book sold.");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        dispose();
                    }
                }
            }

        }
    }

}
