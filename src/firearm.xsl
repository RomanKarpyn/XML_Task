<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Firearms</h2>
                <table border="1">
                    <tr bgcol="#9acd32">
                        <th>model</th>
                        <th>origin</th>
                        <th>tts carry</th>
                        <th>sightRange</th>
                        <th>shackle</th>
                        <th>optics</th>
                        <th>handy</th>>
                        <th>materials</th>
                    </tr>
                    <xsl:for-each select="gun/firearm">
                        <tr>
                            <td>
                                <xsl:value-of select="model"/>
                            </td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:for-each select="tts">
                                    <xsl:value-of select="carry"/>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="tts">
                                    <xsl:value-of select="sightRange"/>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="tts">
                                    <xsl:value-of select="shackle"/>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:for-each select="tts">
                                    <xsl:value-of select="optics"/>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:value-of select="handy"/>
                            </td>
                            <td>
                                <xsl:value-of select="materials"/>
                            </td>
                        </tr>
                        <xsl:sort select="model"/>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>