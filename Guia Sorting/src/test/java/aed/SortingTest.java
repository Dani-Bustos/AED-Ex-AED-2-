package aed;
import java.util.Random;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortingTest {
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
    Ordenadores a = new Ordenadores();
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
     a.mergeSort(randomArray, 0, arraySize-1);  
    
    assertTrue(isInOrder(randomArray));   
}
   
@Test
void TestFrecuencia(){
    Integer[] a = new Integer[]{7,7,7,2,4,8,2};
    Sorting instancia = new Sorting();
    Integer[] res = instancia.OrdenarPorFrecuencia(a);
    Integer[] correcto = {7,7,7,2,2,4,8};
    assertArrayEquals(res, correcto);

    
}

@Test
void RepeticionesContinuas(){
    Sorting instancia = new Sorting();
    int[] a = new int[]{3,3,3,3,2,2,6,6,6,6};
    int[] b = new int[]{2,4,6,3,3,3};
    int[] res = instancia.NsecuenciasRepetidasUnoRandom(a, b, 3);
    int[] correcto = new int[]{2,2,2,3,3,3,3,3,3,3,4,6,6,6,6,6};
    assertArrayEquals(res, correcto);
}  
    

@Test
void RepeticionesContinuasMismosElementosENAyB(){
    Sorting instancia = new Sorting();
    int[] a = new int[]{3,3,2,2,6,6,6};
    int[] b = new int[]{2,6,3,3};
    int[] res = instancia.NSecuenciasRepetidasBmismosElementos(a, b, 3);
    int[] correcto = new int[]{2,2,2,3,3,3,3,6,6,6,6};
    assertArrayEquals(res, correcto);
}
@Test
void OrdenarEscaleras(){
    Sorting instancia = new Sorting();
    int[] a = new int[]{5,6,8,9,10,7,8,9,20,15};
    int[] res = instancia.OrdenaEscaleras(a);
    int[] correcto = new int[]{7,8,9,8,9,10,5,6,15,20};
    assertArrayEquals(correcto, res);
}



    @Test
    public void testEmptyArray() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {};
        ordenadores.AVLSort(A);
        Integer[] expected = {};
        assertArrayEquals(expected, A);
    }

    @Test
    public void testSingleElementArray() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {5};
        ordenadores.AVLSort(A);
        Integer[] expected = {5};
        assertArrayEquals(expected, A);
    }

    @Test
    public void testDistinctElementsArray() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {3, 1, 4, 2, 5};
        ordenadores.AVLSort(A);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, A);
    }

    @Test
    public void testDuplicateElementsArray() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {3, 1, 4, 2, 3, 5, 1};
        ordenadores.AVLSort(A);
        Integer[] expected = {1, 1, 2, 3, 3, 4, 5};
        assertArrayEquals(expected, A);
    }

    @Test
    public void testAllElementsEqualArray() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {2, 2, 2, 2, 2};
        ordenadores.AVLSort(A);
        Integer[] expected = {2, 2, 2, 2, 2};
        assertArrayEquals(expected, A);
    }

    @Test
    public void testArrayWithNullElements() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {null, 3, null, 1, 4, 2, null, 5};
        ordenadores.AVLSort(A);
        Integer[] expected = {null, null, null, 1, 2, 3, 4, 5};
        assertArrayEquals(expected, A);
    }

    @Test
    public void testReverseSortedOrderArray() {
        Ordenadores ordenadores = new Ordenadores();
        Integer[] A = {5, 4, 3, 2, 1};
        ordenadores.AVLSort(A);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, A);
    }
     
    

    @Test
    public void CountintSortTest(){
        Ordenadores ord = new Ordenadores<>();
        int[] A = {5,2,6,2,10,6,2};
        ord.countingSort(A,10);
        int[] expected = {2,2,2,5,6,6,10};
        assertArrayEquals(A,expected);
    }

    @Test
    public void RaizDeNFueraDeRangoTest(){
        Sorting s = new Sorting();
        Ordenadores ord = new Ordenadores<>();
        int[] A = {4,2,3,1};
        Integer[] res = s.RaizDeNNumerosFueraDeRango(A, 3,4);
        assertEquals(true,isInOrder(res));

        }

    @Test
    public void Binaria1eraAparicionEnVector(){
        Ordenadores ord = new Ordenadores<>();
        Vector<Integer> A = new Vector<Integer>();
        
        A.add(1);A.add(2);A.add(2);A.add(2);A.add(3);
        assertEquals(1, ord.BinariaEnRangoVector(A,2));
    }


    @Test
    public void testBasicCase() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1, 2, 3, 4, 5));
        int result = ordenadores.BinariaEnRangoVector(A, 3);
        assertEquals(2, result);
    }

    @Test
    public void testElementAtBeginning() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1, 2, 3, 4, 5));
        int result = ordenadores.BinariaEnRangoVector(A, 1);
        assertEquals(0, result);
    }

    @Test
    public void testElementAtEnd() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1, 2, 3, 4, 5));
        int result = ordenadores.BinariaEnRangoVector(A, 5);
        assertEquals(4, result);
    }

    @Test
    public void testElementRepeated() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1, 2, 3, 3, 4, 5));
        int result = ordenadores.BinariaEnRangoVector(A, 3);
        assertEquals(2, result);
    }

    @Test
    public void testArrayWithSingleElement() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1));
        int result = ordenadores.BinariaEnRangoVector(A, 1);
        assertEquals(0, result);
    }

    @Test
    public void testArrayWithTwoElements() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1, 2));
        int result = ordenadores.BinariaEnRangoVector(A, 2);
        assertEquals(1, result);
    }

    @Test
    public void testElementPresentInMiddle() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>(java.util.Arrays.asList(1, 2, 3, 4, 5));
        int result = ordenadores.BinariaEnRangoVector(A, 3);
        assertEquals(2, result);
    }

    @Test
    public void testLargeArray() {
        Ordenadores ordenadores = new Ordenadores();
        Vector<Integer> A = new Vector<>();
        for (int i = 1; i <= 10000; i++) {
            A.add(i);
        }
        
        int result = ordenadores.BinariaEnRangoVector(A, 5000);
        assertEquals(4999, result);
    }
}









