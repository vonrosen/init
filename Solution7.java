import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution7 {

    static class Operation {
        int beginIndex;
        int endIndex;
        long value;

        Operation(int beginIndex, int endIndex, long value) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.value = value;
        }

        @Override
		public String toString() {
        	return this.beginIndex + " " + this.endIndex + " " + this.value;
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
				if (o1.endIndex < o2.endIndex) {
					return -1;
				}
				else if (o1.endIndex > o2.endIndex) {
					return 1;
				}

				return 0;
			}
        });

        List<Operation> newOperations = new ArrayList<Operation>();
        for (Operation operation: operations) {
        	List<Operation> overlaps = createOperationsFromOverlaps(operation, operations);

        	if (overlaps.size() > 0) {
//        		System.out.println(operation);
//        		System.out.println(overlaps.size());
//
//        		for (Operation ov : overlaps) {
//        			System.out.println(ov);
//        		}

        		operation.endIndex = overlaps.get(0).beginIndex - 1;
        		newOperations.addAll(overlaps);

//        		return;
        	}
        }

        operations.addAll(newOperations);

//        Operation op1 = new Operation(9973617, 9975011, 2085), op2 = new Operation(57, 2203477, 2085);
//
//        System.out.println(op1);
//        System.out.println(op2);
//
//        System.out.println(overlap(op1, op2));
//        if (1 == 1) {
//        	return;
//        }


        Collections.sort(operations, new Comparator<Operation>() {
			@Override
			public int compare(Operation o1, Operation o2) {
				// TODO Auto-generated method stub
				if (o1.endIndex < o2.endIndex) {
					return -1;
				}
				else if (o1.endIndex > o2.endIndex) {
					return 1;
				}

				if (o1.beginIndex < o2.beginIndex) {
					return -1;
				}
				else if (o1.beginIndex > o2.beginIndex) {
					return 1;
				}

				return 0;
			}
        });

//        System.out.println(operations.size());
//
        for (Operation operation : operations) {
        	System.out.println(operation);
        }

        long sum = 0;
        long localSum = 0;
        long lastEndIndex = 0;
        for (Operation operation : operations) {
        	if (lastEndIndex == 0) {
        		localSum = operation.value;
        	}
        	else {
        		if (lastEndIndex == operation.endIndex) {
        			localSum += operation.value;
        		}
        		else {
        			if (localSum > sum) {
        				sum = localSum;
        			}

        			localSum = 0;
        		}
        	}

        	lastEndIndex = operation.endIndex;
        }

        if (localSum > sum) {
        	sum = localSum;
        }

        System.out.println(sum);

//        int opIndex = 0;
//        long localSum = operations.get(0).value;
//        long sum = 0;
//        Operation lastOperation = null;
//        for (Operation operation : operations) {
//        	if (opIndex > 0) {
//        		if (overlap(operation, lastOperation)) {
//        			localSum += operation.value;
//        		}
//        	}
//
//
//
//
//
//
//        	if (opIndex > 0) {
//        		for (int i = opIndex; i > 0; --i) {
//        			if (overlap(operations.get(i), operations.get(i - 1))) {
//        				localSum += operations.get(i).value;
//        			}
//        			else {
//        				if (localSum > sum) {
//        					sum = localSum;
//        				}
//
//        				localSum = 0;
//        			}
//        		}
//
//        		if (localSum > sum) {
//        			sum = localSum;
//        		}
//
//        		localSum = 0;
//        	}
//
//        	lastOperation = operation;
//        	++opIndex;
//        }

//        System.out.println(sum);
    }

    private static List<Operation> createOperationsFromOverlaps(Operation operation, List<Operation> operationsSortedByBeginIndex) {
    	List<Operation> newOperations = new ArrayList<Operation>();

    	for (Operation sortedOperation : operationsSortedByBeginIndex) {
			if (operation.beginIndex > sortedOperation.beginIndex) {
				break;
			}

			if (sortedOperation.beginIndex == operation.beginIndex && sortedOperation.endIndex == operation.endIndex
					&& sortedOperation.value == operation.value) {
				continue;
			}

    		if (overlap(operation, sortedOperation)) {
    			newOperations.add(new Operation(sortedOperation.beginIndex, sortedOperation.endIndex, operation.value));
    		}
    	}

    	return newOperations;
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
    	return ((o1.beginIndex <= o2.beginIndex && o2.beginIndex <= o1.endIndex) ||
    			(o2.beginIndex <= o1.beginIndex && o1.beginIndex <= o2.endIndex));
    }
}
