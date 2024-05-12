package cinema;
import java.util.Scanner;

public class Cinema {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each rows:");
        int cols = scanner.nextInt();

        Grid grid = new Grid(rows, cols);

        runMenu(grid);
    }

    private static void runMenu(Grid grid) {
        while (true) {
            showMenu();
            int menuChoice = scanner.nextInt();
            switch(menuChoice) {
                case 1:
                    grid.printGrid();
                    break;
                case 2:
                    reserveSeat(grid);
                    break;
                case 3:
                    grid.statistics();
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private static int getRow() {
        System.out.println("Enter a row number:");
        return scanner.nextInt();
    }

    private static int getColumn() {
        System.out.println("Enter a seat number in that row:");
        return scanner.nextInt();
    }

    public static void reserveSeat(Grid grid) {
        int row, col;
        boolean seatReserved = false;

        do {
            row = getRow();
            col = getColumn();

            // check if coordinates entered by user are in-bound
            if (!grid.isValidSeat(row, col)) {
                System.out.println("Wrong input!");
                continue; // go to next iteration of the loop
            }

            seatReserved = grid.reserveSeat(row, col);

            if (!seatReserved) {
                System.out.println("That ticket has already been purchased!");
            }

        } while (!seatReserved);

        System.out.printf("Ticket price: $%d\n", grid.calculateSeatPrice(row, col));
    }
}
