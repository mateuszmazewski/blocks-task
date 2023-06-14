package blocks;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class WallTest {

    private static final SimpleBlock OUTER_SIMPLE_BLOCK =
            new SimpleBlock("colorOuterSimple", "materialOuterSimple");
    private static final SimpleBlock NESTED_SIMPLE_BLOCK =
            new SimpleBlock("colorNestedSimple", "materialNestedSimple");
    private static final SimpleBlock DOUBLE_NESTED_SIMPLE_BLOCK =
            new SimpleBlock("colorDoubleNestedSimple", "materialDoubleNestedSimple");
    private static final ComplexBlock OUTER_COMPLEX_BLOCK =
            new ComplexBlock("colorOuterComplex", "materialOuterComplex");
    private static final ComplexBlock NESTED_COMPLEX_BLOCK =
            new ComplexBlock("colorNestedComplex", "materialNestedComplex");
    private static Wall wallContainingBlocks, emptyWall;

    @BeforeAll
    static void beforeAll() {
        NESTED_COMPLEX_BLOCK.addBlock(DOUBLE_NESTED_SIMPLE_BLOCK);
        OUTER_COMPLEX_BLOCK.addBlock(NESTED_COMPLEX_BLOCK);
        OUTER_COMPLEX_BLOCK.addBlock(NESTED_SIMPLE_BLOCK);

        wallContainingBlocks = new Wall(OUTER_SIMPLE_BLOCK, OUTER_COMPLEX_BLOCK);
        emptyWall = new Wall();
    }

    @Nested
    @DisplayName("findBlockByColor()")
    class FindBlockByColorTests {
        @Test
        @DisplayName("should be able to find an outer simple block")
        void findBlockByColor_when_lookingForOuterSimpleBlock_should_returnOuterSimpleBlock() {
            Optional<Block> foundBlock = wallContainingBlocks.findBlockByColor("colorOuterSimple");

            Assertions.assertTrue(foundBlock.isPresent());
            Assertions.assertEquals(OUTER_SIMPLE_BLOCK, foundBlock.get());
        }

        @Test
        @DisplayName("should be able to find an outer complex block")
        void findBlockByColor_when_lookingForOuterComplexBlock_should_returnOuterComplexBlock() {
            Optional<Block> foundBlock = wallContainingBlocks.findBlockByColor("colorOuterComplex");

            Assertions.assertTrue(foundBlock.isPresent());
            Assertions.assertEquals(OUTER_COMPLEX_BLOCK, foundBlock.get());
        }

        @Test
        @DisplayName("should be able to find a nested simple block")
        void findBlockByColor_when_lookingForNestedSimpleBlock_should_returnNestedSimpleBlock() {
            Optional<Block> foundBlock = wallContainingBlocks.findBlockByColor("colorNestedSimple");

            Assertions.assertTrue(foundBlock.isPresent());
            Assertions.assertEquals(NESTED_SIMPLE_BLOCK, foundBlock.get());
        }

        @Test
        @DisplayName("should be able to find a nested complex block")
        void findBlockByColor_when_lookingForNestedComplexBlock_should_returnNestedComplexBlock() {
            Optional<Block> foundBlock = wallContainingBlocks.findBlockByColor("colorNestedComplex");

            Assertions.assertTrue(foundBlock.isPresent());
            Assertions.assertEquals(NESTED_COMPLEX_BLOCK, foundBlock.get());
        }

        @Test
        @DisplayName("should be able to find a double nested block")
        void findBlockByColor_when_lookingForDoubleNestedBlock_should_returnDoubleNestedBlock() {
            Optional<Block> foundBlock = wallContainingBlocks.findBlockByColor("colorDoubleNestedSimple");

            Assertions.assertTrue(foundBlock.isPresent());
            Assertions.assertEquals(DOUBLE_NESTED_SIMPLE_BLOCK, foundBlock.get());
        }

        @Test
        @DisplayName("should return empty Optional when there is no block matching given color")
        void findBlockByColor_when_lookingForNonexistentBlock_should_returnEmptyOptional() {
            Optional<Block> foundBlock = wallContainingBlocks.findBlockByColor("unknownColor");

            Assertions.assertFalse(foundBlock.isPresent());
        }

        @Test
        @DisplayName("should return empty Optional when wall is empty")
        void findBlockByColor_when_wallIsEmpty_should_returnEmptyOptional() {
            Optional<Block> foundBlock = emptyWall.findBlockByColor("colorOuterSimple");

            Assertions.assertFalse(foundBlock.isPresent());
        }

        @Test
        @DisplayName("should throw IllegalArgumentException when color is null")
        void findBlockByColor_when_colorIsNull_should_throwIllegalArgumentException() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> wallContainingBlocks.findBlockByColor(null));
        }
    }

    @Nested
    @DisplayName("findBlocksByMaterial()")
    class FindBlocksByMaterialTests {
        @Test
        @DisplayName("should be able to find an outer simple block")
        void findBlocksByMaterial_when_lookingForOuterSimpleBlock_should_returnListContainingOuterSimpleBlock() {
            List<Block> expectedBlocks = Collections.singletonList(OUTER_SIMPLE_BLOCK);

            List<Block> foundBlocks = wallContainingBlocks.findBlocksByMaterial("materialOuterSimple");

            Assertions.assertEquals(expectedBlocks, foundBlocks);
        }

        @Test
        @DisplayName("should be able to find an outer complex block")
        void findBlocksByMaterial_when_lookingForOuterComplexBlock_should_returnListContainingOuterComplexBlock() {
            List<Block> expectedBlocks = Collections.singletonList(OUTER_COMPLEX_BLOCK);

            List<Block> foundBlocks = wallContainingBlocks.findBlocksByMaterial("materialOuterComplex");

            Assertions.assertEquals(expectedBlocks, foundBlocks);
        }

        @Test
        @DisplayName("should be able to find a nested simple block")
        void findBlocksByMaterial_when_lookingForNestedSimpleBlock_should_returnListContainingNestedSimpleBlock() {
            List<Block> expectedBlocks = Collections.singletonList(NESTED_SIMPLE_BLOCK);

            List<Block> foundBlocks = wallContainingBlocks.findBlocksByMaterial("materialNestedSimple");

            Assertions.assertEquals(expectedBlocks, foundBlocks);
        }

        @Test
        @DisplayName("should be able to find a nested complex block")
        void findBlocksByMaterial_when_lookingForNestedComplexBlock_should_returnListContainingNestedComplexBlock() {
            List<Block> expectedBlocks = Collections.singletonList(NESTED_COMPLEX_BLOCK);

            List<Block> foundBlocks = wallContainingBlocks.findBlocksByMaterial("materialNestedComplex");

            Assertions.assertEquals(expectedBlocks, foundBlocks);
        }

        @Test
        @DisplayName("should be able to find a double nested block")
        void findBlocksByMaterial_when_lookingForDoubleNestedBlock_should_returnListContainingDoubleNestedBlock() {
            List<Block> expectedBlocks = Collections.singletonList(DOUBLE_NESTED_SIMPLE_BLOCK);

            List<Block> foundBlocks = wallContainingBlocks.findBlocksByMaterial("materialDoubleNestedSimple");

            Assertions.assertEquals(expectedBlocks, foundBlocks);
        }

        @Test
        @DisplayName("should return empty list when there is no block matching given material")
        void findBlocksByMaterial_when_lookingForNonexistentBlock_should_returnEmptyList() {
            List<Block> foundBlocks = wallContainingBlocks.findBlocksByMaterial("unknownMaterial");

            Assertions.assertNotNull(foundBlocks);
            Assertions.assertTrue(foundBlocks.isEmpty());
        }

        @Test
        @DisplayName("should return empty list when wall is empty")
        void findBlocksByMaterial_when_wallIsEmpty_should_returnEmptyList() {
            List<Block> foundBlocks = emptyWall.findBlocksByMaterial("materialOuterSimple");

            Assertions.assertNotNull(foundBlocks);
            Assertions.assertTrue(foundBlocks.isEmpty());
        }

        @Test
        @DisplayName("should throw IllegalArgumentException when material is null")
        void findBlocksByMaterial_when_materialIsNull_should_throwIllegalArgumentException() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> wallContainingBlocks.findBlocksByMaterial(null));
        }

        @Test
        @DisplayName("should return list containing only the blocks matching given material")
        void findBlocksByMaterial_when_someBlocksHaveTheSameMaterial_should_returnListContainingThoseBlocks() {
            SimpleBlock outerSimpleBlock = new SimpleBlock("a", "commonMaterial");
            SimpleBlock nestedSimpleBlock = new SimpleBlock("b", "x");
            SimpleBlock doubleNestedSimpleBlock = new SimpleBlock("c", "commonMaterial");
            ComplexBlock outerComplexBlock = new ComplexBlock("d", "y");
            ComplexBlock nestedComplexBlock = new ComplexBlock("e", "commonMaterial");
            nestedComplexBlock.addBlock(doubleNestedSimpleBlock);
            outerComplexBlock.addBlock(nestedComplexBlock);
            outerComplexBlock.addBlock(nestedSimpleBlock);
            Wall wall = new Wall(outerSimpleBlock, outerComplexBlock);

            List<Block> expectedBlocks = Arrays.asList(
                    outerSimpleBlock,
                    doubleNestedSimpleBlock,
                    nestedComplexBlock);

            List<Block> foundBlocks = wall.findBlocksByMaterial("commonMaterial");

            Assertions.assertTrue(foundBlocks.size() == expectedBlocks.size()
                    && foundBlocks.containsAll(expectedBlocks));
        }

        @Test
        @DisplayName("should return list containing all blocks when every block matches given material")
        void findBlocksByMaterial_when_allBlocksHaveTheSameMaterial_should_returnListContainingAllBlocks() {
            SimpleBlock outerSimpleBlock = new SimpleBlock("a", "commonMaterial");
            SimpleBlock nestedSimpleBlock = new SimpleBlock("b", "commonMaterial");
            SimpleBlock doubleNestedSimpleBlock = new SimpleBlock("c", "commonMaterial");
            ComplexBlock outerComplexBlock = new ComplexBlock("d", "commonMaterial");
            ComplexBlock nestedComplexBlock = new ComplexBlock("e", "commonMaterial");
            nestedComplexBlock.addBlock(doubleNestedSimpleBlock);
            outerComplexBlock.addBlock(nestedComplexBlock);
            outerComplexBlock.addBlock(nestedSimpleBlock);
            Wall wall = new Wall(outerSimpleBlock, outerComplexBlock);

            List<Block> expectedBlocks = Arrays.asList(
                    outerComplexBlock,
                    outerSimpleBlock,
                    nestedComplexBlock,
                    nestedSimpleBlock,
                    doubleNestedSimpleBlock);

            List<Block> foundBlocks = wall.findBlocksByMaterial("commonMaterial");

            Assertions.assertTrue(foundBlocks.size() == expectedBlocks.size()
                    && foundBlocks.containsAll(expectedBlocks));
        }

        @Test
        @DisplayName("should return list containing duplicated blocks matching given material")
        void findBlocksByMaterial_when_duplicatedComplexBlock_should_returnListContainingAllTheDuplicates() {
            ComplexBlock complexBlock = new ComplexBlock("a", "commonMaterial");
            SimpleBlock simpleBlock = new SimpleBlock("b", "commonMaterial");
            complexBlock.addBlock(simpleBlock);
            Wall wall = new Wall(complexBlock, complexBlock);

            List<Block> expectedBlocks = Arrays.asList(
                    complexBlock,
                    complexBlock,
                    simpleBlock,
                    simpleBlock
            );

            List<Block> foundBlocks = wall.findBlocksByMaterial("commonMaterial");

            Assertions.assertTrue(foundBlocks.size() == expectedBlocks.size()
                    && foundBlocks.containsAll(expectedBlocks));
        }
    }

    @Nested
    @DisplayName("count()")
    class CountTests {
        @Test
        @DisplayName("should return 0 when wall is empty")
        void count_when_wallIsEmpty_should_return0() {
            int expected = 0;

            int actual = emptyWall.count();

            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("should be able to count outer blocks")
        void count_when_noNestedBlocks_should_returnProperValue() {
            Wall wall = new Wall(OUTER_SIMPLE_BLOCK, new ComplexBlock("a", "b"));
            int expected = 2;

            int actual = wall.count();

            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("should be able to count nested blocks")
        void count_when_wallContainsNestedBlocks_should_returnProperValueCountingBothOuterAndNestedBlocks() {
            int expected = 5;

            int actual = wallContainingBlocks.count();

            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("should take duplicated blocks into account")
        void count_when_duplicatedComplexBlock_should_returnProperValueTakingDuplicatesIntoAccount() {
            ComplexBlock complexBlock = new ComplexBlock("a", "b");
            SimpleBlock simpleBlock = new SimpleBlock("c", "d");
            complexBlock.addBlock(simpleBlock);
            Wall wall = new Wall(complexBlock, complexBlock);
            int expected = 4;

            int actual = wall.count();

            Assertions.assertEquals(expected, actual);
        }
    }
}
