import jdk.jshell.spi.SPIResolutionException;

import java.util.Scanner;

public class Menu {
    static Scanner scan;
    static String[][] qb;
    static int multiplier = 1;
    static int scoreBase = 10;
    static int score = 0;

    public static void start() {
        welcomeMSG();
        startGame();
    }

    public static void startGame() {
        populateQuizQuestions();
        mainMenu();
    }

    public static void startScan() {
       scan = new Scanner(System.in);
    }

    public static void closeScan() {
        System.out.println("");
        System.out.println("Buh Bye!");
        System.out.println("");
        scan.close();
    }

    public static void populateQuizQuestions() {
       QuizBuilder qbuilder = new QuizBuilder();
       qb = qbuilder.getQuizQuestions();
    }

    public static boolean displayQuestion(int num) {

        if(num >= qb.length) {
            System.out.println("Out of bounds");
            return false;
        }

        char rightAnswer = 'z';

        System.out.println("");
        System.out.println(qb[num][0]);
        System.out.println();
        System.out.println("A: " + qb[num][1]);
        System.out.println("B: " + qb[num][2]);
        System.out.println("C: " + qb[num][3]);
        System.out.println("D: " + qb[num][4]);
        System.out.println("");

        if(qb[num][1].equals(qb[num][5])) {
            rightAnswer = 'a';
        }

        if(qb[num][2].equals(qb[num][5])) {
            rightAnswer = 'b';
        }

        if(qb[num][3].equals(qb[num][5])) {
            rightAnswer = 'c';
        }

        if(qb[num][4].equals(qb[num][5])) {
            rightAnswer = 'd';
        }

        char userAnswer = readAnswerInput();

        while( !(userAnswer == 'a' || userAnswer == 'b' || userAnswer == 'c' || userAnswer == 'd') ) {
            wrongInput();
            userAnswer = readAnswerInput();
        }

        if(userAnswer == rightAnswer) {
            System.out.println("Right Answer");
            return true;
        }

        System.out.println("Wrong Answer");
        return false;
    }

    public static char readAnswerInput() {
        System.out.print("Choose your answer: ");
        String input = scan.next().toLowerCase();

        if(input.length() > 1) {
            wrongInput();
            readAnswerInput();
        }

        char[] chr = input.toCharArray();
        return chr[0];
    }

    public static void welcomeMSG() {
        System.out.println("WELCOME TO THE QUIZ GAME");
        System.out.println("");
    }

    public static void wrongInput() {
        System.out.println("");
        System.out.println("Wrong Input, please try again");
        System.out.println("");
    }

    public static void gameOver() {
        System.out.println("");
        System.out.println("GAME OVER");
        System.out.println("");
    }

    public static void displayMainMenu() {
        System.out.println("");
        System.out.println("MENU");
        System.out.println("Press 1 to Play");
        System.out.println("Press 2 to Quit");
        System.out.println("");
    }

    public static void displayMainMenuReplay() {
        System.out.println("");
        System.out.println("MENU");
        System.out.println("Press 1 to Play Again");
        System.out.println("Press 2 to Quit");
        System.out.println("");
    }

    public static void mainMenu() {
        displayMainMenu();
        startScan();/home/vieira
        System.out.print("Enter Input: ");
        int num = scan.nextInt();
        System.out.println("");

        if(num > 2 || num < 0) {
            wrongInput();
            mainMenu();
        }

        if(num == 1) {
            execGame();
        }

        if(num == 2) {
            closeScan();
        }

    }

    public static void mainMenuReplay() {
        displayMainMenuReplay();
        System.out.print("Enter Input: ");
        int num = scan.nextInt();
        System.out.println("");

        if(num > 2 || num < 0) {
            wrongInput();
            mainMenuReplay();
        }

        if(num == 1) {
            populateQuizQuestions();
            execGame();
        }

        if(num == 2) {
            closeScan();
        }

    }

    public static void execGame() {
        multiplier = 1;
        score = 0;
        int correctAnswers = 0;
        for(int i = 0; i < qb.length; i++) {
            if(displayQuestion(i)) {
                score += scoreBase * multiplier;
                correctAnswers++;
                multiplier++;
            } else {
                multiplier = 1;
            }
        }

        System.out.println("");
        System.out.println("Correct Answers: " + correctAnswers + "/10 | SCORE: " + score);
        System.out.println("");

        gameOver();
        mainMenuReplay();

    }

}
