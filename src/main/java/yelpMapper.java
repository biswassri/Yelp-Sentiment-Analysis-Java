import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class yelpMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String reviewText, businessId, sentimentReview;
        Text text = new Text();
        String input = value.toString();
        String[] fields = input.split(",");
        businessId = fields[2];
        reviewText =fields[5];

        sentimentReview = Float.toString(sentimentAnalyser.getRating(reviewText));
        context.write(new Text(businessId), new Text(sentimentReview));
    }

}