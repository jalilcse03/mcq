package bubtjobs.com.project;

/**
 * Created by Murtuza on 3/15/2016.
 */
public class AnswerSetMaker {
    private int id;
    private String key;
    private String answer;

    public AnswerSetMaker() {
    }

    public AnswerSetMaker(int id, String key, String answer) {
        this.id = id;
        this.key = key;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
