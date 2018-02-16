import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution2 {

    static class Operation {
        int beginIndex;
        int endIndex;
        long value;
//        Map<Integer, Integer> overlapPositions = new HashMap<Integer, Integer>();
//        List<Integer> overlapPositions = new ArrayList<Integer>();
        long [][] overlapPositions = null;

        Operation(int beginIndex, int endIndex, long value, int numberOfOperations) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.value = value;
            this.overlapPositions = new long[numberOfOperations][2];
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

            operations.add(new Operation(beginIndex, endIndex, value, numberOfOperations));
        }
        in.close();

        for (int i = 0; i < operations.size(); ++i) {
        	Operation currentOperation = operations.get(i);

        	for (int k = i + 1; k < operations.size(); ++k) {
        		if (operations.get(k).beginIndex <= currentOperation.endIndex &&
        				operations.get(k).endIndex >= currentOperation.beginIndex) {

        			currentOperation.overlapPositions[k][0] = 1;
        			//operations.get(k).overlapPositions[i][0] = 1;

        			for (int j = currentOperation.beginIndex; j <= operations.get(k).endIndex; ++j) {
        				if (j >= operations.get(k).beginIndex && j <= operations.get(k).endIndex) {

        				}
        				else {
        					break;
        				}

        			}

        			currentOperation.overlapPositions[k][1] = 1;

        			//System.out.println(k + " overlaps with " + i);
        		}
        	}

        	//System.out.println("number overlaps " + nov);


        	//if (i == 0) return;

        	//System.out.println(i);
        }

        System.out.println("almost done");

        long max = 0;
        for (int i = 0; i < operations.size(); ++i) {
        	Operation currentOperation = operations.get(i);

        	long localMax = currentOperation.value;
        	int index = 0;
        	for (byte overlapPosition : currentOperation.overlapPositions) {
        		if (overlapPosition == 1) {
        			System.out.println(i + " overlaps with " + index);
        			//localMax += operations.get(index).value;


        		}

        		++index;
        	}

        	System.out.println("local max for " + i + " is " + localMax);

        	if (localMax > max) {
        		max = localMax;
        	}
        }

        System.out.println(max);
    }

}
