<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>정렬</TITLE> </head>
  <body>
	<table border='1'><caption>정렬</caption>
	<tr><td>이름</td><td>학번</td><td>국어</td></tr>
	<xsl:apply-templates select="점수들" />
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="점수들">
	<xsl:for-each select="점수">
	<xsl:sort select="국어" data-type="number" order="descending"/>
	<xsl:if test="국어 > 70">
		<tr>
		<td><xsl:value-of select="@이름"/></td>
		<td><xsl:value-of select="@학번"/></td>
		<td><xsl:value-of select="국어"/></td>
		</tr>
	</xsl:if>
	</xsl:for-each>
</xsl:template>

</xsl:stylesheet>
