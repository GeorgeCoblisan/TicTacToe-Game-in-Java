import javax.swing.*;
import java.util.Scanner;

public class SetGame {

    int rows, cols, computerTurn;

    public void setDimensiune() {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduceti dimensiunea tabelei: ");
        System.out.print("Introduceti numarul de randuri: ");
        rows = in.nextInt();
        System.out.print("Introduceti numarul de coloane: ");
        cols = in.nextInt();
        System.out.print("Doriti sa jucati cu calculatorul? 0 - NU, 1 - DA ");
        computerTurn = in.nextInt();
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public int getComputerTurn() {
        return computerTurn;
    }
}
