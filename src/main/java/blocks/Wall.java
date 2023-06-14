package blocks;

import java.util.*;
import java.util.stream.Collectors;
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

    /**
     * @param block block to be flattened
     * @return Stream of blocks representing a flattened structure of the given block.
     * The stream includes recursively found nested blocks.
     * @throws StackOverflowError when any block from the blocks hierarchy is contained inside itself
     *                            or anywhere lower in its hierarchy it leads to the infinite recursion.
     */
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
        if (material == null) {
            throw new IllegalArgumentException("material cannot be null");
        }

        return blocks.stream()
                .flatMap(this::flattenBlock)
                .filter(block -> material.equals(block.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) blocks.stream()
                .flatMap(this::flattenBlock)
                .count();
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
