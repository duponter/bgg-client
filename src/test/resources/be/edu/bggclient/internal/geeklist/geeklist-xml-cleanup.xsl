<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes" indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@termsofuse | postdate_timestamp | editdate_timestamp">
    </xsl:template>

    <xsl:template match="item">
        <xsl:copy>
            <xsl:apply-templates select="@id"/>
            <xsl:apply-templates select="@objecttype"/>
            <xsl:apply-templates select="@subtype"/>
            <xsl:apply-templates select="@objectid"/>
            <xsl:apply-templates select="@objectname"/>
            <xsl:apply-templates select="@username"/>
            <xsl:apply-templates select="@postdate"/>
<!--            <xsl:apply-templates select="@postdate" mode="date"/>-->
            <xsl:apply-templates select="@editdate"/>
            <xsl:apply-templates select="@thumbs"/>
            <xsl:apply-templates select="@imageid"/>
            <xsl:apply-templates select="body"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="node()|@*" mode="date">
<!--        <xsl:value-of select="replace(current(), '+0000', 'GMT')"/>-->
    </xsl:template>
</xsl:stylesheet>
