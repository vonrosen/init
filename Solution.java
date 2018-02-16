import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

    static class Operation {
        int beginIndex;
        int endIndex;
        long value;
        long max;
        //List<Integer> overlapOperations = new ArrayList<Integer>();


        Operation(int beginIndex, int endIndex, long value) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.value = value;
        }
    }

    final static List<Operation> operations = new ArrayList<Operation>();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int lengthOfList = in.nextInt();
        int numberOfOperations = in.nextInt();
        for(int a0 = 0; a0 < numberOfOperations; a0++){
            int beginIndex = in.nextInt();
            int endIndex = in.nextInt();
            long value = in.nextInt();

            operations.add(new Operation(beginIndex, endIndex, value));
        }
        in.close();

        Collections.sort(operations, new Comparator<Operation>() {

			@Override
			public int compare(Operation o1, Operation o2) {
				// TODO Auto-generated method stub
				if (o1.beginIndex < o2.beginIndex) {
					return -1;
				}
				else if (o1.beginIndex > o2.beginIndex) {
					return 1;
				}

				return 0;
			}
        });

//        long max = 0;
//        Operation maxBeginOperation = operations.get(0);
//        for (Operation operation : operations.subList(1, operations.size())) {
//            if (maxBeginOperation.beginIndex <= operation.endIndex) {
//            	long m = maxBeginOperation.value + operation.value;
//
//            	if (m > max) {
//            		max = m;
//            	}
//            }
//            else {
//            	if (operation.value > max) {
//            		max = operation.value;
//            	}
//            }
//        }

        int index = 1;
        for (Operation operation : operations) {
        	for (Operation op : operations.subList(index, operations.size())) {
        		if (index == operations.size()) break;

        		if (op.beginIndex <= operation.endIndex) {
        			operation.overlapOperations.add(index);
        		}
        	}
        }
    }

//    private static long getMax(Operation operation, int index, List<Operation> operations) {
//    	if (index == 0) {
//    		return operation.value;
//    	}
//
//    	long max = 0;
//    	for (Operation op : operations.subList(0, index + 1)) {
//    		if (operation.beginIndex <= op.endIndex) {
//    			max = operation.value + op.value;
//    		}
//
//
//    	}
//
//    }
//
}
