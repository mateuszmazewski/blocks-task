package blocks;

import java.util.*;

public class Wall implements Structure {
    private final List<Block> blocks = new ArrayList<>();

    public Wall(Block... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wall)) {
            return false;
        }
        Wall wall = (Wall) o;
        return Objects.equals(blocks, wall.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blocks);
    }
}
