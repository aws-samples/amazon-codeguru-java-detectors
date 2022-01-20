package detectors.batches_preferred_over_loops;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class BatchesPreferredOverLoops {

    // {fact rule=batches-preferred-over-loops@v1.0 defects=1}
    public void deleteObjectsNoncompliant(AmazonS3 s3Client, List<DeleteObjectsRequest.KeyVersion> keys,
                                          String bucketName) throws SdkClientException {
        // Noncompliant: creates a separate request per item.
        for (final DeleteObjectsRequest.KeyVersion key : keys) {
            final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key.getKey());
            s3Client.deleteObject(deleteObjectRequest);
        }
    }
    // {/fact}

    // {fact rule=batches-preferred-over-loops@v1.0 defects=0}
    public void deleteObjectsCompliant(AmazonS3 s3Client, List<DeleteObjectsRequest.KeyVersion> keys,
                                       String bucketName) throws SdkClientException {
        if(keys.size() <= 1000) {
            // Compliant: uses the batch operation instead of creating a separate request per item.
            final DeleteObjectsRequest deleteObjectRequest = new DeleteObjectsRequest(bucketName).withKeys(keys);
            DeleteObjectsResult deleteObjectsResult = s3Client.deleteObjects(deleteObjectRequest);
            final int numDeleted = deleteObjectsResult.getDeletedObjects().size();
            log.info("Successfully deleted: " + numDeleted);
        } else {
            // To delete more than 1000 objects, deleteObjects needs to be called more than once.
            throw new IllegalArgumentException("Need multiple batch calls for >1000 objects.");
        }
    }
    // {/fact}
}
