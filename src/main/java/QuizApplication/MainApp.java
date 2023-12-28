package QuizApplication;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);
    static QuestionService service = new QuestionImpl();

    public static void main(String[] args) {
        System.out.println("SELECT OPERATION --> ");
        System.out.println("1. ADD QUESTION ");
        System.out.println("2. REMOVE QUESTION ");
        System.out.println("3. UPDATE QUESTION");
        System.out.println("4. DISPLAY ALL QUESTIONS");
        System.out.println("5. TAKE TEST ");
        System.out.println("6. exit ");
        int ch = sc.nextInt() ;

        if (ch < 5) {
            System.out.println("Enter Password:");
            String enteredPassword = sc.next();

            // Fetch the password from the database
            String actualPassword = service.getPassword();

            if (!enteredPassword.equals(actualPassword)) {
                System.out.println("Incorrect Password.");
                System.exit(0);
            }
        }

        switch (ch)
        {
            case 1:
                addQuestion() ;
                break;
            case 2:
                removeQuestion();
                break;
            case 3:
                updateQuestion();
                break;
            case 4:
                displayAllQuestion();
                break;
            case 5:
                takeTest();
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("INVALID INPUT ");
        }
        main(args);
    }

    private static void takeTest() {
        List<Question> questionList = service.getAllQuestions();
        int marks = 0 ;

        System.out.println("READY FOR TEST ");
        String ans = sc.nextLine();
        for (Question q : questionList)
        {
            System.out.println("Q"+ q.getQuestionId()+". "+q.getQuestion() );
            System.out.println("1. "+ q.getOption1());
            System.out.println("2. "+ q.getOption2());
            System.out.println("3. "+ q.getOption3());
            System.out.println("ENTER YOUR ANS ");
            ans = sc.nextLine();
            String actualAns = q.getAnswer();
            if (ans.equals(actualAns))
                marks+=5 ;
            else
                marks-=2 ;
        }
        System.out.println("\n\n\n\n-----------------------------------");
        System.out.println(" UR TOTAL MARKS ARE : "+ marks);
        System.out.println("-----------------------------------");
        System.exit(0);

    }

    private static void displayAllQuestion() {
        List<Question> questionList = service.getAllQuestions();
        for (Question q : questionList){
            System.out.println("Q"+ q.getQuestionId()+". "+q.getQuestion() );
            System.out.println("1. "+ q.getOption1());
            System.out.println("2. "+ q.getOption2());
            System.out.println("3. "+ q.getOption3());
            System.out.println("-----> "+ q.getAnswer());
            System.out.println("\n-------------------------------------\n");
        }
    }

    public static void addQuestion()
    {
        System.out.println("ENTER QUESTION -->");
        String question = sc.nextLine() ;
        question = sc.nextLine() ;

        System.out.println("ENTER OPTION 1 ");
        String option1 = sc.nextLine() ;

        System.out.println("ENTER OPTION 2 ");
        String option2 = sc.nextLine() ;

        System.out.println("ENTER OPTION 3 ");
        String option3 = sc.nextLine() ;

        System.out.println("ENTER ANSWER ");
        String answer = sc.nextLine() ;

        Question newQuestion = new Question(question , option1 , option2 , option3 , answer);
        int n = service.addQuestions(newQuestion);
        System.out.println(n+" RECORD INSERTED !!");
        System.out.println("\n\n");
    }
    public static void removeQuestion()
    {
        System.out.println("ENTER THE QUESTION ID ");
        int questionId = sc.nextInt() ;

        int n = service.removeQuestions(questionId);
        System.out.println(n + "RECORD DELETED ");
        System.out.println("\n\n");
    }

    public static void updateQuestion(){
        System.out.println("1. MODIFY QUESTION ");
        System.out.println("2. MODIFY OPTIONS ");
        System.out.println("3. <- back ");
        int ch = sc.nextInt();
        switch (ch){
            case 1:
                modifyQuestion() ;
                break;
            case 2:
                // modifyOptions();
                break;
            case 3 :
                return;
        }
        updateQuestion();
    }
    private static void modifyQuestion() {
        System.out.println("Enter the Question ID to be modified");
        int questionId = sc.nextInt();

        System.out.println("Enter the new question");
        sc.nextLine();
        String newQuestionText = sc.nextLine();

        Question uptQuestion = new Question();
        uptQuestion.setQuestionId(questionId);
        uptQuestion.setQuestion(newQuestionText);

        int n = service.modifyQuestion(uptQuestion);
        System.out.println(n + " Question Updated");
    }
}