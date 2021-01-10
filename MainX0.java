import javax.swing.*;
import java.util.Scanner;

public class MainX0 {

    public static void main(String[] args) {

        int rows, cols, computerTurn;
        SetGame game  = new SetGame();
        game.setDimensiune();
        rows = game.getRows();
        cols = game.getCols();
        computerTurn = game.getComputerTurn();
        X0 x0 = new X0(rows, cols, computerTurn);
        JButton[][] b = new JButton[rows][cols];
        x0.setTable(b);
        x0.startGame();
    }
}
