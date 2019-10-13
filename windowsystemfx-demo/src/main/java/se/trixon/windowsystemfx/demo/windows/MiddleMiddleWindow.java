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

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.openide.util.lookup.ServiceProvider;
import se.trixon.windowsystemfx.Window;
import se.trixon.windowsystemfx.WindowSystemComponent;

@WindowSystemComponent.Description(
        iconBase = "",
        preferredId = "se.trixon.windowsystemfx.demo.windows.middleMiddle",
        parentId = "middleMiddle",
        position = 2
)
/**
 *
 * @author Patrik Karlström
 */
@ServiceProvider(service = Window.class)
public class MiddleMiddleWindow extends Window {

    private BorderPane mNode;

    public MiddleMiddleWindow() {
        setName("middleMiddle window");
    }

    @Override
    public Node getNode() {
        if ((mNode == null)) {
            createUI();
        }

        return mNode;
    }

    private void createUI() {
        mNode = new BorderPane(new Label("center"));
        mNode.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
