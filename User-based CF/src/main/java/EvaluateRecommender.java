import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;

public class EvaluateRecommender {
    public static void main(String[] args) throws IOException, TasteException {
        // TODO Auto-generated method stub
        CustomFileDataModel model = new CustomFileDataModel(new File("E:/INFO7250/ratings.csv"));
//        DataModel model = new FileDataModel(new File("E:/INFO7250/ratings.csv"));
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        //A RecommenderEvaluator which computes the average absolute difference between predicted and actual ratings

        RecommenderBuilder builder = dataModel -> {
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);

            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);

//            return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
                return new GenericItemBasedRecommender(dataModel, itemSimilarity);
        };

//        model.memoryIDMigrator.toStringID(recommendation.getItemID());
        double result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
        System.out.println(result);
    }
}