import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class NaturalGroupingKeyComparator extends WritableComparator {

    public NaturalGroupingKeyComparator(){
        super(CompositeKeyWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CompositeKeyWritable ckw1 = (CompositeKeyWritable) a;
        CompositeKeyWritable ckw2 = (CompositeKeyWritable) b;

        if (ckw1.getOpenness() != ckw2.getOpenness())
            return ckw1.getOpenness() - ckw2.getOpenness();
        if (ckw1.getAgreeableness() != ckw2.getAgreeableness())
            return ckw1.getAgreeableness() - ckw2.getAgreeableness();
        if (ckw1.getEmotional_stability() != ckw2.getEmotional_stability() )
            return ckw1.getEmotional_stability()  - ckw2.getEmotional_stability() ;
        if (ckw1.getConscientiousness() != ckw2.getConscientiousness())
            return ckw1.getConscientiousness() - ckw2.getConscientiousness();
        return ckw1.getExtraversion() - ckw2.getExtraversion();
    }
}
