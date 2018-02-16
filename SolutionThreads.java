import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionThreads {

	static class CalculatorThread extends Thread {
		List<Operation> ops = null;
		long [] array = null;

		CalculatorThread(ThreadGroup tg, List<Operation> ops, int arrayLength) {
			super(tg, "calc");
			this.ops = ops;
			this.array = new long[arrayLength];
		}

		@Override
		public void run() {
			for (Operation operation : ops) {
	            int realBeginIndex = operation.beginIndex - 1;
	            int realEndIndex = operation.endIndex - 1;
	            long value = operation.value;

	            for (int index = realBeginIndex; index <= realEndIndex; ++index) {
	            	array[index] += value;
	            }
			}
			System.out.println("done with thread");
		}
	}

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
        int lengthOfList = in.nextInt();
        int numberOfOperations = in.nextInt();
        for(int a0 = 0; a0 < numberOfOperations; a0++){
            int beginIndex = in.nextInt();
            int endIndex = in.nextInt();
            long value = in.nextInt();

            operations.add(new Operation(beginIndex, endIndex, value));
        }
        in.close();

        long [] array = new long[lengthOfList];

        int maxopsPerThread = 20;
        int threadToSpawn = operations.size() < maxopsPerThread ? 1 : operations.size() / maxopsPerThread;

        int opBegin = 0;
        int opEnd = maxopsPerThread;

        ThreadGroup tg = new ThreadGroup("calculator");

        System.out.println(threadToSpawn);
        for (int i = 0; i < threadToSpawn; ++i) {
        	if (operations.size() - 1 < opEnd) {
        		opEnd = operations.size();
        	}

			new CalculatorThread(tg, operations.subList(opBegin, opEnd),
					lengthOfList).start();

			opBegin += maxopsPerThread;
			opEnd += maxopsPerThread;
        }

        while (tg.activeCount() > 0) {

        }

        System.out.println("done");
    }
}
