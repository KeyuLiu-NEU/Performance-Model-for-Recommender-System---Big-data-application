import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class   MapperClass extends Mapper<LongWritable, Text, CompositeKeyWritable, CompositeValueWritable> {

//    private Text k = new Text();
    private CompositeKeyWritable compositeKeyWritable = new CompositeKeyWritable();
    private CompositeValueWritable compositeValueWritable = new CompositeValueWritable();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        double openness = 0.0;
        double agreeableness = 0.0;
        double emotional_stability = 0.0;
        double conscientiousness = 0.0;
        double extraversion = 0.0;

        int is_personalized = 0;
        int enjoy_watching = 0;
        int assignedCondition = 0;


        String line = value.toString();
        String[] tokens = line.split(",");
        try {
                openness = Double.parseDouble(tokens[1]);
                agreeableness = Double.parseDouble(tokens[2]);
                emotional_stability = Double.parseDouble(tokens[3]);
                conscientiousness = Double.parseDouble(tokens[4]);
                extraversion = Double.parseDouble(tokens[5]);

                is_personalized = Integer.parseInt(tokens[10]);
                enjoy_watching = Integer.parseInt(tokens[11]);
        } catch (NumberFormatException ex) {
        }

        if (tokens[7].equals("high"))
            assignedCondition = 9;
        // 11+1*0.75
        if (tokens[7].equals("medium"))
            assignedCondition = 6;
        //(int) ((11+1)*0.5);
        if(tokens[7].equals("low"))
            assignedCondition = 3;
        // 11+1*0.25;
        compositeValueWritable.setIs_personalized(is_personalized);
        compositeValueWritable.setEnjoy_watching(enjoy_watching);
        compositeValueWritable.setAssignedMetric(tokens[6]);
        compositeValueWritable.setAssignedCondition(assignedCondition);

        compositeKeyWritable.setOpenness((int) (openness*10));
        compositeKeyWritable.setAgreeableness((int) (agreeableness*10));
        compositeKeyWritable.setConscientiousness((int) (conscientiousness*10));
        compositeKeyWritable.setEmotional_stability((int) (emotional_stability*10));
        compositeKeyWritable.setExtraversion((int) (extraversion*10));

//        long personality = 1000000000*openness+10000000*agreeableness+100000*emotional_stability+1000*conscientiousness+10*extraversion;
//        k.set(String.valueOf(personality));

        context.write(compositeKeyWritable, compositeValueWritable);
    }
}
