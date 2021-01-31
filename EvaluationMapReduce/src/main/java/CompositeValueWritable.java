import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompositeValueWritable implements Writable {

    private int is_personalized = 0;
    private int enjoy_watching = 0;

    private String assignedMetric = null;
    private int assignedCondition = 0;

    public int getIs_personalized() {
        return is_personalized;
    }

    public void setIs_personalized(int is_personalized) {
        this.is_personalized = is_personalized;
    }

    public int getEnjoy_watching() {
        return enjoy_watching;
    }

    public void setEnjoy_watching(int enjoy_watching) {
        this.enjoy_watching = enjoy_watching;
    }

    public String getAssignedMetric() {
        return assignedMetric;
    }

    public void setAssignedMetric(String assignedMetric) {
        this.assignedMetric = assignedMetric;
    }

    public int getAssignedCondition() {
        return assignedCondition;
    }

    public void setAssignedCondition(int assignedCondition) {
        this.assignedCondition = assignedCondition;
    }

    @Override
    public String toString() {
        return "CompositeValueWritable{" +
                "is_personalized=" + is_personalized +
                ", enjoy_watching=" + enjoy_watching +
                ", assignedMetric='" + assignedMetric + '\'' +
                ", assignedCondition=" + assignedCondition +
                '}';
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        //openness, agreeableness, emotional_stability, conscientiousness, extraversion
        dataOutput.writeInt(is_personalized);
        dataOutput.writeInt(enjoy_watching);
        dataOutput.writeUTF(assignedMetric);
        dataOutput.writeInt(assignedCondition);


    }

    @Override
    public void readFields(DataInput input) throws IOException {
        is_personalized = input.readInt();
        enjoy_watching = input.readInt();
        assignedMetric = input.readUTF();
        assignedCondition = input.readInt();


    }
}
