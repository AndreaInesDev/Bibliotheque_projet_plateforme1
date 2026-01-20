package service;

import java.util.ArrayList;
import java.util.List;

public class ActivityStack {
    private List<String> stack = new ArrayList<>();

    //ajouter une activité
    public void push(String activity) {
        stack.add(activity);
    }

    //supprimer l'activité
    public String pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
    }

    //recuperer l'activité
    public String peek() {
        if (!stack.isEmpty()) {
            return stack.get(stack.size() - 1);
        }
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
