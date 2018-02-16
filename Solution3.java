import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution3 {

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
//        	if (op.beginIndex == this.beginIndex &&
//        			op.endIndex == this.endIndex &&
//        			op.value == this.value)
//        		return true;
//
//        	return false;
//        }
//
//        @Override
//		public int hashCode() {
//        	return this.beginIndex + this.endIndex + Long.hashCode(this.value);
//        }
    }

    public static void main(String[] args) {
        List<Operation> operations = new ArrayList<Operation>();

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

        Operation minBeginOperation = operations.get(0);

        long beginIndex = minBeginOperation.beginIndex;
        long endIndex = maxEndOperation.endIndex;
        long sum = 0;

        for (long index = beginIndex; index <= endIndex; ++index) {
			List<Operation> indexOperations = findIndexOperations(index, operations);

        	long localSum = 0;
        	for (Operation operation : indexOperations) {
        		localSum += operation.value;
        	}

        	if (localSum > sum) {
        		sum = localSum;
        	}
        }

        System.out.println(sum);
    }

    private static List<Operation> findIndexOperations(long index, List<Operation> operations) {
    	List<Operation> ops = new ArrayList<Operation>();

    	for (Operation operation : operations) {
    		if (operation.beginIndex <= index) {
    			if (operation.endIndex >= index) {
    				ops.add(operation);
    			}
    		}
    		else {
    			return ops;
    		}
    	}

    	return ops;
    }
}
