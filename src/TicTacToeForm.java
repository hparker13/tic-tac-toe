import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeForm extends JFrame {
    private JPanel pnlMain;
    private JPanel pnlButtons;
    private JLabel lblTurn;
    private JLabel lblWins;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnClear;
    private JButton btnEnd;
    private ImageIcon ticTacToe;
    private Boolean xTurn = true;
    private Boolean winner;
    private int numTurns = 0;
    private int numXWins = 0;
    private int numOWins = 0;
    private int numTies = 0;
    private List<JButton> lstX = new ArrayList<>();
    private List<JButton> lstO = new ArrayList<>();
    private List<JButton> lstButtons = new ArrayList<>() {{
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        add(btn7);
        add(btn8);
        add(btn9);
    }};

    private List<List<JButton>> lstWins = new ArrayList<>();
    private List<JButton> lstWin1 = new ArrayList<>();
    private List<JButton> lstWin2 = new ArrayList<>();
    private List<JButton> lstWin3 = new ArrayList<>();
    private List<JButton> lstWin4 = new ArrayList<>();
    private List<JButton> lstWin5 = new ArrayList<>();
    private List<JButton> lstWin6 = new ArrayList<>();
    private List<JButton> lstWin7 = new ArrayList<>();
    private List<JButton> lstWin8 = new ArrayList<>();

    TicTacToeForm() {
        this.setContentPane(pnlMain);
        lblTurn.setBackground(new Color(0x3E0D56));
        lblTurn.setOpaque(true);
        lblTurn.setForeground(new Color(0xB624FF));
        lblTurn.setText("X's Turn");
        pnlButtons.setBackground(new Color(0x3E0D56));
        pnlButtons.setOpaque(true);
        lblWins.setText("X Wins: " + numXWins + "     O Wins: " + numOWins + "     Ties " + numTies);
        btnClear.setPreferredSize(new Dimension(120, 35));
        btnClear.setFocusable(false);
        btnEnd.setPreferredSize(new Dimension(120, 35));
        btnEnd.setFocusable(false);
        winner = false;

        lstWin1.add(btn1); lstWin1.add(btn2); lstWin1.add(btn3);
        lstWins.add(lstWin1);
        lstWin2.add(btn4); lstWin2.add(btn5); lstWin2.add(btn6);
        lstWins.add(lstWin2);
        lstWin3.add(btn7); lstWin3.add(btn8); lstWin3.add(btn9);
        lstWins.add(lstWin3);
        lstWin4.add(btn1); lstWin4.add(btn4); lstWin4.add(btn7);
        lstWins.add(lstWin4);
        lstWin5.add(btn2); lstWin5.add(btn5); lstWin5.add(btn8);
        lstWins.add(lstWin5);
        lstWin6.add(btn3); lstWin6.add(btn6); lstWin6.add(btn9);
        lstWins.add(lstWin6);
        lstWin7.add(btn1); lstWin7.add(btn5); lstWin7.add(btn9);
        lstWins.add(lstWin7);
        lstWin8.add(btn3); lstWin8.add(btn5); lstWin8.add(btn7);
        lstWins.add(lstWin8);

        for (JButton button : lstButtons) {
            button.setPreferredSize(new Dimension(150,150));
        }

        ticTacToe = new ImageIcon("src/tic-tac-toe.png");
        this.setIconImage(ticTacToe.getImage());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(750, 750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Tic-Tac-Toe");
        this.setVisible(true);


        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                setDisabledButtonsStyle();

                if (xTurn) {
                    button.setText("X");
                    lblTurn.setText("O's Turn");
                    lstX.add(button);
                } else {
                    button.setText("O");
                    lblTurn.setText("X's Turn");
                    lstO.add(button);
                }
                button.setEnabled(false);
                numTurns++;
                xTurn = !xTurn;

                checkForWinner(lstWins, lstX);
                checkForWinner(lstWins, lstO);

                lblWins.setText("X Wins: " + numXWins + "     O Wins: " + numOWins + "     Ties " + numTies);
            }
        };

        btn1.addActionListener(listener);
        btn2.addActionListener(listener);
        btn3.addActionListener(listener);
        btn4.addActionListener(listener);
        btn5.addActionListener(listener);
        btn6.addActionListener(listener);
        btn7.addActionListener(listener);
        btn8.addActionListener(listener);
        btn9.addActionListener(listener);


        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button : lstButtons) {
                    button.setText("");
                    button.setEnabled(true);
                    button.setBackground(Color.white);
                }
                numTurns = 0;
                lstX.clear();
                lstO.clear();
                xTurn = true;
                lblTurn.setText("X's Turn");

                if (btnClear.getText().equals("New Game")) {
                    btnClear.setText("Clear");
                }
                winner = false;
            }
        });

        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    void checkForWinner(List<List<JButton>> wins, List<JButton> listXO ) {
        int xWins = 0;
        for (List list : lstWins) {

            if (listXO.contains(list.get(0)) && listXO.contains(list.get(1)) && listXO.contains(list.get(2))) {
                if (xTurn) {
                    lblTurn.setText("O WINS!");
                    numOWins++;
                } else {
                    lblTurn.setText("X WINS!");
                    xWins++;
                }
                winner = true;

                for (JButton button : lstButtons) {
                    if (button.equals(list.get(0)) || button.equals(list.get(1)) || button.equals(list.get(2))) {
                        button.setBackground(Color.lightGray);
                    }
                }
            }
        }

        if (xWins >= 1) {
            numXWins++;
        }

        if (winner == true) {
            numTurns = 0;
            btnClear.setText("New Game");
            disableAllButtons();
        }

       if (numTurns == 9 && winner == false) {
            numTies++;
            lblTurn.setText("It's a Tie");
            numTurns = 0;
            btnClear.setText("New Game");
            disableAllButtons();
        }
    }


    void disableAllButtons() {
        for (JButton button : lstButtons) {
            button.setEnabled(false);
        }
    }

    void setDisabledButtonsStyle() {

        if (!xTurn) {
            UIManager.getDefaults().put("Button.disabledText", new ColorUIResource(new Color(0xB624FF)));
        } else {
            UIManager.getDefaults().put("Button.disabledText",new ColorUIResource(new Color(0x3E0D56)));
        }
    }


}
