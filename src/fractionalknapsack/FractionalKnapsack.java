package fractionalknapsack;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack {

    public static double getMaxValue(Item[] items, int capacity) {
      
       Arrays.sort(items, Comparator.comparingDouble((Item item) -> (double)item.value / item.weight).reversed());

       double totalValue = 0.0; // Total value collected in the knapsack

       for (Item item : items) {
           if (capacity <= 0) break; // If the knapsack is full, exit the loop

           if (item.weight <= capacity) {
               // Take the whole item
               totalValue += item.value;
               capacity -= item.weight;
           } else {
               // Take the fraction of the remaining item
               totalValue += (double) item.value * capacity / item.weight;
               capacity = 0; // The knapsack is now full
           }
       }
       return totalValue;
   }

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

       // Input the number of items and knapsack capacity
       System.out.println("Enter number of items and knapsack capacity:");
       int n = scanner.nextInt();
       int capacity = scanner.nextInt();

       Item[] items = new Item[n];
       System.out.println("Enter value and weight for each item:");
       for (int i = 0; i < n; i++) {
           items[i] = new Item(scanner.nextInt(), scanner.nextInt());
       }

       // Calculate and display the maximum value
       double maxValue = getMaxValue(items, capacity);
       System.out.printf("Maximum value in knapsack = %.2f\n", maxValue);

       scanner.close();
   }
}

