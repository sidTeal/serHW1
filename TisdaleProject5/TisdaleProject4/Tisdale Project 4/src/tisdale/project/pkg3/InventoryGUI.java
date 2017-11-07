/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tisdale.project.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

/**
 * ****************************************************
 *** Class Name: InventoryGUI ** Class Author: Chris Tisdale
 * ***************************************************** ** Purpose of the
 * class: ** This class builds the main GUI for the project.
 * ***************************************************** ** October 3, 2017
 * ***************************************************** ** September 20:
 * Created classes: StoreItem, Book, Movie, Painting. ** September 26: Created
 * classes: TisdaleProject3 and tested class functionality. ** September 27:
 * Created classes: InventoryGUI, GUIBuilder, GUIComboBoxActionHandler, **
 * AddBookFrame, AddMovieFrame, AddPaintingFrame. ** September 28: Created
 * classes: SellBookFrame, SellMovieFrame, SellPaintingFrame, **
 * DisplayBookFrame, DisplayMovieFrame, DisplayPaitingFrame. ** October 2, 3:
 * Final comments and testing.
 * ***************************************************** **
 * *****************************************************
 */
public class InventoryGUI extends JFrame {

    String[] itemTypes = {"", "Book", "Movie", "Painting"};
    JLabel addLabel, sellLabel, displayLabel;
    static JComboBox addItemList, sellItemList, displayItemList;
    JButton serializeButton, deserializeButton;

    static ArrayList<Book> books;
    static ArrayList<Movie> movies;
    static ArrayList<Painting> paintings;

    /**
     * ***************************************************
     *** InventoryGUI Constructor ** Author: Chris Tisdale
     * ***************************************************** ** Purpose: Creates
     * instance of InventoryGUI, passing it arrayLists for initial StoreItems.
     * ** Adds labels and combo boxes, as well as combo box action listener to
     * the JFrame. ***************************************************** **
     * Date: September 20
     *
     * @param initBooks
     * @param initMovies
     * @param initPaintings ****************************************************
     */
    public InventoryGUI(ArrayList<Book> initBooks, ArrayList<Movie> initMovies, ArrayList<Painting> initPaintings) {
        books = initBooks;
        movies = initMovies;
        paintings = initPaintings;

        JPanel mainPanel = new JPanel();
        this.getContentPane().setBackground(Color.white);
        this.setTitle("Inventory");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.setLayout(new GridBagLayout());

        addLabel = new JLabel("   Add Inventory: ");
        sellLabel = new JLabel("   Sell Inventory: ");
        displayLabel = new JLabel("   Display Inventory: ");
        addItemList = new JComboBox(itemTypes);
        sellItemList = new JComboBox(itemTypes);
        displayItemList = new JComboBox(itemTypes);
        serializeButton = new JButton("Serialize");
        deserializeButton = new JButton("Deserialize");

        GUIComboBoxActionHandler lForComboBox = new GUIComboBoxActionHandler();
        addItemList.addActionListener(lForComboBox);
        sellItemList.addActionListener(lForComboBox);
        displayItemList.addActionListener(lForComboBox);

        // serialize item list to .ser file
        serializeButton.addActionListener(new ActionListener() {
            /**
             * ***************************************************
             *** Method Name: actionPerformed ** Author: Chris Tisdale
             * ***************************************************** ** Purpose
             * of the Method: handles logic for serializing items to file when
             * clicked ** Method parameters: none ** Return value: void
             * ***************************************************** ** Date:
             * October 30 ****************************************************
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userDirLocation = System.getProperty("user.dir");
                File userDir = new File(userDirLocation);
                JFileChooser fileChooser = new JFileChooser(userDir);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        ArrayList<StoreItem> items = new ArrayList<>();
                        items.clear();
                        books.forEach((b) -> {
                            items.add(b);
                        });
                        movies.forEach((m) -> {
                            items.add(m);
                        });
                        paintings.forEach((p) -> {
                            items.add(p);
                        });
                        Serialize.serialize(items, selectedFile);
//                    Book book = new Book(titleTextField.getText(), (Date)dateAcquiredSpin.getValue(),
//                            Integer.parseInt(purchasePriceTextField.getText()), Integer.parseInt(askingPriceTextField.getText()),
//                            authorTextField.getText(), genreTextField.getText());
//                    InventoryGUI.books.add(book);

                        JFrame frame;
                        frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "Items serialized to file.");
                        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        //dispose();
                    } catch (Exception ex) {
                        //JOptionPane.showMessageDialog(null, "Error: \n" + ex + "\nPlease correct this problem and try again.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } // END if approve option fileChooser
            }
        });

        // serialize item list to .ser file
        deserializeButton.addActionListener(new ActionListener() {
            /**
             * ***************************************************
             *** Method Name: actionPerformed ** Author: Chris Tisdale
             * ***************************************************** ** Purpose
             * of the Method: handles logic for serializing items to file when
             * clicked ** Method parameters: none ** Return value: void
             * ***************************************************** ** Date:
             * October 30 ****************************************************
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userDirLocation = System.getProperty("user.dir");
                File userDir = new File(userDirLocation);
                JFileChooser fileChooser = new JFileChooser(userDir);
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        JFrame frame;
                        frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "Items deserialized from file.");
                        //items.clear();
//                    books.forEach((b) -> {
//                        items.add(b);
//                    });
//                    movies.forEach((m) -> {
//                        items.add(m);
//                    });
//                    paintings.forEach((p) -> {
//                        items.add(p);
//                    });
                        ArrayList<StoreItem> items = Deserialize.deserialize(selectedFile);
//                    Book book = new Book(titleTextField.getText(), (Date)dateAcquiredSpin.getValue(),
//                            Integer.parseInt(purchasePriceTextField.getText()), Integer.parseInt(askingPriceTextField.getText()),
//                            authorTextField.getText(), genreTextField.getText());
//                    InventoryGUI.books.add(book);
                        for (Book b : books) {
                            b.remove();
                        }
                        books.clear();

                        for (Movie m : movies) {
                            m.remove();
                        }
                        movies.clear();

                        for (Painting p : paintings) {
                            p.remove();
                        }
                        paintings.clear();
                        //System.out.println(items.get(1).getClass().getSimpleName());
//                                    String type = items.get(i).getClass().getSimpleName();
//                if(type == "Book"){
//                    
//                }

                        for (int i = 0; i <= items.size(); i++) {
                            String type = items.get(i).getClass().getSimpleName();
                            if (null != type) {
                                switch (type) {
                                    case "Book":
                                        books.add((Book) items.get(i));
                                        Book.incrementBookCount();
                                        break;
                                    case "Movie":
                                        movies.add((Movie) items.get(i));
                                        Movie.incrementMovieCount();
                                        break;
                                    case "Painting":
                                        paintings.add((Painting) items.get(i));
                                        Painting.incrementPaintingCount();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } // END FOR all items
//                    JFrame frame;
//                    frame = new JFrame();
//                    JOptionPane.showMessageDialog(frame, "Items deserialized from file.");
                        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        //dispose();
                    } catch (Exception ex) {
                        //JOptionPane.showMessageDialog(null, "Error: \n" + ex + "\nPlease correct this problem and try again.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } // END if file chooser approve option

            } // END button actionPerformed
        });

        GUIBuilder.addComponent(mainPanel, addLabel, 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, sellLabel, 0, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, displayLabel, 0, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, addItemList, 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, sellItemList, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, displayItemList, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, deserializeButton, 0, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        GUIBuilder.addComponent(mainPanel, serializeButton, 1, 3, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(250, 400);
        this.setResizable(true);
    }

}
