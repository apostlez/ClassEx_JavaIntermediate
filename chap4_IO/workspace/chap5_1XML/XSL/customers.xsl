<?xml version="1.0" encoding="euc-kr"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:output method="xml"/>

   <xsl:template match="/">
       <phonelist>
           <xsl:apply-templates select="/customers/customer"/>        
       </phonelist>
   </xsl:template>

   <xsl:template match="customer">
        <item>
            <xsl:apply-templates select="name"/>
            <xsl:apply-templates select="phone"/>
        </item>
   </xsl:template>

   <xsl:template match="name|phone">
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
   </xsl:template>
</xsl:stylesheet>