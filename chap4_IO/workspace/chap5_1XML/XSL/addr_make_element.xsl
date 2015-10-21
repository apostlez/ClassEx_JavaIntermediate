<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>주소록xsl</TITLE> </head>
  <body>
	<table border='1'>
	<tr><td> </td><td>이름</td></tr>
	<xsl:apply-templates select="주소록/정보" />
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="정보">
	<xsl:call-template name="이름"/>
</xsl:template> 

<xsl:template name="이름">
	<tr>
	<td>
	<xsl:element name="IMG">
		<xsl:attribute name="SRC">righthand.gif</xsl:attribute>
	</xsl:element>
	</td>
	<td><xsl:value-of select="이름" /></td>
	</tr>
</xsl:template> 

</xsl:stylesheet>