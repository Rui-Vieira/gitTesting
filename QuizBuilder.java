import java.util.ArrayList;
import java.util.Collections;

public class QuizBuilder {

    private String[][] quizQuestions;

    public  QuizBuilder() {
        this.quizQuestions = buildQuestions();
    }

    public int[] randomIndexes() {
        int tempInt = Questions.numQuestions();
        ArrayList<Integer> arrListInt = new ArrayList<>();
        int[] arrInt = new int[10];

        for(int i = 0; i < tempInt; i++)  {
            arrListInt.add(i);
        }

        Collections.shuffle(arrListInt);

        for(int i = 0; i < arrInt.length; i++) {
            arrInt[i] = arrListInt.get(i);
        }

        return arrInt;
    }

    public String[][] buildQuestions() {
        String[][] temp = new String[10][6];
        int[] indexes = randomIndexes();

        for(int i = 0; i < temp.length; i++) {
            String[] tempArrString = Questions.getQuestion(indexes[i]);
            for(int j = 0; j < temp[i].length; j++) {
                temp[i][j] = tempArrString[j];
            }
        }

        return temp;
    }

    public String[][] getQuizQuestions() {
        return quizQuestions;
    }

}
