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
package se.trixon.windowsystemfx.test;

import se.trixon.windowsystemfx.internal.DraggingTabPaneSupport;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Patrik Karlström
 */
public class DraggingTabPaneExample extends Application {

    private final Random rng = new Random();

    @Override
    public void start(Stage primaryStage) {
        TabPane[] panes = new TabPane[]{new TabPane(), new TabPane(), new TabPane()};
        VBox root = new VBox(10, panes);
        for (int i = 1; i <= 15; i++) {
            Tab tab = new Tab("Tab " + i);
            tab.setGraphic(new Rectangle(16, 16, randomColor()));
            Region region = new Region();
            region.setMinSize(100, 150);
            tab.setContent(region);
            panes[(i - 1) % panes.length].getTabs().add(tab);
        }

        DraggingTabPaneSupport support1 = new DraggingTabPaneSupport();
        support1.addSupport(panes[0]);
        support1.addSupport(panes[1]);
        DraggingTabPaneSupport support2 = new DraggingTabPaneSupport();
        support2.addSupport(panes[2]);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Color randomColor() {
        return Color.rgb(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
