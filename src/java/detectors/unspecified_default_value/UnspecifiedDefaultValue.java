package rules.unspecified_default_value;

import io.grpc.Channel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import java.net.URI;

class UnspecifiedDefaultValue {

    // {fact rule=unspecified-default-value@v1.0 defects=1}
    private Channel createChannelNonCompliant(URI uri) {
        // Noncompliant: default value for the port number is not specified.
        return NettyChannelBuilder.forAddress(uri.getHost(), uri.getPort())
            .negotiationType(NegotiationType.PLAINTEXT)
            .build();
    }
    // {/fact}

    // {fact rule=unspecified-default-value@v1.0 defects=0}
    private Channel createChannelCompliant(URI uri) {
        int port = uri.getPort();
        // Compliant: default value for the port number is specified.
        if (port == -1) {
            port = 11984;
        }
        return NettyChannelBuilder.forAddress(uri.getHost(), port)
            .negotiationType(NegotiationType.PLAINTEXT)
            .build();
    }
    // {/fact}
}
