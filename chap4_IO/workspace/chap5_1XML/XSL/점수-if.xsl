<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>����</TITLE> </head>
  <body>
	<table border='1'><caption>����</caption>
	<tr><td>�̸�</td><td>�й�</td><td>����</td></tr>
	<xsl:apply-templates select="������" />
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="������">
	<xsl:for-each select="����">
	<xsl:sort select="����" data-type="number" order="descending"/>
	<xsl:if test="���� > 70">
		<tr>
		<td><xsl:value-of select="@�̸�"/></td>
		<td><xsl:value-of select="@�й�"/></td>
		<td><xsl:value-of select="����"/></td>
		</tr>
	</xsl:if>
	</xsl:for-each>
</xsl:template>

</xsl:stylesheet>
