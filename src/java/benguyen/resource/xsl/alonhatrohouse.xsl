<?xml version="1.0" encoding="UTF-8"?>
<!--
    Document   : alonhatrohouse.xsl
    Created on : October 16, 2020, 3:49 PM
    Author     : Gabriel Nguyen
    Description:
        Purpose of transformation follows.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:template match="/">
        <houses>
            <xsl:for-each select="//article">
                <house>
                    <name>
                        <xsl:value-of select="div/a/header/h3[@class='P_Title']"/>
                    </name>
                    <address>
                        <xsl:value-of select="div/div/span[@class='Addrees address']/span"/>
                    </address>
                    <image>
                        <xsl:value-of select="div/@data-background-image"/>
                    </image>
                    <space>
                        <xsl:value-of select="div/div/span[@class='Area product-area']"/>
                    </space>
                    <price>
                        <xsl:value-of select="div/div/span[@class='Price']"/>
                    </price>
                    <benefits>
                        0
                    </benefits>
                    
                    <houselink>
                        <xsl:value-of select="div/a/@href"/>
                    </houselink>
                </house>
            </xsl:for-each>
        </houses>
    </xsl:template>
</xsl:stylesheet>
