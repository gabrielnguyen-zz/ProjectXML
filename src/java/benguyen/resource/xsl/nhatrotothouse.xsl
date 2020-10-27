<?xml version="1.0" encoding="UTF-8"?>
<!--
    Document   : nhatrotothouse.xsl
    Created on : October 16, 2020, 3:49 PM
    Author     : Gabriel Nguyen
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <houses>
            <xsl:for-each select="//div[@data-ng-if='rdL==false']">
                <house>
                    <name>
                        
                        <xsl:value-of select="div/div/a/h3"/>
                    </name>
                    <address>
                        <xsl:value-of select="div/div/div[@itemprop='addressLocality']"/>
                    </address>
                    <image>
                        <xsl:choose>
                            <xsl:when test="(a/img/@src)[contains(.,'http')]">
                                <xsl:value-of select="a/img/@src" />
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="a/img/@data-original"/>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:value-of select="a/img/@src"/>
                    </image>
                    <space>
                        <xsl:value-of select="div/div/div[@class='ri-ar']"/>
                    </space>
                    <price>
                        <xsl:value-of select="div/div[@itemprop='price']/text()"/>
                    </price>
                    <benefits>
                        0
                    </benefits>
                    <houselink>
                        <xsl:value-of select="@data-url"/>
                    </houselink>
                </house>
            </xsl:for-each>
        </houses>
    </xsl:template>
</xsl:stylesheet>
