package blocks;

import java.util.*;
import java.util.stream.Stream;

public class Wall implements Structure {
    private final List<Block> blocks = new ArrayList<>();

    public Wall(Block... blocks) {
        this.blocks.addAll(Arrays.asList(blocks));
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException("color cannot be null");
        }

        return blocks.stream()
                .flatMap(this::flattenBlock)
                .filter(block -> color.equals(block.getColor()))
                .findAny();
    }

    private Stream<Block> flattenBlock(Block block) {
        if (block instanceof CompositeBlock) {
            return Stream.concat(
                    Stream.of(block),
                    ((CompositeBlock) block).getBlocks().stream().flatMap(this::flattenBlock));
        }

        return Stream.of(block);
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
