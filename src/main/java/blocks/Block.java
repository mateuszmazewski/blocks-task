package blocks;

import java.util.stream.Stream;

interface Block {
    String getColor();

    String getMaterial();

    /**
     * @return Stream of blocks representing a flattened structure of the block.
     * The stream includes recursively found nested blocks.
     * @throws StackOverflowError when any block from the blocks hierarchy is contained inside itself
     *                            or anywhere lower in its hierarchy it leads to the infinite recursion.
     */
    Stream<Block> flattenIntoStream();
}
