package be.edu.bggclient.internal.thing;

import java.util.stream.Collectors;

import be.edu.bggclient.internal.Builder;
import be.edu.bggclient.internal.xml.XmlNode;
import be.edu.bggclient.thing.Thing;
import org.w3c.dom.Node;

public class ThingXmlNode extends XmlNode implements Builder<Thing> {
    public ThingXmlNode(Node node) {
        super(node);
    }

    @Override
    public Thing build() {
        return new Thing.Builder()
                .withType(this.string("@type"))
                .withId(this.string("@id"))
                .withThumbnailUrl(this.string("thumbnail"))
                .withImageUrl(this.string("image"))
                .withNames(
                        this.nodes("name")
                                .map(NameXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .withDescription(this.string("description"))
                .withYearPublished(this.numericValueAttribute("yearpublished").intValue())
                .withMinPlayers(this.stringValueAttribute("minplayers"))
                .withMaxPlayers(this.stringValueAttribute("maxplayers"))
                .withSuggestedNumberOfPlayers(
                        this.nodes("poll[@name='suggested_numplayers']")
                                .map(PollXmlNode::new)
                                .map(PollXmlNode::buildPlayerCountPoll)
                                .findFirst().orElse(null)
                )
                .withPlayingTime(this.stringValueAttribute("playingtime"))
                .withMinPlayTime(this.stringValueAttribute("minplaytime"))
                .withMaxPlayTime(this.stringValueAttribute("maxplaytime"))
                .withMinAge(this.stringValueAttribute("minage"))
                .withSuggestedPlayerAge(
                        this.nodes("poll[@name='suggested_playerage']")
                                .map(PollXmlNode::new)
                                .map(PollXmlNode::buildPlayerAgePoll)
                                .findFirst().orElse(null)
                )
                .withLanguageDependence(
                        this.nodes("poll[@name='language_dependence']")
                                .map(PollXmlNode::new)
                                .map(PollXmlNode::buildLanguageDependencePoll)
                                .findFirst().orElse(null)
                )
                .withLinks(
                        this.nodes("link")
                                .map(LinkXmlNode::new)
                                .map(Builder::build)
                                .collect(Collectors.toList())
                )
                .withStatistics(
                        this.nodes("statistics/ratings")
                                .map(ThingStatisticsXmlNode::new)
                                .map(Builder::build)
                                .findFirst().orElse(null)
                )
                .build();
    }
}
