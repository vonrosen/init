import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution4 {

    static class Operation {
        int beginIndex;
        int endIndex;
        long value;

        Operation(int beginIndex, int endIndex, long value) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.value = value;
        }

//        @Override
//		public boolean equals(Object obj) {
//        	Operation op = (Operation)obj;
//
//        	if (this.beginIndex == op.beginIndex && this.endIndex == op.endIndex) {
//        		return true;
//        	}
//
//        	return false;
//        }
//
//        @Override
//		public int hashCode() {
//        	return this.beginIndex + this.endIndex;
//        }
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

        Operation maxEndOperation = operations.get(operations.size() - 1);

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

        List<Operation> finalOperations = new ArrayList<Operation>();

        int lastBeginIndex = -1;
        int lastEndIndex = -1;
        int opIndex = 0;
        for (Operation operation : operations) {

        	if (lastBeginIndex == operation.beginIndex && lastEndIndex == operation.endIndex) {
        		finalOperations.get(opIndex - 1).value += operation.value;
        		continue;
        	}

        	finalOperations.add(operation);

            lastBeginIndex = operation.beginIndex;
            lastEndIndex = operation.endIndex;
            opIndex++;
        }

        Operation minBeginOperation = finalOperations.get(0);

        long beginIndex = minBeginOperation.beginIndex;
        long endIndex = maxEndOperation.endIndex;
        long sum = 0;

        for (long index = beginIndex; index <= endIndex; ++index) {
			long localSum = findSumFromIndexOperations(index, finalOperations);

        	if (localSum > sum) {
        		sum = localSum;
        	}
        }

        System.out.println(sum);
    }

	private static List<Operation> findIndexOperations(long index, List<Operation> operationsSortedByBeginIndex) {
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

	private static long findSumFromIndexOperations(long index, List<Operation> operationsSortedByBeginIndex) {
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
