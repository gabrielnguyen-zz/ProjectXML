<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : alonhatrohousebenefits.xsl
    Created on : October 26, 2020, 3:57 PM
    Author     : Gabriel Nguyen
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <benefits>
            <xsl:for-each select="//div[@class='content faciliti_rent']/div[@class='col-xs-6 col-md-3 col-lg-3']">
                <benefit>
                    <xsl:value-of select="text()"/>
                </benefit>
            </xsl:for-each>
        </benefits>
    </xsl:template>
</xsl:stylesheet>