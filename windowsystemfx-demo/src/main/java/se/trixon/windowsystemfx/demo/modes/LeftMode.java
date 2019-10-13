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
package se.trixon.windowsystemfx.demo.modes;

import org.openide.util.lookup.ServiceProvider;
import se.trixon.windowsystemfx.Mode;
import se.trixon.windowsystemfx.ModeLayout;
import se.trixon.windowsystemfx.WindowSystemComponent;

@WindowSystemComponent.Description(
        preferredId = LeftMode.ID,
        parentId = "middle",
        modeLayout = ModeLayout.SPLIT_VERTICAL,
        position = 1
)
/**
 *
 * @author Patrik Karlström
 */
@ServiceProvider(service = Mode.class)
public class LeftMode extends Mode {

    static final String ID = "middleLeft";

    @WindowSystemComponent.Description(
            preferredId = "middleLeft1",
            parentId = LeftMode.ID,
            modeLayout = ModeLayout.TABS,
            position = 1
    )
    @ServiceProvider(service = Mode.class)
    public static class MiddleLeftMode1 extends Mode {

    }

    @WindowSystemComponent.Description(
            preferredId = "middleLeft2",
            parentId = LeftMode.ID,
            modeLayout = ModeLayout.TABS,
            position = 2
    )
    @ServiceProvider(service = Mode.class)
    public static class MiddleLeftMode2 extends Mode {

    }

    @WindowSystemComponent.Description(
            preferredId = "middleLeft3",
            parentId = LeftMode.ID,
            modeLayout = ModeLayout.TABS,
            position = 3
    )
    @ServiceProvider(service = Mode.class)
    public static class MiddleLeftMode3 extends Mode {

    }

}
