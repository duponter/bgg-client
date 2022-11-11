<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes" indent="yes" method="xml"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="@termsofuse | name | link | @page">
    </xsl:template>

    <xsl:template match="items">
        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="item">
        <xsl:copy>
            <xsl:apply-templates select="@type"/>
            <xsl:apply-templates select="@id"/>
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="poll">
        <xsl:copy>
            <xsl:apply-templates select="@name"/>
            <xsl:apply-templates select="@title"/>
            <xsl:apply-templates select="@totalvotes"/>
            <xsl:apply-templates select="results[1]"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="results[1]">
        <xsl:copy>
            <xsl:apply-templates select="*"/>
            <xsl:apply-templates select="../results[position() > 1]"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="results[position() > 1]">
        <xsl:apply-templates select="*"/>
    </xsl:template>

    <xsl:template match="results[@numplayers]/result">
        <xsl:copy>
            <xsl:apply-templates select="../@numplayers"/>
            <xsl:apply-templates select="@value"/>
            <xsl:apply-templates select="@numvotes"/>
            <xsl:apply-templates select="*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="result">
        <xsl:copy>
            <xsl:apply-templates select="@level"/>
            <xsl:apply-templates select="@value"/>
            <xsl:apply-templates select="@numvotes"/>
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
</xsl:stylesheet>
