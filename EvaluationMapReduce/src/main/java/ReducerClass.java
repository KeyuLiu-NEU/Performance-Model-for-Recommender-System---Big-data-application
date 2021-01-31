import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerClass extends Reducer<CompositeKeyWritable, CompositeValueWritable, Text, Text> {

    private Text personality = new Text();
    private Text performance = new Text();

    @Override
    protected void reduce(CompositeKeyWritable key, Iterable<CompositeValueWritable> values, Context context) throws IOException, InterruptedException {
        float averageAbsoluteDifference = 0.0f;
        int counter = 0;
        int Is_personalizedCounter = 0;
        int Enjoy_watchingCounter = 0;
        int assignedConditionCounter = 0;

        for (CompositeValueWritable val : values){
            //You can add weight for this
            //is_personalized = 1.7*is_personalized;
            //..0.3..
            Is_personalizedCounter += val.getIs_personalized();
            Enjoy_watchingCounter += val.getEnjoy_watching();
            assignedConditionCounter += val.getAssignedCondition();
            counter += 1;
//            assignedMetric = val.getAssignedMetric();

        }

        int absoluteDifference = Math.abs(Is_personalizedCounter+Enjoy_watchingCounter - assignedConditionCounter);
        averageAbsoluteDifference = absoluteDifference/counter;
        //        double openness = 0.0;
        //        double agreeableness = 0.0;
        //        double emotional_stability = 0.0;
        //        double conscientiousness = 0.0;
        //        double extraversion = 0.0;
//        long i = Long.parseLong(key.toString());
//        personality.set("openness: " + i/1000000000 + "agreeableness: " + i/10000000 + "emotional_stability: "+ i/100000 + "conscientiousness" + i/1000 + "extraversion: " + i/10);
        personality.set(key.toString());
        performance.set(","+ counter + "," + absoluteDifference+ ","+ averageAbsoluteDifference);

        context.write(personality,performance);

    }
}
