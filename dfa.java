import java.util.HashMap;
import java.util.Arrays;

public class dfa {
    private HashMap<String, String[]> transitionFunction;
    private String currentState;
    private String[] goalStates;

    public dfa(String representation) {
        String[] goalStatesSplit = representation.split("#");
        this.goalStates = goalStatesSplit[1].split(",");
        this.currentState = "0";
        setTransitionFunction(goalStatesSplit[0].split(";"));

    }

    private void setTransitionFunction(String[] transitionFunction) {
        this.transitionFunction = new HashMap<String, String[]>();
        for (int i = 0; i < transitionFunction.length; i++) {
            String[] transition = transitionFunction[i].split(",");
            String[] destinationStates = { transition[1], transition[2] };
            this.transitionFunction.put(transition[0], destinationStates);
        }
    }

    public boolean run(String input) {
        this.currentState = "0";
        for (int i = 0; i < input.length(); i++) {
            int index = Integer.parseInt("" + input.charAt(i));
            String nextState = this.transitionFunction.get(this.currentState)[index];
            this.currentState = nextState;
        }
        if (Arrays.asList(goalStates).contains(this.currentState))
            return true;
        return false;
    }

    public static void main(String[] args) {
        dfa dfaA = new dfa("0,0,1;1,2,1;2,0,3;3,3,3#1,3");
        dfa dfaB = new dfa("0,3,1;1,2,1;2,2,1;3,3,3#2");
        String[] firstTestCases = { "11", "01010100", "100010010", "101", "0010" };
        String[] secondTestCases = { "010", "10101010", "10010", "100010011", "010010" };
        for (int i = 0; i < firstTestCases.length; i++) {
            boolean result = dfaA.run(firstTestCases[i]);
            System.out.println("FOR TEST CASE " + i + result);
        }

        for (int i = 0; i < secondTestCases.length; i++) {
            boolean result = dfaB.run(secondTestCases[i]);
            System.out.println("FOR TEST CASE " + i + result);
        }

    }
}
