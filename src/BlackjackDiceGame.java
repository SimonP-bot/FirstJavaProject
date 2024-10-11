import java.util.Scanner;
import java.util.Random;

public class BlackjackDiceGame {
    public static void main(String[] args) {

        // Scanner og Random seeded tilføjes
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        // Printer en introduktion til spillet.
        System.out.println("Welcome to the Blackjack Dice Game");
        System.out.println("You throw 2 dices at the time and compete against a dealer");
        System.out.println("If your score higher than 21 the dealer will win.");
        System.out.println();

        // Her gives spilleren en mulighed for at starte spillet eller ikke.
        System.out.println("Do you wanna play? yes or no: ");
        String wannaPlay = sc.nextLine();

        /*
        Hvis spilleren skriver "yes", så starter spillet.
        Hvis spilleren skriver "no" eller noget andet, så sluttes spillet da koden stopper (System.exit(0);)
         */
        if (wannaPlay.equalsIgnoreCase("yes")) {
            System.out.println("You trow the dices");
        } else {
            System.out.println("The game has been ended");
            System.exit(0);
        }

        // Spilleren starter med at kaste terningerne. Værdien af terningerne gemmes i en int-variabel playerScore
        int playerScore = diceValues(rnd);
        System.out.println("You rolled: " + playerScore);

        /*
        En boolean condition opstilles, så spillet forsætter så længe scoreren er under 21
        og spilleren skriver "yes" til at forstsætte spillet.

        Koden stopper, hvis playerScore er over 21
        else statementet siger at hvis playersScore er under eller lig med 21, og spiller skriver
        "no" til at kaste igen, så stopper loopet, og går videre i koden.
        */
        boolean playerEndGame = false;
        while (!playerEndGame) {
            System.out.println("Do you wanna throw again? yes or no: ");
            String continueOrEnd = sc.nextLine();
            if (continueOrEnd.equalsIgnoreCase("yes")) {
                int trowDice = diceValues(rnd);
                System.out.println("You rolled: " + trowDice);
                playerScore += trowDice;
                System.out.println("Your score is now: " + playerScore);

                if (playerScore > 21) {
                    System.out.println("You lost... Your score is over the limit.");
                    return;
                } if (playerScore == 21) {
                    System.out.println();
                    System.out.println("It is dealers turn");
                    break;
                }
            } else {
                playerEndGame = true;
                System.out.println("Your final score is: " + playerScore);
            }
        }
        // Hej
        /*
        Dealerens tur
        Der laves en int-variabel, som gemmer værdien af de to terninger.
        */
        int dealerScore = diceValues(rnd);
        System.out.println("Dealer rolled: " + dealerScore);
        /*
        Dealeren skal kaste terningerne, så længe den samlede score er under 17
        Derfor er vores condition (dealerScore < 17). While loopet køres så længe dealerScore er
        under 17.

        Hvis dealerScore er højere end 21, så har spilleren vundet og koden stoppes.
        */
        while (dealerScore < 17) {
            int throwDice = diceValues(rnd);
            dealerScore += throwDice;
            System.out.println("The dealer trowed: " + throwDice +
                    " and now has a total score of: " + dealerScore);
            if (dealerScore > 21) {
                System.out.println("You won!");
                System.out.println("Your score: " + playerScore);
                System.out.println("Dealers score: " + dealerScore);
                return;
            }
        }

        /*
        Hvis både playerScore og dealerScore er mindre eller lig med 21, så kommer vinderen
        an på hvilken score som er højest eller uafgjort, hvis både playerScore og dealerScore
        er den samme.

        Der printes også en opsumering af både playerScore og dealerScore
        */
        if (playerScore > dealerScore && playerScore <= 21) {
            System.out.println("You won!");
            System.out.println("Your score: " + playerScore);
            System.out.println("Dealers score: " + dealerScore);
        } else if (playerScore < dealerScore) {
            System.out.println("You lost");
            System.out.println("Your score: " + playerScore);
            System.out.println("Dealers score: " + dealerScore);
        } else {
            System.out.println("You and the dealer have the same score. It is a tie");
            System.out.println("Your score: " + playerScore);
            System.out.println("Dealers score: " + dealerScore);
        }
    }
    public static int diceValues(Random random) {

        /*
        Her laves en metode, som returnere værdien af to terninger der bliver kastet.
        Da værdien af random.nextInt(6) giver et tilfældigt helttal mellem 0-5, så pludses denne med 1.
        */
        int firstDiceValue = random.nextInt(6)+1;
        int secondDiceValue = random.nextInt(6)+1;
        return firstDiceValue + secondDiceValue;
    }
}