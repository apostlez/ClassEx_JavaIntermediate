<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>성적표</TITLE> </head>
  <body>
	<table border='1'><caption>성 적 표</caption>
	<tr><td>학번</td>
	<td>이름</td>
	<td>국어</td>
	<td>영어</td>
	<td>수학</td>
	<td>사회</td>
	<td>지리</td></tr>
	<xsl:apply-templates select="점수들/점수" />
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="점수">
		<tr>
		<td><xsl:value-of select="@학번"/></td>
		<td><xsl:value-of select="@이름"/></td>
		<td><xsl:value-of select="국어"/></td>
		<td><xsl:value-of select="영어"/></td>
		<td><xsl:value-of select="수학"/></td>
		<td><xsl:value-of select="사회"/></td>
		<td><xsl:value-of select="지리"/></td>
		</tr>
</xsl:template>

</xsl:stylesheet>
