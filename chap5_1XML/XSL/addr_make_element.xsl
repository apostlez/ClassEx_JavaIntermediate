<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>�ּҷ�xsl</TITLE> </head>
  <body>
	<table border='1'>
	<tr><td> </td><td>�̸�</td></tr>
	<xsl:apply-templates select="�ּҷ�/����" />
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="����">
	<xsl:call-template name="�̸�"/>
</xsl:template> 

<xsl:template name="�̸�">
	<tr>
	<td>
	<xsl:element name="IMG">
		<xsl:attribute name="SRC">righthand.gif</xsl:attribute>
	</xsl:element>
	</td>
	<td><xsl:value-of select="�̸�" /></td>
	</tr>
</xsl:template> 

</xsl:stylesheet>