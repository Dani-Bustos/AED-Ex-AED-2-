package aed;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListaEnlazadaTests {
private <T extends Comparable<T>> boolean isInOrder(T[] array) {
    for (int i = 0; i < array.length - 1; i++) {
        if (array[i].compareTo(array[i + 1]) > 0) {
            return false; // If any element is greater than the next one, the array is not in order
        }
    }
    return true; // The array is in order
}

@Test
void mergeSortTest(){
    Sorting a = new Sorting();
     // Specify the size of the array
     int arraySize = 20;

     // Create an array to store random numbers
     Integer[] randomArray = new Integer[arraySize];

     // Create an instance of the Random class
     Random random = new Random();

     // Fill the array with random numbers
     for (int i = 0; i < arraySize; i++) {
         // Generate a random integer and store it in the array
         randomArray[i] = (Integer) random.nextInt(100); // Change 100 to the desired upper limit
     }
    Integer[] b = a.mergeSort(randomArray, 0, arraySize-1);  
    
    assertTrue(isInOrder(b));   
}
   
@Test
void TestFrecuencia(){
    Integer[] a = new Integer[]{7,7,4,2};
    Sorting instancia = new Sorting();
    Integer[] res = instancia.OrdenarPorFrecuencia(a);
    Integer[] correcto = {7,7,2,4};
    assertArrayEquals(res, correcto);

    
}
    
}
