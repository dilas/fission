package unit;

import com.objectaware.fission.model.Feed;
import com.objectaware.fission.model.FeedType;
import org.junit.Assert;
import org.junit.Test;

public class FeedTest {
    @Test
    public void testFeedType() {
        Feed feed = new Feed();
        Assert.assertEquals(FeedType.SIMPLE, feed.getFeedType());
    }
}
