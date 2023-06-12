package blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
}
