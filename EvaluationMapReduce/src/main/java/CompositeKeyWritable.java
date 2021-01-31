import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompositeKeyWritable implements WritableComparable<CompositeKeyWritable> {
     private int openness;
     private int agreeableness;
     private int emotional_stability;
     private int conscientiousness;
     private int extraversion;

    public CompositeKeyWritable(){
    }


    public int getOpenness() {
        return openness;
    }

    public void setOpenness(int openness) {
        this.openness = openness;
    }

    public int getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(int agreeableness) {
        this.agreeableness = agreeableness;
    }

    public int getEmotional_stability() {
        return emotional_stability;
    }

    public void setEmotional_stability(int emotional_stability) {
        this.emotional_stability = emotional_stability;
    }

    public int getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(int conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public int getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(int extraversion) {
        this.extraversion = extraversion;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        //openness, agreeableness, emotional_stability, conscientiousness, extraversion
        dataOutput.writeInt(openness);
        dataOutput.writeInt(agreeableness);
        dataOutput.writeInt(emotional_stability);
        dataOutput.writeInt(conscientiousness);
        dataOutput.writeInt(extraversion);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        openness = dataInput.readInt();
        agreeableness = dataInput.readInt();
        emotional_stability = dataInput.readInt();
        conscientiousness = dataInput.readInt();
        extraversion = dataInput.readInt();


    }

    public int compareTo(CompositeKeyWritable o) {
        if (this.openness != o.openness)
            return this.openness - o.openness;
        if (this.agreeableness != o.agreeableness)
            return this.agreeableness - o.agreeableness;
        if (this.emotional_stability != o.emotional_stability)
            return this.emotional_stability - o.emotional_stability;
        if (this.conscientiousness != o.conscientiousness)
            return this.conscientiousness - o.conscientiousness;
        return this.extraversion - o.extraversion;
    }

    @Override
    public String toString() {
        return  openness +
                "," + agreeableness +
                "," + emotional_stability +
                "," + conscientiousness +
                "," + extraversion +
                ',';
    }
}
