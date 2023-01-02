package be.edu.bggclient.internal.collection;

import be.edu.bggclient.BggClientError;
import be.edu.bggclient.BggClientException;
import be.edu.bggclient.collection.Collection;
import be.edu.bggclient.collection.CollectionEndpoint;
import be.edu.bggclient.collection.CollectionRequest;
import be.edu.bggclient.internal.BggApi;
import be.edu.bggclient.internal.BggEndpoint;

public final class CollectionBggApiEndpoint extends BggEndpoint implements CollectionEndpoint {
    public CollectionBggApiEndpoint() {
        super(BggApi.V2.create("collection"));
    }

    @Override
    public Collection send(CollectionRequest request) throws BggClientException {
        CollectionXmlNode xmlNode = new CollectionXmlNode(this.asNode(request).getFirstChild());
        try {
            return xmlNode.build();
        } catch (RuntimeException re) {
            // TODO_EDU check more specific exception
            throw new BggClientException(BggClientError.UNSUPPORTED_XML, re);
        }
    }
}
