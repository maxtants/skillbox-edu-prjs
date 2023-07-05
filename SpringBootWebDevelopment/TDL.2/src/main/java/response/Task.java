package response;


import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private AtomicInteger id;
    private String name;

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
