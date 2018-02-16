import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SolutionThreads2 {

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
			System.out.println("Number of ops: " + ops.size());
			for (Operation operation : ops) {
	            int realBeginIndex = operation.beginIndex - 1;
	            int realEndIndex = operation.endIndex - 1;
	            long value = operation.value;

	            for (int index = realBeginIndex; index <= realEndIndex; ++index) {
	            	array[index] += value;
	            }
			}

			System.out.println("done with thread");
			System.out.println(array[272197]);
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

        int threadsToSpawn = 20;

        int opBegin = 0;
        int opsPerThread = operations.size() / threadsToSpawn;

        ThreadGroup tg = new ThreadGroup("calculator");
        for (int i = 0; i < threadsToSpawn; ++i) {
			new CalculatorThread(tg, operations.subList(opBegin, opBegin + opsPerThread),
					lengthOfList).start();
			opBegin += opsPerThread;
        }

        while (tg.activeCount() > 0) {

        }

        System.out.println("done");
    }
}
