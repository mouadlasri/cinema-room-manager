package cinema;

import java.util.Scanner;

public class Grid {
    private final int ROWS;
    private final int COLS;
    private char[][] grid;
    private int currentIncome;
    private int totalIncome;

    public Grid(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        this.ROWS = rows;
        this.COLS = cols;
        this.grid = new char[ROWS][COLS];
        this.buildGrid();
        this.calculateTotalIncome();
    }

    private void buildGrid() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = 'S';
            }
        }
    }

    private void updateIncome(int ticketPrice) {
        this.currentIncome += ticketPrice;
    }

    private double percentagePurchasedTickets() {
        int totalSeats = ROWS * COLS;
        int purchasedTickets = calculatePurchasedTickets();
        return (double) purchasedTickets / totalSeats * 100.00;
    }

    private int calculatePurchasedTickets() {
        int purchasedTickets = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS ; j++) {
                if (grid[i][j] == 'B') {
                    purchasedTickets++;
                }
            }
        }

        return purchasedTickets;
    }

    private void calculateTotalIncome() {
        int totalIncome = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                totalIncome += calculateSeatPrice(i+1, j+1);
            }
        }

        this.totalIncome = totalIncome;
    }

    public void printGrid() {
        System.out.println("Cinema:");
        for (int i = 0; i < ROWS + 1; i++) {
            for (int j = 0; j < COLS + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.printf("%d ", j);
                } else if (j == 0) {
                    System.out.printf("%d ", i);
                } else {
                    System.out.print(grid[i - 1][j - 1] + " ");
                }
            }
            System.out.println();
        }
    }

    public int calculateSeatPrice(int row, int col) {
        if ((ROWS * COLS) <= 60) {
            return 10;
        } else {
            int frontHalfRows = ROWS / 2;  // 9 / 2 = 4.5, which is truncated to 4
            return (row <= frontHalfRows ? 10 : 8);
        }
    }

    public boolean reserveSeat(int row, int col) {
        // check if seat is available or not
        if (isSeatAvailable(row, col)) {
            grid[row - 1][col - 1] = 'B';
            updateIncome(calculateSeatPrice(row, col));
            return true;
        }
        return false;
    }

    public boolean isSeatAvailable(int row, int col) {
        if (grid[row - 1][col - 1] == 'S') {
            return true;
        }
        return false;
    }

    public boolean isValidSeat(int row, int col) {
        if (row < 1 || row > ROWS || col < 1 || col > COLS) {
            return false;
        }
        return true;
    }


   public void statistics() {
        System.out.println("Number of purchased tickets: " + calculatePurchasedTickets());
        System.out.printf("Percentage: %.2f%% %n", percentagePurchasedTickets());
        System.out.printf("Current income: $%d %n", currentIncome);
        System.out.printf("Total income: $%d %n", totalIncome);
   }
}
