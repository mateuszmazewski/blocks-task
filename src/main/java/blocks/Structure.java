package blocks;

import java.util.List;
import java.util.Optional;

interface Structure {

    /**
     * @param color color of a block to be found
     * @return Optional describing any block from the structure matching given color
     * or an empty Optional when no such a block could be found
     */
    Optional<Block> findBlockByColor(String color);

    /**
     * @param material material of blocks to be found
     * @return list of all the blocks from the structure matching given material
     * or an empty list when no such blocks could be found
     */
    List<Block> findBlocksByMaterial(String material);

    /**
     * @return number of all the blocks the structure consists of
     */
    int count();
}
