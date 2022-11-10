<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes" indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@termsofuse">
    </xsl:template>

    <xsl:template match="plays">
        <xsl:copy>
            <xsl:apply-templates select="@username"/>
            <xsl:apply-templates select="@userid"/>
            <xsl:apply-templates select="@total"/>
            <xsl:apply-templates select="@page"/>
            <xsl:apply-templates select="*" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="play">
        <xsl:copy>
            <xsl:apply-templates select="@id"/>
            <xsl:apply-templates select="@date"/>
            <xsl:apply-templates select="@quantity"/>
            <xsl:apply-templates select="@length"/>
            <xsl:apply-templates select="@incomplete"/>
            <xsl:apply-templates select="@nowinstats"/>
            <xsl:apply-templates select="@location"/>
            <xsl:apply-templates select="*" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="item">
        <xsl:copy>
            <xsl:apply-templates select="@name"/>
            <xsl:apply-templates select="@objecttype"/>
            <xsl:apply-templates select="@objectid"/>
            <xsl:apply-templates select="subtypes" />
        </xsl:copy>
    </xsl:template>

    <xsl:template match="player">
        <xsl:copy>
            <xsl:apply-templates select="@username"/>
            <xsl:apply-templates select="@userid"/>
            <xsl:apply-templates select="@name"/>
            <xsl:apply-templates select="@startposition"/>
            <xsl:apply-templates select="@color"/>
            <xsl:apply-templates select="@score"/>
            <xsl:apply-templates select="@new"/>
            <xsl:apply-templates select="@rating"/>
            <xsl:apply-templates select="@win"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
