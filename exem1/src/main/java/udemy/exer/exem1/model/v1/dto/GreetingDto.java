package udemy.exer.exem1.model.v1.dto;

public class GreetingDto {
    private final long id;
    private final String content;

    public GreetingDto(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }
}
