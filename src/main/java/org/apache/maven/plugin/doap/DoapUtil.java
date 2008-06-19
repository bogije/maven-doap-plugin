package org.apache.maven.plugin.doap;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.codehaus.plexus.util.StringUtils;
import org.codehaus.plexus.util.xml.XMLWriter;
import org.codehaus.plexus.util.xml.XmlWriterUtil;

/**
 * Utility class for DOAP mojo.
 *
 * @author <a href="mailto:vincent.siveton@gmail.com">Vincent Siveton</a>
 * @version $Id$
 */
public class DoapUtil
{
    private static final String RDF_RESOURCE = "rdf:resource";

    /**
     * Write comments in the DOAP file header
     *
     * @param writer not null
     */
    public static void writeHeader( XMLWriter writer )
    {
        XmlWriterUtil.writeLineBreak( writer );

        XmlWriterUtil.writeCommentLineBreak( writer );
        XmlWriterUtil.writeComment( writer, StringUtils.repeat( "=", 21 ) + " - DO NOT EDIT THIS FILE! - "
            + StringUtils.repeat( "=", 21 ) );
        XmlWriterUtil.writeCommentLineBreak( writer );
        XmlWriterUtil.writeComment( writer, " " );
        XmlWriterUtil.writeComment( writer, "Any modifications will be overwritten." );
        XmlWriterUtil.writeComment( writer, " " );
        DateFormat dateFormat = DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT, Locale.US );
        XmlWriterUtil.writeComment( writer, "Generated by Maven Doap Plugin on "
            + dateFormat.format( new Date( System.currentTimeMillis() ) ) );
        XmlWriterUtil.writeComment( writer, "See: http://maven.apache.org/plugins/maven-doap-plugin/" );
        XmlWriterUtil.writeComment( writer, " " );
        XmlWriterUtil.writeCommentLineBreak( writer );

        XmlWriterUtil.writeLineBreak( writer );
    }

    /**
     * @param writer not null
     * @param name not null
     * @param value could be null. In this case, the element is not written.
     * @throws IllegalArgumentException if name is null or empty
     */
    public static void writeElement( XMLWriter writer, String name, String value )
        throws IllegalArgumentException
    {
        if ( StringUtils.isEmpty( name ) )
        {
            throw new IllegalArgumentException( "name should be defined" );
        }

        if ( value != null )
        {
            writer.startElement( name );
            writer.writeText( value );
            writer.endElement();
        }
    }

    /**
     * @param writer not null
     * @param name not null
     * @param value could be null. In this case, the element is not written.
     * @throws IllegalArgumentException if name is null or empty
     */
    public static void writeRdfResourceElement( XMLWriter writer, String name, String value )
        throws IllegalArgumentException
    {
        if ( StringUtils.isEmpty( name ) )
        {
            throw new IllegalArgumentException( "name should be defined" );
        }

        if ( value != null )
        {
            writer.startElement( name );
            writer.addAttribute( RDF_RESOURCE, value );
            writer.endElement();
        }
    }
}