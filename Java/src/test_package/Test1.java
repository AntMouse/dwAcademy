package test_package;

import java.util.Arrays;

final class Example {
    private final int[] numbers;

    private Example(final int[] numbers) {
        this.numbers = Arrays.copyOf(numbers, numbers.length);
    }

    public static Example createNewInstance(int[] numbers) {
        return new Example(numbers);
    }

    public int[] getNumbers() {
        return Arrays.copyOf(numbers, numbers.length);
    }

    @Override
    public String toString() {
        return "Example{" +
                "numbers=" + Arrays.toString(numbers) +
                '}';
    }
}

public class Test1 {

	public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        Example example = Example.createNewInstance(numbers);
        System.out.println("Created: " + example);

        int[] retrievedNumbers = example.getNumbers();
        System.out.println("Retrieved: " + Arrays.toString(retrievedNumbers));

        numbers[0] = 100;
        System.out.println("Any change?: " + example);
        System.out.println("Original numbers: " + Arrays.toString(numbers));
        System.out.println("Original numbers: " + Arrays.toString(retrievedNumbers));
    }
}
