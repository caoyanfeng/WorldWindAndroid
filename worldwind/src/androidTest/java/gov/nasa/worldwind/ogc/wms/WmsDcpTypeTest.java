/*
 * Copyright (c) 2016 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package gov.nasa.worldwind.ogc.wms;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import gov.nasa.worldwind.util.xml.XmlPullParserContext;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class WmsDcpTypeTest {

    @Test
    public void testDcpType_ParseAndReadSampleWms() throws Exception {

        // Sample XML
        String xml = "<DCPType xmlns=\"http://www.opengis.net/wms\">\n" +
            "\n" +
            "<HTTP>\n" +
            "\n" +
            "<Get>\n" +
            "\n" +
            "<OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
            "\n" +
            "xlink:type=\"simple\"\n" +
            "\n" +
            "xlink:href=\"http://hostname/path?\" />\n" +
            "\n" +
            "</Get>\n" +
            "\n" +
            "<Post>\n" +
            "\n" +
            "<OnlineResource xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n" +
            "\n" +
            "xlink:type=\"simple\"\n" +
            "\n" +
            "xlink:href=\"http://hostname/path?\" />\n" +
            "\n" +
            "</Post>\n" +
            "\n" +
            "</HTTP>\n" +
            "\n" +
            "</DCPType>";
        // Initialize the context and basic model
        XmlPullParserContext context = new XmlPullParserContext(XmlPullParserContext.DEFAULT_NAMESPACE);
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        context.setParserInput(is);
        WmsDcpType elementModel = new WmsDcpType(XmlPullParserContext.DEFAULT_NAMESPACE);
        Object o;

        do {
            o = elementModel.read(context);
        } while (o != null);

        assertEquals("test DCP protocol", "HTTP", elementModel.getDcpInfos().get(0).protocol);
        assertEquals("test DCP method", "Get", elementModel.getDcpInfos().get(0).method);
        assertEquals("test DCP protocol", "HTTP", elementModel.getDcpInfos().get(1).protocol);
        assertEquals("test DCP method", "Post", elementModel.getDcpInfos().get(1).method);
        assertEquals(
            "test DCP resource url",
            "http://hostname/path?",
            elementModel.getDcpInfos().get(0).onlineResource.getHref());
    }
}
