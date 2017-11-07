package tisdale.project.pkg3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class TisdaleProject4 {

/******************************************************
***  Class Name: TisdaleProject4
***  Class Author: Chris Tisdale 
******************************************************
*** Purpose of the class:
*** This class simply initializes a StoreItem of each type and creates a new instance of 
*** the InventoryGUI, passing it the initial items.
****************************************************** 
*** October 3, 2017
******************************************************
*** September 20:   Created classes:    StoreItem, Book, Movie, Painting.
*** September 26:   Created classes:    TisdaleProject3 and tested class functionality.
*** September 27:   Created classes:    InventoryGUI, GUIBuilder, GUIComboBoxActionHandler,
***                                     AddBookFrame, AddMovieFrame, AddPaintingFrame.
*** September 28:   Created classes:    SellBookFrame, SellMovieFrame, SellPaintingFrame,
***                                     DisplayBookFrame, DisplayMovieFrame, DisplayPaitingFrame.
*** October 2:      Final comments and testing.
******************************************************
***  
*******************************************************/

    public static void main(String[] args) {

        
        ArrayList<Book> initBooks = new ArrayList<>();
        ArrayList<Movie> initMovies = new ArrayList<>();
        ArrayList<Painting> initPaintings = new ArrayList<>();
        
        initBooks.add(new Book("The Hobbit", "Tolkien", new Date(116, 3, 11), 3, 7, "Fantasy"));
        
        String actors[] = {"Schwarzenegger", "Biehn"};
        String actresses[] = {"Hamilton", "Motta"};
        initMovies.add(new Movie("The Terminator", "Cameron", new Date(115, 9, 22), 4, 8, actors, actresses));

        initPaintings.add(new Painting("Mountainscape", "Ross", new Date(116, 4, 5), 33, 66, 24, 12, "oil"));

        InventoryGUI GUI = new InventoryGUI(initBooks, initMovies, initPaintings);
        
        }
    
}
