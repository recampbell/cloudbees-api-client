/*
 * Copyright 2010-2011, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudbees.api.util;

import com.cloudbees.api.ApplicationInfo;
import com.cloudbees.api.ApplicationListResponse;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * @author Olivier Lamy
 */
public class XmlResponseGenerator
{
    public static String applicationListResponse()
    {
        ApplicationListResponse applicationListResponse = new ApplicationListResponse();
        ApplicationInfo applicationInfo =
            new ApplicationInfo( "foo", "nice application", new Date(), "running", new String[]{ "http://foo.bar" } );

        applicationListResponse.getApplications().add( applicationInfo );
        XStream xstream = new XStream();

        return xstream.toXML( applicationListResponse );
    }

}
