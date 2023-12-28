package QuizApplication;

import java.util.List;

public interface QuestionService {
    int addQuestions(Question newQuestion);
    int removeQuestions( int questionId ) ;
    List<Question> getAllQuestions();
    int modifyQuestion(Question uptQuestion);

    String getPassword();
}