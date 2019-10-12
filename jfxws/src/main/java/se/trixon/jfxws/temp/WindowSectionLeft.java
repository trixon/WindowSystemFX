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
package se.trixon.jfxws.temp;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Patrik Karlström
 */
public class WindowSectionLeft extends WindowSectionBase {

    private final TabPane mTabPane0 = new TabPane();
    private final TabPane mTabPane1 = new TabPane();
    private final TabPane mTabPane2 = new TabPane();

    public WindowSectionLeft() {
        getItems().setAll(mTabPane0, mTabPane1, mTabPane2);

        Tab measureTab = new Tab("MEASURE");
        Tab temporalTab = new Tab("DATE");
        mTabPane0.getTabs().addAll(measureTab, temporalTab);

        Tab bookmarkTab = new Tab("BOOKMARKS");
        mTabPane1.getTabs().add(bookmarkTab);

        Tab layersTab = new Tab("LAYERS");
        Tab gridsTab = new Tab("GRIDS");
        mTabPane2.getTabs().addAll(layersTab, gridsTab);
    }
}