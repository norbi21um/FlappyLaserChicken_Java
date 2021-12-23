public class FlappyAgent {
    public static class StateDTO {
        public int birdPos;
        public int birdSpeed;
        public int objectType;
        public int objectDistance;
        public int objectHeight;

        public StateDTO(int birdPos, int birdSpeed, int objectType, int objectDistance, int objectHeight) {
            this.birdPos = birdPos;
            this.birdSpeed = birdSpeed;
            this.objectType = objectType;
            this.objectDistance = objectDistance;
            this.objectHeight = objectHeight;
        }

        @Override
        public String toString() {
            return "StateDTO{" +
                    "birdPos=" + birdPos +
                    ", birdSpeed=" + birdSpeed +
                    ", objectType=" + objectType +
                    ", objectDistance=" + objectDistance +
                    ", objectHeight=" + objectHeight +
                    '}';
        }
    }
    public static class QTable implements java.io.Serializable {
        public double[][][][][][] table;

        public QTable() {

        }

        public QTable(int[] stateSpaceSize, int actionDimension) {
            table = new double[stateSpaceSize[0]][stateSpaceSize[1]][stateSpaceSize[2]][stateSpaceSize[3]][stateSpaceSize[4]][actionDimension];
        }

        public double[] getActions(StateDTO state) {
            return table[state.birdPos][state.birdSpeed][state.objectType][state.objectDistance][state.objectHeight];
        }

        public QTable copy() {
            QTable res = new QTable();
            res.table = this.table.clone();
            return res;
        }
    }

    QTable qTable;
    int[] actionSpace;
    int nIterations;
    boolean test = false;

    public FlappyAgent(int[] observationSpaceSize, int[] actionSpace, int nIterations) {
        this.qTable = new QTable(observationSpaceSize,actionSpace.length);
        this.actionSpace = actionSpace;
        this.nIterations = nIterations;
    }

    public int step(StateDTO state) {
        int action = 0;
        double max = qTable.table[state.birdPos][state.birdSpeed][state.objectType][state.objectDistance][state.objectHeight][0];
        double r = Math.random();
        
        if (r < 0.01 && !test) {
        	if(r > 0.3 && r < 0.6 ) {
        		action = 1;
        	} else if(r > 0.6) {
        		action = 2;
        	}
        	
        }
        
        
        
        else {
        
        	if (max < qTable.table[state.birdPos][state.birdSpeed][state.objectType][state.objectDistance][state.objectHeight][1]) {
        		max = qTable.table[state.birdPos][state.birdSpeed][state.objectType][state.objectDistance][state.objectHeight][1];
        		action = 1;
        	}
        	if (max < qTable.table[state.birdPos][state.birdSpeed][state.objectType][state.objectDistance][state.objectHeight][2]) {
        		max = qTable.table[state.birdPos][state.birdSpeed][state.objectType][state.objectDistance][state.objectHeight][2];
        		action = 2;
        	}
        }
        return action;
    }

    public void epochEnd(int epochRewardSum) {
    		
    }

    public void learn(StateDTO oldState, int action, StateDTO newState, double reward) {
        double first = qTable.table[oldState.birdPos][oldState.birdSpeed][oldState.objectType][oldState.objectDistance][oldState.objectHeight][action];
        double second = qTable.table[newState.birdPos][newState.birdSpeed][newState.objectType][newState.objectDistance][newState.objectHeight][0];
        
        if (qTable.table[newState.birdPos][newState.birdSpeed][newState.objectType][newState.objectDistance][newState.objectHeight][1] > second){
            second = qTable.table[newState.birdPos][newState.birdSpeed][newState.objectType][newState.objectDistance][newState.objectHeight][1];
        }
        
        if (qTable.table[newState.birdPos][newState.birdSpeed][newState.objectType][newState.objectDistance][newState.objectHeight][2] > second){
            second = qTable.table[newState.birdPos][newState.birdSpeed][newState.objectType][newState.objectDistance][newState.objectHeight][2];
        }
        
        first = first + (0.7)*(reward + 0.5* (second) - first);
        qTable.table[oldState.birdPos][oldState.birdSpeed][oldState.objectType][oldState.objectDistance][oldState.objectHeight][action]=first;
   
    }

    public void trainEnd() {
        test = true;
    }
}
