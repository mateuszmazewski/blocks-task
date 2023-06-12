package blocks;

import java.util.ArrayList;
import java.util.List;

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
}
