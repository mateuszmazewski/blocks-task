# blocks-task

## About

This project is a solution to the following task:

> Zaimplementować metody findBlockByColor, findBlocksByMaterial, count w klasie Wall - najchętniej unikając powielania
> kodu i umieszczając całą logikę w klasie Wall. Z uwzględnieniem w analizie i implementacji interfejsu CompositeBlock.
>```
>interface Structure {
>// zwraca dowolny element o podanym kolorze
>Optional<Block> findBlockByColor(String color);
>
>// zwraca wszystkie elementy z danego materiału
>List<Block> findBlocksByMaterial(String material);
>
>//zwraca liczbę wszystkich elementów tworzących strukturę
>int count();
>}
>
>public class Wall implements Structure {
>private List<Block> blocks;
>}
>
>interface Block {
>String getColor();
>String getMaterial();
>}
>
>interface CompositeBlock extends Block {
>List<Block> getBlocks();
>}
>```

## Running the application

The project is a standard Maven project. It requires **Java 8**.

You can import the project to your IDE of choice as you would with any
Maven project and run it directly there.

The project contains unit tests that can be run in order to check the solution.

## Notes

- Not all the logic was placed in Wall class in order to apply "composite" design pattern which allows better
  separation.
- Block interface contains an additional method for the same reason as above.
- No block from the blocks hierarchy can be contained inside itself or anywhere lower in its hierarchy, because it leads
  to the infinite recursion.
