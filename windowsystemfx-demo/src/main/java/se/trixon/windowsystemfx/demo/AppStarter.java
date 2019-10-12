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
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.trixon.windowsystemfx.WindowManager;
import se.trixon.windowsystemfx.temp.StaticWindowManager;

/**
 *
 * @author Patrik Karlström
 */
public class AppStarter extends Application {

    private WindowManager mWindowManager = WindowManager.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        Scene myScene = new Scene(mWindowManager.getRootPane());

        Scene myScene = new Scene(new StaticWindowManager());
        primaryStage.setTitle("WindowSystemFX Demo");
        primaryStage.setScene(myScene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
