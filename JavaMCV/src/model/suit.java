package model;

public class suit {
    private String id;
    private String type;
    private int durability;
    
    public suit(String id, String type, int durability) {
        this.id = id;
        this.type = type;
        this.durability = durability;
    }
    
    public String getId() { 
        return id; 
    }
    public String getType() { 
        return type; 
    }
    public int getDurability() { 
        return durability; 
    }
    
    public boolean isDurabilityValid() {
        return switch (type) {
            case "ชุดทรงพลัง" -> durability >= 70;
            case "ชุดลอบเร้น" -> durability >= 50;
            case "ชุดปกปิดตัวตน" -> !(durability % 10 == 3 || durability % 10 == 7);
            default -> false;
        };
    }
    
    public void repair() {
        if (durability < 100) {
            durability = Math.min(100, durability + 25);
        }
    }
}
