<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr' cdata-section-elements="td"/> 
<xsl:variable name="co" select="5"/>
<xsl:template match="/">
<html>
  <head><TITLE>정렬</TITLE> </head>
  <body>
	<table border='1'><caption>정렬</caption>
	<tr><td>이름</td><td>학번</td><td>국어</td>
	<td>총점</td><td>평균</td></tr>
	<xsl:apply-templates select="점수들/점수" >
		<xsl:sort select="sum(*)" data-type="number" order="descending"/>
	</xsl:apply-templates>
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="점수">
	<xsl:variable name="total" select="sum(*)"/>
	<tr>
	<td><xsl:value-of select="@이름"/></td>
	<td><xsl:value-of select="@학번"/></td>
	<td><xsl:value-of select="국어"/></td>
	<td><xsl:value-of select="$total"/></td>
	<td><xsl:value-of select="$total div $co"/></td>
	</tr>
</xsl:template>

</xsl:stylesheet>
