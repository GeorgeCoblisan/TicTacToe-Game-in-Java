import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;

public class X0 implements ActionListener{

int rows, cols, computerTurn = 0;
   X0(int rows, int cols, int computerTurn) {
       this.rows = rows;
       this.cols = cols;
       this.computerTurn = computerTurn;
    }

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel table_panel = new JPanel();
    JPanel computer = new JPanel();
    JLabel textfield = new JLabel();
    JButton compButton = new JButton("Computer Turn");
    JButton reset = new JButton("Reset");
    boolean player1_turn;

    JButton[][] table = new JButton[rows][cols];
    public void setTable(JButton[][] table) {
        this.table = table;
    }

    public void startGame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("CASTELLAR", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("X/0 Game");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 1400, 1400);

        table_panel.setLayout(new GridLayout(rows, cols));
        table_panel.setBackground(Color.white);

        computer.setLayout(new GridLayout(2, 1));
        compButton.setBackground(new Color(25, 25, 25));
        compButton.setForeground(new Color(25, 255, 0));
        reset.setBackground(new Color(25, 25, 25));
        reset.setForeground(new Color(25, 255, 0));
        computer.add(compButton);
        compButton.setFont(new Font("CASTELLAR", Font.BOLD, 25));
        compButton.addActionListener(this);
        computer.add(reset);
        reset.setFont(new Font("CASTELLAR", Font.BOLD, 25));
        reset.addActionListener(this);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                table[i][j] = new JButton();
                table_panel.add(table[i][j]);
                table[i][j].setFont(new Font("Consolas", Font.BOLD, 120));
                table[i][j].setBackground(Color.white);
                table[i][j].setFocusable(false);
                table[i][j].addActionListener(this);
            }
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(computer, BorderLayout.EAST);
        frame.add(table_panel);

        firstTurn();
    }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (computerTurn == 1) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {

                        if (e.getSource() == table[i][j] && player1_turn == true) {
                            if (table[i][j].getText() == "") {
                                table[i][j].setForeground(new Color(255, 128, 0));
                                table[i][j].setText("X");
                                player1_turn = false;
                                textfield.setText("O turn");
                                check();
                            }
                        } else if (e.getSource() == compButton && player1_turn == false) {
                            int ok = 0;
                            while (ok == 0) {
                                int ii = random.nextInt(rows);
                                int jj = random.nextInt(cols);
                                if (table[ii][jj].getText() == "") {
                                    table[ii][jj].removeActionListener(this);
                                    table[ii][jj].setForeground(new Color(0, 128, 255));
                                    table[ii][jj].setText("O");
                                    player1_turn = true;
                                    textfield.setText("X turn");
                                    ok = 1;
                                    check();
                                }
                            }
                        }
                    }
                }
            } else if (computerTurn == 0) {

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {

                        if (e.getSource() == table[i][j]) {
                            if (player1_turn) {
                                if (table[i][j].getText() == "") {
                                    table[i][j].setForeground(new Color(255, 128, 0));
                                    table[i][j].setText("X");
                                    player1_turn = false;
                                    textfield.setText("O turn");
                                    check();
                                }
                            } else {
                                if (table[i][j].getText() == "") {
                                    table[i][j].setForeground(new Color(0, 128, 255));
                                    table[i][j].setText("O");
                                    player1_turn = true;
                                    textfield.setText("X turn");
                                    check();
                                }
                            }

                        }
                    }
                }
            }

            if (e.getSource() == reset) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        table[i][j].setText("");
                        table[i][j].setEnabled(true);
                        table[i][j].setBackground(Color.white);
                    }
                }
                check = 0;
                firstTurn();
            }
    }

    public void firstTurn() {

       try {
            Thread.sleep(2000);
        }
       catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(random.nextInt(2)==0) {
            player1_turn=true;
            textfield.setText("X turn");
        }
        else {
            player1_turn=false;
            textfield.setText("O turn");
        }
    }

    int check = 0;

    public void check() {
        //check X conditions for each row
        int ok = 0, i_start = 0, j_start = 0, i_final = 0, j_final = 0;
        while(ok < rows) {
            j_start = 0;
            for (int i = 0; i < rows; i++) {
                i_start = i;
                for (int j = 0; j < cols; j++) {
                    if (table[i][j].getText() == "X") {
                        ok++;
                        i_final = i;
                        j_final = j;
                    }
                }
                if(ok < rows)
                    ok = 0;
                else {
                    xWins(i_start, j_start, i_final, j_final);
                    check--;
                    break;
                }
            }
            if(ok < rows)
                ok = rows;
        }

        //check X conditions for each column
        ok = 0;
        i_start = 0;
        j_start = 0;
        i_final = 0;
        j_final = 0;
        while(ok < rows) {
            i_start = 0;
            for (int j = 0; j < cols; j++) {
                j_start = j;
                for (int i = 0; i < rows; i++) {
                    if (table[i][j].getText() == "X") {
                        ok++;
                        i_final = i;
                        j_final = j;
                    }
                }
                if(ok < rows)
                    ok = 0;
                else {
                    xWins(i_start, j_start, i_final, j_final);
                    check--;
                    break;
                }
            }
            if(ok < rows)
                ok = rows;
        }

        //check X conditions for main diagonal
        ok = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(i == j && table[i][j].getText() == "X")
                    ok++;
            }
        }
        if(ok == rows) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == j && table[i][j].getText() == "X")
                        table[i][j].setBackground(Color.GREEN);
                }
            }
            check--;
            textfield.setText("X wins");
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    table[i][j].setEnabled(false);
                }
            }
        }

        //check X conditions for secondary diagonal
        ok = 0;
        for(int i = 0; i < rows; i++) {
            if(table[i][rows - i - 1].getText() == "X")
                ok++;
        }
        if(ok == rows) {
            for(int i = 0; i < rows; i++) {
                if(table[i][rows - i - 1].getText() == "X")
                    table[i][rows - i - 1].setBackground(Color.GREEN);
            }
            check--;
            textfield.setText("X wins");
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    table[i][j].setEnabled(false);
                }
            }
        }

        //check O conditions for each row
        ok = 0;
        i_start = 0;
        j_start = 0;
        i_final = 0;
        j_final = 0;
        while(ok < rows) {
            j_start = 0;
            for (int i = 0; i < rows; i++) {
                i_start = i;
                for (int j = 0; j < cols; j++) {
                    if (table[i][j].getText() == "O") {
                        ok++;
                        i_final = i;
                        j_final = j;
                    }
                }
                if(ok < rows)
                    ok = 0;
                else {
                    oWins(i_start, j_start, i_final, j_final);
                    check--;
                    break;
                }
            }
            if(ok < rows)
                ok = rows;
        }

        //check O conditions for each column
        ok = 0;
        i_start = 0;
        j_start = 0;
        i_final = 0;
        j_final = 0;
        while(ok < rows) {
            i_start = 0;
            for (int j = 0; j < cols; j++) {
                j_start = j;
                for (int i = 0; i < rows; i++) {
                    if (table[i][j].getText() == "O") {
                        ok++;
                        i_final = i;
                        j_final = j;
                    }
                }
                if(ok < rows)
                    ok = 0;
                else {
                    oWins(i_start, j_start, i_final, j_final);
                    check--;
                    break;
                }
            }
            if(ok < rows)
                ok = rows;
        }

        //check O conditions for main diagonal
        ok = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(i == j && table[i][j].getText() == "O")
                    ok++;
            }
        }
        if(ok == rows) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == j && table[i][j].getText() == "O")
                        table[i][j].setBackground(Color.GREEN);
                }
            }
            check--;
            textfield.setText("O wins");
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    table[i][j].setEnabled(false);
                }
            }
        }

        //check O conditions for secondary diagonal
        ok = 0;
        for(int i = 0; i < rows; i++) {
            if(table[i][rows - i - 1].getText() == "O")
                ok++;
        }
        if(ok == rows) {
            for(int i = 0; i < rows; i++) {
                if(table[i][rows - i - 1].getText() == "O")
                    table[i][rows - i - 1].setBackground(Color.GREEN);
            }
            check--;
            textfield.setText("O wins");
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) {
                    table[i][j].setEnabled(false);
                }
            }
        }

        check++;
        nobodyWins(check);
    }

    public void xWins(int i_start, int j_start, int i_final, int j_final) {
        for(int i = i_start; i < i_final + 1; i++) {
            for(int j = j_start; j < j_final + 1; j++) {
                table[i][j].setBackground(Color.GREEN);
            }
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                table[i][j].setEnabled(false);
            }
        }
        textfield.setText("X wins");
    }
    public void oWins(int i_start, int j_start, int i_final, int j_final) {
        for(int i = i_start; i < i_final + 1; i++) {
            for(int j = j_start; j < j_final + 1; j++) {
                table[i][j].setBackground(Color.GREEN);
            }
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                table[i][j].setEnabled(false);
            }
        }
        textfield.setText("O wins");
    }

    public void nobodyWins(int winner) {
        if(winner == rows * cols) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    table[i][j].setBackground(Color.RED);
                }
            }
            textfield.setText("Nobody wins");
        }
    }

}
