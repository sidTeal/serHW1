package tisdale.project.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/******************************************************
***  Class Name: AddPaintingFrame
***  Class Author: Chris Tisdale 
******************************************************
*** Purpose of the class:
*** This class builds the AddPaintingFrame.
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
public class AddPaintingFrame extends JFrame{
    
    JLabel titleLabel, dateAcquiredLabel, purchasePriceLabel, askingPriceLabel, heightLabel, widthLabel, painterLabel, mediaLabel;
    JTextField titleTextField, purchasePriceTextField, askingPriceTextField, heightTextField, widthTextField, painterTextField;
    JSpinner dateAcquiredSpin;
    JButton addButton;
    JComboBox mediaList;
    String[] media = {"", "oil", "acrylic", "watercolor", "ink", "charcoal", "airbrush"};

/*****************************************************
*** AddPaintingFrame Constructor
*** Author: Chris Tisdale
******************************************************
*** Purpose: Creates instance of AddPaintingFrame. Adds labels and button, as well as button action listener to the JFrame.
******************************************************
*** Date: September 27
******************************************************/    
    public AddPaintingFrame(){
        JPanel mainPanel = new JPanel();
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Add Painting");
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());
        
        Date todaysDate = new Date(); // get todays date
        
        titleLabel = new JLabel("   Title: ");
        dateAcquiredLabel = new JLabel("   Date Acquired: ");
        purchasePriceLabel = new JLabel("   Purchase Price: ");
        askingPriceLabel = new JLabel("   Asking Price: ");
        heightLabel = new JLabel("   Height (inches): ");
        widthLabel = new JLabel("   Width (inches): ");
        painterLabel = new JLabel("   Painter: ");
        mediaLabel = new JLabel("   Media: ");
        mediaList = new JComboBox(media);
        
        titleTextField = new JTextField(10);
        dateAcquiredSpin = new JSpinner(new SpinnerDateModel(todaysDate, null, null, Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateAcquiredSpin, "dd/MM/yy"); // set date editor format
        dateAcquiredSpin.setEditor(dateEditor); // set editor to chosen format
        purchasePriceTextField = new JTextField(10);
        askingPriceTextField = new JTextField(10);
        heightTextField = new JTextField(10);
        widthTextField = new JTextField(10);
        painterTextField = new JTextField(10);
        
        addButton = new JButton("Add");
        
        // create new painting populated from fields on frame, error dialog pop up if invalid entry
        addButton.addActionListener(new ActionListener() {
/*****************************************************
*** Method Name: actionPerformed
*** Author: Chris Tisdale
******************************************************
*** Purpose of the Method: handles logic for adding painting when clicked
*** Method parameters: none
*** Return value: void
******************************************************
*** Date: September 27
******************************************************/
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Painting painting = new Painting(titleTextField.getText(), painterTextField.getText(), (Date)dateAcquiredSpin.getValue(),
                            Integer.parseInt(purchasePriceTextField.getText()), Integer.parseInt(askingPriceTextField.getText()),
                            Integer.parseInt(heightTextField.getText()), Integer.parseInt(widthTextField.getText()),
                            mediaList.getSelectedItem().toString());
                    InventoryGUI.paintings.add(painting);

                    JFrame frame;
                    frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Painting added.");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: \n" + ex + "\nMissing or invalid entry.\nPlease correct this problem and try again.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: \n" + ex + "\nPlease correct this problem and try again.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        GUIBuilder.addComponent(mainPanel, titleLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, dateAcquiredLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, purchasePriceLabel, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, askingPriceLabel, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, heightLabel, 0, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, widthLabel, 0, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, painterLabel, 0, 6, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, mediaLabel, 0, 7, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        
        GUIBuilder.addComponent(mainPanel, titleTextField, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, dateAcquiredSpin, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, purchasePriceTextField, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, askingPriceTextField, 1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, heightTextField, 1, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, widthTextField, 1, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, painterTextField, 1, 6, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, mediaList, 1, 7, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        
        GUIBuilder.addComponent(mainPanel, addButton, 1, 8, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        
        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(350, 400);
        this.setResizable(true);
    }
    
}
