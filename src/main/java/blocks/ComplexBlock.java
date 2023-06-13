package blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComplexBlock extends SimpleBlock implements CompositeBlock {

    private final List<Block> blocks = new ArrayList<>();

    public ComplexBlock(String color, String material) {
        super(color, material);
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
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
