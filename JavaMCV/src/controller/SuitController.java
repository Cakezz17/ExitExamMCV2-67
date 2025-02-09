package controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import model.suit;

public class SuitController {
    private List<suit> suits;

    public SuitController() {
        suits = new ArrayList<>();
        loadSuits();
    }
    private void loadSuits() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/suits.json")));
            JSONArray suitsArr = new JSONArray(json);

            for (int i = 0; i < suitsArr.length(); i++) {
                JSONObject obj = suitsArr.getJSONObject(i);
                suits.add(new suit(
                    obj.getString("id"),
                    obj.getString("type"),
                    obj.getInt("durability")
                ));
            }   
        } catch (Exception e) {
            System.out.println("Error loading cows: " + e.getMessage());
        }
    }
    public suit findsuitById(String id) {
        return suits.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}
    

