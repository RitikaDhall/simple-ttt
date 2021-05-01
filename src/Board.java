package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame {
    private Container pane;
    private String currentPlayer;
    private JButton[][] board;
    private boolean hasWinner;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem newGame;
    private JMenuItem quit;

    String X = "X";
    String O = "O";

    public Board() {
        super();
        pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
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
                board[i][j].setText("");
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setFont(btn.getFont().deriveFont(64.0f));
                board[i][j] = btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(((JButton)e.getSource()).getText().equals("") && hasWinner == false) {
                            btn.setText(currentPlayer);
                            hasWinner();
                            togglePlayer();
                        }
                    }                    
                });

                pane.add(btn);
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

    private void hasWinner() {
        // To do: complete all winning combos
        String messageTitle = "GAME OVER";
        String message = "Player " + currentPlayer + " wins!";
        // 1st col
        if(board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer) && board[2][0].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, message, messageTitle, JOptionPane.PLAIN_MESSAGE);
            hasWinner = true;
        }
        // 2nd col
        else if(board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, message, messageTitle, JOptionPane.PLAIN_MESSAGE);
            hasWinner = true;
        }
        // 3rd col
        else if(board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer) && board[2][2].getText().equals(currentPlayer)) {
            JOptionPane.showMessageDialog(null, message, messageTitle, JOptionPane.PLAIN_MESSAGE);
            hasWinner = true;
        }
    }
}