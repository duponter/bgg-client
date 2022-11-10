<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes" indent="yes" method="xml"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@termsofuse | name/@sortindex | stats | privateinfo">
    </xsl:template>

    <xsl:template match="items">
        <xsl:copy>
            <xsl:apply-templates select="@totalitems"/>
            <xsl:apply-templates select="@pubdate"/>
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="item">
        <xsl:copy>
            <xsl:apply-templates select="@objecttype"/>
            <xsl:apply-templates select="@objectid"/>
            <xsl:apply-templates select="@subtype"/>
            <xsl:apply-templates select="@collid"/>
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="stats">
        <xsl:copy>
            <xsl:apply-templates select="@minplayers"/>
            <xsl:apply-templates select="@maxplayers"/>
            <xsl:apply-templates select="@minplaytime"/>
            <xsl:apply-templates select="@maxplaytime"/>
            <xsl:apply-templates select="@playingtime"/>
            <xsl:apply-templates select="@numowned"/>
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="rank">
        <xsl:copy>
            <xsl:apply-templates select="@type"/>
            <xsl:apply-templates select="@id"/>
            <xsl:apply-templates select="@name"/>
            <xsl:apply-templates select="@friendlyname"/>
            <xsl:apply-templates select="@value"/>
            <xsl:apply-templates select="@bayesaverage"/>
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="status">
        <xsl:copy>
            <xsl:apply-templates select="@own"/>
            <xsl:apply-templates select="@prevowned"/>
            <xsl:apply-templates select="@fortrade"/>
            <xsl:apply-templates select="@want"/>
            <xsl:apply-templates select="@wanttoplay"/>
            <xsl:apply-templates select="@wanttobuy"/>
            <xsl:apply-templates select="@wishlist"/>
            <xsl:apply-templates select="@wishlistpriority"/>
            <xsl:apply-templates select="@preordered"/>
            <xsl:apply-templates select="@lastmodified"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="privateinfo">
        <xsl:copy>
            <xsl:apply-templates select="@pp_currency" />
            <xsl:apply-templates select="@pricepaid" />
            <xsl:apply-templates select="@cv_currency" />
            <xsl:apply-templates select="@currvalue" />
            <xsl:apply-templates select="@quantity" />
            <xsl:apply-templates select="@acquisitiondate" />
            <xsl:apply-templates select="@acquiredfrom" />
            <xsl:apply-templates select="@inventorydate" />
            <xsl:apply-templates select="@inventorylocation" />
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
