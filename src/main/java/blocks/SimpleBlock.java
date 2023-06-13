package blocks;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleBlock)) {
            return false;
        }
        SimpleBlock that = (SimpleBlock) o;
        return Objects.equals(color, that.color) && Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, material);
    }
}
