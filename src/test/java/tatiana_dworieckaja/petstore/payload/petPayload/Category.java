package tatiana_dworieckaja.petstore.payload.petPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Category {

    private int id;
    private String name;

    private static final Map<Integer, String> categories = new HashMap<>();

    static {
        categories.put(0, "cat");
        categories.put(1, "dog");
        categories.put(2, "mouse");
        categories.put(3, "rat");
        categories.put(4, "unknown");
    }

    public void setName(){
        setId();
        this.name = categories.get(this.id);
    }

    public void  setId(){
        Random rnd = new Random();
        this.id = rnd.nextInt(categories.size());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


