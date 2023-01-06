import be.edu.bggclient.collection.CollectionEndpoint;
import be.edu.bggclient.internal.collection.CollectionBggApiEndpoint;

module be.edu.bgg.client {
    requires java.xml;
    requires java.net.http;
    requires dev.failsafe;
    requires org.slf4j;

    exports be.edu.bggclient;
    exports be.edu.bggclient.collection;
    exports be.edu.bggclient.geeklist;
    exports be.edu.bggclient.play;
    exports be.edu.bggclient.thing;

    provides CollectionEndpoint with CollectionBggApiEndpoint;

    uses CollectionEndpoint;
}
