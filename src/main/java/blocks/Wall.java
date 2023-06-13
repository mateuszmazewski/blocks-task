package blocks;

import java.util.*;

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

        Block foundBlock;
        for (Block block : blocks) {
            foundBlock = recursiveBlockSearch(block, color);
            if (foundBlock != null) {
                return Optional.of(foundBlock);
            }
        }

        return Optional.empty();
    }

    private Block recursiveBlockSearch(Block block, String color) {
        if (color.equals(block.getColor())) {
            return block;
        }

        if (block instanceof CompositeBlock) {
            Block foundBlock;

            for (Block b : ((CompositeBlock) block).getBlocks()) {
                foundBlock = recursiveBlockSearch(b, color);
                if (foundBlock != null) {
                    return foundBlock;
                }
            }
        }

        return null;
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
