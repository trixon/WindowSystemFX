/*
 * Copyright 2019 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.windowsystemfx.demo.windows;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.openide.util.lookup.ServiceProvider;
import se.trixon.windowsystemfx.Window;

@Window.Description(
        iconBase = "",
        preferredID = "se.trixon.windowsystemfx.demo.windows.OutputID",
        modeId = "bottom"
)
@Window.Registration(
        openAtStartup = true,
        position = 1
)
/**
 *
 * @author Patrik Karlström
 */
@ServiceProvider(service = Window.class)
public class OutputWindow extends Window {

    private TextArea mNode;

    public OutputWindow() {
        setName("output window");
    }

    @Override
    public Node getNode() {
        if ((mNode == null)) {
            createUI();
        }

        return mNode;
    }

    private void createUI() {
        mNode = new TextArea("TextArea in " + getName());
    }
}
