<?xml version="1.0" encoding="UTF-8"?>
<!--
    Document   : space.xsl
    Created on : October 17, 2020, 3:52 PM
    Author     : Gabriel Nguyen
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <spaces>
            <xsl:for-each select="//li[@class='dtich-item ']">
                <space>                  
                    <xsl:value-of select="a/div"/>
                </space>
            </xsl:for-each>
        </spaces>
    </xsl:template>
</xsl:stylesheet>
