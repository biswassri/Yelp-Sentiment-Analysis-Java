import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.Math;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class yelpReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        Float avgSentiment = 0f, totalSentiment =0f;
        Float count=0f;
        for (Text value: values){
            totalSentiment += Float.parseFloat(value.toString());
            count +=1;
        }
        avgSentiment = totalSentiment/count;
        System.out.println("total Sentiment"+totalSentiment);
        System.out.println("Average Sentiment"+avgSentiment);
        context.write(key, new Text(Float.toString(avgSentiment)));
    }
}
