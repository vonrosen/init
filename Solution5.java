import java.util.Scanner;

public class Solution5 {

    static class Operation {
        int beginIndex;
        int endIndex;
        long value;
        //int overlapIndex = 0;
        //Integer [] overlaps = new Integer[100000];
        //byte [] overlaps = new byte[100_000];
        //List<Integer> overlaps = new ArrayList<Integer>();
        //int [] overlaps = new int[100_000];

        Operation(int beginIndex, int endIndex, long value) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.value = value;
        }

//        void addOverlap(int index) {
//        	overlaps[overlapIndex++] = index;
//        }

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
        Operation [] operations = null;

        Scanner in = new Scanner(System.in);
        in.nextInt();
        int numberOfOperations = in.nextInt();
        operations = new Operation[numberOfOperations];

        for(int a0 = 0; a0 < numberOfOperations; a0++){
            int beginIndex = in.nextInt();
            int endIndex = in.nextInt();
            long value = in.nextInt();

            operations[a0] = new Operation(beginIndex, endIndex, value);
        }
        in.close();

        long totalSum = 0;
        for (int i = 0; i < operations.length; ++i) {
        	long localSum = operations[i].value;

        	if (i < operations.length - 1) {
            	for (int k = i + 1; k < operations.length; ++k) {
            		Operation op1 = operations[i];
            		Operation op2 = operations[k];

                	if ((op2.beginIndex >= op1.beginIndex && op2.beginIndex <= op1.endIndex) ||
                			(op1.beginIndex >= op2.beginIndex && op1.beginIndex <= op2.endIndex)) {

                		localSum += operations[k].value;
                	}
            	}
        	}

        	if (localSum > totalSum) {
        		totalSum = localSum;
        	}
        }

        System.out.println(totalSum);
    }

    private static boolean isOverlap(Operation op1, Operation op2) {
    	if (op1.beginIndex == op2.beginIndex) return true;
    	if (op1.endIndex == op2.endIndex) return true;

    	if (op2.beginIndex > op1.beginIndex && op2.beginIndex < op1.endIndex) return true;

    	if (op1.beginIndex > op2.beginIndex && op1.beginIndex < op2.endIndex) return true;

    	return false;
    }
}
