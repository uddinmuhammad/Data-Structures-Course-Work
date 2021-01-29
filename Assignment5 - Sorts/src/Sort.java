import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sort {

    ArrayList<Data> dataList = new ArrayList<>();
    ArrayList<int[]> arraysToSort = new ArrayList<>();
    int bubbleCounter, quickcounter, mergeCounter;

    public void readData() {
        String dataFile = "src/Data.txt";

        try {
            Scanner scanner = new Scanner(new File(dataFile));

            while(scanner.hasNext()){
                String heading;
                int[] records;
                int numberOfRecords;
                Data data;

                heading = scanner.nextLine();
                System.out.println(heading);
                numberOfRecords = findNumberOfRecords(heading.substring(0,2));
                records = new int[numberOfRecords];

                for(int i=0; i < numberOfRecords; i++){
                    records[i] = scanner.nextInt();
                    System.out.print(records[i] +", ");
                }

                data = new Data(heading, records);
                dataList.add(data);

                if(scanner.hasNext())
                    scanner.nextLine();
                System.out.println();

            }

        } catch(FileNotFoundException fE) {
            System.out.println(fE.getMessage());
        }
    }

    public int findNumberOfRecords(String str){
        return Integer.parseInt(str);
    }

    public void sortAllData(){
        ArrayList<int[]> arrBubbleSort = new ArrayList<>();
        ArrayList<int[]> arrQuickSort = new ArrayList<>();
        ArrayList<int[]> arrMergeSort = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++){
            int[] temp = dataList.get(i).getData();

            int[] bubbleSort = new int[dataList.get(i).getData().length];
            int[] quickSort = new int[dataList.get(i).getData().length];
            int[] mergeSort = new int[dataList.get(i).getData().length];
            for(int j = 0; j < dataList.get(i).getData().length; j++){
                bubbleSort[j] = temp[j];
                quickSort[j] = temp[j];//tempTwo[j];
                mergeSort[j] = temp[j];//tempThree[j];
            }
            arrBubbleSort.add(bubbleSort);
            arrQuickSort.add(quickSort);
            arrMergeSort.add(mergeSort);
        }

        for(int i = 0; i < dataList.size(); i++ ){

            System.out.println(dataList.get(i).getHeading());

            bubbleSort(arrBubbleSort.get(i));
            System.out.print("Bubble Sort: ");
            printArray(arrBubbleSort.get(i));
            System.out.println("Number of Comparison: " +bubbleCounter);

            quickSort(arrQuickSort.get(i), 0, arrQuickSort.get(i).length-1);
            System.out.print("Quick Sort: ");
            printArray(arrQuickSort.get(i));
            System.out.println("Number of Comparison: " +quickcounter);


            mergeSort(arrMergeSort.get(i), 0, arrMergeSort.get(i).length-1);
            System.out.print("Merge Sort: ");
            printArray(arrMergeSort.get(i));
            System.out.println("Number of Comparison: " +mergeCounter);

            System.out.println(checkBestSort(bubbleCounter, quickcounter, mergeCounter));

            bubbleCounter = 0;
            mergeCounter = 0;
            quickcounter = 0;
            System.out.println();


        }

    }

    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - 1; j++) {
                bubbleCounter++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    void merge(int arr[], int l, int m, int r)
    {
        //counter++;
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        //counter++;
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        //counter++;
        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            mergeCounter++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;

            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            mergeCounter++;
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            mergeCounter++;
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void mergeSort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }


    public void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);

        }

   }

   private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);


        for (int j = begin; j < end; j++) {
            quickcounter++;
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
        }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }


    public String checkBestSort(int bubbleSort, int quickSort, int mergeSort){
        String smallest;
        if (bubbleSort <= quickSort && bubbleSort <= mergeSort) {
            return "Most Efficient Sort: Bubble Sort";
        } else if (quickSort <= mergeSort && quickSort <= bubbleSort) {
            return "Most Efficient Sort: Quick Sort";
        } else {
            return "Most Efficient Sort: Merge Sort";
        }

    }

    void printArray(int arr[]){
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }


}
