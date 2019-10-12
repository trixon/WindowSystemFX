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
package se.trixon.windowsystemfx.temp;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Patrik Karlström
 */
public class StaticWindowManager extends BorderPane {

    private final SplitPane mHorizontalSplitPane = new SplitPane();
    private final SplitPane mVerticalSplitPane = new SplitPane();
    private final WindowSectionLeft mWindowSectionLeft = new WindowSectionLeft();
    private final WindowSectionMiddle mWindowSectionMiddle = new WindowSectionMiddle();
    private final WindowSectionRight mWindowSectionRight = new WindowSectionRight();

    public StaticWindowManager() {
        mHorizontalSplitPane.setOrientation(Orientation.HORIZONTAL);
        mHorizontalSplitPane.getItems().setAll(
                mWindowSectionLeft,
                mWindowSectionMiddle,
                mWindowSectionRight
        );
        mHorizontalSplitPane.setDividerPositions(0.2f, 0.8f, 0.1f);

        Tab output = new Tab("OUTPUT");
        TabPane bottomTabPane = new TabPane(output);
//        setBottom(bottomTabPane);

        Tab top = new Tab("TOP");
        TabPane topTabPane = new TabPane(top);
//        setTop(topTabPane);

        mVerticalSplitPane.setOrientation(Orientation.VERTICAL);
        mVerticalSplitPane.getItems().setAll(
                topTabPane,
                mHorizontalSplitPane,
                bottomTabPane
        );

        setCenter(mVerticalSplitPane);
    }
}
