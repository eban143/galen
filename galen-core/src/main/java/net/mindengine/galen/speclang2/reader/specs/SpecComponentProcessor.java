/*******************************************************************************
* Copyright 2015 Ivan Shubin http://mindengine.net
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package net.mindengine.galen.speclang2.reader.specs;

import net.mindengine.galen.parser.SyntaxException;
import net.mindengine.galen.specs.Spec;
import net.mindengine.galen.specs.SpecComponent;
import net.mindengine.galen.specs.reader.StringCharReader;

import java.io.File;

public class SpecComponentProcessor implements SpecProcessor {
    @Override
    public Spec process(StringCharReader reader, String contextPath) {
        SpecComponent spec = new SpecComponent();

        String childFilePath = reader.getTheRest().trim();

        if (reader.readWord().equals("frame")) {
            childFilePath = reader.getTheRest().trim();
            spec.setFrame(true);
        }
        if (childFilePath.isEmpty()) {
            throw new SyntaxException("File path to component spec is not specified");
        }

        String fullFilePath = childFilePath;
        if (contextPath != null) {
            fullFilePath = contextPath + File.separator + childFilePath;
        }

        spec.setSpecPath(fullFilePath);
        return spec;
    }
}
