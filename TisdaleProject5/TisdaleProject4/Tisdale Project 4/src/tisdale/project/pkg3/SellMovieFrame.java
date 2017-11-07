package tisdale.project.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/******************************************************
***  Class Name: SellMovieFrame
***  Class Author: Chris Tisdale 
******************************************************
*** Purpose of the class:
*** This class builds the SellMovieFrame.
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
public class SellMovieFrame extends JFrame {

    JLabel sellLabel;
    static JComboBox movieList;

/*****************************************************
*** SellMovieFrame Constructor
*** Author: Chris Tisdale
******************************************************
*** Purpose: Creates instance of SellMovieFrame. Adds labels and combo box, as well as button action listener to the combobox.
******************************************************
*** Date: September 28
******************************************************/
    public SellMovieFrame(ArrayList<Movie> movies) {
        JPanel mainPanel = new JPanel();
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Sell Movie");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());
        ArrayList<Movie> currentMovies = movies;
        String[] movieTitles = new String[currentMovies.size() + 1];
        movieTitles[0] = "";

        for (int i = 0; i < currentMovies.size(); i++) {
            movieTitles[i + 1] = currentMovies.get(i).getTitle();
        }

        sellLabel = new JLabel("   Sell: ");
        movieList = new JComboBox(movieTitles);
        ComboBoxActionHandler lForComboBox = new ComboBoxActionHandler();
        movieList.addActionListener(lForComboBox);

        GUIBuilder.addComponent(mainPanel, sellLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, movieList, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

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
*** Purpose of the Method: removes chosen movie from inventory and shows dialog pop up confirming sale
*** Method parameters: ActionEvent
*** Return value: void
******************************************************
*** Date: September 28
******************************************************/
        @Override
        public void actionPerformed(ActionEvent e) {

            String item = movieList.getSelectedItem().toString();
            if (item == "") {
                return;
            } else {
                for (int i = 0; i <= InventoryGUI.movies.size(); i++) {
                    if (InventoryGUI.movies.get(i).getTitle() == item) {
                        InventoryGUI.movies.get(i).remove();
                        InventoryGUI.movies.remove(i);
                        movieList.setSelectedItem("");

                        JFrame frame;
                        frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "Movie sold.");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        dispose();
                    }
                }
            }

        }
    }
}
