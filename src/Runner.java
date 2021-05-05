package src;

import javax.swing.SwingUtilities;

/*
    Ritika Pankaj Dhall
    IU1941230032
    CSE-A
 */

public class Runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {                
                new Board();
            }
        });
    }
}