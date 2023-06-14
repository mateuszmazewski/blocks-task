package blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ComplexBlock extends SimpleBlock implements CompositeBlock {

    private final List<Block> blocks = new ArrayList<>();

    public ComplexBlock(String color, String material) {
        super(color, material);
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public Stream<Block> flattenIntoStream() {
        return Stream.concat(
                super.flattenIntoStream(),
                blocks.stream().flatMap(Block::flattenIntoStream));
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComplexBlock)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ComplexBlock that = (ComplexBlock) o;
        return Objects.equals(blocks, that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), blocks);
    }
}
