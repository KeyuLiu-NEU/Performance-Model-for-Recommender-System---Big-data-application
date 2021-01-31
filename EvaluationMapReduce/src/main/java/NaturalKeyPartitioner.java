import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class NaturalKeyPartitioner extends Partitioner<CompositeKeyWritable, CompositeValueWritable> {

    public NaturalKeyPartitioner() {
    }

    @Override
    public int getPartition(CompositeKeyWritable compositeKeyWritable, CompositeValueWritable compositeValueWritable, int i) {
        return compositeKeyWritable.toString().hashCode()% i;
    }
}
