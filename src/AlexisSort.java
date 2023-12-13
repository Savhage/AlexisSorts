import java.util.Random;
public class AlexisSort {
    private int[] arr;
    //used swap to save time typing out every time I needed to switch positions
    public void swap(int in1, int in2){
        int temp=arr[in2];
        arr[in2]=arr[in1];
        arr[in1]=temp;
    }
    //Checks each index i against all the others and swaps as needed
    public int[] bubbleSort(int[] input){
        arr=input;
        boolean check=true;
        while(check){
            check=false;
            for(int i=0;i<arr.length-1;i++){
                if (arr[i] > arr[i+1]) {
                    swap(i,i+1);
                    check=true;
                }
            }
        }
        return arr;
    }
    //Finds smallest index after current and swaps positions
    public int[] selectSort(int[] input){
        arr=input;
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[minIndex]>arr[j]){
                    minIndex=j;
                }
            }
            swap(i,minIndex);
        }
        return arr;
    }
    //checks backwards to see if any swaps need to be made
    public int[] insertionSort(int[] input){
        arr=input;
        for(int i=1;i<arr.length;i++){
            int pos=i;
            int j=i-1;
            while(j>=0 && arr[j]>arr[pos]){
                swap(j,pos);
                pos--;
                j--;
            }
        }
        return arr;
    }
    //Splits the array down into individual pieces and then recursively builds it back up in order.
    public int[] mergeSort(int[] input){
        if(input.length==1){
            return input;
        }
        int[] left=new int[input.length/2];
        int[] right=new int[input.length-left.length];
        for(int i=0;i<left.length;i++){
            left[i]=input[i];
        }
        for(int i=0;i<right.length;i++){
            right[i]=input[left.length+i];
        }
        left=mergeSort(left);
        right=mergeSort(right);
        return merge(left, right, input);
    }
    //Used to meger the split arrays in mergeSort
    private int[] merge(int[] left, int[] right, int[] input){
        int i=0;
        int j=0;
        int k=0;
        while(j<left.length && k<right.length){
            if(left[j]<right[k]){
                input[i]=left[j];
                j++;
                i++;
            }
            else{
                input[i]=right[k];
                i++;
                k++;
            }
        }
        while(j<left.length){
            input[i]=left[j];
            i++;
            j++;
        }
        while(k<right.length){
            input[i]=right[k];
            i++;
            k++;
        }
        return input;
    }
    //public method that initiates the private method after assigning the input to class variable
    //https://www.youtube.com/watch?v=Vtckgz38QHs
    public int[] quickSort(int[] input){
        arr=input;
        internalQuickSort(0,arr.length-1);
        return arr;
    }
    //Recursively takes the end point and finds its correct position where all numbers before it are smaller and all numbers after are bigger
    // then does the same thing for each side of pivot point
    private void internalQuickSort(int min, int max){
        if(max<min){

        }
        else {
            int pivotPoint = partition(min, max);
            internalQuickSort(min, pivotPoint - 1);
            internalQuickSort(pivotPoint + 1, max);
        }
    }
    //this method moves the numbers so that the max index number is exactly where it needs to be so that no number before it is bigger
    //and no number after is small
    private int partition(int min, int max) {
        int j=min-1;
        int pivot=arr[max];
        for(int i=min; i<=max-1; i++){
            if(arr[i]<pivot){
                j++;
                swap(i,j);
            }
        }
        j++;
        swap(j,max);
        return j;
    }
    //This method looks at an array as if it was a tree with a parent potentially having a left and right child. By moving down it will place
    //the number in the respective positions so that it can be placed in a stack. I cut it off before putting it in a stack so the numbers are in
    //reverse order
    //https://tutorialhorizon.com/algorithms/heap-sort-java-implementation/
    public int[] heapSort(int[] input){
        arr=input;
        for(int i=(arr.length/2-1);i>=0;i--){
            heapify(i);
        }
        for(int i=arr.length-1;i>=0;i--){
            swap(0,i);
            heapify(0);
        }
        return arr;
    }
    //Made this a max heap and moves smaller numbers down the tree either to the left or right child
    private void heapify(int index){
        int largest=index;
        int l=2*index+1;
        int r=2*index+2;
        if(hasLeftChild(index) && arr[largest]<arr[l]){
            largest=l;
        }
        if (hasRightChild(index) && arr[largest]<arr[r]){
            largest=(r);
        }
        if(index!=largest){
            swap(index, largest);
            heapify(largest);
        }

    }
    //boolean to make my life easier. if the index that would belong to the left child of current index is larger than the size of our index
    //it returns false
    private boolean hasLeftChild(int index){
        if((index*2+1)>=arr.length){
            return false;
        }
        else{
            return true;
        }
    }
    //same as previous except checking for right child
    private boolean hasRightChild(int index){
        if((index*2+2)>=arr.length){
            return false;
        }
        else{
            return true;
        }
    }
    public int[] shellSort(int[] input){
        arr=input;
        for(int gap=arr.length/2; gap>0; gap/=2){
            for(int i=gap;i<arr.length;i++){
                int key=arr[i];
                int j=i;
                while(j>=gap && arr[j-gap]>key){
                    arr[j]=arr[j-gap];
                    j-=gap;
                }
                arr[j]=key;
            }
        }
        return arr;
    }
    private int[] generateNumbers(int n){
        int[] temp=new int[n];
        Random rand=new Random();
        for(int i=0;i<temp.length;i++){
            temp[i]=rand.nextInt(n);
        }
        return temp;
    }
    public void calculateTimes(){
        calculateBubble();
        System.out.println();
        calculateSelectSort();
        System.out.println();
        calculateInsertion();
        System.out.println();
        calculateMergeSort();
        System.out.println();
        calculateHeapSort();
        System.out.println();
        calculateShellSort();
        System.out.println();
        calculateQuickSort();
    }
    private void calculateBubble(){
        double start, finish;

        System.out.println("Bubble Sort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        bubbleSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));


        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        bubbleSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));


        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        bubbleSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));

    }
    private void calculateSelectSort(){
        double start, finish;
        System.out.println("Select Sort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        selectSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));


        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        selectSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));


        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        selectSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));
    }
    private void calculateInsertion(){
        double start, finish;
        System.out.println("Insertion Sort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        insertionSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));

        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        insertionSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));

        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        insertionSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));
    }
    private void calculateMergeSort(){
        double start, finish;
        System.out.println("Merge Sort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        mergeSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));


        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        mergeSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));


        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        mergeSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));
    }
    private void calculateHeapSort(){
        double start, finish;
        System.out.println("Heap Sort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        heapSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));

        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        heapSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));

        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        heapSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));
    }
    private void calculateQuickSort(){
        double start, finish;
        System.out.println("QuickSort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        quickSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));

        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        quickSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));

        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        quickSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));
    }
    private void calculateShellSort(){
        double start, finish;
        System.out.println("Shell Sort");
        arr=generateNumbers(100);
        start=System.currentTimeMillis();
        shellSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("100: " + (finish-start));

        arr=generateNumbers(10000);
        start=System.currentTimeMillis();
        shellSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("10000: " + (finish-start));

        arr=generateNumbers(200000);
        start=System.currentTimeMillis();
        shellSort(arr);
        finish=System.currentTimeMillis();
        System.out.println("200000: " + (finish-start));
    }


}
