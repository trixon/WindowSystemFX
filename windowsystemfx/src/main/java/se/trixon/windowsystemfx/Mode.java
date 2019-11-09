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
package se.trixon.windowsystemfx;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Patrik Karlström
 */
public abstract class Mode extends WindowSystemComponent {

    private SplitPane mSplitPane;
    private StackPane mStackPane;
    private TabPane mTabPane;

    public Mode() {
    }

    public void addMode(Mode mode) {
        getItems().add(mode.getRegion());
    }

    public void addModes(ArrayList<Mode> modes) {
        for (Mode mode : modes) {
            getItems().add(mode.getRegion());
        }
    }

    public void addWindow(Window window) {
        switch (getLayout()) {
            case SPLIT_HORIZONTAL:
            case SPLIT_VERTICAL:
            case STACK:
                getItems().add(window.getNode());
                break;

            case TABS:
                var tab = new WindowTab(window);

                getTabPane().getTabs().add(tab);
                tab.textProperty().bind(window.nameProperty());
                break;
        }
    }

    public void addWindows(ArrayList<Window> windows) {
        for (Window window : windows) {
            addWindow(window);
        }
    }

    public Region getRegion() {
        switch (getLayout()) {
            case SPLIT_HORIZONTAL:
            case SPLIT_VERTICAL:
                return getSplitPane();

            case STACK:
                return getStackPane();

            case TABS:
                return getTabPane();

            default:
                return null;
        }
    }

    private ObservableList<Node> getItems() {
        switch (getLayout()) {
            case SPLIT_HORIZONTAL:
            case SPLIT_VERTICAL:
                return getSplitPane().getItems();

            case STACK:
                return getStackPane().getChildren();

            default:
                return null;
        }
    }

    private SplitPane getSplitPane() {
        if (mSplitPane == null) {
            mSplitPane = new SplitPane();
            if (getLayout() == ModeLayout.SPLIT_HORIZONTAL) {
                mSplitPane.setOrientation(Orientation.HORIZONTAL);
            } else {
                mSplitPane.setOrientation(Orientation.VERTICAL);
            }

            mSplitPane.getStyleClass().clear();
            mSplitPane.getStyleClass().add("window-system-mode");
        }

        return mSplitPane;
    }

    private StackPane getStackPane() {
        if (mStackPane == null) {
            mStackPane = new StackPane();
        }

        return mStackPane;
    }

    private TabPane getTabPane() {
        if (mTabPane == null) {
            mTabPane = new TabPane();
            mTabPane.getStyleClass().add("window-system-tab-pane");
        }

        return mTabPane;
    }
}
