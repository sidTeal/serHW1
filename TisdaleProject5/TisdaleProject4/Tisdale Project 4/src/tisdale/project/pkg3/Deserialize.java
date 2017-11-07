/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tisdale.project.pkg3;

import java.io.*;
import java.util.ArrayList;

public class Deserialize {

    static final int MAX_SIZE = 100;
    static ArrayList<StoreItem> items = new ArrayList<>();

    static public ArrayList deserialize(File inputFile) {
        try {

            try ( //FileInputStream fileIn = new FileInputStream("storeItems.ser");
                    FileInputStream fileIn = new FileInputStream(inputFile)) {
                ObjectInputStream in = new ObjectInputStream(fileIn);
                for (int i = 0; i < MAX_SIZE; i++) {
                    StoreItem item = (StoreItem) in.readObject();
                    items.add(item);
                }
                in.close();
            }

        } catch (EOFException e) {
            //e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("StoreItem class not found");
            c.printStackTrace();
        }
        return items;
    }
}
