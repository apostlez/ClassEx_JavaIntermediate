<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>주소록xsl</TITLE> </head>
  <body>
	<table border='1'>
	<tr><td>이름</td>
	<td>번호</td>
	<td>email</td>
	<td>휴대전화</td></tr>
	<xsl:apply-templates select="주소록/정보"/>
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="정보">
	<tr>
		<td><xsl:value-of select="이름" /></td>
		<td><xsl:value-of select="@주민번호" /></td>
		<td><xsl:value-of select="email" /></td>
		<td><xsl:copy-of select="휴대전화" /></td>
	</tr>
</xsl:template> 

</xsl:stylesheet>