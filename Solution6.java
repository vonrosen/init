import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution6 {

    static class Operation {
        int beginIndex;
        int endIndex;
        long value;

        Operation(int beginIndex, int endIndex, long value) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.value = value;
        }

    }

    public static void main(String[] args) {
        List<Operation> operations = new ArrayList<Operation>();

        Scanner in = new Scanner(System.in);
        in.nextInt();
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

        int opIndex = 0;
        long localSum = operations.get(0).value;
        long sum = 0;
        Operation lastOperation = null;
        for (Operation operation : operations) {
        	if (opIndex > 0) {
        		for (int i = opIndex; i > 0; --i) {
        			if (overlap(operations.get(i), operations.get(i - 1))) {
        				localSum += operations.get(i).value;
        			}
        			else {
        				if (localSum > sum) {
        					sum = localSum;
        				}

        				localSum = 0;
        			}
        		}

        		if (localSum > sum) {
        			sum = localSum;
        		}

        		localSum = 0;
        	}

        	lastOperation = operation;
        	++opIndex;
        }

        System.out.println(sum);
    }

    /**
     * 1  10    = o1
     *  2   11  = o2
     *
     *  or
     *
     *  2   11  = o1
     * 1   10   = o2
     *
     * or
     *
     *  2 3
     * 1   4
     *
     * or
     *
     * 1     4
     *   2 3
     * @param o1
     * @param o2
     * @return
     */
    private static boolean overlap(Operation o1, Operation o2) {
    	return ((o1.beginIndex <= o2.beginIndex && o1.endIndex <= o2.endIndex) ||
    			(o1.beginIndex >= o2.beginIndex && o1.endIndex >= o2.endIndex) ||
    			(o1.beginIndex >= o2.beginIndex && o1.endIndex <= o2.endIndex) ||
    			(o1.beginIndex <= o2.beginIndex && o1.endIndex >= o2.endIndex));
    }

	private static List<Operation> findIndexOperations(long index, Operation [] operationsSortedByBeginIndex) {
		List<Operation> ops = new ArrayList<Operation>();

		for (Operation operation : operationsSortedByBeginIndex) {
			if (operation.beginIndex > index) {
				break;
			}

			if (operation.beginIndex <= index && operation.endIndex >= index) {
				ops.add(operation);
			}
		}

		return ops;
	}

	private static long findSumFromIndexOperations(long index, Operation [] operationsSortedByBeginIndex) {
		long sum = 0;
		for (Operation operation : operationsSortedByBeginIndex) {
			if (operation.beginIndex > index) {
				break;
			}

			if (operation.beginIndex <= index && operation.endIndex >= index) {
				sum += operation.value;
			}
		}

		return sum;
	}
}
