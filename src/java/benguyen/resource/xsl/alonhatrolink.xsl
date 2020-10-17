<?xml version="1.0" encoding="UTF-8"?>
<!--
    Document   : house.xsl
    Created on : October 16, 2020, 3:49 PM
    Author     : Gabriel Nguyen
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <houselinks>
            <xsl:for-each select="//article">
                <houselink>
                    <xsl:value-of select="div/a/@href"/>
                </houselink>
            </xsl:for-each>
        </houselinks>
    </xsl:template>
</xsl:stylesheet>
