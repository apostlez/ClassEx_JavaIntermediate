<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding='euc-kr'/> 

<xsl:template match="/">
<html>
  <head><TITLE>����ǥ</TITLE> </head>
  <body>
	<table border='1'><caption>�� �� ǥ</caption>
	<tr><td>�й�</td>
	<td>�̸�</td>
	<td>����</td>
	<td>����</td>
	<td>����</td>
	<td>��ȸ</td>
	<td>����</td></tr>
	<xsl:apply-templates select="������/����" />
	</table>
  </body>
</html>
</xsl:template> 

<xsl:template match="����">
		<tr>
		<td><xsl:value-of select="@�й�"/></td>
		<td><xsl:value-of select="@�̸�"/></td>
		<td><xsl:value-of select="����"/></td>
		<td><xsl:value-of select="����"/></td>
		<td><xsl:value-of select="����"/></td>
		<td><xsl:value-of select="��ȸ"/></td>
		<td><xsl:value-of select="����"/></td>
		</tr>
</xsl:template>

</xsl:stylesheet>
