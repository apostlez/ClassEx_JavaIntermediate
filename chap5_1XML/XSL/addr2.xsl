<?xml version='1.0' encoding='euc-kr'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" encoding='euc-kr'/> 

<xsl:template match="/">
		<xsl:copy-of select="주소록/정보[@주민번호='a111-112']" />
</xsl:template> 

</xsl:stylesheet>