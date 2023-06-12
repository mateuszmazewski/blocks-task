package blocks;

public class SimpleBlock implements Block {

    private final String color;
    private final String material;

    public SimpleBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }
}
