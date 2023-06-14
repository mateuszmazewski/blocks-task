package blocks;

import java.util.*;
import java.util.stream.Collectors;

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
                .flatMap(Block::flattenIntoStream)
                .filter(block -> color.equals(block.getColor()))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) {
            throw new IllegalArgumentException("material cannot be null");
        }

        return blocks.stream()
                .flatMap(Block::flattenIntoStream)
                .filter(block -> material.equals(block.getMaterial()))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) blocks.stream()
                .flatMap(Block::flattenIntoStream)
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
