<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr' cdata-section-elements="td"/> 
<xsl:variable name="co" select="5"/>
<xsl:template match="/">
<html>
  <head><TITLE>����</TITLE> </head>
  <body>
	<table border='1'><caption>����</caption>
	<tr><td>�̸�</td><td>�й�</td><td>����</td>
	<td>����</td><td>���</td></tr>
	<xsl:apply-templates select="������/����" >
		<xsl:sort select="sum(*)" data-type="number" order="descending"/>
	</xsl:apply-templates>
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="����">
	<xsl:variable name="total" select="sum(*)"/>
	<tr>
	<td><xsl:value-of select="@�̸�"/></td>
	<td><xsl:value-of select="@�й�"/></td>
	<td><xsl:value-of select="����"/></td>
	<td><xsl:value-of select="$total"/></td>
	<td><xsl:value-of select="$total div $co"/></td>
	</tr>
</xsl:template>

</xsl:stylesheet>
