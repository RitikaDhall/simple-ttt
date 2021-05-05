package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    Ritika Pankaj Dhall
    IU1941230032
    CSE-A
 */

public class Board extends JFrame {
    private final Container pane;
    private String currentPlayer;
    private final JButton[][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem quit;

    Color green = new Color(116, 237, 126);
    Color white = new Color(250, 250, 250);

    String X = "X";
    String O = "O";

    public Board() {
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentPlayer = X;
        board = new JButton[3][3];
        hasWinner = false;
        initializeBoard();
        initializeMenuBar();
        setVisible(true);
    }

    private void initializeMenuBar() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");

        newGame = new JMenuItem("New game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }            
        });

        menu.add(newGame);
        menu.add(quit);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void resetBoard() {
        currentPlayer = X;
        hasWinner = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setBorderPainted(true);
                board[i][j].setOpaque(false);
                board[i][j].setBackground(white);
                board[i][j].setText("");
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(button.getFont().deriveFont(64.0f));
                board[i][j] = button;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(((JButton)e.getSource()).getText().equals("") && !hasWinner) {
                            button.setText(currentPlayer);
                            hasWinner();
                            togglePlayer();
                        }
                    }                    
                });
                pane.add(button);
            }
        }
    }

    private void togglePlayer() {
        if (currentPlayer.equals(X)) {
            currentPlayer = O;
        } else {
            currentPlayer = X;
        }
    }

    private boolean checkWin(JButton[] seq) {
        String a = seq[0].getText();
        String b = seq[1].getText();
        String c = seq[2].getText();

        return currentPlayer.equals(a) && a.equals(c) && b.equals(c);
    }

    private void hasWinner() {
        String messageTitle = "GAME OVER";
        String winMessage = "Player " + currentPlayer + " wins!";
        String tieMessage = "It's a tie!";

        JButton[] r1 = new JButton[] {board[0][0], board[0][1], board[0][2]};
        JButton[] r2 = new JButton[] {board[1][0], board[1][1], board[1][2]};
        JButton[] r3 = new JButton[] {board[2][0], board[2][1], board[2][2]};
        JButton[] c1 = new JButton[] {board[0][0], board[1][0], board[2][0]};
        JButton[] c2 = new JButton[] {board[0][1], board[1][1], board[2][1]};
        JButton[] c3 = new JButton[] {board[0][2], board[1][2], board[2][2]};
        JButton[] d1 = new JButton[] {board[0][0], board[1][1], board[2][2]};
        JButton[] d2 = new JButton[] {board[0][2], board[1][1], board[2][0]};

        JButton[][] possibleWin = new JButton[][] {r1, r2, r3, c1, c2, c3, d1, d2};

        for (JButton[] option : possibleWin) {
           if (checkWin(option)) {
                for (JButton button : option) {
                    button.setBorderPainted(false);
                    button.setOpaque(true);
                    button.setBackground(green);
                }
                JOptionPane.showMessageDialog(pane, winMessage, messageTitle, JOptionPane.PLAIN_MESSAGE);
                hasWinner = true;      
           }
        }

        if (BoardFull() && !hasWinner) {
            JOptionPane.showMessageDialog(pane, tieMessage, messageTitle, JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean BoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
}