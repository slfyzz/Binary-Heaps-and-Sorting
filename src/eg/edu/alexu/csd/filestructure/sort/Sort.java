package eg.edu.alexu.csd.filestructure.sort;

import java.lang.reflect.Array;
import java.util.*;

public class Sort<T extends Comparable<T>> implements ISort<T> {
    @Override

    public IHeap<T> heapSort(ArrayList<T> unordered) {
        IHeap<T> binaryHeap = new BinaryHeap_2<>();
        binaryHeap.build(unordered);

        if (unordered == null || unordered.size() == 0)
            return binaryHeap;

        //unordered.clear();
        ((BinaryHeap_2<T>)binaryHeap).setSorted(true);

        while (binaryHeap.size() > 0) {
            binaryHeap.extract();
        }
        //Collections.reverse(unordered);
        ((BinaryHeap_2<T>)binaryHeap).setSize(unordered.size());

        return binaryHeap;
    }

    @Override
    public void sortSlow(ArrayList<T> unordered) {
        if (unordered == null || unordered.size() == 0)
            return;
        BubbleSort(unordered);
    }

    /**
     * implementing merge sort
     * @param unordered unordered elements
     */
    @Override
    public void sortFast(ArrayList<T> unordered) {
        if (unordered == null)
        {
            return;
        }

        /*List<T> sorted = this.mergeSort(unordered);

        unordered.clear();
        unordered.addAll(sorted);*/
        QuickSort(unordered);
       // Collections.sort(unordered);
    }
    /**
     * implement merge sort
     * @param unordered unordered elements
     * @return sorted list
     */
    private List<T> mergeSort(List<T> unordered) {
        if (unordered.size() > 1)
        {
            List<T> firstSub = this.mergeSort(unordered.subList(0, unordered.size() / 2));
            List<T> secondSub = this.mergeSort(unordered.subList(unordered.size() / 2, unordered.size()));

            return merge(firstSub, secondSub);
        }

        return unordered;
    }

    /**
     * merges two lists
     * @param first first list
     * @param second second list
     * @return merged list
     */
    private List<T> merge(List<T> first, List<T> second) {
        List<T> merged = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;
        while (index1 < first.size() || index2 < second.size())
        {
            if (index1 >= first.size())
            {
                merged.add(second.get(index2++));
            }
            else if (index2 >= second.size())
            {
                merged.add(first.get(index1++));
            }
            else
            {
                // Second list contains smaller element
                if (first.get(index1).compareTo(second.get(index2)) > 0)
                {
                    merged.add(second.get(index2++));
                }
                // First list contains smaller element
                else
                {
                    merged.add(first.get(index1++));
                }
            }
        }
        return merged;
    }

    private void BubbleSort(List<T> unOrdered)
    {
        T[] unSortedArr = unOrdered.toArray((T[]) Array.newInstance(unOrdered.get(0).getClass(), 0));

        for (int i = 0; i < unOrdered.size(); i++)
            for (int j = 0; j < unOrdered.size() - 1 - i; j++)
                if (unSortedArr[j].compareTo(unSortedArr[j + 1]) > 0)
                    swap(unSortedArr, j, j + 1);

        unOrdered.clear();
        unOrdered.addAll(Arrays.asList(unSortedArr));
    }

    private void QuickSort(List<T> unOrdered)
    {
        if (unOrdered == null || unOrdered.size() <= 1)
            return;

        T[] unSortedArr = unOrdered.toArray((T[]) Array.newInstance(unOrdered.get(0).getClass(), 0));
        randomQuickSort(unSortedArr, 0, unOrdered.size() - 1);

        unOrdered.clear();
        unOrdered.addAll(Arrays.asList(unSortedArr));
    }

    private void randomQuickSort(T[] unOrdered, int start, int end)
    {
        if (end <= start)
            return;
        //Choosing the pivot Randomly
        Random random = new Random();

        int pivot = random.nextInt(end - start + 1) + start;
        swap(unOrdered, pivot, end); //having the pivot in the last of the array

        int barrier = start - 1;
        for (int i = start; i < end; i++)
        {
            // if its smaller than the pivot, then  it should be in the left side
            if (unOrdered[i].compareTo(unOrdered[end]) < 0)
            {
                barrier++;
                swap(unOrdered, barrier, i);
            }
        }
        barrier++;
        swap(unOrdered, barrier, end);
        randomQuickSort(unOrdered, start, barrier - 1);
        randomQuickSort(unOrdered, barrier + 1, end);
    }
    private void swap(T [] unOrdered, int ind1, int ind2)
    {
        T temp = unOrdered[ind1];
        unOrdered[ind1] = unOrdered[ind2];
        unOrdered[ind2] = temp;
    }
}
