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

import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
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
public class MiddleWindow extends Window {

    private SplitPane mNode;

    public MiddleWindow() {
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
        BorderPane bp = new BorderPane(new Label("center"));
        bp.setTop(new TextField("qwe"));
        bp.setLeft(getTabPane());
        bp.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            JTextArea ta = new JTextArea("swing");
            ta.setEditable(true);
            swingNode.setContent(ta);
        });
        bp.setBottom(swingNode);
        Label label = new Label("botom");
        mNode = new SplitPane(bp, new VBox(label));
        mNode.setOrientation(Orientation.VERTICAL);
        mNode.setId("www");
    }

    private TabPane getTabPane() {
        Tab tab1 = new Tab("1");
        tab1.setContent(new TextArea("q"));
        Tab tab2 = new Tab("2");
        Tab tab3 = new Tab("3");
        TabPane tabPane = new TabPane(tab1, tab2, tab3);

        return tabPane;
    }
}
