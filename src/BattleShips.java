
import java.util.Scanner;

public class BattleShips {

    private String[][] battleGround;

    //defining the constructor for the battleShips Game

    public BattleShips(String[][] battleGround) {
        this.battleGround = battleGround;
    }
    //METHOD FOR GENERATING THE SHIPS BY THE COMPUTER DURING THE START OF THE GAME
    public void computerGenerateShips() {
        System.out.println("Computer is deploying ships");
        int i = 1, randomX, randomY;
        int sizeX = 10;
        int sizeY = 10;
        while(i <= 5) {
            randomX = (int)(Math.random()*sizeX);
            randomY = (int)(Math.random()*sizeY);
            if((!battleGround[randomX][randomY].equals("2")) && (!battleGround[randomX][randomY].equals("@"))) {
                battleGround[randomX][randomY] = "2";
                battleGround[randomX][randomY] = "2";
                System.out.println("Computer ship "+i+" deployed successfully");
            }
            i++;
        }
        System.out.println("-----------------------------------------");
    }
    public void initializeBattleground() {
        int sizeX = 10;
        int sizeY = 10;
        for(int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                battleGround[i][j] = " ";
            }
        }
    }

    public void gameIntro() {
        System.out.println("------------------ WELCOME TO THE BATTLESHIPS GAME ---------------------");

        System.out.println("The instructions are : ");
        System.out.println("1. You will be given a sea which is a 10x10 arena where you will have your ships and the computer will have her own ships.");
        System.out.println("2. You will enter your coordinates that is the location of your ships, provided which the computer will generate the location of her ships. 0");
        System.out.println("3. You and the computer will be provided with 5 ships each deployed in the map, you will know the coordinates of your ships but not the computer's ships.");
        System.out.println("3. Now you will enter the X and Y coordinates in the map and if it matches the computer's coordinates then you have shunk her ship and you will get a ! in the map ");
        System.out.println("4. The first to lose all the 5 ships loses the games");
        System.out.println("-----------------------------------------------------------");
        System.out.println("RIGHT NOW THE SEA LOOKS LIKE THIS : ");
        showBattleGround();
    }

    public void showBattleGround() {
        System.out.println("    0 1 2 3 4 5 6 7 8 9    ");
        for(int i = 0; i < 10; i++) {
            System.out.print(i+" | ");
            for(int j = 0; j < 10; j++) {
                if (battleGround[i][j] == "2") {
                    System.out.print("  ");
                } else {
                    System.out.print(battleGround[i][j]+" ");
                }
            }
            System.out.print("| "+i+"\n");
        }
        System.out.println();
    }
    public void PlayerEntersCoordinates() {
        Scanner input = new Scanner(System.in);
        int i = 0;
        System.out.println("Deploy your ships");

        while(i < 5) {
            System.out.print("Enter the x-coordinates of your ship " + (i+1) + " : ");
            int playerX = input.nextInt();
            System.out.print("Enter the y-coordinates of your ship "+ (i+1) +" : ");
            int playerY = input.nextInt();
            if ((playerX > 9 || playerX < 0) || (playerY > 9 || playerY < 0)) {
                System.out.println("Enter the correct coordinates");
            }
            else if(battleGround[playerX][playerY] == "@") {
                System.out.println("You already have a ship there. Try another location");
            }
            else {
                battleGround[playerX][playerY] = "@";
                i++;
            }
        }
        System.out.println("----------------------------------------------");
    }
    public void playGame() {
        int playerX, playerY, computerX, computerY;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("----------- YOUR TURN ------------");
            System.out.println("Enter the coordinates of your ship : ");

            //PLAYER'S TURN

            System.out.print("Enter the X coordinate : ");
            playerX = input.nextInt();
            System.out.print("Enter the Y coordinate : ");
            playerY = input.nextInt();
            //checking player's turn and also checking the coordinates of the player's input if they are valid or not
            while((playerX > 9 || playerX < 0) || (playerY > 9 || playerY < 0)) {
                System.out.println("Please enter the correct coordinates !!!!");
                System.out.print("Enter the X coordinate : ");
                playerX = input.nextInt();
                System.out.print("Enter the Y coordinates : ");
                playerY = input.nextInt();
            }
            //now working upon the operations of the player
            if (battleGround[playerX][playerY] == "2") {              //the player finds computer's ship at the coordintates
                System.out.println("BOOM! You sunk one of the computer's ships!!");
                battleGround[playerX][playerY] = "!";                //the player finds her ship at the coordinates she has entered
            } else if (battleGround[playerX][playerY] == "@") {
                System.out.println("Sorry!! You sunk your own ship! :(");
                battleGround[playerX][playerY] = "X";
            } else {                                                     //the player has missed the target
                System.out.println("Buddy! You have missed the target");
                battleGround[playerX][playerY] = "-";
            }

            //NOW COMPUTER'S TURN
            System.out.println("------------ COMPUTER TURN -------------");
            System.out.println("The computer is entering the coordinates! PRAY SHE DOESN'T SINK YOUR SHIP!");
            computerX = (int)(Math.random()*10);
            computerY = (int)(Math.random()*10);

            if (battleGround[computerX][computerY] == "2") {
                System.out.println("The computer sank her own ship!");
                battleGround[computerX][computerY] = "!";
            } else if (battleGround[computerX][computerY] == "@") {
                System.out.println("The computer sank your ship! SORRY :(");
                battleGround[computerX][computerY] = "X";
            } else {
                System.out.println("The computer also missed her coordinates. THANK GOD, YOU ARE SAFE!");
                battleGround[computerX][computerY] = "-";
            }

            System.out.println("You have ships : "+ countShips("@"));
            System.out.println("Computer has ships : "+ countShips("2"));
            System.out.println();
            showBattleGround();
        } while ((countShips( "2") != 0 ) && (countShips( "@") != 0));

        //THE GAME IS OVER... PRINT OUT THE RESULTS

        if (countShips( "2") == 0) {
            System.out.println("HOOOORAY! You have successfully sank all the computer's ship!!!! AWESOME");
        } else {
            System.out.println("Sorry! You have lost the game");
        }
        System.out.println(" --------------------------------- GAME OVER --------------------------------- ");
    }
    public int countShips(String input) {
        int count = 0;
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(battleGround[i][j] == input) {
                    count++;
                }
            }
        }
        return count;
    }
}
