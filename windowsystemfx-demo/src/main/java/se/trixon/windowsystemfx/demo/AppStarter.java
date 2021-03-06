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
package se.trixon.windowsystemfx.demo;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import se.trixon.windowsystemfx.WindowManager;

/**
 *
 * @author Patrik Karlström
 */
public class AppStarter extends Application {

    private BorderPane mRoot;
    private WindowManager mWindowManager = WindowManager.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        mWindowManager.init();
        mRoot = new BorderPane(mWindowManager.getRoot());
        mRoot.setTop(createToolBar());
        Scene scene = new Scene(mRoot);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN), () -> {
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        //scene.getStylesheets().add("main.css");

        stage.setTitle("WindowSystemFX Demo");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();
        stage.centerOnScreen();
    }

    private Node createToolBar() {
        ToolBar toolBar = new ToolBar();
        Button b1 = new Button("close L1A");
        Button b2 = new Button("Show Root");
        Button b3 = new Button("Show Only Center");

        b1.setOnAction((event) -> {
            System.out.println("TODO");
        });
        b2.setOnAction((event) -> {
            mWindowManager.showRoot();
        });
        b3.setOnAction((event) -> {
            mWindowManager.showOnlyWindowById("se.trixon.windowsystemfx.demo.windows.middleMiddle");
        });

        toolBar.getItems().addAll(
                b1,
                b2,
                b3
        );

        return toolBar;
    }
}
