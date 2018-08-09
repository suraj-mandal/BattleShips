

public class GamePlay {
    public static void main(String[] args) {
        String[][] battleGround = new String[10][10];
        BattleShips battleShips = new BattleShips(battleGround);

        //initialising the battleground
        battleShips.initializeBattleground();
        //starting the game
        battleShips.gameIntro();
        //taking the input from the player
        battleShips.PlayerEntersCoordinates();
        //computer generating the coordinates of the ship
        battleShips.computerGenerateShips();
        //after entering the coordinates the battlefield is shown
        battleShips.showBattleGround();
        //the game starts
        battleShips.playGame();


    }
}
