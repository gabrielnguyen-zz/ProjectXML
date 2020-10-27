<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : nhatrototbenefits.xsl
    Created on : October 26, 2020, 3:57 PM
    Author     : Gabriel Nguyen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <benefits>
            <xsl:variable name="document" select="//div[1 and @class='ri clearfix']"/>
            <xsl:for-each select="$document//div[@class='ri-fal-show']">
                <benefit>
                    <xsl:value-of select="span/text()"/>
                </benefit>
            </xsl:for-each>
        </benefits>
    </xsl:template>
</xsl:stylesheet>